package cn.seasun.nacosconfigdemo.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;

/**
 * @author : zxq
 * @create : 2022/3/10 22:19
 *
 * 补充：最近看了下文档，可以通过 @RefreshScope 注解，实现数据源的动态变化，
 * 具体可以看看《SpringCloud 运行时刷新数据源相关配置》文章。也就是说，不一定需要通过实现对 EnvironmentChangeEvent 事件的监听处理。
 * https://www.iocoder.cn/Fight/The-SpringCloud-runtime-refreshes-the-datasource-related-configuration/?self
 *
 *
 * 通过 @ConfigurationProperties 或者 @Value + @RefreshScope 注解，已经能够满足我们绝大多数场景下的自动刷新配置的功能。
 * 但是，在一些场景下，我们仍然需要实现对配置的监听，执行自定义的逻辑。
 * 例如说，当数据库连接的配置发生变更时，我们需要通过监听该配置的变更，重新初始化应用中的数据库连接，从而访问到新的数据库地址。
 * 又例如说，当日志级别发生变更时，我们需要通过监听该配置的变更，设置应用中的 Logger 的日志级别，从而后续的日志打印可以根据新的日志级别。
 *
 * 在 Spring Cloud 中，在 Environment 的属性配置发生变化时，会发布 EnvironmentChangeEvent 事件。
 * 这样，我们只需要实现 EnvironmentChangeEvent 事件的监听器，就可以进行自定义的逻辑处理。
 * 例如说，Spring Cloud 内置了 LoggingRebinder 监听器，实现了对 EnvironmentChangeEvent 事件的监听，
 * 在发现 logging.level 配置项发生变化时，重新设置日志组件的日志级别。如下图所示：
 */
@Component
public class DemoEnvironmentChangeListener implements ApplicationListener<EnvironmentChangeEvent> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ConfigurableEnvironment environment;

    @Override
    public void onApplicationEvent(EnvironmentChangeEvent event) {
        for (String key : event.getKeys()) {
            logger.info("[onApplicationEvent][key({}) 最新 value 为 {}]", key, environment.getProperty(key));
        }
    }

}