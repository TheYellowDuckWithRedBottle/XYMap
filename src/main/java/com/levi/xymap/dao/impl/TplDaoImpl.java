package com.levi.xymap.dao.impl;

import com.levi.xymap.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName
 * @Description TODO
 * @Author LeviFan
 * @Date 2022/11/24 9:33
 * @Version 1.0
 **/
public class TplDaoImpl {
    private static final String TPL_LOCATION = "/tpls/";
    private static final String TPL_SUFFIX = ".tpl";
    private static final String DEFAULT_TPL="base.tpl";
    private static final String WIDGETS_TPL="widget.tpl";
    private static final String SEARCH_TPL="search.json";
    private static final String ORGAN_TPL="organ.json";
    private static final String ANALYSIS_CONFIG="analysis.json";

    @Autowired
    private TemplateService templateService;
}
