package com.example.demoprometheus;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author zhangxinqiang
 * @create 2021/9/2 16:24
 */
public class Test {
    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("0.00");
        BigDecimal x = new BigDecimal("3.9");
        System.out.println(x.longValue());
    }
}
