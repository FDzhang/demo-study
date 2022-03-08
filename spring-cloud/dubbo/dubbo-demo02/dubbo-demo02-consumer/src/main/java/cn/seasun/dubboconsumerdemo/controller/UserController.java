package cn.seasun.dubboconsumerdemo.controller;

import cn.seasun.dubboapidemo.api.UserService;
import cn.seasun.dubboapidemo.dto.UserAddDTO;
import cn.seasun.dubboapidemo.dto.UserDTO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhangxinqiang
 * @create 2022/3/4 13:38
 */
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * protocol 注解，设置使用的 Protocol Bean 的名字。
     * version 属性，服务的版本号。
     */
    @DubboReference(protocol = "dubbo", version = "1.0.0")
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
