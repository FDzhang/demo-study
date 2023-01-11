package com.example.demodesignpattern;

import java.util.Arrays;

/**
 * @author : zxq
 * @create : 2022/8/23 23:23
 */
public enum MType {
    TYPE1, TYPE2, ERROR;

    public static void main(String[] args) {
        MType asdf = Arrays.stream(MType.values()).filter(e -> e.name().equals("asdf")).findFirst().orElse(MType.ERROR);
        System.err.println(asdf);
    }
}
