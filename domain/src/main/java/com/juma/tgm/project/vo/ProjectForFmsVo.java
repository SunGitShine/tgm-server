package com.juma.tgm.project.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.juma.tgm.project.domain.Project;

import java.io.Serializable;

/**
 * @ClassName: ProjectForFmsVo
 * @Description:
 * @author: liang
 * @date: 2018-08-20 14:59
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
public class ProjectForFmsVo implements Serializable {

    @JSONField(serialize = false)
    protected Project project;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }


    public Integer getProjectId() {
        if (this.project == null) return null;
        return this.project.getProjectId();
    }

    public String getName() {
        if(this.project == null) return null;

        return this.project.getName();
    }
}
