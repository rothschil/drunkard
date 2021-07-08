package xyz.wongs.drunkard.base.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import xyz.wongs.drunkard.base.message.annoation.ResponseResult;
import xyz.wongs.drunkard.base.message.response.Result;

import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description 消息返回体
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 20/11/10 09:28
 * @Version 1.0.0
*/
@ControllerAdvice(basePackages = "xyz.wongs")
public class ResponseResultHandler implements ResponseBodyAdvice<Object> {

    private static final Logger LOG = LoggerFactory.getLogger(ResponseResultHandler.class);

    private static final String RESPONSE_RESULT_ANN = "RESPONSE-RESULT-ANN";

    /**
     * @Description 判断是否要执行 beforeBodyWrite 方法，true为执行，false不执行，有注解标记的时候处理返回值
     * @param returnType 返回类型
     * @param converterType 转换类型
     * @return boolean
     * @date 20/11/13 10:50
     */
    @Override
    public boolean supports(@Nullable MethodParameter returnType, @Nullable Class<? extends HttpMessageConverter<?>> converterType) {
        ServletRequestAttributes sra =(ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        Assert.notNull(sra,"The ServletRequestAttributes must not be null");
        HttpServletRequest request = sra.getRequest();
        ResponseResult responseResult = (ResponseResult)request.getAttribute(RESPONSE_RESULT_ANN);
        return responseResult==null;
    }

    @Override
    public Object beforeBodyWrite(Object body,@Nullable MethodParameter returnType, @Nullable MediaType selectContentType, Class<? extends HttpMessageConverter<?>> selectConverterType, @Nullable ServerHttpRequest request,@Nullable ServerHttpResponse response) {
        LOG.error(" ENTER MSG .... Excuse");
        if(body instanceof Result){
            return body;
        }
        return Result.success(body);
    }
}
