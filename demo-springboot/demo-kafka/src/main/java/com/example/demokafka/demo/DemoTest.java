package com.example.demokafka.demo;

import com.example.demokafka.kafka.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author ：zxq
 * @date ：Created in 2020/6/28 14:43
 */
@Component
public class DemoTest {
    @Autowired
    private Producer product;


    /**
     * 被@PostConstruct修饰的方法会在构造函数之后，init()方法之前运行。
     */
    @PostConstruct
    public void init() {
        for (int i = 0; i < 10; i++) {
            product.send("test" + i, i);
        }
    }
}
