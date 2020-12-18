package com.example.demomysql.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：zxq
 * @date ：Created in 2020/12/18 14:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private String phone;
    private String thingCode;
}
