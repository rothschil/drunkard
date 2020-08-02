package xyz.wongs.drunktard.base.persistence.mybatis.mapper;

import xyz.wongs.drunktard.base.persistence.mybatis.entity.BaseEntityAbstract;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName BaseMapper
 * @Description 通用Mapper
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2019/11/21 17:40
 * @Version 1.0.0
*/
public interface BaseMapper<T extends BaseEntityAbstract,ID extends Serializable> {

    /**
     * @author WCNGS@QQ.COM
     * @See
     * @date 2019/7/23 19:27
     * @param t
     * @return 
     * @throws 
     * @since 
    */
    List<T> getList(T t);

    List<T> getListByCondition(Object condition);

    List<T> getListByExample(Object example);

    int deleteByPrimaryKey(ID id);

    Long insert(T t);

    Long insertSelective(T t);

    T selectByPrimaryKey(ID id);

    int updateByPrimaryKeySelective(T t);

    int updateByPrimaryKeyWithBlob(T t);

    int updateByPrimaryKey(T t);

}
