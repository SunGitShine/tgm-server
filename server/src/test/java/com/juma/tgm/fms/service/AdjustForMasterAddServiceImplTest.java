package com.juma.tgm.fms.service;

import com.google.gson.Gson;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.crm.support.service.CrmService;
import com.juma.tgm.common.id.IdGeneratorTable;
import com.juma.tgm.common.query.QueryCond;
import com.juma.tgm.crm.domain.CustomerInfo;
import com.juma.tgm.crm.service.CustomerInfoService;
import com.juma.tgm.fms.dao.v3.AdjustForFreightAttachMapper;
import com.juma.tgm.fms.dao.v3.AdjustForItemMapper;
import com.juma.tgm.fms.dao.v3.AdjustForMasterMapper;
import com.juma.tgm.fms.dao.v3.ReconciliationExtraForPayableMapper;
import com.juma.tgm.fms.dao.v3.ReconcilicationForPayableLogMapper;
import com.juma.tgm.fms.domain.v3.*;
import com.juma.tgm.fms.domain.v3.enums.AdjustMasterType;
import com.juma.tgm.fms.domain.v3.enums.AdjustType;
import com.juma.tgm.fms.domain.v3.vo.AdjustForItemFilter;
import com.juma.tgm.fms.domain.v3.vo.AdjustForMasterAddVO;
import com.juma.tgm.fms.domain.v3.vo.AdjustForMasterFilter;
import com.juma.tgm.fms.domain.v3.vo.ReconciliationPayableItemFilter;
import com.juma.tgm.fms.service.impl.v3.AdjustForMasterAddServiceImpl;
import com.juma.tgm.fms.service.impl.v3.SendPayableServiceV3;
import com.juma.tgm.fms.service.impl.v3.SendReceivableService;
import com.juma.tgm.fms.service.v3.AdjustForWaybillService;
import com.juma.tgm.fms.service.v3.ReconciliationCommonService;
import com.juma.tgm.fms.service.v3.ReconcilicationForPayableService;
import com.juma.tgm.fms.service.v3.ReconcilicationForReceivableService;
import com.juma.tgm.fms.service.v3.ReconcilicationForSubPayableService;
import com.juma.tgm.id.service.IdGeneratorService;
import com.juma.tgm.mq.service.MqService;
import com.juma.tgm.tool.domain.ExecuteWorkflowInfo;
import com.juma.tgm.tools.service.AuthCommonService;
import com.juma.tgm.tools.service.FmsCommonService;
import com.juma.tgm.tools.service.InvoiceCommonService;
import com.juma.tgm.tools.service.VmsCommonService;
import com.juma.tgm.tools.service.WorkflowCommonService;
import com.juma.tgm.waybill.domain.TruckRequire;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillAmount;
import com.juma.tgm.waybill.domain.vo.WaybillAmountFilter;
import com.juma.tgm.waybill.service.TruckRequireService;
import com.juma.tgm.waybill.service.WaybillAmountService;
import com.juma.tgm.waybill.service.WaybillCommonService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * 功能 : 
 *
 * @author : Bruce(刘正航) 11:55 2019-05-30
 */
public class AdjustForMasterAddServiceImplTest {

    private static final LoginEmployee loginEmployee = new Gson().fromJson(
            "{\"employeeId\":2904,\"maxInactiveInterval\":3600,\"loginDepartment\":{\"departmentId\":1479,\"departmentCode\":\"00\",\"departmentName\":\"驹马配送\",\"businessAreas\":[{\"areaCode\":\"00\",\"areaName\":\"全国\"}]},\"authDepartments\":[{\"departmentId\":1479,\"departmentName\":\"驹马配送\"}],\"sessionId\":\"757EB2EB0BCF4DF09BB0BFC4F4C5BD21\",\"tenantId\":19,\"tenantCode\":\"000000004\",\"userId\":1,\"loginName\":\"admin\",\"userName\":\"超级管理员\",\"mobileNumber\":\"18030574832\",\"isTest\":true,\"isSysUser\":true}",
            LoginEmployee.class);

    @Mock
    AdjustForItemMapper adjustForItemMapper;
    @Mock
    AdjustForMasterMapper adjustForMasterMapper;
    @Mock
    AdjustForFreightAttachMapper adjustForFreightAttachMapper;
    @Mock
    ReconcilicationForPayableLogMapper reconcilicationForPayableLogMapper;
    @Mock
    ReconciliationExtraForPayableMapper reconciliationExtraForPayableMapper;
    @Mock
    MqService mqService;
    @Mock
    CrmService crmService;
    @Mock
    FmsCommonService fmsCommonService;
    @Mock
    VmsCommonService vmsCommonService;
    @Mock
    SendReceivableService sendService;
    @Mock
    SendPayableServiceV3 sendPayableService;
    @Mock
    AuthCommonService authCommonService;
    @Mock
    CustomerInfoService customerInfoService;
    @Mock
    IdGeneratorService idGeneratorService;
    @Mock
    TruckRequireService truckRequireService;
    @Mock
    InvoiceCommonService invoiceCommonService;
    @Mock
    WaybillCommonService waybillCommonService;
    @Mock
    WaybillAmountService waybillAmountService;
    @Mock
    WorkflowCommonService workflowCommonService;
    @Mock
    AdjustForWaybillService adjustForWaybillService;
    @Mock
    ReconciliationCommonService reconciliationCommonService;
    @Mock
    ReconcilicationForPayableService reconcilicationForPayableService;
    @Mock
    ReconcilicationForReceivableService reconcilicationForReceivableService;
    @Mock
    ReconcilicationForSubPayableService reconcilicationForSubPayableService;
    @Mock
    Logger log;
    @InjectMocks
    AdjustForMasterAddServiceImpl adjustForMasterAddServiceImpl;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateOrUpdateAdjust() {
//        when(idGeneratorService.genAdjustFormMasterNo((IdGeneratorTable.IdType)any())).thenReturn("genAdjustFormMasterNoResponse");
//        when(invoiceCommonService.validReceivableInvoiceAmount(anyString(), (BigDecimal) any())).thenReturn(true);
//        when(workflowCommonService.startWorkflow((ExecuteWorkflowInfo)any(), (LoginEmployee)any())).thenReturn(null);
//        AdjustForWaybillTemp listItem = new AdjustForWaybillTemp();
//        when(adjustForWaybillService.findByFilter((QueryCond)any(), (LoginEmployee)any())).thenReturn(Arrays.<AdjustForWaybillTemp>asList(new AdjustForWaybillTemp()));
//        AdjustForMasterAddVO vo = new AdjustForMasterAddVO();
//        vo.setAdjustType(AdjustType.BEFORE.getCode());
//        vo.setAdjustForWho(AdjustMasterType.CUSTOMER.getCode());
//        vo.setAdjustForReason("测试原因哈哈哈哈哈啊哈哈");
//        vo.setCargoLossProofAttach("http://www.baidu.com");
//        adjustForMasterAddServiceImpl.masterCreateOrUpdateAdjust(vo, loginEmployee);
    }

    @Test
    public void testFindByFilter() {
        List<AdjustForMaster> result = adjustForMasterAddServiceImpl.findByFilter(new AdjustForMasterFilter(), null);
        Assert.assertEquals(result, Arrays.<AdjustForMaster>asList(new AdjustForMaster()));
    }

    @Test
    public void testFindItemByFilter() {
        List<AdjustForItem> result = adjustForMasterAddServiceImpl.findItemByFilter(new AdjustForItemFilter(), null);
        Assert.assertEquals(result, Arrays.<AdjustForItem>asList(new AdjustForItem()));
    }

}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme