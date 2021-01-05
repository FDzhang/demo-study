package com.example.demovalidator.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ：zxq
 * @date ：Created in 2020/12/3 16:44
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result implements Serializable {
    private static final long serialVersionUID = -2070679440530906406L;
    private String code;
    private String message;
    private Object data;
}
