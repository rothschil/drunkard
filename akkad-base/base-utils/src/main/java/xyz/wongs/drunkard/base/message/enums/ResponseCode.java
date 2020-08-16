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


    FIRST_LANDING(20000, "首次登录"),
    TOKEN_EXPIRED(20001,"Token过期"),
    TOKEN_GENERTATION_FAIL(20002,"生成Token失败"),
    AUTHENTICATION_FAILED(20009, "用户或者密码不正确"),
    SIGN_VERIFI_NOT_COMPLIANT(20010,"签名校验不合规"),
    PASSWORD_RESET_FAILED(20101, "重置密码失败"),
    UNKONWN_INDENTITY(20102, "未知身份"),
    MANY_USER_LOGINS(21101,"多用户在线"),
    TOO_MANY_PASSWD_ENTER(21102, "密码输入次数过多"),
    VERIFICATION_CODE_INCORECT(22002,"图形验证码不正确"),
    VERIFICATION_CODE_FAIL(22003,"图形验证码生产失败"),


    NETWORK_ERROR(99999, "网络错误，待会重试");

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
