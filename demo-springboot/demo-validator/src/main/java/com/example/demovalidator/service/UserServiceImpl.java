package com.example.demovalidator.service;

import com.example.demovalidator.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ：zxq
 * @date ：Created in 2021/1/5 16:57
 */
@Validated
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    @Lazy
    UserService userService;

    @Override
    public Long saveUser(User user) {
        System.out.println("校验通过");

        System.out.println(user);
        return 1L;
    }


    /**
     * 调用本类的校验方法
     * 1（不推荐）将验证方法移到其他类中 。这种方法奏效，但是无缘无故需要多建立一个service，有时候可能就是一个空方法，只不过参数有验证，其他不知道的小伙伴看到可能会比较懵
     * 2 注入ApplicationContext获取bean
     * 3 通过注入自己来获取当前类的实例，再调用该实例的方法。需要加@Lazy注解防止自我注入时spring抛出
     * org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type 'org.springframework.core.env.Environment' available: expected at least 1 bean which qualifies as autowire candidate. Dependency annotations: {}异常
     *
     * @return
     */
    /**
     * 批量保存EXCEL中的数据
     */
    @Override
    public void saveUsersFromExcel() {
        List<User> data = readFromExcel();

        int i = 0;
        for (User datum : data) {
            try {
//                saveUser(datum);
//                applicationContext.getBean(UserService.class).saveUser(datum);
                userService.saveUser(datum);
                System.out.println("第" + i + "条记录保存成功");
            } catch (ConstraintViolationException e) {
                log.error("第" + i + "条记录违法约束：" + e.getMessage());
            } catch (Exception e) {
                log.error("第" + i + "条记录保存失败");
            }
            i++;
        }
    }


    private List<User> readFromExcel() {
        List<User> users = new ArrayList<>();
        users.add(new User("", 12, new Date(), "1233333qq@.com"));
        users.add(new User("name2", 12, new Date(), "1232223qq.com"));
        users.add(new User("name3", -12, new Date(), "123311113qq.com"));
        return users;
    }


}
