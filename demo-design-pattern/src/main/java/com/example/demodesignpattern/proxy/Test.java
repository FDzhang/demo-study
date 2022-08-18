package com.example.demodesignpattern.proxy;

/**
 * @author ：zxq
 * @date ：Created in 2021/4/15 19:06
 */

public class Test {
    public static void main(String[] args) {

        SmsService smsService = (SmsService) JdkProxyFactory.getProxy(new SmsServiceImpl());
        smsService.send("java");
    }
}
