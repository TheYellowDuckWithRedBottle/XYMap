package com.levi.xymap.controller;

import com.levi.xymap.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName
 * @Description TODO
 * @Author LeviFan
 * @Date 2022/12/7 10:57
 * @Version 1.0
 **/
@RestController
public class TplsController {
    @Autowired
    TemplateService templateService;
    @RequestMapping("list")
    public String list(){
       String tplContent =  templateService.getTemplate("");
       return tplContent;
    }
//    @RequestMapping("/tpls")
//    public String list(){
//        String tplContent =  templateService.getTemplate("");
//        return tplContent;
//    }
}
