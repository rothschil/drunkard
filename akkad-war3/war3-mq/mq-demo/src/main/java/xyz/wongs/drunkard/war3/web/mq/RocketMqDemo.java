package xyz.wongs.drunkard.war3.web.mq;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import xyz.wongs.drunkard.war3.web.mq.vo.UserVo;

import java.util.Random;

@Slf4j
@RestController
public class RocketMqDemo {

    @GetMapping("send/{id}")
    public UserVo send(@PathVariable("id") String id){
        int idx = new Random().nextInt(1000);
        UserVo userVo  = new UserVo(Integer.toString(idx),"侯征");
        log.warn(JSON.toJSONString(userVo));
        return userVo;
    }
}
