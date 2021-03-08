package com.example.demomysql.bean;

import lombok.*;

/**
 *
 * @Data 注解重写了 实体类的 equals 方法
 *
 * @author ：zxq
 * @date ：Created in 2020/12/18 14:34
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private String phone;
    private String thingCode;
}
