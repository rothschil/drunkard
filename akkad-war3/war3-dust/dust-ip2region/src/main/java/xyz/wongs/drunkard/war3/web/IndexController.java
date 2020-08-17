package xyz.wongs.drunkard.war3.web;

import lombok.extern.slf4j.Slf4j;
import org.lionsoul.ip2region.Ip2regionSearcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RequestMapping("/index")
@RestController
public class IndexController {

    @Autowired
    private Ip2regionSearcher ip2regionSearcher;

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public String get(){
        try {
            ip2regionSearcher.search("185.199.110.153");
            log.error("Country={},Province={}",ip2regionSearcher.getCountry(),ip2regionSearcher.getProvince());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "index";
    }
}
