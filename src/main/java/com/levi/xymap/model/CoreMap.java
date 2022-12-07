package com.levi.xymap.model;

import com.levi.xymap.entity.core.AbstractEntity;
import com.levi.xymap.model.gis.Bound;
import com.levi.xymap.model.gis.MapGroup;
import com.levi.xymap.model.gis.MapStatus;

/**
 * @ClassName
 * @Description TODO
 * @Author LeviFan
 * @Date 2022/12/7 14:49
 * @Version 1.0
 **/
public class CoreMap extends AbstractEntity {
    private static final long serialVersionUID = -3126733380287501415L;

    public String alias;

    public int weight;
    public int wkid;
    public Bound extent;
    public double minScale;
    public double maxScale;
    public String dataSourceId;
    public String year;
    public String regionCode;
    public MapStatus status = MapStatus.RUNNING;

    public MapGroup group;
    public String groupPath;

    @Override
    public String getId() {
        return super.getId();
    }
}
