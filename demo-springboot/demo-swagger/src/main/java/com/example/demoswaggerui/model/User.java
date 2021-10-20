package com.example.demoswaggerui.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ：zxq
 * @date ：Created in 2020/7/13 20:03
 */

@Data
@ApiModel("用户类")
public class User {
    // 属性描述
    @ApiModelProperty(value = "名字")
    private String name;
    @ApiModelProperty(value = "年龄")
    private Integer age;
    // hidden 隐藏
    @ApiModelProperty(value = "体重", hidden = true)
    private Integer weight;
}
