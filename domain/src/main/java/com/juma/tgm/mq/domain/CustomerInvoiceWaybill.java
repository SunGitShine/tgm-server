package com.juma.tgm.mq.domain;

import java.io.Serializable;

/**
 * 功能 :
 *
 * @author : Bruce(刘正航) 10:59 2019-07-02
 */
public class CustomerInvoiceWaybill implements Serializable {
    /**项目ID**/
    private Integer projectId;
    /**项目名称**/
    private String projectName;
    /**对账日期**/
    private String reconciliationDate;
    /**运单时间:最小完成时间/最大完成时间**/
    private String waybillFinishTime;
    /**用车时间:最小用车时间/最大用车时间**/
    private String planDeliveryTime;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getReconciliationDate() {
        return reconciliationDate;
    }

    public void setReconciliationDate(String reconciliationDate) {
        this.reconciliationDate = reconciliationDate;
    }

    public String getWaybillFinishTime() {
        return waybillFinishTime;
    }

    public void setWaybillFinishTime(String waybillFinishTime) {
        this.waybillFinishTime = waybillFinishTime;
    }

    public String getPlanDeliveryTime() {
        return planDeliveryTime;
    }

    public void setPlanDeliveryTime(String planDeliveryTime) {
        this.planDeliveryTime = planDeliveryTime;
    }
}
