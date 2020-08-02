package xyz.wongs.drunktard.base.persistence.mybatis.entity;

import com.alibaba.fastjson.annotation.JSONField;
import xyz.wongs.drunktard.base.persistence.mybatis.config.Global;
import xyz.wongs.drunktard.base.persistence.mybatis.stas.Cons;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseEntityAbstract<ID extends Serializable> extends AbstractIdEntity<ID> {

    @JSONField
    private String dtype;

    public String getDtype() {
        return Global.getConfig(Cons.DB_TYPE);
    }

    /** 请求参数 */
    private Map<String, Object> params;

    public Map<String, Object> getParams()
    {
        if (params == null)
        {
            params = new HashMap<>();
        }
        return params;
    }

    public void setParams(Map<String, Object> params)
    {
        this.params = params;
    }

}
