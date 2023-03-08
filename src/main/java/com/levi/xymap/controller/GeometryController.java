package com.levi.xymap.controller;

import com.levi.xymap.model.Result;
import com.levi.xymap.service.GeometryService;
import com.levi.xymap.service.impl.GeometryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
@RequestMapping(value="geometry")
public class GeometryController {
    @Autowired
    GeometryService geometryService;

    @PostMapping("/zip/upload")
    @ResponseBody
    public Result zipUpload(@RequestParam(value = "file")  MultipartFile file,String targetCrs) throws Exception {
        //TODO
       String geojson = geometryService.getZipCoordinates(file.getInputStream());
       Result result = Result.success(geojson);
       return result;
    }
}
