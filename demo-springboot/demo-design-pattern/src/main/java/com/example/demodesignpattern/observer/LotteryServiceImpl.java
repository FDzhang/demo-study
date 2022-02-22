package com.example.demodesignpattern.observer;

/**
 * @author zhangxinqiang
 * @create 2022/2/22 18:25
 */
public class LotteryServiceImpl extends LotteryService{


    private MinibusTargetService minibusTargetService = new MinibusTargetService();

    @Override
    protected LotteryResult doDraw(String uId) {
        // 摇号
        String lottery = minibusTargetService.lottery(uId);
        // 结果
        return new LotteryResult(uId, lottery, new Date());
    }

}
