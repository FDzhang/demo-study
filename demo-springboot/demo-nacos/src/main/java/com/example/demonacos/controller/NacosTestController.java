package com.example.demonacos.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.example.demonacos.model.CityInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：zxq
 * @date ：Created in 2020/4/27 14:04
 */


@RestController
@RequestMapping("/config")
public class NacosTestController {

    @NacosValue(value = "${city}", autoRefreshed = true)
    private String city;

    @GetMapping("nacos")
    public Object getValues(String cityCode){
        // 从nacos中获取数据
        JSONObject jsonObject = JSON.parseObject(city);
        System.out.println("nacos-config:"+city);

        // 根据key获取对象
        CityInfo info = jsonObject.getObject(cityCode, CityInfo.class);
        System.out.println(cityCode+"的info:"+info);

        return info;
    }
}
