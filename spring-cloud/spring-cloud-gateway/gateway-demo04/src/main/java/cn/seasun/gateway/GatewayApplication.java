package cn.seasun.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhangxinqiang
 * @create 2022/3/29 16:39
 * <p>
 * 灰度发布（又名金丝雀发布）是指在黑与白之间，能够平滑过渡的一种发布方式。
 * <p>
 * 在其上可以进行 A/B testing，即让一部分用户继续用产品特性
 * A，一部分用户开始用产品特性 B，如果用户对 B 没有什么反对意见，那么逐步扩大范围，把所有用户都迁移到 B 上面来。
 * 灰度发布可以保证整体系统的稳定，在初始灰度的时候就可以发现、调整问题，以保证其影响度。
 */
@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

}