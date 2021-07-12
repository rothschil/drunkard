package xyz.wongs.drunkard.oauth2.domain.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.wongs.drunkard.base.persistence.mybatis.mapper.BaseMapper;
import xyz.wongs.drunkard.base.persistence.mybatis.service.BaseService;
import xyz.wongs.drunkard.oauth2.domain.entity.SysPermission;
import xyz.wongs.drunkard.oauth2.domain.mapper.SysPermissionMapper;

import java.util.List;


@Service
@Transactional(readOnly = true)
public class SysPermissionService extends BaseService<SysPermission, Long> {

	@Autowired
	private SysPermissionMapper sysPermissionMapper;

	@Override
	protected BaseMapper<SysPermission, Long> getMapper() {
		return sysPermissionMapper;
	}

	public List<SysPermission> findByAdminUserId(Long uId){
		return sysPermissionMapper.findByAdminUserId(uId);
	}
}
