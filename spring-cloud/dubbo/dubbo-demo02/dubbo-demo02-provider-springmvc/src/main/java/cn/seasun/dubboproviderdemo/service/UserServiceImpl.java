package cn.seasun.dubboproviderdemo.service;

import cn.seasun.dubboapidemo.api.UserService;
import cn.seasun.dubboapidemo.dto.UserAddDTO;
import cn.seasun.dubboapidemo.dto.UserDTO;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangxinqiang
 * @create 2022/3/4 13:25
 */
@DubboService(protocol = "dubbo", version = "1.0.0")
@RestController
@RequestMapping("/user")
public class UserServiceImpl implements UserService {

    @Override
    @GetMapping("/get")
    public UserDTO get(Integer id) {
        return new UserDTO()
                .setId(id)
                .setName("没有昵称：" + id)
                .setGender(id % 2 + 1); // 1 - 男；2 - 女
    }

    @Override
    @PostMapping("/add")
    public Integer add(UserAddDTO addDTO) {
        return (int) (System.currentTimeMillis() / 1000); // 嘿嘿，随便返回一个 id
    }

}
