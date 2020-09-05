package xyz.wongs.drunkard.war3.web.domain.area.mapper;

import xyz.wongs.drunkard.base.persistence.mybatis.mapper.BaseMapper;
import xyz.wongs.drunkard.war3.web.domain.area.entity.Location;

import java.util.List;

public interface LocationMapper extends BaseMapper<Location,Long> {

    int deleteByPrimaryKey(Long id);

    int insert(Location record);

    int insertSelective(Location record);

    Location selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Location record);

    int getCount(Location location);

    List<Location> getList(Location location);

    int insertBatchByOn(List<Location> locations);

    List<Location> getList2(Location location);

}