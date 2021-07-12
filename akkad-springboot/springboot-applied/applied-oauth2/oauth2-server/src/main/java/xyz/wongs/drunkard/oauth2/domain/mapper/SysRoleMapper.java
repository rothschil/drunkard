package xyz.wongs.drunkard.oauth2.domain.mapper;


import xyz.wongs.drunkard.base.persistence.mybatis.mapper.BaseMapper;
import xyz.wongs.drunkard.oauth2.domain.entity.SysRole;

public interface SysRoleMapper extends BaseMapper<SysRole,Long> {
    @Override
    int deleteByPrimaryKey(Long id);

    @Override
    SysRole selectByPrimaryKey(Long id);

    @Override
    int updateByPrimaryKeySelective(SysRole record);

    @Override
    int updateByPrimaryKey(SysRole record);
}