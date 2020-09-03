package xyz.wongs.drunkard.war3.web;

import lombok.extern.slf4j.Slf4j;
import org.lionsoul.ip2region.Ip2regionSearcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.wongs.drunkard.base.message.response.ResponseResult;
import xyz.wongs.drunkard.base.web.BaseController;
import xyz.wongs.drunkard.war3.web.vo.IpRegionVo;

import java.io.IOException;

/**
 * @ClassName IndexController
 * @Description 
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/8/17 18:27
 * @Version 1.0.0
*/
@Slf4j
@RequestMapping("/index")
@RestController
public class IndexController extends BaseController {


    @Autowired
    private Ip2regionSearcher ip2regionSearcher;

    /**
     * @Description
     * @param ip
     * @return xyz.wongs.drunkard.base.message.response.ResponseResult
     * @throws
     * @date 2020/8/17 18:26
     */
    @GetMapping(value = "/convert/{ip}")
    public ResponseResult convert(@PathVariable String ip){
        ResponseResult responseResult= getResponseResult();
        try {
            ip2regionSearcher.search(ip, Ip2regionSearcher.ALGORITHM.MEMORY_SEARCH);
            IpRegionVo ipRegionVo = IpRegionVo.builder()
                    .country(ip2regionSearcher.getCountry())
                    .city(ip2regionSearcher.getCity())
                    .isp(ip2regionSearcher.getISP())
                    .province(ip2regionSearcher.getProvince())
                    .region(ip2regionSearcher.getProvince()).build();
            responseResult.setData(ipRegionVo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseResult;
    }
}
