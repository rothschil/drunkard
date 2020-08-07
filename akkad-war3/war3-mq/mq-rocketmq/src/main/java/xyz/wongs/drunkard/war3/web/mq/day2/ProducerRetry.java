package xyz.wongs.drunkard.war3.web.mq.day2;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import xyz.wongs.drunkard.war3.web.mq.vo.UserVo;

/**
 * @ClassName RocketMqRetry
 * @Description 描述消息重试
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/8/7 10:55
 * @Version 1.0.0
*/
@Slf4j
public class ProducerRetry {

    /**
     * 生产者组
     */
    private static String PRODUCE_RGROUP = "test_producer";

    public static void main(String[] args) throws Exception {
        //1、创建生产者对象
        DefaultMQProducer producer = new DefaultMQProducer(PRODUCE_RGROUP);
        //设置重试次数(默认2次）
        producer.setRetryTimesWhenSendFailed(3000);
        //绑定name server
        producer.setNamesrvAddr("192.168.244.128:9876");
        producer.setRetryTimesWhenSendFailed(3000);
        producer.start();
        //创建消息
        UserVo userVo = new UserVo("2","WONGS");
        Message message = new Message("rocket-topic-01", JSON.toJSON(userVo).toString().getBytes());
        //发送 这里填写超时时间是5毫秒 所以每次都会发送失败
        SendResult sendResult = producer.send(message,5);
        log.info("输出生产者信息={}",sendResult);
    }
}
