package com.example.demodubboapi.service;

import com.example.demodubboapi.model.User;

/**
 * @author ：zxq
 * @date ：Created in 2020/7/1 14:51
 */

public interface SayHelloService {
    void sayHello(String s);

    User getUser();

    void build(User user);

    User reBuild(User user);
}
