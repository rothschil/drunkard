package xyz.wongs.drunkard.base.persistence.mybatis.service;

import com.github.pagehelper.PageInfo;
import xyz.wongs.drunkard.base.po.BasePo;
import xyz.wongs.drunkard.base.persistence.mybatis.page.PaginationInfo;

import java.io.Serializable;

/**
 * @Description
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/8/19 20:58
 * @Version 1.0.0
*/
public interface SuperService<T extends BasePo,ID extends Serializable> {

    /**  待补充
     * @Description
     * @param pgInfo 分页信息
     * @param t 实体对象
     * @return PageInfo
     * @date 2020/8/2 14:07
     */
    PageInfo<T> selectPage(PaginationInfo pgInfo, T t);

    /**  待补充
     * @Description
     * @param condition 条件
     * @param pgInfo 分页信息
     * @return PageInfo
     * @date 2020/8/2 14:07
     */
    PageInfo<T> selectPageByCondition(PaginationInfo pgInfo, Object condition);


    /**  待补充
     * @Description
     * @param pgInfo 分页信息
     * @param example Example对象
     * @return PageInfo
     * @date 2020/8/2 14:07
     */
    PageInfo<T> selectByExample(PaginationInfo pgInfo, Object example);

    /**  待补充
     * @Description
     * @param id ID KEY
     * @return int
     * @date 2020/8/2 14:07
     */
    int deleteByPrimaryKey(ID id);


    /**  待补充
     * @Description
     * @param id ID KEY
     * @return int
     * @date 2020/8/2 14:08
     */
    T selectByPrimaryKey(ID id);

    /**  待补充
     * @Description
     * @param t 对象信息
     * @return int
     * @date 2020/8/2 14:08
     */
    int updateByPrimaryKeySelective(T t);

    /** 待补充
     * @Description
     * @param t 对象信息
     * @return int
     * @date 2020/8/2 14:08
     */
    int updateByPrimaryKeyWithBlob(T t);

    /** 待补充
     * @Description
     * @param t 对象信息
     * @return int
     * @date 2020/8/2 14:08
     */
    int updateByPrimaryKey(T t);
}
