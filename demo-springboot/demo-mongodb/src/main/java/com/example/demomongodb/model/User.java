package com.example.demomongodb.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * User实体类
 * @author ：zxq
 * @date ：Created in 2020/5/7 14:54
 */
@Data
public class User {

    private String name;
    private int age;
    @Field(name = "create_time")
    private Date createTime;

}
