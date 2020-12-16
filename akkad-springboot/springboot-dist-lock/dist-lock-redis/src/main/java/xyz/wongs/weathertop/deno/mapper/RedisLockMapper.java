package xyz.wongs.weathertop.deno.mapper;

import xyz.wongs.drunkard.base.persistence.mybatis.mapper.BaseMapper;
import xyz.wongs.weathertop.deno.entity.RedisLock;

public interface RedisLockMapper extends BaseMapper<RedisLock,Integer> {
    int deleteByPrimaryKey(Integer id);

    RedisLock selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RedisLock record);

    int updateByPrimaryKey(RedisLock record);
}