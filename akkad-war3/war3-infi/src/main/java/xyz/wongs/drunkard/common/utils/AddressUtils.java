package xyz.wongs.drunkard.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.wongs.drunkard.base.constant.Constant;
import xyz.wongs.drunkard.base.utils.IpUtils;
import xyz.wongs.drunkard.base.utils.StringUtils;
import xyz.wongs.drunkard.base.utils.http.HttpUtils;
import xyz.wongs.drunkard.common.conf.DrunkardConfig;

/**
 * @Description 获取地址类
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 20/12/9 17:25
 * @Version 1.0.0
*/
public class AddressUtils {

    private static final Logger LOG = LoggerFactory.getLogger(AddressUtils.class);

    /**
     * IP地址查询
     */
    public static final String IP_URL = "http://whois.pconline.com.cn/ipJson.jsp";

    /**
     * 未知地址
     */
    public static final String UNKNOWN = "XX XX";

    /**
     * @Description 查询开源网址获取真实的地理位置信息
     * @param ip ip地址
     * @return String
     * @date 20/12/9 17:32
     */
    public static String getRealAddressByIP(String ip) {
        String address = UNKNOWN;
        // 内网不查询
        if (IpUtils.internalIp(ip)) {
            return "内网IP";
        }
        if (DrunkardConfig.isAddressEnabled()) {
            try {
                String rspStr = HttpUtils.sendGet(IP_URL, "ip=" + ip + "&json=true", Constant.GBK);
                if (StringUtils.isEmpty(rspStr)) {
                    LOG.error("获取地理位置异常 {}", ip);
                    return UNKNOWN;
                }
                JSONObject obj = JSONObject.parseObject(rspStr);
                String region = obj.getString("pro");
                String city = obj.getString("city");
                return String.format("%s %s", region, city);
            } catch (Exception e) {
                LOG.error("获取地理位置异常 {}", e);
            }
        }
        return address;
    }


    /**
     * @Description 查询本地的IP库获取真实的地理位置信息
     * @param ip
     * @return String
     * @throws
     * @date 20/12/9 17:32
     */
    public static String getRealAddressByLocalIP(String ip) {
        StringBuffer sbuf = new StringBuffer();
//        try {
            String lo ="";
//            RegionAddress regionAddress = template.getRegionAddress(ip);
//            sbuf.append(regionAddress.getCountry()).append(regionAddress.getProvince()).append(regionAddress.getCity());
//        }
//        catch (IOException e) {
//            return UNKNOWN;
//        }
        return sbuf.toString();
    }
}
