package cn.seasun.feignconsumerdemo.config;


import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author zhangxinqiang
 * @create 2022/3/3 16:54
 * <p>
 * <p>
 * ① 对于 DefaultFeignClientConfiguration 和 DemoProviderFeignClientConfiguration 两个配置类，我们并没有添加 @Configuration 注解。
 * <p>
 * 因为，Spring Boot 项目默认扫描 DemoConsumerApplication 所在包以及子包下的所有 Bean 们。
 * 而 @Configuration 注解也是一种 Bean，也会被扫描到。
 * <p>
 * 如果添加 @Configuration 注解到 DefaultFeignClientConfiguration 和 DemoProviderFeignClientConfiguration 上，
 * 将会被 Spring Boot 所扫描到，导致整个项目的 Feign 客户端都使用相同的 Feign 配置，就无法到达 Feign 客户端级别的自定义配置的目的。
 * <p>
 * 因此，我们没有给 DefaultFeignClientConfiguration 和 DemoProviderFeignClientConfiguration 添加 @Configuration 注解。
 * <p>
 * 友情提示，可以不看。
 * 当然，如果胖友想要添加 @Configuration 注解到 DefaultFeignClientConfiguration 和 DemoProviderFeignClientConfiguration
 * 上的话，还有一个不是很推荐的方案，将 DefaultFeignClientConfiguration 和 DemoProviderFeignClientConfiguration
 * 移到和 DemoConsumerApplication 不同包中，避免被 Spring Boot 所扫描到。
 * 例如说，在根路径下创建 feign 包，并将 DefaultFeignClientConfiguration 和 DemoProviderFeignClientConfiguration 放入其中。
 * <p>
 * ② 为了避免多个 Feign 客户端级别的配置类创建的 Bean 之间互相冲突，Spring Cloud OpenFeign 通过 FeignContext 类，
 * 为每一个 Feign 客户端创建一个 Spring 子上下文。在 Spring Cloud OpenFeign 的设计中，
 * Spring 的上下文设计特别有趣，胖友可以先暂时记住：
 * 全局级别的 FeignClient 配置类是在 Spring 父上下文生效
 * 客户端级别的 FeignClient 配置类在 Spring 子上下文生效。
 * 不过这里要注意，因为 DefaultFeignClientConfiguration 和 DemoProviderFeignClientConfiguration
 * 都创建了 Logger.Level Bean，而 DefaultFeignClientConfiguration 是在 Spring 父上下文生效，
 * 会和 DemoProviderFeignClientConfiguration 所在的 Spring 子上下文共享。
 * <p>
 * 这样就导致从 Spring 获取 Logger.Level Bean 时，存在两个而不知道选择哪一个。
 * 因此，我们声明 DefaultFeignClientConfiguration 创建的 Logger.Level Bean 为 @Primary，优先使用它。
 * <p>
 * 友情提示：这里会有一点点绕，胖友好好理解哈~
 * <p>
 * <p>
 * 3.2.6 更多配置 Bean
 * 通过 FeignClientsConfiguration 配置类，我们可以看到 Spring JavaConfig 所支持的 FeignClient 的所有 Bean。例如说：
 * <p>
 * // FeignClientsConfiguration.java
 * @Bean
 * @ConditionalOnMissingBean public Contract feignContract(ConversionService feignConversionService) {
 * // 创建 SpringMvcContract 类，支持 SpringMVC 注解。
 * return new SpringMvcContract(this.parameterProcessors, feignConversionService);
 * }
 * <p>
 * // ... 省略其他的
 * 因为 FeignClientsConfiguration 创建的 Bean 基本都有 @ConditionalOnMissingBean 条件注解，所以我们可以通过 Spring JavaConfig 自定义。
 * <p>
 * <p>
 * <p>
 * 3.3 实践建议
 * 对于 Feign 自定义配置，推荐使用配置文件的方式，简单方便好管理。在配置文件的方式无法满足的情况下，
 * 使用 Spring JavaConfig 的方式作为补充。不过绝大多数场景下，都基本不需要哈~
 * 配置文件方式的优先级高于 Spring JavaConfig 方式，客户端级别的优先级高于全局级别
 */

/**
 * 服务 demo-provider 的 FeignClient 配置类
 */
@Configuration
public class DemoProviderFeignClientConfiguration {

    @Bean
    @Primary // 主 Bean
    public Logger.Level feignClientLoggerLevel() {
        return Logger.Level.FULL;
    }

}
