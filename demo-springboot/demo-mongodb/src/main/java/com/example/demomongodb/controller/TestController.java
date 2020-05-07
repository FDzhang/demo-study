package com.example.demomongodb.controller;

import com.example.demomongodb.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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
        // 查找条件
        Query query = createQuery(user);
        // 查找
        List<User> users = mongoTemplate.find(query, User.class, "User");

        return users;
    }

    @PostMapping("/add")
    public String testAdd(User user) {
        if (user != null) {
            // 添加
            mongoTemplate.insert(user, "User");
            return "success";
        }
        return "参数不能为空";
    }

    private Query createQuery(User user) {
        Query query = new Query();
        if (user != null) {
            if (!StringUtils.isEmpty(user.getName())) {
                query.addCriteria(Criteria.where("name").is(user.getName()));
            }
        }
        return query;
    }
}
