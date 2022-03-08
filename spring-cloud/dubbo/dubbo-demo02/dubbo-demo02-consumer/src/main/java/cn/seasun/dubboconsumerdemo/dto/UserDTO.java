package cn.seasun.dubboconsumerdemo.dto;


import java.io.Serializable;

/**
 * 用户信息 DTO
 * <p>
 * <p>
 * 注意，要实现 java.io.Serializable 接口。因为，Dubbo RPC 会涉及远程通信，需要序列化和反序列化。
 */
public class UserDTO implements Serializable {

    /**
     * 用户编号
     */
    private Integer id;
    /**
     * 昵称
     */
    private String name;
    /**
     * 性别
     */
    private Integer gender;

    public Integer getId() {
        return id;
    }

    public UserDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getGender() {
        return gender;
    }

    public UserDTO setGender(Integer gender) {
        this.gender = gender;
        return this;
    }
}