package com.example.demoasync.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;

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

