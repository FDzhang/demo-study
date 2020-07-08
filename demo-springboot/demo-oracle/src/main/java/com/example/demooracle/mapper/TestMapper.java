package com.example.demooracle.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author ：zxq
 * @date ：Created in 2020/7/8 17:50
 */
@Mapper
public interface TestMapper {
    @Select("SELECT COUNT(*) FROM table1")
    int countRealByPage1();


    @Select("SELECT name " +
            "FROM table2 " +
            "WHERE ID = #{id}")
    String test2(@Param("id") String id);
}
