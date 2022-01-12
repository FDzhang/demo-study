package com.example.demojaeger.config;

import io.jaegertracing.internal.MDCScopeManager;
import io.opentracing.contrib.java.spring.jaeger.starter.TracerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangxinqiang
 * @create 2022/1/11 18:02
 */
@Configuration
public class JaegerConfig {

    @Bean
    public TracerBuilderCustomizer mdcBuilderCustomizer() {
        // 1.8新特性，函数式接口
        return builder -> builder.withScopeManager(new MDCScopeManager.Builder().build());
    }
}