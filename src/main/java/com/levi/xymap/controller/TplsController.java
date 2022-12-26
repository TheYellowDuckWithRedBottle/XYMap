package com.levi.xymap.controller;

import com.levi.xymap.entity.Tpl;
import com.levi.xymap.entity.mapper.TplMapper;
import com.levi.xymap.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName
 * @Description TODO
 * @Author LeviFan
 * @Date 2022/12/7 10:57
 * @Version 1.0
 **/
@RestController
@RequestMapping("/tpl")
public class TplsController {
    @Autowired
    private TplMapper tplMapper;

    @GetMapping("/list")
    public List<Tpl> findAll(){
       return tplMapper.findAll();
    }

}
