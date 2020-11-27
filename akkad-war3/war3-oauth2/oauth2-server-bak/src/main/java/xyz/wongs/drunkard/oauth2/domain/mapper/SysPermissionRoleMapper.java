package xyz.wongs.drunkard.oauth2.domain.mapper;


import xyz.wongs.drunkard.base.persistence.mybatis.mapper.BaseMapper;
import xyz.wongs.drunkard.oauth2.domain.entity.OauthUser;
import xyz.wongs.drunkard.oauth2.domain.entity.SysPermissionRole;

public interface SysPermissionRoleMapper extends BaseMapper<SysPermissionRole,Long> {
    int deleteByPrimaryKey(Long id);

    SysPermissionRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysPermissionRole record);

    int updateByPrimaryKey(SysPermissionRole record);
}