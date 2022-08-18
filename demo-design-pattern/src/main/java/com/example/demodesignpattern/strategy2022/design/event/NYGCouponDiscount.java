package com.example.demodesignpattern.strategy2022.design.event;

import com.example.demodesignpattern.strategy2022.design.ICouponDiscount;

import java.math.BigDecimal;

/**
 * @author : zxq
 * @create : 2022/2/24 20:46
 */
public class NYGCouponDiscount implements ICouponDiscount<Double> {

    /**
     * n元购购买
     * 1. 无论原价多少钱都固定金额购买
     */
    @Override
    public BigDecimal discountAmount(Double couponInfo, BigDecimal skuPrice) {
        return new BigDecimal(couponInfo);
    }

}
