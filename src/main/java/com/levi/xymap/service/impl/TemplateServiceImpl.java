package com.levi.xymap.service.impl;

import com.levi.xymap.entity.Configuration;
import com.levi.xymap.entity.mapper.TplMapper;
import com.levi.xymap.service.TemplateService;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName
 * @Description TODO
 * @Author LeviFan
 * @Date 2022/12/6 17:27
 * @Version 1.0
 **/
@Service
public class TemplateServiceImpl implements TemplateService {
    @Autowired
    private TplMapper tplMapper;
    private String location;
    private Configuration cfg;
    private void init() {
        cfg= new Configuration();

    }
    @Override
    public String getTemplate(String tplName) {
        return null;
    }

    @Override
    public String getTemplate(String tplName, String encoding) {
        return null;
    }

    @Override
    public String modify(String tplName, String content) {
        return null;
    }

    @Override
    public String[] getFileNames(String folder) {
        return new String[0];
    }

    @Override
    public List<String> listTplNames(String folder) {
        List<String> names = new ArrayList<String>();
        try {
            Resource res = getTplResource(folder);
            File[] listFile = res.getFile().listFiles();
            for(File file: listFile){
                names.add(file.getName());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return names;
    }

    @Override
    public String createTpl(String tplName, String content) {
        return null;
    }

    @Override
    public void deleteTpl(String tpl) {

    }
    private Resource getTplResource(String tplName) throws MalformedURLException {
        return new UrlResource(location.concat(tplName));
    }
}
