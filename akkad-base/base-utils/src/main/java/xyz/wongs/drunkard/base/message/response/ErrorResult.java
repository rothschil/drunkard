package xyz.wongs.drunkard.base.message.response;

import lombok.Data;
import xyz.wongs.drunkard.base.message.enums.ResultCode;

@Data
public class ErrorResult {
	
	private Integer code;
	
	private String msg;
	
	private String exception;

	public static ErrorResult fail(ResultCode resultCode, Throwable e, String message) {
		ErrorResult errorResult = ErrorResult.fail(resultCode, e);
		errorResult.setMsg(message);
		return errorResult;
	}
	
	public static ErrorResult fail(ResultCode resultCode, Throwable e) {
		ErrorResult errorResult = new ErrorResult();
		errorResult.setCode(resultCode.getCode());
		errorResult.setMsg(resultCode.getMsg());
		errorResult.setException(e.getClass().getName());
		return errorResult;
	}
	public static ErrorResult fail(Integer code, String message) {
		ErrorResult errorResult = new ErrorResult();
		errorResult.setCode(code);
		errorResult.setMsg(message);
		return errorResult;
	}
	
}
