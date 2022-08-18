package com.example.demodesignpattern.proxy.cglib;

/**
 * @author ：zxq
 * @date ：Created in 2021/4/15 19:15
 */

public class Test {
    public static void main(String[] args) {
        AliSmsService aliSmsService = (AliSmsService) CglibProxyFactory.getProxy(AliSmsService.class);
        aliSmsService.send("java");
    }
}
