package xyz.wongs.drunkard.base.message.exception.expand;

import xyz.wongs.drunkard.base.message.exception.GlobalException;
import xyz.wongs.drunkard.base.message.enums.ResponseCode;

/**
 * @ClassName
 * @Description
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/8/2 13:31
 * @Version 1.0.0
 */
public class WeathertopAccountException extends GlobalException {

    public WeathertopAccountException(){
        super(ResponseCode.AUTHENTICATION_FAILED.getMsg(), ResponseCode.AUTHENTICATION_FAILED.getCode());
    }

    public WeathertopAccountException(String message, int code) {
        super(message, code);
    }

    public WeathertopAccountException(String message){
        super(message, ResponseCode.AUTHENTICATION_FAILED.getCode());
    }
}
