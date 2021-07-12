package xyz.wongs.drunkard.oauth2.web;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.wongs.drunkard.base.message.exception.DrunkardException;
import xyz.wongs.drunkard.oauth2.util.JSONUtils;
import xyz.wongs.drunkard.oauth2.util.enums.DateFormatEnum;
import xyz.wongs.drunkard.oauth2.util.enums.GrantTypeEnum;

import java.util.*;

/**
 * @ClassName OAuth2Controller
 * @Description 
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 20/11/27 15:26
 * @Version 1.0.0
*/
@Slf4j
@RestController
@RequestMapping(value = "/oauth2")
public class OAuth2Controller {

    @Autowired
    private Environment propertyService;
    @Autowired
    @Lazy
    private TokenStore tokenStore;

    /**
     * @Description /oauth/token(令牌端点) 获取用户token信息
     * @Date 2019/7/22 15:59
     * @Version  1.0
     */
    @PostMapping(value = "/token")
    public Map<String, Object> getToken(@RequestParam String username, @RequestParam String password){

        ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();
        resource.setId(propertyService.getProperty("spring.security.oauth.resource.id"));
        resource.setClientId(propertyService.getProperty("spring.security.oauth.resource.client.id"));
        resource.setClientSecret(propertyService.getProperty("spring.security.oauth.resource.client.secret"));
        resource.setGrantType(GrantTypeEnum.PASSWORD.getGrantType());
        resource.setAccessTokenUri(propertyService.getProperty("spring.security.oauth.token.uri"));
        resource.setUsername(username);
        resource.setPassword(password);
        resource.setScope(Arrays.asList("all"));

        OAuth2RestTemplate template = new OAuth2RestTemplate(resource);
        ResourceOwnerPasswordAccessTokenProvider provider = new ResourceOwnerPasswordAccessTokenProvider();
        template.setAccessTokenProvider(provider);
        try {
            OAuth2AccessToken accessToken = template.getAccessToken();
            Map<String, Object> result = Maps.newHashMap();
            result.put("access_token", accessToken.getValue());
            result.put("token_type", accessToken.getTokenType());
            result.put("refresh_token", accessToken.getRefreshToken().getValue());
            result.put("expires_in", accessToken.getExpiresIn());
            result.put("scope", StringUtils.join(accessToken.getScope(), ","));
            result.putAll(accessToken.getAdditionalInformation());

            Collection<? extends GrantedAuthority> authorities = tokenStore.readAuthentication(template.getAccessToken()).getUserAuthentication().getAuthorities();
            List<Map> list = Lists.newArrayList();
            for(GrantedAuthority authority:authorities){
                list.add(JSONUtils.toJavaBean(authority.getAuthority(), Map.class));
            }
            result.put("authorities", list);
            return result;
        } catch (Exception e){
            throw new DrunkardException("登录异常，请检查登录信息...");
        }
    }
    /**
     * @Description /oauth/token（令牌端点）刷新token信息
     * @Date 2019/7/25 16:13
     * @Version  1.0
     */
    @PostMapping(value = "refresh_token")
    public Map<String, Object> refreshToken(@RequestParam String refresh_token){
        try {
            ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();
            resource.setId(propertyService.getProperty("spring.security.oauth.resource.id"));
            resource.setClientId(propertyService.getProperty("spring.security.oauth.resource.client.id"));
            resource.setClientSecret(propertyService.getProperty("spring.security.oauth.resource.client.secret"));
            resource.setGrantType(GrantTypeEnum.REFRESH_TOKEN.getGrantType());
            resource.setAccessTokenUri(propertyService.getProperty("spring.security.oauth.token.uri"));

            ResourceOwnerPasswordAccessTokenProvider provider = new ResourceOwnerPasswordAccessTokenProvider();
            OAuth2RefreshToken refreshToken = tokenStore.readRefreshToken(refresh_token);
            OAuth2AccessToken accessToken = provider.refreshAccessToken(resource, refreshToken, new DefaultAccessTokenRequest());

            Map<String, Object> result = Maps.newLinkedHashMap();
            result.put("access_token", accessToken.getValue());
            result.put("token_type", accessToken.getTokenType());
            result.put("refresh_token", accessToken.getRefreshToken().getValue());
            result.put("expires_in", accessToken.getExpiresIn());
            result.put("scope", StringUtils.join(accessToken.getScope(), ","));
            result.putAll(accessToken.getAdditionalInformation());

            Collection<? extends GrantedAuthority> authorities = tokenStore.readAuthentication(accessToken).getUserAuthentication().getAuthorities();
            List<Map> list = Lists.newArrayList();
            for(GrantedAuthority authority:authorities){
                list.add(JSONUtils.toJavaBean(authority.getAuthority(), Map.class));
            }
            result.put("authorities", list);
            return result;
        } catch (Exception e){
            throw new DrunkardException("登录异常，请检查登录信息...");
        }
    }
    /**
     * @Description oauth/check_token（端点校验）token有效性
     * @Date 2019/7/25 16:22
     * @Version  1.0
     */
    @PostMapping(value = "check_token")
    public Map<String, Object> checkToken(@RequestParam String access_token){
        try {
            OAuth2AccessToken accessToken = tokenStore.readAccessToken(access_token);
            OAuth2Authentication auth2Authentication = tokenStore.readAuthentication(access_token);
            Map<String, Object> map = Maps.newHashMap();
            //用户名
            map.put("username", auth2Authentication.getUserAuthentication().getName());
            //是否过期
            map.put("isExpired", accessToken.isExpired());
            //过期时间
            map.put("expiration", DateFormatUtils.format(accessToken.getExpiration(), DateFormatEnum.YYYY_MM_DD_HH_MM_SS.getFormat()));
            return map;
        } catch (Exception e){
            throw new DrunkardException("登录异常，请检查登录信息...");
        }
    }
    /**
     * @Description 账号退出
     * @Date 2019/7/25 17:47
     * @Version  1.0
     */
    @PostMapping(value = "logout")
    public Map<String, Object> logOut(@RequestParam String access_token){
        try {
            if(StringUtils.isNotBlank(access_token)){
                OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(access_token);
                if(oAuth2AccessToken != null){
                    log.error("----access_token是："+oAuth2AccessToken.getValue());
                    tokenStore.removeAccessToken(oAuth2AccessToken);
                    OAuth2RefreshToken oAuth2RefreshToken = oAuth2AccessToken.getRefreshToken();
                    tokenStore.removeRefreshToken(oAuth2RefreshToken);
                    tokenStore.removeAccessTokenUsingRefreshToken(oAuth2RefreshToken);
                }
            }
            HashMap<String, Object> data = new HashMap<>(3);
            data.put("200", "测试成功");
            return data;
        } catch (Exception e){
            throw new DrunkardException("登出异常");
        }
    }
}
