package com.juma.tgm.manage.domain;

import com.juma.tgm.base.domain.BaseDomain;

/**
 * company - 公司
 * 
 * @author 2016-05-09
 * @version 1.0
 */
public class Company extends BaseDomain {

    private static final long serialVersionUID = 1139682834539065301L;
    private Integer companyId;
    private Integer parentCompanyId;
    private String companyCode;
    private Integer status;
    private String companyName;
    private String companyAddress;
    private String companyLegaler;

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getParentCompanyId() {
        return parentCompanyId;
    }

    public void setParentCompanyId(Integer parentCompanyId) {
        this.parentCompanyId = parentCompanyId;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyLegaler() {
        return companyLegaler;
    }

    public void setCompanyLegaler(String companyLegaler) {
        this.companyLegaler = companyLegaler;
    }

}