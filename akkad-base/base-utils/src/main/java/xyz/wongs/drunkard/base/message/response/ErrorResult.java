package xyz.wongs.drunkard.base.message.response;

import xyz.wongs.drunkard.base.message.enums.ResultCode;

import java.io.Serializable;

/**
 * @author WCNGS@QQ.COM
 * @Description 异常错误的返回信息实体
 * @Github <a>https://github.com/rothschil</a>
 * @date 20/11/18 10:42
 * @Version 1.0.0
 */
public class ErrorResult extends SubResult implements Serializable {

    private static final long serialVersionUID = -4505655308965878999L;

    /**
     * 错误
     **/
    private String exception;

    public static ErrorResult fail(ResultCode resultCode, Throwable e, String message) {
        ErrorResult errorResult = ErrorResult.fail(resultCode, e);
        errorResult.setMessage(message);
        return errorResult;
    }

    public static ErrorResult fail(ResultCode resultCode, Throwable e) {
        ErrorResult errorResult = new ErrorResult();
        errorResult.setCode(resultCode.getCode());
        errorResult.setMessage(resultCode.getMsg());
        errorResult.setException(e.getClass().getName());
        return errorResult;
    }

    public static ErrorResult fail(Integer code, String message) {
        ErrorResult errorResult = new ErrorResult();
        errorResult.setCode(code);
        errorResult.setMessage(message);
        return errorResult;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }
}
