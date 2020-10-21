package com.example.demomysql.controller;

import com.example.demomysql.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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

    @PostMapping("test")
    public String test3(HttpServletRequest request){
        System.out.println(request.getParameter("str"));
        System.out.println(request.getParameter("id"));
        return "successs";
    }
}
