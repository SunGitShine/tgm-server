package com.juma.tgm.landing.waybill;

import com.alibaba.fastjson.JSON;
import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.EcoUser;
import com.juma.auth.user.domain.LoginEcoUser;
import com.juma.auth.user.domain.LoginUser;
import com.juma.auth.user.domain.UserInfo;
import com.juma.auth.user.domain.UserVerification;
import com.juma.auth.user.service.EcoUserService;
import com.juma.auth.user.service.UserService;
import com.juma.conf.domain.ConfParamOption;
import com.juma.conf.service.ConfParamService;
import com.juma.crm.customer.domain.ConsignorContactsInfo;
import com.juma.monitor.service.FenceService;
import com.juma.tgm.common.BaseUtil;
import com.juma.tgm.common.Constants;
import com.juma.tgm.common.DateUtil;
import com.juma.tgm.common.FreightEnum;
import com.juma.tgm.configure.domain.FreightRule;
import com.juma.tgm.configure.domain.PrivateFreightContext;
import com.juma.tgm.configure.service.FreightRuleService;
import com.juma.tgm.configure.service.PrivateFreightFactorService;
import com.juma.tgm.configure.service.ServiceConfService;
import com.juma.tgm.crm.domain.CustomerInfo;
import com.juma.tgm.crm.service.CustomerInfoService;
import com.juma.tgm.customer.domain.TruckCustomer;
import com.juma.tgm.driver.domain.Driver;
import com.juma.tgm.driver.service.DriverService;
import com.juma.tgm.landing.waybill.service.DispatchingTruckService;
import com.juma.tgm.landingWaybill.domain.AtFenceResultVo;
import com.juma.tgm.receiptManage.service.ReceiptManageService;
import com.juma.tgm.scatteredWaybill.service.AbsWaybillService;
import com.juma.tgm.scatteredWaybill.service.ScatteredMsgService;
import com.juma.tgm.sop.domain.Sop;
import com.juma.tgm.sop.service.SopService;
import com.juma.tgm.truck.domain.AdditionalFunction;
import com.juma.tgm.truck.domain.Truck;
import com.juma.tgm.truck.domain.TruckType;
import com.juma.tgm.truck.domain.bo.DriverTruckInfoBo;
import com.juma.tgm.truck.service.AdditionalFunctionService;
import com.juma.tgm.truck.service.TruckService;
import com.juma.tgm.truck.service.TruckTypeService;
import com.juma.tgm.user.domain.UserRouteMaster;
import com.juma.tgm.waybill.domain.CityAdressData;
import com.juma.tgm.waybill.domain.TruckRequire;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.Waybill.Status;
import com.juma.tgm.waybill.domain.WaybillBo;
import com.juma.tgm.waybill.domain.WaybillDeliveryAddress;
import com.juma.tgm.waybill.domain.WaybillDetailInfo;
import com.juma.tgm.waybill.domain.WaybillParam;
import com.juma.tgm.waybill.domain.WaybillReceiveAddress;
import com.juma.tgm.waybill.domain.ai.Feature;
import com.juma.tgm.waybill.domain.view.WaybillViewVo;
import com.juma.tgm.waybill.domain.vo.DistanceAndPriceParamVo;
import com.juma.tgm.waybill.domain.vo.ScatteredWaybillCreateVo;
import com.juma.tgm.waybill.domain.vo.ScatteredWaybillViewVo;
import com.juma.tgm.waybill.service.TruckRequireService;
import com.juma.tgm.waybill.service.UserRouteBusinessService;
import com.juma.tgm.waybill.service.WaybillAutoFenceServicve;
import com.juma.tgm.waybill.service.WaybillCommonService;
import com.juma.tgm.waybill.service.WaybillDeliveryAddressService;
import com.juma.tgm.waybill.service.WaybillParamService;
import com.juma.tgm.waybill.service.WaybillReceiveAddressService;
import com.juma.tgm.waybill.service.WaybillTransformToCarrierService;
import com.juma.tgm.waybill.service.WaybillViewService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 落地配Service
 *
 * @ClassName: ScatteredWaybillServiceImpl
 * @Description:
 * @author: liang
 * @date: 2017-11-13 11:30
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */

@Service
public class ScatteredWaybillServiceImpl extends AbsWaybillService {

    private final static Logger log = LoggerFactory.getLogger(ScatteredWaybillServiceImpl.class);

    @Resource
    private WaybillCommonService waybillCommonService;
    @Resource
    private WaybillDeliveryAddressService waybillDeliveryAddressService;
    @Resource
    private WaybillReceiveAddressService waybillReceiveAddressService;
    @Resource
    private TruckRequireService truckRequireService;
    @Resource
    private TruckService truckService;
    @Resource
    private DriverService driverService;
    @Resource
    private CustomerInfoService customerInfoService;
    @Resource
    private TruckTypeService truckTypeService;
    @Resource
    private AdditionalFunctionService additionalFunctionService;
    /**
     * 保存用户常用路线
     */
    @Resource
    private UserRouteBusinessService userRouteUtils;
    @Resource
    private WaybillParamService waybillParamService;
    @Resource
    private UserService userService;
    @Resource
    private EcoUserService ecoUserService;
    @Resource
    private WaybillAutoFenceServicve waybillAutoFenceServicve;
    @Resource
    private FenceService fenceService;
    @Resource
    private ConfParamService confParamService;
    /**
     * 带业务逻辑的发送消息
     */
    @Resource
    private ScatteredMsgService scatteredMsgService;
    @Resource
    private DispatchingTruckService dispatchingTruckService;
    @Resource
    private FreightRuleService freightRuleService;
    @Resource
    private ServiceConfService serviceConfService;
    @Resource
    private ReceiptManageService receiptManageService;

    @Resource
    private WaybillViewService waybillViewService;

    @Resource
    private SopService sopService;
    /**
     * 税前费用计算
     */
    @Resource
    private PrivateFreightFactorService privateFreightFactorService;

    @Resource
    private WaybillTransformToCarrierService waybillTransformToCarrierService;

    @Override
    public Page<ScatteredWaybillViewVo> searchForApp(PageCondition pageCondition, LoginUser loginUser)
            throws BusinessException {
        Page<ScatteredWaybillViewVo> scatteredWaybillViewVoPage = new Page<>(pageCondition.getPageNo(),
                pageCondition.getPageSize(), 0);
        // 货主id或经纪人id，必须由调用方决定
        pageCondition.setOrderBy("(if(C.statusView = -2, 2, C.statusView)) asc,C.planDeliveryTime desc");// -2当做2处理
        int count = waybillViewService.searchForAppCount(pageCondition);
        if (count == 0) return scatteredWaybillViewVoPage;

        scatteredWaybillViewVoPage.setTotal(count);
        List<WaybillViewVo> baseData = waybillViewService.searchForApp(pageCondition);
        List<ScatteredWaybillViewVo> finalData = new ArrayList<>();
        // 数据组装
        ScatteredWaybillViewVo scatteredVo = null;
        for (Waybill bill : baseData) {
            scatteredVo = new ScatteredWaybillViewVo();
            scatteredVo.setWaybill(bill);
            // 配送线路信息
            this.buildDeliveryRoute(scatteredVo, bill);

            finalData.add(scatteredVo);
        }
        scatteredWaybillViewVoPage.setResults(finalData);

        return scatteredWaybillViewVoPage;
    }

    @Override
    public ScatteredWaybillViewVo getDetail(int waybillId, LoginUser loginUser) throws BusinessException {
        Waybill waybill = waybillCommonService.getWaybillById(waybillId);
        if (waybill == null) throw new BusinessException("waybillNotExist", "errors.paramError");

        ScatteredWaybillViewVo scatteredWaybillViewVo = new ScatteredWaybillViewVo();
        scatteredWaybillViewVo.setWaybill(waybill);

        // 货物要求信息
        TruckRequire truckRequire = truckRequireService.findTruckRequireByWaybillId(waybillId, loginUser);
        scatteredWaybillViewVo.setTruckRequire(truckRequire);
        this.buildAdditionalFunction(scatteredWaybillViewVo, truckRequire);
        scatteredWaybillViewVo.setWaybillParam(waybillParamService.findByWaybillId(waybillId));

        // 司机
        // if (waybill.getDriverId() != null) {
        // scatteredWaybillViewVo.setDriver(driverService.getDriver(waybill.getDriverId()));
        // }
        // 车辆信息
        // if (waybill.getTruckId() != null) {
        // scatteredWaybillViewVo.setTruck(truckService.getTruck(waybill.getTruckId()));
        // scatteredWaybillViewVo.setTruckInfo(truckService.findTruckInfoStrByTruckId(waybill.getTruckId()));
        // }
        // if (waybill.getCustomerId() != null) {
        // CustomerInfo customerInfo = customerInfoService.findCusInfoById(waybill.getCustomerId());
        // scatteredWaybillViewVo.setCustomerInfo(customerInfo);
        // }

        // 司机车辆信息
        scatteredWaybillViewVo.setDriverTruckInfoBo(driverService.findDriverTruckInfoByWaybillId(waybillId));

        // 配送地址信息
        this.buildDeliveryRoute(scatteredWaybillViewVo, waybill);
        // 客服电话
        scatteredWaybillViewVo
                .setHotline(serviceConfService.findCustomerServiceTel(waybill.getRegionCode(), loginUser));

        // 回单信息
        if (waybill.getNeedReceipt() != null && (NumberUtils.compare(waybill.getNeedReceipt(),
                Waybill.NeedReceipt.NO_NEED_RECEIPT.getCode()) != 0
                || NumberUtils.compare(waybill.getNeedReceipt(), Waybill.NeedReceipt.NOT_HAVE_UPLOAD.getCode()) != 0)) {
            scatteredWaybillViewVo.setReceiptManageList(receiptManageService.listReceiptImageByWaybillId(waybillId));
        }

        try {
            // 派单等待倒计时
            List<ConfParamOption> options = confParamService.findParamOptions(Constants.SCATTERED_WAYBILL_CONFIG);
            if (CollectionUtils.isNotEmpty(options)) {
                for (ConfParamOption option : options) {
                    if (StringUtils.equals(Constants.SCATTERED_WAYBILL_WAIT_ASSIGN_TIME, option.getOptionName())) {
                        scatteredWaybillViewVo.setAssignWaitingTime(option.getOptionValue());
                    }
                }
            }
        } catch (BusinessException e) {
            log.warn("派单等待倒计时没有配置", e);
        }

        if (StringUtils.isBlank(scatteredWaybillViewVo.getAssignWaitingTime())) {
            scatteredWaybillViewVo.setAssignWaitingTime("120");// 默认值120秒
        }
        // 展示箱型
        if (waybill.getVehicleBoxType() != null) {
            scatteredWaybillViewVo
                    .setVehicleBoxTypeName(truckTypeService.findVehicleBoxTypeName(truckRequire.getTruckTypeId()));
        }
        // 用车要求车型
        if (truckRequire.getTruckTypeId() != null) {
            String truckTypeName = truckTypeService.findTruckTypeNameByTypeId(truckRequire.getTruckTypeId());
            scatteredWaybillViewVo.setTruckTypeName(truckTypeName);
        }

        return scatteredWaybillViewVo;
    }

    @Override
    public void confirmFeeDelivery(int waybillId, LoginEmployee loginEmployee) throws BusinessException {
        Waybill waybill = waybillCommonService.getWaybillById(waybillId);
        if (waybill == null) throw new BusinessException("waybillNotExist", "errors.paramError");
        WaybillParam waybillParam = waybillParamService.findByWaybillId(waybillId);

        // 运单状态必须是配送完成
        // 司机已确认已代收货款
        if (NumberUtils.compare(waybillParam.getAgencyTakeFreightStatus(),
                WaybillParam.AgencyTakeFreightStatus.STATUS_DRIVER.getCode()) == 0) {
            waybillParam.setAgencyTakeFreightStatus(WaybillParam.AgencyTakeFreightStatus.STATUS_CMANAGER.getCode());
        } else {
            throw new BusinessException("statusError", "errors.common.prompt", "运单状态错误");
        }

        waybillParamService.update(waybillParam, loginEmployee);

        scatteredMsgService.customerManConfirmFeeDeliveryMsg(waybill, loginEmployee);

    }

    //V3 对账版本后停用
    @Deprecated
    @Override
    public void confirmFreightAccept(int waybillId, LoginEmployee loginEmployee) throws BusinessException {
        Waybill waybill = waybillCommonService.getWaybillById(waybillId);
        if (waybill == null) throw new BusinessException("waybillNotExist", "errors.paramError");

        if (waybill.getStatus() != Waybill.Status.DELIVERIED.getCode()) {
            throw new BusinessException("statusError", "waybill.error.status");
        }

        waybill.setStatus(Waybill.Status.PAIED.getCode());
        waybill.setStatusView(Waybill.StatusView.FINISH.getCode());
        if (null == waybill.getProjectId()) {
            waybill.setReconciliationStatus(Waybill.ReconciliationStatus.HAS_RECONCILIATION.getCode());
            waybill.setSettlementStatus(Waybill.SettlementStatus.HAS_CLEAR.getCode());
            waybill.setReceiptStatus(Waybill.ReceiptStatus.HAS_COLLECTION.getCode());
        }

        waybillCommonService.update(waybill, loginEmployee);
        waybillCommonService.increaseWaybillCount(waybill);

        // 推送消息
        scatteredMsgService.customerManConfirmFreightAcceptMsg(waybill, loginEmployee);
    }

    /**
     * 设置运单的附加功能
     *
     * @param scatteredWaybillViewVo
     * @param truckRequire
     */
    private void buildAdditionalFunction(ScatteredWaybillViewVo scatteredWaybillViewVo, TruckRequire truckRequire) {
        // 用车额外功能枚举
        String funStr = truckRequire.getAdditionalFunctionIds();
        if (StringUtils.isBlank(funStr)) return;

        scatteredWaybillViewVo.setAdditionalFunctions(additionalFunctionService.getAdditionFunctionByIds(funStr));
    }

    // 配送地址信息
    private void buildDeliveryRoute(ScatteredWaybillViewVo scatteredVo, Waybill waybill) {
        if (scatteredVo == null) return;

        if (waybill == null) return;

        // 取货地
        WaybillDeliveryAddress srcAddr = waybillDeliveryAddressService.findByWaybillId(waybill.getWaybillId());

        List<WaybillDeliveryAddress> srcAddrs = new ArrayList<>();
        srcAddrs.add(srcAddr);

        // 配送地
        List<WaybillReceiveAddress> destAddr = waybillReceiveAddressService.findAllByWaybillId(waybill.getWaybillId());
        scatteredVo.setSrcAddress(srcAddrs);
        scatteredVo.setDestAddress(destAddr);
    }

    @Override
    public Integer createScatteredWaybillForCargoOwner(ScatteredWaybillCreateVo scatteredWaybillCreateVo,
            LoginEcoUser loginEcoUser) throws BusinessException {
        this.checkUserStatus(loginEcoUser.getEcoUserId(), loginEcoUser);
        // 参数完整性检查
        if (scatteredWaybillCreateVo == null)
            throw new BusinessException("paramNullError", "errors.paramCanNotNullWithName", "运单");
        Waybill waybill = scatteredWaybillCreateVo.getWaybill();
        if (waybill == null) throw new BusinessException("paramNullError", "errors.paramCanNotNullWithName", "运单");
        // 业务区域必填
        if (StringUtils.isBlank(waybill.getAreaCode()))
            throw new BusinessException("paramNullError", "errors.paramCanNotNullWithName", "运单所属业务区域");

        waybill.setTest(loginEcoUser.isTest());
        // 获取货主信息
        TruckCustomer truckCustomer = null;
        if (truckCustomer == null)
            throw new BusinessException("truckCustomerNotExistError", "errors.common.prompt", "货主不存在");

        waybill.setTruckCustomerId(truckCustomer.getTruckCustomerId());
        // 设置默认企业货主
        this.assignCustomer(waybill, loginEcoUser);
        // 业务区域
        waybill.setTenantCode(loginEcoUser.getTenantCode());
        waybill.setTenantId(loginEcoUser.getTenantId());
        waybill.setWaybillSource(Waybill.WaybillSource.CARGO_OWNER.getCode());
        Integer id = this.createScatteredWaybill(scatteredWaybillCreateVo, loginEcoUser);

        // 如果有客户经理通知客户经理
        scatteredMsgService.cargoOwnerCreateBillMsg(waybill, loginEcoUser);

        dispatchingTruckService.findTruck(id, loginEcoUser);
        return id;
    }

    /**
     * 自由货主指派默认企业客户
     *
     * @param waybill
     * @param loginEcoUser
     */
    private void assignCustomer(Waybill waybill, LoginEcoUser loginEcoUser) {
        // 判断是否有客户经理
        ConsignorContactsInfo contactsInfo = customerInfoService.findConsignorContactsByUser(loginEcoUser);
        CustomerInfo customerInfo = null;
        if (contactsInfo == null) return;

        // --有 设置客户经理
        waybill.setCustomerId(contactsInfo.getCustomerId());
        customerInfo = customerInfoService.findByCrmId(contactsInfo.getCustomerId());
        if (customerInfo == null) return;

        waybill.setCustomerManagerId(customerInfo.getCustomerManagerUserId());
        waybill.setCustomerId(customerInfo.getCustomerId());
        waybill.setAreaCode(customerInfo.getAreaCode());
    }

    @Override
    public List<Integer> createScatteredWaybillForCustomerManager(ScatteredWaybillCreateVo scatteredWaybillCreateVo,
            LoginUser loginUser) throws BusinessException {

        // 参数完整性检查
        if (scatteredWaybillCreateVo == null)
            throw new BusinessException("paramNullError", "errors.paramCanNotNullWithName", "运单");
        Waybill waybill = scatteredWaybillCreateVo.getWaybill();
        if (waybill == null) throw new BusinessException("paramNullError", "errors.paramCanNotNullWithName", "运单");
        //子公司属性不能为空
//        if(null == waybill.getDepartmentId()){
//            throw new BusinessException("departmentCodeNotNull", "waybill.error.departmentCodeNotNull");
//        }
        // 货主不能为空
        if (waybill.getCustomerId() == null)
            throw new BusinessException("customerNullError", "errors.paramCanNotNullWithName", "所属客户");
        // 联系人不能为空
        if (waybill.getTruckCustomerId() == null)
            throw new BusinessException("consignorAccountNullError", "errors.paramCanNotNullWithName", "联系人");

        // 运单所属客户经理不能为空
        if (waybill.getCustomerManagerId() == null)
            throw new BusinessException("customerManagerIdNullError", "errors.paramCanNotNullWithName", "客户经理");
        // 运单来源不能为空
        if (waybill.getWaybillSource() == null)
            throw new BusinessException("waybillSourceNullError", "errors.paramCanNotNullWithName", "运单来源");

        // this.checkUserStatus(waybill.getTruckCustomerId(), loginEmployee);

        // 转运承运商参数校验
        if (scatteredWaybillCreateVo.getWaybillCarrierVo() != null) {
            waybillTransformToCarrierService.transformBillCheck(scatteredWaybillCreateVo.getWaybillCarrierVo());
        }

        waybill.setTest(loginUser.isTest());
        // waybill.setCustomerManagerId(loginEmployee.getEmployeeId());
        // waybill.setOwnerEmployeeId(loginEmployee.getEmployeeId());
        // 业务区域
        CustomerInfo customerInfo = customerInfoService.findCusInfoById(waybill.getCustomerId());
        waybill.setAreaCode(customerInfo.getAreaCode());
        waybill.setTenantCode(customerInfo.getTenantCode());
        waybill.setTenantId(customerInfo.getTenantId());
        // 运单来源由调用方确定
        // waybill.setWaybillSource(Waybill.WaybillSource.JUMA_CLIENT.getCode());

        // 是否需要批量建单
        if (scatteredWaybillCreateVo.getCreateBatchAmount() == null) {
            scatteredWaybillCreateVo.setCreateBatchAmount(1);
        }
        List<Integer> waybillIds = new ArrayList<>();
        for (int i = 0; i < scatteredWaybillCreateVo.getCreateBatchAmount(); i++) {
            int id = this.createScatteredWaybill(scatteredWaybillCreateVo, loginUser);
            waybillIds.add(id);

            // 执行转运逻辑
            if (scatteredWaybillCreateVo.getWaybillCarrierVo() != null) {
                waybillTransformToCarrierService.saveTransformBillParam(waybill,
                        scatteredWaybillCreateVo.getWaybillParam(), scatteredWaybillCreateVo.getWaybillCarrierVo(),
                        loginUser);
                waybillTransformToCarrierService.dispatch(scatteredWaybillCreateVo, loginUser);
            }

            scatteredMsgService.customerManagerCreateBillMsg(waybill, loginUser);

            // 是否系统派单
            if (NumberUtils.compare(waybill.getReceiveWay(), Waybill.ReceiveWay.AUTO_ASSIGN.getCode()) == 0
                    || NumberUtils.compare(waybill.getBusinessBranch(),
                            Waybill.BusinessBranch.BRANCH_SCATTERED.getCode()) == 0) {
                dispatchingTruckService.findTruck(id, loginUser);
            }
        }

        return waybillIds;
    }

    private Integer createScatteredWaybill(ScatteredWaybillCreateVo scatteredWaybillCreateVo, LoginUser loginUser)
            throws BusinessException {
        Waybill waybill = scatteredWaybillCreateVo.getWaybill();
        TruckRequire truckRequire = scatteredWaybillCreateVo.getTruckRequire();
        List<WaybillDeliveryAddress> srcAddress = scatteredWaybillCreateVo.getSrcAddress();
        List<WaybillReceiveAddress> destAddress = scatteredWaybillCreateVo.getDestAddress();
        WaybillParam waybillParam = scatteredWaybillCreateVo.getWaybillParam();

        Sop sop = sopService.findNewestVersionSopByTenantId(loginUser.getTenantId());
        if (sop == null) {
            throw new BusinessException("errors.notFound.Sop", "errors.notFound.Sop", loginUser.getTenantId());
        }

        if (truckRequire == null)
            throw new BusinessException("truckRequireNullError", "errors.paramCanNotNullWithName", "用车要求");
        if (CollectionUtils.isEmpty(srcAddress))
            throw new BusinessException("srcAddressNullError", "errors.paramCanNotNullWithName", "取货地");
        if (CollectionUtils.isEmpty(destAddress))
            throw new BusinessException("destAddressNullError", "errors.paramCanNotNullWithName", "配送地");
        if (waybillParam == null) {
            waybillParam = new WaybillParam();
            scatteredWaybillCreateVo.setWaybillParam(waybillParam);
        }
        if (null != waybill.getNeedDeliveryPointNote() && waybill.getNeedDeliveryPointNote() == 1) {
            waybill.setIsChangeDeliveryPoint(Waybill.ChangeDeliveryPoint.NOT_UPLOAD.getCode());
        }
        waybillParam.setSopId(sop.getSopId());

        this.dataTransform(waybill, truckRequire);

        // 线路信息校验
        this.checkWaybillTimeAndPoint(waybill, srcAddress, destAddress);
        // 建单校验
        this.checkWaybillRequireInfo(waybill, truckRequire, waybillParam);
        this.checkCustomerInfo(waybill, loginUser);
        TruckType truckType = truckTypeService.getTruckType(truckRequire.getTruckTypeId());
        this.fillEmptyRequire(waybill, truckType, truckRequire);
        this.checkCarryCapacity(waybill, truckRequire, truckType, loginUser);

        waybill.setEntryLicense(truckRequire.getEntryLicense());// 为了运单池过滤

        // 添加重量
        if (StringUtils.isNotBlank(truckRequire.getGoodsWeight())) {
            waybill.setGoodsWeight(
                    new BigDecimal(truckRequire.getGoodsWeight()).multiply(new BigDecimal("1000")).intValue());
        }
        // 添加体积
        if (StringUtils.isNotBlank(truckRequire.getGoodsVolume())) {
            waybill.setGoodsVolume(new BigDecimal(truckRequire.getGoodsVolume()));
        }

        // 运单数据保存
        // 运单号
        waybill.setWaybillNo(waybillCommonService.getWaybillNo());
        // 预估完成时间
        if (NumberUtils.compare(waybill.getBusinessBranch(), Waybill.BusinessBranch.BRANCH_SCATTERED.getCode()) == 0) {
            // 零担4小时
            waybill.setCmEstimateFinishTime(
                    DateUtils.addHours(waybill.getPlanDeliveryTime(), Constants.SCATTERED_ESTIMATE_FINISH_TIME));
        }

        this.calculateAfterTaxFreight(waybill, truckRequire);

        return this.saveData(scatteredWaybillCreateVo, loginUser);
    }

    /**
     * 吨方件为空时直接填当前车辆最大载重
     *
     * @param waybill
     * @param truckRequire
     */
    private void fillEmptyRequire(Waybill waybill, TruckType truckType, TruckRequire truckRequire) {
        if (NumberUtils.compare(waybill.getBusinessBranch(), Waybill.BusinessBranch.BRANCH_FULL.getCode()) != 0) return;

        if (truckRequire == null) return;
        if (truckType == null) return;

        // 吨
        if (StringUtils.isBlank(truckRequire.getGoodsWeight())) {
            truckRequire.setGoodsWeight(Constants.DECIMAL_2_FORMAT.format(truckType.getTruckTypeLoad()));
        }
        // 方
        if (StringUtils.isBlank(truckRequire.getGoodsVolume())) {
            truckRequire.setGoodsVolume(Constants.DECIMAL_2_FORMAT.format(truckType.getTruckTypeVolume()));
        }

    }

    /**
     * 计算税后费用
     *
     * @param waybill
     * @param truckRequire
     */
    private void calculateAfterTaxFreight(Waybill waybill, TruckRequire truckRequire) {
        if (truckRequire.getTaxRateValue() == null) return;
        if (waybill.getEstimateFreight() == null) return;
        // 原价
        BigDecimal freight = waybill.getEstimateFreight().divide(truckRequire.getTaxRateValue().add(BigDecimal.ONE), 2, BigDecimal.ROUND_HALF_UP);

        waybill.setAfterTaxFreight(freight);
    }

    /**
     * 用车要求转换
     *
     * @param waybill
     * @param truckRequire
     */
    private void dataTransform(Waybill waybill, TruckRequire truckRequire) {
        // 默认字段设置
        if (waybill.getOnlyLoadCargo() == null) {
            waybill.setOnlyLoadCargo(Integer.valueOf(Constants.flag_false));
        }
        if (waybill.getNeedDeliveryPointNote() == null) {
            waybill.setNeedDeliveryPointNote(Integer.valueOf(Constants.flag_false));
        }

        if (waybill.getShow4DriverFreight() == null) {
            waybill.setShow4DriverFreight(new BigDecimal("0"));
        }

        waybill.setReceiptType(Waybill.ReceiptType.CUSTOMER_CHEQUES.getCode());
        if (waybill.getReceiveWay() == null) {
            waybill.setReceiveWay(Waybill.ReceiveWay.AUTO_ASSIGN.getCode());
        }
        // 改价审核
        waybill.setUpdateFreightAuditStatus(0);
        waybill.setReconciliationStatus(Waybill.ReconciliationStatus.INIT.getCode());
        waybill.setReceivableReconcilicationStatus(Waybill.ReconciliationStatus.INIT.getCode());
        // 结算状态
        waybill.setSettlementStatus(Waybill.SettlementStatus.INIT.getCode());
        // 收款状态
        waybill.setReceiptStatus(Waybill.ReceiptStatus.INIT.getCode());

        // 运单状态
        if (waybill.getStatus() == null) {
            waybill.setStatus(Waybill.Status.WATING_RECEIVE.getCode());
        }
        if (waybill.getStatusView() == null) {
            waybill.setStatusView(Waybill.StatusView.WATING_RECEIVE.getCode());
        }

        // 是否结算
        // waybill.setCheckout(false);

        // 回单
        String funStr = truckRequire.getAdditionalFunctionIds();
        waybill.setNeedReceipt(Waybill.NeedReceipt.NO_NEED_RECEIPT.getCode());
        if (StringUtils.isNotBlank(funStr)) {
            String[] fids = funStr.split(",");
            AdditionalFunction needReceiptFun = additionalFunctionService
                    .findAdditionFunctionByKey(AdditionalFunction.FunctionKeys.NEED_RECEIPT.name());
            Arrays.sort(fids);
            if (Arrays.binarySearch(fids, needReceiptFun.getAdditionalFunctionId().toString()) >= 0) {
                waybill.setNeedReceipt(Waybill.NeedReceipt.NOT_HAVE_UPLOAD.getCode());
            }
        }

    }

    // 保存运单数据
    private int saveData(ScatteredWaybillCreateVo scatteredWaybillCreateVo, LoginUser loginUser) {
        Waybill waybill = scatteredWaybillCreateVo.getWaybill();
        List<WaybillDeliveryAddress> srcAddress = scatteredWaybillCreateVo.getSrcAddress();
        List<WaybillReceiveAddress> destAddress = scatteredWaybillCreateVo.getDestAddress();
        WaybillParam waybillParam = scatteredWaybillCreateVo.getWaybillParam();
        // 保存运单信息
        waybillCommonService.insert(waybill, loginUser);
        // 保存发货地,取货地,用车要求
        WaybillBo waybillBo = new WaybillBo();
        waybillBo.setWaybill(waybill);
        waybillBo.setTruckRequire(scatteredWaybillCreateVo.getTruckRequire());
        waybillBo.setDeliveryAddress(srcAddress);
        waybillBo.setReceiveAddress(destAddress);
        waybillCommonService.insertLinkTable(waybillBo, loginUser);

        // 记录计价规则和搬运费
        waybillCommonService.saveWaybillParam(waybillParam, waybill, loginUser);

        // 保存用户常用路线
        UserRouteMaster.BusinessBranch type = null;
        if (NumberUtils.compare(waybill.getBusinessBranch(), Waybill.BusinessBranch.BRANCH_FULL.getCode()) == 0) {
            type = UserRouteMaster.BusinessBranch.TYPE_MULTIPLY;
        } else if (NumberUtils.compare(waybill.getBusinessBranch(),
                Waybill.BusinessBranch.BRANCH_SCATTERED.getCode()) == 0) {
            type = UserRouteMaster.BusinessBranch.TYPE_SINGLE;
        }
        if (loginUser instanceof LoginEcoUser) {
            userRouteUtils.addUserRoute(srcAddress, destAddress, type, loginUser);
        } else if (loginUser instanceof LoginEmployee) {
            userRouteUtils.addUserRouteForManager(srcAddress, destAddress, type, waybill.getTruckCustomerId());
        }

        return waybill.getWaybillId();
    }

    /**
     * 用车时间和配送地校验
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
        if (fetchAdds.getContactName() != null
                && fetchAdds.getContactName().length() > WaybillCommonService.WAYBILL_CONTACT_NAME_LENGTH) {
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
                        throw new BusinessException("dataTooLong", "errors.validation.dataTooLong",
                                "第" + temp + "目的地联系人");
                    }
                }
            }
        } else {
            // 配送地不能为空
            throw new BusinessException("validationFailure", "errors.validation.toAddress");
        }

        // regionCode
//        if (StringUtils.isBlank(waybill.getRegionCode())) {
//            throw new BusinessException("regionCodeNullError", "errors.paramCanNotNullWithName", "所属区域");
//        }

    }

    /**
     * 校验客户信息
     * @param waybill
     * @param loginUser
     */
    private void checkCustomerInfo(Waybill waybill, LoginUser loginUser){

        CustomerInfo customerInfo = customerInfoService.findCusInfoById(waybill.getCustomerId());
        if (customerInfo == null)
            throw new BusinessException("waybillCustomerNotFound", "waybill.errors.customerInfoNotNull");
    }

    /**
     * 用车要求相关检查
     *
     * @param waybill
     * @param truckRequire
     * @param waybillParam
     */
    private void checkWaybillRequireInfo(Waybill waybill, TruckRequire truckRequire, WaybillParam waybillParam) {
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

        // 代收货款
        String funStr = truckRequire.getAdditionalFunctionIds();

        if (StringUtils.isNotBlank(funStr)) {
            String[] fids = funStr.split(",");
            AdditionalFunction collectionPaymentFun = additionalFunctionService
                    .findAdditionFunctionByKey(AdditionalFunction.FunctionKeys.COLLECTION_PAYMENT.name());
            Arrays.sort(fids);
            if (Arrays.binarySearch(fids, collectionPaymentFun.getAdditionalFunctionId().toString()) >= 0) {
                // 需要代收货款则需要输入金额
                if (waybillParam == null || waybillParam.getAgencyTakeFreight() == null)
                    throw new BusinessException("canNotBeBlank", "errors.validation.canNotBeBlank", "代收货款");
            }
        }

        // 业务类型--这里必须是落地配
        if (waybill.getBusinessBranch() == null)
            throw new BusinessException("BusinessBranchNull", "errors.validation.canNotBeBlank", "业务类型");

        if (NumberUtils.compare(waybill.getBusinessBranch(), Waybill.BusinessBranch.BRANCH_FULL.getCode()) != 0
                && NumberUtils.compare(waybill.getBusinessBranch(),
                        Waybill.BusinessBranch.BRANCH_SCATTERED.getCode()) != 0)
            throw new BusinessException("BusinessBranchError", "errors.paramErrorWithName", "业务类型");

    }

    @Override
    public void customerManagerCancelBill(Waybill waybill, LoginEmployee loginEmployee) throws BusinessException {
        Waybill original = this.buildCancelWaybillInfo(waybill);

        waybillCommonService.update(waybill, loginEmployee);

        original.setWaybillCancelRemark(waybill.getWaybillCancelRemark());
        original.setStatusView(waybill.getStatusView());
        original.setStatus(waybill.getStatus());
        scatteredMsgService.customerManBillCancelMsg(original, loginEmployee);

        // 通知数据平台
        dispatchingTruckService.buildFeature(waybill.getWaybillId(), Feature.Operate.Dispatcher_Cancel, loginEmployee);
    }

    @Override
    public void cargoOwnerCancelBill(Waybill waybill, LoginEcoUser loginEcoUser) throws BusinessException {
        Waybill original = this.buildCancelWaybillInfo(waybill);

        waybillCommonService.update(waybill, loginEcoUser);

        original.setWaybillCancelRemark(waybill.getWaybillCancelRemark());
        original.setStatusView(waybill.getStatusView());
        original.setStatus(waybill.getStatus());

        scatteredMsgService.cargoOwnerCancelBillMsg(original, loginEcoUser);

        // 通知数据平台
        dispatchingTruckService.buildFeature(waybill.getWaybillId(), Feature.Operate.Dispatcher_Cancel, loginEcoUser);
    }

    private Waybill buildCancelWaybillInfo(Waybill waybill) {
        if (waybill == null) throw new BusinessException("waybillNotExist", "errors.paramErrorWithName", "运单信息");
        // id
        if (waybill.getWaybillId() == null)
            throw new BusinessException("waybillIdNullError", "errors.paramCanNotNullWithName", "运单id");
        // 原因
        if (StringUtils.isBlank(waybill.getWaybillCancelRemark()))
            throw new BusinessException("reasonNullError", "errors.paramCanNotNullWithName", "取消原因");

        Waybill original = waybillCommonService.getWaybillById(waybill.getWaybillId());
        if (original == null) throw new BusinessException("waybillNotExist", "errors.common.prompt", "运单不存在");

        // 未指派&待配送可以取消
        if (NumberUtils.compare(original.getStatusView(), Waybill.StatusView.WATING_DELIVERY.getCode()) != 0
                && NumberUtils.compare(original.getStatusView(), Waybill.StatusView.WATING_RECEIVE.getCode()) != 0
                && NumberUtils.compare(original.getStatusView(), Waybill.StatusView.TEMP.getCode()) != 0)
            throw new BusinessException("waybillStatusError", "waybill.error.notCancel");

        waybill.setCancelChannel(Waybill.CancelChannel.JUMA_CLIENT.getCode());
        waybill.setStatus(Waybill.Status.SYS_CANCEL.getCode());
        waybill.setStatusView(Waybill.StatusView.CANCEL.getCode());
        return original;
    }

    @Override
    public AtFenceResultVo isAtFenceArea(CityAdressData srcAddress, List<CityAdressData> toAddress, LoginUser loginUser)
            throws BusinessException {
        AtFenceResultVo rst = new AtFenceResultVo();

        if (srcAddress != null && StringUtils.isNotBlank(srcAddress.getCoordinate())) {
            // 取货地是否在业务区域
            String[] businessCoordinates = srcAddress.getCoordinate().split(",");
            Double lng = NumberUtils.createDouble(businessCoordinates[0]);
            Double lat = NumberUtils.createDouble(businessCoordinates[1]);

            log.info("scatteredWaybillService->isAtFenceArealng:{},lat:{}", lng, lat);
            List<Integer> businessIds = fenceService.fenceTrigger(loginUser, Constants.FENCE_TYPE_AT_BUSINESS_AREA, lng,
                    lat);
            log.info("scatteredWaybillService->isAtFenceArea:{}", JSON.toJSONString(businessIds));
            if (CollectionUtils.isNotEmpty(businessIds)) {
                rst.setAtBusinessArea(true);
            }

            // 取货地是否需要入城
            List<Integer> cityIds = fenceService.fenceTrigger(loginUser, Constants.FENCE_TYPE_AT_CITY, lng, lat);
            if (CollectionUtils.isNotEmpty(cityIds)) {
                rst.setAtCity(true);
            }

            // 取货地是否在禁货区
            List<Integer> forbiddenIds = fenceService.fenceTrigger(loginUser, Constants.FENCE_TYPE_AT_FORBIDDEN_AREA,
                    lng, lat);
            if (CollectionUtils.isNotEmpty(forbiddenIds)) {
                rst.setAtForbiddenArea(true);
                rst.setForBiddenType(AtFenceResultVo.ForbiddenType.sourceArea);
            }
        }

        // 配送地信息判断
        int idx = 1;
        if (CollectionUtils.isNotEmpty(toAddress)) {
            for (CityAdressData toAddr : toAddress) {
                String[] areaCoordinates = toAddr.getCoordinate().split(",");
                Double lng = NumberUtils.createDouble(areaCoordinates[0]);
                Double lat = NumberUtils.createDouble(areaCoordinates[1]);

                // 配送地是否需要入城
                if (!rst.getAtCity()) {
                    List<Integer> cityIds = fenceService.fenceTrigger(loginUser, Constants.FENCE_TYPE_AT_CITY, lng,
                            lat);

                    if (CollectionUtils.isNotEmpty(cityIds)) {
                        rst.setAtCity(true);
                    }
                }

                // 配送地是否在禁货区
                if (!rst.isAtForbiddenArea()) {
                    List<Integer> forbiddenIds = fenceService.fenceTrigger(loginUser,
                            Constants.FENCE_TYPE_AT_FORBIDDEN_AREA, lng, lat);
                    if (CollectionUtils.isNotEmpty(forbiddenIds)) {
                        rst.setAtForbiddenArea(true);
                        rst.setForBiddenType(AtFenceResultVo.ForbiddenType.destinationArea);
                        rst.setForbiddenAreaIndex(idx);
                    }
                }

                idx++;
            }

        }

        return rst;
    }

    /**
     * 用户认证状态校验
     *
     * @param id
     * @throws BusinessException
     */
    private void checkUserStatus(int id, LoginUser loginUser) throws BusinessException {
        EcoUser ecoUser = null;
        UserInfo userInfo = null;
        if (loginUser instanceof LoginEcoUser) {
            ecoUser = ecoUserService.findEcoUser(id, loginUser);
            userInfo = userService.findUserInfo(ecoUser.getUserId());
        } else {
            throw new BusinessException("userTypeError", "errors.common.prompt", "不支持的用户类型");
        }

        if (userInfo.getUserVerification() == null) throw new BusinessException("noVerification", "您还未完成身份认证,暂不能继续发单哦");

        UserVerification verification = userInfo.getUserVerification();
        if (NumberUtils.compare(verification.getIdcardStatus(),
                UserVerification.AuthenticationStatus.not.getValue()) == 0) {
            throw new BusinessException("noVerification", "您还未完成身份认证,暂不能继续发单哦");
        } else if (NumberUtils.compare(verification.getIdcardStatus(),
                UserVerification.AuthenticationStatus.reject.getValue()) == 0) {
            throw new BusinessException("rejectError", "您上传的手持身份证照未通过审核,请重新上传后再进行发单");
        }

    }

    /**
     * 运力校验
     *
     * @param waybill
     * @param truckRequire
     */
    private void checkCarryCapacity(Waybill waybill, TruckRequire truckRequire, TruckType truckType,
            LoginUser loginUser) {
        // 货物方吨件
        if (StringUtils.isBlank(truckRequire.getGoodsVolume()))
            throw new BusinessException("GoodsVolumeNull", "errors.validation.canNotBeBlank", "方量");
        double volume = new Double(truckRequire.getGoodsVolume());
        if (StringUtils.isBlank(truckRequire.getGoodsWeight()))
            throw new BusinessException("GoodsWeightNull", "errors.validation.canNotBeBlank", "重量");
        double weight = new Double(truckRequire.getGoodsWeight());
        // if (StringUtils.isBlank(truckRequire.getGoodsAmount()))
        // throw new BusinessException("GoodsAmountNull", "errors.validation.canNotBeBlank", "件数");
        // double amount = new Double(truckRequire.getGoodsAmount());
        Integer distance = waybill.getEstimateDistance();
        if (distance == null)
            throw new BusinessException("estimateDistanceNull", "errors.validation.canNotBeBlank", "距离");

        if (NumberUtils.compare(waybill.getBusinessBranch(), Waybill.BusinessBranch.BRANCH_FULL.getCode()) == 0) {// 整车
            // 车型校验
            if (truckRequire.getTruckTypeId() == null)
                throw new BusinessException("truckTypeNull", "errors.validation.canNotBeBlank", "车型");

            if (truckType == null) throw new BusinessException("truckTypeNotExist", "errors.paramErrorWithName", "车型");

            if (truckType.getTruckTypeVolume() != null) {// FIXME 这里按立方米处理
                Double maxVolume = truckType.getTruckTypeVolume().doubleValue();
                if (NumberUtils.compare(volume, maxVolume) == 1)
                    throw new BusinessException("GoodsVolumeOverCapacityError", "errors.common.prompt",
                            "货物的吨方件超出整车单车最大装载值哦~请拆分成多车下单");
            }

            if (truckType.getTruckTypeLoad() != null) {// FIXME 这里按吨处理
                Double maxWeight = truckType.getTruckTypeLoad().doubleValue();
                if (NumberUtils.compare(weight, maxWeight) == 1)
                    throw new BusinessException("GoodsWeightOverCapacityError", "errors.common.prompt",
                            "货物的吨方件超出整车单车最大装载值哦~请拆分成多车下单");
            }

        } else {// 零担
            FreightRule rule = freightRuleService.findByRegionCode(waybill.getRegionCode(), loginUser);
            if (rule == null) throw new BusinessException("freightRuleNull", "errors.common.prompt", "没有运费规则");
            if (rule.getMaxMiles() == null)
                throw new BusinessException("MaxMilesError", "errors.common.prompt", "没有最大里程");
            if (rule.getMaxVolume() == null)
                throw new BusinessException("MaxVolumeError", "errors.common.prompt", "没有最大方量");
            if (rule.getMaxWeight() == null)
                throw new BusinessException("MaxWeightError", "errors.common.prompt", "没有最大载重");

            // FIXME 这里按立方分米和千克处理
            double tempVolume = volume * 1000;
            double tempWeight = weight * 1000;

            if (NumberUtils.compare(distance, rule.getMaxMiles()) == 1
                    || NumberUtils.compare(distance, rule.getMaxMiles()) == 0)
                throw new BusinessException("MaxMilesOverMaxLimit", "errors.common.prompt", "根据您的用车要求只能使用整车下单哟");
            if (NumberUtils.compare(tempVolume, rule.getMaxVolume()) == 1
                    || NumberUtils.compare(tempVolume, rule.getMaxVolume()) == 0)
                throw new BusinessException("MaxVolumeOverMaxLimit", "errors.common.prompt", "根据您的用车要求只能使用整车下单哟");
            if (NumberUtils.compare(tempWeight, rule.getMaxWeight()) == 1
                    || NumberUtils.compare(tempWeight, rule.getMaxWeight()) == 0)
                throw new BusinessException("MaxWeightOverMaxLimit", "errors.common.prompt", "根据您的用车要求只能使用整车下单哟");
        }
    }

    /**
     * 完成配送
     *
     * @param waybill
     * @param loginUser
     * @throws BusinessException
     */
    @Override
    public void changeToDeliveried(Waybill waybill, LoginUser loginUser) throws BusinessException {
        log.info("stand module for changeToDeliveried");
        WaybillParam waybillParamDb = waybillParamService.findByWaybillId(waybill.getWaybillId());
        if (waybillParamDb == null)
            throw new BusinessException("WaybillParamNotExist", "errors.common.prompt", "WaybillParam不存在");

        WaybillParam updateWaybillParam = new WaybillParam();
        updateWaybillParam.setParamId(waybillParamDb.getParamId());
        updateWaybillParam.setValuationConstJson(waybill.getValuationConstJson());
        updateWaybillParam.setAgencyTakeFreightStatus(WaybillParam.AgencyTakeFreightStatus.STATUS_DRIVER.getCode());
        waybillParamService.update(updateWaybillParam, loginUser);

        Waybill dbEntity = waybillCommonService.getWaybillById(waybill.getWaybillId());
        changeToDeliveriedAtBefore(dbEntity, loginUser);
        Waybill entity = new Waybill();
        entity.setWaybillId(waybill.getWaybillId());
        entity.setFinishTime(new Date());
        entity.setStatus(Waybill.Status.PAIED.getCode());
        entity.setStatusView(Waybill.StatusView.FINISH.getCode());
        //进入未对账状态
        entity.setReconciliationStatus(Waybill.ReconciliationStatus.NOT_RECONCILIATION.getCode());
        entity.setReceivableReconcilicationStatus(Waybill.ReconciliationStatus.NOT_RECONCILIATION.getCode());

        waybillCommonService.update(entity, loginUser);
        // 算价 TODO

        // 订单完善
        waybillParamService.doCompleteWaybillParam(dbEntity.getWaybillId(), waybill.getValuationConstJson(), loginUser);

        // 自动派车黑名单
        dispatchingTruckService.removeBlacklistCache(waybill.getWaybillId());

        // 更新司机状态为空闲
        driverService.updateDriverTaskStatus(dbEntity.getDriverId(), Driver.TaskStatus.ABLE);
        // 清除电子围栏
        waybillAutoFenceServicve.removeAllFenceId(waybill.getWaybillId(), loginUser);
        // 推送消息
        scatteredMsgService.pushFinishWaybillMsg(dbEntity, loginUser);
        // 通知数据平台
        dispatchingTruckService.buildFeature(waybill.getWaybillId(), Feature.Operate.Finish_Delivery, loginUser);

        // 同步更改转运方的运单信息
        this.modofyTransformBillToDeliveried(waybill.getWaybillId(), loginUser);
    }

    // 同步更改转运方的运单信息
    public void modofyTransformBillToDeliveried(Integer transformBillLinkId, LoginUser loginUser) {
        WaybillParam waybillParam = waybillParamService.findByTransformBillLinkId(transformBillLinkId);
        if (null == waybillParam) {
            return;
        }
        Waybill waybill = waybillCommonService.getWaybillById(waybillParam.getWaybillId());

        waybill.setFinishTime(new Date());
        waybill.setStatus(Status.PAIED.getCode());
        waybill.setStatusView(Waybill.StatusView.FINISH.getCode());
        //进入未对账状态
        waybill.setReconciliationStatus(Waybill.ReconciliationStatus.NOT_RECONCILIATION.getCode());
        waybill.setReceivableReconcilicationStatus(Waybill.ReconciliationStatus.NOT_RECONCILIATION.getCode());

        waybillCommonService.update(waybill, loginUser);

        // 推送消息
        scatteredMsgService.pushFinishWaybillMsg(waybill, loginUser);
    }

    @Override
    public BigDecimal computeFreight(DistanceAndPriceParamVo distanceAndPriceParamVo, LoginUser loginUser)
            throws BusinessException {
        if (distanceAndPriceParamVo == null)
            throw new BusinessException("distanceAndPriceParamVoNull", "errors.paramErrorWithName", "价格计算");

        Waybill waybill = distanceAndPriceParamVo.getWaybill();
        if (waybill == null) throw new BusinessException("waybillNull", "errors.validation.canNotBeBlank", "运单信息");

        TruckRequire truckRequire = distanceAndPriceParamVo.getTruckRequire();
        if (truckRequire == null)
            throw new BusinessException("truckRequireNull", "errors.validation.canNotBeBlank", "用车要求");

        WaybillParam waybillParam = distanceAndPriceParamVo.getWaybillParam();

        BigDecimal realMiles = new BigDecimal(waybill.getEstimateDistance());
        BigDecimal tollsFee = new BigDecimal(waybill.getTolls() == null ? 0 + "" : waybill.getTolls() + "");
        BigDecimal collectionPayment = waybillParam == null || waybillParam.getAgencyTakeFreight() == null
                ? BigDecimal.ZERO : waybillParam.getAgencyTakeFreight();
        BigDecimal realVolume = NumberUtils.createBigDecimal(truckRequire.getGoodsVolume());
        BigDecimal realWeight = NumberUtils.createBigDecimal(truckRequire.getGoodsWeight());
        BigDecimal taxRateValue = truckRequire.getTaxRateValue() == null ? BigDecimal.ZERO
                : truckRequire.getTaxRateValue();

        PrivateFreightContext context = new PrivateFreightContext();
        context.setKm(realMiles);
        context.setVolumn(realVolume);
        context.setWeight(realWeight);
        context.setCollectionPayment(collectionPayment);
        // 附加要求
        context.setFunctionIds(truckRequire.getAdditionalFunctionIds());
        context.setTollsFee(tollsFee);

        // 装货耗时
        if (waybill.getDeliveryTime() != null && waybill.getArriveDepotTime() != null) {
            BigDecimal shipmentTimeMinute = DateUtil.calDate(waybill.getArriveDepotTime(), waybill.getDeliveryTime(),
                    DateUtil.TimeUnitEnum.MINUTE);
            context.setShipmentMin(shipmentTimeMinute);
            log.info("装货耗时:{}", new String[] { Constants.DECIMAL_2_FORMAT.format(context.getShipmentMin()) });
        }
        // 点位信息
        if (CollectionUtils.isNotEmpty(distanceAndPriceParamVo.getToAddress())) {
            int point = distanceAndPriceParamVo.getToAddress().size();
            context.setPointNum(new BigDecimal(point));
        }

        if (waybill.getBusinessBranch() == Waybill.BusinessBranch.BRANCH_SCATTERED.getCode()) {
            // FreightRule rule = freightRuleService.findByRegionCode(waybill.getRegionCode(), loginUser);
            // if (rule == null) {
            // throw new BusinessException("regionCode.no.rule", "regionCode.no.rule");
            // }

            BigDecimal preTaxFee = privateFreightFactorService.calFreight(waybill.getRegionCode(),
                    FreightEnum.LINGDAN.getCode(), null, context, loginUser);
            if (preTaxFee == null) return null;

            return preTaxFee.setScale(0, BigDecimal.ROUND_HALF_UP).setScale(2);

            // return preTaxFee.multiply(BigDecimal.ONE.add(taxRateValue)).setScale(0, BigDecimal.ROUND_HALF_UP).setScale(2);
            // return FreightRuleFunction.computeForCarpool(rule, realMiles, realVolume, realWeight).multiply(BigDecimal.ONE.add(taxRateValue)).setScale(0,
            // BigDecimal.ROUND_HALF_UP).setScale(2);
        } else if (waybill.getBusinessBranch() == Waybill.BusinessBranch.BRANCH_FULL.getCode()) {
            // PackFreightRule rule = packFreightRuleService.findByRegionAndTruckType(waybill.getRegionCode(), truckRequire.getTruckTypeId(), loginUser);
            // if (rule == null) {
            // throw new BusinessException("regionCode.no.rule", "regionCode.no.rule");
            // }
            BigDecimal preTaxFee = privateFreightFactorService.calFreight(waybill.getRegionCode(),
                    FreightEnum.ZHENGCHE.getCode(), truckRequire.getTruckTypeId(), context, loginUser);
            if (preTaxFee == null) return null;

            return preTaxFee.setScale(0, BigDecimal.ROUND_HALF_UP).setScale(2);
            // return preTaxFee.multiply(BigDecimal.ONE.add(taxRateValue)).setScale(0, BigDecimal.ROUND_HALF_UP).setScale(2);
            // return FreightRuleFunction.computeForPack(rule, realMiles).multiply(BigDecimal.ONE.add(taxRateValue)).setScale(0, BigDecimal.ROUND_HALF_UP).setScale(2);
        }
        return null;
    }

    @Override
    public WaybillDetailInfo getScatteredBillDetail(int waybillId, LoginUser loginUser) throws BusinessException {
        WaybillDetailInfo detailInfo = new WaybillDetailInfo();
        // 运单
        Waybill waybill = waybillCommonService.getWaybillById(waybillId);
        detailInfo.setWaybill(waybill);
        // 线路信息
        detailInfo.setWaybillDeliveryAddresses(waybillDeliveryAddressService.findAllByWaybillId(waybillId));
        detailInfo.setWaybillReceiveAddresses(waybillReceiveAddressService.findAllByWaybillId(waybillId));
        // 客户
        CustomerInfo customerInfo = customerInfoService.findCusInfoById(waybill.getCustomerId());
        detailInfo.setCustomerInfo(customerInfo);
        // 用车要求
        detailInfo.setTruckRequire(truckRequireService.findTruckRequireByWaybillId(waybillId, loginUser));
        detailInfo.setTruckRequireStr(truckRequireService.getTruckRequire(detailInfo.getTruckRequire(), null));

        // 计价规则
        WaybillParam waybillParam = waybillParamService.findByWaybillId(waybillId);
        detailInfo.setWaybillParam(waybillParam);
        // 司机车辆
        Truck truck = truckService.getTruck(waybill.getTruckId());
        DriverTruckInfoBo driverTruckInfoBo = new DriverTruckInfoBo(0, driverService.getDriver(waybill.getDriverId()));
        detailInfo.setDriverTruckInfoBo(driverTruckInfoBo);
        if (truck != null) {
            driverTruckInfoBo.setPlateNumber(truck.getPlateNumber());
            driverTruckInfoBo.setTruckInfo(truckService.findTruckInfoStrByTruckId(truck.getTruckId(), loginUser));
        }
        // 取系统报价
        waybill.setCalculatedFreight(null);
        // 用车人是否可用
        this.buildCanUseTruckCustomer(detailInfo, customerInfo, loginUser);

        return detailInfo;
    }

    private void buildCanUseTruckCustomer(WaybillDetailInfo detailInfo, CustomerInfo customerInfo,
            LoginUser loginUser) {
        if (customerInfo == null) return;
        if (detailInfo == null) return;
        if (detailInfo.getWaybill() == null) return;
        if (detailInfo.getWaybill().getTruckCustomerId() == null) return;
        Integer truckCustomerId = detailInfo.getWaybill().getTruckCustomerId();

        LoginEmployee loginEmployee = new LoginEmployee();
        loginEmployee.setLoginName(loginUser.getLoginName());
        loginEmployee.setTenantId(loginUser.getTenantId());
        loginEmployee.setTenantCode(loginUser.getTenantCode());
        loginEmployee.setUserId(loginUser.getUserId());
        List<TruckCustomer> truckCustomerList = customerInfoService
                .findCargoOwnerFromCrm(customerInfo.getCrmCustomerId(), loginEmployee);
        if (CollectionUtils.isEmpty(truckCustomerList)) {
            detailInfo.setCanUseCustomerInfo(false);
            return;
        }

        for (TruckCustomer truckCustomer : truckCustomerList) {
            if (NumberUtils.compare(truckCustomer.getTruckCustomerId(), truckCustomerId) == 0) {
                // 用车人
                detailInfo.setTruckCustomer(truckCustomer);
                detailInfo.setCanUseCustomerInfo(true);
                return;
            }
        }

        detailInfo.setCanUseCustomerInfo(false);

    }
}
