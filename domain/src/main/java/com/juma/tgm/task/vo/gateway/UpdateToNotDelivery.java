package com.juma.tgm.task.vo.gateway;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "不配送")
public class UpdateToNotDelivery implements Serializable {

    @ApiModelProperty(value = "日历id")
    private Integer calendarId;

    @ApiModelProperty(value = "原因种类 1:承运商原因 2:客户原因 3:公司原因")
    private Integer reasonSort;

    @ApiModelProperty(value = "原因")
    private String reason;

    public Integer getCalendarId() {
        return calendarId;
    }

    public void setCalendarId(Integer calendarId) {
        this.calendarId = calendarId;
    }

    public Integer getReasonSort() {
        return reasonSort;
    }

    public void setReasonSort(Integer reasonSort) {
        this.reasonSort = reasonSort;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
