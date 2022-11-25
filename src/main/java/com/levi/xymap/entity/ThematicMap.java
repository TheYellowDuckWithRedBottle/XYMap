package com.levi.xymap.entity;

/**
 * @ClassName
 * @Description TODO
 * @Author LeviFan
 * @Date 2022/11/24 9:13
 * @Version 1.0
 **/
public class ThematicMap implements Comparable<ThematicMap>{
    public String id;
    public String name;
    public String title;
    public String desc;
    public String thumbnail;
    public String tpl;
    public boolean enable;
    public int order;
    @Override
    public int compareTo(ThematicMap o) {
        return this.order-o.order;
    }
    public ThematicMap copy(ThematicMap thematicMap){
        this.name = thematicMap.name;
        this.title = thematicMap.title;
        this.desc = thematicMap.desc;
        this.thumbnail = thematicMap.thumbnail;
        this.tpl = thematicMap.id;
        this.enable = thematicMap.enable;
        return this;
    }
}
