package xyz.wongs.drunkard.war3.web.controller;


import com.github.hiwepy.ip2region.spring.boot.IP2regionTemplate;
import com.github.hiwepy.ip2region.spring.boot.ext.RegionAddress;
import lombok.extern.slf4j.Slf4j;
import org.nutz.plugins.ip2region.DataBlock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.wongs.drunkard.base.aop.annotion.ApplicationLog;
import xyz.wongs.drunkard.base.message.annoation.ResponseResult;
import xyz.wongs.drunkard.base.message.exception.DrunkardException;
import xyz.wongs.drunkard.war3.limit.RequestLimit;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@ResponseResult
public class IndexController {

    /**
     * @Description
     * @param regUserVo
     * @return void
     * @throws
     * @date 2020/8/4 19:14
     */
    @GetMapping("/index")
    public String index(){
        log.error("=====");
        return "I AM INDEX";
    }

    @RequestLimit(maxCount=3,second=20)
    @ApplicationLog
    @GetMapping("/test")
    public Map<String, Object> test() {
        HashMap<String, Object> data = new HashMap<>(3);
        data.put("info", "测试成功");
        return data;
    }

    @ApplicationLog
    @GetMapping("/fail")
    public Integer error() {
        int res = 0; // 查询结果数
        if( res == 0 ) {
            throw new DrunkardException("没有数据");
        }
        return res;
    }

//     @Autowired
//    private Ip2regionSearcher ip2regionSearcher;

    @Autowired
    IP2regionTemplate template;

    /** 根据输入IP地址，返回解析后的地址
     * @Description
     * @param ip
     * @return xyz.wongs.drunkard.base.message.response.ResponseResult
     * @throws
     * @date 2020/8/17 18:26
     */
    @GetMapping(value = "/convert/{ip}")
    public DataBlock convertDataBlock(@PathVariable String ip){
        DataBlock dataBlock = null;
        try {
//            ip2regionSearcher.search(ip, Ip2regionSearcher.ALGORITHM.MEMORY_SEARCH);
//            regionAddress = RegionAddress.builder()
//                    .country(ip2regionSearcher.getCountry())
//                    .city(ip2regionSearcher.getCity())
//                    .isp(ip2regionSearcher.getISP())
//                    .province(ip2regionSearcher.getProvince())
//                    .region(ip2regionSearcher.getProvince()).build();

            dataBlock = template.binarySearch(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataBlock;
    }

    /** 根据输入IP地址，返回解析后的地址
     * @Description
     * @param ip
     * @return xyz.wongs.drunkard.base.message.response.ResponseResult
     * @throws
     * @date 2020/8/17 18:26
     */
    @RequestLimit(maxCount=3)
    @GetMapping(value = "/region/{ip}")
    public RegionAddress convert(@PathVariable String ip){
        RegionAddress regionAddress = null;
        try {
            regionAddress = template.getRegionAddress(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return regionAddress;
    }
}
