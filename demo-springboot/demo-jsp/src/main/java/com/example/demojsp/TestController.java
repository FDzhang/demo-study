package com.example.demojsp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author ：zxq
 * @date ：Created in 2020/4/22 14:54
 */
@Controller
public class TestController {

    @GetMapping("index")
    public String test1(){
        return "/test1";
    }
}
