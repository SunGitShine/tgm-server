package com.juma.tgm.project.vo.v2;

import java.io.Serializable;
import java.util.List;

public class ProjectMemberVo implements Serializable {

    private Integer projectId;

    private List<ProjectMemberUserVo> projectMember;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public List<ProjectMemberUserVo> getProjectMember() {
        return projectMember;
    }

    public void setProjectMember(List<ProjectMemberUserVo> projectMember) {
        this.projectMember = projectMember;
    }
}