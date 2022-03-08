package cn.seasun.dubboproviderdemo.service;

import cn.seasun.dubboapidemo.api.UserService;
import cn.seasun.dubboapidemo.dto.UserAddDTO;
import cn.seasun.dubboapidemo.dto.UserDTO;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author zhangxinqiang
 * @create 2022/3/4 13:25
 */
@DubboService(protocol = "dubbo", version = "1.0.0")
public class UserServiceImpl implements UserService {

    @Override
    public UserDTO get(Integer id) {
        return new UserDTO()
                .setId(id)
                .setName("没有昵称：" + id)
                .setGender(id % 2 + 1); // 1 - 男；2 - 女
    }

    @Override
    public Integer add(UserAddDTO addDTO) {
        return (int) (System.currentTimeMillis() / 1000); // 嘿嘿，随便返回一个 id
    }

}
