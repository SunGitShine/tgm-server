package com.juma.tgm.manage.domain;

import com.juma.tgm.base.domain.BaseDomain;

/**
 * company_region - 公司地区
 * 
 * @author 2016-05-09
 * @version 1.0
 */
public class CompanyRegion extends BaseDomain {

    private static final long serialVersionUID = -8251682850483986793L;
    private Integer companyRegionId;
    private Integer companyId;
    private String regionCode;

    public Integer getCompanyRegionId() {
        return companyRegionId;
    }

    public void setCompanyRegionId(Integer companyRegionId) {
        this.companyRegionId = companyRegionId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

}