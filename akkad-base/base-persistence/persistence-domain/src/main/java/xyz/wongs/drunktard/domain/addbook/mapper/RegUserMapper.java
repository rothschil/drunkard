package xyz.wongs.drunktard.domain.addbook.mapper;

import xyz.wongs.drunktard.base.persistence.mybatis.mapper.BaseMapper;
import xyz.wongs.drunktard.domain.addbook.entity.RegUser;

public interface RegUserMapper extends BaseMapper<RegUser,Long> {

    @Override
    int deleteByPrimaryKey(Long uId);
    @Override
    Long insert(RegUser record);
    @Override
    Long insertSelective(RegUser record);
    @Override
    RegUser selectByPrimaryKey(Long uId);
    @Override
    int updateByPrimaryKeySelective(RegUser record);

    @Override
    int updateByPrimaryKeyWithBlob(RegUser record);

    @Override
    int updateByPrimaryKey(RegUser record);
}