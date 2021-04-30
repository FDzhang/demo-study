package com.example.demosqlserver.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

@Data
@TableName("user")
public class Vo extends Model<Vo> {
    String name;
    Integer age;

    String type;
    String desc;
}
