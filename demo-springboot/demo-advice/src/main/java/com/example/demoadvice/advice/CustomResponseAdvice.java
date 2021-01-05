package com.example.demoadvice.advice;

import com.example.demoadvice.annotion.CustomResponse;
import com.example.demoadvice.bean.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Method;

/**
 * @author ：zxq
 * @date ：Created in 2020/12/3 17:20
 */
//@ControllerAdvice
@Slf4j
public class CustomResponseAdvice implements ResponseBodyAdvice {



    /**
     * 是否需要处理返回结果
     *
     * @param methodParameter
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        log.info("In supports() method of {}", getClass().getSimpleName());
        Method method = methodParameter.getMethod();
        assert method != null;
        return method.isAnnotationPresent(CustomResponse.class);
    }

    /**
     * 处理返回结果
     *
     * @param body
     * @param methodParameter
     * @param mediaType
     * @param aClass
     * @param serverHttpRequest
     * @param serverHttpResponse
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        log.info("In beforeBodyWrite() method of " + getClass().getSimpleName());
        if (body instanceof Result) {
            return body;
        }
        return new Result("200", "advice成功", body);
    }
}
