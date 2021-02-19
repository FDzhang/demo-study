package com.example.demofilter.controller;

import com.example.demofilter.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试：
 * 1 Servlet 输入流的重复读取的问题
 * 2 打印 入参日志
 * 3 controller 中的接口耗时
 *
 * @author ：zxq
 * @date ：Created in 2021/2/19 15:18
 */
@RestController
public class TestController {

    @GetMapping("")
    public String get(String name) {

        return "hello: " + name;
    }

    @PostMapping("")
    public String post(@RequestBody User user) {
        return user.toString();
    }
}
