package xyz.wongs.drunkard.war3.web.mq.day1;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;
import xyz.wongs.drunkard.war3.web.mq.vo.UserVo;

/** 消费者示例
 * @ClassName UserConsumer
 * @Description 
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/9/9 15:08
 * @Version 1.0.0
*/
@Slf4j
@Component
@RocketMQMessageListener(topic = "rocket-topic-01", consumerGroup = "my-rocket-topic-01")
public class UserConsumer implements RocketMQListener<UserVo> {

    @Override
    public void onMessage(UserVo message) {
        log.warn("接受到消息: {}",message.toString());
    }
}
