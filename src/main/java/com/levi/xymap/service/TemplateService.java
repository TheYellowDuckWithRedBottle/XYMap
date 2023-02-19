package com.levi.xymap.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface TemplateService {
    public String getTemplate(String tplName);
    public String getTemplate(String tplName, String encoding);
    public String modify(String tplName, String content);
    String[] getFileNames(String folder);
    List<String> listTplNames(String folder);
    String createTpl(String tplName, String content);
    void deleteTpl(String tpl);
    
}
