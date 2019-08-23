package com.juma.tgm.export.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/5/7 0007.
 */
public class ExportParams implements Serializable {

    private Integer tenantId;
    private Integer userId;
    private Integer systemId;
    private List<FilterParam> filterParamList;
    private List<SelectParam> selectParamList;
    private String fileName;


    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSystemId() {
        return systemId;
    }

    public void setSystemId(Integer systemId) {
        this.systemId = systemId;
    }

    public List<FilterParam> getFilterParamList() {
        return filterParamList;
    }

    public void setFilterParamList(List<FilterParam> filterParamList) {
        this.filterParamList = filterParamList;
    }

    public List<SelectParam> getSelectParamList() {
        return selectParamList;
    }

    public void setSelectParamList(List<SelectParam> selectParamList) {
        this.selectParamList = selectParamList;
    }
}
