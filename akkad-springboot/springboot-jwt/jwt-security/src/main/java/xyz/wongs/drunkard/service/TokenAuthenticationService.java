package xyz.wongs.drunkard.service;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.util.Assert;
import xyz.wongs.drunkard.base.constant.Constant;
import xyz.wongs.drunkard.base.message.enums.ResultCode;
import xyz.wongs.drunkard.base.message.exception.DrunkardException;
import xyz.wongs.drunkard.base.message.response.Result;
import xyz.wongs.drunkard.base.utils.DateUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
 * @Description //TODO
 * @Github <a>https://github.com/rothschil</a>
 * @date 2021/7/9 - 9:58
 * @Version 1.0.0
 */
public class TokenAuthenticationService {

    private static Logger LOG = LoggerFactory.getLogger(TokenAuthenticationService.class);
    /**
    * 60
     */
    static final int EXPIRATION_TIME = 60;
    /**
     * JWT密码
     */
    static final String SECRET = "P@ssw02d";
    /**
     * Token前缀
     */
    static final String TOKEN_PREFIX = "Bearer";
    /**
     * 存放Token的Header Key
     */
    static final String HEADER_STRING = "Authorization";

    /**
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description JWT生成方法
     * @Date 2021/7/9-9:58
     * @Param response response请求
     * @param userName 账号名称
     **/
    public static void addAuthentication(HttpServletResponse response, String userName) {

        Date dateNow = DateUtils.getNowDate();
        // 生成JWT
        String jwt = Jwts.builder()
                // 保存权限（角色）
                .claim("authorities", "ROLE_ADMIN,AUTH_WRITE")
                // 用户名写入标题
                .setSubject(userName)
                // 有效期设置
                .setExpiration(DateUtils.offset(dateNow, EXPIRATION_TIME, Calendar.SECOND))
                // 签名设置
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        // 将 JWT 写入 body
        try {
            response.setContentType(Constant.APPLICATION_JSON);
            response.setStatus(HttpServletResponse.SC_OK);
            response.getOutputStream().println(JSONObject.toJSON(Result.success(jwt)).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description JWT验证方法
     * @Date 2021/7/9-10:02
     * @Param request
     * @return Authentication
     **/
    public static Authentication getAuthentication(HttpServletRequest request) {
        // 从Header中拿到token
        String token = request.getHeader(HEADER_STRING);

        if (token != null) {
            LOG.error(token);
            // 解析 Token
            Claims claims = null;
            try {
                claims = Jwts.parser()
                        // 验签
                        .setSigningKey(SECRET)
                        // 去掉 Bearer
                        .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                        .getBody();
            } catch (ExpiredJwtException e) {
                throw new DrunkardException(ResultCode.TOKEN_EXPIRED);
            } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e) {
                e.printStackTrace();
            } catch (SignatureException e) {
                throw new DrunkardException(ResultCode.TOKEN_INVALID);
            }
            Assert.notNull(claims,"The Claims must not be null");
            // 拿用户名
            String user = claims.getSubject();

            // 得到 权限（角色）
            List<GrantedAuthority> authorities =  AuthorityUtils.commaSeparatedStringToAuthorityList((String) claims.get("authorities"));

            // 返回验证令牌
            return user != null ?
                    new UsernamePasswordAuthenticationToken(user, null, authorities) :
                    null;
        }
        return null;
    }

}
