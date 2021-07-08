package xyz.wongs.weathertop.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.wongs.drunkard.base.po.BasePo;

import java.util.Date;

@EqualsAndHashCode(callSuper=false)
@Data
public class TLock extends BasePo<Integer> {

    private Integer id;

    private String lockKey;

    private String owner;

    private Integer expireSeconds;

    private Date createTime;


}