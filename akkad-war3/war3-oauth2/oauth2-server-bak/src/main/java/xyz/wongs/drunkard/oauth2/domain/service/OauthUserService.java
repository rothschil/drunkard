package xyz.wongs.drunkard.oauth2.domain.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.wongs.drunkard.base.persistence.mybatis.mapper.BaseMapper;
import xyz.wongs.drunkard.base.persistence.mybatis.service.BaseService;
import xyz.wongs.drunkard.oauth2.domain.entity.OauthUser;
import xyz.wongs.drunkard.oauth2.domain.mapper.OauthUserMapper;


@Service
@Transactional(readOnly = true)
public class OauthUserService extends BaseService<OauthUser, Long> {

	@Autowired
	private OauthUserMapper oauthUserMapper;

	@Override
	protected BaseMapper<OauthUser, Long> getMapper() {
		return oauthUserMapper;
	}

}
