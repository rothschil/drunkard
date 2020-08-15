package xyz.wongs.drunkard.base.message.enums;

/**
 * @ClassName
 * @Description
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/8/2 13:31
 * @Version 1.0.0
 */
public enum ResponseCode {

    FAILED(400,"请求失败"),
    ERROR(500,"应用服务不知名错误"),
    ERROR_NULL(501,"空指针异常"),
    ERROR_CLASS_CAST(502,"类型转换异常"),
    ERROR_RUNTION(503,"运行时异常"),
    ERROR_IO(504,"上传文件异常"),
    ERROR_MOTHODNOTSUPPORT(505,"没有匹配请求方法"),


    DICT_LOCK_FAIL(601,"获取分布式锁失败"),

    TOKEN_EXPIRED(10001,"token 过期"),
    SIGN_VERIFI_ERROR(10002,"签名不匹配"),
    ALGORITHM_CAN_NOT_NULL(10003,"加密方式不能为空，可选 RS256、HS256"),
    VALID_ENTITY_PARAMS(10004,"请求参数校验不匹配"),
    VALID_UNION_PARAMS(10005,"实体对象传参不匹配"),
    NOT_EXISTS(10006,"不存在数据信息"),
    FIRST_LANDING(10007, "首次登录"),
    AUTHENTICATION_FAILED_ERROR(10009, "用户或者密码不正确"),
    PARAM_ERROR(12001,"参数不合规"),
    CAPTCHA_ERROR(12002,"验证码不合规"),

    ATTR_COPY_ERROR(13001,"属性COPY异常"),
    ATTR_DUPLICATION(14001,"Data already exists"),


    RESOURCE_NOT_EXIST(1001, "资源不存在"),
    INSUFFICIENT_RESOURCE(1002, "资源不符合规范"),
    DUPLICATEKEY_ERROR_CODE(1003,"数据库中已存在该记录"),
    MANY_LOGINS(1101,"多用户在线"),
    SYSNC_LOCK(1111,"分布式锁获取失败"),



    NETWORK_ERROR(9999, "网络错误，待会重试");

    private int code;
    private String msg;

    ResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
