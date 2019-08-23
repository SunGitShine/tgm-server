package com.juma.tgm.adjust.service.impl;

import com.alibaba.fastjson.JSON;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.tgm.common.query.QueryCond;
import com.juma.tgm.fms.dao.v3.AdjustForMasterMapper;
import com.juma.tgm.fms.domain.v3.AdjustForMaster;
import com.juma.tgm.fms.domain.v3.bo.ReconcilicationForPayApply;
import com.juma.tgm.fms.domain.v3.enums.AdjustMasterType;
import com.juma.tgm.fms.domain.v3.vo.AdjustForMasterVo;
import com.juma.tgm.fms.domain.v3.vo.WaybillStatisticsAmountVO;
import com.juma.tgm.fms.service.v3.AdjustForMasterService;
import com.juma.tgm.fms.service.v3.ReconcilicationForPayApplyService;
import com.juma.tgm.fms.service.v3.ReconcilicationForPayableService;
import com.juma.tgm.project.service.ProjectService;
import com.juma.tgm.tools.service.VmsCommonService;
import com.juma.tgm.waybill.service.WaybillAmountService;
import com.juma.tgm.waybill.service.WaybillCommonService;
import org.testng.annotations.Test;
import org.testng.collections.Lists;
import testng.BaseTestNGTest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Map;

/**
 * @description: ${description}
 *
 * @author: xieqiang
 *
 * @create: 2019-05-10 16:02
 **/
public class AdjustForMasterServiceTest extends BaseTestNGTest {

	/**这里需要启动manage,通过断点方式获取json字符串,在这里使用**/
	private static final LoginEmployee loginEmployee = new Gson().fromJson(
		"{\"employeeId\":2904,\"maxInactiveInterval\":3600,\"loginDepartment\":{\"departmentId\":1479,\"departmentCode\":\"00\",\"departmentName\":\"驹马配送\",\"businessAreas\":[{\"areaCode\":\"00\",\"areaName\":\"全国\"}]},\"authDepartments\":[{\"departmentId\":1479,\"departmentName\":\"驹马配送\"}],\"sessionId\":\"757EB2EB0BCF4DF09BB0BFC4F4C5BD21\",\"tenantId\":19,\"tenantCode\":\"000000004\",\"userId\":1,\"loginName\":\"admin\",\"userName\":\"超级管理员\",\"mobileNumber\":\"18030574832\",\"isTest\":true,\"isSysUser\":true}",
		LoginEmployee.class);

	@Resource
	private AdjustForMasterService adjustForMasterService;

	@Resource
	private ReconcilicationForPayApplyService reconcilicationForPayApplyService;

	@Test
	public void adjustPageTest(){

		QueryCond<AdjustForMasterVo> queryCond = new QueryCond<>();
		queryCond.setPageNo(1);
		queryCond.setPageSize(10);
		queryCond.setFilters(new AdjustForMasterVo());

//		System.out.println(JSON.toJSONString(adjustForMasterService.findAdjustForMasterPage(queryCond, loginEmployee)));
	}

	@Test
	public void testDetailAmount(){
		loginEmployee.setTenantId(19);
		System.out.println(JSON.toJSONString(adjustForMasterService.findAdjustDetail(365,loginEmployee)));
	}

	@Resource
	private ProjectService projectService;

	@Resource
	private VmsCommonService vmsCommonService;

	@Resource
	private AdjustForMasterMapper adjustForMasterMapper;

	@Resource
	private WaybillCommonService waybillCommonService;

	@Resource
	private WaybillAmountService waybillAmountService;

	@Resource
	private ReconcilicationForPayableService reconcilicationForPayableService;


	@Test
	public void testAdjustAmount(){
		AdjustForMaster master = adjustForMasterMapper.selectByPrimaryKey(300);
		BigDecimal profitRateDiffer = BigDecimal.ZERO;
		WaybillStatisticsAmountVO waybillStatisticsAmountVO = new WaybillStatisticsAmountVO();
		waybillStatisticsAmountVO.setCustomerBeforeHasTaxAmount(BigDecimal.ZERO);
		waybillStatisticsAmountVO.setCustomerAfterHasTaxAmount(BigDecimal.ZERO);
		waybillStatisticsAmountVO.setVendorHasTaxAmount(BigDecimal.ZERO);
		waybillStatisticsAmountVO.setVendorBeforeHasTaxAmount(BigDecimal.ZERO);
		waybillStatisticsAmountVO.setVendorAfterHasTaxAmount(BigDecimal.ZERO);
		waybillStatisticsAmountVO.setCustomerHasTaxAmount(BigDecimal.ZERO);
		waybillStatisticsAmountVO.setBeforeProfitAmount(BigDecimal.ZERO);
		waybillStatisticsAmountVO.setAfterProfitAmount(BigDecimal.ZERO);
		if(AdjustMasterType.CUSTOMER.getCode().equals(master.getAdjustForWho())){
			/**计算客户侧毛利率**/
			adjustForMasterService.calculateCustomerProportion(master,waybillStatisticsAmountVO);
			Double rateGap = fetchPercentage(waybillStatisticsAmountVO.getAfterProportionGap());
			if( null != rateGap ){
				profitRateDiffer = BigDecimal.valueOf(rateGap);
			}
		}else if(AdjustMasterType.VENDOR.getCode().equals(master.getAdjustForWho())){
			/**计算承运侧毛利率**/
			adjustForMasterService.calculateVendorProportion(master,waybillStatisticsAmountVO);
			Double rateGap = fetchPercentage(waybillStatisticsAmountVO.getAfterProportionGap());
			if( null != rateGap ){
				profitRateDiffer = BigDecimal.valueOf(rateGap);
			}
		}
		System.out.println(profitRateDiffer);
	}

	@Test
	public void jodaTest(){
	}

	private Double fetchPercentage(String key) {
		try {
			NumberFormat nf= NumberFormat.getPercentInstance();
			Number optionAmount = nf.parse(key);
			return optionAmount.doubleValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Test
	public void testSearch(){
		PageCondition pageCondition = new PageCondition();
		pageCondition.setPageNo(1);
		pageCondition.setPageSize(15);
		Map<String,Object> params = Maps.newConcurrentMap();
		params.put("areaCodeList",Lists.newArrayList("00"));
		params.put("startTime","2019-06-01 00:00:00");
		params.put("endTime","2019-07-12 23:59:59");
		params.put("projectId",7050);
		pageCondition.setFilters(params);
		Page<ReconcilicationForPayApply> pages = reconcilicationForPayApplyService.search(pageCondition,loginEmployee);
		System.out.println(JSON.toJSONString(pages));
	}

}
