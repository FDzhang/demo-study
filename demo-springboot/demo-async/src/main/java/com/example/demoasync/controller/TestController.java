package com.example.demoasync.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author ：zxq
 * @date ：Created in 2021/3/30 14:36
 */
@Controller
public class TestController {

    @RequestMapping(value="re",method = RequestMethod.GET)
    public String re(){
        System.out.println("重定向");
        return "redirect:https://www.baidu.com";
    }
}

