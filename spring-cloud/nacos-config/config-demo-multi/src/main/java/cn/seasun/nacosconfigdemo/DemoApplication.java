package cn.seasun.nacosconfigdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

/**
 * @author : zxq
 * @create : 2022/3/10 20:01
 *
 * 7. 配置加载顺序
 * 示例代码对应仓库：labx-05-sca-nacos-config-demo-multi。
 *
 * Nacos Config 提供了三种配置 Nacos 配置集的方式：
 *
 * A：通过 spring.cloud.nacos.config.shared-configs 配置项，支持多个共享 Nacos 配置集。
 * B：通过 spring.cloud.nacos.config.extension-configs 配置项，支持多个拓展 Nacos 配置集。
 * C：通过 spring.cloud.nacos.config.name 配置项，支持一个 Nacos 配置集。
 * 当三种方式共同使用时，它们的优先级关系是：A < B < C。另外，A 和 B 的命名带有“共享”或是“拓展”，没有任何含义，只是优先级不同。
 *
 * 下面，我们来创建一个 labx-05-sca-nacos-config-demo-multi 示例项目，搭建同时使用三种配置 Nacos 配置集的方式的示例。最终项目代码如下图所示：
 *
 *
 * ① 在 spring.cloud.nacos.config 配置项下，同时使用三种配置 Nacos 配置集的方式。
 *
 * ② 在 spring.profiles.active 配置项下，设置为 dev，用于测试结合 Profiles 的情况。
 */
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        // 启动 Spring Boot 应用
        ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);

        // 查看 Environment
        Environment environment = context.getEnvironment();
        System.out.println(environment);
    }

}