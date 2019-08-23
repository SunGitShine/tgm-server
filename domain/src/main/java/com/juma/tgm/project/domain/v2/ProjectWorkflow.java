package com.juma.tgm.project.domain.v2;

import java.io.Serializable;
import java.util.Date;

public class ProjectWorkflow implements Serializable {
    private Integer projectWorkflowId;

    private Integer projectId;

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

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
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

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ProjectWorkflow other = (ProjectWorkflow) that;
        return (this.getProjectWorkflowId() == null ? other.getProjectWorkflowId() == null : this.getProjectWorkflowId().equals(other.getProjectWorkflowId()))
            && (this.getProjectId() == null ? other.getProjectId() == null : this.getProjectId().equals(other.getProjectId()))
            && (this.getProjectWorkflowType() == null ? other.getProjectWorkflowType() == null : this.getProjectWorkflowType().equals(other.getProjectWorkflowType()))
            && (this.getAuditStatus() == null ? other.getAuditStatus() == null : this.getAuditStatus().equals(other.getAuditStatus()))
            && (this.getProcessInstanceId() == null ? other.getProcessInstanceId() == null : this.getProcessInstanceId().equals(other.getProcessInstanceId()))
            && (this.getSubmitter() == null ? other.getSubmitter() == null : this.getSubmitter().equals(other.getSubmitter()))
            && (this.getSubmitTime() == null ? other.getSubmitTime() == null : this.getSubmitTime().equals(other.getSubmitTime()))
            && (this.getExcuteTime() == null ? other.getExcuteTime() == null : this.getExcuteTime().equals(other.getExcuteTime()))
            && (this.getReason() == null ? other.getReason() == null : this.getReason().equals(other.getReason()))
            && (this.getAttachment() == null ? other.getAttachment() == null : this.getAttachment().equals(other.getAttachment()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getProjectWorkflowId() == null) ? 0 : getProjectWorkflowId().hashCode());
        result = prime * result + ((getProjectId() == null) ? 0 : getProjectId().hashCode());
        result = prime * result + ((getProjectWorkflowType() == null) ? 0 : getProjectWorkflowType().hashCode());
        result = prime * result + ((getAuditStatus() == null) ? 0 : getAuditStatus().hashCode());
        result = prime * result + ((getProcessInstanceId() == null) ? 0 : getProcessInstanceId().hashCode());
        result = prime * result + ((getSubmitter() == null) ? 0 : getSubmitter().hashCode());
        result = prime * result + ((getSubmitTime() == null) ? 0 : getSubmitTime().hashCode());
        result = prime * result + ((getExcuteTime() == null) ? 0 : getExcuteTime().hashCode());
        result = prime * result + ((getReason() == null) ? 0 : getReason().hashCode());
        result = prime * result + ((getAttachment() == null) ? 0 : getAttachment().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", projectWorkflowId=").append(projectWorkflowId);
        sb.append(", projectId=").append(projectId);
        sb.append(", projectWorkflowType=").append(projectWorkflowType);
        sb.append(", auditStatus=").append(auditStatus);
        sb.append(", processInstanceId=").append(processInstanceId);
        sb.append(", submitter=").append(submitter);
        sb.append(", submitTime=").append(submitTime);
        sb.append(", excuteTime=").append(excuteTime);
        sb.append(", reason=").append(reason);
        sb.append(", attachment=").append(attachment);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public enum Column {
        projectWorkflowId,
        projectId,
        projectWorkflowType,
        auditStatus,
        processInstanceId,
        submitter,
        submitTime,
        excuteTime,
        reason,
        attachment;

        public String asc() {
            return column() + " ASC";
        }

        public String desc() {
            return column() + " DESC";
        }

        private String column() {
            StringBuilder buffer = new StringBuilder();
            char[] charArray = this.name().toCharArray();
            for(char ch : charArray) {
                if(Character.isUpperCase(ch)){
                    buffer.append("_");
                    buffer.append(Character.toLowerCase(ch));
                } else {
                    buffer.append(ch);
                }
            }
            return buffer.toString();
        }
    }
}