package com.example.demo.config;

import com.alibaba.fastjson.JSON;
import com.example.demo.mq.RocketMqConsumerClient;
import com.example.demo.mq.RocketMqProducerClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;

/**
 *
 * @author ：zxq
 * @date ：Created in 2020/11/20 15:35
 */
@Slf4j
@Configuration
public class RocketMqConfig {

    @Value("${rocketMq.nameServer}")
    private String nameServer;
    @Value("${rocketMq.producer.group}")
    private String producerGroup;
    @Value("${rocketMq.consumer.group}")
    private String consumerGroup;
    @Value("${rocketMq.topic}")
    private String topic;

//    @Autowired
//    private xxxxx xxxxx;

    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public RocketMqProducerClient getRocketMqProducerClient() {
        log.info("---provider bean config---");
        RocketMqProducerClient producerClient = new RocketMqProducerClient();
        DefaultMQProducer producer = new DefaultMQProducer(producerGroup);
        producer.setNamesrvAddr(nameServer);
        producer.setVipChannelEnabled(false);
        producerClient.setProducer(producer);
        producerClient.setTopic(topic);
        return producerClient;
    }

    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public RocketMqConsumerClient getRocketMqConsumerClient() throws MQClientException {
        log.info("---consumer bean config---");
        RocketMqConsumerClient consumerClient = new RocketMqConsumerClient();
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(consumerGroup);
        consumer.setNamesrvAddr(nameServer);
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.subscribe(topic, "*");
        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            log.info("---开始消费---");
            for (Message msg : msgs) {
                String body = new String(msg.getBody(), StandardCharsets.UTF_8);
                log.info("Consumer \n topic: {} \n tags: {} \n keys: {} \n msg: {}", msg.getTopic(), msg.getTags(), msg.getKeys(), body);
                // ...
            }
            log.info("---完成消费---");
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        consumerClient.setConsumer(consumer);
        return consumerClient;
    }
}
