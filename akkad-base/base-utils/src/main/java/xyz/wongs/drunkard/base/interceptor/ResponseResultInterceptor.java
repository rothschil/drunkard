package xyz.wongs.drunkard.base.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import xyz.wongs.drunkard.base.message.annoation.ResponseResult;

import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author WCNGS@QQ.COM
 * @Description 请求的拦截器
 * @Github <a>https://github.com/rothschil</a>
 * @date 20/10/30 22:08
 * @Version 1.0.0
 */
@Component
public class ResponseResultInterceptor implements HandlerInterceptor {

    private static final String RESPONSE_RESULT_ANN = "RESPONSE-RESULT-ANN";

    @Override
    public boolean preHandle(@Nullable HttpServletRequest request, @Nullable HttpServletResponse response, @Nullable Object handler) {
        Assert.notNull(request,"The request must not be null");
        Assert.notNull(response,"The response must not be null");
        try {
            if (handler instanceof HandlerMethod) {
                final HandlerMethod handlerMethod = (HandlerMethod) handler;
                final Class<?> clazz = handlerMethod.getBeanType();
                final Method method = handlerMethod.getMethod();
                if (clazz.isAnnotationPresent(ResponseResult.class)) {
                    request.setAttribute(RESPONSE_RESULT_ANN, clazz.getAnnotation(ResponseResult.class));
                } else if (method.isAnnotationPresent(ResponseResult.class)) {
                    request.setAttribute(RESPONSE_RESULT_ANN, method.getAnnotation(ResponseResult.class));
                }
            }
        } catch (Exception e){
        }
        return true;
    }

}
