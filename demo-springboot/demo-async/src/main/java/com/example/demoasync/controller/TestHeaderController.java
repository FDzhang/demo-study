package com.example.demoasync.controller;

import cn.hutool.core.thread.ThreadUtil;
import com.example.demoasync.async.AsyncService;
import com.example.demoasync.async.Task1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author ：zxq
 * @date ：Created in 2021/3/30 14:36
 */
@RestController
public class TestHeaderController {

    @Autowired
    private AsyncService asyncService;

    @RequestMapping(value="header",method = RequestMethod.GET)
    public String re(HttpServletRequest request){
        System.out.println("拿请求头");
        System.out.println(request.getHeader("user-agent"));
        return "success";
    }

    @GetMapping("test1")
    public String test(){
        asyncService.print(100);
        asyncService.print(100);
        return "success";
    }

}

