package com.example.demomysql.controller;

import cn.hutool.core.util.NumberUtil;
import com.example.demomysql.bean.User;
import com.example.demomysql.mapper.TestMapper;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author ：zxq
 * @date ：Created in 2020/10/20 11:01
 */
@RestController
@Slf4j
public class TestController {

    private int countUser;
    private int count;

    @Autowired
    private TestMapper testMapper;

    @GetMapping("test")
    public String test() {
        return "hello world";
    }

    @GetMapping("count")
    public int test2() {
        return testMapper.count();
    }

    @PostMapping("test")
    public String test3(HttpServletRequest request) {
        System.out.println(request.getParameter("str"));
        System.out.println(request.getParameter("id"));
        return "successs";
    }

    @GetMapping("put")
    public String put() {
        log.info("put");
        countUser = testMapper.countUser();
        count = 0;
        int id = 0;
        while (id >= 0) {
            id = putCode(id);
            log.info("id: {}", id);
        }
        return "success";
    }

    private Integer putCode(int id) {
        // 取出mobile
        List<User> phoneList = testMapper.phoneList(id);
        log.info("phoneList size: {}", phoneList.size());
        count += phoneList.size();
        log.info("progress: {} %", NumberUtil.round(count * 100.0 / countUser, 1));

        if (phoneList.size() <= 0) {
            return -1;
        }

        // mobile -> thingCode
        phoneList.forEach(this::initCode);

        // 存入 thingCode
        for (User user : phoneList) {
            testMapper.updateCode(user.getId(), user.getThingCode());
        }

        return phoneList.get(phoneList.size() - 1).getId();
    }

    public static String phoneToCode(String phone) {
        HashFunction hashFunction = Hashing.murmur3_128();
        return hashFunction.hashBytes(phone.getBytes(StandardCharsets.UTF_8)).toString();
    }

    public void initCode(User user) {
        user.setThingCode(phoneToCode(user.getPhone()));
    }
}
