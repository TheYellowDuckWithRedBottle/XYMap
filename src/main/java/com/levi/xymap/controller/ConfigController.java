package com.levi.xymap.controller;

import com.levi.xymap.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName
 * @Description TODO
 * @Author LeviFan
 * @Date 2022/11/23 16:33
 * @Version 1.0
 **/
@RestController
@RequestMapping("config")
public class ConfigController {
    private ConfigService configService;
    @Autowired
    public ConfigController() {

    }

    @GetMapping("/helloworld")
    public String hello() {
        return "Hello World!";
    }

    @GetMapping(value = "/tpls")
    public List tpls(){
        return configService.getThumbTplInfos();
    }
}
