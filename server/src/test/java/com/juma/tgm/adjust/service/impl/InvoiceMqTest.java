package com.juma.tgm.adjust.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.juma.auth.conf.domain.BusinessArea;
import com.juma.auth.employee.domain.Department;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.crm.support.domain.CustomerInfoBo;
import com.juma.tgm.common.DateUtils;
import com.juma.tgm.crm.domain.CustomerInfo;
import com.juma.tgm.crm.service.CustomerInfoService;
import com.juma.tgm.fms.domain.v3.ReconcilicationForReceivable;
import com.juma.tgm.fms.domain.v3.ReconcilicationForReceivableItem;
import com.juma.tgm.fms.service.v3.ReconciliationCommonService;
import com.juma.tgm.fms.service.v3.ReconcilicationForReceivableService;
import com.juma.tgm.mq.domain.CustomerInvoiceEvent;
import com.juma.tgm.mq.domain.CustomerInvoiceWaybill;
import com.juma.tgm.mq.service.MqService;
import com.juma.tgm.project.domain.v2.Project;
import com.juma.tgm.project.service.ProjectService;
import com.juma.tgm.tools.service.AuthCommonService;
import com.juma.tgm.tools.service.CrmCommonService;
import com.juma.tgm.tools.service.InvoiceCommonService;
import com.juma.tgm.waybill.domain.TruckRequire;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillAmount;
import com.juma.tgm.waybill.domain.vo.TruckRequireFilter;
import com.juma.tgm.waybill.domain.vo.WaybillAmountFilter;
import com.juma.tgm.waybill.service.TruckRequireService;
import com.juma.tgm.waybill.service.WaybillAmountService;
import com.juma.tgm.waybill.service.WaybillService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 功能 :
 * @author : Bruce(刘正航) 12:47 2019-07-23
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:/META-INF/spring-standalone-beans.xml"
})
public class InvoiceMqTest {

    /**这里需要启动manage,通过断点方式获取json字符串,在这里使用**/
    private static final LoginEmployee loginEmployee = new Gson().fromJson(
            "{\"employeeId\":2904,\"maxInactiveInterval\":3600,\"loginDepartment\":{\"departmentId\":1479,\"departmentCode\":\"00\",\"departmentName\":\"驹马配送\",\"businessAreas\":[{\"areaCode\":\"00\",\"areaName\":\"全国\"}]},\"authDepartments\":[{\"departmentId\":1479,\"departmentName\":\"驹马配送\"}],\"sessionId\":\"757EB2EB0BCF4DF09BB0BFC4F4C5BD21\",\"tenantId\":19,\"tenantCode\":\"000000004\",\"userId\":1,\"loginName\":\"admin\",\"userName\":\"超级管理员\",\"mobileNumber\":\"18030574832\",\"isTest\":true,\"isSysUser\":true}",
            LoginEmployee.class);

    @Autowired
    private MqService mqService;

    @Autowired
    private WaybillService waybillService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private CrmCommonService crmCommonService;

    @Autowired
    private AuthCommonService authCommonService;

    @Autowired
    private TruckRequireService truckRequireService;

    @Autowired
    private CustomerInfoService customerInfoService;

    @Autowired
    private WaybillAmountService waybillAmountService;

    @Autowired
    private InvoiceCommonService invoiceCommonService;

    @Autowired
    private ReconciliationCommonService reconciliationCommonService;

    @Autowired
    private ReconcilicationForReceivableService reconcilicationForReceivableService;

    @Test
    public void testSendInvoice(){
        ReconcilicationForReceivable master = reconcilicationForReceivableService.findReceivableById(1783);
        sendMessageToInvoice(master,loginEmployee);
    }

    @Test
    public void fetchAmount(){
        invoiceCommonService.validReceivableInvoiceAmount("AR20190212160050572001320",BigDecimal.valueOf(500));
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
        List<ReconcilicationForReceivableItem> items = reconcilicationForReceivableService.findReceivableItemsByReconciliationId(master.getReconcilicationId());
        if( CollectionUtils.isEmpty(items) ){
            return;
        }
        List<Integer> waybillIds = Lists.newArrayList();
        for (ReconcilicationForReceivableItem item : items){
            waybillIds.add(item.getWaybillId());
        }

        List<Waybill> waybills = waybillService.findByWaybillIds(waybillIds);
        if( CollectionUtils.isEmpty(waybillIds) ){
            return;
        }

        // 设置运单税率
        fetchAndSetTaxRate(event,waybillIds);
        // 设置运单金额求和
        fetchAndSetAmountWithTax(event,waybillIds);

        //完成时间
        Date minFinishTime = null;
        Date maxFinishTime = null;
        //用车时间
        Date minDeployTime = null;
        Date maxDeployTime = null;
        BigDecimal taxRate = BigDecimal.ZERO;
        String companyTaxNumber = null;
        for (Waybill waybill : waybills){
            if( null == waybill.getFinishTime() ){ continue; }
            if( null == minFinishTime ){
                minFinishTime = waybill.getFinishTime();
            }else if( waybill.getFinishTime().before(minFinishTime) ){
                minFinishTime = waybill.getFinishTime();
            }
            if( null == maxFinishTime ){
                maxFinishTime = waybill.getFinishTime();
            }else if( waybill.getFinishTime().after(minFinishTime) ){
                maxFinishTime = waybill.getFinishTime();
            }

            if( null == minDeployTime ){
                minDeployTime = waybill.getPlanDeliveryTime();
            }else if( waybill.getPlanDeliveryTime().before(minDeployTime) ){
                minDeployTime = waybill.getPlanDeliveryTime();
            }
            if( null == maxDeployTime ){
                maxDeployTime = waybill.getPlanDeliveryTime();
            }else if( waybill.getPlanDeliveryTime().after(maxDeployTime) ){
                maxDeployTime = waybill.getPlanDeliveryTime();
            }
            companyTaxNumber = waybill.getContractToCompanyCreditCode();
        }
        if( StringUtils.isNotBlank(companyTaxNumber) ){
            event.setCompanyTaxNumber(companyTaxNumber);
        }
        Project project = projectService.getProjectV2(master.getProjectId());
        if( null != project ) {
            event.setCompanyTaxNumber(project.getContractToCompanyCreditCode());
            event.setCompanyId(project.getContractToCompany());
            if( null != project.getContractToCompany() ){
                Department department = authCommonService.loadDepartment(project.getContractToCompany());
                if( null !=department ){
                    event.setCompanyCode(department.getDepartmentCode());
                    event.setCompanyName(department.getBusinessLicenceName());
                }
            }
            event.setContractCode(project.getContractNo());
            event.setOtherInfo(project.getName()+"/"+DateUtils.format(minFinishTime)+"/"+DateUtils.format(maxFinishTime));
            event.getPayload().setProjectId(project.getProjectId());
            event.getPayload().setProjectName(project.getName());
        }
        event.getPayload().setWaybillFinishTime(DateUtils.format(minFinishTime)+"/"+DateUtils.format(maxFinishTime));
        event.getPayload().setPlanDeliveryTime(DateUtils.format(minDeployTime)+"/"+DateUtils.format(maxDeployTime));
    }

    private void fetchAndSetAmountWithTax(CustomerInvoiceEvent event, List<Integer> waybillIds) {
        WaybillAmountFilter amountFilter = new WaybillAmountFilter();
        amountFilter.setWaybillIds(waybillIds);
        List<WaybillAmount> amounts = waybillAmountService.findByFilter(amountFilter,new LoginUser(19,1));
        BigDecimal amountWithTax = BigDecimal.ZERO;
        for (WaybillAmount waybillAmount : amounts){
            amountWithTax = amountWithTax.add(waybillAmount.getCustomerFreightWithTax());
        }
        event.setAmountIncludeTax(amountWithTax);
    }

    private void fetchAndSetTaxRate(CustomerInvoiceEvent event, List<Integer> waybillIds) {
        TruckRequireFilter truckRequireFilter = new TruckRequireFilter();
        truckRequireFilter.setWaybillIds(waybillIds);
        List<TruckRequire> requires = truckRequireService.findByFilter(truckRequireFilter,new LoginUser(19,1));
        Map<Integer,TruckRequire> requireMap = Maps.newConcurrentMap();
        BigDecimal rate = BigDecimal.ZERO;
        for (TruckRequire require : requires){
            rate = require.getTaxRateValue();
        }
        event.setTaxRate(rate);
    }
}
