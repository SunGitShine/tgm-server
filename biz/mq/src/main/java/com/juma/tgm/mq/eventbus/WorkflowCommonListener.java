package com.juma.tgm.mq.eventbus;

import com.alibaba.fastjson.JSON;
import com.giants.common.exception.BusinessException;
import com.juma.tgm.common.workflow.WorkflowConstants;
import com.juma.tgm.fms.service.v3.AdjustForMasterAddService;
import com.juma.tgm.fms.service.v3.ReconcilicationForPayableService;
import com.juma.tgm.fms.service.v3.ReconcilicationForReceivableService;
import com.juma.tgm.project.service.ProjectProcessService;
import com.third.eventbus.handler.EventHandler;
import com.third.service.boot.starter.eventbus.domain.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 功能 : 工作流监听
 *
 * @author : Bruce(刘正航) 14:42 2019-05-16
 */
@Slf4j
@Component
public class WorkflowCommonListener extends AbstractWorkflowCommonListener{

    @Autowired
    private AdjustForMasterAddService adjustForMasterAddService;
    @Autowired
    private ProjectProcessService projectProcessService;
    @Autowired
    private ReconcilicationForPayableService reconcilicationForPayableService;
    @Autowired
    private ReconcilicationForReceivableService reconcilicationForReceivableService;

    /**调整单:应付(承运商):**/
    @EventHandler(value = WorkflowConstants.PROCESS_KEY_ADJUST_PAYABLE_CREATE)
    public void doWorkflowForAdjustPayable(Event event) throws BusinessException {
        log.info("listener workflow for adjust payable param :{}", JSON.toJSONString(event));
        adjustForMasterAddService.doAdjustWorkflow(parseMessage(event));
    }

    /**调整单:应付-对账前(承运商):**/
    @EventHandler(value = WorkflowConstants.PROCESS_KEY_ADJUST_BEFORE_PAYABLE_CREATE)
    public void doWorkflowForAdjustBeforePayable(Event event) throws BusinessException {
        log.info("listener workflow for adjust before payable param :{}", JSON.toJSONString(event));
        adjustForMasterAddService.doAdjustWorkflow(parseMessage(event));
    }

    /**调整单:应收(客户):**/
    @EventHandler(value = WorkflowConstants.PROCESS_KEY_ADJUST_RECEIVE_CREATE)
    public void doWorkflowForAdjustReceive(Event event) throws BusinessException {
        log.info("listener workflow for adjust receive param :{}", JSON.toJSONString(event));
        adjustForMasterAddService.doAdjustWorkflow(parseMessage(event));
    }

    /**调整单:应收(客户):**/
    @EventHandler(value = WorkflowConstants.PROCESS_KEY_ADJUST_BEFORE_RECEIVE_CREATE)
    public void doWorkflowForAdjustBeforeReceive(Event event) throws BusinessException {
        log.info("listener workflow for adjust before receive param :{}", JSON.toJSONString(event));
        adjustForMasterAddService.doAdjustWorkflow(parseMessage(event));
    }

    /**调整单:新建,暂时不用**/
    @EventHandler(value = WorkflowConstants.PROCESS_KEY_ADJUST_CREATE)
    public void doWorkflowForAdjust(Event event) throws BusinessException {
        log.info("listener workflow for adjust param :{}", JSON.toJSONString(event));
        adjustForMasterAddService.doAdjustWorkflow(parseMessage(event));
    }

    /**应收对账单:**/
    @EventHandler(value = WorkflowConstants.PROCESS_KEY_RECONCILICATION_RECEIVABLE)
    public void doWorkflowForPayable(Event event) throws BusinessException {
        log.info("listener workflow for reconciliation receive param :{}", JSON.toJSONString(event));
        reconcilicationForReceivableService.finishWorkFlowTask(parseMessage(event));
    }

    /**应付对账单:**/
    @EventHandler(value = WorkflowConstants.PROCESS_KEY_RECONCILICATION_PAYABLE)
    public void doWorkflowForReceive(Event event) throws BusinessException {
        log.info("listener workflow for reconciliation payable param :{}", JSON.toJSONString(event));
        reconcilicationForPayableService.doFinishWorkFlowTask(parseMessage(event));
    }

    /**项目开始:**/
    @EventHandler(value = WorkflowConstants.PROCESS_KEY_PROJECT_START)
    public void doWorkflowForProjectStart(Event event) throws BusinessException {
        log.info("listener workflow for project start param :{}", JSON.toJSONString(event));
        projectProcessService.finishWorkFlowTask(parseMessage(event));
    }

    /**项目暂停:**/
    @EventHandler(value = WorkflowConstants.PROCESS_KEY_PROJECT_PAUSE)
    public void doWorkflowForProjectPause(Event event) throws BusinessException {
        log.info("listener workflow for project pause param :{}", JSON.toJSONString(event));
        projectProcessService.finishWorkFlowTask(parseMessage(event));
    }

    /**项目结束:**/
    @EventHandler(value = WorkflowConstants.PROCESS_KEY_PROJECT_END)
    public void doWorkflowForProjectEnd(Event event) throws BusinessException {
        log.info("listener workflow for project end param :{}", JSON.toJSONString(event));
        projectProcessService.finishWorkFlowTask(parseMessage(event));
    }

    /**项目恢复:**/
    @EventHandler(value = WorkflowConstants.PROCESS_KEY_PROJECT_RECOVER)
    public void doWorkflowForProjectRecover(Event event) throws BusinessException {
        log.info("listener workflow for project recover param :{}", JSON.toJSONString(event));
        projectProcessService.finishWorkFlowTask(parseMessage(event));
    }

}
