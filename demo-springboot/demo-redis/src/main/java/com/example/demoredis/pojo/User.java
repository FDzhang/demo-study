package com.example.demoredis.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ：zxq
 * @date ：Created in 2021/3/9 14:13
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1581237001925585960L;

    private String name;
    private Integer age;
    private String level;
}
