package com.example.demodesignpattern.observer2022.impl;

/**
 * This interface is implemented by all those
 * classes that are to be updated whenever there
 * is an update from CricketData
 *
 * @author : zxq
 * @create : 2022/8/18 22:25
 */
public interface Observer {
    void update(int runs, int wickets, float overs);
}
