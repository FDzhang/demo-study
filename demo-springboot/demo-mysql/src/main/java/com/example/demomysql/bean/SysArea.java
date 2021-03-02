package com.example.demomysql.bean;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * 区域
 */
@Data
public class SysArea implements Serializable {
    /**
     * 编号
     */
    private String id;
    /**
     * 父级编号
     */
    private String parentId;
    /**
     * 所有父级编号
     */
    private String parentIds;
    /**
     * 名称
     */
    private String name;
    /**
     * 排序
     */
    private Double sort;

    /**
     * 区域编码
     */
    private String code;
    /**
     * 区域类型
     */
    private String type;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 更新者
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private Date updateDate;
    /**
     * 备注信息
     */
    private String remarks;
    /**
     * 删除标记
     */
    private String delFlag;




}