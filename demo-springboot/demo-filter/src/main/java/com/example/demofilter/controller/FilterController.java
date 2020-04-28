package com.example.demofilter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：zxq
 * @date ：Created in 2020/4/27 17:54
 */

@RestController
@RequestMapping("/filter")
public class FilterController {

    @GetMapping("/test")
    public String test(String cityCode){
        System.out.println("test-"+cityCode);
        return cityCode;
    }
}
