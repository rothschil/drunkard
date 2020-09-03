package xyz.wongs.drunkard.base.persistence.mybatis.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import xyz.wongs.drunkard.base.persistence.mybatis.entity.BaseEntityAbstract;
import xyz.wongs.drunkard.base.persistence.mybatis.mapper.RootMapper;
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
public abstract class RootServiceImpl<T extends BaseEntityAbstract,ID extends Serializable> implements RootService<T,ID> {


    /** 待补充
     * @Description
     * @param null
     * @return
     * @throws
     * @date 2020/8/2 14:12
     */
    protected abstract RootMapper<T,ID> getMapper();

    @Override
    public PageInfo<T> selectPage(PaginationInfo pgInfo, T t) {
        PageHelper.startPage(pgInfo.getPageNum(), pgInfo.getPageSize());
        List<T> lt = getMapper().getList(t);
        PageInfo<T> pageInfo = new PageInfo<T>(lt);
        return pageInfo;
    }


    public PageInfo<T> selectPageByCondition(PaginationInfo pgInfo, Object condition) {
        PageHelper.startPage(pgInfo.getPageNum(), pgInfo.getPageSize());
        List<T> lt = getMapper().getListByCondition(condition);
        PageInfo<T> pageInfo = new PageInfo<T>(lt);
        return pageInfo;
    }

    @Override
    public PageInfo<T> selectByExample(PaginationInfo pgInfo, Object example) {
        PageHelper.startPage(pgInfo.getPageNum(), pgInfo.getPageSize());
        List<T> lt = getMapper().getListByExample(example);
        PageInfo<T> pageInfo = new PageInfo<T>(lt);
        return pageInfo;
    }

    @Override
    public int deleteByPrimaryKey(ID id) {
        return getMapper().deleteByPrimaryKey(id);
    }


    @Override
    public T selectByPrimaryKey(ID id) {
        return getMapper().selectByPrimaryKey(id);
    }

    @Transactional(readOnly = false)
    @Override
    public int updateByPrimaryKeySelective(T t) {
        return getMapper().updateByPrimaryKeySelective(t);
    }

    @Transactional(readOnly = false)
    @Override
    public int updateByPrimaryKeyWithBlob(T t) {
        return getMapper().updateByPrimaryKeyWithBlob(t);
    }


    /** Update entity based on primary key
     * @author WCNGS@QQ.COM
     * @See
     * @date 2019/7/23 19:31
     * @param t entity
     * @return int
     * @throws
     * @since
     */
    @Transactional(readOnly = false)
    @Override
    public int updateByPrimaryKey(T t) {
        return getMapper().updateByPrimaryKey(t);
    }
}