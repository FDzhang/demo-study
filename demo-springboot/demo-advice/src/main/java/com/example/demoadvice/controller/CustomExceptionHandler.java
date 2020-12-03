package com.example.demoadvice.controller;

import com.example.demoadvice.bean.Result;
import com.example.demoadvice.exception.MyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ：zxq
 * @date ：Created in 2020/12/3 18:04
 */
@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result handleException(Exception e) {
        log.error("系统异常 {}", e);
        return new Result("201", "失败", e.getMessage());
    }

    @ExceptionHandler(MyException.class)
    @ResponseBody
    public Result handleMyException(MyException e) {
        log.error("系统异常 {}", e);
        return new Result("201", "失败", e.getMessage());
    }
}
