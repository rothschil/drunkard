package xyz.wongs.drunkard.jwt;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
/**
 * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
 * @Description 公钥-私钥封装实体
 * @Github <a>https://github.com/rothschil</a>
 * @date 2021/7/6 - 17:00
 * @Version 1.0.0
 */
public class RsaKeyBo {
    /**
     * 公钥
     */
    private RSAPublicKey publicKey;

    /**
     * 私钥
     */
    private RSAPrivateKey privateKey;

    public RSAPublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(RSAPublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public RSAPrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(RSAPrivateKey privateKey) {
        this.privateKey = privateKey;
    }
}
