package cn.seasun.feignconsumerdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author : zxq
 * @create : 2022/3/2 22:08
 * <p>
 * 8. 请求重试
 * 示例代码对应仓库：
 * <p>
 * 服务提供者：labx-03-sc-feign-demo01-provider
 * 服务消费者：labx-03-sc-feign-demo07-consumer
 * Feign 和 Ribbon 都有请求重试的功能，两者都启用该功能的话，会产生冲突的问题。
 * 因此，有且只能启动一个的重试。目前比较推荐的是使用 Ribbon 来提供重试，如下是来自 Spring Cloud 开发者的说法：
 * <p>
 * 在 Spring Cloud OpenFeign 中，默认创建的是 NEVER_RETRY 不进行重试。如此，我们只需要配置 Ribbon 的重试功能即可。
 * 下面，让我们来搭建下 Feign + Ribbon 请求重试的使用示例。
 * <p>
 * <p>
 * 9. Feign 与 RestTemplate 的对比
 * 从开发效率、可维护性的角度来说，Feign 更加有优势。
 * 从执行性能、灵活性的角度来说，RestTemplate 更加有优势。
 * <p>
 * 个人推荐使用 Feign 为主，RestTemplate 为辅：
 * <p>
 * 相比来说，开发效率、可维护性非常重要，要保证开发的体验。
 * 执行性能的问题，因为 Feign 多一层 JDK 动态代理，所以会差一些。
 * 不过 HTTP 调用的整体性能的大头在网络传输和服务端的执行时间，所以 Feign 和 RestTemplate 的性能差距可以相对忽略。
 * 灵活性的问题，99.99% 的情况下，Feign 都能够实现或者相对绕的实现；无法实现的情况下，在考虑采用 RestTemplate 进行实现。
 * 10. Feign 主要组件
 * 绝大多数情况下，我们并不需要去深入了解 Feign 的主要组件。所以本小节的内容，更多作为拓展知识，胖友可以根据自己的需要进行阅读。
 * <p>
 * <p>
 * 10.1 Feign.Builder
 * Feign.Builder 类，Feign 构造器，可以设置各种配置，最终构建出指定 API 接口的 HTTP “客户端”。示例代码如下：
 * <p>
 * RemoteService service = Feign.builder()
 * .options(new Options(1000, 5000)) // 请求的连接和读取超时时间
 * .retryer(new Retryer.Default(5000, 5000, 3)) // 重试策略
 * .target(RemoteService.class, "http://www.iocoder.cn"); // 目标 API 接口和目标地址
 * <p>
 * 10.2 Client
 * Client 接口，定义提交 HTTP 请求的方法。
 * <p>
 * Feign 提供了 4 个 Client 实现类，如下图所示：
 * <p>
 * <p>
 * Client.Default，基于 JDK HttpURLConnection 封装的 HTTP 客户端。
 * Client.Proxied，在 Client.Default 的基础上，允许使用 java.net.Proxy 代理。
 * ApacheHttpClient，基于 Apache HttpClient 封装的 HTTP 客户端。
 * OkHttpClient，基于 OkHttp 封装的 HTTP 客户端。
 * Spring Cloud OpenFeign 提供了上图另外 2 个 Client 实现类。
 * LoadBalancerFeignClient，对 Ribbon 进行集成，提供负载均衡的能力。
 * FeignBlockingLoadBalancerClient，对 Spring Cloud LoadBalancer 的 BlockingLoadBalancerClient 进行集成，提供负载均衡的能力。
 * 这里有一个非常有趣的设计，以 LoadBalancerFeignClient 举例子，我们来看看 Feign 和 Ribbon 是怎么集成的。如下图所示：
 * <p>
 * <p>
 * 10.8 小结
 * Feign 的原理与源码并不复杂，建议胖友可以阅读下《Spring Cloud Feign 设计原理》http://www.iocoder.cn/Fight/Spring-Cloud-Feign-design-principles/?self
 * 文章，重点要理解两点：
 * <p>
 * Feign 是如何给 Java API 接口创建动态代理，从而生成调用远程 HTTP API 接口的实现类。
 * Feign 和 Ribbon 是如何集成的，并实现前者负责 HTTP 接口的声明与调用，后者负责服务实例的负载均衡。
 */
@SpringBootApplication
@EnableFeignClients
public class DemoConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoConsumerApplication.class, args);
    }

}