package cn.seasun.dubboconsumerdemo.config;

import cn.seasun.dubboconsumerdemo.feign.UserFeignClient;
import com.alibaba.cloud.dubbo.annotation.DubboTransported;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhangxinqiang
 * @create 2022/3/8 14:26
 *
 * 服务调用方式三：RestTemplate + Dubbo
 *
 * 在创建 RestTemplate Bean 的方法上，也添加了 @DubboTransported 注解，
 * 这样 RestTemplate 调用服务时，底层也变成了是使用 Dubbo 进行调用服务，也就是说 RestTemplate 变成了 DubboTemplate，嘿嘿。
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    @LoadBalanced
    @DubboTransported(protocol = "dubbo")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
