package xyz.wongs.drunkard.domain.addbook.mapper;

import xyz.wongs.drunkard.base.persistence.mybatis.mapper.BaseMapper;
import xyz.wongs.drunkard.domain.addbook.entity.DictRegion;
import xyz.wongs.drunkard.domain.addbook.entity.RegisterUserHis;

public interface RegisterUserHisMapper extends BaseMapper<RegisterUserHis,Long> {
    int deleteByPrimaryKey(Long registerUserHisId);

    int insert(RegisterUserHis record);

    int insertSelective(RegisterUserHis record);

    RegisterUserHis selectByPrimaryKey(Long registerUserHisId);

    int updateByPrimaryKeySelective(RegisterUserHis record);

    int updateByPrimaryKey(RegisterUserHis record);
}