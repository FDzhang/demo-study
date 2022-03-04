//package com.example.demomybatisplus.controller;
//
//import com.alibaba.fastjson.JSON;
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.example.demomybatisplus.bean.User;
//import com.example.demomybatisplus.mapper.UserMapper;
//import com.example.demomybatisplus.service.UserServiceImpl;
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//import java.util.List;
//
///**
// * @author : zxq
// * @date-time : 2021/4/20 23:24
// */
//@RestController
//@RequestMapping
//public class UserController {
//
//    @Resource
//    private UserMapper mapper;
//    @Resource
//    private UserServiceImpl userService;
//
//    @GetMapping("test")
//    public PageInfo<User> getList(Integer page,Integer size){
//        PageHelper.startPage(page,size);
//        QueryWrapper<User> queryWrapper=  new QueryWrapper<>();
//        queryWrapper.eq("name","test");
//        queryWrapper.orderByDesc("age");
//        List<User> users = mapper.selectList(queryWrapper);
//        PageInfo<User> pageInfo = new PageInfo<>(users);
//
//        System.out.println(JSON.toJSONString(pageInfo));
//        return pageInfo;
//    }
//
//    @GetMapping("test1")
//    public PageInfo<User> getList1(Integer page,Integer size){
//        PageHelper.startPage(page,size);
//        QueryWrapper<User> queryWrapper=  new QueryWrapper<>();
//        queryWrapper.eq("name","test");
//        queryWrapper.orderByDesc("age");
//        List<User> users = userService.list(queryWrapper);
//        PageInfo<User> pageInfo = new PageInfo<>(users);
//
//        System.out.println(JSON.toJSONString(pageInfo));
//        return pageInfo;
//    }
//}
