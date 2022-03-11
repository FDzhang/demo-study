package cn.seasun.nacosconfigdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author : zxq
 * @create : 2022/3/10 20:01
 *
 * 在上面的示例中，我们已经实现从 Nacos 读取配置。那么，在应用已经启动的情况下，如果我们将读取的 Nacos 的配置进行修改时，应用是否会自动刷新本地的配置呢？
 *
 * 如果是我们上面的示例，答案是部分会。使用 @ConfigurationProperties 注解的会，使用 @Value 注解的不会。
 *
 * 使用 @ConfigurationProperties 注解的成功刷新，使用 @Value 注解的失败刷新。
 */
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class);
    }

}