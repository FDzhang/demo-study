package com.example.demoadvice.controller;

import com.alibaba.fastjson.JSON;
import com.example.demoadvice.annotion.CustomResponse;
import com.example.demoadvice.bean.Result;
import com.example.demoadvice.bean.User;
import com.example.demoadvice.bean.User1;
import com.example.demoadvice.bean.User2;
import com.example.demoadvice.exception.MyException;
import com.google.gson.Gson;
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
            int x = 1 / 0;
        } catch (Exception e) {
            throw new MyException("201", "myException", "异常");
        }
        return new User(1, "悟空", new Date());
    }

//    public static void main(String[] args) {
//        User1 user1 = new User1(1, "悟空", new Date());
//
//
//        String gsonStr = new Gson().toJson(user1);
//        log.info("gsonStr : {}", gsonStr);
//
//        String fastStr = JSON.toJSONString(user1);
//        log.info("fastStr : {}", fastStr);
//
////        log.info("-------- gsonStr - fastTo ----------");
////        User1 fastTo = JSON.parseObject(gsonStr, User1.class);
////        log.info("fastTo : {}", fastTo);
////        log.info("-------- gsonStr - gsonTo ----------");
////        User1 gsonTo = new Gson().fromJson(gsonStr, User1.class);
////        log.info("gsonTo : {}", gsonTo);
//
//        log.info("-------- fastStr - fastTo ----------");
//        User1 fastTo = JSON.parseObject(fastStr, User1.class);
//        log.info("fastTo : {}", fastTo);
//
//        log.info("-------- fastStr - gsonTo ----------");
//        User1 gsonTo = new Gson().fromJson(fastStr, User1.class);
//        log.info("gsonTo : {}", gsonTo);
//    }


    public static void main(String[] args) {
        User2 user1 = new User2(1, "悟空", new Date());

        String gsonStr = new Gson().toJson(user1);
        log.info("gsonStr : {}", gsonStr);

//        String fastStr = JSON.toJSONString(user1);
//        log.info("fastStr : {}", fastStr);
        String fastStr = "{\"birth\":1607445110455,\"id\":1,\"name\":\"悟空\"}";


//        log.info("-------- gsonStr - fastTo ----------");
//        User1 fastTo = JSON.parseObject(gsonStr, User1.class);
//        log.info("fastTo : {}", fastTo);
        log.info("-------- gsonStr - gsonTo ----------");
        User2 gsonTo = new Gson().fromJson(gsonStr, User2.class);
        log.info("gsonTo : {}", gsonTo);

        log.info("-------- fastStr - fastTo ----------");
        User2 fastTo = JSON.parseObject(fastStr, User2.class);
        log.info("fastTo : {}", fastTo);
//
//        log.info("-------- fastStr - gsonTo ----------");
//        User1 gsonTo = new Gson().fromJson(fastStr, User1.class);
//        log.info("gsonTo : {}", gsonTo);
    }
}
