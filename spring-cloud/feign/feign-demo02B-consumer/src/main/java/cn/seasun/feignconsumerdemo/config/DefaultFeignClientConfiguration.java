package cn.seasun.feignconsumerdemo.config;

/**
 * @author zhangxinqiang
 * @create 2022/3/3 16:54
 */
// DefaultFeignClientConfiguration.java

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 全局 FeignClient 配置类
 */
@Configuration
public class DefaultFeignClientConfiguration {

    @Bean
    public Logger.Level defaultFeignClientLoggerLevel() {
        return Logger.Level.BASIC;
    }

}
