package com.levi.xymap.model.gis;

public enum MapStatus {
    RUNNING("运行中"),
    STOP("停止"),
    PAUSE("暂停"),
    IMPORTING("导入中");
    public String label;

    MapStatus(String label) {
        this.label = label;
    }
}
