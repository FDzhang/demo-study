package cn.seasun.eurekconsumerdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author zhangxinqiang
 * @create 2022/2/25 15:03
 * <p>
 * <p>
 * ① @EnableDiscoveryClient 注解，因为已经无需添加，所以我们进行了注释，原因在上面已经解释过。
 * ② RestTemplateConfiguration 配置类，创建 RestTemplate Bean。RestTemplate
 * 是 Spring 提供的 HTTP 调用模板工具类，可以方便我们稍后调用服务提供者的 HTTP API。
 * ③ TestController 提供了 /hello 接口，用于调用服务提供者的 /demo 接口。代码略微有几行，我们来稍微解释下哈。
 * discoveryClient 属性，DiscoveryClient 对象，服务发现客户端，上文我们已经介绍过。
 * 这里我们注入的不是 Eureka 提供的 EurekaDiscoveryClient，保证通用性。未来如果我们不使用 Eureka 作为注册中心，
 * 而是使用 Nacos 或则 Zookeeper 时，则无需改动这里的代码。
 * loadBalancerClient 属性，LoadBalancerClient 对象，负载均衡客户端。
 * 稍后我们会使用它，从 Eureka 获取的服务 demo-provider 的实例列表中，选择一个进行 HTTP 调用。
 *
 *
 * 拓展小知识：在 Spring Cloud Common 项目中，定义了LoadBalancerClient 接口，
 * 作为通用的负载均衡客户端，提供从指定服务中选择一个实例、对指定服务发起请求等 API 方法。
 * 而想要集成到 Spring Cloud 体系的负载均衡的组件，需要提供对应的 LoadBalancerClient 实现类。
 * 例如说，Spring Cloud Netflix Ribbon 提供了 RibbonLoadBalancerClient 实现。
 *
 * 如此，所有需要使用到的地方，只需要获取到 DiscoveryClient 客户端，而无需关注具体实现，保证其通用性。
 * 😈 不过貌似 Spring Cloud 体系中，暂时只有 Ribbon 一个负载均衡组件。
 *
 * 当然，LoadBalancerClient 的服务的实例列表，是来自 DiscoveryClient 提供的。
 *
 * /hello 接口，示例接口，对服务提供者发起一次 HTTP 调用。
 *
 * <1> 处，获得服务 demo-provider 的一个实例。这里我们提供了两种方式的代码，分别基于 DiscoveryClient 和 LoadBalancerClient。
 * <2> 处，通过获取到的服务实例 ServiceInstance 对象，拼接请求的目标 URL，之后使用 RestTemplate 发起 HTTP 调用。
 *
 */
@SpringBootApplication
// @EnableDiscoveryClient
public class DemoConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoConsumerApplication.class, args);
    }

    @Configuration
    public class RestTemplateConfiguration {

        @Bean
        public RestTemplate restTemplate() {
            return new RestTemplate();
        }

    }

    @RestController
    static class TestController {

        @Autowired
        private DiscoveryClient discoveryClient;
        @Autowired
        private RestTemplate restTemplate;
        @Autowired
        private LoadBalancerClient loadBalancerClient;

        @GetMapping("/hello")
        public String hello(String name) {
            //<1> 获得服务 `demo-provider` 的一个实例
            ServiceInstance instance;
            if (true) {
                // 获取服务 `demo-provider` 对应的实例列表
                List<ServiceInstance> instances = discoveryClient.getInstances("demo-provider");
                // 选择第一个
                instance = instances.size() > 0 ? instances.get(0) : null;
            } else {
                instance = loadBalancerClient.choose("demo-provider");
            }
            //<2> 发起调用
            if (instance == null) {
                throw new IllegalStateException("获取不到实例");
            }
            String targetUrl = instance.getUri() + "/echo?name=" + name;
            String response = restTemplate.getForObject(targetUrl, String.class);
            // 返回结果
            return "consumer:" + response;
        }

    }

}
