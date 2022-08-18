package com.example.demodesignpattern.strategy;

/**
 * @author ：zxq
 * @date ：Created in 2021/3/18 17:10
 */

public class Context {
    private Strategy strategy;

    public Context(){

    }

    public Context(Strategy strategy){
        this.strategy = strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public int executeStrategy(int num1, int num2){
        return strategy.doOperation(num1, num2);
    }
}
