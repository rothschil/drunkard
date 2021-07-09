package xyz.wongs.drunkard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import xyz.wongs.drunkard.jwt.po.Role;
import xyz.wongs.drunkard.jwt.po.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
 * @Description //TODO
 * @Github <a>https://github.com/rothschil</a>
 * @date 2021/7/9 - 15:58
 * @Version 1.0.0
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null || "".equals(username))
        {
            throw new RuntimeException("用户不能为空");
        }
        // 调用方法查询用户
        User user = userService.getUserById(username);
        if (user == null)
        {
            throw new RuntimeException("用户不存在");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role:userService.findRoleByUsername(username)){
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),"{noop}"+user.getPassword(),authorities);
    }
}
