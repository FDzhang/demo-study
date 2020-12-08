package com.example.demoadvice.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ：zxq
 * @date ：Created in 2020/12/3 16:46
 */
//@ToString
public class User1 implements Serializable {
    private Integer id;
    private String firstName;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birth;

    public User1() {
    }

    public User1(Integer id, String firstName, Date birth) {
        this.id = id;
        this.firstName = firstName;
        this.birth = birth;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return firstName;
    }

    public void setName(String firstName) {
        this.firstName = firstName;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "User1{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", birth=" + birth +
                '}';
    }
}
