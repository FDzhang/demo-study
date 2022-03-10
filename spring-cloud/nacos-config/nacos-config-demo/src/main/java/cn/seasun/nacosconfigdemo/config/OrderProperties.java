package cn.seasun.nacosconfigdemo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author : zxq
 * @create : 2022/3/10 20:01
 * 在类上，添加 @Component 注解，保证该配置类可以作为一个 Bean 被扫描到。
 * 在类上，添加 @ConfigurationProperties 注解，并设置 prefix = "order" 属性，这样它就可以读取前缀为 order 配置项，设置到配置类对应的属性上。
 *
 * 😈 这里，我们注释了一段 @NacosConfigurationProperties 注解的代码，该注解在功能上是对标 @ConfigurationProperties 注解，用于将 Nacos 配置注入 POJO 配置类中。
 * 为什么我们这里注释掉了呢？因为我们在「2.2 配置文件」中，添加 Nacos Config 相关配置到 bootstrap.yaml 配置文件中，
 * 因此 Spring Cloud 应用在启动时，预加载了来自 Nacos 配置，所以可以直接使用 @ConfigurationProperties 注解即可。
 * 这样的好处，是可以更加通用，而无需和 Nacos 有耦合与依赖。
 */
@Component
//@NacosConfigurationProperties(prefix = "order", dataId = "${nacos.config.data-id}", type = ConfigType.YAML)
@ConfigurationProperties(prefix = "order")
public class OrderProperties {

    /**
     * 订单支付超时时长，单位：秒。
     */
    private Integer payTimeoutSeconds;

    /**
     * 订单创建频率，单位：秒
     */
    private Integer createFrequencySeconds;

//    /**
//     * 配置描述
//     */
//    private String desc;

    public Integer getPayTimeoutSeconds() {
        return payTimeoutSeconds;
    }

    public OrderProperties setPayTimeoutSeconds(Integer payTimeoutSeconds) {
        this.payTimeoutSeconds = payTimeoutSeconds;
        return this;
    }

    public Integer getCreateFrequencySeconds() {
        return createFrequencySeconds;
    }

    public OrderProperties setCreateFrequencySeconds(Integer createFrequencySeconds) {
        this.createFrequencySeconds = createFrequencySeconds;
        return this;
    }

//    public String getDesc() {
//        return desc;
//    }
//
//    public OrderProperties setDesc(String desc) {
//        this.desc = desc;
//        return this;
//    }

}