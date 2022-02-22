package com.example.demodesignpattern.observer.event.listener;

import com.example.demodesignpattern.observer.LotteryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhangxinqiang
 * @create 2022/2/22 18:24
 */
public class MessageEventListener implements EventListener  {
    private Logger logger = LoggerFactory.getLogger(MessageEventListener.class);

    @Override
    public void doEvent(LotteryResult result) {
        logger.info("给用户 {} 发送短信通知(短信)：{}", result.getuId(), result.getMsg());
    }
}
