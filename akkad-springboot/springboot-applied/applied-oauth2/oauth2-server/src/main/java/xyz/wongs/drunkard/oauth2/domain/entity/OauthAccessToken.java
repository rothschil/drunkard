package xyz.wongs.drunkard.oauth2.domain.entity;

import lombok.Data;

/**
 * @ClassName OauthAccessToken
 * @Description 
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 20/12/2 16:19
 * @Version 1.0.0
*/
@Data
public class OauthAccessToken {
    private String authenticationId;

    private String tokenId;

    private String userName;

    private String clientId;

    private String refreshToken;

    private byte[] token;

    private byte[] authentication;
}