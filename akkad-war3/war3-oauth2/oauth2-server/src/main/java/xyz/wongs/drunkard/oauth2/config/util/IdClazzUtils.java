package xyz.wongs.drunkard.oauth2.config.util;

import xyz.wongs.drunkard.base.utils.bean.SpringContextHolder;
import xyz.wongs.drunkard.base.persistence.mybatis.service.RedisUidService;

/**
 * @ClassName IdClazzUtils
 * @Description 
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/9/9 15:28
 * @Version 1.0.0
*/
public class IdClazzUtils {

    private final static RedisUidService redisUidService;

    static {
        redisUidService = SpringContextHolder.getBean(RedisUidService.class);
    }

    /**BaseService
     * @Description
     * @param clazz
     * @return java.lang.Long
     * @throws
     * @date 2020/9/9 15:28
     */
    public static long getId(Class<?> clazz){
        return redisUidService.generate(clazz.getSimpleName().toUpperCase());
    }

}
