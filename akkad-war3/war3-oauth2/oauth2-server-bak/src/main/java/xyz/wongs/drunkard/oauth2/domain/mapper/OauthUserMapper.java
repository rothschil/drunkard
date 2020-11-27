package xyz.wongs.drunkard.oauth2.domain.mapper;


import xyz.wongs.drunkard.base.persistence.mybatis.mapper.BaseMapper;
import xyz.wongs.drunkard.oauth2.domain.entity.OauthUser;
import xyz.wongs.drunkard.oauth2.domain.entity.SysPermission;

import java.util.List;

public interface OauthUserMapper extends BaseMapper<OauthUser,Long> {

    int deleteByPrimaryKey(Long uId);

    OauthUser selectByPrimaryKey(Long uId);

    int updateByPrimaryKeySelective(OauthUser record);

    int updateByPrimaryKey(OauthUser record);

    List<OauthUser> select(OauthUser record);
}