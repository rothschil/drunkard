package xyz.wongs.drunkard.war3.domain.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.wongs.drunkard.base.persistence.jpa.service.BaseService;
import xyz.wongs.drunkard.war3.domain.entity.Location;
import xyz.wongs.drunkard.war3.domain.repository.LocationRepository;

import java.util.Collections;
import java.util.List;

/**
 * @ClassName LocationService
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
}
