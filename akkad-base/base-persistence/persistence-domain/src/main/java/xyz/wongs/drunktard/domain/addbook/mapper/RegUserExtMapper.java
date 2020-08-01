package xyz.wongs.drunktard.domain.addbook.mapper;

import java.util.List;

import xyz.wongs.drunktard.base.persistence.mybatis.mapper.BaseMapper;
import xyz.wongs.drunktard.domain.addbook.entity.GrpAddbook;
import xyz.wongs.drunktard.domain.addbook.entity.RegUser;
import xyz.wongs.drunktard.domain.addbook.entity.RegUserExt;
import xyz.wongs.drunktard.domain.addbook.entity.RegUserExtExample;

public interface RegUserExtMapper extends BaseMapper<RegUserExt,Long> {
    int deleteByPrimaryKey(Long uRegUserExtId);

    Long insert(RegUserExt record);

    Long insertSelective(RegUserExt record);

    List<RegUserExt> selectByExample(RegUserExtExample example);

    RegUserExt selectByPrimaryKey(Long uRegUserExtId);

    int updateByPrimaryKeySelective(RegUserExt record);

    int updateByPrimaryKey(RegUserExt record);
}