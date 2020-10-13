package xyz.wongs.drunkard.domain.real.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class RespHeadVo {

    /**
     * resultObject : {"resultCode":"00","resultMsg":"一致","compareTime":0}
     * resultCode : 0
     * resultType : 0
     */
    private RespBodyVo resultObject;
    private String resultCode;
    private String resultType;

}
