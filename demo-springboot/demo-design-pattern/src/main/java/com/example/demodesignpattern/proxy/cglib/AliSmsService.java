package com.example.demodesignpattern.proxy.cglib;

/**
 * @author ：zxq
 * @date ：Created in 2021/4/15 19:10
 */

public class AliSmsService {
    public String send(String message) {
        System.out.println("send message:" + message);
        return message;
    }
}
