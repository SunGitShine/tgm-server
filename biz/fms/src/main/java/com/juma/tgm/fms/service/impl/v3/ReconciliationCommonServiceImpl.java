package com.juma.tgm.fms.service.impl.v3;

import com.alibaba.fastjson.JSONObject;
import com.giants.common.exception.BusinessException;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.juma.auth.conf.domain.BusinessArea;
import com.juma.auth.employee.domain.Department;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.fms.v2.common.*;
import com.juma.fms.v2.core.payment.payable.domain.PayableMessage;
import com.juma.fms.v2.core.receivables.receivable.domain.ReceivableMessage;
import com.juma.fms.v2.mq.dto.FmsMQMessageDTO;
import com.juma.tgm.common.BaseUtil;
import com.juma.tgm.common.DateUtils;
import com.juma.tgm.crm.service.CustomerInfoService;
import com.juma.tgm.fms.dao.v3.ReconcilicationForCompanyMapper;
import com.juma.tgm.fms.domain.v3.*;
import com.juma.tgm.fms.service.v3.ReconciliationCommonService;
import com.juma.tgm.fms.service.v3.ReconcilicationForReceivableService;
import com.juma.tgm.mq.domain.CustomerInvoiceEvent;
import com.juma.tgm.mq.domain.CustomerInvoiceWaybill;
import com.juma.tgm.mq.service.MqService;
import com.juma.tgm.project.domain.v2.Project;
import com.juma.tgm.project.service.ProjectService;
import com.juma.tgm.tools.service.AuthCommonService;
import com.juma.tgm.tools.service.CrmCommonService;
import com.juma.tgm.waybill.domain.TruckRequire;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillAmount;
import com.juma.tgm.waybill.domain.vo.TruckRequireFilter;
import com.juma.tgm.waybill.domain.vo.WaybillAmountFilter;
import com.juma.tgm.waybill.service.TruckRequireService;
import com.juma.tgm.waybill.service.WaybillAmountService;
import com.juma.tgm.waybill.service.WaybillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 对账单-公共逻辑
 * 功能 :
 * 1.关联单逻辑
 * @author : Bruce(刘正航) 15:40 2019-05-22
 */
@Slf4j
@Service
public class ReconciliationCommonServiceImpl implements ReconciliationCommonService {

    @Resource
    private MqService mqService;

    @Resource
    private ProjectService projectService;

    @Resource
    private WaybillService waybillService;

    @Resource
    private CrmCommonService crmCommonService;

    @Resource
    private TruckRequireService truckRequireService;

    @Resource
    private CustomerInfoService customerInfoService;

    @Resource
    private WaybillAmountService waybillAmountService;

    @Resource
    private AuthCommonService authCommonService;

    @Resource
    private SendCompanyService sendCompanyService;

    @Autowired
    private ReconcilicationForCompanyMapper reconcilicationForCompanyMapper;

    @Autowired
    private ReconcilicationForReceivableService reconcilicationForReceivableService;

    @Override
    public void validAndCreateReconciliationLink(String reconciliationNo) throws BusinessException {
        this.validAndCreateReconciliationLink(reconcilicationForReceivableService.findReconciliationByReconciliationNo(reconciliationNo));
    }

    @Override
    public void validAndCreateReconciliationLink(ReconcilicationForReceivable receivable) throws BusinessException {

        ReconcilicationForCompany company = validIfNeedCreateRelationRecord(receivable);
        if (company == null) { return; }

        a2b(company, receivable);
        b2a(company, receivable);
    }

    @Override
    public void validAndCreateReconciliationAdjustLink(ReconcilicationForReceivable receivable, AdjustForMaster master) throws BusinessException {
        ReconcilicationForCompany company = validIfNeedCreateRelationRecord(receivable);
        if (company == null) { return; }

        a2bForAdjust(company, receivable, master);
        b2aForAdjust(company, receivable, master);
    }

    @Override
    public boolean validAndCreateReconciliationForInvoice(ReconcilicationForReceivable receivable, LoginEmployee loginEmployee) throws BusinessException {
        ReconcilicationForCompany company = validIfNeedCreateRelationRecord(receivable);
        if (company == null) { return false; }
        b2aForInvoice(company, receivable, loginEmployee);
        return true;
    }

    /**校验是否需要生成关联单**/
    private ReconcilicationForCompany validIfNeedCreateRelationRecord(ReconcilicationForReceivable receivable) {
        ReconcilicationForCompanyExample example = new ReconcilicationForCompanyExample().createCriteria()
                .andReconcilicationReceivableIdEqualTo(receivable.getReconcilicationId())
                .example();

        List<ReconcilicationForCompany> rows = reconcilicationForCompanyMapper.selectByExample(example);

        if (CollectionUtils.isEmpty(rows)) { return null; }

        ReconcilicationForCompany company = rows.get(0);
        if(null == company){ return null; }
        if(null == company.getContractToCompany()){return null;}
        if(null == company.getPayToCompany()){return null;}
        // 2.检查签约主体、运营主体是否一致
        if (company.getContractToCompany().equals(company.getPayToCompany())) { return null; }

        return company;
    }

    // 应付 a-->b
    private void a2b(ReconcilicationForCompany company, ReconcilicationForReceivable receivable) {

        long amount = BaseUtil.transferToCent(company.getFreightWithTax()).longValue();

        FmsMQMessageDTO<PayableMessage> a2b = new FmsMQMessageDTO<PayableMessage>();
        a2b.setBusinessSystem(BusinessSystemEnum.TMS.getCode());

        PayableMessage data = new PayableMessage();
        data.setTenantId(company.getTenantId());

        LoginUser loginUser = new LoginUser();
        loginUser.setTenantId(company.getTenantId());
        BusinessArea businessArea = authCommonService.loadBusinessArea(company.getAreaCode(), loginUser);
        if (businessArea != null) {
            data.setAreaName(businessArea.getAreaName());
        }
        data.setAreaCode(company.getAreaCode());
        data.setNoteType("relation");

        data.setAssociationNoteNo(receivable.getReconcilicationNo());
        data.setAssociationNoteType("shipper-account-bill"); // PayableNoteSourceNoteTypeEnum.LOGISTICS_CARRIER_ADJUSTMENT_BILL

        data.setTax(0L);
        data.setTaxRate(BigDecimal.ZERO);

        data.setAmountWithoutTax(amount);// 所有运单不含税金额求和
        data.setAmountDue(amount);// 所有运单含税金额求和
        data.setPayableProject("业务转包");

        data.setUniformSocialCreditCode(company.getContractToCompanyCreditCode());
        Department a = authCommonService.loadDepartment(company.getContractToCompany());
        if (a == null) { return; }
        data.setSubCompanyId(a.getDepartmentId());
        data.setSubCompanyName(a.getBusinessLicenceName());
        data.setSubCompanyCode(a.getDepartmentCode());
        Department b = authCommonService.loadDepartment(company.getPayToCompany());
        if (b == null) { return; }
        data.setTransactionalAccountNo(company.getPayToCompanyCreditCode());
        data.setTransactionalAccountName(b.getBusinessLicenceName());
        data.setTransactionalAccountType(TransactionalAccountTypeEnum.INNER_GROUP.getCode());
        data.setRemark("应付A:" + a.getDepartmentId() + "-->B:" + b.getDepartmentId());

        a2b.setData(data);

        List<PayableMessage.DetailListBean> detailList = new ArrayList<PayableMessage.DetailListBean>();

        PayableMessage.DetailListBean detail = new PayableMessage.DetailListBean();
        detail.setAmountWithoutTax(amount);// 运单不含税金额
        detail.setPayableAmount(amount);// 运单含税金额
        detail.setTax(0L);
        detail.setTaxRate(BigDecimal.ZERO);
        detail.setPayableProject("业务转包");
        detail.setSourceBizType("运单");
        detail.setSourceBizNo(receivable.getReconcilicationNo());


        detailList.add(detail);
        data.setDetailList(detailList);

        log.info("Send To FMS A->B: {}.", JSONObject.toJSONString(a2b));
        sendCompanyService.sendPayable(a2b);
    }

    // 应付 a-->b
    private void a2bForAdjust(ReconcilicationForCompany company, ReconcilicationForReceivable receivable, AdjustForMaster master) {

        long amount = BaseUtil.transferToCent(master.getAdjustAmount()).longValue();

        FmsMQMessageDTO<PayableMessage> a2b = new FmsMQMessageDTO<PayableMessage>();
        a2b.setBusinessSystem(BusinessSystemEnum.TMS.getCode());

        PayableMessage data = new PayableMessage();
        data.setTenantId(company.getTenantId());

        LoginUser loginUser = new LoginUser();
        loginUser.setTenantId(company.getTenantId());
        BusinessArea businessArea = authCommonService.loadBusinessArea(company.getAreaCode(), loginUser);
        if (businessArea != null) {
            data.setAreaName(businessArea.getAreaName());
        }
        data.setAreaCode(company.getAreaCode());
        data.setNoteType("relation");

        data.setAssociationNoteNo(receivable.getReconcilicationNo());
        data.setAssociationNoteType(PayableNoteSourceNoteTypeEnum.LOGISTICS_CARRIER_ADJUSTMENT_BILL.getCode());

        data.setTax(0L);
        data.setTaxRate(BigDecimal.ZERO);

        data.setAmountWithoutTax(amount);// 所有运单不含税金额求和
        data.setAmountDue(amount);// 所有运单含税金额求和
        data.setPayableProject("业务转包");

        data.setUniformSocialCreditCode(company.getContractToCompanyCreditCode());
        Department a = authCommonService.loadDepartment(company.getContractToCompany());
        if (a == null) { return; }
        data.setSubCompanyId(a.getDepartmentId());
        data.setSubCompanyName(a.getBusinessLicenceName());
        data.setSubCompanyCode(a.getDepartmentCode());
        Department b = authCommonService.loadDepartment(company.getPayToCompany());
        if (b == null) { return; }
        data.setTransactionalAccountNo(company.getPayToCompanyCreditCode());
        data.setTransactionalAccountName(b.getBusinessLicenceName());
        data.setTransactionalAccountType(TransactionalAccountTypeEnum.INNER_GROUP.getCode());
        data.setRemark("应付A:" + a.getDepartmentId() + "-->B:" + b.getDepartmentId());

        a2b.setData(data);

        List<PayableMessage.DetailListBean> detailList = new ArrayList<PayableMessage.DetailListBean>();

        PayableMessage.DetailListBean detail = new PayableMessage.DetailListBean();
        detail.setAmountWithoutTax(amount);// 运单不含税金额
        detail.setPayableAmount(amount);// 运单含税金额
        detail.setTax(0L);
        detail.setTaxRate(BigDecimal.ZERO);
        detail.setPayableProject("业务转包");
        detail.setSourceBizType("运单");
        detail.setSourceBizNo(receivable.getReconcilicationNo());


        detailList.add(detail);
        data.setDetailList(detailList);

        log.info("Send Adjust Payable To FMS A->B: {}.", JSONObject.toJSONString(a2b));
        sendCompanyService.sendPayable(a2b);
    }

    // 应收 b-->a
    private void b2a(ReconcilicationForCompany company, ReconcilicationForReceivable receivable) {

        long amount = BaseUtil.transferToCent(company.getFreightWithTax()).longValue();

        FmsMQMessageDTO<ReceivableMessage> b2a = new FmsMQMessageDTO<ReceivableMessage>();
        b2a.setBusinessSystem(BusinessSystemEnum.TMS.getCode());

        ReceivableMessage data = new ReceivableMessage();
        data.setTenantId(company.getTenantId());
        data.setAreaCode(company.getAreaCode());
        data.setNoteType("associated");
        data.setAssociationNoteType("SHIPPER_STATEMENT_BILL");

        LoginUser loginUser = new LoginUser();
        loginUser.setTenantId(company.getTenantId());
        BusinessArea businessArea = authCommonService.loadBusinessArea(company.getAreaCode(), loginUser);
        if (businessArea != null) {
            data.setAreaName(businessArea.getAreaName());
        }
        data.setSourceNoteNo(receivable.getReconcilicationNo());
        data.setSourceType("logistics-shipper-statement-bill");
        data.setTax(0L);
        data.setTaxRate(BigDecimal.ZERO);
        data.setReceivableAmount(amount);// 所有运单的含税金额求和
        data.setAmountWithoutTax(amount);// 所有运单不含税金额求和
        data.setReceivableProject("业务转包");

        data.setUniformSocialCreditCode(company.getPayToCompanyCreditCode());
        Department b = authCommonService.loadDepartment(company.getPayToCompany());
        if (b == null) { return; }
        data.setSubCompanyId(b.getDepartmentId());
        data.setSubCompanyName(b.getBusinessLicenceName());
        data.setSubCompanyCode(b.getDepartmentCode());

        Department a = authCommonService.loadDepartment(company.getContractToCompany());
        if (a == null) { return; }
        data.setTransactionalAccountNo(company.getContractToCompanyCreditCode());
        data.setTransactionalAccountName(a.getBusinessLicenceName());
        data.setTransactionalAccountType(TransactionalAccountTypeEnum.INNER_GROUP.getCode());
        data.setRemark("应收B:" + b.getDepartmentId() + "-->A:" + a.getDepartmentId());

        List<ReceivableMessage.DetailListBean> detailList = new ArrayList<ReceivableMessage.DetailListBean>();

        ReceivableMessage.DetailListBean detail = new ReceivableMessage.DetailListBean();
        detail.setAmountWithoutTax(amount);
        detail.setReceivableAmount(amount);
        detail.setTax(0L);
        detail.setTaxRate(0d);
        detail.setFreeType("N");
        detail.setReceivableProject("业务转包");
        detail.setBizDetailNo(receivable.getReconcilicationNo());
        detail.setBizDetailType("SHIPPER_STATEMENT_BILL");

        b2a.setData(data);
        detailList.add(detail);
        data.setDetailList(detailList);

        log.info("Send To FMS B->A: {}.", JSONObject.toJSONString(b2a));
        sendCompanyService.sendReceivable(b2a);
    }

    // 应收 b-->a
    private void b2aForAdjust(ReconcilicationForCompany company, ReconcilicationForReceivable receivable, AdjustForMaster master) {

        long amount = BaseUtil.transferToCent(master.getAdjustAmount()).longValue();

        FmsMQMessageDTO<ReceivableMessage> b2a = new FmsMQMessageDTO<ReceivableMessage>();
        b2a.setBusinessSystem(BusinessSystemEnum.TMS.getCode());

        ReceivableMessage data = new ReceivableMessage();
        data.setTenantId(company.getTenantId());
        data.setAreaCode(company.getAreaCode());
        data.setNoteType("associated");
        data.setAssociationNoteType(BillTypeEnum.STATEMENT_ADJUSTMENT_BILL.getCode());// 和应收调整单的billType一致

        LoginUser loginUser = new LoginUser();
        loginUser.setTenantId(company.getTenantId());
        BusinessArea businessArea = authCommonService.loadBusinessArea(company.getAreaCode(), loginUser);
        if (businessArea != null) {
            data.setAreaName(businessArea.getAreaName());
        }
        data.setSourceNoteNo(receivable.getReconcilicationNo());
        data.setSourceType(SourceTypeEnum.LOGISTICS_SHIPPER_ADJUSTMENT_BILL.getCode()); // 和应收调整单的SourceType一致
        data.setTax(0L);
        data.setTaxRate(BigDecimal.ZERO);
        data.setReceivableAmount(amount);// 所有运单的含税金额求和
        data.setAmountWithoutTax(amount);// 所有运单不含税金额求和
        data.setReceivableProject("业务转包");

        data.setUniformSocialCreditCode(company.getPayToCompanyCreditCode());
        Department b = authCommonService.loadDepartment(company.getPayToCompany());
        if (b == null) { return; }
        data.setSubCompanyId(b.getDepartmentId());
        data.setSubCompanyName(b.getBusinessLicenceName());
        data.setSubCompanyCode(b.getDepartmentCode());

        Department a = authCommonService.loadDepartment(company.getContractToCompany());
        if (a == null) { return; }
        data.setTransactionalAccountNo(company.getContractToCompanyCreditCode());
        data.setTransactionalAccountName(a.getBusinessLicenceName());
        data.setTransactionalAccountType(TransactionalAccountTypeEnum.INNER_GROUP.getCode());
        data.setRemark("应收B:" + b.getDepartmentId() + "-->A:" + a.getDepartmentId());

        List<ReceivableMessage.DetailListBean> detailList = new ArrayList<ReceivableMessage.DetailListBean>();

        ReceivableMessage.DetailListBean detail = new ReceivableMessage.DetailListBean();
        detail.setAmountWithoutTax(amount);
        detail.setReceivableAmount(amount);
        detail.setTax(0L);
        detail.setTaxRate(0d);
        detail.setFreeType("N");
        detail.setReceivableProject("业务转包");
        detail.setBizDetailNo(receivable.getReconcilicationNo());
        detail.setBizDetailType("SHIPPER_ADJUSTMENT_BILL");// SHIPPER_ADJUSTMENT_BILL

        b2a.setData(data);
        detailList.add(detail);
        data.setDetailList(detailList);

        log.info("Send Adjust Receivable To FMS B->A: {}.", JSONObject.toJSONString(b2a));
        sendCompanyService.sendReceivable(b2a);
    }

    // 应收 b-->a
    private void b2aForInvoice(ReconcilicationForCompany company, ReconcilicationForReceivable master, LoginEmployee loginEmployee) {
        CustomerInvoiceEvent event = new CustomerInvoiceEvent();
        event.setIsMain(false);
        event.setIsRelation(true);
        event.setSourceDocumentNo(master.getReconcilicationNo());
        event.setBusinessType("LOGISTICS");
        event.setSourceDocumentType("RECONCILIATION");
        BusinessArea businessArea = authCommonService.loadBusinessArea(master.getAreaCode(),loginEmployee);
        if( null != businessArea ){
            event.setBusinessAreaId(businessArea.getBusinessAreaId());
            event.setBusinessAreaCode(businessArea.getAreaCode());
            event.setBusinessAreaName(businessArea.getAreaName());
        }

        event.setCompanyTaxNumber(company.getPayToCompanyCreditCode());
        CustomerInvoiceWaybill invoiceWaybill = new CustomerInvoiceWaybill();
        event.setTenantId(master.getTenantId());
        event.setSourceDocumentCreateDate(DateUtils.format(master.getCreateTime(), DateUtils.Parttern.FORMAT_YYMMDD_MID));
        invoiceWaybill.setReconciliationDate(DateUtils.format(master.getCreateTime(), DateUtils.Parttern.FORMAT_YYMMDD_MID));
        // 获取运单完成时间 以及运单用车时间的最大最小值
        event.setPayload(invoiceWaybill);
        Project project = projectService.getProjectV2(master.getProjectId());
        if( null != project ) {
            // 关联单中, 客户为:签约主体
            if( null != company.getContractToCompany() ){
                event.setCustomerId(company.getContractToCompany());
                Department department = authCommonService.loadDepartment(company.getContractToCompany());
                if( null !=department ){
                    event.setCustomerType("ENTERPRISE");
                    event.setCustomerName(department.getBusinessLicenceName());
                }
            }
            // 关联单中, 公司为:运营主体
            event.setCompanyId(company.getPayToCompany());
            if( null != company.getPayToCompany() ){
                Department department = authCommonService.loadDepartment(company.getPayToCompany());
                if( null !=department ){
                    event.setCompanyCode(department.getDepartmentCode());
                    event.setCompanyName(department.getBusinessLicenceName());
                }
            }
            event.setContractCode(project.getContractNo());
            event.getPayload().setProjectId(project.getProjectId());
            event.getPayload().setProjectName(project.getName());
        }
        validAndSetWaybillTimeRange(company, master, project, event);
        mqService.sendReceiveToInvoice(event);
    }


    /**获取运单时间范围**/
    private void validAndSetWaybillTimeRange(ReconcilicationForCompany company, ReconcilicationForReceivable master, Project project, CustomerInvoiceEvent event) {
        List<ReconcilicationForReceivableItem> items = reconcilicationForReceivableService.findReceivableItemsByReconciliationId(master.getReconcilicationId());
        if( org.apache.commons.collections.CollectionUtils.isEmpty(items) ){
            return;
        }
        List<Integer> waybillIds = Lists.newArrayList();
        for (ReconcilicationForReceivableItem item : items){
            waybillIds.add(item.getWaybillId());
        }

        List<Waybill> waybills = waybillService.findByWaybillIds(waybillIds);
        if( org.apache.commons.collections.CollectionUtils.isEmpty(waybillIds) ){
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
        }

        if( null != project ){
            event.setOtherInfo(project.getName()+"/"+DateUtils.format(minFinishTime)+"/"+DateUtils.format(maxFinishTime));
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
            amountWithTax = amountWithTax.add(waybillAmount.getLastCustomerFreightWithTax());
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
