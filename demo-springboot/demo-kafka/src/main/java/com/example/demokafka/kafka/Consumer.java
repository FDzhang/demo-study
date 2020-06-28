package com.example.demokafka.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author ：zxq
 * @date ：Created in 2020/6/28 14:39
 */
@Component
public class Consumer {

    @KafkaListener(topics = "user")
    public void consumer(ConsumerRecord consumerRecord){
        Optional<Object> kafkaMessage = Optional.ofNullable(consumerRecord.value());
        if (kafkaMessage.isPresent()){
            Object o = kafkaMessage.get();
            System.out.println(o);
        }
    }
}
