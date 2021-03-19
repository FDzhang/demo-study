package com.example.demodesignpattern.strategy;

/**
 * @author ：zxq
 * @date ：Created in 2021/3/18 17:08
 */

public class OperationAdd implements Strategy {
    @Override
    public int doOperation(int num1, int num2) {
        return num1 + num2;
    }
}
