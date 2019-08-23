package com.juma.tgm.adjust.service.impl;

import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.common.query.QueryCond;
import com.juma.tgm.crm.domain.CustomerInfo;
import com.juma.tgm.crm.service.CustomerInfoService;
import com.juma.tgm.fms.dao.v3.AdjustForWaybillTempMapper;
import com.juma.tgm.fms.domain.v3.AdjustForItem;
import com.juma.tgm.fms.domain.v3.AdjustForMaster;
import com.juma.tgm.fms.domain.v3.AdjustForWaybillTemp;
import com.juma.tgm.fms.domain.v3.vo.*;
import com.juma.tgm.fms.service.impl.v3.AdjustForWaybillServiceImpl;
import com.juma.tgm.fms.service.v3.AdjustForMasterAddService;
import com.juma.tgm.project.domain.v2.Project;
import com.juma.tgm.project.service.ProjectService;
import com.juma.tgm.tools.service.AuthCommonService;
import com.juma.tgm.tools.service.FmsCommonService;
import com.juma.tgm.tools.service.InvoiceCommonService;
import com.juma.tgm.tools.service.VmsCommonService;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillAmount;
import com.juma.tgm.waybill.domain.vo.WaybillAmountFilter;
import com.juma.tgm.waybill.service.WaybillAmountService;
import com.juma.tgm.waybill.service.WaybillService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mybatis.generator.my.page.Page;
import org.slf4j.Logger;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

import static org.mockito.Mockito.*;

/**
 * 功能 : 
 *
 * @author : Bruce(刘正航) 09:59 2019-06-04
 */
public class AdjustForWaybillServiceImplTest {
    @Mock
    ScheduledExecutorService executorService;
    @Mock
    WaybillService waybillService;
    @Mock
    ProjectService projectService;
    @Mock
    FmsCommonService fmsCommonService;
    @Mock
    VmsCommonService vmsCommonService;
    @Mock
    AuthCommonService authCommonService;
    @Mock
    CustomerInfoService customerInfoService;
    @Mock
    InvoiceCommonService invoiceCommonService;
    @Mock
    WaybillAmountService waybillAmountService;
    @Mock
    AdjustForMasterAddService adjustForMasterAddService;
    @Mock
    AdjustForWaybillTempMapper adjustForWaybillTempMapper;
    @Mock
    Logger log;
    @InjectMocks
    AdjustForWaybillServiceImpl adjustForWaybillServiceImpl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindPageByFilter() throws Exception {
        when(waybillService.findByWaybillNos((List)any())).thenReturn(Arrays.<Waybill>asList(new Waybill()));
        when(projectService.getProjectV2(anyInt())).thenReturn(new Project());
        when(vmsCommonService.loadVendorByVendorId(anyInt())).thenReturn(null);
        when(vmsCommonService.loadDriverByDriverId(anyInt())).thenReturn(null);
        when(customerInfoService.findCusInfoById(anyInt())).thenReturn(new CustomerInfo());

        Page<AdjustForWaybillVO> result = adjustForWaybillServiceImpl.findPageByFilter(new QueryCond<AdjustForWaybillTempFilter>(), null);
        Assert.assertEquals(null, result);
    }

    @Test
    public void testFindByFilter() throws Exception {
        List<AdjustForWaybillTemp> result = adjustForWaybillServiceImpl.findByFilter(new QueryCond<AdjustForWaybillTempVO>(), null);
        Assert.assertEquals(Arrays.<AdjustForWaybillTemp>asList(new AdjustForWaybillTemp()), result);
    }

    @Test
    public void testAddTemp() throws Exception {
        adjustForWaybillServiceImpl.addTemp(new AdjustForWaybillTemp(), null);
    }

    @Test
    public void testAddTempBatch() throws Exception {
        adjustForWaybillServiceImpl.addTempBatch(Integer.valueOf(0), Arrays.<AdjustForWaybillTemp>asList(new AdjustForWaybillTemp()), null);
    }

    @Test
    public void testUpdateTempBatch() throws Exception {
        adjustForWaybillServiceImpl.updateTempBatch(Integer.valueOf(0), Arrays.<AdjustForWaybillTemp>asList(new AdjustForWaybillTemp()), null);
    }

    @Test
    public void testDeleteTempById() throws Exception {
        adjustForWaybillServiceImpl.deleteTempById(Integer.valueOf(0), null);
    }

    @Test
    public void testDeleteByAdjustId() throws Exception {
        adjustForWaybillServiceImpl.deleteByAdjustId(Integer.valueOf(0), null);
    }

    @Test
    public void testCoverTempByUser() throws Exception {
        when(adjustForMasterAddService.findByFilter((AdjustForMasterFilter)any(), (LoginEmployee)any())).thenReturn(Arrays.<AdjustForMaster>asList(new AdjustForMaster()));
        when(adjustForMasterAddService.findItemByFilter((AdjustForItemFilter)any(), (LoginEmployee)any())).thenReturn(Arrays.<AdjustForItem>asList(new AdjustForItem()));

        adjustForWaybillServiceImpl.coverTempByUser(Integer.valueOf(0), null);
    }

    @Test
    public void testValidWaybillForAdjust() throws Exception {
        when(waybillService.findByWaybillNos((List)any())).thenReturn(Arrays.<Waybill>asList(new Waybill()));
        when(authCommonService.getOptionValue(anyString(), anyString(), anyString())).thenReturn("getOptionValueResponse");
        when(invoiceCommonService.validReceivableInvoiceAmount(anyString(), (BigDecimal)any())).thenReturn(true);
        when(waybillAmountService.findByFilter((WaybillAmountFilter)any(), (LoginUser)any())).thenReturn(Arrays.<WaybillAmount>asList(new WaybillAmount()));
        when(adjustForMasterAddService.findByFilter((AdjustForMasterFilter)any(), (LoginEmployee)any())).thenReturn(Arrays.<AdjustForMaster>asList(new AdjustForMaster()));

        AdjustItemValidHolder result = adjustForWaybillServiceImpl.validWaybillForAdjust(new AdjustAttachVO(), Arrays.<AdjustForWaybillTemp>asList(new AdjustForWaybillTemp()), new AdjustItemValidHolder(), null);
        Assert.assertEquals(new AdjustItemValidHolder(), result);
    }

}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme