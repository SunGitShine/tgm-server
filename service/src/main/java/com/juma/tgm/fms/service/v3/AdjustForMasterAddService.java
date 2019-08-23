package com.juma.tgm.fms.service.v3;

import com.giants.common.exception.BusinessException;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.tgm.fms.domain.v3.AdjustForItem;
import com.juma.tgm.fms.domain.v3.AdjustForMaster;
import com.juma.tgm.fms.domain.v3.AdjustForWaybillTemp;
import com.juma.tgm.fms.domain.v3.vo.*;
import com.juma.tgm.mq.domain.WorkFlowMessage;

import java.util.List;
import java.util.Map;

/**
 * 调整单功能实现
 * 功能 :
 * 1.添加调整单
 * @author : Bruce(刘正航) 17:42 2019-05-10
 */
public interface AdjustForMasterAddService {

	/**添加调整单 主方法 masterCreateOrUpdateAdjust**/
	void createOrUpdateAdjust(AdjustItemValidHolder holder, AdjustForMasterAddVO vo, List<AdjustForWaybillTemp> temps, LoginEmployee loginUser) throws BusinessException;

	/**添加调整单-之前的-校验**/
	WaybillStatisticsAmountVO validBeforeSubmit(AdjustForMasterAddVO vo, LoginEmployee loginUser) throws BusinessException;

	/**根据条件,查询调整单列表**/
	List<AdjustForMaster> findByFilter(AdjustForMasterFilter filter, LoginEmployee loginUser) throws BusinessException;

	/**根据条件查询运单和调整单关联关系**/
	Map<Integer,List<AdjustForMaster>> findMasterDictionaryByFilter(AdjustForMasterFilter filter) throws BusinessException;

	/**根据条件,查询调整单信息**/
	List<AdjustForItem> findItemByFilter(AdjustForItemFilter filter, LoginEmployee loginUser) throws BusinessException;

	/**根据条件,查询调整单列表**/
	AdjustForMaster getAdjustForMasterById(Integer adjustId) throws BusinessException;

	/**对账单工作流信息处理**/
	void doAdjustWorkflow(WorkFlowMessage message) throws BusinessException;

	/**补发月账单入口**/
	void doResendBillDatasToFmsOld(List<Integer> adjustIds, List<Integer> adjustItemIds, LoginEmployee loginUser) throws BusinessException;

	/**月毛利率计算**/
	void calculateMonthProportion(Integer adjustType, Integer adjustMasterType, String reconciliationNo, WaybillStatisticsAmountVO waybillStatisticsAmountVO) throws BusinessException;

}
