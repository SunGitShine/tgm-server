package com.juma.tgm.mq.eventbus;

import com.alibaba.fastjson.JSON;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.tgm.common.workflow.ApprovalStatus;
import com.juma.tgm.mq.domain.WorkFlowMessage;
import com.third.service.boot.starter.eventbus.domain.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.Map;

/**
 * 功能 : 
 *
 * @author : Bruce(刘正航) 17:44 2019-05-16
 */
@Slf4j
public abstract class AbstractWorkflowCommonListener {

    protected WorkFlowMessage parseMessage(Event event) {
        if( null == event ){
            log.warn("事件对象为空,终止处理");
            return null;
        }
        if( null == event.getValue() ){
            log.warn("事件返回结果为空,终止处理");
            return null;
        }
        if( !(event.getValue() instanceof Map) ){
            log.warn("事件返回结果为非Map结构:{},终止处理", JSON.toJSONString(event.getValue()));
            return null;
        }
        Map<String,Object> data = (Map<String, Object>) event.getValue();
        if(CollectionUtils.isEmpty(data)){
            log.warn("事件返回结果为空Map");
            return null;
        }

        LoginEmployee loginEmployee = new LoginEmployee();
        loginEmployee.setUserId((Integer)data.get("examineUserId"));
        loginEmployee.setTenantId((Integer)data.get("examineTenantId"));
        loginEmployee.setTenantCode((String)data.get("examineTenantCode"));
        loginEmployee.setEmployeeId((Integer)data.get("examineEmployeeId"));

        //兼容旧的工作流流程图
        ApprovalStatus status ;
        if(data.get("status") instanceof Integer){
            status = ApprovalStatus.getApprovalWithCode((Integer) data.get("status"));
        }else {
            status = ApprovalStatus.getApprovalWithWorkKey((String) data.get("status"));
        }

        return WorkFlowMessage.builder()
                .processInstanceId((String)data.get("processInstanceId"))
                .number((String)data.get("number"))
                .status(status)
                .businessKey((String)data.get("businessKey"))
                .loginEmployee(loginEmployee)
                .build();
    }
}
