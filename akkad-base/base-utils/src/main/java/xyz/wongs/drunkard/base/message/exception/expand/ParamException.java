package xyz.wongs.drunkard.base.message.exception.expand;

import xyz.wongs.drunkard.base.message.exception.GlobalException;
import xyz.wongs.drunkard.base.message.enums.ResponseCode;

/**
 * @ClassName
 * @Description
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/8/2 13:48
 * @Version 1.0.0
 */
public class ParamException extends GlobalException {

    public ParamException(String message){
        super(message, ResponseCode.PARAM_ERROR_CODE.getCode());
    }
}
