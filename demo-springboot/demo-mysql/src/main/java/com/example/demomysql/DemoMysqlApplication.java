package com.example.demomysql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.example.demomysql.mapper")
public class DemoMysqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoMysqlApplication.class, args);
    }

}
