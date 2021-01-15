package com.example.demowebflux.bean;

import lombok.Data;

/**
 * 城市实体类
 * @author ：zxq
 * @date ：Created in 2021/1/14 15:20
 */
@Data
public class City {
    /**
     * 城市编号
     */
    private Long id;

    /**
     * 省份编号
     */
    private Long provinceId;

    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 描述
     */
    private String description;

}
