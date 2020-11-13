package xyz.wongs.drunkard.base.aop;

import cn.hutool.core.util.URLUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import xyz.wongs.drunkard.base.aop.annotion.ApplicationLog;
import xyz.wongs.drunkard.base.aop.pojo.OperationLog;
import xyz.wongs.drunkard.base.aop.service.OperationLogService;
import xyz.wongs.drunkard.base.utils.DateUtils;
import xyz.wongs.drunkard.base.utils.IpUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.Instant;
import java.util.Date;

/**
 * @ClassName LogAop
 * @Description 应用全局日志APO 异步日志，
 * 正常下执行次序是：@Around @Before ${METHOD} @Around @After @AfterReturning
 * 异常下执行次序是：@Around @Before ${METHOD} @After @AfterThrowing;
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2019/10/29 16:44
 * @Version 1.0.0
*/
@Slf4j
@Aspect
@Component
public class ApplicationLogAop {

    @Autowired
    private OperationLogService operationLogService;

    private ThreadLocal<OperationLog> threadLocal = new ThreadLocal<OperationLog>();

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

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

//    @After(value ="cutService()")
//    public void after(JoinPoint point) throws Throwable {
//        Date endTime = Date.from(Instant.now());
//        OperationLog operationLog = threadLocal.get();
//        operationLog.setEndTime(endTime);
//        threadLocal.set(operationLog);
//    }

    @AfterReturning(returning = "ret",pointcut = "cutService()")
    public void afterReturning(Object ret){
        doFinal(null,ret,null);
    }

    @AfterThrowing(value ="cutService()", throwing = "e")
    public void afterThrowing(JoinPoint point, Exception e) throws Throwable {
        doFinal(point,null,e);
    }

    protected void doFinal(JoinPoint point,Object ret, Exception e){
        int sucess = 0;
        Date endTime = Date.from(Instant.now());
        OperationLog operationLog = threadLocal.get();
        if(null!=e){
            sucess=1;
            operationLog.setErrMsg(e.getMessage());
        }
        if(null!=ret){
            operationLog.setRespContent(JSON.toJSONString(ret));
        }
        operationLog.setEndTime(endTime);
        operationLog.setIsSuccess(sucess);
        operationLog.setCost(DateUtils.getMills(operationLog.getBeginTime(),endTime));
        //通过线 执行日志保存
        threadPoolTaskExecutor.execute(new SaveLogThread(operationLog, operationLogService));
    }

    protected OperationLog getOperationLog(ApplicationLog applicationLog, JoinPoint joinPoint){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = sra.getRequest();
        //获取拦截的方法名
        Date beginTime = Date.from(Instant.now());
        String methodName = joinPoint.getSignature().getName();
        String bussinessName = applicationLog.value();
        String key = applicationLog.key();
        // 类名
        String className =joinPoint.getTarget().getClass().getName();
        // 参数
        Object[] params = joinPoint.getArgs();
        OperationLog.OperationLogBuilder opt = OperationLog.builder();

        opt.className(className).methodName(methodName).logName(bussinessName).logType(key)
                .ipAddr(IpUtils.getIpAddr(request)).actionUrl(URLUtil.getPath(request.getRequestURI()))
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

    /**
     * @ClassName ApplicationLogAop
     * @Description 日志更新线程
     * @author WCNGS@QQ.COM
     * @Github <a>https://github.com/rothschil</a>
     * @date 2019/12/24 15:51
     * @Version 1.0.0
    */
    private static class SaveLogThread implements Runnable{
        private OperationLog operationLog;
        private OperationLogService operationLogService;

        public SaveLogThread() {
        }

        public SaveLogThread(OperationLog operationLog, OperationLogService operationLogService) {
            this.operationLog = operationLog;
            this.operationLogService = operationLogService;
        }

        @Override
        public void run() {
            operationLogService.insert(operationLog);
        }
    }

}