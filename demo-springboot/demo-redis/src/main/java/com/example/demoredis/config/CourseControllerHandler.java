package com.example.demoredis.config;

import com.example.demoredis.util.CourseDateConverter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * 接收所有时间格式
 *
 * @author ：zxq
 * @date ：Created in 2021/1/25 14:08
 */

@ControllerAdvice
public class CourseControllerHandler {
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        GenericConversionService genericConversionService = (GenericConversionService) binder.getConversionService();
        if (genericConversionService != null) {
            genericConversionService.addConverter(new CourseDateConverter());
        }
    }
}
