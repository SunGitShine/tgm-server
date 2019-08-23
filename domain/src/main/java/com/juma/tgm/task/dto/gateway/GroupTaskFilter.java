package com.juma.tgm.task.dto.gateway;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "任务分组")
public class GroupTaskFilter implements Serializable {

    @ApiModelProperty(value = "租户")
    private Integer tenantId;

    @ApiModelProperty(value = "承运商")
    private Integer vendorId;

    @ApiModelProperty(value = "承运商")
    private Integer projectId;

    @ApiModelProperty(value = "任务状态 0:待指派运力1:邀请中2:待生效3:运行中4:已暂停5:已结束6:已取消")
    private Integer taskStatus;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }
}
