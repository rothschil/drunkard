package xyz.wongs.drunkard.base.message.enums;

/**
 * @ClassName
 * @Description
 * 1000～1999 区间表示参数错误
 * 2000～2999 区间表示用户错误
 * 3000～3999 区间表示接口异常
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/8/2 13:31
 * @Version 1.0.0
 */
public enum ResultCode {


    SUCCESS(1,"成功"),
    FAILURE(0,"成功"),

    /**
     * 1000～1999 区间表示参数错误
     */
    PARAMS_IS_INVALID(1001,"参数无效"),
    PARAMS_IS_BANK(1002,"参数为空"),
    PARAMS_TYPE_BIND_ERROR(1003,"参数类型错误"),
    PARAMS_NOT_COMPLETE(1004,"参数缺失"),

    /**
     * 2000～2999 区间表示用户错误
     */
    USER_NOT_LOGGED_IN(2001,"用户未登录，访问路径需要验证"),
    USER_NOT_LOGIN_ERROR(2002,"用户不存在或密码错误"),
    USER_ACCOUNT_FORBIDDEN(2003,"用户被禁用"),
    USER_NOT_EXIST(2004,"用户不存在"),
    USER_HAS_EXISTED(2005,"用户已存在"),
    USER_IS_EXPIRED(2006,"用户账号已过期"),
    USER_FIRST_LANDING(2007, "首次登录"),
    USER_TOKEN_EXPIRED(2008,"Token过期"),
    USER_TOKEN_GENERTATION_FAIL(2009,"生成Token失败"),
    USER_SIGN_VERIFI_NOT_COMPLIANT(2010,"签名校验不合规"),
    USER_PASSWORD_RESET_FAILED(2011, "重置密码失败"),
    USER_UNKONWN_INDENTITY(2012, "未知身份"),
    MANY_USER_LOGINS(2111,"多用户在线"),
    TOO_MANY_PASSWD_ENTER(2112, "密码输入次数过多"),
    VERIFICATION_CODE_INCORECT(2202,"图形验证码不正确"),
    VERIFICATION_CODE_FAIL(2203,"图形验证码生产失败"),

    /**
     * 3000～3999 区间表示接口异常
     */
    INTF_REQ_MORE_THAN_SET(3001,"接口请求超过设置值"),

    FAILED(400,"请求失败"),
    ERROR(500,"应用服务不知名错误"),
    ERROR_NULL(501,"空指针异常"),
    ERROR_CLASS_CAST(502,"类型转换异常"),
    ERROR_RUNTION(503,"运行时异常"),

    ERROR_MOTHODNOTSUPPORT(505,"没有匹配请求方法"),




    ALGORITHM_CAN_NOT_NULL(10003,"加密方式不能为空，可选 RS256、HS256"),
    VALID_ENTITY_PARAMS(10004,"请求参数校验不匹配"),
    VALID_UNION_PARAMS(10005,"实体对象传参不匹配"),
    PARAM_ERROR(12001,"参数不合规"),


    GET_LOCK_FAIL(33333,"获取分布式锁失败"),
    RELEASE_LOCK_FAIL(33334,"释放分布式失败"),

    UPLOAD_FAIL(34000,"上传文件失败"),
    FILE_TOO_LARGE(34001,"文件过大"),


    ATTR_COPY_ERROR(13001,"属性COPY异常"),

    RESOURCE_EXIST(14001,"资源已经存在"),
    RESOURCE_NOT_EXIST(14002, "资源不存在"),
    INSUFFICIENT_RESOURCE(14003, "资源不符合规范"),

    SYSTEM_ERROR(10000, "系统异常，请稍后重试"),
    NETWORK_ERROR(99999, "网络错误，待会重试");

    /**
     *
     */
    private Integer code;

    /**
     *
     */
    private String msg;

    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
