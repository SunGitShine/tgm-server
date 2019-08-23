package com.juma.tgm.fms.domain;

import java.io.Serializable;

public class Task implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -9115669965539954570L;

    private Integer reconciliationId;
    
    private String taskId;
    
    private String approvalKey;
    
    private String comment;

    public Integer getReconciliationId() {
        return reconciliationId;
    }

    public void setReconciliationId(Integer reconciliationId) {
        this.reconciliationId = reconciliationId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getApprovalKey() {
        return approvalKey;
    }

    public void setApprovalKey(String approvalKey) {
        this.approvalKey = approvalKey;
    }
    
}
