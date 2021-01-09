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
}
