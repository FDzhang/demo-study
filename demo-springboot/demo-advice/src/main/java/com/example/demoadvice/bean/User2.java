package com.example.demoadvice.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ：zxq
 * @date ：Created in 2020/12/3 16:46
 */

public class User2 implements Serializable {
    private Integer id;
    private String firstName;
    private Date birth;

    public User2() {
    }

    public User2(Integer id, String firstName, Date birth) {
        this.id = id;
        this.firstName = firstName;
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
