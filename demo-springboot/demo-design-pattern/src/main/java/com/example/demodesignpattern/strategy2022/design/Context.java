package com.example.demodesignpattern.strategy2022.design;

import java.math.BigDecimal;

/**
 * 策略模式的控制类主要是外部可以传递不同的策略实现，在通过统一的方法执行优惠策略计算。
 * 另外这里也可以包装成map结构，让外部只需要对应的泛型类型即可使用相应的服务。
 *
 * @author : zxq
 * @create : 2022/2/24 20:51
 */
public class Context<T> {

    private ICouponDiscount<T> couponDiscount;

    public Context(ICouponDiscount<T> couponDiscount) {
        this.couponDiscount = couponDiscount;
    }

    public BigDecimal discountAmount(T couponInfo, BigDecimal skuPrice) {
        return couponDiscount.discountAmount(couponInfo, skuPrice);
    }

}

