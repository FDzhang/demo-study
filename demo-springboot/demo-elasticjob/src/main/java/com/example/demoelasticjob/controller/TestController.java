package com.example.demoelasticjob.controller;

import com.example.demoelasticjob.job.MyJob;
import org.apache.shardingsphere.elasticjob.lite.api.bootstrap.impl.OneOffJobBootstrap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : zxq
 * @date-time : 2021/4/2 22:01
 */
@RestController
public class TestController {
    @Autowired
    @Qualifier(value = "occurErrorNoticeDingtalkBean")
    private OneOffJobBootstrap occurErrorNoticeDingtalkJob;
}
