package com.juma.tgm.tools.service;

import com.giants.common.exception.BusinessException;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.tgm.tool.domain.ExecuteWorkflowInfo;
import com.juma.workflow.core.domain.ProcessInstance;

/**
 * 工作流对接公共类
 * 功能 : 
 * 1.发起审批:创建工作流
 *  发消息
 * 2.中间审批:推进工作流
 *  发消息
 * 3.接收工作流反馈
 *  收消息
 *  不同状态的处理
 *      通过
 *      驳回
 *      放弃
 * 4.撤销工作流
 * @author : Bruce(刘正航) 14:06 2019-05-16
 */
public interface WorkflowCommonService {
    /**发起工作流**/
    ProcessInstance startWorkflow(ExecuteWorkflowInfo startWorkflowInfo, LoginEmployee loginUser) throws BusinessException;

    /**撤销申请工作流**/
    void revokeWorkflow(ExecuteWorkflowInfo executeWorkflowInfo, LoginEmployee loginUser) throws BusinessException;

    /**放弃申请工作流**/
    void giveUpWorkflow(ExecuteWorkflowInfo executeWorkflowInfo, LoginEmployee loginUser) throws BusinessException;

    /**修改后再次审批工作流**/
    void reopenWorkflow(ExecuteWorkflowInfo executeWorkflowInfo, LoginEmployee loginUser) throws BusinessException;
}
