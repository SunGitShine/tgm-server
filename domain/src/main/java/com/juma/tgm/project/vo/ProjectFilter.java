package com.juma.tgm.project.vo;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.juma.tgm.project.domain.v2.Project;

public class ProjectFilter extends Project implements Serializable {

    private static final long serialVersionUID = -5400972547103031155L;
    private Integer crmCustomerId;
    // 运营专员或项目经理
    private Integer userId;
    private Integer backPageSize;
    private List<String> areaCodeList;
    private List<Integer> projectStatusList;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBackPageSize() {
        return backPageSize;
    }

    public void setBackPageSize(Integer backPageSize) {
        this.backPageSize = backPageSize;
    }

    public List<String> getAreaCodeList() {
        if (CollectionUtils.isEmpty(areaCodeList)) {
            return areaCodeList;
        }

        if (areaCodeList.contains("00")) {
            areaCodeList.remove("00");
        }
        return areaCodeList;
    }

    public void setAreaCodeList(List<String> areaCodeList) {
        this.areaCodeList = areaCodeList;
    }

    public List<Integer> getProjectStatusList() {
        return projectStatusList;
    }

    public void setProjectStatusList(List<Integer> projectStatusList) {
        this.projectStatusList = projectStatusList;
    }

    public Integer getCrmCustomerId() {
        return crmCustomerId;
    }

    public void setCrmCustomerId(Integer crmCustomerId) {
        this.crmCustomerId = crmCustomerId;
    }
}
