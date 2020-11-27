package xyz.wongs.drunkard.oauth2.domain.entity;

import lombok.Data;

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