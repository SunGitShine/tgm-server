package com.juma.tgm.fms.service.v3;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.fms.domain.v3.AdjustForReceivable;
import com.juma.tgm.fms.domain.v3.ReconcilicationForCompany;
import com.juma.tgm.fms.domain.v3.ReconcilicationForReceivable;
import com.juma.tgm.fms.domain.v3.ReconcilicationForReceivableItem;
import com.juma.tgm.fms.domain.v3.vo.*;
import com.juma.tgm.mq.domain.WorkFlowMessage;
import com.juma.tgm.waybill.domain.Waybill;

import java.math.BigDecimal;
import java.util.List;

/**
 * 应收账单管理
 */
public interface ReconcilicationForReceivableService {

	/**
	 * 以用户和项目为标准，查询未对账的数据统计，统计结果为这个用户的未对账 金额
	 * @param pageCondition
	 * @param loginUser
	 * @return
	 */
	Page<ReceivableApplyVo> findReceivableApplyPage(PageCondition pageCondition, LoginUser loginUser) throws BusinessException;

	/***
	 *
	 *
	 *  查询 运单对账信息，需要注意的是 这里不是对账单，而是生成对账单之前的 运单查询
	 *<br/>
	 * <br/>
	 *  查询维度:
	 *  <li/><li>
	 *  运单号<li/>
	 *  用车时间范围，开始到结束<li/>
	 *  车牌号<li/>
	 *  司机姓名<li/>
	 *  客户id<li/>
	 *  项目id
	 *  路线名称
	 *
	 *  <br/>
	 *
	 *  其中 客户id 和项目id 是必填
	 *
	 *  @param pageCondition 分页参数 和查询参数
	 *
	 * */
	Page<ReconciliationWaybillDetailVo> searchWaybills(LoginEmployee loginEmployee, PageCondition pageCondition) throws BusinessException;

	/**
	 * 改价
	 * @param adjustForReceivable
	 * @param loginUser
	 * @throws BusinessException
	 */
	void updateFreight(AdjustForReceivable adjustForReceivable, LoginUser loginUser) throws BusinessException;

	/**
	 * 批量改价
	 * @param reconciliationWaybillExcelVos
	 * @param loginUser
	 * @throws BusinessException
	 */
	void batchUpdateFreight(List<ReconciliationWaybillExcelVo> reconciliationWaybillExcelVos, LoginUser loginUser) throws BusinessException;

	/**
	 * 查询初始含税金额及税率
	 * 先查adjust_for_receivable表看是否存在改价记录，若存在初始值取第一条
	 * 若不存在初始值取运单数据
	 * @param waybillId
	 * @return
	 */
	AdjustForReceivable findOldWaybillDate(Integer waybillId, LoginUser loginUser) throws BusinessException;

	/**
	 * 分页查询改价记录
	 * @param pageCondition
	 * @return
	 */
	Page<AdjustForReceivable> findAdjustByPage(PageCondition pageCondition)throws BusinessException;

	/**
	 * 创建对账单，插入应收对账单主表、对账单明细表，更新运单运单号、状态
	 * @param waybillIds
	 * @param loginEmployee
	 */
	String createReceivableReconciliation(List<Integer> waybillIds, LoginEmployee loginEmployee) throws BusinessException;

	/**
	 * 查询运单
	 * @param waybillIds
	 * @return
	 */
	List<Waybill> getWaybillList(List<Integer> waybillIds)throws BusinessException;

	/**
	 * 分页查询对账单列表
	 * @param pageCondition
	 * @param loginEmployee
	 * @return
	 */
	Page<ReconcilicationForReceivablePageVo> findReceivableReconciliationPage(PageCondition pageCondition, LoginEmployee loginEmployee)throws BusinessException;

	/**
	 * 分页查询对账单明细
	 * @param pageCondition
	 * @return
	 */
	Page<ReconcilicationForReceivableItemVo> findReceivableReconciliationItemPage(PageCondition pageCondition)throws BusinessException;

	/**
	 * 提交运单审核到工作流
	 * @param reconcilicationForReceivableIds
	 * @param loginEmployee
	 */
	void submitToWorkFlow(List<Integer> reconcilicationForReceivableIds, LoginEmployee loginEmployee) throws BusinessException;

	/**
	 * 计算毛利率差
	 * @param reconcilication
	 * @return
	 * @throws BusinessException
	 */
	BigDecimal calculateProfitRateDiffer(ReconcilicationForReceivable reconcilication) throws BusinessException;

	/**
	 * 通过id查询客户对账单
	 * @param receivableId
	 * @return
	 */
	ReconcilicationForReceivable findReceivableById(Integer receivableId)throws BusinessException;

	/**
	 * 撤销对账单
	 * @param reconciliationId
	 * @param loginEmployee
	 * @throws BusinessException
	 */
	void cancelReconciliation(Integer reconciliationId, LoginEmployee loginEmployee) throws BusinessException;

	/**
	 * 撤销审核
	 * @param reconciliationId
	 * @param loginEmployee
	 * @throws BusinessException
	 */
	void cancelWorkFlowTask(int reconciliationId, LoginEmployee loginEmployee) throws BusinessException;

	/**
	 * 对账单详情页数据
	 * @param reconciliationId
	 * @return
	 */
	ReconcilicationForReceivableVo getReconciliationOverView(Integer reconciliationId)throws BusinessException;

	/**
	 * 通过工作流处理id获取对账单信息
	 * @param processInstanceId
	 * @return
	 * @throws BusinessException
	 */
	ReconcilicationForReceivable findReconciliationByProcessInstanceId(String processInstanceId) throws BusinessException;

	/***
		* 按对账单 号查询对账单，不包含对账单详情
     *
		 *@param reconciliationNo 对账单号
     *
		 * */
	ReconcilicationForReceivable findReconciliationByReconciliationNo(String reconciliationNo)throws BusinessException;

	/**批量发送应收对账单开票消息给开票系统**/
	void batchSendInvoiceMessage(List<Integer> receivableConciliationIds) throws BusinessException;

	/**
	 * 修改对账单
	 */
	void updateReconciliation(ReconcilicationForReceivable reconcilicationForReceivable) throws BusinessException;


	/**
	 * 原始数据,不做修改:通过对账单号查询对账单明细
	 * @param reconciliationId
	 * @return
	 */
	List<ReconcilicationForReceivableItem> findReceivableItemsByReconciliationId(Integer reconciliationId);

	/**
	 * 通过对账单号查询对账单明细
	 * @param reconciliationId
	 * @return
	 */
	List<ReconcilicationForReceivableItemExcelVo> findReceivableItemByReconciliationId(Integer reconciliationId)throws BusinessException;

	/**
	 * 审批完成
	 *
	 * @param message
	 * @throws BusinessException
	 */
	void finishWorkFlowTask(WorkFlowMessage message) throws BusinessException;
	
    /**
     * 
     * @Title: findReconcilicationForCompanyByReconcilicationId   
     * @Description: 关联运单
     * @param: @return
     * @param: @throws BusinessException      
     * @return: List<ReconcilicationForCompanyExample>      
     * @throws
     */
	List<ReconcilicationForCompany> findReconcilicationForCompanyByReconcilicationId(Integer reconcilicationId) throws BusinessException;
	
	/**
	 * 
	 * @Title: sendReconcilicationForCompanyLink   
	 * @Description: 关联运单  后门(测试用)
	 * @param: @param reconcilicationId      
	 * @return: void      
	 * @throws
	 */
	void sendReconcilicationForCompanyLink(Integer reconcilicationId);
	
	/**
	 * 
	 * @Title: sendToFMS   
	 * @Description: 工作流超时或业务出错后，流程已经走完，状态不对 手动再次发起
	 * @param: @param reconcilicationNo      
	 * @return: void      
	 * @throws
	 */
	void sendToFMS(String reconcilicationNo) throws BusinessException;
}
