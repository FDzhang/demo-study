package cn.seasun.feignconsumerdemo.feign;

import cn.seasun.feignconsumerdemo.dto.DemoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author : zxq
 * @create : 2022/3/2 22:10
 * <p>
 * <p>
 * <p>
 * 📚 GET 场景
 * <p>
 * ①【最推荐】方式一，采用 Spring Cloud OpenFeign 提供的 @SpringQueryMap 注解，并使用 DemoDTO 对象。
 * <p>
 * 默认情况下，Feign 针对 POJO 类型的参数，即使我们声明为 GET 类型的请求，也会自动转换成 POST 类型的请求。
 * 如果我们去掉 @SpringQueryMap 注解，就会报如下异常：
 * <p>
 * feign.FeignException$MethodNotAllowed: status 405 reading DemoProviderFeignClient#getDemo(DemoDTO)
 * Feign 自动转换成了 POST /get_demo 请求，而服务提供者提供的 /get_demo 只支持 GET 类型，因此返回响应状态码为 405 的错误。
 * @SpringQueryMap 注解的作用，相当于 Feign 的 @QueryMap 注解，将 POJO 对象转换成 QueryString。
 * <p>
 * ②【较推荐】方式二，采用 SpringMVC 提供的 @RequestParam 注解，并将所有参数平铺开。
 * <p>
 * 参数较少的时候，可以采用这种方式。如果参数过多的话，还是采用方式一更优。
 * <p>
 * ③【不推荐】方式三，采用 SpringMVC 提供的 @RequestParam 注解，并使用 Map 对象。非常不推荐，因为可读性差，都不知道传递什么参数。
 * <p>
 * 📚 POST 场景
 * <p>
 * ① 唯一方式，采用 SpringMVC 提供的 @RequestBody 注解，并使用 DemoDTO 对象。
 */
@FeignClient(name = "demo-provider")
public interface DemoProviderFeignClient {

    @GetMapping("/echo")
    String echo(@RequestParam("name") String name);

    @GetMapping("/get_demo")
        // GET 方式一，最推荐
    DemoDTO getDemo(@SpringQueryMap DemoDTO demoDTO);

    @GetMapping("/get_demo")
        // GET 方式二，相对推荐
    DemoDTO getDemo(@RequestParam("username") String username, @RequestParam("password") String password);

    @GetMapping("/get_demo")
        // GET 方式三，不推荐
    DemoDTO getDemo(@RequestParam Map<String, Object> params);

    @PostMapping("/post_demo")
        // POST 方式
    DemoDTO postDemo(@RequestBody DemoDTO demoDTO);
}
