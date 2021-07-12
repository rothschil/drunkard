package xyz.wongs.drunkard.oauth2.domain.mapper;

import xyz.wongs.drunkard.oauth2.domain.entity.OauthAccessToken;

public interface OauthAccessTokenMapper {

    int deleteByPrimaryKey(String authenticationId);

    int insert(OauthAccessToken record);

    int insertSelective(OauthAccessToken record);

    OauthAccessToken selectByPrimaryKey(String authenticationId);

    int updateByPrimaryKeySelective(OauthAccessToken record);

    int updateByPrimaryKeyWithBLOBs(OauthAccessToken record);

    int updateByPrimaryKey(OauthAccessToken record);
}