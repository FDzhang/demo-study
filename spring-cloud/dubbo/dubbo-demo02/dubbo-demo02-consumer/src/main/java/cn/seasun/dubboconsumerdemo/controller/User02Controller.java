package cn.seasun.dubboconsumerdemo.controller;

import cn.seasun.dubboconsumerdemo.dto.UserAddDTO;
import cn.seasun.dubboconsumerdemo.dto.UserDTO;
import cn.seasun.dubboconsumerdemo.feign.UserFeignClient02;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhangxinqiang
 * @create 2022/3/8 14:38
 */
@RestController
@RequestMapping("/user02")
public class User02Controller {

    @Autowired
    private UserFeignClient02 userFeignClient;

    @GetMapping("/get")
    public UserDTO get(@RequestParam("id") Integer id) {
        return userFeignClient.get(id);
    }

    @PostMapping("/add")
    public Integer add(UserAddDTO addDTO) {
        return userFeignClient.add(addDTO);
    }

}