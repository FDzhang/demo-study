package com.example.democombination;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@NacosPropertySource(dataId = "config.properties", groupId = "demo-combination", autoRefreshed = true)
public class DemoCombinationApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoCombinationApplication.class, args);
    }

}
