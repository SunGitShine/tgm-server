package com.juma.tgm.base.domain;

import java.io.Serializable;
import java.util.List;

public class RegionBo implements Serializable{

    private static final long serialVersionUID = -5504820086513496833L;
    private Integer regionId;
    private Integer parentRegionId;
    private String regionCode;
    /** 已选地区ID */
    private List<RegionBo> regionIdList;

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public Integer getParentRegionId() {
        return parentRegionId;
    }

    public void setParentRegionId(Integer parentRegionId) {
        this.parentRegionId = parentRegionId;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public List<RegionBo> getRegionIdList() {
        return regionIdList;
    }

    public void setRegionIdList(List<RegionBo> regionIdList) {
        this.regionIdList = regionIdList;
    }

    public enum SimpleRegionKey {
        PROVINCE, PROVINCE_CITY, CITY, CITY_TOWN, TOWN;
    }
}
