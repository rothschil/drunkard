package xyz.wongs.drunktard.domain.addbook.mapper;

import java.util.List;

import xyz.wongs.drunktard.base.persistence.mybatis.mapper.BaseMapper;
import xyz.wongs.drunktard.domain.addbook.entity.DictRegion;
import xyz.wongs.drunktard.domain.addbook.entity.GrpAddbook;
import xyz.wongs.drunktard.domain.addbook.entity.GrpAddbookExample;

public interface GrpAddbookMapper extends BaseMapper<GrpAddbook,Long> {
    int deleteByPrimaryKey(Long grpAddbookId);

    Long insert(GrpAddbook record);

    Long insertSelective(GrpAddbook record);

    List<GrpAddbook> selectByExampleWithBLOBs(GrpAddbookExample example);

    List<GrpAddbook> selectByExample(GrpAddbookExample example);

    GrpAddbook selectByPrimaryKey(Long grpAddbookId);

    int updateByPrimaryKeySelective(GrpAddbook record);

    int updateByPrimaryKeyWithBLOBs(GrpAddbook record);

    int updateByPrimaryKey(GrpAddbook record);
}