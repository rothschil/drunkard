package xyz.wongs.drunkard.oauth2.domain.mapper;


import xyz.wongs.drunkard.base.persistence.mybatis.mapper.BaseMapper;
import xyz.wongs.drunkard.oauth2.domain.entity.SysRoleUser;

public interface SysRoleUserMapper extends BaseMapper<SysRoleUser,Long> {
    @Override
    int deleteByPrimaryKey(Long id);

    @Override
    SysRoleUser selectByPrimaryKey(Long id);

    @Override
    int updateByPrimaryKeySelective(SysRoleUser record);

    @Override
    int updateByPrimaryKey(SysRoleUser record);
}