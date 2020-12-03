package com.example.demoadvice.annotion;

import java.lang.annotation.*;

/**
 * @author ：zxq
 * @date ：Created in 2020/12/3 17:31
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CustomResponse {
}
