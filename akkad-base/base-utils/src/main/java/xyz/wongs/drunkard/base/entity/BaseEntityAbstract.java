package xyz.wongs.drunkard.base.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import xyz.wongs.drunkard.base.config.Global;
import xyz.wongs.drunkard.base.stas.Cons;

import java.io.Serializable;
import java.util.Date;
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
@Data
public abstract class BaseEntityAbstract<ID extends Serializable> extends AbstractIdEntity<ID> {

    @JSONField(serialize = false)
    private String dtype;

    /**
     * 搜索值
     */
    private String searchValue;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 请求参数
     */
    private Map<String, Object> params;

    public String getDtype() {
        return Global.getConfig(Cons.DB_TYPE);
    }

    public Map<String, Object> getParams() {
        if (params == null) {
            params = new HashMap<>(6);
        }
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

}
