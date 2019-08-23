package com.juma.tgm.fms.service.impl.v3;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.juma.auth.conf.domain.BusinessArea;
import com.juma.auth.conf.service.BusinessAreaService;
import com.juma.auth.employee.domain.Department;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.employee.service.DepartmentService;
import com.juma.auth.user.domain.LoginUser;
import com.juma.crm.support.service.CrmService;
import com.juma.fms.v2.common.BillTypeEnum;
import com.juma.fms.v2.common.BusinessDetailTypeEnum;
import com.juma.fms.v2.common.BusinessSystemEnum;
import com.juma.fms.v2.common.SourceTypeEnum;
import com.juma.fms.v2.common.TransactionalAccountTypeEnum;
import com.juma.fms.v2.mq.dto.FmsMQMessageDTO;
import com.juma.fms.v2.source.logistics.dto.SourceLogisticsDTO;
import com.juma.fms.v2.source.logistics.dto.SourceLogisticsDetailDTO;
import com.juma.tgm.base.domain.Paging;
import com.juma.tgm.common.BaseUtil;
import com.juma.tgm.common.DateUtil;
import com.juma.tgm.common.id.IdGeneratorTable;
import com.juma.tgm.common.workflow.ApprovalStatus;
import com.juma.tgm.common.workflow.WorkflowConstants;
import com.juma.tgm.crm.domain.CustomerInfo;
import com.juma.tgm.crm.service.CustomerInfoService;
import com.juma.tgm.fms.dao.v3.ReconciliationExtraForPayableMapper;
import com.juma.tgm.fms.dao.v3.ReconciliationMonthlyBillMapper;
import com.juma.tgm.fms.dao.v3.ReconcilicationForPayableItemMapper;
import com.juma.tgm.fms.dao.v3.ReconcilicationForPayableLogMapper;
import com.juma.tgm.fms.dao.v3.ReconcilicationForPayableMapper;
import com.juma.tgm.fms.dao.v3.ext.ReconcilicationForPayableExtMapper;
import com.juma.tgm.fms.dao.v3.ext.ReconcilicationForPayableItemExtraMapper;
import com.juma.tgm.fms.domain.bo.ReconciliationLog;
import com.juma.tgm.fms.domain.v3.AdjustForItem;
import com.juma.tgm.fms.domain.v3.AdjustForMaster;
import com.juma.tgm.fms.domain.v3.AdjustForPayable;
import com.juma.tgm.fms.domain.v3.ReconciliationExtraForPayable;
import com.juma.tgm.fms.domain.v3.ReconciliationExtraForPayableExample;
import com.juma.tgm.fms.domain.v3.ReconcilicationForPayable;
import com.juma.tgm.fms.domain.v3.ReconcilicationForPayableExample;
import com.juma.tgm.fms.domain.v3.ReconcilicationForPayableItem;
import com.juma.tgm.fms.domain.v3.ReconcilicationForPayableItemExample;
import com.juma.tgm.fms.domain.v3.ReconcilicationForPayableItemExample.Criteria;
import com.juma.tgm.fms.domain.v3.ReconcilicationForPayableLog;
import com.juma.tgm.fms.domain.v3.ReconcilicationForPayableLogExample;
import com.juma.tgm.fms.domain.v3.bo.MonthlyBillBo;
import com.juma.tgm.fms.domain.v3.bo.ReconciliationExtraForPayableBo;
import com.juma.tgm.fms.domain.v3.bo.ReconcilicationForPayableBo;
import com.juma.tgm.fms.domain.v3.bo.ReconcilicationForPayableItemBo;
import com.juma.tgm.fms.domain.v3.enums.AdjustMasterType;
import com.juma.tgm.fms.domain.v3.enums.AdjustType;
import com.juma.tgm.fms.domain.v3.enums.PayableSettleAccountTypeEnum;
import com.juma.tgm.fms.domain.v3.vo.AdjustForMasterFilter;
import com.juma.tgm.fms.domain.v3.vo.AdjustFreightVo;
import com.juma.tgm.fms.domain.v3.vo.WaybillStatisticsAmountVO;
import com.juma.tgm.fms.service.v3.AdjustForItemService;
import com.juma.tgm.fms.service.v3.AdjustForMasterAddService;
import com.juma.tgm.fms.service.v3.AdjustForMasterService;
import com.juma.tgm.fms.service.v3.AdjustForPayableService;
import com.juma.tgm.fms.service.v3.ReconcilicationForPayableService;
import com.juma.tgm.fms.service.v3.ReconcilicationForSubPayableService;
import com.juma.tgm.imageUploadManage.domain.ImageUploadManage;
import com.juma.tgm.imageUploadManage.service.ImageUploadManageService;
import com.juma.tgm.mq.domain.WorkFlowMessage;
import com.juma.tgm.project.domain.v2.Project;
import com.juma.tgm.project.service.ProjectService;
import com.juma.tgm.tools.service.BusinessAreaCommonService;
import com.juma.tgm.tools.service.VmsCommonService;
import com.juma.tgm.waybill.domain.TruckRequire;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.Waybill.ReceiveWay;
import com.juma.tgm.waybill.domain.WaybillAmount;
import com.juma.tgm.waybill.domain.vo.WaybillAmountFilter;
import com.juma.tgm.waybill.service.TruckRequireService;
import com.juma.tgm.waybill.service.WaybillAmountService;
import com.juma.tgm.waybill.service.WaybillCommonService;
import com.juma.tgm.waybill.service.WaybillService;
import com.juma.vms.driver.domain.Driver;
import com.juma.vms.driver.enumeration.DriverTypeEnum;
import com.juma.vms.transport.domain.CapacityPool;
import com.juma.vms.truck.domain.Truck;
import com.juma.vms.truck.enumeration.TruckRunTypeEnum;
import com.juma.vms.vendor.domain.Vendor;
import com.juma.vms.vendor.enumeration.VendorSourceEnum;
import com.juma.vms.vendor.enumeration.VendorTypeEnum;
import com.juma.workflow.core.domain.ProcessInstance;
import com.juma.workflow.core.domain.TaskDetail;
import com.juma.workflow.core.service.ProcessService;
import com.juma.workflow.core.service.TaskService;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import javax.annotation.Resource;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReconcilicationForPayableServiceImpl implements ReconcilicationForPayableService {

    private final static Logger log = LoggerFactory.getLogger("monthlyBillAppender");

    @Resource
    private ReconcilicationForPayableMapper reconcilicationForPayableMapper;
    @Resource
    private ReconcilicationForPayableItemMapper reconcilicationForPayableItemMapper;
    @Resource
    private ReconcilicationForPayableItemExtraMapper reconcilicationForPayableItemExtraMapper;
    @Resource
    private ReconciliationExtraForPayableMapper reconciliationExtraForPayableMapper;
    @Resource
    private ReconcilicationForPayableExtMapper reconcilicationForPayableExtMapper;
    @Resource
    private ReconciliationMonthlyBillMapper reconciliationMonthlyBillMapper;
    @Autowired
    private SendPayableServiceV3 sendPayableService;
    @Autowired
    private SendReceivableServiceV3 sendReceivableService;
    @Resource
    private VmsCommonService vmsCommonService;
    @Resource
    private TruckRequireService truckRequireService;
    @Resource
    private WaybillCommonService waybillCommonService;
    @Resource
    private WaybillService waybillService;
    @Resource
    private ProcessService processService;
    @Resource
    private TaskService taskService;
    @Resource
    private DepartmentService departmentService;
    @Resource
    private BusinessAreaService businessAreaService;
    @Resource
    private ReconcilicationForSubPayableService reconcilicationForSubPayableService;
    @Resource
    private CustomerInfoService customerInfoService;
    @Resource
    private ReconcilicationForPayableLogMapper reconcilicationForPayableLogMapper;
    @Resource
    private BusinessAreaCommonService businessAreaCommonService;
    @Resource
    private ImageUploadManageService imageUploadManageService;
    @Resource
    private CrmService crmService;
    @Autowired
    private AdjustForMasterService adjustForMasterService;
    @Autowired
    private AdjustForItemService adjustForItemService;
    @Autowired
    private WaybillAmountService waybillAmountService;
    @Autowired
    private AdjustForPayableService adjustForPayableService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private AdjustForMasterAddService adjustForMasterAddService;

    @Override
    public void monthlyBill(String firstDay, String lastDay){

        if (StringUtils.isBlank(firstDay) && StringUtils.isBlank(lastDay)) {
            // 获取上个月时间
            GetLastMonthTime getLastMonthTime = new GetLastMonthTime().invoke();
            firstDay = getLastMonthTime.getFirstDay();
            lastDay = getLastMonthTime.getLastDay();
        }
        log.info("月账单定时任务执行时间{}", DateUtil.format(new Date()));
        log.info("月账单定时任务时间取值范围:{} 到 {}", firstDay, lastDay);

        // 应付主体
        List<Waybill> payableMonthlyBillMain = reconciliationMonthlyBillMapper.findPayableMonthlyBillMain(firstDay,lastDay);
        // 应收主体
        List<Waybill> receivableMonthlyBillMain = reconciliationMonthlyBillMapper.findReceivableMonthlyBillMain(firstDay, lastDay);

        // 组装应付月账单并发送MQ
        buildPayable(payableMonthlyBillMain);
        // 组装应收月账单并发送MQ
        buildReceivable(receivableMonthlyBillMain);
    }

    @Override
    public ReconcilicationForPayable getReconciliationById(int reconciliationId) throws BusinessException {
        return reconcilicationForPayableMapper.selectByPrimaryKey(reconciliationId);
    }

    private void buildPayable(Collection<Waybill> payableMonthlyBillMain) {
        log.info("monthly payable log start");

        Map<String, List<Waybill>> groupMap = new HashMap<String, List<Waybill>>();
        FmsMQMessageDTO fmsMQMessageDTO = new FmsMQMessageDTO();
        fmsMQMessageDTO.setBusinessSystem(BusinessSystemEnum.TMS.getCode());
        if (CollectionUtils.isNotEmpty(payableMonthlyBillMain)) {
            for (Waybill waybill : payableMonthlyBillMain) {
                if (waybill.getReceiveWay() == null || (waybill.getVendorId() == null && waybill.getVehicleToVendor() == null)) {
                    continue;
                }

                // 判断非转运单的承运商类型，非转运单的自营承运商不发送mq
                if (!waybill.getReceiveWay().equals(ReceiveWay.TRANSFORM_BILL.getCode())) {
                    Vendor vendor = vmsCommonService.loadVendorByVendorId(waybill.getVehicleToVendor());
                    if (null == vendor || null == vendor.getVendorSource() || vendor.getVendorSource()
                        .equals(VendorSourceEnum.SELF_SUPPORT.getCode())) {
                        continue;
                    }
                }

                String key = waybill.getTenantId() + ""
                        + (Waybill.ReceiveWay.TRANSFORM_BILL.getCode() == waybill.getReceiveWay()
                                ? waybill.getVendorId() : waybill.getVehicleToVendor());
                if (groupMap.containsKey(key)) {
                    groupMap.get(key).add(waybill);
                } else {
                    List<Waybill> w = new ArrayList<Waybill>();
                    w.add(waybill);
                    groupMap.put(key, w);
                }
            }

            Iterator<Map.Entry<String, List<Waybill>>> it = groupMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, List<Waybill>> next = it.next();
                SourceLogisticsDTO dto = new SourceLogisticsDTO();
                Waybill waybill = next.getValue().get(0);
                //组装基础数据
                buildPayableBasic(waybill, dto);
                //组装明细
                for (Waybill w : next.getValue()) {
                    SourceLogisticsDetailDTO sourceDTO = buildPayableDetail(w);
                    dto.getDetailList().add(sourceDTO);
                }
                fmsMQMessageDTO.setData(dto);
                // MQ
                log.info("应付-月账单 To FMS : {}", JSONObject.toJSONString(fmsMQMessageDTO));
                sendPayableService.send(fmsMQMessageDTO);
            }
            log.info("应付-月账单条数: {}", groupMap.size());
            log.info("monthly payable log end");
        }
    }

    private void buildPayableBasic(Waybill waybill, SourceLogisticsDTO dto) {
        // 月账单号uuid
        dto.setSourceNoteNo(UUID.randomUUID().toString());
        // 往来户编号/往来户名称(承运商id/名称)
        if (Waybill.ReceiveWay.TRANSFORM_BILL.getCode() == waybill.getReceiveWay()) {
            if (null != waybill.getVendorId()) {
                dto.setTransactionalAccountNo(String.valueOf(waybill.getVendorId()));
                Vendor vendor = null;
                try {
                    vendor = vmsCommonService.loadVendorByVendorId(waybill.getVendorId());
                } catch (Exception e) {
                    log.warn(e.getMessage(),e);
                }
                if (null != vendor) {
                    dto.setTransactionalAccountName(vendor.getVendorName());
                    dto.setEntityId(vendor.getJumaPin());
                }
            }
        } else {
            if (null != waybill.getVehicleToVendor()) {
                dto.setTransactionalAccountNo(String.valueOf(waybill.getVehicleToVendor()));
                Vendor vendor = null;
                try {
                    vendor = vmsCommonService.loadVendorByVendorId(waybill.getVehicleToVendor());
                } catch (Exception e) {
                    log.warn(e.getMessage(),e);
                }
                if (null != vendor) {
                    dto.setTransactionalAccountName(vendor.getVendorName());
                    dto.setEntityId(vendor.getJumaPin());
                }
            }
        }
        // 往来户类型
        dto.setTransactionalAccountType(TransactionalAccountTypeEnum.CARRIER.getCode());
        dto.setBillType(BillTypeEnum.MONTHLY_BILL.getCode());
        dto.setSourceType(SourceTypeEnum.LOGISTICS_CARRIER_MONTHLY_BILL.getCode());
        // 统计类型
        dto.setStatisticsType(waybill.getProjectName());

        dto.setDetailList(new ArrayList<SourceLogisticsDetailDTO>());

        // 租户Id
        if (null != waybill.getTenantId()) {
            dto.setTenantId(waybill.getTenantId());
        }
        // 业务区域
        if (StringUtils.isNotBlank(waybill.getAreaCode())) {
            dto.setAreaCode(waybill.getAreaCode());
        }

        dto.setUniformSocialCreditCode(waybill.getPayToCompanyCreditCode());
        if (null != waybill.getPayToCompany()) {
            // 子公司编码（运营主体）
            Integer departmentId = waybill.getPayToCompany();
            dto.setSubCompanyId(departmentId);
            Department department = null;
            try {
                department = departmentService.loadDepartment(departmentId);
            } catch (Exception e) {
                log.warn(e.getMessage(),e);
            }
            dto.setSubCompanyCode(department == null ? "" : department.getDepartmentCode());
            dto.setSubCompanyName(department == null ? "" :department.getBusinessLicenceName());
        }
    }

    private String getCustomerJumaPin(Integer crmCustomerId) {
        if (crmCustomerId == null) {return null;}
        try {
            com.juma.crm.customer.domain.CustomerInfo crmCustomerInfo = crmService.find(crmCustomerId, null);
            return crmCustomerInfo == null ? null : crmCustomerInfo.getJumaPin();
        }catch (Exception e){
            log.warn("crm获取客户失败 id：{}",crmCustomerId);
        }
        return null;
    }

    private SourceLogisticsDetailDTO buildPayableDetail(Waybill w) {
        SourceLogisticsDetailDTO sourceDTO = new SourceLogisticsDetailDTO();

        // 车牌号
        sourceDTO.setPlateNumber(w.getPlateNumber());
        // 司机名称
        sourceDTO.setDriverName(w.getDriverName());
        // 司机类型
        sourceDTO.setDriverType(
                w.getDriverType() == null ? "" : DriverTypeEnum.getDescByCode(w.getDriverType()));
        // 客户运费
        sourceDTO.setCustomerFreight(w.getEstimateFreight() == null ? 0
                : w.getEstimateFreight().multiply(new BigDecimal(100 + "")).longValue());
        // 承运商运费
        sourceDTO.setVendorFreight(w.getShow4DriverFreight() == null ? 0
                : w.getShow4DriverFreight().multiply(new BigDecimal(100 + "")).longValue());

        // 业务类型
        sourceDTO.setSourceBizType(BusinessDetailTypeEnum.WAYBILL.getCode());
        // 运单号
        sourceDTO.setSourceBizNo(w.getWaybillNo());
        // 用车时间
        sourceDTO.setUseCarTime(w.getPlanDeliveryTime());
        // 运单完成时间
        sourceDTO.setWaybillCompleteTime(w.getFinishTime());
        // 物流项目
        sourceDTO.setLogisticsName(w.getProjectName());

        TruckRequire truckRequire = truckRequireService.findTruckRequireByWaybillId(w.getWaybillId(),null);
        BigDecimal taxRateValue = null;
        if(null != truckRequire){
            taxRateValue = truckRequire.getTaxRateValue();
        }
        //运单金额
        WaybillAmount waybillAmount = waybillAmountService.loadByWaybillId(w.getWaybillId());
        if(null != waybillAmount){
            // 含税金额,单位分
            sourceDTO.setPriceIncludingTax(waybillAmount.getLastVendorFreightWithTax().multiply(BigDecimal.valueOf(100)).longValue());
            // 不含税金额,单位分
            BigDecimal priceIncludingTax = BaseUtil.calFreightWithNotTax(waybillAmount.getLastVendorFreightWithTax(), taxRateValue);
            sourceDTO.setPriceExcludingTax(priceIncludingTax.multiply(BigDecimal.valueOf(100)).longValue());
        }else {
            sourceDTO.setPriceIncludingTax(w.getShow4DriverFreight() == null ? 0L
                    : w.getShow4DriverFreight().multiply(BigDecimal.valueOf(100)).longValue());
            sourceDTO.setPriceExcludingTax(sourceDTO.getPriceIncludingTax());
        }

        // 税,单位分
        sourceDTO.setTax(0L);
        // 税率,如0.1表示10%
        sourceDTO.setTaxRate(BigDecimal.ZERO);
        // 车架号
        Truck truck = null;
        try {
            truck = vmsCommonService.loadTruckByTruckId(w.getTruckId());
        } catch (Exception e) {
            log.warn(e.getMessage(),e);
        }
        sourceDTO.setVin(truck == null ? null : truck.getTruckIdentificationNo());
        if (null != truck) {
            sourceDTO.setTruckType(truck.getTruckRunType() == null ? "" : TruckRunTypeEnum.getDescByCode(truck.getTruckRunType()));
        }
        return sourceDTO;
    }

    private void buildReceivable(Collection<Waybill> receivableMonthlyBillMain) {
        log.info("monthly receivable log start");

        Map<String, List<Waybill>> groupMap = new HashMap<String, List<Waybill>>();
        FmsMQMessageDTO fmsMQMessageDTO = new FmsMQMessageDTO();
        fmsMQMessageDTO.setBusinessSystem(BusinessSystemEnum.TMS.getCode());

        if (CollectionUtils.isNotEmpty(receivableMonthlyBillMain)) {
            for (Waybill waybill : receivableMonthlyBillMain) {
                String key = waybill.getTenantId() + "" + waybill.getCustomerId() + "" + waybill.getProjectId();
                if (groupMap.containsKey(key)) {
                    groupMap.get(key).add(waybill);
                } else {
                    List<Waybill> w = new ArrayList<Waybill>();
                    w.add(waybill);
                    groupMap.put(key, w);
                }
            }
            Iterator<Map.Entry<String, List<Waybill>>> it = groupMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, List<Waybill>> next = it.next();
                SourceLogisticsDTO dto = new SourceLogisticsDTO();
                Waybill waybill = next.getValue().get(0);
                //组装基础数据
                buildReceivableBasic(waybill, dto);
                //组装明细
                for (Waybill w : next.getValue()) {
                    SourceLogisticsDetailDTO sourceDTO = buildReceivableDetail(w);
                    dto.getDetailList().add(sourceDTO);
                }
                fmsMQMessageDTO.setData(dto);
                // MQ
                log.info("应收-月账单 To FMS : {}", JSONObject.toJSONString(fmsMQMessageDTO));
                sendReceivableService.send(fmsMQMessageDTO);
            }
            log.info("应收-月账单条数: {}", groupMap.size());
            log.info("monthly receivable log end");
        }
    }

    private void buildReceivableBasic(Waybill waybill, SourceLogisticsDTO dto) {
        // 月账单号uuid
        dto.setSourceNoteNo(UUID.randomUUID().toString());
        // 往来户编号/往来户名称(承运商id/名称)
        CustomerInfo customerInfo = customerInfoService.findCusInfoById(waybill.getCustomerId());
        if (null != customerInfo) {
            dto.setTransactionalAccountNo(customerInfo.getCrmCustomerId().toString());
            // juma pin
            dto.setEntityId(getCustomerJumaPin(customerInfo.getCrmCustomerId()));
        }
        dto.setTransactionalAccountName(waybill.getCustomerName());
        // 往来户类型
        dto.setTransactionalAccountType(TransactionalAccountTypeEnum.OWNER.getCode());
        dto.setBillType(BillTypeEnum.MONTHLY_BILL.getCode());
        dto.setSourceType(SourceTypeEnum.LOGISTICS_SHIPPER_MONTHLY_BILL.getCode());
        // 统计类型
        dto.setStatisticsType(waybill.getProjectName());

        dto.setDetailList(new ArrayList<SourceLogisticsDetailDTO>());
        // 租户Id
        dto.setTenantId(waybill.getTenantId());
        // 业务区域
        dto.setAreaCode(waybill.getAreaCode());

        dto.setUniformSocialCreditCode(waybill.getContractToCompanyCreditCode());
        if (null != waybill.getDepartmentId()) {
            // 子公司编码（签约主体）
            Integer departmentId = waybill.getDepartmentId();
            dto.setSubCompanyId(departmentId);
            Department department = null;
            try {
                department = departmentService.loadDepartment(departmentId);
            } catch (Exception e) {
                log.warn(e.getMessage(),e);
            }
            dto.setSubCompanyCode(department == null ? "" : department.getDepartmentCode());
            dto.setSubCompanyName(department == null ? "" :department.getBusinessLicenceName());
        }
    }

    private SourceLogisticsDetailDTO buildReceivableDetail(Waybill w) {
        SourceLogisticsDetailDTO sourceDTO = new SourceLogisticsDetailDTO();

        // 车牌号
        sourceDTO.setPlateNumber(w.getPlateNumber());
        // 司机名称
        sourceDTO.setDriverName(w.getDriverName());
        // 司机类型
        sourceDTO.setDriverType(
            w.getDriverType() == null ? "" : DriverTypeEnum.getDescByCode(w.getDriverType()));
        // 客户运费
        sourceDTO.setCustomerFreight(w.getEstimateFreight() == null ? 0
            : w.getEstimateFreight().multiply(new BigDecimal(100 + "")).longValue());
        // 承运商运费
        sourceDTO.setVendorFreight(w.getShow4DriverFreight() == null ? 0
            : w.getShow4DriverFreight().multiply(new BigDecimal(100 + "")).longValue());

        // 业务类型
        sourceDTO.setSourceBizType(BusinessDetailTypeEnum.WAYBILL.getCode());
        // 运单号
        sourceDTO.setSourceBizNo(w.getWaybillNo());
        // 用车时间
        sourceDTO.setUseCarTime(w.getPlanDeliveryTime());
        // 运单完成时间
        sourceDTO.setWaybillCompleteTime(w.getFinishTime());
        // 物流项目
        sourceDTO.setLogisticsName(w.getProjectName());
        // 税率,如0.1表示10%
        BigDecimal taxRateValue = null;
        TruckRequire truckRequire = truckRequireService.findTruckRequireByWaybillId(w.getWaybillId(), null);
        if (null != truckRequire) {
            taxRateValue = truckRequire.getTaxRateValue();
            sourceDTO.setTaxRate(taxRateValue);
        } else {
            sourceDTO.setTaxRate(BigDecimal.ZERO);
        }

        //运单单金额
        WaybillAmount waybillAmount = waybillAmountService.loadByWaybillId(w.getWaybillId());
        if (null != waybillAmount) {
            // 含税金额,单位分
            sourceDTO.setPriceIncludingTax(
                    waybillAmount.getLastCustomerFreightWithTax().multiply(BigDecimal.valueOf(100)).longValue());
            // 不含税金额,单位分
            BigDecimal priceIncludingTax = BaseUtil
                    .calFreightWithNotTax(waybillAmount.getLastCustomerFreightWithTax(), taxRateValue);
            sourceDTO.setPriceExcludingTax(priceIncludingTax.multiply(BigDecimal.valueOf(100)).longValue());
        } else {
            sourceDTO.setPriceIncludingTax(w.getEstimateFreight() == null ? 0L
                    : w.getEstimateFreight().multiply(BigDecimal.valueOf(100)).longValue());
            sourceDTO.setPriceExcludingTax(w.getAfterTaxFreight() == null ? 0L
                    : w.getAfterTaxFreight().multiply(BigDecimal.valueOf(100)).longValue());
        }
        // 税,单位分
        sourceDTO.setTax(sourceDTO.getPriceIncludingTax() - sourceDTO.getPriceExcludingTax());
        // 车架号
        Truck truck = null;
        try {
            truck = vmsCommonService.loadTruckByTruckId(w.getTruckId());
        } catch (Exception e) {
            log.warn(e.getMessage(),e);
        }
        sourceDTO.setVin(truck == null ? null : truck.getTruckIdentificationNo());
        if(null != truck){
            sourceDTO.setTruckType(truck.getTruckRunType() == null ? "" : TruckRunTypeEnum.getDescByCode(truck.getTruckRunType()));
        }
        return sourceDTO;
    }

    private class GetLastMonthTime {
        private String firstDay;
        private String lastDay;

        public String getFirstDay() {
            return firstDay;
        }

        public String getLastDay() {
            return lastDay;
        }

        public GetLastMonthTime invoke() {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, -1);
            cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            firstDay = sdf.format(cal.getTime());

            Calendar ca = Calendar.getInstance();
            ca.add(Calendar.MONTH, -1);
            ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
            ca.set(Calendar.HOUR_OF_DAY, 23);
            ca.set(Calendar.MINUTE, 59);
            ca.set(Calendar.SECOND, 59);
            lastDay = sdf.format(ca.getTime());
            return this;
        }
    }

    @Override
    public Page<ReconcilicationForPayableBo> vendorSearch(PageCondition pageCondition, LoginUser loginUser)
            throws BusinessException {
        if (null == loginUser || null == loginUser.getTenantId()) {
            return new Page<ReconcilicationForPayableBo>(pageCondition.getPageNo(), pageCondition.getPageSize(), 0,
                    new ArrayList<ReconcilicationForPayableBo>());
        }
        pageCondition.getFilters().put("tenantId", loginUser.getTenantId());
        int total = reconcilicationForPayableExtMapper.vendorCount(pageCondition);
        List<ReconcilicationForPayable> list = reconcilicationForPayableExtMapper.vendorSearch(pageCondition);
        List<ReconcilicationForPayableBo> bos = new ArrayList<>();
        for (ReconcilicationForPayable payable : list) {
            ReconcilicationForPayableBo bo = new ReconcilicationForPayableBo();
            BeanUtils.copyProperties(payable, bo);
            // 子公司名称
            Department department = departmentService.loadDepartment(payable.getDepartmentId());
            bo.setDepartmentName(department == null ? "" : department.getBusinessLicenceName());

            // 签约子公司
            List<Waybill> waybills = waybillCommonService.findByReconciliationNo(payable.getReconcilicationNo());
            if (CollectionUtils.isNotEmpty(waybills)) {
                Integer departmentId = waybills.get(0).getPayToCompany();
                Department d = departmentService.loadDepartment(departmentId);
                bo.setSignedDepartmentName(d == null ? "" : d.getBusinessLicenceName());
            }

            // 是否上传了对账凭证
            bo.setHasEvidence(
                    !(imageUploadManageService
                            .listByRelationIdAndSign(payable.getReconcilicationId(),
                                    ImageUploadManage.ImageUploadManageSign.RECONCILIATION_FOR_PAYABLE.getCode())
                            .isEmpty()));

            //调整金额
            List<AdjustForMaster> adjustForMasters = adjustForMasterService.findByReconcilicationNo(payable.getReconcilicationNo(),ApprovalStatus.APPROVAL_AGREE.getCode());
            BigDecimal adjustAmount = BigDecimal.ZERO;
            for(AdjustForMaster adjust : adjustForMasters){
                adjustAmount = adjustAmount.add(adjust.getAdjustAmount());
            }
            bo.setAdjustFreight(adjustAmount.compareTo(BigDecimal.ZERO)  == 0 ? null : adjustAmount);

            bos.add(bo);
        }
        return new Page<ReconcilicationForPayableBo>(pageCondition.getPageNo(), pageCondition.getPageSize(), total,
                bos);
    }

    @Override
    public Page<ReconciliationExtraForPayableBo> vendorSearchDetail(PageCondition pageCondition, LoginUser loginUser)
            throws BusinessException {
        // 未结算默认传0
        Map<String, Object> filters = pageCondition.getFilters();
        if (null != filters && null != filters.get("settleStatus")
                && filters.get("settleStatus").equals(Waybill.SettlementStatus.NOT_CLEAR.getCode())) {
            filters.put("settleStatus", Waybill.SettlementStatus.INIT.getCode());
        }
        int total = reconcilicationForPayableItemExtraMapper.itemCount(pageCondition);
        List<ReconcilicationForPayableItem> rows = reconcilicationForPayableItemExtraMapper.itemSearch(pageCondition);
        Page<ReconcilicationForPayableItem> page = new Page<>(pageCondition.getPageNo(), pageCondition.getPageSize(),
                total, rows);
        if (CollectionUtils.isEmpty(page.getResults())) {
            return new Page<ReconciliationExtraForPayableBo>(pageCondition.getPageNo(), pageCondition.getPageSize(), 0,
                    new ArrayList<ReconciliationExtraForPayableBo>());
        }
        List<ReconciliationExtraForPayableBo> reconciliationExtraForPayableBos = new ArrayList<ReconciliationExtraForPayableBo>();
        for (ReconcilicationForPayableItem reconcilication : page.getResults()) {
            ReconciliationExtraForPayableBo bo = new ReconciliationExtraForPayableBo();
            ReconciliationExtraForPayableExample example = new ReconciliationExtraForPayableExample();
            ReconciliationExtraForPayableExample.Criteria criteria = example.createCriteria();
            criteria.andReconciliationIdEqualTo(reconcilication.getReconcilicationId());
            criteria.andVendorIdEqualTo(reconcilication.getSettleAccountId());
            List<ReconciliationExtraForPayable> reconciliationExtraForPayables = reconciliationExtraForPayableMapper
                    .selectByExample(example);
            BeanUtils.copyProperties(reconcilication, bo);
            if (CollectionUtils.isNotEmpty(reconciliationExtraForPayables)) {
                ReconciliationExtraForPayable extraForPayables = reconciliationExtraForPayables.get(0);
                // 组装返回参数
                bo.setOilCardFee(extraForPayables.getOilCardFee());
                bo.setManagementFee(extraForPayables.getManagementFee());
                bo.setIsInvoice(extraForPayables.getIsInvoice());
                bo.setChargeTaxRate(extraForPayables.getTaxRate());
                bo.setDeductionTaxFee(extraForPayables.getDeductionTaxFee());
                bo.setReferenceTaxFee(extraForPayables.getReferenceTaxFee());
                bo.setExtraId(extraForPayables.getExtraId());
                bo.setVendorName(reconcilication.getSettleAccountName());
                bo.setVendorTaxRate(extraForPayables.getVendorTaxRate().compareTo(new BigDecimal(-1)) == 0 ? null : extraForPayables.getVendorTaxRate());
                // 承运商不含税金额
                if (null != reconcilication.getSettleFreight()) {
                    if (null != extraForPayables.getDeductionTaxFee()) {
                        bo.setVendorAfterTaxFreight(
                                reconcilication.getSettleFreight().subtract(extraForPayables.getDeductionTaxFee()));
                    } else {
                        bo.setVendorAfterTaxFreight(reconcilication.getSettleFreight());
                    }
                }
            }

            Vendor vendor = null;
            if (reconcilication.getSettleType().equals(PayableSettleAccountTypeEnum.DRIVER.getCode())) {
                // 司机名称
                Driver driver = vmsCommonService.loadDriverByDriverId(reconcilication.getSettleAccountId());
                if (null != driver) {
                    bo.setDriverName(driver.getName());
                }
                Waybill waybill = waybillService.getWaybill(reconcilication.getWaybillId());
                vendor = vmsCommonService.loadVendorByVendorId(waybill.getVehicleToVendor());
			}else if (reconcilication.getSettleType().equals(PayableSettleAccountTypeEnum.VENDOR.getCode())) {
                vendor = vmsCommonService.loadVendorByVendorId(reconcilication.getSettleAccountId());
            }
            if (null != vendor) {
                //应付金额:承运商含税金额-管理费-进项税额
                if (Objects.equals(VendorSourceEnum.NO_SELF_SUPPORT.getCode(), vendor.getVendorSource())) {
                    if(null != bo.getSettleFreight()){
                        bo.setPayableAmount(bo.getSettleFreight().subtract(null == bo.getManagementFee() ? BigDecimal.ZERO : bo.getManagementFee())
                                .subtract(null == bo.getDeductionTaxFee() ? BigDecimal.ZERO : bo.getDeductionTaxFee()));
                    }
                }
                bo.setVendorSourceName(VendorSourceEnum.getSourceDesc(vendor.getVendorSource()));
                bo.setVendorName(vendor.getVendorName());
                bo.setVendorTypeName(VendorTypeEnum.getVendorTypeDesc(vendor.getVendorType()));
            }
            //调整金额
            if (reconcilication.getSettleType().equals(PayableSettleAccountTypeEnum.VENDOR.getCode())){
                getAdjustFregiht(reconcilication, bo);
            }else {
                CapacityPool capacityPool = vmsCommonService.loadCapacityByDriverId(reconcilication.getSettleAccountId(), loginUser);
                if(null != capacityPool){
                    reconcilication.setSettleAccountId(capacityPool.getVendorId());
                    getAdjustFregiht(reconcilication, bo);
                }
            }

            // 默认为未结算状态
            if (bo.getSettleStatus().equals(Waybill.SettlementStatus.INIT.getCode())) {
                bo.setSettleStatus(Waybill.SettlementStatus.NOT_CLEAR.getCode());
            }
            //调整后含税金额
            if(bo.getAdjustFreight() != null && bo.getAdjustFreight().compareTo(BigDecimal.ZERO) != 0){
                bo.setAfterAdjustFreight(sumAjustAfterAmount(reconcilication.getReconcilicationId(), reconcilication.getSettleAccountId(), loginUser));
            }

            bo.setVendorCharge(itemIsCharge(reconcilication));

            reconciliationExtraForPayableBos.add(bo);
        }
        return new Page<ReconciliationExtraForPayableBo>(pageCondition.getPageNo(), pageCondition.getPageSize(), total,
                reconciliationExtraForPayableBos);
    }

    /**
     * 计算对账单详情承运商调整后金额
     * @param reconciliationId
     * @param settleAccountId
     * @param loginUser
     * @return
     */
    private BigDecimal sumAjustAfterAmount(Integer reconciliationId, Integer settleAccountId, LoginUser loginUser){

        BigDecimal sum = BigDecimal.ZERO;

        ReconcilicationForPayableItemExample example = new ReconcilicationForPayableItemExample();
        ReconcilicationForPayableItemExample.Criteria criteria = example.createCriteria();
        criteria.andReconcilicationIdEqualTo(reconciliationId).andSettleAccountIdEqualTo(settleAccountId);

        List<ReconcilicationForPayableItem> items = reconcilicationForPayableItemMapper.selectByExample(example);
        List<Integer> waybillIds = new ArrayList<>();
        for(ReconcilicationForPayableItem item : items){
            waybillIds.add(item.getWaybillId());
        }

        if(waybillIds.size() == 0){
			return sum;
		}

        WaybillAmountFilter filter = new WaybillAmountFilter();
        filter.setWaybillIds(waybillIds);
        List<WaybillAmount> waybillAmounts = waybillAmountService.findByFilter(filter, loginUser);

        for(WaybillAmount waybillAmount : waybillAmounts){
            sum = sum.add(waybillAmount.getLastVendorFreightWithTax());
        }
        return sum;
    }

    private void getAdjustFregiht(ReconcilicationForPayableItem reconcilication, ReconciliationExtraForPayableBo bo) {
        ReconcilicationForPayable payable = reconcilicationForPayableMapper.selectByPrimaryKey(reconcilication.getReconcilicationId());
        if (null != payable) {
            List<AdjustForMaster> adjustForMasters = adjustForMasterService.findByReconcilicationNo(payable.getReconcilicationNo(), ApprovalStatus.APPROVAL_AGREE.getCode());
            BigDecimal sum = BigDecimal.ZERO;
            for (AdjustForMaster master : adjustForMasters) {
                AdjustForItem adjustForItem = new AdjustForItem();
                adjustForItem.setAdjustId(master.getAdjustId());
                List<AdjustForItem> adjustForItems = adjustForItemService.getByFilter(adjustForItem);
                for (AdjustForItem item : adjustForItems) {
                    if (!item.getVendorId().equals(reconcilication.getSettleAccountId())) {
                        continue;
                    }
                    sum = sum.add(item.getAdjustForFreight()).add(item.getAdjustForCarry()).add(item.getAdjustForWorkload())
                            .add(item.getAdjustForUpstairs()).add(item.getAdjustForFine()).add(item.getAdjustForCargoLoss());
                }
            }
            bo.setAdjustFreight(sum.compareTo(BigDecimal.ZERO) == 0 ? null : sum);
        }
    }

    @Override
    public Page<ReconciliationExtraForPayableBo> waybillDetails(PageCondition pageCondition, LoginUser loginUser)
            throws BusinessException {
        ReconcilicationForPayableItemExample example = new ReconcilicationForPayableItemExample();
        Paging paging = new Paging(pageCondition, "reconcilication_item_id", null);
        example.setStartOffSet(paging.getStartOffSet());
        example.setSize(paging.getPageSize());
        example.setOrderByClause(paging.getOrder());
        ReconcilicationForPayableItemExample.Criteria criteria = example.createCriteria();
        Map<String, Object> filters = pageCondition.getFilters();
        if (null == filters.get("reconcilicationId")) {
            return new Page<ReconciliationExtraForPayableBo>(pageCondition.getPageNo(), pageCondition.getPageSize(), 0,
                    new ArrayList<ReconciliationExtraForPayableBo>());
        }
        criteria.andReconcilicationIdEqualTo((Integer) filters.get("reconcilicationId"));
        if (null != filters.get("settleType")) {
            criteria.andSettleTypeEqualTo((Integer) filters.get("settleType"));
        }
        if (null != filters.get("settleAccountId")) {
            criteria.andSettleAccountIdEqualTo((Integer) filters.get("settleAccountId"));
        }
        if (null != filters.get("waybillNo")) {
            criteria.andWaybillNoEqualTo(filters.get("waybillNo").toString());
        }
        int total = reconcilicationForPayableItemMapper.countByExample(example);
        List<ReconcilicationForPayableItem> list = reconcilicationForPayableItemMapper.selectByExample(example);
        Page<ReconcilicationForPayableItem> page = new Page<ReconcilicationForPayableItem>(pageCondition.getPageNo(),
                pageCondition.getPageSize(), total, list);
        if (CollectionUtils.isEmpty(page.getResults())) {
            return new Page<ReconciliationExtraForPayableBo>(pageCondition.getPageNo(), pageCondition.getPageSize(), 0,
                    new ArrayList<ReconciliationExtraForPayableBo>());
        }
        List<ReconciliationExtraForPayableBo> reconciliationExtraForPayableBos = new ArrayList<ReconciliationExtraForPayableBo>();
        for (ReconcilicationForPayableItem reconcilication : page.getResults()) {
            ReconciliationExtraForPayableBo bo = new ReconciliationExtraForPayableBo();
            BeanUtils.copyProperties(reconcilication, bo);
            TruckRequire truckRequire = truckRequireService.findTruckRequireByWaybillId(reconcilication.getWaybillId(),
                    loginUser);
            if (null != truckRequire) {
                bo.setTaxRateValue(truckRequire.getTaxRateValue());
            }
            Waybill waybill = waybillCommonService.getWaybillById(reconcilication.getWaybillId());
            if (null != waybill) {
                bo.setPlanDeliveryTime(waybill.getPlanDeliveryTime());
                bo.setEstimateFreight(waybill.getEstimateFreight());
                bo.setAfterTaxFreight(waybill.getAfterTaxFreight());
                bo.setRebateFee(waybill.getRebateFee());
                bo.setVehicleType(waybill.getVehicleType());
                // 司机类型名称
                if (null != waybill.getDriverType()) {
                    bo.setDriverTypeName(DriverTypeEnum.getDescByCode(waybill.getDriverType()));
                }

            }
            if (reconcilication.getSettleType().equals(PayableSettleAccountTypeEnum.DRIVER.getCode())) {
                // 司机名称
                Driver driver = vmsCommonService.loadDriverByDriverId(reconcilication.getSettleAccountId());
                if (null != driver) {
                    bo.setDriverName(driver.getName());
                }
            } else if (reconcilication.getSettleType().equals(PayableSettleAccountTypeEnum.VENDOR.getCode())) {
                Vendor vendor = vmsCommonService.loadVendorByVendorId(reconcilication.getSettleAccountId());
                if (null != vendor) {
                    bo.setVendorName(vendor.getVendorName());
                    bo.setVendorSourceName(VendorSourceEnum.getSourceDesc(vendor.getVendorSource()));
                    bo.setVendorTypeName(VendorTypeEnum.getVendorTypeDesc(vendor.getVendorType()));
                }
            }

            AdjustFreightVo adjustFreightVo = adjustForItemService.loadAdjustAbsFreightByWaybillIdAnd
                    (reconcilication.getWaybillId(), AdjustType.AFTER.getCode(),AdjustMasterType.VENDOR.getCode());

            WaybillAmount waybillAmount = waybillAmountService.loadByWaybillId(reconcilication.getWaybillId());
            if(null != adjustFreightVo){
                // 运单调整金额
                bo.setAdjustFreight(adjustFreightVo.getAdjustFreight());
                // 运单调整后金额
                bo.setAfterAdjustFreight(waybillAmount == null ? null : waybillAmount.getLastVendorFreightWithTax());
            }

            if (null != waybillAmount) {
                // 重新赋值客户侧费用信息
                bo.setEstimateFreight(waybillAmount.getLastCustomerFreightWithTax());
                bo.setAfterTaxFreight(BaseUtil.calFreightWithNotTax(waybillAmount.getLastCustomerFreightWithTax(),
                    (truckRequire == null || truckRequire.getTaxRateValue() == null ? BigDecimal.ZERO
                        : truckRequire.getTaxRateValue())));
            }
            //司机/承运商含税
            bo.setShow4DriverFreight(reconcilication.getSettleFreight());
            reconciliationExtraForPayableBos.add(bo);
        }
        return new Page<ReconciliationExtraForPayableBo>(pageCondition.getPageNo(), pageCondition.getPageSize(), total,
                reconciliationExtraForPayableBos);
    }

    @Override
    public ReconcilicationForPayableBo getReconciliationOverView(int reconciliationId, LoginEmployee loginEmployee)
            throws BusinessException {
        ReconcilicationForPayable payable = reconcilicationForPayableMapper.selectByPrimaryKey(reconciliationId);
        if (payable == null) {
            return new ReconcilicationForPayableBo();
        }
        PageCondition pageCondition = new PageCondition();
        pageCondition.setPageNo(1);
        pageCondition.setPageSize(Integer.MAX_VALUE);
        pageCondition.getFilters().put("reconciliationNo", payable.getReconcilicationNo());

        ReconcilicationForPayableBo bo = new ReconcilicationForPayableBo();
        //基础信息
        buildOverViewBasicInfo(loginEmployee, payable, pageCondition, bo);
        //金额信息
        buildOverViewAmountInfo(payable, bo, pageCondition);
        return bo;
    }

    private void buildOverViewBasicInfo(LoginEmployee loginEmployee, ReconcilicationForPayable payable, PageCondition pageCondition, ReconcilicationForPayableBo bo) {
        bo.setApprovalStatus(payable.getApprovalStatus());
        bo.setCustomerName(payable.getCustomerName());
        bo.setProjectName(payable.getProjectName());
        bo.setReconcilicationNo(payable.getReconcilicationNo());
        // 承运商数量
        PageCondition p = new PageCondition();
        p.setPageNo(1);
        p.setPageSize(Integer.MAX_VALUE);
        p.getFilters().put("reconcilicationId", payable.getReconcilicationId());
        int vendorCount = reconcilicationForPayableItemExtraMapper.itemCount(p);
        bo.setVendorCount(vendorCount);
        // 运单数量
        int waybillCount = waybillCommonService.searchCount(pageCondition);
        bo.setWaybillCount(waybillCount);
        // 子公司名称
        bo.setDepartmentId(payable.getDepartmentId());
        Department department = departmentService.loadDepartment(payable.getDepartmentId());
        bo.setDepartmentName(department == null ? "" : department.getBusinessLicenceName());
        // 子公司更改记录
        ReconcilicationForPayableLogExample example = new ReconcilicationForPayableLogExample();
        example.setOrderByClause("create_time desc");
        ReconcilicationForPayableLogExample.Criteria criteria = example.createCriteria();
        criteria.andReconcilicationIdEqualTo(payable.getReconcilicationId());
        List<ReconcilicationForPayableLog> logs = reconcilicationForPayableLogMapper.selectByExampleWithBLOBs(example);
        if (CollectionUtils.isNotEmpty(logs)) {
            ReconciliationLog reconciliationLog = JSON.parseObject(logs.get(0).getContent(), ReconciliationLog.class);
            bo.setChangRecord("子公司由" + reconciliationLog.getOldText() + "(id:" + reconciliationLog.getOldValue()
                    + ")更改为" + reconciliationLog.getNewText() + "(id:" + reconciliationLog.getNewValue() + ")");
        }
        // 业务区域名称
        bo.setAreaCodeName(businessAreaCommonService.loadLogicAndSelfAreaName(payable.getAreaCode(), loginEmployee));
    }

    private void buildOverViewAmountInfo(int reconciliationId, ReconcilicationForPayable payable, ReconcilicationForPayableBo bo, PageCondition pageCondition) {
        // 对账时承运侧含税金额
        BigDecimal sumSettleFreight = reconcilicationForPayableItemExtraMapper
            .sumSettleFreight(reconciliationId);
        bo.setSumSettleFreight(null == sumSettleFreight ? BigDecimal.ZERO : sumSettleFreight);
        // 承运商不含税总额:承运商含税金额-管理费-进项税额
        BigDecimal managementFee = BigDecimal.ZERO;
        BigDecimal deductionTaxFee = BigDecimal.ZERO;
        ReconciliationExtraForPayable extraForPayable = reconcilicationForPayableItemExtraMapper
            .sumDeductionTaxFee(reconciliationId);
        if (null != extraForPayable) {
            managementFee = (null == extraForPayable.getManagementFee() ? BigDecimal.ZERO
                : extraForPayable.getManagementFee());
            deductionTaxFee = (null == extraForPayable.getDeductionTaxFee() ? BigDecimal.ZERO
                : extraForPayable.getDeductionTaxFee());
        }
        bo.setSumNonSettleFreight(
            bo.getSumSettleFreight().subtract(managementFee).subtract(deductionTaxFee));
    }

    private void buildOverViewAmountInfo(ReconcilicationForPayable payable, ReconcilicationForPayableBo bo, PageCondition pageCondition) {
        List<Waybill> waybills = waybillCommonService.search(pageCondition);
        //Map (运单id:承运商id)
        Map<Integer, Integer> vendorIdMap = Maps.newHashMap();
        List<Integer> vendorIds = Lists.newArrayList();
        buildVendorData(waybills, vendorIdMap, vendorIds);
        //Map (承运商id:承运商类型)
        Map<Integer, Byte> ownVendorMap = vmsCommonService.listVendorSource(vendorIds, VendorSourceEnum.SELF_SUPPORT.getCode());
        BigDecimal profitRatePromise = BigDecimal.ZERO;
        //承诺毛利率
        Project project = projectService.getProjectV2(payable.getProjectId());
        if(null != project){
            profitRatePromise = project.getProfitRate();
            bo.setProfitRatePromise(BaseUtil.formatDecimal(profitRatePromise.doubleValue()));
        }
        //组装对账时金额
        buildAmount(payable, bo, vendorIdMap, ownVendorMap, profitRatePromise);
        //组装调整金额
        buildAdjustAmount(payable, bo, waybills,ownVendorMap, profitRatePromise);
        //组装按月统计金额
        buildMonthAmount(payable, bo, waybills);
    }

    private void buildAmount(ReconcilicationForPayable payable, ReconcilicationForPayableBo bo, Map<Integer, Integer> vendorIdMap, Map<Integer, Byte> ownVendorMap, BigDecimal profitRatePromise) {
        // 对账时承运侧含税金额
        BigDecimal sumSettleFreight = reconcilicationForPayableItemExtraMapper.sumSettleFreight(payable.getReconcilicationId());
        bo.setSumSettleFreight(null == sumSettleFreight ? BigDecimal.ZERO : sumSettleFreight);

        BigDecimal sumEstimateFreight = BigDecimal.ZERO;
        BigDecimal sumEstimateFreightVendor = BigDecimal.ZERO;
        BigDecimal vendorSettleFreight = BigDecimal.ZERO;
        BigDecimal profitRate = null;

        ReconcilicationForPayableItemExample example = new ReconcilicationForPayableItemExample();
        Criteria criteria = example.createCriteria();
        criteria.andReconcilicationIdEqualTo(payable.getReconcilicationId());
        List<ReconcilicationForPayableItem> reconcilications = reconcilicationForPayableItemMapper.selectByExample(example);

        List<ReconcilicationForPayableItemBo> itemBos = Lists.newArrayList();
        buildItemBo(reconcilications, vendorIdMap, itemBos);
        // 承运侧不含税金额=承运侧含税金额/（1+税率）
        if(CollectionUtils.isNotEmpty(reconcilications)){
            BigDecimal taxRate = reconcilications.get(0).getTaxRate();
            bo.setSumNonSettleFreight(null == taxRate ? bo.getSumSettleFreight() : BaseUtil.calFreightWithNotTax(bo.getSumSettleFreight(),taxRate));
        }

        Boolean initValue = false;
        for (ReconcilicationForPayableItemBo reconcilication : itemBos) {
            if(null != reconcilication.getReceivableWithTax()){
                initValue = true;
            }
            //含自营承运商:
            sumEstimateFreightVendor = sumEstimateFreightVendor.add(reconcilication.getReceivableWithTax() == null ? BigDecimal.ZERO : reconcilication.getReceivableWithTax());
            //排除自营承运商（计算毛利额、毛利率时需排除）
            if(null != ownVendorMap.get(reconcilication.getVendorId())){continue;}
            sumEstimateFreight = sumEstimateFreight.add(reconcilication.getReceivableWithTax() == null ? BigDecimal.ZERO : reconcilication.getReceivableWithTax());
            vendorSettleFreight = vendorSettleFreight.add(reconcilication.getSettleFreight() == null ? BigDecimal.ZERO : reconcilication.getSettleFreight());
        }

        //对账时客户侧含税金额
        if(initValue){
            bo.setCustomerFreightWithTax(sumEstimateFreightVendor);
        }else {
            bo.setCustomerFreightWithTax(sumEstimateFreightVendor.compareTo(BigDecimal.ZERO)  == 0 ? null : sumEstimateFreightVendor);
        }
        //对账时毛利额
        if(sumEstimateFreight.compareTo(BigDecimal.ZERO) != 0){
            bo.setProfit(sumEstimateFreight.subtract(vendorSettleFreight));
            //对账时毛利率
            profitRate = (sumEstimateFreight.subtract(vendorSettleFreight)).divide(sumEstimateFreight, 4, BigDecimal.ROUND_HALF_UP);
            bo.setProfitRate(BaseUtil.formatDecimal(profitRate.doubleValue()));
        }

        //对账时与承诺毛利差：对账时毛利率 -项目承诺毛利率
        if(null != profitRate){
            bo.setProfitRateDiffer(BaseUtil.formatDecimal(profitRate.subtract(profitRatePromise).doubleValue()));
        }
    }

    private void buildItemBo(List<ReconcilicationForPayableItem> reconcilications, Map<Integer, Integer> vendorIdMap, List<ReconcilicationForPayableItemBo> itemBos) {
        for (ReconcilicationForPayableItem reconcilication : reconcilications) {
            ReconcilicationForPayableItemBo itemBo = new ReconcilicationForPayableItemBo();
            BeanUtils.copyProperties(reconcilication, itemBo);
            itemBo.setVendorId(vendorIdMap.get(reconcilication.getWaybillId()));
            itemBos.add(itemBo);
        }
    }

    private void buildVendorData(List<Waybill> waybills, Map<Integer, Integer> vendorIdMap, List<Integer> vendorIds) {
        for (Waybill waybill : waybills) {
            vendorIdMap.put(waybill.getWaybillId(), waybill.getVehicleToVendor());
            vendorIds.add(waybill.getVehicleToVendor());
        }
    }

    private void buildMonthAmount(ReconcilicationForPayable payable, ReconcilicationForPayableBo bo, List<Waybill> waybills) {
        if(CollectionUtils.isNotEmpty(waybills)){
            //项目年、月（运单用车时间）
            Calendar cal = Calendar.getInstance();
            cal.setTime(waybills.get(0).getPlanDeliveryTime());
            bo.setYear(cal.get(Calendar.YEAR));
            bo.setMonth(cal.get(Calendar.MONTH)+1);

            //判断对账单运单是否在同一个月
            Boolean sameMonth = waybillCommonService.checkPlanDeliveryTimeInSameMonth(waybills);
            if(sameMonth && null != payable.getProjectId()){
                WaybillStatisticsAmountVO amountVO = waybillCommonService.getMonthAmountInfo(payable.getProjectId(), waybills.get(0).getPlanDeliveryTime());
                bo.setMonthProfit(amountVO.getMonthProfitAmount());
                if(null != amountVO.getMonthProportion()){
                    bo.setMonthProfitRate(BaseUtil.formatDecimal(amountVO.getMonthProportion().doubleValue()));
                }
                if(null != amountVO.getMonthProportionGap()){
                    bo.setMonthProfitRateDiffer(BaseUtil.formatDecimal(amountVO.getMonthProportionGap().doubleValue()));
                }
            }
        }
    }

    private void buildAdjustAmount(ReconcilicationForPayable payable, ReconcilicationForPayableBo bo, List<Waybill> waybills,Map<Integer, Byte> ownVendorMap, BigDecimal profitRatePromise) {
        BigDecimal sumFreightWithTax = BigDecimal.ZERO;
        BigDecimal customerFreightWithTax = BigDecimal.ZERO;
        BigDecimal adjustProfitRate = null;

        //排除运单自营承运商
        List<Integer> excludeWaybillIds = Lists.newArrayList();
        for (Waybill waybill : waybills) {
            if(null != ownVendorMap.get(waybill.getVehicleToVendor())){continue;}
            excludeWaybillIds.add(waybill.getWaybillId());
        }

        List<WaybillAmount> waybillAmounts = waybillAmountService.findByWaybillIds(excludeWaybillIds,null);
        for (WaybillAmount amount : waybillAmounts) {
            sumFreightWithTax = sumFreightWithTax.add(amount.getLastVendorFreightWithTax());
            customerFreightWithTax = customerFreightWithTax.add(amount.getLastCustomerFreightWithTax());
        }

        //对账单审批通过&调整单审批通过展示调整金额
        if (payable.getApprovalStatus() != null && payable.getApprovalStatus()
                .equals(ReconcilicationForPayableBo.ReconciliationStatus.AGREE.getCode())) {
            //判断运单是否有调整
            Boolean hadAdjust = checkWaybillHadAdjust(excludeWaybillIds);
            if(hadAdjust){
                //调整后的毛利额
                if(customerFreightWithTax.compareTo(BigDecimal.ZERO) != 0 || sumFreightWithTax.compareTo(BigDecimal.ZERO) != 0){
                    bo.setAdjustProfit(customerFreightWithTax.subtract(sumFreightWithTax));
                }
                //调整后的毛利率
                if(sumFreightWithTax.compareTo(BigDecimal.ZERO) != 0){
                    adjustProfitRate = (customerFreightWithTax.subtract(sumFreightWithTax)).divide(customerFreightWithTax, 4, BigDecimal.ROUND_HALF_UP);
                    bo.setAdjustProfitRate(BaseUtil.formatDecimal(adjustProfitRate.doubleValue()));
                }
                //调整后与承诺毛利率差，对账后调整后的毛利率 - 项目承诺毛利率
                if(null != adjustProfitRate){
                    bo.setAdjustProfitRateDiffer(BaseUtil.formatDecimal(adjustProfitRate.subtract(profitRatePromise).doubleValue()));
                }
            }
        }
    }

    @Override
    public Boolean checkWaybillHadAdjust(List<Integer> waybillIds){
        for (Integer waybillId : waybillIds) {
            AdjustForMasterFilter filter = new AdjustForMasterFilter();
            filter.setWaybillId(waybillId);
            filter.setApprovalStatus(ApprovalStatus.APPROVAL_AGREE.getCode());
            List<AdjustForMaster> adjust = adjustForMasterAddService.findByFilter(filter, null);
            if(CollectionUtils.isNotEmpty(adjust)){
                return true;
            }
        }
        return false;
    }

    @Override
    public ReconcilicationForPayable findByReconciliationNo(String reconciliationNo) throws BusinessException {
        ReconcilicationForPayableExample example = new ReconcilicationForPayableExample();
        ReconcilicationForPayableExample.Criteria criteria = example.createCriteria();
        criteria.andReconcilicationNoEqualTo(reconciliationNo);
        List<ReconcilicationForPayable> list = reconcilicationForPayableMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) {
            throw new BusinessException("reconciliationNewExamples", "errors.common.prompt", "对账单不存在");
        } else {
            return list.get(0);
        }
    }

    @Override
    public void vendorCharge(ReconciliationExtraForPayable reconciliationExtraForPayable, LoginUser loginUser) {
        // 根据对账单id、承运商id查询历史扣款记录
        ReconciliationExtraForPayableExample example = new ReconciliationExtraForPayableExample();
        ReconciliationExtraForPayableExample.Criteria criteria = example.createCriteria();
        criteria.andReconciliationIdEqualTo(reconciliationExtraForPayable.getReconciliationId());
        criteria.andVendorIdEqualTo(reconciliationExtraForPayable.getVendorId());
        List<ReconciliationExtraForPayable> payables = reconciliationExtraForPayableMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(payables)) {
            // 新增
            reconciliationExtraForPayable.setAdjustTime(new Date());
            reconciliationExtraForPayable.setAdjustUserId(loginUser.getUserId());
            reconciliationExtraForPayableMapper.insertSelective(reconciliationExtraForPayable);
        } else {
            // 编辑
            reconciliationExtraForPayable.setExtraId(payables.get(0).getExtraId());
            reconciliationExtraForPayable.setAdjustTime(new Date());
            reconciliationExtraForPayable.setAdjustUserId(loginUser.getUserId());
            reconciliationExtraForPayableMapper.updateByPrimaryKeySelective(reconciliationExtraForPayable);
        }
    }

    @Override
    public BigDecimal taxReference(ReconcilicationForPayableItem item) {
        if (item.getSettleFreight() != null && item.getTaxRate() != null) {
            return item.getSettleFreight().subtract(item.getSettleFreight()
                    .divide(item.getTaxRate().add(BigDecimal.valueOf(1)), 2, BigDecimal.ROUND_HALF_UP));
        }
        return BigDecimal.valueOf(0);
    }

    @Override
    public void doFinishWorkFlowTask(WorkFlowMessage message) throws BusinessException {
        if(null == message || StringUtils.isBlank(message.getNumber())){
            return;
        }
        log.info("payableTaskParm :{}", JSON.toJSONString(message));
        LoginEmployee loginEmployee = message.getLoginEmployee();
        ReconcilicationForPayable payable = null;

        if(StringUtils.isNotBlank(message.getBusinessKey()) && NumberUtils.isNumber(message.getBusinessKey())){
            payable = reconcilicationForPayableMapper.selectByPrimaryKey(Integer.valueOf(message.getBusinessKey()));
        }else {
            //对工作流未返回businessKey容错处理
            payable = this.findByReconciliationNo(message.getNumber());
        }

        if(null == payable){
            throw new BusinessException("payableNotExist", "应付对账单不存在");
        }

        if (payable.getApprovalStatus() != null && payable.getApprovalStatus()
                .equals(ReconcilicationForPayableBo.ReconciliationStatus.AGREE.getCode())) {
            throw new BusinessException("reconciliationStatus", "该对账单已经审核通过");
        }

        try {
            this.handleWorkFlowMsg(message, payable, loginEmployee);
        } catch (Exception e) {
            log.error("AP reconciliation no is {},error is {}: ", payable.getReconcilicationNo(), e.getMessage());
            if (e instanceof BusinessException) {
                throw e;
            }
            throw new BusinessException("unknow.error", "外部错误:" + e.getMessage());
        }
    }

    /**
     * 处理工作流消息
     */
    private void handleWorkFlowMsg(WorkFlowMessage message, ReconcilicationForPayable payable, LoginEmployee loginEmployee) {
        if(null == message.getStatus()){ return;}
        String workFlowKey = message.getStatus().getWorkFlowKey();

        payable.setApprovalStatus(ApprovalStatus.getApprovalStatusWithWorkKey(workFlowKey));
        this.updateReconcilicationForPayable(payable, loginEmployee);
        if (ApprovalStatus.APPROVAL_AGREE.getWorkFlowKey().equals(workFlowKey)){
            // 将运单的对账状态更改为已对账
            updateWaybillByWorkFlow(payable);

            // 推送MQ消息给FMS
            log.info("payable send to FMS start");
            this.doSendWorkFlowPassMsgV2(payable, loginEmployee);
        }
    }

    private void updateWaybillByWorkFlow(ReconcilicationForPayable payable) {
        if (null == payable.getReconcilicationNo()) {
            return;
        }

        List<Waybill> waybills = waybillCommonService.findByReconciliationNo(payable.getReconcilicationNo());
        for (Waybill waybill : waybills) {
            if (NumberUtils.compare(Waybill.ReconciliationStatus.IN_THE_ACCOUNT.getCode(),
                    waybill.getReconciliationStatus()) == 0) {
                waybill.setReconciliationStatus(Waybill.ReconciliationStatus.HAS_RECONCILIATION.getCode());
            }
            waybill.setSettlementStatus(Waybill.SettlementStatus.NOT_CLEAR.getCode());
        }
        waybillCommonService.batchUpdateHasReconciliation(waybills);
    }

    /**
     * 通知fms：对账单以承运商拆分
     * 
     * @param payable
     */
    private void doSendWorkFlowPassMsgV2(ReconcilicationForPayable payable, LoginUser loginUser) {
        // 获取应付对账详情
        ReconcilicationForPayableItemExample example = new ReconcilicationForPayableItemExample();
        Criteria criteria = example.createCriteria();
        criteria.andReconcilicationIdEqualTo(payable.getReconcilicationId());
        List<ReconcilicationForPayableItem> reconcilications = reconcilicationForPayableItemMapper
                .selectByExample(example);

        // 以承运商为维度拆分
        Map<String, String> vendorNameMap = new HashMap<String, String>();
        Map<String, List<SourceLogisticsDetailDTO>> map = new HashMap<String, List<SourceLogisticsDetailDTO>>();
        for (ReconcilicationForPayableItem item : reconcilications) {
            if (NumberUtils.compare(item.getSettleType(), PayableSettleAccountTypeEnum.DRIVER.getCode()) == 0) {
                continue;
            }

            Waybill w =  waybillCommonService.getWaybillById(item.getWaybillId());
            if (null == w || (null == w.getVehicleToVendor() && null == w.getVendorId())) {
                continue;
            }

            if (!w.getReceiveWay().equals(ReceiveWay.TRANSFORM_BILL.getCode())) {
                Vendor vendor = vmsCommonService.loadVendorByVendorId(w.getVehicleToVendor());
                if (null == vendor || null == vendor.getVendorSource() || vendor.getVendorSource()
                    .equals(VendorSourceEnum.SELF_SUPPORT.getCode())) {
                    continue;
                }
            }

            SourceLogisticsDetailDTO sourceDTO = this.buildSourceLogisticsDetailDTO(item);
            // 物流项目
            sourceDTO.setLogisticsName(payable.getProjectName());

            String mapKey = item.getSettleAccountId().toString();
            if (map.containsKey(mapKey)) {
                map.get(mapKey).add(sourceDTO);
            } else {
                List<SourceLogisticsDetailDTO> detailList = new ArrayList<SourceLogisticsDetailDTO>();
                detailList.add(sourceDTO);
                map.put(mapKey, detailList);
                vendorNameMap.put(mapKey, item.getSettleAccountName());
            }
        }

        // 循环map，组装主体信息及承运商信息
        Map<String, FmsMQMessageDTO<SourceLogisticsDTO>> toFmsMap = new HashMap<String, FmsMQMessageDTO<SourceLogisticsDTO>>();
        for (String key : map.keySet()) {
            // MQ主信息
            FmsMQMessageDTO<SourceLogisticsDTO> fmsMQMessageDTO = new FmsMQMessageDTO<SourceLogisticsDTO>();
            fmsMQMessageDTO.setBusinessSystem(BusinessSystemEnum.TMS.getCode());
            // MQ承运商信息
            SourceLogisticsDTO dto = this.buildSourceLogisticsDTO(payable, loginUser);
            // 创建子账单
            String subReconcilicationNo = reconcilicationForSubPayableService.insert(payable.getReconcilicationId(),
                    Integer.valueOf(key), loginUser);
            // 单据来源编号 -- 子对账单号
            dto.setSourceNoteNo(subReconcilicationNo);
            // TMS对账单号
            dto.setSourceBizNo(payable.getReconcilicationNo());
            // 往来户编号
            dto.setTransactionalAccountNo(key);
            // 往来户名称
            dto.setTransactionalAccountName(vendorNameMap.get(key));
            Vendor vendor = vmsCommonService.loadVendorByVendorId(Integer.valueOf(key));
            dto.setEntityId(vendor.getJumaPin());

            List<SourceLogisticsDetailDTO> byVendorList = map.get(key);

            ReconciliationExtraForPayableExample payableExample = new ReconciliationExtraForPayableExample();
            com.juma.tgm.fms.domain.v3.ReconciliationExtraForPayableExample.Criteria payableCriteria = payableExample.createCriteria();
            payableCriteria.andReconciliationIdEqualTo(payable.getReconcilicationId());
            payableCriteria.andVendorIdEqualTo(Integer.valueOf(key));
            List<ReconciliationExtraForPayable> payables = reconciliationExtraForPayableMapper.selectByExample(payableExample);
            if( !org.springframework.util.CollectionUtils.isEmpty(payables) ){
                ReconciliationExtraForPayable extraForPayable = payables.get(0);
                // 是否开票
                dto.setTransactionalIsInvoice(extraForPayable.getIsInvoice()?1:0);
                if( extraForPayable.getIsInvoice() && BigDecimal.valueOf(-1).compareTo(extraForPayable.getVendorTaxRate()) != 0) {
                    // 承运商开票税率
                    dto.setTransactionalInvoiceTaxRate(extraForPayable.getVendorTaxRate());
                }
                // 进项税率
                dto.setTransactionalInputTaxRate(extraForPayable.getTaxRate());
                // 油卡、管理费、进项税
                byVendorList.addAll(this.buildExtra(byVendorList, payables, loginUser));
            }
            dto.setDetailList(byVendorList);
            fmsMQMessageDTO.setData(dto);
            toFmsMap.put(key, fmsMQMessageDTO);
        }

        // 循环map，发送FMS的MQ消息
        for (String key : toFmsMap.keySet()) {
            log.info("Send To FMS : {}.", JSONObject.toJSONString(toFmsMap.get(key)));
            sendPayableService.send(toFmsMap.get(key));
        }
    }

    // 组装承运商信息
    private SourceLogisticsDTO buildSourceLogisticsDTO(ReconcilicationForPayable payable, LoginUser loginUser) {
        SourceLogisticsDTO dto = new SourceLogisticsDTO();
        dto.setTransactionalAccountType(TransactionalAccountTypeEnum.CARRIER.getCode());
        // 来源单类型
        dto.setSourceType(SourceTypeEnum.LOGISTICS_CARRIER_STATEMENT_BILL.getCode());
        // 账单类型
        dto.setBillType(BillTypeEnum.STATEMENT_BILL.getCode());
        // 统计类型
        dto.setStatisticsType(payable.getProjectName());
        dto.setTenantId(payable.getTenantId());
        dto.setAreaCode(payable.getAreaCode());
        // 备注
        ReconcilicationForPayableLogExample example = new ReconcilicationForPayableLogExample();
        example.setOrderByClause("create_time desc");
        ReconcilicationForPayableLogExample.Criteria criteria = example.createCriteria();
        criteria.andReconcilicationIdEqualTo(payable.getReconcilicationId());
        List<ReconcilicationForPayableLog> logs = reconcilicationForPayableLogMapper.selectByExampleWithBLOBs(example);
        String str = "";
        if (CollectionUtils.isNotEmpty(logs)) {
            ReconciliationLog reconciliationLog = JSON.parseObject(logs.get(0).getContent(), ReconciliationLog.class);
            str = "子公司已由" + reconciliationLog.getOldText() + "更改为" + reconciliationLog.getNewText();
        }
        dto.setRemark(str);
        BusinessArea businessArea = businessAreaService.loadBusinessArea(payable.getAreaCode(), loginUser);
        if (null != businessArea) {
            dto.setAreaName(businessArea.getAreaName());
        }

        dto.setUniformSocialCreditCode(payable.getPayToCompanyCreditCode());
        Integer departmentId = payable.getDepartmentId();
        dto.setSubCompanyId(departmentId);
        Department department = departmentService.loadDepartment(departmentId);
        dto.setSubCompanyCode(department == null ? "" : department.getDepartmentCode());
        dto.setSubCompanyName(department == null ? "" :department.getBusinessLicenceName());
        return dto;
    }

    // 组装SourceLogisticsDetailDTO详情信息
    private SourceLogisticsDetailDTO buildSourceLogisticsDetailDTO(ReconcilicationForPayableItem item) {
        SourceLogisticsDetailDTO sourceDTO = new SourceLogisticsDetailDTO();
        sourceDTO.setBeforePriceIncludingTax(BigDecimal.ZERO.longValue());
        sourceDTO.setAdjustmentPrice(BigDecimal.ZERO.longValue());
        Waybill w = waybillCommonService.getWaybillById(item.getWaybillId());
        if (w != null) {
            // 车牌号
            sourceDTO.setPlateNumber(w.getPlateNumber());
            // 司机名称
            sourceDTO.setDriverName(w.getDriverName());
            // 司机类型
            sourceDTO.setDriverType(DriverTypeEnum.NOT_OWN_SALE.getDesc());

            // 赋值三个费用信息是保证waybillAmount表没有数据时的兼容
            // 客户运费
            sourceDTO.setCustomerFreight(w.getEstimateFreight() == null ? 0
                    : w.getEstimateFreight().multiply(new BigDecimal(100 + "")).longValue());
            // 承运商运费
            sourceDTO.setVendorFreight(w.getShow4DriverFreight() == null ? 0
                    : w.getShow4DriverFreight().multiply(new BigDecimal(100 + "")).longValue());
            // 不含税金额,单位分
            sourceDTO.setPriceExcludingTax(w.getAfterTaxFreight().multiply(new BigDecimal("100")).longValue());
        }

        // 获取运单费用表信息，有数据则覆盖三个字段的取值
        WaybillAmount amount = waybillAmountService.loadByWaybillId(item.getWaybillId());
        if (null != amount) {
            // 客户运费
            sourceDTO.setCustomerFreight(amount.getLastCustomerFreightWithTax() == null ? 0
                : amount.getLastCustomerFreightWithTax().multiply(new BigDecimal(100 + "")).longValue());
            // 不含税金额,单位分
            sourceDTO.setPriceExcludingTax(BaseUtil.calFreightWithNotTax(amount.getLastCustomerFreightWithTax(),
                item.getTaxRate()).multiply(new BigDecimal("100")).longValue());

            BigDecimal lastVendorFreightWithTax = amount.getLastVendorFreightWithTax() == null ? BigDecimal.ZERO :
                amount.getLastVendorFreightWithTax();
            // 承运商运费
            sourceDTO.setVendorFreight(lastVendorFreightWithTax.multiply(new BigDecimal(100 + "")).longValue());
        }

        // 获取调整单组装数据
        AdjustForPayable adjustForPayable = adjustForPayableService.findLastByWaybillId(item.getWaybillId());
        if (null != adjustForPayable) {
            BigDecimal payableWithTax = adjustForPayable.getPayableWithTax() == null ? BigDecimal.ZERO
                    : adjustForPayable.getPayableWithTax();
            BigDecimal payableWithTaxAdjust = adjustForPayable.getPayableWithTaxAdjust() == null ? BigDecimal.ZERO
                    : adjustForPayable.getPayableWithTaxAdjust();
            // 调整前金额,单位分
            sourceDTO.setBeforePriceIncludingTax(payableWithTax.multiply(new BigDecimal("100")).longValue());
            // 调整金额,单位分
            sourceDTO.setAdjustmentPrice(
                    (payableWithTaxAdjust.subtract(payableWithTax)).multiply(new BigDecimal("100")).longValue());
        }

        // 业务类型
        sourceDTO.setSourceBizType(BusinessDetailTypeEnum.WAYBILL.getCode());
        // 运单号
        sourceDTO.setSourceBizNo(item.getWaybillNo());
        // 含税金额,单位分
        sourceDTO.setPriceIncludingTax(item.getSettleFreight().multiply(new BigDecimal("100")).longValue());
        // 税,单位分
        sourceDTO.setTax(sourceDTO.getPriceExcludingTax() - sourceDTO.getPriceExcludingTax());
        // 税率,如0.1表示10%
        sourceDTO.setTaxRate(item.getTaxRate());
        // 车架号
        sourceDTO.setVin(item.getVehicleFrameNo());

        // 获取运单组装数据
        Waybill waybill = waybillCommonService.getWaybillById(item.getWaybillId());
        if (null != waybill) {
            // 用车时间
            sourceDTO.setUseCarTime(waybill.getPlanDeliveryTime());
            // 运单完成时间
            sourceDTO.setWaybillCompleteTime(waybill.getFinishTime());
			// 车型
            com.juma.vms.truck.domain.Truck truck = vmsCommonService.loadTruckByTruckId(waybill.getTruckId());
			if (null != truck) {
				sourceDTO.setTruckType(truck.getTruckRunType() == null ? "" : TruckRunTypeEnum.getDescByCode(truck.getTruckRunType()));
			}
        }

        return sourceDTO;
    }

    // 油卡、管理费、税费
    private List<SourceLogisticsDetailDTO> buildExtra(List<SourceLogisticsDetailDTO> byVendorList, Collection<ReconciliationExtraForPayable> payables, LoginUser loginUser) {
        List<SourceLogisticsDetailDTO> result = new ArrayList<SourceLogisticsDetailDTO>();
        for (ReconciliationExtraForPayable extra : payables) {
            // 油卡
            SourceLogisticsDetailDTO dtoOilCard = new SourceLogisticsDetailDTO();
            dtoOilCard.setSourceBizType(BusinessDetailTypeEnum.OIL_CARD.getCode());
            dtoOilCard.setPriceIncludingTax(extra.getOilCardFee() == null ? 0L
                    : extra.getOilCardFee().multiply(new BigDecimal("-100")).longValue());
            result.add(dtoOilCard);

            // 管理费
            SourceLogisticsDetailDTO dtoManagementFee = new SourceLogisticsDetailDTO();
            dtoManagementFee.setSourceBizType(BusinessDetailTypeEnum.MANAGEMENT_FEE.getCode());
            dtoManagementFee.setPriceIncludingTax(extra.getManagementFee() == null ? 0L
                    : extra.getManagementFee().multiply(new BigDecimal("-100")).longValue());
            result.add(dtoManagementFee);

            // 可抵扣进项税
            SourceLogisticsDetailDTO dtoTaxFee = new SourceLogisticsDetailDTO();
            dtoTaxFee.setSourceBizType(BusinessDetailTypeEnum.TAX.getCode());
            dtoTaxFee.setPriceIncludingTax(extra.getDeductionTaxFee() == null ? 0L
                    : extra.getDeductionTaxFee().multiply(new BigDecimal("-100")).longValue());
            result.add(dtoTaxFee);
        }
        return result;
    }

    @Override
    public void cancelReconciliation(int reconciliationId, LoginEmployee loginEmployee) throws BusinessException {
        ReconcilicationForPayable payable = reconcilicationForPayableMapper.selectByPrimaryKey(reconciliationId);
        if (payable == null) {
            return;
        }
        if (NumberUtils.compare(payable.getApprovalStatus(),
                ReconcilicationForPayableBo.ReconciliationStatus.Append.getCode()) != 0
                && NumberUtils.compare(payable.getApprovalStatus(),
                        ReconcilicationForPayableBo.ReconciliationStatus.REJECT.getCode()) != 0) {
            throw new BusinessException("reconciliationStatusError", "errors.reconciliation.cancelStatusError");
        }
        String processInstanceId = payable.getProcessInstanceId();
        Integer reconciliationStatus = payable.getApprovalStatus();
        // 回滚运单状态
        List<Waybill> waybills = waybillCommonService.findByReconciliationNo(payable.getReconcilicationNo());
        if (CollectionUtils.isNotEmpty(waybills)) {
            Waybill w = null;
            for (Waybill waybill : waybills) {
                w = new Waybill();
                w.setWaybillId(waybill.getWaybillId());
                w.setReconciliationNo("");
                w.setIsSubmitMatch(null);
                w.setReconciliationStatus(Waybill.ReconciliationStatus.NOT_RECONCILIATION.getCode());
                waybillCommonService.update(w, loginEmployee);
            }
        }
        // 删除对账单
        reconcilicationForPayableMapper.deleteByPrimaryKey(reconciliationId);
        // 删除对账单明细
        ReconcilicationForPayableItemExample example = new ReconcilicationForPayableItemExample();
        ReconcilicationForPayableItemExample.Criteria criteria = example.createCriteria();
        criteria.andReconcilicationIdEqualTo(reconciliationId);
        reconcilicationForPayableItemMapper.deleteByExample(example);
        // 删除应付额外表
        ReconciliationExtraForPayableExample e = new ReconciliationExtraForPayableExample();
        ReconciliationExtraForPayableExample.Criteria c = e.createCriteria();
        c.andReconciliationIdEqualTo(reconciliationId);
        reconciliationExtraForPayableMapper.deleteByExample(e);
        // 驳回状态要删除工作流记录
        if (NumberUtils.compare(reconciliationStatus,
                ReconcilicationForPayableBo.ReconciliationStatus.REJECT.getCode()) == 0) {
            if (!payable.getSubmitUserId().equals(loginEmployee.getUserId())) {
                throw new BusinessException("cancelReconciliationError", "操作人必须和提交到工作流的是同一个人");
            }
            processService.deleteProcessInstance(processInstanceId, "发起人撤回", loginEmployee);
        }
    }

    @Override
    public void cancelWorkFlowTask(int reconciliationId, LoginEmployee loginEmployee) throws BusinessException {
        ReconcilicationForPayable payable = reconcilicationForPayableMapper.selectByPrimaryKey(reconciliationId);
        if (payable == null) {
            return;
        }
        if (payable.getProcessInstanceId() == null) {
            return;
        }
        if (NumberUtils.compare(payable.getApprovalStatus(),
                ReconcilicationForPayableBo.ReconciliationStatus.AGREE.getCode()) == 0) {
            return;
        }
        if (NumberUtils.compare(payable.getApprovalStatus(),
                ReconcilicationForPayableBo.ReconciliationStatus.Append.getCode()) == 0) {
            return;
        }
        // 撤回工作流审批
        processService.revokeProcessInstance(payable.getProcessInstanceId(), "发起人撤回", loginEmployee);
        // 修改对账单状态
        payable.setApprovalStatus(ReconcilicationForPayableBo.ReconciliationStatus.Append.getCode());
        this.updateReconcilicationForPayable(payable, loginEmployee);
    }

    @Override
    public void submitToWorkFlow(ReconcilicationForPayableBo bo, LoginEmployee loginEmployee) throws BusinessException {
        if (bo == null || bo.getReconciliationIds() == null || bo.getReconciliationIds().isEmpty()) {
            return;
        }
        // 判断创建人与提交人是否一致，避免在提交工作流循环中抛错
        for (Integer id : bo.getReconciliationIds()) {

            ReconcilicationForPayable reconcilicationForPayable = reconcilicationForPayableMapper
                    .selectByPrimaryKey(id);
            if(reconcilicationForPayable == null){
                throw new BusinessException("reconcilicationNotExist", "对账单不存在");
            }
            if (reconcilicationForPayable.getCreateUserId().compareTo(loginEmployee.getUserId()) != 0) {
                throw new BusinessException("userIdNotMatch",
                        "承运商对账单{" + reconcilicationForPayable.getReconcilicationNo() + "}:创建人与提交审核人不一致");
            }

            //检查对账单中是否所有的承运商都存在扣款记录
            Integer vendorChargeNum = getVendorChargeNum(reconcilicationForPayable);
            this.checkVendorCharge(reconcilicationForPayable, vendorChargeNum);

            if (imageUploadManageService.listByRelationIdAndSign(id,
                    ImageUploadManage.ImageUploadManageSign.RECONCILIATION_FOR_PAYABLE.getCode()).isEmpty()) {
                throw new BusinessException("notUploadEvidence", "peconcilicationForPayable.error.notUploadEvidence",
                        reconcilicationForPayable.getReconcilicationNo());
            }
        }
        for (Integer id : bo.getReconciliationIds()) {
            ReconcilicationForPayable payable = new ReconcilicationForPayable();
            payable.setApprovalStatus(ReconcilicationForPayableBo.ReconciliationStatus.SUBMIT.getCode());
            payable.setReconcilicationId(id);
            ReconcilicationForPayable reconcilicationForPayable = reconcilicationForPayableMapper
                    .selectByPrimaryKey(id);
            if (reconcilicationForPayable == null) {
                continue;
            }

            // 工作流程API
            Map<String, Object> variables = new HashMap<String, Object>();
            variables.put("number", reconcilicationForPayable.getReconcilicationNo());
            //毛利率差  承诺毛利率 — 实际毛利率
            BigDecimal profitRateDiffer = calculateProfitRateDiffer(reconcilicationForPayable);
            if(profitRateDiffer != null){
                profitRateDiffer = profitRateDiffer.multiply(new BigDecimal(100.00));
                variables.put("profitRateDiffer", profitRateDiffer);
            }
            ProcessInstance ins = processService.startProcessInstance(WorkflowConstants.PROCESS_KEY_RECONCILICATION_PAYABLE, id + "", variables,
                    loginEmployee);
            payable.setProcessInstanceId(ins.getProcessInstanceId());
            payable.setSubmitUserId(loginEmployee.getUserId());
            payable.setSubmitTime(new Date());
            this.updateReconcilicationForPayable(payable, loginEmployee);
        }
    }

    /**
     * 计算应付对账单毛利率差
     * @param payable
     * @return
     */
    @Override
    public BigDecimal calculateProfitRateDiffer(ReconcilicationForPayable payable){
        BigDecimal sumEstimateFreight = BigDecimal.ZERO;
        BigDecimal vendorSettleFreight = BigDecimal.ZERO;
        BigDecimal profitRateDiffer = BigDecimal.ZERO;
        BigDecimal profitRate = null;

        ReconcilicationForPayableItemExample example = new ReconcilicationForPayableItemExample();
        Criteria criteria = example.createCriteria();
        criteria.andReconcilicationIdEqualTo(payable.getReconcilicationId());
        List<ReconcilicationForPayableItem> reconcilications = reconcilicationForPayableItemMapper.selectByExample(example);
        List<Integer> waybillIds = Lists.newArrayList();
        for (ReconcilicationForPayableItem reconcilication : reconcilications) {
            waybillIds.add(reconcilication.getWaybillId());
        }
        List<Waybill> waybills = waybillService.findByWaybillIds(waybillIds);
        //Map (运单id:承运商id)
        Map<Integer, Integer> vendorIdMap = Maps.newHashMap();
        List<Integer> vendorIds = Lists.newArrayList();
        buildVendorData(waybills, vendorIdMap, vendorIds);
        //Map (承运商id:承运商类型)
        Map<Integer, Byte> ownVendorMap = vmsCommonService.listVendorSource(vendorIds, VendorSourceEnum.SELF_SUPPORT.getCode());
        List<ReconcilicationForPayableItemBo> itemBos = Lists.newArrayList();
        buildItemBo(reconcilications, vendorIdMap, itemBos);
        for (ReconcilicationForPayableItemBo reconcilication : itemBos) {
            //排除自营承运商（计算毛利额、毛利率时需排除）
            if(null != ownVendorMap.get(reconcilication.getVendorId())){continue;}
            sumEstimateFreight = sumEstimateFreight.add(reconcilication.getReceivableWithTax() == null ? BigDecimal.ZERO : reconcilication.getReceivableWithTax());
            vendorSettleFreight = vendorSettleFreight.add(reconcilication.getSettleFreight() == null ? BigDecimal.ZERO : reconcilication.getSettleFreight());
        }

        //对账时毛利率
        if(sumEstimateFreight.compareTo(BigDecimal.ZERO) != 0){
            profitRate = (sumEstimateFreight.subtract(vendorSettleFreight)).divide(sumEstimateFreight, 4,BigDecimal.ROUND_HALF_UP);
        }

        //承诺毛利率
        Project project = projectService.getProjectV2(payable.getProjectId());
        BigDecimal profitRatePromise = (null == project ? null : project.getProfitRate());
        //毛利差
        if(null != profitRate && null != profitRatePromise){
            profitRateDiffer = profitRate.subtract(profitRatePromise);
        }else{
            profitRateDiffer = new BigDecimal(-5);
        }

        return profitRateDiffer;
    }

    /**
     * 检查对账单中是否所有的承运商都存在扣款记录
     * @param reconcilicationForPayable
     */
    private void checkVendorCharge(ReconcilicationForPayable reconcilicationForPayable, Integer vendorChargeNum){

        ReconciliationExtraForPayableExample example = new ReconciliationExtraForPayableExample();
        ReconciliationExtraForPayableExample.Criteria criteria = example.createCriteria();
        criteria.andReconciliationIdEqualTo(reconcilicationForPayable.getReconcilicationId());
        long chargeCount = reconciliationExtraForPayableMapper.countByExample(example);

        if(vendorChargeNum > chargeCount){
            throw new BusinessException("chargeCountIsError", "对账单{"+reconcilicationForPayable.getReconcilicationNo()+"}，还有承运商未确认扣款扣税，请仔细检查后再提价审核！");
        }
    }

    private Integer getVendorChargeNum(ReconcilicationForPayable reconcilicationForPayable){

		PageCondition pageCondition = new PageCondition();
		Map<String, Object> filters = new HashMap<>();
		filters.put("reconcilicationId", reconcilicationForPayable.getReconcilicationId());
		pageCondition.setFilters(filters);
		pageCondition.setPageNo(1);
		pageCondition.setPageSize(Integer.MAX_VALUE);
		List<ReconcilicationForPayableItem> rows = reconcilicationForPayableItemExtraMapper.itemSearch(pageCondition);

        Integer vendorChargeNum = 0;

        for(ReconcilicationForPayableItem item : rows){

            boolean isCharge = itemIsCharge(item);
            if(isCharge){
                vendorChargeNum = vendorChargeNum + 1;
            }
        }

        return vendorChargeNum;
    }

    /**
     * 对账单详情是否需要扣款
     * @param item
     * @return
     */
    private boolean itemIsCharge(ReconcilicationForPayableItem item){

        if (NumberUtils.compare(item.getSettleType(), PayableSettleAccountTypeEnum.DRIVER.getCode()) == 0) {
            return false;
        }

        Waybill w =  waybillCommonService.getWaybillById(item.getWaybillId());
        if (null == w || null == w.getVehicleToVendor()) {
            return false;
        }

        if (!w.getReceiveWay().equals(ReceiveWay.TRANSFORM_BILL.getCode())) {
            Vendor vendor = vmsCommonService.loadVendorByVendorId(w.getVehicleToVendor());
            if (null == vendor || null == vendor.getVendorSource() || vendor.getVendorSource()
                .equals(VendorSourceEnum.SELF_SUPPORT.getCode())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public TaskDetail getWorkflowElement(String taskId, LoginEmployee loginEmployee) {
        return taskService.findTaskDetail(taskId, loginEmployee);
    }

    private void updateReconcilicationForPayable(ReconcilicationForPayable payable, LoginEmployee loginEmployee) {
        reconcilicationForPayableMapper.updateByPrimaryKeySelective(payable);
    }

    @Override
    public Integer insert(ReconcilicationForPayable reconcilicationForPayable, LoginUser loginUser)
            throws BusinessException {
        reconcilicationForPayable.setTenantId(loginUser.getTenantId());
        reconcilicationForPayable.setTenantCode(loginUser.getTenantCode());
        reconcilicationForPayable.setSubmitUserId(loginUser.getUserId());
        reconcilicationForPayable.setCreateUserId(loginUser.getUserId());
        reconcilicationForPayable.setCreateTime(new Date());

        reconcilicationForPayableMapper.insert(reconcilicationForPayable);
        return reconcilicationForPayable.getReconcilicationId();
    }

    @Override
    public void insertItem(ReconcilicationForPayableItem reconcilicationForPayableItem, LoginUser loginUser)
            throws BusinessException {
        reconcilicationForPayableItemMapper.insert(reconcilicationForPayableItem);
    }

    @Override
    public void updateNameBySettleAccountIdAndType(String settleAccountName, Integer settleAccountId,
            Integer settleType) throws BusinessException {
        if (StringUtils.isBlank(settleAccountName) || null == settleAccountId || null == settleType) {
            return;
        }

        ReconcilicationForPayableItem item = new ReconcilicationForPayableItem();
        item.setSettleAccountName(settleAccountName);

        ReconcilicationForPayableItemExample example = new ReconcilicationForPayableItemExample();
        Criteria criteria = example.createCriteria();
        criteria.andSettleAccountIdEqualTo(settleAccountId);
        criteria.andSettleTypeEqualTo(settleType);
        reconcilicationForPayableItemMapper.updateByExampleSelective(item, example);
    }

    @Override
    public void updatePayableItemFee(Integer waybillId, BigDecimal settleFreight, BigDecimal driverTransportFee,
            BigDecimal temporaryTransportFee) throws BusinessException {
        if (null == waybillId) {
            return;
        }

        ReconcilicationForPayableItemExample example = new ReconcilicationForPayableItemExample();
        example.createCriteria().andWaybillIdEqualTo(waybillId);

        ReconcilicationForPayableItem item = new ReconcilicationForPayableItem();
        item.setSettleFreight(settleFreight);
        item.setDriverTransportFee(driverTransportFee);
        item.setTemporaryTransportFee(temporaryTransportFee);
        reconcilicationForPayableItemMapper.updateByExampleSelective(item, example);
    }

    @Override
    public void updateDepartmentId(ReconcilicationForPayable reconcilicationForPayable, LoginUser loginUser)
            throws BusinessException {
        if (null == reconcilicationForPayable.getReconcilicationId()) {
            throw new BusinessException("reconciliationIdNull", "errors.paramCanNotNullWithName", "对账单id");
        }
        if (null == reconcilicationForPayable.getDepartmentId()) {
            throw new BusinessException("departmentIdNull", "errors.paramCanNotNullAndSelect", "子公司");
        }
        // 插入更改日志
        ReconcilicationForPayableLog log = new ReconcilicationForPayableLog();
        log.setReconcilicationId(reconcilicationForPayable.getReconcilicationId());
        log.setCreateTime(new Date());
        log.setCreateUserId(loginUser.getUserId());
        ReconciliationLog content = new ReconciliationLog();
        content.setField("子公司");
        ReconcilicationForPayable payable = reconcilicationForPayableMapper
                .selectByPrimaryKey(reconcilicationForPayable.getReconcilicationId());
        if (null != payable.getDepartmentId()) {
            content.setOldValue(payable.getDepartmentId().toString());
            Department department = departmentService.loadDepartment(payable.getDepartmentId());
            content.setOldText(department == null ? "" : department.getBusinessLicenceName());
        }
        content.setNewValue(reconcilicationForPayable.getDepartmentId().toString());
        Department department = departmentService.loadDepartment(reconcilicationForPayable.getDepartmentId());
        content.setNewText(department == null ? "" : department.getBusinessLicenceName());
        log.setContent(JSONObject.toJSONString(content));
        reconcilicationForPayableLogMapper.insert(log);

        reconcilicationForPayableMapper.updateByPrimaryKeySelective(reconcilicationForPayable);
    }

    @Override
    public ReconcilicationForPayableBo getDepartmentName(ReconcilicationForPayable reconcilicationForPayable) {
        if (StringUtils.isBlank(reconcilicationForPayable.getReconcilicationNo())) {
            return null;
        }
        // 签约子公司
        ReconcilicationForPayableBo bo = new ReconcilicationForPayableBo();
        List<Waybill> waybills = waybillCommonService
                .findByReconciliationNo(reconcilicationForPayable.getReconcilicationNo());
        if (CollectionUtils.isNotEmpty(waybills)) {
            Integer departmentId = waybills.get(0).getPayToCompany();
            Department department = departmentService.loadDepartment(reconcilicationForPayable.getDepartmentId());
            bo.setSignedDepartmentName(department == null ? "" : department.getBusinessLicenceName());
        }
        return bo;
    }

    @Override
    public void sendToFMS(String reconcilicationNo, LoginEmployee loginEmployee) throws BusinessException {
        ReconcilicationForPayableExample example = new ReconcilicationForPayableExample();
        example.createCriteria().andReconcilicationNoEqualTo(reconcilicationNo);
        List<ReconcilicationForPayable> rows = reconcilicationForPayableMapper.selectByExample(example);
        if (rows.isEmpty() || rows.size() > 1) {
            throw new BusinessException("DataError", "一个对账单号查询出了多条记录或为空");
        }
        ReconcilicationForPayable payable = rows.get(0);

        //更新对账单审核状态
        payable.setApprovalStatus(ApprovalStatus.APPROVAL_AGREE.getCode());
        reconcilicationForPayableMapper.updateByPrimaryKeySelective(payable);
        // 将运单的对账状态更改为已对账
        updateWaybillByWorkFlow(payable);
        // 推送MQ消息给FMS
        log.info("payable send to FMS start");
        this.doSendWorkFlowPassMsgV2(payable, loginEmployee);
    }

    @Override
    public List<ReconcilicationForPayableItem> findItemByFilter(final com.juma.tgm.fms.domain.v3.vo.ReconciliationPayableItemFilter filter, final LoginEmployee loginEmployee) throws BusinessException {
        ReconcilicationForPayableItemExample example = new ReconcilicationForPayableItemExample();
        ReconcilicationForPayableItemExample.Criteria criteria = example.createCriteria();
        if( null == filter.getReconcilicationId() && null == filter.getWaybillId() && StringUtils.isBlank(filter.getWaybillNo())){
            return com.google.common.collect.Lists.newArrayList();
        }
        if( CollectionUtils.isEmpty(filter.getWaybillIds()) ){

        }
        if( null != filter.getReconcilicationId() ){
            criteria.andReconcilicationIdEqualTo(filter.getReconcilicationId());
        }
        if( null != filter.getWaybillId() ){
            criteria.andWaybillIdEqualTo(filter.getWaybillId());
        }
        if( StringUtils.isNotBlank(filter.getWaybillNo()) ){
            criteria.andWaybillNoEqualTo(filter.getWaybillNo());
        }
        return reconcilicationForPayableItemMapper.selectByExample(example);
    }

    @Override
    public void updateSettleFreight(Integer reconcilicationId, Integer adjustForWho, WaybillAmount waybillAmount) {
        ReconcilicationForPayableItem item = new ReconcilicationForPayableItem();
        item.setSettleFreight(waybillAmount.getLastVendorFreightWithTax());
        ReconcilicationForPayableItemExample example = new ReconcilicationForPayableItemExample();
        example.createCriteria().andReconcilicationIdEqualTo(reconcilicationId);
        reconcilicationForPayableItemMapper.updateByExampleSelective(item,example);
    }

    @Override
    public void resendMonthlyBill(MonthlyBillBo monthlyBillBo) {
        List<String> waybillNos = monthlyBillBo.getWaybillNos();
        FmsMQMessageDTO fmsMQMessageDTO = new FmsMQMessageDTO();
        fmsMQMessageDTO.setBusinessSystem(BusinessSystemEnum.TMS.getCode());

        if(Objects.equals(monthlyBillBo.getType(), IdGeneratorTable.IdType.AR.getTableName())){
            //应收
            for (String waybillNo : waybillNos) {
                Waybill waybill = waybillService.findWaybillByWaybillNo(waybillNo, null);
                if(null != waybill){
                    //组装基础数据
                    SourceLogisticsDTO dto = new SourceLogisticsDTO();
                    buildReceivableBasic(waybill, dto);
                    //组装明细
                    SourceLogisticsDetailDTO sourceDTO = buildReceivableDetail(waybill);
                    dto.getDetailList().add(sourceDTO);
                    fmsMQMessageDTO.setData(dto);
                    log.info("补发-应收-月账单 To FMS : {}", JSONObject.toJSONString(fmsMQMessageDTO));
                    sendReceivableService.send(fmsMQMessageDTO);
                }
            }
        }else if(Objects.equals(monthlyBillBo.getType(), IdGeneratorTable.IdType.AP.getTableName())){
            //应付
            for (String waybillNo : waybillNos) {
                Waybill waybill = waybillService.findWaybillByWaybillNo(waybillNo, null);
                if(null != waybill){
                    // 判断非转运单的承运商类型，非转运单的自营承运商不发送mq
                    if (!waybill.getReceiveWay().equals(ReceiveWay.TRANSFORM_BILL.getCode())) {
                        Vendor vendor = vmsCommonService.loadVendorByVendorId(waybill.getVehicleToVendor());
                        if (null == vendor || null == vendor.getVendorSource() || vendor.getVendorSource()
                                .equals(VendorSourceEnum.SELF_SUPPORT.getCode())) {
                            continue;
                        }
                    }
                    //组装基础数据
                    SourceLogisticsDTO dto = new SourceLogisticsDTO();
                    buildPayableBasic(waybill, dto);
                    //组装明细
                    SourceLogisticsDetailDTO sourceDTO = buildPayableDetail(waybill);
                    dto.getDetailList().add(sourceDTO);
                    fmsMQMessageDTO.setData(dto);
                    log.info("补发-应付-月账单 To FMS : {}", JSONObject.toJSONString(fmsMQMessageDTO));
                    sendPayableService.send(fmsMQMessageDTO);
                }
            }

        }
    }

}
