package xyz.wongs.drunkard.oauth2.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import xyz.wongs.drunkard.base.message.enums.ResultCode;
import xyz.wongs.drunkard.oauth2.util.JSONUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 用户认证成功处理
 * @ProjectName: spring-parent
 * @Package: com.yaomy.security.handler.AjaxAuthenticationSuccessHandler
 * @Date: 2019/7/1 15:38
 * @Version: 1.0
 */
@Component
public class UserAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        JSONUtils.writeValue(response.getOutputStream(), ResultCode.SUCCESS);
    }
}
