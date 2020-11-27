package xyz.wongs.drunkard.oauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

/**
 * @author WCNGS@QQ.COM
 * @ClassName ResourceServerConfig
 * @Description 配置资源服务中心 这个类表明了此应用是OAuth2 的资源服务器，此处主要指定了受资源服务器保护的资源链接
 * @Github <a>https://github.com/rothschil</a>
 * @date 20/11/27 11:14
 * @Version 1.0.0
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Primary
    @Bean
    public RemoteTokenServices tokenServices() {
        final RemoteTokenServices tokenService = new RemoteTokenServices();
        tokenService.setCheckTokenEndpointUrl("http://localhost:9090/oauth/check_token");
        tokenService.setClientId("client");
        tokenService.setClientSecret("123456");
        return tokenService;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //禁用了 csrf 功能
        http.csrf().disable()
                //限定签名成功的请求
                .authorizeRequests()
                //必须认证过后才可以访问;注意：hasAnyRole 会默认加上ROLE_前缀，而hasAuthority不会加前缀
                .antMatchers("/decision/**", "/govern/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/test/**").authenticated()
                //其他没有限定的请求，允许随意访问
                .anyRequest().permitAll()
                //对于没有配置权限的其他请求允许匿名访问
                .and().anonymous();
    }
}