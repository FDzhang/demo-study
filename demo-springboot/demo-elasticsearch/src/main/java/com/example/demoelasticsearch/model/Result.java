package com.example.demoelasticsearch.model;

import lombok.Data;

/**
 * @author ：zxq
 * @date ：Created in 2020/6/29 18:03
 */
@Data
public class Result {
    /**
     * 返回状态码
     */
    private int code;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回数据
     */
    private Object data;

    public static Result success(Object o) {
        Result result = new Result();
        result.code = 200;
        result.message = "success";
        result.setData(o);
        return result;
    }

    public static Result success() {
        Result result = new Result();
        result.code = 200;
        result.message = "success";
        return result;
    }

    public static Result error() {
        Result result = new Result();
        result.code = 201;
        return result;
    }
}
