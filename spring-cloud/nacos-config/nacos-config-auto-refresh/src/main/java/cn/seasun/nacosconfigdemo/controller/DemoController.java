package cn.seasun.nacosconfigdemo.controller;

import cn.seasun.nacosconfigdemo.config.OrderProperties;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author : zxq
 * @create : 2022/3/10 20:03
 *
 * 4.2 @RefreshScope
 * 在 Spring Cloud 中，提供了 @RefreshScope 注解，可以声明在 Bean 上，实现该 Bean 的配置刷新。
 *
 * 友情提示：对 @RefreshScope 注解的实现原理感兴趣的胖友，可以阅读《@RefreshScope 那些事》文章。
 *
 * 下面，我们将 @RefreshScope 注解添加在 DemoController 类上，实现 @Value 注解的属性的刷新。代码如下：
 *
 *
 * 4.4 EnvironmentChangeEvent
 * 通过 @ConfigurationProperties 或者 @Value + @RefreshScope 注解，已经能够满足我们绝大多数场景下的自动刷新配置的功能。
 * 但是，在一些场景下，我们仍然需要实现对配置的监听，执行自定义的逻辑。
 *
 *
 * 例如说，当数据库连接的配置发生变更时，我们需要通过监听该配置的变更，重新初始化应用中的数据库连接，从而访问到新的数据库地址。
 * 又例如说，当日志级别发生变更时，我们需要通过监听该配置的变更，设置应用中的 Logger 的日志级别，从而后续的日志打印可以根据新的日志级别。
 * 在 Spring Cloud 中，在 Environment 的属性配置发生变化时，会发布 EnvironmentChangeEvent 事件。
 * 这样，我们只需要实现 EnvironmentChangeEvent 事件的监听器，就可以进行自定义的逻辑处理。
 *
 * 例如说，Spring Cloud 内置了 LoggingRebinder 监听器，实现了对 EnvironmentChangeEvent 事件的监听，
 * 在发现 logging.level 配置项发生变化时，重新设置日志组件的日志级别。如下图所示：
 */
@RestController
@RequestMapping("/demo")
@RefreshScope // 加我加我加我
public class DemoController {

    @Autowired
    private OrderProperties orderProperties;

    /**
     * 测试 @ConfigurationProperties 注解的配置属性类
     */
    @GetMapping("/test01")
    public OrderProperties test01() {
        return orderProperties;
    }

    @Value(value = "${order.pay-timeout-seconds}") // @NacosValue(value = "${order.pay-timeout-seconds}")
    private Integer payTimeoutSeconds;
    @Value(value = "${order.create-frequency-seconds}") // @NacosValue(value = "${order.create-frequency-seconds}")
    private Integer createFrequencySeconds;

    /**
     * 测试 @Value 注解的属性
     */
    @GetMapping("/test02")
    public Map<String, Object> test02() {
        return new JSONObject().fluentPut("payTimeoutSeconds", payTimeoutSeconds)
                .fluentPut("createFrequencySeconds", createFrequencySeconds);
    }

}