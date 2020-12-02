package xyz.wongs.drunkard.oauth2.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import xyz.wongs.drunkard.oauth2.domain.entity.OauthUser;
import xyz.wongs.drunkard.oauth2.domain.entity.SysPermission;
import xyz.wongs.drunkard.oauth2.domain.mapper.OauthUserMapper;
import xyz.wongs.drunkard.oauth2.domain.service.OauthUserService;
import xyz.wongs.drunkard.oauth2.domain.service.SysPermissionService;

import java.util.ArrayList;
import java.util.List;

/** 进行登录用户自定义过滤
 * @ClassName UserDetailsServiceImpl
 * @Description
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 20/11/26 17:41
 * @Version 1.0.0
*/
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private OauthUserMapper oauthUserMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private OauthUserService oauthUserService;

    @Autowired
    private SysPermissionService sysPermissionService;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        OauthUser user = new OauthUser();
        List<OauthUser> users = oauthUserMapper.select(user);
        // 如果用户不存在则认证失败
        if (users.isEmpty()) {
            throw new UsernameNotFoundException(username + " not found");
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        user = users.get(0);
        log.info("loadUserByUsername username=" + username);
        List<SysPermission> permissions = sysPermissionService.findByAdminUserId(user.getId());

        //声明授权文件
        for (SysPermission permission : permissions) {
            if (permission != null && permission.getPermName() != null) {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + permission.getPermName());
                grantedAuthorities.add(grantedAuthority);
            }

            // 注意：此处的密码记得要进行加密，因为在前面配置的时候是使用了 MD5 加密，所以这里也要进行加密
        }
        log.error("grantedAuthorities={}", grantedAuthorities);
        return new User(user.getUName(), user.getPassWord(), grantedAuthorities);
    }
}
