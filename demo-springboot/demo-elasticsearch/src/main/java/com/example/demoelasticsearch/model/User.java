package com.example.demoelasticsearch.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ：zxq
 * @date ：Created in 2020/6/29 17:25
 */
@Data
@Document(indexName = "info", type = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 4352954802272079927L;
    @Id
    private String id;
    private String name;
    private Integer age;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date date;
}
