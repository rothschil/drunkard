package xyz.wongs.drunkard.base.persistence.mybatis.service;


import xyz.wongs.drunkard.base.entity.BaseEntityAbstract;

import java.io.Serializable;

/**
 * @ClassName
 * @Description
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/8/2 13:31
 * @Version 1.0.0
 */
public interface IBaseService<T extends BaseEntityAbstract,ID extends Serializable> extends RootService<T,ID>{

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

}
