package com.juma.tgm.task.domain.ext;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "任务进度")
public class GroupTaskWorkStatus implements Serializable {

    @ApiModelProperty(value = "配送日状态 0:运行中 1:已完成 2:不配送 3:运力冲突")
    private Integer workStatus;

    @ApiModelProperty(value = "天数")
    private Integer days;

    public Integer getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(Integer workStatus) {
        this.workStatus = workStatus;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }
}
