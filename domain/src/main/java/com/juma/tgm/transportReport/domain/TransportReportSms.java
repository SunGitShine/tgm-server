package com.juma.tgm.transportReport.domain;

import java.io.Serializable;

/**
 * @ClassName TransportReportSms.java
 * @Description 运输报告
 * @author Libin.Wei
 * @Date 2018年8月13日 下午3:36:45
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class TransportReportSms implements Serializable {
    private Integer truckCustomerId;
    private String contactPhone;
    private Integer tenantId;
    private Integer projectId;

    public Integer getTruckCustomerId() {
        return truckCustomerId;
    }

    public void setTruckCustomerId(Integer truckCustomerId) {
        this.truckCustomerId = truckCustomerId;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

}
