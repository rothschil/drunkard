package xyz.wongs.drunkard.jwt.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import xyz.wongs.drunkard.base.message.enums.ResultCode;
import xyz.wongs.drunkard.base.message.exception.DrunkardException;
import xyz.wongs.drunkard.jwt.bo.GrantedAuthorityImpl;

import java.util.ArrayList;

/**
 * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
 * @Description 自定义身份认证验证组件
 * @Github <a>https://github.com/rothschil</a>
 * @date 2021/7/9 - 10:05
 * @Version 1.0.0
 */
public class CustomAuthenticationProvider implements AuthenticationProvider {

    /**
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description //TODO
     * @Date 2021/7/9-10:06
     * @Param authentication
     * @return Authentication
     **/
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取认证的用户名 & 密码
        String name = authentication.getName();
        String passWord = authentication.getCredentials().toString();
        // 认证逻辑
        if (name.equals("admin")&& passWord.equals("123")) {
            // 这里设置权限和角色
            ArrayList<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add( new GrantedAuthorityImpl("ROLE_ADMIN") );
            authorities.add( new GrantedAuthorityImpl("AUTH_WRITE") );
            // 生成令牌
            Authentication auth = new UsernamePasswordAuthenticationToken(name, passWord, authorities);
            return auth;
        }else {
            throw new DrunkardException(ResultCode.USER_NOT_LOGIN_ERROR);
        }
    }

    /**
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description 是否可以提供输入类型的认证服务
     * @Date 2021/7/9-10:06
     * @Param authentication
     * @return boolean
     **/
    @Override
    public boolean supports(Class<?> authentication) {

        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
