package xyz.wongs.drunkard.mapper.location;


import xyz.wongs.drunkard.base.persistence.mybatis.mapper.BaseMapper;
import xyz.wongs.drunkard.entity.location.Location;

import java.util.List;

public interface LocationMapper extends BaseMapper<Location,Long> {
    @Override
    int deleteByPrimaryKey(Long id);

    @Override
    Location selectByPrimaryKey(Long id);

    @Override
    int updateByPrimaryKeySelective(Location record);

    @Override
    int updateByPrimaryKey(Location record);

    int insertBatchByOn(List<Location> locations);

    @Override
    List<Location> getList(Location location);

    List<Location> getList2(Location location);
}