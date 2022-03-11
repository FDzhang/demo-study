package cn.seasun.nacosconfigdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author : zxq
 * @create : 2022/3/10 20:01
 * <p>
 * 简单测试
 * 下面，我们使用命令行参数进行 --spring.profiles.active 配置项，实现不同环境，读取不同配置文件。
 * 经过测试：
 * 使用命令行参数进行 --spring.profiles.active 配置，对 bootstrap.yaml 配置文件无效。
 * 使用 VM 参数进行 -Dspring.profiles.active 配置爱，对 bootstrap.yaml 配置文件有效。
 * 具体的原因还不知道，先暂时这么解决哈~
 * ① 开发环境示例：直接在 IDEA 中，增加 -Dspring.profiles.active=dev 到 VM options 中。如下图所示：
 *
 *
 *
 * 除了通过 Nacos namespace 命名空间的方案之外，我们还有两种可以实现多环境配置：
 *
 * ① 通过配置集的 dataId 来实现。例如说：开发环境使用 Nacos 配置集的 dataId 为 {applicationName}-dev，
 * 生产环境使用 Nacos 配置集的 dataId 为 {applicationName}-prod。
 *
 * 因为 Spring Cloud Alibaba Nacos Config 提供的 Profiles 功能，内置就提供在配置的 Nacos 配置集的 dataId 拼接 -{profile}，所以还是比较方便的。
 *
 * ② 通过配置集的 group 分组来实现。例如说：开发环境使用配置集的 group 为 DEV_GROUP，生产环境使用配置集的 group 为 PPROD_GROUP。
 *
 * 📚 怎么选？
 *
 * 相比来说，最最最推荐采用基于 namespace 的方案，因为官方推荐，同时未来 Nacos 的权限控制是按照 namespace 级别进行控制，
 * 可以更好的结合，毕竟不是人人都有生产环境的配置管理的权限。
 *
 * 次之推荐采用基于 dataId 的方案，因为 Spring Cloud Alibaba Nacos Config 提供了良好的支持，Spring Cloud Config 也是采用类似方案。
 *
 * 最最不推荐采用 group 的方案，毕竟 group 的定位，是将相同的应用或组件的配置集进行分组。
 * 例如说，group 可以有 DATABASE_GROUP、MQ_GROUP、RPC_GROUP 等等。
 */
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class);
    }

}