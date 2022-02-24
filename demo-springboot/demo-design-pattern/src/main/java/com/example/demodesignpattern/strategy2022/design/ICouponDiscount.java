package com.example.demodesignpattern.strategy2022.design;

import java.math.BigDecimal;

/**
 * @author : zxq
 * @create : 2022/2/24 20:42
 */
public interface ICouponDiscount<T> {

    /**
     * 优惠券金额计算
     * @param couponInfo 券折扣信息；直减、满减、折扣、N元购
     * @param skuPrice   sku金额
     * @return           优惠后金额
     */
    BigDecimal discountAmount(T couponInfo, BigDecimal skuPrice);

}
