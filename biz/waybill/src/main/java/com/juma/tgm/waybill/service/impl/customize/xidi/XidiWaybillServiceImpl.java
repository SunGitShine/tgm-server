package com.juma.tgm.waybill.service.impl.customize.xidi;

import com.giants.common.beanutils.BeanUtils;
import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.conf.domain.BusinessArea;
import com.juma.auth.conf.service.BusinessAreaService;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.employee.service.EmployeeService;
import com.juma.auth.user.domain.LoginEcoUser;
import com.juma.auth.user.domain.LoginUser;
import com.juma.auth.user.domain.User;
import com.juma.server.vm.domain.Vehicle;
import com.juma.server.vm.domain1.vo.DriverTenantVO;
import com.juma.server.vm.service.vehicle.AmsServiceV2;
import com.juma.tgm.common.BaseUtil;
import com.juma.tgm.common.Constants;
import com.juma.tgm.common.DateUtil;
import com.juma.tgm.configure.service.ServiceConfService;
import com.juma.tgm.crm.domain.CustomerInfo;
import com.juma.tgm.crm.service.CustomerInfoService;
import com.juma.tgm.driver.domain.Driver;
import com.juma.tgm.driver.service.DriverService;
import com.juma.tgm.landing.waybill.service.DispatchingTruckService;
import com.juma.tgm.project.domain.ValuationWay;
import com.juma.tgm.project.service.RoadMapPriceRuleService;
import com.juma.tgm.receiptManage.service.ReceiptManageService;
import com.juma.tgm.scatteredWaybill.service.ScatteredMsgService;
import com.juma.tgm.scatteredWaybill.service.ScatteredWaybillService;
import com.juma.tgm.tools.service.BusinessAreaCommonService;
import com.juma.tgm.tools.service.VmsCommonService;
import com.juma.tgm.truck.domain.Truck;
import com.juma.tgm.truck.service.TruckService;
import com.juma.tgm.truck.service.TruckTypeService;
import com.juma.tgm.waybill.domain.AppListFilter;
import com.juma.tgm.waybill.domain.CityAdressData;
import com.juma.tgm.waybill.domain.TruckRequire;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.Waybill.Status;
import com.juma.tgm.waybill.domain.WaybillBindFence;
import com.juma.tgm.waybill.domain.WaybillBo;
import com.juma.tgm.waybill.domain.WaybillDeliveryAddress;
import com.juma.tgm.waybill.domain.WaybillDetailInfo;
import com.juma.tgm.waybill.domain.WaybillNotice;
import com.juma.tgm.waybill.domain.WaybillParam;
import com.juma.tgm.waybill.domain.WaybillReceiveAddress;
import com.juma.tgm.waybill.domain.WaybillReceiveAddressCargo;
import com.juma.tgm.waybill.domain.WaybillReceiveAddressResponse;
import com.juma.tgm.waybill.domain.ai.Feature;
import com.juma.tgm.waybill.domain.bo.VendorBo;
import com.juma.tgm.waybill.domain.vo.DistanceAndPriceParamVo;
import com.juma.tgm.waybill.service.BuildVehicleTypeAndVendorService;
import com.juma.tgm.waybill.service.DeliveryPointSupplementService;
import com.juma.tgm.waybill.service.TruckRequireService;
import com.juma.tgm.waybill.service.WaybillAutoFenceServicve;
import com.juma.tgm.waybill.service.WaybillCheckService;
import com.juma.tgm.waybill.service.WaybillCommonService;
import com.juma.tgm.waybill.service.WaybillDeliveryAddressService;
import com.juma.tgm.waybill.service.WaybillNoticeService;
import com.juma.tgm.waybill.service.WaybillParamService;
import com.juma.tgm.waybill.service.WaybillReceiveAddressCargoService;
import com.juma.tgm.waybill.service.WaybillReceiveAddressService;
import com.juma.tgm.waybill.service.WaybillService;
import com.juma.tgm.waybill.service.WaybillTrackService;
import com.juma.tgm.waybill.service.customize.xidi.XidiWaybillService;
import com.juma.vms.driver.enumeration.DriverTypeEnum;
import com.juma.vms.external.service.VmsService;
import com.juma.vms.vendor.domain.Vendor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: XidiWaybillServiceImpl
 * @Description:
 * @author: liang
 * @date: 2018-03-07 11:05
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
@Deprecated
@Service
public class XidiWaybillServiceImpl implements XidiWaybillService {

    private static Logger log = LoggerFactory.getLogger(XidiWaybillServiceImpl.class);

    @Autowired
    private DriverService driverService;
    @Autowired
    private TruckService truckService;
    @Resource
    private WaybillCommonService waybillCommonService;
    @Resource
    private WaybillService waybillService;
    @Resource
    private DispatchingTruckService dispatchingTruckService;
    @Resource
    private ScatteredMsgService scatteredMsgService;
    @Autowired
    private WaybillDeliveryAddressService waybillDeliveryAddressService;
    @Autowired
    private WaybillReceiveAddressService waybillReceiveAddressService;
    @Autowired
    private TruckRequireService truckRequireService;
    @Resource
    private WaybillParamService waybillParamService;
    @Resource
    private ScatteredWaybillService scatteredWaybillService;
    @Resource
    private WaybillNoticeService waybillNoticeService;
    @Resource
    private CustomerInfoService customerInfoService;
    @Resource
    private ServiceConfService serviceConfService;
    @Resource
    private BusinessAreaService businessAreaService;
    @Resource
    private AmsServiceV2 amsServiceV2;
    @Resource
    private WaybillAutoFenceServicve waybillAutoFenceServicve;
    @Autowired
    private TruckTypeService truckTypeService;
    @Resource
    private ReceiptManageService receiptManageService;
    @Resource
    private DeliveryPointSupplementService deliveryPointSupplementService;
    @Resource
    private EmployeeService employeeService;
    @Resource
    private BusinessAreaCommonService businessAreaCommonService;
    @Resource
    private WaybillReceiveAddressCargoService waybillReceiveAddressCargoService;
    @Resource
    private RoadMapPriceRuleService roadMapPriceRuleService;
    @Resource
    private WaybillTrackService waybillTrackService;
    @Resource
    private VmsService vmsService;
    
    @Resource
    private VmsCommonService vmsCommonService;
    
    @Resource
    private BuildVehicleTypeAndVendorService buildVehicleTypeAndVendorService;
    @Resource
    private WaybillCheckService waybillCheckService;

    @Override
    public void changeCar(int waybillId, int driverId, int truckId, int flightId, int receiveWay, String remark, LoginUser loginUser) throws BusinessException {
        if (StringUtils.isBlank(remark)) {
            throw new BusinessException("changeCarReasonRequired", "waybill.error.changeCarReasonRequired");
        }

        // 校验运单
        Waybill wb = waybillCommonService.getForUpdate(waybillId);
        if (null == wb) {
            throw new BusinessException("waybillNotfound", "waybill.error.notfound");
        }

        // 验证能不能更换车辆
        if (!this.allowChangeCar(wb)) {
            throw new BusinessException("noAllowToUpateCar", "errors.noAllowToUpateCar",
                    Waybill.ReceiveWay.MANUAL_ASSIGN.getDescr() + "或" + Waybill.ReceiveWay.AUTO_ASSIGN.getDescr()
                            + "】且在用车时间【" + Constants.TIME_LIMIT_ALLOW_UPDATE_CAR);
        }

        Driver driver = driverService.getDriver(driverId);
        Truck truck = truckService.getTruck(truckId);

        waybillCommonService.doCancelAssign(receiveWay, wb, driver, truck, loginUser);

        // 通知数据平台
        dispatchingTruckService.buildFeature(waybillId, Feature.Operate.Dispatcher_Cancel, loginUser);

        waybillCommonService.doAssignCarAgain(receiveWay, remark, wb, driver, truck, loginUser);

        // 推送消息
        scatteredMsgService.pushChangeCarMsgToDriver(wb, wb.getDriverId(), loginUser);

        // 通知数据平台
        dispatchingTruckService.buildFeature(waybillId, Feature.Operate.Dispatcher_Arrange, loginUser);

        // 更改被承运的运单信息
        this.updateVendorWaybill(wb, true, loginUser);
    }

    // 承运商运单同步更新被承运的运单
    private void updateVendorWaybill(Waybill waybill, boolean isChangeCar, LoginUser loginUser) {
        Waybill transformBill = waybillCommonService.findWaybillByTransformBillId(waybill.getWaybillId());
        if (null == transformBill) {
            return;
        }

        // 将loginUser里的租户信息更换为运单的租户信息
        loginUser.setTenantId(waybill.getTenantId());
        loginUser.setTenantCode(waybill.getTenantCode());

        transformBill.setAssignWaybillRemark(waybill.getAssignWaybillRemark());
        waybillCommonService.changeToAssignedUpdateDb(transformBill, loginUser);

        // 推送消息
        transformBill.setDriverId(waybill.getDriverId());
        transformBill.setPlateNumber(waybill.getPlateNumber());
        if (isChangeCar) {
            scatteredMsgService.pushChangeCarMsg(transformBill, loginUser);
        } else {
//            scatteredMsgService.pushAssignedWaybillMsg(waybill, loginUser);
        }
    }

    @Override
    public void cancelWaybill(Integer waybillId, Waybill.CancelChannel cancelChannel, String waybillCancelRemark, LoginEmployee loginEmployee) throws BusinessException {
        Waybill wb = waybillCommonService.getWaybillById(waybillId);
        if (wb == null) throw new BusinessException("waybillNotfound", "waybill.error.notfound");

        wb.setCancelChannel(cancelChannel.getCode());
        wb.setWaybillCancelRemark(waybillCancelRemark);

        if (NumberUtils.compare(Waybill.CancelChannel.BACKGROUND_IMPORT.getCode(), wb.getCancelChannel()) != 0) {
            BaseUtil.checkSelf(wb.getCustomerManagerId(), loginEmployee.getEmployeeId());
        }

        // 待配送的运单，取消逻辑修改为：用车时间前1小时都能取消，1小时内，取消按钮变为“联系客服”：后台运单取消不受此限制
        if (NumberUtils.compare(Waybill.StatusView.WATING_DELIVERY.getCode(), wb.getStatusView()) == 0
                && NumberUtils.compare(Waybill.CancelChannel.BACKGROUND_IMPORT.getCode(), wb.getCancelChannel()) != 0) {
            // 用车前一小时
            Date legalDate = DateUtils.addHours(wb.getPlanDeliveryTime(), -1);
            if (DateUtils.truncatedCompareTo(new Date(), legalDate, Calendar.MINUTE) == 1) {
                throw new BusinessException("validationFailure", "waybill.error.cancelTimeError");
            }
        }

        if (Waybill.StatusView.CANCEL.getCode() != wb.getStatusView()) {
            // 判断运单能不能被取消
            Integer statusView = wb.getStatusView();
            if (!allowCancelForBackPersonnel(statusView)) {
                throw new BusinessException("waybillNotCancel", "waybill.error.notCancel");
            }

            // 到仓时间为空，说明司机没有点击到仓且电子围栏没有触碰到仓，判断司机是否迟到
            if (null == wb.getArriveDepotTime()) {
                waybillCommonService.markLate(wb);
            }

            waybillCommonService.doCancelWaybill(wb, loginEmployee, Waybill.Status.SYS_CANCEL);

            if (Waybill.CancelChannel.BACKGROUND_IMPORT.getCode() == cancelChannel.getCode()) {
                //通知客户经理、用车人、司机
                scatteredMsgService.pushCancelWaybillMsg(wb, loginEmployee);
            }
            if (Waybill.CancelChannel.JUMA_CLIENT.getCode() == cancelChannel.getCode()) {
                //通知调度和司机
                scatteredMsgService.pushCancelWaybillToDriverAndDispatcher(wb, loginEmployee);
            }
        }

        // 通知数据平台
        dispatchingTruckService.removeBlacklistCache(wb.getWaybillId());
        dispatchingTruckService.buildFeature(wb.getWaybillId(), Feature.Operate.Dispatcher_Cancel, loginEmployee);

        // 取消承运商的承运运单
        this.cancelVendorWaybill(wb, loginEmployee);
    }

    // 取消承运商的承运运单
    private void cancelVendorWaybill(Waybill waybill, LoginUser loginUser) {
        // 获取承运商的运单
        WaybillParam waybillParam = waybillParamService.findByWaybillId(waybill.getWaybillId());
        if (null == waybillParam) {
            return;
        }

        Waybill vendorWaybill = waybillCommonService.getWaybillById(waybillParam.getTransformBillLinkId());
        if (null == vendorWaybill) {
            return;
        }

        loginUser.setTenantId(vendorWaybill.getTenantId());
        loginUser.setTenantCode(vendorWaybill.getTenantCode());

        vendorWaybill.setCancelChannel(waybill.getCancelChannel());
        vendorWaybill.setWaybillCancelRemark(waybill.getWaybillCancelRemark());
        waybillCommonService.doCancelWaybill(vendorWaybill, loginUser, Status.SYS_CANCEL);
        // 通知订单
        if (Waybill.CancelChannel.BACKGROUND_IMPORT.getCode() == waybill.getCancelChannel()) {
            scatteredMsgService.pushCancelWaybillMsg(vendorWaybill, loginUser);
        }
        if (Waybill.CancelChannel.JUMA_CLIENT.getCode() == waybill.getCancelChannel()) {
            scatteredMsgService.pushCancelWaybillToDriverAndDispatcher(vendorWaybill, loginUser);
        }
    }

    // 判断订单是否可以后台取消 statusView:运单状态
    // receivingtime:接单或被被指派时间 planDeliveryTime:计划配送时间 2.2.1需求要求后台取消去掉时间限制
    private boolean allowCancelForBackPersonnel(Integer statusView) {
        // 后台可以取消的状态：0、异常订单、1、派车中（1、-2）、2、待配送，3、配送中，4、待支付(需求2.1.3)
        if (null != statusView) {
            if (Waybill.StatusView.DEFAULT.getCode() == statusView
                    || Waybill.StatusView.WATING_PAY.getCode() == statusView
                    || Waybill.StatusView.WATING_DELIVERY.getCode() == statusView
                    || Waybill.StatusView.DELIVERYING.getCode() == statusView
                    || Waybill.StatusView.WATING_RECEIVE.getCode() == statusView
                    || Waybill.StatusView.TEMP.getCode() == statusView) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void updateArriveDepotTime(Waybill waybill, LoginEcoUser loginEcoUser) throws BusinessException {
        if (null == waybill) {
            throw new BusinessException("validationFailure", "errors.validation.failure");
        }
        Integer waybillId = waybill.getWaybillId();
        if (waybillId == null) {
            throw new BusinessException("validationFailure", "errors.validation.failure");
        }
        Waybill wb = waybillCommonService.getWaybillById(waybillId);
        if (null == wb) {
            throw new BusinessException("waybillNotfound", "waybill.error.notfound");
        }
        // 判断运单是否已经到仓:到仓时间不为空，则证明已经到仓
        if (null != wb.getArriveDepotTime()) {
            throw new BusinessException("driverHasArriveDepot", "waybill.error.driverHasArriveDepot");
        }

        this.checkOverPlanDeliveryTimeAndStatus(wb);
        this.checkDriver(wb.getDriverId(), loginEcoUser);

        wb.setArriveDepotTime(new Date());
        waybillCommonService.markLate(wb);

        waybillCommonService.update(wb, loginEcoUser);

        // 标记取货地已到达
        WaybillDeliveryAddress deliveryAddress = waybillDeliveryAddressService.findByWaybillId(wb.getWaybillId());
        if (null != deliveryAddress) {
            deliveryAddress.setIsArrived(WaybillDeliveryAddress.Arrive.YES.getCode());
            waybillDeliveryAddressService.update(deliveryAddress, loginEcoUser);
        }

        scatteredMsgService.pushArriveDepotMsg(wb, loginEcoUser);
    }

    /**
     * 是否已经到达计划用车时间
     *
     * @param waybill
     */
    private void checkOverPlanDeliveryTimeAndStatus(Waybill waybill) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(waybill.getPlanDeliveryTime());
        c1.set(Calendar.HOUR_OF_DAY, 0);
        c1.set(Calendar.MINUTE, 0);
        c1.set(Calendar.SECOND, 0);
        c1.set(Calendar.MILLISECOND, 0);

        Calendar c2 = Calendar.getInstance();
        c2.setTime(new Date());
        c2.set(Calendar.HOUR_OF_DAY, 0);
        c2.set(Calendar.MINUTE, 0);
        c2.set(Calendar.SECOND, 0);
        c2.set(Calendar.MILLISECOND, 0);
        if (c1.getTime().getTime() > c2.getTime().getTime()) {
            throw new BusinessException("waybill.error.todoAtPlanDeliveryTime", "waybill.error.todoAtPlanDeliveryTime");
        }

        Integer status = waybill.getStatus();
        if (!(Waybill.Status.ASSIGNED.getCode() == status || Waybill.Status.RECEIVED.getCode() == status
                || Waybill.Status.DELIVERYING.getCode() == status)) {
            throw new BusinessException("statusErr", "errors.statusErr");
        }
    }

    private void checkDriver(Integer driverId, LoginEcoUser loginEcoUser) {
        Driver driver = driverService.findDriverByUserId(loginEcoUser.getUserId());
        if (driver == null) {
            throw new BusinessException("driver.error.not.found", "driver.error.not.found");
        }
        BaseUtil.checkSelf(driver.getDriverId(), driverId);
    }


    @Override
    public void updateLeaveDepotTime(Waybill waybill, LoginEcoUser loginEcoUser) throws BusinessException {
//        Waybill wb = check(waybill, true);
        if (null == waybill) {
            throw new BusinessException("validationFailure", "errors.validation.failure");
        }

        Integer waybillId = waybill.getWaybillId();
        if (waybillId == null) {
            throw new BusinessException("validationFailure", "errors.validation.failure");
        }
        Waybill wb = waybillCommonService.getWaybillById(waybillId);

        if (null == wb) {
            throw new BusinessException("waybillNotfound", "waybill.error.notfound");
        }

        if (null != wb.getDeliveryTime()) {
            // 若司机已经离仓，但运单状态还是待配送，更改运单配送状态为配送中
            if (NumberUtils.compare(wb.getStatusView(), Waybill.StatusView.WATING_DELIVERY.getCode()) == 0
                    || NumberUtils.compare(wb.getStatus(), Waybill.Status.ASSIGNED.getCode()) == 0
                    || NumberUtils.compare(wb.getStatus(), Waybill.Status.RECEIVED.getCode()) == 0) {
                wb.setStatus(Waybill.Status.DELIVERYING.getCode());
                wb.setStatusView(Waybill.StatusView.DELIVERYING.getCode());
                waybillCommonService.update(wb, loginEcoUser);
                return;
            }
            throw new BusinessException("driverHasLeaveDepot", "waybill.error.driverHasLeaveDepot");
        }

        this.checkOverPlanDeliveryTimeAndStatus(wb);

        checkDriver(wb.getDriverId(), loginEcoUser);

        // 落地配运单重新算价(计算超时等待费)
        BigDecimal estimateFreight = wb.getEstimateFreight();

        wb.setDeliveryTime(new Date());
        // 将运单更改为配送中
        wb.setStatus(Waybill.Status.DELIVERYING.getCode());
        wb.setStatusView(Waybill.StatusView.DELIVERYING.getCode());

        this.calPriceAgain(wb, loginEcoUser);
        waybillCommonService.update(wb, loginEcoUser);

        // 更新司机状态为配送中
        driverService.updateDriverTaskStatus(wb.getDriverId(), Driver.TaskStatus.DISPATCHING);

        scatteredMsgService.pushLeaveDepotMsg(wb, estimateFreight, loginEcoUser);
        // 若需要经纪人填写，则通知经纪人
        if (waybillCheckService.checkProjectIsWorkload(waybillId)
                && !waybillCheckService.checkIsDriverWriteWork(wb.getWaybillId())) {
            scatteredMsgService.pushNoticeCustomerManageCompleteWork(waybillId, loginEcoUser);
        }
        // 通知数据平台
        dispatchingTruckService.buildFeature(waybill.getWaybillId(), Feature.Operate.Start_Delivery, loginEcoUser);
    }

    // 重新算价
    private void calPriceAgain(Waybill waybill, LoginUser loginUser) {

        DistanceAndPriceParamVo vo = new DistanceAndPriceParamVo();
        vo.setWaybill(waybill);
        vo.setTruckRequire(truckRequireService.findTruckRequireByWaybillId(waybill.getWaybillId(), loginUser));
        vo.setWaybillParam(waybillParamService.findByWaybillId(waybill.getWaybillId()));

        WaybillDeliveryAddress deliveryAddress = waybillDeliveryAddressService.findByWaybillId(waybill.getWaybillId());
        if (null != deliveryAddress) {
            CityAdressData srcAddress = new CityAdressData();
            srcAddress.setRegionCode(deliveryAddress.getRegionCode());
            srcAddress.setCoordinate(deliveryAddress.getCoordinates());
            srcAddress.setAddress(deliveryAddress.getAddressName());
            srcAddress.setAddressDetail(deliveryAddress.getAddressDetail());
            vo.setSrcAddress(srcAddress);
        }

        List<CityAdressData> toAddress = new ArrayList<CityAdressData>();
        List<WaybillReceiveAddress> list = waybillReceiveAddressService.findAllByWaybillId(waybill.getWaybillId());
        for (WaybillReceiveAddress receiveAddress : list) {
            CityAdressData address = new CityAdressData();
            address.setRegionCode(receiveAddress.getRegionCode());
            address.setCoordinate(receiveAddress.getCoordinates());
            address.setAddress(receiveAddress.getAddressName());
            address.setAddressDetail(receiveAddress.getAddressDetail());
            toAddress.add(address);
        }
        vo.setToAddress(toAddress);

        
        loginUser.setTenantId(waybill.getTenantId());//运单的租户
        BigDecimal computeFreight = scatteredWaybillService.computeFreight(vo, loginUser);
        log.info("司机离仓重新计算价格result: {}", computeFreight);
        computeFreight = computeFreight == null ? BigDecimal.ZERO : computeFreight;
        BigDecimal taxRateValue = vo.getTruckRequire().getTaxRateValue() == null ? BigDecimal.ZERO
                : vo.getTruckRequire().getTaxRateValue();
        waybill.setEstimateFreight(computeFreight);
        BigDecimal afterTaxFreight = computeFreight.divide(BigDecimal.ONE.add(taxRateValue),
                2, BigDecimal.ROUND_HALF_UP);
        waybill.setAfterTaxFreight(afterTaxFreight);
        waybill.setShow4DriverFreight(afterTaxFreight);
    }


    @Override
    public WaybillDetailInfo getWaybillInfo(Integer waybillId, LoginUser loginUser) throws BusinessException {
        WaybillDetailInfo result = buildDetailInfo(waybillCommonService.getWaybillById(waybillId), loginUser);
        result.setWaybillDeliveryAddresses(waybillDeliveryAddressService.findAllByWaybillId(waybillId));
        result.setWaybillReceiveAddresses(waybillReceiveAddressService.findAllByWaybillId(waybillId));
        buildCustomerInfo(result);
        return result;
    }


    // 构建用车要求等信息
    private WaybillDetailInfo buildDetailInfo(Waybill waybill, LoginUser loginUser) {
        WaybillDetailInfo result = new WaybillDetailInfo();
        if (null == waybill) {
            return result;
        }

        Integer waybillId = waybill.getWaybillId();
        result.setWaybill(waybill);
        result.setDriverTruckInfoBo(driverService.findDriverTruckInfoByWaybillId(waybillId));
        TruckRequire truckRequire = truckRequireService.findTruckRequireByWaybillId(waybillId, loginUser);
        if (null != truckRequire) {
            result.setTruckRequire(truckRequire);
            result.setColdChain(truckRequireService.isColdChain(truckRequire, null) ? 1 : 0);
            result.setGoodsInfoStr(truckRequireService.getGoodsFullName(truckRequire, null));
            result.setTruckRequireStr(truckRequireService.getTruckRequire(truckRequire, null));
        }
        WaybillNotice notice = waybillNoticeService.findBy(waybillId);
        if (null != notice) {
            result.setDriverNotice(notice.getNoticeDriverNum() * 3 + 5);
        }
        result.setWaybillParam(waybillParamService.findByWaybillId(waybillId));
        //专车抢单超时时间
//        result.setExpireTimeLength(customerManWaybillUtils.getExpireTimeLength());

        result.setCanUseCustomerInfo(customerInfoService.customerBelongToManager(waybill.getCustomerId(),
                waybill.getCustomerManagerId()));
        // 只有待配送、配送中的计算预估完成时间
        if (Waybill.StatusView.WATING_DELIVERY.getCode() == waybill.getStatusView()
                || Waybill.StatusView.DELIVERYING.getCode() == waybill.getStatusView()) {
            result.setEstimateFinishTime(
                    calculEstimateFinishTime(waybill.getPlanDeliveryTime(), waybill.getEstimateTimeConsumption()));
        }
        return result;
    }

    // 计算预估完成时间：预估完成时间 = 用车时间 + 预估耗时
    private String calculEstimateFinishTime(Date planDeliveryTime, Integer estimateTimeConsumption) {
        if (null != planDeliveryTime) {
            Date date = DateUtil.addMinutes(planDeliveryTime,
                    (estimateTimeConsumption == null ? 0 : estimateTimeConsumption));
            return DateUtil.format(date);
        }
        return null;
    }

    // 大客户信息
    private void buildCustomerInfo(WaybillDetailInfo info) {
        Waybill waybill = info.getWaybill();
        if (null != waybill) {
            Integer customerId = waybill.getCustomerId();
            if (null != customerId) {
                CustomerInfo customerInfo = customerInfoService.findCusInfoById(customerId);
                // 大客户信息修改为从crm端获取
                if (customerInfo != null) {
                    this.buildCrmCustomerInfo(customerInfo);
                    info.setCustomerId(customerId);
                    info.setCustomerInfo(customerInfo);
                }
            }
        }
    }

    // 从crm中获取客户信息
    private void buildCrmCustomerInfo(CustomerInfo customerInfo) {
        if (customerInfo.getCrmCustomerId() == null) {
            return;
        }
        com.juma.crm.customer.domain.CustomerInfo crmCustomerInfo = customerInfoService
                .getCrmCustomerInfoByTgmCustomerId(customerInfo.getCustomerId());
        if (crmCustomerInfo == null) {
            return;
        }
        customerInfo.setCustomerManagerUserId(crmCustomerInfo.getUserId());
        customerInfo.setEmployeeId(crmCustomerInfo.getUserId());
        customerInfo.setCustomerName(crmCustomerInfo.getCustomerName());
    }


    @Override
    public Page<WaybillBo> getPageForTodoWaybillList(PageCondition pageCondition, LoginUser loginUser) throws BusinessException {
        if (null == pageCondition.getOrderBy()) {
            pageCondition.setOrderBy("status_view asc,plan_delivery_time desc");
        }
        if (null == loginUser || null == loginUser.getTenantId()) {
            return new Page<WaybillBo>(pageCondition.getPageNo(), pageCondition.getPageSize(), 0, new ArrayList<WaybillBo>());
        }
        pageCondition.getFilters().put("tenantId", loginUser.getTenantId());
        return getWaybillList(pageCondition, loginUser);
    }

    private Page<WaybillBo> getWaybillList(PageCondition pageCondition, LoginUser loginUser) {
        int count = waybillCommonService.searchCount(pageCondition);
        List<Waybill> rows = waybillCommonService.search(pageCondition);
        List<WaybillBo> out = transfer(rows, loginUser);
        return new Page<WaybillBo>(pageCondition.getPageNo(), pageCondition.getPageSize(), count, out);
    }

    private List<WaybillBo> transfer(List<Waybill> rows, LoginUser loginUser) {
        List<WaybillBo> out = new ArrayList<WaybillBo>();
        for (Waybill row : rows) {
            WaybillBo bo = new WaybillBo();
            try {
                BeanUtils.copyProperties(bo, row);
            } catch (IllegalAccessException e) {
                log.warn(e.getMessage(), e);
            } catch (InvocationTargetException e) {
                log.warn(e.getMessage(), e);
            }
            bo.setCustomerServiceTel(serviceConfService.findCustomerServiceTel(row.getRegionCode(), loginUser));
            bo.setCoordinates(waybillCommonService.truckLocation(row.getPlateNumber()));
            bo.setEstimateFinishTime(buildEstimeFinishTime(row));
            bo.setPlanDeliveryDate(DateUtil.buildDateFormat(row.getPlanDeliveryTime()));
            bo.setImportantInfoStr(truckRequireService.getImportantInfo(row.getWaybillId()));
            bo.setDeliveryAddress(waybillDeliveryAddressService.findAllByWaybillId(row.getWaybillId()));
            bo.setReceiveAddress(waybillReceiveAddressService.findAllByWaybillId(row.getWaybillId()));
            bo.setWaybillParam(waybillParamService.findByWaybillId(row.getWaybillId()));
            TruckRequire truckRequire = truckRequireService.findTruckRequireByWaybillId(row.getWaybillId(), loginUser);
            bo.setTruckRequire(truckRequire);
            bo.setTruckRequireStr(truckRequireService.getTruckRequireString(truckRequire, new StringBuffer("")));
            bo.setGoodsInfoStr(truckRequireService.getGoodsFullName(truckRequire, null));
            loginUser.setTenantId(row.getTenantId());//跟随运单租户
            bo.setShowYourPrice(truckService.isShowYourPrice(row.getTruckId(), loginUser));
            out.add(bo);
        }
        return out;
    }

    private Date buildEstimeFinishTime(Waybill row) {
        Integer estimateTimeConsumption = row.getEstimateTimeConsumption() == null ? 0
                : row.getEstimateTimeConsumption();
        return new Date(row.getPlanDeliveryTime().getTime() + estimateTimeConsumption * 60 * 1000l);
    }
    @Override
    public Page<WaybillBo> getPageForAcceptableWaybillList(PageCondition pageCondition, LoginUser loginUser) throws BusinessException {
        pageCondition = buildAcceptableCondition(pageCondition, loginUser);
        pageCondition.setOrderBy("status_view asc,plan_delivery_time desc");
        int count = waybillCommonService.searchCount(pageCondition);
        List<Waybill> rows = waybillCommonService.search(pageCondition);
        List<WaybillBo> out = this.transfer(rows, loginUser);
        return new Page<WaybillBo>(pageCondition.getPageNo(), pageCondition.getPageSize(), count, out);
    }

    private void buildEmptyListCondition(PageCondition pageCondition, List<AppListFilter> appListFilter) {
        AppListFilter filter = new AppListFilter(1000, -999 + "");
        appListFilter.add(filter);
        pageCondition.getFilters().put("appListFilter", appListFilter);
    }
    
    /**
     * 构造运单池条件
     */
    private PageCondition buildAcceptableCondition(PageCondition pageCondition, LoginUser loginUser) {
        Map<String, Object> filters = pageCondition.getFilters();
        // filters.put("tenantId", loginUser.getTenantId());
        filters.put("status", Waybill.Status.WATING_RECEIVE.getCode());
        filters.put("receiveWay", Waybill.ReceiveWay.RECEIVED.getCode());
        filters.put("filterTimeout", true);
        // 只可见本业务区域的非分享运单和其他业务区域的分享运单，不可见本业务区域的分享运单
        filters.put("ownerAreaSahreCanNotSee", true);
        // 按组织区分

        // 单租户v1接口
        Driver driver = driverService.findDriverByUserId(loginUser.getUserId());
        if (filters.containsKey("tenantId") && filters.get("tenantId") != null) {
            List<String> areaCodeList = new ArrayList<String>();
            if (null == driver) {
                areaCodeList.add("-999");
                filters.put("areaCodeList", areaCodeList);
                return pageCondition;
            }
            com.juma.server.vm.domain.Driver amsDriver = amsServiceV2.getDriverByTenant(driver.getAmsDriverId(),
                    loginUser.getTenantId());
            if (null == amsDriver) {
                areaCodeList.add("-999");
                filters.put("areaCodeList", areaCodeList);
                return pageCondition;
            }

            log.info("amsDriver areacode :{}.", amsDriver.getAreaCode());
            BusinessArea businessArea = businessAreaService.loadLogicBusinessArea(amsDriver.getAreaCode(), loginUser);
            if (businessArea != null) {
                areaCodeList.add(businessArea.getAreaCode());
            }
            if(areaCodeList.isEmpty()) {
                areaCodeList.add("-999");
            }
            filters.put("areaCodeList", areaCodeList);
        } else {
            List<AppListFilter> appListFilter = new ArrayList<AppListFilter>();

            if (null == driver) {
                buildEmptyListCondition(pageCondition, appListFilter);
                return pageCondition;
            }
            if (null == driver.getAmsDriverId()) {
                buildEmptyListCondition(pageCondition, appListFilter);
                return pageCondition;
            }

            List<DriverTenantVO> driverTenantVOs = amsServiceV2.getTenantListByDriverId(driver.getAmsDriverId());
            log.info("amsDriver tenant :{}.", StringUtils.join(driverTenantVOs, ","));

            if (driverTenantVOs == null || driverTenantVOs.isEmpty()) {
                buildEmptyListCondition(pageCondition, appListFilter);
                return pageCondition;
            }

            for (DriverTenantVO driverTenantVO : driverTenantVOs) {
                AppListFilter filter = new AppListFilter();
                if (driverTenantVO.getTenantId() == null) {
                    log.info("driver {} no bind tenant.", driverTenantVO.getDriverId());
                    continue;
                }
                filter.setTenantId(driverTenantVO.getTenantId());

                LoginUser u = new LoginUser();
                u.setUserId(loginUser.getUserId());
                u.setTenantId(driverTenantVO.getTenantId());
                BusinessArea businessArea = businessAreaService.loadLogicBusinessArea(driverTenantVO.getAreaCode(), u);
                if (businessArea == null) {
                    log.info("areacode {} no set business area.", driverTenantVO.getAreaCode());
                    continue;
                }
                if (businessArea != null) {
                    filter.setAreaCode(businessArea.getAreaCode());
                }

                appListFilter.add(filter);
            }

            if (appListFilter.isEmpty()) {
                buildEmptyListCondition(pageCondition, appListFilter);
                return pageCondition;
            }
            pageCondition.getFilters().put("appListFilter", appListFilter);
        }

        Vehicle vehicle = amsServiceV2.getVehicleByDriverId(driver.getAmsDriverId());
        // 按入城证区分
        if (vehicle != null) {
            Integer entryLicense = 0;
            if (null != vehicle.getGoCityLicenseType()) {
                entryLicense = vehicle.getGoCityLicenseType().intValue();
            }

            if (entryLicense != 1) {
                // 车辆非A1入城证
                // 那就只能看不要入城证的单子
                // 因为用车的时候选入城证是是否的关系
                filters.put("entryLicense", 0);
            }
            filters.put("vehicleBoxType", vehicle.getVehicleBoxType());

            // 货物重量
            filters.put("goodsWeight", vehicle.getMaxLoadCapacity() == null ? -1 : vehicle.getMaxLoadCapacity());

            // 货物体积
            // pageCondition.put("goodsVolume", vehicle.getLoadVolume());
        }

        return pageCondition;
    }

    @Override
    public boolean allowChangeCar(Waybill waybill) throws BusinessException {
        if (null == waybill) {
            return false;
        }

        if (null == waybill.getBusinessBranch()) {
            return false;
        }

        if (!(Waybill.Status.ASSIGNED.getCode() == waybill.getStatus()
                || Waybill.Status.WAITINT_DRIVER_ANSWER.getCode() == waybill.getStatus())) {
            return false;
        }

        // 落地配的运单，处于待配送状态(-2,2)且到仓之前的都可以改派
        if ((NumberUtils.compare(Waybill.StatusView.WATING_DELIVERY.getCode(), waybill.getStatusView()) == 0
                || NumberUtils.compare(Waybill.StatusView.TEMP.getCode(), waybill.getStatusView()) == 0)
                && waybill.getArriveDepotTime() == null) {
            return true;
        }
        return true;
    }

    @Override
    public Page<Waybill> search(PageCondition pageCondition, LoginUser loginUser) throws BusinessException {
        if (null == loginUser || null == loginUser.getTenantId()) {
            return new Page<Waybill>(pageCondition.getPageNo(), pageCondition.getPageSize(), 0,
                    new ArrayList<Waybill>());
        }

        pageCondition.getFilters().put("tenantId", loginUser.getTenantId());
        pageCondition.getFilters().put("backstage", true);
        waybillCommonService.structPageCondition(pageCondition, loginUser);

        List<Waybill> result = new ArrayList<Waybill>();
        int count = waybillCommonService.searchCount(pageCondition);
        List<Waybill> rows = waybillCommonService.search(pageCondition);
        for (Waybill waybill : rows) {
            waybill.setAllowCancel(allowCancelForBackPersonnel(waybill.getStatusView()));
            waybill.setAllowChangeCar(allowChangeCar(waybill));

            WaybillParam waybillParam = waybillParamService.findByWaybillId(waybill.getWaybillId());
            if (null != waybillParam) {
                waybill.setDriverHasRead(waybillParam.getDriverRead() == null ? false
                        : (waybillParam.getDriverRead() == 1 ? true : false));

                // 获取承运商的运单
                Waybill vendorWaybill = waybillCommonService.getWaybillById(waybillParam.getTransformBillLinkId());
                if (null != vendorWaybill) {
                    waybill.setDriverName(vendorWaybill.getDriverName());
                    waybill.setPlateNumber(vendorWaybill.getPlateNumber());
                }
            }
            result.add(waybill);
        }

        return new Page<Waybill>(pageCondition.getPageNo(), pageCondition.getPageSize(), count, result);
    }

    @Override
    public void changeToAssigned(int waybillId, int driverId, int truckId, int flightId, int receiveWay, String remark, LoginUser loginUser) throws BusinessException {
        // 校验运单
        Waybill wb = waybillCommonService.getForUpdate(waybillId);

        if (null == wb) {
            throw new BusinessException("waybillNotfound", "waybill.error.notfound");
        }
        if (null != wb.getDriverId()) {
            throw new BusinessException("waybillHasAssigned", "waybill.error.waybillHasAssigned", wb.getWaybillNo());
        }
        log.info("wb_id:{0};driver_id:{1}", wb.getWaybillId() + "", wb.getDriverId() + "");

        Driver driver = driverService.getDriver(driverId);
        // 校验司机
        waybillCommonService.checkDriverCanbeAssign(driver, wb.getStatus(), wb.getReceiveWay());

        // 校验车辆
        Truck truck = truckService.getTruckAndCheckEixst(truckId);

        // 校验派车方式
        if (StringUtils.isBlank(Waybill.ReceiveWay.buildReceiveWayStr(receiveWay))) {
            throw new BusinessException("receiveWayErr", "waybill.error.receiveWayErr");
        }

//        Integer flightUsageId = findFlight(waybillId, flightId, loginUser, wb, truck);

        // 组装参数
        Waybill waybill = wb;
        waybill.setWaybillId(waybillId);
        waybill.setDriverId(driverId);
        waybill.setDriverName(driver.getNickname());
        waybill.setDriverType(vmsCommonService.findDriverRunType(driver.getDriverId()));
        waybill.setTruckId(truckId);
        waybill.setPlateNumber(truck.getPlateNumber());
        waybill.setReceiveWay(receiveWay);
        waybill.setFlightUsageId(null);
        waybill.setAssignWaybillRemark(remark);
        waybill = buildVehicleTypeAndVendorService.checkAndBuildVehicleTypeAndVendor(waybill, truck.getVehicleId(),
                waybill.getDriverType(), BuildVehicleTypeAndVendorService.CHANGE_TO_ASSIGN, loginUser);

        // 派车
        waybillCommonService.changeToAssignedUpdateDb(waybill, loginUser);

        // 清除自动派车的预计完成时间
        truckService.removeEstimateFinishTime(truckId);

        // 推送消息
        scatteredMsgService.pushAssignedWaybillToDriverAppMsg(waybill, loginUser);
//        scatteredMsgService.pushAssignedWaybillMsg(waybill, loginUser);

        // 绑定电子围栏
        waybillAutoFenceServicve.bindWaybillIdAndFenceId(waybillId, WaybillBindFence.Sign.DELIVERY_ADDRESS,
                loginUser);

        // 通知数据平台
        dispatchingTruckService.buildFeature(waybillId, Feature.Operate.Dispatcher_Arrange, loginUser);

        // 更改被承运的运单信息
        this.updateVendorWaybill(waybill, false, loginUser);
    }

    @Override
    public WaybillDetailInfo findWaybillDetailById(Integer waybillId, LoginUser loginUser) throws BusinessException {
        WaybillDetailInfo info = new WaybillDetailInfo();

        Waybill waybill = waybillService.getWaybill(waybillId);
        if (null == waybill) {
            return info;
        }

        // 若是转运单，则获取承运商运单的司机车辆信息
        this.buildFromVendorWaybill(waybill);

        if (null != waybill.getDriverType()) {
            waybill.setDriverTypeName(DriverTypeEnum.getDescByCode(waybill.getDriverType()));
        }

        if (null != waybill.getNeedReceipt()) {
            waybill.setNeedReceiptText(Waybill.NeedReceipt.buildNeedReceiptStr(waybill.getNeedReceipt()));
        }

        waybill.setAllowCancel(allowCancelForBackPersonnel(waybill.getStatusView()));
        if (Waybill.Status.ASSIGNED.getCode() == waybill.getStatus()
                || Waybill.Status.WAITINT_DRIVER_ANSWER.getCode() == waybill.getStatus()) {
            waybill.setAllowChangeCar(allowChangeCar(waybill));
        }
        // 业务区域
        waybill.setAreaName(businessAreaCommonService.loadPreAndSelfAreaName(waybill.getAreaCode(), loginUser));

        info.setWaybillParam(buildWaybillParam(waybillId));
        // 用车要求
        TruckRequire truckRequire = truckRequireService.findTruckRequireByWaybillId(waybill.getWaybillId(), loginUser);
        if (null != truckRequire) {
            truckRequire.setTruckRequireStr(truckRequireService.getTruckRequire(truckRequire, null));
            info.setTruckTypeName(truckTypeService.findTruckTypeNameByTypeId(truckRequire.getTruckTypeId()));

            if (NumberUtils.compare(Waybill.BusinessBranch.BRANCH_SCATTERED.getCode(), waybill.getBusinessBranch()) == 0
                    && null != waybill.getVehicleBoxType()) {
                String vehicleBoxTypeName = truckTypeService.findVehicleBoxTypeName(waybill.getVehicleBoxType());
                if (StringUtils.isNotBlank(vehicleBoxTypeName)) {
                    truckRequire.setTruckRequireStr(StringUtils.isBlank(truckRequire.getTruckRequireStr())
                            ? vehicleBoxTypeName : vehicleBoxTypeName + " | " + truckRequire.getTruckRequireStr());
                }
            }
        }
        info.setTruckRequire(truckRequire);

        // 回单图片
        info.setReceiptImageList(receiptManageService.listReceiptImageByWaybillId(waybillId));
        //货物图片
        info.setGoodsImageList(receiptManageService.listGoodsImageByWaybillId(waybillId));
        // 取货地
        info.setWaybillDeliveryAddresses(waybillDeliveryAddressService.findAllByWaybillId(waybillId));
        // 目的地
        List<WaybillReceiveAddress> receiveAddressList = waybillReceiveAddressService.findAllByWaybillId(waybillId);
        info.setWaybillReceiveAddresses(receiveAddressList);
        // 目的地的货物信息
        List<WaybillReceiveAddressResponse> receiveAddressResponseList = new ArrayList<WaybillReceiveAddressResponse>();
        for (WaybillReceiveAddress receiveAddress : receiveAddressList) {
            List<WaybillReceiveAddressCargo> listCargo = waybillReceiveAddressCargoService
                    .listByAddressId(receiveAddress.getAddressId());
            receiveAddressResponseList.add(new WaybillReceiveAddressResponse(receiveAddress, listCargo));
        }
        info.setWaybillReceiveAddressResponseList(receiveAddressResponseList);
        // 配送单
        if (NumberUtils.compare(Waybill.ReceiveWay.TRANSFORM_BILL.getCode(), waybill.getReceiveWay()) == 0) {
            info.setDeliveryPointSupplementList(
                deliveryPointSupplementService.getByWayBill(info.getWaybillParam().getTransformBillLinkId()));
        } else {
            info.setDeliveryPointSupplementList(deliveryPointSupplementService.getByWayBill(waybillId));
        }
        info.setDriver(driverService.getDriver(waybill.getDriverId()));
        Truck truck = truckService.getTruck(waybill.getTruckId());
        if (null != truck) {
            truck.setTruckInfoStr(truckService.findTruckInfoStrByTruckId(truck.getTruckId(), loginUser));
            com.juma.server.vm.domain.Driver amsDriver = amsServiceV2.getByBindVehicleId(truck.getVehicleId());
            if (null != amsDriver) {
                truck.setParkingAddress(amsDriver.getAddress());
            }
            info.setTruck(truck);
        }

        // 客户经理
        if (null != waybill.getCustomerManagerId()) {
            User user = employeeService.loadUserByEmployeeId(waybill.getCustomerManagerId(), loginUser);
            if (null != user) {
                waybill.setCustomerManagerMobile(user.getMobileNumber());
            }
        }
        // 当前行驶距离
        try {
            waybillTrackService.getWaybillTraceInfo(waybill);
        } catch (Exception e) {
            log.warn("获取实际运输距离错误.", e);
        }

        info.setWaybill(waybill);
        // 获取承运商信息
        Vendor vendor = findVendorInfo(waybill.getVendorId(),loginUser);
        if(vendor != null){
            VendorBo vendorBo = new VendorBo();
            org.springframework.beans.BeanUtils.copyProperties(vendor,vendorBo);
            info.setVendorBo(vendorBo);
        }
        return info;
    }

    // 承运商运单信息
    private void buildFromVendorWaybill(Waybill waybill) {
        WaybillParam waybillParam = waybillParamService.findByWaybillId(waybill.getWaybillId());
        if (null == waybillParam || null == waybillParam.getTransformBillLinkId()) {
            return;
        }

        Waybill vendorWaybill = waybillCommonService.getWaybillById(waybillParam.getTransformBillLinkId());
        if (null == vendorWaybill) {
            return;
        }

        waybill.setDriverName(vendorWaybill.getDriverName());
        waybill.setDriverType(vendorWaybill.getDriverType());
        waybill.setDriverId(vendorWaybill.getDriverId());
        waybill.setTruckId(vendorWaybill.getTruckId());
        waybill.setPlateNumber(vendorWaybill.getPlateNumber());
    }

    // 获取承运商信息
    private Vendor findVendorInfo(Integer vendorId, LoginUser loginUser) {
        if (null == vendorId) {
            return null;
        }
        return vmsService.loadByVenorIdTenant(vendorId, loginUser.getTenantId());
    }

    // 获取添加的搬运费 ;配送点数 如果司机端没有指定就取后台的数据
    private WaybillParam buildWaybillParam(Integer waybillId) {
        WaybillParam waybillParam = waybillParamService.findByWaybillId(waybillId);
        if (null == waybillParam) {
            waybillParam = new WaybillParam();
            List<WaybillReceiveAddress> list = waybillReceiveAddressService.findAllByWaybillId(waybillId);
            waybillParam.setDistributionPointNo(list.size());
        }

        Integer distributionPointNo = waybillParam.getDistributionPointNo();
        if (null == distributionPointNo) {
            List<WaybillReceiveAddress> list = waybillReceiveAddressService.findAllByWaybillId(waybillId);
            waybillParam.setDistributionPointNo(list.size());
        }

        // 计价详情
        if (StringUtils.isNotBlank(waybillParam.getValuationConstJson())) {
            List<ValuationWay> valuationWays = roadMapPriceRuleService
                .buildValuationWays(waybillParam.getValuationConstJson());
            waybillParam.setValuationWays(valuationWays);
        }

        return waybillParam;
    }

    /**
     * 临时添加
     *
     * @param waybill
     * @param loginUser
     * @throws BusinessException
     */
    //FIXME 临时添加 删除
    @Override
    public void changeToDeliveried(Waybill waybill, LoginUser loginUser) throws BusinessException {
        scatteredWaybillService.changeToDeliveried(waybill, loginUser);
    }
}
