package com.juma.tgm.customerManager.domain.vo.taskWaybill;

import com.juma.tgm.customerManager.domain.TaskWaybillTemplate;

import java.io.Serializable;

/**
 * @ClassName: TaskWaybillTemplateQueryVo
 * @Description:
 * @author: liang
 * @date: 2018-09-28 16:12
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
public class TaskWaybillTemplateQueryVo extends TaskWaybillTemplate implements Serializable {

    //客户名称
    private String customerName;
    //用车人名称
    private String truckCustomer;
    //项目名称
    private String projectName;
    //今日任务
    private String days;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getTruckCustomer() {
        return truckCustomer;
    }

    public void setTruckCustomer(String truckCustomer) {
        this.truckCustomer = truckCustomer;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }
}
