package xyz.wongs.drunkard.domain.addbook.mapper;

import xyz.wongs.drunkard.base.persistence.mybatis.mapper.BaseMapper;
import xyz.wongs.drunkard.domain.addbook.entity.DictRegion;

public interface DictRegionMapper extends BaseMapper<DictRegion,Long> {
    int deleteByPrimaryKey(Long dictRegionId);

    int insert(DictRegion record);

    int insertSelective(DictRegion record);

    DictRegion selectByPrimaryKey(Long dictRegionId);

    int updateByPrimaryKeySelective(DictRegion record);

    int updateByPrimaryKey(DictRegion record);
}