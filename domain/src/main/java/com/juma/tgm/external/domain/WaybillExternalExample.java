package com.juma.tgm.external.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @ClassName WaybillExternalExample.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2018年7月20日 上午11:14:38
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class WaybillExternalExample implements Serializable {

    /**
     * 客户ID
     */
    private Integer crmCustomerId;
    /**
     * 租户ID
     */
    private Integer tenantId;
    /**
     * 查询时间范围开始时间，可为空
     */
    private Date startTime;
    /**
     * 查询时间范围结束时间，可为空
     */
    private Date endTime;
    /**
     * 查询运单的状态列表，可为空 具体运单状态可查看Waybill.StatusView
     */
    private List<Integer> listStatusView;

    public Integer getCrmCustomerId() {
        return crmCustomerId;
    }

    public void setCrmCustomerId(Integer crmCustomerId) {
        this.crmCustomerId = crmCustomerId;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public List<Integer> getListStatusView() {
        return listStatusView;
    }

    public void setListStatusView(List<Integer> listStatusView) {
        this.listStatusView = listStatusView;
    }

    @Override
    public String toString() {
        return "WaybillExternalExample [crmCustomerId=" + crmCustomerId + ", tenantId=" + tenantId + ", startTime="
                + startTime + ", endTime=" + endTime + ", listStatusView=" + listStatusView + "]";
    }
}
