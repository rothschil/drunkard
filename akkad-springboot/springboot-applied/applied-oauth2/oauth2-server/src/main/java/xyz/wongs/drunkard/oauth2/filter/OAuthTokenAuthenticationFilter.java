package xyz.wongs.drunkard.oauth2.filter;

import org.springframework.http.HttpMethod;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName OAuthTokenAuthenticationFilter
 * @Description token端点过滤器
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 20/11/27 14:59
 * @Version 1.0.0
*/
@Component
public class OAuthTokenAuthenticationFilter extends GenericFilterBean {
    private static final String OAUTH_TOKEN_URL = "/oauth2/token";

    private RequestMatcher requestMatcher;

    public OAuthTokenAuthenticationFilter(){
        //OrRequestMatcher or组合多个RequestMatcher
        this.requestMatcher = new OrRequestMatcher(
                new AntPathRequestMatcher(OAUTH_TOKEN_URL, HttpMethod.POST.name())
        );
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if(requestMatcher.matches(request)){
            if(false){
                response.getWriter().println("验证码或者图形验证码不正确");
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

}
