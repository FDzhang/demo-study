package com.example.demoadvice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：zxq
 * @date ：Created in 2020/12/3 18:11
 */
@Data
@AllArgsConstructor
public class MyException extends RuntimeException {
    private String code;
    private String message;
    private String desc;
}
