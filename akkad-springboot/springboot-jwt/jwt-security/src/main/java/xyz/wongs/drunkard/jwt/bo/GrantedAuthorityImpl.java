package xyz.wongs.drunkard.jwt.bo;

import org.springframework.security.core.GrantedAuthority;

/**
 * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
 * @Description 权限存储
 * @Github <a>https://github.com/rothschil</a>
 * @date 2021/7/9 - 9:53
 * @Version 1.0.0
 */
public class GrantedAuthorityImpl implements GrantedAuthority {

    private String authority;

    public GrantedAuthorityImpl(String authority) {
        this.authority = authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
