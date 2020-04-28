package com.example.demohttp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：zxq
 * @date ：Created in 2020/4/28 17:11
 */

@RestController
@RequestMapping("/test")
public class TestController {


    @PostMapping("/post")
    public String postTest(){
        return "不带参数的post请求";
    }

    @PostMapping("/post1")
    public String postTest1(String cityCode){
        System.out.println("cityCode"+cityCode);
        return "带参数的post请求";
    }
}
