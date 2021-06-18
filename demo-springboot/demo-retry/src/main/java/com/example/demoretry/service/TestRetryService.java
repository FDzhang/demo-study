package com.example.demoretry.service;

/**
 * @author zhangxinqiang
 * @create 2021/6/18 14:10
 */
public interface TestRetryService {

    int retryTest(int code) throws Exception;
}
