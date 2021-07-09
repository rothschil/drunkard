package xyz.wongs.drunkard.factory;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import xyz.wongs.drunkard.base.utils.DateUtils;
import xyz.wongs.drunkard.jwt.JwtBo;

import java.util.Date;
import java.util.UUID;

/**
 * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
 * @Description Token生成工厂类
 * @Github <a>https://github.com/rothschil</a>
 * @date 2021/7/6 - 15:24
 * @Version 1.0.0
 */
public class TokenFactory {

    /**
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description 生成token
     * @Date 2021/7/6-16:06
     * @Param jwtBo
     * @Param calendar
     * @return String
     **/
    public static String createToken(JwtBo jwtBo, int calendar){
        Date dateNow = DateUtils.getNowDate();
        return JWT.create()
                .withIssuer(jwtBo.getIssuer())
                .withSubject(jwtBo.getSubject())
                .withAudience(jwtBo.getAudience())
                .withIssuedAt(dateNow)
                .withExpiresAt(DateUtils.offset(dateNow, jwtBo.getExpTime(), calendar))
                .withClaim("data", JSON.toJSONString(jwtBo.getData()))
                .withNotBefore(dateNow)
                .withJWTId(UUID.randomUUID().toString())
                .sign(jwtBo.getAlgorithm());
    }
}
