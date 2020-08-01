package xyz.wongs.drunktard.domain.addbook.mapper;

import java.util.List;

import xyz.wongs.drunktard.base.persistence.mybatis.mapper.BaseMapper;
import xyz.wongs.drunktard.domain.addbook.entity.RegUser;
import xyz.wongs.drunktard.domain.addbook.entity.RegUserExample;
import xyz.wongs.drunktard.domain.addbook.entity.RegUserExt;

public interface RegUserMapper extends BaseMapper<RegUser,Long> {
    int deleteByPrimaryKey(Long uId);

    Long insert(RegUser record);

    Long insertSelective(RegUser record);

    List<RegUser> selectByExampleWithBLOBs(RegUserExample example);

    List<RegUser> selectByExample(RegUserExample example);

    RegUser selectByPrimaryKey(Long uId);

    int updateByPrimaryKeySelective(RegUser record);

    int updateByPrimaryKeyWithBLOBs(RegUser record);

    int updateByPrimaryKey(RegUser record);
}