package com.juma.tgm.adjust.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bruce.tool.rpc.http.core.Https;
import com.giants.common.exception.BusinessException;
import com.giants.common.tools.PageCondition;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.juma.auth.conf.domain.BusinessArea;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.employee.service.ECompanyService;
import com.juma.auth.user.domain.LoginUser;
import com.juma.crm.support.domain.CustomerInfoBo;
import com.juma.tgm.common.DateUtils;
import com.juma.tgm.common.query.QueryCond;
import com.juma.tgm.crm.domain.CustomerInfo;
import com.juma.tgm.crm.service.CustomerInfoService;
import com.juma.tgm.fms.dao.v3.ReconcilicationForPayableItemMapper;
import com.juma.tgm.fms.dao.v3.ReconcilicationForReceivableItemMapper;
import com.juma.tgm.fms.domain.v3.AdjustForMaster;
import com.juma.tgm.fms.domain.v3.AdjustForWaybillTemp;
import com.juma.tgm.fms.domain.v3.ReconcilicationForReceivable;
import com.juma.tgm.fms.domain.v3.ReconcilicationForReceivableItem;
import com.juma.tgm.fms.domain.v3.ReconcilicationForReceivableItemExample;
import com.juma.tgm.fms.domain.v3.enums.AdjustMasterType;
import com.juma.tgm.fms.domain.v3.enums.AdjustType;
import com.juma.tgm.fms.domain.v3.vo.AdjustForMasterAddVO;
import com.juma.tgm.fms.domain.v3.vo.AdjustForMasterFilter;
import com.juma.tgm.fms.domain.v3.vo.AdjustForWaybillTempFilter;
import com.juma.tgm.fms.domain.v3.vo.InvoiceAmount;
import com.juma.tgm.fms.domain.v3.vo.WaybillVendorExportVO;
import com.juma.tgm.fms.service.v3.AdjustForMasterAddService;
import com.juma.tgm.fms.service.v3.AdjustForWaybillService;
import com.juma.tgm.fms.service.v3.ReconciliationCommonService;
import com.juma.tgm.fms.service.v3.ReconcilicationForReceivableService;
import com.juma.tgm.mq.domain.CustomerInvoiceEvent;
import com.juma.tgm.mq.domain.CustomerInvoiceWaybill;
import com.juma.tgm.mq.service.MqService;
import com.juma.tgm.project.service.ProjectService;
import com.juma.tgm.tools.service.AuthCommonService;
import com.juma.tgm.tools.service.CrmCommonService;
import com.juma.tgm.tools.service.VmsCommonService;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillAmount;
import com.juma.tgm.waybill.domain.vo.TruckRequireFilter;
import com.juma.tgm.waybill.domain.vo.WaybillAmountFilter;
import com.juma.tgm.waybill.service.TruckRequireService;
import com.juma.tgm.waybill.service.WaybillAmountService;
import com.juma.tgm.waybill.service.WaybillCommonService;
import com.juma.tgm.waybill.service.WaybillService;
import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import me.about.poi.reader.XlsxReader;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

/**
 * @description: ${description}
 *
 * @author: xieqiang
 *
 * @create: 2019-05-10 16:02
 *
 **/
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/META-INF/spring-standalone-beans.xml"
})
public class AdjustForMasterAddServiceTest{

	/**这里需要启动manage,通过断点方式获取json字符串,在这里使用**/
	private static final LoginEmployee loginEmployee = new Gson().fromJson(
		"{\"employeeId\":2904,\"maxInactiveInterval\":3600,\"loginDepartment\":{\"departmentId\":1479,\"departmentCode\":\"00\",\"departmentName\":\"驹马配送\",\"businessAreas\":[{\"areaCode\":\"00\",\"areaName\":\"全国\"}]},\"authDepartments\":[{\"departmentId\":1479,\"departmentName\":\"驹马配送\"}],\"sessionId\":\"757EB2EB0BCF4DF09BB0BFC4F4C5BD21\",\"tenantId\":19,\"tenantCode\":\"000000004\",\"userId\":1,\"loginName\":\"admin\",\"userName\":\"超级管理员\",\"mobileNumber\":\"18030574832\",\"isTest\":true,\"isSysUser\":true}",
		LoginEmployee.class);

	@Autowired
	private MqService mqService;

	@Autowired
	private CrmCommonService crmCommonService;

	@Autowired
	private WaybillService waybillService;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private AuthCommonService authCommonService;

	@Autowired
	private CustomerInfoService customerInfoService;

	@Autowired
	private VmsCommonService vmsCommonService;

	@Resource
	private ECompanyService eCompanyService;

	@Autowired
	private TruckRequireService truckRequireService;

	@Autowired
	private WaybillAmountService waybillAmountService;

	@Autowired
	private WaybillCommonService waybillCommonService;

	@Autowired
	private AdjustForWaybillService adjustForWaybillService;

	@Resource
	private AdjustForMasterAddService adjustForMasterAddService;

	@Resource
	private ReconciliationCommonService reconciliationCommonService;

	@Resource
	private ReconcilicationForPayableItemMapper reconcilicationForPayableItemMapper;

	@Resource
	public ReconcilicationForReceivableItemMapper reconcilicationForReceivableItemMapper;

	/**列表查询测试**/
	@Test
	public void adjust_page_test_should_return_list(){
//		AdjustForMasterFilter filter = new AdjustForMasterFilter();
//		filter.setApprovalStatus(ApprovalStatus.APPROVAL_AGREE.getCode());
//		List<AdjustForMaster> list = adjustForMasterAddService.findByFilter(filter,loginEmployee);
//		Assert.notEmpty(list);
	}

	/**添加调整单测试**/
	@Test
	public void adjust_create_test_with_init_params(){
		AdjustForMasterAddVO vo = new AdjustForMasterAddVO();
//		adjustForMasterAddService.masterCreateOrUpdateAdjust(vo,loginEmployee);
	}

	@Test
	public void adjust_bigdecimal_add(){
		AdjustForWaybillTemp temp = new AdjustForWaybillTemp();
		BigDecimal adjustAmount = BigDecimal.ZERO
				.add(null != temp.getAdjustForFreight()?temp.getAdjustForFreight():BigDecimal.ZERO)
				.add(temp.getAdjustForCarry())
				.add(temp.getAdjustForWorkload())
				.add(temp.getAdjustForUpstairs())
				.add(temp.getAdjustForFine())
				.add(temp.getAdjustForCargoLoss());
		System.out.println(adjustAmount);
	}

	@Test
	public void should_return_adjust_temp_datas(){
//		QueryCond<AdjustForWaybillTempVO> filter = new QueryCond<>();
//		loginEmployee.setTenantId(1);
//		List<AdjustForWaybillTemp> list =  adjustForWaybillService.findByFilter(filter,loginEmployee);
//		System.out.println(JSON.toJSONString(list));
	}

	@Test
	public void should_return_adjust_temp_page_datas(){
		QueryCond<AdjustForWaybillTempFilter> filter = new QueryCond<>();
		AdjustForWaybillTempFilter vo = new AdjustForWaybillTempFilter();
		filter.setFilters(vo);
		filter.setPageNo(0);
		filter.setPageSize(10);
		loginEmployee.setTenantId(5);
//		org.mybatis.generator.my.page.Page<AdjustForWaybillVO> list =  adjustForWaybillService.findPageByFilter(filter,loginEmployee);
//		System.out.println(JSON.toJSONString(list));
	}

	/**完整业务流程测试**/
	@Test
	public void business_process_test_with_complete_process(){
		/**
		 * 1.添加调整单-查询运单列表
		 * 2.添加调整单-导出选中的运单数据
		 * 3.添加调整单-编辑运单数据,并导入数据(导入,校验,存储)
		 * 4.添加调整单-查询+编辑临时表数据(删除/覆盖重传)
		 * 5.添加调整单-提交调整单(校验,开票系统,FMS,提交审批)
		 * 6.添加调整单-审批流程(通过/驳回/撤销)
		 * 7.编辑调整单-详情表数据查询(下载/上传/保存到临时表)
		 * 8.编辑调整单-编辑临时表(删除/覆盖重传)
		 * 9.编辑调整单-再次提交调整单(校验,开票系统,FMS,提交审批)
		 */
		OperatorType operatorType = OperatorType.builder()
				.adjustMasterType(AdjustMasterType.CUSTOMER.getCode())
				.adjustType(AdjustType.BEFORE.getCode())
				.build();
		searchWaybills(operatorType);
		exportWaybills(operatorType);
	}

	private void searchWaybills(final OperatorType operatorType) {
		Map<String,Object> filters = Maps.newHashMap();
		//承运商对账单号
//		filters.put("reconciliationNo","");
		//客户对账单号
//		filters.put("receivableReconcilicationNo","");
		//承运商对账状态，1：未对账，2：已对账
//		filters.put("reconciliationStatus",null);
		//客户对账状态，1：未对账，2：已对账
		filters.put("receivableReconcilicationStatus",1);
		PageCondition pageCondition = new PageCondition();
		pageCondition.setFilters(filters);
//		Page<Waybill> waybills = waybillService.search(loginEmployee,pageCondition);
//		if(CollectionUtils.isEmpty(waybills.getResults()) ){
//			return;
//		}
//		operatorType.setWaybills(Lists.<Waybill>newArrayList().addAll(waybills.getResults()));
	}

	/**2.添加调整单-导出选中的运单数据**/
	private void exportWaybills(OperatorType operatorType) {
		List<Integer> waybillIds = Lists.newArrayList();
//		List<Waybill> list = waybillService.findByWaybillIds(Lists.newArrayList(waybillIds),loginEmployee);
//		List<WaybillCustomerExportVO> result = Lists.newArrayList();
//		for (Waybill waybill : list) {
//			WaybillCustomerExportVO vo = new WaybillCustomerExportVO();
//			vo.setWaybillNo(waybill.getWaybillNo());
//			vo.setCustomerName(waybill.getCustomerName());
//			vo.setProjectName(waybill.getProjectName());
//			vo.setVendorName(waybill.getVendorName());
//			vo.setDriverName(waybill.getDriverName());
//			vo.setFreightWithTax(waybill.getEstimateFreight());
//			result.add(vo);
//		}
//		try (FileOutputStream fos = new FileOutputStream(System.getProperty("user.home")+"/Downloads/运单原始数据.xlsx")){
//			XlsxWriter.toOutputStream(result,fos);
//		} catch (Exception e) {
//			throw new BusinessException("ExcelAnalysisExportError",e.getMessage());
//		}
	}

	@Test
	public void testImport(){
		List<WaybillVendorExportVO> list = null;
		byte[] dataBytes = Https.create()
				.url("https://marryself.oss-cn-hangzhou.aliyuncs.com/%E8%BF%90%E5%8D%95%E5%8E%9F%E5%A7%8B%E6%95%B0%E6%8D%AE-%E6%89%BF%E8%BF%90%E5%95%86-Full-1.xlsx?Expires=1559626884&OSSAccessKeyId=TMP.AgG9etVTCLRjoSViwexobtBX2a-kNkdenXVoLiL138avnI3NZ0svWDFJWHnGMC4CFQDEZnigTTdAFKGhtTUg16W2y3SkRQIVAN2ywBffxTK5SVP_p486oxBSbEmG&Signature=eoGBgRgcdhhncGP6ZUwERFMGvfs%3D")
				.download();
		try (ByteArrayInputStream bais = new ByteArrayInputStream(dataBytes)){
			list = XlsxReader.fromInputStream(bais, WaybillVendorExportVO.class);
		} catch (Exception e) {
			throw new BusinessException("ExcelAnalysisImportError",e.getMessage());
		}
	}

	@Test
	public void testGroups(){
		loginEmployee.setTenantId(19);
		CustomerInfoBo result = crmCommonService.findByCrmCustomerId(110173);
		System.out.println(JSON.toJSONString(result));
	}

	@Test
	public void frozenInvoice(){
		boolean success = frozenReceivableInvoice("111116");
		System.out.println(success);
	}

	@Test
	public void unFrozenInvoice(){
		boolean success = unfrozenReceivableInvoice("111116");
		System.out.println(success);
	}

	@Test
	public void fetchInvoiceAmount(){
		List<InvoiceAmount> amounts = fetchInvoiceAmount("AR20190614141231235005461");
		System.out.println(JSON.toJSONString(amounts));
	}

	@Test
	public void validReceivableInvoiceAmount(){
		boolean success = validReceivableInvoiceAmount("AR20190614141231235005461",BigDecimal.valueOf(100));
		System.out.println(success);
	}

	@Test
	public void sendAdjustToInvoice(){
		mqService.sendAdjustReceiveToInvoice("AR20190614141231235005461",BigDecimal.valueOf(100));
	}

	@Test
	public void findWaybillList(){
//		List<Waybill> waybills = waybillService.findByWaybillIds(Lists.<Integer>newArrayList(1757967));
//		System.out.println(JSON.toJSONString(waybills));
	}

	@Test
	public void sendToinvoice(){
		ReconcilicationForReceivable master = new ReconcilicationForReceivable();
		master.setProjectId(8303);
		master.setAreaCode("000000");
		master.setCustomerId(106840);
		master.setCustomerName("宜花科技（上海）");
		master.setReconcilicationId(5461);
		master.setCreateTime(DateUtils.parse("2019-06-14 14:12:31").toDate());
		master.setReconcilicationNo("AR20190614141231235005461");
		sendMessageToInvoice(master,loginEmployee);
	}

	private void sendMessageToInvoice(ReconcilicationForReceivable master, LoginEmployee loginEmployee) {
		CustomerInvoiceEvent event = new CustomerInvoiceEvent();
		event.setIsMain(true);
		event.setIsRelation(false);
		event.setSourceDocumentNo(master.getReconcilicationNo());
		event.setBusinessType("LOGISTICS");
		event.setSourceDocumentType("RECONCILIATION");
		BusinessArea businessArea = authCommonService.loadBusinessArea(master.getAreaCode(),loginEmployee);
		if( null != businessArea ){
			event.setBusinessAreaId(businessArea.getBusinessAreaId());
			event.setBusinessAreaCode(businessArea.getAreaCode());
			event.setBusinessAreaName(businessArea.getAreaName());
		}
		CustomerInfo customerInfo = customerInfoService.findCusInfoById(master.getCustomerId());
		if( null != customerInfo ){
			CustomerInfoBo customerInfoBo = crmCommonService.findByCrmCustomerId(customerInfo.getCrmCustomerId());
			event.setCustomerId(Integer.valueOf(customerInfoBo.getCustomerId()));
			event.setCustomerName(customerInfoBo.getCustomerName());
			if( Byte.valueOf("1").equals(customerInfoBo.getCustomerIdentityType()) ){
				event.setCustomerType("PERSONAL");
			}
			if( Byte.valueOf("2").equals(customerInfoBo.getCustomerIdentityType()) ){
				event.setCustomerType("ENTERPRISE");
			}
		}
		event.setTenantId(loginEmployee.getTenantId());
		event.setSourceDocumentCreateDate(DateUtils.format(master.getCreateTime(), DateUtils.Parttern.FORMAT_YYMMDD_MID));
		CustomerInvoiceWaybill invoiceWaybill = new CustomerInvoiceWaybill();
		invoiceWaybill.setReconciliationDate(DateUtils.format(master.getCreateTime(), DateUtils.Parttern.FORMAT_YYMMDD_MID));
		// 获取运单完成时间 以及运单用车时间的最大最小值
		event.setPayload(invoiceWaybill);
		validAndSetWaybillTimeRange(master, event);
		if(reconciliationCommonService.validAndCreateReconciliationForInvoice(master,loginEmployee)){
			event.setIsRelation(true);
		}
		mqService.sendReceiveToInvoice(event);
	}

	/**获取运单时间范围**/
	private void validAndSetWaybillTimeRange(ReconcilicationForReceivable master, CustomerInvoiceEvent event) {
		ReconcilicationForReceivableItemExample itemExample = new ReconcilicationForReceivableItemExample();
		itemExample.createCriteria().andReconcilicationIdEqualTo(master.getReconcilicationId());
		List<ReconcilicationForReceivableItem> items = reconcilicationForReceivableItemMapper.selectByExample(itemExample);
		if( org.apache.commons.collections.CollectionUtils.isEmpty(items) ){
			return;
		}
		List<Integer> waybillIds = Lists.newArrayList();
		for (ReconcilicationForReceivableItem item : items){
			waybillIds.add(item.getWaybillId());
		}

//		List<Waybill> waybills = waybillService.findByWaybillIds(waybillIds);
//		if( org.apache.commons.collections.CollectionUtils.isEmpty(waybillIds) ){
//			return;
//		}
//		//完成时间
//		Date minFinishTime = null;
//		Date maxFinishTime = null;
//		//用车时间
//		Date minDeployTime = null;
//		Date maxDeployTime = null;
//		for (Waybill waybill : waybills){
//			if( null == waybill.getFinishTime() ){ continue; }
//			if( null == minFinishTime ){
//				minFinishTime = waybill.getFinishTime();
//			}else if( waybill.getFinishTime().before(minFinishTime) ){
//				minFinishTime = waybill.getFinishTime();
//			}
//			if( null == maxFinishTime ){
//				maxFinishTime = waybill.getFinishTime();
//			}else if( waybill.getFinishTime().after(minFinishTime) ){
//				maxFinishTime = waybill.getFinishTime();
//			}
//
//			if( null == minDeployTime ){
//				minDeployTime = waybill.getPlanDeliveryTime();
//			}else if( waybill.getPlanDeliveryTime().before(minDeployTime) ){
//				minDeployTime = waybill.getPlanDeliveryTime();
//			}
//			if( null == maxDeployTime ){
//				maxDeployTime = waybill.getPlanDeliveryTime();
//			}else if( waybill.getPlanDeliveryTime().after(maxDeployTime) ){
//				maxDeployTime = waybill.getPlanDeliveryTime();
//			}
//		}
//		Project project = projectService.getProjectV2(master.getProjectId());
//		if( null != project ) {
//			event.setCompanyId(project.getContractToCompany());
//			if( null != project.getContractToCompany() ){
//				Department department = authCommonService.loadDepartment(project.getContractToCompany());
//				if( null !=department ){
//					event.setCompanyCode(department.getDepartmentCode());
//					event.setCompanyName(department.getDepartmentName());
//				}
//			}
//			event.setContractCode(project.getContractNo());
//			event.setOtherInfo(project.getName()+"/"+DateUtils.format(minFinishTime)+"/"+DateUtils.format(maxFinishTime));
//			event.getPayload().setProjectId(project.getProjectId());
//			event.getPayload().setProjectName(project.getName());
//		}
//		event.getPayload().setWaybillFinishTime(DateUtils.format(minFinishTime)+"/"+DateUtils.format(maxFinishTime));
//		event.getPayload().setPlanDeliveryTime(DateUtils.format(minDeployTime)+"/"+DateUtils.format(maxDeployTime));
	}

	private void fetchAndSetAmountWithTax(CustomerInvoiceEvent event, List<Integer> waybillIds) {
		WaybillAmountFilter amountFilter = new WaybillAmountFilter();
		amountFilter.setWaybillIds(waybillIds);
//		List<WaybillAmount> amounts = waybillAmountService.findByFilter(amountFilter,loginEmployee);
//		BigDecimal amountWithTax = BigDecimal.ZERO;
//		for (WaybillAmount waybillAmount : amounts){
//			amountWithTax = amountWithTax.add(waybillAmount.getCustomerFreightWithTax());
//		}
//		event.setAmountIncludeTax(amountWithTax);
	}

	private void fetchAndSetTaxRate(CustomerInvoiceEvent event, List<Integer> waybillIds) {
		TruckRequireFilter truckRequireFilter = new TruckRequireFilter();
		truckRequireFilter.setWaybillIds(waybillIds);
//		List<TruckRequire> requires = truckRequireService.findByFilter(truckRequireFilter,loginEmployee);
//		Map<Integer,TruckRequire> requireMap = Maps.newConcurrentMap();
//		BigDecimal rate = BigDecimal.ZERO;
//		for (TruckRequire require : requires){
//			rate = require.getTaxRateValue();
//		}
//		event.setTaxRate(rate);
	}


	@Value("${tms.customer.reconciliation.invoice.url}")
	private String BASE_URL;
	/****/
	private static final String ERROR_INFO_SIGN = "businessErrorKey";
	/**获取开票数据金额**/
	private static final String INVOICE_AMOUNT_INFO_URL = "/invoice/datapool/amount.html";
	/**开票数据冻结**/
	private static final String INVOICE_FROZEN_INFO_URL = "/invoice/datapool/frozen.html";
	/**开票数据解冻**/
	private static final String INVOICE_UNFROZEN_INFO_URL = "/invoice/datapool/unfreeze.html";

	public boolean validReceivableInvoiceAmount(final String reconcilicationNo, final BigDecimal afterReconciliationAmount) throws BusinessException {
		List<InvoiceAmount> invoiceAmounts = fetchInvoiceAmount(reconcilicationNo);
		BigDecimal totalInvoicedAmount = BigDecimal.ZERO;
		BigDecimal totalInvoicingAmount = BigDecimal.ZERO;
		for (InvoiceAmount invoiceAmount : invoiceAmounts) {
			totalInvoicedAmount = totalInvoicedAmount.add(invoiceAmount.getInvoicedAmount());
			totalInvoicingAmount = totalInvoicingAmount.add(invoiceAmount.getInvoicingAmount());
		}
		return afterReconciliationAmount.compareTo(totalInvoicedAmount.add(totalInvoicingAmount)) >= 0;
	}

	public List<InvoiceAmount> fetchInvoiceAmount(final String reconciliationNo) throws BusinessException {
		String result;
		try {
			result = Https.create()
					.print(true)
					.url(BASE_URL + INVOICE_AMOUNT_INFO_URL)
					.add("sourceDocumentNo",reconciliationNo)
					.get();
		} catch (Exception e) {
			throw new BusinessException("FetchInvoiceNotNull","开票信息获取失败");
		}

		if(StringUtils.isBlank(result) ){
			return Lists.newArrayList();
		}

		JSONObject jsonObject = JSON.parseObject(result);
		if( !Integer.valueOf(0).equals(jsonObject.get("code")) ){
			log.warn("发票错误信息:{}",result);
			return Lists.newArrayList();
		}

		List<InvoiceAmount> list = JSON.parseArray(result,InvoiceAmount.class);
		if(CollectionUtils.isEmpty(list)){
			return Lists.newArrayList();
		}

		return list;
	}

	public boolean frozenReceivableInvoice(final String reconciliationNo) throws BusinessException {
        String result;
        try {
            result = Https.create()
                    .print(true)
                    .url(BASE_URL + INVOICE_FROZEN_INFO_URL)
                    .addBody("sourceDocumentNo",reconciliationNo)
                    .post();
        } catch (Exception e) {
        	log.warn("请求错误,错误信息:{}",e.getMessage());
            throw new BusinessException("FrozenReconciliationFail","冻结开票信息失败");
        }

        if(StringUtils.isBlank(result) ){
            return false;
        }

		JSONObject jsonObject = JSON.parseObject(result);
		if( !Integer.valueOf(0).equals(jsonObject.get("code")) ){
            log.error("冻结:错误信息:{}",result);
            return false;
        }
        return true;
	}

	public boolean unfrozenReceivableInvoice(final String reconciliationNo) throws BusinessException {
        String result;
        try {
            result = Https.create()
                    .print(true)
                    .url(BASE_URL + INVOICE_UNFROZEN_INFO_URL)
                    .addBody("sourceDocumentNo",reconciliationNo)
                    .post();
        } catch (Exception e) {
            throw new BusinessException("UnFrozenReconciliationFail","解冻开票信息失败");
        }

        if(StringUtils.isBlank(result) ){
            return false;
        }

        JSONObject jsonObject = JSON.parseObject(result);
		if( !Integer.valueOf(0).equals(jsonObject.get("code")) ){
			log.error("解冻:错误信息:{}",result);
			return false;
		}

		return true;
	}

	@Test
	public void testAmount(){
		String json = "{\"code\":0,\"data\":[{\"invoicedAmount\":0.00,\"invoicingAmount\":2500.00,\"noinvoiceAmount\":10000.00,\"period\":-1,\"sourceDocumentNo\":\"AR20190801184004606008575\",\"totalAmount\":12500.00,\"vin\":\"\"}],\"message\":\"操作成功！\"}";
		JSONObject jsonObject = JSON.parseObject(json);
		List<InvoiceAmount> list = JSON.parseArray(jsonObject.getString("data"),InvoiceAmount.class);
		System.out.println(JSON.toJSONString(list));
		// 如果是多条数据(转运单), 任何一条不满足, 都不能调价
		BigDecimal afterAdjustAmount = BigDecimal.valueOf(6000);
		for (InvoiceAmount invoiceAmount : list) {
			if( null == invoiceAmount ){ continue; }
			BigDecimal invoicedAmount = BigDecimal.ZERO;
			BigDecimal invoicingAmount = BigDecimal.ZERO;
			if( null != invoiceAmount.getInvoicedAmount() ){
				invoicedAmount = invoiceAmount.getInvoicedAmount();
			}
			if( null != invoiceAmount.getInvoicingAmount() ){
				invoicingAmount = invoiceAmount.getInvoicingAmount();
			}
			if(afterAdjustAmount.compareTo(invoicedAmount.add(invoicingAmount)) < 0){
				System.out.println("false");
			}
		}
	}

	@Autowired
	private ReconcilicationForReceivableService reconcilicationForReceivableService;

	/**获取对账单调整后的金额**/
	@Test
	public void fetchReceivableReconciliationAmountAfterAdjust() {
		// String reconciliationNo, List<AdjustForWaybillTemp> list, LoginEmployee loginUser
		LoginUser loginUser = new LoginUser();
		loginUser.setTenantId(19);
		loginUser.setUserId(1);
		ReconcilicationForReceivable receivable = reconcilicationForReceivableService.findReconciliationByReconciliationNo("AR20190801114344977005471");
		List<ReconcilicationForReceivableItem> items = reconcilicationForReceivableService.findReceivableItemsByReconciliationId(receivable.getReconcilicationId());
		List<Integer> waybillIds = Lists.newArrayList();
		// 运单ID集合收集
		for (ReconcilicationForReceivableItem item : items){
			waybillIds.add(item.getWaybillId());
		}
		// 运单金额收集
		WaybillAmountFilter filter = new WaybillAmountFilter();
		filter.setWaybillIds(waybillIds);
		List<WaybillAmount> amounts = waybillAmountService.findByFilter(filter,loginUser);
		Map<Integer,WaybillAmount> amountMap = Maps.newConcurrentMap();
		for (WaybillAmount amount : amounts){
			amountMap.put(amount.getWaybillId(),amount);
		}
		// 运单调整金额收集
		Map<Integer,AdjustForWaybillTemp> waybillTempMap = Maps.newConcurrentMap();
		List<AdjustForWaybillTemp> temps = Lists.newArrayList();
		AdjustForWaybillTemp tempa = new AdjustForWaybillTemp();
		for (AdjustForWaybillTemp temp : temps) {
			waybillTempMap.put(temp.getWaybillId(),temp);
		}
		// 对账单-调整后金额计算
		List<Waybill> waybills = waybillService.findByWaybillIds(waybillIds);
		BigDecimal afterAdjustAmount = BigDecimal.ZERO;
		for (Waybill waybill : waybills){
			WaybillAmount waybillAmount = amountMap.get(waybill.getWaybillId());
			BigDecimal finalWaybillAmount = waybill.getEstimateFreight();
			if( null != waybillAmount ){
				finalWaybillAmount = waybillAmount.getLastCustomerFreightWithTax();
			}
			AdjustForWaybillTemp temp = waybillTempMap.get(waybill.getWaybillId());
			if( null != temp ){
				finalWaybillAmount = finalWaybillAmount
						.add(validAndSetBigDecimalValue(temp.getAdjustForFreight()))
						.add(validAndSetBigDecimalValue(temp.getAdjustForCarry()))
						.add(validAndSetBigDecimalValue(temp.getAdjustForWorkload()))
						.add(validAndSetBigDecimalValue(temp.getAdjustForUpstairs()))
						.add(validAndSetBigDecimalValue(temp.getAdjustForFine()))
						.add(validAndSetBigDecimalValue(temp.getAdjustForCargoLoss()));
			}
			afterAdjustAmount = afterAdjustAmount.add(finalWaybillAmount);
		}
		System.out.println(afterAdjustAmount);
	}

	private BigDecimal validAndSetBigDecimalValue(BigDecimal amount) {
        return null != amount ? amount : BigDecimal.ZERO;
    }

	public void should_return_waybill_adjust_list_map_with_waybill_ids(){
		AdjustForMasterFilter adjustForMasterFilter = new AdjustForMasterFilter();
		adjustForMasterFilter.setWaybillIds(Lists.<Integer>newArrayList(1390012,1418156));
		adjustForMasterFilter.setApprovalStatus(com.juma.tgm.common.workflow.ApprovalStatus.APPROVAL_SUBMIT.getCode());
		Map<Integer,List<AdjustForMaster>> adjustForMasterMap = adjustForMasterAddService.findMasterDictionaryByFilter(adjustForMasterFilter);
		Assert.notEmpty(adjustForMasterMap);
		System.out.println(JSON.toJSONString(adjustForMasterMap));
	}
}
