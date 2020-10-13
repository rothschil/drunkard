package xyz.wongs.drunkard.domain.real.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class RespBodyVo {

    /**
     * resultCode : 00
     * resultMsg : 一致
     * compareTime : 0
     */

    private String resultCode;
    private String resultMsg;
    private int compareTime;


}
