package com.juma.tgm.task.dto.manage;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "任务查询")
public class TaskFilter implements Serializable {

    @ApiModelProperty(value = "租户")
    private Integer tenantId;

    @ApiModelProperty(value = "任务编号")
    private String taskNo;

    @ApiModelProperty(value = "任务状态  0待发送邀请1任务邀请中2待生效3运行中4已暂停5已结束6已取消")
    private Integer taskStatus;

    @ApiModelProperty(value = "业务区域")
    private List<String> areaCodeList;

    @ApiModelProperty(value = "邀请状态  0：待回复1：已中选2：已拒绝3：已失效4：已过期")
    private Integer ackStatus;

    @ApiModelProperty(value = "承运商id")
    private Integer vendorId;

    @ApiModelProperty(value = "客户id")
    private Integer customerId;

    @ApiModelProperty(value = "项目id")
    private Integer projectId;

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

    public String getTaskNo() {
        return taskNo;
    }

    public void setTaskNo(String taskNo) {
        this.taskNo = taskNo;
    }

    public List<String> getAreaCodeList() {
        return areaCodeList;
    }

    public void setAreaCodeList(List<String> areaCodeList) {
        this.areaCodeList = areaCodeList;
    }

    public Integer getAckStatus() {
        return ackStatus;
    }

    public void setAckStatus(Integer ackStatus) {
        this.ackStatus = ackStatus;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

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
}
