package com.example.demouploadfiles.controllers;

import com.example.demouploadfiles.vo.LoginRespVo;
import com.example.demouploadfiles.vo.LoginVo;
import com.example.demouploadfiles.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 如何使用 PostMan 的 pre-request-script 来进行预登陆？
 * 1、 使用脚本 请求 /login 接口，获取 token 并设置为环境变量
 * 2、 在请求 /token 时， 利用 '{{}}' 符号 将环境变量的值， 写到请求头中
 *
 * @author zhangxinqiang
 * @create 2021/7/5 13:38
 */
@RestController
public class PreLoginTestController {
    @PostMapping("login")
    @ResponseBody
    public Result<LoginRespVo> login(@RequestBody LoginVo vo) {
        System.err.println(vo);

        LoginRespVo respVo = new LoginRespVo();
        respVo.setToken("9999");

        if (vo.getMobilePhone().equals("1234")) {
            return Result.ok(respVo);
        }

        return Result.ok();
    }

    @GetMapping("token")
    @ResponseBody
    public Result<String> token(HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("Authorization");
        System.err.println(authorization);
        return Result.ok(authorization);
    }
}
