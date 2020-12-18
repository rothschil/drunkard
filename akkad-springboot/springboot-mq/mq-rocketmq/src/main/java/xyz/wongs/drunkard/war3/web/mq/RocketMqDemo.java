package xyz.wongs.drunkard.war3.web.mq;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class RocketMqDemo {

    @Autowired
    RocketMQTemplate rocketMQTemplate;

    @GetMapping("send/{id}")
    public String send(@PathVariable("id") String id){
        UserVo userVo  = new UserVo(id,"侯征");
        log.warn(JSON.toJSONString(userVo));
        rocketMQTemplate.send("rocket-topic-01", MessageBuilder.withPayload(userVo).build());
        return "SUCESS";
    }
}
