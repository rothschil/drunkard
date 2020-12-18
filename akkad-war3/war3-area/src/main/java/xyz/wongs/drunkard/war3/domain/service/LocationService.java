package xyz.wongs.drunkard.war3.domain.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xyz.wongs.drunkard.base.persistence.mybatis.mapper.BaseMapper;
import xyz.wongs.drunkard.base.persistence.mybatis.service.BaseService;
import xyz.wongs.drunkard.war3.domain.entity.Location;
import xyz.wongs.drunkard.war3.domain.repository.LocationMapper;

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

	public PageInfo<Location> getLocationsByLv(int lv, int pageNum, int pageSize){
		PageInfo<Location> pageInfo =null;
		try {
			PageHelper.startPage(pageNum,pageSize);
			Location location = new Location();
			location.setLv(lv);
			List<Location> locations = locationtMapper.getList2(location);
			pageInfo = new PageInfo<>(locations);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageInfo;
	}

	public PageInfo<Location> getLocationsByLvAndFlag(int pageNum, int pageSize,Location location){
		PageInfo<Location> pageInfo =null;
		try {
			PageHelper.startPage(pageNum,pageSize);
			List<Location> locations = locationtMapper.getList2(location);
			pageInfo = new PageInfo<>(locations);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageInfo;
	}

	public List<Location> getLocationBySupLocalCode(String supLocalCode) {
		// TODO Auto-generated method stub
		Location location = new Location();
		location.setSupLocalCode(supLocalCode);
		return locationtMapper.getList(location);
	}

	public List<Location> getLocationByLvAndFlag(int lv,String flag){
		Location location = Location.builder().lv(lv).flag(flag).build();
		return locationtMapper.getList(location);
	}

	public List<Location> findLocationTreeNodeByLocalCode(String localCode){
		return getLocationBySupLocalCode(localCode);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class)
	public int insertBatchByOn(List<Location> locations){
		return locationtMapper.insertBatchByOn(locations);
	}

	public List<Location> getLocationListByLevel(Integer lv){
		Location location = new Location();
		location.setLv(lv);
		return locationtMapper.getList(location);
	}

	public PageInfo<Location> getLocationsByLevel(Integer lv, int pageNumber){
		//每页大小
		int pageSize=20;
		return getLocationsByLv(lv,pageNumber,pageSize);
	}

	public List<Location> getLocationListById(Long id){
		Location location = new Location();
		location.setId(id);
		return locationtMapper.getList(location);
	}

	public List<Location> findLocationThrid(Integer lv, Integer rownum) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getLocationCountsByLevel(Integer lv){
		//每页大小
		int pageSize=20;
		Location location = new Location();
		location.setLv(lv);
		int size = locationtMapper.getCount(location);
		if(size%pageSize!=0) {
			return  (size/pageSize)+1;
		} else {
			return  size/pageSize;
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class)
	public void updateLocationFlag(String flag, Long id) {
		Location location = new Location();
		location.setId(id);
		location.setFlag(flag);
		locationtMapper.updateByPrimaryKey(location);
	}

	@Autowired
	private LocationMapper locationtMapper;

	@Override
	protected BaseMapper<Location, Long> getMapper() {
		return locationtMapper;
	}

	public List<Location> getList2(Location location){
		return locationtMapper.getList2(location);
	}
}
