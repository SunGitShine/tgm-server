package com.juma.tgm.fms.service;

import java.math.BigDecimal;
import java.util.List;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.fms.domain.Reconciliation;
import com.juma.tgm.fms.domain.ReconciliationItem;
import com.juma.tgm.fms.domain.Task;
import com.juma.tgm.fms.domain.bo.ReconciliationMaster;
import com.juma.workflow.core.domain.TaskDetail;

public interface ReconciliationService {

    Page<ReconciliationMaster> searchPage(PageCondition cond, LoginUser loginUser);
    
    void createReconciliation(List<Integer> waybillIds, List<String> images,LoginUser loginUser) throws BusinessException;

    void adjustmentReconciliation(Integer reconciliationId, BigDecimal totalFee, List<String> images,LoginUser loginUser) throws BusinessException;
    
    void updateTotalFreight(Integer reconciliationId, BigDecimal totalFreight, LoginUser loginUser) throws BusinessException;
    
    void updateReconciliationItem(ReconciliationItem item) throws BusinessException;
    
    void updateReconciliationItemByCustomerId(ReconciliationItem item) throws BusinessException;
    
    void updateReconciliationItemByProjectId(ReconciliationItem item) throws BusinessException;
    
    void batchUpdateReconciliationItem(List<ReconciliationItem> items) throws BusinessException;
    
    List<ReconciliationMaster> findItemsById(PageCondition cond);
    
    Page<ReconciliationMaster> findItemsPageById(PageCondition cond);
    
    ReconciliationMaster sumItemByReconciliationId(ReconciliationMaster master);
    
    // 流程相关API
    
    TaskDetail getWorkflowElement(String taskId,LoginEmployee loginEmployee);
    
    void completeTask(Task task,LoginEmployee loginEmployee) throws BusinessException;
    
    void sumbitTask(Reconciliation reconciliation,LoginEmployee loginEmployee) throws BusinessException;
    
    void cancelTask(Integer reconciliationId,LoginEmployee loginEmployee) throws BusinessException;
    
    void updateReconciliationItemByWaybillId(ReconciliationItem item) throws BusinessException;
    
    List<ReconciliationItem> findByReconciliationNo(String reconciliationNo);
    
    void test();

     String getReconciliationNo();

}
