package com.example.demodesignpattern.strategy.s1;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author ：zxq
 * @date ：Created in 2021/3/19 9:56
 */
@Component("wx")
public class WxApplets implements Applets {
    @Override
    public Map<String, String> sign(String url) {
        return null;
    }

    @Override
    public String code2Token(String code) {
        return "wx token";
    }

    @Override
    public String decryptContent(String content) {
        return "wx decrypt";
    }
}
