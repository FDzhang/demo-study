package com.example.democombination.controller;

import com.alibaba.fastjson.JSON;
import com.example.democombination.bean.TestBean;

/**
 * @author ：zxq
 * @date ：Created in 2020/10/23 10:57
 */

public class JsonTest {

    public static void main(String[] args) {
        String s = "{\"testName\":\"测试name\",\"value\":\"测试value\"}";
        TestBean testBean = JSON.parseObject(s, TestBean.class);
        System.out.println(testBean);

        TestBean testBean1 = new TestBean();
        testBean1.setName("测试2- name");
        testBean1.setValue("测试2- value");

        String s1 = JSON.toJSONString(testBean1);
        System.out.println(s1);
    }
}
