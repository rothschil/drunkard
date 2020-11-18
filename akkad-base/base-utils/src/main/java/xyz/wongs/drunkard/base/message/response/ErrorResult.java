package xyz.wongs.drunkard.base.message.response;

import lombok.Data;
import xyz.wongs.drunkard.base.message.enums.ResultCode;

/**
 * @ClassName ErrorResult
 * @Description 异常错误的返回信息实体
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 20/11/18 10:42
 * @Version 1.0.0
*/
@Data
public class ErrorResult {

	/**  错误编码  **/
	private Integer code;
	/**  消息描述 **/
	private String msg;
	/**  错误 **/
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
