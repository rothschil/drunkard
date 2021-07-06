//package xyz.wongs.drunkard.common.utils;
//
//import com.alibaba.fastjson.JSONObject;
//import com.github.hiwepy.ip2region.spring.boot.IP2regionTemplate;
//import com.github.hiwepy.ip2region.spring.boot.ext.RegionAddress;
//import lombok.extern.slf4j.Slf4j;
//import org.nutz.plugins.ip2region.DataBlock;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import xyz.wongs.drunkard.base.utils.IpUtils;
//import xyz.wongs.drunkard.base.utils.StringUtils;
//import xyz.wongs.drunkard.base.utils.bean.SpringUtils;
//import xyz.wongs.drunkard.base.utils.http.HttpUtils;
//import xyz.wongs.drunkard.common.conf.DrunkardConfig;
//import xyz.wongs.drunkard.framework.limit.RequestLimit;
//import xyz.wongs.drunkard.war3.constant.Constants;
//
//import java.io.IOException;
//
///**
// * @ClassName AddressUtils
// * @Description 获取地址类
// * @author WCNGS@QQ.COM
// * @Github <a>https://github.com/rothschil</a>
// * @date 20/12/9 17:25
// * @Version 1.0.0
//*/
//@Slf4j
//public class AddressUtils {
//
//    // IP地址查询
//    public static final String IP_URL = "http://whois.pconline.com.cn/ipJson.jsp";
//
//    // 未知地址
//    public static final String UNKNOWN = "XX XX";
//
//    /**
//     * @Description 查询开源网址获取真实的地理位置信息
//     * @param ip
//     * @return String
//     * @throws
//     * @date 20/12/9 17:32
//     */
//    public static String getRealAddressByIP(String ip) {
//        String address = UNKNOWN;
//        // 内网不查询
//        if (IpUtils.internalIp(ip)) {
//            return "内网IP";
//        }
//        if (DrunkardConfig.isAddressEnabled()) {
//            try {
//                String rspStr = HttpUtils.sendGet(IP_URL, "ip=" + ip + "&json=true", Constants.GBK);
//                if (StringUtils.isEmpty(rspStr)) {
//                    log.error("获取地理位置异常 {}", ip);
//                    return UNKNOWN;
//                }
//                JSONObject obj = JSONObject.parseObject(rspStr);
//                String region = obj.getString("pro");
//                String city = obj.getString("city");
//                return String.format("%s %s", region, city);
//            } catch (Exception e) {
//                log.error("获取地理位置异常 {}", e);
//            }
//        }
//        return address;
//    }
//
//    private static IP2regionTemplate template;
//
//    static {
//        template =  SpringUtils.getBean(IP2regionTemplate.class);
//    }
//
//    /**
//     * @Description 查询本地的IP库获取真实的地理位置信息
//     * @param ip
//     * @return String
//     * @throws
//     * @date 20/12/9 17:32
//     */
//    public static String getRealAddressByLocalIP(String ip) {
//        StringBuffer sbuf = new StringBuffer();
//        try {
//            RegionAddress regionAddress = template.getRegionAddress(ip);
//            sbuf.append(regionAddress.getCountry()).append(regionAddress.getProvince()).append(regionAddress.getCity());
//        } catch (IOException e) {
//            return UNKNOWN;
//        }
//        return sbuf.toString();
//    }
//}
