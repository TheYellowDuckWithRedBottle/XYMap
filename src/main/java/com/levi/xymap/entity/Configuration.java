package com.levi.xymap.entity;

import java.util.List;

/**
 * @ClassName
 * @Description TODO
 * @Author LeviFan
 * @Date 2022/11/23 16:41
 * @Version 1.0
 **/
public class Configuration {
    public String defaultRegionCode;
    public String createAt;
    public String description;
    public String name;
    public boolean coordinateVisiable;
    public String title;
    public String logo;
    public Map map;
    public List<Widget> widgets;
    public WidgetContainer widgetContainer;

    public static final class Map{
        public java.util.Map initExtent;
        public List<Object> lods;
        public List<BaseMapLayer> baseLayers;
        public List<MapService> operationLayers;
        public double defaultScale;
    }
    public static final class Widget implements Comparable<Widget> {
        public String id;
        public String label;
        public String icon;
        public String url;
        public boolean collapsible;
        public  String group;
        public boolean isDefaultOpen;
        public boolean display;
        public java.util.Map config;
        public int order;
        public Widget() {
        }
        public Widget(String id, String label, String url) {
            this.id = id;
            this.label = label;
            this.url = url;
        }

        @Override
        public int compareTo(Widget o) {
            return this.order-o.order;
        }
    }
    public static final class WidgetContainer {
        public List<Widget> widgets;
        public List<WidgetGroup> widgetGrouops;
    }
    public static final class WidgetGroup implements Comparable<WidgetGroup>{
        public String id;
        public String label;
        public String icon;
        public int type;
        public String url;
        public List<Widget> widgets;
        public int order;

        @Override
        public int compareTo(WidgetGroup o) {
            return this.order-o.order;
        }
    }
}
