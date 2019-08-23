package com.juma.tgm.project.vo.v2;

import java.io.Serializable;
import java.util.Date;

public class ProjectWorkflowVo implements Serializable {
    private Integer projectWorkflowId;

    private ProjectVo Project;

    private Integer projectWorkflowType;

    private Integer auditStatus;

    private String processInstanceId;

    private Integer submitter;

    private Date submitTime;

    private Date excuteTime;

    private String reason;

    private String attachment;

    private static final long serialVersionUID = 1L;

    public Integer getProjectWorkflowId() {
        return projectWorkflowId;
    }

    public void setProjectWorkflowId(Integer projectWorkflowId) {
        this.projectWorkflowId = projectWorkflowId;
    }

    public ProjectVo getProject() {
        return Project;
    }

    public void setProject(ProjectVo project) {
        Project = project;
    }

    public Integer getProjectWorkflowType() {
        return projectWorkflowType;
    }

    public void setProjectWorkflowType(Integer projectWorkflowType) {
        this.projectWorkflowType = projectWorkflowType;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId == null ? null : processInstanceId.trim();
    }

    public Integer getSubmitter() {
        return submitter;
    }

    public void setSubmitter(Integer submitter) {
        this.submitter = submitter;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public Date getExcuteTime() {
        return excuteTime;
    }

    public void setExcuteTime(Date excuteTime) {
        this.excuteTime = excuteTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment == null ? null : attachment.trim();
    }

}