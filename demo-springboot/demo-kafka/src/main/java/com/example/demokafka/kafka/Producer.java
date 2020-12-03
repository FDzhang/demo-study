package com.example.demokafka.kafka;

import com.alibaba.fastjson.JSONArray;
import com.example.demokafka.model.Track;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：zxq
 * @date ：Created in 2020/6/28 14:28
 */

@RestController
@RequestMapping
@Slf4j
public class Producer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("/send")
    public String send() {
        String topic = "kafka-topic-test";

        for (int i = 0; i < 10; i++) {
            List<Track> list = mockTrackList(i + "");
            send(topic, list);
        }

        return "success";
    }

    private void send(String topic, List<Track> list) {
        String data = JSONArray.toJSONString(list);
        ListenableFuture<SendResult<String, String>> send = kafkaTemplate.send(topic, data);

        send.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("===Producing message success");
                log.info("result:{}", result.toString());
            }

            @Override
            public void onFailure(Throwable ex) {
                log.info("===Producing message failed");
                log.warn("ex : {}", ex.getMessage());
            }

        });
    }


    private List<Track> mockTrackList(String x) {
        List<Track> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Track track = new Track();
            track.setCityName("上海" + x);
            track.setId(i + "");
            list.add(track);
        }
        return list;
    }

}
