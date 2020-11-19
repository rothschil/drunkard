package xyz.wongs.drunkard.base.persistence.mybatis.service;

import org.springframework.transaction.annotation.Transactional;
import xyz.wongs.drunkard.base.entity.AbstractEntity;
import xyz.wongs.drunkard.base.persistence.mybatis.mapper.BaseMapper;

import java.io.Serializable;

/**
 * @ClassName BaseService
 * @Description 
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/8/2 13:34
 * @Version 1.0.0
*/
@Transactional(readOnly = true)
public abstract class AbstractService<T extends AbstractEntity,ID extends Serializable> extends AbstractRootService<T,ID> implements IBaseService<T,ID> {


    /** 待补充
     * @Description
     * @param null
     * @return 
     * @throws 
     * @date 2020/8/2 14:12
    */
    @Override
    protected abstract BaseMapper<T,ID> getMapper();

    @Transactional(readOnly = false)
    @Override
    public int insert(T t) {
        return getMapper().insert(t);
    }


    @Transactional(readOnly = false)
    @Override
    public int insertSelective(T t) {
        return getMapper().insertSelective(t);
    }

}
