package com.juma.tgm.fms.service.v3;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.fms.domain.v3.ReconciliationExtraForPayable;
import com.juma.tgm.fms.domain.v3.ReconcilicationForPayable;
import com.juma.tgm.fms.domain.v3.ReconcilicationForPayableItem;
import com.juma.tgm.fms.domain.v3.bo.MonthlyBillBo;
import com.juma.tgm.fms.domain.v3.bo.ReconciliationExtraForPayableBo;
import com.juma.tgm.fms.domain.v3.bo.ReconcilicationForPayableBo;
import com.juma.tgm.fms.domain.v3.vo.ReconciliationPayableItemFilter;
import com.juma.tgm.mq.domain.WorkFlowMessage;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillAmount;
import com.juma.workflow.core.domain.TaskDetail;

import java.math.BigDecimal;
import java.util.List;

public interface ReconcilicationForPayableService {

    /**
     * 暂估月账单
     */
    void monthlyBill(String firstDay,String lastDay);

    /**
     * id查询承运商对账单
     *
     * @param reconciliationId
     * @return
     * @throws BusinessException
     */
    ReconcilicationForPayable getReconciliationById(int reconciliationId) throws BusinessException;

    /**
     * 承运商对账单搜索
     *
     * @param pageCondition
     * @param loginUser
     * @return
     * @throws BusinessException
     */
    Page<ReconcilicationForPayableBo> vendorSearch(PageCondition pageCondition, LoginUser loginUser)
            throws BusinessException;

    /**
     * 承运商对账单详情搜索
     *
     * @param pageCondition
     * @param loginUser
     * @return
     * @throws BusinessException
     */
    Page<ReconciliationExtraForPayableBo> vendorSearchDetail(PageCondition pageCondition, LoginUser loginUser)
            throws BusinessException;

    /**
     * 对账单下的运单列表
     *
     * @param pageCondition
     * @param loginUser
     * @return
     * @throws BusinessException
     */
    Page<ReconciliationExtraForPayableBo> waybillDetails(PageCondition pageCondition, LoginUser loginUser)
            throws BusinessException;

    /**
     * 
     * 生成对账单
     * 
     * @author Libin.Wei
     * @Date 2018年11月27日 下午3:12:02
     * @param reconcilicationForPayable
     * @param loginUser
     * @return
     * @throws BusinessException
     */
    Integer insert(ReconcilicationForPayable reconcilicationForPayable, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * 生成对账单详情
     * 
     * @author Libin.Wei
     * @Date 2018年11月27日 下午5:39:30
     * @param reconcilicationForPayableItem
     * @param loginUser
     * @return
     * @throws BusinessException
     */
    void insertItem(ReconcilicationForPayableItem reconcilicationForPayableItem, LoginUser loginUser)
            throws BusinessException;

    /**
     * 对账单表头
     *
     * @param reconciliationId
     * @return
     * @throws BusinessException
     */
    ReconcilicationForPayableBo getReconciliationOverView(int reconciliationId, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * 通过对账单号获取对账单
     *
     * @param reconciliationNo
     */
    ReconcilicationForPayable findByReconciliationNo(String reconciliationNo) throws BusinessException ;

    /**
     * 承运商扣款
     */
    void vendorCharge(ReconciliationExtraForPayable reconciliationExtraForPayable, LoginUser loginUser);

    /**
     * 系统计算计税参考
     */
    BigDecimal taxReference(ReconcilicationForPayableItem reconcilicationForPayableItem);

    /**
     * 审批完成
     *
     * @param message
     * @throws BusinessException
     */
    void doFinishWorkFlowTask(WorkFlowMessage message) throws BusinessException;

    /**
     * 撤销(取消该次对账)
     *
     * @param reconciliationId
     * @param loginEmployee
     * @throws BusinessException
     */
    void cancelReconciliation(int reconciliationId, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * 工作流-撤回(从工作流撤回申请)
     *
     * @param reconciliationId
     * @param loginEmployee
     * @throws BusinessException
     */
    void cancelWorkFlowTask(int reconciliationId, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * 提交到工作流审核
     *
     * @param bo
     * @param loginEmployee
     * @throws BusinessException
     */
    void submitToWorkFlow(ReconcilicationForPayableBo bo, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * 计算毛利率差
     * @param payable
     * @return
     * @throws BusinessException
     */
    BigDecimal calculateProfitRateDiffer(ReconcilicationForPayable payable) throws BusinessException;

    /**
     * 流程相关API
     *
     * @param taskId
     * @param loginEmployee
     * @throws BusinessException
     */
    TaskDetail getWorkflowElement(String taskId, LoginEmployee loginEmployee);

    /**
     * 
     * 根据承运商或司机的ID更改承运商或司机的名称
     * 
     * @author Libin.Wei
     * @Date 2018年12月3日 下午6:36:10
     * @param settleAccountName
     * @param settleAccountId
     * @param settleType
     * @throws BusinessException
     */
    void updateNameBySettleAccountIdAndType(String settleAccountName, Integer settleAccountId, Integer settleType)
            throws BusinessException;

    /**
     * 
     * 修改费用信息
     * 
     * @author Libin.Wei
     * @Date 2018年12月4日 下午2:38:27
     * @param waybillId
     * @param settleFreight
     * @param driverTransportFee
     * @param temporaryTransportFee
     * @throws BusinessException
     */
    void updatePayableItemFee(Integer waybillId, BigDecimal settleFreight, BigDecimal driverTransportFee,
            BigDecimal temporaryTransportFee) throws BusinessException;

    /**
     *
     * 对账单更改子公司
     *
     * @param reconcilicationForPayable
     * @param loginUser
     * @throws BusinessException
     */
    void updateDepartmentId(ReconcilicationForPayable reconcilicationForPayable, LoginUser loginUser) throws BusinessException;


    /**
     * 根据对账单no查询子公司
     */
    ReconcilicationForPayableBo getDepartmentName(ReconcilicationForPayable reconcilicationForPayable);
    
    /**
     * 
     * @Title: sendToFMS   
     * @Description: 工作流超时或业务出错后，流程已经走完，状态不对 手动再次发起
     * @param: @param reconcilicationNo      
     * @return: void      
     * @throws
     */
    void sendToFMS(String reconcilicationNo,LoginEmployee loginEmployee) throws BusinessException;

    /**根据查询条件查询应付对账单子表**/
    List<ReconcilicationForPayableItem> findItemByFilter(ReconciliationPayableItemFilter filter,LoginEmployee loginEmployee) throws BusinessException;

    /**调整单审批成功之后:根据运单金额最新金额,更新应付对账单表对账单金额**/
    void updateSettleFreight(Integer reconcilicationId, Integer adjustForWho, WaybillAmount waybillAmount);

    void resendMonthlyBill(MonthlyBillBo monthlyBillBo);

    /**判断运单的调整单是否审核通过**/
    Boolean checkWaybillHadAdjust(List<Integer> waybillIds);
}
