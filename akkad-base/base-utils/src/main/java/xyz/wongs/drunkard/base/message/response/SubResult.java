package xyz.wongs.drunkard.base.message.response;

/**
 * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
 * @Description 超类的结果反馈，定义返回编码和消息描述
 * @Github <a>https://github.com/rothschil</a>
 * @date 2021/7/8 - 10:02
 * @Version 1.0.0
 */
public abstract class SubResult {

    /**
     * 错误编码
     **/
    Integer code;
    /**
     * 消息描述
     **/
    String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
