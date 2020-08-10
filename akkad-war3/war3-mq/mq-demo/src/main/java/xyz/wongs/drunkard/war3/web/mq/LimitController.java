package xyz.wongs.drunkard.war3.web.mq;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RequestMapping("/limit")
@RestController
public class LimitController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/index")
    public JSONObject index(){
        JSONObject jsnObj = new JSONObject();
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        jsnObj.put("msg",port+" 调用成功");
        System.out.println(" 调用一次 ");
        return jsnObj;
    }

}