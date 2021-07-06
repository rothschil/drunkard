package xyz.wongs.weathertop.tool;

import com.auth0.jwt.algorithms.Algorithm;

import java.io.Serializable;

/**
 * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
 * @Description //TODO
 * @Github <a>https://github.com/rothschil</a>
 * @date 2021/7/6 - 15:29
 * @Version 1.0.0
 */
public class JwtInfo implements Serializable {

    private static final String ISSUER ="weathertop";
    private static final String SUBJECT ="drunkard";
    /**
     * 受众
     */
    String[] audience={};

    /**
     * 数据
     */
    Algorithm algorithm;

    /**
     * 数据
     */
    Object data;
    /**
     * 发布者
     */
    String issuer;
    /**
     * 主题
     */
    String subject;
    /**
     * 有效期，单位为秒，默认为30秒
     */
    int expTime;

    public JwtInfo() {
    }

    public JwtInfo(Algorithm algorithm, Object data) {
        this.audience = new String[]{"app", "web"};
        this.algorithm = algorithm;
        this.data = data;
        this.issuer = ISSUER;
        this.subject = SUBJECT;
        this.expTime = 30;
    }

    public JwtInfo(Algorithm algorithm, Object data, String issuer, String subject) {
        this.audience = new String[]{"app", "web"};
        this.algorithm = algorithm;
        this.data = data;
        this.issuer = issuer;
        this.subject = subject;
        this.expTime = 30;
    }

    public JwtInfo(Algorithm algorithm, Object data, String issuer, String subject, int expTime) {
        this.audience = new String[]{"app", "web"};
        this.algorithm = algorithm;
        this.data = data;
        this.issuer = issuer;
        this.subject = subject;
        this.expTime = expTime;
    }

    public String[] getAudience() {
        return audience;
    }

    public void setAudience(String[] audience) {
        this.audience = audience;
    }

    public Algorithm getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getExpTime() {
        return expTime;
    }

    public void setExpTime(int expTime) {
        this.expTime = expTime;
    }
}
