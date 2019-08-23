package com.juma.tgm.fms.service.v2;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageQueryCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.fms.domain.Reconciliation;
import com.juma.tgm.fms.domain.Task;
import com.juma.tgm.fms.domain.v2.ReconciliationItemNew;
import com.juma.tgm.fms.domain.v2.ReconciliationNew;
import com.juma.tgm.fms.domain.v2.vo.*;
import com.juma.tgm.imageUploadManage.domain.ImageUploadManage;
import com.juma.tgm.project.vo.ProjectForFmsVo;
import com.juma.tgm.project.vo.ProjectQueryVo;

import java.math.BigDecimal;
import java.util.List;

public interface ReconciliationService {

    /********************************对账管理-开始***********************/

    /***
     *
     * 创建对账单
     *
     * @param waybillIds 运单 ids
     *
     * @param loginUser 登录信息
     *
     * */
    String createReconciliation(List<Integer> waybillIds, LoginUser loginUser) throws BusinessException;

    /**
     * 对账单搜索
     *
     * @param params
     * @param loginEmployee
     * @return
     * @throws BusinessException
     */
    Page<ReconciliationVo> reconciliationSearch(PageQueryCondition<ReconciliationQueryVo> params, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * 提交到工作流审核
     *
     * @param reconciliation
     * @param loginEmployee
     * @throws BusinessException
     */
    void submitToWorkFlow(Reconciliation reconciliation, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * 审批完成
     *
     * @param task
     * @param loginEmployee
     * @throws BusinessException
     */
    void finishWorkFlowTask(Task task, LoginEmployee loginEmployee) throws BusinessException;


    /**
     * 工作流-撤回(从工作流撤回申请)
     *
     * @param reconciliationId
     * @param loginEmployee
     * @throws BusinessException
     */
    void cancelWorkFlowTask(int reconciliationId, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * 撤销(取消该次对账)
     *
     * @param reconciliationId
     * @param loginEmployee
     * @throws BusinessException
     */
    void cancelReconciliation(int reconciliationId, LoginEmployee loginEmployee) throws BusinessException;

//    /**
//     * 工作流-驳回
//     *
//     * @param task
//     * @param loginEmployee
//     * @throws BusinessException
//     */
//    void workFlowReject(Task task, LoginEmployee loginEmployee) throws BusinessException;

//    /**
//     * 重新发起审核
//     *
//     * @param task
//     * @param loginEmployee
//     * @throws BusinessException
//     */
//    void reSubmitToWorkFlow(Task task, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * 客户项目统计信息（客户-项目-对账当数量-税前费用-税后费用）
     *
     * @param queryVo
     * @return
     * @throws BusinessException
     */
    CustomerStatisticsVo findCustomerStatistics(CustomerStatisticsQueryVo queryVo) throws BusinessException;

    /**
     * 对账单概览
     *
     * @param reconciliationId
     * @return
     * @throws BusinessException
     */
    ReconciliationOverviewVo getReconciliationOverView(int reconciliationId) throws BusinessException;



    /**
     * 查看对账凭证列表
     *
     * @param reconciliationId
     * @return
     * @throws BusinessException
     */
    List<ImageUploadManage> getEvidence(int reconciliationId) throws BusinessException;

    /**
     * 车辆对账列表查询
     *
     * @param params
     * @param loginEmployee
     * @return
     * @throws BusinessException
     */
    Page<ReconciliationForVehicleVo> reconciliationSearchForVehicle(PageQueryCondition<ReconciliationForVehicleQueryVo> params, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * 获取对账单详情
     *
     * @param reconciliationId
     * @return
     * @throws BusinessException
     */
    ReconciliationVo getReconciliationVoById(int reconciliationId) throws BusinessException;

    //车辆对账费用修改轨迹

    /**
     * 对账单-运单列表查询
     *
     * @param params
     * @param loginEmployee
     * @return
     * @throws BusinessException
     */
    Page<ReconciliationWaybillDetailVo> reconciliationSearchWaybill(PageQueryCondition<ReconciliationWaybillDetailQueryVo> params, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * 车辆费用调整
     *
     * @param freightAdjustVo 调整内容
     * @param loginEmployee   登录人信息
     * @return 税后差额
     * @throws BusinessException
     */
    BigDecimal updateReconciliationFreightForVehicle(FreightAdjustVo freightAdjustVo, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * 客户费用调整
     *
     * @param freightAdjustVo 调整内容
     * @param loginEmployee
     * @throws BusinessException
     */
    void updateReconciliationFreightForCustomer(FreightAdjustVo freightAdjustVo, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * 通过对账单id获取对账明细
     *
     * @param reconciliationId
     * @return
     * @throws BusinessException
     */
    List<ReconciliationItemNew> findReconciliationItemByReconciliationId(int reconciliationId) throws BusinessException;


    /***
     *通过对账单号 和车牌号 查询 对账单详情
     *
     * @param reconciliationNo 对账单号
     *
     * @param plateNumber 车牌号
     *
     * */
    ReconciliationItemNew findReconciliationItemNewByPlateNumberAndReconciliationNo(String reconciliationNo, String plateNumber)throws BusinessException;


    /***
     * 通过对账单号 和 承运商id 车讯对账单详情
     *
     * @param vendorId 承运商id
     *
     * @param reconciliationNo 对账单号
     *
     * */
    ReconciliationItemNew findReconciliationItemNewByVendorIdAndReconciliationNo(String reconciliationNo, Integer vendorId);


    /***
     * 修改 对账单详情
     *
     *@param reconciliationItemNew 对账单详情
     *
     *@see ReconciliationItemNew
     *
     * */
    void updateReconciliationItemNew(ReconciliationItemNew reconciliationItemNew)throws BusinessException;


    /***
     *
     * 修改对账单
     *
     *
     * */
    void updateReconciliationNew(ReconciliationNew reconciliationNew)throws BusinessException;


    /***
     * 按对账单 号查询对账单，不包含对账单详情
     *
     *@param reconciliationNo 对账单号
     *
     * */
    ReconciliationNew findReconciliationNewByReconciliationNo(String reconciliationNo)throws BusinessException;


    /**
     * 通过reconciliationNewId获取itemList
     * @param reconciliationNewId
     * @return
     * @throws BusinessException
     */
    List<ReconciliationForVehicleVo> findReconciliationItemByReconciliationNewId(int reconciliationNewId) throws BusinessException;

    /**
     * 通过对账单id查找对账单下的运单
     * @param reconciliationNewId
     * @return
     * @throws BusinessException
     */
    List<ReconciliationWaybillDetailVo> findReconciliationWaybillDetailVoByReconciliationId(int reconciliationNewId) throws BusinessException;

    /**
     * 通过工作流处理id获取对账单信息
     * @param processInstanceId
     * @return
     * @throws BusinessException
     */
    ReconciliationNew findReconciliationNewByProcessInstanceId(String processInstanceId) throws BusinessException;


    /**
     * fms 查询客户项目列表
     * @param queryCondition crmCustomerId,projectName
     * @param loginUser 当前登录人信息
     * @return 项目列表
     * @throws BusinessException 业务异常
     */
    Page<ProjectForFmsVo> searchProjectForFms(PageQueryCondition<ProjectQueryVo> queryCondition, LoginUser loginUser) throws BusinessException;



    /****
     *
     * 提供给客户变更更新 客户名字的接口
     *
     * @param customerId 本地数据库客户 id
     *
     * @param customerName 需要更新的 客户名称
     *
     * */
    void updateCustomerName( Integer customerId , String customerName ) throws BusinessException;


    /********************************对账管理-结束***********************/


}
