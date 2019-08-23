package com.juma.tgm.customerManager.domain.vo.taskWaybill;

import java.io.Serializable;

/**
 * @ClassName: Task4WaybillRelationVo
 * @Description:
 * @author: liang
 * @date: 2018-10-08 17:32
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
public class Task4WaybillRelationVo implements Serializable {

    /**
     * 项目id
     */
    private Integer projectId;

    /**
     * 任务模板 id
     */
    private Integer taskWaybillTemplateId;


    /**
     * 任务id
     */
    private Integer taskId;


    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getTaskWaybillTemplateId() {
        return taskWaybillTemplateId;
    }

    public void setTaskWaybillTemplateId(Integer taskWaybillTemplateId) {
        this.taskWaybillTemplateId = taskWaybillTemplateId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }
}
