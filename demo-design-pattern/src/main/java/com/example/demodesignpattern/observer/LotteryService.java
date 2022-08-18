package com.example.demodesignpattern.observer;

import com.example.demodesignpattern.observer.event.EventManager;
import com.example.demodesignpattern.observer.event.listener.MQEventListener;
import com.example.demodesignpattern.observer.event.listener.MessageEventListener;

/**
 * https://segmentfault.com/a/1190000023061530
 *
 * @author zhangxinqiang
 * @create 2022/2/22 18:22
 */
public abstract class LotteryService {

    private EventManager eventManager;

    public LotteryService() {
        eventManager = new EventManager(EventManager.EventType.MQ, EventManager.EventType.Message);

        eventManager.subscribe(EventManager.EventType.MQ, new MQEventListener());
        eventManager.subscribe(EventManager.EventType.Message, new MessageEventListener());
    }

    public LotteryResult draw(String uId) {
        LotteryResult lotteryResult = doDraw(uId);
        // 需要什么通知就给调用什么方法
        eventManager.notify(EventManager.EventType.MQ, lotteryResult);
        eventManager.notify(EventManager.EventType.Message, lotteryResult);
        return lotteryResult;
    }

    protected abstract LotteryResult doDraw(String uId);

}
