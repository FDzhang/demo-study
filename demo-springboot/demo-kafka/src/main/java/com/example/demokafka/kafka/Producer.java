//package com.example.demokafka.kafka;
//
//import com.alibaba.fastjson.JSON;
//import com.example.demokafka.model.UserTrack;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Component;
//
///**
// * @author ：zxq
// * @date ：Created in 2020/6/28 14:28
// */
//@Component
//public class Producer {
//
//    @Autowired
//    private KafkaTemplate kafkaTemplate;
//
//    public void send(String name,Integer age){
//        UserTrack user = new UserTrack();
//        user.setName(name);
//        user.setAge(age);
//        kafkaTemplate.send("test", JSON.toJSONString(user));
//    }
//}
