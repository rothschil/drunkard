package xyz.wongs.weathertop.factory;

import xyz.wongs.weathertop.jwt.RsaKeyBo;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
 * @Description //TODO
 * @Github <a>https://github.com/rothschil</a>
 * @date 2021/7/6 - 17:12
 * @Version 1.0.0
 */
public class KeyFactory {
    /**
     * 算法密钥
     */
    public static final String KEY_ALGORITHM = "RSA";
    private static final String PUBLIC_KEY = "RSAPublicKey";
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    private static RsaKeyBo rsaKeyBo;

    /**
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description 获得公钥
     * @Date 2021/7/6-17:12
     * @Param keyMap
     * @return String
     **/
    public static String getPublicKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return encryptBASE64(key.getEncoded());
    }

    /**
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description 获得公钥
     * @Date 2021/7/6-17:12
     * @Param rsa256Key
     * @return String
     **/
    public static String getPublicKey(RsaKeyBo rsaKeyBo) throws Exception {
        Key key = rsaKeyBo.getPublicKey();
        return encryptBASE64(key.getEncoded());
    }

    /**
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description 获得私钥
     * @Date 2021/7/6-17:12
     * @Param keyMap
     * @return String
     **/
    public static String getPrivateKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return encryptBASE64(key.getEncoded());
    }
    /**
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description 获得私钥
     * @Date 2021/7/6-17:12
     * @Param rsa256Key
     * @return String
     **/
    public static String getPrivateKey(RsaKeyBo rsaKeyBo) throws Exception {
        Key key = rsaKeyBo.getPrivateKey();
        return encryptBASE64(key.getEncoded());
    }

    /**
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description 解码返回byte
     * @Date 2021/7/6-17:24
     * @Param key
     * @return String
     **/
    public static byte[] decryptBASE64(String key) throws Exception {
        return Base64.getDecoder().decode(key);
    }

    /**
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description 编码返回字符串
     * @Date 2021/7/6-17:24
     * @Param key
     * @return String
     **/
    public static String encryptBASE64(byte[] key) throws Exception {
        return Base64.getEncoder().encodeToString(key);
    }

    /**
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description 生成公钥和私钥，并在map对象中存放
     * @Date 2021/7/6-17:21
     * @Param
     * @return String
     **/
    public static Map<String, Object> initKey() throws Exception {
        //RSA算法要求有一个可信任的随机数源
        //获得对象 KeyPairGenerator 参数 RSA 1024个字节
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(1024);
        //通过对象 KeyPairGenerator 生成密匙对 KeyPair
        KeyPair keyPair = keyPairGen.generateKeyPair();
        //通过对象 KeyPair 获取RSA公私钥对象RSAPublicKey RSAPrivateKey
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        //公私钥对象存入map中
        Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }
    /**
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description 获取公私钥，采用DCL设计模式
     * @Date 2021/7/6-17:18
     * @Param
     * @return RSA256Key
     **/
    public static synchronized RsaKeyBo getRSA256Key() throws Exception {
        if(rsaKeyBo == null){
            synchronized (RsaKeyBo.class){
                if(rsaKeyBo == null) {
                    rsaKeyBo = new RsaKeyBo();
                    Map<String, Object> map = initKey();
                    rsaKeyBo.setPrivateKey((RSAPrivateKey) map.get(KeyFactory.PRIVATE_KEY));
                    rsaKeyBo.setPublicKey((RSAPublicKey) map.get(KeyFactory.PUBLIC_KEY));
                }
            }
        }
        return rsaKeyBo;
    }
}