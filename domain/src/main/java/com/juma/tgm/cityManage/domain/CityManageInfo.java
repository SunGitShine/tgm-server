package com.juma.tgm.cityManage.domain;

import java.io.Serializable;
import java.util.List;

public class CityManageInfo implements Serializable {

    private static final long serialVersionUID = 3073760350984598378L;
    private List<CityManage> cityManageList;
    private List<Integer> checkedIds;
    private List<CityManageBo> cityManageBoList;

    public List<CityManage> getCityManageList() {
        return cityManageList;
    }

    public void setCityManageList(List<CityManage> cityManageList) {
        this.cityManageList = cityManageList;
    }

    public List<Integer> getCheckedIds() {
        return checkedIds;
    }

    public void setCheckedIds(List<Integer> checkedIds) {
        this.checkedIds = checkedIds;
    }

    public List<CityManageBo> getCityManageBoList() {
        return cityManageBoList;
    }

    public void setCityManageBoList(List<CityManageBo> cityManageBoList) {
        this.cityManageBoList = cityManageBoList;
    }

}
