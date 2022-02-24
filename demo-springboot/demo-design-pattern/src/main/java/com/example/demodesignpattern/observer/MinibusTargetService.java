package com.example.demodesignpattern.observer;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;

import java.util.Random;

/**
 * @author : zxq
 * @create : 2022/2/24 20:07
 */
public class MinibusTargetService {
    public String lottery(String uId) {
        String success = StrUtil.format("恭喜你，编码{}在本次中签", uId);
        String fail = StrUtil.format("很遗憾，编码{}在本次未中签或摇号资格已过期", uId);
        return Math.abs(uId.hashCode()) % 2 == 0 ? success : fail;
    }
}
