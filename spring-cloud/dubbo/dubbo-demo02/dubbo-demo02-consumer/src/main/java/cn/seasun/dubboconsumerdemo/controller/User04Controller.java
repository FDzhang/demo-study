package cn.seasun.dubboconsumerdemo.controller;

import cn.seasun.dubboapidemo.api.UserService;
import cn.seasun.dubboapidemo.dto.UserAddDTO;
import cn.seasun.dubboapidemo.dto.UserDTO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhangxinqiang
 * @create 2022/3/8 14:28
 *
 * 服务调用方式四：Dubbo
 *
 * 该方式是 Spring Cloud Alibaba Dubbo 开发者最推荐的方式，没有之一。所以说，上述的方式一、二、三都是过渡方案，嘿嘿。
 */
@RestController
@RequestMapping("/user04")
public class User04Controller {

    @DubboReference(version = "1.0.0", protocol = "dubbo")
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
