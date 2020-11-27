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
 * @ClassName GlobalExceptionHandler
 * @Description 全局异常处理Handler
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2019/9/23 15:03
 * @Version 1.0.0
*/
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /** 参数校验不通过
     * @author WCNGS@QQ.COM
     * @See
     * @date 2019/9/23 17:53
     * @param ex
     * @return xyz.wongs.drunkard.base.message.response.ErrorResult
     * @throws
     * @since
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseBody
    public ErrorResult handleConstraintViolationException(ConstraintViolationException ex) {
        log.error("ConstraintViolationException msg:{}",ex.getMessage());
        return ErrorResult.fail(ResultCode.PARAMS_IS_INVALID,ex);
    }


    /** 自定义异常
     * @author WCNGS@QQ.COM
     * @See
     * @date 2019/9/23 17:53
     * @param request
     * @param ex
     * @return xyz.wongs.drunkard.base.message.response.ErrorResult
     * @throws
     * @since
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(DrunkardException.class)
    @ResponseBody
    public ErrorResult handleWeathertopException(HttpServletRequest request, DrunkardException ex) {
        log.error("WeathertopRuntimeException code:{},msg:{}",ex.getCode(),ex.getMessage());
        return ErrorResult.fail(ex.getCode(),ex.getMessage());
    }

    /**
     * @Description 拦截抛出的异常，@ResponseStatus：用来改变响应状态码
     * @param e
     * @param request
     * @return xyz.wongs.drunkard.base.message.response.ErrorResult
     * @throws
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
     * @Description 参数校验异常
     * @param e
     * @param request
     * @return xyz.wongs.drunkard.base.message.response.ErrorResult
     * @throws
     * @date 20/11/13 11:14
     */
    @ExceptionHandler(BindException.class)
    public ErrorResult handleBindExcpetion(BindException e, HttpServletRequest request) {
        log.error("发生参数校验异常！原因是：",e);
        ErrorResult error = ErrorResult.fail(ResultCode.API_PARAM_EXCEPTION, e, e.getAllErrors().get(0).getDefaultMessage());
        return error;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        log.error("发生参数校验异常！原因是：",e);
        ErrorResult error = ErrorResult.fail(ResultCode.API_PARAM_EXCEPTION,e,e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return error;
    }
//
//
//    /**全局异常
//     * @author WCNGS@QQ.COM
//     * @See
//     * @date 2019/9/23 17:53
//     * @param request
//     * @param ex
//     * @return xyz.wongs.drunkard.base.message.response.Response
//     * @throws
//     * @since
//     */
//    @org.springframework.web.bind.annotation.ExceptionHandler(GlobalException.class)
//    @ResponseBody
//    public ResponseResult<?> handleException(HttpServletRequest request, Exception ex) {
//        log.error("exception error:{}",ex);
//        ResponseResult<?> response = new ResponseResult();
//        if (ex instanceof org.springframework.web.servlet.NoHandlerFoundException) {
//            response.setCode(404);
//
//        } else {
//            response.setCode(500);
//        }
//        response.setMsg(ex.getMessage());
//        response.setData(null);
//        response.setStatus(false);
//        return response;
//    }
//
//    /** 数据库中已存在异常
//     * @author WCNGS@QQ.COM
//     * @See
//     * @date 2019/9/23 17:53
//     * @param request
//     * @param ex
//     * @return xyz.wongs.drunkard.base.message.response.Response
//     * @throws
//     * @since
//     */
//    @org.springframework.web.bind.annotation.ExceptionHandler(DuplicateKeyException.class)
//    @ResponseBody
//    public ResponseResult<?> handleDuplicateKeyException(HttpServletRequest request, DuplicateKeyException ex){
//        log.error("exception error:{}", ex);
//        return new ResponseResult(false,ResponseCode.RESOURCE_EXIST.getCode(),ResponseCode.RESOURCE_EXIST.getMsg());
//    }
//
//    @ResponseBody
//    @org.springframework.web.bind.annotation.ExceptionHandler(RuntimeException.class)
//    public ResponseResult<?> runtimeException(RuntimeException ex){
//        log.error(ex.getMessage(),ex);
//        ResponseResult response = new ResponseResult(false,ResponseCode.ERROR_RUNTION.getCode(),ResponseCode.ERROR_RUNTION.getMsg());
//        return response;
//    }
//
//    @ResponseBody
//    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
//    public ResponseResult<?> exception(Exception ex){
//        log.error(ex.getMessage(),ex);
//        ResponseResult response = new ResponseResult(false,ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getMsg());
//        return response;
//    }
//
//    @ResponseBody
//    @org.springframework.web.bind.annotation.ExceptionHandler(HttpRequestMethodNotSupportedException.class)
//    public ResponseResult<?> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex){
//        log.error(ex.getMessage(),ex);
//        ResponseResult response = new ResponseResult(false,ResponseCode.ERROR_MOTHODNOTSUPPORT.getCode(),ResponseCode.ERROR_MOTHODNOTSUPPORT.getMsg());
//        return response;
//    }
//
////    @ResponseBody
////    @org.springframework.web.bind.annotation.ExceptionHandler(IOException.class)
////    public ResponseResult<?> ioException(IOException ex){
////        log.error(ex.getMessage(),ex);
////        ResponseResult response = new ResponseResult(false,ResponseCode.ERROR_IO.getCode(),ResponseCode.ERROR_IO.getMsg());
////        return response;
////    }
//
//    @ResponseBody
//    @org.springframework.web.bind.annotation.ExceptionHandler(NullPointerException.class)
//    public ResponseResult<?> nullPointer(NullPointerException ex){
//        log.error(ex.getMessage(),ex);
//        ResponseResult response = new ResponseResult(false,ResponseCode.ERROR_NULL.getCode(),ResponseCode.ERROR_NULL.getMsg());
//        return response;
//    }
//
//    @ResponseBody
//    @org.springframework.web.bind.annotation.ExceptionHandler(ClassCastException.class)
//    public ResponseResult<?> classCastException(ClassCastException ex){
//        log.error(ex.getMessage(),ex);
//        ResponseResult response = new ResponseResult(false,ResponseCode.ERROR_CLASS_CAST.getCode(),ResponseCode.ERROR_CLASS_CAST.getMsg());
//        return response;
//    }
//
////    /**统一处理请求参数校验.
////     * @author WCNGS@QQ.COM
////     * @See
////     * @date 2019/10/23 9:22
////     * @param e
////     * @return java.lang.String
////     * @throws
////     * @since
////     */
////    @org.springframework.web.bind.annotation.ExceptionHandler(value = ConstraintViolationException.class)
////    public ResponseResult<?> handleConstrainViolationException(ConstraintViolationException e) {
////        StringBuilder message = new StringBuilder();
////        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
////        for (ConstraintViolation violation : violations) {
////            Path path = violation.getPropertyPath();
////            String[] pathArr = StringUtils.splitByWholeSeparatorPreserveAllTokens(path.toString(), ".");
////            message.append(pathArr[1]).append(violation.getMessage()).append(",");
////        }
////        message = new StringBuilder(message.substring(0, message.length() - 1));
////        ResponseResult response = new ResponseResult(false,ResponseCode.VALID_ENTITY_PARAMS.getCode(),message.toString());
////        return response;
////    }
//
//    /** 统一请求参数校验(实体对象传参).
//     * @author WCNGS@QQ.COM
//     * @See
//     * @date 2019/10/23 9:22
//     * @param e
//     * @return java.lang.String
//     * @throws
//     * @since
//     */
//    @org.springframework.web.bind.annotation.ExceptionHandler({BindException.class})
//    public ResponseResult<?> validExceptionHandler(BindException e) {
//        StringBuilder message = new StringBuilder();
//        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
//        for (FieldError error : fieldErrors) {
//            message.append(error.getField()).append(error.getDefaultMessage()).append(",");
//        }
//        message = new StringBuilder(message.substring(0, message.length() - 1));
//        ResponseResult response = new ResponseResult(false,ResponseCode.VALID_UNION_PARAMS.getCode(),message.toString());
//        return response;
//    }
}
