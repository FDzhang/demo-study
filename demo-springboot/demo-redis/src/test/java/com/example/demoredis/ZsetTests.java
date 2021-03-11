package com.example.demoredis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ：zxq
 * @date ：Created in 2021/3/9 15:03
 */
@SpringBootTest
public class ZsetTests {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    void testPut() {
        //新增一个有序集合，存在的话为false,但是已经会更新score，不存在的话为true
        System.out.println(redisTemplate.opsForZSet().add("zset", "zset-1", 1.7));

        ZSetOperations.TypedTuple<Object> objectTypedTuple1 = new DefaultTypedTuple<>("zset-1", 1.1);
        ZSetOperations.TypedTuple<Object> objectTypedTuple2 = new DefaultTypedTuple<>("zset-2", 2.2);
        ZSetOperations.TypedTuple<Object> objectTypedTuple3 = new DefaultTypedTuple<>("zset-3", 3.3);
        ZSetOperations.TypedTuple<Object> objectTypedTuple4 = new DefaultTypedTuple<>("zset-4", 4.4);
        ZSetOperations.TypedTuple<Object> objectTypedTuple5 = new DefaultTypedTuple<>("zset-5", 5.5);
        ZSetOperations.TypedTuple<Object> objectTypedTuple6 = new DefaultTypedTuple<>("zset-6", 6.6);
        Set<ZSetOperations.TypedTuple<Object>> tuples = new HashSet<>();
        tuples.add(objectTypedTuple1);
        tuples.add(objectTypedTuple2);
        tuples.add(objectTypedTuple3);
        tuples.add(objectTypedTuple4);
        tuples.add(objectTypedTuple5);
        tuples.add(objectTypedTuple6);

        System.out.println(redisTemplate.opsForZSet().add("zset1", tuples));
        System.out.println(redisTemplate.opsForZSet().range("zset1", 0, -1));
    }

    @Test
    void testGet() {
    }

    @Test
    void testHasKey() {
    }

    @Test
    void testUnion() {
        System.out.println(redisTemplate.opsForZSet().add("{zzset1}zzset1", "zset-1", 1.0));
        System.out.println(redisTemplate.opsForZSet().add("{zzset1}zzset1", "zset-2", 2.0));
        System.out.println(redisTemplate.opsForZSet().add("{zzset1}zzset1", "zset-3", 3.0));
        System.out.println(redisTemplate.opsForZSet().add("{zzset1}zzset1", "zset-4", 6.0));

        System.out.println(redisTemplate.opsForZSet().add("{zzset1}zzset2", "zset-1", 1.0));
        System.out.println(redisTemplate.opsForZSet().add("{zzset1}zzset2", "zset-2", 2.0));
        System.out.println(redisTemplate.opsForZSet().add("{zzset1}zzset2", "zset-3", 3.0));
        System.out.println(redisTemplate.opsForZSet().add("{zzset1}zzset2", "zset-4", 6.0));
        System.out.println(redisTemplate.opsForZSet().add("{zzset1}zzset2", "zset-5", 7.0));
        System.out.println(redisTemplate.opsForZSet().unionAndStore("{zzset1}zzset1", "{zzset1}zzset2", "{sameslot}destZset11"));

        Set<ZSetOperations.TypedTuple<Object>> tuples = redisTemplate.opsForZSet().rangeWithScores("destZset11", 0, -1);
        assert tuples != null;
        for (ZSetOperations.TypedTuple<Object> typedTuple : tuples) {
            prints(typedTuple);
        }
    }

    @Test
    void testAdd() {
        // 增加元素的score值，并返回增加后的值
        System.out.println(redisTemplate.opsForZSet().incrementScore("zset1", "zset-2", 9.1));
    }


    @Test
    void testRemove() {
        // 增加元素的score值，并返回增加后的值
        System.out.println(redisTemplate.opsForZSet().range("zset1", 0, -1));
        System.out.println(redisTemplate.opsForZSet().removeRange("zset1", 1, 2));
        System.out.println(redisTemplate.opsForZSet().range("zset1", 0, -1));

        // 根据指定的score值得范围来移除成员
        System.out.println(redisTemplate.opsForZSet().range("zset2", 0, -1));
        System.out.println(redisTemplate.opsForZSet().removeRangeByScore("zset2", 2, 3));
        System.out.println(redisTemplate.opsForZSet().range("zset2", 0, -1));
    }

    @Test
    void print() {
        // 通过索引区间返回有序集合成指定区间内的成员，其中有序集成员按分数值递增(从小到大)顺序排列
        System.out.println(redisTemplate.opsForZSet().range("zset1", 0, -1));

        // 返回有序集中指定成员的排名，其中有序集成员按分数值递增(从小到大)顺序排列
        // 0  -> 表明排名第一
        System.out.println(redisTemplate.opsForZSet().rank("zset1", "zset-5"));
        // 返回有序集中指定成员的排名，其中有序集成员按分数值递减(从大到小)顺序排列
        System.out.println(redisTemplate.opsForZSet().reverseRank("zset1", "zset-5"));


        Set<ZSetOperations.TypedTuple<Object>> tuples = redisTemplate.opsForZSet().rangeWithScores("zset1", 0, -1);

        assert tuples != null;
        for (ZSetOperations.TypedTuple<Object> typedTuple : tuples) {
            prints(typedTuple);
        }
        //通过分数返回有序集合指定区间内的成员，其中有序集成员按分数值递增(从小到大)顺序排列
        System.out.println(redisTemplate.opsForZSet().rangeByScore("zset1", 0, 5));

        // 通过分数返回有序集合指定区间内的成员对象，其中有序集成员按分数值递增(从小到大)顺序排列
        Set<ZSetOperations.TypedTuple<Object>> tuples1 = redisTemplate.opsForZSet().rangeByScoreWithScores("zset1", 0, 5);
        assert tuples1 != null;
        for (ZSetOperations.TypedTuple<Object> typedTuple : tuples1) {
            prints(typedTuple);
        }

        // 通过分数返回有序集合指定区间内的成员，并在索引范围内，其中有序集成员按分数值递增(从小到大)顺序排列
        System.out.println(redisTemplate.opsForZSet().rangeByScore("zset1", 0, 5));
        System.out.println(redisTemplate.opsForZSet().rangeByScore("zset1", 0, 5, 1, 2));


        Set<ZSetOperations.TypedTuple<Object>> tuples2 = redisTemplate.opsForZSet().rangeByScoreWithScores("zset1", 0, 5, 1, 2);
        assert tuples2 != null;
        for (ZSetOperations.TypedTuple<Object> typedTuple : tuples2) {
            prints(typedTuple);
        }

        // 通过索引区间返回有序集合成指定区间内的成员，其中有序集成员按分数值递减(从大到小)顺序排列
        System.out.println(redisTemplate.opsForZSet().reverseRange("zset1", 0, -1));

        Set<ZSetOperations.TypedTuple<Object>> tuples3 = redisTemplate.opsForZSet().reverseRangeWithScores("zset1", 0, -1);
        assert tuples3 != null;
        for (ZSetOperations.TypedTuple<Object> typedTuple : tuples3) {
            prints(typedTuple);
        }

        // 通过分数返回有序集合指定区间内的成员个数
        System.out.println(redisTemplate.opsForZSet().count("zset1", 0, 5));

        // 获取有序集合的成员数，内部调用的就是zCard方法
        System.out.println(redisTemplate.opsForZSet().size("zset1"));

        // 获取指定成员的score值
        System.out.println(redisTemplate.opsForZSet().score("zset1", "zset-1"));
    }

    private void prints(ZSetOperations.TypedTuple<Object> typedTuple) {
        System.out.println("value: " + typedTuple.getValue() + "  score: " + typedTuple.getScore());
    }
}
