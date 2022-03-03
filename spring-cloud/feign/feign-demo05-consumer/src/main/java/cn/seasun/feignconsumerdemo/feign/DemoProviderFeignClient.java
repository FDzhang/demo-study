package cn.seasun.feignconsumerdemo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author : zxq
 * @create : 2022/3/2 22:10
 *
 *
 *  Feign 单独使用
 * 示例代码对应仓库：
 *
 * 在使用 Spring Cloud 的项目中，我们大多数是通过 Feign 调用从 Ribbon 负载均衡选择的服务实例，
 * 而 Ribbon 是通过注册中心获取到的服务实例列表。但是有些场景下，可能想要单独使用 Feign 调用，例如说：
 *
 * 调用第三方服务，例如说短信云服务、推送云服务
 * 调用的虽然是内部服务，但是并没有注册到注册中心，而是使用 Nginx 代理并负载均衡实现高可用
 * 下面，让我们来搭建下 Feign 单独使用的示例。
 *
 * 将 @FeignClient 注解的 url 属性设置要调用的服务的地址。
 * 不过要注意，保持 name 属性和 url 属性的 host 是一致的，不然还是会使用 Ribbon 进行负载均衡。
 */
//@FeignClient(name = "demo-provider")
@FeignClient(name = "baidu", url = "https://www.baidu.com/") // 注意，保持 name 属性和 url 属性的 host 是一致的。
public interface DemoProviderFeignClient {

//    @GetMapping("/echo")
//    String echo(@RequestParam("name") String name);

    @GetMapping("/") // 请求首页
    String echo(@RequestParam("name") String name);

}