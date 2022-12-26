package com.levi.xymap.entity.mapper;

import com.levi.xymap.entity.Tpl;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface TplMapper {
    @Select("select * from om_tpl")
    List<Tpl> findAll();
}
