package xyz.wongs.weathertop.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.wongs.drunkard.base.entity.AbstractEntity;

import java.util.Date;

@EqualsAndHashCode(callSuper=false)
@Data
public class TLock extends AbstractEntity<Integer> {

    private Integer id;

    private String lockKey;

    private String owner;

    private Integer expireSeconds;

    private Date createTime;


}