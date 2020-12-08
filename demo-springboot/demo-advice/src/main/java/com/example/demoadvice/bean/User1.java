package com.example.demoadvice.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ：zxq
 * @date ：Created in 2020/12/3 16:46
 */

public class User1 implements Serializable {
    private Integer id;
    private String firstName;
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
