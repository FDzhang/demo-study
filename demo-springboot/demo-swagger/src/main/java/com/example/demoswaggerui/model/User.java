package com.example.demoswaggerui.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ：zxq
 * @date ：Created in 2020/7/13 20:03
 */

@Data
public class User {
    // 属性描述
    @ApiModelProperty(value = "名字")
    private String name;
    @ApiModelProperty(value = "年龄")
    private Integer age;
}
