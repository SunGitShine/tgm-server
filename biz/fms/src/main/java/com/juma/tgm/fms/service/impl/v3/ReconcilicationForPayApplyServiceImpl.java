package com.juma.tgm.fms.service.impl.v3;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.juma.auth.employee.domain.Department;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.employee.service.DepartmentService;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.common.id.IdGeneratorTable;
import com.juma.tgm.common.workflow.ApprovalStatus;
import com.juma.tgm.crm.domain.CustomerInfo;
import com.juma.tgm.crm.service.CustomerInfoService;
import com.juma.tgm.fms.dao.v3.ext.ReconcilicationForPayableExtMapper;
import com.juma.tgm.fms.domain.Reconciliation.ReconciliationStatus;
import com.juma.tgm.fms.domain.v3.AdjustForPayable;
import com.juma.tgm.fms.domain.v3.ReconcilicationForPayable;
import com.juma.tgm.fms.domain.v3.ReconcilicationForPayableItem;
import com.juma.tgm.fms.domain.v3.bo.ReconciliationWaybillDetailBo;
import com.juma.tgm.fms.domain.v3.bo.ReconcilicationForPayApply;
import com.juma.tgm.fms.domain.v3.bo.WaybillAdjustFrightForPayable;
import com.juma.tgm.fms.domain.v3.enums.PayableSettleAccountTypeEnum;
import com.juma.tgm.fms.domain.v3.vo.AdjustForMasterFilter;
import com.juma.tgm.fms.service.v3.AdjustForMasterAddService;
import com.juma.tgm.fms.service.v3.AdjustForPayableService;
import com.juma.tgm.fms.service.v3.ReconcilicationForPayApplyService;
import com.juma.tgm.fms.service.v3.ReconcilicationForPayableService;
import com.juma.tgm.id.service.IdGeneratorService;
import com.juma.tgm.project.domain.RoadMap;
import com.juma.tgm.project.service.ProjectService;
import com.juma.tgm.project.service.RoadMapService;
import com.juma.tgm.tools.service.VmsCommonService;
import com.juma.tgm.waybill.domain.TruckRequire;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillAmount;
import com.juma.tgm.waybill.domain.WaybillParam;
import com.juma.tgm.waybill.domain.vo.WaybillAmountFilter;
import com.juma.tgm.waybill.service.*;
import com.juma.vms.driver.domain.Driver;
import com.juma.vms.driver.enumeration.DriverTypeEnum;
import com.juma.vms.truck.domain.Truck;
import com.juma.vms.truck.enumeration.TruckRunTypeEnum;
import com.juma.vms.vendor.domain.Vendor;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

@Service
public class ReconcilicationForPayApplyServiceImpl implements ReconcilicationForPayApplyService {

    @Resource
    private ReconcilicationForPayableExtMapper reconcilicationForPayableExtMapper;
    @Resource
    private ReconcilicationForPayableService reconcilicationForPayableService;
    @Resource
    private WaybillService waybillService;
    @Resource
    private WaybillParamService waybillParamService;
    @Resource
    private WaybillCommonService waybillCommonService;
    @Resource
    private CustomerInfoService customerInfoService;
    @Resource
    private ProjectService projectService;
    @Resource
    private TruckRequireService truckRequireService;
    @Resource
    private MsgWithBusinessService msgWithBusinessService;
    @Resource
    private AdjustForPayableService adjustForPayableService;
    @Resource
    private IdGeneratorService idGeneratorService;
    @Resource
    private VmsCommonService vmsCommonService;
    @Resource
    private RoadMapService roadMapService;
    @Resource
    private DepartmentService departmentService;
    @Autowired
    private AdjustForMasterAddService adjustForMasterAddService;
    @Resource
    private WaybillAmountService waybillAmountService;

    @Override
    public Page<ReconcilicationForPayApply> search(PageCondition pageCondition, LoginUser loginUser) {
        if (null == loginUser || null == loginUser.getTenantId()) {
            return new Page<ReconcilicationForPayApply>(pageCondition.getPageNo(), pageCondition.getPageSize(), 0,
                    new ArrayList<ReconcilicationForPayApply>());
        }
        pageCondition.getFilters().put("tenantId", loginUser.getTenantId());
        pageCondition.getFilters().put("reconciliationStatus",
                Waybill.ReconciliationStatus.NOT_RECONCILIATION.getCode());
        pageCondition.getFilters().put("statusView", Waybill.StatusView.FINISH.getCode());
        int total = reconcilicationForPayableExtMapper.searchCount(pageCondition);
        List<ReconcilicationForPayApply> rows = reconcilicationForPayableExtMapper.search(pageCondition);
        for (ReconcilicationForPayApply apply : rows) {
            calculateWaybillAmount(apply, pageCondition, loginUser);
            try {
                Department department = departmentService.loadDepartment(apply.getDepartmentId());
                apply.setBusinessLicenceName(department == null ? "" : department.getBusinessLicenceName());
            } catch (Exception e) {

            }
        }

        return new Page<ReconcilicationForPayApply>(pageCondition.getPageNo(), pageCondition.getPageSize(), total,
                rows);
    }

    /**根据客户-项目-部门-支付公司-计算对应的运单求和金额**/
    private void calculateWaybillAmount(ReconcilicationForPayApply forPayApply, PageCondition pageCondition, LoginUser loginUser) {
        pageCondition.getFilters().put("customerId",forPayApply.getCustomerId());
        pageCondition.getFilters().put("projectId",forPayApply.getProjectId());
        pageCondition.getFilters().put("payToCompany",forPayApply.getDepartmentId());
        List<Waybill> waybills = reconcilicationForPayableExtMapper.selectByFilter(pageCondition);
        if( CollectionUtils.isEmpty(waybills) ){ return; }
        List<Integer> waybillIds = Lists.newArrayList();
        for (Waybill waybill : waybills){
            if( null == forPayApply.getProjectId() && null != waybill.getProjectId()){
                continue;
            }
            waybillIds.add(waybill.getWaybillId());
        }
        WaybillAmountFilter amountFilter = new WaybillAmountFilter();
        amountFilter.setWaybillIds(waybillIds);
        List<WaybillAmount> amounts = waybillAmountService.findByFilter(amountFilter,loginUser);
        Map<Integer,WaybillAmount> waybillAmountMap = Maps.newConcurrentMap();
        for (WaybillAmount amount : amounts){
            waybillAmountMap.put(amount.getWaybillId(),amount);
        }
        BigDecimal totalAmountWithTax = BigDecimal.ZERO;
        for (Waybill waybill : waybills){
            if( null == forPayApply.getProjectId() && null != waybill.getProjectId()){
                continue;
            }
            BigDecimal lastAmount = waybill.getShow4DriverFreight();
            WaybillAmount waybillAmount = waybillAmountMap.get(waybill.getWaybillId());
            if(null != waybillAmount){
                lastAmount = waybillAmount.getLastVendorFreightWithTax();
            }
            totalAmountWithTax = totalAmountWithTax.add(lastAmount);
        }
        forPayApply.setVendorAmountNotReconciled(totalAmountWithTax);
    }

    @Override
    public Page<ReconciliationWaybillDetailBo> searchWaybills(PageCondition pageCondition, LoginEmployee loginEmployee) {
        List<ReconciliationWaybillDetailBo> reconciliationWaybillDetailBos = new ArrayList<ReconciliationWaybillDetailBo>();
        if (null == pageCondition.getFilters() || null == pageCondition.getFilters().get("customerId")) {
            return new Page<>(pageCondition.getPageNo(), pageCondition.getPageSize(), 0,
                    reconciliationWaybillDetailBos);
        }
        if (null == pageCondition.getFilters() || null == pageCondition.getFilters().get("departmentId")) {
            throw new BusinessException("paramDepartmentIdIsNull", "errors.paramDepartmentIdIsNull");
        }
        //运作方
        pageCondition.getFilters().put("payToCompany",pageCondition.getFilters().get("departmentId"));
        pageCondition.getFilters().remove("departmentId");
        pageCondition.getFilters().put("reconciliationStatus",
                Waybill.ReconciliationStatus.NOT_RECONCILIATION.getCode());
        pageCondition.getFilters().put("statusView", Waybill.StatusView.FINISH.getCode());
        if (null == pageCondition.getFilters().get("projectId")
                || StringUtils.isBlank(pageCondition.getFilters().get("projectId").toString())) {
            pageCondition.getFilters().put("projectId", "ISNULL");
        }
        Page<Waybill> waybillPage = waybillService.search(loginEmployee, pageCondition);
        for (Waybill waybill : waybillPage.getResults()) {
            ReconciliationWaybillDetailBo bo = new ReconciliationWaybillDetailBo();
            WaybillAmount amount = waybillAmountService.loadByWaybillId(waybill.getWaybillId());
            waybill.setShow4DriverFreight(amount == null ? waybill.getShow4DriverFreight() : amount.getLastVendorFreightWithTax());

            waybill.setVehicleId(this.getVehicleId(waybill.getTruckId()));
            waybill.setAmsDriverId(this.getAmsDriverId(waybill.getDriverId()));
            RoadMap roadMap = roadMapService.getRoadMap(waybill.getRoadMapId());
            waybill.setRoadMapName(roadMap == null ? "" : roadMap.getName());
            bo.setWaybill(waybill);
            bo.setVendorOrDriverName(this.getVendorOrDriverName(waybill));
            bo.setWaybillParam(waybillParamService.findByWaybillId(waybill.getWaybillId()));
            if (null != waybill.getDriverType()) {
                bo.setDriverTypeName(DriverTypeEnum.getDescByCode(waybill.getDriverType()));
            }
            if (null != waybill.getVehicleType()) {
                bo.setVehicleTypeName(TruckRunTypeEnum.getDescByCode(waybill.getVehicleType()));
            }
            if (null != waybill.getReceivableReconcilicationStatus()) {
                com.juma.tgm.waybill.domain.Waybill.ReconciliationStatus reconciliationStatus = Waybill.ReconciliationStatus
                        .getReconciliationStatusByCode(waybill.getReceivableReconcilicationStatus());
                bo.setReceivableReconcilicationStatusDesc(
                        reconciliationStatus == null ? null : reconciliationStatus.getDescr());
            }

            //改价申请
            AdjustForMasterFilter filter = new AdjustForMasterFilter();
            filter.setWaybillId(waybill.getWaybillId());
            filter.setApprovalStatus(ApprovalStatus.APPROVAL_SUBMIT.getCode());
            bo.setHasAdjustApply(CollectionUtils.isEmpty(adjustForMasterAddService.findByFilter(filter, loginEmployee)) ? false : true);

            //客户侧税率
            TruckRequire truckRequire = truckRequireService.loadReuckRequireByWaybillId(waybill.getWaybillId());
            bo.setTaxRateValue(truckRequire == null ? null : truckRequire.getTaxRateValue());

            reconciliationWaybillDetailBos.add(bo);
        }

        return new Page<>(waybillPage.getPageNo(), waybillPage.getPageSize(), waybillPage.getTotal(),
                reconciliationWaybillDetailBos);
    }

    private String getVendorName(Waybill waybill) {
        Vendor vendor = vmsCommonService.loadVendorByVendorId(waybill.getVendorId());
        return vendor == null ? "" : vendor.getVendorName();
    }

    private String getVendorOrDriverName(Waybill waybill) {
        if (NumberUtils.compare(waybill.getReceiveWay(), Waybill.ReceiveWay.TRANSFORM_BILL.getCode()) == 0) {
            // 转运单
            return getVendorName(waybill);
        }

        if (null != waybill.getDriverType() && NumberUtils.compare(waybill.getDriverType(),
                DriverTypeEnum.OWN_SALE.getCode()) == 0) {
            // 自营司机
            return waybill.getDriverName();
        }

        // 非自营司机
        Vendor vendor = vmsCommonService.loadVendorByVendorId(waybill.getVehicleToVendor());
        if (null != vendor) {
            return vendor.getVendorName();
        }

        return null;
    }

    private Integer getVehicleId(Integer truckId) {
        if (null == truckId) {
            return null;
        }

        Truck truck = vmsCommonService.loadTruckByTruckId(truckId);
        if (null == truck) {
            return null;
        }

        return truck.getVehicleId();
    }

    private Integer getAmsDriverId(Integer driverId) {
        if (null == driverId) {
            return null;
        }

        Driver driver = vmsCommonService.loadDriverByDriverId(driverId);
        if (null == driver) {
            return null;
        }

        return driver.getAmsDriverId();
    }

    @Override
    public List<Waybill> listByWaybillIds(List<Integer> waybillIds, LoginUser loginUser) {
        List<Waybill> waybillList = new ArrayList<>();

        if (CollectionUtils.isEmpty(waybillIds)) {
            return waybillList;
        }

        Waybill waybill = null;
        for (Integer waybillId : waybillIds) {
            waybill = waybillCommonService.getWaybillById(waybillId);
            waybillList.add(waybill);
        }
        return waybillList;
    }

    @Override
    public void updateCostBatch(List<WaybillAdjustFrightForPayable> waybillAdjustFrightForPayables, LoginUser loginUser)
            throws BusinessException {
        for (WaybillAdjustFrightForPayable WaybillAdjustFrightForPayable : waybillAdjustFrightForPayables) {
            this.update(WaybillAdjustFrightForPayable, loginUser);
        }
    }

    private void update(WaybillAdjustFrightForPayable WaybillAdjustFrightForPayable, LoginUser loginUser)
            throws BusinessException {
        if (null == WaybillAdjustFrightForPayable.getWaybillId()) {
            return;
        }

        if (StringUtils.isBlank(WaybillAdjustFrightForPayable.getAdjustRemark())) {
            throw new BusinessException("adjustRemarkRequired",
                    "waybillAdjustFrightForPayable.error.adjustRemarkRequired");
        }

        Waybill waybill = waybillService.getWaybillAndCheckExist(WaybillAdjustFrightForPayable.getWaybillId());

        // 原司机搬运费
        BigDecimal oldShow4DriverFreight = waybill.getShow4DriverFreight();
        // 改价前校验运单信息
        checkWaybillBeforeUpadteFreight(waybill);

        // 费用合法性校验
        checkFright(WaybillAdjustFrightForPayable.getShow4DriverFreight(), "承运商结算金额");
        checkFright(WaybillAdjustFrightForPayable.getDriverHandlingCost(), "司机搬运费");
        checkFright(WaybillAdjustFrightForPayable.getLaborerHandlingCost(), "小工搬运费");

        // 调整记录数据组装
        AdjustForPayable adjust = new AdjustForPayable();
        adjust.setWaybillId(waybill.getWaybillId());
        adjust.setWaybillNo(waybill.getWaybillNo());
        adjust.setPayableWithTax(oldShow4DriverFreight == null ? BigDecimal.ZERO : oldShow4DriverFreight);
        adjust.setPayableWithTaxAdjust(WaybillAdjustFrightForPayable.getShow4DriverFreight() == null ? BigDecimal.ZERO
                : WaybillAdjustFrightForPayable.getShow4DriverFreight());
        adjust.setDriverTransportFeeAdjust(WaybillAdjustFrightForPayable.getDriverHandlingCost() == null
                ? BigDecimal.ZERO : WaybillAdjustFrightForPayable.getDriverHandlingCost());
        adjust.setTemporaryTransportFeeAdjust(WaybillAdjustFrightForPayable.getLaborerHandlingCost() == null
                ? BigDecimal.ZERO : WaybillAdjustFrightForPayable.getLaborerHandlingCost());
        adjust.setAdjustRemark(WaybillAdjustFrightForPayable.getAdjustRemark());

        // 修改搬运费
        updateHandlingCost(WaybillAdjustFrightForPayable, adjust, loginUser);

        // 修改运单信息
        waybill.setUpdateFreightRemark(WaybillAdjustFrightForPayable.getAdjustRemark());
        waybill.setShow4DriverFreight(WaybillAdjustFrightForPayable.getShow4DriverFreight() == null ? BigDecimal.ZERO
                : WaybillAdjustFrightForPayable.getShow4DriverFreight());
        waybillCommonService.update(waybill, loginUser);

        // 修改应付对账单详情里的费用信息
        reconcilicationForPayableService.updatePayableItemFee(waybill.getWaybillId(), waybill.getShow4DriverFreight(),
                WaybillAdjustFrightForPayable.getDriverHandlingCost(),
                WaybillAdjustFrightForPayable.getLaborerHandlingCost());

        // 通知司机：司机结算价改变
        if ("是".equals(WaybillAdjustFrightForPayable.getNoticeToDriver())
                && waybill.getShow4DriverFreight().compareTo(oldShow4DriverFreight) != 0) {
            msgWithBusinessService.pushChangePriceMsgToDriver(waybill.getWaybillId(), oldShow4DriverFreight);
        }

        // 应付调价记录
        adjustForPayableService.insert(adjust, loginUser);
    }

    // 改价前校验运单信息
    private void checkWaybillBeforeUpadteFreight(Waybill waybill) {
        // 只有已完成的运单才可以改价
        if (NumberUtils.compare(Waybill.StatusView.FINISH.getCode(), waybill.getStatusView()) != 0) {
            throw new BusinessException("waybillIsNotFinish", "waybill.error.waybillIsNotFinish");
        }

        // 有改价待审核的运单不能改价
        if (null != waybill.getUpdateFreightAuditStatus()
                && NumberUtils.compare(Waybill.UpdateFreightAuditStatus.WATING_AUDIT.getCode(),
                        waybill.getUpdateFreightAuditStatus()) == 0) {
            throw new BusinessException("waybillIsAuditStatus", "waybill.error.waybillIsAuditStatus");
        }

        // 对账完成的运单不能改价
        if (NumberUtils.compare(Waybill.ReconciliationStatus.HAS_RECONCILIATION.getCode(),
                waybill.getReconciliationStatus()) == 0) {
            throw new BusinessException("waybillHasReconciliation", "waybill.error.waybillHasReconciliation");
        }
    }

    // 校验费用
    private void checkFright(BigDecimal freight, String freightName) {
        if (null == freight) {
            return;
        }

        if (freight.compareTo(BigDecimal.ZERO) == -1) {
            throw new BusinessException("freightMustNotLessThanZero", "waybill.error.freightMustNotLessThanZero", freightName);
        }

        if (freight.compareTo(new BigDecimal("999999.99")) == 1) {
            throw new BusinessException("freightTooMax", "waybill.error.freightTooMax");
        }
    }

    // 修改搬运费
    private void updateHandlingCost(WaybillAdjustFrightForPayable WaybillAdjustFrightForPayable,
            AdjustForPayable adjust, LoginUser loginUser) {
        WaybillParam param = waybillParamService.findByWaybillId(WaybillAdjustFrightForPayable.getWaybillId());
        adjust.setDriverTransportFee(
                param.getDriverHandlingCost() == null ? BigDecimal.ZERO : param.getDriverHandlingCost());
        adjust.setTemporaryTransportFee(WaybillAdjustFrightForPayable.getLaborerHandlingCost() == null ? BigDecimal.ZERO
                : param.getLaborerHandlingCost());

        WaybillParam waybillParam = new WaybillParam();
        waybillParam.setWaybillId(WaybillAdjustFrightForPayable.getWaybillId());
        waybillParam.setDriverHandlingCost(WaybillAdjustFrightForPayable.getDriverHandlingCost() == null
                ? BigDecimal.ZERO : WaybillAdjustFrightForPayable.getDriverHandlingCost());
        waybillParam.setLaborerHandlingCost(WaybillAdjustFrightForPayable.getLaborerHandlingCost() == null
                ? BigDecimal.ZERO : WaybillAdjustFrightForPayable.getLaborerHandlingCost());
        waybillParamService.addOrUpdateOnly(waybillParam, loginUser);
    }

    @Override
    public String createReconcilication(List<Integer> waybillIds, LoginUser loginUser) throws BusinessException {
        // 如果 没有 运单id 则 忽略
        if (CollectionUtils.isEmpty(waybillIds)) {
            throw new BusinessException("waybills", "errors.reconciliation.waybills");
        }

        List<Waybill> waybills = this.listByWaybillIds(waybillIds, loginUser);
        if (waybills.isEmpty()) {
            return null;
        }

        // 校验
        String plateNumbers = checkWaybills(waybills, loginUser);
        if (StringUtils.isNotBlank(plateNumbers)) {
            throw new BusinessException("vehicleToVendorIsNull", "errors.reconciliation.vehicleToVendorIsNull",
                    plateNumbers);
        }
        // 对账单号
        String reconcilicationNo = idGeneratorService.genId(IdGeneratorTable.IdType.AP);
        Map<Integer,WaybillParam> waybillParamMap = Maps.newConcurrentMap();
        Map<Integer, WaybillAmount> waybillAmountMap = Maps.newConcurrentMap();
        // 组装对账单数据
        ReconcilicationForPayable reconcilicationForPayable = this.buildReconcilicationForPayable(waybills, waybillParamMap,waybillAmountMap);
        reconcilicationForPayable.setReconcilicationNo(reconcilicationNo);
        // 生成一个对账单
        Integer reconcilicationId = reconcilicationForPayableService.insert(reconcilicationForPayable, loginUser);
        // 对账单详情
        this.addReconcilicationForPayableItem(waybills, reconcilicationId, waybillParamMap, waybillAmountMap, loginUser);
        // 更新运单的 对账单号
        setReconciliationNoToWaybills(reconcilicationNo, waybills, loginUser);
        return reconcilicationNo;
    }

    // 如果 检查通过 则返回对账单 的类型
    private String checkWaybills(List<Waybill> waybills, LoginUser loginUser) throws BusinessException {
        Set<Integer> customerIdSet = new HashSet<Integer>();
        Set<Integer> projectIdSet = new HashSet<Integer>();
        boolean checkIsReceivableFirst = false;
        Set<String> plateNumbers = new HashSet<>();
        //税率
        Set<BigDecimal> taxRates = new HashSet<>();

        for (Waybill waybill : waybills) {
            // 判断这些运单是否属于该租户
            if (!loginUser.getTenantId().equals(waybill.getTenantId())) {
                throw new BusinessException("waybill permission refusal", "errors.reconciliation.waybills.authority",
                        waybill.getWaybillNo());
            }

            if (null == waybill.getPayToCompany() || NumberUtils.compare(waybill.getPayToCompany(), 0) == 0) {
                throw new BusinessException("departmentRequired", "errors.reconciliation.payToCompanyRequired",
                        waybill.getWaybillNo());
            }

            // 判断 不为空 则这个运单已经生成过对账单
            if (StringUtils.isNotBlank(waybill.getReconciliationNo())) {
                throw new BusinessException("waybill", "errors.reconciliation.repeat", waybill.getWaybillNo());
            }

            // 容错检测customerId 是否为null
            if (waybill.getCustomerId() == null) {
                throw new BusinessException("customerId", "errors.paramCanNotNullWithName", "运单客户id");
            }

            customerIdSet.add(waybill.getCustomerId());
            // 如果customerIdSet的size大于1就抛出异常，证明这一批 不是一个 客户的
            if (customerIdSet.size() > 1) {
                Object[] array = customerIdSet.toArray();
                throw new BusinessException("customerId", "errors.reconciliation.customer",
                        "{" + array[0] + "," + array[1] + "}");
            }

            projectIdSet.add(waybill.getProjectId());
            // 如果projectIdSet的size大于1就抛出异常，证明这一批 不是一个 项目的
            if (projectIdSet.size() > 1) {
                Object[] array = projectIdSet.toArray();
                throw new BusinessException("projectId", "errors.reconciliation.project",
                        "{" + array[0] + "," + array[1] + "}");
            }

            // 校验是否首先客户对账
            if (null != waybill.getProjectId() && NumberUtils.compare(waybill.getReceivableReconcilicationStatus(),
                    Waybill.ReconciliationStatus.HAS_RECONCILIATION.getCode()) != 0) {
                if (!checkIsReceivableFirst) {
                    checkIsReceivableFirst = projectService.checkIsReceivableFirst(waybill.getProjectId());
                }

                if (checkIsReceivableFirst) {
                    throw new BusinessException("needReceivableFirst", "errors.needReceivableFirst",
                            waybill.getWaybillNo());
                }
            }

            // 非转运单：验证非自营司机是否关联了承运商
            if (NumberUtils.compare(waybill.getReceiveWay(), Waybill.ReceiveWay.TRANSFORM_BILL.getCode()) != 0) {
                if (null == waybill.getDriverType()) {
                    throw new BusinessException("driverTypeIsnull", "waybill.error.driverTypeIsnull",
                            waybill.getWaybillNo());
                }

                if (NumberUtils.compare(waybill.getDriverType(), DriverTypeEnum.OWN_SALE.getCode()) != 0
                        && (null == waybill.getVehicleToVendor()
                                || NumberUtils.compare(waybill.getVehicleToVendor(), 0) != 1)) {
                    plateNumbers.add(waybill.getPlateNumber());
                }
            }

            TruckRequire truckRequire = truckRequireService.loadReuckRequireByWaybillId(waybill.getWaybillId());
            if(truckRequire != null){
                taxRates.add(truckRequire.getTaxRateValue());
            }
        }

        //所选运单税率必须一致
        if(taxRates.size() > 1){
            throw new BusinessException("taxRateNotSame", "您所勾选的运单，税率不一致，不能生产对账单，请仔细检查，如需调整运单税率，请向大区或总部申请");
        }

        if (projectIdSet.size() > 0) {
            Object[] array = projectIdSet.toArray();
            if (null != array[0]) {
                projectService.checkIsReceivableFirst(Integer.valueOf(array[0].toString()));
            }
        }

        if (plateNumbers.isEmpty()) {
            return null;
        }

        StringBuffer sf = new StringBuffer();
        for (String plateNumber : plateNumbers) {
            sf.append(plateNumber).append("、");
        }
        return sf.length() > 0 ? sf.deleteCharAt(sf.length() - 1).toString() : null;
    }

    private ReconcilicationForPayable buildReconcilicationForPayable(List<Waybill> waybills,
            Map<Integer, WaybillParam> waybillParamMap, Map<Integer, WaybillAmount> waybillAmountMap) {
        ReconcilicationForPayable rfp = new ReconcilicationForPayable();
        rfp.setCustomerId(waybills.get(0).getCustomerId());
        rfp.setCustomerName(waybills.get(0).getCustomerName());
        rfp.setProjectId(waybills.get(0).getProjectId());
        rfp.setProjectName(waybills.get(0).getProjectName());
        // 获取项目运营主体
        rfp.setPayToCompanyCreditCode(
            waybills.get(0).getPayToCompanyCreditCode() == null ? "" : waybills.get(0).getPayToCompanyCreditCode());
        rfp.setDepartmentId(waybills.get(0).getPayToCompany());
        rfp.setApprovalStatus(ReconciliationStatus.Append.getCode());

        // 获取客户信息，组装业务区域
        CustomerInfo customerInfo = customerInfoService.findCusInfoById(rfp.getCustomerId());
        if (null == customerInfo) {
            throw new BusinessException("customerInWaybillNotFind",
                    "reconcilicationForPayable.error.customerInWaybillNotFind");
        }
        rfp.setAreaCode(customerInfo.getAreaCode());

        BigDecimal payableWithTax = BigDecimal.ZERO;
        BigDecimal driverTransportFee = BigDecimal.ZERO;
        BigDecimal temporaryTransportFee = BigDecimal.ZERO;
        List<Integer> waybillIds = Lists.newArrayList();
        for (Waybill waybill : waybills) {
            waybillIds.add(waybill.getWaybillId());
        }

        List<WaybillAmount> waybillAmounts = waybillAmountService.findByWaybillIds(waybillIds,null);
        for (WaybillAmount waybillAmount : waybillAmounts){
            waybillAmountMap.put(waybillAmount.getWaybillId(),waybillAmount);
        }

        List<WaybillParam> waybillParams = waybillParamService.findByWaybillIds(waybillIds);
        for (WaybillParam waybillParam : waybillParams){
            waybillParamMap.put(waybillParam.getWaybillId(),waybillParam);
        }

        for (Waybill waybill : waybills) {
            WaybillAmount amount = waybillAmountMap.get(waybill.getWaybillId());
            if (null != amount) {
                waybill.setShow4DriverFreight(amount.getLastVendorFreightWithTax());
            }

            payableWithTax = payableWithTax
                    .add(waybill.getShow4DriverFreight() == null ? BigDecimal.ZERO : waybill.getShow4DriverFreight());
            WaybillParam waybillParam = waybillParamMap.get(waybill.getWaybillId());
            if (null == waybillParam) {
                continue;
            }

            driverTransportFee = driverTransportFee.add(waybillParam.getDriverHandlingCost() == null ? BigDecimal.ZERO
                    : waybillParam.getDriverHandlingCost());
            temporaryTransportFee = temporaryTransportFee.add(waybillParam.getLaborerHandlingCost() == null
                    ? BigDecimal.ZERO : waybillParam.getLaborerHandlingCost());
        }

        rfp.setPayableWithTax(payableWithTax);
        rfp.setDriverTransportFee(driverTransportFee);
        rfp.setTemporaryTransportFee(temporaryTransportFee);

        return rfp;
    }

    // 创建应付账单详情
    private void addReconcilicationForPayableItem(List<Waybill> waybills, Integer reconcilicationId,
            Map<Integer, WaybillParam> paramMap, Map<Integer, WaybillAmount> waybillAmountMap, LoginUser loginUser) {
        ReconcilicationForPayableItem item = new ReconcilicationForPayableItem();
        item.setReconcilicationId(reconcilicationId);
        List<Integer> waybillIds = Lists.newArrayList();
        for (Waybill waybill : waybills) {
            waybillIds.add(waybill.getWaybillId());
        }

        List<TruckRequire> truckRequires = truckRequireService.findByWaybillIds(waybillIds);
        Map<Integer,TruckRequire> truckRequireMap = Maps.newConcurrentMap();
        for (TruckRequire truckRequire : truckRequires){
            truckRequireMap.put(truckRequire.getWaybillId(),truckRequire);
        }

        for (Waybill waybill : waybills) {
            item.setWaybillId(waybill.getWaybillId());
            item.setWaybillNo(waybill.getWaybillNo());
            item.setSettleFreight(waybill.getShow4DriverFreight());
            item.setSettleStatus(waybill.getSettlementStatus());
            item.setPlateNumber(waybill.getPlateNumber());
            Truck truck = vmsCommonService.loadTruckByTruckId(waybill.getTruckId());
            if (null != truck) {
                item.setVehicleFrameNo(truck.getTruckIdentificationNo());
            }

            // 搬运费
            WaybillParam waybillParam = paramMap.get(waybill.getWaybillId());
            if (null != waybillParam) {
                item.setDriverTransportFee(waybillParam.getDriverHandlingCost());
                item.setTemporaryTransportFee(waybillParam.getLaborerHandlingCost());
            }

            // 转运单，使用vendorId
            if (NumberUtils.compare(waybill.getReceiveWay(), Waybill.ReceiveWay.TRANSFORM_BILL.getCode()) == 0) {
                if (null == waybill.getVendorId()) {
                    throw new BusinessException("waybillNoVendor", "reconcilicationForPayApply.error.waybillNoVendor",
                            waybill.getWaybillNo());
                }
                item.setSettleType(PayableSettleAccountTypeEnum.VENDOR.getCode());
                item.setSettleAccountId(waybill.getVendorId());
                item.setSettleAccountName(getVendorName(waybill));
            } else {
                // 非转运单
                if (null != waybill.getDriverType() && NumberUtils.compare(waybill.getDriverType(),
                        DriverTypeEnum.OWN_SALE.getCode().intValue()) == 0) {
                    item.setSettleType(PayableSettleAccountTypeEnum.DRIVER.getCode());
                    item.setSettleAccountId(waybill.getDriverId());
                    item.setSettleAccountName(waybill.getDriverName());
                } else {
                    if (null == waybill.getVehicleToVendor()) {
                        throw new BusinessException("waybillNoVendor",
                                "reconcilicationForPayApply.error.waybillNoVendor", waybill.getWaybillNo());
                    }
                    item.setSettleType(PayableSettleAccountTypeEnum.VENDOR.getCode());
                    item.setSettleAccountId(waybill.getVehicleToVendor());
                    Vendor vendor = vmsCommonService.loadVendorByVendorId(waybill.getVehicleToVendor());
                    if (null != vendor) {
                        item.setSettleAccountName(vendor.getVendorName());
                    }
                }
            }

            item.setRebateFee(waybill.getRebateFee());
            TruckRequire truckRequire = truckRequireMap.get(waybill.getWaybillId());
            if (null != truckRequire) {
                item.setTaxRate(truckRequire.getTaxRateValue());
            }

            WaybillAmount amount = waybillAmountMap.get(waybill.getWaybillId());
            item.setReceivableWithTax(null == amount ? null : amount.getLastCustomerFreightWithTax());

            // 添加
            reconcilicationForPayableService.insertItem(item, loginUser);
        }
    }

    // 更新运单中的对账单号
    private void setReconciliationNoToWaybills(String reconciliationNo, List<Waybill> waybills, LoginUser loginUser) {
        for (Waybill waybill : waybills) {
            Waybill w = new Waybill();
            w.setIsSubmitMatch(null);
            w.setWaybillId(waybill.getWaybillId());
            w.setReconciliationNo(reconciliationNo);
            // 生成对账单后运单对账状态为对账中
            w.setReconciliationStatus(Waybill.ReconciliationStatus.IN_THE_ACCOUNT.getCode());
            waybillCommonService.update(w, loginUser);
        }
    }

    @Override
    public WaybillAdjustFrightForPayable findupdateFrightDetails(Integer waybillId, LoginUser loginUser)
            throws BusinessException {
        if (null == waybillId) {
            return null;
        }

        Waybill waybill = waybillCommonService.getWaybillById(waybillId);
        WaybillParam waybillParam = waybillParamService.findByWaybillId(waybillId);
        WaybillAdjustFrightForPayable waybillAdjustFright = new WaybillAdjustFrightForPayable(waybill, waybillParam);
        // 获取改价记录
        AdjustForPayable adjustForPayable = adjustForPayableService.findFirstByWaybillId(waybillId);
        if (null != adjustForPayable) {
            waybillAdjustFright.setInitShow4DriverFreight(adjustForPayable.getPayableWithTax());
            waybillAdjustFright.setInitDriverHandlingCost(adjustForPayable.getDriverTransportFee());
            waybillAdjustFright.setInitLaborerHandlingCost(adjustForPayable.getTemporaryTransportFee());
        }

        return waybillAdjustFright;
    }

}
