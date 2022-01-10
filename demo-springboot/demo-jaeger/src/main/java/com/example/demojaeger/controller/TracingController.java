package com.example.demojaeger.controller;

import cn.hutool.http.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhangxinqiang
 * @create 2022/1/10 13:59
 */
@RestController
public class TracingController {



    @Autowired
    private RestTemplate restTemplate;

    @Value("${server.port}")
    private int port;

    @RequestMapping("/tracing")
    public String tracing() throws InterruptedException {
        Thread.sleep(100);
        return "tracing";
    }

    @RequestMapping("/open")
    public String open() throws InterruptedException {
        ResponseEntity<String> response =
                restTemplate.getForEntity("http://localhost:" + port + "/tracing",
                        String.class);
        Thread.sleep(200);
        return "open " + response.getBody();
    }


    @GetMapping("/test")
    public String test(String key) {
        return "test " + key;
    }
}