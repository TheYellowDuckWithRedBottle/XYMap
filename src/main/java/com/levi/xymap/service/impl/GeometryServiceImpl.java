package com.levi.xymap.service.impl;

import com.levi.xymap.entity.Document;
import com.levi.xymap.service.DocumentService;
import com.levi.xymap.service.GeometryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @ClassName
 * @Description TODO
 * @Author LeviFan
 * @Date 2023/2/19 22:26
 * @Version 1.0
 **/
@Service
public class GeometryServiceImpl implements GeometryService {
    @Autowired
    DocumentService documentService;
    @Override
    public void getZipCoordinates(InputStream file) {
        try {
          List<Document> documents =  documentService.readZipIn(file);
          File FileShp = null;
          File FileDbf = null;
          File FilePrj = null;
          String path = System.getProperty("java.io.tmpdir").concat(String.valueOf(System.currentTimeMillis()));
          for(Document document : documents){
              if(document.getName().endsWith(".shp")){
                  FileShp = new File(path+ document.getName());
              }else if(document.getName().endsWith(".dbf")){
                  FileDbf = new File(path+document.getName());
              }else if(document.getName().endsWith(".prj")){
                  FilePrj = new File(path+document.getName());
              }
          }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
