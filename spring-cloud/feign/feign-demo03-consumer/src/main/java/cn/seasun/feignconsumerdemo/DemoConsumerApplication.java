package cn.seasun.feignconsumerdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author : zxq
 * @create : 2022/3/2 22:08
 * <p>
 * 4.5 实践建议
 * 在 Spring Cloud OpenFeign 官方文档有这么一段话：
 * FROM Feign Inheritance Support
 * It is generally not advisable to share an interface between a server and a client.
 * It introduces tight coupling, and also actually doesn’t work with Spring MVC in its current form
 * (method parameter mapping is not inherited).
 * <p>
 * 意思是不推荐使用继承特性，因为通过 Java 接口的共享，导致服务提供者和消费者的耦合，
 * 而微服务的目的是为了服务提供者和消费者的解耦，存在一定的冲突。
 * <p>
 * 不过实际场景下，蛮多公司采用继承特性，显而易见的好处，可以方便服务消费者的快速接入，基本无需编写额外的代码。
 * 具体怎么选择，胖友可以自己进行评估，看看使用继承特性的情况下，在享受优点的同时，是否能够接受带来的缺点。
 * <p>
 * 艿艿个人意见的话，是支持采用继承特性。
 * 从 Dubbo 的使用方式来说，也可以认为它是支持采用继承特性
 */
@SpringBootApplication
@EnableFeignClients
public class DemoConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoConsumerApplication.class, args);
    }

}