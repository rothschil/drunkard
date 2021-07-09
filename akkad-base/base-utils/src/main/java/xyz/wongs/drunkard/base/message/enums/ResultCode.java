package xyz.wongs.drunkard.base.message.enums;

/**
 * @Description  定义的接口状态码   1000～1999 区间表示参数错误；000～2999 区间表示用户错误；3000～3999 区间表示接口异常
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/8/2 13:31
 * @Version 1.0.0
*/
public enum ResultCode {

    /** 成功 **/
    SUCCESS(0,"SUCCESS"),
    /** 失败 **/
    FAILURE(-1,"FAILURE"),

    EXCEPTION(201, "未知异常"),
    RUNTIME_EXCEPTION(202, "运行时异常"),
    NULL_POINTER_EXCEPTION(203, "空指针异常"),
    CLASS_CAST_EXCEPTION(204, "类型转换异常"),
    IO_EXCEPTION(205, "IO异常"),
    SYSTEM_EXCEPTION(210, "系统异常"),
    NOT_FOUND(404, "Not Found"),

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
    USER_NOT_LOGGED_IN(2001,"Access to Resources Requires Identity! Please Sign In"),
    USER_NOT_LOGIN_ERROR(2002,"The User does not exist Or the password is wrong"),
    USER_ACCOUNT_FORBIDDEN(2003,"Account is disabled"),
    USER_NOT_EXIST(2004,"Account not exists"),
    USER_HAS_EXISTED(2005,"Account exists"),
    USER_IS_EXPIRED(2006,"Account expired"),
    USER_FIRST_LANDING(2007, "Login for the first time"),
    USER_SIGN_VERIFY_NOT_COMPLIANT(2010,"Signature does not match"),
    USER_PASSWORD_RESET_FAILED(2011, "Password reset failed"),
    USER_UNKNOWN_IDENTITY(2012, "Unknown identity"),
    UNSUCCESSFUL_AUTHENTICATION(2014, "Authentication failed"),

    MANY_USER_LOGINS(2111,"Users are online"),
    USER_KEY_EXCEPTION(2108,"Key generation failed"),
    TOO_MANY_PASSWD_ENTER(2112, "Enter password frequently"),
    VERIFICATION_CODE_INCORRECT(2202,"Verification code error"),
    VERIFICATION_CODE_FAIL(2203,"Verification code generation failed"),

    TOKEN_EXPIRED(2308,"Token Expired"),
    TOKEN_GENERATION_FAIL(2309,"The Token generation failed"),
    TOKEN_INVALID(2310,"The Token is invalid"),
    TOKEN_VERIFICATION_FAIL(2311,"The Token verification failed"),
    TOKEN_VERIFICATION_PROCESS_ERR(2311,"The Token verification process error"),
    /**
     * 3000～3999 区间表示接口异常
     */
    API_EXCEPTION(3000, "接口异常"),
    API_NOT_FOUND_EXCEPTION(3002, "接口不存在"),
    API_REQ_MORE_THAN_SET(3003, "接口访问过于频繁，请稍后再试"),
    API_IDEMPOTENT_EXCEPTION(3004, "接口不可以重复提交，请稍后再试"),
    API_PARAM_EXCEPTION(3005, "参数异常"),
    API_PARAM_MISSING_EXCEPTION(3006, "缺少参数"),
    API_METHOD_NOT_SUPPORTED_EXCEPTION(3007, "不支持的Method类型"),
    API_METHOD_PARAM_TYPE_EXCEPTION(3008, "参数类型不匹配"),

    ARRAY_EXCEPTION(11001, "数组异常"),
    ARRAY_OUT_OF_BOUNDS_EXCEPTION(11002, "数组越界异常"),

    JSON_SERIALIZE_EXCEPTION(30000, "序列化数据异常"),
    JSON_DESERIALIZE_EXCEPTION(30001, "反序列化数据异常"),

    READ_RESOURCE_EXCEPTION(31002, "读取资源异常"),
    READ_RESOURCE_NOT_FOUND_EXCEPTION(31003, "资源不存在异常"),

    DATA_EXCEPTION(32004, "数据异常"),
    DATA_NOT_FOUND_EXCEPTION(32005, "未找到符合条件的数据异常"),
    DATA_CALCULATION_EXCEPTION(32006, "数据计算异常"),
    DATA_COMPRESS_EXCEPTION(32007, "数据压缩异常"),
    DATA_DE_COMPRESS_EXCEPTION(32008, "数据解压缩异常"),
    DATA_HAS_EXISTED(32010, "已存在"),
    DATA_NOT_EXIST(32010, "不存在"),
    DATA_PARSE_EXCEPTION(32009, "数据转换异常"),

    ENCODING_EXCEPTION(33006, "编码异常"),
    ENCODING_UNSUPPORTED_EXCEPTION(33006, "编码不支持异常"),

    DATE_PARSE_EXCEPTION(34001, "日期转换异常"),

    MALE_SEND_EXCEPTION(35001, "邮件发送异常"),

    SYNC_LOCK_FAILURE(4001, "获取锁失败"),
    SYNC_LOCK_SUCCESS(4002, "获取锁成功"),
    SYNC_LOCK_MANY_REQ(4003, "请求太多"),
    SYNC_LOCK_NOT_ENOUGH_STOCK(4004, "库存不够");

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
