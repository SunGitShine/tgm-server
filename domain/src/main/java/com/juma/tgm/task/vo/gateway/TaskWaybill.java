package com.juma.tgm.task.vo.gateway;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@ApiModel(value = "运单对应任务")
public class TaskWaybill implements Serializable {

    @ApiModelProperty(value = "任务开始时间")
    private Date taskStartDate;

    @ApiModelProperty(value = "任务结束时间")
    private Date taskEndDate;

    @ApiModelProperty(value = "是否标准时间")
    private Boolean isStandardTime;

    @ApiModelProperty(value = "配送周期 比如1,2,3,4")
    private String deliveryPeriod;

    @ApiModelProperty(value = "配送时间 比如09:00")
    private String deliveryPeriodTime;



    public Boolean getIsStandardTime() {
        return isStandardTime;
    }

    public void setIsStandardTime(Boolean isStandardTime) {
        this.isStandardTime = isStandardTime;
    }

    public Date getTaskStartDate() {
        return taskStartDate;
    }

    public void setTaskStartDate(Date taskStartDate) {
        this.taskStartDate = taskStartDate;
    }

    public Date getTaskEndDate() {
        return taskEndDate;
    }

    public void setTaskEndDate(Date taskEndDate) {
        this.taskEndDate = taskEndDate;
    }

    public String getDeliveryPeriod() {
        return deliveryPeriod;
    }

    public void setDeliveryPeriod(String deliveryPeriod) {
        this.deliveryPeriod = deliveryPeriod;
    }

    public String getDeliveryPeriodTime() {
        return deliveryPeriodTime;
    }

    public void setDeliveryPeriodTime(String deliveryPeriodTime) {
        this.deliveryPeriodTime = deliveryPeriodTime;
    }
}
