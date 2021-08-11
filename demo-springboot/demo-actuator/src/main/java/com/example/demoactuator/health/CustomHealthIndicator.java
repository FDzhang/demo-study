package com.example.demoactuator.health;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

/**
 * 除了使用自动引入的健康指示器之外，我们也可以自定义一个 Health Indicator，只需要实现 HealthIndicator 接口或者继承 AbstractHealthIndicator 类。
 * 例如下面我们创建了一个 CustomHealthIndicator 类，继承了 AbstractHealthIndicator 类，并返回了一些健康信息。
 * 我们重启应用并访问地址：localhost:8080/actuator/health，我们可以看到自定义的健康信息。
 *
 * @author zhangxinqiang
 * @create 2021/8/11 14:09
 */
@Component
public class CustomHealthIndicator extends AbstractHealthIndicator {
    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        // 使用 builder 来创建健康状态信息
        // 如果你throw录 了一个 exception，那么status 就会被置为DOWN，异常信息会被记下来
        builder.up()
                .withDetail("app", "xx向你报告：项目很健康哦！")
                .withDetail("error", "xx向你报告：项目有点问题哦！");
    }
}