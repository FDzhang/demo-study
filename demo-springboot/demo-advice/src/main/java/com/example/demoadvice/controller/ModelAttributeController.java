package com.example.demoadvice.controller;

import com.example.demoadvice.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：zxq
 * @date ：Created in 2020/12/21 13:51
 */
@RestController
@RequestMapping
@Slf4j
public class ModelAttributeController {


    @PostMapping("/id/{id}/name/{name}")
    public String test(@ModelAttribute User user) {
        log.info("user: {}", user);
        return "ok";
    }
}
