package cn.seasun.feignconsumerdemo.feign;

import cn.seasun.feignconsumerdemo.config.DemoProviderFeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author : zxq
 * @create : 2022/3/2 22:10
 *
 * 3.2.3 客户端级别的自定义配置
 * 通过 @FeignClient 注解的 configuration 属性，我们可以设置指定 FeignClient 使用的配置类，即 Feign 客户端级别的自定义配置。
 */
@FeignClient(name = "demo-provider", configuration = DemoProviderFeignClientConfiguration.class)
public interface DemoProviderFeignClient {

    @GetMapping("/echo")
    String echo(@RequestParam("name") String name);

}
