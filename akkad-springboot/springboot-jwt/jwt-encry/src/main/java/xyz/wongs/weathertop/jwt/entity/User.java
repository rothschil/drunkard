package xyz.wongs.weathertop.jwt.entity;

/**
 * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
 * @Description //TODO
 * @Github <a>https://github.com/rothschil</a>
 * @date 2021/7/6 - 17:01
 * @Version 1.0.0
 */
public class User {
    private Long userId;
    private String username;
    private int age;
    private int sex;

    public User(Long userId, String username, int age, int sex) {
        this.userId = userId;
        this.username = username;
        this.age = age;
        this.sex = sex;
    }

    public User() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}
