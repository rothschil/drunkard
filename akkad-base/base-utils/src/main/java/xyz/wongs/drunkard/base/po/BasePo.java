package xyz.wongs.drunkard.base.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import xyz.wongs.drunkard.base.config.Global;
import xyz.wongs.drunkard.base.constant.Constant;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author WCNGS@QQ.COM
 * @Description
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/8/2 13:32
 * @Version 1.0.0
 */
public abstract class BasePo<ID extends Serializable> extends SuperPo<ID> {

    /**
     * 数据库类型
     * */
    @JSONField(serialize = false)
    private String dbType;

    /**
     * 搜索值
     */
    private String searchValue;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 创建者
     */
    private String createBy;

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

    public String getDbType() {
        return Global.getConfig(Constant.DB_TYPE);
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

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
