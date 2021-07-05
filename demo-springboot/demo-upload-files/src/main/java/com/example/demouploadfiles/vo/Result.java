package com.example.demouploadfiles.vo;

import lombok.Data;

/**
 * @author zhangxinqiang
 * @create 2021/7/5 13:12
 */
@Data
public class Result<T> {
    private int code;
    private String message;
    private T data;

    public static <T> Result<T> ok(T data) {
        Result<T> res = new Result<>();
        res.setCode(200);
        res.setMessage("ok");
        res.setData(data);
        return res;
    }

    public static <T> Result<T> ok() {
        Result<T> res = new Result<>();
        res.setCode(200);
        res.setMessage("ok");

        return res;
    }
}
