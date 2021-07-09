package xyz.wongs.drunkard.deno.mapper;

import xyz.wongs.drunkard.base.persistence.mybatis.mapper.BaseMapper;
import xyz.wongs.drunkard.deno.entity.RedisLock;

public interface RedisLockMapper extends BaseMapper<RedisLock,Integer> {
    @Override
    int deleteByPrimaryKey(Integer id);

    @Override
    RedisLock selectByPrimaryKey(Integer id);

    @Override
    int updateByPrimaryKeySelective(RedisLock record);

    @Override
    int updateByPrimaryKey(RedisLock record);
}