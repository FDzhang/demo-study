package com.example.demojtaatomikos.controller;

import com.example.demojtaatomikos.manager.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：zxq
 * @date ：Created in 2020/12/9 16:36
 */
@RequestMapping
@RestController
public class TestController {

    @Autowired
    private Manager manager;

    @GetMapping("test")
    public String test(Integer x){
        try {
            manager.test(x);
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
        return "Success";
    }
}
