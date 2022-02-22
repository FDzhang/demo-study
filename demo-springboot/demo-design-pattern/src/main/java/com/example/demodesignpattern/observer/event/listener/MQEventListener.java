package com.example.demodesignpattern.observer.event.listener;

import com.example.demodesignpattern.observer.LotteryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhangxinqiang
 * @create 2022/2/22 18:24
 */
public class MQEventListener implements EventListener {
    private Logger logger = LoggerFactory.getLogger(MQEventListener.class);

    @Override
    public void doEvent(LotteryResult result) {
        logger.info("记录用户 {} 摇号结果(MQ)：{}", result.getuId(), result.getMsg());
    }

}
