package xyz.wongs.drunkard.oauth2.web;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName LoginController
 * @Description 登录测试接口
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 20/11/27 15:11
 * @Version 1.0.0
*/
@Slf4j
@RestController
@RequestMapping("/auth_user")
public class LoginController {
    /**
     * @Description 登录测试接口
     * @Date 2019/7/8 17:42
     * @Version  1.0
     */
    @RequestMapping(value = "/get_token_info", method = RequestMethod.GET)
    public Object login(HttpServletRequest request){
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) auth;
        log.info(auth.getAuthorities()+"--"+auth.getCredentials()+"--"+auth.getDetails()+"--"+auth.getPrincipal()+"--"+auth.getName());
        return oAuth2Authentication;
    }
    /**
     * @Description 获取授权码
     * @Date 2019/7/9 15:44
     * @Version  1.0
     */
    @RequestMapping("/get_auth_code")
    public String home(HttpServletRequest request) {
        String code = request.getParameter("code");
        log.info("--------code----------------"+code);
        if(StringUtils.isBlank(code)){
            code="denyAll";
        }
        return code;
    }

}
