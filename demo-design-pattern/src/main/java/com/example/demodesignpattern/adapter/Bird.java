package com.example.demodesignpattern.adapter;

/**
 * @author : zxq
 * @create : 2022/9/4 23:14
 */
// Java implementation of Adapter pattern

interface Bird {
    // birds implement Bird interface that allows
    // them to fly and make sounds adaptee interface
    public void fly();

    public void makeSound();
}
