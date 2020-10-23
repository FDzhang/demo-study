package com.example.democombination.bean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author ：zxq
 * @date ：Created in 2020/10/23 11:00
 */

@Data
public class TestBean {
    @JSONField(name = "test_name")
    private String name;
    private String value;
}
