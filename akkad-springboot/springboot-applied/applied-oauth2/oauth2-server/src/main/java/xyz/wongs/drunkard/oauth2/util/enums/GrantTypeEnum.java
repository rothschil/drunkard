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

    /** 密码模式 **/
    PASSWORD("password", "密码模式"),

    /** 刷新token **/
    REFRESH_TOKEN("refresh_token", "刷新token");

    private final String grantType;
    private final String grantName;

    private GrantTypeEnum(String grantType, String grantName){
        this.grantType = grantType;
        this.grantName = grantName;
    }

    public String getGrantType() {
        return grantType;
    }

}
