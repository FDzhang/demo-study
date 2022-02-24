package com.example.demodesignpattern.strategy2022.design;

import com.example.demodesignpattern.strategy2022.design.event.MJCouponDiscount;
import com.example.demodesignpattern.strategy2022.design.event.NYGCouponDiscount;
import com.example.demodesignpattern.strategy2022.design.event.ZJCouponDiscount;
import com.example.demodesignpattern.strategy2022.design.event.ZKCouponDiscount;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 以上四组测试分别验证了不同类型优惠券的优惠策略，测试结果是满足我们的预期。
 * 这里四种优惠券最终都是在原价100元上折扣10元，最终支付90元。
 *
 * @author : zxq
 * @create : 2022/2/24 20:56
 */
@SpringBootTest
@Slf4j
class ICouponDiscountTest {
    @Test
    public void test_zj() {
        // 直减；100-10，商品100元
        Context<Double> context = new Context<>(new ZJCouponDiscount());
        BigDecimal discountAmount = context.discountAmount(10D, new BigDecimal(100));
        log.info("测试结果：直减优惠后金额 {}", discountAmount);
    }

    @Test
    public void test_mj() {
        // 满100减10，商品100元
        Context<Map<String, String>> context = new Context<Map<String, String>>(new MJCouponDiscount());
        Map<String, String> mapReq = new HashMap<String, String>();
        mapReq.put("x", "100");
        mapReq.put("n", "10");
        BigDecimal discountAmount = context.discountAmount(mapReq, new BigDecimal(100));
        log.info("测试结果：满减优惠后金额 {}", discountAmount);
    }

    @Test
    public void test_zk() {
        // 折扣9折，商品100元
        Context<Double> context = new Context<Double>(new ZKCouponDiscount());
        BigDecimal discountAmount = context.discountAmount(0.9D, new BigDecimal(100));
        log.info("测试结果：折扣9折后金额 {}", discountAmount);
    }

    @Test
    public void test_nyg() {
        // n元购；100-10，商品100元
        Context<Double> context = new Context<Double>(new NYGCouponDiscount());
        BigDecimal discountAmount = context.discountAmount(90D, new BigDecimal(100));
        log.info("测试结果：n元购优惠后金额 {}", discountAmount);

    }


}