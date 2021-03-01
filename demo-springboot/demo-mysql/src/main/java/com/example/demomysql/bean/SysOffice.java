package com.example.demomysql.bean;

import lombok.Data;

import java.util.Date;

/**
 * 机构
 * @author ：zxq
 * @date ：Created in 2021/3/1 14:13
 */
@Data
public class SysOffice {

    private String id;
    private String parentId;
    private String parentIds;
    private String name;
    private String code;
    private Double sort;
    private String areaId;
    private String type;
    private String grade;

    private String createBy;
    private String updateBy;

    private Date createDate;
    private Date updateDate;
    private String delFlag = "0";

}
