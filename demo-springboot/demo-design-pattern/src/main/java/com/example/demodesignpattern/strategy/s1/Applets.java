package com.example.demodesignpattern.strategy.s1;

import java.util.Map;

/**
 * @author ：zxq
 * @date ：Created in 2021/3/18 17:08
 */

public interface Applets {

    /**
     * 签名校验
     */
    Map<String, String> sign(String url);

    /**
     * code2session
     */
    String code2Token(String code);

    /**
     * 解密 如：解密手机号
     */
    String decryptContent(String content);
}
