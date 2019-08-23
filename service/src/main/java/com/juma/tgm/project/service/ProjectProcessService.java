package com.juma.tgm.project.service;

import com.giants.common.exception.BusinessException;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.mq.domain.WorkFlowMessage;
import com.juma.tgm.project.domain.v2.ProjectWorkflow;
import com.juma.tgm.project.vo.v2.ProjectWorkflowVo;
import com.juma.tgm.tool.domain.ExecuteWorkflowInfo;
import com.juma.workflow.core.domain.TaskDetail;

/**
 * 项目工作流处理
 */
public interface ProjectProcessService {

	/**
	 * 申请启动项目
	 * @param projectWorkflow
	 * @param loginEmployee
	 */
	void submitApplyStartProject(ProjectWorkflow projectWorkflow, LoginEmployee loginEmployee) throws BusinessException;

	/**
	 * 申请暂停项目
	 * @param projectWorkflow
	 * @param loginEmployee
	 * @throws BusinessException
	 */
	void submitApplyPauseProject(ProjectWorkflow projectWorkflow, LoginEmployee loginEmployee) throws BusinessException;

	/**
	 * 申请结束项目
	 * @param projectWorkflow
	 * @param loginEmployee
	 * @throws BusinessException
	 */
	void submitApplyEndProject(ProjectWorkflow projectWorkflow, LoginEmployee loginEmployee) throws BusinessException;

	/**
	 * 申请恢复项目
	 * @param projectWorkflow
	 * @param loginEmployee
	 * @throws BusinessException
	 */
	void submitApplyRecoverProject(ProjectWorkflow projectWorkflow, LoginEmployee loginEmployee) throws BusinessException;

	/**
	 * 撤销工作流
	 * @param projectWorkflowId
	 * @param loginEmployee
	 * @throws BusinessException
	 */
	void cancelWorkFlowTask(Integer projectWorkflowId, LoginEmployee loginEmployee) throws BusinessException;

	/**
	 * 审批工作流
	 * @param message
	 * @throws BusinessException
	 */
	void finishWorkFlowTask(WorkFlowMessage message) throws BusinessException;

	/**
	 * 根据项目ID获取
	 *
	 * @param projectId
	 * @return
	 * @throws BusinessException
	 */
	ProjectWorkflow findByProjectId(Integer projectId) throws BusinessException;

	/**
	 * 重新申请项目审批记录
	 * @param projectWorkflow
	 * @throws BusinessException
	 */
	void reapplyProjectWorkflow(ProjectWorkflow projectWorkflow, LoginEmployee loginEmployee) throws BusinessException;

	/**
	 * 重新申请通知工作流
	 * @param executeWorkflowInfo
	 * @throws BusinessException
	 */
	void reapplyToWorkflow(ExecuteWorkflowInfo executeWorkflowInfo, LoginEmployee loginEmployee) throws BusinessException;

	/**
	 * 放弃申请通知工作流
	 * @param executeWorkflowInfo
	 * @throws BusinessException
	 */
	void giveUpToWorkflow(ExecuteWorkflowInfo executeWorkflowInfo, LoginEmployee loginEmployee) throws BusinessException;

	/**
	 * 查询项目审批详情
	 * @return
	 * @throws BusinessException
	 */
	ProjectWorkflowVo findProjectWorkflowDetail(Integer projectWorkflowId, LoginUser loginUser) throws BusinessException;

	/**
	 * 通过流程id查询任务详情
	 * @param processInstanceId
	 * @return
	 * @throws BusinessException
	 */
	TaskDetail findTaskByProcessInstanceId(String processInstanceId, LoginEmployee loginEmployee) throws BusinessException;

	/**
	 *
	 */
	void updateProjectStatusTimer();
}
