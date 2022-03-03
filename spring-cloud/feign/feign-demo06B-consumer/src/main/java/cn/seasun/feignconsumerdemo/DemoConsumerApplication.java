package cn.seasun.feignconsumerdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author : zxq
 * @create : 2022/3/2 22:08
 * <p>
 * 7.2.4 简单测试
 * ① 在 OkHttpFeignLoadBalancedConfiguration 配置类中打个断点，用于确认是否创建了 ApacheHttpClient 对象。
 * <p>
 * ② 使用 Debug 来启动 DemoConsumerApplication 服务消费者。会进入 OkHttpFeignLoadBalancedConfiguration 打的断点，如下图所示
 * <p>
 * <p>
 * 7.3 实践建议
 * OkHttp 和 Apache HttpClient 在性能方面是基本接近的，有资料说 OkHttp 好一些，也有资料说 HttpClient 好一些。艿艿建议的话，按照自己对哪一个更熟悉一点，就选择哪一个。
 * 这里有一篇两者对比的文章《HTTP 客户端连接，选择 HttpClient 还是OkHttp？》，感兴趣的胖友可以阅读一波。
 * <p>
 * 《HTTP 客户端连接，选择 HttpClient 还是OkHttp？》 http://www.iocoder.cn/sFight/HTTP-client-connection-HttpClient-or-OkHttp/?self
 */
@SpringBootApplication
@EnableFeignClients
public class DemoConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoConsumerApplication.class, args);
    }

}