package xyz.wongs.drunkard.oauth2.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.wongs.drunkard.base.entity.AbstractEntity;

@EqualsAndHashCode(callSuper=false)
@Data
public class SysRoleUser extends AbstractEntity<Long> {

    private Long id;

    private Long userId;

    private Long roleId;
}