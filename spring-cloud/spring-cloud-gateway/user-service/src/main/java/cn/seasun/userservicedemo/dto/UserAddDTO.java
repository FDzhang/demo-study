package cn.seasun.userservicedemo.dto;

/**
 * @author zhangxinqiang
 * @create 2022/3/29 18:13
 */

import java.io.Serializable;

/**
 * 用户添加 DTO
 */
public class UserAddDTO implements Serializable {

    /**
     * 昵称
     */
    private String name;
    /**
     * 性别
     */
    private Integer gender;

    public String getName() {
        return name;
    }

    public UserAddDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getGender() {
        return gender;
    }

    public UserAddDTO setGender(Integer gender) {
        this.gender = gender;
        return this;
    }

}