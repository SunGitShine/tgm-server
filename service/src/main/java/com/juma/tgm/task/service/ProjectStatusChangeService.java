package com.juma.tgm.task.service;

public interface ProjectStatusChangeService {

    /**
     * 项目状态变更为已暂停
     * @param projectId
     */
    void pause(Integer projectId);

    /**
     * 项目状态变更为已结束
     * @param projectId
     */
    void finish(Integer projectId);

}
