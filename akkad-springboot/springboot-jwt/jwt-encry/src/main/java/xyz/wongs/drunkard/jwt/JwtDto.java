package xyz.wongs.drunkard.jwt;

/**
 * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
 * @Description //TODO
 * @Github <a>https://github.com/rothschil</a>
 * @date 2021/7/6 - 17:11
 * @Version 1.0.0
 */
public class JwtDto {
    /**
     * 加密方式
     */
    private String alg;

    /**
     * token
     */
    private String token;

    public JwtDto() {
    }

    public String getAlg() {
        return alg;
    }

    public void setAlg(String alg) {
        this.alg = alg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
