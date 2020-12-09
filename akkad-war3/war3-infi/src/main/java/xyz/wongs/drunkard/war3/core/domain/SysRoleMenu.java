package xyz.wongs.drunkard.war3.core.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author WCNGS@QQ.COM
 * @ClassName SysRoleMenu
 * @Description 角色和菜单关联
 * @Github <a>https://github.com/rothschil</a>
 * @date 20/12/9 10:51
 * @Version 1.0.0
 */
public class SysRoleMenu {
    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 菜单ID
     */
    private Long menuId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("roleId", getRoleId())
                .append("menuId", getMenuId())
                .toString();
    }
}
