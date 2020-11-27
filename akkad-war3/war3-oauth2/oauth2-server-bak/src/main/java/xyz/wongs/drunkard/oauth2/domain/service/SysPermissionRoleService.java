package xyz.wongs.drunkard.oauth2.domain.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.wongs.drunkard.base.persistence.mybatis.mapper.BaseMapper;
import xyz.wongs.drunkard.base.persistence.mybatis.service.BaseService;
import xyz.wongs.drunkard.oauth2.domain.entity.SysPermissionRole;
import xyz.wongs.drunkard.oauth2.domain.mapper.SysPermissionRoleMapper;


@Service
@Transactional(readOnly = true)
public class SysPermissionRoleService extends BaseService<SysPermissionRole, Long> {

	@Autowired
	private SysPermissionRoleMapper sysPermissionRoleMapper;

	@Override
	protected BaseMapper<SysPermissionRole, Long> getMapper() {
		return sysPermissionRoleMapper;
	}

}
