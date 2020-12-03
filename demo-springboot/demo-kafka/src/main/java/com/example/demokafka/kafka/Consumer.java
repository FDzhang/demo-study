package com.example.demokafka.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author ：zxq
 * @date ：Created in 2020/6/28 14:39
 */
@Component
@Slf4j
public class Consumer {

//    @KafkaListener(topics = "kafka-topic-test")
//    public void consumerTest(ConsumerRecord consumerRecord) {
//        Optional<Object> kafkaMessage = Optional.ofNullable(consumerRecord.value());
//        log.info("consumer Record: {}", consumerRecord.toString());
//        if (kafkaMessage.isPresent()) {
//
//            Object o = kafkaMessage.get();
//            List<Track> userTracks = JSONObject.parseArray(o.toString(), Track.class);
//            log.info("track len: {}", userTracks.size());
//            log.info("tracks : {}", JSON.toJSON(userTracks));
////            List<Track> collect = userTracks.stream()
////                    .filter(t -> "000001".equals(t.getCityCode()))
////                    .filter(t -> contains(t.getThingCode()))
////                    .collect(Collectors.toList());
////            System.out.println(collect);
//        }
//    }

    @KafkaListener(topics = "kafka-topic-test", containerFactory = "kafkaListenerContainerFactory")
    public void consumerTest1(List<ConsumerRecord<String, String>> list) {
        List<String> messages = new ArrayList<>();
        for (ConsumerRecord<String, String> record : list) {
            log.info("consumer record : {}", record);
            Optional<?> kafkaMessage = Optional.ofNullable(record.value());
            // 获取消息
            kafkaMessage.ifPresent(o -> messages.add(o.toString()));
        }

        log.info("list len: {}", list.size());
    }

    public static boolean contains(String code) {
        String ss = "xxx,xxxx,xxxxx";
        return ss.contains(code);
    }
}
