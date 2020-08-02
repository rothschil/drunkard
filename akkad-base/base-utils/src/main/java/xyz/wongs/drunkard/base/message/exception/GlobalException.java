package xyz.wongs.drunkard.base.message.exception;

/**
 * @ClassName
 * @Description
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/8/2 13:31
 * @Version 1.0.0
 */
public class GlobalException extends Exception {

    private int code;
    public void setCode(int code) {
        this.code = code;
    }
    public int getCode() {
        return code;
    }

    public GlobalException() {

    }

    public GlobalException(String message) {
        super(message);
    }
    public GlobalException(String message, int code) {
        super(message);
        this.code = code;
    }
}
