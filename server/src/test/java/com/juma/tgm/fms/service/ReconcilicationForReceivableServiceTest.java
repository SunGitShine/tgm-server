package com.juma.tgm.fms.service;

import com.alibaba.fastjson.JSON;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.fms.domain.Task;
import com.juma.tgm.fms.domain.v3.AdjustForReceivable;
import com.juma.tgm.fms.domain.v3.ReconcilicationForPayable;
import com.juma.tgm.fms.domain.v3.vo.ReceivableApplyVo;
import com.juma.tgm.fms.domain.v3.vo.ReconciliationWaybillDetailVo;
import com.juma.tgm.fms.domain.v3.vo.ReconcilicationForReceivableVo;
import com.juma.tgm.fms.service.v3.ReconcilicationForPayableService;
import com.juma.tgm.fms.service.v3.ReconcilicationForReceivableService;
import com.juma.tgm.imageUploadManage.domain.ImageUploadManage;
import com.juma.tgm.imageUploadManage.service.ImageUploadManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import testng.BaseTestNGTest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReconcilicationForReceivableServiceTest extends BaseTestNGTest {

	@Autowired
	private ReconcilicationForReceivableService service;

	@Autowired
	private ImageUploadManageService imageUploadManageService;

	@Autowired
	private ReconcilicationForPayableService payableService;
	@Resource
	private ReconcilicationForPayableService reconcilicationForPayableService;

	@Test
	public void findReceivableApplyPage(){

		LoginUser loginUser = makeLoginUser();
		PageCondition pageCondition = makePage();
//		pageCondition.getFilters().put("areaCode", "00,01,02");
		Page<ReceivableApplyVo> pageData = service.findReceivableApplyPage(pageCondition, loginUser);
		System.out.println("========="+ JSON.toJSONString(pageData));
	}

	@Test
	public void searchWaybillsTest(){

		LoginEmployee loginUser = makeloginEmployee();
		PageCondition pageCondition = makePage();
		pageCondition.getFilters().put("customerId","19");
//		pageCondition.getFilters().put("projectId","ISNULL");

		Page<ReconciliationWaybillDetailVo> detailVoPage = service.searchWaybills(loginUser, pageCondition);
		System.out.println(JSON.toJSONString(detailVoPage));
	}

	@Test
	public void updateFreightTest(){

		LoginEmployee loginEmployee = makeloginEmployee();
		AdjustForReceivable adjust = new AdjustForReceivable();
		adjust.setWaybillId(2515);
		adjust.setTaxRateAdjust(new BigDecimal("0.25"));
		adjust.setReceivableWithTaxAdjust(new BigDecimal("250"));
		adjust.setAdjustRemark("改价测试1");
		service.updateFreight(adjust, loginEmployee);
	}

	@Test
	public void findAdjustByPage(){

		PageCondition pageCondition = makePage();
		pageCondition.getFilters().put("waybillId", "2517");
		System.out.println(JSON.toJSONString(service.findAdjustByPage(pageCondition)));
	}

	@Test
	public void createReceivableReconciliationTest(){

		LoginEmployee loginEmployee = makeloginEmployee();

		ReconcilicationForReceivableVo receivableVo = new ReconcilicationForReceivableVo();
		List<Integer> waybillIds = new ArrayList<>();
		waybillIds.add(new Integer(999791));
		waybillIds.add(new Integer(999790));
		waybillIds.add(new Integer(999788));
		service.createReceivableReconciliation(waybillIds, loginEmployee);
	}

	public LoginUser makeLoginUser(){
		LoginUser loginUser = new LoginUser();
		loginUser.setUserId(4524);
		loginUser.setTenantId(2);
		return loginUser;
	}

	public LoginEmployee makeloginEmployee(){
		LoginEmployee loginEmployee = new LoginEmployee();
		loginEmployee.setUserId(4524);
		loginEmployee.setTenantId(19);
		return loginEmployee;
	}

	public PageCondition makePage(){

		PageCondition pageCondition = new PageCondition();
		pageCondition.setPageNo(1);
		pageCondition.setPageSize(10);

		return pageCondition;
	}

	@Test
	public void workFlow(){

		Task task = new Task();
		task.setTaskId("1");
		task.setReconciliationId(14);
		task.setApprovalKey("同意");
		task.setComment("审批测试");
//		service.finishWorkFlowTask(task, makeloginEmployee());
	}

	@Test
	public void findReconcilicationForReceivablePage(){
		System.out.println(JSON.toJSONString(service.findReceivableReconciliationPage(makePage(), makeloginEmployee())));
	}

	@Test
	public void findReconcilicationForReceivableItemPage(){

		PageCondition pageCondition = makePage();
		pageCondition.getFilters().put("reconcilicationId", "12");
		System.out.println(JSON.toJSONString(service.findReceivableReconciliationItemPage(pageCondition)));
	}

	@Test
	public void listByRelationIdAndSign(){
		System.out.println(JSON.toJSONString(imageUploadManageService.listByRelationIdAndSign(12, ImageUploadManage.ImageUploadManageSign.RECONCILIATION_RECEIVABLE.getCode())));
	}

	@Test
	public void sendToFms(){
//		payableService.sendToFMS("AP20190219160832397001397", makeloginEmployee());
		service.sendToFMS("AR20190423134825604005417");
	}

	@Test
	public void findByReconciliationNo() {
		ReconcilicationForPayable ap20190111043803280000001 = reconcilicationForPayableService
			.findByReconciliationNo("AP20190111043803280000001");
	}

	@Test
	public void findPayable(){
		Map<String,Object> map = new HashMap<>();
		map.put("reconcilicationId", 788);
		PageCondition pageCondition = new PageCondition();
		pageCondition.setFilters(map);
		pageCondition.setPageNo(1);
		pageCondition.setPageSize(15);
		reconcilicationForPayableService.vendorSearchDetail(pageCondition, makeLoginUser());
	}

	@Test
	private void calculateProfitRateDifferTest(){
		ReconcilicationForPayable payable = reconcilicationForPayableService.findByReconciliationNo("AP20190627184645254001400");
		System.out.println(reconcilicationForPayableService.calculateProfitRateDiffer(payable));
	}
}
