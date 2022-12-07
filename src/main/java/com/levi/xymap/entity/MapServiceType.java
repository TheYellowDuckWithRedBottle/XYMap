package com.levi.xymap.entity;

public enum MapServiceType {
    WMS("wms服务","wms"),
    WMTS("wmts服务","wmts"),
    WFS("wfs服务","wfs"),
    REST("rest服务","rest"),
    ARCGIS_REST("arcgis rest检索服务","dynamic"),
    ARCGIS_TILE("arcgis rest静态切片","tiled"),
    ARCGIS_EXPORT("arcgis rest动态切片","export");

    public String label;
    public String value;

    MapServiceType(String label, String value) {
        this.label = label;
        this.value = value;
    }
}
