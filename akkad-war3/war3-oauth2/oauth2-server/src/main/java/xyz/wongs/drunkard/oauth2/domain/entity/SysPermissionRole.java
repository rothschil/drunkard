package xyz.wongs.drunkard.oauth2.domain.entity;

import lombok.Data;
import xyz.wongs.drunkard.base.entity.AbstractEntity;

@Data
public class SysPermissionRole extends AbstractEntity<Long> {
    private Long id;

    private Long roleId;

    private Long permissionId;
}