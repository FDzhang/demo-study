package com.example.demovalidator.controller;

import com.example.demovalidator.bean.JacksonOutFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author zhangxinqiang
 * @create 2021/7/6 15:49
 */

@RestController
public class JacksonOutFormatController {

    @GetMapping("jackson")
    public JacksonOutFormat jacksonOutFormat(){
        JacksonOutFormat json = new JacksonOutFormat();

        json.setDate1(new Date());
        json.setDate2(new Date());
        json.setDate3(new Date());

        json.setName("测试");
        json.setNumber(123);
        json.setId(1L);

        json.setD1(33366612345.6789);
        json.setD2(33366612345.6789);
        json.setD3(33366612345.6789);
        json.setD4(0.99);
        json.setD5(33366612345.6789);

        return json;
    }

}
