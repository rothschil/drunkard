package xyz.wongs.drunkard.oauth2.domain.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.wongs.drunkard.base.persistence.mybatis.mapper.BaseMapper;
import xyz.wongs.drunkard.base.persistence.mybatis.service.BaseService;
import xyz.wongs.drunkard.oauth2.domain.entity.SysRole;
import xyz.wongs.drunkard.oauth2.domain.mapper.SysRoleMapper;


@Service
@Transactional(readOnly = true)
public class SysRoleService extends BaseService<SysRole, Long> {

	@Autowired
	private SysRoleMapper sysRoleMapper;

	@Override
	protected BaseMapper<SysRole, Long> getMapper() {
		return sysRoleMapper;
	}

}
