package xyz.wongs.drunkard.base.utils.security;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Random;

/**
 * Md5加密方法
 *
 * @author WCNGS@QQ.COM
 * @ClassName Md5Utils
 * @Description
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/8/15 22:50
 * @Version 1.0.0
 */
@Slf4j
public class Md5Utils {
    /**
     * 生成含有随机盐的密码
     *
     * @param password 要加密的密码
     * @return String 含有随机盐的密码
     */
    public static String getSaltMD5(String password, String salts) {
        password = md5Hex(password + salts);
        char[] cs = new char[48];
        for (int i = 0; i < 48; i += 3) {
            cs[i] = password.charAt(i / 3 * 2);
            char c = salts.charAt(i / 3);
            cs[i + 1] = c;
            cs[i + 2] = password.charAt(i / 3 * 2 + 1);
        }
        return String.valueOf(cs);
    }

    /**
     * MD5加密,并把结果由字节数组转换成十六进制字符串
     * @param str 要加密的内容
     * @return String 返回加密后的十六进制字符串
     */
    private static String md5Hex(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(str.getBytes());
            return hex(digest);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());
            return "";
        }
    }

    /** byte[]字节数组 转换成 十六进制字符串
     * @Description
     * @param arry   要转换的byte[]字节数组
     * @return java.lang.String 返回十六进制字符串
     * @throws
     * @date 2020/8/15 23:23
     */
    private static String hex(byte[] arry) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arry.length; ++i) {
            sb.append(Integer.toHexString((arry[i] & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString();
    }


    /**
     * @Description
     * @param password  原密码
     * @param md5Str    加密之后的密码
     * @param salt      salt
     * @return boolean  true:密码一致；false：不一致
     * @throws
     * @date 2020/8/15 23:21
     */
    public static boolean getSaltverifyMD5(String password, String md5Str,String salt) {
        char[] cars = new char[32];
        for (int i = 0; i < 48; i += 3) {
            cars[i / 3 * 2] = md5Str.charAt(i);
            cars[i / 3 * 2 + 1] = md5Str.charAt(i + 2);
        }
        return md5Hex(password + salt).equals(String.valueOf(cars));
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        System.out.println(Md5Utils.getSaltMD5("fZ1j1ll", "MZONl1233224322168"));
        // eM49Z79O49N3fl041b62e731133f2cf2a44f43e427323312
        System.out.println(Md5Utils.getSaltverifyMD5("fZ1j1ll","eM49Z79O49N3fl041b62e731133f2cf2a44f43e427323312", "MZONl1233224322168"));
    }
}
