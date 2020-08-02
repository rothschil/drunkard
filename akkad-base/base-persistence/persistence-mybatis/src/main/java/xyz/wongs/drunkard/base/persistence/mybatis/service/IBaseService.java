package xyz.wongs.drunkard.base.persistence.mybatis.service;


import com.github.pagehelper.PageInfo;
import xyz.wongs.drunkard.base.persistence.mybatis.entity.BaseEntityAbstract;
import xyz.wongs.drunkard.base.persistence.mybatis.page.PaginationInfo;

import java.io.Serializable;

/**
 * @ClassName
 * @Description
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/8/2 13:31
 * @Version 1.0.0
 */
public interface IBaseService<T extends BaseEntityAbstract,ID extends Serializable>{

    /**  待补充
     * @Description
    * @param pgInfo
     * @param t
     * @return 
     * @throws 
     * @date 2020/8/2 14:07
    */
    PageInfo<T> selectPage(PaginationInfo pgInfo, T t);

    /**  待补充
     * @Description
     * @param condition
     * @param pgInfo
     * @return
     * @throws 
     * @date 2020/8/2 14:07
    */
    PageInfo<T> selectPageByCondition(PaginationInfo pgInfo, Object condition);

    
    /**  待补充
     * @Description
     * @param pgInfo
     * @param example
     * @return
     * @throws 
     * @date 2020/8/2 14:07
    */
    PageInfo<T> selectByExample(PaginationInfo pgInfo, Object example);
    
    /**  待补充
     * @Description
    * @param id
     * @return 
     * @throws 
     * @date 2020/8/2 14:07
    */
    int deleteByPrimaryKey(ID id);


    /**  待补充
     * @Description
    * @param t
     * @return 
     * @throws 
     * @date 2020/8/2 14:07
    */
    Long insert(T t);

    /**  待补充
     * @Description
    * @param t
     * @return 
     * @throws 
     * @date 2020/8/2 14:08
    */
    Long insertSelective(T t);

    /**  待补充
     * @Description
     * @param id
     * @return
     * @throws
     * @date 2020/8/2 14:08
    */
    T selectByPrimaryKey(ID id);

    /**  待补充
     * @Description
     * @param t
     * @return
     * @throws
     * @date 2020/8/2 14:08
     */
    int updateByPrimaryKeySelective(T t);

    /** 待补充
     * @Description
     * @param t
     * @return
     * @throws
     * @date 2020/8/2 14:08
     */
    int updateByPrimaryKeyWithBlob(T t);

    /** 待补充
     * @Description
     * @param t
     * @return
     * @throws
     * @date 2020/8/2 14:08
     */
    int updateByPrimaryKey(T t);
}
