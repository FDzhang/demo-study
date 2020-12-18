package com.example.demomysql.mapper;

import com.example.demomysql.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author ：zxq
 * @date ：Created in 2020/10/20 11:03
 */
@Mapper
public interface TestMapper {

    @Select("select count(*) from t_user")
    public int count();

    @Select("select count(id) from user_integral_info_copy")
    public int countUser();

    @Select("select id, phone from user_integral_info_copy where id > #{id} order by id limit 2000")
    public List<User> phoneList(@Param("id") Integer id);

    @Select(" UPDATE user_integral_info_copy SET thing_code = #{thingCode} where id=#{id} ")
    public List<User> updateCode(@Param("id") Integer id, @Param("thingCode") String thingCode);

}
