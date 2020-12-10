package com.example.demofilter.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author ：zxq
 * @date ：Created in 2020/12/10 13:58
 */
@Controller
@Slf4j
public class IndexController {

    @GetMapping("/index")
    public String index(){
        log.info("index");
        return "index";
    }

    @GetMapping("/page")
    public String page(){
        log.info("page");
        return "page";
    }
}
