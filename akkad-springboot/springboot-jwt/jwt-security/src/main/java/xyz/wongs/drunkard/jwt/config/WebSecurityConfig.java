package xyz.wongs.drunkard.jwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import xyz.wongs.drunkard.base.constant.Constant;
import xyz.wongs.drunkard.jwt.filter.JwtAuthenticationEntryPoint;
import xyz.wongs.drunkard.jwt.filter.JwtAuthenticationFilter;
import xyz.wongs.drunkard.jwt.filter.JwtAuthorizationFilter;

/**
 * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
 * @Description //TODO
 * @Github <a>https://github.com/rothschil</a>
 * @date 2021/7/9 - 9:55
 * @Version 1.0.0
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final static String LOGIN_URL="/login";

    @Autowired
    @Qualifier("userDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 关闭csrf验证
        http.csrf().disable()
                // 对请求进行认证
                .authorizeRequests()
                // 所有 / 的所有请求 都放行
                .antMatchers(Constant.SLASH).permitAll()
                // 所有 /login 的POST请求 都放行
                .antMatchers(HttpMethod.POST, LOGIN_URL).permitAll()
                // 权限检查
                .antMatchers("/hello").hasAuthority("AUTH_WRITE")
                // 角色检查
                .antMatchers("/world").hasRole("ADMIN")
                .antMatchers("/data").hasRole("ADMIN")
                // 所有请求需要身份认证 authenticated()
                .anyRequest().permitAll()
                .and()
                // 添加一个过滤器 所有访问 /login 的请求交给 JWTLoginFilter 来处理 这个类处理所有的JWT相关内容
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager()))
                // 设置Session的创建策略为：Spring Security永不创建HttpSession 不使用HttpSession来获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().exceptionHandling()
                .authenticationEntryPoint(new JwtAuthenticationEntryPoint());

    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 注册跨域配置
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

}
