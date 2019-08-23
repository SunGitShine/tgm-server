package com.juma.tgm.fms.service.impl.v3;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.auth.user.domain.User;
import com.juma.auth.user.service.UserService;
import com.juma.tgm.common.BaseUtil;
import com.juma.tgm.common.query.QueryCond;
import com.juma.tgm.common.workflow.ApprovalStatus;
import com.juma.tgm.common.workflow.WorkflowConstants;
import com.juma.tgm.fms.dao.v3.AdjustForFreightAttachMapper;
import com.juma.tgm.fms.dao.v3.AdjustForItemMapper;
import com.juma.tgm.fms.dao.v3.AdjustForMasterMapper;
import com.juma.tgm.fms.domain.v3.*;
import com.juma.tgm.fms.domain.v3.enums.AdjustMasterType;
import com.juma.tgm.fms.domain.v3.enums.AdjustType;
import com.juma.tgm.fms.domain.v3.vo.*;
import com.juma.tgm.fms.service.v3.AdjustForMasterAddService;
import com.juma.tgm.fms.service.v3.AdjustForMasterService;
import com.juma.tgm.project.service.ProjectService;
import com.juma.tgm.tool.domain.ExecuteWorkflowInfo;
import com.juma.tgm.tools.service.*;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillAmount;
import com.juma.tgm.waybill.domain.WaybillVO;
import com.juma.tgm.waybill.domain.vo.WaybillAmountFilter;
import com.juma.tgm.waybill.service.WaybillAmountService;
import com.juma.tgm.waybill.service.WaybillCommonService;
import com.juma.tgm.waybill.service.WaybillService;
import com.juma.vms.driver.vo.DriverQuery;
import com.juma.vms.external.service.VmsService;
import com.juma.vms.truck.vo.TruckQuery;
import com.juma.vms.vendor.domain.Vendor;
import com.juma.vms.vendor.enumeration.VendorSourceEnum;
import com.juma.vms.vendor.vo.VendorFilter;
import com.juma.vms.vendor.vo.VendorQuery;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @description: ${description}
 *
 * @author: xieqiang
 *
 * @create: 2019-05-10 11:36
 **/
@Service
public class AdjustForMasterServiceImpl implements AdjustForMasterService {

	@Autowired
	private AdjustForFreightAttachMapper adjustForFreightAttachMapper;

	@Autowired
	private AdjustForMasterMapper adjustForMasterMapper;

	@Autowired
	private AdjustForItemMapper adjustForItemMapper;

	@Autowired
	private WorkflowCommonService workflowCommonService;

	@Autowired
	private UserService userService;

	@Autowired
	private VmsService vmsService;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private WaybillService waybillService;

	@Autowired
	private VmsCommonService vmsCommonService;

	@Autowired
	private WaybillCommonService waybillCommonService;

	@Autowired
	private BusinessAreaCommonService businessAreaCommonService;

	@Autowired
	private WaybillAmountService waybillAmountService;

	@Autowired
	private FmsCommonService fmsCommonService;

	@Autowired
	private InvoiceCommonService invoiceCommonService;

	@Autowired
	private AdjustForMasterAddService adjustForMasterAddService;

	@Override
	public Page<AdjustForMasterVo> findAdjustForMasterPage(QueryCond<AdjustForMasterVo> queryCond,
		LoginUser loginUser) throws BusinessException {

		AdjustForMasterExample example = new AdjustForMasterExample().createCriteria()
			.andTenantIdEqualTo(loginUser.getTenantId())
			.andIsDeleteEqualTo(0)
			.andAdjustTypeEqualTo(queryCond.getFilters().getAdjustType())
			.andAdjustForWhoEqualTo(queryCond.getFilters().getAdjustForWho())
			.andApprovalStatusEqualTo(queryCond.getFilters().getApprovalStatus())
			.andAdjustNoLike(queryCond.getFilters().getAdjustNo())
			.andReconcilicationNoEqualTo(queryCond.getFilters().getReconcilicationNo())
			.andCreateUserNameEqualTo(queryCond.getFilters().getCreateUserName())
			.andCreateTimeGreaterThanOrEqualTo(queryCond.getFilters().getStartTime())
			.andCreateTimeLessThanOrEqualTo(queryCond.getFilters().getEndTime())
			.andAreaCodeLikeList(queryCond.getFilters().getAreaCodeList())
			.example();
		example.setOrderByClause(AdjustForMaster.Column.createTime.desc());
		example.setSize(queryCond.getPageSize());
		example.setStartOffSet(queryCond.getStartOffset());

		long count = adjustForMasterMapper.countByExample(example);
		Page<AdjustForMasterVo> page = new Page<>(queryCond.getPageNo(), queryCond.getPageSize(), (int)count, null);
		if(count == 0){
			return page;
		}

		List<AdjustForMaster> adjustForMasters = adjustForMasterMapper.selectByExample(example);
		//组装额外数据
		List<AdjustForMasterVo> adjustForMasterVos = makeAdjustPage(adjustForMasters, loginUser);
		page.setTotal((int)count);
		page.setResults(adjustForMasterVos);
		return page;
	}

	/**
	 * 组装调整单分页数据
	 * @param adjustForMasters
	 * @return
	 */
	private List<AdjustForMasterVo> makeAdjustPage(List<AdjustForMaster> adjustForMasters, LoginUser loginUser){

		List<AdjustForMasterVo> adjustForMasterVos = new ArrayList<>();
		List<Integer> adjustIds = Lists.newArrayList();
		for(AdjustForMaster adjustForMaster : adjustForMasters){
			adjustIds.add(adjustForMaster.getAdjustId());
		}

		//调整单运单总数
		AdjustForItemExample example = new AdjustForItemExample().createCriteria()
				.andAdjustIdIn(adjustIds).example();
		List<AdjustForItem> items = adjustForItemMapper.selectByExample(example);
		Map<Integer,List<AdjustForItem>> listMap = Maps.newConcurrentMap();
		for (AdjustForItem item : items){
			List<AdjustForItem> mapItems = listMap.get(item.getAdjustId());
			if(CollectionUtils.isEmpty(mapItems)){
				mapItems = Lists.newArrayList();
				listMap.put(item.getAdjustId(),mapItems);
			}
			mapItems.add(item);
		}

		for(AdjustForMaster adjustForMaster : adjustForMasters){

			AdjustForMasterVo adjustForMasterVo = new AdjustForMasterVo();
			BeanUtils.copyProperties(adjustForMaster, adjustForMasterVo);
			//创建人手机号
			User user = userService.loadUser(adjustForMaster.getCreateUserId());
			adjustForMasterVo.setCreateUserPhone(user == null ? "" : user.getMobileNumber());

			List<AdjustForItem> adjustForItems = listMap.get(adjustForMaster.getAdjustId());
			if( !CollectionUtils.isEmpty(adjustForItems) ){
				adjustForMasterVo.setWaybillCount(adjustForItems.size());
				//调整前含税总额
				BigDecimal freightWithTaxTotal = new BigDecimal(0);
				//调整比例
				BigDecimal adjustScale = new BigDecimal(0);
				for(AdjustForItem item : adjustForItems){
					freightWithTaxTotal = freightWithTaxTotal.add(item.getFreightWithTax());
				}
				if(freightWithTaxTotal.intValue() != 0 && adjustForMaster.getAdjustAmount().intValue() != 0){
					adjustScale = adjustForMaster.getAdjustAmount().divide(freightWithTaxTotal, 4,BigDecimal.ROUND_HALF_UP);
				}
				adjustForMasterVo.setAdjustScale(adjustScale);
			}
			//业务区域
			adjustForMasterVo.setAreaName(businessAreaCommonService.loadAreaName(adjustForMaster.getAreaCode(), loginUser));
			adjustForMasterVos.add(adjustForMasterVo);
		}

		return adjustForMasterVos;
	}

	@Override
	public void cancelWorkFlowTask(Integer adjustId, LoginEmployee loginEmployee) throws BusinessException {

		if(adjustId == null){
			throw new BusinessException("paramError", "errors.paramsError");
		}
		AdjustForMaster master = adjustForMasterMapper.selectByPrimaryKey(adjustId);
		if(master == null){
			throw new BusinessException("WorkflowNotExist", "errors.common.prompt", "审批流程不存在");
		}
//		if(adjustForMaster.getApprovalStatus().compareTo(AdjustEnum.AuditStatus.REJECT.getCode()) == 0
//			|| adjustForMaster.getApprovalStatus().compareTo(AdjustEnum.AuditStatus.AGREE.getCode()) == 0){
//			throw new BusinessException("workflowStart", "errors.common.prompt", "审批流程已被审批，不能撤销");
//		}
		if(loginEmployee.getUserId().compareTo(master.getCreateUserId()) != 0){
			throw new BusinessException("isNotCreateUser", "errors.common.prompt", "创建人才能撤销");
		}

		revokeWorkflow(master, loginEmployee);

		//主表置为删除状态
		AdjustForMaster updateAdjust = new AdjustForMaster();
		updateAdjust.setAdjustId(master.getAdjustId());
		updateAdjust.setIsDelete(1);
		adjustForMasterMapper.updateByPrimaryKeySelective(updateAdjust);

		// 对账前,不需要撤销
		if( AdjustType.BEFORE.getCode().equals(master.getAdjustType()) ){return;}
		// 撤销工作流之后,需要解冻FMS数据
		List<String> vendorIds = fetchVendorIds(master,loginEmployee);
		fmsCommonService.unfrozenPayable(master.getReconcilicationNo(),vendorIds);
		// 客户侧,撤销,解冻开票系统状态
		if( AdjustMasterType.CUSTOMER.getCode().equals(master.getAdjustForWho()) ){
			invoiceCommonService.unfrozenReceivableInvoice(master.getReconcilicationNo());
		}
	}

	/**撤销工作流**/
	private void revokeWorkflow(AdjustForMaster master, LoginEmployee loginEmployee) {
		String processDefinitionKey = "";

		//应收调整
		if( AdjustMasterType.CUSTOMER.getCode().equals(master.getAdjustForWho())){
			processDefinitionKey = WorkflowConstants.PROCESS_KEY_ADJUST_RECEIVE_CREATE;
		}
		//应付调整
		if( AdjustMasterType.VENDOR.getCode().equals(master.getAdjustForWho())){
			processDefinitionKey = WorkflowConstants.PROCESS_KEY_ADJUST_PAYABLE_CREATE;
		}
		workflowCommonService.revokeWorkflow(ExecuteWorkflowInfo.builder()
				.workflowDesc("撤销调整单审批")
				.processDefinitionKey(processDefinitionKey)
				.businessKey(master.getAdjustId().toString())
				.number(master.getAdjustNo())
				.build(),loginEmployee);
	}

	/**获取承运商ID集合**/
	private List<String> fetchVendorIds(AdjustForMaster master, LoginEmployee loginEmployee) {
		AdjustForItemFilter filter = new AdjustForItemFilter();
		filter.setAdjustId(master.getAdjustId());
		List<AdjustForItem> items = adjustForMasterAddService.findItemByFilter(filter,loginEmployee);
		List<String> vendorIds = Lists.newArrayList();
		for (AdjustForItem item : items){
			vendorIds.add(String.valueOf(item.getVendorId()));
		}
		return vendorIds;
	}

	@Override
	public List<VendorQuery> listByVendorFilter(VendorFilter vendorFilter, Integer pageSize, LoginUser loginUser) {
		return vmsService.listByVendorFilter(vendorFilter, pageSize, loginUser);
	}

	@Override
	public List<DriverQuery> listByDriver(DriverQuery driverQuery, Integer pageSize, LoginUser loginUser) {
		return vmsService.listByDriver(driverQuery, pageSize, loginUser);
	}

	@Override
	public List<TruckQuery> listByPlateNumber(TruckQuery truckQuery, Integer pageSize, LoginUser loginUser) {
		return vmsService.listByPlateNumber(truckQuery, pageSize, loginUser);
	}

	@Override
	public List<AdjustForMaster> findByApprovalTime(AdjustForMasterVo adjust) throws BusinessException {
		AdjustForMasterExample example = new AdjustForMasterExample().createCriteria()
				.andApprovalStatusEqualTo(adjust.getApprovalStatus())
				.andAdjustTypeEqualTo(adjust.getAdjustType())
				.andAdjustForWhoEqualTo(adjust.getAdjustForWho())
				.andApprovalTimeGreaterThanOrEqualTo(adjust.getStartTime())
				.andApprovalTimeLessThanOrEqualTo(adjust.getEndTime())
				.example();
		return adjustForMasterMapper.selectByExample(example);
	}

	@Override
	public AdjustForMasterDetail findAdjustDetail(Integer adjustId, LoginUser loginUser) throws BusinessException {

		if(adjustId == null){
			throw new BusinessException("paramError", "errors.common.prompt", "参数异常");
		}
		AdjustForMasterDetail detail = new AdjustForMasterDetail();
		AdjustForMaster adjustForMaster = adjustForMasterMapper.selectByPrimaryKey(adjustId);
		if( null == adjustForMaster ){
			throw new BusinessException("adjustInfoNotExist", "调整单不存在");
		}

		AdjustForItemExample aiExample = new AdjustForItemExample().createCriteria()
			.andAdjustIdEqualTo(adjustId).example();
		long waybillCount = adjustForItemMapper.countByExample(aiExample);

		List<AdjustForItem> adjustForItems = adjustForItemMapper.selectByExample(aiExample);
		//调整前含税总额
		BigDecimal freightWithTaxTotal = new BigDecimal(0);
		for(AdjustForItem item : adjustForItems){
			freightWithTaxTotal = freightWithTaxTotal.add(item.getFreightWithTax());
		}

		//调整比例
		if(freightWithTaxTotal.intValue() != 0){
			BigDecimal adjustPercent = adjustForMaster.getAdjustAmount().divide(freightWithTaxTotal, 4,BigDecimal.ROUND_HALF_UP);
			detail.setAdjustPercent(adjustPercent);
		}

		AdjustForFreightAttachExample afaExample = new AdjustForFreightAttachExample().createCriteria()
			.andAdjustIdEqualTo(adjustId).example();
		List<AdjustForFreightAttach> adjustForFreightAttaches = adjustForFreightAttachMapper.selectByExample(afaExample);

		detail.setAdjustForMaster(adjustForMaster);
		detail.setWaybillCount((int)waybillCount);
		detail.setAdjustForFreightAttach(adjustForFreightAttaches.isEmpty() ? new AdjustForFreightAttach() : adjustForFreightAttaches.get(0));

		/**构造调整单-整体金额汇总**/
		makeAdjustAmountInfo(detail, adjustForMaster);

		return detail;
	}

	@Override
	public AdjustForMasterDetail findAdjustDetail(String adjustNo, LoginUser loginUser) throws BusinessException {
		if(StringUtils.isBlank(adjustNo)){
			return null;
		}
		AdjustForMasterExample example = new AdjustForMasterExample().createCriteria()
			.andAdjustNoEqualTo(adjustNo).example();
		List<AdjustForMaster> adjustForMasters = adjustForMasterMapper.selectByExample(example);
		if(adjustForMasters == null || adjustForMasters.isEmpty()){
			return null;
		}
		return this.findAdjustDetail(adjustForMasters.get(0).getAdjustId(), loginUser);
	}

	@Override
	public Page<AdjustForItemDetail> findAdjustItemPage(QueryCond<AdjustForItem> queryCond, LoginUser loginUser) {

		if(queryCond.getFilters() == null || queryCond.getFilters().getAdjustId() == null){
			throw new BusinessException("adjustIdNullError", "errors.common.prompt", "adjustId不能为空");
		}

		AdjustForItemExample example = new AdjustForItemExample().createCriteria()
			.andAdjustIdEqualTo(queryCond.getFilters().getAdjustId()).example();
		example.setOrderByClause(AdjustForItem.Column.createTime.desc());
		example.setSize(queryCond.getPageSize());
		example.setStartOffSet(queryCond.getStartOffset());

		long adjustItemCount = adjustForItemMapper.countByExample(example);

		Page<AdjustForItemDetail> page = new Page<>(queryCond.getPageNo(), queryCond.getPageSize(), (int)adjustItemCount, null);
		if(adjustItemCount == 0){
			return page;
		}

		List<AdjustForItem> adjustForItems = adjustForItemMapper.selectByExample(example);
		List<AdjustForItemDetail> details = new ArrayList<>();
		for(AdjustForItem item : adjustForItems){
			AdjustForItemDetail detail = new AdjustForItemDetail();
			BeanUtils.copyProperties(item, detail);

			Waybill waybill = waybillService.getWaybill(item.getWaybillId());
			if(waybill != null){
				detail.setProjectName(waybill.getProjectName());
				detail.setDriverName(waybill.getDriverName());
				detail.setPlateNumber(waybill.getPlateNumber());
				// 承运商名称
				if (null != waybill.getReceiveWay() && waybill.getReceiveWay().equals(Waybill.ReceiveWay.TRANSFORM_BILL.getCode())) {
					Vendor vendor = vmsCommonService.loadVendorByVendorId(waybill.getVendorId());
					detail.setVendorName(vendor == null ? null : vendor.getVendorName());
				}else{
					Vendor vendor = vmsCommonService.loadVendorByVendorId(waybill.getVehicleToVendor());
					detail.setVendorName(vendor == null ? null : vendor.getVendorName());
				}
			}
			//调整金额
			BigDecimal adjustAmountItem = item.getAdjustForFreight().add(item.getAdjustForCarry())
				.add(item.getAdjustForWorkload()).add(item.getAdjustForUpstairs())
				.add(item.getAdjustForFine()).add(item.getAdjustForCargoLoss());
			detail.setAdjustAmount(adjustAmountItem);
			detail.setAdjustAfterAmount(item.getFreightWithTax().add(adjustAmountItem));
			details.add(detail);
		}

		page.setResults(details);
		return page;
	}

	@Override
	public AdjustFreightVo loadAdjustForMasterByReconcilicationNo(String reconcilicationNo) {
		if (StringUtils.isBlank(reconcilicationNo)) {
			return null;
		}

		List<AdjustForMaster> list = adjustForMasterMapper
			.selectByExample(new AdjustForMasterExample().createCriteria()
				.andReconcilicationNoEqualTo(reconcilicationNo)
				.andApprovalStatusEqualTo(ApprovalStatus.APPROVAL_AGREE.getCode())
				.example());

		if (list.isEmpty()) {
			return null;
		}

		AdjustFreightVo vo = new AdjustFreightVo();
		vo.setAdjustFreight(BigDecimal.ZERO);
		vo.setAdjustAbsFreight(BigDecimal.ZERO);
		for (AdjustForMaster master : list) {
			vo.setAdjustFreight(vo.getAdjustFreight().add(master.getAdjustAmount()));
			vo.setAdjustAbsFreight(vo.getAdjustAbsFreight().add(master.getAdjustAbsAmount()));
		}

		return vo;
	}

	@Override
	public List<AdjustForMaster> findByReconcilicationNo(String reconcilicationNo,Integer approvalStatus) throws BusinessException {
		return adjustForMasterMapper.selectByExample(new AdjustForMasterExample().createCriteria()
				.andReconcilicationNoEqualTo(reconcilicationNo)
				.andApprovalStatusEqualTo(approvalStatus)
				.example());
	}

	@Override
	public Page<WaybillVO> searchWaybill(PageCondition pageCondition, LoginEmployee loginEmployee) throws BusinessException {
		Page<WaybillVO> pageWaybill = waybillService.searchForManageSys(loginEmployee, pageCondition);
		Map<Integer, WaybillAmount> amountMap = fetchWaybillAmount(pageWaybill.getResults(), loginEmployee);
		if(pageCondition != null && pageWaybill.getResults() != null && !pageWaybill.getResults().isEmpty()){
			List<WaybillVO> waybills = new ArrayList<>(pageWaybill.getResults());
			for(Waybill waybill : waybills){
				WaybillAmount waybillAmount = amountMap.get(waybill.getWaybillId());
				if(waybillAmount != null){
					waybill.setEstimateFreight(waybillAmount.getLastCustomerFreightWithTax());
					waybill.setShow4DriverFreight(waybillAmount.getLastVendorFreightWithTax());
				}
			}
			pageWaybill.setResults(waybills);
		}
		return pageWaybill;
	}

	/**构造调整单-整体金额汇总**/
	private void makeAdjustAmountInfo(AdjustForMasterDetail detail, AdjustForMaster adjustForMaster) {
		WaybillStatisticsAmountVO waybillStatisticsAmountVO = new WaybillStatisticsAmountVO();
		waybillStatisticsAmountVO.setAdjustMasterType(adjustForMaster.getAdjustForWho());
		waybillStatisticsAmountVO.setAdjustType(adjustForMaster.getAdjustType());

		// 月毛利率
		adjustForMasterAddService.calculateMonthProportion(adjustForMaster.getAdjustType(),adjustForMaster.getAdjustForWho(),adjustForMaster.getReconcilicationNo(),waybillStatisticsAmountVO);

		waybillStatisticsAmountVO.setCustomerBeforeHasTaxAmount(BigDecimal.ZERO.setScale(2));
		waybillStatisticsAmountVO.setCustomerAfterHasTaxAmount(BigDecimal.ZERO.setScale(2));
		waybillStatisticsAmountVO.setVendorHasTaxAmount(BigDecimal.ZERO.setScale(2));
		waybillStatisticsAmountVO.setVendorBeforeHasTaxAmount(BigDecimal.ZERO.setScale(2));
		waybillStatisticsAmountVO.setVendorAfterHasTaxAmount(BigDecimal.ZERO.setScale(2));
		waybillStatisticsAmountVO.setCustomerHasTaxAmount(BigDecimal.ZERO.setScale(2));
		waybillStatisticsAmountVO.setBeforeProfitAmount(BigDecimal.ZERO.setScale(2));
		waybillStatisticsAmountVO.setAfterProfitAmount(BigDecimal.ZERO.setScale(2));
		if (AdjustMasterType.CUSTOMER.getCode().equals(adjustForMaster.getAdjustForWho())) {
			calculateCustomerProportion(adjustForMaster, waybillStatisticsAmountVO);
		}
		if (AdjustMasterType.VENDOR.getCode().equals(adjustForMaster.getAdjustForWho())) {
			calculateVendorProportion(adjustForMaster, waybillStatisticsAmountVO);
		}
		detail.setWaybillStatisticsAmountVO(waybillStatisticsAmountVO);
	}

	/**计算承运侧毛利率**/
	@Override
	public void calculateVendorProportion(AdjustForMaster adjustForMaster, WaybillStatisticsAmountVO waybillStatisticsAmountVO) {
		// 含自营
		waybillStatisticsAmountVO.setVendorBeforeHasTaxAmount(adjustForMaster.getBeforeAdjustWithselfAmount());
		waybillStatisticsAmountVO.setVendorAfterHasTaxAmount(adjustForMaster.getBeforeAdjustWithselfAmount().add(adjustForMaster.getAdjustAmount()));
		waybillStatisticsAmountVO.setCustomerHasTaxAmount(adjustForMaster.getOtherSideWithselfAmount());

		// 不含自营
		BigDecimal beforeAmount = adjustForMaster.getBeforeAdjustAmount();
		// 获取不含自营运单的调整单调整总金额
		BigDecimal adjustAmountWithoutSelf = fetchAdjustAmountWithoutSelf(adjustForMaster);
		BigDecimal afterAmount = adjustForMaster.getBeforeAdjustAmount().add(adjustAmountWithoutSelf);
		BigDecimal otherSideAmount = adjustForMaster.getOtherSideAmount();

		waybillStatisticsAmountVO.setBeforeProfitAmount(otherSideAmount.subtract(beforeAmount));
		if (BigDecimal.ZERO.compareTo(otherSideAmount) != 0) {
			BigDecimal beforeProportion = waybillStatisticsAmountVO.getBeforeProfitAmount().divide(otherSideAmount, 4, BigDecimal.ROUND_HALF_UP);
			waybillStatisticsAmountVO.setBeforeProportion(BaseUtil.formatDecimal(beforeProportion.doubleValue()));
			if( null != waybillStatisticsAmountVO.getProfitRate() ) {
				waybillStatisticsAmountVO.setBeforeProportionGap(BaseUtil.formatDecimal(beforeProportion.subtract(waybillStatisticsAmountVO.getProfitRate()).doubleValue()));
			}
		}
		waybillStatisticsAmountVO.setAfterProfitAmount(otherSideAmount.subtract(afterAmount));
		if (BigDecimal.ZERO.compareTo(otherSideAmount) != 0) {
			BigDecimal afterProportion = waybillStatisticsAmountVO.getAfterProfitAmount().divide(otherSideAmount, 4, BigDecimal.ROUND_HALF_UP);
			waybillStatisticsAmountVO.setAfterProportion(BaseUtil.formatDecimal(afterProportion.doubleValue()));
			if( null != waybillStatisticsAmountVO.getProfitRate() ) {
				waybillStatisticsAmountVO.setAfterProportionGap(BaseUtil.formatDecimal(afterProportion.subtract(waybillStatisticsAmountVO.getProfitRate()).doubleValue()));
			}
		}
	}

	/**获取不含自营运单的调整单调整总金额**/
	private BigDecimal fetchAdjustAmountWithoutSelf(AdjustForMaster adjustForMaster) {
		// 这里的调整金额需要额外计算
		Map<Integer,Boolean> selfCheckMap = Maps.newConcurrentMap();
		AdjustForItemFilter filter = new AdjustForItemFilter();
		filter.setAdjustId(adjustForMaster.getAdjustId());
		List<AdjustForItem> items = adjustForMasterAddService.findItemByFilter(filter,null);
		if( CollectionUtils.isEmpty(items) ){ return BigDecimal.ZERO; }
		List<Integer> waybillIds = Lists.newArrayList();
		for (AdjustForItem item : items){
			waybillIds.add(item.getWaybillId());
		}
		List<Waybill> waybills = waybillService.findByWaybillIds(waybillIds);
		for (Waybill waybill : waybills){
			// 承运商名称
			Vendor vendor;
			if (null != waybill.getReceiveWay() && waybill.getReceiveWay().equals(Waybill.ReceiveWay.TRANSFORM_BILL.getCode())) {
				vendor = vmsCommonService.loadVendorByVendorId(waybill.getVendorId());
			}else{
				vendor = vmsCommonService.loadVendorByVendorId(waybill.getVehicleToVendor());
			}
			if( null != vendor && Byte.valueOf(VendorSourceEnum.SELF_SUPPORT.getCode()).equals(vendor.getVendorSource()) ){
				selfCheckMap.put(waybill.getWaybillId(),true);
			}else{
				selfCheckMap.put(waybill.getWaybillId(),false);
			}
		}

		BigDecimal adjustAmountWithoutSelf = BigDecimal.ZERO;
		for (AdjustForItem item : items){
			Boolean isSelf = selfCheckMap.get(item.getWaybillId());
			if( null != isSelf && isSelf ){
				continue;
			}
			BigDecimal adjustAmount = validAndSetBigDecimalValue(item.getAdjustForFreight())
					.add(validAndSetBigDecimalValue(item.getAdjustForCarry()))
					.add(validAndSetBigDecimalValue(item.getAdjustForWorkload()))
					.add(validAndSetBigDecimalValue(item.getAdjustForUpstairs()))
					.add(validAndSetBigDecimalValue(item.getAdjustForFine()))
					.add(validAndSetBigDecimalValue(item.getAdjustForCargoLoss()));
			adjustAmountWithoutSelf = adjustAmountWithoutSelf.add(adjustAmount);
		}
		return adjustAmountWithoutSelf;
	}

	private BigDecimal validAndSetBigDecimalValue(BigDecimal amount) {
		return null != amount ? amount : BigDecimal.ZERO;
	}

	/**计算客户侧毛利率**/
	@Override
	public void calculateCustomerProportion(AdjustForMaster adjustForMaster, WaybillStatisticsAmountVO waybillStatisticsAmountVO) {
		// 含自营
		waybillStatisticsAmountVO.setCustomerBeforeHasTaxAmount(adjustForMaster.getBeforeAdjustWithselfAmount());
		waybillStatisticsAmountVO.setCustomerAfterHasTaxAmount(adjustForMaster.getBeforeAdjustWithselfAmount().add(adjustForMaster.getAdjustAmount()));
		waybillStatisticsAmountVO.setVendorHasTaxAmount(adjustForMaster.getOtherSideWithselfAmount());

		// 不含自营
		BigDecimal beforeAmount = adjustForMaster.getBeforeAdjustAmount();
		// 获取不含自营运单的调整单调整总金额
		BigDecimal adjustAmountWithoutSelf = fetchAdjustAmountWithoutSelf(adjustForMaster);
		BigDecimal afterAmount = adjustForMaster.getBeforeAdjustAmount().add(adjustAmountWithoutSelf);
		BigDecimal otherSideAmount = adjustForMaster.getOtherSideAmount();

		waybillStatisticsAmountVO.setBeforeProfitAmount(beforeAmount.subtract(otherSideAmount));
		if (BigDecimal.ZERO.compareTo(beforeAmount) != 0) {
			BigDecimal beforeProportion = waybillStatisticsAmountVO.getBeforeProfitAmount().divide(beforeAmount, 4, BigDecimal.ROUND_HALF_UP);
			waybillStatisticsAmountVO.setBeforeProportion(BaseUtil.formatDecimal(beforeProportion.doubleValue()));
			if( null != waybillStatisticsAmountVO.getProfitRate() ){
				waybillStatisticsAmountVO.setBeforeProportionGap(BaseUtil.formatDecimal(beforeProportion.subtract(waybillStatisticsAmountVO.getProfitRate()).doubleValue()));
			}
		}
		waybillStatisticsAmountVO.setAfterProfitAmount(afterAmount.subtract(otherSideAmount));
		if (BigDecimal.ZERO.compareTo(afterAmount) != 0) {
			BigDecimal afterProportion = waybillStatisticsAmountVO.getAfterProfitAmount().divide(afterAmount, 4, BigDecimal.ROUND_HALF_UP);
			waybillStatisticsAmountVO.setAfterProportion(BaseUtil.formatDecimal(afterProportion.doubleValue()));
			if( null != waybillStatisticsAmountVO.getProfitRate() ) {
				waybillStatisticsAmountVO.setAfterProportionGap(BaseUtil.formatDecimal(afterProportion.subtract(waybillStatisticsAmountVO.getProfitRate()).doubleValue()));
			}
		}
	}

	/**根据运单获取运费确认状态**/
	private Map<Integer, WaybillAmount> fetchWaybillAmount(Collection<WaybillVO> rows, LoginUser loginUser) {
		List<Integer> waybillIds = Lists.newArrayList();
		for (Waybill waybill : rows) {
			waybillIds.add(waybill.getWaybillId());
		}
		Map<Integer,WaybillAmount> amountMap = Maps.newConcurrentMap();
		if( com.giants.common.collections.CollectionUtils.isEmpty(waybillIds) ){ return amountMap; }
		WaybillAmountFilter filter = new WaybillAmountFilter();
		filter.setWaybillIds(waybillIds);
		List<WaybillAmount> amounts = waybillAmountService.findByFilter(filter,loginUser);
		for (WaybillAmount amount : amounts){
			amountMap.put(amount.getWaybillId(),amount);
		}
		return amountMap;
	}
}
