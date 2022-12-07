package com.levi.xymap.entity.core;

import java.util.Date;

/**
 * @ClassName
 * @Description TODO
 * @Author LeviFan
 * @Date 2022/12/7 16:16
 * @Version 1.0
 **/
public class AbstractEntity implements Entity{
    private String id;
    private String name;
    private String description;
    private Date createAt;
    private boolean enabled = true;
    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
