package com.example.demomongodb.config;

import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：zxq
 * @date ：Created in 2020/11/4 10:46
 */
@Configuration
@EnableWebMvc
public class FastConfig implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        /**
         * 1、定义一个convert转换消息对象；
         * 2、添加fastJson的配置信息，是否要格式化返回的Json数据；
         * 3、处理中文乱码
         * 4、在convert中添加配置信息；
         * 5、将convert添加到converters当中；
         */
        // 1、定义一个convert转换消息对象；
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

        // 2、添加fastJson的配置信息，是否要格式化返回的Json数据；
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        fastJsonConfig.setFeatures(Feature.OrderedField);

        // 3、处理中文乱码
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON);

        fastConverter.setSupportedMediaTypes(fastMediaTypes);

        // 4、在convert中添加配置信息；
        fastConverter.setFastJsonConfig(fastJsonConfig);

        // 5、将convert添加到converters当中；
        converters.add(fastConverter);
    }
}
