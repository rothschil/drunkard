package xyz.wongs.drunkard.base.persistence.mybatis.mapper;

import xyz.wongs.drunkard.base.entity.AbstractEntity;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName RootMapper
 * @Description 
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/8/19 20:54
 * @Version 1.0.0
*/
public interface RootMapper<T extends AbstractEntity,ID extends Serializable> {

    /** 获取一个对象List集合
     * @author WCNGS@QQ.COM
     * @See
     * @date 2019/7/23 19:27
     * @param t
     * @return
     * @throws
     * @since
     */
    List<T> getList(T t);

    /**  按条件查询对象反馈一个List集合
     * @Description
     * @param condition
     * @return
     * @throws
     * @date 2020/8/2 13:24
     */
    List<T> getListByCondition(Object condition);

    /**  按条件查询对象反馈一个List集合
     * @Description
     * @param example
     * @return
     * @throws
     * @date 2020/8/2 13:24
     */
    List<T> getListByExample(Object example);

    /** 根据主键删除一个对象
     * @Description
     * @param id
     * @return
     * @throws
     * @date 2020/8/2 13:24
     */
    int deleteByPrimaryKey(ID id);

    /** 按照主键查询一个对象
     * @Description
     * @param id
     * @return t
     * @throws
     * @date 2020/8/2 13:24
     */
    T selectByPrimaryKey(ID id);

    /** 按照主键选择性更新一个对象
     * @Description
     * @param t
     * @return
     * @throws
     * @date 2020/8/2 13:24
     */
    int updateByPrimaryKeySelective(T t);

    /** 按照主键和属性值更新一个对象
     * @Description
     * @param t
     * @return
     * @throws
     * @date 2020/8/2 13:24
     */
    int updateByPrimaryKeyWithBlob(T t);

    /** 按照主键更新一个对象
     * @Description
     * @param t
     * @return
     * @throws
     * @date 2020/8/2 13:24
     */
    int updateByPrimaryKey(T t);
}
