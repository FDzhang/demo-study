package cn.seasun.dubboconsumerdemo.controller;

import cn.seasun.dubboapidemo.dto.UserAddDTO;
import cn.seasun.dubboapidemo.dto.UserDTO;
import cn.seasun.dubboconsumerdemo.feign.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhangxinqiang
 * @create 2022/3/8 14:23
 *
 * ③ 因为底层实际使用 Dubbo 来调用服务，所以在使用 dubbo:// 协议时，
 * 仍需要引入 labx-07-sca-dubbo-demo02-api 依赖，避免反序列化报错。
 */
@RestController
@RequestMapping("/user01")
public class User01Controller {


    @Autowired
    private UserFeignClient userFeignClient;

    @GetMapping("/get")
    public UserDTO get(@RequestParam("id") Integer id) {
        return userFeignClient.get(id);
    }

    @PostMapping("/add")
    public Integer add(UserAddDTO addDTO) {
        return userFeignClient.add(addDTO);
    }

}
