package com.example.demodubboprovider.service;

import com.example.demodubboapi.model.User;
import com.example.demodubboapi.service.SayHelloService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author ：zxq
 * @date ：Created in 2020/7/1 15:09
 */
@DubboService(retries = 0, timeout = 5000, group = "${group.name}")
public class SayHelloServiceImpl implements SayHelloService {
    @Override
    public void sayHello(String s) {
        System.out.println("hello : " + s);
    }

    @Override
    public User getUser() {
        User user = new User();
        user.setName("悟空");
        user.setAge(500);
        return user;
    }

    @Override
    public void build(User user) {
        user.setAge(500);
    }

    @Override
    public User reBuild(User user) {
        user.setAge(500);
        return user;
    }
}
