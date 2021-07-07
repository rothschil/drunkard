package xyz.wongs.drunkard.base.aop;

import cn.hutool.core.util.URLUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import xyz.wongs.drunkard.base.aop.annotion.ApplicationLog;
import xyz.wongs.drunkard.base.aop.pojo.OperationLog;
import xyz.wongs.drunkard.base.handler.impl.QueueTaskHandler;
import xyz.wongs.drunkard.base.queue.AppLogQueue;
import xyz.wongs.drunkard.base.utils.DateUtils;
import xyz.wongs.drunkard.base.utils.IpUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.Instant;
import java.util.Date;

/**
 * @Description 应用全局日志APO 异步日志，正常下执行次序是：@Around @Before ${METHOD} @Around @After @AfterReturning；异常下执行次序是：@Around @Before ${METHOD} @After @AfterThrowing;
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 20/12/2 10:23
 * @Version 1.0.0
*/
@Slf4j
@Aspect
@Component
public class ApplicationLogAop {

    private final ThreadLocal<OperationLog> threadLocal = new ThreadLocal<>();

    @Autowired
    private QueueTaskHandler queueTaskHandler;

    @Autowired
    private AppLogQueue appLogQueue;

    @Pointcut(value = "@annotation(xyz.wongs.drunkard.base.aop.annotion.ApplicationLog)")
    public void cutService() {}

    @Before(value = "cutService()")
    public void before(JoinPoint joinPoint) throws InterruptedException{
        ApplicationLog applicationLog = getApplicationLog(joinPoint);
        if(null==applicationLog){
            return ;
        }
        OperationLog operationLog = getOperationLog(applicationLog,joinPoint);
        threadLocal.set(operationLog);
    }

    @AfterReturning(returning = "ret",pointcut = "cutService()")
    public void afterReturning(Object ret){
        doFinal(ret,null);
    }

    @AfterThrowing(value ="cutService()", throwing = "e")
    public void afterThrowing(Exception e) {
        doFinal(null,e);
    }

    protected void doFinal(Object ret, Exception e){
        int success = 0;
        Date endTime = Date.from(Instant.now());
        OperationLog operationLog = threadLocal.get();
        if(null!=e){
            success=-1;
            operationLog.setErrMsg(e.getMessage());
        }
        if(null!=ret){
            operationLog.setRespContent(JSON.toJSONString(ret));
        }
        operationLog.setEndTime(endTime);
        operationLog.setIsSuccess(success);
        operationLog.setCost(DateUtils.getMills(operationLog.getBeginTime(),endTime));
        threadLocal.remove();
        queueTaskHandler.setOperationLog(operationLog);
        appLogQueue.addQueue(queueTaskHandler);
    }

    protected OperationLog getOperationLog(ApplicationLog applicationLog, JoinPoint joinPoint){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) requestAttributes;
        Assert.notNull(sra,"The ServletRequestAttributes must not be null");
        HttpServletRequest request = sra.getRequest();
        //获取拦截的方法名
        Date beginTime = Date.from(Instant.now());
        String methodName = joinPoint.getSignature().getName();
        String businessName = applicationLog.value();
        String key = applicationLog.key();
        // 类名
        String className =joinPoint.getTarget().getClass().getName();
        // 参数
        Object[] params = joinPoint.getArgs();
        OperationLog.OperationLogBuilder opt = OperationLog.builder();

        opt.className(className).methodName(methodName).logName(businessName).logType(key)
                .ipAddress(IpUtils.getIpAddr(request)).actionUrl(URLUtil.getPath(request.getRequestURI()))
                .requestMethod(request.getMethod()).userAgent(request.getHeader("user-agent"))
                .beginTime(beginTime).reqContent(JSON.toJSONString(params));
        return opt.build();
    }


    private static ApplicationLog getApplicationLog(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method != null){
            return method.getAnnotation(ApplicationLog.class);
        }
        return null;
    }

}
