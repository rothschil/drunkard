package xyz.wongs.drunkard.oauth2.domain.mapper;


import xyz.wongs.drunkard.base.persistence.mybatis.mapper.BaseMapper;
import xyz.wongs.drunkard.oauth2.domain.entity.OauthUser;
import xyz.wongs.drunkard.oauth2.domain.entity.SysPermissionRole;

public interface SysPermissionRoleMapper extends BaseMapper<SysPermissionRole,Long> {
    @Override
    int deleteByPrimaryKey(Long id);

    @Override
    SysPermissionRole selectByPrimaryKey(Long id);

    @Override
    int updateByPrimaryKeySelective(SysPermissionRole record);

    @Override
    int updateByPrimaryKey(SysPermissionRole record);
}