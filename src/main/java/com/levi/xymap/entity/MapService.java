package com.levi.xymap.entity;

import com.levi.xymap.model.CoreMap;
import org.ehcache.xml.model.ServiceType;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @ClassName
 * @Description TODO
 * @Author LeviFan
 * @Date 2022/11/23 16:46
 * @Version 1.0
 **/
public class MapService implements Comparable<MapService> {
    public String id;
    public String name;
    public String alias;
    public String url;
    public String type;
    public boolean visible;
    public float alpha;
    /**
     * 图层序号
     */
    public int index;
    public String xzdm;
    public String xzmc;
    public double minRefScale;
    public double maxRefScale;
    public double xMinExtent;
    public double xMaxExtent;
    public double yMinExtent;
    public double yMaxExtent;
    public String year;
    public String category;
    public String group;
    public String note;
    public List<Function> functions;
    public MapService clearFunction(){
        this.functions = null;
        return this;
    }
    public static MapService fromMap(CoreMap map, Map<MapServiceType,MapService> type, String groudId){
        MapService service = new MapService();
        if(map ==null) return service;
        service.id = map.getId();
        service.alias = map.alias;
        service.category = map.group.name;
        service.alpha = 1f;
        service.name = map.getName();
        service.visible = false;
        service.year = !map.year.equals("")?map.year:"0";
        service.xzdm = map.regionCode;
        service.group = groudId;
        service.minRefScale = map.minScale;
        service.maxRefScale = map.maxScale;
        service.xMinExtent= map.extent.xmin;
        service.xMaxExtent = map.extent.xmax;
        service.yMinExtent = map.extent.ymin;
        service.yMaxExtent = map.extent.ymax;

        if(type !=null && !Collections.emptyMap().equals(type)){
            if(type.containsKey(MapServiceType.ARCGIS_TILE)){
                service.type = MapServiceType.ARCGIS_TILE.value;
                MapService mapService = type.get(MapServiceType.ARCGIS_TILE);
                service.url = mapService.url;
            } else if(type.containsKey(MapServiceType.ARCGIS_REST)){
                service.type = MapServiceType.ARCGIS_REST.value;
                MapService mapService = type.get(MapServiceType.ARCGIS_REST);
                service.url = mapService.url;
            } else {
               Map.Entry<MapServiceType,MapService> defaultType =  type.entrySet().iterator().next();
               service.type = defaultType.getKey().value;
               service.url = defaultType.getValue().url;
            }
        }
        return service;
    }

    @Override
    public int compareTo(MapService o) {
        return 0;
    }
}
