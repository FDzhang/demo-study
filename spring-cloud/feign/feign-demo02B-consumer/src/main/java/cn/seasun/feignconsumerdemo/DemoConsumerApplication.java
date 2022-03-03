package cn.seasun.feignconsumerdemo;

import cn.seasun.feignconsumerdemo.config.DefaultFeignClientConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author : zxq
 * @create : 2022/3/2 22:08
 *
 * 通过 @EnableFeignClients 注解的 defaultConfiguration 属性，我们可以设置默认 FeignClient 使用的配置类，即 Feign 全局级别的自定义配置。
 */
@SpringBootApplication
@EnableFeignClients(defaultConfiguration = DefaultFeignClientConfiguration.class)
public class DemoConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoConsumerApplication.class, args);
    }

}