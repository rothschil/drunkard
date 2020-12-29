package xyz.wongs.drunkard.base.utils.security;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
    public static String getSalt4Md5(String password, String salts) {
        password = md5Hex(password + salts);
        int size = 48;
        int stride = 3;
        int bic = 2;
        char[] cs = new char[size];
        for (int i = 0; i < size; i += stride) {
            cs[i] = password.charAt(i / stride * bic);
            char c = salts.charAt(i / stride);
            cs[i + 1] = c;
            cs[i + 2] = password.charAt(i / stride * bic + 1);
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
    public static boolean getSaltverify4Md5(String password, String md5Str, String salt) {
        int size = 48;
        int stride = 3;
        int bic = 2;
        char[] cars = new char[bic * (bic<<stride)];
        for (int i = 0; i < size; i += stride) {
            cars[i / stride * bic] = md5Str.charAt(i);
            cars[i / stride * bic + 1] = md5Str.charAt(i + bic);
        }
        return md5Hex(password + salt).equals(String.valueOf(cars));
    }

    public static String hash(String s) {
        try {
            return new String(toHex(md5(s)).getBytes("UTF-8"), "UTF-8");
        } catch (Exception e) {
            log.error("not supported charset...{}", e);
            return s;
        }
    }

    private static final String toHex(byte hash[]) {
        if (hash == null) {
            return null;
        }
        StringBuffer buf = new StringBuffer(hash.length * 2);
        int i;

        for (i = 0; i < hash.length; i++) {
            if ((hash[i] & 0xff) < 0x10) {
                buf.append("0");
            }
            buf.append(Long.toString(hash[i] & 0xff, 16));
        }
        return buf.toString();
    }

    private static byte[] md5(String s) {
        MessageDigest algorithm;
        try {
            algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(s.getBytes("UTF-8"));
            byte[] messageDigest = algorithm.digest();
            return messageDigest;
        } catch (Exception e) {
            log.error("MD5 Error...", e);
        }
        return null;
    }

    public static String getMd5(File file) {
        FileInputStream fileInputStream = null;
        try {
            MessageDigest MD5 = MessageDigest.getInstance("MD5");
            fileInputStream = new FileInputStream(file);
            byte[] buffer = new byte[8192];
            int length;
            while ((length = fileInputStream.read(buffer)) != -1) {
                MD5.update(buffer, 0, length);
            }
            return new String(Hex.encodeHex(MD5.digest()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (fileInputStream != null){
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        System.out.println(2<<4);
        //        System.out.println(Md5Utils.getSalt4Md5("fZ1j1ll", "MZONl1233224322168"));
        // eM49Z79O49N3fl041b62e731133f2cf2a44f43e427323312
//        System.out.println(Md5Utils.getSaltverify4Md5("fZ1j1ll","eM49Z79O49N3fl041b62e731133f2cf2a44f43e427323312", "MZONl1233224322168"));
    }
}
