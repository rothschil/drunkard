package xyz.wongs.drunkard.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.wongs.drunkard.base.persistence.mybatis.service.RedisUidService;

@Component
public class IdClazzUtils {

    @Autowired
    private RedisUidService redisUidService;

    public Long getId(Class<?> clazz){
        return Long.valueOf(redisUidService.generate(clazz.getSimpleName().toUpperCase()));
    }

}
