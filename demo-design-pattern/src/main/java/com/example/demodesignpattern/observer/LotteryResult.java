package com.example.demodesignpattern.observer;

import lombok.Data;

import java.util.Date;

/**
 * @author zhangxinqiang
 * @create 2022/2/22 18:25
 */
@Data
public class LotteryResult {
    public LotteryResult() {
    }

    public LotteryResult(String uId, String msg, Date time) {
        this.uId = uId;
        this.msg = msg;
        this.time = time;
    }

    private String uId;
    private String msg;
    private Date time;


    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
