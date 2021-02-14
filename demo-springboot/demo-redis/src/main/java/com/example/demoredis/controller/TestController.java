package com.example.demoredis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：zxq
 * @date ：Created in 2021/2/3 14:24
 */
@RestController
@RequestMapping
public class TestController {

    @Autowired
    private RedisTemplate template;



}
