package xyz.wongs.drunkard.oauth2.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.wongs.drunkard.base.po.BasePo;

@EqualsAndHashCode(callSuper=false)
@Data
public class SysPermission extends BasePo<Long> {
    private Long id;

    private String permName;

    private String descritpion;

    private String url;

    private Integer pid;

}