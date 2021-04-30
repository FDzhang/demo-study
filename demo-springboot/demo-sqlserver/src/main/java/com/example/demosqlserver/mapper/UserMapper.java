package com.example.demosqlserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demosqlserver.bean.User;
import com.example.demosqlserver.bean.Vo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<Vo> {

    @Select("select * from user where name=#{name}")
    User getUser(String name);
}
