package xyz.wongs.drunkard.base.persistence.mybatis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import xyz.wongs.drunkard.base.persistence.mybatis.mapper.BaseMapper;
import xyz.wongs.drunkard.base.po.BasePo;

import java.io.Serializable;

/**
 * @Description
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/8/2 13:34
 * @Version 1.0.0
 */
@Transactional(readOnly = true)
public abstract class BaseService<T extends BasePo,ID extends Serializable> extends AbstractSuperService<T,ID>  {

    /** 待补充
     * @Description
     * @date 2020/8/2 14:12
     */
    @Override
    protected abstract BaseMapper<T,ID> getMapper();

    @Autowired
    private RedisUidService redisUidService;

    @Transactional(rollbackFor = Exception.class)
    public Long insert(T t) {
        Long id = getPrimaryKey(t);
        t.setId(id);
        getMapper().insert(t);
        return id;
    }

    public Long getPrimaryKey(T t){
        return redisUidService.generate(t.getClass().getSimpleName().toUpperCase());
    }

    @Transactional(rollbackFor = Exception.class)
    public Long insertSelective(T t) {
        Long id = getPrimaryKey(t);
        t.setId(id);
        getMapper().insertSelective(t);
        return id;
    }

}