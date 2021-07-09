package xyz.wongs.drunkard.jwt.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import xyz.wongs.drunkard.base.message.enums.ResultCode;
import xyz.wongs.drunkard.base.message.exception.DrunkardException;
import xyz.wongs.drunkard.base.utils.DateUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
 * @Description //TODO
 * @Github <a>https://github.com/rothschil</a>
 * @date 2021/7/9 - 15:44
 * @Version 1.0.0
 */
public class JwtTokenUtil {

    /**
     * 过期时间 默认为 60
     */
    public static final int TOKEN_EXPIRATION_TIME = 60;
    /**
     * JWT密钥
     */
    public static final String APP_SECRET = "drunkard_secret";
    /**
     * Token前缀
     */
    public static final String TOKEN_PREFIX = "Bearer ";
    /**
     * Token 主题
     */
    public static final String TOKEN_SUBJECT = "drunkard";
    /**
     * 存放Token的Header Key
     */
    public static final String TOKEN_HEADER = "Authorization";

    /**
     * 角色权限声明
     */
    public static final String ROLE_CLAIMS = "role";

    public static final String USER_NAME="userName";

    /**
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description //TODO
     * @Date 2021/7/9-15:49
     * @Param userName 账号名称
     * @param role 角色
     * @return String
     **/
    public static String createToken(String userName,String role) {
        Map<String,Object> map = new HashMap<>(4);
        map.put(ROLE_CLAIMS, role);
        Date dateNow = DateUtils.getNowDate();
        return Jwts
                .builder()
                .setSubject(userName)
                .setClaims(map)
                .claim(USER_NAME,userName)
                .setIssuedAt(dateNow)
                .setExpiration(DateUtils.offset(dateNow, TOKEN_EXPIRATION_TIME, Calendar.SECOND))
                .signWith(SignatureAlgorithm.HS256, APP_SECRET).compact();
    }

    /**
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description 校验Token
     * @Date 2021/7/9-15:51
     * @Param token
     * @return Claims
     **/
    public static Claims checkJwt(String token) {
        try {
            return Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            throw new DrunkardException(ResultCode.TOKEN_VERIFICATION_FAIL);
        }
    }

    /**
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description 从Token中获取 USER_NAME
     * @Date 2021/7/9-15:51
     * @Param token
     * @return Claims
     **/
    public static String getUsername(String token){
        Claims claims = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody();
        return claims.get(USER_NAME).toString();
    }

    /**
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description 从Token中获取用户角色
     * @Date 2021/7/9-15:51
     * @Param token
     * @return Claims
     **/
    public static String getUserRole(String token){
        Claims claims = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody();
        return claims.get(ROLE_CLAIMS).toString();
    }

    /**
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description 校验Token是否过期
     * @Date 2021/7/9-15:51
     * @Param token
     * @return Claims
     **/
    public static boolean isExpiration(String token){
        Claims claims = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody();
        return claims.getExpiration().before(new Date());
    }
}
