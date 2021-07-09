package xyz.wongs.drunkard.jwt.bo;

import java.io.Serializable;

/**
 * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
 * @Description 用户密码凭据
 * @Github <a>https://github.com/rothschil</a>
 * @date 2021/7/9 - 9:51
 * @Version 1.0.0
 */
public class AccountCredentials implements Serializable {

    private String userName;
    private String passWord;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
