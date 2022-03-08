package cn.seasun.dubboproviderdemo.service;

import cn.seasun.dubboapidemo.api.UserService;
import cn.seasun.dubboapidemo.dto.UserAddDTO;
import cn.seasun.dubboapidemo.dto.UserDTO;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author zhangxinqiang
 * @create 2022/3/4 13:25
 *
 * 推荐：如果胖友想把 Dubbo 服务提供者的所有 Service 服务的参数校验都开启，
 * 可以修改 application.yaml 配置文件，增加 dubbo.provider.validation = true 配置。
 */
@DubboService(protocol = "dubbo", version = "1.0.0", validation = "true")
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
