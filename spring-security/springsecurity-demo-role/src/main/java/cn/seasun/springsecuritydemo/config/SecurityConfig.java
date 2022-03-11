package cn.seasun.springsecuritydemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

/**
 * @author zhangxinqiang
 * @create 2022/3/11 18:33
 *
 * 创建 SecurityConfig 配置类，继承 WebSecurityConfigurerAdapter 抽象类，实现 Spring Security 在 Web 场景下的自定义配置。代码如下：
 * 我们可以通过重写 WebSecurityConfigurerAdapter 的方法，实现自定义的 Spring Security 的配置。
 *
 *
 * <X> 处，调用 HttpSecurity#authorizeRequests() 方法，开始配置 URL 的权限控制。注意看艿艿配置的四个权限控制的配置。下面，是配置权限控制会使用到的方法：
 *
 * #(String... antPatterns) 方法，配置匹配的 URL 地址，基于 Ant 风格路径表达式 ，可传入多个。
 * 【常用】#permitAll() 方法，所有用户可访问。
 * 【常用】#denyAll() 方法，所有用户不可访问。
 * 【常用】#authenticated() 方法，登录用户可访问。
 * #anonymous() 方法，无需登录，即匿名用户可访问。
 * #rememberMe() 方法，通过 remember me 登录的用户可访问。
 * #fullyAuthenticated() 方法，非 remember me 登录的用户可访问。
 * #hasIpAddress(String ipaddressExpression) 方法，来自指定 IP 表达式的用户可访问。
 * 【常用】#hasRole(String role) 方法， 拥有指定角色的用户可访问。
 * 【常用】#hasAnyRole(String... roles) 方法，拥有指定任一角色的用户可访问。
 * 【常用】#hasAuthority(String authority) 方法，拥有指定权限(authority)的用户可访问。
 * 【常用】#hasAuthority(String... authorities) 方法，拥有指定任一权限(authority)的用户可访问。
 * 【最牛】#access(String attribute) 方法，当 Spring EL 表达式的执行结果为 true 时，可以访问。
 * <Y> 处，调用 HttpSecurity#formLogin() 方法，设置 Form 表单登录。
 *
 * 如果胖友想要自定义登录页面，可以通过 #loginPage(String loginPage) 方法，来进行设置。不过这里我们希望像「2. 快速入门」一样，使用默认的登录界面，所以不进行设置。
 * <Z> 处，调用 HttpSecurity#logout() 方法，配置退出相关。
 * 如果胖友想要自定义退出页面，可以通过 #logoutUrl(String logoutUrl) 方法，来进行设置。不过这里我们希望像「2. 快速入门」一样，使用默认的退出界面，所以不进行设置。
 *
 *
 * 修改 SecurityConfig 配置类，增加 @EnableGlobalMethodSecurity 注解，开启对 Spring Security 注解的方法，进行权限验证。代码如下：
 *
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 配置请求地址的权限
                .authorizeRequests()
                .antMatchers("/test/demo").permitAll() // 所有用户可访问
                .antMatchers("/test/admin").hasRole("ADMIN") // 需要 ADMIN 角色
                .antMatchers("/test/normal").access("hasRole('ROLE_NORMAL')") // 需要 NORMAL 角色。
                // 任何请求，访问的用户都需要经过认证
                .anyRequest().authenticated()
                .and()
                // 设置 Form 表单登陆
                .formLogin()
//                    .loginPage("/login") // 登陆 URL 地址
                .permitAll() // 所有用户可访问
                .and()
                // 配置退出相关
                .logout()
//                    .logoutUrl("/logout") // 退出 URL 地址
                .permitAll(); // 所有用户可访问
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                // 使用内存中的 InMemoryUserDetailsManager
                        inMemoryAuthentication()
                // 不使用 PasswordEncoder 密码编码器
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                // 配置 admin 用户
                .withUser("admin").password("admin").roles("ADMIN")
                // 配置 normal 用户
                .and().withUser("normal").password("normal").roles("NORMAL");
    }

}
