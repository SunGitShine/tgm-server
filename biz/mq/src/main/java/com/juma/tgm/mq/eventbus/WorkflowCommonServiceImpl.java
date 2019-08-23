package com.juma.tgm.mq.eventbus;

import com.alibaba.fastjson.JSON;
import com.giants.common.exception.BusinessException;
import com.google.common.collect.Maps;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.tgm.common.Constants;
import com.juma.tgm.tool.domain.ExecuteWorkflowInfo;
import com.juma.tgm.tools.service.WorkflowCommonService;
import com.juma.workflow.core.domain.ProcessInstance;
import com.juma.workflow.core.service.ProcessService;
import com.juma.workflow.core.service.WorkflowSupportServer;
import com.third.eventbus.EventBus;
import com.third.service.boot.starter.eventbus.domain.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 功能 : 
 *
 * @author : Bruce(刘正航) 14:42 2019-05-16
 */
@Slf4j
@Service
public class WorkflowCommonServiceImpl implements WorkflowCommonService {

    private static final String EVENT_NAME = "WorkflowExternalNextEventBusKey";

    @Autowired
    private EventBus eventBus;

    @Autowired
    private ProcessService processService;

    @Autowired
    private WorkflowSupportServer workflowSupportServer;

    /**启动工作流**/
    @Override
    public ProcessInstance startWorkflow(final ExecuteWorkflowInfo executeWorkflowInfo, final LoginEmployee loginUser) throws BusinessException {
        executeWorkflowInfo.getVariables().put("number", executeWorkflowInfo.getNumber());
        try {
            log.info("启动工作流: 业务:{},唯一标识: {}",executeWorkflowInfo.getWorkflowDesc(),executeWorkflowInfo.getBusinessKey());
            return processService.startProcessInstance(
                    executeWorkflowInfo.getProcessDefinitionKey(),
                    executeWorkflowInfo.getBusinessKey(),
                    executeWorkflowInfo.getVariables(), loginUser);
        } catch (BusinessException e){
            log.warn("启动工作流异常: 业务:{},唯一标识: {},错误信息: {}" , executeWorkflowInfo.getWorkflowDesc(), executeWorkflowInfo.getBusinessKey(), e.getMessage());
            throw e;
        } catch (Exception e) {
            log.warn("启动工作流异常: 业务:{},唯一标识: {},错误信息: {}" , executeWorkflowInfo.getWorkflowDesc(), executeWorkflowInfo.getBusinessKey(), e.getMessage());
            e.printStackTrace();
            throw new BusinessException("unknown.error", "外部错误:"+e.getMessage());
        }
    }

    /**放弃申请**/
    @Override
    public void giveUpWorkflow(final ExecuteWorkflowInfo executeWorkflowInfo, final LoginEmployee loginUser) throws BusinessException {
        try {
            log.info("放弃申请工作流: 业务:{},唯一标识: {}",executeWorkflowInfo.getWorkflowDesc(),executeWorkflowInfo.getBusinessKey());
            Event<String> event = new Event<>();
            event.setEventId(EVENT_NAME);
            event.setSystemAuthKey(Constants.SYSTEM_NAME.toLowerCase());
            Map<String, Object> data = Maps.newHashMap();
            data.put("nextKeyCode", "CANCEL");
            data.put("processInstanceId", executeWorkflowInfo.getProcessInstanceId());
            data.put("businessKey", executeWorkflowInfo.getBusinessKey());
            data.put("number", executeWorkflowInfo.getNumber());
            //审核人信息
            data.put("userId", loginUser.getUserId());
            data.put("employeeId", loginUser.getEmployeeId());
            data.put("tenantId", loginUser.getTenantId());
            event.setValue(JSON.toJSONString(data));
            eventBus.push(event);
        } catch (BusinessException e){
            log.warn("放弃申请工作流异常: 业务:{},唯一标识: {},错误信息: {}" , executeWorkflowInfo.getWorkflowDesc(), executeWorkflowInfo.getBusinessKey(), e.getMessage());
            throw e;
        } catch (Exception e) {
            log.warn("放弃申请工作流异常: 业务:{},唯一标识: {},错误信息: {}" , executeWorkflowInfo.getWorkflowDesc(), executeWorkflowInfo.getBusinessKey(), e.getMessage());
            e.printStackTrace();
            throw new BusinessException("unknown.error", "外部错误:"+e.getMessage());
        }
    }

    /**撤销申请**/
    @Override
    public void revokeWorkflow(final ExecuteWorkflowInfo executeWorkflowInfo, final LoginEmployee loginUser) throws BusinessException {
        executeWorkflowInfo.getVariables().put("number", executeWorkflowInfo.getNumber());
        try {
            log.info("撤销申请工作流: 业务:{},唯一标识: {}",executeWorkflowInfo.getWorkflowDesc(),executeWorkflowInfo.getBusinessKey());
            workflowSupportServer.revokeProcessInstance(
                    executeWorkflowInfo.getProcessDefinitionKey(),
                    executeWorkflowInfo.getBusinessKey(),
                    executeWorkflowInfo.getReason(),
                    loginUser);
        } catch (BusinessException e){
            log.warn("撤销申请工作流异常: 业务:{},唯一标识: {},错误信息: {}" , executeWorkflowInfo.getWorkflowDesc(), executeWorkflowInfo.getBusinessKey(), e.getMessage());
            throw e;
        } catch (Exception e) {
            log.warn("撤销申请工作流异常: 业务:{},唯一标识: {},错误信息: {}" , executeWorkflowInfo.getWorkflowDesc(), executeWorkflowInfo.getBusinessKey(), e.getMessage());
            e.printStackTrace();
            throw new BusinessException("unknown.error", "外部错误:"+e.getMessage());
        }
    }

    /**重新申请**/
    @Override
    public void reopenWorkflow(final ExecuteWorkflowInfo executeWorkflowInfo, final LoginEmployee loginUser) throws BusinessException {
        try {
            log.info("驳回后重新发起工作流: 业务:{},唯一标识: {}",executeWorkflowInfo.getWorkflowDesc(),executeWorkflowInfo.getBusinessKey());
            Event<String> event = new Event<>();
            event.setEventId(EVENT_NAME);
            event.setSystemAuthKey(Constants.SYSTEM_NAME.toLowerCase());
            Map<String, Object> data = Maps.newHashMap();
            data.put("nextKeyCode", "REAPPLY");
            data.put("processInstanceId", executeWorkflowInfo.getProcessInstanceId());
            data.put("businessKey", executeWorkflowInfo.getBusinessKey());
            data.put("number", executeWorkflowInfo.getNumber());
            //审核人信息
            data.put("userId", loginUser.getUserId());
            data.put("employeeId", loginUser.getEmployeeId());
            data.put("tenantId", loginUser.getTenantId());
            data.put("variables", executeWorkflowInfo.getVariables());
            event.setValue(JSON.toJSONString(data));
            eventBus.push(event);
        } catch (BusinessException e){
            log.warn("驳回后重新发起工作流异常: 业务:{},唯一标识: {},错误信息: {}" , executeWorkflowInfo.getWorkflowDesc(), executeWorkflowInfo.getBusinessKey(), e.getMessage());
            throw e;
        } catch (Exception e) {
            log.warn("驳回后重新发起工作流异常: 业务:{},唯一标识: {},错误信息: {}" , executeWorkflowInfo.getWorkflowDesc(), executeWorkflowInfo.getBusinessKey(), e.getMessage());
            e.printStackTrace();
            throw new BusinessException("unknown.error", "外部错误:"+e.getMessage());
        }
    }
}
