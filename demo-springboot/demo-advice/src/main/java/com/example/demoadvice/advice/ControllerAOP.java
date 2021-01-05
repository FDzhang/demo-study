package com.example.demoadvice.advice;

/**
 * @author ：zxq
 * @date ：Created in 2021/1/5 15:10
 */

import com.example.demoadvice.bean.ResultBean;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 处理和包装异常
 */
@Aspect
@Slf4j
@Component
public class ControllerAOP {

    @Pointcut("execution(* com.example.demoadvice.controller.TestController.*(..))")
    public void all() {
    }

    @Around("all()")
    public Object handlerControllerMethod(ProceedingJoinPoint pjp) {
        long startTime = System.currentTimeMillis();

        ResultBean<?> result;

        try {
            result = (ResultBean<?>) pjp.proceed();
            log.info(pjp.getSignature() + " use time: " + (System.currentTimeMillis() - startTime) + " ms");
        } catch (Throwable e) {
            result = handlerException(pjp, e);
        }

        return result;
    }

    /**
     * 封装异常信息，注意区分已知异常（自己抛出的）和未知异常
     */
    private ResultBean<?> handlerException(ProceedingJoinPoint pjp, Throwable e) {
        ResultBean<?> result = new ResultBean();

        // 已知异常
//        if (e instanceof CheckException) {
//            result.setMsg(e.getLocalizedMessage());
//            result.setCode(ResultBean.FAIL);
//        } else if (e instanceof UnloginException) {
//            result.setMsg("Unlogin");
//            result.setCode(ResultBean.NO_LOGIN);
//        } else {
        log.error(pjp.getSignature() + " error ", e);
        //TODO 未知的异常，应该格外注意，可以发送邮件通知等
        result.setMsg(e.toString());
        result.setCode(ResultBean.FAIL);
//        }

        return result;
    }
}