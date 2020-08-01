package xyz.wongs.drunktard.domain.addbook.mapper;

import java.util.List;

import xyz.wongs.drunktard.base.persistence.mybatis.mapper.BaseMapper;
import xyz.wongs.drunktard.domain.addbook.entity.DictRegion;
import xyz.wongs.drunktard.domain.addbook.entity.DictRegionExample;

public interface DictRegionMapper extends BaseMapper<DictRegion,Long> {
    int deleteByPrimaryKey(Long dictRegionId);

    Long insert(DictRegion record);

    Long insertSelective(DictRegion record);

    List<DictRegion> selectByExampleWithBLOBs(DictRegionExample example);

    List<DictRegion> selectByExample(DictRegionExample example);

    DictRegion selectByPrimaryKey(Long dictRegionId);

    int updateByPrimaryKeySelective(DictRegion record);

    int updateByPrimaryKeyWithBLOBs(DictRegion record);

    int updateByPrimaryKey(DictRegion record);
}