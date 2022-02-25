package cn.seasun.eurekaserverdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author zhangxinqiang
 * @create 2022/2/25 14:39
 * <p>
 * <p>
 * 在类上添加 @EnableEurekaServer 注解，声明启动 Eureka-Server 服务。
 * <p>
 * 简单测试
 * 我们执行 EurekaServerApplication 来启动注册中心。此时，我们在控制台打印 Eureka 相关的日志如下：
 * e.s.EurekaServerInitializerConfiguration : Started Eureka Server
 * <p>
 * 使用浏览器，访问 http://127.0.0.1:8761 地址，可以查看到 Eureka-Server 运维界面。
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }

}
