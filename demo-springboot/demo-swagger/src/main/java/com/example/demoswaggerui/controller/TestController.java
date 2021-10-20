package com.example.demoswaggerui.controller;

import com.example.demoswaggerui.model.User;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

/**
 * @author ：zxq
 * @date ：Created in 2020/7/13 19:51
 */

@RestController
@RequestMapping
//Api注解，描述信息 可通过tag进行分类
@Api(tags = "TestController")
public class TestController {

    //方法描述
    @ApiOperation(notes = "post方法", value = "put_user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok", response = User.class)
    })
    @PostMapping("/put_user")
    public User putUser(@RequestBody User user) {
        System.out.println(user);
        return user;
    }
}
