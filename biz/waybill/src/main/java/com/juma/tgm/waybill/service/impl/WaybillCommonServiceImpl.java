package com.juma.tgm.waybill.service.impl;

import com.alibaba.fastjson.JSON;
import com.giants.common.collections.CollectionUtils;
import com.giants.common.exception.BusinessException;
import com.giants.common.tools.PageCondition;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.juma.auth.conf.domain.BusinessArea;
import com.juma.auth.conf.service.BusinessAreaService;
import com.juma.auth.employee.domain.Employee;
import com.juma.auth.employee.domain.EmployeeInfo;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.employee.service.EmployeeService;
import com.juma.auth.user.domain.LoginUser;
import com.juma.auth.user.domain.User;
import com.juma.auth.user.domain.User.UserUniqueAttribute;
import com.juma.auth.user.service.UserService;
import com.juma.conf.domain.ConfParamOption;
import com.juma.conf.domain.Region;
import com.juma.monitor.service.RealTimePositionService;
import com.juma.monitor.truck.domain.RealTimePosition;
import com.juma.server.vm.service.vehicle.AmsServiceV2;
import com.juma.tgm.common.BaseUtil;
import com.juma.tgm.common.Constants;
import com.juma.tgm.common.DateUtil;
import com.juma.tgm.crm.domain.CustomerInfo;
import com.juma.tgm.crm.service.CustomerInfoService;
import com.juma.tgm.driver.domain.Driver;
import com.juma.tgm.driver.service.DriverService;
import com.juma.tgm.fms.domain.v3.vo.WaybillStatisticsAmountVO;
import com.juma.tgm.gaode.domain.AmapRegeoResponse;
import com.juma.tgm.landingWaybill.domain.AtFenceResultVo;
import com.juma.tgm.landingWaybill.domain.AtFenceResultVo.ForbiddenType;
import com.juma.tgm.project.common.CustomerForProductAndDept;
import com.juma.tgm.project.common.LogisticsProduct;
import com.juma.tgm.project.domain.RoadMap;
import com.juma.tgm.project.domain.v2.Project;
import com.juma.tgm.project.service.ProjectService;
import com.juma.tgm.project.service.RoadMapService;
import com.juma.tgm.region.service.RegionTgmService;
import com.juma.tgm.scatteredWaybill.service.ScatteredWaybillService;
import com.juma.tgm.tools.service.AuthCommonService;
import com.juma.tgm.tools.service.CrmCommonService;
import com.juma.tgm.tools.service.VmsCommonService;
import com.juma.tgm.truck.domain.Truck;
import com.juma.tgm.truck.service.TruckService;
import com.juma.tgm.util.CustomerManWaybillUtils;
import com.juma.tgm.waybill.dao.WaybillDao;
import com.juma.tgm.waybill.dao.WaybillExtDao;
import com.juma.tgm.waybill.domain.*;
import com.juma.tgm.waybill.domain.example.WaybillExample;
import com.juma.tgm.waybill.domain.example.WaybillExample.Criteria;
import com.juma.tgm.waybill.domain.map.WaybillAddress;
import com.juma.tgm.waybill.domain.map.WaybillMapTracePoint;
import com.juma.tgm.waybill.domain.vo.DistanceAndPriceParamVo;
import com.juma.tgm.waybill.domain.vo.WaybillAmountFilter;
import com.juma.tgm.waybill.enumeration.WaybillOperateTrackEnum.OperateApplication;
import com.juma.tgm.waybill.enumeration.WaybillOperateTrackEnum.OperateType;
import com.juma.tgm.waybill.service.*;
import com.juma.tgm.waybill.vo.ConfirmWaybillAmountVO;
import com.juma.tgm.waybill.vo.WaybillFilter;
import com.juma.vms.driver.enumeration.DriverTypeEnum;
import com.juma.vms.external.service.VmsService;
import com.juma.vms.transport.domain.CapacityPool;
import com.juma.vms.vendor.domain.Vendor;
import com.juma.vms.vendor.enumeration.VendorSourceEnum;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 运单通用类，尽量不要直接暴露给controller
 *
 * @ClassName: WaybillCommonServiceImpl
 * @Description:
 * @author: liang
 * @date: 2017-11-13 11:14
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
@Service
public class WaybillCommonServiceImpl implements WaybillCommonService {

    private static final Logger log = LoggerFactory.getLogger(WaybillCommonServiceImpl.class);

    @Resource
    private WaybillDao waybillDao;
    @Resource
    private WaybillExtDao waybillExtDao;

    @Resource
    private UserService userService;

    @Resource
    private AuthCommonService authCommonService;

    @Resource
    private TruckService truckService;

    @Resource
    private DriverService driverService;

    @Resource
    private TruckRequireService truckRequireService;

    @Resource
    private WaybillDeliveryAddressService waybillDeliveryAddressService;
    @Resource
    private WaybillReceiveAddressService waybillReceiveAddressService;
    @Resource
    private RegionTgmService regionTgmService;
    @Resource
    private GaoDeMapService gaoDeMapService;
    @Resource
    private CustomerInfoService customerInfoService;
    @Resource
    private EmployeeService employeeService;
    @Resource
    private AddressHistoryService addressHistoryService;
    @Resource
    private WaybillParamService waybillParamService;
    @Value("${dataBase.name}")
    private String dataBaseName;
    @Resource
    private RealTimePositionService realTimePositionService;
    @Resource
    private WaybillAutoFenceServicve waybillAutoFenceServicve;

    @Resource
    private WaybillOperateTrackService waybillOperateTrackService;

    /**
     * 用于处理客户经理订单超时通知
     */
    @Resource
    private CustomerManWaybillUtils customerManWaybillUtils;

    @Resource
    private BusinessAreaService businessAreaService;

    @Resource
    private ScatteredWaybillService scatteredWaybillService;

    @Resource
    private WaybillService waybillService;

    @Resource
    private ProjectService projectService;

    @Resource
    private VmsCommonService vmsCommonService;

    @Resource
    private BuildVehicleTypeAndVendorService buildVehicleTypeAndVendorService;

    @Resource
    private CrmCommonService crmCommonService;
    @Resource
    private RoadMapService roadMapService;

    @Resource
    private VmsService vmsService;
    @Resource
    private AmsServiceV2 amsServiceV2;
    @Resource
    private WaybillAmountService waybillAmountService;

    @Override
    public List<Waybill> search(PageCondition pageCondition) throws BusinessException {
        return waybillDao.search(pageCondition);
    }

    @Override
    public Waybill getWaybillById(Integer waybillId) throws BusinessException {
        if (null == waybillId) {
            return null;
        }
        Waybill waybill = waybillDao.get(waybillId);
        return waybill;
    }

    @Override
    public Waybill getForUpdate(int waybillId) throws BusinessException {
        return waybillDao.getForUpdate(waybillId);
    }

    @Override
    public int searchCount(PageCondition pageCondition) throws BusinessException {
        int count = waybillDao.searchCount(pageCondition);
        return count;
    }

    @Override
    public void insert(Waybill waybill, LoginUser loginUser) {
        if (waybill == null) return;

        if (null == waybill.getWaybillSource()) {
            throw new BusinessException("waybillSourceNotKnow", "waybill.error.waybillSourceNotKnow");
        }

        // 转运单，不需要标签，设置默认标签
        if (NumberUtils.compare(waybill.getReceiveWay(), Waybill.ReceiveWay.TRANSFORM_BILL.getCode()) == 0
                && !crmCommonService.isShowLogisticsProduct(loginUser)) {
            waybill.setLogisticsLabel(LogisticsProduct.NO_LOGISTICS_LABEL);
        }

        // 司机
        if (null != waybill.getDriverId()) {
            Driver driver = driverService.getDriver(waybill.getDriverId());
            if (null != driver) {
                waybill.setDriverName(driver.getNickname());
            }
        }

        waybill.setIsChangeDeliveryPoint(0);

        waybill.setCalculatedFreight(
                waybill.getCalculatedFreight() == null ? BigDecimal.ZERO : waybill.getCalculatedFreight());

        waybill.setCreateUserId(loginUser.getUserId());
        waybill.setBusinessBranch(waybill.getBusinessBranch() == null ? Waybill.BusinessBranch.BRANCH_FULL.getCode()
                : waybill.getBusinessBranch());
        waybill.setEstimateTimeConsumption(
                waybill.getEstimateTimeConsumption() == null ? 0 : waybill.getEstimateTimeConsumption());
        waybill.setEstimateDistance(waybill.getEstimateDistance() == null ? 0 : waybill.getEstimateDistance());

        // 是否上传配送单，若是则默认未上传
        if (null != waybill.getNeedDeliveryPointNote()
                && NumberUtils.compare(waybill.getNeedDeliveryPointNote(), 1) == 0) {
            waybill.setIsChangeDeliveryPoint(Waybill.ChangeDeliveryPoint.NOT_UPLOAD.getCode());
        }

        // 客户名称
        CustomerInfo customerInfo = customerInfoService.findCusInfoById(waybill.getCustomerId());
        if (null != customerInfo) {
            waybill.setCustomerName(customerInfo.getCustomerName());
        }

        // 客户经理
        try {
            if (null != waybill.getCustomerManagerId()) {
                User user = employeeService.loadUserByEmployeeId(waybill.getCustomerManagerId(), loginUser);
                waybill.setCustomerManagerName(user.getName());
                // 非项目运单把客户经理user_id赋值给客户 方便经济人端查询（项目经理、运营专员）
                if (null != waybill.getProjectId()) {
                    waybill.setProjectManagerUserId(user.getUserId());
                }
            }
        } catch (Exception e) {
        }

        // 项目id为空时，项目名称置为空
        if (null == waybill.getProjectId()) {
            waybill.setProjectName(null);
            if (null != customerInfo) {
                CustomerForProductAndDept customerForProductAndDept = crmCommonService
                    .loadCustomerForProductAndDeptByCrmCustomerId(customerInfo.getCrmCustomerId(), loginUser);
                waybill.setDepartmentId(customerForProductAndDept.getDeparmentId());
                // 非项目下单运单中同时记录运行主体和签约主体，设置为同一个
                waybill.setPayToCompany(customerForProductAndDept.getDeparmentId());
                waybill.setContractToCompanyCreditCode(customerForProductAndDept.getCompanyCreditCode());
                // 非项目下单运单中同时记录运行主体和签约主体，设置为同一个
                waybill.setPayToCompanyCreditCode(
                    waybill.getContractToCompanyCreditCode() == null ? "" : waybill.getContractToCompanyCreditCode());
                // 非项目下单需要把客户经理写到项目经理字段
                User user = authCommonService.loadUserByEmployeeId(waybill.getCustomerManagerId(), loginUser);
                waybill.setProjectManagerUserId(user == null ? null : user.getUserId());
            }
        } else {
            // 签约主体与运营主体
            Project project = projectService.getProjectV2(waybill.getProjectId());
            if (project != null) {
                waybill.setDepartmentId(project.getContractToCompany());
                waybill.setPayToCompany(project.getPayToCompany());
                waybill.setContractToCompanyCreditCode(
                    project.getContractToCompanyCreditCode() == null ? "" : project.getContractToCompanyCreditCode());
                waybill.setPayToCompanyCreditCode(
                    project.getPayToCompanyCreditCode() == null ? "" : project.getPayToCompanyCreditCode());
                waybill.setProjectManagerUserId(project.getProjectManagerUserId());

                // 查询路线是不是属于本项目
                RoadMap roadMap = roadMapService.getRoadMap(waybill.getRoadMapId());
                if (waybill.getWaybillSource() != Waybill.WaybillSource.TRANSFORM_BILL.getCode() && null != roadMap
                    && !waybill.getProjectId().equals(roadMap.getProjectId())) {
                    throw new BusinessException("roadMapIdNotBelongToProject",
                        "waybill.error.roadMapIdNotBelongToProject");
                }
            }

            if (StringUtils.isBlank(waybill.getPayToCompanyCreditCode())
                || StringUtils.isBlank(waybill.getContractToCompanyCreditCode())) {
                throw new BusinessException("contractToCompanyCreditCodeNull", "errors.common.prompt",
                    "未获取到社会统一信用代码，请联系总部财务核实该问题");
            }
        }

        // 运单来源是转运单，则为乙方单
        if (waybill.getWaybillSource() == Waybill.WaybillSource.TRANSFORM_BILL.getCode()) {
            /*
             * if((waybill.getTenantId() != 4 || waybill.getTenantId() != 20) && waybill.getProjectId() == null){ throw new BusinessException("notAllowCreateWaybill",
             * "errors.common.prompt", "非越好租户不能下非项目单"); }
             */
            waybill.setCreateUserId(waybill.getProjectManagerUserId());
        }

        if (waybill.getTenantId() != 4 && waybill.getTenantId() != 20 && waybill.getProjectId() == null) {
            throw new BusinessException("notAllowCreateWaybill", "errors.common.prompt", "非越好租户不能下非项目单");
        }

        if (waybill.getCreateUserId() != null) {
            User user = userService.loadUser(waybill.getCreateUserId());
            waybill.setCreateUserName(user == null ? "" : user.getName());
        }
        if (waybill.getProjectManagerUserId() != null) {
            User user = userService.loadUser(waybill.getProjectManagerUserId());
            waybill.setProjectManagerUserName(user == null ? "" : user.getName());
        }
        waybillDao.insert(waybill);
    }

    // 关联表
    @Override
    public void insertLinkTable(WaybillBo waybillBo, LoginUser loginUser) {
        Integer waybillId = waybillBo.getWaybill().getWaybillId();
        List<WaybillDeliveryAddress> deliveryAddress = waybillBo.getDeliveryAddress();

        List<WaybillReceiveAddress> receiveAddress = waybillBo.getReceiveAddress();
        waybillDeliveryAddressService.insert(deliveryAddress, waybillId, loginUser);
        if (CollectionUtils.isNotEmpty(waybillBo.getListReceiveAddressResponse())) {
            waybillReceiveAddressService.insert(waybillBo.getListReceiveAddressResponse(), waybillId, loginUser);
        } else if (CollectionUtils.isNotEmpty(receiveAddress)) {
            List<WaybillReceiveAddressResponse> listAddressResponse = new ArrayList<WaybillReceiveAddressResponse>();
            for (WaybillReceiveAddress address : receiveAddress) {
                listAddressResponse.add(new WaybillReceiveAddressResponse(address, null));
            }
            waybillReceiveAddressService.insert(listAddressResponse, waybillId, loginUser);
        }
        // 用车要求
        TruckRequire truckRequire = waybillBo.getTruckRequire();
        if (truckRequire != null) {
            truckRequire.setWaybillId(waybillId);
            truckRequireService.insert(truckRequire);
        }

        // 地址历史 联系人历史
        addressHistoryService.addOrUpdateressHistory(deliveryAddress, receiveAddress, loginUser);
        // 运单运费信息完善
        doCompleteWaybillAmount(waybillId, waybillBo.getWaybill().getEstimateFreight(),
            waybillBo.getWaybill().getShow4DriverFreight(), loginUser);

    }

    /**
     * 保存搬运费
     *
     * @param waybillParam
     * @param loginUser
     * @return
     */
    @Override
    public void saveWaybillParam(WaybillParam waybillParam, Waybill waybill, LoginUser loginUser) {
        // 改为默认都添加
        // if (waybillParam.getDriverHandlingCost() == null &&
        // waybillParam.getLaborerHandlingCost() == null) return;
        // 添加搬运费
        waybillParam.setWaybillId(waybill.getWaybillId());
        waybillParam.setDriverRead(0);
        waybillParamService.addOrUpdateOnly(waybillParam, loginUser);
    }

    /**
     * 生成运单编号，默认每天10W单
     *
     * @return
     */
    @Override
    public String getWaybillNo() {
        StringBuilder wayNo = new StringBuilder();
        wayNo.append(DateUtil.format(DateUtil.YYYYMMDD_SIMPLE));
        Integer nextSeq = waybillDao.getNextSequence(dataBaseName);
        Integer random = ThreadLocalRandom.current().nextInt(10000, 99999);
        wayNo.append(random);
        DecimalFormat format = new DecimalFormat("000000");
        wayNo.append(format.format(nextSeq % 100000));
        return wayNo.toString();
    }

    /**
     * 重新计算费用
     *
     * @param src 取货地 不能为空
     * @param dest 配送地 不能为空
     * @return
     */
    @Override
    public DistanceAndPriceData buildDistanceAndPriceData(WaybillDeliveryAddress src, List<WaybillReceiveAddress> dest)
            throws BusinessException {
        // 构造取货地
        CityAdressData startCityData = this.transformSrcAddr(src);

        // 构造收货地
        List<CityAdressData> terminateCityList = this.transformDestAddr(dest);

        return this.buildAppCalPriceInfo(null, startCityData, terminateCityList);
    }

    /**
     * 构造配送地
     *
     * @param dest
     * @return
     */
    private List<CityAdressData> transformDestAddr(List<WaybillReceiveAddress> dest) {
        List<CityAdressData> terminateCityList = new ArrayList<>();

        CityAdressData terminateCityData = null;
        for (WaybillReceiveAddress address : dest) {
            terminateCityData = new CityAdressData();

            terminateCityData.setRegionCode(regionTgmService.findRegionCodeByCoordinate(address.getCoordinates()));
            terminateCityData.setAddress(address.getAddressDetail());
            terminateCityData.setCity(regionTgmService.findRegionNameByCode(address.getRegionCode()));
            terminateCityData.setCoordinate(address.getCoordinates());

            terminateCityList.add(terminateCityData);
        }
        return terminateCityList;
    }

    /**
     * 构造取货地
     *
     * @param src
     * @return
     */
    private CityAdressData transformSrcAddr(WaybillDeliveryAddress src) {
        CityAdressData startCityData = new CityAdressData();
        startCityData.setAddress(src.getAddressDetail());
        startCityData.setCoordinate(src.getCoordinates());
        startCityData.setRegionCode(regionTgmService.findRegionCodeByCoordinate(src.getCoordinates()));
        startCityData.setCity(regionTgmService.findRegionNameByCode(src.getRegionCode()));
        return startCityData;
    }

    /**
     * 获取地址线路信息
     *
     * @param result
     * @param formAddress
     * @param toAddress
     * @return
     */
    private DistanceAndPriceData buildAppCalPriceInfo(DistanceAndPriceData result, CityAdressData formAddress,
            List<CityAdressData> toAddress) {
        String regionCode = null;
        if (null != formAddress) {
            regionCode = formAddress.getRegionCode();
            // 若客户端没有回传城市编码，则根据取货地坐标或地址获取
            if (StringUtils.isBlank(regionCode)) {
                regionCode = regionTgmService.findRegionCodeByAddress(formAddress);
            }
        }

        // 得到坐标和距离
        if (null != formAddress && StringUtils.isNotBlank(formAddress.getAddress())
                && StringUtils.isNotBlank(formAddress.getCoordinate())
                && com.giants.common.collections.CollectionUtils.isNotEmpty(toAddress)) {
            result = gaoDeMapService.getDistanceInfo(formAddress, toAddress);
            result.setSiteNo(toAddress.size());
        }
        result.setRegionCode(regionCode);
        return result;
    }

    @Override
    public void update(Waybill waybill, LoginUser loginUser) {
        this.checkFreight(waybill.getEstimateFreight());
        this.checkFreight(waybill.getShow4DriverFreight());
        waybill.setLastUpdateTime(new Date());
        if (null != loginUser) {
            waybill.setLastUpdateUserId(loginUser.getUserId());
        }
        waybillDao.update(waybill);
    }

    private void checkFreight(BigDecimal freight) {
        if (null == freight) {
            return;
        }

        if (BigDecimal.ZERO.compareTo(freight) == 1) {
            throw new BusinessException("freightMustGreaterThanZero", "errors.freightMustGreaterThanZero");
        }

        if (new BigDecimal("999999.99").compareTo(freight) == -1) {
            throw new BusinessException("freightMustLessThanMaximumValue", "errors.freightMustLessThanMaximumValue");
        }
    }

    @Override
    public void increaseWaybillCount(Waybill dbWaybill) {
        // 更新企业客户订单数量
        if (dbWaybill.getCustomerId() != null) {
            try {
                customerInfoService.increaseWaybillCount(1, dbWaybill.getCustomerId());
            } catch (BusinessException e) {
                log.warn("更新企业客户订单数量错误,customerId:{}", dbWaybill.getCustomerId(), e);
            }
        }
    }

    /**
     * 司机端地图显示取货地、配送地
     */
    @Override
    public WaybillMapTracePoint findWaybillMapPoint(Integer driverId, Integer toDayOrTomorrow, LoginUser loginUser) {
        WaybillMapTracePoint wmp = new WaybillMapTracePoint();
        Driver driver = driverService.getDriver(driverId);
        if (driver != null) {
            CapacityPool pool = vmsService.loadCapacityPoolByDriverId(driverId, loginUser);
            if (pool != null && pool.getTruckId() != null) {
                com.juma.vms.truck.domain.Truck truck = vmsService.loadByTruckId(pool.getTruckId());
                if (truck != null) {
                    wmp.setCoordinates(truckLocation(truck.getPlateNumber()));
                }
            }
        }

        List<Integer> values = new ArrayList<Integer>();
        values.add(Waybill.StatusView.WATING_DELIVERY.getCode());
        values.add(Waybill.StatusView.DELIVERYING.getCode());
        WaybillExample example = new WaybillExample();
        Criteria criteria = example.createCriteria().andDriverIdEqualTo(driverId)
                .andTenantIdEqualTo(loginUser.getTenantId()).andStatusViewIn(values);
        if (toDayOrTomorrow == 2) {
            Date[] d = buildTomorrow();
            criteria.andPlanDeliveryTimeBetween(d[0], d[1]);
        } else if (toDayOrTomorrow == 1) {
            Date[] d = buildToday();
            criteria.andPlanDeliveryTimeBetween(d[0], d[1]);
        }

        List<Waybill> waybills = waybillDao.selectByExample(example);
        for (Waybill waybill : waybills) {
            WaybillDeliveryAddress waybillDeliveryAddress = waybillDeliveryAddressService
                    .findByWaybillId(waybill.getWaybillId());
            if (waybillDeliveryAddress != null) {
                if (waybillDeliveryAddress.getIsArrived() != null
                        && WaybillDeliveryAddress.Arrive.YES.getCode() == waybillDeliveryAddress.getIsArrived()) {
                    // 已经取货需要显示收货地
                    List<WaybillReceiveAddress> rows = waybillReceiveAddressService
                            .findAllByWaybillId(waybill.getWaybillId());
                    for (WaybillReceiveAddress waybillReceiveAddress : rows) {
                        if (waybillReceiveAddress.getIsArrived() != null
                                && waybillReceiveAddress.getIsArrived() == WaybillReceiveAddress.Arrive.YES.getCode())
                            continue;//// 电子围栏触发
                        WaybillAddress n2 = new WaybillAddress();
                        BeanUtils.copyProperties(waybillReceiveAddress, n2);
                        wmp.addNode(n2);
                        n2.setType(2);
                    }
                } else {
                    WaybillAddress n = new WaybillAddress();
                    BeanUtils.copyProperties(waybillDeliveryAddress, n);
                    n.setType(1);
                    wmp.addNode(n);
                }
            }
        }
        if (!wmp.getNodes().isEmpty()) {
            Collections.sort(wmp.getNodes());
        }
        return wmp;
    }

    /*
     * private List<WaybillDeliveryAddress> buildWaybillDeliveryAddress(List<Integer> waybillIds) { WaybillDeliveryAddressExample example = new WaybillDeliveryAddressExample();
     * example.createCriteria().andWaybillIdIn(waybillIds); return waybillDeliveryAddressDao.selectByExample(example); }
     *
     * private List<WaybillReceiveAddress> buildWaybillReceiveAddress(List<Integer> waybillIds) { WaybillReceiveAddressExample example = new WaybillReceiveAddressExample();
     * example.createCriteria().andWaybillIdIn(waybillIds); return waybillReceiveAddressDao.selectByExample(example); }
     */

    private Date[] buildTomorrow() {
        Date d = new Date();
        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(d);
        fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
        fromCalendar.set(Calendar.MINUTE, 0);
        fromCalendar.set(Calendar.SECOND, 0);
        fromCalendar.set(Calendar.MILLISECOND, 0);
        fromCalendar.add(Calendar.DAY_OF_YEAR, 1);

        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(fromCalendar.getTime());
        toCalendar.add(Calendar.DAY_OF_YEAR, 1);

        return new Date[] { fromCalendar.getTime(), toCalendar.getTime() };
    }

    private Date[] buildToday() {
        Date d = new Date();
        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(d);
        fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
        fromCalendar.set(Calendar.MINUTE, 0);
        fromCalendar.set(Calendar.SECOND, 0);
        fromCalendar.set(Calendar.MILLISECOND, 0);

        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(fromCalendar.getTime());
        toCalendar.add(Calendar.DAY_OF_YEAR, 1);

        return new Date[] { fromCalendar.getTime(), toCalendar.getTime() };
    }

    @Override
    public List<Waybill> selectByExample(WaybillExample example) {
        return waybillDao.selectByExample(example);
    }

    @Override
    public void batchUpdate(List<Waybill> waybills) {
        waybillDao.batchUpdate(waybills);
    }

    @Override
    public DistanceAndPriceData getGaodeMapInfo(CityAdressData formAddress, List<CityAdressData> toAddress)
            throws BusinessException {
        DistanceAndPriceData result = new DistanceAndPriceData();
        String fromRegionCode = null;

        Region region = null;
        if (null != formAddress) {
            region = regionTgmService.findRegionByAddress(formAddress);
            fromRegionCode = formAddress.getRegionCode();
        }

        // 得到坐标和距离
        if (null != formAddress && StringUtils.isNotBlank(formAddress.getAddress())
                && StringUtils.isNotBlank(formAddress.getCoordinate()) && CollectionUtils.isNotEmpty(toAddress)) {
            result = gaoDeMapService.getDistanceInfo(formAddress, toAddress);
            result.setSiteNo(toAddress.size());
        }

        result.setRegionCode(fromRegionCode);
        if (region != null) {
            result.setRegionCode(region.getRegionCode());
            result.setRegion(region);
        }

        return result;
    }

    @Override
    public String truckLocation(String plateNumber) {
        if (StringUtils.isBlank(plateNumber)) {
            return null;
        }

        RealTimePosition position = realTimePositionService.queryLastPosByPlateNum(plateNumber);
        if (position != null) {
            return position.getLng() + "," + position.getLat();
        }
        return null;
    }

    @Override
    public List<Waybill> listFreightGroupByDriverId(PageCondition pageCondition) {
        return waybillDao.getFreightList(pageCondition);
    }

    @Override
    public EmployeeInfo loadEmployeeInfo(Integer employeeId) {
        EmployeeInfo info = new EmployeeInfo();
        Employee employee = employeeService.loadEmployee(employeeId);
        if (employee != null) {
            info.setEmployeeId(employeeId);
            User u = userService.loadUser(employee.getUserId());
            if (u != null) {
                info.setUserId(u.getUserId());
                info.setMobileNumber(u.getMobileNumber());
                info.setName(u.getName());
                info.setLoginName(u.getLoginName());
            }
        }
        return info;
    }

    /**
     * 取消原来指派的司机（没有删除运单中的司机车辆信息）
     *
     * @param receiveWay
     * @param loginUser
     * @param wb
     * @param driver
     * @param truck
     */
    @Override
    public void doCancelAssign(int receiveWay, Waybill wb, Driver driver, Truck truck, LoginUser loginUser)
            throws BusinessException {
        // 校验司机
        this.checkDriverCanbeAssign(driver, wb.getStatus(), wb.getReceiveWay());

        // 校验车辆

        if (null == truck) {
            throw new BusinessException("truckNotfound", "truck.error.not.found");
        }

        // 校验派车方式
        if (StringUtils.isBlank(Waybill.ReceiveWay.buildReceiveWayStr(receiveWay))) {
            throw new BusinessException("receiveWayErr", "waybill.error.receiveWayErr");
        }
    }

    /**
     * 重新指派司机
     *
     * @param receiveWay
     * @param remark
     * @param loginUser
     * @param wb
     * @param driver
     * @param truck
     */
    @Override
    public void doAssignCarAgain(int receiveWay, String remark, Waybill wb, Driver driver, Truck truck,
            LoginUser loginUser) throws BusinessException {

        if (wb == null) throw new BusinessException("waybillNull", "errors.paramCanNotNullWithName", "运单");
        if (truck == null) throw new BusinessException("waybillNull", "errors.paramCanNotNullWithName", "车辆");
        if (driver == null) throw new BusinessException("waybillNull", "errors.paramCanNotNullWithName", "司机");
        if (StringUtils.isBlank(truck.getPlateNumber()))
            throw new BusinessException("plateNumberCanNotNull", "errors.plateNumberCanNotNull");

        // 组装参数
        Waybill waybill = new Waybill();
        waybill.setWaybillId(wb.getWaybillId());
        waybill.setVehicleToVendor(wb.getVehicleToVendor());
        waybill.setDriverId(driver.getDriverId());
        waybill.setDriverName(driver.getNickname());
        waybill.setDriverType(vmsCommonService.findDriverRunType(driver.getDriverId()));
        waybill.setTruckId(truck.getTruckId());
        waybill.setPlateNumber(truck.getPlateNumber());
        waybill.setReceiveWay(receiveWay);
        waybill.setAssignWaybillRemark(remark);
        waybill = buildVehicleTypeAndVendorService.checkAndBuildVehicleTypeAndVendor(waybill, truck.getVehicleId(),
                waybill.getDriverType(), BuildVehicleTypeAndVendorService.CHANGE_TO_ASSIGN, loginUser);

        // 派车
        changeToAssignedUpdateDb(waybill, loginUser);

        // 重置司机已读
        waybillParamService.driverUnReadWaybill(wb.getWaybillId(), loginUser);

        // 解绑电子围栏
        waybillAutoFenceServicve.removeAllFenceId(wb.getWaybillId(), loginUser);

        // 绑定电子围栏
        waybillAutoFenceServicve.bindWaybillIdAndFenceId(wb.getWaybillId(), WaybillBindFence.Sign.DELIVERY_ADDRESS,
                loginUser);

    }

    // 检查运单能不能指派给司机，并返回司机信息
    public void checkDriverCanbeAssign(Driver driver, Integer status, Integer receiveWay) throws BusinessException {
        if (driver == null) {
            throw new BusinessException("driverNotFound", "driver.error.not.found");
        }

        if (status != Waybill.Status.NEW.getCode() && Waybill.ReceiveWay.ASSIGNED.getCode() == receiveWay) {
            throw new BusinessException("assignedWaybillCannotChangeCar",
                    "waybill.error.assignedWaybillCannotChangeCar");
        }
    }

    // 运单指派司机操作：更改数据库运单信息，真正的人工派车操作
    public void changeToAssignedUpdateDb(Waybill waybill, LoginUser loginUser) throws BusinessException {
        waybill.setStatus(Waybill.Status.ASSIGNED.getCode());
        waybill.setStatusView(Waybill.StatusView.WATING_DELIVERY.getCode());
        waybill.setReceivingTime(new Date());
        update(waybill, loginUser);
    }

    // 取消订单
    public void doCancelWaybill(Waybill waybill, LoginUser loginUser, Waybill.Status statuEn) {
        // 取消原因判断
        if (Waybill.CancelChannel.WECHAT_CLIENT.getCode() != waybill.getCancelChannel()) {
            this.checkCancelReason(waybill.getWaybillCancelRemark());
        }
        waybill.setStatus(statuEn.getCode());
        waybill.setStatusView(Waybill.StatusView.CANCEL.getCode());
        try {
            update(waybill, loginUser);
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
            throw e;
        }

        // 清除电子围栏
        try {
            waybillAutoFenceServicve.removeAllFenceId(waybill.getWaybillId(), loginUser);
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
            throw e;
        }
        // 更新司机状态为空闲
        try {
            updateDriverStatusToFree(waybill.getDriverId());
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
            throw e;
        }
        // 清空客户经理订单超时提醒
        customerManWaybillUtils.delHandledWaybill(waybill.getWaybillId());
    }

    // 判断运单取消原因
    private void checkCancelReason(String cancelReason) {
        if (StringUtils.isBlank(cancelReason)) {
            throw new BusinessException("cancelReasonNotNull", "waybill.error.cancelReasonNotNull");
        } else if (cancelReason.length() > 50) {
            throw new BusinessException("cancelReasonTooLong", "waybill.error.cancelReasonTooLong");
        }
    }

    @Override
    public void markLate(Waybill waybill) throws BusinessException {
        Date planDeliveryTime = waybill.getPlanDeliveryTime();
        Date arriveDepotTime = waybill.getArriveDepotTime();
        Integer distance = 0;

        // 获取司机点击到仓时操作位置与实际位置的距离
        WaybillOperateTrack waybillOperateTrack = waybillOperateTrackService.findOperateTrackBy(waybill.getWaybillId(),
                OperateType.ARRIVE_DEPOT.getCode(),
                OperateApplication.DRIVER_SYS.getCode());

        if (null != waybillOperateTrack) {
            distance = waybillOperateTrack.getDistance() == null ? 0 : waybillOperateTrack.getDistance();
        }

        // 迟到标记：时间上判断
        boolean timeFlag = false;
        // 距离太远：距离上判断
        boolean disFlag = false;
        if (null != planDeliveryTime
                && planDeliveryTime.compareTo(arriveDepotTime == null ? new Date() : arriveDepotTime) == -1) {// 超出计划用车时间
            timeFlag = true;
        }
        if (Constants.DEFAULT_DISTANCE.compareTo(distance) == -1) {
            disFlag = true;
        }
        if (timeFlag && disFlag) {
            waybill.setLate(Waybill.Late.LATE_AND_DISTANCE.getCode());
        } else if (timeFlag) {
            waybill.setLate(Waybill.Late.LATE_TIME.getCode());
        } else if (disFlag) {
            waybill.setLate(Waybill.Late.DISTANCE_TO_SOON.getCode());
        }
    }

    public void updateDriverStatusToFree(Integer driverId) {
        if (null == driverId) {
            return;
        }

        Waybill waybill = new Waybill();
        waybill.setDriverId(driverId);
        waybill.setStatus(Waybill.Status.DELIVERYING.getCode());
        List<Waybill> list = waybillDao.findByExample(waybill);
        if (CollectionUtils.isEmpty(list)) {
            // 更新司机状态为空闲
            driverService.updateDriverTaskStatus(driverId, Driver.TaskStatus.ABLE);
        }
    }

    @Override
    public void structPageCondition(PageCondition pageCondition, LoginUser loginUser) throws BusinessException {
        Map<String, Object> filters = pageCondition.getFilters();
        if (null == filters) {
            filters = new HashMap<String, Object>();
            pageCondition.setFilters(filters);
            return;
        }

        // 处理运单显示状态查询
        Object obj = filters.get("statusView");
        if (null != obj) {
            int statusView = BaseUtil.strToNum(obj.toString());
            if (statusView == Waybill.StatusView.WATING_DELIVERY.getCode()) {
                List<Integer> statusViewList = new ArrayList<Integer>();
                statusViewList.add(statusView);
                statusViewList.add(Waybill.StatusView.TEMP.getCode());
                filters.put("statusViewList", statusViewList);
                filters.remove("statusView");
            }
        }

        // 根据司机手机号或用车人手机号查询，只能精确查询
        obj = filters.get("driverPhone");
        filters.remove("driverPhone");
        if (null != obj) {
            // 根据司机手机号获取司机的ID(driverId)
            Driver driver = driverService.findDriverByPhone(obj.toString().trim());
            if (null != driver) {
                filters.put("driverId", driver.getDriverId());
            } else {
                // 查询不到是的策略
                filters.put("driverId", -1);
            }
        }
        obj = filters.get("driverName");
        filters.remove("driverName");
        if (null != obj) {
            // 根据司机姓名获取司机的ID(driverId)
            List<Driver> list = driverService.listDriverByName(obj.toString().trim());
            if (CollectionUtils.isNotEmpty(list)) {
                List<Integer> driverIdList = new ArrayList<Integer>();
                for (Driver driver : list) {
                    driverIdList.add(driver.getDriverId());
                }
                filters.put("driverIdList", driverIdList);
            } else {
                // 查询不到是的策略
                filters.put("driverId", -1);
            }
        }

        obj = filters.get("customerManagePhone");
        filters.remove("customerManagePhone");
        if (null != obj) {
            // 根据客户经理(企业客户的客户经理)手机号获取客户经理的userId
            Employee employee = employeeService.loadEmployee(User.UserUniqueAttribute.mobileNumber,
                    obj.toString().trim(), loginUser);
            log.info("根据客户经理(企业客户的客户经理)手机号获取客户经理员工ID：{}", employee == null ? null : JSON.toJSONString(employee));
            if (null != employee) {
                filters.put("customerManagerId", employee.getEmployeeId());
            } else {
                // 查询不到是的策略
                filters.put("customerManagerId", -1);
            }
        }

        obj = filters.get("projectManagePhone");
        filters.remove("projectManagePhone");
        if (null != obj) {
            // 根据项目经理手机号获取项目经理的userId
            User user = userService.loadUser(UserUniqueAttribute.mobileNumber, obj.toString());
            log.info("根据项目经理手机号获取项目经理的ID：{}", user == null ? null : JSON.toJSONString(user));
            if (null != user) {
                filters.put("projectManagerUserId", user.getUserId());
            } else {
                // 查询不到是的策略
                filters.put("projectManagerUserId", -1);
            }
        }

        // 保留原因：CRM系统会调用运单列表，传回来的参数是customerName
        obj = filters.get("customerName");
        filters.remove("customerName");
        if (null != obj) {
            CustomerInfo customerInfo = customerInfoService.findCusInfoByName(obj.toString(), null, loginUser);
            if (null != customerInfo) {
                filters.put("customerId", customerInfo.getCustomerId());
            } else {
                // 查询不到是的策略
                filters.put("customerId", -1);
            }
        }

        pageCondition.setFilters(filters);
    }

    @Override
    public WaybillMap buildWaybillMap(Waybill waybill) throws BusinessException {
        WaybillMap map = new WaybillMap();
        if (waybill == null) return map;

        map.setWaybillId(waybill.getWaybillId());
        if (null != waybill.getCmEstimateFinishTime()) {
            String estimateFinishTime = DateUtil.format(waybill.getCmEstimateFinishTime());
            map.setEstimateFinishTime(estimateFinishTime);
        }
        map.setWaybillNo(waybill.getWaybillNo());
        map.setActualMileage(waybill.getActualMileage());
        map.setDeviceNo(waybill.getDeviceNo());
        Integer truckId = waybill.getTruckId();
        Truck truck = truckService.getTruck(truckId);
        if (truck != null) {
            map.setVehicleId(truck.getVehicleId());
            map.setPlateNumber(truck.getPlateNumber());
        }
        Integer driverId = waybill.getDriverId();
        if (driverId != null) {
            Driver driver = driverService.getDriver(driverId);
            if (driver != null) {
                com.juma.server.vm.domain.Driver driver2 = amsServiceV2.getDriverById(driver.getAmsDriverId());
                if (driver2 != null) {
                    map.setDriverName(driver2.getName());
                    map.setParkingRegionCode(driver2.getParkRegionCode());
                    if (null != driver2.getLongitude() && null != driver2.getLatitude()) {
                        AmapRegeoResponse regeocode = gaoDeMapService
                                .regeocode(driver2.getLongitude() + "," + driver2.getLatitude());
                        if (null != regeocode) {
                            map.setParkingRegionCodeText(regeocode.getRegeocode().getFormattedAddress());
                        }
                    }
                }
            }
        }
        Integer customerId = waybill.getCustomerId();
        if (customerId != null) {
            CustomerInfo customerInfo = customerInfoService.findCusInfoById(customerId);
            if (customerInfo != null) {
                map.setCustomerName(customerInfo.getCustomerName());
            }
        }

        try {
            Employee employee = employeeService.loadEmployee(waybill.getCustomerManagerId());
            User user = userService.findUser(employee.getUserId());
            map.setCustomerManagerName(user.getName());
        } catch (Exception e) {
        }

        WaybillParam waybillParam = waybillParamService.findByWaybillId(waybill.getWaybillId());
        if (waybillParam != null) {
            map.setRequiredMaxTemperature(waybillParam.getRequiredMaxTemperature());
            map.setRequiredMinTemperature(waybillParam.getRequiredMinTemperature());
            if (waybillParam.getDistributionPointNo() == null) {
                map.setDistributionPointNo(waybillReceiveAddressService.findNumByWaybillId(waybill.getWaybillId()));
            } else {
                map.setDistributionPointNo(waybillParam.getDistributionPointNo());
            }
        } else {
            map.setDistributionPointNo(waybillReceiveAddressService.findNumByWaybillId(waybill.getWaybillId()));
        }

        map.setTruckRequire(truckRequireService.getTruckRequire(null, waybill.getWaybillId()));
        map.setEstimateDistance(waybill.getEstimateDistance());
        map.setFinishTime(waybill.getFinishTime());
        map.setPlanDeliveryTime(waybill.getPlanDeliveryTime());
        map.setLeaveDepotTime(waybill.getDeliveryTime());
        map.setArriveDepotTime(waybill.getArriveDepotTime());

        map.setReceiveAddress(waybillReceiveAddressService.findAllByWaybillId(waybill.getWaybillId()));
        map.setStatusView(waybill.getStatusView());
        map.setStatusViewText(waybill.getStatusViewText());
        map.setDeliveryAddress(waybillDeliveryAddressService.findAllByWaybillId(waybill.getWaybillId()));

        return map;
    }

    @Override
    public DistanceAndPriceData calculateStanderPrice(DistanceAndPriceParamVo dp, LoginUser loginUser)
            throws BusinessException {
        DistanceAndPriceData rst = new DistanceAndPriceData();

        this.buildRegionData(dp, rst, loginUser);

        Waybill waybill = dp.getWaybill();
        if (waybill == null) return rst;
        waybill.setEstimateDistance(rst.getDistance());
        waybill.setTolls(rst.getTolls());
        if (rst.getDistance() == null) return rst;
        if (dp.getTruckRequire() == null) return rst;
        waybill.setRegionCode(rst.getRegionCode());
        if (StringUtils.isBlank(waybill.getRegionCode())) return rst;

        // 价格计算（不含税费）
        BigDecimal price = scatteredWaybillService.computeFreight(dp, loginUser);
        if (price != null) {
            rst.setPrice(price);
        }

        return rst;
    }

    /**
     * 计算含税费用
     *
     * @param originalPrice
     * @param taxRate
     * @return
     */
    private BigDecimal getWithTaxPrice(BigDecimal originalPrice, BigDecimal taxRate) {
        if (originalPrice == null) return null;
        if (taxRate == null) return originalPrice;

        return originalPrice.multiply(taxRate.add(new BigDecimal("1")));
    }

    @Override
    public DistanceAndPriceData calculateStanderPriceWithDriverFreight(DistanceAndPriceParamVo dp, LoginUser loginUser)
            throws BusinessException {

        WaybillBo bo = new WaybillBo();
        bo.setTruckRequire(dp.getTruckRequire());
        bo.setWaybill(dp.getWaybill());
        this.planEstimateFinishTimeCheck(bo);

        DistanceAndPriceData priceData = this.calculateStanderPrice(dp, loginUser);

        Waybill waybill = dp.getWaybill();

        if (waybill == null) {
            waybill = new Waybill();
        }

        if (dp.getTruckRequire() != null) {
            TruckRequire truckRequire = dp.getTruckRequire();
            // 获取含税价格
            BigDecimal withTaxPrice = this.getWithTaxPrice(priceData.getPrice(), truckRequire.getTaxRateValue());
            if (waybill.getEstimateFreight() == null) {
                waybill.setEstimateFreight(withTaxPrice);
            }
            waybill.setRebateRate(waybill.getRebateRate());
            priceData.setWithTaxPrice(withTaxPrice);
        }

        if (waybill.getEstimateFreight() == null) {
            waybill.setEstimateFreight(priceData.getPrice());
        }

        waybill.setAfterTaxFreight(priceData.getPrice());
        priceData.setWithoutTaxPrice(priceData.getPrice());
        // // --获取司机结算价格
        WaybillParam waybillParam = waybillService.settingExtraFee(bo, null);
        BigDecimal show4DriverFreight = waybillParam.getShow4DriverFreight();
        if (show4DriverFreight != null) {
            priceData.setShow4DriverFreight(show4DriverFreight.setScale(2, BigDecimal.ROUND_HALF_UP));
        }

        return priceData;
    }

    @Override
    public DistanceAndPriceData calculateStanderPrice(int waybillId, LoginUser loginUser) throws BusinessException {
        Waybill waybill = this.getWaybillById(waybillId);
        if (waybill == null) return null;

        DistanceAndPriceParamVo dpVo = new DistanceAndPriceParamVo();
        dpVo.setWaybill(waybill);
        dpVo.setTruckRequire(truckRequireService.findTruckRequireByWaybillId(waybill.getWaybillId(), loginUser));
        List<WaybillReceiveAddress> destList = waybillReceiveAddressService.findAllByWaybillId(waybillId);
        List<WaybillDeliveryAddress> srcList = waybillDeliveryAddressService.findAllByWaybillId(waybillId);
        if (CollectionUtils.isEmpty(destList)) return null;
        if (CollectionUtils.isEmpty(srcList)) return null;

        dpVo.setToAddress(this.transformDestAddr(destList));
        dpVo.setSrcAddress(this.transformSrcAddr(srcList.get(0)));
        WaybillParam waybillParam = waybillParamService.findByWaybillId(waybillId);
        dpVo.setWaybillParam(waybillParam);
        DistanceAndPriceData priceData = this.calculateStanderPrice(dpVo, loginUser);

        return priceData;
    }

    private void planEstimateFinishTimeCheck(WaybillBo waybillBo) {
        try {
            if (DateUtils.truncatedCompareTo(waybillBo.getWaybill().getPlanDeliveryTime(),
                    waybillBo.getWaybill().getCmEstimateFinishTime(), Calendar.MINUTE) >= 0) {
                throw new BusinessException("estimateFinishTimeEarly", "waybill.error.estimate.finish.time.early");
            }
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            // ignore
        }
    }

    /**
     * 获取距离区域信息
     *
     * @param dp
     * @param rst
     * @param loginUser
     */
    private void buildRegionData(DistanceAndPriceParamVo dp, DistanceAndPriceData rst, LoginUser loginUser) {
        DistanceAndPriceData distanceData = this.getGaodeMapInfo(dp.getSrcAddress(), dp.getToAddress());

        if (distanceData == null) return;

        rst.setDistance(distanceData.getDistance());
        rst.setDuration(distanceData.getDuration());
        rst.setRegionCode(distanceData.getRegionCode());
        rst.setTollDistance(distanceData.getTollDistance());
        rst.setTolls(distanceData.getTolls());

        // 获取业务区域
        if (distanceData.getRegion() == null) return;

        Region region = distanceData.getRegion();
        rst.setRegionCode(StringUtils.left(region.getRegionCode(), 5));
        BusinessArea businessArea = businessAreaService.loadBelongingBusinessArea(loginUser.getTenantId(), region);
        distanceData.setRegion(null);
        if (businessArea == null) {
            // 找不到业务区域则将运单归属到总部
            businessArea = businessAreaService.loadLogicBusinessArea("00", loginUser);
        }

        if (businessArea == null) return;
        // 行政区域到市一级
        rst.setWaybillAreaCode(businessArea.getAreaCode());

    }

    @Override
    public List<Waybill> findByReconciliationId(Integer reconciliationId) {
        if (null == reconciliationId) {
            return new ArrayList<Waybill>();
        }
        return waybillDao.findByReconciliationId(reconciliationId);

    }

    @Override
    public void batchUpdateHasReconciliation(List<Waybill> waybills) {
        if (waybills.isEmpty()) {
            return;
        }
        waybillDao.batchUpdateHasReconciliation(waybills);
    }

    @Override
    public void cancelWaybillForOrder(List<Waybill> waybills, LoginUser loginUser) throws BusinessException {
        if (CollectionUtils.isEmpty(waybills))
            throw new BusinessException("waybillIdsNull", "errors.paramCanNotNullWithName", "运单号");
        if (loginUser == null) throw new BusinessException("waybillIdsNull", "errors.paramCanNotNullWithName", "操作人");
        for (Waybill waybill : waybills) {
            if (waybill.getCancelChannel() == null)
                throw new BusinessException("CancelChannelNull", "errors.common.prompt", "运单取消原因为空");

            this.doCancelWaybill(waybill, loginUser, Waybill.Status.SYS_CANCEL);
            // 操作轨迹
            waybillOperateTrackService.insert(waybill.getWaybillId(), OperateType.CANCEL,
                    OperateApplication.INTERFACE, null, loginUser);
        }
    }

    @Override
    public List<Waybill> findByReconciliationNo(String reconciliationNo) throws BusinessException {
        if (StringUtils.isBlank(reconciliationNo)) return null;
        Waybill example = new Waybill();
        example.setReconciliationNo(reconciliationNo);

        List<Waybill> waybills = waybillDao.findByExample(example);
        return waybills;
    }

    @Override
    public List<Waybill> findByReceivableReconciliationNo(String receivableReconcilicationNo) throws BusinessException {
        if (StringUtils.isBlank(receivableReconcilicationNo)) return null;
        Waybill example = new Waybill();
        example.setReceivableReconcilicationNo(receivableReconcilicationNo);

        List<Waybill> waybills = waybillDao.findByExample(example);
        return waybills;
    }

    @Override
    public void modifyWaybillReceiptStatusByReconciliationNo(String reconciliationNo,
            Waybill.ReceiptStatus receiptStatus, LoginUser loginUser) throws BusinessException {
        if (StringUtils.isBlank(reconciliationNo)) return;
        if (receiptStatus == null) return;

        Waybill waybillAboutReconciliation = new Waybill();
        if (loginUser != null) {
            waybillAboutReconciliation.setLastUpdateUserId(loginUser.getUserId());
            waybillAboutReconciliation.setLastUpdateTime(new Date());
        }
        waybillAboutReconciliation.setReceiptStatus(receiptStatus.getCode());
        waybillExtDao.modifyStatusAboutReconciliationByReconciliationNo(reconciliationNo, null, null,
                waybillAboutReconciliation);
    }

    @Override
    public void modifyWaybillSettlementStatusByReconciliationNo(String reconciliationNo, String plateNumber,
            Integer vendorId, Waybill.SettlementStatus settlementStatus, LoginUser loginUser) throws BusinessException {
        if (StringUtils.isBlank(reconciliationNo)) return;
        if (StringUtils.isBlank(plateNumber) && vendorId == null) return;
        if (settlementStatus == null) return;

        Waybill waybillAboutReconciliation = new Waybill();
        if (loginUser != null) {
            waybillAboutReconciliation.setLastUpdateUserId(loginUser.getUserId());
            waybillAboutReconciliation.setLastUpdateTime(new Date());
        }

        waybillAboutReconciliation.setSettlementStatus(settlementStatus.getCode());
        waybillExtDao.modifyStatusAboutReconciliationByReconciliationNo(reconciliationNo, plateNumber, vendorId,
                waybillAboutReconciliation);

    }

    @Override
    public Waybill findWaybillByTransformBillId(Integer transformBillLinkId) {
        // 判断运单是不是承运单
        Waybill carryWaybill = this.getWaybillById(transformBillLinkId);
        if (null == carryWaybill) {
            return null;
        }

        if (NumberUtils.compare(Waybill.WaybillSource.TRANSFORM_BILL.getCode(), carryWaybill.getWaybillSource()) != 0) {
            return null;
        }

        // 获取被承运的运单信息
        WaybillParam waybillParam = waybillParamService.findByTransformBillLinkId(transformBillLinkId);
        if (null == waybillParam) {
            return null;
        }

        return this.getWaybillById(waybillParam.getWaybillId());
    }

    @Override
    public void checkBuzAreaAndForbiddenArea(List<WaybillDeliveryAddress> sourceAddress,
            List<WaybillReceiveAddress> destAddress, LoginUser loginUser) throws BusinessException {
        if (sourceAddress == null || sourceAddress.isEmpty() || destAddress == null || destAddress.isEmpty())
            throw new BusinessException("waybillNullError", "errors.paramCanNotNullWithName", "运单地址参数");

        if (loginUser.getTenantId() != 3) return;

        CityAdressData srcAddress = new CityAdressData();
        List<CityAdressData> toAddress = new ArrayList<CityAdressData>();

        WaybillDeliveryAddress deliveryAddress = sourceAddress.get(0);
        srcAddress.setCity(deliveryAddress.getAddressName());
        srcAddress.setAddress(deliveryAddress.getAddressName());
        srcAddress.setAddressDetail(deliveryAddress.getAddressDetail());
        srcAddress.setRegionCode(deliveryAddress.getRegionCode());
        srcAddress.setCoordinate(deliveryAddress.getCoordinates());
        for (WaybillReceiveAddress destAddr : destAddress) {
            CityAdressData toCityAddress = new CityAdressData();
            toCityAddress.setCity(destAddr.getAddressName());
            toCityAddress.setAddress(destAddr.getAddressName());
            toCityAddress.setAddressDetail(destAddr.getAddressDetail());
            toCityAddress.setRegionCode(destAddr.getRegionCode());
            toCityAddress.setCoordinate(destAddr.getCoordinates());
            toAddress.add(toCityAddress);
        }
        AtFenceResultVo result = scatteredWaybillService.isAtFenceArea(srcAddress, toAddress, loginUser);

        if (result == null) return;
        if (result.getForBiddenType() != null && result.isAtForbiddenArea()) {
            // 取货地
            if (result.getForBiddenType() == ForbiddenType.sourceArea) {
                throw new BusinessException("sourceAreaInProhibitionZone", "errors.sourceAreaInProhibitionZone");
            } else if (result.getForBiddenType() == ForbiddenType.destinationArea) {
                throw new BusinessException("destinationAreaInProhibitionZone",
                        "errors.destinationAreaInProhibitionZone", result.getForbiddenAreaIndex() + 1);
            }
        }

        if (!result.getAtBusinessArea()) {
            throw new BusinessException("notAtBusinessArea", "errors.notAtBusinessArea");
        }
    }

    @Override
    public void batchUpdateReceivableReconcilicationNo(List<Waybill> waybills) {
        waybillDao.batchUpdateReceivableWaybill(waybills);
    }

    @Override
    public void doWaybillAddVehicleTovendorByTruckId(Integer truckId, Integer vendorId) {
        waybillDao.doWaybillAddVehicleTovendorByTruckId(truckId, vendorId);
    }

    @Override
    public void modifyWaybillReceiptStatusByReceivableReconcilicationNo(String receivableReconcilicationNo,
            Integer receiptStatus, LoginUser loginUser) {

        Waybill waybill = new Waybill();
        waybill.setReceivableReconcilicationNo(receivableReconcilicationNo);
        waybill.setReceiptStatus(receiptStatus);
        waybill.setLastUpdateUserId(loginUser.getUserId());
        waybill.setLastUpdateTime(new Date());
        waybillDao.updateWaybillByReceivableReconcilicationNo(waybill);
    }

    /**确认运费**/
    @Override
    public void confirmWaybillAmount(ConfirmWaybillAmountVO confirmWaybillAmountVO, LoginEmployee loginEmployee) throws BusinessException {
        Waybill waybill = validParams(confirmWaybillAmountVO,loginEmployee);
        validWaybilGrossProfitRate(confirmWaybillAmountVO,waybill);
        updateWaybillAmount(confirmWaybillAmountVO,waybill,loginEmployee);
    }

    /**
     * 刷新运单金额状态数据接口
     * @param gap 确认运费过期时间间隔(1天)
     */
    @Override
    public void freshWaybillAmountStatus(List<Integer> inputWaybillIds,Integer gap) throws BusinessException {
        log.info("刷新运单-运费确认状态,开始定时任务");
        LoginUser loginUser = new LoginUser(19,1);
        Map<Integer,WaybillAmount> amountMap = Maps.newConcurrentMap();
        List<Waybill> waybills = Lists.newArrayList();
        if( CollectionUtils.isEmpty(inputWaybillIds) ){
            waybills = fetchLast2DaysWaybills(gap);
        }else{
            fetchInputWaybills(inputWaybillIds, waybills);
        }

        if( CollectionUtils.isEmpty(waybills) ){ return; }

        List<Integer> waybillIds = Lists.newArrayList();
        for (Waybill waybill : waybills){
            waybillIds.add(waybill.getWaybillId());
        }

        WaybillAmountFilter filter = new WaybillAmountFilter();
        filter.setWaybillIds(waybillIds);
        filter.setAmountStatus(AmountStatus.UNCONFIRM.getCode());
        List<WaybillAmount> amounts = waybillAmountService.findByFilter(filter,loginUser);
        for (WaybillAmount waybillAmount : amounts){
            amountMap.put(waybillAmount.getWaybillId(),waybillAmount);
        }

        // 用车时间N天前允许改价
        List<Integer> needFreshWaybillIds = Lists.newArrayList();

        List<WaybillAmount> needFreshs = Lists.newArrayList();
        for (Waybill waybill : waybills){
            WaybillAmount amount = amountMap.get(waybill.getWaybillId());
            if( null == amount ){ continue; }
            amount.setAmountStatus(AmountStatus.TIMEOUT_UNCONFIRM.getCode());
            needFreshs.add(amount);
        }
        if( CollectionUtils.isEmpty(needFreshs) ){ return; }
        List<WaybillAmount> insertBatch = Lists.newArrayList();
        log.info("刷新运单-运费确认状态:运单费用数据:{}条",needFreshs.size());
        for (WaybillAmount waybillAmount : needFreshs){
            insertBatch.add(waybillAmount);
            // 分段执行,避免SQL语句超长
            if( insertBatch.size() == Constants.DEFAULT_LIST_SIZE ){
                waybillAmountService.updateBatch(insertBatch,loginUser);
                log.info("刷新运单-运费确认状态:运单费用数据:{}",JSON.toJSONString(insertBatch));
                insertBatch.clear();
            }
        }
        if( !CollectionUtils.isEmpty(insertBatch) ){
            waybillAmountService.updateBatch(insertBatch,loginUser);
        }
        log.info("刷新运单-运费确认状态:定时任务结束");
    }

    private void fetchInputWaybills(List<Integer> inputWaybillIds, List<Waybill> waybills) {
        List<Waybill> owaybills = waybillService.findByWaybillIds(inputWaybillIds);
        String timeLimit = fetchConstants(Constants.ALLOW_CHANGE_PRICE_TIME_LIMIT_KEY,Constants.ALLOW_CHANGE_PRICE_DEFAULT_TIME_LIMIT.toString());
        for (Waybill waybill : owaybills){
            if( null == waybill.getFinishTime() ){ continue; }
            DateTime finishTime = com.juma.tgm.common.DateUtils.create(waybill.getFinishTime());
            // 当前时间大于运单完成时间+1天的23:59:59时,点击确认按钮提示：“当前已超过运费确认时间，如需调整运费请使用调整单”
            if( com.juma.tgm.common.DateUtils.getDayOfEnd(com.juma.tgm.common.DateUtils.addDays(finishTime,Integer.parseInt(timeLimit))).isBeforeNow() ){
                waybills.add(waybill);
            }
        }
    }

    private List<Waybill> fetchLast2DaysWaybills(Integer gap) {
        String timeLimit = fetchConstants(Constants.ALLOW_CHANGE_PRICE_TIME_LIMIT_KEY,Constants.ALLOW_CHANGE_PRICE_DEFAULT_TIME_LIMIT.toString());
        WaybillFilter waybillFilter = new WaybillFilter();
        Integer days = -1 + -1 * Integer.parseInt(timeLimit);
        if( null == gap ){ gap = 1; }
        Date endTime = com.juma.tgm.common.DateUtils.getDayOfEnd(
                com.juma.tgm.common.DateUtils.addDays(
                        com.juma.tgm.common.DateUtils.create(),days)).toDate();
        Date startTime = com.juma.tgm.common.DateUtils.getDayOfEnd(
                com.juma.tgm.common.DateUtils.addDays(
                        com.juma.tgm.common.DateUtils.create(),days - gap)).toDate();
        waybillFilter.setStartTime(startTime);
        waybillFilter.setEndTime(endTime);
        return  waybillService.findByWaybillByFilter(waybillFilter);
    }

    /**更新运单金额表**/
    private void updateWaybillAmount(ConfirmWaybillAmountVO confirmWaybillAmountVO, Waybill waybill, LoginUser loginUser) {
        WaybillAmount waybillAmount = waybillAmountService.loadByWaybillId(waybill.getWaybillId());
        if( null != waybillAmount ){
            waybillAmount.setCustomerFreightWithTax(confirmWaybillAmountVO.getCustomerAmount());
            waybillAmount.setLastCustomerFreightWithTax(confirmWaybillAmountVO.getCustomerAmount());
            if( null != confirmWaybillAmountVO.getVendorAmount() ){
                waybillAmount.setVendorFreightWithTax(confirmWaybillAmountVO.getVendorAmount());
                waybillAmount.setLastVendorFreightWithTax(confirmWaybillAmountVO.getVendorAmount());
            }
            waybillAmount.setAmountStatus(AmountStatus.CONFIRMED.getCode());
            waybillAmountService.update(waybillAmount,loginUser);
        }
        if( null != confirmWaybillAmountVO.getVendorAmount() ){
            waybill.setShow4DriverFreight(confirmWaybillAmountVO.getVendorAmount());
        }
        waybill.setEstimateFreight(confirmWaybillAmountVO.getCustomerAmount());
        // 税后价格
        TruckRequire truckRequire = truckRequireService.loadReuckRequireByWaybillId(waybill.getWaybillId());
        if (null != truckRequire) {
            BigDecimal rate = BigDecimal.ONE.add(truckRequire.getTaxRateValue() == null ? BigDecimal.ZERO : truckRequire.getTaxRateValue());
            waybill.setAfterTaxFreight(waybill.getEstimateFreight().divide(rate, 2, BigDecimal.ROUND_HALF_UP));
        }
        update(waybill,loginUser);
    }

    /**运单毛利率校验**/
    private void validWaybilGrossProfitRate(ConfirmWaybillAmountVO confirmWaybillAmountVO, Waybill waybill) {
        // 已对账的运单,是否能确认运费?
        Vendor vendor = null;
        if( !Integer.valueOf(Waybill.ReceiveWay.TRANSFORM_BILL.getCode()).equals(waybill.getReceiveWay()) ){
            vendor = vmsCommonService.loadVendorByVendorId(waybill.getVehicleToVendor());
        }else{
            vendor = vmsCommonService.loadVendorByVendorId(waybill.getVendorId());
        }
        if( null == vendor ){
            throw new BusinessException("VendorCannotBeNull","运单没有承运商,不能完成确认运费");
        }

        if( null == vendor.getVendorSource() ){
            throw new BusinessException("VendorSourceCannotBeNull","运单没有承运商,不能完成确认运费");
        }

        // 自营承运商, 不走毛利校验逻辑
        if( VendorSourceEnum.SELF_SUPPORT.getCode() == vendor.getVendorSource().byteValue() ){
            return;
        }

        if( null == confirmWaybillAmountVO.getVendorAmount() ){
            throw new BusinessException("VendorAmountCannotBeNull","非自营承运商,承运侧含税金额不能为空");
        }

        String ceilingAmount = fetchConstants(Constants.ALLOW_WAYBILL_CHANGE_PRICE_CEILING_LIMIT_KEY,Constants.ALLOW_WAYBILL_CHANGE_PRICE_CEILING_LIMIT.toString());
        if( BigDecimal.ZERO.compareTo(confirmWaybillAmountVO.getVendorAmount()) > 0){
            throw new BusinessException("CustomerAmountOutOfRange","输入金额不能小于0，请重新输入");
        }
        if( new BigDecimal(ceilingAmount).compareTo(confirmWaybillAmountVO.getVendorAmount()) < 0){
            throw new BusinessException("CustomerAmountOutOfRange","输入金额已超过单笔运单上限，请重新输入");
        }
    }

    /**参数校验**/
    private Waybill validParams(ConfirmWaybillAmountVO confirmWaybillAmountVO, LoginEmployee loginEmployee) {
        if( null == confirmWaybillAmountVO.getWaybillId() ){
            throw new BusinessException("WaybillCannotBeNull","运单ID不能为空");
        }
        if( null == confirmWaybillAmountVO.getCustomerAmount() ){
            throw new BusinessException("CustomerAmountCannotBeNull","客户侧含税金额不能为空");
        }
        Waybill waybill = waybillService.getWaybill(confirmWaybillAmountVO.getWaybillId());
        if( null == waybill ){
            throw new BusinessException("WaybillNotExist","运单不存在");
        }
        if( null == waybill.getFinishTime() ){
            throw new BusinessException("WaybillNotFinished","运单未完成,不能确认运费");
        }
        if( Integer.valueOf(Waybill.ReconciliationStatus.IN_THE_ACCOUNT.getCode()).equals(waybill.getReconciliationStatus())
                || Integer.valueOf(Waybill.ReconciliationStatus.HAS_RECONCILIATION.getCode()).equals(waybill.getReconciliationStatus())){
            throw new BusinessException("VendorReconciliation","运单处于承运侧对账中或者已对账完毕,不能再确认运费");
        }
        if( Integer.valueOf(Waybill.ReconciliationStatus.IN_THE_ACCOUNT.getCode()).equals(waybill.getReceivableReconcilicationStatus())
                || Integer.valueOf(Waybill.ReconciliationStatus.HAS_RECONCILIATION.getCode()).equals(waybill.getReceivableReconcilicationStatus())){
            throw new BusinessException("VendorReconciliation","运单处于客户侧对账中或者已对账完毕,不能再确认运费");
        }

        if(!projectService.isProjectManagerOrOperator(waybill.getProjectId(),loginEmployee.getUserId())
                && !authCommonService.isPermission(Constants.CITY_MANAGER_PERMISSION_KEY, loginEmployee)){
            throw new BusinessException("NotProjectManagerOrOperatorReconciliation","您不是此项目的项目经理或者运营专员,无法确认运费.");
        }

        // 用车时间N天前允许改价
        String timeLimit = fetchConstants(Constants.ALLOW_CHANGE_PRICE_TIME_LIMIT_KEY,Constants.ALLOW_CHANGE_PRICE_DEFAULT_TIME_LIMIT.toString());

        DateTime finishTime = com.juma.tgm.common.DateUtils.create(waybill.getFinishTime());
        // 当前时间大于运单完成时间+1天的23:59:59时,点击确认按钮提示：“当前已超过运费确认时间，如需调整运费请使用调整单”
        if( com.juma.tgm.common.DateUtils.getDayOfEnd(com.juma.tgm.common.DateUtils.addDays(finishTime,Integer.parseInt(timeLimit))).isBeforeNow() ){
            throw new BusinessException("ConfirmTimeout","当前已超过运费确认时间，如需调整运费请使用调整单");
        }
        // 已对账的运单,是否能确认运费?
        // 0≤客户侧含税金额≤30000
        // 用车时间N天前允许改价
        String ceilingAmount = fetchConstants(Constants.ALLOW_WAYBILL_CHANGE_PRICE_CEILING_LIMIT_KEY,Constants.ALLOW_WAYBILL_CHANGE_PRICE_CEILING_LIMIT.toString());
        if( BigDecimal.ZERO.compareTo(confirmWaybillAmountVO.getCustomerAmount()) > 0){
            throw new BusinessException("CustomerAmountOutOfRange","输入金额不能小于0，请重新输入");
        }
        if( new BigDecimal(ceilingAmount).compareTo(confirmWaybillAmountVO.getCustomerAmount()) < 0){
            throw new BusinessException("CustomerAmountOutOfRange","输入金额已超过单笔运单上限，请重新输入");
        }
        return waybill;
    }

    private String fetchConstants(String key, String defaultValue) {
        String timeLimit = defaultValue;
        List<ConfParamOption> options = authCommonService.listOption(key);
        for (ConfParamOption c : options) {
            if (StringUtils.isBlank(c.getOptionValue()) || !NumberUtils.isNumber(c.getOptionValue())) {
                continue;
            }

            timeLimit = c.getOptionValue();
        }
        return timeLimit;
    }

    private Double fetchPercentage(String key, String defaultValue) {
        List<ConfParamOption> options = authCommonService.listOption(key);
        NumberFormat nf= NumberFormat.getPercentInstance();
        for (ConfParamOption option : options) {
            String optionValue = option.getOptionValue();
            if (StringUtils.isBlank(optionValue)) {
                continue;
            }
            try {
                Number optionAmount = nf.parse(optionValue);
                return optionAmount.doubleValue();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            Number optionAmount = nf.parse(defaultValue);
            return optionAmount.doubleValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Double.valueOf(0.2);
    }

    private String formatPercentage(Double value){
        NumberFormat num = NumberFormat.getPercentInstance();
        num.setMaximumIntegerDigits(3);
        num.setMaximumFractionDigits(2);
        return num.format(value);
    }

    // 运单费用完善
    private void doCompleteWaybillAmount(Integer waybillId, BigDecimal customerFreightWithTax,
        BigDecimal vendorFreightWithTax, LoginUser loginUser) {
        WaybillAmount amount = new WaybillAmount();
        amount.setWaybillId(waybillId);
        amount.setCustomerFreightWithTax(customerFreightWithTax);
        amount.setVendorFreightWithTax(vendorFreightWithTax);
        amount.setLastCustomerFreightWithTax(customerFreightWithTax);
        amount.setLastVendorFreightWithTax(vendorFreightWithTax);
        waybillAmountService.addOrUpdate(amount, loginUser);
    }

    @Override
    public boolean checkCustomerPriceUpperLimit(BigDecimal customerFreight) {
        if (null == customerFreight) {
            return false;
        }

        List<ConfParamOption> options = authCommonService.listOption(Constants.TMS_FREIGHT_WITH_TAX_UPPER_LIMIT_KEY);
        for (ConfParamOption c : options) {
            if (StringUtils.isBlank(c.getOptionValue())) {
                continue;
            }

            if (customerFreight.compareTo(new BigDecimal(c.getOptionValue())) == 1) {
                return true;
            }
        }

        return false;
    }

    @Override
    public BigDecimal findUnReconciliationByVendorId(Integer vendorId) {
        if (null == vendorId) {
            return null;
        }

        return waybillDao.findUnReconciliationByVendorId(vendorId);
    }

    @Override
    public Waybill findByWaybillNo(String waybillNo) {
        if (StringUtils.isBlank(waybillNo)) {
            return null;
        }

        WaybillExample example = new WaybillExample();
        Criteria criteria = example.createCriteria();
        criteria.andWaybillNoEqualTo(waybillNo);

        List<Waybill> list = waybillDao.selectByExample(example);

        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public Boolean checkPlanDeliveryTimeInSameMonth(List<Waybill> waybills) {
        if(CollectionUtils.isEmpty(waybills)){
            return false;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(waybills.get(0).getPlanDeliveryTime());
        int firstYear = cal.get(Calendar.YEAR);
        int firstMonth = cal.get(Calendar.MONTH);
        for (Waybill waybill : waybills) {
            cal.setTime(waybill.getPlanDeliveryTime());
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            if(firstYear != year || firstMonth != month){
                return false;
            }
        }
        return true;
    }

    @Override
    public WaybillStatisticsAmountVO getMonthAmountInfo(Integer projectId, Date planDeliveryTime) {
        if(null == projectId || null == planDeliveryTime){
            return new WaybillStatisticsAmountVO();
        }

        Date beginDate = com.juma.tgm.common.DateUtils.getMonthOfBegin(com.juma.tgm.common.DateUtils.create(planDeliveryTime)).toDate();
        Date endDate = com.juma.tgm.common.DateUtils.getMonthOfEnd(com.juma.tgm.common.DateUtils.create(planDeliveryTime)).toDate();

        WaybillStatisticsAmountVO waybillStatisticsAmountVO = new WaybillStatisticsAmountVO();
        WaybillExample example = new WaybillExample();
        Criteria criteria = example.createCriteria();
        criteria.andProjectIdEqualTo(projectId)
                .andStatusViewEqualTo(Waybill.StatusView.FINISH.getCode())
                .andIsDeleteEqualTo(false)
                .andPlanDeliveryTimeGreaterThanOrEqualTo(beginDate)
                .andPlanDeliveryTimeLessThanOrEqualTo(endDate);
        List<Waybill> waybills = waybillDao.selectByExample(example);
        List<Integer> vendorIds = Lists.newArrayList();
        for (Waybill waybill : waybills) {
            vendorIds.add(waybill.getVehicleToVendor());
        }
        //获取所有的自营承运商
        Map<Integer,Byte> ownVendorMap = vmsCommonService.listVendorSource(vendorIds,VendorSourceEnum.SELF_SUPPORT.getCode());
        List<Integer> waybillIdList = Lists.newArrayList();
        for (Waybill waybill : waybills) {
            //排除自营承运商
            if(null != waybill.getVehicleToVendor()){
                if(null != ownVendorMap.get(waybill.getVehicleToVendor())){
                    continue;
                }
            }
            //排除自营司机
            if(null == waybill.getVehicleToVendor() && null != waybill.getDriverType()){
                if(Objects.equals(DriverTypeEnum.OWN_SALE.getCode(),waybill.getDriverType())){
                    continue;
                }
            }
            waybillIdList.add(waybill.getWaybillId());
        }

        BigDecimal monthVendorFreight = BigDecimal.ZERO;
        BigDecimal monthCustomerFreight = BigDecimal.ZERO;
        List<WaybillAmount> amounts = waybillAmountService.findByWaybillIds(waybillIdList, null);
        for (WaybillAmount amount : amounts) {
            monthVendorFreight = monthVendorFreight.add(amount.getLastVendorFreightWithTax());
            monthCustomerFreight = monthCustomerFreight.add(amount.getLastCustomerFreightWithTax());
        }

        //按月项目整体毛利额
        if(monthVendorFreight.compareTo(BigDecimal.ZERO) != 0 || monthCustomerFreight.compareTo(BigDecimal.ZERO) != 0){
            waybillStatisticsAmountVO.setMonthProfitAmount(monthCustomerFreight.subtract(monthVendorFreight));
        }
        //按月项目整体毛利率
        if(monthCustomerFreight.compareTo(BigDecimal.ZERO) != 0){
            waybillStatisticsAmountVO.setMonthProportion((monthCustomerFreight.subtract(monthVendorFreight)).divide(monthCustomerFreight, 4,BigDecimal.ROUND_HALF_UP));
        }
        //按月项目整体毛利率差额
        Project project = projectService.getProjectV2(projectId);
        BigDecimal profitRate = (null == project ? BigDecimal.ZERO : project.getProfitRate());
        if(null != waybillStatisticsAmountVO.getMonthProportion()){
            waybillStatisticsAmountVO.setMonthProportionGap(waybillStatisticsAmountVO.getMonthProportion().subtract(profitRate));
        }
        return waybillStatisticsAmountVO;
    }

    @Override
    public List<Integer> countWaybillStatus(List<Integer> projectIds, Date dailyDate) {
        return waybillDao.countWaybillStatus(projectIds, dailyDate, getCurrentDateLastTime(dailyDate));
    }

    @Override
    public List<Waybill> checkWayBillStatsByDate(Date dailyDate) {
        return waybillDao.checkWayBillStatsByDate(dailyDate, getCurrentDateLastTime(dailyDate));
    }


    /**
     * 获取日期最后时间点，yyyy-MM-dd 23:59:59
     * @param date 需要转换的时间，无值默认当前日期
     * @return
     */
    public static Date getCurrentDateLastTime(Date date){
        Calendar calendar = Calendar.getInstance();
        if(null == date){
            date = new Date();
        }
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }
}
