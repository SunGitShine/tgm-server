package com.juma.tgm.mq.domain;


import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.tgm.common.workflow.ApprovalStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WorkFlowMessage {
	/**工作流实例ID*/
	private String processInstanceId;
	/**业务单号*/
	private String number;
	/**业务id*/
	private String businessKey;
	/**状态**/
	private ApprovalStatus status;
	/**登录人*/
	private LoginEmployee loginEmployee;
}
