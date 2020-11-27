package xyz.wongs.drunkard.oauth2.domain.mapper;


import xyz.wongs.drunkard.base.persistence.mybatis.mapper.BaseMapper;
import xyz.wongs.drunkard.oauth2.domain.entity.SysPermission;

import java.util.List;

public interface SysPermissionMapper extends BaseMapper<SysPermission,Long> {

    int deleteByPrimaryKey(Long id);

    SysPermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysPermission record);

    int updateByPrimaryKey(SysPermission record);

    List<SysPermission> findByAdminUserId(Long uId);
}