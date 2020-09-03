package xyz.wongs.drunkard.base.persistence.mybatis.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import xyz.wongs.drunkard.base.persistence.mybatis.entity.BaseEntityAbstract;
import xyz.wongs.drunkard.base.persistence.mybatis.mapper.BaseMapper;
import xyz.wongs.drunkard.base.persistence.mybatis.page.PaginationInfo;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName BaseService
 * @Description 
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/8/2 13:34
 * @Version 1.0.0
*/
@Transactional(readOnly = true)
public abstract class BaseService<T extends BaseEntityAbstract,ID extends Serializable> extends RootServiceImpl<T,ID> implements IBaseService<T,ID> {


    /** 待补充
     * @Description
     * @param null
     * @return 
     * @throws 
     * @date 2020/8/2 14:12
    */
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
