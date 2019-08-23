package com.juma.tgm.task.dto.gateway;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "任务查询")
public class TaskFilter implements Serializable {

    @ApiModelProperty(value = "客户id")
    private Integer customerId;

    @ApiModelProperty(value = "项目id")
    private Integer projectId;

    @ApiModelProperty(value = "任务状态")
    private List<Integer> taskStatusList = new ArrayList<>();

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

    public List<Integer> getTaskStatusList() {
        return taskStatusList;
    }

    public void setTaskStatusList(List<Integer> taskStatusList) {
        this.taskStatusList = taskStatusList;
    }

}
