package com.levi.xymap.service;

import org.springframework.stereotype.Service;

import java.io.InputStream;

public interface GeometryService {
    /**
     * 上传zip文件
     * @param file
     */
    String getZipCoordinates(InputStream file) throws Exception;
}
