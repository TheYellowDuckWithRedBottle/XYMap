package com.levi.xymap.controller;

import com.levi.xymap.service.GeometryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @ClassName
 * @Description TODO
 * @Author LeviFan
 * @Date 2023/2/19 17:39
 * @Version 1.0
 **/
@Controller
@RequestMapping(value="/geometry")
public class GeometryController {
    GeometryService geometryService;
    @PostMapping("/zip/upload")
    public void zipUpload(@RequestParam(value = "file") MultipartFile file) throws Exception {
        //TODO
        geometryService.getZipCoordinates(file.getInputStream());
    }
}