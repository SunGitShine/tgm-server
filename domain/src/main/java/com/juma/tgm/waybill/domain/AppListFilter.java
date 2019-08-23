package com.juma.tgm.waybill.domain;

public class AppListFilter {

    private Integer tenantId;

    private String areaCode;

    public AppListFilter() {}

    public AppListFilter(Integer tenantId, String areaCode) {
        this.tenantId = tenantId;
        this.areaCode = areaCode;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

}
