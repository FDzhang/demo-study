package com.example.demo.mq;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;

/**
 * RocketMq Consumer
 *
 * @author ：zxq
 * @date ：Created in 2020/11/20 14:11
 */
@Slf4j
@Data
public class RocketMqConsumerClient {
    private DefaultMQPushConsumer consumer;

    /**
     * 对象在使用之前必须要调用一次，只能初始化一次
     */
    public void start() {
        try {
            this.consumer.start();
            log.info("RocketMq Consumer start...");
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    /**
     * 一般在应用上下文，使用上下文监听器，进行关闭
     */
    public void shutdown() {
        this.consumer.shutdown();
        log.info("RocketMq Consumer shutdown.");
    }
}
