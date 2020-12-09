package xyz.wongs.drunkard.war3.core.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author WCNGS@QQ.COM
 * @ClassName SysUserRole
 * @Description 用户和角色关联
 * @Github <a>https://github.com/rothschil</a>
 * @date 20/12/9 10:52
 * @Version 1.0.0
 */
public class SysUserRole {
    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("userId", getUserId())
                .append("roleId", getRoleId())
                .toString();
    }
}
