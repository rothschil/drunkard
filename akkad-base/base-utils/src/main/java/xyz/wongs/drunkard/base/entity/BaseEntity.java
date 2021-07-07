package xyz.wongs.drunkard.base.entity;

import java.io.Serializable;


/**
 * @Description
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/8/2 13:32
 * @Version 1.0.0
*/
public abstract class BaseEntity<ID extends Serializable> implements Serializable {

    /** 无
     * @Description
     * @return ID
     * @date 2020/8/2 13:23
    */
    public abstract ID getId();

    /** 无
     * @Description
     * @param id
     * @date 2020/8/2 13:22
     */
    public abstract void setId(ID id);
}
