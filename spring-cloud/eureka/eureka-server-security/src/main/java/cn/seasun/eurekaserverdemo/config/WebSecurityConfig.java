package cn.seasun.eurekaserverdemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author zhangxinqiang
 * @create 2022/2/25 16:03
 *
 * 默认配置下，Spring Security 要求每个請求需要携带 CSRF Token 才可以访问，
 * 而 Eureka-Client 是不会进行携带的，因此需要针对 /eureka/** 路径进行禁用，不然就要改 Eureka-Client 的源码啦~~~
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 设置 Eureka-Server 提供的 /eureka/** 无需传递 CSRF Token，
        http.csrf().ignoringAntMatchers("/eureka/**");
        super.configure(http);
    }

}