package com.example.demovalidator.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * @author ：zxq
 * @date ：Created in 2021/1/4 10:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @NotBlank(message ="用户名不能为空")
    @Size(min = 3, max = 5, message = "用户名长度为{min}-{max}之间")
    private String name;

    @Positive(message = "年龄必须是一个正数")
    private Integer age;

    @Past(message = "必须是一个过去的日期")
    private Date birth;

    @NotBlank(message ="邮箱不能为空")
    @Email(message = "邮箱格式不合法")
    private String email;
}
