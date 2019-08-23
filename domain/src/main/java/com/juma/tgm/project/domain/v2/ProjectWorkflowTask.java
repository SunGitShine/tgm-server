package com.juma.tgm.project.domain.v2;

import java.io.Serializable;

public class ProjectWorkflowTask implements Serializable{

	private Integer projectWorkflowId;

	private String taskId;

	private String approvalKey;

	private String comment;

	public Integer getProjectWorkflowId() {
		return projectWorkflowId;
	}

	public void setProjectWorkflowId(Integer projectWorkflowId) {
		this.projectWorkflowId = projectWorkflowId;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
