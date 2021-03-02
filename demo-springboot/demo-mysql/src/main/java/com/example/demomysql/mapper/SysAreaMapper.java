package com.example.demomysql.mapper;

import com.example.demomysql.bean.SysArea;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 区域
 *
 * @author ：zxq
 * @date ：Created in 2021/3/1 13:53
 */
@Mapper
public interface SysAreaMapper {

    @Select("select * from sys_area where parent_id=#{parent_id};")
    List<SysArea> areaList(@Param("parent_id") String parentId);
}
