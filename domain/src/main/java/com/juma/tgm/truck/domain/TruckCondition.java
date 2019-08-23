package com.juma.tgm.truck.domain;

import java.io.Serializable;

public class TruckCondition implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -8604671840401420543L;

    private String departmentCode;
    
    private Integer entryLicense;
    
    private Integer status;

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public Integer getEntryLicense() {
        return entryLicense;
    }

    public void setEntryLicense(Integer entryLicense) {
        this.entryLicense = entryLicense;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
}
