package com.example.democombination.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author ：zxq
 * @date ：Created in 2020/10/20 11:03
 */
@Mapper
public interface TestMapper {

    @Select("select count(*) from t_user")
    public int count();
}
