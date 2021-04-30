package com.example.demosqlserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.demosqlserver.mapper")
public class DemoSqlserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoSqlserverApplication.class, args);
    }

}
