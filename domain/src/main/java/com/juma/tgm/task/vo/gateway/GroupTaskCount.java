package com.juma.tgm.task.vo.gateway;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "任务分组数量")
public class GroupTaskCount implements Serializable {

    @ApiModelProperty(value = "任务状态 0:待指派运力1:邀请中2:待生效3:运行中4:已暂停5:已结束6:已取消7:已过期")
    private Integer taskStatus;

    @ApiModelProperty(value = "数量")
    private Integer count;

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
