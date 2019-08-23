package com.juma.tgm.customerManager.domain.vo.taskWaybill;

import com.alibaba.fastjson.annotation.JSONField;
import com.juma.tgm.customerManager.domain.Task4Waybill;
import com.juma.tgm.customerManager.domain.Task4WaybillReport;
import com.juma.tgm.customerManager.domain.TaskWaybillTemplate;
import com.juma.tgm.project.domain.Project;
import com.juma.tgm.project.domain.RoadMap;

import java.io.Serializable;

/**
 * @ClassName: Task4WaybillReportDetail
 * @Description:
 * @author: liang
 * @date: 2018-09-30 11:21
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
public class Task4WaybillReportDetailVo implements Serializable {

    /**
     * 项目信息
     */
    @JSONField(serialize = false)
    private Project project;

    /**
     * 模板信息
     */
    @JSONField(serialize = false)
    private TaskWaybillTemplate taskWaybillTemplate;

    /**
     * 线路概要
     */
    @JSONField(serialize = false)
    private RoadMap roadMap;

    /**
     * 任务信息
     */
    @JSONField(serialize = false)
    private Task4Waybill task4Waybill;

    /**
     * 报告结果
     */
    private Task4WaybillReport task4WaybillReport;


    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public TaskWaybillTemplate getTaskWaybillTemplate() {
        return taskWaybillTemplate;
    }

    public void setTaskWaybillTemplate(TaskWaybillTemplate taskWaybillTemplate) {
        this.taskWaybillTemplate = taskWaybillTemplate;
    }

    public RoadMap getRoadMap() {
        return roadMap;
    }

    public void setRoadMap(RoadMap roadMap) {
        this.roadMap = roadMap;
    }

    public Task4Waybill getTask4Waybill() {
        return task4Waybill;
    }

    public void setTask4Waybill(Task4Waybill task4Waybill) {
        this.task4Waybill = task4Waybill;
    }

    public Task4WaybillReport getTask4WaybillReport() {
        return task4WaybillReport;
    }

    public void setTask4WaybillReport(Task4WaybillReport task4WaybillReport) {
        this.task4WaybillReport = task4WaybillReport;
    }

    /**
     * 线路名称
     * @return
     */
    public String getRoadMapName(){
        if(this.taskWaybillTemplate == null) return null;

        if(this.taskWaybillTemplate.getRoadMapId() == null) return null;

        if(this.roadMap == null) return null;

        return roadMap.getName();

    }

    /**
     * 项目名称
     * @return
     */
    public String getProjectName(){
        if(this.project == null) return null;

        return this.project.getName();
    }

    /**
     * 用车时间
     * @return
     */
    public String getPlayDeliveryTime(){
        if(this.taskWaybillTemplate == null) return null;

        return this.taskWaybillTemplate.getDeliveryTimePoint();
    }
}
