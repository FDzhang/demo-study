package com.example.demokafka.config;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewPartitions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：zxq
 * @date ：Created in 2020/12/2 14:39
 */
@Component
public class InitKafkaPartition implements ApplicationRunner {

    @Value("${spring.kafka.num-of-partitions}")
    private Integer partitions;

    @Value("${spring.kafka.template.default-topic}")
    private String topic;

    @Autowired
    private AdminClient adminClient;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        init(topic, partitions);
    }

    /**
     * 增加某个主题的分区（注意分区只能增加不能减少）
     *
     * @param topicName 主题名称
     * @param number    修改数量
     */
    public void init(String topicName, Integer number) {
        Map<String, NewPartitions> newPartitions = new HashMap<>(8);
        //创建新的分区的结果
        newPartitions.put(topicName, NewPartitions.increaseTo(number));
        adminClient.createPartitions(newPartitions);
    }
}
