package xyz.wongs.drunkard.oauth2.domain.mapper;


import xyz.wongs.drunkard.base.persistence.mybatis.mapper.BaseMapper;
import xyz.wongs.drunkard.oauth2.domain.entity.OauthUser;
import xyz.wongs.drunkard.oauth2.domain.entity.SysPermission;

import java.util.List;

/**
 * @ClassName OauthUserMapper
 * @Description 
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 20/12/2 16:58
 * @Version 1.0.0
*/
public interface OauthUserMapper extends BaseMapper<OauthUser,Long> {

    @Override
    int deleteByPrimaryKey(Long uId);

    @Override
    OauthUser selectByPrimaryKey(Long uId);

    @Override
    int updateByPrimaryKeySelective(OauthUser record);

    @Override
    int updateByPrimaryKey(OauthUser record);

    List<OauthUser> select(OauthUser record);
}