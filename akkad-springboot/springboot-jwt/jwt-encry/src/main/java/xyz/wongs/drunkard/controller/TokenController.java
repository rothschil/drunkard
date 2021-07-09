package xyz.wongs.drunkard.controller;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.wongs.drunkard.base.message.enums.ResultCode;
import xyz.wongs.drunkard.base.message.exception.DrunkardException;
import xyz.wongs.drunkard.base.utils.StringUtils;
import xyz.wongs.drunkard.factory.KeyFactory;
import xyz.wongs.drunkard.jwt.JwtDto;
import xyz.wongs.drunkard.jwt.RsaKeyBo;
import xyz.wongs.drunkard.jwt.entity.User;
import xyz.wongs.drunkard.service.UserService;
import xyz.wongs.drunkard.jwt.JwtBo;
import xyz.wongs.drunkard.factory.TokenFactory;
import java.util.Calendar;

/**
 * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
 * @Description //TODO
 * @Github <a>https://github.com/rothschil</a>
 * @date 2021/7/6 - 16:24
 * @Version 1.0.0
 */
@RestController
public class TokenController {

    @Autowired
    private UserService userService;
    /**
     * 加密密钥
     */
    private static final String SECRET = "rstyro";

    private static final String RS256 ="rs256";

    /**
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description 通过HS256加密获取Token
     * @Date 2021/7/6-16:25
     * @Param
     * @return String
     **/
    @GetMapping("/getTokenByHS256")
    public String generateTokenByHS256() throws Exception{
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        JwtBo jwtBo = new JwtBo(algorithm,userService.getUser());
        return TokenFactory.createToken(jwtBo, Calendar.SECOND);
    }

    /**
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description 通过RS256加密获取Token
     * @Date 2021/7/6-16:25
     * @Param
     * @return String
     **/
    @GetMapping("/getTokenByRS256")
    public String generateTokenByRS256() throws Exception{
        return generateToken(0);
    }

    /**
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description 获取token ,但是有效期在当前时间 10 秒内
     * @Date 2021/7/6-16:25
     * @Param
     * @return String
     **/
    @GetMapping("/getTokenExpire")
    public String generateTokenByRS256AndTimer() throws Exception{
        return generateToken(20);
    }
    /**
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description 通过token 返回数据
     * @Date 2021/7/6-16:25
     * @Param   jwtdto：
     * @return String
     **/
    @PostMapping("/getDataByToken")
    public User getDataByToken(JwtDto jwtdto) throws Exception{
        if(StringUtils.isEmpty(jwtdto.getAlg())){
            throw new DrunkardException(ResultCode.PARAMS_IS_BANK);
        }
        Algorithm algorithm =null;
        DecodedJWT verify =null;
        if(RS256.equalsIgnoreCase(jwtdto.getAlg())){
            algorithm = Algorithm.RSA256(KeyFactory.getRSA256Key().getPublicKey(), null);
        }else {
            algorithm = Algorithm.HMAC256(SECRET);
        }
        JWTVerifier verifier = JWT.require(algorithm).withIssuer("weathertop").build();
        try {
            verify = verifier.verify(jwtdto.getToken());
        }catch (TokenExpiredException ex){
            throw new DrunkardException(ResultCode.TOKEN_EXPIRED);
        }catch (JWTVerificationException ex){
            throw new DrunkardException(ResultCode.TOKEN_VERIFICATION_FAIL);
        }
        String dataString = verify.getClaim("data").asString();
        return JSON.parseObject(dataString,User.class);
    }

    private String generateToken(int expTime){
        RsaKeyBo rsaKeyBo = null;
        JwtBo jwtBo = null;
        try {
            rsaKeyBo = KeyFactory.getRSA256Key();
            Algorithm algorithm = Algorithm.RSA256(rsaKeyBo.getPublicKey(), rsaKeyBo.getPrivateKey());
            jwtBo = new JwtBo(algorithm,userService.getUser());
            if(expTime>0){
                jwtBo.setExpTime(expTime);
            }
        } catch (Exception e) {
            throw new DrunkardException(ResultCode.USER_KEY_EXCEPTION);
        }
        return TokenFactory.createToken(jwtBo, Calendar.SECOND);
    }
}
