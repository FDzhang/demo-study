package com.example.demoretry.service.impl;

import com.example.demoretry.service.TestRetryService;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

/**
 * @author zhangxinqiang
 * @create 2021/6/18 14:10
 */
@EnableRetry
@Service
public class TestRetryServiceImpl implements TestRetryService {


    @Override
    @Retryable(value = Exception.class, maxAttempts = 3, backoff = @Backoff(delay = 2000, multiplier = 1.5, maxDelay = 360000L))
    public int retryTest(int code) throws Exception {

        System.out.println("retryTest 被调用,时间：" + LocalTime.now());
        if (code == 0) {
            throw new Exception("情况不对头！");
        }
        System.out.println("retryTest 被调用,情况对头了！");

        return 200;
    }

    @Recover
    public int recover1(Exception e) {
        System.out.println("回调方法执行！！！！");
        //记日志到数据库 或者调用其余的方法
        return 400;
    }
}
