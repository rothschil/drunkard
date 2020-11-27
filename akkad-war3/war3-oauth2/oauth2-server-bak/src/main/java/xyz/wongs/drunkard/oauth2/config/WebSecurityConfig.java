package xyz.wongs.drunkard.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import xyz.wongs.drunkard.oauth2.util.MD5Util;

/**
 * @ClassName WebSecurityConfig
 * @Description  访问控制交给资源服务器，只保留 /oauth/**,/login/**,/logout/**
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 20/11/27 15:18
 * @Version 1.0.0
*/
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * userDetailsService 获取token的时候对用户进行一些自定义过滤，并将保存用户信息（用户名，密码，角色等）
     */
    @Autowired
    @Qualifier("userDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    /**
     * 使用MD5对client_secreat进行加密，可以使用默认的加密方式也可以自定义，这里使用MD5加密方式
     *
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return MD5Util.md5Encode(String.valueOf(charSequence),null);
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return s.equals(MD5Util.md5Encode(String.valueOf(charSequence),null));
            }
        };
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 配置用户签名服务 主要是user-details 机制，
     *
     * @param auth 签名管理器构造器，用于构建用户具体权限控制
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }


    @Deprecated
    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 加入了oauth 2.0 的配置，所以该文件之前的权限设置可以删掉，访问控制交给资源服务器只保留“/oauth/**,"/login/**”,"/logout/**"，修改后的WebSecurityConfig
//        http.csrf().disable()//禁用了 csrf 功能
//                .authorizeRequests()//限定签名成功的请求
//                .antMatchers("/decision/**","/govern/**").hasAnyRole("USER","ADMIN")//对decision和govern 下的接口 需要 USER 或者 ADMIN 权限
//                .antMatchers("/admin/login").permitAll()///admin/login 不限定
//                .antMatchers("/admin/**").hasRole("ADMIN")//对admin下的接口 需要ADMIN权限
//                .antMatchers("/oauth/**").permitAll()//不拦截 oauth 开放的资源
//                .anyRequest().permitAll()//其他没有限定的请求，允许访问
//                .and().anonymous()//对于没有配置权限的其他请求允许匿名访问
//                .and().formLogin()//使用 spring security 默认登录页面
//                .and().httpBasic();//启用http 基础验证
        //不拦截 oauth 开放的资源
        http.csrf().disable();
        //使HttpSecurity接收以"/login/","/oauth/"开头请求。
        http.requestMatchers()
                .antMatchers("/oauth/**", "/login/**", "/logout/**")
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/**").authenticated()
                .and()
                .formLogin();

    }

    /**
     * @Description Spring Security应该忽略URLS以xxx开头的路由
     * @param web
     * @return void
     * @throws
     * @date 20/11/27 15:52
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/oauth2/**");
    }
}