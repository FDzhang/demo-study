package cn.seasun.dubboapidemo.dto;

/**
 * @author zhangxinqiang
 * @create 2022/3/4 13:17
 */

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 用户添加 DTO
 * <p>
 * 注意，要实现 java.io.Serializable 接口。因为，Dubbo RPC 会涉及远程通信，需要序列化和反序列化。
 */
public class UserAddDTO implements Serializable {
    /**
     * 昵称
     */
    @NotEmpty(message = "昵称不能为空")
    @Length(min = 5, max = 16, message = "账号长度为 5-16 位")
    private String name;
    /**
     * 性别
     */
    @NotNull(message = "性别不能为空")
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
