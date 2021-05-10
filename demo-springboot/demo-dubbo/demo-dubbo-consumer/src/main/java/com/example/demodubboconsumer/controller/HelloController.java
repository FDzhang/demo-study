package com.example.demodubboconsumer.controller;

import com.example.demodubboapi.model.User;
import com.example.demodubboapi.service.SayHelloService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：zxq
 * @date ：Created in 2020/7/1 15:28
 */

@RestController
@RequestMapping("/")
public class HelloController {

    @DubboReference(group = "${group.name}")
    private SayHelloService sayHelloService;

    @GetMapping("/hello")
    public String sayHello(){
        sayHelloService.sayHello("dubbo");
        return "success";
    }

    @GetMapping("/getUser")
    public User getUser(){
        return sayHelloService.getUser();
    }

    @GetMapping("build")
    public User build(){
        User user = new User();
        user.setName("悟空");
        sayHelloService.build(user);
        return user;
    }

    @GetMapping("build2")
    public User build2(){
        User user = new User();
        user.setName("悟空");
        user = sayHelloService.reBuild(user);
        return user;
    }
}
