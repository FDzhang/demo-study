package com.example.demovalidator.bean;

import cn.hutool.core.util.RandomUtil;

import java.util.Random;

/**
 * @author zhangxinqiang
 * @create 2021/7/6 16:46
 */
public class PersonSupplier {

    public Person build() {
        Person person = new Person();
        person.setId((long) new Random().nextInt(100));
        person.setAge(new Random().nextInt(100));
        person.setUsername(RandomUtil.randomString("孙悟空路飞", 2));
        return person;
    }

}
