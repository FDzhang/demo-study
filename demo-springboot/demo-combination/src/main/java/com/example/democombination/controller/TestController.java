package com.example.democombination.controller;

import com.example.democombination.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：zxq
 * @date ：Created in 2020/10/20 11:01
 */
@RestController
public class TestController {

    @Autowired
    private TestMapper testMapper;

    @GetMapping("test")
    public String test(){
        return "hello world";
    }

    @GetMapping("count")
    public int test2(){
        return testMapper.count();
    }
}
