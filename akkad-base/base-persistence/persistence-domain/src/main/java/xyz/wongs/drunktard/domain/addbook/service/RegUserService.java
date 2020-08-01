package xyz.wongs.drunktard.domain.addbook.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.wongs.drunktard.base.persistence.mybatis.mapper.BaseMapper;
import xyz.wongs.drunktard.base.persistence.mybatis.service.BaseService;
import xyz.wongs.drunktard.domain.addbook.entity.RegUser;
import xyz.wongs.drunktard.domain.addbook.mapper.RegUserMapper;

@Service
@Transactional(readOnly = true)
public class RegUserService extends BaseService<RegUser, Long> {


	@Autowired
	private RegUserMapper regUserMapper;

	@Override
	protected BaseMapper<RegUser, Long> getMapper() {
		return regUserMapper;
	}

}
