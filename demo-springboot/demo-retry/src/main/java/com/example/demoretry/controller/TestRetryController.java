package com.example.demoretry.controller;

import com.example.demoretry.service.TestRetryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhangxinqiang
 * @create 2021/6/18 14:19
 */
@RestController
public class TestRetryController {

    @Resource
    private TestRetryService testRetryService;

    @GetMapping("retry/{code}")
    public String retryTest(@PathVariable Integer code) throws Exception {
        int res = testRetryService.retryTest(code);
        return "result: " + res;
    }
}
