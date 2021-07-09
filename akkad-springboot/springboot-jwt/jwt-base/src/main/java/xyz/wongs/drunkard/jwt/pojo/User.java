package xyz.wongs.drunkard.jwt.pojo;

import java.io.Serializable;

/**
 * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
 * @Description //TODO
 * @Github <a>https://github.com/rothschil</a>
 * @date `2021/7/6` - 10:22
 * @Version 1.0.0
 */
public class User implements Serializable {
    private String userName;
    private String PassWord;

    public User() {
    }

    public User(String userName, String passWord) {
        this.userName = userName;
        PassWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
    }
}
