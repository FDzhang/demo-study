package com.example.demojaeger.service.impl;

import com.example.demojaeger.service.TracingService;
import org.springframework.stereotype.Service;

/**
 * @author zhangxinqiang
 * @create 2022/1/10 17:33
 */
@Service
public class TracingServiceImpl implements TracingService {

    @Override
    public String trace(String s) {
        return "trace " + s;
    }
}
