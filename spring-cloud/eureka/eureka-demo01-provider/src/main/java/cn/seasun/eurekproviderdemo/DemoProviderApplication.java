package cn.seasun.eurekproviderdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangxinqiang
 * @create 2022/2/25 14:51
 * <p>
 * ① @EnableDiscoveryClient 注解，开启 Spring Cloud 的注册发现功能。
 * 不过从 Spring Cloud Edgware 版本开始，实际上已经不需要添加 @EnableDiscoveryClient 注解，
 * 只需要引入 Spring Cloud 注册发现组件，就会自动开启注册发现的功能。
 * 例如说，我们这里已经引入了 spring-cloud-starter-alibaba-nacos-discovery 依赖，
 * 就不用再添加 @EnableDiscoveryClient 注解了。
 * <p>
 * 拓展小知识：在 Spring Cloud Common 项目中，定义了 DiscoveryClient 接口，
 * 作为通用的发现客户端，提供读取服务和读取服务列表的 API 方法。
 * 而想要集成到 Spring Cloud 体系的注册中心的组件，需要提供对应的 DiscoveryClient 实现类。
 * <p>
 * 例如说，Spring Cloud Alibaba Nacos Discovery 提供了 NacosDiscoveryClient 实现，
 * Spring Cloud Netflix Eureka 提供了 EurekaDiscoveryClient 实现。
 * <p>
 * 如此，所有需要使用到的地方，只需要获取到 DiscoveryClient 客户端，而无需关注具体实现，保证其通用性。
 * <p>
 * ② TestController 类，提供了 /echo 接口，返回 provider:${name} 结果。
 * <p>
 * 简单测试
 * ① 通过 DemoProviderApplication 启动服务提供者，IDEA 控制台输出日志如：
 * o.s.c.n.e.s.EurekaServiceRegistry        : Registering application DEMO-PROVIDER with eureka with status UP
 * com.netflix.discovery.DiscoveryClient    : DiscoveryClient_DEMO-PROVIDER/macbook-pro-8:demo-provider:18080: registering service...
 * com.netflix.discovery.DiscoveryClient    : DiscoveryClient_DEMO-PROVIDER/macbook-pro-8:demo-provider:18080 - registration status: 204
 */
@SpringBootApplication
@EnableDiscoveryClient
public class DemoProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoProviderApplication.class, args);
    }

    @RestController
    static class TestController {

        @GetMapping("/echo")
        public String echo(String name) {
            return "provider:" + name;
        }

    }

}
