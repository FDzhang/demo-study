package com.example.demojaeger.controller;

import io.opentracing.Span;
import io.opentracing.Tracer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhangxinqiang
 * @create 2022/1/10 13:59
 */
@RestController
@Slf4j
public class Tracing2Controller {
    @Autowired
    private Tracer tracer;

    private String dateStr() {
        return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
    }

    /**
     * 模拟业务执行，耗时100毫秒
     *
     * @param parentSpan
     */
    private void mockBiz(Span parentSpan) {
        // 基于指定span，创建其子span
        Span span = tracer.buildSpan("mockBizChild").asChildOf(parentSpan).start();

        log.info("hello");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        span.finish();
    }

    /**
     * 返回字符串类型
     *
     * @return
     */
    @GetMapping("/hello")
    public String hello() {
        long startTime = System.currentTimeMillis();

        // 生成当前时间
        String timeStr = dateStr();

        // 创建一个span，在创建的时候就添加一个tag
        Span span = tracer.buildSpan("mockBiz")
                .withTag("time-str", timeStr)
                .start();
        // span日志
        span.log("normal span log");
        // 模拟一个耗时100毫秒的业务
        mockBiz(span);
        // 增加一个tag
        span.setTag("tiem-used", System.currentTimeMillis() - startTime);
        // span结束
        span.finish();


        // 返回
        return "hello, " + timeStr;
    }
}