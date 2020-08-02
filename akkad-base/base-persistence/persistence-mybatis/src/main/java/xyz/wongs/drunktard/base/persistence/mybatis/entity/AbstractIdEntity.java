package xyz.wongs.drunktard.base.persistence.mybatis.entity;

import java.io.Serializable;


public abstract class AbstractIdEntity<ID extends Serializable> implements Serializable {

    public abstract ID getId();

    public abstract void setId(ID id);
}
