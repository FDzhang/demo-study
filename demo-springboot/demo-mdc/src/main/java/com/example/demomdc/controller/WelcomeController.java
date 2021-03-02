package com.example.demomdc.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class WelcomeController {

    @GetMapping("/hello")
    public String welcomeMessage()
    {
        log.info("inside the welcomeMessage");
        return "Welcome to http://www.SpringBootDev.com";
    }

    @GetMapping("level")
    public void level(){
        /**
         * 通过 Logger 的api打印信息
         * 日志的级别；
         * 由低到高   trace<debug<info<warn<error
         */
        log.debug("这是日志");
        log.info("这是日志");
        log.warn("这是日志");
        log.error("这是日志");
    }
}
