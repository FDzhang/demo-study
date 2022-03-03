package cn.seasun.feignconsumerdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author : zxq
 * @create : 2022/3/2 22:08
 * <p>
 * <p>
 * 7. HTTP 客户端
 * 默认情况下，Feign 通过 JDK 自带的 HttpURLConnection 封装了 Client.Default，
 * 实现 HTTP 调用的客户端。因为 HttpURLConnection 缺少对 HTTP 连接池的支持，所以性能较低，在并发到达一定量级后基本会出现。
 * <p>
 * 因此 Feign 提供了另外两个 HTTP 客户端：
 * <p>
 * ApacheHttpClient，基于 Apache HttpClient 封装
 * OkHttpClient，基于 OkHttp 封装
 * <p>
 * ① 在 HttpClientFeignLoadBalancedConfiguration 配置类中打个断点，用于确认是否创建了 ApacheHttpClient 对象。
 * ② 使用 Debug 来启动 DemoConsumerApplication 服务消费者。会进入 HttpClientFeignLoadBalancedConfiguration 打的断点
 */
@SpringBootApplication
@EnableFeignClients
public class DemoConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoConsumerApplication.class, args);
    }

}