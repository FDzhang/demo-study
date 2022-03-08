package cn.seasun.dubboconsumerdemo.feign;

import cn.seasun.dubboapidemo.dto.UserAddDTO;
import cn.seasun.dubboapidemo.dto.UserDTO;
import com.alibaba.cloud.dubbo.annotation.DubboTransported;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zhangxinqiang
 * @create 2022/3/8 14:22
 *
 * 服务调用方式一：Feign + Dubbo
 *
 * 比较有趣的是，我们在接口上添加了 Spring Cloud Alibaba Dubbo 定义的 @DubboTransported 注解。通过该注解，
 * 可以将 Feign 客户端的底层实际使用 Dubbo 调用服务，也就是说实际变成了 Dubbo 客户端，仅仅是使用 Feign 来声明服务调用的接口。
 *
 * 友情提示：为什么 Spring Cloud Alibaba Dubbo 定义了 DubboMetadataService 接口提供元数据呢？
 * 就是为了获取远程服务的方法的元数据，从而能够使用 Dubbo 调用远程服务。
 *
 * @DubboTransported 注解的目的，是将原本使用 Feign 进行服务调用的 Spring Cloud 项目，
 * 逐步迁移到使用 Dubbo 进行服务调用。也就是说，如果是新的 Spring Cloud 项目，
 * 就不要使用 @DubboTransported 注解，而是使用 @Reference 注解。感兴趣的胖友，可以看看 ISSUE#602。
 * https://github.com/alibaba/spring-cloud-alibaba/issues/602
 */
@FeignClient(name = "demo-provider")
@DubboTransported(protocol = "dubbo")
// @DubboTransported(protocol = "rest")
public interface UserFeignClient {

    /**
     * 根据指定用户编号，获得用户信息
     *
     * @param id 用户编号
     * @return 用户信息
     */
    @GetMapping("/user/get")
    UserDTO get(@RequestParam("id") Integer id);

    /**
     * 添加新用户，返回新添加的用户编号
     *
     * @param addDTO 添加的用户信息
     * @return 用户编号
     */
    @PostMapping("/user/add")
    Integer add(@RequestBody UserAddDTO addDTO);

}
