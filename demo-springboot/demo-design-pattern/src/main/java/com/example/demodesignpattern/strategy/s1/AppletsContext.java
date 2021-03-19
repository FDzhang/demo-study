package com.example.demodesignpattern.strategy.s1;

/**
 * @author ：zxq
 * @date ：Created in 2021/3/18 17:33
 */

public class AppletsContext {
    private Applets applets;

    public AppletsContext() {
    }

    public AppletsContext(Applets applets) {
        this.applets = applets;
    }

    public String code2Token(String code) {
        return applets.code2Token(code);
    }

    public String decryptContent(String content) {
        return applets.decryptContent(content);
    }
}
