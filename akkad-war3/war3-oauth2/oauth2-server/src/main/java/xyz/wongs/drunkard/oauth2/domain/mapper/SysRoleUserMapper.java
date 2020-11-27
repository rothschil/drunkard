package xyz.wongs.drunkard.oauth2.domain.mapper;


import xyz.wongs.drunkard.base.persistence.mybatis.mapper.BaseMapper;
import xyz.wongs.drunkard.oauth2.domain.entity.SysRoleUser;

public interface SysRoleUserMapper extends BaseMapper<SysRoleUser,Long> {
    int deleteByPrimaryKey(Long id);

    SysRoleUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRoleUser record);

    int updateByPrimaryKey(SysRoleUser record);
}