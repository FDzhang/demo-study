package com.example.demodesignpattern.strategy.s1;

import java.util.Map;

/**
 * @author ：zxq
 * @date ：Created in 2021/3/19 9:57
 */

public class AliApplets implements Applets {
    @Override
    public Map<String, String> sign(String url) {
        return null;
    }

    @Override
    public String code2Token(String code) {
        return "ali token";
    }

    @Override
    public String decryptContent(String content) {
        return "ali decrypt";
    }
}
