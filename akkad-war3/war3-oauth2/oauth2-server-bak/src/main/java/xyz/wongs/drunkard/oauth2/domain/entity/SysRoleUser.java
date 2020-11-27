package xyz.wongs.drunkard.oauth2.domain.entity;

import lombok.Data;
import xyz.wongs.drunkard.base.entity.AbstractEntity;

@Data
public class SysRoleUser extends AbstractEntity<Long> {

    private Long id;

    private Long userId;

    private Long roleId;
}