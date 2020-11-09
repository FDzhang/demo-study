package com.example.demomongodb.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ：zxq
 * @date ：Created in 2020/11/9 18:38
 */
@Data
public class Cat implements Serializable {
    private static final long serialVersionUID = -4748040392604474022L;
    private String name;
    private Integer age;
    private Date birthDay;
}
