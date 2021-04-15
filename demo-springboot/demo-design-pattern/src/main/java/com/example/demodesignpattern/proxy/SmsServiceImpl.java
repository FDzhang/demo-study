package com.example.demodesignpattern.proxy;

/**
 * @author ：zxq
 * @date ：Created in 2021/4/15 19:01
 */

public class SmsServiceImpl implements SmsService {
    @Override
    public String send(String message) {
        System.out.println("send message:" + message);
        return message;
    }
}
