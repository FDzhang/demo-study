package com.example.demoredis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
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
    private RedisTemplate<String, String> template;

    public static void main(String[] args) {
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.afterPropertiesSet();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());

        // redis的hash 可以 将多个键值对存储到一个redis建里面
        redisTemplate.opsForHash().put("redishash","name","wukong");
        redisTemplate.opsForHash().put("redishash","age",23);
        redisTemplate.opsForHash().put("redishash","level","9");


    }

}
