package xyz.wongs.drunkard.oauth2.domain.service;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import xyz.wongs.drunkard.oauth2.authority.UserGrantedAuthority;

import java.util.Arrays;
import java.util.List;

/**
 * @Description: 加载用户的核心数据，它在整个框架中作为用户的Dao使用，被{@link org.springframework.security.authentication.dao.DaoAuthenticationProvider}使用
 * @ProjectName: spring-parent
 * @Package: com.yaomy.security.po.User
 * @Date: 2019/6/28 17:37
 * @Version: 1.0
 */
@Component
public class UserAuthDetailsService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    /**
     * @Description 根据用户名查询用户角色、权限等信息
     * @Date 2019/7/1 14:50
     * @Version  1.0
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        GrantedAuthority authority = new UserGrantedAuthority("username", username);

        List<String> list = Lists.newArrayList();
        list.add("/a/b");
        list.add("/a/c");
        list.add("/oauth/token");
        GrantedAuthority interfaces = new UserGrantedAuthority("interfaces", list);
        /**
         isEnabled 账户是否启用
         isAccountNonExpired 账户没有过期
         isCredentialsNonExpired 身份认证是否是有效的
         isAccountNonLocked 账户没有被锁定
         */
         return new User(username, passwordEncoder.encode("123"),
                true,
                true,
                true,
                true,
                 Arrays.asList(authority, interfaces));
    }

}
