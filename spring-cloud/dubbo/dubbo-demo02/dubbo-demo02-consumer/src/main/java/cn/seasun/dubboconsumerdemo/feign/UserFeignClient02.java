package cn.seasun.dubboconsumerdemo.feign;

import cn.seasun.dubboconsumerdemo.dto.UserAddDTO;
import cn.seasun.dubboconsumerdemo.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zhangxinqiang
 * @create 2022/3/8 14:24
 * <p>
 * 服务调用方式二：Feign + Ribbon
 * <p>
 * 标准的 Feign + Ribbon 的组合，最终调用服务的 rest:// 协议或 SpringMVC 提供的 HTTP 接口。
 * <p>
 * ③ 因为是 HTTP 接口的调用，所以实际无需引入 labx-07-sca-dubbo-demo02-api 依赖，
 * 这里使用到的 UserDTO 和 UserAddDTO 类都是在 dto 包下另外创建的。
 */
@FeignClient(name = "demo-provider")
public interface UserFeignClient02 {

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