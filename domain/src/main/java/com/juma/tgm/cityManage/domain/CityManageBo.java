package com.juma.tgm.cityManage.domain;

import java.io.Serializable;
import java.util.List;

public class CityManageBo implements Serializable {

    private static final long serialVersionUID = 7602980027024537348L;
    private CityManage cityManage;
    private Integer regionId;
    private List<CityManageBo> children;

    public CityManage getCityManage() {
        return cityManage;
    }

    public void setCityManage(CityManage cityManage) {
        this.cityManage = cityManage;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public List<CityManageBo> getChildren() {
        return children;
    }

    public void setChildren(List<CityManageBo> children) {
        this.children = children;
    }
}
