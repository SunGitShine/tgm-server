package com.juma.tgm.filiale.waybill.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.giants.common.exception.BusinessException;
import com.juma.auth.conf.domain.BusinessArea;
import com.juma.auth.conf.service.BusinessAreaService;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.tenant.domain.Tenant;
import com.juma.auth.tenant.service.TenantService;
import com.juma.auth.user.domain.LoginUser;
import com.juma.conf.domain.ConfParamOption;
import com.juma.crm.customer.domain.Customer4TmsBo;
import com.juma.crm.customer.domain.ProductsLableInfo;
import com.juma.crm.support.service.Crm4TmsService;
import com.juma.tgm.common.BaseUtil;
import com.juma.tgm.common.Constants;
import com.juma.tgm.common.DateUtil;
import com.juma.tgm.common.FreightEnum;
import com.juma.tgm.configure.domain.FreightFactor;
import com.juma.tgm.configure.service.FreightFactorService;
import com.juma.tgm.configure.service.ServiceConfService;
import com.juma.tgm.crm.domain.CustomerInfo;
import com.juma.tgm.crm.service.CustomerInfoService;
import com.juma.tgm.driver.domain.ReportInfoDetails;
import com.juma.tgm.driver.service.DriverService;
import com.juma.tgm.filiale.service.FilialeBillService;
import com.juma.tgm.filiale.waybill.utils.FilialeBillUtil;
import com.juma.tgm.filiale.waybill.utils.FilialeMsgWithBusinessUtils;
import com.juma.tgm.project.domain.Project;
import com.juma.tgm.project.domain.ProjectFreightRule;
import com.juma.tgm.project.domain.RoadMap;
import com.juma.tgm.project.domain.ValuationWay;
import com.juma.tgm.project.domain.v2.enums.ProjectEnum;
import com.juma.tgm.project.enumeration.ValuationWayEnum;
import com.juma.tgm.project.service.ProjectMemberService;
import com.juma.tgm.project.service.ProjectService;
import com.juma.tgm.project.service.RoadMapService;
import com.juma.tgm.project.vo.ProjectBillVo;
import com.juma.tgm.project.vo.ProjectFreightRuleVo;
import com.juma.tgm.receiptManage.service.ReceiptManageService;
import com.juma.tgm.region.service.RegionTgmService;
import com.juma.tgm.reportInfo.service.ReportInfoDetailService;
import com.juma.tgm.reportInfo.service.ReportInfoService;
import com.juma.tgm.sop.domain.Sop;
import com.juma.tgm.sop.service.SopService;
import com.juma.tgm.tools.service.VmsCommonService;
import com.juma.tgm.truck.domain.Truck;
import com.juma.tgm.truck.domain.TruckType;
import com.juma.tgm.truck.service.TruckService;
import com.juma.tgm.truck.service.TruckTypeFreightService;
import com.juma.tgm.truck.service.TruckTypeService;
import com.juma.tgm.util.CustomerManWaybillUtils;
import com.juma.tgm.waybill.domain.DistanceAndPriceData;
import com.juma.tgm.waybill.domain.TruckRequire;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.Waybill.WaybillSource;
import com.juma.tgm.waybill.domain.WaybillBindFence;
import com.juma.tgm.waybill.domain.WaybillBo;
import com.juma.tgm.waybill.domain.WaybillDeliveryAddress;
import com.juma.tgm.waybill.domain.WaybillDetailInfo;
import com.juma.tgm.waybill.domain.WaybillOperateTrack;
import com.juma.tgm.waybill.domain.WaybillParam;
import com.juma.tgm.waybill.domain.WaybillReceiveAddress;
import com.juma.tgm.waybill.enumeration.WaybillOperateTrackEnum.OperateApplication;
import com.juma.tgm.waybill.enumeration.WaybillOperateTrackEnum.OperateType;
import com.juma.tgm.waybill.service.BuildVehicleTypeAndVendorService;
import com.juma.tgm.waybill.service.DeliveryPointSupplementService;
import com.juma.tgm.waybill.service.TruckRequireService;
import com.juma.tgm.waybill.service.WaybillAutoFenceServicve;
import com.juma.tgm.waybill.service.WaybillCheckService;
import com.juma.tgm.waybill.service.WaybillCommonService;
import com.juma.tgm.waybill.service.WaybillDeliveryAddressService;
import com.juma.tgm.waybill.service.WaybillOperateTrackService;
import com.juma.tgm.waybill.service.WaybillParamService;
import com.juma.tgm.waybill.service.WaybillReceiveAddressService;
import com.juma.tgm.waybill.service.WaybillService;
import com.juma.tgm.waybill.service.WaybillTransformToCarrierService;
import com.juma.vms.driver.domain.Driver;
import com.juma.vms.external.service.VmsService;
import com.juma.vms.transport.domain.CapacityPool;
import com.juma.vms.vendor.domain.Vendor;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: FilialeBillServiceImpl
 * @Description:
 * @author: liang
 * @date: 2017-09-27 10:44
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
@Service
public class FilialeBillServiceImpl implements FilialeBillService {

    private static final Logger log = LoggerFactory.getLogger(FilialeBillServiceImpl.class);

    @Resource
    private WaybillService waybillService;

    @Resource
    private CustomerInfoService customerInfoService;

    @Resource
    private TruckService truckService;

    @Resource
    private DriverService driverService;

    @Resource
    private TruckTypeService truckTypeService;

    @Resource
    private ProjectService projectService;

    @Resource
    private TruckRequireService truckRequireService;

    @Resource
    private RegionTgmService regionTgmService;

    @Resource
    private WaybillDeliveryAddressService waybillDeliveryAddressService;

    @Resource
    private WaybillReceiveAddressService waybillReceiveAddressService;

    @Resource
    private WaybillParamService waybillParamService;

    @Resource
    private TruckTypeFreightService truckTypeFreightService;

    @Resource
    private WaybillCommonService waybillCommonService;

    @Resource
    private DeliveryPointSupplementService deliveryPointSupplementService;

    @Resource
    private ReportInfoDetailService reportInfoDetailService;

    @Resource
    private ServiceConfService serviceConfService;

    /**
     * 带业务逻辑的发送消息
     */
    @Resource
    private FilialeMsgWithBusinessUtils filialeMsgWithBusinessUtils;


    @Resource
    private ReceiptManageService receiptManageService;

    @Resource
    private FreightFactorService freightFactorService;

    @Resource
    private SopService sopService;

    @Resource
    private WaybillAutoFenceServicve waybillAutoFenceServicve;

    @Resource
    private WaybillTransformToCarrierService waybillTransformToCarrierService;

    @Resource
    private TenantService tenantService;

    /**
     * 用于处理客户经理订单超时通知
     */
    @Resource
    private CustomerManWaybillUtils customerManWaybillUtils;

    @Autowired
    private ReportInfoService reportInfoService;

    @Autowired
    private BusinessAreaService businessAreaService;

    @Autowired
    private RoadMapService roadMapService;

    @Autowired
    private VmsService vmsService;

    @Autowired
    private WaybillOperateTrackService waybillOperateTrackService;

    @Resource
    private BuildVehicleTypeAndVendorService buildVehicleTypeAndVendorService;

    @Resource
    private WaybillCheckService waybillCheckService;

    @Resource
    private Crm4TmsService crm4TmsService;

    @Resource
    private ProjectMemberService projectMemberService;
    @Resource
    private VmsCommonService vmsCommonService;


    // 自动建单成功 发送应用消息
    private static final String TMS_DO_AUTO_CREATE_BILL_TASK_MSG = "TMS_DO_AUTO_CREATE_BILL_TASK_MSG";

    // 自动建单失败发送应用消息
    private static final String TMS_DO_AUTO_s_BILL_TASK_ERROR_MSG = "TMS_DO_AUTO_CREATE_BILL_TASK_ERROR_MSG";

    // 自动建单失败发送短信消息
    private static final String TMS_DO_AUTO_CREATE_BILL_TASK_ERROR_SMS = "TMS_DO_AUTO_CREATE_BILL_TASK_ERROR_SMS";

    @Override
    public List<Integer> createProjectBill(ProjectBillVo projectBillVo, LoginUser loginUser) {
        if (projectBillVo == null) throw new BusinessException("waybillNull", "errors.paramCanNotNullWithName", "参数");

        Waybill waybill = projectBillVo.getWaybill();
        if (waybill == null) throw new BusinessException("waybillNull", "errors.paramCanNotNullWithName", "运单信息");

        //校验项目信息，通过后返回数据库项目
        com.juma.tgm.project.domain.v2.Project project = checkProject(projectBillVo.getProject().getProjectId(), waybill.getWaybillSource(), loginUser);

        TruckRequire truckRequire = projectBillVo.getTruckRequire();
        if (truckRequire == null)
            throw new BusinessException("truckRequireNull", "errors.paramCanNotNullWithName", "用车要求");

        //转单参数校验
        if (projectBillVo.getWaybillCarrierVo() != null) {
            waybillTransformToCarrierService.transformBillCheck(projectBillVo.getWaybillCarrierVo());
        }

        waybill.setProjectId(project.getProjectId());
        waybill.setProjectName(project.getName());
        waybill.setEstimateDistance(0);
        waybill.setTest(loginUser.isTest());

        if (waybill.getShow4DriverFreight() == null) {
            waybill.setShow4DriverFreight(new BigDecimal("0"));
        }

        // 作为查询条件使用
        waybill.setGoodsWeight(StringUtils.isBlank(truckRequire.getGoodsWeight()) ? null
            : (new BigDecimal(truckRequire.getGoodsWeight()).multiply(new BigDecimal("1000"))).intValue());
        waybill.setGoodsVolume(StringUtils.isBlank(truckRequire.getGoodsVolume()) ? null
            : new BigDecimal(truckRequire.getGoodsVolume()));

        // 组装路线由谁填写工作量
        if (StringUtils.isNotBlank(projectBillVo.getWaybillParam().getProjectFreightRuleJson())) {
            ProjectFreightRule freightRule = JSON.parseObject(projectBillVo.getWaybillParam().getProjectFreightRuleJson(), ProjectFreightRule.class);
            if (null != freightRule) {
                RoadMap roadMap = roadMapService.getRoadMap(waybill.getRoadMapId());
                if (null != roadMap) {
                    freightRule.setWhoWriteWork(roadMap.getWhoWriteWork() == null ? 1 : roadMap.getWhoWriteWork().intValue());
                    projectBillVo.getWaybillParam().setProjectFreightRuleJson(JSON.toJSONString(freightRule));
                }
            }
        }

        this.waybillCheck(waybill, projectBillVo.getDeliveryAddress(), projectBillVo.getReceiveAddress(), truckRequire, loginUser);

        TruckType truckType = truckTypeService.getTruckType(truckRequire.getTruckTypeId());
        if (truckType != null) {
            this.checkCarryCapacity(waybill, truckRequire, truckType);
        }
        // 转化为普通运单
        List<Integer> list = this.generateEachWaybill(projectBillVo, loginUser);
        if (null == list) {
            return list;
        }

        for (Integer waybillId : list) {
            addWaybillOperateTrack(waybillId, loginUser);
        }
        return list;

    }

    /**
     * 为每个车生成运单
     *
     * @param projectBillVo
     * @param loginUser
     */
    private List<Integer> generateEachWaybill(ProjectBillVo projectBillVo, LoginUser loginUser) {
        Waybill waybill = projectBillVo.getWaybill();
        List<WaybillDeliveryAddress> deliveryAddress = projectBillVo.getDeliveryAddress();
        List<WaybillReceiveAddress> receiveAddress = projectBillVo.getReceiveAddress();
        TruckRequire truckRequire = projectBillVo.getTruckRequire();

        // 结算方式
        waybill.setReceiptType(Waybill.ReceiptType.PROJECTPAY.getCode());
        // 回单
        boolean needReceipt = truckRequireService.needNeedReceipt(truckRequire, null);
        waybill.setNeedReceipt(needReceipt ? Waybill.NeedReceipt.NOT_HAVE_UPLOAD.getCode() : Waybill.NeedReceipt.NO_NEED_RECEIPT.getCode());
        // 改价审核
        waybill.setUpdateFreightAuditStatus(0);
        waybill.setReconciliationStatus(Waybill.ReconciliationStatus.INIT.getCode());
        waybill.setReceivableReconcilicationStatus(Waybill.ReconciliationStatus.INIT.getCode());
        //结算状态
        waybill.setSettlementStatus(Waybill.SettlementStatus.INIT.getCode());
        //收款状态
        waybill.setReceiptStatus(Waybill.ReceiptStatus.INIT.getCode());

        waybill.setEntryLicense(truckRequire.getEntryLicense());// 为了运单池过滤
        // 是否结算
//        waybill.setCheckout(false);
        // 业务区域
        CustomerInfo customerInfo = customerInfoService.findCusInfoById(waybill.getCustomerId());
        waybill.setAreaCode(customerInfo.getAreaCode());
        waybill.setCustomerName(customerInfo.getCustomerName());
        waybill.setCustomerManagerId(customerInfo.getCustomerManagerUserId());
        // 写入租户信息
        waybill.setTenantCode(customerInfo.getTenantCode());
        waybill.setTenantId(customerInfo.getTenantId());

        // 搬运费--每辆车各自对应
        WaybillBo waybillBo = new WaybillBo();
        waybillBo.setWaybill(waybill);
        waybillBo.setTruckRequire(truckRequire);
        waybillBo.setReceiveAddress(receiveAddress);
        waybillBo.setDeliveryAddress(deliveryAddress);
        waybillBo.setListReceiveAddressResponse(projectBillVo.getWaybillReceiveAddressResponseList());
        WaybillParam waybillParam = null;
        if (projectBillVo.getWaybillParam() == null) {
            waybillParam = new WaybillParam();
            projectBillVo.setWaybillParam(waybillParam);
        } else {
            waybillParam = projectBillVo.getWaybillParam();
        }
        Sop sop = sopService.findNewestVersionSopByTenantId(loginUser.getTenantId());
        if (sop == null) {
            throw new BusinessException("errors.notFound.Sop", "errors.notFound.Sop", loginUser.getTenantId());
        }
        // 不取配置的搬运费
        waybillParam.setSopId(sop.getSopId());
        waybillParam.setDriverHandlingCost(new BigDecimal("0"));
        waybillParam.setLaborerHandlingCost(new BigDecimal("0"));

        // 计算预估距离
        if (CollectionUtils.isNotEmpty(projectBillVo.getReceiveAddress())) {
            DistanceAndPriceData distance = waybillCommonService.buildDistanceAndPriceData(deliveryAddress.get(0),
                receiveAddress);
            if (distance != null) {
                waybill.setEstimateDistance(distance.getDistance());
                waybill.setRegionCode(distance.getRegionCode());
            }
        } else {
            waybill.setEstimateDistance(0);
        }

        // regionCode
        if (StringUtils.isBlank(waybill.getRegionCode())) {
            String regionCode = regionTgmService.findRegionCodeByCoordinate(deliveryAddress.get(0).getCoordinates());
            if (regionCode.length() > 5) {
                regionCode = regionCode.substring(0, 5);
            }
            waybill.setRegionCode(regionCode);
        }

        this.calculateFixedPrice(projectBillVo);

        // 获取指派的车辆
        com.juma.tgm.project.domain.v2.Project project = projectBillVo.getProject();

        //自动建单
        if (projectBillVo.getAutoCreate() != null && projectBillVo.getAutoCreate()) {
            return this.autoCreateBill(projectBillVo, loginUser, waybill, truckRequire, waybillBo, waybillParam, project);
        }

        // 支持生成调度指派的运单
        if (NumberUtils.compare(waybill.getReceiveWay(), Waybill.ReceiveWay.MANUAL_ASSIGN.getCode()) == 0) {
            return this.createDispatcherBill(projectBillVo, waybillBo, waybillParam, truckRequire, loginUser);
        }

        //自主派车
        if (NumberUtils.compare(waybill.getReceiveWay(), Waybill.ReceiveWay.ASSIGNED.getCode()) == 0) {
            this.projectBillCheck(projectBillVo);
            return createFixedTruckBill(loginUser, waybill, truckRequire, waybillBo, waybillParam, projectBillVo.getFixedTruckIds());
        }

        return null;

    }

    //如果是一口价则计算费用
    private void calculateFixedPrice(ProjectBillVo projectBillVo) {
        WaybillParam waybillParam = projectBillVo.getWaybillParam();

        if (StringUtils.isBlank(waybillParam.getProjectFreightRuleJson())) return;

        ProjectFreightRule projectFreightRule = JSONObject.parseObject(waybillParam.getProjectFreightRuleJson(), ProjectFreightRule.class);

        if (projectFreightRule == null) return;

        if (projectFreightRule.getValuationWay() == null) return;

        if (NumberUtils.compare(projectFreightRule.getValuationWay(), ValuationWayEnum.FIXED_PRICE.getCode()) != 0)
            return;

        Waybill waybill = projectBillVo.getWaybill();

        if (waybill.getEstimateFreight() == null)
            throw new BusinessException("EstimateFreightNullError", "errors.common.prompt", "一口价为空");
        //税后费用
        TruckRequire truckRequire = projectBillVo.getTruckRequire();
        waybill.setAfterTaxFreight(waybill.getEstimateFreight());
        BigDecimal taxRate = truckRequire.getTaxRateValue();
        if (taxRate != null) {
            BigDecimal afterTaxPrice = waybill.getEstimateFreight().divide(BigDecimal.ONE.add(taxRate), 2, BigDecimal.ROUND_HALF_UP);
            waybill.setAfterTaxFreight(afterTaxPrice);
        }
        //系统报价
        waybill.setCalculatedFreight(waybill.getEstimateFreight());

        //司机结算价
        //----减去小工搬运费
        BigDecimal laborerHandlingCost = waybillParam.getLaborerHandlingCost();
        if (laborerHandlingCost != null) {
            BigDecimal tmpPrice = waybill.getAfterTaxFreight().subtract(laborerHandlingCost);

            if (tmpPrice.compareTo(BigDecimal.ZERO) < 0) {
                tmpPrice = BigDecimal.ZERO;
            }
            waybill.setShow4DriverFreight(tmpPrice);
        }
        //----减去返点费
        if (waybill.getProjectId() == null) return;

        Project project = projectService.getProject(waybill.getProjectId());

        if (project == null) return;

        if (project.getRebateRate() == null) return;

        BigDecimal rebatePrice = waybill.getEstimateFreight().multiply(project.getRebateRate());

        BigDecimal driverPrice = waybill.getShow4DriverFreight().subtract(rebatePrice);
        waybill.setShow4DriverFreight(driverPrice);



    }


    /**
     * 自动建单方式
     *
     * @param projectBillVo
     * @param loginUser
     * @param waybill
     * @param truckRequire
     * @param waybillBo
     * @param waybillParam
     * @param project
     * @return
     */
    private List<Integer> autoCreateBill(ProjectBillVo projectBillVo, LoginUser loginUser, Waybill waybill, TruckRequire truckRequire, WaybillBo waybillBo, WaybillParam waybillParam, com.juma.tgm.project.domain.v2.Project project) {
        List<Integer> rstList = null;
        //调度指派的运单
        if (NumberUtils.compare(projectBillVo.getReceiveWay(), Waybill.ReceiveWay.MANUAL_ASSIGN.getCode()) == 0) {
//            try {
            rstList = this.createDispatcherBill(projectBillVo, waybillBo, waybillParam, truckRequire, loginUser);
//            } catch (Exception e1) {
//                log.error("自动建单-调度指派建单失败:", e1);
//                filialeMsgWithBusinessUtils.fixedDemandAutoCreateBillTruckNoMatch(project, loginUser.getUserId(), loginUser);
//            }
            return rstList;
        }

        //车型数量不匹配则改为调度指派
//        try {
        this.projectBillCheck(projectBillVo);
//        } catch (Exception e) {
//            log.error("自动建单-自主派车建单失败:", e);
        //通知客户经理运力不匹配
        //自动建单loginEmployee userId构造为固定需求的客户经理userId
//            filialeMsgWithBusinessUtils.fixedDemandAutoCreateBillTruckNoMatch(project, loginUser.getUserId(), loginUser);
        //车辆检查失败则改为调度指派
//            waybill.setReceiveWay(Waybill.ReceiveWay.MANUAL_ASSIGN.getCode());
//            try {
//                rstList = this.createDispatcherBill(projectBillVo, waybillBo, waybillParam, truckRequire, loginUser);
//            } catch (Exception e1) {
//                log.error("自动建单-调度指派建单失败:", e1);
//        filialeMsgWithBusinessUtils.fixedDemandAutoCreateBillTruckNoMatch(project, loginUser.getUserId(), loginUser);
//            }

//            return rstList;
//        }

        return createFixedTruckBill(loginUser, waybill, truckRequire, waybillBo, waybillParam, projectBillVo.getFixedTruckIds());
    }

    /**
     * 生成固定车辆运单
     *
     * @param loginUser
     * @param waybill
     * @param truckRequire
     * @param waybillBo
     * @param waybillParam
     * @param fixedTruckIds
     * @return
     */
    private List<Integer> createFixedTruckBill(LoginUser loginUser, Waybill waybill, TruckRequire truckRequire,
                                               WaybillBo waybillBo, WaybillParam waybillParam, Set<Integer> fixedTruckIds) {
        // 运单状态
        waybill.setStatus(Waybill.Status.ASSIGNED.getCode());
        waybill.setStatusView(Waybill.StatusView.WATING_DELIVERY.getCode());
        List<Integer> waybillIds = new ArrayList<>();
        for (Integer truckId : fixedTruckIds) {
            this.buildWaybill(waybill, truckId, truckRequire, loginUser);
            // 保存运单信息
            waybillCommonService.insert(waybill, loginUser);
//            addWaybillOperateTrack( waybill , loginUser);
            // 保存用车要求
            // 保存线路信息
            waybillCommonService.insertLinkTable(waybillBo, loginUser);
            // 记录计价规则和搬运费
            waybillCommonService.saveWaybillParam(waybillParam, waybill, loginUser);

            waybillIds.add(waybill.getWaybillId());

            // 推送消息
            filialeMsgWithBusinessUtils.pushAssignWaybillToDriver(waybill.getDriverId(), waybill.getWaybillId());

            // 绑定电子围栏
            waybillAutoFenceServicve.bindWaybillIdAndFenceId(waybill.getWaybillId(), WaybillBindFence.Sign.DELIVERY_ADDRESS, loginUser);
        }

        return waybillIds;
    }

    private List<Integer> createDispatcherBill(ProjectBillVo projectBillVo, WaybillBo waybillBo, WaybillParam waybillParam, TruckRequire truckRequire, LoginUser loginUser) {
        this.checkDisptcherBill(projectBillVo);
        if (waybillBo.getWaybill().getReceiveWay() == null) {
            waybillBo.getWaybill().setReceiveWay(Waybill.ReceiveWay.MANUAL_ASSIGN.getCode());
        }
        // 运单状态
        this.chooseWaybillStatus(waybillBo.getWaybill(), loginUser);
        //默认建一个单子
        if (projectBillVo.getCreateBatchAmount() == null) {
            projectBillVo.setCreateBatchAmount(1);
        }
        List<Integer> waybillIds = new ArrayList<>();
        for (int i = 0; i < projectBillVo.getCreateBatchAmount(); i++) {
            Waybill waybill = this
                .buildWaybillWithoutTruck(waybillBo.getWaybill(), waybillParam, truckRequire);
            // 保存运单信息
            waybillCommonService.insert(waybill, loginUser);
//            addWaybillOperateTrack( waybillBo.getWaybill() , loginUser );
            // 保存用车要求
            // 保存线路信息
            waybillCommonService.insertLinkTable(waybillBo, loginUser);
            // 记录计价规则和搬运费
            waybillCommonService.saveWaybillParam(waybillParam, waybillBo.getWaybill(), loginUser);

            waybillIds.add(waybillBo.getWaybill().getWaybillId());
        }

        return waybillIds;
    }

    /**
     * 选择新建运单时的运单状态
     * 希地租户建单时：Waybill.Status.NO_DRIVER_ANSWER,Waybill.StatusView.WATING_RECEIVE
     * 其他是:Waybill.Status.WATING_RECEIVE,Waybill.StatusView.WATING_RECEIVE
     *
     * @param waybill
     * @param loginUser
     */
    private void chooseWaybillStatus(Waybill waybill, LoginUser loginUser) {
        Tenant current = tenantService.loadTenant(loginUser.getTenantId());
        if (current == null) throw new BusinessException("tenantNotExist", "errors.common.prompt", "租户信息不存在");

        if (StringUtils.equals(current.getTenantKey(), Constants.TENANT_KEY_XIDI_LOGISTICS)) {
            waybill.setStatus(Waybill.Status.NO_DRIVER_ANSWER.getCode());
            waybill.setStatusView(Waybill.StatusView.WATING_RECEIVE.getCode());
        } else {
            waybill.setStatus(Waybill.Status.WATING_RECEIVE.getCode());
            waybill.setStatusView(Waybill.StatusView.WATING_RECEIVE.getCode());
        }

    }

    /**
     * 调度指派校验车型运费规则
     *
     * @param projectBillVo
     */
    private void checkDisptcherBill(ProjectBillVo projectBillVo) {
        //转运项目不需要检查计价规则
        if (NumberUtils.compare(projectBillVo.getWaybill().getWaybillSource(), Waybill.WaybillSource.TRANSFORM_BILL.getCode()) == 0) {
            return;
        }
        //项目维护过的车型
        WaybillParam waybillParam = projectBillVo.getWaybillParam();

        if (StringUtils.isEmpty(waybillParam.getProjectFreightRuleJson()))
            throw new BusinessException("ProjectFreightRuleNull", "errors.common.prompt", "所选车型计价规则为空");
        //当前运单的车型
        TruckRequire truckRequire = projectBillVo.getTruckRequire();

        //三方接口订单不做车型校验
        if (NumberUtils.compare(projectBillVo.getWaybill().getWaybillSource(), Waybill.WaybillSource.INTERFACE.getCode()) == 0) {
            return;
        }
        if (truckRequire.getTruckTypeId() == null)
            throw new BusinessException("truckRequireTruckTypeIdNull", "errors.common.prompt", "运单车型为空");

        ProjectFreightRule projectFreightRule = null;
        try {
            projectFreightRule = JSONObject.parseObject(waybillParam.getProjectFreightRuleJson(), ProjectFreightRule.class);
        } catch (Exception e) {
            log.warn("序列化计价规则错误", e);
            throw new BusinessException("ProjectFreightRuleFormatError", "errors.common.prompt", "当前车型计价规则格式错误");
        }

        //当前运单的车型没有维护计价规则
        if (NumberUtils.compare(projectFreightRule.getTruckTypeId(), truckRequire.getTruckTypeId()) == 0) return;

        throw new BusinessException("ProjectFreightRuleNull", "errors.common.prompt", "当前车型没有计价规则");
    }

    private Waybill buildWaybillWithoutTruck(Waybill waybill, WaybillParam waybillParam, TruckRequire truckRequire) {
        // 运单号
        waybill.setWaybillNo(waybillCommonService.getWaybillNo());
        //第三方接口运单不需要车型计价规则
        if (NumberUtils.compare(waybill.getWaybillSource(), Waybill.WaybillSource.INTERFACE.getCode()) == 0) {
            waybill.setWaybillId(null);

            return waybill;
        }

        // 每个运单的箱型要求不同
        TruckType truckType = truckTypeService.getTruckType(truckRequire.getTruckTypeId());
        if (truckType != null) {
            // 检查要不要箱型
            waybill.setVehicleBoxType(truckType.getVehicleBoxType());
        }
        // 用车要求
        truckRequire.setTruckTypeId(truckType.getTruckTypeId());

        // 添加重量
        if (null != truckRequire && StringUtils.isNotBlank(truckRequire.getGoodsWeight())) {
            waybill.setGoodsWeight(
                new BigDecimal(truckRequire.getGoodsWeight()).multiply(new BigDecimal("1000")).intValue());
        }
        // 添加体积
        if (null != truckRequire && StringUtils.isNotBlank(truckRequire.getGoodsVolume())) {
            waybill.setGoodsVolume(new BigDecimal(truckRequire.getGoodsVolume()));
        }

        return waybill;
    }


    /**
     * 组装每个司机的运单
     *
     * @param waybill
     * @param truckRequire
     * @param truckId
     * @return
     */
    private Waybill buildWaybill(Waybill waybill, Integer truckId, TruckRequire truckRequire, LoginUser loginUser) {
        // 找到相应的司机
        Truck truck = truckService.getTruck(truckId);
        if (truck == null) throw new BusinessException("truckNotFound", "errors.paramErrorWithName", "车辆id");

        CapacityPool capacityPool = vmsCommonService.loadCapacityByTruckId(truckId, loginUser);
        if (null == capacityPool || null == capacityPool.getStatus() || !capacityPool.getStatus()) {
            throw new BusinessException("truckNotFound", "errors.common.prompt", "车牌号【" + truck.getPlateNumber() + "】没有有效运力");
        }

        Driver driver = vmsCommonService.loadDriverByDriverId(capacityPool.getDriverId());
        if (driver == null) throw new BusinessException("truckNotFound", "errors.common.prompt", "vms司机不存在");
        if (driver.getDriverRunType() == null) throw new BusinessException("truckNotFound", "errors.common.prompt", "vms司机类型不存在");

        Vendor vendor = vmsCommonService.loadVendorByVendorId(capacityPool.getVendorId());
        if (null == vendor) {
            throw new BusinessException("vendorNotFound", "errors.paramErrorWithName", "车牌号【" + truck.getPlateNumber() + "】没有绑定承运商或承运商不存在");
        }

        waybill.setTruckId(truck.getTruckId());
        waybill.setPlateNumber(truck.getPlateNumber());
        waybill.setDriverId(driver.getDriverId());
        waybill.setDriverType(driver.getDriverRunType());
        waybill.setDriverName(driver.getName());
        waybill.setVehicleToVendor(vendor.getVendorId());
        waybill = buildVehicleTypeAndVendorService.checkAndBuildVehicleTypeAndVendor(waybill, truck.getVehicleId(),
                waybill.getDriverType(), BuildVehicleTypeAndVendorService.CHANGE_TO_ASSIGN, loginUser);

        // 每个运单的箱型要求不同
        TruckType truckType = truckTypeService.getTruckType(truckRequire.getTruckTypeId());
        if (truckType != null) {
            // 检查要不要箱型
            waybill.setVehicleBoxType(truckType.getVehicleBoxType());
        }
        // 运单号
        waybill.setWaybillNo(waybillCommonService.getWaybillNo());
        waybill.setWaybillId(null);

        // 添加重量
        if (null != truckRequire && StringUtils.isNotBlank(truckRequire.getGoodsWeight())) {
            waybill.setGoodsWeight(
                new BigDecimal(truckRequire.getGoodsWeight()).multiply(new BigDecimal("1000")).intValue());
        }
        // 添加体积
        if (null != truckRequire && StringUtils.isNotBlank(truckRequire.getGoodsVolume())) {
            waybill.setGoodsVolume(new BigDecimal(truckRequire.getGoodsVolume()));
        }

        return waybill;
    }


    /**
     * 运单信息检查
     *
     * @param waybill
     * @param deliveryAddress 取货地
     * @param receiveAddress  配送地
     * @param truckRequire
     */
    private void waybillCheck(Waybill waybill, List<WaybillDeliveryAddress> deliveryAddress,
                              List<WaybillReceiveAddress> receiveAddress, TruckRequire truckRequire, LoginUser loginUser) {

//        if(StringUtils.isBlank(waybill.getDepartmentCode())){
//            throw new BusinessException("departmentCodeNotNull", "waybill.error.departmentCodeNotNull");
//        }
        if (waybill.getWaybillSource() == null)
            throw new BusinessException("waybillSourceNullError", "errors.paramCanNotNullWithName", "运单来源");

        if (waybill.getCustomerId() == null)
            throw new BusinessException("waybillCustomerNotFound", "waybill.errors.customerInfoNotNull");

        CustomerInfo customerInfo = customerInfoService.findCusInfoById(waybill.getCustomerId());
        if (customerInfo == null)
            throw new BusinessException("waybillCustomerNotFound", "waybill.errors.customerInfoNotNull");

        if(crm4TmsService.isShowConfigure(ProductsLableInfo.configureEnum.LOGISTICS_PRODUCTS.getCode(), loginUser)
            && StringUtils.isBlank(waybill.getLogisticsLabel())){
            throw new BusinessException("logisticsLabelNotNull", "物流产品标签不能为空");
        }

        this.checkWaybillTimeAndPoint(waybill, deliveryAddress, receiveAddress);
        this.checkWaybillRequireInfo(waybill, truckRequire, loginUser);
    }

    /**
     * 项目信息检查
     *
     * @param projectBillVo
     */
    private void projectBillCheck(ProjectBillVo projectBillVo) {
        WaybillParam waybillParam = projectBillVo.getWaybillParam();
        TruckRequire truckRequire = projectBillVo.getTruckRequire();

        if (!projectBillVo.getWaybill().getWaybillSource().equals(WaybillSource.FORM_TASK.getCode())) {
            // --车辆信息
            // --车型数量是否一致
            this.checkTruckInfo(projectBillVo);
        }
        Set<Integer> requireTruckType = new HashSet<>();
        requireTruckType.add(truckRequire.getTruckTypeId());

        // --车型是否维护过单价
        ProjectFreightRule projectFreightRule = null;
        try {
            projectFreightRule = JSONObject.parseObject(waybillParam.getProjectFreightRuleJson(), ProjectFreightRule.class);
        } catch (Exception e) {
            log.warn("序列化计价规则错误", e);
            throw new BusinessException("ProjectFreightRuleFormatError", "errors.common.prompt", "当前车型计价规则格式错误");
        }
        if (!projectBillVo.getWaybill().getWaybillSource().equals(WaybillSource.FORM_TASK.getCode())) {
            // --车辆信息
            // --车型数量是否一致
            this.checkTruckFreightRule(projectFreightRule, requireTruckType);
        }

    }

    /**
     * 用车时间和线路检查
     *
     * @param waybill
     * @param deliveryAddress
     * @param receiveAddress
     */
    private void checkWaybillTimeAndPoint(Waybill waybill, List<WaybillDeliveryAddress> deliveryAddress,
                                          List<WaybillReceiveAddress> receiveAddress) {

        // 用车时间
        Date planDeliveryTime = waybill.getPlanDeliveryTime();
        if (null == planDeliveryTime) {
            throw new BusinessException("planDeliveryTimeNotNull", "waybill.error.planDeliveryTimeNotNull");
        }
        if (DateUtil.checkOvertime(planDeliveryTime, (20 * 60)) == -1) {
            throw new BusinessException("planDeliveryTimeToLess", "waybill.error.planDeliveryTimeToLess");
        }

        // 取货地
        WaybillDeliveryAddress fetchAdds = deliveryAddress.get(0);
        if (com.giants.common.collections.CollectionUtils.isEmpty(deliveryAddress) || null == fetchAdds) {
            throw new BusinessException("validationFailure", "errors.validation.srcAddress");
        }
        if (fetchAdds.getContactName() != null && fetchAdds.getContactName().length() > 30) {
            throw new BusinessException("dataTooLong", "errors.validation.dataTooLong", "取货地联系人");
        }
        if (fetchAdds.getContactPhone() != null && !BaseUtil.checkMobilePhone(fetchAdds.getContactPhone())
            && !BaseUtil.checkTelephone(fetchAdds.getContactPhone())) {
            throw new BusinessException("contactPhoneFmtError", "errors.contactPhoneFmtError", "取货地");
        }

        // 目的地
        if (CollectionUtils.isNotEmpty(receiveAddress)) {
            // 计数器
            int temp = 0;
            for (WaybillReceiveAddress address : receiveAddress) {
                temp += 1;
                if (StringUtils.isBlank(address.getCoordinates())) {
                    throw new BusinessException("validation.srcAddress.lawful", "errors.validation.toAddress.lawful",
                        temp);
                }
                if (StringUtils.isNotBlank(address.getContactPhone())
                    && !BaseUtil.checkMobilePhone(address.getContactPhone())
                    && !BaseUtil.checkTelephone(address.getContactPhone())) {
                    throw new BusinessException("contactPhoneFmtError", "errors.contactPhoneFmtError",
                        "第" + temp + "目的地");
                }

                if (StringUtils.isNotBlank(address.getContactName())) {
                    if (address.getContactName().length() > WaybillCommonService.WAYBILL_CONTACT_NAME_LENGTH) {
                        throw new BusinessException("dataTooLong", "errors.validation.dataTooLong", "第" + temp + "目的地联系人");
                    }
                }
            }
        }

    }

    /**
     * 用车要求相关检查
     *
     * @param waybill
     * @param truckRequire
     */
    private void checkWaybillRequireInfo(Waybill waybill, TruckRequire truckRequire, LoginUser loginUser) {
        // if (StringUtils.isBlank(waybill.getAreaCode())) {
        // throw new BusinessException("areaCodeNotNull", "waybill.error.areaCodeNotNull");
        // }
        if (null == truckRequire) {
            throw new BusinessException("truckTypeMustSelect", "truckTypeFreight.not.truckTypeMustSelect");
        }
        // 业务区域
        log.info("后台建单接收参数-校验：{}", JSON.toJSONString(waybill));

        // 派车方式
        Integer receiveWay = waybill.getReceiveWay();
        if (null == receiveWay) {
            throw new BusinessException("receiveWayNotNull", "waybill.error.receiveWayNotNull");
        }

        if (StringUtils.isBlank(Waybill.ReceiveWay.buildReceiveWayStr(waybill.getReceiveWay()))) {
            throw new BusinessException("receiveWayNotError", "waybill.error.receiveWayNotError");
        }

        // 货物类型
        if (StringUtils.isBlank(truckRequire.getGoodsType())) {
            throw new BusinessException("validationFailure", "errors.validation.goodsType");
        }

        // 货物类型名称超长
        if (truckRequire.getGoodsType().length() > Constants.GOODS_TYPE_MAX_LENGTH) {
            throw new BusinessException("validationFailure", "errors.overGoodsTypeMaxLength",
                Constants.GOODS_TYPE_MAX_LENGTH);
        }

        // 结算方式
        Integer receiptType = waybill.getReceiptType();
        if (null == receiptType) {
            throw new BusinessException("canNotBeBlank", "errors.validation.canNotBeBlank", "结算方式");
        }

    }

    // 用车数量检查

    /**
     * 用车数量检查
     *
     * @param projectBillVo
     * @return
     */
    private Set<Integer> checkTruckInfo(ProjectBillVo projectBillVo) {
        Waybill waybill = projectBillVo.getWaybill();
        Set<ProjectBillVo.TruckValidator> truckValidators = projectBillVo.getTruckValidator();
        TruckRequire truckRequire = projectBillVo.getTruckRequire();

        // --车型数量是否一致
        Set<Integer> fixedTruckIds = projectBillVo.getFixedTruckIds();

        if (CollectionUtils.isEmpty(fixedTruckIds))
            throw new BusinessException("projectTrucksNull", "errors.paramCanNotNullWithName", "配送司机");
        if (CollectionUtils.isEmpty(truckValidators))
            throw new BusinessException("requireNull", "errors.paramCanNotNullWithName", "车辆要求");

        // 获取车辆对应的车型
        // 对已选车型计数
        Set<Integer> fixedDriver = new HashSet<>();
        Map<String, Integer> truckTypeCounter = new HashMap<>();
        for (Integer truckId : fixedTruckIds) {
            Truck truck = truckService.getTruck(truckId);
            if (truck == null) throw new BusinessException("truckError", "errors.paramErrorWithName", "司机车辆");
            //非自主派车才进行入城证校验
            if (NumberUtils.compare(waybill.getReceiveWay(), Waybill.ReceiveWay.ASSIGNED.getCode()) != 0) {
                if (FilialeBillUtil.hasAddtionalFunction(Constants.ADDTIONAL_FUNCTION_ENTRY_LICENSE, truckRequire.getAdditionalFunctionIds())) {
                    if (truck.getEntryLicense() == null || NumberUtils.compare(truck.getEntryLicense(), 0) == 0)
                        throw new BusinessException("entryLicenseError", "errors.common.prompt", "入城证不匹配");
                }
            }

            if (truck.getVehicleBoxType() == null || truck.getVehicleBoxLength() == null) continue;
            String key = truck.getVehicleBoxType() + "," + truck.getVehicleBoxLength();
            if (truckTypeCounter.containsKey(key)) {
                Integer count = truckTypeCounter.get(key) + 1;
                truckTypeCounter.put(key, count);
            } else {
                truckTypeCounter.put(key, 1);
            }

            fixedDriver.add(truckId);
        }

        // 需要校验是否有运费规则
//        Set<Integer> requireTruckType = new HashSet<>();
        int totalTruckRequire = 0;
        // 按类型比较数量是否一致
        for (ProjectBillVo.TruckValidator tv : truckValidators) {
            Integer vehicleBoxType = tv.getVehicleBoxType();
            Integer vehicleBoxLength = tv.getVehicleBoxLength();
            Integer typeAmount = tv.getAmount();
            if (vehicleBoxType == null || vehicleBoxLength == null)
                throw new BusinessException("truckAmountError", "errors.paramErrorWithName", "用车要求BoxType或BoxLength");

            if (typeAmount == null)
                throw new BusinessException("truckAmountError", "errors.paramErrorWithName", "用车要求数量");

            String key = vehicleBoxType + "," + vehicleBoxLength;
            Integer amount = truckTypeCounter.get(key);
            if (amount == null) throw new BusinessException("truckAmountError", "errors.common.prompt", "所选车辆与用车要求不一致");

            if (NumberUtils.compare(amount, tv.getAmount()) != 0)
                throw new BusinessException("truckAmountError", "errors.common.prompt", "所选车辆与用车要求不一致");

//            requireTruckType.add(typeId);
            totalTruckRequire += typeAmount;
        }

        // a.若固定司机数小于用车数，提示需完成指派差额司机数
        if (fixedDriver.size() < totalTruckRequire) throw new BusinessException("truckAmountError",
            "errors.common.prompt", "还需指派" + (totalTruckRequire - fixedDriver.size()) + "个司机");

        // b.若固定司机数大于用车数，提示需去掉差额司机数
        if (fixedDriver.size() > totalTruckRequire) throw new BusinessException("truckAmountError",
            "errors.common.prompt", "还需去掉" + (fixedDriver.size() - totalTruckRequire) + "个司机");

//        return requireTruckType;
        //不能使用车辆表中的车型
        return null;
    }

    // 车型单价检查

    /**
     * 车型单价检查
     *
     * @param freightRule
     * @param requireTruckType
     */
    private void checkTruckFreightRule(ProjectFreightRule freightRule, Set<Integer> requireTruckType) {
        // 没有维护过车型单价的车辆不能下单
        if (freightRule == null)
            throw new BusinessException("projectFreightRuleNull", "errors.common.prompt", "没有维护计价规则");

        Set<Integer> legalTruckType = new HashSet<>();
        legalTruckType.add(freightRule.getTruckTypeId());
        // 计价规则中没有该车型则报错
        // 要求的车型数>已维护价格的车型数
        requireTruckType.removeAll(legalTruckType);
        if (CollectionUtils.isNotEmpty(requireTruckType)) {
            // 获取要维护车型价格的名称
            StringBuilder sb = new StringBuilder("");
            for (Integer type : requireTruckType) {
                sb.append(truckTypeService.findTruckTypeNameByTypeId(type));
                sb.append(" ");
            }
            throw new BusinessException("projectFreightRuleError", "errors.common.prompt",
                "请先添加以下车型的计价规则:\n" + sb.toString());
        }

    }

    @Override
    public WaybillDetailInfo getProjectBillDetail(int waybillId, LoginUser loginUser) throws BusinessException {
        WaybillDetailInfo detailInfo = new WaybillDetailInfo();
        // 运单
        Waybill waybill = waybillService.getWaybill(waybillId);
        //查询业务范围
        BusinessArea businessArea = businessAreaService.loadBusinessArea(waybill.getAreaCode(), loginUser);
        if(businessArea != null){
            waybill.setAreaName(businessArea.getAreaName());
        }

        //是不是项目单
        waybill.setAsProjectWaybillHandle(this.checkAsProjectWaybill(waybill));
        // 转运单需要根据原单确定司机端是否填写工作量
        if (NumberUtils.compare(waybill.getWaybillSource(), Waybill.WaybillSource.TRANSFORM_BILL.getCode()) == 0) {
            WaybillParam transformBillParam = waybillParamService.findByTransformBillLinkId(waybill.getWaybillId());
            if (null != transformBillParam) {
                Waybill transformBill = waybillService.getWaybill(transformBillParam.getWaybillId());
                if (null != transformBill && null != transformBill.getProjectId()) {
                    waybill.setValuationWays(transformBill.getValuationWays());
                    boolean isDriverWriteWork = waybillCheckService
                        .checkIsDriverWriteWork(transformBill.getWaybillId());
                    waybill.setWhoWriteWork(isDriverWriteWork ? 1 : 2);
                    waybill.setCompleteWorkload(
                        waybillCheckService.checkProjectIsWorkload(transformBill.getWaybillId()) && !isDriverWriteWork);
                }
            }
        } else {
            if (null != waybill.getProjectId()) {
                boolean isDriverWriteWork = waybillCheckService.checkIsDriverWriteWork(waybill.getWaybillId());
                waybill.setWhoWriteWork(isDriverWriteWork ? 1 : 2);
                waybill.setCompleteWorkload(
                    waybillCheckService.checkProjectIsWorkload(waybill.getWaybillId()) && !isDriverWriteWork);
            }
        }
        detailInfo.setWaybill(waybill);
        // 线路信息
        detailInfo.setWaybillDeliveryAddresses(waybillDeliveryAddressService.findAllByWaybillId(waybillId));
        detailInfo.setWaybillReceiveAddresses(waybillReceiveAddressService.findAllByWaybillId(waybillId));
        // 客户
        CustomerInfo customerInfo = customerInfoService.findCusInfoById(waybill.getCustomerId());
        detailInfo.setCustomerInfo(customerInfo);
        detailInfo.setCanUseCustomerInfo(customerInfoService.customerBelongToManager(waybill.getCustomerId(), waybill.getCustomerManagerId()));
        //物流产品标签名称
        Customer4TmsBo customer4TmsBo = crm4TmsService.findProductAndDepId(null, loginUser);
        if(customer4TmsBo != null){
            Map<String, Object> logisticsProductMap = customer4TmsBo.getLogisticsProducts();
            Object logisticsNameOb = logisticsProductMap.get(waybill.getLogisticsLabel());
            waybill.setLogisticsName(logisticsNameOb == null ? "" : (String)logisticsNameOb);
        }
        // 用车要求
        detailInfo.setTruckRequire(truckRequireService.findTruckRequireByWaybillId(waybillId, loginUser));
        detailInfo.setTruckRequireStr(truckRequireService.getTruckRequire(detailInfo.getTruckRequire(), null));
        //回单记录
        detailInfo.setReceiptImageList(receiptManageService.listReceiptImageByWaybillId(waybillId));
        //抢单超时倒计时
        detailInfo.setExpireTimeLength(customerManWaybillUtils.getExpireTimeLength());

        // 计价规则
        WaybillParam waybillParam = waybillParamService.findByWaybillId(waybillId);
        detailInfo.setWaybillParam(waybillParam);
        // 实际使用情况
        //detailInfo.setValuationConst(waybillParam.getValuationConstJson());
        ProjectFreightRuleVo rule = null;
        if (StringUtils.isNotBlank(waybillParam.getProjectFreightRuleJson())) {

            List<ValuationWay> valuationWays = new ArrayList<ValuationWay>();
            detailInfo.setValuationWays(valuationWays);

            rule = JSON.parseObject(waybillParam.getProjectFreightRuleJson(), ProjectFreightRuleVo.class);
            rule.setFactorJson(rule.getFactorJson());
            detailInfo.setProjectFreightRule(rule);
            rule.setTruckTypeId(detailInfo.getTruckRequire().getTruckTypeId());
            // 获取计价方式
            if (rule != null) {
                // 车型名称
                String truckTypeName = truckTypeService.findTruckTypeNameByTypeId(rule.getTruckTypeId());
                rule.setTruckTypeName(truckTypeName);
            }
        }
        // 司机车辆
//        Truck truck = truckService.getTruck(waybill.getTruckId());
//        DriverTruckInfoBo driverTruckInfoBo = new DriverTruckInfoBo(0, driverService.getDriver(waybill.getDriverId()));
        detailInfo.setDriverTruckInfoBo(driverService.findDriverTruckInfoByWaybillId(waybillId));
//        if (truck != null) {
//            driverTruckInfoBo.setPlateNumber(truck.getPlateNumber());
//            driverTruckInfoBo.setTruckInfo(truckService.findTruckInfoStrByTruckId(truck.getTruckId()));
//        }
        //计价方式
        List<FreightFactor> factors = freightFactorService.findByFreightWay(FreightEnum.PROJECT.getCode(), loginUser);
        detailInfo.setAllFactors(factors);
        // 取系统报价
//        Waybill billRst = new Waybill();
//        billRst.setWaybillId(waybillId);
//        waybillService.calculateProjectFreight(waybillId, billRst);
//        waybill.setCalculatedFreight(billRst.getEstimateFreight());

        // 配送单
        if (NumberUtils.compare(Waybill.ReceiveWay.TRANSFORM_BILL.getCode(), waybill.getReceiveWay()) == 0) {
            detailInfo.setDeliveryPointSupplementList(
                deliveryPointSupplementService.getByWayBill(waybillParam.getTransformBillLinkId()));
        } else {
            detailInfo.setDeliveryPointSupplementList(deliveryPointSupplementService.getByWayBill(waybillId));
        }
        //异常报备
        detailInfo.setReportInfoList(reportInfoDetailService.listByWaybillId(waybillId, null));

        List<ConfParamOption> confParamOptions = reportInfoService.listRepotInfoType();
        if (detailInfo.getReportInfoList() != null) {
            for (ReportInfoDetails reportInfoDetails : detailInfo.getReportInfoList()) {
                reportInfoDetails.setAllReportTypes(confParamOptions);
            }
        }

        //Constants
        //客服电话
        detailInfo.setCustomerServiceTel(serviceConfService.findCustomerServiceTel(waybill.getRegionCode(), loginUser));

        //当运单类型为转运时组装司机和承运商信息
        if (waybill.getReceiveWay().equals(Waybill.ReceiveWay.TRANSFORM_BILL.getCode())
            && waybill.getVendorId() != null) {
            setDriverAndTransformData(waybillId, detailInfo);
        }
        if(waybill.getRoadMapId() == null || waybill.getRoadMapId() == 0){//老数据默认司机填写工作量
            detailInfo.setWhoWriteWork(1);
        }else{
            detailInfo.setWhoWriteWork(waybillCheckService.loadWhoWriteWork(waybillId));
        }

        return detailInfo;
    }

    // 判断是不是按工作量:与waybillServiceImpl相同
    private boolean checkProjectIsWorkload(Integer waybillId) {
        WaybillParam waybillParam = waybillParamService.findByWaybillId(waybillId);
        if (null == waybillParam || StringUtils.isBlank(waybillParam.getProjectFreightRuleJson())) {
            return false;
        }

        ProjectFreightRule freightRule = JSON.parseObject(waybillParam.getProjectFreightRuleJson(),
                ProjectFreightRule.class);
        if (null == freightRule) {
            return false;
        }

        if (null != freightRule.getValuationWay()
                && NumberUtils.compare(freightRule.getValuationWay(), ValuationWayEnum.FIXED_PRICE.getCode()) == 0) {
            return false;
        }
        return true;
    }

    @Override
    public void modifyPreTaxFreight(Waybill waybill, LoginEmployee loginEmployee) throws BusinessException {
        if (waybill == null) throw new BusinessException("waybillNull", "errors.paramCanNotNullWithName", "运单");

        if (waybill.getWaybillId() == null)
            throw new BusinessException("waybillIdNull", "errors.paramCanNotNullWithName", "运单id");

        if (waybill.getEstimateFreight() == null)
            throw new BusinessException("waybillNull", "errors.paramCanNotNullWithName", "运单价格");

        Waybill db = waybillService.getWaybill(waybill.getWaybillId());
        if (NumberUtils.compare(Waybill.StatusView.WATING_PAY.getCode(), db.getStatusView()) != 0)
            throw new BusinessException("waybillNull", "errors.common.prompt", "运单状态不是待支付");

        WaybillBo bo = new WaybillBo();
        bo.setWaybill(db);

        BigDecimal oldFreight = db.getShow4DriverFreight();

        // 计算价格
        this.getNewFreight(waybill, db);
        db.setUpdateFreightRemark(waybill.getUpdateFreightRemark());
        // 保存数据
        waybillCommonService.update(db, loginEmployee);

        this.modifyFreightPushMsg(waybill, oldFreight, null, loginEmployee);
    }

    /**
     * 执行价格更新
     *
     * @param waybillVo 包含预估运费和运单id
     * @param waybillDb 数据库中的原值
     */
    private Waybill getNewFreight(Waybill waybillVo, Waybill waybillDb) {
        if (waybillVo.getEstimateFreight() == null) {
            throw new BusinessException("waybillEstimateFreightNull", "errors.paramErrorWithName", "预估价格");
        }
        // 预估价格
        waybillDb.setEstimateFreight(waybillVo.getEstimateFreight());
        // 税后
        waybillDb.setAfterTaxFreight(truckTypeFreightService.getAfterTaxFright(null, waybillDb));
        // 分公司运单要求经纪人修改税前价格后，影响司机结算价
        waybillDb.setShow4DriverFreight(waybillDb.getAfterTaxFreight());
        WaybillParam waybillParam = waybillParamService.findByWaybillId(waybillDb.getWaybillId());
        this.calculateProjectFreight(waybillParam, waybillDb);

        waybillDb.setCompareResult(
            BaseUtil.calCompareResult(waybillDb.getCalculatedFreight().multiply(new BigDecimal("1.1")),
                waybillDb.getEstimateFreight(), true));
        return waybillDb;
    }

    @Override
    public void changeToComplete(Integer waybillId, LoginEmployee loginEmployee) throws BusinessException {
        if (null == waybillId) {
            throw new BusinessException("waybillNotfound", "waybill.error.notfound");
        }
        Waybill dbWaybill = waybillService.getWaybill(waybillId);
        if (dbWaybill == null) {
            throw new BusinessException("waybillNotfound", "waybill.error.notfound");
        }
        if (dbWaybill.getStatus() != Waybill.Status.DELIVERIED.getCode()) {
            throw new BusinessException("statusError", "waybill.error.status");
        }

        Waybill entity = new Waybill();
        entity.setWaybillId(waybillId);
        entity.setStatus(Waybill.Status.PAIED.getCode());
        entity.setStatusView(Waybill.StatusView.FINISH.getCode());
        waybillCommonService.update(entity, loginEmployee);

        // 增加客户运单数量
        increaseWaybillCount(dbWaybill);

    }

    private void increaseWaybillCount(Waybill dbWaybill) {
        // 更新企业客户订单数量
        if (dbWaybill.getCustomerId() != null) {
            try {
                customerInfoService.increaseWaybillCount(1, dbWaybill.getCustomerId());
            } catch (BusinessException e) {
                log.warn("更新企业客户订单数量错误,customerId:{}", dbWaybill.getCustomerId(), e);
            }
        }
    }


    @Override
    public void modifyWaybillTaxRate(int waybillId, BigDecimal taxRateValue, LoginEmployee loginEmployee)
        throws BusinessException {
        Waybill waybill = waybillService.getWaybill(waybillId);

        if (waybill == null) throw new BusinessException("waybillNotExist", "errors.paramError");

        // 项目运单才支持修改
        if (waybill.getProjectId() == null)
            throw new BusinessException("waybillTypeError", "errors.common.prompt", "不是项目运单");

        // 待支付状态才能修改税率
        if (waybill.getStatusView() != Waybill.StatusView.WATING_PAY.getCode())
            throw new BusinessException("waybillStatusError", "errors.common.prompt", "运单状态不合法");

        // 修改truckRequire
        TruckRequire truckRequire = truckRequireService.findTruckRequireByWaybillId(waybillId, loginEmployee);
        truckRequire.setTaxRateValue(taxRateValue);
        truckRequireService.update(truckRequire);

        WaybillParam waybillParam = waybillParamService.findByWaybillId(waybillId);

        Waybill entity = new Waybill();
        entity.setWaybillId(waybillId);
        // 已完成运单修改税率要修改司机结算价和税后费用
        BigDecimal afterTaxFee = truckTypeFreightService.getAfterTaxFright(truckRequire, waybill);
        entity.setAfterTaxFreight(afterTaxFee);
        this.calculateProjectFreight(waybillParam, entity);

        waybillCommonService.update(entity, loginEmployee);

        this.modifyFreightPushMsg(waybill, waybill.getShow4DriverFreight(), null, loginEmployee);
    }

    /**
     * 计算司机结算费 公式：税后-小工搬运费
     *
     * @param waybillParam
     * @param entity
     */
    private void calculateProjectFreight(WaybillParam waybillParam, Waybill entity) {
        //
        BigDecimal show4DriverFreight = entity.getAfterTaxFreight();
        if (waybillParam.getLaborerHandlingCost() != null) {
            show4DriverFreight = show4DriverFreight.subtract(waybillParam.getLaborerHandlingCost());
        }
        entity.setShow4DriverFreight(show4DriverFreight);
    }

    /**
     * 改价后发送通知
     *
     * @param waybillDb
     * @param oldFreight
     * @param loginEmployee
     */
    private void modifyFreightPushMsg(Waybill waybillDb, BigDecimal oldDriverFreight, BigDecimal oldFreight,
                                      LoginEmployee loginEmployee) {
        filialeMsgWithBusinessUtils.pushChangePriceMsgToDriver(waybillDb.getWaybillId(), oldDriverFreight);
        // if (!Constants.CUSTOMER_MANAGER_PERMISSION_KEY.equals(loginEmployee.getLoginPermissionKey())) {
        // msgWithBusinessUtils.pushChangePricelMsgToWaybillOwner(waybillDb.getWaybillId(), oldFreight);
        // }
    }


    /**
     * 运力校验
     *
     * @param waybill
     * @param truckRequire
     */
    private void checkCarryCapacity(Waybill waybill, TruckRequire truckRequire, TruckType truckType) {
        //货物方吨件
        if (StringUtils.isBlank(truckRequire.getGoodsVolume()))
            throw new BusinessException("GoodsVolumeNull", "errors.validation.canNotBeBlank", "方量");
        double volume = new Double(truckRequire.getGoodsVolume());
        if (StringUtils.isBlank(truckRequire.getGoodsWeight()))
            throw new BusinessException("GoodsWeightNull", "errors.validation.canNotBeBlank", "重量");
        double weight = new Double(truckRequire.getGoodsWeight());
        Integer distance = waybill.getEstimateDistance();
        if (distance == null)
            throw new BusinessException("estimateDistanceNull", "errors.validation.canNotBeBlank", "距离");

        //车型校验
        if (truckRequire.getTruckTypeId() == null)
            throw new BusinessException("truckTypeNull", "errors.validation.canNotBeBlank", "车型");

        if (truckType == null) throw new BusinessException("truckTypeNotExist", "errors.paramErrorWithName", "车型");

        if (truckType.getTruckTypeVolume() != null) {//FIXME 这里按立方米处理
            Double maxVolume = truckType.getTruckTypeVolume().doubleValue();
            if (NumberUtils.compare(volume, maxVolume) == 1)
                throw new BusinessException("GoodsVolumeOverCapacityError", "errors.common.prompt", "货物的吨方件超出整车单车最大装载值哦~请拆分成多车下单");
        }

        if (truckType.getTruckTypeLoad() != null) {//FIXME 这里按吨处理
            Double maxWeight = truckType.getTruckTypeLoad().doubleValue();
            if (NumberUtils.compare(weight, maxWeight) == 1)
                throw new BusinessException("GoodsWeightOverCapacityError", "errors.common.prompt", "货物的吨方件超出整车单车最大装载值哦~请拆分成多车下单");
        }
    }

 // 判断是不是以项目单处理
    private boolean checkAsProjectWaybill(Waybill waybill) {
        if (null == waybill) {
            return false;
        }

        // 非转运单
        if (NumberUtils.compare(Waybill.WaybillSource.TRANSFORM_BILL.getCode(), waybill.getWaybillSource()) != 0) {
            if (null != waybill.getProjectId()) {
                return true;
            }
        } else {
            // 转运单
            Waybill transformBill = waybillCommonService.findWaybillByTransformBillId(waybill.getWaybillId());
            if (null == transformBill) {
                return false;
            }

            if (null != transformBill.getProjectId()) {
                return true;
            }
        }
        return false;
    }


    /**
     * 当运单为承运单时组装司机和承运商信息
     */
    public void setDriverAndTransformData(int waybillId, WaybillDetailInfo detailInfo) {

        try {
            Vendor vendor = vmsService.loadByVenorId(detailInfo.getWaybill().getVendorId());
            if(null != vendor){
                detailInfo.setContactUserPhone(vendor.getContactPhone());
                detailInfo.setContactUserName(vendor.getContactUserName());
                detailInfo.getWaybill().setVendorName(vendor.getVendorName());
            }
        } catch (Exception e) {
            // 异常后继续 设置剩下 的值
            log.warn("获取承运商信息异常：" + "(" + detailInfo.getWaybill().getTenantId() + "," + detailInfo.getWaybill().getVendorId() + ")", e);
        }

        WaybillParam waybillParam = waybillParamService.findByWaybillId(waybillId);
        if (waybillParam == null) {
            throw new RuntimeException("不存在运单号" + waybillId + "对应的运单参数");
        }

        Waybill waybill = waybillService.getWaybill(waybillParam.getTransformBillLinkId());
        if (waybill == null) {
            throw new RuntimeException("不存在转运单号" + waybillParam.getTransformBillLinkId() + "对应的运单");
        }
        // 设置配送单状态
        detailInfo.getWaybill().setIsChangeDeliveryPoint(waybill.getIsChangeDeliveryPoint());
        //设置司机属性
        detailInfo.setDriverTruckInfoBo(driverService.findDriverTruckInfoByWaybillId(waybill.getWaybillId()));
    }


    private void  addWaybillOperateTrack ( Integer waybillId,LoginUser loginUser) {
        if (null == waybillId) {
            return;
        }

        Waybill waybill = waybillCommonService.getWaybillById(waybillId);
        if (null == waybill) {
            return;
        }

        OperateApplication application = null;
        if (NumberUtils.compare(Waybill.WaybillSource.JUMA_CLIENT.getCode(), waybill.getWaybillSource()) == 0) {
            application = OperateApplication.CUSTOMER_SYS;
        } else if (NumberUtils.compare(Waybill.WaybillSource.BACKGROUND_NEW.getCode(), waybill.getWaybillSource()) == 0) {
            application = OperateApplication.BACKGROUND_SYS;
        } else if (NumberUtils.compare(WaybillSource.FIXED_DEMAND_AUTO_CREATE.getCode(), waybill.getWaybillSource()) == 0) {
            application = OperateApplication.TASK_SYS;
        }

        WaybillOperateTrack waybillOperateTrack = new WaybillOperateTrack();
        waybillOperateTrack.setWaybillId(waybill.getWaybillId());
        waybillOperateTrack.setOperateApplication( application!= null ? application.getCode() : null );
        waybillOperateTrack.setDriverId(waybill.getDriverId());
        waybillOperateTrack.setPlateNumber(waybill.getPlateNumber());
        waybillOperateTrack.setOperateType(OperateType.CREATE_WAYBILL.getCode());
        waybillOperateTrackService.insert(waybillOperateTrack, loginUser);
        // 如果是创建的时候就有司机 那么一定是指派的
        if( waybill.getDriverId() != null ) {
            waybillOperateTrack.setOperateType( OperateType.ASSIGNED_SYS.getCode() );
            waybillOperateTrackService.insert(waybillOperateTrack, loginUser);
        }
    }

    /**
     * 校验当前登录人是否项目的项目经理或运营专员
     * 项目状态是否为运行中
     * 项目签约方不能为空
     * 项目运营方不能为空
     * @param projectId
     * @param loginUser
     */
    private com.juma.tgm.project.domain.v2.Project checkProject(Integer projectId, Integer waybillSource, LoginUser loginUser){

        String hintSubject = ""; //提示主体
        if(waybillSource.compareTo(Waybill.WaybillSource.TRANSFORM_BILL.getCode()) == 0){
            hintSubject = "承运商";
        }

        if (projectId == null)
            throw new BusinessException("projectNull", "errors.paramCanNotNullWithName", hintSubject+"项目id");

        com.juma.tgm.project.domain.v2.Project project = projectService.getProjectV2(projectId);
        if(project == null)
            throw new BusinessException("projectNotExist", "errors.common.prompt", hintSubject+"项目不存在");

        //不是转运单的时候判断
        if(waybillSource.compareTo(Waybill.WaybillSource.TRANSFORM_BILL.getCode()) != 0){
            List<Integer> projectIdList = projectMemberService.findProjectIdsByUserId(loginUser.getUserId(), loginUser.getTenantId());
            if(projectIdList.isEmpty() || !projectIdList.contains(projectId))
                throw new BusinessException("projectNotBelongUser", "errors.common.prompt", "登录用户不是项目经理或运营专员，不能下单");
        }

        if(project.getProjectStatus().compareTo(ProjectEnum.ProjectStatus.RUNING.getCode()) != 0)
            throw new BusinessException("projectStatusError", "errors.common.prompt", hintSubject+"项目状态不是运行中，不能下单");

        if(project.getPayToCompany() == null)
            throw new BusinessException("projectStatusError", "errors.common.prompt", hintSubject+"项目运营主体不存在，请联系相关人员配置");

        if(project.getContractToCompany() == null)
            throw new BusinessException("projectStatusError", "errors.common.prompt", hintSubject+"项目签约主体不存在，请联系相关人员配置");

        return project;
    }
}
