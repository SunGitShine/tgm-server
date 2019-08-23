package com.juma.tgm.task.vo.gateway;


import com.juma.tgm.common.StringTemplateUtils;
import com.juma.tgm.task.domain.ext.GroupTaskWorkStatus;
import com.juma.tgm.task.enums.TaskCalendarEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApiModel(value = "任务日程")
public class TaskCalendarMaster implements Serializable {

    @ApiModelProperty(value = "任务Id")
    private Integer taskId;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "线路名称")
    private String roadMapName;

    @ApiModelProperty(value = "任务进度说明")
    private String taskProgressView;

    @ApiModelProperty(value = "任务进度")
    private List<GroupTaskWorkStatus> groupTaskWorkStatusList = new ArrayList<>();

    @ApiModelProperty(value = "任务日历")
    private List<TaskCalendarDetail> taskCalendars = new ArrayList<>();

    public String getTaskProgressView() {
        Map<String,String> context = new HashMap<>();
        context.put(TaskCalendarEnum.WorkStatus.Finish.getCode() + "","0");
        context.put(TaskCalendarEnum.WorkStatus.Running.getCode() + "","0");
        context.put(TaskCalendarEnum.WorkStatus.NotDelivery.getCode() + "","0");
        context.put(TaskCalendarEnum.WorkStatus.Conflict.getCode() + "","0");

        String template = "完成${1}天 | 进行中${0}天 | 不配送${2}天 | 冲突${3}天";
        setTaskProgressView(StringTemplateUtils.render(template,context));
        if (groupTaskWorkStatusList.isEmpty()) return taskProgressView;
        for (GroupTaskWorkStatus groupTaskWorkStatus : groupTaskWorkStatusList) {
            context.put(groupTaskWorkStatus.getWorkStatus()+"",groupTaskWorkStatus.getDays()+"");
        }
        setTaskProgressView(StringTemplateUtils.render(template,context));
        return taskProgressView;
    }

    public void setTaskProgressView(String taskProgressView) {
        this.taskProgressView = taskProgressView;
    }

    public List<GroupTaskWorkStatus> getGroupTaskWorkStatusList() {
        return groupTaskWorkStatusList;
    }

    public void setGroupTaskWorkStatusList(List<GroupTaskWorkStatus> groupTaskWorkStatusList) {
        this.groupTaskWorkStatusList = groupTaskWorkStatusList;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getRoadMapName() {
        return roadMapName;
    }

    public void setRoadMapName(String roadMapName) {
        this.roadMapName = roadMapName;
    }

    public List<TaskCalendarDetail> getTaskCalendars() {
        return taskCalendars;
    }

    public void setTaskCalendars(List<TaskCalendarDetail> taskCalendars) {
        this.taskCalendars = taskCalendars;
    }
}
