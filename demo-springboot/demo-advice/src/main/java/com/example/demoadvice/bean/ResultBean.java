package com.example.demoadvice.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * ResultBean定义带泛型，使用了lombok。
 * @author ：zxq
 * @date ：Created in 2021/1/5 15:07
 */

// ResultBean定义带泛型，使用了lombok。
@Data
public class ResultBean<T> implements Serializable {
    private static final long serialVersionUID = -4658598190207681052L;

    public static final String NO_LOGIN = "-1";

    public static final String SUCCESS = "0";

    public static final String FAIL = "1";

    public static final String NO_PERMISSION = "2";

    private String code = SUCCESS;

    private String msg = "success";

    private T data;

    public ResultBean() {
        super();
    }

    public ResultBean(T data) {
        super();
        this.data = data;
    }

    public ResultBean(Throwable e) {
        super();
        this.msg = e.toString();
        this.code = FAIL;
    }
}
