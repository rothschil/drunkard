package xyz.wongs.drunktard.base.message.exception.expand;

import xyz.wongs.drunktard.base.message.enums.ResponseCode;
import xyz.wongs.drunktard.base.message.exception.GlobalException;

public class ParamException extends GlobalException {

    public ParamException(String message){
        super(message, ResponseCode.PARAM_ERROR_CODE.getCode());
    }
}
