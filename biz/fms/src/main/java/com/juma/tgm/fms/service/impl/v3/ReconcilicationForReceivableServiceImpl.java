package com.juma.tgm.fms.service.impl.v3;

import com.alibaba.fastjson.JSONObject;
import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.juma.auth.conf.domain.BusinessArea;
import com.juma.auth.employee.domain.Department;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.employee.service.DepartmentService;
import com.juma.auth.user.domain.LoginUser;
import com.juma.auth.user.domain.User;
import com.juma.auth.user.service.UserService;
import com.juma.crm.support.domain.CustomerInfoBo;
import com.juma.crm.support.service.CrmService;
import com.juma.fms.v2.common.*;
import com.juma.fms.v2.core.payment.payable.domain.PayableMessage;
import com.juma.fms.v2.mq.dto.FmsMQMessageDTO;
import com.juma.fms.v2.source.logistics.dto.SourceLogisticsDTO;
import com.juma.fms.v2.source.logistics.dto.SourceLogisticsDetailDTO;
import com.juma.tgm.common.BaseUtil;
import com.juma.tgm.common.DateUtils;
import com.juma.tgm.common.id.IdGeneratorTable;
import com.juma.tgm.common.workflow.ApprovalStatus;
import com.juma.tgm.common.workflow.WorkflowConstants;
import com.juma.tgm.crm.domain.CustomerInfo;
import com.juma.tgm.crm.service.CustomerInfoService;
import com.juma.tgm.fms.dao.v3.ReconcilicationForCompanyMapper;
import com.juma.tgm.fms.dao.v3.ReconcilicationForReceivableItemMapper;
import com.juma.tgm.fms.dao.v3.ReconcilicationForReceivableMapper;
import com.juma.tgm.fms.dao.v3.ext.AdjustForReceivableExtraMapper;
import com.juma.tgm.fms.dao.v3.ext.ReconcilicationForReceivableExtraMapper;
import com.juma.tgm.fms.dao.v3.ext.ReconcilicationForReceivableItemExtraMapper;
import com.juma.tgm.fms.domain.Reconciliation;
import com.juma.tgm.fms.domain.v3.*;
import com.juma.tgm.fms.domain.v3.bo.ReconcilicationForReceivableItemBo;
import com.juma.tgm.fms.domain.v3.enums.AdjustMasterType;
import com.juma.tgm.fms.domain.v3.enums.AdjustType;
import com.juma.tgm.fms.domain.v3.enums.ReconcilicationForReceivableEnum;
import com.juma.tgm.fms.domain.v3.vo.*;
import com.juma.tgm.fms.service.v3.*;
import com.juma.tgm.id.service.IdGeneratorService;
import com.juma.tgm.imageUploadManage.domain.ImageUploadManage;
import com.juma.tgm.imageUploadManage.service.ImageUploadManageService;
import com.juma.tgm.mq.domain.CustomerInvoiceEvent;
import com.juma.tgm.mq.domain.CustomerInvoiceWaybill;
import com.juma.tgm.mq.domain.WorkFlowMessage;
import com.juma.tgm.mq.service.MqService;
import com.juma.tgm.project.domain.RoadMap;
import com.juma.tgm.project.domain.v2.Project;
import com.juma.tgm.project.service.ProjectService;
import com.juma.tgm.project.service.RoadMapService;
import com.juma.tgm.tools.service.AuthCommonService;
import com.juma.tgm.tools.service.CrmCommonService;
import com.juma.tgm.tools.service.VmsCommonService;
import com.juma.tgm.truck.service.TruckTypeFreightService;
import com.juma.tgm.waybill.domain.TruckRequire;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillAmount;
import com.juma.tgm.waybill.domain.WaybillOperateTrack;
import com.juma.tgm.waybill.domain.vo.TruckRequireFilter;
import com.juma.tgm.waybill.domain.vo.WaybillAmountFilter;
import com.juma.tgm.waybill.enumeration.WaybillOperateTrackEnum.OperateApplication;
import com.juma.tgm.waybill.enumeration.WaybillOperateTrackEnum.OperateType;
import com.juma.tgm.waybill.service.*;
import com.juma.vms.driver.enumeration.DriverTypeEnum;
import com.juma.vms.truck.domain.Truck;
import com.juma.vms.truck.enumeration.TruckRunTypeEnum;
import com.juma.vms.vendor.domain.Vendor;
import com.juma.vms.vendor.enumeration.VendorSourceEnum;
import com.juma.workflow.core.domain.ProcessInstance;
import com.juma.workflow.core.service.ProcessService;
import com.juma.workflow.core.service.TaskService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
public class ReconcilicationForReceivableServiceImpl implements ReconcilicationForReceivableService {

    private final static Logger log = LoggerFactory.getLogger("monthlyBillAppender");

    @Autowired
    private AdjustForReceivableExtraMapper adjustForReceivableMapper;

    @Autowired
    private ReconcilicationForReceivableExtraMapper reconcilicationForReceivableExtraMapper;

    @Autowired
    private ReconcilicationForReceivableMapper reconcilicationForReceivableMapper;

    @Autowired
    private ReconcilicationForReceivableItemExtraMapper reconcilicationForReceivableItemExtraMapper;

    @Autowired
    private ReconcilicationForReceivableItemMapper reconcilicationForReceivableItemMapper;

    @Autowired
    private ReconciliationBaseService reconciliationBaseService;

    @Autowired
    private WaybillService waybillService;

    @Autowired
    private TruckRequireService truckRequireService;

    @Autowired
    private TruckTypeFreightService truckTypeFreightService;

    @Autowired
    private MsgWithBusinessService msgWithBusinessService;

    @Autowired
    private WaybillCommonService waybillCommonService;

    @Autowired
    private WaybillOperateTrackService waybillOperateTrackService;

    @Autowired
    private MqService mqService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProcessService processService;

    @Autowired
    private IdGeneratorService idGeneratorService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private SendReceivableService sendService;

    @Autowired
    private ImageUploadManageService imageUploadManageService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private CustomerInfoService customerInfoService;

    @Resource
    private VmsCommonService vmsCommonService;

    @Resource
    private RoadMapService roadMapService;

    @Resource
    private ReconcilicationForCompanyMapper reconcilicationForCompanyMapper;

    @Resource
    private CrmService crmService;

    @Resource
    private AdjustForMasterAddService adjustForMasterAddService;

    @Resource
    private WaybillAmountService waybillAmountService;

    @Resource
    private ReconciliationCommonService reconciliationCommonService;

    @Resource
    private AdjustForMasterService adjustForMasterService;

    @Resource
    private AdjustForItemService adjustForItemService;

    @Resource
    private AuthCommonService authCommonService;

    @Resource
    private CrmCommonService crmCommonService;

    @Autowired
    private ReconcilicationForPayableService reconcilicationForPayableService;


    @Override
    public Page<ReceivableApplyVo> findReceivableApplyPage(PageCondition pageCondition, LoginUser loginUser) {

        // 查询未对账的数据
        pageCondition.getFilters().put("receivableReconcilicationStatus",
                Waybill.ReconciliationStatus.NOT_RECONCILIATION.getCode());
        pageCondition.getFilters().put("statusView", Waybill.StatusView.FINISH.getCode());
        pageCondition.getFilters().put("receivableReconcilicationNo", "ISNULL");
        pageCondition.getFilters().put("tenantId", loginUser.getTenantId());

        Page<ReceivableApplyVo> page = new Page<>(pageCondition.getPageNo(), pageCondition.getPageSize(), 0);

        Integer receivableApplyCount = reconciliationBaseService.findReceivableApplyCount(pageCondition);
        if (receivableApplyCount == null || receivableApplyCount.equals(0)) {
            return page;
        }

        List<ReceivableApplyVo> receivableApplyBos = reconciliationBaseService.findReceivableApplyPage(pageCondition);
        for (ReceivableApplyVo receivableApplyVo : receivableApplyBos) {
            // 根据客户-项目-部门-支付公司-计算对应的运单求和金额
            calculateWaybillAmount(receivableApplyVo, pageCondition, loginUser);

            Department contractToCompany = departmentService.loadDepartment(receivableApplyVo.getDepartmentId());
            receivableApplyVo.setDepartmentName(contractToCompany == null ? "" : contractToCompany.getBusinessLicenceName());

            Department payToCompany = departmentService.loadDepartment(receivableApplyVo.getPayToCompany());
            receivableApplyVo.setPayToCompanyName(payToCompany == null ? "" : payToCompany.getBusinessLicenceName());

        }
        page.setResults(receivableApplyBos);
        page.setTotal(receivableApplyCount);
        return page;
    }

    /**根据客户-项目-部门-支付公司-计算对应的运单求和金额**/
    private void calculateWaybillAmount(ReceivableApplyVo receivableApplyVo, PageCondition pageCondition, LoginUser loginUser) {
        pageCondition.getFilters().put("customerId",receivableApplyVo.getCustomerId());
        pageCondition.getFilters().put("projectId",receivableApplyVo.getProjectId());
        pageCondition.getFilters().put("departmentId",receivableApplyVo.getDepartmentId());
        pageCondition.getFilters().put("payToCompany",receivableApplyVo.getPayToCompany());
        List<Waybill> waybills = reconciliationBaseService.findWaybillByFilter(pageCondition);
        if( CollectionUtils.isEmpty(waybills) ){ return; }
        List<Integer> waybillIds = Lists.newArrayList();
        for (Waybill waybill : waybills){
            if( null == receivableApplyVo.getProjectId() && null != waybill.getProjectId()){
                continue;
            }
            waybillIds.add(waybill.getWaybillId());
        }
        TruckRequireFilter truckRequireFilter = new TruckRequireFilter();
        truckRequireFilter.setWaybillIds(waybillIds);
        List<TruckRequire> requires = truckRequireService.findByFilter(truckRequireFilter,loginUser);
        Map<Integer,TruckRequire> requireMap = Maps.newConcurrentMap();
        for (TruckRequire require : requires){
            requireMap.put(require.getWaybillId(),require);
        }
        WaybillAmountFilter amountFilter = new WaybillAmountFilter();
        amountFilter.setWaybillIds(waybillIds);
        List<WaybillAmount> amounts = waybillAmountService.findByFilter(amountFilter,loginUser);
        Map<Integer,WaybillAmount> waybillAmountMap = Maps.newConcurrentMap();
        for (WaybillAmount amount : amounts){
            waybillAmountMap.put(amount.getWaybillId(),amount);
        }
        BigDecimal totalAmountWithTax = BigDecimal.ZERO;
        BigDecimal totalAmountWithOutTax = BigDecimal.ZERO;
        for (Waybill waybill : waybills){
            if( null == receivableApplyVo.getProjectId() && null != waybill.getProjectId()){
                continue;
            }
            BigDecimal lastAmount = waybill.getEstimateFreight();
            BigDecimal lastAmountWithOutTax = waybill.getAfterTaxFreight();
            WaybillAmount amount = waybillAmountMap.get(waybill.getWaybillId());
            if( null != amount ){
                lastAmount = amount.getLastCustomerFreightWithTax();
                TruckRequire require = requireMap.get(amount.getWaybillId());
                if( null != require && null != require.getTaxRateValue()){
                    BigDecimal taxRate = require.getTaxRateValue();
                    lastAmountWithOutTax = BaseUtil.calFreightWithNotTax(lastAmount,taxRate);
                }
            }
            totalAmountWithTax = totalAmountWithTax.add(lastAmount);
            totalAmountWithOutTax = totalAmountWithOutTax.add(lastAmountWithOutTax);
        }
        receivableApplyVo.setEstimateFreight(totalAmountWithTax);
        receivableApplyVo.setAfterTaxFreight(totalAmountWithOutTax);
    }

    @Override
    public Page<ReconciliationWaybillDetailVo> searchWaybills(LoginEmployee loginEmployee, PageCondition pageCondition)
            throws BusinessException {

        if (pageCondition.getFilters() == null || pageCondition.getFilters().get("customerId") == null) {
            throw new BusinessException("customerId", "errors.paramCanNotNull");
        }
        if (pageCondition.getFilters().get("projectId") == null) {
            pageCondition.getFilters().put("projectId", "ISNULL");
        }
        if (pageCondition.getFilters().get("departmentId") == null) {
            throw new BusinessException("departmentId", "errors.paramCanNotNull");
        }
        if (pageCondition.getFilters().get("payToCompany") == null) {
            throw new BusinessException("payToCompany", "errors.paramCanNotNull");
        }

        // 已完成未对账的数据
        pageCondition.getFilters().put("receivableReconcilicationStatus",
                Waybill.ReconciliationStatus.NOT_RECONCILIATION.getCode());
        pageCondition.getFilters().put("statusView", Waybill.StatusView.FINISH.getCode());
        pageCondition.getFilters().put("receivableReconcilicationNo", "ISNULL");

        List<ReconciliationWaybillDetailVo> reconciliationWaybillDetailVos = new ArrayList<>();
        Page<Waybill> waybillPage = waybillService.search(loginEmployee, pageCondition);
        // 封装费率
        if (waybillPage.getResults() != null) {
            List<Integer> waybillIds = Lists.newArrayList();
            for (Waybill waybill : waybillPage.getResults()) {
                waybillIds.add(waybill.getWaybillId());
            }

            Map<Integer,TruckRequire> truckRequireMap = truckRequireService.findDictionaryByWaybillIds(waybillIds);
            Map<Integer,WaybillAmount> waybillAmountMap = waybillAmountService.findDictionaryByWaybillIds(waybillIds);
            AdjustForMasterFilter adjustForMasterFilter = new AdjustForMasterFilter();
            adjustForMasterFilter.setWaybillIds(waybillIds);
            adjustForMasterFilter.setApprovalStatus(ApprovalStatus.APPROVAL_SUBMIT.getCode());
            Map<Integer,List<AdjustForMaster>> adjustForMasterMap = adjustForMasterAddService.findMasterDictionaryByFilter(adjustForMasterFilter);

            for (Waybill waybill : waybillPage.getResults()) {
                ReconciliationWaybillDetailVo reconciliationWaybillDetailVo = new ReconciliationWaybillDetailVo();
                RoadMap roadMap = roadMapService.getRoadMap(waybill.getRoadMapId());
                waybill.setRoadMapName(roadMap == null ? "" : roadMap.getName());
                TruckRequire truckRequire = truckRequireMap.get(waybill.getWaybillId());
                WaybillAmount amount = waybillAmountMap.get(waybill.getWaybillId());
                if (null != amount) {
                    // 重新赋值客户含税价、不含税价
                    waybill.setEstimateFreight(amount.getLastCustomerFreightWithTax());
                    waybill.setAfterTaxFreight(BaseUtil.calFreightWithNotTax(waybill.getEstimateFreight(),
                        ((truckRequire == null || truckRequire.getTaxRateValue() == null) ? BigDecimal.ZERO : truckRequire.getTaxRateValue())));
                }

                reconciliationWaybillDetailVo.setWaybill(waybill);
                reconciliationWaybillDetailVo.setRequire(truckRequire);

                // 改价申请
                AdjustForMasterFilter filter = new AdjustForMasterFilter();
                filter.setWaybillId(waybill.getWaybillId());
                filter.setApprovalStatus(ApprovalStatus.APPROVAL_SUBMIT.getCode());
                reconciliationWaybillDetailVo.setHasAdjustApply(CollectionUtils.isEmpty(adjustForMasterMap.get(waybill.getWaybillId())) ? false : true);
                reconciliationWaybillDetailVos.add(reconciliationWaybillDetailVo);
            }
        }
        return new Page<>(waybillPage.getPageNo(), waybillPage.getPageSize(), waybillPage.getTotal(),
                reconciliationWaybillDetailVos);
    }

    @Override
    public void updateFreight(AdjustForReceivable adjustForReceivable, LoginUser loginUser) throws BusinessException {

        if (adjustForReceivable.getWaybillId() == null
                || (adjustForReceivable.getReceivableWithTaxAdjust() == null
                && adjustForReceivable.getTaxRateAdjust() == null)
                || StringUtils.isBlank(adjustForReceivable.getAdjustRemark())) {
            throw new BusinessException("adjustForReceivable", "errors.paramCanNotNull");
        }

        Integer waybillId = adjustForReceivable.getWaybillId();

        StringBuffer changeInfo = new StringBuffer("");
        Waybill waybill = waybillService.getWaybillAndCheckExist(waybillId);
        // 原始含税价、税率
        AdjustForReceivable oldAdjustForReceivable = findOldWaybillDate(waybillId, loginUser);

        // 原税前费用
        BigDecimal oldEstimateFreight = oldAdjustForReceivable.getReceivableWithTax();
        // 原始税率
        BigDecimal oldTaxRate = oldAdjustForReceivable.getTaxRate();

        // 改价前校验运单信息
        checkWaybillBeforeUpadteFreight(waybill, loginUser.getTenantId());

        // 费用合法性校验
        checkFright(adjustForReceivable.getReceivableWithTaxAdjust());

        // 修改税率
        TruckRequire truckRequire = updateTruckRequire(waybillId, oldTaxRate, adjustForReceivable.getTaxRateAdjust(),
                changeInfo, loginUser);
        // 设置新税率
        truckRequire.setTaxRateValue(adjustForReceivable.getTaxRateAdjust());

        // 修改运单信息
        // waybill.setUpdateFreightRemark(adjustForReceivable.getAdjustRemark());
        waybill.setEstimateFreight(adjustForReceivable.getReceivableWithTaxAdjust() == null ? BigDecimal.ZERO
                : adjustForReceivable.getReceivableWithTaxAdjust());
        waybill.setAfterTaxFreight(truckTypeFreightService.getAfterTaxFright(truckRequire, waybill));

        waybillCommonService.update(waybill, loginUser);

        // ==== 同步更新改价表 ========
        adjustForReceivable.setWaybillNo(waybill.getWaybillNo());
        adjustForReceivable.setTaxRate(oldTaxRate);
        adjustForReceivable.setReceivableWithTax(oldEstimateFreight);
        adjustForReceivable.setAdjustUserId(loginUser.getUserId());
        changeReconciliationItem(adjustForReceivable);

        changeInfo.append("原税前费用").append(oldEstimateFreight).append(",新税前费用：").append(waybill.getEstimateFreight())
                .append(";");

        // 通知发单人： 税前费用该变
        if ("是".equals(adjustForReceivable.getNoticeToWaybillOwner())
                && waybill.getEstimateFreight().compareTo(oldEstimateFreight) != 0) {
            msgWithBusinessService.pushChangePricelMsgToWaybillOwner(waybill.getWaybillId(), oldEstimateFreight,
                    loginUser);
        }

        // 操作轨迹
        addWaybillOperateTrack(waybill.getWaybillId(), changeInfo.toString(), loginUser);
    }

    @Override
    public void batchUpdateFreight(List<ReconciliationWaybillExcelVo> reconciliationWaybillExcelVos,
                                   LoginUser loginUser) throws BusinessException {

        for (ReconciliationWaybillExcelVo excelVo : reconciliationWaybillExcelVos) {
            AdjustForReceivable adjustForReceivable = new AdjustForReceivable();
            adjustForReceivable.setWaybillId(excelVo.getWaybillId());
            adjustForReceivable.setReceivableWithTaxAdjust(excelVo.getEstimateFreight());
            adjustForReceivable.setTaxRateAdjust(excelVo.getTaxRateValue());
            adjustForReceivable.setNoticeToWaybillOwner(excelVo.getNoticeToWaybillOwner());
            adjustForReceivable.setAdjustRemark(excelVo.getUpdateFreightRemark());
            updateFreight(adjustForReceivable, loginUser);
        }
    }

    private void changeReconciliationItem(AdjustForReceivable adjustForReceivable) {
        adjustForReceivable.setAdjustTime(new Date());
        adjustForReceivableMapper.insertSelective(adjustForReceivable);
    }

    // 操作轨迹:由于只是简单的添加一条记录，故不使用异步
    private void addWaybillOperateTrack(Integer waybillId, String remark, LoginUser loginUser) {
        try {
            WaybillOperateTrack track = new WaybillOperateTrack();
            track.setWaybillId(waybillId);
            track.setOperateApplication(OperateApplication.BACKGROUND_SYS.getCode());
            track.setOperateType(OperateType.UPDATE_FREIGHT.getCode());
            track.setDeclareTime(new Date());
            track.setRemark(remark);
            waybillOperateTrackService.insert(track, loginUser);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    // 修改费率
    private TruckRequire updateTruckRequire(Integer wayBillId, BigDecimal oldTaxRateValue, BigDecimal newTaxRateValue,
                                            StringBuffer changeInfo, LoginUser loginUser) {

        TruckRequire truckRequire = truckRequireService.findTruckRequireByWaybillId(wayBillId, loginUser);
        // 更改记录
        changeInfo.append("原税率").append(oldTaxRateValue).append(",新税率：").append(newTaxRateValue).append(";");

        // 更改操作
        if (null == newTaxRateValue) {
            // 清除税率
            truckRequire.setTaxRateValue(null);
            truckRequireService.removeNullInfo(truckRequire);
        } else {
            truckRequire.setTaxRateValue(newTaxRateValue);
            truckRequireService.update(truckRequire);
        }
        return truckRequire;
    }

    // 改价前校验运单信息
    private void checkWaybillBeforeUpadteFreight(Waybill waybill, Integer tenantId) {
        // 判断这些运单是否属于该租户
        if (!tenantId.equals(waybill.getTenantId())) {
            throw new BusinessException("waybill permission refusal", "errors.reconciliation.waybills.authority",
                    waybill.getWaybillNo());
        }
        // 只有已完成的运单才可以改价
        if (NumberUtils.compare(Waybill.StatusView.FINISH.getCode(), waybill.getStatusView()) != 0) {
            throw new BusinessException("waybillIsNotFinish", "waybill.error.waybillIsNotFinish");
        }

        // 对账完成的运单不能改价
        if (NumberUtils.compare(Waybill.ReconciliationStatus.HAS_RECONCILIATION.getCode(),
                waybill.getReceivableReconcilicationStatus()) == 0) {
            throw new BusinessException("waybillHasReconciliation", "waybill.error.waybillHasReconciliation");
        }
    }

    // 校验费用
    private void checkFright(BigDecimal freight) {
        if (null == freight) {
            return;
        }

        if (freight.compareTo(new BigDecimal("999999.99")) == 1) {
            throw new BusinessException("freightTooMax", "waybill.error.freightTooMax");
        }
    }

    @Override
    public AdjustForReceivable findOldWaybillDate(Integer waybillId, LoginUser loginUser) throws BusinessException {

        if (waybillId == null) {
            throw new BusinessException("waybillId", "errors.paramCanNotNull");
        }
        AdjustForReceivable adjustForReceivable = new AdjustForReceivable();
        adjustForReceivable.setWaybillId(waybillId);
        adjustForReceivable.setReceivableWithTax(BigDecimal.ZERO);

        // 是否存在改价记录
        AdjustForReceivable firstAdjustForReceivable = adjustForReceivableMapper.findFirst(waybillId);
        if (firstAdjustForReceivable != null) {
            adjustForReceivable.setWaybillNo(firstAdjustForReceivable.getWaybillNo());
            adjustForReceivable.setReceivableWithTax(firstAdjustForReceivable.getReceivableWithTax());
            adjustForReceivable.setTaxRate(firstAdjustForReceivable.getTaxRate());
            return adjustForReceivable;
        }

        Waybill waybill = waybillService.getWaybillAndCheckExist(waybillId);
        adjustForReceivable.setWaybillNo(waybill.getWaybillNo());
        adjustForReceivable.setReceivableWithTax(waybill.getEstimateFreight());

        TruckRequire truckRequire = truckRequireService.findTruckRequireByWaybillId(waybillId, loginUser);
        adjustForReceivable.setTaxRate(truckRequire.getTaxRateValue());
        return adjustForReceivable;
    }

    @Override
    public Page<AdjustForReceivable> findAdjustByPage(PageCondition pageCondition) {

        if (pageCondition == null || pageCondition.getFilters().get("waybillId") == null) {
            throw new BusinessException("waybillId", "errors.paramCanNotNull");
        }

        Page<AdjustForReceivable> adjustForReceivablePage = new Page<>(pageCondition.getPageNo(),
                pageCondition.getPageSize(), 0);

        Integer adjustCount = adjustForReceivableMapper.findAdjustCount(pageCondition);
        if (adjustCount == null || adjustCount.equals(0)) {
            return adjustForReceivablePage;
        }

        List<AdjustForReceivable> adjustForReceivables = adjustForReceivableMapper.findAdjustBypage(pageCondition);
        // 设置操作人名字
        for (AdjustForReceivable ad : adjustForReceivables) {
            User user = userService.loadUser(ad.getAdjustUserId());
            if (user != null) {
                ad.setAdjustUserName(user.getName());
            }
        }
        adjustForReceivablePage.setTotal(adjustCount);
        adjustForReceivablePage.setResults(adjustForReceivables);
        return adjustForReceivablePage;
    }

    @Override
    public String createReceivableReconciliation(List<Integer> waybillIds, LoginEmployee loginEmployee)
            throws BusinessException {

        if (waybillIds == null || waybillIds.isEmpty()) {
            throw new BusinessException("waybills", "errors.reconciliation.waybills");// 一个合法的运单都没有
        }
        List<Waybill> waybills = waybillService.findByWaybillIds(waybillIds);

        // 校验运单合法性
        checkWaybills(waybills, loginEmployee.getTenantId());
        // 应收（含税）合计
        BigDecimal sumreceivableWithTax = BigDecimal.ZERO;
        // 应收（不含税）合计
        BigDecimal sumreceivableWithoutTax = BigDecimal.ZERO;
        // 对账单号
        String reconcilicationNo = idGeneratorService.genId(IdGeneratorTable.IdType.AR);

        TruckRequireFilter truckRequireFilter = new TruckRequireFilter();
        truckRequireFilter.setWaybillIds(waybillIds);
        List<TruckRequire> requires = truckRequireService.findByFilter(truckRequireFilter,loginEmployee);
        //获取税率
        Map<Integer, BigDecimal> taxRateMap = Maps.newHashMap();
        for (TruckRequire require : requires) {
            taxRateMap.put(require.getWaybillId(),require.getTaxRateValue());
        }
        List<WaybillAmount> waybillAmounts = waybillAmountService.findByWaybillIds(waybillIds, loginEmployee);
        //获取客户最终金额
        Map<Integer, BigDecimal> customerFreightMap = Maps.newHashMap();
        for (WaybillAmount amount : waybillAmounts) {
            if (null == amount || amount.getLastCustomerFreightWithTax().compareTo(BigDecimal.ZERO) < 0) {
                throw new BusinessException("freightError", "运单最终的税前税后费用不能小于0");
            }
            customerFreightMap.put(amount.getWaybillId(),amount.getLastCustomerFreightWithTax());
        }

        for (Waybill waybill : waybills) {
            // 对账状态不能为空且对账状态为未对账
            if (waybill.getReceivableReconcilicationStatus() == null || waybill.getReceivableReconcilicationStatus()
                    .compareTo(Waybill.ReconciliationStatus.NOT_RECONCILIATION.getCode()) != 0) {
                throw new BusinessException("reconciliationStatus", "对账状态须为未对账");
            }

            // 重新赋值运单税前税后费用
            waybill.setEstimateFreight(customerFreightMap.get(waybill.getWaybillId()));
            BigDecimal taxRate = (null == taxRateMap.get(waybill.getWaybillId()) ? BigDecimal.ZERO : taxRateMap.get(waybill.getWaybillId()));
            waybill.setAfterTaxFreight(BaseUtil.calFreightWithNotTax(customerFreightMap.get(waybill.getWaybillId()),taxRate));

            // 含税和不含税金额不能为null或者0
            if (waybill.getEstimateFreight() == null || waybill.getEstimateFreight().compareTo(BigDecimal.ZERO) < 0
                    || waybill.getAfterTaxFreight() == null
                    || waybill.getAfterTaxFreight().compareTo(BigDecimal.ZERO) < 0) {
                throw new BusinessException("freightError", "税前税后费用不能小于0");
            }

            sumreceivableWithTax = sumreceivableWithTax.add(waybill.getEstimateFreight());
            sumreceivableWithoutTax = sumreceivableWithoutTax.add(waybill.getAfterTaxFreight());
            waybill.setReceivableReconcilicationNo(reconcilicationNo);
            waybill.setReceivableReconcilicationStatus(
                    ReconcilicationForReceivableEnum.ReconciliationStatus.IN_THE_ACCOUNT.getCode());
        }
        ReconcilicationForReceivable reconcilication = new ReconcilicationForReceivable();
        reconcilication.setAreaCode(waybills.get(0).getAreaCode());
        reconcilication.setCustomerId(waybills.get(0).getCustomerId());
        reconcilication.setCustomerName(waybills.get(0).getCustomerName());
        reconcilication.setProjectId(waybills.get(0).getProjectId());
        reconcilication.setProjectName(waybills.get(0).getProjectName());
        reconcilication.setReceivableWithTax(sumreceivableWithTax);
        reconcilication.setReceivableWithoutTax(sumreceivableWithoutTax);

        // 插入对账单主表
        createReconciliation(reconcilication, reconcilicationNo, loginEmployee);
        // 批量插入对账单明细表
        insertItem(waybills, reconcilication.getReconcilicationId(), loginEmployee);
        // 更新运单表对账单号和对账状态
        waybillService.batchUpdateReceivableReconcilicationNo(waybills);

        Waybill waybill = waybills.get(0);

        if (waybill == null || waybill.getDepartmentId() == null
                || waybill.getPayToCompany() == null
                || waybill.getDepartmentId().equals(waybill.getPayToCompany())) return reconcilicationNo;

        // 签约、运营主体不一样
        List<ReconcilicationForCompany> rows = new ArrayList<ReconcilicationForCompany>();

        String reconcilicationCompany = idGeneratorService.genId(IdGeneratorTable.IdType.CO);
        ReconcilicationForCompany company = new ReconcilicationForCompany();
        company.setAreaCode(reconcilication.getAreaCode());
        company.setTenantId(loginEmployee.getTenantId());
        company.setTenantCode(loginEmployee.getTenantCode());
        company.setReconcilicationCompanyNo(reconcilicationCompany);
        company.setReconcilicationReceivableId(reconcilication.getReconcilicationId());
        company.setFreightWithTax(reconcilication.getReceivableWithTax());
        company.setContractToCompany(waybill.getDepartmentId());
        company.setContractToCompanyCreditCode(
            waybill.getContractToCompanyCreditCode() == null ? "" : waybill.getContractToCompanyCreditCode());
        company.setPayToCompany(waybill.getPayToCompany());
        company.setPayToCompanyCreditCode(
            waybill.getPayToCompanyCreditCode() == null ? "" : waybill.getPayToCompanyCreditCode());
        company.setCreateUserId(loginEmployee.getUserId());
        company.setCreateTime(new Date());
        company.setLastUpdateUserId(loginEmployee.getUserId());
        company.setLastUpdateTime(new Date());
        rows.add(company);
        //批量写入
        reconcilicationForCompanyMapper.insertBatch(rows);

        return reconcilicationNo;
    }

    // 如果 检查通过 则返回对账单 的类型
    private void checkWaybills(List<Waybill> waybills, Integer tenantId) throws BusinessException {
        Integer customerId = null;
        if (waybills == null || waybills.isEmpty()) {
            throw new BusinessException("waybills is empty", "errors.reconciliation.waybills");
        }
        for (Waybill waybill : waybills) {
            // 判断这些运单是否属于该租户
            if (!tenantId.equals(waybill.getTenantId())) {
                throw new BusinessException("waybill permission refusal", "errors.reconciliation.waybills.authority",
                        waybill.getWaybillNo());
            }
            // 判断 不为空 则这个运单已经生成过对账单
            if (StringUtils.isNotBlank(waybill.getReceivableReconcilicationNo())) {
                throw new BusinessException("waybill", "errors.reconciliation.repeat", waybill.getWaybillNo());
            }
            // 容错检测customerId 是否为null
            if (waybill.getCustomerId() == null) {
                throw new BusinessException("customerId", "errors.paramCanNotNullWithName", "运单客户id");
            }
            // 如果不为空则开始判断是否属于同一个客户
            else if (customerId == null) {
                customerId = waybill.getCustomerId();
            }
            // 如果后面的 和第一个 只要有一个不一样就抛出异常，证明这一批 不是一个 客户的
            else if (!customerId.equals(waybill.getCustomerId())) {
                throw new BusinessException("customerId", "errors.reconciliation.customer",
                        "{" + customerId + "," + waybill.getCustomerId() + "}");
            }
            // 子公司不能为空
            if (waybill.getDepartmentId() == null) {
                throw new BusinessException("departmentId", waybill.getWaybillNo() + "：该运单没有签约主体，不能生成对账单！");
            }
        }
    }

    // 插入对账单主表
    private Integer createReconciliation(ReconcilicationForReceivable reconcilication, String reconcilicationNo,
                                         LoginEmployee loginEmployee) {

        reconcilication.setTenantId(loginEmployee.getTenantId());
        reconcilication.setTenantCode(loginEmployee.getTenantCode());
        reconcilication.setReconcilicationNo(reconcilicationNo);
        reconcilication.setApprovalStatus(ReconcilicationForReceivableEnum.ApprovalStatusStatus.Append.getCode());
        reconcilication.setReceiveStatus(ReconcilicationForReceivableEnum.ReceiptStatus.NOT_COLLECTION.getCode());
        reconcilication.setInvoiceStatus(ReconcilicationForReceivableEnum.InvoiceStatus.NOT_INVOICESTATUS.getCode());
        reconcilication.setCreateTime(new Date());
        reconcilication.setCreateUserId(loginEmployee.getUserId());
        return reconcilicationForReceivableMapper.insertSelective(reconcilication);
    }

    /**
     * 获取运单列表
     *
     * @param waybillIds
     * @return
     */
    @Override
    public List<Waybill> getWaybillList(List<Integer> waybillIds) {
        if (CollectionUtils.isEmpty(waybillIds)) return null;

        List<Waybill> waybillList = new ArrayList<>();
        Waybill waybill = null;
        for (Integer id : waybillIds) {
            waybill = waybillCommonService.getWaybillById(id);
            if (waybill == null) continue;

            waybillList.add(waybill);
        }

        return waybillList;
    }

    @Override
    public Page<ReconcilicationForReceivablePageVo> findReceivableReconciliationPage(PageCondition pageCondition,
                                                                                     LoginEmployee loginEmployee) {

        pageCondition.getFilters().put("tenantId", loginEmployee.getTenantId());

        Page<ReconcilicationForReceivablePageVo> page = new Page<>(pageCondition.getPageNo(),
                pageCondition.getPageSize(), 0);
        Integer receivableCount = reconcilicationForReceivableExtraMapper.findReceivableCount(pageCondition);
        if (receivableCount == null || receivableCount.equals(0)) {
            return page;
        }

        List<ReconcilicationForReceivable> receivables = reconcilicationForReceivableExtraMapper
                .findReceivablePage(pageCondition);
        List<ReconcilicationForReceivablePageVo> receivableVos = new ArrayList<>();
        for (ReconcilicationForReceivable receivable : receivables) {

            ReconcilicationForReceivablePageVo pageVo = new ReconcilicationForReceivablePageVo();
            BeanUtils.copyProperties(receivable, pageVo);
            // 查询对账凭证
            List<ImageUploadManage> images = imageUploadManageService.listByRelationIdAndSign(
                    receivable.getReconcilicationId(),
                    ImageUploadManage.ImageUploadManageSign.RECONCILIATION_RECEIVABLE.getCode());
            if (images.isEmpty()) {
                pageVo.setHasEvidence(0);
            } else {
                pageVo.setHasEvidence(1);
            }

            // 对账单调整金额
            AdjustFreightVo adjustFreightVo = adjustForMasterService
                .loadAdjustForMasterByReconcilicationNo(receivable.getReconcilicationNo());
            pageVo.setAdjustForFreight(adjustFreightVo == null ? null : adjustFreightVo.getAdjustFreight());

            receivableVos.add(pageVo);
        }
        page.setTotal(receivableCount);
        page.setResults(receivableVos);
        return page;
    }

    @Override
    public Page<ReconcilicationForReceivableItemVo> findReceivableReconciliationItemPage(PageCondition pageCondition) {

        if (pageCondition == null || pageCondition.getFilters() == null
                || pageCondition.getFilters().get("reconcilicationId") == null
                || pageCondition.getFilters().get("reconcilicationId").equals("")) {
            throw new BusinessException("reconcilicationId", "errors.paramCanNotNull");
        }

        ReconcilicationForReceivableItemExample example = new ReconcilicationForReceivableItemExample();
        if (StringUtils.isNotEmpty(pageCondition.getOrderBy())) {
            example.setOrderByClause(pageCondition.getOrderBy() + " " + pageCondition.getOrderSort());
        }

        example.setSize(pageCondition.getPageSize());
        example.setStartOffSet(pageCondition.getStartOffSet());

        ReconcilicationForReceivableItemExample.Criteria criteria = example.createCriteria();
        criteria.andReconcilicationIdEqualTo(
                Integer.parseInt(pageCondition.getFilters().get("reconcilicationId").toString()));
        if (pageCondition.getFilters().get("waybillNo") != null) {
            criteria.andWaybillNoEqualTo((String) pageCondition.getFilters().get("waybillNo"));
        }

        Page<ReconcilicationForReceivableItemVo> page = new Page<>(pageCondition.getPageNo(),
                pageCondition.getPageSize(), 0);

        Integer receivableItemCount = reconcilicationForReceivableItemMapper.countByExample(example);
        if (receivableItemCount.equals(0)) {
            return page;
        }

        List<ReconcilicationForReceivableItem> receivableItemList = reconcilicationForReceivableItemMapper
                .selectByExample(example);
        // 组装对账状态和结算状态
        List<ReconcilicationForReceivableItemVo> resultReceivableItemList = makeItemVo(receivableItemList);

        page.setTotal(receivableItemCount);
        page.setResults(resultReceivableItemList);
        return page;
    }

    private String getVendorName(Waybill waybill) {
        LoginUser loginUser = new LoginUser();
        loginUser.setTenantId(waybill.getTenantId());
        Vendor vendor = vmsCommonService.loadVendorByVendorId(waybill.getVendorId());
        return vendor == null ? "" : vendor.getVendorName();
    }

    private List<ReconcilicationForReceivableItemVo> makeItemVo(
            List<ReconcilicationForReceivableItem> receivableItemList) {

        List<ReconcilicationForReceivableItemVo> resultReceivableItemList = new ArrayList<>();
        List<Integer> waybillIds = Lists.newArrayList();
        for (ReconcilicationForReceivableItem item : receivableItemList) {
            waybillIds.add(item.getWaybillId());
        }
        List<Waybill> waybills = waybillService.findByWaybillIds(waybillIds);
        if( CollectionUtils.isEmpty(waybillIds) ){
            return Lists.newArrayList();
        }
        Map<Integer,Waybill> waybillMap = Maps.newConcurrentMap();
        for (Waybill waybill : waybills){
            waybillMap.put(waybill.getWaybillId(),waybill);
        }
        List<WaybillAmount> waybillAmounts = waybillAmountService.findByWaybillIds(waybillIds,null);
        Map<Integer,WaybillAmount> waybillAmountMap = Maps.newConcurrentMap();
        for (WaybillAmount waybillAmount : waybillAmounts){
            waybillAmountMap.put(waybillAmount.getWaybillId(),waybillAmount);
        }

        for (ReconcilicationForReceivableItem item : receivableItemList) {
            ReconcilicationForReceivableItemVo itemVo = new ReconcilicationForReceivableItemVo();
            BeanUtils.copyProperties(item, itemVo);

            Waybill waybill = waybillMap.get(item.getWaybillId());
            if (null == waybill) {
                resultReceivableItemList.add(itemVo);
                continue;
            }

            itemVo.setSettleStatus(waybill.getSettlementStatus());
            itemVo.setPayableReconcilicationStatus(waybill.getReconciliationStatus());
            itemVo.setVendorName(getVendorName(waybill));
            itemVo.setDriverName(waybill.getDriverName());
            itemVo.setDriverType(waybill.getDriverType());
            itemVo.setPlateNumber(waybill.getPlateNumber());
            itemVo.setAdjustRemark(waybill.getUpdateFreightRemark());

            AdjustFreightVo adjustFreightVo = adjustForItemService
                .loadAdjustAbsFreightByWaybillIdAnd(item.getWaybillId(), AdjustType.AFTER.getCode(),
                    AdjustMasterType.CUSTOMER.getCode());
            if (null != adjustFreightVo) {
                // 运单调整金额
                itemVo.setAdjustForFreight(adjustFreightVo.getAdjustFreight());

                // 运单调整后金额
                WaybillAmount waybillAmount = waybillAmountMap.get(item.getWaybillId());
                itemVo.setFinalFreightForCustomer(waybillAmount == null ? null : waybillAmount.getLastCustomerFreightWithTax());
            }

            resultReceivableItemList.add(itemVo);
        }

        return resultReceivableItemList;
    }

    /**
     * 插入对账单明细
     */
    private void insertItem(List<Waybill> waybills, Integer reconcilicationId, LoginEmployee loginEmployee) {

        List<ReconcilicationForReceivableItemVo> items = new ArrayList<>();

        for (Waybill waybill : waybills) {

            ReconcilicationForReceivableItemVo item = new ReconcilicationForReceivableItemVo();

            item.setReconcilicationId(reconcilicationId);
            item.setWaybillId(waybill.getWaybillId());
            item.setWaybillNo(waybill.getWaybillNo());
            item.setPlanDeliveryTime(waybill.getPlanDeliveryTime());
            item.setReceivableWithTax(waybill.getEstimateFreight());
            item.setReceivableWithoutTax(waybill.getAfterTaxFreight());
            item.setRebateFee(waybill.getRebateFee());
            item.setSettleStatus(ReconcilicationForReceivableEnum.SettlementStatus.NOT_CLEAR.getCode());
            item.setPayableReconcilicationStatus(
                    ReconcilicationForReceivableEnum.ReconciliationStatus.NOT_RECONCILIATION.getCode());
            TruckRequire truckRequire = truckRequireService.findTruckRequireByWaybillId(waybill.getWaybillId(),
                    loginEmployee);
            if (truckRequire != null) {
                item.setTaxRate(truckRequire.getTaxRateValue());
            }
            WaybillAmount amount = waybillAmountService.loadByWaybillId(waybill.getWaybillId());
            item.setPayableWithTax(null == amount ? null : amount.getLastVendorFreightWithTax());
            items.add(item);
        }

        reconcilicationForReceivableItemExtraMapper.batchInsert(items);
    }

    @Override
    public void submitToWorkFlow(List<Integer> reconcilicationForReceivableIds, LoginEmployee loginEmployee) {

        if (reconcilicationForReceivableIds == null || reconcilicationForReceivableIds.isEmpty()) {
            return;
        }

        for (Integer id : reconcilicationForReceivableIds) {

            ReconcilicationForReceivable reconcilication = new ReconcilicationForReceivable();
            reconcilication.setReconcilicationId(id);
            reconcilication.setSubmitTime(new Date());
            reconcilication.setSubmitUserId(loginEmployee.getUserId());
            reconcilication.setApprovalStatus(ReconcilicationForReceivableEnum.ApprovalStatusStatus.SUBMIT.getCode());

            ReconcilicationForReceivable reconcilicationDB = reconcilicationForReceivableMapper.selectByPrimaryKey(id);
            if (reconcilicationDB == null) {
                continue;
            }

            if (reconcilicationDB.getCreateUserId() != null
                    && reconcilicationDB.getCreateUserId().compareTo(loginEmployee.getUserId()) != 0) {
                throw new BusinessException("userId", "创建对账单人与提交审核人不一致");
            }

            // 工作流程API
            Map<String, Object> variables = new HashMap<String, Object>();
            variables.put("number", reconcilicationDB.getReconcilicationNo());
            BigDecimal profitRateDiffer = calculateProfitRateDiffer(reconcilicationDB);
            if(profitRateDiffer != null){
                profitRateDiffer = profitRateDiffer.multiply(new BigDecimal(100.00));
                variables.put("profitRateDiffer", profitRateDiffer);
            }
            ProcessInstance ins = processService.startProcessInstance(WorkflowConstants.PROCESS_KEY_RECONCILICATION_RECEIVABLE, id + "", variables,
                    loginEmployee);
            reconcilication.setProcessInstanceId(ins.getProcessInstanceId());

            // 更新对账单信息
            reconcilicationForReceivableMapper.updateByPrimaryKeySelective(reconcilication);
        }
    }

    /**
     * 计算毛利率差
     * @param reconcilication
     * @return
     */
    @Override
    public BigDecimal calculateProfitRateDiffer(ReconcilicationForReceivable reconcilication){
        BigDecimal sumShow4DriverFreight = BigDecimal.ZERO;
        BigDecimal receivableWithTax = BigDecimal.ZERO;
        BigDecimal profitRateDiffer = BigDecimal.ZERO;
        BigDecimal profitRate = null;

        ReconcilicationForReceivableItemExample example = new ReconcilicationForReceivableItemExample();
        ReconcilicationForReceivableItemExample.Criteria criteria = example.createCriteria();
        criteria.andReconcilicationIdEqualTo(reconcilication.getReconcilicationId());
        List<ReconcilicationForReceivableItem> receivables = reconcilicationForReceivableItemMapper.selectByExample(example);
        List<Integer> waybillIds = Lists.newArrayList();
        for (ReconcilicationForReceivableItem receivableItem : receivables) {
            waybillIds.add(receivableItem.getWaybillId());
        }
        List<Waybill> waybills = waybillService.findByWaybillIds(waybillIds);
        //Map (运单id:承运商id)
        Map<Integer, Integer> vendorIdMap = Maps.newHashMap();
        List<Integer> vendorIds = Lists.newArrayList();
        buildVendorData(waybills, vendorIdMap, vendorIds);
        //Map (承运商id:承运商类型)
        Map<Integer, Byte> ownVendorMap = vmsCommonService.listVendorSource(vendorIds, VendorSourceEnum.SELF_SUPPORT.getCode());
        List<ReconcilicationForReceivableItemBo> itemBos = Lists.newArrayList();
        buildItemBo(receivables, vendorIdMap, itemBos);

        for (ReconcilicationForReceivableItemBo receivable : itemBos) {
            //排除自营承运商（计算毛利额、毛利率时需排除）
            if(null != ownVendorMap.get(receivable.getVendorId())){continue;}
            sumShow4DriverFreight = sumShow4DriverFreight.add(receivable.getPayableWithTax() == null ? BigDecimal.ZERO : receivable.getPayableWithTax());
            receivableWithTax = receivableWithTax.add(receivable.getReceivableWithTax() == null ? BigDecimal.ZERO : receivable.getReceivableWithTax());
        }

        //对账时毛利率
        if(receivableWithTax.compareTo(BigDecimal.ZERO) != 0){
            profitRate = (receivableWithTax.subtract(sumShow4DriverFreight)).divide(receivableWithTax, 4,BigDecimal.ROUND_HALF_UP);
        }

        //承诺毛利率
        Project project = projectService.getProjectV2(reconcilication.getProjectId());
        BigDecimal profitRatePromise = (null == project ? null : project.getProfitRate());
        //毛利差
        if(null != profitRate && null != profitRatePromise){
            profitRateDiffer = profitRate.subtract(profitRatePromise);
        }else{
            profitRateDiffer = new BigDecimal(-5);
        }

        return profitRateDiffer;
    }

    @Override
    public ReconcilicationForReceivable findReceivableById(Integer receivableId) {
        return reconcilicationForReceivableMapper.selectByPrimaryKey(receivableId);
    }

    @Override
    public void cancelReconciliation(Integer reconciliationId, LoginEmployee loginEmployee) throws BusinessException {

        if (reconciliationId == null) {
            throw new BusinessException("reconcilicationId", "errors.paramCanNotNull");
        }

        ReconcilicationForReceivable reconciliation = reconcilicationForReceivableMapper
                .selectByPrimaryKey(reconciliationId);
        if (reconciliation == null) return;
        // if(reconciliation.getCreateUserId() != null && reconciliation.getCreateUserId().compareTo(loginEmployee.getUserId()) != 0){
        // throw new BusinessException("userId", "创建对账单人与取消对账单人不一致");
        // }
        if (NumberUtils.compare(reconciliation.getApprovalStatus(),
                ReconcilicationForReceivableEnum.ApprovalStatusStatus.Append.getCode()) != 0
                && NumberUtils.compare(reconciliation.getApprovalStatus(),
                ReconcilicationForReceivableEnum.ApprovalStatusStatus.REJECT.getCode()) != 0)
            throw new BusinessException("reconciliationStatusError", "errors.reconciliation.cancelStatusError");

        String processInstanceId = reconciliation.getProcessInstanceId();
        Byte approvalStatusStatus = reconciliation.getApprovalStatus().byteValue();
        // 回滚运单状态
        List<Waybill> waybills = waybillCommonService
                .findByReceivableReconciliationNo(reconciliation.getReconcilicationNo());
        if (CollectionUtils.isEmpty(waybills)) throw new BusinessException("reconciliationError", "运单为空");
        Waybill w = null;
        for (Waybill waybill : waybills) {
            w = new Waybill();
            w.setWaybillId(waybill.getWaybillId());
            w.setReceivableReconcilicationNo("");
            w.setIsSubmitMatch(null);
            w.setReceivableReconcilicationStatus(Waybill.ReconciliationStatus.NOT_RECONCILIATION.getCode());
            waybillCommonService.update(w, loginEmployee);
        }
        // 删除对账单明细
        ReconcilicationForReceivableItemExample example = new ReconcilicationForReceivableItemExample();
        ReconcilicationForReceivableItemExample.Criteria criteria = example.createCriteria();
        criteria.andReconcilicationIdEqualTo(reconciliationId);

        reconcilicationForReceivableItemMapper.deleteByExample(example);
        // 删除对账单
        reconcilicationForReceivableMapper.deleteByPrimaryKey(reconciliationId);

        // 删除相关联的签约、运营主体不一致的情况
        reconcilicationForCompanyMapper.deleteByExample(new ReconcilicationForCompanyExample()
                .createCriteria().andReconcilicationReceivableIdEqualTo(reconciliationId).example());

        // 驳回状态要删除工作流记录
        if (NumberUtils.compare(approvalStatusStatus, Reconciliation.ReconciliationStatus.REJECT.getCode()) == 0) {
            if (!reconciliation.getSubmitUserId().equals(loginEmployee.getUserId())) {
                throw new BusinessException("cancelReconciliationError", "操作人必须和提交到工作流的是同一个人");
            }
            processService.deleteProcessInstance(processInstanceId, "发起人撤回", loginEmployee);
        }
    }

    @Override
    public void cancelWorkFlowTask(int reconciliationId, LoginEmployee loginEmployee) throws BusinessException {

        ReconcilicationForReceivable reconciliation = reconcilicationForReceivableMapper
                .selectByPrimaryKey(reconciliationId);
        if (reconciliation == null) return;
        if (reconciliation.getProcessInstanceId() == null) return;
        if (NumberUtils.compare(reconciliation.getApprovalStatus(),
                ReconcilicationForReceivableEnum.ApprovalStatusStatus.AGREE.getCode()) == 0)
            return;
        if (NumberUtils.compare(reconciliation.getApprovalStatus(),
                ReconcilicationForReceivableEnum.ApprovalStatusStatus.Append.getCode()) == 0)
            return;
        if (reconciliation.getCreateUserId() != null
                && reconciliation.getCreateUserId().compareTo(loginEmployee.getUserId()) != 0) {
            throw new BusinessException("userId", "创建对账单人与取消对账单人不一致");
        }
        // 撤回工作流审批
        processService.revokeProcessInstance(reconciliation.getProcessInstanceId(), "发起人撤回", loginEmployee);
        // 修改对账单状态
        ReconcilicationForReceivable updateReceivable = new ReconcilicationForReceivable();
        updateReceivable.setReconcilicationId(reconciliationId);
        updateReceivable.setApprovalStatus(ReconcilicationForReceivableEnum.ApprovalStatusStatus.Append.getCode());
        reconcilicationForReceivableMapper.updateByPrimaryKeySelective(updateReceivable);
    }

    @Override
    public ReconcilicationForReceivableVo getReconciliationOverView(Integer reconciliationId) {

        ReconcilicationForReceivableVo receivableVo = new ReconcilicationForReceivableVo();

        if (reconciliationId == null) {
            throw new BusinessException("reconcilicationId", "errors.paramCanNotNull");
        }
        ReconcilicationForReceivable reconciliation = reconcilicationForReceivableMapper
                .selectByPrimaryKey(reconciliationId);
        if (reconciliation == null) {
            throw new BusinessException("reconciliation", "errors.reconciliation.notExist");
        }
        receivableVo.setProjectName(reconciliation.getProjectName());
        receivableVo.setCustomerName(reconciliation.getCustomerName());
        receivableVo.setReconcilicationNo(reconciliation.getReconcilicationNo());
        receivableVo.setReceivableWithoutTax(reconciliation.getReceivableWithoutTax());
        receivableVo.setReceivableWithTax(reconciliation.getReceivableWithTax());
        receivableVo.setReceiveStatus(reconciliation.getReceiveStatus());
        receivableVo.setInvoiceStatus(reconciliation.getInvoiceStatus());
        receivableVo.setApprovalStatus(reconciliation.getApprovalStatus());

        ReconcilicationForReceivableItemExample example = new ReconcilicationForReceivableItemExample();
        ReconcilicationForReceivableItemExample.Criteria criteria = example.createCriteria();
        criteria.andReconcilicationIdEqualTo(reconciliationId);
        Integer waybillNum = reconcilicationForReceivableItemMapper.countByExample(example);

        // 组装子公司名字
        PageCondition pageCondition = new PageCondition();
        pageCondition.getFilters().put("receivableReconcilicationNo", reconciliation.getReconcilicationNo());
        pageCondition.setPageNo(1);
        pageCondition.setPageSize(Integer.MAX_VALUE);
        List<Waybill> waybills = waybillCommonService.search(pageCondition);
        if (CollectionUtils.isNotEmpty(waybills)) {

            Department contractToCompany = departmentService.loadDepartment(waybills.get(0).getDepartmentId());
            receivableVo.setDepartmentId(waybills.get(0).getDepartmentId());
            receivableVo.setDepartmentName(contractToCompany == null ? "" : contractToCompany.getBusinessLicenceName());

            Department payToCompany = departmentService.loadDepartment(waybills.get(0).getPayToCompany());
            receivableVo.setPayToCompany(waybills.get(0).getPayToCompany());
            receivableVo.setPayToCompanyName(payToCompany == null ? "" : payToCompany.getBusinessLicenceName());
        }

        receivableVo.setWaybillNum(waybillNum);
        //组装金额信息
        buildOverViewAmountInfo(receivableVo, reconciliation, waybills);

        return receivableVo;
    }

    private void buildOverViewAmountInfo(ReconcilicationForReceivableVo receivableVo, ReconcilicationForReceivable reconciliation, List<Waybill> waybills) {
        //Map (运单id:承运商id)
        Map<Integer, Integer> vendorIdMap = Maps.newHashMap();
        List<Integer> vendorIds = Lists.newArrayList();
        buildVendorData(waybills, vendorIdMap, vendorIds);
        //Map (承运商id:承运商类型)
        Map<Integer, Byte> ownVendorMap = vmsCommonService.listVendorSource(vendorIds, VendorSourceEnum.SELF_SUPPORT.getCode());
        BigDecimal profitRatePromise = BigDecimal.ZERO;
        //项目承诺毛利率
        Project project = projectService.getProjectV2(reconciliation.getProjectId());
        if(null != project){
            profitRatePromise = project.getProfitRate();
            receivableVo.setProfitRatePromise(BaseUtil.formatDecimal(profitRatePromise.doubleValue()));
        }

        //组装对账时金额
        buildAmount(receivableVo, reconciliation, vendorIdMap, ownVendorMap, profitRatePromise);
        //组装调整金额
        buildAdjustAmount(receivableVo, reconciliation, waybills,ownVendorMap, profitRatePromise);
        //组装按月统计金额
        buildMonthAmount(receivableVo, reconciliation, waybills);
    }

    private void buildAmount(ReconcilicationForReceivableVo receivableVo, ReconcilicationForReceivable reconciliation, Map<Integer, Integer> vendorIdMap, Map<Integer, Byte> ownVendorMap, BigDecimal profitRatePromise) {
        BigDecimal sumShow4DriverFreight = BigDecimal.ZERO;
        BigDecimal sumShow4DriverFreightVendor = BigDecimal.ZERO;
        BigDecimal receivableWithTax = BigDecimal.ZERO;
        BigDecimal profitRate = null;

        ReconcilicationForReceivableItemExample example = new ReconcilicationForReceivableItemExample();
        ReconcilicationForReceivableItemExample.Criteria criteria = example.createCriteria();
        criteria.andReconcilicationIdEqualTo(reconciliation.getReconcilicationId());
        List<ReconcilicationForReceivableItem> receivables = reconcilicationForReceivableItemMapper.selectByExample(example);

        List<ReconcilicationForReceivableItemBo> itemBos = Lists.newArrayList();
        buildItemBo(receivables, vendorIdMap, itemBos);

        Boolean initValue = false;
        for (ReconcilicationForReceivableItemBo receivable : itemBos) {
            if(null != receivable.getPayableWithTax()){
                initValue = true;
            }
            //含自营承运商
            sumShow4DriverFreightVendor = sumShow4DriverFreightVendor.add(receivable.getPayableWithTax() == null ? BigDecimal.ZERO : receivable.getPayableWithTax());
            //排除自营承运商（计算毛利额、毛利率时需排除）
            if(null != ownVendorMap.get(receivable.getVendorId())){continue;}
            sumShow4DriverFreight = sumShow4DriverFreight.add(receivable.getPayableWithTax() == null ? BigDecimal.ZERO : receivable.getPayableWithTax());
            receivableWithTax = receivableWithTax.add(receivable.getReceivableWithTax() == null ? BigDecimal.ZERO : receivable.getReceivableWithTax());
        }

        //对账时承运侧含税金额
        if(initValue){
            receivableVo.setVendorFreightWithTax(sumShow4DriverFreightVendor);
        }else{
            receivableVo.setVendorFreightWithTax(sumShow4DriverFreightVendor.compareTo(BigDecimal.ZERO)  == 0 ? null : sumShow4DriverFreightVendor);
        }
        //对账时毛利额
        if(sumShow4DriverFreight.compareTo(BigDecimal.ZERO) != 0){
            receivableVo.setProfit(receivableWithTax.subtract(sumShow4DriverFreight));
            //对账时毛利率
            if(receivableWithTax.compareTo(BigDecimal.ZERO) != 0){
                profitRate = (receivableWithTax.subtract(sumShow4DriverFreight)).divide(receivableWithTax, 4,BigDecimal.ROUND_HALF_UP);
                receivableVo.setProfitRate(BaseUtil.formatDecimal(profitRate.doubleValue()));
            }
        }

        //对账时与承诺毛利差：项目承诺毛利率 - 对账时毛利率
        if(null != profitRate){
            receivableVo.setProfitRateDiffer(BaseUtil.formatDecimal(profitRate.subtract(profitRatePromise).doubleValue()));
        }
    }

    private void buildMonthAmount(ReconcilicationForReceivableVo receivableVo, ReconcilicationForReceivable reconciliation, List<Waybill> waybills) {
        if(CollectionUtils.isNotEmpty(waybills)){
            //项目年、月（运单用车时间）
            Calendar cal = Calendar.getInstance();
            cal.setTime(waybills.get(0).getPlanDeliveryTime());
            receivableVo.setYear(cal.get(Calendar.YEAR));
            receivableVo.setMonth(cal.get(Calendar.MONTH)+1);

            //判断对账单运单是否在同一个月
            Boolean sameMonth = waybillCommonService.checkPlanDeliveryTimeInSameMonth(waybills);
            if(sameMonth && null != reconciliation.getProjectId()){
                WaybillStatisticsAmountVO amountVO = waybillCommonService.getMonthAmountInfo(reconciliation.getProjectId(), waybills.get(0).getPlanDeliveryTime());
                receivableVo.setMonthProfit(amountVO.getMonthProfitAmount());
                if(null != amountVO.getMonthProportion()){
                    receivableVo.setMonthProfitRate(BaseUtil.formatDecimal(amountVO.getMonthProportion().doubleValue()));
                }
                if(null != amountVO.getMonthProportionGap()){
                    receivableVo.setMonthProfitRateDiffer(BaseUtil.formatDecimal(amountVO.getMonthProportionGap().doubleValue()));
                }
            }
        }
    }

    private void buildAdjustAmount(ReconcilicationForReceivableVo receivableVo, ReconcilicationForReceivable reconciliation, List<Waybill> waybills,Map<Integer, Byte> ownVendorMap, BigDecimal profitRatePromise) {
        BigDecimal sumFreightWithTax = BigDecimal.ZERO;
        BigDecimal vendorFreightWithTax = BigDecimal.ZERO;
        BigDecimal adjustProfitRate = null;

        List<Integer> excludeWaybillIds = new ArrayList<>();
        for (Waybill waybill : waybills) {
            if(null != ownVendorMap.get(waybill.getVehicleToVendor())){continue;}
            excludeWaybillIds.add(waybill.getWaybillId());
        }

        List<WaybillAmount> waybillAmounts = waybillAmountService.findByWaybillIds(excludeWaybillIds,null);
        for (WaybillAmount amount : waybillAmounts) {
            sumFreightWithTax = sumFreightWithTax.add(amount.getLastCustomerFreightWithTax());
            vendorFreightWithTax = vendorFreightWithTax.add(amount.getLastVendorFreightWithTax());
        }

        //对账单审批通过&调整单审批通过展示调整金额
        if (reconciliation.getApprovalStatus() != null && reconciliation.getApprovalStatus()
                .equals(ReconcilicationForReceivableEnum.ApprovalStatusStatus.AGREE.getCode())) {
            //判断运单是否有调整
            Boolean hadAdjust = reconcilicationForPayableService.checkWaybillHadAdjust(excludeWaybillIds);
            if(hadAdjust){
                //调整后的毛利额
                if(sumFreightWithTax.compareTo(BigDecimal.ZERO) != 0 || vendorFreightWithTax.compareTo(BigDecimal.ZERO) != 0){
                    receivableVo.setAdjustProfit(sumFreightWithTax.subtract(vendorFreightWithTax));
                }
                //调整后的毛利率
                if(vendorFreightWithTax.compareTo(BigDecimal.ZERO) != 0){
                    adjustProfitRate = (sumFreightWithTax.subtract(vendorFreightWithTax)).divide(sumFreightWithTax, 4, BigDecimal.ROUND_HALF_UP);
                    receivableVo.setAdjustProfitRate(BaseUtil.formatDecimal(adjustProfitRate.doubleValue()));
                }
                //调整后与承诺毛利率差
                if(null != adjustProfitRate){
                    receivableVo.setAdjustProfitRateDiffer(BaseUtil.formatDecimal(adjustProfitRate.subtract(profitRatePromise).doubleValue()));
                }
            }
        }
    }

    private void buildItemBo(List<ReconcilicationForReceivableItem> receivables, Map<Integer, Integer> vendorIdMap, List<ReconcilicationForReceivableItemBo> itemBos) {
        for (ReconcilicationForReceivableItem receivableItem : receivables) {
            ReconcilicationForReceivableItemBo receivableItemBo = new ReconcilicationForReceivableItemBo();
            BeanUtils.copyProperties(receivableItem, receivableItemBo);
            receivableItemBo.setVendorId(vendorIdMap.get(receivableItem.getWaybillId()));
            itemBos.add(receivableItemBo);
        }
    }

    private void buildVendorData(List<Waybill> waybills, Map<Integer, Integer> vendorIdMap, List<Integer> vendorIds) {
        for (Waybill waybill : waybills) {
            vendorIdMap.put(waybill.getWaybillId(), waybill.getVehicleToVendor());
            vendorIds.add(waybill.getVehicleToVendor());
        }
    }

    @Override
    public ReconcilicationForReceivable findReconciliationByProcessInstanceId(String processInstanceId)
            throws BusinessException {

        ReconcilicationForReceivableExample example = new ReconcilicationForReceivableExample();
        ReconcilicationForReceivableExample.Criteria criteria = example.createCriteria();
        criteria.andProcessInstanceIdEqualTo(processInstanceId);

        List<ReconcilicationForReceivable> receivables = reconcilicationForReceivableMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(receivables)) return null;
        return receivables.get(0);
    }

    @Override
    public ReconcilicationForReceivable findReconciliationByReconciliationNo(String reconciliationNo)
            throws BusinessException {

        ReconcilicationForReceivableExample example = new ReconcilicationForReceivableExample();
        ReconcilicationForReceivableExample.Criteria criteria = example.createCriteria();
        criteria.andReconcilicationNoEqualTo(reconciliationNo);

        List<ReconcilicationForReceivable> receivables = reconcilicationForReceivableMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(receivables)) return null;
        return receivables.get(0);
    }

    @Override
    public void batchSendInvoiceMessage(List<Integer> receivableConciliationIds) throws BusinessException {
        if( CollectionUtils.isEmpty(receivableConciliationIds) ){ return; }
        for (Integer reconciliationId : receivableConciliationIds){
            ReconcilicationForReceivable receivable = reconcilicationForReceivableMapper.selectByPrimaryKey(reconciliationId);
            if( null == receivable ){ continue; }
            LoginEmployee loginEmployee = new LoginEmployee();
            loginEmployee.setUserId(receivable.getCreateUserId());
            loginEmployee.setTenantId(receivable.getTenantId());
            loginEmployee.setTenantCode(receivable.getTenantCode());
            sendMessageToInvoice(receivable, loginEmployee);
        }
    }

    /**
     * 处理工作流消息
     *
     * @param message
     * @param loginEmployee
     */
    private void handleWorkFlowMsg(WorkFlowMessage message, ReconcilicationForReceivable master, LoginEmployee loginEmployee) {
        if(null == message.getStatus()){ return;}
        String workFlowKey = message.getStatus().getWorkFlowKey();
        master.setApprovalStatus(ApprovalStatus.getApprovalStatusWithWorkKey(workFlowKey));
        reconcilicationForReceivableMapper.updateByPrimaryKeySelective(master);

        if (ApprovalStatus.APPROVAL_AGREE.getWorkFlowKey().equals(workFlowKey)){
            // 将运单的对账状态更改为已对账
            updateWaybillToHasReconciliation(master.getReconcilicationNo());
            this.doSendWorkFlowPassMsg(master, loginEmployee);
            sendMessageToInvoice(master, loginEmployee);
        }
    }

    /**发送消息给开票系统**/
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
        event.setTenantId(master.getTenantId());
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
        List<ReconcilicationForReceivableItem> items = findReceivableItemsByReconciliationId(master.getReconcilicationId());
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
            if( StringUtils.isBlank(companyTaxNumber) ){
                companyTaxNumber = waybill.getContractToCompanyCreditCode();
            }
        }
        if( StringUtils.isNotBlank(companyTaxNumber) ){
            event.setCompanyTaxNumber(companyTaxNumber);
        }
        Project project = projectService.getProjectV2(master.getProjectId());
        if( null != project ) {
            // 运单纳税人识别号优先:运单号无纳税人识别号,才用项目的纳税人识别号
            if(StringUtils.isBlank(event.getCompanyTaxNumber())){
                event.setCompanyTaxNumber(project.getContractToCompanyCreditCode());
            }
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

    /**
     * 发送消息到fms
     *
     * @param master
     * @param loginEmployee
     */
    private void doSendWorkFlowPassMsg(ReconcilicationForReceivable master, LoginEmployee loginEmployee) {

        FmsMQMessageDTO<SourceLogisticsDTO> fmsMQMessageDTO = new FmsMQMessageDTO<SourceLogisticsDTO>();
        fmsMQMessageDTO.setBusinessSystem(BusinessSystemEnum.TMS.getCode());
        // fmsMQMessageDTO.setBusinessType(BusinessTypeEnum.LOGISTICS.getCode());
        SourceLogisticsDTO sourceLogisticsDTO = new SourceLogisticsDTO();
        sourceLogisticsDTO.setAreaCode(master.getAreaCode());
        sourceLogisticsDTO.setSourceNoteNo(master.getReconcilicationNo());
        sourceLogisticsDTO.setSourceBizNo(master.getReconcilicationNo());
        sourceLogisticsDTO.setTenantId(master.getTenantId());
        sourceLogisticsDTO.setSourceType(SourceTypeEnum.LOGISTICS_SHIPPER_STATEMENT_BILL.getCode());
        sourceLogisticsDTO.setBillType(BillTypeEnum.STATEMENT_BILL.getCode());
        // sourceLogisticsDTO.setStatisticsType("物流项目");
        // 转化为crm客户id
        Integer crmCustomerId = null;
        if (master.getCustomerId() != null) {
            CustomerInfo customerInfo = customerInfoService.findCusInfoById(master.getCustomerId());
            if (customerInfo != null) {
                crmCustomerId = customerInfo.getCrmCustomerId();
            }
        }
        sourceLogisticsDTO.setTransactionalAccountNo(String.valueOf(crmCustomerId));
        sourceLogisticsDTO.setTransactionalAccountName(master.getCustomerName());
        sourceLogisticsDTO.setTransactionalAccountType(TransactionalAccountTypeEnum.OWNER.getCode());

        sourceLogisticsDTO.setEntityId(getCustomerJumaPin(crmCustomerId));
        // sourceLogisticsDTO.setSourceNoteDate(master.getSubmitTime());

        List<SourceLogisticsDetailDTO> sourceLogisticsDetailDTOList = makeDetails(master.getReconcilicationId(),
                loginEmployee, sourceLogisticsDTO);
        sourceLogisticsDTO.setDetailList(sourceLogisticsDetailDTOList);

        fmsMQMessageDTO.setData(sourceLogisticsDTO);
        log.info("Receivable Send To FMS : {}.", JSONObject.toJSONString(fmsMQMessageDTO));
        sendService.send(fmsMQMessageDTO);

        //签约、运营不一致  生成关联单据
//        reconciliationLink(master);
        reconciliationCommonService.validAndCreateReconciliationLink(master);
    }

    private String getCustomerJumaPin(Integer crmCustomerId) {
        if (crmCustomerId == null) return null;
        com.juma.crm.customer.domain.CustomerInfo crmCustomerInfo = crmService.find(crmCustomerId, null);
        return crmCustomerInfo == null ? null : crmCustomerInfo.getJumaPin();
    }

    private List<SourceLogisticsDetailDTO> makeDetails(Integer reconciliationId, LoginEmployee loginEmployee,
                                                       SourceLogisticsDTO sourceLogisticsDTO) {

        List<SourceLogisticsDetailDTO> detailDTOS = new ArrayList<>();

        ReconcilicationForReceivableItemExample example = new ReconcilicationForReceivableItemExample();
        ReconcilicationForReceivableItemExample.Criteria criteria = example.createCriteria();
        criteria.andReconcilicationIdEqualTo(reconciliationId);
        List<ReconcilicationForReceivableItem> receivables = reconcilicationForReceivableItemMapper
                .selectByExample(example);
        // 子公司id
        Integer departmentId = null;
        String contractToCompanyCreditCode = null;
        String projectName = "";
        for (ReconcilicationForReceivableItem item : receivables) {

            Waybill waybill = waybillCommonService.getWaybillById(item.getWaybillId());
            contractToCompanyCreditCode = waybill.getContractToCompanyCreditCode();
            departmentId = waybill.getDepartmentId();
            projectName = waybill.getProjectName();
            // 查询车架号
            String vehicleFrameNo = "";
            Integer truckType = null;
            if (StringUtils.isNotBlank(waybill.getPlateNumber())) {
                Truck truck = vmsCommonService.loadTruckByPlateNumber(waybill.getPlateNumber());
                if (truck != null) {
                    vehicleFrameNo = truck.getTruckIdentificationNo();
                    truckType = truck.getTruckRunType();
                }
            }

            // 修改后的含税价
            BigDecimal receivableWithTaxAdjust;
            WaybillAmount waybillAmount = waybillAmountService.loadByWaybillId(item.getWaybillId());
            if(waybillAmount == null){
                receivableWithTaxAdjust = waybill.getEstimateFreight();
            }else{
                receivableWithTaxAdjust = waybillAmount.getLastCustomerFreightWithTax();
            }

            // 修改后的税率
            TruckRequire truckRequire = truckRequireService.findTruckRequireByWaybillId(waybill.getWaybillId(), loginEmployee);
            BigDecimal taxRateAdjust = BigDecimal.ZERO;
            if (truckRequire != null) {
                taxRateAdjust = truckRequire.getTaxRateValue();
            }

            // 原始含税价
            BigDecimal receivableWithTax = waybill.getEstimateFreight();
            // //查询最后一条改价记录
            // AdjustForReceivable lastAdjust = adjustForReceivableMapper.findFirst(item.getWaybillId());

            // 调整金额
            long adjustmentPrice;
            // 调整税金额
            long Tax;
            // 调整未含税金额
            long adjustmentWithOutTax;

            // 调整后价格减初始价格
            adjustmentPrice = receivableWithTaxAdjust.subtract(receivableWithTax).multiply(new BigDecimal(100))
                    .longValue();
            // 调整后价格/(1+税率)
            adjustmentWithOutTax = receivableWithTaxAdjust
                    .divide(taxRateAdjust.add(new BigDecimal(1)), 2, RoundingMode.HALF_UP).multiply(new BigDecimal(100))
                    .longValue();
            // 调整后的含税价减不含税价
            Tax = receivableWithTaxAdjust.multiply(new BigDecimal(100)).subtract(new BigDecimal(adjustmentWithOutTax))
                    .longValue();

            SourceLogisticsDetailDTO detailDTO = new SourceLogisticsDetailDTO();

            // 车牌号
            detailDTO.setPlateNumber(waybill.getPlateNumber());
            detailDTO.setTruckType(truckType == null ? "" : TruckRunTypeEnum.getDescByCode(truckType));
            // 司机名称
            detailDTO.setDriverName(waybill.getDriverName());
            // 司机类型
            detailDTO.setDriverType(waybill.getDriverType() == null ? ""
                    : DriverTypeEnum.getDescByCode(waybill.getDriverType()));
            // 客户运费
            detailDTO.setCustomerFreight(waybill.getEstimateFreight() == null ? 0
                    : waybill.getEstimateFreight().multiply(new BigDecimal(100 + "")).longValue());
            // 承运商运费
            detailDTO.setVendorFreight(waybill.getShow4DriverFreight() == null ? 0
                    : waybill.getShow4DriverFreight().multiply(new BigDecimal(100 + "")).longValue());

            detailDTO.setSourceBizType(BusinessDetailTypeEnum.WAYBILL.getCode());
            detailDTO.setSourceBizNo(waybill.getWaybillNo());
            detailDTO.setUseCarTime(waybill.getPlanDeliveryTime());
            detailDTO.setWaybillCompleteTime(waybill.getFinishTime());
            detailDTO.setLogisticsName(waybill.getProjectName());
            detailDTO.setVin(vehicleFrameNo);
            detailDTO.setPriceExcludingTax(adjustmentWithOutTax);
            detailDTO.setPriceIncludingTax(receivableWithTaxAdjust.multiply(new BigDecimal(100)).longValue());
            detailDTO.setTax(Tax);
            detailDTO.setTaxRate(taxRateAdjust);
            detailDTO.setBeforePriceIncludingTax(BigDecimal.ZERO.longValue());
            detailDTO.setAdjustmentPrice(BigDecimal.ZERO.longValue());

            //查询最后一条改价记录
            AdjustForReceivable lastAdjust = adjustForReceivableMapper.findFirst(item.getWaybillId());
            if( null != lastAdjust ){
                detailDTO.setBeforePriceIncludingTax(lastAdjust.getReceivableWithTax().multiply(new BigDecimal(100)).longValue());
                BigDecimal oldAdjustmentPrice = lastAdjust.getReceivableWithTax().subtract(lastAdjust.getReceivableWithTaxAdjust()).multiply(new BigDecimal(100));
                detailDTO.setAdjustmentPrice(oldAdjustmentPrice.longValue());
            }

            detailDTOS.add(detailDTO);
        }

        sourceLogisticsDTO.setUniformSocialCreditCode(contractToCompanyCreditCode);
        // 设置子公司信息
        if (departmentId != null) {
            sourceLogisticsDTO.setSubCompanyId(departmentId);
            Department department = departmentService.loadDepartment(departmentId);
            sourceLogisticsDTO.setSubCompanyCode(department == null ? "" : department.getDepartmentCode());
            sourceLogisticsDTO.setSubCompanyName(department == null ? "" : department.getBusinessLicenceName());
        }

        sourceLogisticsDTO.setStatisticsType(projectName);

        return detailDTOS;
    }

    /**
     * v2 版本通过对账单号更新运单状态
     *
     * @param reconciliationNo
     */
    private void updateWaybillToHasReconciliation(String reconciliationNo) {
        log.info("开始执行对账操作reconciliationId：{}", reconciliationNo);
        if (null == reconciliationNo) {
            return;
        }

        List<Waybill> waybills = waybillCommonService.findByReceivableReconciliationNo(reconciliationNo);

        for (Waybill waybill : waybills) {
            if (NumberUtils.compare(Waybill.ReconciliationStatus.IN_THE_ACCOUNT.getCode(),
                    waybill.getReceivableReconcilicationStatus()) == 0) {
                waybill.setReceivableReconcilicationStatus(Waybill.ReconciliationStatus.HAS_RECONCILIATION.getCode());
            }
            waybill.setReceiptStatus(Waybill.ReceiptStatus.NOT_COLLECTION.getCode());
        }

        waybillCommonService.batchUpdateReceivableReconcilicationNo(waybills);
    }

    @Override
    public void updateReconciliation(ReconcilicationForReceivable reconcilicationForReceivable) {
        reconcilicationForReceivableMapper.updateByPrimaryKeySelective(reconcilicationForReceivable);
    }

    @Override
    public List<ReconcilicationForReceivableItem> findReceivableItemsByReconciliationId(Integer reconciliationId) {
        ReconcilicationForReceivableItemExample example = new ReconcilicationForReceivableItemExample();
        ReconcilicationForReceivableItemExample.Criteria criteria = example.createCriteria();
        criteria.andReconcilicationIdEqualTo(reconciliationId);
        return reconcilicationForReceivableItemMapper.selectByExample(example);
    }

    @Override
    public List<ReconcilicationForReceivableItemExcelVo> findReceivableItemByReconciliationId(Integer reconciliationId) {

        ReconcilicationForReceivableItemExample example = new ReconcilicationForReceivableItemExample();
        ReconcilicationForReceivableItemExample.Criteria criteria = example.createCriteria();
        criteria.andReconcilicationIdEqualTo(reconciliationId);
        List<ReconcilicationForReceivableItem> receivables = reconcilicationForReceivableItemMapper
                .selectByExample(example);
        if (receivables == null || receivables.isEmpty()) {
            return null;
        }

        List<ReconcilicationForReceivableItemExcelVo> itemExcelVos = new ArrayList<>();
        List<ReconcilicationForReceivableItemVo> itemVos = makeItemVo(receivables);

        for (ReconcilicationForReceivableItemVo itemVo : itemVos) {

            ReconcilicationForReceivableItemExcelVo itemExcelVo = new ReconcilicationForReceivableItemExcelVo();
            if (itemVo != null) {
                BeanUtils.copyProperties(itemVo, itemExcelVo);
                if (itemVo.getDriverType() != null) {
                    itemExcelVo.setDriverType(DriverTypeEnum.getDescByCode(itemVo.getDriverType()));
                }
                if (StringUtils.isBlank(itemVo.getDriverName())) {
                    itemExcelVo.setVendorOrDriver(itemVo.getVendorName());
                } else {
                    itemExcelVo.setVendorOrDriver(itemVo.getDriverName());
                }
                itemExcelVos.add(itemExcelVo);
            }
        }

        return itemExcelVos;
    }

    @Override
    public void finishWorkFlowTask(WorkFlowMessage message) throws BusinessException {
        if(null == message || StringUtils.isBlank(message.getNumber())){
            return;
        }
        LoginEmployee loginEmployee = message.getLoginEmployee();
        ReconcilicationForReceivable master = null;
        if(StringUtils.isNotBlank(message.getBusinessKey()) && NumberUtils.isNumber(message.getBusinessKey())){
            master = reconcilicationForReceivableMapper.selectByPrimaryKey(Integer.valueOf(message.getBusinessKey()));
        }else {
            //对工作流未返回businessKey容错处理
            master = this.findReconciliationByReconciliationNo(message.getNumber());
        }

        if (null == master) {
            throw new BusinessException("reconciliation", "errors.reconciliation.notExist");
        }
        if (master.getApprovalStatus() != null && Integer.valueOf(master.getApprovalStatus())
                .equals(ReconcilicationForReceivableEnum.ApprovalStatusStatus.Append.getCode())) {
            throw new BusinessException("reconciliationStatus", "该对账单未提交审核");
        }
        if (master.getApprovalStatus() != null && Integer.valueOf(master.getApprovalStatus())
                .equals(ReconcilicationForReceivableEnum.ApprovalStatusStatus.AGREE.getCode())) {
            throw new BusinessException("reconciliationStatus", "该对账单已经审核通过");
        }

        try {
            this.handleWorkFlowMsg(message, master, loginEmployee);
        } catch (Exception e) {
            log.error("AR reconciliation id is {},error is {}: ", master.getReconcilicationNo(), e);
            if (e instanceof BusinessException) {
                throw e;
            }
            throw new BusinessException("unknow.error", "外部错误:" + e.getMessage());
        }
    }

    @Override
    public List<ReconcilicationForCompany> findReconcilicationForCompanyByReconcilicationId(Integer reconcilicationId)
            throws BusinessException {
        ReconcilicationForCompanyExample example = new ReconcilicationForCompanyExample()
                .createCriteria().andReconcilicationReceivableIdEqualTo(reconcilicationId).example();
        return reconcilicationForCompanyMapper.selectByExample(example);
    }

    @Override
    public void sendReconcilicationForCompanyLink(Integer reconcilicationId) {
//        reconciliationLink(reconcilicationForReceivableMapper.selectByPrimaryKey(reconcilicationId));
        reconciliationCommonService.validAndCreateReconciliationLink(reconcilicationForReceivableMapper.selectByPrimaryKey(reconcilicationId));
    }

    public static void main(String[] args) {

        FmsMQMessageDTO<PayableMessage> a2b = new FmsMQMessageDTO<PayableMessage>();
        a2b.setBusinessSystem(BusinessSystemEnum.TMS.getCode());

        PayableMessage data = new PayableMessage();
        data.setTenantId(1);

        System.out.println(JSONObject.toJSONString(a2b));
    }

    @Override
    public void sendToFMS(String reconcilicationNo) throws BusinessException {
        ReconcilicationForReceivableExample example = new ReconcilicationForReceivableExample();
        example.createCriteria().andReconcilicationNoEqualTo(reconcilicationNo);

        List<ReconcilicationForReceivable> rows = reconcilicationForReceivableMapper.selectByExample(example);
        if (rows.isEmpty() || rows.size() > 1) {
            throw new BusinessException("DataError", "一个对账单号查询出了多条记录或为空");
        }
        ReconcilicationForReceivable master = rows.get(0);
        //更新对账单审核状态为已通过
        master.setApprovalStatus(ReconcilicationForReceivableEnum.ApprovalStatusStatus.AGREE.getCode());
        reconcilicationForReceivableMapper.updateByPrimaryKeySelective(master);
        // 将运单的对账状态更改为已对账
        updateWaybillToHasReconciliation(master.getReconcilicationNo());

        LoginEmployee loginEmployee = new LoginEmployee();
        loginEmployee.setUserId(master.getCreateUserId());
        loginEmployee.setTenantId(master.getTenantId());
        loginEmployee.setTenantCode(master.getTenantCode());
        this.doSendWorkFlowPassMsg(master, loginEmployee);
    }
}
