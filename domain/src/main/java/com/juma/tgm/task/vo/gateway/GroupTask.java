package com.juma.tgm.task.vo.gateway;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "任务分组")
public class GroupTask implements Serializable {

    @ApiModelProperty(value = "项目id")
    private Integer projectId;

    @ApiModelProperty(value = "项目编号")
    private String projectNo;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "项目经理")
    private String projectManagerName;

    @ApiModelProperty(value = "项目经理电话")
    private String projectManagerTel;

    @ApiModelProperty(value = "任务数量")
    private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectManagerName() {
        return projectManagerName;
    }

    public void setProjectManagerName(String projectManagerName) {
        this.projectManagerName = projectManagerName;
    }

    public String getProjectManagerTel() {
        return projectManagerTel;
    }

    public void setProjectManagerTel(String projectManagerTel) {
        this.projectManagerTel = projectManagerTel;
    }

}
