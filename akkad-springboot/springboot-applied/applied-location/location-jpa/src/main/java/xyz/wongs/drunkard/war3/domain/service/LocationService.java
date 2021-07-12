package xyz.wongs.drunkard.war3.domain.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.wongs.drunkard.base.persistence.jpa.service.BaseService;
import xyz.wongs.drunkard.war3.domain.entity.Location;
import xyz.wongs.drunkard.war3.domain.repository.LocationRepository;

import java.util.List;

/**
 * @Description
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/9/9 16:11
 * @Version 1.0.0
*/
@Service(value="locationService")
@Transactional(readOnly = true)
public class LocationService extends BaseService<Location, Long> {

	private LocationRepository locationRepository;

	@Autowired
	@Qualifier("locationRepository")
	@Override
	public void setJpaRepository(JpaRepository<Location, Long> jpaRepository) {
		this.jpaRepository=jpaRepository;
		this.locationRepository =(LocationRepository)jpaRepository;
	}

	public List<Location> getLocationListByLevel(int lv){
		return locationRepository.findByLv(lv);
	}

	public Page<Location> getList(Location location,int pageNum, int pageSize){
		Sort sort = Sort.by(Sort.Direction.DESC, "id");
		Pageable pageable = PageRequest.of(pageNum, pageSize, sort);
		return locationRepository.find(location,pageable);
	}

	/** 根据SQL，查询结果，获取结果列表
	 * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
	 * @Description //TODO
	 * @Date 2021/7/8-14:42
	 * @Param sql 原生SQL语句
	 * @return List
	 **/
	public List<Location> listBySql(String sql) {
		return locationRepository.listBySql(sql);
	}

	/** 根据hql，查询结果，获取结果列表
	 * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
	 * @Description //TODO
	 * @Date 2021/7/8-14:42
	 * @Param hql
	 * @return List
	 **/
	public List<Location> listByHql(String hql) {
		return locationRepository.listByHql(hql);
	}
}
