package com.levi.xymap.service;

public interface TemplateService {
    public String getTemplate(String tplName);
    public String getTemplate(String tplName, String encoding);
    public String modify(String tplName, String content);
    
}
