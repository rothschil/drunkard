package xyz.wongs.drunkard.base.persistence.mybatis.service;

import org.springframework.transaction.annotation.Transactional;
import xyz.wongs.drunkard.base.po.BasePo;
import xyz.wongs.drunkard.base.persistence.mybatis.mapper.BaseMapper;

import java.io.Serializable;

/**
 * @Description
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/8/2 13:34
 * @Version 1.0.0
*/
@Transactional(readOnly = true)
public abstract class BaseService<T extends BasePo<ID>,ID extends Serializable> extends AbstractSuperService<T,ID> {

    /**
     * 待补充
     * */
    @Override
    protected abstract BaseMapper<T,ID> getMapper();

    /** 持久化一个对象
     * @Description
     * @param t 对象信息
     * @return int
     * @date 2020/8/2 13:24
     */
    @Transactional(rollbackFor = Exception.class)
    public int insert(T t) {
        return getMapper().insert(t);
    }

    /** 持久化一个对象
     * @Description
     * @param t 对象信息
     * @return int
     * @date 2020/8/2 13:24
     */
    @Transactional(rollbackFor = Exception.class)
    public int insertSelective(T t) {
        return getMapper().insertSelective(t);
    }

}
