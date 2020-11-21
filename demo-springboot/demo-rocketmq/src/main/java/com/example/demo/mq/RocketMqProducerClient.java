package com.example.demo.mq;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * RocketMq producer
 *
 * @author ：zxq
 * @date ：Created in 2020/11/20 13:58
 */
@Slf4j
@Data
public class RocketMqProducerClient {
    private DefaultMQProducer producer;
    private String topic;

    /**
     * 对象在使用之前必须要调用一次，只能初始化一次
     */
    public void start() {
        try {
            this.producer.start();
            log.info("RocketMq Producer start...");
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    /**
     * 一般在应用上下文，使用上下文监听器，进行关闭
     */
    public void shutdown() {
        this.producer.shutdown();
        log.info("RocketMq Producer shutdown.");
    }

    public SendResult send(JSONObject msg) throws Exception {
        log.info("---send msg to mq start---");
        Message message = new Message(topic, msg.getString("tags"), msg.getString("keys"),
                msg.getBytes("body"));
        SendResult sendResult = producer.send(message);
        log.info("send message: {}", msg.toJSONString());
        log.info("send Result: {}", sendResult);
        log.info("---send msg to mq end---");
        return sendResult;
    }
}
