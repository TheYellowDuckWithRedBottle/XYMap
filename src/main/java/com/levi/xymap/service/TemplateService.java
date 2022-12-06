package com.levi.xymap.service;

import java.util.List;

public interface TemplateService {
    public String getTemplate(String tplName);
    public String getTemplate(String tplName, String encoding);
    public String modify(String tplName, String content);
    String[] getFileNames(String folder);
    List<String> listTplNames(String folder);
    String createTpl(String tplName, String content);
    void deleteTpl(String tpl);
    
}
