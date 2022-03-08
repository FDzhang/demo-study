package cn.seasun.dubboproviderdemo.service;

import cn.seasun.dubboapidemo.api.UserService;
import cn.seasun.dubboapidemo.dto.UserAddDTO;
import cn.seasun.dubboapidemo.dto.UserDTO;
import org.apache.dubbo.config.annotation.DubboService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

/**
 * @author zhangxinqiang
 * @create 2022/3/4 13:25
 * <p>
 * ① 在 @Service 注解的 protocol 属性，增加 rest 来声明暴露 rest:// 协议的服务。
 * <p>
 * ② 在类和方法上，增加了 @Path、@GET、@POST、@Produces、@Consumes、@QueryParam 等等 JAX-RS 定义的注解，
 * 因为 Dubbo rest:// 协议是基于标准的 Java REST API —— JAX-RS 2.0（Java API for RESTful Web Services 的简写）来实现的。
 */
@DubboService(protocol = "dubbo", version = "1.0.0")
@Path("/user")
public class UserServiceImpl implements UserService {

    @Override
    @GET
    @Path("/get")
    @Produces(APPLICATION_JSON_VALUE)
    public UserDTO get(@QueryParam("id") Integer id) {
        return new UserDTO().setId(id)
                .setName("没有昵称：" + id)
                .setGender(id % 2 + 1); // 1 - 男；2 - 女
    }

    @Override
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Integer add(UserAddDTO addDTO) {
        return (int) (System.currentTimeMillis() / 1000); // 嘿嘿，随便返回一个 id
    }

}
