package xyz.wongs.drunkard.oauth2.domain.mapper;


import xyz.wongs.drunkard.base.persistence.mybatis.mapper.BaseMapper;
import xyz.wongs.drunkard.oauth2.domain.entity.SysRole;

public interface SysRoleMapper extends BaseMapper<SysRole,Long> {
    int deleteByPrimaryKey(Long id);

    SysRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);
}