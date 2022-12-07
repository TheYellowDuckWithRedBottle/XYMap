package com.levi.xymap.model.gis;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName
 * @Description TODO
 * @Author LeviFan
 * @Date 2022/12/7 15:21
 * @Version 1.0
 **/
public class MapGroup {
    private static final long serialVersionUID = -7376626752738512294L;
    public String id;
    public String name;
    public String description;
    public Date createAt;
    public int weight;
    public MapGroup parent;
    public List<MapGroup> children = new ArrayList<>();

    public String getPath() {
        StringBuilder sb = new StringBuilder();
        MapGroup group = this;
        while(group!=null){
            sb.append(group.id).append("/");
            group = group.parent;
        }
        return sb.toString();
    }
}
