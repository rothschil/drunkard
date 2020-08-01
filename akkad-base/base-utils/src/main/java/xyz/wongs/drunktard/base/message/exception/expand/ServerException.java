package xyz.wongs.drunktard.base.message.exception.expand;

import xyz.wongs.drunktard.base.message.enums.ResponseCode;
import xyz.wongs.drunktard.base.message.exception.GlobalException;

public class ServerException extends GlobalException {

    public ServerException(String message){
        super(message, ResponseCode.ERROR.getCode());
    }
}
