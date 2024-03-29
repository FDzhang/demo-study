package cn.seasun.springsecuritydemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : zxq
 * @create : 2022/3/11 21:43
 *
 * 对于 /test/echo 接口，直接访问，无需登录。
 * 对于 /test/home 接口，无法直接访问，需要进行登录。
 * 对于 /test/admin 接口，需要登录「admin/admin」用户，因为需要 ADMIN 角色。
 * 对于 /test/normal 接口，需要登录「normal/normal」用户，因为需要 NORMAL 角色。
 * 胖友可以按照如上的说明，进行各种测试。例如说，登录「normal/normal」用户后，去访问 /test/admin 接口，会返回 403 界面，无权限~
 */
// TestController.java

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/echo")
    public String demo() {
        return "示例返回";
    }

    @GetMapping("/home")
    public String home() {
        return "我是首页";
    }

    @GetMapping("/admin")
    public String admin() {
        return "我是管理员";
    }

    @GetMapping("/normal")
    public String normal() {
        return "我是普通用户";
    }

}