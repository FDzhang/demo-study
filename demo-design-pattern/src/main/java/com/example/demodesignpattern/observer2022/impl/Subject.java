package com.example.demodesignpattern.observer2022.impl;


/**
 * @author : zxq
 * @create : 2022/8/18 22:25
 */
public interface Subject {
    void registerObserver(Observer o);
    void unregisterObserver(Observer o);
    void notifyObservers();
}
