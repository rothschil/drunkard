package xyz.wongs.weathertop.mapper.location;


import xyz.wongs.drunkard.base.persistence.mybatis.mapper.BaseMapper;
import xyz.wongs.weathertop.entity.location.Location;

import java.util.List;

public interface LocationMapper extends BaseMapper<Location,Long> {
    int deleteByPrimaryKey(Long id);

    Location selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Location record);

    int updateByPrimaryKey(Location record);

    int insertBatchByOn(List<Location> locations);

    List<Location> getList(Location location);

    List<Location> getList2(Location location);
}