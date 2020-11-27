package xyz.wongs.drunkard.oauth2.domain.entity;

import lombok.Data;
import xyz.wongs.drunkard.base.entity.AbstractEntity;

@Data
public class SysPermission extends AbstractEntity<Long> {
    private Long id;

    private String permName;

    private String descritpion;

    private String url;

    private Integer pid;

}