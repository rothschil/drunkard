package xyz.wongs.weathertop.palant.controller;

import com.alibaba.druid.DruidRuntimeException;
import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.wongs.drunkard.base.message.annoation.ResponseResult;
import xyz.wongs.drunkard.base.message.enums.ResultCode;
import xyz.wongs.drunkard.base.message.exception.DrunkardException;
import xyz.wongs.drunkard.base.utils.DateUtils;
import xyz.wongs.drunkard.base.utils.StringUtils;
import xyz.wongs.weathertop.dto.JwtDto;
import xyz.wongs.weathertop.jwt.RSA256Key;
import xyz.wongs.weathertop.jwt.entity.User;
import xyz.wongs.weathertop.dao.CreateSecrteKey;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@RestController
public class TokenController {

    /**
     * 加密密钥
     */
    private static final String SECRET = "rstyro";

    @ApiOperation("获取Token 通过HS256加密")
    @GetMapping("/getTokenByHS256")
    public String generTokenByHS256() throws Exception{
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        return createToken(algorithm, User.getAuther());
    }

    @ApiOperation("获取Token 通过RS256加密")
    @GetMapping("/getTokenByRS256")
    public String generTokenByRS256() throws Exception{
        RSA256Key rsa256Key = CreateSecrteKey.getRSA256Key();
        Algorithm algorithm = Algorithm.RSA256(rsa256Key.getPublicKey(), rsa256Key.getPrivateKey());
        // 返回token
        return createToken(algorithm, User.getAuther());
    }

    @ApiOperation("获取token ,但是 10 秒内，有效")
    @GetMapping("/getTokenExpire")
    public String getTokenByRS256AndTimer() throws Exception{
        RSA256Key rsa256Key = CreateSecrteKey.getRSA256Key();
        Algorithm algorithm = Algorithm.RSA256(rsa256Key.getPublicKey(), rsa256Key.getPrivateKey());
        String token =JWT.create()
                .withIssuer("rstyro")   //发布者
                .withSubject("test")    //主题
                .withIssuedAt(new Date())   // 生成签名的时间
                .withExpiresAt(DateUtils.offset(new Date(),20, Calendar.SECOND))    // 生成签名的有效期,20秒
                .withClaim("data", JSON.toJSONString(User.getAuther())) //存数据
                .sign(algorithm);
        return token;
    }

    /**
     * 通过token 返回数据
     * @param jwtdto
     * @return
     * @throws Exception
     */
    @ApiOperation("通过token 获取数据")
    @PostMapping("/getDataByToken")
    public User getDataByToken(JwtDto jwtdto) throws Exception{
        Algorithm algorithm =null;
        DecodedJWT verify =null;
        if(StringUtils.isEmpty(jwtdto.getAlg())){
            throw new DrunkardException(ResultCode.PARAMS_IS_BANK);
        }
        if("rs256".equalsIgnoreCase(jwtdto.getAlg())){
            algorithm = Algorithm.RSA256(CreateSecrteKey.getRSA256Key().getPublicKey(), null);
        }else {
            algorithm = Algorithm.HMAC256(SECRET);
        }
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("rstyro")
                .build();
        try {
            verify = verifier.verify(jwtdto.getToken());
        }catch (TokenExpiredException ex){
            throw new DrunkardException(ResultCode.USER_TOKEN_EXPIRED);
        }catch (JWTVerificationException ex){
            throw new JWTVerificationException(ResultCode.USER_SIGN_VERIFI_NOT_COMPLIANT.getMsg());
        }
        String dataString = verify.getClaim("data").asString();
        return JSON.parseObject(dataString,User.class);
    }

    /**
     * 生成token
     * @param algorithm
     * @param data
     * @return
     */
    public String createToken(Algorithm algorithm,Object data){
        String[] audience  = {"app","web"};
        return JWT.create()
                .withIssuer("rstyro")   //发布者
                .withSubject("test")    //主题
                .withAudience(audience)     //观众，相当于接受者
                .withIssuedAt(new Date())   // 生成签名的时间
                .withExpiresAt(DateUtils.offset(new Date(),2, Calendar.HOUR_OF_DAY))    // 生成签名的有效期,分钟
                .withClaim("data", JSON.toJSONString(data)) //存数据
                .withNotBefore(new Date())  //生效时间
                .withJWTId(UUID.randomUUID().toString())    //编号
                .sign(algorithm);
    }
}
