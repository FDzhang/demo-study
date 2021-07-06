package com.example.demovalidator.controller;

import com.example.demovalidator.bean.Person;
import com.example.demovalidator.bean.PersonSupplier;
import com.example.demovalidator.bean.User;
import com.example.demovalidator.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ：zxq
 * @date ：Created in 2021/1/4 15:49
 */

@RestController
@RequestMapping
@Slf4j
public class TestController {

    @Autowired
    private UserService userService;

    @GetMapping("get")
    public String get(@Valid User user) {
        log.info("user: {}", user);
        return "get ok";
    }

    @PostMapping("save")
    public String validation() {
        User user = new User("name", 12, new Date(), "1233333qq.com");

        // 调用非本类的校验方法
        // 我们在实现类上，模拟controller校验，加上@Validated以及@Valid注解
        userService.saveUser(user);
        return "ok";
    }

    @GetMapping("/from")
    public String saveUsersFromExcel() {
        userService.saveUsersFromExcel();
        return "ok";
    }

    public static void main(String[] args) {
        Map<Integer, List<Person>> personGroups = Stream.generate(new PersonSupplier()::build)
                .limit(10)
                .collect(Collectors.groupingBy(Person::getAge));

        Iterator it = personGroups.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, List<Person>> persons = (Map.Entry) it.next();
            System.out.println("Age " + persons.getKey() + " = " + persons.getValue());
        }


        Map<Boolean, List<Person>> children = Stream.generate(new PersonSupplier()::build).
                limit(100).
                collect(Collectors.partitioningBy(p -> p.getAge() < 18));

        System.out.println("Children number: " + children.get(true).size());
        System.out.println("Adult number: " + children.get(false).size());
    }
}
