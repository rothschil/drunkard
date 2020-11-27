package xyz.wongs.drunkard.oauth2.util.enums;

/**
 * @ClassName GrantTypeEnum
 * @Description 认证模式枚举类
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 20/11/27 15:45
 * @Version 1.0.0
*/
public enum GrantTypeEnum {

    PASSWORD("password", "密码模式"),
    REFRESH_TOKEN("refresh_token", "刷新token");

    private final String grant_type;
    private final String grant_name;

    private GrantTypeEnum(String grant_type, String grant_name){
        this.grant_type = grant_type;
        this.grant_name = grant_name;
    }

    public String getGrant_type() {
        return grant_type;
    }

}
