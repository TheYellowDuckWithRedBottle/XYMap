package com.levi.xymap.entity.core;

import java.io.Serializable;

public interface Entity extends Serializable {
    String getId();

    String getName();

    String getDescription();

    boolean isEnabled();
}
