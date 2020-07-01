package com.example.demodubboapi.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ：zxq
 * @date ：Created in 2020/7/1 15:34
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -7541890996035073097L;
    private String name;
    private Integer age;
}
