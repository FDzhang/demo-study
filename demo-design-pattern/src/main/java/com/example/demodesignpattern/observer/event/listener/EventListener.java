package com.example.demodesignpattern.observer.event.listener;

import com.example.demodesignpattern.observer.LotteryResult;

/**
 * @author zhangxinqiang
 * @create 2022/2/22 18:23
 */
public interface EventListener {
    void doEvent(LotteryResult result);
}
