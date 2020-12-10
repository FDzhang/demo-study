package com.example.demojtaatomikos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.example.demojtaatomikos.mapper")
@ComponentScan(basePackages = {"com.example.demojtaatomikos.*"})
public class DemoJtaAtomikosApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoJtaAtomikosApplication.class, args);
    }

}
