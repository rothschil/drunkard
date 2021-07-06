package xyz.wongs.weathertop.tool;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import xyz.wongs.drunkard.base.utils.DateUtils;

import java.util.Date;
import java.util.UUID;

/**
 * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
 * @Description //TODO
 * @Github <a>https://github.com/rothschil</a>
 * @date 2021/7/6 - 15:24
 * @Version 1.0.0
 */
public class TokenTool {

    /**
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description 生成token
     * @Date 2021/7/6-16:06
     * @Param jwtInfo
     * @return String
     **/
    public static String createToken(JwtInfo jwtInfo,int calendar){
        Date dateNow = DateUtils.getNowDate();
        return JWT.create()
                .withIssuer(jwtInfo.getIssuer())
                .withSubject(jwtInfo.getSubject())
                .withAudience(jwtInfo.getAudience())
                .withIssuedAt(dateNow)
                .withExpiresAt(DateUtils.offset(dateNow,jwtInfo.getExpTime(), calendar))
                .withClaim("data", JSON.toJSONString(jwtInfo.getData()))
                .withNotBefore(dateNow)
                .withJWTId(UUID.randomUUID().toString())
                .sign(jwtInfo.getAlgorithm());
    }
}
