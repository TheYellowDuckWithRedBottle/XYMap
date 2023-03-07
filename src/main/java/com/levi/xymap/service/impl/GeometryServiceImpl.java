package com.levi.xymap.service.impl;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.levi.xymap.entity.Document;
import com.levi.xymap.service.DocumentService;
import com.levi.xymap.service.GeometryService;
import org.apache.commons.io.IOUtils;
import org.checkerframework.checker.units.qual.Current;
import org.geotools.data.FeatureSource;
import org.geotools.data.PrjFileReader;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.shapefile.dbf.DbaseFileHeader;
import org.geotools.data.shapefile.dbf.DbaseFileReader;
import org.geotools.feature.DefaultFeatureCollection;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.geotools.geojson.feature.FeatureJSON;
import org.geotools.geojson.geom.GeometryJSON;
import org.geotools.geometry.jts.JTS;
import org.geotools.referencing.CRS;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.*;

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
    public String getZipCoordinates(InputStream file) {
        FileOutputStream outputStream = null;
        try {
          List<Document> documents =  documentService.readZipIn(file);
          File FileShp = null;
          File FileDbf = null;
          File FilePrj = null;
          String path = System.getProperty("java.io.tmpdir")+"temp"+ System.currentTimeMillis();
          File fileFolder = new File(path);
          if(!fileFolder.exists()) {
              fileFolder.mkdir();
          }
          for(Document document : documents){
              String filePath = path+"\\"+document.getName();
              outputStream = new FileOutputStream(filePath);
              IOUtils.write(document.getContent(),outputStream);
              if(document.getName().endsWith(".shp")){
                  FileShp = new File(filePath);
              }else if(document.getName().endsWith(".dbf")){
                  FileDbf = new File(filePath);
              }else if(document.getName().endsWith(".prj")){
                  FilePrj = new File(filePath);
              }
          }
          String geoJSON = parseShpfile(FileShp,FileDbf,FilePrj,null);
          return geoJSON;
        } catch (IOException e) {
            throw new RuntimeException(e.getLocalizedMessage());
        }finally {
            try {
                outputStream.close();
                IOUtils.closeQuietly();
            } catch (IOException e) {

            }
        }
    }
    private String parseShpfile(File shpFile,File bdfFile, File prjFile,Map properties) throws IOException {
        List<Map<String, Object>> featureList = new ArrayList<>();
        ShapefileDataStore shpFileDataStore = null;
        FeatureSource featureSource = null;
        DbaseFileReader DbfReader = null;
        PrjFileReader projFileReader = null;
        CoordinateReferenceSystem sourceCrs = null;
        if(shpFile != null) {
            String str = shpFile.toString();
        }
        // 获取上传图形的crs
        if(prjFile !=null) {
            FileInputStream fileInputStream = new FileInputStream(prjFile);
            try {
                final FileChannel channel = fileInputStream.getChannel();
                projFileReader = new PrjFileReader(channel);
                CoordinateReferenceSystem crs = projFileReader.getCoordinateReferenceSystem();
                if(crs != null) {
                    sourceCrs = crs;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (FactoryException e) {
                e.printStackTrace();
            } finally {
                fileInputStream.close();
                projFileReader.close();
            }
        }
        // 获取图形数据
        shpFileDataStore = new ShapefileDataStore(shpFile.toURI().toURL());
        try {
            featureSource = shpFileDataStore.getFeatureSource();
            FeatureCollection featureCollection =  featureSource.getFeatures();
            if (featureCollection.size() > 0) {
                FeatureIterator<SimpleFeature> iterator =  featureCollection.features();
                List<Geometry> geometries = new ArrayList<>(); // 放所有的geometry
                try{
                    while(iterator.hasNext()) {
                        SimpleFeature feature = iterator.next(); // 迭代器
                        List<Geometry> multiPolygons = new ArrayList<>(); // 放当前的Multipgeometry
                        Geometry geo =  (Geometry)feature.getDefaultGeometry();
                        Map<String,Object> map = new HashMap<>();
                        if( geo instanceof MultiPolygon) {
                            MultiPolygon multiPolygon = (MultiPolygon) geo;
                            // 判断如过geometry是多个还是一个组成
                            if (multiPolygon.getNumGeometries() == 1){
                                geo = (Geometry) multiPolygon.getGeometryN(0);
                                multiPolygons.add(geo);
                            } else if( multiPolygon.getNumGeometries() > 1) {
                                for( int i = 0;i<multiPolygon.getNumGeometries();i++) {
                                    multiPolygons.add(multiPolygon.getGeometryN(i));
                                }
                            }
                        } else {
                            multiPolygons.add(geo);
                        }
                        geometries.addAll(multiPolygons);
                        map.put("SHAPE",geo.toText());
                        featureList.add(map);
                    }
                }finally {
                    iterator.close();
                    shpFileDataStore.dispose();
                }
                // todo 对geometries做拓扑错误判
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 判断dbfFile
        if( bdfFile !=null) {
                FileInputStream fileInputStream = new FileInputStream(bdfFile);
                DbfReader = new DbaseFileReader(fileInputStream.getChannel(), false, null);
                DbaseFileHeader header = DbfReader.getHeader();
                int fieldsNum = header.getNumFields();
                try{
                    while(DbfReader.hasNext()) {
                        DbaseFileReader.Row row =  DbfReader.readRow();
                        for(int i =0;i<fieldsNum;i++) {
                            String  fieldName = header.getFieldName(i);
                            Object value = row.read(i);
                            Map<String, Object> map = featureList.get(i);
                            if(map.containsKey(fieldName.toLowerCase())|| map.containsKey(fieldName.toUpperCase())) {
                                continue;
                            }
                            map.put(fieldName, value);
                        }
                    }
                }finally {
                    DbfReader.close();
                }

            }
        FeatureCollection featureCollection =  list2FeatureCollection(featureList,sourceCrs,sourceCrs);
        String geoJSON = featureCollection2GeoJSON(featureCollection);
        return geoJSON;
    }
    private String featureCollection2GeoJSON(Object feature){
        FeatureJSON featureJSON = new FeatureJSON(new GeometryJSON(14));
        StringWriter geoJson = new StringWriter();
        if(feature instanceof FeatureCollection) {
            if(((FeatureCollection) feature).size() > 0) {
                // 设置bound属性
               if (((FeatureCollection)feature).getBounds() !=null){
                   featureJSON.setEncodeFeatureBounds(true);
               } else {
                   featureJSON.setEncodeFeatureBounds(false);
               }
               // 设置crs
                if(((SimpleFeature)(((FeatureCollection)feature).toArray()[0])).getFeatureType().getCoordinateReferenceSystem() !=null){
                    featureJSON.setEncodeFeatureCRS(true);
                } else {
                    featureJSON.setEncodeFeatureCRS(false);
                }
                try {
                    featureJSON.writeFeatureCollection((FeatureCollection)feature,geoJson);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else if (feature instanceof  SimpleFeature) {
                // 是否添加bound的属性
                if(((SimpleFeature)feature).getBounds() !=null){
                    featureJSON.setEncodeFeatureBounds(true);
                } else {
                    featureJSON.setEncodeFeatureBounds(false);
                }
                // 是否添加crs
                if(((SimpleFeature) feature).getFeatureType().getCoordinateReferenceSystem() !=null){
                    featureJSON.setEncodeFeatureCRS(true);
                } else {
                    featureJSON.setEncodeFeatureCRS(false);
                }
                try {
                    featureJSON.writeFeature((SimpleFeature) feature,geoJson);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return geoJson.toString();

    }

    /**
     *
     * @param featureList
     * @param sourceCrs 上传的数据的坐标系统
     * @param targetCrs 要转化的地图的坐标系统
     */
    private FeatureCollection list2FeatureCollection(List<Map<String, Object>> featureList, CoordinateReferenceSystem sourceCrs, CoordinateReferenceSystem targetCrs) {
        DefaultFeatureCollection collection = new DefaultFeatureCollection(null, null);
        for(Map item: featureList) {
            SimpleFeature simpleFeature = map2SimpleFeature(item,sourceCrs,targetCrs);
            collection.add(simpleFeature);
        }
        return collection;
    }
    private SimpleFeature map2SimpleFeature(Map<String,Object> featureMap, CoordinateReferenceSystem sourceCrs, CoordinateReferenceSystem targetCrs) {
        if(featureMap.containsKey("SHAPE")) {
            var shpString =  (String)featureMap.get("SHAPE");
            Geometry geometry =  readWkt(shpString);
            SimpleFeatureType featureType;
            if(geometry !=null && sourceCrs !=null && targetCrs !=null && sourceCrs != targetCrs) {
                geometry =project(geometry,sourceCrs,targetCrs); // 图形投影
                featureType = getFeatureType(featureMap,targetCrs);
            } else {
                featureType = getFeatureType(featureMap, targetCrs);
            }
            SimpleFeatureBuilder featureBuilder = new SimpleFeatureBuilder(featureType);
            String[] keys = featureMap.keySet().toArray(new String[0]);
            for(String key: keys) {
                if("SHAPE".equals(key)){
                    featureBuilder.add(geometry);
                } else if("crs".equals(key)){
                    continue;
                } else {
                    featureBuilder.add(featureMap.get(key) !=null? featureMap.get(key):"");
                }
            }
            SimpleFeature feature =  featureBuilder.buildFeature("feature".concat(UUID.randomUUID().toString()));
            return feature;
        } else {
            return null;
        }
    }
    private SimpleFeatureType getFeatureType(Map<String, Object> value, CoordinateReferenceSystem crs) {
        SimpleFeatureTypeBuilder typeBuilder = new SimpleFeatureTypeBuilder();
        typeBuilder.setName("Feature");
        if(crs !=null) {
            typeBuilder.setCRS(crs);
        }
        String[] keys = value.keySet().toArray(new String [0]);
        for(String key: keys)  {
            if("SHAPE".equals(key)) {
                typeBuilder.add("geometry", Geometry.class);
            } else if("crs".equals(key)) {
                continue;
            } else {
                typeBuilder.add(key, value.get(key) !=null?value.get(key).getClass():String.class);
            }
        }
        return typeBuilder.buildFeatureType();
    }
    /**
     *
     * @param geometry 要进行投影的geomety
     * @param sourceCrs 数据原本的坐标系
     * @param targetCrs 要转化成的坐标系
     * @return 转化为的geometry
     */
    private Geometry project(Geometry geometry, CoordinateReferenceSystem sourceCrs, CoordinateReferenceSystem targetCrs) {
        try {
            MathTransform tranform = CRS.findMathTransform(sourceCrs, targetCrs);
            geometry = JTS.transform(geometry, tranform);
            return geometry;
        } catch (FactoryException | TransformException e) {
            e.printStackTrace();
        }
        return null;
    }
    private Geometry readWkt(String wkt) {
        WKTReader wktReader = new WKTReader();
        try {
           Geometry geometry = wktReader.read(wkt);
           return geometry;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
 }