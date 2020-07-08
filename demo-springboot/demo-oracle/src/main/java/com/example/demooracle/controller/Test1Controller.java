package com.example.demooracle.controller;

import com.example.demooracle.mapper.TestMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：zxq
 * @date ：Created in 2020/7/8 17:46
 */

@RestController
@RequestMapping("/")
public class Test1Controller {

    @Autowired
    private TestMapper testMapper;

    @GetMapping("/test1")
    public String test1(){

        System.out.println(testMapper.countRealByPage1());
        return "success";
    }

    @GetMapping("/test2/{id}")
    public String test2(@PathVariable("id") String id){

        System.out.println(testMapper.test2(id));
        return "success";
    }
}
