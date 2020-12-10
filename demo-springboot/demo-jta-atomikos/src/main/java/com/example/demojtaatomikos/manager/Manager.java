package com.example.demojtaatomikos.manager;

/**
 * @author ：zxq
 * @date ：Created in 2020/12/9 14:11
 */
import com.example.demojtaatomikos.bean.User;
import com.example.demojtaatomikos.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Objects;

/**
 * @author whh
 * @date 2020/7/22
 */
@Component
public class Manager {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Transactional(rollbackFor = Exception.class)
    public void test(Integer test) throws Exception {
        userMapper.insert(new User(1, "test", new Date()));
        mongoTemplate.save(new User(1, "test", new Date()), "user");
        userMapper.insert(new User(2, "test", new Date()));
        mongoTemplate.save(new User(2, "test", new Date()), "user");
        if (Objects.equals(1, test)) {
            throw new Exception();
        }
    }
}