package xyz.wongs.drunkard.base.message.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xyz.wongs.drunkard.base.message.enums.ResultCode;
import xyz.wongs.drunkard.base.message.response.ErrorResult;

import javax.validation.ConstraintViolationException;

/**
 * @author WCNGS@QQ.COM
 * @Description 全局异常处理Handler
 * @Github <a>https://github.com/rothschil</a>
 * @date 2019/9/23 15:03
 * @Version 1.0.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * @return ErrorResult
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description 参数校验不通过
     * @Date 2021/7/8-10:18
     * @Param ex
     **/
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseBody
    public ErrorResult handleConstraintViolationException(ConstraintViolationException ex) {
        LOG.error("ConstraintViolationException msg:{}", ex.getMessage());
        return ErrorResult.fail(ResultCode.PARAMS_IS_INVALID, ex);
    }

    /**
     * @return ErrorResult
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description 自定义异常
     * @Date 2021/7/8-10:18
     * @Param ex
     **/
    @org.springframework.web.bind.annotation.ExceptionHandler(DrunkardException.class)
    @ResponseBody
    public ErrorResult handleWeathertopException(DrunkardException ex) {
        LOG.error("WeathertopRuntimeException code:{},msg:{}", ex.getCode(), ex.getMessage());
        return ErrorResult.fail(ex.getCode(), ex.getMessage());
    }

    /**
     * @return ErrorResult
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description 拦截抛出的异常，@ResponseStatus：用来改变响应状态码
     * @Date 2021/7/8-10:19
     * @Param e
     **/
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public ErrorResult handlerThrowable(Throwable e) {
        LOG.error("发生未知异常！原因是: ", e);
        return ErrorResult.fail(ResultCode.RUNTIME_EXCEPTION, e);
    }

    /**
     * @return ErrorResult
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description 参数校验异常
     * @Date 2021/7/8-10:20
     * @Param e
     **/
    @ExceptionHandler(BindException.class)
    public ErrorResult handleBindException(BindException e) {
        LOG.error("发生参数校验异常！原因是：", e);
        return ErrorResult.fail(ResultCode.API_PARAM_EXCEPTION, e, e.getAllErrors().get(0).getDefaultMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        LOG.error("发生参数校验异常！原因是：", e);
        return ErrorResult.fail(ResultCode.API_PARAM_EXCEPTION, e, e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }
}
