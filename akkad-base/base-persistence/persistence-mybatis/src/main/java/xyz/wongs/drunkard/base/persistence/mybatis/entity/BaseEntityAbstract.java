package xyz.wongs.drunkard.base.persistence.mybatis.entity;

import com.alibaba.fastjson.annotation.JSONField;
import xyz.wongs.drunkard.base.persistence.mybatis.config.Global;
import xyz.wongs.drunkard.base.persistence.mybatis.stas.Cons;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author WCNGS@QQ.COM
 * @ClassName BaseEntityAbstract
 * @Description
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/8/2 13:32
 * @Version 1.0.0
 */
public abstract class BaseEntityAbstract<ID extends Serializable> extends AbstractIdEntity<ID> {

    @JSONField
    private String dtype;

    public String getDtype() {
        return Global.getConfig(Cons.DB_TYPE);
    }

    /**
     * 请求参数
     */
    private Map<String, Object> params;

    public Map<String, Object> getParams() {
        if (params == null) {
            params = new HashMap<>(12);
        }
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

}