package com.levi.xymap.dao;

import com.levi.xymap.entity.Configuration;

import java.util.List;

/**
 * @ClassName
 * @Description TODO
 * @Author LeviFan
 * @Date 2022/11/24 9:28
 * @Version 1.0
 **/
public interface TplDao {
    List<String> getTplNames();
    Configuration getConfiguration(String tplName);
    Configuration saveConfiguration(String tplName, Configuration configuration);
    Configuration createTpl(String tplName);
    void deleteTpl(String tplName);

}
