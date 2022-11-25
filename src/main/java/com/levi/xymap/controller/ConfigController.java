package com.levi.xymap.controller;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName
 * @Description TODO
 * @Author LeviFan
 * @Date 2022/11/23 16:33
 * @Version 1.0
 **/
@SpringBootApplication
@RestController
public class ConfigController {
    @GetMapping("/helloworld")
    public String hello() {
        return "Hello World!";
    }
}
