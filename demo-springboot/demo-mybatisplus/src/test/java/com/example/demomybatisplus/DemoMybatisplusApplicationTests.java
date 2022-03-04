//package com.example.demomybatisplus;
//
//import com.alibaba.fastjson.JSON;
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.example.demomybatisplus.bean.User;
//import com.example.demomybatisplus.mapper.UserMapper;
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import javax.annotation.Resource;
//import java.util.List;
//
//@SpringBootTest
//class DemoMybatisplusApplicationTests {
//
//    @Test
//    void contextLoads() {
//    }
//
//    @Resource
//    private UserMapper mapper;
//
//    @Test
//    void test(){
//        PageHelper.startPage(1,2);
//        QueryWrapper<User> queryWrapper=  new QueryWrapper<>();
//        queryWrapper.select("name");
//        queryWrapper.eq("name","test");
//        queryWrapper.orderByDesc("age");
//        List<User> users = mapper.selectList(queryWrapper);
//        PageInfo<User> pageInfo = new PageInfo<>(users);
//
//        System.out.println(JSON.toJSONString(pageInfo));
//    }
//}
