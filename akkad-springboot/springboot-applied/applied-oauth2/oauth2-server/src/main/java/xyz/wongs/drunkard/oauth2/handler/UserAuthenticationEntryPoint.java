package xyz.wongs.drunkard.oauth2.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import xyz.wongs.drunkard.base.message.enums.ResultCode;
import xyz.wongs.drunkard.oauth2.util.JSONUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 用来解决匿名用户访问无权限资源时的异常
 * @ProjectName: spring-parent
 * @Package: com.yaomy.security.handler.AjaxAuthenticationEntryPoint
 * @Date: 2019/7/1 15:36
 * @Version: 1.0
 */
@Component
public class UserAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        JSONUtils.writeValue(response.getOutputStream(), ResultCode.USER_NOT_LOGIN_ERROR);
    }
}
