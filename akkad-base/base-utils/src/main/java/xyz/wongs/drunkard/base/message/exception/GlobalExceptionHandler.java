package xyz.wongs.drunkard.base.message.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xyz.wongs.drunkard.base.message.enums.ResultCode;
import xyz.wongs.drunkard.base.message.response.ErrorResult;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

/**
 * @author WCNGS@QQ.COM
 * @ClassName GlobalExceptionHandler
 * @Description 全局异常处理Handler
 * @Github <a>https://github.com/rothschil</a>
 * @date 2019/9/23 15:03
 * @Version 1.0.0
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 参数校验不通过
     *
     * @param ex
     * @return xyz.wongs.drunkard.base.message.response.ErrorResult
     * @throws
     * @author WCNGS@QQ.COM
     * @See
     * @date 2019/9/23 17:53
     * @since
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseBody
    public ErrorResult handleConstraintViolationException(ConstraintViolationException ex) {
        log.error("ConstraintViolationException msg:{}", ex.getMessage());
        return ErrorResult.fail(ResultCode.PARAMS_IS_INVALID, ex);
    }


    /**
     * 自定义异常
     *
     * @param request
     * @param ex
     * @return xyz.wongs.drunkard.base.message.response.ErrorResult
     * @throws
     * @author WCNGS@QQ.COM
     * @See
     * @date 2019/9/23 17:53
     * @since
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(DrunkardException.class)
    @ResponseBody
    public ErrorResult handleWeathertopException(HttpServletRequest request, DrunkardException ex) {
        log.error("WeathertopRuntimeException code:{},msg:{}", ex.getCode(), ex.getMessage());
        return ErrorResult.fail(ex.getCode(), ex.getMessage());
    }

    /**
     * @param e
     * @param request
     * @return xyz.wongs.drunkard.base.message.response.ErrorResult
     * @throws
     * @Description 拦截抛出的异常，@ResponseStatus：用来改变响应状态码
     * @date 20/11/13 11:14
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public ErrorResult handlerThrowable(Throwable e, HttpServletRequest request) {
        log.error("发生未知异常！原因是: ", e);
        ErrorResult error = ErrorResult.fail(ResultCode.RUNTIME_EXCEPTION, e);
        return error;
    }

    /**
     * @param e
     * @param request
     * @return xyz.wongs.drunkard.base.message.response.ErrorResult
     * @throws
     * @Description 参数校验异常
     * @date 20/11/13 11:14
     */
    @ExceptionHandler(BindException.class)
    public ErrorResult handleBindExcpetion(BindException e, HttpServletRequest request) {
        log.error("发生参数校验异常！原因是：", e);
        ErrorResult error = ErrorResult.fail(ResultCode.API_PARAM_EXCEPTION, e, e.getAllErrors().get(0).getDefaultMessage());
        return error;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        log.error("发生参数校验异常！原因是：", e);
        ErrorResult error = ErrorResult.fail(ResultCode.API_PARAM_EXCEPTION, e, e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return error;
    }
}
