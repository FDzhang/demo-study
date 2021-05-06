package com.example.demosqlserver.controller;

import com.example.demosqlserver.bean.User;
import com.example.demosqlserver.bean.Vo;
import com.example.demosqlserver.mapper.UserMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private UserMapper userMapper;

    @GetMapping()
    public List<Vo> getUser(){
        return userMapper.selectList(null);
    }

    @GetMapping("find")
    public User getUser(String name){
        return userMapper.getUser(name);
    }
}
