package com.levi.xymap.model.gis;

/**
 * @ClassName
 * @Description TODO
 * @Author LeviFan
 * @Date 2022/12/7 15:03
 * @Version 1.0
 **/
public class Bound {
    public double xmin;
    public double xmax;
    public double ymin;
    public double ymax;

    public Bound(double xmin, double xmax, double ymin, double ymax) {
        this.xmin = xmin;
        this.xmax = xmax;
        this.ymin = ymin;
        this.ymax = ymax;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Bound)){
            return false;
        }
        Bound bound = (Bound) obj;
        return bound.xmax ==this.xmax && bound.xmin == this.xmin
                && bound.ymin == this.ymin && bound.ymax == this.ymax;
    }

    @Override
    protected Bound clone() throws CloneNotSupportedException {
        return (Bound) super.clone();
    }
}
