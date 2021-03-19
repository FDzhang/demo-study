package com.example.demodesignpattern.strategy.s1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：zxq
 * @date ：Created in 2021/3/19 10:51
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {
    private final AppletsContext appletsContext;


    @Autowired
    public TestController(AppletsContext appletsContext) {
        this.appletsContext = appletsContext;
    }

    @GetMapping("/choose")
    public String choose(@RequestParam String from) {
        return appletsContext.code2Token("123", from);
    }
}
