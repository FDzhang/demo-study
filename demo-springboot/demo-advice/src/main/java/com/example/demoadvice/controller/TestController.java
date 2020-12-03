package com.example.demoadvice.controller;

import com.example.demoadvice.annotion.CustomResponse;
import com.example.demoadvice.bean.Result;
import com.example.demoadvice.bean.User;
import com.example.demoadvice.exception.MyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author ：zxq
 * @date ：Created in 2020/12/3 16:43
 */
@RestController
@RequestMapping
@Slf4j
public class TestController {

    @GetMapping("test")
    public Result test() {
        User user = new User(1, "悟空", new Date());
        return new Result("200", "成功", user);
    }

    @GetMapping("test1")
    public User test1() {
        return new User(1, "悟空", new Date());
    }

    @GetMapping("test2")
    @CustomResponse
    public User test2() {
        return new User(1, "悟空", new Date());
    }

    @GetMapping("test3")
    @CustomResponse
    public User test3() {
        try {
            int x = 1/0;
        }catch (Exception e){
            throw new MyException("201","myException","异常");
        }
        return new User(1, "悟空", new Date());
    }
}
