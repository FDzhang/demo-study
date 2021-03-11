package com.example.demovalidator.controller;

import com.example.demovalidator.bean.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ：zxq
 * @date ：Created in 2020/12/3 18:04
 */
@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Result handleException(MethodArgumentNotValidException e) {
        List<String> errMsg = e.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage).collect(Collectors.toList());
        log.error("异常 {}", errMsg);
        return new Result("2", "参数校验未通过", errMsg);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public Result handleException(HttpMessageNotReadableException e) {
        log.error("异常 {}", e);
        return new Result("3", "参数无法读取", e.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    public Result handleException2(ConstraintViolationException e) {
        List<String> violations = e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessageTemplate).collect(Collectors.toList());
        return new Result("2", "参数校验未通过", violations);
    }

}
