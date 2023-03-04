package com.levi.xymap.service.impl;

import com.levi.xymap.entity.Document;
import com.levi.xymap.service.DocumentService;
import com.levi.xymap.service.GeometryService;
import org.geotools.data.FeatureSource;
import org.geotools.data.PrjFileReader;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.shapefile.dbf.DbaseFileReader;
import org.geotools.data.shapefile.files.ShpFiles;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.ReadableByteChannel;
import java.util.List;
import java.util.Map;

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
          Map result = parseShpfile(FileShp,FileDbf,FilePrj,null);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private Map parseShpfile(File shpFile,File bdfFile, File prjFile,Map properties){
        ShapefileDataStore shpFileDataStore = null;
        FeatureSource featureSource = null;
        DbaseFileReader reader = null;
        PrjFileReader projFileReader = null;
        CoordinateReferenceSystem sourceCrs = null;
        if(shpFile != null) {
            String str = shpFile.toString();
        }
        if(prjFile !=null) {
            try {
                projFileReader = new PrjFileReader((ReadableByteChannel) new ShpFiles(prjFile));
                CoordinateReferenceSystem crs = projFileReader.getCoordinateReferenceSystem();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (FactoryException e) {
                e.printStackTrace();
            } finally {
                projFileReader.close();
            }

        }

    }
}
