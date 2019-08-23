package com.juma.tgm.fms.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.fms.domain.Reconciliation;
import com.juma.tgm.fms.domain.Task;
import com.juma.tgm.fms.domain.v3.vo.ReceivableApplyVo;
import com.juma.tgm.fms.service.v2.ReconciliationBootstrapService;
import com.juma.tgm.fms.service.v2.ReconciliationService;
import com.juma.tgm.fms.service.v2.ReconciliationSyncService;
import com.juma.tgm.fms.service.v3.ReconcilicationForReceivableService;
import com.juma.tgm.id.service.IdGeneratorService;
import testng.BaseTestNGTest;

public class ReconciliationBootstrapMapperTest extends BaseTestNGTest {


    @Autowired
    private ReconciliationBootstrapService reconciliationBootstrapService;


    @Autowired
    private ReconciliationService reconciliationService;

    @Autowired
    private ReconciliationSyncService reconciliationSyncService;

    @Autowired
    private ReconcilicationForReceivableService reconcilicationForReceivableService;
    
    @Resource
    private IdGeneratorService idGeneratorService;

    @Test
    public void statistics() {
        //JSONObject.toJSONString( reconciliationBootstrapMapper.statistics(5,1,0,20 , null , null ));
        LoginUser loginUser = new LoginUser();
        List<Integer> wids = new ArrayList<>();
        wids.add(152784);
//        wids.add(2108);
        loginUser.setTenantCode("000000004");
        loginUser.setTenantId(9);
        loginUser.setUserId(1);
        reconciliationService.createReconciliation(wids, loginUser);
        loginUser.setLoginName("admin");
    }


    @Test
    public void sendTofms() {
        Task task = new Task();
        task.setReconciliationId(48);
        LoginEmployee loginEmployee = new LoginEmployee();
        loginEmployee.setUserId(1);
        loginEmployee.setTenantId(9);
        Map<String, Object> param = new HashMap<>();
        param.put("status", Reconciliation.ReconciliationStatus.AGREE.getCode());
//        reconciliationService.fmsTest(task, loginEmployee, param);
    }


    @Test
    public void updateWaybillReceiptStatusTest() {
        String reconciliationNo = "2018060911081326332000152";
        Integer customerId = 18;
        String customerName = "大东鞋业";
        BigDecimal receipt = new BigDecimal("1");

//        reconciliationSyncService.receipt(reconciliationNo, customerId, customerName, receipt);


    }

    @Test
    public void updateWaybillSettlementStatusTest() {
        String reconciliationNo = "2018060911081326332000152";
        String plateNumber = "川AVZ705";
        Integer settlementState = 2;
        BigDecimal settlement = new BigDecimal("1");
//        reconciliationSyncService.settlement(reconciliationNo, plateNumber, settlementState, settlement);
    }

    @Test
    public void findReceivableApplyPageTest(){

        LoginUser loginUser = new LoginUser();
        loginUser.setUserId(4524);
        loginUser.setTenantId(26);

        PageCondition pageCondition = new PageCondition();
        pageCondition.setPageNo(1);
        pageCondition.setPageSize(10);
//        pageCondition.getFilters().put("customerName", "龙邦快递");
        Page<ReceivableApplyVo> pageData = reconcilicationForReceivableService.findReceivableApplyPage(pageCondition, loginUser);
        System.out.println("========="+JSON.toJSONString(pageData));
    }
}
