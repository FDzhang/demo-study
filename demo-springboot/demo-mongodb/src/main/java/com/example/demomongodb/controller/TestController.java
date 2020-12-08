package com.example.demomongodb.controller;

import cn.hutool.core.date.DateUtil;
import com.example.demomongodb.model.Cat;
import com.example.demomongodb.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author ：zxq
 * @date ：Created in 2020/5/7 14:52
 */

@RestController
public class TestController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("/get")
    public List<User> test(User user) {
        user.setCreateTime(new Date());
        // 查找条件
        Query query = createQuery(user);
        // 查找
        List<User> users = mongoTemplate.find(query, User.class, "User");

        return users;
    }

    @GetMapping("/cat")
    public List<Cat> test() {
        Cat cat = new Cat();

        cat.setBirthDay(new Date());
        // 查找条件
        Query query = createCatQuery(cat);
        // 查找
        List<Cat> cats = mongoTemplate.find(query, Cat.class, "cat");

        return cats;
    }

    @PostMapping("/add")
    public String testAdd() {
        User user = new User();
        user.setAge(1);
        user.setName("23123");
        user.setCreateTime(new Date());
        if (user != null) {
            // 添加
            mongoTemplate.insert(user, "User");
            return "success";
        }
        return "参数不能为空";
    }

    @PostMapping("/addCat")
    public String addCat() {
        Cat cat = new Cat();
        cat.setName("2号");
        cat.setAge(3);
//        cat.setBirthDay(new Date(1604160000000L));
        cat.setBirthDay(new Date());
        mongoTemplate.insert(cat, "cat");
        return "success";
    }


    private Query createCatQuery(Cat cat) {
        Query query = new Query();
        query.addCriteria(Criteria.where("birthDay")
                .gte(DateUtil.beginOfMonth(cat.getBirthDay()))
                .lte(DateUtil.endOfMonth(cat.getBirthDay())));
        return query;
    }

    private Query createQuery(User user) {
        Query query = new Query();
        if (user != null) {
            if (!StringUtils.isEmpty(user.getName())) {
                query.addCriteria(Criteria.where("name").is(user.getName()));
            }
        }
        query.addCriteria(Criteria.where("create_time")
                .gte(DateUtil.beginOfMonth(user.getCreateTime()))
                .lte(DateUtil.endOfMonth(user.getCreateTime())));
        return query;
    }
}
