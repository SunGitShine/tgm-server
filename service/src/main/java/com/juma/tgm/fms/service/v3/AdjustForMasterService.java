package com.juma.tgm.fms.service.v3;

import com.giants.common.tools.PageCondition;
import com.juma.tgm.common.query.QueryCond;
import com.juma.tgm.fms.domain.v3.vo.*;

import java.util.List;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.fms.domain.v3.AdjustForItem;
import com.juma.tgm.fms.domain.v3.AdjustForMaster;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillVO;
import com.juma.vms.driver.vo.DriverQuery;
import com.juma.vms.truck.vo.TruckQuery;
import com.juma.vms.vendor.vo.VendorFilter;
import com.juma.vms.vendor.vo.VendorQuery;

/**
 * @description: ${description}
 *
 * @author: xieqiang
 *
 * @create: 2019-05-10 11:31
 **/
public interface AdjustForMasterService {

	/**
	 * 分页查询调整单列表
	 * @param queryCond
	 * @param loginUser
	 * @return
	 * @throws BusinessException
	 */
	Page<AdjustForMasterVo> findAdjustForMasterPage(QueryCond<AdjustForMasterVo> queryCond, LoginUser loginUser) throws BusinessException;

	/**
	 * 撤销调整单
	 * @param adjustId
	 * @param loginEmployee
	 * @throws BusinessException
	 */
	void cancelWorkFlowTask(Integer adjustId, LoginEmployee loginEmployee) throws BusinessException;

	/**
	 * 承运商名称模糊查询承运商列表
	 * @param vendorFilter
	 * @param pageSize
	 * @param loginUser
	 * @return
	 */
	List<VendorQuery> listByVendorFilter(VendorFilter vendorFilter, Integer pageSize, LoginUser loginUser);

	/**
	 * 司机名称模糊查询司机列表
	 * @param driverQuery
	 * @param pageSize
	 * @param loginUser
	 * @return
	 */
	List<DriverQuery> listByDriver(DriverQuery driverQuery, Integer pageSize, LoginUser loginUser);

	/**
	 * 车牌号模糊搜索车辆
	 * @param truckQuery
	 * @param pageSize
	 * @param loginUser
	 * @return
	 */
	List<TruckQuery> listByPlateNumber(TruckQuery truckQuery, Integer pageSize, LoginUser loginUser);

	/**
	 * 查询审核通过时间范围内的调整单
	 * @param adjust
	 * @return
	 * @throws BusinessException
	 */
	List<AdjustForMaster> findByApprovalTime(AdjustForMasterVo adjust) throws BusinessException;

	/**
	 * 查询调整单主数据详情
	 * @return
	 * @throws BusinessException
	 */
	AdjustForMasterDetail findAdjustDetail(Integer adjustId, LoginUser loginUser) throws BusinessException;

	/**
	 * 通过调整单编号查详情
	 * @param adjustNo
	 * @param loginUser
	 * @return
	 * @throws BusinessException
	 */
	AdjustForMasterDetail findAdjustDetail(String adjustNo, LoginUser loginUser) throws BusinessException;

	/**
	 * 分页查询调整运单列表
	 * @param queryCond
	 * @param loginUser
	 * @return
	 */
	Page<AdjustForItemDetail> findAdjustItemPage(QueryCond<AdjustForItem> queryCond, LoginUser loginUser);

	/**
	 * 根据对账单号获取调整单调整金额与调整绝对金额，审批通过的
	 *
	 * @param reconcilicationNo
	 * @return
	 */
	AdjustFreightVo loadAdjustForMasterByReconcilicationNo(String reconcilicationNo);

	/**
	 * 根据对账单查询调整单
	 * @param reconcilicationNo
	 * @return
	 * @throws BusinessException
	 */
	List<AdjustForMaster> findByReconcilicationNo(String reconcilicationNo,Integer approvalStatus) throws BusinessException;

	/**
	 * 运单分页查询
	 * @return
	 * @throws BusinessException
	 */
	Page<WaybillVO> searchWaybill(PageCondition pageCondition, LoginEmployee loginEmployee) throws BusinessException;

	/**计算承运侧毛利率**/
	void calculateVendorProportion(AdjustForMaster adjustForMaster, WaybillStatisticsAmountVO waybillStatisticsAmountVO) throws BusinessException;

	/**计算客户侧毛利率**/
	void calculateCustomerProportion(AdjustForMaster adjustForMaster, WaybillStatisticsAmountVO waybillStatisticsAmountVO) throws BusinessException;
}
