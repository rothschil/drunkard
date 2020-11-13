package xyz.wongs.drunkard.base.message.response;

import lombok.Data;
import xyz.wongs.drunkard.base.message.enums.ResultCode;

import java.io.Serializable;

/**
 * @ClassName
 * @Description
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/8/2 13:48
 * @Version 1.0.0
 */
@Data
public class R implements Serializable {
    private static final long serialVersionUID = -4505655308965878999L;

    private Integer code;

    private String message;

    private Object data;

    private R() {
    }

    public R(ResultCode resultCode, Object data) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMsg();
        this.data = data;
    }

    private void setResultCode(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMsg();
    }

    // 返回成功
    public static R success() {
        R result = new R();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }
    // 返回成功
    public static R success(Object data) {
        R result = new R();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    // 返回失败
    public static R fail(Integer code, String message) {
        R result = new R();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }
    // 返回失败
    public static R fail(ResultCode resultCode) {
        R result = new R();
        result.setResultCode(resultCode);
        return result;
    }


}
