package xyz.wongs.drunkard.jwt.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import xyz.wongs.drunkard.base.message.enums.ResultCode;
import xyz.wongs.drunkard.base.message.exception.DrunkardException;
import xyz.wongs.drunkard.jwt.annotation.IgnoreTokenCheck;
import xyz.wongs.drunkard.jwt.annotation.LoginToken;
import xyz.wongs.drunkard.jwt.po.User;
import xyz.wongs.drunkard.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
 * @Description 拦截器，获取 Token，并验证 Token合法性
 * @Github <a>https://github.com/rothschil</a>
 * @date 2021/7/6 - 10:25
 * @Version 1.0.0
 */
public class AuthenticationInterceptor implements HandlerInterceptor {

    private static String TOKEN = "token";

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean flag = handler instanceof HandlerMethod;
        if (!flag) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if (method.isAnnotationPresent(IgnoreTokenCheck.class)) {
            IgnoreTokenCheck ignoreTokenCheck = method.getAnnotation(IgnoreTokenCheck.class);
            if (ignoreTokenCheck.required()) {
                return true;
            }
        }
        if (!method.isAnnotationPresent(LoginToken.class)) {
            return true;
        }

        LoginToken loginToken = method.getAnnotation(LoginToken.class);
        if (loginToken.required()) {
            String token = request.getHeader(TOKEN);
            if (StringUtils.isEmpty(token)) {
                throw new DrunkardException(ResultCode.USER_NOT_LOGGED_IN);
            }
            String id;
            try {
                id = JWT.decode(token).getAudience().get(0);
            } catch (JWTDecodeException e) {
                throw new DrunkardException(ResultCode.TOKEN_EXPIRED);
            }
            User user = userService.getUserById(id);
            if (null == user) {
                throw new DrunkardException(ResultCode.USER_NOT_LOGIN_ERROR);
            }
            // 验证 token
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getName())).build();
            try {
                jwtVerifier.verify(token);
            } catch (JWTVerificationException e) {
                throw new DrunkardException(ResultCode.USER_SIGN_VERIFY_NOT_COMPLIANT);
            }
            return true;
        }


        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
