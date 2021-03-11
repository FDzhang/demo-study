package com.example.demoredis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * hash Test
 */
@SpringBootTest
class DemoRedisApplicationTests {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    void contextLoads() {

    }

    @Test
    void testPut() {

        // redis的hash 可以 将多个键值对存储到一个redis建里面
        redisTemplate.opsForHash().put("redishash", "name", "wukong");
        redisTemplate.opsForHash().put("redishash", "age", 23);
        redisTemplate.opsForHash().put("redishash", "level", "9");


        Map<String, Object> testMap = new HashMap();
        testMap.put("name", "beijita");
        testMap.put("age", 27);
        testMap.put("level", "8");
        // 一次设置多个键值对
        redisTemplate.opsForHash().putAll("redisHash1", testMap);
        System.out.println(redisTemplate.opsForHash().entries("redisHash1"));

        //仅当hashKey不存在时才设置散列hashKey的值。
        redisTemplate.opsForHash().putIfAbsent("redishash", "name", "123");
        redisTemplate.opsForHash().putIfAbsent("redishash", "sex", "男");

    }

    @Test
    void testGet() {
        // 获取 hashkey 对应的值
        System.out.println(redisTemplate.opsForHash().entries("redishash"));
    }

    @Test
    void testHasKey() {
        System.out.println(redisTemplate.opsForHash().hasKey("redishash", "name"));
        System.out.println(redisTemplate.opsForHash().hasKey("redishash", "names"));
    }

    @Test
    void testAdd() {
        System.out.println(redisTemplate.opsForHash().get("redishash", "age"));
//        System.out.println(redisTemplate.opsForHash().increment("redishash","age",1));
        System.out.println(redisTemplate.opsForHash().increment("redishash", "age", 1.1));

    }

    @Test
    void print() {
        System.out.println(redisTemplate.opsForHash().entries("redishash"));
        System.out.println(redisTemplate.opsForHash().get("redishash", "age"));

        List<Object> kes = new ArrayList<>();
        kes.add("name");
        kes.add("age");
        System.out.println(redisTemplate.opsForHash().multiGet("redishash", kes));

        System.out.println(redisTemplate.opsForHash().keys("redishash"));
        System.out.println(redisTemplate.opsForHash().values("redishash"));

        System.out.println(redisTemplate.opsForHash().size("redishash"));

        // 使用Cursor在key的hash中迭代，相当于迭代器。
        Cursor<Map.Entry<Object, Object>> redihash = redisTemplate.opsForHash().scan("redishash", ScanOptions.NONE);

        while (redihash.hasNext()) {
            Map.Entry<Object, Object> next = redihash.next();
            System.out.println(next.getKey()+":"+next.getValue());
        }
    }


}
