package com.example.demoadvice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example.demoadvice.*")
public class DemoAdviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoAdviceApplication.class, args);
    }

}
