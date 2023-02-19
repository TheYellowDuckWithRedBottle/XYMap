package com.levi.xymap.service;

import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public interface GeometryService {
    /**
     * 上传zip文件
     * @param file
     */
    void getZipCoordinates(InputStream file) throws Exception;
}
