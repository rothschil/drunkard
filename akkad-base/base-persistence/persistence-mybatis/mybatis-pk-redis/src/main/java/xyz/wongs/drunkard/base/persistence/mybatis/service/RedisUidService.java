package xyz.wongs.drunkard.base.persistence.mybatis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/8/2 14:13
 * @Version 1.0.0
*/
@Component
public class RedisUidService {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    /**
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description //TODO
     * @Date 2021/7/8-13:39
     * @Param key 键
     * @param value 值
     * @param timeout   超时时间
     * @param unit  时间的单位
     * @return long
     **/
    public void set(String key, int value, long timeout, TimeUnit unit) {
        RedisAtomicLong counter = getRedisAtomicLong(key);
        counter.set(value);
        counter.expire(timeout, unit);
    }


    /**
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description //TODO
     * @Date 2021/7/8-13:39
     * @Param key 键
     * @return long
     **/
    public long generate(String key) {
        RedisAtomicLong counter = getRedisAtomicLong(key);
        return counter.incrementAndGet();
    }

    /**
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description //TODO
     * @Date 2021/7/8-13:39
     * @Param key 键
     * @param expireTime 超时日期
     * @return long
     **/
    public long generate(String key, Date expireTime) {
        RedisAtomicLong counter = getRedisAtomicLong(key);
        counter.expireAt(expireTime);
        return counter.incrementAndGet();
    }

    /**
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description //TODO
     * @Date 2021/7/8-13:39
     * @Param key 键
     * @param increment 增加
     * @return long
     **/
    public long generate(String key, int increment) {
        RedisAtomicLong counter = getRedisAtomicLong(key);
        return counter.addAndGet(increment);
    }

    /**
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description //TODO
     * @Date 2021/7/8-13:39
     * @Param key 键
     * @param increment 增加
     * @param expireTime 超时日期
     * @return long
     **/
    public long generate(String key, int increment, Date expireTime) {
        RedisAtomicLong counter = getRedisAtomicLong(key);
        counter.expireAt(expireTime);
        return counter.addAndGet(increment);
    }

    /**
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description //TODO
     * @Date 2021/7/8-13:39
     * @Param key 键
     * @return RedisAtomicLong
     **/
    public RedisAtomicLong getRedisAtomicLong(String key){
        RedisConnectionFactory factory = redisTemplate.getConnectionFactory();
        Assert.notNull(factory,"The factory must not be null");
        return new RedisAtomicLong(key, factory);
    }
}
