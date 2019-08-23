package com.juma.tgm.base.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 在途监控
 * 
 * @author weilibin
 *
 */

public class MapViewDomain implements Serializable {

    private static final long serialVersionUID = -5900049813320338296L;
    private Integer code;
    private List<ViewInfo> data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<ViewInfo> getData() {
        return data;
    }

    public void setData(List<ViewInfo> data) {
        this.data = data;
    }


}
