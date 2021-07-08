package com.example.demovalidator.bean;

import com.example.demovalidator.serialize.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.util.Date;

/**
 * @author zhangxinqiang
 * @create 2021/7/6 14:57
 */
@Data
public class JacksonOutFormat {

    private String name;
    private Integer number;
    private Long id;

    @JsonSerialize(using = IDoubleSerialize.class, nullsUsing = IDoubleSerialize.class)
    private Double d1;
    @JsonSerialize(using = IWanSerialize.class)
    private Double d2;
    @JsonSerialize(using = IWanStrSerialize.class)
    private Double d3;
    @JsonSerialize(using = IRatioStrSerialize.class)
    private Double d4;
    @JsonSerialize(using = INumberSerialize.class)
    private Double d5;


    @JsonFormat(pattern = "yyyy", timezone = "GMT+8")
    private Date date1;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date date2;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date date3;
}
