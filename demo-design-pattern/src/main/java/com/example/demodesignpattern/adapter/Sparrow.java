package com.example.demodesignpattern.adapter;

/**
 * @author : zxq
 * @create : 2022/9/4 23:15
 */
public class Sparrow implements Bird {
    // a concrete implementation of bird
    @Override
    public void fly() {
        System.out.println("Flying");
    }

    @Override
    public void makeSound() {
        System.out.println("Chirp Chirp");
    }
}
