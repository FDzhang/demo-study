package com.example.demoswaggerui.controller;

import com.example.demoswaggerui.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：zxq
 * @date ：Created in 2020/7/13 19:51
 */

@RestController
@RequestMapping
//Api注解，描述信息 可通过tag进行分类
@Api(value = "TestController")
public class TestController {

//    //方法描述
//    @ApiOperation(notes = "get方法", value = "test3")
//    @GetMapping("/test")
//    public String test1(@ApiParam(name = "test", value = "测试") String test,
//                        @ApiParam(name = "id", value = "id") Integer id) {
//        return "success:" + test + id;
//    }

    //方法描述
    @ApiOperation(notes = "post方法", value = "putUser")
    @PostMapping("/putUser")
    public User test9(@ApiParam(name = "User", value = "用户") User user) {
        System.out.println(user);
        return user;
    }
}
