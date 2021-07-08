package xyz.wongs.weathertop.mapper;


import xyz.wongs.drunkard.base.persistence.mybatis.mapper.BaseMapper;
import xyz.wongs.weathertop.entity.TLock;

public interface TLockMapper extends BaseMapper<TLock,Integer> {

    @Override
    int deleteByPrimaryKey(Integer id);

    @Override
    TLock selectByPrimaryKey(Integer id);

    @Override
    int updateByPrimaryKeySelective(TLock record);

    @Override
    int updateByPrimaryKey(TLock record);
}