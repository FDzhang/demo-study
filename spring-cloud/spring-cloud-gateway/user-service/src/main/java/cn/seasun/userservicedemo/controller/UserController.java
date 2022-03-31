package cn.seasun.userservicedemo.controller;

import cn.seasun.userservicedemo.dto.UserAddDTO;
import cn.seasun.userservicedemo.dto.UserDTO;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhangxinqiang
 * @create 2022/3/29 18:13
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/get")
    public UserDTO get(@RequestParam("id") Integer id) {
        System.err.println("ccurrent " + System.currentTimeMillis() / 1000);
        return new UserDTO().setId(id)
                .setName("没有昵称：" + id)
                .setGender(id % 2 + 1); // 1 - 男；2 - 女
    }

    @PostMapping("/add")
    public Integer add(UserAddDTO addDTO) {
        System.err.println("ccurrent " + System.currentTimeMillis() / 1000);
        return (int) (System.currentTimeMillis() / 1000); // 嘿嘿，随便返回一个 id
    }

}