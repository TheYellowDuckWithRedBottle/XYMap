package com.levi.xymap.dao.impl;

import com.levi.xymap.dao.TplDao;
import com.levi.xymap.entity.Configuration;
import com.levi.xymap.service.TemplateService;
import org.apache.commons.lang3.StringUtils;
import org.hsqldb.lib.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName
 * @Description TODO
 * @Author LeviFan
 * @Date 2022/11/24 9:33
 * @Version 1.0
 **/
public class TplDaoImpl implements TplDao {
    private static final String TPL_LOCATION = "/tpls/";
    private static final String TPL_SUFFIX = ".tpl";
    private static final String DEFAULT_TPL="base.tpl";
    private static final String WIDGETS_TPL="widget.tpl";
    private static final String SEARCH_TPL="search.json";
    private static final String ORGAN_TPL="organ.json";
    private static final String ANALYSIS_CONFIG="analysis.json";

    @Autowired
    private TemplateService templateService;

    @Override
    public List<String> getTplNames() {
        List<String> names = templateService.listTplNames(TPL_LOCATION);
        List<String> values = new ArrayList<>(names.size());
        for(String name:names){
            if(StringUtils.endsWith(name,TPL_SUFFIX)){
                String fileName = name.substring(0, name.lastIndexOf(TPL_SUFFIX));
                values.add(fileName);
            }
        }
        return values;
    }

    @Override
    public Configuration getConfiguration(String tplName) {
        return null;
    }

    @Override
    public Configuration saveConfiguration(String tplName, Configuration configuration) {
        return null;
    }

    @Override
    public Configuration createTpl(String tplName) {
        return null;
    }

    @Override
    public void deleteTpl(String tplName) {

    }
}
