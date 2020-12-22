package xyz.wongs.drunkard.war3.domain.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.wongs.drunkard.base.persistence.jpa.service.BaseService;
import xyz.wongs.drunkard.base.utils.StringUtils;
import xyz.wongs.drunkard.war3.domain.entity.Location;
import xyz.wongs.drunkard.war3.domain.repository.LocationRepository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
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

	public Page<Location> getList(Location location,int pageNum, int pageSize){
		Sort sort = Sort.by(Sort.Direction.DESC, "id");
		Pageable pageable = PageRequest.of(pageNum, pageSize, sort);
		return locationRepository.findCriteria(getSpecification(location),pageable);
	}

	public Specification getSpecification(Location location){
		return new Specification<Location>() {
			@Override
			public Predicate toPredicate(Root<Location> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<>();
				int lv = location.getLv()==null?0:location.getLv();
				predicateList.add(cb.equal(root.get("lv").as(Integer.class), lv));
				Predicate[] pre = new Predicate[predicateList.size()];
				pre = predicateList.toArray(pre);
				return query.where(pre).getRestriction();
			}
		};
	}
}
