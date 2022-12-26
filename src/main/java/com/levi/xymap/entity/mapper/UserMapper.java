package com.levi.xymap.entity.mapper;

import com.levi.xymap.entity.User;
import org.apache.ibatis.annotations.*;
import org.checkerframework.common.value.qual.IntRangeFromGTENegativeOne;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from om_user")
    List<User> findAll();

    @Insert("insert into om_user(id,name,password,view_name,attr,description,enabled) values(#{id},#{name},#{password},#{viewName},#{attr},#{description},#{enabled})")
    int insert(User user);

    @Insert("insert into om_user(id,name,password,view_name,attr,description,enabled) values(#{id},#{name},#{password},#{viewName},#{attr},#{description},#{enabled})")
    int update(User user);

    @Delete("delete from om_user where id = #{id}")
    int deleteById(@Param("id") Integer id);
}
