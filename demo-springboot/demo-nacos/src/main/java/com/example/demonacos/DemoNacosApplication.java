package com.example.demonacos;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@NacosPropertySource(dataId = "config.properties", groupId = "test", autoRefreshed = true)
public class DemoNacosApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoNacosApplication.class, args);
    }

}
