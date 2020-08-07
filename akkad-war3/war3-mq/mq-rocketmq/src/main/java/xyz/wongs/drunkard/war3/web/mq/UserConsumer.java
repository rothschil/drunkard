package xyz.wongs.drunkard.war3.web.mq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RocketMQMessageListener(topic = "rocket-topic-01", consumerGroup = "my-rocket-topic-01")
public class UserConsumer implements RocketMQListener<UserVo> {

    @Override
    public void onMessage(UserVo message) {
        log.warn("接受到消息: {}",message.toString());
    }
}
