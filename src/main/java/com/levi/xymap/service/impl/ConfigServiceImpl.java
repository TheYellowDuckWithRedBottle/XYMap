package com.levi.xymap.service.impl;

import com.levi.xymap.dao.TplDao;
import com.levi.xymap.entity.BaseMapLayer;
import com.levi.xymap.entity.Configuration;
import com.levi.xymap.entity.MapService;
import com.levi.xymap.entity.ThematicMap;
import com.levi.xymap.service.ConfigService;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;


import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @ClassName
 * @Description TODO
 * @Author LeviFan
 * @Date 2022/12/7 11:13
 * @Version 1.0
 **/
public class ConfigServiceImpl implements ConfigService {
    @Autowired
    private TplDao tplDao;
    @Override
    public boolean alterProperty(String propKey, String propVal) {
        return false;
    }

    @Override
    public List<Map> getThumbTplInfos() {
        List<Map> info = new ArrayList<>();
        Configuration configuration = null;
        for(String tplName: tplDao.getTplNames()){
            configuration = getConfiguration(tplName);
            Map item = new HashMap();
            item.put("tpl",tplName);
            item.put("name",configuration.name);
            item.put("description",configuration.description);
            item.put("createAt",configuration.createAt);
            Configuration.Map map =configuration.map;
            String thumbnail = "";
            // 获得底图
            if(map.baseLayers.size() > 0){
                BaseMapLayer baseMapLayer = map.baseLayers.get(0);
                thumbnail = baseMapLayer.thumbnailUrl;
            }
            // 如果底图为空，获取可操作的图层
            if(StringUtils.isBlank(thumbnail)){
                if(map.operationLayers.size() > 0){
                    MapService service = map.operationLayers.get(0);
                    if(service!=null && service.id!=null){
                        // todo 获取缩率图
                        thumbnail = "";
                    }
                    item.put("thumbnail", thumbnail);
                    info.add(item);
                }
            }
        }
        Collections.sort(info, new Comparator<Map>() {
            @Override
            public int compare(Map map1, Map map2) {
                // todo 比较日期
               String map1Date = MapUtils.getString(map1,"createAt");
               String map2Date = MapUtils.getString(map2,"createAt");
               return map1Date.compareTo(map2Date);
            }
        });
        return info;
    }

    @Override
    public List<ThematicMap> getThematicMaps() {
        return null;
    }

    @Override
    public void deleteThematicMap(String id) {

    }

    @Override
    public ThematicMap updateThematicMap(ThematicMap thematicMap) {
        return null;
    }

    @Override
    public Configuration getConfiguration(String tplName) {
        return tplDao.getConfiguration(tplName);
    }

    @Override
    public Configuration createTpl(String tplName) {
        return null;
    }

    @Override
    public Configuration createTpl(String tpl, String name, String description, String thematicMap, String parentTpl) {
        return null;
    }

    @Override
    public void deleteTpl(String tplName) {

    }

    @Override
    public List getAllServices(String id) {
        return null;
    }

    @Override
    public List<Map> getLayerFields(String mapId, String layerName) {
        return null;
    }
}
