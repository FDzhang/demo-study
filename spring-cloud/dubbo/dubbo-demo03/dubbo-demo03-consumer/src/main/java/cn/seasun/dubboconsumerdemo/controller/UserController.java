package cn.seasun.dubboconsumerdemo.controller;

import cn.seasun.dubboapidemo.api.UserService;
import cn.seasun.dubboapidemo.dto.UserAddDTO;
import cn.seasun.dubboapidemo.dto.UserDTO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhangxinqiang
 * @create 2022/3/4 13:38
 *
 * 推荐：如果胖友想把 Dubbo 服务消费者的所有 Service 服务的参数校验都开启，
 * 可以修改 application.yaml 配置文件，增加 dubbo.consumer.validation = true 配置。
 *
 * 可能胖友会有疑惑，服务提供者和服务消费者的 validation = true ，都是开启参数校验规则，会有什么区别呢？
 * Dubbo 内置 ValidationFilter 过滤器，实现参数校验的功能，可作用于服务提供者和服务消费者。效果如下：
 *
 * 如果服务消费者开启参数校验，请求参数校验不通过时，结束请求，抛出 ConstraintViolationException 异常。即，不会向服务提供者发起请求。
 * 如果服务提供者开启参数校验，请求参数校验不通过时，结束请求，抛出 ConstraintViolationException 异常。即，不会执行后续的业务逻辑。
 * 实际项目在使用时，至少要开启服务提供者的参数校验功能。
 */
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * protocol 注解，设置使用的 Protocol Bean 的名字。
     * version 属性，服务的版本号。
     */
    @DubboReference(protocol = "dubbo", version = "1.0.0", validation = "true")
    private UserService userService;

    @GetMapping("/get")
    public UserDTO get(@RequestParam("id") Integer id) {
        return userService.get(id);
    }

    @PostMapping("/add")
    public Integer add(UserAddDTO addDTO) {
        return userService.add(addDTO);
    }

}
