package xyz.wongs.drunktard.war3.web.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.wongs.drunkard.base.persistence.mybatis.service.RedisUidService;

/**
 * @ClassName
 * @Description 
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/8/2 13:49
 * @Version 1.0.0
*/
@Component
public class IdClazzUtils {

    @Autowired
    private RedisUidService redisUidService;

    public Long getId(Class<?> clazz){
        return Long.valueOf(redisUidService.generate(clazz.getSimpleName().toUpperCase()));
    }

}
