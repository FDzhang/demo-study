package com.example.demoredis.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * redis工具类
 * xxxHasPre : 表示带前缀
 * xxxFix : 表示固定时间自动过期
 *
 * @author ：zxq
 * @date ：Created in 2021/1/25 18:12
 */
@Component
public class RedisUtilEasy {
    private static RedisTemplate<String, String> redisTemplate;

    private static final long FIX_TIME = 60L;

    public static void set(String key, String value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    public static void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value, FIX_TIME, TimeUnit.SECONDS);
    }

    public static void update(String key, String value) {
        // 更新值，不更新过期时间
        redisTemplate.opsForValue().set(key, value, 0L);
    }

    public static String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public static Long getExpire(String key, TimeUnit unit) {
        return redisTemplate.getExpire(key, unit);
    }

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        RedisUtilEasy.redisTemplate = redisTemplate;
    }
}
