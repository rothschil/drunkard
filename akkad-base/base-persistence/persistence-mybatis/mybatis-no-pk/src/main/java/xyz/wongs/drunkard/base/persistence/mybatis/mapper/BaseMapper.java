package xyz.wongs.drunkard.base.persistence.mybatis.mapper;

import xyz.wongs.drunkard.base.po.BasePo;

import java.io.Serializable;

/**
 * @Description 通用Mapper
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2019/11/21 17:40
 * @Version 1.0.0
*/
public interface BaseMapper<T extends BasePo,ID extends Serializable> extends SuperMapper<T,ID> {

    /** 持久化一个对象
     * @Description
     * @param t 对象信息
     * @return int
     * @date 2020/8/2 13:24
     */
    int insert(T t);

    /** 按条件将数据对象持久化
     * @Description
     * @param t 对象信息
     * @return int
     * @date 2020/8/2 13:24
     */
    int insertSelective(T t);
}
