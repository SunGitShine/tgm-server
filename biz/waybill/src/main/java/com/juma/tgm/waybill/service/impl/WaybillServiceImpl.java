package com.juma.tgm.waybill.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.giants.cache.redis.RedisClient;
import com.giants.common.beanutils.BeanUtils;
import com.giants.common.codec.EncryptionDevice;
import com.giants.common.collections.CollectionUtils;
import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.juma.auth.conf.domain.BusinessArea;
import com.juma.auth.conf.service.BusinessAreaService;
import com.juma.auth.conf.service.EncryptionService;
import com.juma.auth.employee.domain.Department;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.employee.service.EmployeeService;
import com.juma.auth.tenant.domain.Tenant;
import com.juma.auth.tenant.service.TenantService;
import com.juma.auth.user.domain.LoginEcoUser;
import com.juma.auth.user.domain.LoginUser;
import com.juma.auth.user.domain.User;
import com.juma.auth.user.service.UserService;
import com.juma.conf.domain.ConfParamOption;
import com.juma.conf.service.ConfParamService;
import com.juma.crm.customer.domain.Customer4TmsBo;
import com.juma.crm.customer.domain.ProductsLableInfo;
import com.juma.crm.support.service.Crm4TmsService;
import com.juma.monitor.service.RealTimePositionService;
import com.juma.monitor.truck.domain.RealTimePosition;
import com.juma.tgm.base.domain.PageConditionDomain;
import com.juma.tgm.common.BaseUtil;
import com.juma.tgm.common.Constants;
import com.juma.tgm.common.DateUtil;
import com.juma.tgm.common.ManagerScheduleType;
import com.juma.tgm.common.RedisKeyConstants;
import com.juma.tgm.common.SQLUtil;
import com.juma.tgm.configure.domain.FreightFactor;
import com.juma.tgm.configure.service.FreightFactorService;
import com.juma.tgm.configure.service.PrivateFreightFactorService;
import com.juma.tgm.configure.service.ServiceConfService;
import com.juma.tgm.crm.domain.CustomerInfo;
import com.juma.tgm.crm.domain.YesterdayIncomeInfo;
import com.juma.tgm.crm.service.CustomerInfoService;
import com.juma.tgm.crm.service.IncomeStatisticsService;
import com.juma.tgm.customer.domain.TruckCustomer;
import com.juma.tgm.customerManager.service.ManagerScheduleService;
import com.juma.tgm.driver.domain.Driver;
import com.juma.tgm.driver.domain.DriverBaseInfo;
import com.juma.tgm.driver.service.DriverService;
import com.juma.tgm.fms.domain.v3.enums.AdjustMasterType;
import com.juma.tgm.fms.domain.v3.enums.AdjustType;
import com.juma.tgm.fms.domain.v3.vo.AdjustFreightVo;
import com.juma.tgm.fms.service.v3.AdjustForItemService;
import com.juma.tgm.mq.enumeration.WaybillOperateChangeEnum;
import com.juma.tgm.pay.domain.TransactionResponse;
import com.juma.tgm.project.domain.RoadMap;
import com.juma.tgm.project.domain.ValuationWay;
import com.juma.tgm.project.domain.v2.Project;
import com.juma.tgm.project.enumeration.ValuationWayEnum;
import com.juma.tgm.project.service.ProjectService;
import com.juma.tgm.project.service.RoadMapPriceRuleService;
import com.juma.tgm.project.service.RoadMapService;
import com.juma.tgm.project.vo.v2.ProjectVo;
import com.juma.tgm.receiptManage.domain.ReceiptManage;
import com.juma.tgm.receiptManage.service.ReceiptManageService;
import com.juma.tgm.redis.service.TemperatureAlertService;
import com.juma.tgm.region.service.RegionTgmService;
import com.juma.tgm.sop.domain.Sop;
import com.juma.tgm.sop.service.SopService;
import com.juma.tgm.task.service.TaskCalendarService;
import com.juma.tgm.task.service.TaskScheduledService;
import com.juma.tgm.tools.service.AmsCommonService;
import com.juma.tgm.tools.service.AuthCommonService;
import com.juma.tgm.tools.service.BigDataCommonService;
import com.juma.tgm.tools.service.BusinessAreaCommonService;
import com.juma.tgm.tools.service.CommonService;
import com.juma.tgm.tools.service.MessagePushService;
import com.juma.tgm.tools.service.VmsCommonService;
import com.juma.tgm.truck.domain.AdditionalFunction;
import com.juma.tgm.truck.domain.AdditionalFunctionFreight;
import com.juma.tgm.truck.domain.Truck;
import com.juma.tgm.truck.domain.TruckType;
import com.juma.tgm.truck.domain.TruckTypeFreight;
import com.juma.tgm.truck.domain.bo.DriverTruckInfoBo;
import com.juma.tgm.truck.service.AdditionalFunctionFreightService;
import com.juma.tgm.truck.service.AdditionalFunctionService;
import com.juma.tgm.truck.service.TruckService;
import com.juma.tgm.truck.service.TruckTypeFreightService;
import com.juma.tgm.truck.service.TruckTypeService;
import com.juma.tgm.user.domain.CurrentUser;
import com.juma.tgm.user.domain.UserRouteMaster;
import com.juma.tgm.util.CheckDriverIsEnableUtil;
import com.juma.tgm.util.CustomerManWaybillUtils;
import com.juma.tgm.util.CustomerManagerPerformanceUtil;
import com.juma.tgm.util.DriverArriveRedisUtils;
import com.juma.tgm.util.MqAfterCommitSendUtil;
import com.juma.tgm.waybill.dao.WaybillDao;
import com.juma.tgm.waybill.domain.AmountStatus;
import com.juma.tgm.waybill.domain.AppListFilter;
import com.juma.tgm.waybill.domain.CityAdressData;
import com.juma.tgm.waybill.domain.DistanceAndPriceData;
import com.juma.tgm.waybill.domain.FreightCompareRate;
import com.juma.tgm.waybill.domain.TaxRate;
import com.juma.tgm.waybill.domain.TruckRequire;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.Waybill.NeedReceipt;
import com.juma.tgm.waybill.domain.Waybill.Status;
import com.juma.tgm.waybill.domain.Waybill.StatusView;
import com.juma.tgm.waybill.domain.Waybill.WaybillSource;
import com.juma.tgm.waybill.domain.WaybillAmount;
import com.juma.tgm.waybill.domain.WaybillBindFence;
import com.juma.tgm.waybill.domain.WaybillBo;
import com.juma.tgm.waybill.domain.WaybillCountResponse;
import com.juma.tgm.waybill.domain.WaybillDeliveryAddress;
import com.juma.tgm.waybill.domain.WaybillDetailInfo;
import com.juma.tgm.waybill.domain.WaybillInfo;
import com.juma.tgm.waybill.domain.WaybillMonitor;
import com.juma.tgm.waybill.domain.WaybillNotice;
import com.juma.tgm.waybill.domain.WaybillOperateTrack;
import com.juma.tgm.waybill.domain.WaybillOwner;
import com.juma.tgm.waybill.domain.WaybillParam;
import com.juma.tgm.waybill.domain.WaybillReceiveAddress;
import com.juma.tgm.waybill.domain.WaybillReceiveAddressCargo;
import com.juma.tgm.waybill.domain.WaybillReceiveAddressResponse;
import com.juma.tgm.waybill.domain.WaybillVO;
import com.juma.tgm.waybill.domain.WaybillViewHistory;
import com.juma.tgm.waybill.domain.bo.PrePaySign;
import com.juma.tgm.waybill.domain.bo.VendorBo;
import com.juma.tgm.waybill.domain.drools.PriceProxy;
import com.juma.tgm.waybill.domain.example.WaybillExample;
import com.juma.tgm.waybill.domain.view.SumForWaybill;
import com.juma.tgm.waybill.domain.view.WaybillViewVo;
import com.juma.tgm.waybill.domain.vo.ChangeCarVo;
import com.juma.tgm.waybill.domain.vo.CustomerManagerDebtDetailVo;
import com.juma.tgm.waybill.domain.vo.CustomerManagerDebtOverviewVo;
import com.juma.tgm.waybill.domain.vo.CustomerManagerDebtVo;
import com.juma.tgm.waybill.domain.vo.CustomerPerformanceVo;
import com.juma.tgm.waybill.domain.vo.DistanceAndPriceParamVo;
import com.juma.tgm.waybill.domain.vo.WaybillAmountFilter;
import com.juma.tgm.waybill.enumeration.WaybillOperateTrackEnum.OperateApplication;
import com.juma.tgm.waybill.enumeration.WaybillOperateTrackEnum.OperateType;
import com.juma.tgm.waybill.service.BuildVehicleTypeAndVendorService;
import com.juma.tgm.waybill.service.DeliveryPointSupplementService;
import com.juma.tgm.waybill.service.FreightCompareRateService;
import com.juma.tgm.waybill.service.GaoDeMapService;
import com.juma.tgm.waybill.service.MsgWithBusinessService;
import com.juma.tgm.waybill.service.TaxRateService;
import com.juma.tgm.waybill.service.TruckRequireService;
import com.juma.tgm.waybill.service.UserRouteBusinessService;
import com.juma.tgm.waybill.service.WaybillAmountService;
import com.juma.tgm.waybill.service.WaybillAutoFenceServicve;
import com.juma.tgm.waybill.service.WaybillBindFenceService;
import com.juma.tgm.waybill.service.WaybillCheckService;
import com.juma.tgm.waybill.service.WaybillCommonService;
import com.juma.tgm.waybill.service.WaybillDeliveryAddressService;
import com.juma.tgm.waybill.service.WaybillNoticeService;
import com.juma.tgm.waybill.service.WaybillOperateTrackService;
import com.juma.tgm.waybill.service.WaybillParamService;
import com.juma.tgm.waybill.service.WaybillReceiveAddressCargoService;
import com.juma.tgm.waybill.service.WaybillReceiveAddressService;
import com.juma.tgm.waybill.service.WaybillService;
import com.juma.tgm.waybill.service.WaybillTrackService;
import com.juma.tgm.waybill.service.WaybillTransformToCarrierService;
import com.juma.tgm.waybill.service.WaybillViewHistoryService;
import com.juma.tgm.waybill.service.WaybillViewService;
import com.juma.tgm.waybill.vo.WaybillArriveDoptTimeLimit;
import com.juma.tgm.waybill.vo.WaybillCostInformationVo;
import com.juma.tgm.waybill.vo.WaybillDriverVo;
import com.juma.tgm.waybill.vo.WaybillFilter;
import com.juma.tgm.waybill.vo.WaybillQuery;
import com.juma.vms.driver.domain.DriverTenant;
import com.juma.vms.driver.enumeration.DriverTypeEnum;
import com.juma.vms.external.service.VmsService;
import com.juma.vms.transport.domain.CapacityPool;
import com.juma.vms.vendor.domain.Vendor;
import com.juma.vms.vendor.external.VendorTenantExternal;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.kie.api.cdi.KSession;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class WaybillServiceImpl implements WaybillService {

    private static Logger log = LoggerFactory.getLogger(WaybillServiceImpl.class);

    /**
     * 用于事务回滚导致的无效班次占用问题
     */
    private static ThreadLocal<Integer> flightUsageThreadTemp = new ThreadLocal<>();

    private final static Integer MAX_SPLIT_NUM = 10000;

    @Autowired
    private WaybillDao waybillDao;
    @Autowired
    private WaybillDeliveryAddressService waybillDeliveryAddressService;
    @Autowired
    private WaybillReceiveAddressService waybillReceiveAddressService;
    @Autowired
    private WaybillReceiveAddressCargoService waybillReceiveAddressCargoService;
    @Autowired
    private AdditionalFunctionService additionalFunctionService;
    @Autowired
    private TruckTypeFreightService truckTypeFreightService;
    @Autowired
    private DriverService driverService;
    @Autowired
    private TruckService truckService;
    @Autowired
    private TruckTypeService truckTypeService;
    @Autowired
    private GaoDeMapService gaoDeMapService;
    @Autowired
    private MessagePushService messagePushService;
    @Autowired
    private TruckRequireService truckRequireService;
    @Resource
    private EmployeeService employeeService;
    @Autowired
    private FreightCompareRateService freightCompareRateService;
    @Resource
    private WaybillOperateTrackService waybillOperateTrackService;
    @Resource
    private IncomeStatisticsService incomeStatisticsService;
    @Resource
    private CustomerInfoService customerInfoService;
    @Autowired
    private RedisClient redisClient;
    @KSession("ksession")
    private KieSession kSession;
    @Resource
    private EncryptionService encryptionService;
    @Resource
    private UserService userService;
    @Resource
    private WaybillParamService waybillParamService;
    @Resource
    private WaybillAutoFenceServicve waybillAutoFenceServicve;
    @Resource
    private WaybillBindFenceService waybillBindFenceService;
    @Resource
    private RegionTgmService regionTgmService;
    @Resource
    private TaxRateService taxRateService;
    @Resource
    private ReceiptManageService receiptManageService;
    @Value("${dataBase.name}")
    private String dataBaseName;
    @Resource
    private WaybillNoticeService waybillNoticeService;
    @Resource
    private WaybillViewHistoryService waybillViewHistoryService;
    @Resource
    private AdditionalFunctionFreightService additionalFunctionFreightService;
    @Resource
    private ConfParamService confParamService;
    @Resource
    private RoadMapPriceRuleService roadMapPriceRuleService;
    @Resource
	private VmsService vmsService;
    @Resource
    private Crm4TmsService crm4TmsService;
    @Resource
    private AuthCommonService authCommonService;
    @Resource
    private RoadMapService roadMapService;

    /**
     * 用于处理客户经理订单超时通知
     */
    @Resource
    private CustomerManWaybillUtils customerManWaybillUtils;
    /**
     * 带业务逻辑的发送消息
     */
    @Resource
    private MsgWithBusinessService msgWithBusinessService;

    @Resource
    private CommonService commonService;

    @Resource
    private DeliveryPointSupplementService deliveryPointSupplementService;

    /**
     * 保存用户常用路线
     */
    @Resource
    private UserRouteBusinessService userRouteBusinessService;

    @Resource
    private BusinessAreaService businessAreaService;

    @Resource
    private ManagerScheduleService managerScheduleService;

    @Resource
    private RealTimePositionService realTimePositionService;

    @Resource
    private BusinessAreaCommonService businessAreaCommonService;

    @Resource
    private FreightFactorService freightFactorService;
    @Resource
    private WaybillTrackService waybillTrackService;

    @Resource
    private SopService sopService;

    /**
     * 客户经理业绩
     */
    @Resource
    private CustomerManagerPerformanceUtil customerManagerPerformanceUtil;

    @Resource
    private WaybillCommonService waybillCommonService;

    @Resource
    private ServiceConfService serviceConfService;

    @Resource
    private WaybillViewService waybillViewService;

    @Resource
    private TemperatureAlertService temperatureAlertService;

    @Resource
    private PrivateFreightFactorService privateFreightFactorService;

    @Resource
    private TenantService tenantService;

    @Resource
    private DriverArriveRedisUtils driverArriveRedisUtils;

    @Resource
    private WaybillTransformToCarrierService waybillTransformToCarrierService;

    @Resource
    private AmsCommonService amsCommonService;

    @Resource
    private BuildVehicleTypeAndVendorService buildVehicleTypeAndVendorService;

    @Resource
    private WaybillCheckService waybillCheckService;

    @Resource
    private ProjectService projectService;

    @Resource
    private VmsCommonService vmsCommonService;
    @Resource
    private CheckDriverIsEnableUtil checkDriverIsEnableUtil;
    @Resource
    private WaybillAmountService waybillAmountService;
    @Resource
    private AdjustForItemService adjustForItemService;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    @Autowired
    private BigDataCommonService bigDataCommonService;
    @Value("${ops.id.waybill.amount}")
    private String opsIdWaybillAmount;

    @Resource
    private TaskCalendarService taskCalendarService;

    @Resource
    private TaskScheduledService taskScheduledService;
    @Resource
    private MqAfterCommitSendUtil mqAfterCommitSendUtil;

    private void update(Waybill waybill, LoginUser loginUser) {
        waybillCommonService.update(waybill, loginUser);
    }

    @Override
    public Page<Waybill> search(LoginUser loginUser, PageCondition pageCondition) throws BusinessException {
        if (null == loginUser ) {
            return new Page<Waybill>(pageCondition.getPageNo(), pageCondition.getPageSize(), 0, new ArrayList<Waybill>());
        }
        Map<String, Object> filters = pageCondition.getFilters();
        filters.put("tenantId", loginUser.getTenantId());
        buildParam(pageCondition, loginUser);
        List<Waybill> result = new ArrayList<>();
        int count = waybillDao.searchCount(pageCondition);
        List<Waybill> rows = waybillDao.search(pageCondition);
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

            WaybillAmount waybillAmount = waybillAmountService.loadByWaybillId(waybill.getWaybillId());
            // 客户侧最终含税金额
            waybill.setLastCustomerFreightWithTax(
                    waybillAmount == null ? (null == waybill.getEstimateFreight() ? BigDecimal.ZERO : waybill.getEstimateFreight())
                            : waybillAmount.getLastCustomerFreightWithTax());

            // 承运侧最终含税金额
            waybill.setLastVendorFreightWithTax(
                    waybillAmount == null ? waybill.getShow4DriverFreight() : waybillAmount.getLastVendorFreightWithTax());

            if(null == waybill.getEstimateFreight()){
                waybill.setEstimateFreight(BigDecimal.ZERO);
            }

            result.add(waybill);
        }
        return new Page<Waybill>(pageCondition.getPageNo(), pageCondition.getPageSize(), count, result);
    }

    @Override
    public Page<Waybill> searchBasicInfo(LoginUser loginUser, PageCondition pageCondition) throws BusinessException {
        if (null == loginUser) {
            return new Page<>(pageCondition.getPageNo(), pageCondition.getPageSize(), 0, new ArrayList<Waybill>());
        }
        Map<String, Object> filters = pageCondition.getFilters();
        filters.put("tenantId", loginUser.getTenantId());
        buildParam(pageCondition, loginUser);
        int count = waybillDao.searchCount(pageCondition);
        List<Waybill> rows = waybillDao.search(pageCondition);
        return new Page<>(pageCondition.getPageNo(), pageCondition.getPageSize(), count, rows);
    }

    @Override
    public Page<WaybillVO> searchForManageSys(LoginUser loginUser, PageCondition pageCondition) throws BusinessException {
        Page<Waybill> page = this.search(loginUser, pageCondition);
        Map<Integer, WaybillAmount> amountMap = fetchWaybillAmount(page.getResults(), loginUser);
        List<WaybillVO> result = new ArrayList<>();
        for (Waybill waybill : page.getResults()) {
            WaybillVO waybillVO = new WaybillVO();
            WaybillAmount amount = amountMap.get(waybill.getWaybillId());
            if( null != amount ){
                //【运费确认】已超时、派车中、待配送、配送中、已取消状态的运单，运费状态请显示为空
                List<Integer> statusViews = Lists.newArrayList(
                        Integer.valueOf(StatusView.HAS_TIMED_OUT.getCode()),
                        Integer.valueOf(StatusView.WATING_RECEIVE.getCode()),
                        Integer.valueOf(StatusView.WATING_DELIVERY.getCode()),
                        Integer.valueOf(StatusView.DELIVERYING.getCode()),
                        Integer.valueOf(StatusView.CANCEL.getCode())
                );
                if( !statusViews.contains(waybill.getStatusView()) ){
                    waybillVO.setAmountStatus(amount.getAmountStatus());
                    waybillVO.setAmountStatusDesc(AmountStatus.getByCode(amount.getAmountStatus()));
                }
            }
            org.springframework.beans.BeanUtils.copyProperties(waybill,waybillVO);

            // 业务区域
            waybillVO.setAreaName(businessAreaCommonService.loadAreaName(waybill.getAreaCode(), loginUser));
            // 承运商名称
            if (null != waybill.getReceiveWay() && waybill.getReceiveWay().equals(Waybill.ReceiveWay.TRANSFORM_BILL.getCode())) {
                Vendor vendor = vmsCommonService.loadVendorByVendorId(waybill.getVendorId());
                if( null != vendor ){
                    waybillVO.setVendorName(vendor.getVendorName());
                    if( null != vendor.getVendorSource() ){
                        waybillVO.setVendorSource(vendor.getVendorSource().intValue());
                    }
                }
            }else{
                Vendor vendor = vmsCommonService.loadVendorByVendorId(waybill.getVehicleToVendor());
                if( null != vendor ){
                    waybillVO.setVendorName(vendor.getVendorName());
                    if( null != vendor.getVendorSource() ){
                        waybillVO.setVendorSource(vendor.getVendorSource().intValue());
                    }
                }
            }
            result.add(waybillVO);
        }
        return new Page<WaybillVO>(pageCondition.getPageNo(), pageCondition.getPageSize(), page.getTotal(), result);
    }

    /**根据运单获取运费确认状态**/
    private Map<Integer, WaybillAmount> fetchWaybillAmount(Collection<Waybill> rows, LoginUser loginUser) {
        List<Integer> waybillIds = Lists.newArrayList();
        for (Waybill waybill : rows) {
            waybillIds.add(waybill.getWaybillId());
        }
        Map<Integer,WaybillAmount> amountMap = Maps.newConcurrentMap();
        if( CollectionUtils.isEmpty(waybillIds) ){ return amountMap; }
        WaybillAmountFilter filter = new WaybillAmountFilter();
        filter.setWaybillIds(waybillIds);
        List<WaybillAmount> amounts = waybillAmountService.findByFilter(filter,loginUser);
        for (WaybillAmount amount : amounts){
            amountMap.put(amount.getWaybillId(),amount);
        }
        return amountMap;
    }

    @Override
    public WaybillBo getWaybillBoById(Integer waybillId, LoginUser loginUser) {
        Waybill waybill = getWaybill(waybillId);
        if (null == waybill) {
            return null;
        }

        // 判断是不是转运单，若是转运单则获取车辆司机信息
        if (NumberUtils.compare(waybill.getReceiveWay(), Waybill.ReceiveWay.TRANSFORM_BILL.getCode()) == 0) {
            WaybillParam waybillParam = waybillParamService.findByWaybillId(waybillId);
            if (null != waybillParam && null != waybillParam.getTransformBillLinkId()) {
                Waybill vendorWaybill = waybillCommonService.getWaybillById(waybillParam.getTransformBillLinkId());
                if (null != vendorWaybill) {
                    waybill.setTruckId(vendorWaybill.getTruckId());
                    waybill.setDriverId(vendorWaybill.getDriverId());
                    waybill.setDriverName(vendorWaybill.getDriverName());
                }
            }
        }

        // 业务区域
        waybill.setAreaName(businessAreaCommonService.loadPreAndSelfAreaName(waybill.getAreaCode(), loginUser));


        // 当前行驶距离
        try {
            this.getWaybillTraceInfo(waybill);
        } catch (Exception e) {
            log.warn("获取实际运输距离错误.", e);
        }

        WaybillBo waybillBo = transforWaybillBo(waybill, loginUser);
        // 回单图片
        waybillBo.setReceiptImageList(receiptManageService.listReceiptImageByWaybillId(waybillId));
        // 取货地
        waybillBo.setDeliveryAddress(waybillDeliveryAddressService.findAllByWaybillId(waybillId));
        // 目的地
        waybillBo.setReceiveAddress(waybillReceiveAddressService.findAllByWaybillId(waybillId));
        // 配送单
        waybillBo.setDeliveryPointSupplementList(deliveryPointSupplementService.getByWayBill(waybillId));
        return waybillBo;
    }

    private WaybillBo transforWaybillBo(Waybill waybill, LoginUser loginUser) {
        WaybillBo bo = new WaybillBo();
        if (null != waybill) {
            bo.setWaybill(waybill);
            bo.setCustomerServiceTel(serviceConfService.findCustomerServiceTel(waybill.getRegionCode(), loginUser));
            bo.setReceiptTypeStr(Waybill.ReceiptType.getPayWayStr(waybill.getReceiptType()));
            Integer waybillId = waybill.getWaybillId();
            // 获取发单人是客户经理的用户信息
            // bo.setManageCustomer(findCustomerManger(waybill, loginUser));
            buildUseDate(waybill);
            Date cmEstimateFinishTime = waybill.getCmEstimateFinishTime();
            if (cmEstimateFinishTime != null) {
                bo.setEstimateFinishTime(buildEstimeFinishTime(waybill));
            }
            // 运单能不能取消
            bo.getWaybill().setAllowCancel(allowCancelForBackPersonnel(waybill.getStatusView()));
            // 获取司机信息
            bo.setDriver(driverService.getDriver(waybill.getDriverId()));
            // 根据货车ID获取货车
            Truck truck = truckService.getTruck(waybill.getTruckId());
            if (null != truck) {
                bo.setTruck(truck);
                // 获取车辆信息
                bo.setTruckInfo(truckService.findTruckInfoStrByTruckId(truck.getTruckId(), loginUser));
            }
            // 用车要求信息
            TruckRequire truckRequire = truckRequireService.findTruckRequireByWaybillId(waybillId, loginUser);
            if (null != truckRequire) {
                // 获取用车要求全称构造
                bo.setTruckRequireStr(truckRequireService.getTruckRequire(truckRequire, null));
                // 获取车型信息
                bo.setTruckTypeName(truckTypeService.findTruckTypeNameByTypeId(truckRequire.getTruckTypeId()));
                bo.setGoodsInfoStr(truckRequireService.getGoodsFullName(truckRequire, null));
                buildTruckRequire(bo, truckRequire);
            }
            buildCustomerInfo(bo, loginUser);
            // 邀请人
            bo.setLowerCalculatedFreight(buildCompareRate(waybill));
            buildWaybillParam(bo, waybillId);
            bo.setAllowAddHandlingCost(allowAddHandlingCost(waybill));
        }
        return bo;
    }

    private TruckCustomer findCustomerManger(Waybill waybill, LoginUser loginUser) {
        loginUser.setTenantId(waybill.getTenantId());// 运单的租户
        WaybillOwner owner = commonService.findCustomerManager(waybill.getCustomerManagerId(), loginUser);
        TruckCustomer manageCustomer = new TruckCustomer();
        manageCustomer.setNickname(owner.getNickname());
        manageCustomer.setContactPhone(owner.getContactPhone());
        manageCustomer.setUserId(owner.getUserId());
        return manageCustomer;
    }

    /**
     * 运单池数量
     */
    @Override
    public int getAcceptableWaybillCount(PageCondition pageCondition, LoginUser loginUser) throws BusinessException {
        return waybillDao.searchCount(buildAcceptableCondition(pageCondition, loginUser));
    }

    /**
     * 构造运单池条件
     */
    private PageCondition buildAcceptableCondition(PageCondition pageCondition, LoginUser loginUser) throws BusinessException {
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

            DriverTenant driverTenant = vmsCommonService.loadDriverTenantByDriverId(driver.getDriverId(), loginUser);
            if (null == driverTenant) {
                areaCodeList.add("-999");
                filters.put("areaCodeList", areaCodeList);
                return pageCondition;
            }

            log.info("amsDriver areacode :{}.", driverTenant.getAreaCode());
            BusinessArea businessArea = businessAreaService.loadLogicBusinessArea(driverTenant.getAreaCode(), loginUser);
            if (businessArea != null) {
                areaCodeList.add(businessArea.getAreaCode());
            }
            if (areaCodeList.isEmpty()) {
                areaCodeList.add("-999");
            }
            filters.put("areaCodeList", areaCodeList);
        } else {
            List<AppListFilter> appListFilter = new ArrayList<AppListFilter>();

            if (null == driver) {
                buildEmptyListCondition(pageCondition, appListFilter);
                return pageCondition;
            }

            List<DriverTenant> driverTenants = vmsCommonService.listDriverTenantByDriverId(driver.getDriverId());

            log.info("vmsDriver tenant :{}.", StringUtils.join(driverTenants, ","));

            if (driverTenants == null || driverTenants.isEmpty()) {
                buildEmptyListCondition(pageCondition, appListFilter);
                return pageCondition;
            }

            for (DriverTenant driverTenantVO : driverTenants) {
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

        com.juma.vms.truck.domain.Truck truck = vmsCommonService.loadTruckByDriverId(driver.getDriverId(), loginUser);
        // 按入城证区分
        if (truck != null) {
            Integer entryLicense = 0;
            if (null != truck.getGoCityLicenseType()) {
                entryLicense = truck.getGoCityLicenseType().intValue();
            }

            if (entryLicense != 1) {
                // 车辆非A1入城证
                // 那就只能看不要入城证的单子
                // 因为用车的时候选入城证是是否的关系
                filters.put("entryLicense", 0);
            }
            filters.put("vehicleBoxType", truck.getVehicleBoxType());

            // 货物重量
//            filters.put("goodsWeight", vehicle.getMaxLoadCapacity() == null ? -1 : vehicle.getMaxLoadCapacity());

            // 货物体积
            // pageCondition.put("goodsVolume", vehicle.getLoadVolume());
        }

        return pageCondition;
    }

    private void buildEmptyListCondition(PageCondition pageCondition, List<AppListFilter> appListFilter) {
        AppListFilter filter = new AppListFilter(1000, -999 + "");
        appListFilter.add(filter);
        pageCondition.getFilters().put("appListFilter", appListFilter);
    }

    /**
     * 运单池(待接单)
     */
    @Override
    public Page<WaybillBo> getPageForAcceptableWaybillList(PageCondition pageCondition, LoginUser loginUser)
        throws BusinessException {
        pageCondition = buildAcceptableCondition(pageCondition, loginUser);
        pageCondition.setOrderBy("status_view asc,plan_delivery_time desc");
        int count = waybillDao.searchCount(pageCondition);
        List<Waybill> rows = waybillDao.search(pageCondition);
        List<WaybillBo> out = transfer(rows, loginUser);
        return new Page<WaybillBo>(pageCondition.getPageNo(), pageCondition.getPageSize(), count, out);
    }

    @Override
    public Page<WaybillDetailInfo> searchPageList(PageCondition pageCondition, LoginEmployee loginEmployee) {
        pageCondition.setOrderBy("(if(C.statusView = -2, 2, C.statusView)) asc,C.planDeliveryTime desc");// -2当做2处理
        int count = waybillViewService.searchForAppCount(pageCondition);
        List<WaybillViewVo> rows = waybillViewService.searchForApp(pageCondition);

        List<WaybillDetailInfo> results = new ArrayList<WaybillDetailInfo>();
        //所有物流产品标签
        Map<String, Object> logisticsProductMap = new HashMap<>();
        Customer4TmsBo customer4TmsBo = crm4TmsService.findProductAndDepId(null, loginEmployee);
        if(customer4TmsBo != null){
            logisticsProductMap = customer4TmsBo.getLogisticsProducts();
        }
        for (WaybillViewVo waybill : rows) {
            WaybillDetailInfo info = new WaybillDetailInfo();
            //物流产品标签名称
            Object logisticsNameOb = logisticsProductMap.get(waybill.getLogisticsLabel());
            waybill.setLogisticsName(logisticsNameOb == null ? "" : (String)logisticsNameOb);
            info.setWaybill(waybill);
            Integer waybillId = waybill.getWaybillId();

            TruckRequire truckRequire = truckRequireService.findTruckRequireByWaybillId(waybillId, loginEmployee);
            if(truckRequire != null){
                waybill.setTruckRequireId(truckRequire.getTruckRequireId());
                waybill.setTruckTypeId(truckRequire.getTruckTypeId());
                waybill.setAdditionalFunctionIds(truckRequire.getAdditionalFunctionIds());
            }

            Integer carsNum = 0;
            WaybillNotice waybillNotice = waybillNoticeService.findBy(waybillId);
            if (waybillNotice != null) {
                carsNum = waybillNotice.getNoticeDriverNum() * 3 + 5;
                info.setDriverNotice(carsNum);
            }

            info.setColdChain(truckRequireService.isColdChain(null, waybillId) ? 1 : 0);
            info.setWaybillDeliveryAddresses(waybillDeliveryAddressService.findAllByWaybillId(waybillId));
            info.setWaybillReceiveAddresses(waybillReceiveAddressService.findAllByWaybillId(waybillId));
            info.setExpireTimeLength(customerManWaybillUtils.getExpireTimeLength());
            info.setCurrentLoginUserId(loginEmployee.getUserId());

            Driver driver = driverService.getDriver(waybill.getDriverId());
            DriverTruckInfoBo driverTruckInfoBo = new DriverTruckInfoBo(carsNum, driver);
            info.setDriverTruckInfoBo(driverTruckInfoBo);
            //当运单类型为转运时组装司机和承运商信息
            if (waybill.getReceiveWay().equals(Waybill.ReceiveWay.TRANSFORM_BILL.getCode())
                    && waybill.getVendorId() != null
                    // 订单状态 必须是待配送以上的
                    && waybill.getStatusView() >= Waybill.StatusView.WATING_DELIVERY.getCode()) {
                setDriverAndTransformData(waybillId, info);
            }
            //由谁填写工作量
            if(waybill.getRoadMapId() == null || waybill.getRoadMapId() == 0){//老数据默认司机填写工作量
                info.setWhoWriteWork(1);
            }else{
                WaybillParam waybillParam = waybillParamService.findByWaybillId(waybillId);
                if(waybillParam != null && StringUtils.isNotBlank(waybillParam.getProjectFreightRuleJson())) {
                    RoadMap roadMap = JSON.parseObject(waybillParam.getProjectFreightRuleJson(), RoadMap.class);
                    info.setWhoWriteWork(roadMap == null || roadMap.getWhoWriteWork() == null ? 1 : roadMap.getWhoWriteWork().intValue());
                    //info.setWhoWriteWork(roadMapService.getRoadMap(waybill.getRoadMapId()).getWhoWriteWork().intValue());
                }

            }
            results.add(info);

            // 兼容项目运单信息
            if (waybill.getProjectId() != null) {
                this.supportProjectBillInfo(waybill, driverTruckInfoBo, loginEmployee);
            }
        }
        return new Page<WaybillDetailInfo>(pageCondition.getPageNo(), pageCondition.getPageSize(), count, results);
    }

    private void supportProjectBillInfo(Waybill waybill, DriverTruckInfoBo driverTruckInfoBo, LoginEmployee loginEmployee) {
        Truck truck = truckService.getTruck(waybill.getTruckId());
        if (truck != null) {
            driverTruckInfoBo.setPlateNumber(truck.getPlateNumber());
            try {
                driverTruckInfoBo.setTruckInfo(truckService.findTruckInfoStrByTruckId(truck.getTruckId(), loginEmployee));
            } catch (Exception e) {
                log.warn("运单列表错误:{}", waybill.getWaybillId(), e);
            }
        }
    }

    @Override
    public Page<WaybillDetailInfo> searchWechatPageList(PageCondition pageCondition, LoginEcoUser loginEcoUser) {
        int count = waybillDao.searchCount(pageCondition);
        List<Waybill> rows = waybillDao.search(pageCondition);
        List<WaybillDetailInfo> results = new ArrayList<WaybillDetailInfo>();
        for (Waybill waybill : rows) {

            Integer carsNum = 0;
            WaybillDetailInfo info = new WaybillDetailInfo();
            WaybillNotice waybillNotice = waybillNoticeService.findBy(waybill.getWaybillId());
            if (waybillNotice != null) {
                carsNum = waybillNotice.getNoticeDriverNum() * 3 + 5;
                info.setDriverNotice(carsNum);
            }
            info.setWaybill(waybill);
            Integer driverId = waybill.getDriverId();
            Driver driver = driverService.getDriver(driverId);
            DriverTruckInfoBo driverTruckInfoBo = new DriverTruckInfoBo(carsNum, driver);
            Truck truck = truckService.getTruck(waybill.getTruckId());
            if (null != truck) {
                info.setTruckTypeName(
                    truckTypeService.findTruckTypeNameBy(truck.getVehicleBoxType(), truck.getVehicleBoxLength()));
                info.setTruck(truck);
            }
            info.setDriverTruckInfoBo(driverTruckInfoBo);
            info.setGoodsInfoStr(truckRequireService.getGoodsFullName(null, waybill.getWaybillId()));
            results.add(info);
        }
        return new Page<WaybillDetailInfo>(pageCondition.getPageNo(), pageCondition.getPageSize(), count, results);
    }

    /**
     * 任务列表
     */
    @Override
    public Page<WaybillBo> getPageForTodoWaybillList(PageCondition pageCondition, LoginUser loginUser)
        throws BusinessException {
        if (null == pageCondition.getOrderBy()) {
            pageCondition.setOrderBy("status_view asc,plan_delivery_time desc");
        }
        if (null == loginUser || null == loginUser.getTenantId()) {
            return new Page<WaybillBo>(pageCondition.getPageNo(), pageCondition.getPageSize(), 0,
                new ArrayList<WaybillBo>());
        }
        // pageCondition.getFilters().put("tenantId", loginUser.getTenantId());
        return getWaybillList(pageCondition, loginUser);
    }

    private Page<WaybillBo> getWaybillList(PageCondition pageCondition, LoginUser loginUser) {

        int count = waybillDao.searchCount(pageCondition);
        List<Waybill> rows = waybillDao.search(pageCondition);
        List<WaybillBo> out = transfer(rows, loginUser);
        return new Page<WaybillBo>(pageCondition.getPageNo(), pageCondition.getPageSize(), count, out);
    }

    private List<WaybillBo> transfer(List<Waybill> rows, LoginUser loginUser) {
        List<WaybillBo> out = new ArrayList<WaybillBo>();
        for (Waybill row : rows) {
            WaybillBo bo = new WaybillBo();
            WaybillParam waybillParam = waybillParamService.findByWaybillId(row.getWaybillId());
            if (waybillParam != null && StringUtils.isNotBlank(waybillParam.getProjectFreightRuleJson())) {
                row.setValuationWays(
                    transfer(waybillParam.getProjectFreightRuleJson(), waybillParam.getValuationConstJson()));
            }

            // 路线名称
            RoadMap roadMap = roadMapService.getRoadMap(row.getRoadMapId());
            row.setRoadMapName(roadMap == null ? null : roadMap.getName());
            try {
                BeanUtils.copyProperties(bo, row);
            } catch (IllegalAccessException e) {
                log.warn(e.getMessage(), e);
            } catch (InvocationTargetException e) {
                log.warn(e.getMessage(), e);
            }

            bo.setAsProjectWaybillHandle(this.checkAsProjectWaybill(row));
            bo.setCustomerServiceTel(serviceConfService.findCustomerServiceTel(row.getRegionCode(), loginUser));
            bo.setCoordinates(waybillCommonService.truckLocation(row.getPlateNumber()));
            bo.setEstimateFinishTime(buildEstimeFinishTime(row));
            bo.setPlanDeliveryDate(DateUtil.buildDateFormat(row.getPlanDeliveryTime()));
            bo.setImportantInfoStr(truckRequireService.getImportantInfo(row.getWaybillId()));
            bo.setDeliveryAddress(waybillDeliveryAddressService.findAllByWaybillId(row.getWaybillId()));
            bo.setReceiveAddress(waybillReceiveAddressService.findAllByWaybillId(row.getWaybillId()));
            bo.setTruckCustomer(findCustomerManger(row, loginUser));

            TruckRequire truckRequire = truckRequireService.findTruckRequireByWaybillId(row.getWaybillId(), loginUser);
            bo.setTruckRequire(truckRequire);
            bo.setTruckRequireStr(truckRequireService.getTruckRequireString(truckRequire, new StringBuffer("")));
            bo.setGoodsInfoStr(truckRequireService.getGoodsFullName(truckRequire, null));
			loginUser.setTenantId(row.getTenantId());//跟随运单租户
            bo.setShowYourPrice(truckService.isShowYourPrice(row.getTruckId(), loginUser));
            bo.setProjectVo(projectService.getProjectVo(row.getProjectId(), loginUser));

            out.add(bo);
        }
        return out;
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

    private Date buildEstimeFinishTime(Waybill row) {
        Integer estimateTimeConsumption = row.getEstimateTimeConsumption() == null ? 0
            : row.getEstimateTimeConsumption();
        return new Date(row.getPlanDeliveryTime().getTime() + estimateTimeConsumption * 60 * 1000l);
    }

    /**
     * 待重构
     */
    private WaybillBo transferToBo(Waybill row, LoginUser loginUser) {
        WaybillBo bo = new WaybillBo();
        try {
            BeanUtils.copyProperties(bo, row);
        } catch (IllegalAccessException e) {
            log.warn(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            log.warn(e.getMessage(), e);
        }
        log.info("ValuationWayView 2 is {}.", row.getValuationWayView());

        // 转运单需要根据原单确定司机端是否填写工作量
        if (NumberUtils.compare(row.getWaybillSource(), Waybill.WaybillSource.TRANSFORM_BILL.getCode()) == 0) {
            WaybillParam transformBillParam = waybillParamService.findByTransformBillLinkId(row.getWaybillId());
            if (null != transformBillParam) {
                Waybill transformBill = this.getWaybill(transformBillParam.getWaybillId());
                if (null != transformBill && null != transformBill.getProjectId()) {
                    bo.setValuationWays(transformBill.getValuationWays());
                    bo.setWhoWriteWork(waybillCheckService.loadWhoWriteWork(row.getWaybillId()));
                    bo.setCompleteWorkload(waybillCheckService.checkProjectIsWorkload(transformBill.getWaybillId())
                            && waybillCheckService.checkIsDriverWriteWork(row.getWaybillId()));
                }
            }
        } else {
            if (null != row.getProjectId()) {
                bo.setWhoWriteWork(waybillCheckService.loadWhoWriteWork(row.getWaybillId()));
                bo.setCompleteWorkload(waybillCheckService.checkProjectIsWorkload(row.getWaybillId())
                        && waybillCheckService.checkIsDriverWriteWork(row.getWaybillId()));
            }
        }

        bo.setAsProjectWaybillHandle(this.checkAsProjectWaybill(row));
        bo.setValuationWayView(row.getValuationWayView());
        // 落地配
        bo.setCustomerServiceTel(serviceConfService.findCustomerServiceTel(row.getRegionCode(), loginUser));

        Integer waybillId = row.getWaybillId();

        TruckRequire truckRequire = truckRequireService.findTruckRequireByWaybillId(waybillId, loginUser);
        if (null != truckRequire) {
            bo.setTruckRequireStr(truckRequireService.getTruckRequire(truckRequire, null));
            bo.setTruckRequire(truckRequire);
            bo.setGoodsInfoStr(truckRequireService.getGoodsFullName(truckRequire, null));
            bo.setTruckTypeName(truckTypeService.findTruckTypeNameByTypeId(truckRequire.getTruckTypeId()));
        }

        bo.setWaybillParam(waybillParamService.findByWaybillId(waybillId));
        if (row.getTruckId() != null) {
            Truck truck = truckService.getTruck(row.getTruckId());
            if (null != truck) {
                bo.setTruck(truck);
            }
        }
        bo.setCoordinates(waybillCommonService.truckLocation(row.getPlateNumber()));
        bo.setDeliveryAddress(waybillDeliveryAddressService.findAllByWaybillId(waybillId));
        bo.setReceiveAddress(waybillReceiveAddressService.findAllByWaybillId(waybillId));
        bo.setTruckCustomer(findCustomerManger(row, loginUser));
        Integer customerId = row.getCustomerId();
        if (null != customerId) {
            bo.setCustomerInfo(customerInfoService.findCusInfoById(customerId));
        }

        // 只有待配送、配送中的计算预估完成时间
        if (Waybill.StatusView.WATING_DELIVERY.getCode() == row.getStatusView()
            || Waybill.StatusView.DELIVERYING.getCode() == row.getStatusView()) {
            bo.setEstimateFinishTime(buildEstimeFinishTime(row));
        }
        if (Waybill.Status.DELIVERYING.getCode() == row.getStatus()) {
            buildPointNo(bo, waybillId);
        }

        List<ReceiptManage> rows = receiptManageService.listByWaybillId(waybillId);
        if (!rows.isEmpty()) {
            bo.setUploadedReceipt(true);
        }
        //是否显示价格
		loginUser.setTenantId(row.getTenantId());//跟随运单租户
		bo.setShowYourPrice(truckService.isShowYourPrice(row.getTruckId(), loginUser));

		if(row.getRoadMapId() == null || row.getRoadMapId() == 0){//老数据默认司机填写工作量
			bo.setWhoWriteWork(1);
		}else{
            bo.setWhoWriteWork(waybillCheckService.loadWhoWriteWork(waybillId));
        }

        return bo;
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

    /**
     * 司机端 查看运单详情
     */
    @Override
    public WaybillBo findWaybillBo(Integer waybillId, LoginUser loginUser) throws BusinessException {
        Waybill waybill = getWaybill(waybillId);
        if (null != waybill) {
            this.buildFromVendorWaybill(waybill);
            this.buildTenantName(waybill);
            // 路线信息
            RoadMap roadMap = roadMapService.getRoadMap(waybill.getRoadMapId());
            waybill.setRoadMapName(roadMap == null ? null : roadMap.getName());

            WaybillBo w = transferToBo(waybill, loginUser);
            w.setProjectVo(projectService.getProjectVo(waybill.getProjectId(), loginUser));
            w.setValuationWayView(waybill.getValuationWayView());

            // 项目信息
            Project project = projectService.getProjectV2(w.getProjectId());
            if (null != project) {
                ProjectVo vo = new ProjectVo();
                org.springframework.beans.BeanUtils.copyProperties(project, vo);
                if (null != project.getProjectManagerUserId()) {
                    User user = userService.loadUser(project.getProjectManagerUserId());
                    vo.setProjectManagerUserName(user == null ? null : user.getName());
                    vo.setProjectManagerUserPhone(user == null ? null : user.getMobileNumber());
                }
            }

            log.info("ValuationWayView  1 is:{}", w.getValuationWayView());
            return w;
        }
        return null;
    }

    @Override
    public void saveWaybillViewHistory(Integer waybillId, Integer driverId) {
        // 数据收集 查看记录入库
        try {
            WaybillViewHistory waybillViewHistory = waybillViewHistoryService.findBy(waybillId, driverId);
            if (waybillViewHistory == null) {
                Date now = new Date();
                WaybillNotice waybillNotice = waybillNoticeService.findBy(waybillId);
                if (waybillNotice != null) {
                    waybillViewHistory = new WaybillViewHistory();
                    waybillViewHistory.setWaybillId(waybillId);
                    waybillViewHistory.setDriverId(driverId);
                    if (waybillNotice != null) {
                        waybillViewHistory
                            .setCostSecond((now.getTime() - waybillNotice.getNoticeTime().getTime()) / 1000);
                    }
                    waybillViewHistory.setViewTime(now);
                    waybillViewHistoryService.save(waybillViewHistory);
                }
            }
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }
    }

    private void buildPointNo(WaybillBo bo, Integer waybillId) {
        List<WaybillReceiveAddress> list = waybillReceiveAddressService.findAllByWaybillId(waybillId);
        int completedPointNo = waybillBindFenceService.findReceivePointNo(waybillId,
            WaybillBindFence.Status.INVALID.getCode());
        int totalPointNo = list.size();
        bo.setCompletedPointNo(completedPointNo);
        bo.setSurplusPointNo(totalPointNo < completedPointNo ? 0 : (totalPointNo - completedPointNo));
    }

    @Override
    public Integer createWaybill(WaybillBo waybillBo, WaybillSource waybillSource, LoginUser loginUser) {
        // 网关层设置ownerEcoUserId、ownerEmpleeUserId、test
        Waybill waybill = waybillBo.getWaybill();

        if (Waybill.WaybillSource.WECHAT_CLIENT.getCode() != waybillSource.getCode()) {
            this.setCustomerManagerId(waybill);
        }
        waybill.setTest(loginUser.isTest());

        TruckRequire truckRequire = waybillBo.getTruckRequire();

        // TODO 4.6.1去掉
        if (null != truckRequire && truckRequire.getTaxRateValue() == null && null != truckRequire.getTaxRateId()) {
            TaxRate taxRate = taxRateService.getTaxRate(truckRequire.getTaxRateId());
            truckRequire.setTaxRateValue(taxRate == null ? null : taxRate.getTaxRateValue());
        }

        List<WaybillDeliveryAddress> deliveryAddress = waybillBo.getDeliveryAddress();
        List<WaybillReceiveAddress> receiveAddress = waybillBo.getReceiveAddress();

        checkCreateWaybill(waybill, waybillSource, truckRequire, deliveryAddress, receiveAddress, loginUser);
        checkCustomerInfo(waybill, loginUser);

        //转单参数校验
        if (waybillBo.getWaybillCarrierVo() != null) {
            waybillTransformToCarrierService.transformBillCheck(waybillBo.getWaybillCarrierVo());
        }

        TruckType truckType = truckTypeService.getTruckType(truckRequire.getTruckTypeId());
        if (truckType != null) {
            checkCarryCapacity(waybill, truckRequire, truckType);
            // 检查要不要箱型
            waybill.setVehicleBoxType(truckType.getVehicleBoxType());
        }
        // 检查要不要入城证
        waybill.setEntryLicense(truckRequire.getEntryLicense());// 为了运单池过滤

        // 回单
        boolean needReceipt = truckRequireService.needNeedReceipt(truckRequire, null);
        waybill.setNeedReceipt(needReceipt ? Waybill.NeedReceipt.NOT_HAVE_UPLOAD.getCode()
            : Waybill.NeedReceipt.NO_NEED_RECEIPT.getCode());

        checkFright(waybill.getEstimateFreight(), null);
        checkFright(waybill.getCalculatedFreight(), null);
        waybill.setAfterTaxFreight(truckTypeFreightService.getAfterTaxFright(truckRequire, waybill));

        Integer waybillId = null;// insert后返回

        // 如果页面没有回传取货地的regionCode，则去取货地信息里面的regionCode
        // 1.app先选城市 决定有没region_code
        // 2.后台下单 没有region_code
        if (StringUtils.isBlank(waybill.getRegionCode())) {
            String regionCode = regionTgmService.findRegionCodeByCoordinate(deliveryAddress.get(0).getCoordinates());
            if (regionCode.length() > 5) {
                regionCode = regionCode.substring(0, 5);
            }
            waybill.setRegionCode(regionCode);
        }

        // 返点率入库
        BigDecimal rebateRate = customerInfoService.getRebateRate(waybill.getCustomerId());
        waybill.setRebateRate(rebateRate);

        // 司机结算价
        BigDecimal show4DriverFreight = waybill.getShow4DriverFreight();
        WaybillParam waybillParam = settingExtraFee(waybillBo, null);
        if (null != show4DriverFreight) {
            waybill.setShow4DriverFreight(show4DriverFreight);
        }
        Integer driverId = null;
        if (NumberUtils.compare(waybill.getReceiveWay(), Waybill.ReceiveWay.ASSIGNED.getCode()) == 0) {
            driverId = waybill.getDriverId();
            waybill.setDriverId(null);
        }
        insertWaybill(waybill, waybillSource, truckRequire, loginUser);
        waybillId = waybill.getWaybillId();
        // 关联表
        if (waybillBo.getWaybillParam() != null) {
            waybillParam.setAgencyTakeFreight(waybillBo.getWaybillParam().getAgencyTakeFreight());
        }
        waybillCommonService.insertLinkTable(waybillBo, loginUser);
        if (waybillBo != null && waybillBo.getWaybillParam() != null) {
            waybillParam.setRequiredMinTemperature(waybillBo.getWaybillParam().getRequiredMinTemperature());
            waybillParam.setRequiredMaxTemperature(waybillBo.getWaybillParam().getRequiredMaxTemperature());
        }
        log.info("waybillParam {}.", waybillParam);
        Sop sop = sopService.findNewestVersionSopByTenantId(loginUser.getTenantId());
        if (sop == null) {
            throw new BusinessException("errors.notFound.Sop", "errors.notFound.Sop", loginUser.getTenantId());
        }
        waybillParam.setSopId(sop.getSopId());

        if (waybillBo.getWaybillParam() != null) {
            org.springframework.beans.BeanUtils.copyProperties(waybillParam, waybillBo.getWaybillParam());
        }
        waybillCommonService.saveWaybillParam(waybillBo.getWaybillParam(), waybill, loginUser);

        // 执行转单逻辑
        if (waybillBo.getWaybillCarrierVo() != null) {
            waybillTransformToCarrierService.saveTransformBillParam(waybill, waybillBo.getWaybillParam(), waybillBo.getWaybillCarrierVo(), loginUser);
            waybillTransformToCarrierService.dispatch(waybillBo, loginUser);
        }

        // 添加操作日志与任务
        if (Waybill.WaybillSource.WECHAT_CLIENT.getCode() != waybillSource.getCode()) {
            customerManWaybillUtils.addWaitHandleWaybill(waybill, loginUser);
        }

        if (NumberUtils.compare(waybill.getReceiveWay(), Waybill.ReceiveWay.ASSIGNED.getCode()) == 0) {
            waybill.setDriverId(driverId);
            this.appointCar(waybill, loginUser);
        }

        return waybillId;
    }

    /**
     * 指派车辆
     *
     * @param waybill
     * @param loginUser
     */
    private void appointCar(Waybill waybill, LoginUser loginUser) {
        Driver driver = driverService.getDriver(waybill.getDriverId());
        if (null == driver) {
            throw new BusinessException("driverNotFound", "driver.error.not.found");
        }

        ChangeCarVo changeCarVo = new ChangeCarVo(waybill.getWaybillId(),waybill.getDriverId(), waybill.getTruckId(),
                waybill.getVehicleToVendor(), waybill.getReceiveWay(), null, waybill, driver);
        this.doChangeToAssigned(changeCarVo, loginUser);
    }

    @Override
    public WaybillParam settingExtraFee(WaybillBo waybillBo, WaybillParam wParam) {

        WaybillParam waybillParam = wParam == null ? this.buildCarryFee(waybillBo) : wParam;
        if (waybillBo.getWaybill() == null || waybillBo.getWaybill().getAfterTaxFreight() == null) {
            return waybillParam;
        }
        BigDecimal show4DriverFreight = waybillBo.getWaybill().getAfterTaxFreight();
        /*
         * if (waybillParam.getDriverHandlingCost() != null) { show4DriverFreight = show4DriverFreight.subtract(waybillParam.getDriverHandlingCost()); }
         */
        if (waybillParam.getLaborerHandlingCost() != null) {
            show4DriverFreight = show4DriverFreight.subtract(waybillParam.getLaborerHandlingCost());
        }
        if (waybillBo.getWaybill().getRebateRate() != null && waybillBo.getWaybill().getEstimateFreight() != null) {
            show4DriverFreight = show4DriverFreight.subtract(
                waybillBo.getWaybill().getEstimateFreight().multiply(waybillBo.getWaybill().getRebateRate()));
        }

        // 返点费
        waybillBo.getWaybill().setShow4DriverFreight(show4DriverFreight);// 本地方法
        waybillParam.setShow4DriverFreight(show4DriverFreight);// 对外

        return waybillParam;
    }

    // 创建运单
    private void insertWaybill(Waybill waybill, WaybillSource waybillSource, TruckRequire truckRequire,
                                 LoginUser loginUser) {
        // 添加重量
        if (null != truckRequire && StringUtils.isNotBlank(truckRequire.getGoodsWeight())) {
            waybill.setGoodsWeight(
                new BigDecimal(truckRequire.getGoodsWeight()).multiply(new BigDecimal("1000")).intValue());
        }
        // 添加体积
        if (null != truckRequire && StringUtils.isNotBlank(truckRequire.getGoodsVolume())) {
            waybill.setGoodsVolume(new BigDecimal(truckRequire.getGoodsVolume()));
        }

        // 客户所在业务区域就订单的区域 前台传 TODO

        waybill.setWaybillNo(waybillCommonService.getWaybillNo());
        waybill.setWaybillSource(waybillSource.getCode());
        waybill.setUpdateFreightAuditStatus(0);
        waybill.setReconciliationStatus(Waybill.ReconciliationStatus.INIT.getCode());
        waybill.setReceivableReconcilicationStatus(Waybill.ReconciliationStatus.INIT.getCode());
        // 结算状态
        waybill.setSettlementStatus(Waybill.SettlementStatus.INIT.getCode());
        // 收款状态
        waybill.setReceiptStatus(Waybill.ReceiptStatus.INIT.getCode());
        if (null != waybill.getNeedDeliveryPointNote() && waybill.getNeedDeliveryPointNote() == 1) {
            waybill.setIsChangeDeliveryPoint(Waybill.ChangeDeliveryPoint.NOT_UPLOAD.getCode());
        }
        if (Waybill.WaybillSource.WECHAT_CLIENT.equals(waybillSource)) {
            waybill.setStatus(Waybill.Status.PENDING_EXAMINE.getCode());
            waybill.setStatusView(Waybill.StatusView.WATING_RECEIVE.getCode());
        } else {
            if (waybill.getStatus() == null) {
                waybill.setStatus(Status.WATING_RECEIVE.getCode());
            }
            if (waybill.getStatusView() == null) {
                waybill.setStatusView(Waybill.StatusView.WATING_RECEIVE.getCode());
            }
        }
        if (null != waybill.getReceiptType() && waybill.getReceiptType() == Waybill.ReceiptType.WEIXINPAY.getCode()) {
            waybill.setReconciliationStatus(Waybill.ReconciliationStatus.HAS_RECONCILIATION.getCode());
            waybill.setReceiptStatus(Waybill.ReceiptStatus.HAS_COLLECTION.getCode());
            waybill.setSettlementStatus(Waybill.SettlementStatus.HAS_CLEAR.getCode());

        }
        if (null != waybill.getCalculatedFreight()) {
            waybill.setCompareResult(
                BaseUtil.calCompareResult(waybill.getCalculatedFreight().multiply(new BigDecimal("1.1")),
                    waybill.getEstimateFreight(), true));
        }
        waybillCommonService.insert(waybill, loginUser);
//        return truckCustomer.getOpenId();
    }

    // 获取配置的搬运费
    public WaybillParam buildCarryFee(WaybillBo waybillBo) {
        Waybill waybill = waybillBo.getWaybill();
        TruckRequire truckRequire = waybillBo.getTruckRequire();

        if (waybill == null) throw new BusinessException("waybillNull", "errors.paramCanNotNullWithName", "运单");
        if (truckRequire == null)
            throw new BusinessException("truckRequireNull", "errors.paramCanNotNullWithName", "用车要求");

        // 获取搬运费
        AdditionalFunction driverCarryFun = additionalFunctionService
            .findAdditionFunctionByKey(AdditionalFunction.FunctionKeys.DRIVER_CARRY.name());
        AdditionalFunction laborFun = additionalFunctionService
            .findAdditionFunctionByKey(AdditionalFunction.FunctionKeys.LABORER_CARRY.name());

        BigDecimal driverCarryFee = null;
        BigDecimal laborerFee = null;

        WaybillParam waybillParam = new WaybillParam();
        waybillParam.setLaborerHandlingCost(new BigDecimal("0"));
        waybillParam.setDriverHandlingCost(new BigDecimal("0"));

        // 判断需不需要设置搬运费数据
        if (StringUtils.isNotBlank(waybillBo.getTruckRequire().getAdditionalFunctionIds())) {
            // 获取价格规则
            TruckTypeFreight truckTypeFreight = truckTypeFreightService
                .findFreightByTypeIdAndCityManageId(truckRequire.getTruckTypeId(), waybill.getRegionCode());
            if (null == truckTypeFreight) {
                return waybillParam;
            }

            String[] fids = waybillBo.getTruckRequire().getAdditionalFunctionIds().split(",");
            Arrays.sort(fids);

            // 司机搬运费
            if (Arrays.binarySearch(fids, driverCarryFun.getAdditionalFunctionId().toString()) >= 0) {

                // 获取车型运费规则
                AdditionalFunctionFreight functionFreight = additionalFunctionFreightService.getAddFuncFreightByTypeAF(
                    truckTypeFreight.getFreightId(), driverCarryFun.getAdditionalFunctionId());
                if (functionFreight != null) {
                    driverCarryFee = functionFreight.getLowestFreight();
                    waybillParam.setDriverHandlingCost(driverCarryFee);
                }
            }

            // 小工搬运费
            if (Arrays.binarySearch(fids, laborFun.getAdditionalFunctionId().toString()) >= 0) {
                // 获取车型运费规则
                AdditionalFunctionFreight functionFreight = additionalFunctionFreightService
                    .getAddFuncFreightByTypeAF(truckTypeFreight.getFreightId(), laborFun.getAdditionalFunctionId());
                if (functionFreight != null) {
                    laborerFee = functionFreight.getLowestFreight();
                    waybillParam.setLaborerHandlingCost(laborerFee);
                }
            }
        }

        return waybillParam;
    }

    // 建单验证
    private void checkCreateWaybill(Waybill waybill, WaybillSource waybillSource, TruckRequire truckRequire,
                                    List<WaybillDeliveryAddress> deliveryAddress, List<WaybillReceiveAddress> receiveAddress,
                                    LoginUser loginUser) {
        if (StringUtils.isBlank(waybill.getAreaCode())) {
            throw new BusinessException("areaCodeNotNull", "waybill.error.areaCodeNotNull");
        }
//        if(StringUtils.isBlank(waybill.getDepartmentCode())){
//            throw new BusinessException("departmentCodeNotNull", "waybill.error.departmentCodeNotNull");
//        }
        if (null == truckRequire) {
            throw new BusinessException("truckTypeMustSelect", "truckTypeFreight.not.truckTypeMustSelect");
        }

        // 微信下单直接返回
        if (Waybill.WaybillSource.WECHAT_CLIENT.equals(waybillSource)) {
            return;
        }

        // 业务区域
        log.info("后台建单接收参数-校验：{}", JSON.toJSONString(waybill));

        // 微信必填项目校验
        this.checkCreateWaybillForWechat(waybill, deliveryAddress, receiveAddress, loginUser);

        // 派车方式
        Integer receiveWay = waybill.getReceiveWay();
        if (null == receiveWay) {
            throw new BusinessException("receiveWayNotNull", "waybill.error.receiveWayNotNull");
        }
        if (!Waybill.ReceiveWay.map().containsKey(receiveWay + "")) {
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

        if (NumberUtils.compare(waybill.getBusinessBranch(), Waybill.BusinessBranch.BRANCH_FULL.getCode()) != 0) {
            // 货物重量
            if (StringUtils.isBlank(truckRequire.getGoodsWeight())) {
                throw new BusinessException("canNotBeBlank", "errors.validation.canNotBeBlank", "重量");
            }
            if (!BaseUtil.isNumOrDecimal(truckRequire.getGoodsWeight())
                && new BigDecimal(truckRequire.getGoodsWeight()).compareTo(BigDecimal.ZERO) <= 0) {
                throw new BusinessException("notNumeric", "errors.validation.notNumeric", "重量");
            }
        }

        // 货物体积
        if (StringUtils.isNotBlank(truckRequire.getGoodsVolume())) {
            if (!BaseUtil.isNumOrDecimal(truckRequire.getGoodsVolume())
                && new BigDecimal(truckRequire.getGoodsVolume()).compareTo(BigDecimal.ZERO) <= 0) {
                throw new BusinessException("notNumeric", "errors.validation.notNumeric", "体积");
            }
        }

        // 结算方式
        Integer receiptType = waybill.getReceiptType();
        if (null == receiptType) {
            throw new BusinessException("canNotBeBlank", "errors.validation.canNotBeBlank", "结算方式");
        }

        if(crm4TmsService.isShowConfigure(ProductsLableInfo.configureEnum.LOGISTICS_PRODUCTS.getCode(), loginUser)
            && StringUtils.isBlank(waybill.getLogisticsLabel())){
            throw new BusinessException("logisticsLabelNotNull", "物流产品标签不能为空");
        }

        // 客户侧含税金额
        if (waybillCommonService.checkCustomerPriceUpperLimit(waybill.getEstimateFreight())) {
            throw new BusinessException("customerPriceTooUpper", "errors.common.prompt", "运单税前费用过高，请认真核对！");
        }
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

        Department department = customerInfoService.findDepartmentByCustomerId(customerInfo.getCrmCustomerId(), loginUser);
        if(department == null || department.getDepartmentId() == null){
            throw new BusinessException("departmentIdRequired", "waybill.error.departmentIdRequired", customerInfo.getCustomerName());
        }
    }

    /**
     * 微信下单需要检查的项目
     *
     * @param waybill
     * @param deliveryAddress
     * @param receiveAddress
     */
    private void checkCreateWaybillForWechat(Waybill waybill, List<WaybillDeliveryAddress> deliveryAddress,
                                             List<WaybillReceiveAddress> receiveAddress, LoginUser loginUser) {

        // 用车时间
        Date planDeliveryTime = waybill.getPlanDeliveryTime();
        if (null == planDeliveryTime) {
            throw new BusinessException("planDeliveryTimeNotNull", "waybill.error.planDeliveryTimeNotNull");
        }
        if (DateUtil.checkOvertime(planDeliveryTime, (20 * 60)) == -1) {
            throw new BusinessException("planDeliveryTimeToLess", "waybill.error.planDeliveryTimeToLess");
        }

        // 取货地
        if (CollectionUtils.isEmpty(deliveryAddress) || null == deliveryAddress.get(0)) {
            throw new BusinessException("validationFailure", "errors.validation.srcAddress");
        }
        WaybillDeliveryAddress waybillDeliveryAddress = deliveryAddress.get(0);
        if (StringUtils.isBlank(waybillDeliveryAddress.getContactName())) {
            throw new BusinessException("canNotBeBlank", "errors.validation.canNotBeBlank", "取货地联系人");
        }
        if (waybillDeliveryAddress.getContactName().length() > WaybillCommonService.WAYBILL_CONTACT_NAME_LENGTH) {
            throw new BusinessException("dataTooLong", "errors.validation.dataTooLong", "取货地联系人");
        }
        if (StringUtils.isBlank(waybillDeliveryAddress.getContactPhone())) {
            throw new BusinessException("canNotBeBlank", "errors.validation.canNotBeBlank", "取货地联系人电话");
        }
        if (!BaseUtil.checkMobilePhone(waybillDeliveryAddress.getContactPhone())
            && !BaseUtil.checkTelephone(waybillDeliveryAddress.getContactPhone())) {
            throw new BusinessException("contactPhoneFmtError", "errors.contactPhoneFmtError", "取货地");
        }

        // 目的地
        if (CollectionUtils.isEmpty(receiveAddress)) {
            throw new BusinessException("validationFailure", "errors.validation.toAddress");
        }

        if(crm4TmsService.isShowConfigure(ProductsLableInfo.configureEnum.LOGISTICS_PRODUCTS.getCode(), loginUser)
            && StringUtils.isBlank(waybill.getLogisticsLabel())){
            throw new BusinessException("logisticsLabelNotNull", "物流产品标签不能为空");
        }

        // 计数器
        int temp = 0;
        for (WaybillReceiveAddress address : receiveAddress) {
            temp += 1;
            if (StringUtils.isBlank(address.getCoordinates())) {
                throw new BusinessException("validation.srcAddress.lawful", "errors.validation.toAddress.lawful", temp);
            }
            if (StringUtils.isNotBlank(address.getContactPhone())
                && !BaseUtil.checkMobilePhone(address.getContactPhone())
                && !BaseUtil.checkTelephone(address.getContactPhone())) {
                throw new BusinessException("contactPhoneFmtError", "errors.contactPhoneFmtError", "第" + temp + "目的地");
            }

            if (StringUtils.isNotBlank(address.getContactName())) {
                if (address.getContactName().length() > WaybillCommonService.WAYBILL_CONTACT_NAME_LENGTH) {
                    throw new BusinessException("dataTooLong", "errors.validation.dataTooLong", "第" + temp + "目的地联系人");
                }
            }
        }

    }

    @Override
    public DistanceAndPriceData calWaybillPrice(CityAdressData formAddress, List<CityAdressData> toAddress,
                                                WaybillBo waybillBo, LoginUser loginUser) {
        DistanceAndPriceData result = new DistanceAndPriceData();
        try {
            result = waybillCommonService.getGaodeMapInfo(formAddress, toAddress);
        } catch (Exception e) {
            log.warn("计算运单价格错误", e);
            throw new BusinessException("calculatePriceError", "waybill.error.calculatePrice");
        }
        PriceProxy proxy = truckTypeFreightService.getFright(waybillBo, result, result.getSiteNo(),
            result.getRegionCode(), loginUser);
        kSession.insert(proxy);
        kSession.fireAllRules();
        BigDecimal price = proxy.getFinalPrice();
        log.info("DistanceAndPriceData price: " + price);
        result.setPrice(price);
        result.setPriceProxy(proxy);
        return result;
    }

    @Override
    public DistanceAndPriceData recountThePrice(Integer waybillId, Integer actualMileage, Date finishTime,
                                                LoginUser loginUser) {
        DistanceAndPriceData data = new DistanceAndPriceData();
        if (null == waybillId) {
            return data;
        }

        Waybill waybill = getWaybill(waybillId);
        if (null == waybill) {
            return data;
        }

        // 项目运单不重新计算价格
        if (null != waybill.getProjectId()) {
            return data;
        }

        TruckRequire truckRequire = truckRequireService.findTruckRequireByWaybillId(waybillId, loginUser);

        WaybillBo bo = new WaybillBo();
        // 配送完成时间
        if (null != finishTime) {
            waybill.setFinishTime(finishTime);
        }
        bo.setWaybill(waybill);
        bo.setTruckRequire(truckRequire);

        // 实际里程 -> 预估里程
        actualMileage = actualMileage == null ? waybill.getActualMileage() : actualMileage;
        if (actualMileage == null || actualMileage.equals(0)) {
            data.setDistance(waybill.getEstimateDistance());

        } else {
            data.setDistance(new BigDecimal(actualMileage.toString())
                .divide(new BigDecimal("1000"), 0, BigDecimal.ROUND_HALF_UP).intValue());
        }

        data.setTolls(waybill.getTolls());

        List<WaybillDeliveryAddress> deliveryAddressList = waybillDeliveryAddressService.findAllByWaybillId(waybillId);

        PriceProxy proxy = truckTypeFreightService.getFright(bo, data, deliveryAddressList.size(),
            waybill.getRegionCode(), loginUser);
        kSession.insert(proxy);
        kSession.fireAllRules();
        data.setPrice(proxy.getFinalPrice() == null ? BigDecimal.ZERO : proxy.getFinalPrice());
        return data;
    }

    /**
     * 接单
     */
    @Override
    public void receiveWaybill(Waybill waybill, LoginEcoUser loginEcoUser) throws BusinessException {
        Waybill wb = checkBeforwaybillToDriver(waybill);

        loginEcoUser.setTenantId(wb.getTenantId());
        User currUser = userService.loadUser(loginEcoUser.getUserId());// 接单人帐号类型

        if (currUser == null) {
            throw new BusinessException("validationFailure", "errors.validation.failure");
        }

        // log.info("发单人帐号:{}", createUser.getUserId());
        log.info("接单人帐号:{}", currUser.getUserId());

        if (wb.isTest() && !currUser.isTest()) {
            throw new BusinessException("waybill.error.testOrder", "waybill.error.testOrder");
        }

        if (!wb.isTest() && currUser.isTest()) {
            throw new BusinessException("waybill.error.testUser", "waybill.error.testUser");
        }

        if (wb.getStatus() != Waybill.Status.WATING_RECEIVE.getCode()) {
            throw new BusinessException("statusError", "waybill.error.received");
        }


        Driver driver = driverService.findDriverByUserId(loginEcoUser.getUserId());
        if (null == driver) {
            throw new BusinessException("driverNotfound", "driver.error.not.found");
        }

        CapacityPool capacityPool = vmsCommonService.loadCapacityByDriverId(driver.getDriverId(), loginEcoUser);
        if (null == capacityPool || null == capacityPool.getStatus()) {
            throw new BusinessException("driverCapacityPoolInNull", "errors.common.prompt", "您还未绑定车辆或承运商，无法抢单！");
        }

        if (!capacityPool.getStatus()) {
            VendorTenantExternal vendorTenantExternal = vmsCommonService
                .loadVendorTenantByVendorId(capacityPool.getVendorId(), loginEcoUser);
            if (null == vendorTenantExternal || null == vendorTenantExternal.getEnable()) {
                throw new BusinessException("driverCapacityPoolInNull", "errors.common.prompt", "您还未绑定车辆或承运商，无法抢单！");
            } else if (!vendorTenantExternal.getEnable()) {
                throw new BusinessException("driverCapacityPoolInNull", "errors.common.prompt", "您的承运商已停用，无法抢单！");
            } else {
                throw new BusinessException("driverCapacityPoolInNull", "errors.common.prompt", "您的运力是不可用状态，无法抢单！");
            }
        }

        checkDriverIsEnableUtil.canDriverTransportWaybill(driver.getDriverId(), wb.getPlanDeliveryTime(),
                wb.getCmEstimateFinishTime() == null ? DateUtil.addMinutes(wb.getPlanDeliveryTime(), wb.getEstimateTimeConsumption()) : wb.getCmEstimateFinishTime());

        com.juma.vms.truck.domain.Truck truck = vmsCommonService.loadTruckByDriverId(driver.getDriverId(), loginEcoUser);
        if (truck == null) {
            throw new BusinessException("truckNobindDriver", "truck.nobind.driver");
        }

        // BaseUtil.checkSelf(driver.getDriverId(), wb.getDriverId());
        waybill.setDriverType(vmsCommonService.findDriverRunType(driver.getDriverId()));
        waybill.setDriverId(driver.getDriverId());
        waybill.setDriverName(driver.getNickname());
        waybill.setTruckId(truck.getTruckId());
        waybill.setPlateNumber(truck.getPlateNumber());
        waybill.setVehicleToVendor(capacityPool.getVendorId());
        waybill.setReceivingTime(new Date());
        waybill.setReceiveWay(Waybill.ReceiveWay.RECEIVED.getCode());
        waybill.setStatus(Waybill.Status.RECEIVED.getCode());
        waybill.setStatusView(Waybill.StatusView.WATING_DELIVERY.getCode());
        // 判断车辆是否绑定承运商
        waybill = buildVehicleTypeAndVendorService.checkAndBuildVehicleTypeAndVendor(waybill, truck.getVehicleId(),
                waybill.getDriverType(), BuildVehicleTypeAndVendorService.RECEIVE_WAYBILL, loginEcoUser);
        update(waybill, loginEcoUser);

        // 标记为司机已读
        this.updateDriverToRead(waybill.getWaybillId(), loginEcoUser);

        // 清除自动派车的预计完成时间
        truckService.removeEstimateFinishTime(truck.getTruckId());

        // 推送消息
        msgWithBusinessService.pushDriverReceiveWaybillMsg(wb, driver, loginEcoUser);

        // 删除经纪人订单超时通知
        customerManWaybillUtils.delHandledWaybill(waybill.getWaybillId());

        // 更改承运商运单对应的运单信息
        this.updateTransformBillForReceiveWaybill(waybill, loginEcoUser);
    }

    public void updateTransformBillForReceiveWaybill(Waybill waybill, LoginUser loginUser) {
        Waybill transformBill = waybillCommonService.findWaybillByTransformBillId(waybill.getWaybillId());
        if (null == transformBill) {
            return;
        }

        // 重新组装loginUser
        loginUser.setTenantId(waybill.getTenantId());
        loginUser.setTenantCode(waybill.getTenantCode());

        transformBill.setReceivingTime(new Date());
        transformBill.setReceiveWay(Waybill.ReceiveWay.RECEIVED.getCode());
        transformBill.setStatus(Waybill.Status.RECEIVED.getCode());
        transformBill.setStatusView(Waybill.StatusView.WATING_DELIVERY.getCode());
        update(transformBill, loginUser);

        // 标记为司机已读
        this.updateDriverToRead(transformBill.getWaybillId(), loginUser);

        // 推送消息
        msgWithBusinessService.pushDriverReceiveWaybillMsg(transformBill, null, loginUser);

        // 删除经纪人订单超时通知
        customerManWaybillUtils.delHandledWaybill(waybill.getWaybillId());
    }

    // 标记为司机已读
    private void updateDriverToRead(Integer waybillId, LoginUser loginUser) {
        WaybillParam param = new WaybillParam();
        param.setWaybillId(waybillId);
        param.setDriverRead(1);
        param.setDriverReadTime(new Date());
        waybillParamService.addOrUpdateOnly(param, loginUser);
    }

    // 接单或时使用，行锁，校验参数 callbackWaybill 检查运单是否存在并返回数据中的运单
    private Waybill checkBeforwaybillToDriver(Waybill waybill) {
        if (null == waybill) {
            throw new BusinessException("validationFailure", "errors.validation.failure");
        }
        Integer waybillId = waybill.getWaybillId();
        if (waybillId == null) {
            throw new BusinessException("validationFailure", "errors.validation.failure");
        }
        Waybill wb = waybillDao.getForUpdate(waybill.getWaybillId());
        if (null == wb) {
            throw new BusinessException("waybillNotfound", "waybill.error.notfound");
        }
        return wb;
    }

    @Override
    public void changeNewToManual(int waybillId, LoginUser loginUser) throws BusinessException {
        Waybill waybill = getWaybillAndCheckExist(waybillId);
        if (Waybill.Status.NEW.getCode() != waybill.getStatus()
            && Waybill.Status.PENDING_EXAMINE.getCode() != waybill.getStatus()) {
            throw new BusinessException("waybillNotNewStatus", "waybill.error.not.new.status");
        }

        waybill.setStatus(Waybill.Status.WATING_RECEIVE.getCode());
        waybill.setReceiveWay(Waybill.ReceiveWay.MANUAL_ASSIGN.getCode());
        waybill.setStatusView(Waybill.StatusView.WATING_RECEIVE.getCode());

        update(waybill, loginUser);
    }

    // 更换车辆
    @Override
    public void changeCar(int waybillId, int driverId, int truckId, Integer vendorId, int receiveWay, String remark,
                          LoginUser loginUser) throws BusinessException {
        // 校验运单
        Waybill wb = waybillCommonService.getForUpdate(waybillId);
        if (null == wb) {
            throw new BusinessException("waybillNotfound", "waybill.error.notfound");
        }
        checkDriverIsEnableUtil.canDriverTransportWaybill(driverId, wb.getPlanDeliveryTime(),
                wb.getCmEstimateFinishTime() == null ? DateUtil.addMinutes(wb.getPlanDeliveryTime(), wb.getEstimateTimeConsumption()) : wb.getCmEstimateFinishTime());

        // 验证能不能更换车辆
        if (!allowChangeCar(wb)) {
            throw new BusinessException("noAllowToUpateCar", "errors.noAllowToUpateCar",
                Waybill.ReceiveWay.MANUAL_ASSIGN.getDescr() + "或" + Waybill.ReceiveWay.AUTO_ASSIGN.getDescr()
                    + "】且在用车时间【" + Constants.TIME_LIMIT_ALLOW_UPDATE_CAR);
        }

        Driver driver = driverService.getDriver(driverId);
        Truck truck = truckService.getTruck(truckId);

        waybillCommonService.doCancelAssign(receiveWay, wb, driver, truck, loginUser);

        waybillCommonService.doAssignCarAgain(receiveWay, remark, wb, driver, truck, loginUser);

        // 推送消息
        msgWithBusinessService.pushChangeCarMsg(wb.getWaybillId(), wb.getDriverId(), loginUser);

        // 更改被承运的运单信息
        this.updateVendorWaybill(wb, true, loginUser);

        mqAfterCommitSendUtil.afterWaybillOperateChangeCommit(waybillId, WaybillOperateChangeEnum.WAYBILL_CHANGE_TRUCK);
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
            msgWithBusinessService.pushChangeCarMsg(transformBill.getWaybillId(), null, loginUser);
        } else {
            msgWithBusinessService.pushPointCarMsg(transformBill.getWaybillId(), loginUser);
            // 删除经纪人订单超时通知
            customerManWaybillUtils.delHandledWaybill(transformBill.getWaybillId());
        }

    }

    // 指派车辆
    @Override
    public void changeToAssigned(int waybillId, int driverId, int truckId, Integer vendorId, int receiveWay, String remark,
                                 LoginUser loginUser) throws BusinessException {
        // 校验运单
        Waybill wb = waybillDao.getForUpdate(waybillId);
        if (null == wb) {
            throw new BusinessException("waybillNotfound", "waybill.error.notfound");
        }

        checkDriverIsEnableUtil.canDriverTransportWaybill(driverId, wb.getPlanDeliveryTime(),
                wb.getCmEstimateFinishTime() == null ? DateUtil.addMinutes(wb.getPlanDeliveryTime(), wb.getEstimateTimeConsumption()) : wb.getCmEstimateFinishTime());

        if (null != wb.getDriverId()) {
            throw new BusinessException("waybillHasAssigned", "waybill.error.waybillHasAssigned", wb.getWaybillNo());
        }

        Driver driver = driverService.getDriver(driverId);
        // 校验司机
        waybillCommonService.checkDriverCanbeAssign(driver, wb.getStatus(), wb.getReceiveWay());

        this.doChangeToAssigned(new ChangeCarVo(waybillId, driverId, truckId, vendorId, receiveWay, remark, wb, driver), loginUser);

        mqAfterCommitSendUtil.afterWaybillOperateChangeCommit(waybillId, WaybillOperateChangeEnum.WAYBILL_ASSIGN_TRUCK);
    }

    //执行换车
    private void doChangeToAssigned(ChangeCarVo changeCarVo, LoginUser loginUser) {
        // Driver driver = checkDriverCanbeAssign(driverId, wb.getStatus(), wb.getReceiveWay());

        // 校验车辆
        Truck truck = truckService.getTruckAndCheckEixst(changeCarVo.getTruckId());
        if (StringUtils.isBlank(truck.getPlateNumber()))
            throw new BusinessException("plateNumberCanNotNull", "errors.plateNumberCanNotNull");

        // 校验派车方式
        if (StringUtils.isBlank(Waybill.ReceiveWay.buildReceiveWayStr(changeCarVo.getReceiveWay()))) {
            throw new BusinessException("receiveWayErr", "waybill.error.receiveWayErr");
        }


        Driver driver = changeCarVo.getDriver();
        // 组装参数
        Waybill waybill = new Waybill();
        waybill.setWaybillId(changeCarVo.getWaybillId());
        waybill.setDriverId(changeCarVo.getDriverId());
        waybill.setDriverName(driver.getNickname());
        waybill.setDriverType(vmsCommonService.findDriverRunType(driver.getDriverId()));
        waybill.setTruckId(changeCarVo.getTruckId());
        waybill.setVehicleToVendor(changeCarVo.getVendorId());
        waybill.setPlateNumber(truck.getPlateNumber());
        waybill.setReceiveWay(changeCarVo.getReceiveWay());
        waybill.setAssignWaybillRemark(changeCarVo.getRemark());
        waybill = buildVehicleTypeAndVendorService.checkAndBuildVehicleTypeAndVendor(waybill, truck.getVehicleId(),
                waybill.getDriverType(), BuildVehicleTypeAndVendorService.CHANGE_TO_ASSIGN, loginUser);

        // 派车
        waybillCommonService.changeToAssignedUpdateDb(waybill, loginUser);

        // 绑定电子围栏
        waybillAutoFenceServicve.bindWaybillIdAndFenceId(changeCarVo.getWaybillId(), WaybillBindFence.Sign.DELIVERY_ADDRESS, loginUser);

        // 删除经纪人订单超时通知
        customerManWaybillUtils.delHandledWaybill(changeCarVo.getWaybillId());
        msgWithBusinessService.pushPointCarMsg(changeCarVo.getWaybillId(), loginUser);

        // 更改被承运的运单信息
        this.updateVendorWaybill(changeCarVo.getWb(), false, loginUser);
    }

    // 派车 司机抢单
    @Override
    public void changeToWaitingReceive(int waybillId, LoginUser loginUser) throws BusinessException {
        Waybill waybill = getWaybillAndCheckExist(waybillId);

        // 派车类型为司机抢单：1、司机状态为-新建、派车中、派车待定；2、运单原有派车方式不是-司机抢单
        if (Waybill.Status.NEW.getCode() != waybill.getStatus()
            && Waybill.Status.WATING_RECEIVE.getCode() != waybill.getStatus()
            && Waybill.Status.UNDETERMINED.getCode() != waybill.getStatus()
            && Waybill.ReceiveWay.RECEIVED.getCode() == waybill.getReceiveWay()) {
            throw new BusinessException("receivedError", "errors.receivedError");
        }

        waybill.setStatus(Waybill.Status.WATING_RECEIVE.getCode());
        waybill.setStatusView(Waybill.StatusView.WATING_RECEIVE.getCode());
        waybill.setReceiveWay(Waybill.ReceiveWay.RECEIVED.getCode());
        update(waybill, loginUser);
        // 推送给司机
        messagePushService.robWaybillMessage(waybill.getWaybillId(), loginUser);
    }

    @Override
    public Integer getWaybillStatusNotEnd(LoginEmployee loginEmployee) {
        Integer result = 0;
        Waybill waybill = new Waybill();
        waybill.setCustomerManagerId(loginEmployee.getEmployeeId());
        List<Waybill> waybills = waybillDao.findByExample(waybill);
        if (waybills != null) {
            for (Waybill wb : waybills) {
                if (wb.getStatus() == Waybill.Status.ASSIGNED.getCode()
                    || wb.getStatus() == Waybill.Status.RECEIVED.getCode()
                    || wb.getStatus() == Waybill.Status.DELIVERYING.getCode()
                    || wb.getStatus() == Waybill.Status.WATING_RECEIVE.getCode()
                    || wb.getStatus() == Waybill.Status.DELIVERIED.getCode()) {
                    ++result;
                }
            }
        }
        return result;
    }

    @Override
    public WaybillDetailInfo getWaybillInfo(Integer waybillId, LoginUser loginUser) {
        WaybillDetailInfo result = buildDetailInfo(getWaybill(waybillId), loginUser);
        result.setReceiptImageList(receiptManageService.listReceiptImageByWaybillId(waybillId));
        result.setWaybillDeliveryAddresses(waybillDeliveryAddressService.findAllByWaybillId(waybillId));
        result.setWaybillReceiveAddresses(waybillReceiveAddressService.findAllByWaybillId(waybillId));
//        vmsService.loadByVenorId();
        buildCustomerInfo(result);
        return result;
    }

    // 获取租户名称
    private void buildTenantName(Waybill waybill) {
        if (null == waybill.getTenantId()) {
            return;
        }

        Tenant tenant = tenantService.loadTenant(waybill.getTenantId());
        if (null != tenant) {
            waybill.setTenantName(tenant.getTenantName());
        }
    }

    @Override
    public WaybillDetailInfo findWaybillDetailById(Integer waybillId, LoginUser loginUser) {
        WaybillDetailInfo info = new WaybillDetailInfo();

        Waybill waybill = getWaybill(waybillId);
        if (null == waybill) {
            return info;
        }

        //任务信息
        if (waybill.getWaybillSource() != null && waybill.getWaybillSource().equals(WaybillSource.FORM_TASK.getCode())) {
            info.setTask(taskScheduledService.findTaskLinkWaybill(waybillId));
        }

        // 项目经理
        User manager = authCommonService.loadUser(waybill.getProjectManagerUserId());
        if (null != manager) {
            waybill.setProjectManagerUserPhone(manager.getMobileNumber());
        }

        // 下单人
        User createUser = authCommonService.loadUser(waybill.getCreateUserId());
        if (null != createUser) {
            waybill.setCreateUserPhone(createUser.getMobileNumber());
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

        buildUseDate(waybill);

        info.setWaybillParam(buildWaybillParam(waybillId));
        // 用车要求
        TruckRequire truckRequire = truckRequireService.findTruckRequireByWaybillId(waybill.getWaybillId(), loginUser);
        if (null != truckRequire) {
            truckRequire.setTruckRequireStr(truckRequireService.getTruckRequire(truckRequire, null));
            info.setTruckTypeName(truckTypeService.findTruckTypeNameByTypeId(truckRequire.getTruckTypeId()));
            info.setVehicleBoxName(truckTypeService.findVehicleBoxTypeName(truckRequire.getVehicleBoxType()));
            // 判断车型
            TruckType truckType = truckTypeService.getTruckType(truckRequire.getTruckTypeId());
            if (truckType != null) {
                truckRequire.setVehicleBoxType(truckType.getVehicleBoxType());
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
            // 司机信息
            com.juma.vms.driver.domain.Driver vmsDriver = vmsCommonService.loadDriverByDriverId(waybill.getDriverId());
            if (null != vmsDriver) {
                com.juma.server.vm.domain.Driver amsDriver = amsCommonService.findDriver(vmsDriver.getAmsDriverId(), loginUser);
                if (null != amsDriver) {
                    truck.setParkingAddress(amsDriver.getAddress());
                }
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
            if (waybill.getStatusView().equals(StatusView.DELIVERYING.getCode()) || waybill.getStatusView()
                .equals(StatusView.WATING_DELIVERY.getCode())) {
                this.getWaybillTraceInfo(waybill);
            }
        } catch (Exception e) {
            log.warn("获取实际运输距离错误.", e);
        }

        info.setWaybill(waybill);

        // 获取转承运商信息
        VendorBo vendorBo = new VendorBo();
        Vendor vendor = findVendorInfo(waybill.getVendorId());
        if(vendor != null){
            org.springframework.beans.BeanUtils.copyProperties(vendor,vendorBo);
        }
        info.setWaybillCostInformationVo(buildWaybillCostInformation(waybill, truckRequire));

        // 获取承运商信息
        Vendor v = findVendorInfo(waybill.getVehicleToVendor());
        vendorBo.setVehicleToVendorName(v == null ? null : v.getVendorName());
        vendorBo.setVehicleToVendorPhone(v == null ? null : v.getContactPhone());

        info.setVendorBo(vendorBo);
        return info;
    }

    // 费用信息
    private WaybillCostInformationVo buildWaybillCostInformation(Waybill waybill, TruckRequire truckRequire) {
        WaybillCostInformationVo costVo = new WaybillCostInformationVo();
        costVo.setCustomerFreightWithTax(null == waybill.getEstimateFreight() ? BigDecimal.ZERO : waybill.getEstimateFreight());
        costVo.setCustomerFreightWithNotTax(waybill.getAfterTaxFreight());
        costVo.setVendorFreightWithTax(waybill.getShow4DriverFreight());
        costVo.setCustomerTaxValue(truckRequire == null ? BigDecimal.ZERO : truckRequire.getTaxRateValue());

        // 客户侧的对账前调整
        AdjustFreightVo customerAdjustBefore = adjustForItemService
            .loadAdjustAbsFreightByWaybillIdAnd(waybill.getWaybillId(), AdjustType.BEFORE.getCode(),
                    AdjustMasterType.CUSTOMER.getCode());
        costVo.setCustomerAdjustFreightBeforeReconciliation(customerAdjustBefore == null ? null :
            customerAdjustBefore.getAdjustFreight());
        costVo.setCustomerAbsAdjustFeightBeforeReconciliation(customerAdjustBefore == null ? null :
            customerAdjustBefore.getAdjustAbsFreight());

        // 客户侧的对账后调整
        AdjustFreightVo customerAdjustAfter = adjustForItemService
            .loadAdjustAbsFreightByWaybillIdAnd(waybill.getWaybillId(), AdjustType.AFTER.getCode(),
                    AdjustMasterType.CUSTOMER.getCode());
        costVo.setCustomerAdjustFreightAfterReconciliation(
            customerAdjustAfter == null ? null : customerAdjustAfter.getAdjustFreight());
        costVo.setCustomerAbsAdjustFreightAfterReconciliation(customerAdjustAfter == null ? null :
            customerAdjustAfter.getAdjustAbsFreight());

        // 承运侧对账前调整
        AdjustFreightVo vendorAdjustBefore = adjustForItemService
            .loadAdjustAbsFreightByWaybillIdAnd(waybill.getWaybillId(), AdjustType.BEFORE.getCode(),
                    AdjustMasterType.VENDOR.getCode());
        costVo.setVendorAdjustFreightBeforeReconciliation(
            vendorAdjustBefore == null ? null : vendorAdjustBefore.getAdjustFreight());
        costVo.setVendorAbsAdjustFreightBeforeReconciliation(vendorAdjustBefore == null ? null :
            vendorAdjustBefore.getAdjustAbsFreight());

        // 承运侧对账后调整
        AdjustFreightVo vendorAdjustAfter = adjustForItemService
            .loadAdjustAbsFreightByWaybillIdAnd(waybill.getWaybillId(), AdjustType.AFTER.getCode(),
                    AdjustMasterType.VENDOR.getCode());
        costVo.setVendorAdjustFreightAfterReconciliation(
            vendorAdjustAfter == null ? null : vendorAdjustAfter.getAdjustFreight());
        costVo.setVendorAbsAdjustFreightAfterReconciliation(
            vendorAdjustAfter == null ? null : vendorAdjustAfter.getAdjustAbsFreight());

        // 最终的调整金额
        WaybillAmount waybillAmount = waybillAmountService.loadByWaybillId(waybill.getWaybillId());

        // 客户侧调整后含税金额：waybillAmount为空显示运单里的客户侧含税金额
        costVo.setCustomerAdjustFreightWithTax(
            waybillAmount == null ? costVo.getCustomerFreightWithTax() : waybillAmount.getLastCustomerFreightWithTax());
        // 客户侧调整后不含税金额：waybillAmount为空显示运单里的客户侧不含税金额
        costVo.setCustomerAdjustFreightWithNotTax(waybillAmount == null ? costVo.getCustomerFreightWithNotTax() : null);

        // 承运侧调整后含税金额：waybillAmount为空则显示运单里的承运侧含税金额
        costVo.setVendorAdjustFreightWithTax(
            waybillAmount == null ? costVo.getVendorFreightWithTax() : waybillAmount.getLastVendorFreightWithTax());

        return costVo;
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
    private Vendor findVendorInfo(Integer vendorId) {
        if (null == vendorId) {
            return null;
        }
        return vmsService.loadByVenorId(vendorId);
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

    // 构建用车要求等信息
    private WaybillDetailInfo buildDetailInfo(Waybill waybill, LoginUser loginUser) {
        WaybillDetailInfo result = new WaybillDetailInfo();
        if (null == waybill) {
            return result;
        }

        this.buildFromVendorWaybill(waybill);
        this.buildTenantName(waybill);

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
        // 发单人
        result.setTruckCustomer(this.findCustomerManger(waybill, loginUser));
        result.setWaybillParam(waybillParamService.findByWaybillId(waybillId));
        result.setExpireTimeLength(customerManWaybillUtils.getExpireTimeLength());
        result.setCanUseCustomerInfo(
            customerInfoService.customerBelongToManager(waybill.getCustomerId(), waybill.getCustomerManagerId()));
        // 只有待配送、配送中的计算预估完成时间
        if (Waybill.StatusView.WATING_DELIVERY.getCode() == waybill.getStatusView()
            || Waybill.StatusView.DELIVERYING.getCode() == waybill.getStatusView()) {
            result.setEstimateFinishTime(
                calculEstimateFinishTime(waybill.getPlanDeliveryTime(), waybill.getEstimateTimeConsumption()));
        }
        return result;
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

    /**
     * 获取即将配送的订单信息
     */
    @Override
    public WaybillBo getDeliveriedWaybill(Integer driverId, LoginUser loginUser) throws BusinessException {
        if (null == driverId) {
            return null;
        }

        List<Integer> statusArray = buildDeliveryingStatus();
        PageCondition pageCondition = new PageCondition();
        Map<String, Object> filters = pageCondition.getFilters();
        pageCondition.setPageNo(1);
        pageCondition.setPageSize(1);
        filters.put("driverId", driverId);
        filters.put("statusList", statusArray);
        pageCondition.setOrderBy("status desc, plan_delivery_time asc");
        List<Waybill> rows = waybillDao.search(pageCondition);
        return rows.size() == 0 ? null : transferToBo(rows.get(0), loginUser);
    }

    /**
     * <p>
     * Title: changeToDeliveried
     * </p>
     * <p>
     * Description: 结束配送
     * </p>
     *
     * @param waybill
     * @param loginUser
     * @throws BusinessException
     * @see
     */
    @Override
    public void changeToDeliveried(Waybill waybill, LoginUser loginUser) throws BusinessException {
        log.info("stand module for changeToDeliveried");
        Waybill dbEntity = check(waybill, true);
        changeToDeliveriedAtBefore(dbEntity, loginUser);

        long estimateTimeConsumption = dbEntity.getEstimateTimeConsumption() == null ? 0
            : dbEntity.getEstimateTimeConsumption();
        long planEndtime = dbEntity.getPlanDeliveryTime().getTime() + estimateTimeConsumption * 60 * 1000;
        if (new Date().getTime() < planEndtime) {
            throw new BusinessException("finishError", "waybill.error.finish");
        }

        Waybill entity = new Waybill();
        entity.setWaybillId(dbEntity.getWaybillId());
        entity.setFinishTime(new Date());
        // 如果是项目结算的运单司机结束配送订单直接完成
        // if (NumberUtils.compare(dbEntity.getReceiptType(), Waybill.ReceiptType.PROJECTPAY.getCode()) == 0) {
        entity.setStatus(Status.PAIED.getCode());
        entity.setStatusView(Waybill.StatusView.FINISH.getCode());
        //进入未对账状态
        entity.setReconciliationStatus(Waybill.ReconciliationStatus.NOT_RECONCILIATION.getCode());
        entity.setReceivableReconcilicationStatus(Waybill.ReconciliationStatus.NOT_RECONCILIATION.getCode());

        waybillCommonService.increaseWaybillCount(dbEntity);

        // 项目计价 非项目计价
        // 专车算价
        // 承运单不重新算价
        if (loginUser != null && loginUser.getTenantId() == 2 && NumberUtils
            .compare(Waybill.WaybillSource.TRANSFORM_BILL.getCode(), dbEntity.getWaybillSource()) != 0) {
            // 重新计算价格
            try {
                DistanceAndPriceData recountThePrice = recountThePrice(dbEntity.getWaybillId(),
                    entity.getActualMileage(), entity.getFinishTime(), loginUser);
                if (null != recountThePrice && null != recountThePrice.getPrice()) {
                    if (recountThePrice.getPrice().compareTo(new BigDecimal("999999.99")) == 1) {
                        entity.setCalculatedFreight(new BigDecimal("999999.99"));
                    } else {
                        entity.setCalculatedFreight(recountThePrice.getPrice());
                    }
                }
            } catch (Exception e) {
                log.warn("结束配送重新计算价格失败", e);
            }
        }

        update(entity, loginUser);

        // 订单完善

        waybillParamService.doCompleteWaybillParam(dbEntity.getWaybillId(), waybill.getValuationConstJson(), loginUser);

        // 更新司机状态为空闲
        waybillCommonService.updateDriverStatusToFree(dbEntity.getDriverId());

        // 清除电子围栏
        waybillAutoFenceServicve.removeAllFenceId(waybill.getWaybillId(), loginUser);

        // 清除用车时间提醒的通知
        String key = RedisKeyConstants.TMS_PLAN_DELIVERY_TIME_REMIND_KEY + "_" + waybill.getWaybillId();
        redisClient.del(key);
        // 推送消息
        msgWithBusinessService.pushFinishWaybillMsg(dbEntity, loginUser);

        // 清楚缓存
        temperatureAlertService.close(dbEntity.getWaybillId() + "," + dbEntity.getPlateNumber());

        // 同步更改转运方的运单信息
        this.modofyTransformBillToDeliveried(dbEntity.getWaybillId(), loginUser);

        // 更改项目为开跑状态
        projectService.updateProjectToRunning(dbEntity.getProjectId(), dbEntity.getPlanDeliveryTime());

        //更新配送日状态
        taskCalendarService.updateByWaybillId(dbEntity.getWaybillId());

        mqAfterCommitSendUtil.afterWaybillOperateChangeCommit(dbEntity.getWaybillId(), WaybillOperateChangeEnum.WAYBILL_FINISH);
    }

    private void changeToDeliveriedAtBefore(Waybill dbEntity, LoginUser loginUser) {
        if (dbEntity == null) {
            throw new BusinessException("waybillNotfound", "waybill.error.notfound");
        }
        if (dbEntity.getStatus() == Waybill.Status.DELIVERIED.getCode()) {
            throw new BusinessException("waybillBeDeliveried", "waybill.error.deliveried");
        }
        if (dbEntity.getStatus() == Waybill.Status.SYS_CANCEL.getCode()) {
            throw new BusinessException("waybillSysCancel", "waybill.error.syscancel");
        }
        if (dbEntity.getStatus() != Waybill.Status.DELIVERYING.getCode()) {
            throw new BusinessException("waybillNotDeliverying", "waybill.error.waybillNotDeliverying");
        }
        Integer driverId = dbEntity.getDriverId();
        if (null == driverId) {
            throw new BusinessException("waybillNotAssignDriver", "waybill.error.waybillNotAssignDriver");
        }
        Driver driver = driverService.findDriverByUserId(loginUser.getUserId());
        if (null == driver) {
            throw new BusinessException("waybillAssignDriverNotExist", "waybill.error.waybillAssignDriverNotExist");
        }
        BaseUtil.checkSelf(driver.getDriverId(), driverId);

        WaybillParam waybillParam = waybillParamService.findByWaybillId(dbEntity.getWaybillId());
        if (null == waybillParam) {
            return;
        }

        // 已经填写了工作量则不判断
        if (StringUtils.isNotBlank(waybillParam.getValuationConstJson())) {
            return;
        }

        if (waybillCheckService.checkProjectIsWorkload(dbEntity.getWaybillId())
                && !waybillCheckService.checkIsDriverWriteWork(dbEntity.getWaybillId())) {
            throw new BusinessException("pleaseNoticeToCustomerManageCompleteWorkLoad",
                    "waybill.error.pleaseNoticeToCustomerManageCompleteWorkLoad");
        }
    }

    // 同步更改转运方的运单信息
    public void modofyTransformBillToDeliveried(Integer transformBillId, LoginUser loginUser) {
        WaybillParam waybillParam = waybillParamService.findByTransformBillLinkId(transformBillId);
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

        waybillCommonService.increaseWaybillCount(waybill);

        update(waybill, loginUser);

        // 推送消息
        msgWithBusinessService.pushFinishWaybillMsg(waybill, loginUser);
    }

    // 构造后台与导出列表的查询条件
    private boolean buildParam(PageCondition pageCondition, LoginUser loginUser) {
        waybillCommonService.structPageCondition(pageCondition, loginUser);
        pageCondition.getFilters().put("backstage", true);
        return true;
    }

    @Override
    public PageConditionDomain buildNewPageCondition(PageCondition pageCondition, LoginEmployee loginEmployee) {
        PageConditionDomain domain = new PageConditionDomain();
        boolean flag = buildParam(pageCondition, loginEmployee);
        domain.setPageCondition(pageCondition);
        domain.setFlag(flag);
        return domain;
    }

    //V3对账后停用
    @Deprecated
    @Override
    public void changeToPaied(Integer waybillId, LoginUser loginUser) throws BusinessException {
        if (null == waybillId) {
            throw new BusinessException("waybillNotfound", "waybill.error.notfound");
        }
        Waybill dbWaybill = waybillDao.get(waybillId);
        if (dbWaybill == null) {
            throw new BusinessException("waybillNotfound", "waybill.error.notfound");
        }
        if (dbWaybill.getStatus() != Waybill.Status.DELIVERIED.getCode()) {
            throw new BusinessException("statusError", "waybill.error.status");
        }
        Waybill entity = new Waybill();
        entity.setReconciliationStatus(Waybill.ReconciliationStatus.HAS_RECONCILIATION.getCode());
        entity.setReceiptStatus(Waybill.ReceiptStatus.HAS_COLLECTION.getCode());
        entity.setSettlementStatus(Waybill.SettlementStatus.HAS_CLEAR.getCode());
        entity.setWaybillId(waybillId);
        entity.setStatus(Waybill.Status.PAIED.getCode());
        entity.setStatusView(Waybill.StatusView.FINISH.getCode());
        update(entity, loginUser);

        // 推送消息
        msgWithBusinessService.pushFinishPayMsg(dbWaybill);
        waybillCommonService.increaseWaybillCount(dbWaybill);
    }

    /**
     * 得到最近使用的5个源地址
     */
    @Override
    public List<WaybillDeliveryAddress> getWaybillDeliveryLastAddress(Integer userId) {
        return waybillDeliveryAddressService.findAllByUserId(userId);
    }

    /**
     * 得到最近使用的5个目的地址
     */
    @Override
    public List<WaybillReceiveAddress> getWaybillReceiveLastAddress(Integer userId) {
        return waybillReceiveAddressService.findAllByUserId(userId);
    }

    /**
     * 获取订单状态
     */
    @Override
    public List<Map<Integer, Object>> getWaybillStatus() {
        List<Map<Integer, Object>> list = new ArrayList<Map<Integer, Object>>();
        Waybill.Status[] rows = Waybill.Status.values();
        for (Waybill.Status row : rows) {
            Map<Integer, Object> map = new HashMap<Integer, Object>();
            map.put(row.getCode(), row.getDescr());
            list.add(map);
        }
        return list;
    }

    @Override
    public Waybill findWaybillByWaybillNo(String waybillNo, LoginUser loginUser) {
        Waybill example = new Waybill();
        example.setWaybillNo(waybillNo);
        List<Waybill> waybills = waybillDao.findByExample(example);
        return waybills.size() > 0 ? waybills.get(0) : null;
    }

    @Override
    public void updateWaybillByWbNo(Waybill waybill, LoginUser loginUser) {
        Waybill wb = findWaybillByWaybillNo(waybill.getWaybillNo(), loginUser);
        if (null == wb) {
            return;
        }

        waybill.setWaybillId(wb.getWaybillId());
        update(waybill, loginUser);
    }

    @Override
    public List<Waybill> getWaybillListByParam(PageCondition pageCondition) {
        return waybillDao.search(pageCondition);
    }

    @Override
    public WaybillCountResponse getFreight(PageCondition pageCondition, LoginUser loginUser) {
        WaybillCountResponse response = new WaybillCountResponse();
        if (null == loginUser || null == loginUser.getTenantId()) {
            return response;
        }
        pageCondition.getFilters().put("tenantId", loginUser.getTenantId());

        Object obj = pageCondition.getFilters().get("waybillIds");
        List<Integer> waybillIdList = obj == null ? null : (List<Integer>) obj;
        if (CollectionUtils.isNotEmpty(waybillIdList)) {
            pageCondition = new PageCondition();
            pageCondition.getFilters().put("waybillIds", waybillIdList);
            response.setTotal(waybillIdList.size());
        } else {
            buildParam(pageCondition, loginUser);
            response.setTotal(waybillDao.searchCount(pageCondition));
        }

        //获取SQL
        String sql = null;
        StringBuilder param = new StringBuilder();
        try{
            sql = SQLUtil.getSql(sqlSessionFactory.getConfiguration(),"com.juma.tgm.waybill.dao.WaybillDao.searchCount",pageCondition);
            param.append("[{\"filters\":{\"queryCondition\":\"")
                    .append(StringUtils.substringAfter(sql, "WHERE")).append("[sql]\"").append("}}]");
        }catch (Exception e){
            log.warn(e.getMessage(), e);
            return response;
        }
        //调用大数据接口
        List<WaybillCountResponse> opsData = bigDataCommonService.getOpsData(opsIdWaybillAmount, param.toString(), WaybillCountResponse.class);
        if(CollectionUtils.isNotEmpty(opsData) && null != opsData.get(0)){
            response.setEstimateFreight(opsData.get(0).getEstimateFreight());
            response.setAfterTaxFreight(opsData.get(0).getAfterTaxFreight());
            response.setShow4DriverFreight(opsData.get(0).getShow4DriverFreight());
        }
        return response;
    }

    /**拆分运单*/
    private List<List<Integer>> splitWaybills( List<Integer> waybillIds){
        List<List<Integer>> result = Lists.newArrayList();
        if ( CollectionUtils.isNotEmpty(waybillIds)){
            if ( waybillIds.size() <= MAX_SPLIT_NUM ){
                // 源List元素数量小于等于目标分组数量
                result.add(waybillIds);
            }else{
                // 计算拆分后list数量
                int splitNum = ( waybillIds.size() % MAX_SPLIT_NUM == 0 ) ? ( waybillIds.size() / MAX_SPLIT_NUM ) : ( waybillIds.size() / MAX_SPLIT_NUM + 1 );
                List<Integer> value = null;
                for ( int i = 0; i < splitNum; i++ ){
                    if ( i < splitNum - 1 ){
                        value = waybillIds.subList( i * MAX_SPLIT_NUM, ( i + 1 ) * MAX_SPLIT_NUM );
                    }else{
                        value = waybillIds.subList( i * MAX_SPLIT_NUM, waybillIds.size() );
                    }
                    result.add(value);
                }
            }
        }
        return result;
    }

    @Override
    public int searchCount(PageCondition pageCondition, LoginUser loginUser) {
        return waybillDao.searchCount(pageCondition);
    }

    @Override
    public void cancelWaybill(Integer waybillId, Waybill.CancelChannel cancelChannel, String waybillCancelRemark,
                              LoginEmployee loginEmployee) {
        Waybill wb = getWaybillAndCheckExist(waybillId);
        wb.setCancelChannel(cancelChannel.getCode());
        wb.setWaybillCancelRemark(waybillCancelRemark);

        if (NumberUtils.compare(Waybill.CancelChannel.BACKGROUND_IMPORT.getCode(), wb.getCancelChannel()) != 0) {
            if (null != wb.getProjectManagerUserId()){
                // 项目单
                if (!wb.getProjectManagerUserId().equals(loginEmployee.getUserId())
                        && !wb.getCreateUserId().equals(loginEmployee.getUserId())
                ) {
                    throw new BusinessException("waybillCanNotSelf", "waybill.error.waybillCanNotSelf");
                }
            } else {
                // 非项目单
                if (!wb.getCreateUserId().equals(loginEmployee.getUserId())) {
                    throw new BusinessException("waybillCanNotSelf", "waybill.error.waybillCanNotSelf");
                }
            }
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

        if (wb.getStatus() == Waybill.Status.PENDING_EXAMINE.getCode()) {
            waybillCommonService.doCancelWaybill(wb, loginEmployee, Status.SYS_CANCEL);
            mqAfterCommitSendUtil.afterWaybillOperateChangeCommit(waybillId,
                WaybillOperateChangeEnum.WAYBILL_CANCEL);
        } else if (Waybill.StatusView.CANCEL.getCode() != wb.getStatusView()) {
            // 判断运单能不能被取消
            Integer statusView = wb.getStatusView();
            if (!allowCancelForBackPersonnel(statusView)) {
                throw new BusinessException("waybillNotCancel", "waybill.error.notCancel");
            }

            // 到仓时间为空，说明司机没有点击到仓且电子围栏没有触碰到仓，判断司机是否迟到
            if (null == wb.getArriveDepotTime()) {
                waybillCommonService.markLate(wb);
            }

            waybillCommonService.doCancelWaybill(wb, loginEmployee, Status.SYS_CANCEL);

            // 取消承运商的承运运单
            this.cancelVendorWaybill(wb, loginEmployee);

            mqAfterCommitSendUtil.afterWaybillOperateChangeCommit(waybillId,
                WaybillOperateChangeEnum.WAYBILL_CANCEL);
        }
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
        Integer statusView = vendorWaybill.getStatusView();

        vendorWaybill.setCancelChannel(waybill.getCancelChannel());
        vendorWaybill.setWaybillCancelRemark(waybill.getWaybillCancelRemark());
        loginUser.setTenantId(vendorWaybill.getTenantId());
        waybillCommonService.doCancelWaybill(vendorWaybill, loginUser, Status.SYS_CANCEL);
    }

    // 判断订单是否可以客户端取消 statusView:运单状态
    // planDeliveryTime:计划配送时间
    // waybillSource:运单来源渠道
    private boolean allowCancelForClient(Integer statusView, Date receivingTime, Integer waybillSource) {
        if (null != statusView) {
            // 若运单派车没成功，则可以直接取消
            if (Waybill.StatusView.DEFAULT.getCode() == statusView) {
                return true;
            } else if (Waybill.StatusView.WATING_RECEIVE.getCode() == statusView) {
                // 运单状态为派车中，可以取消(微信端、客户经理端)
                return true;
            } else if (Waybill.StatusView.WATING_DELIVERY.getCode() == statusView
                && Waybill.WaybillSource.JUMA_CLIENT.getCode() == waybillSource) {
                // 运单被接单时间的半小时之后的时间
                Date date = DateUtil.addMinutes(receivingTime, 30);
                // 客户经理端:运单被接单时间的半小时之内，可以取消
                if (DateUtil.compare(new Date(), date, DateUtil.YYYYMMDDHHMMSS) == -1) {
                    return true;
                }
            }
        }
        return false;
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

    // 是否显示对应的价格百分比
    private boolean buildCompareRate(Waybill waybill) {
        FreightCompareRate rate = freightCompareRateService.findOne(1);
        if (null != rate) {
            BigDecimal compareResult = waybill.getCompareResult();
            BigDecimal compareRate = rate.getCompareRate();
            if (null != compareResult && null != compareRate) {
                return compareResult.compareTo(compareRate) != -1;
            }
        }
        return false;
    }

    // 获取添加的搬运费 ;配送点数 如果司机端没有指定就取后台的数据
    private void buildWaybillParam(WaybillBo bo, Integer waybillId) {
        WaybillParam waybillParam = waybillParamService.findByWaybillId(waybillId);
        if (null != waybillParam) {
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

        } else {
            waybillParam = new WaybillParam();
            List<WaybillReceiveAddress> list = waybillReceiveAddressService.findAllByWaybillId(waybillId);
            waybillParam.setDistributionPointNo(list.size());
        }
        bo.setWaybillParam(waybillParam);
    }

    // 是否能编辑搬运费 后台状态为取消 或者完成状态不能编辑
    private boolean allowAddHandlingCost(Waybill waybill) {
        if (Waybill.Status.PAIED.getCode() == waybill.getStatus()
            || Waybill.Status.CUSTOMER_CANCEL.getCode() == waybill.getStatus()
            || Waybill.Status.SYS_CANCEL.getCode() == waybill.getStatus() || null != waybill.getProjectId()) {
            return false;
        }
        return true;
    }

    // 时间格式转换
    private void buildUseDate(Waybill waybill) {
        if (null == waybill) {
            return;
        }

        // 配送耗时:配送耗时=结束时间-离仓时间;离仓时间优先级：电子围栏>司机操作
        Date leaveDeopt = waybill.getDeliveryTime();

        // 兼容历史数据：3.6.0版本上线之前的数据使用之前的获取到仓时间的方法
        if (DateUtil.parse("2017-09-19 23:59:59", DateUtil.YYYYMMDDHHMMSS).after(waybill.getCreateTime())) {
            WaybillOperateTrack waybillOperateTrack = waybillOperateTrackService.findOperateTrackBy(
                waybill.getWaybillId(), OperateType.LEAVE_DEPOT.getCode(),
                OperateApplication.FRNCE.getCode());
            if (null != waybillOperateTrack) {
                leaveDeopt = waybillOperateTrack.getDeclareTime();
            }
        }

        BigDecimal shippingTime = DateUtil.calDate(leaveDeopt, waybill.getFinishTime(), null);
        waybill.setShippingTime(shippingTime == null ? null : shippingTime.toString());
        // 在仓耗时=离仓-到仓
        BigDecimal driverArrivedLoadingTime = DateUtil.calDate(waybill.getArriveDepotTime(), waybill.getDeliveryTime(),
            null);
        waybill.setDriverArrivedLoadingTime(
            driverArrivedLoadingTime == null ? null : driverArrivedLoadingTime.toString());
    }

    // 用车要求信息CN
    private void buildTruckRequire(WaybillBo waybillBo, TruckRequire truckRequire) {
        if (null != truckRequire) {
            waybillBo.setTruckRequire(truckRequire);
            String functionIds = truckRequire.getAdditionalFunctionIds();
            if (StringUtils.isNotBlank(functionIds)) {
                String[] strings = functionIds.split(",");
                for (String functionId : strings) {
                    AdditionalFunction function = additionalFunctionService
                        .getAdditionFunction(Integer.valueOf(functionId));
                    if (null != function) {
                        String functionKey = function.getFunctionKey();
                        if (StringUtils.isNotBlank(functionKey)) {
                            if (functionKey.equals(AdditionalFunction.FunctionKeys.NEED_CARRY.toString())) {
                                waybillBo.setNeedCarry(function.getFunctionName());
                            } else if (functionKey
                                .equals(AdditionalFunction.FunctionKeys.NEED_BACK_STORAGE.toString())) {
                                waybillBo.setNeedBackStorage(function.getFunctionName());
                            } else if (functionKey
                                .equals(AdditionalFunction.FunctionKeys.COLLECTION_PAYMENT.toString())) {
                                waybillBo.setCollectionPayment(function.getFunctionName());
                            }
                        }
                    }
                }
            }
        }
    }

    // 所属项目
    private void buildCustomerInfo(WaybillBo waybillBo, LoginUser loginUser) {
        CustomerInfo info = customerInfoService.findCusInfoById(waybillBo.getWaybill().getCustomerId());

        if (null != info) {
            this.buildCrmCustomerInfo(info);// 设置crm中企业客户的名称
            waybillBo.setCustomerInfo(info);

            // 客户经理信息从crm系统中获取
            // 2017-04-11 update
            // 客户经理信息应在运单表中取，不再在crm中获取，保证企业客户更换客户经理的时候，运单仍然属于原客户经理
            User user = employeeService.loadUserByEmployeeId(waybillBo.getWaybill().getCustomerManagerId(), loginUser);
            if (null != user) {
                waybillBo.setCustomerManageName(user.getName());
                waybillBo.setCustomerManagePhone(user.getMobileNumber());
            }
        }
    }

    @Override
    public Waybill getWaybill(Integer waybillId) {
        if (null == waybillId) {
            return null;
        }
        Waybill waybill = waybillDao.get(waybillId);
        if (waybill != null && waybill.getWaybillId() != null) {
            WaybillParam waybillParam = waybillParamService.findByWaybillId(waybill.getWaybillId());
            if (waybillParam != null && StringUtils.isNotBlank(waybillParam.getProjectFreightRuleJson())) {
                waybill.setValuationWays(
                    transfer(waybillParam.getProjectFreightRuleJson(), waybillParam.getValuationConstJson()));
            }
        }

        return waybill;

    }

    private List<ValuationWay> transfer(String projectRuleJson, String valuationJson) {
        List<ValuationWay> valuationWays = new ArrayList<ValuationWay>();
        Map<String, FreightFactor> allFactorMap = freightFactorService.mapInputNameToValue();

        if (projectRuleJson == null) {
            return valuationWays;
        }
        Map<String, String> projectRuleJsonMap = JSON.parseObject(projectRuleJson,
            new TypeReference<HashMap<String, String>>() {});

        Integer valWay =  ValuationWayEnum.WORKLOAD.getCode();

        if (projectRuleJsonMap.containsKey("valuationWay")) {
            String valuationWayStr = projectRuleJsonMap.get("valuationWay");
            if (StringUtils.isNotBlank(valuationWayStr)) {
                valWay = Integer.valueOf(valuationWayStr);
            }
        }

        if (projectRuleJsonMap.containsKey("factorJson")) {
            String factorJson = projectRuleJsonMap.get("factorJson");
            HashMap<String, String> factorJsonMap = JSON.parseObject(factorJson,
                new TypeReference<LinkedHashMap<String, String>>() {});
            Map<String, String> valuationJsonMap = new HashMap<String, String>();

            if (StringUtils.isNotBlank(valuationJson)) {
                valuationJsonMap = JSON.parseObject(valuationJson, new TypeReference<HashMap<String, String>>() {});
            }
            for (Map.Entry<String, String> entry : factorJsonMap.entrySet()) {
                String key = entry.getKey();
                ValuationWay valuationWay = new ValuationWay();
                if (NumberUtils.compare(valWay, ValuationWayEnum.FIXED_PRICE.getCode()) == 0) {
                    valuationWay.setLabelInputName(key);
                    valuationWay.setLabelName(ValuationWayEnum.FIXED_PRICE.getDesc());
                    valuationWay.setValue(entry.getValue());
                    valuationWays.add(valuationWay);
                    break;
                }

                if (allFactorMap.containsKey(key)) {// && !key.equals("initiateRate")
                    FreightFactor freightFactor =  allFactorMap.get(key);
                    if (key.equals("initiateRate")) {
                        valuationWay.setValue(entry.getValue());
                    } else {
                        if (valuationJsonMap.containsKey(key)) {
                            valuationWay.setValue(valuationJsonMap.get(key));
                        } else {
                            valuationWay.setValue("0");
                        }
                    }
                    if(freightFactor != null) {
                        valuationWay.setLabelName(freightFactor.getLabelName());// 名字
                        valuationWay.setRequiredMinValue(freightFactor.getRequiredMinValue());
                        valuationWay.setRequiredMaxValue(freightFactor.getRequiredMaxValue());
                    }
                    valuationWay.setLabelInputName(entry.getKey());
                    valuationWays.add(valuationWay);
                }
            }
        }

        return valuationWays;
    }

    @Override
    public Waybill getWaybillAndCheckExist(Integer waybillId) {
        Waybill waybill = getWaybill(waybillId);
        if (null == waybill) {
            throw new BusinessException("waybillNotfound", "waybill.error.notfound");
        }
        return waybill;
    }

    @Override
    public void updateWaybillStatusToDeliverying(Waybill waybill, LoginUser loginUser) {
        // 更新到达计划时间的订单为配送中
        // updateWaybillStatusToDeliverying();
        // 更新没有未被接的订单为超时，并推送通知
        updateOvertimeWaybill(loginUser);
        // 还有30分钟就需要配送的运单给司机推送APP消息
        pushUseCarTimeMsg();
        // 微信72小时未付款运单
        // update:2018-07-24 微信订单去掉
        // hasNoPayWaybillForWechat();
        // 客户经理发布运单10分钟超时无人接单提醒
        customerManWaybillUtils.sendWaitHandleExpireMsg(loginUser);
        // 运单已经超过预计结束时间30分钟，但是司机还没有点击结束的运单提醒
        updateToFinishWaybillMsg();
    }

    // 运单已经超过预计结束时间30分钟，但是司机还没有点击结束的运单提醒
    private void updateToFinishWaybillMsg() {
        // 获取超过预计结束时间30分钟且没有超过32分钟的运单 只通知一次
        List<Integer> waybillIds = driverArriveRedisUtils.waitingToDeliveriedWaybills();
        if (CollectionUtils.isEmpty(waybillIds)) return;//没有数据就直接返回
        WaybillExample example = new WaybillExample();
        example.createCriteria().andWaybillIdIn(waybillIds);
        List<Waybill> list = waybillDao.selectByExample(example);
        long c = new Date().getTime();
        for (Waybill waybill : list) {
            Date d = waybill.getCmEstimateFinishTime();
            if (d == null) continue;
            //忽略已完成、已取消
            if(waybill.getStatusView() == Waybill.StatusView.DELIVERYING.getCode()) {
                long t = d.getTime();
                if (t >= (c + 30 * 60 * 1000l) && t <= (c + 32 * 60 * 1000l)) {
                    // 发送消息
                    msgWithBusinessService.pushWaybillShouldFinishMsg(waybill.getWaybillId());
                    driverArriveRedisUtils.remove(waybill.getWaybillId());
                }
            } else {
                driverArriveRedisUtils.remove(waybill.getWaybillId());
            }
        }
    }

    @Override
    public WaybillInfo hasNoPayWaybill(LoginEmployee loginEmployee) {
        Waybill waybill = new Waybill();
        waybill.setCustomerManagerId(loginEmployee.getEmployeeId());
        Date date = DateUtil.addHours(new Date(), -72);
        waybill.setStatus(Waybill.Status.DELIVERIED.getCode());
        // 程序定时为两分钟执行一次，所有每条数据只有两分钟的执行窗口，确保执行一次
        waybill.setFinishTime(DateUtil.addMinutes(date, -2));
        List<Waybill> list = waybillDao.findByExample(waybill);
        WaybillInfo info = new WaybillInfo();
        if (CollectionUtils.isNotEmpty(list)) {
            info.setIdentification(true);
            info.setWaybill(list.get(0));
        }
        return info;
    }

    @Override
    public Waybill getDeliveringWaybill(int driverId) throws BusinessException {

        Map<String, Object> params = new HashMap<>();
        params.put("driverId", driverId);
        params.put("status", Status.DELIVERYING.getCode());

        List<Waybill> datas = waybillDao.selectEntryList(params);

        if (CollectionUtils.isEmpty(datas)) {
            return null;
        }

        if (datas.size() > 1) {
            throw new BusinessException("waybillMoreThanOne", "errors.data.size");
        }

        return datas.get(0);
    }

    @Override
    public Waybill findWaybillByOwnerUser(LoginEmployee loginEmployee) {
        Waybill example = new Waybill();
        example.setTenantId(loginEmployee.getTenantId());
        example.setCreateUserId(loginEmployee.getUserId());
        example.setStatus(Waybill.Status.NEW.getCode());
        List<Waybill> rows = waybillDao.findByExample(example);
        return rows.isEmpty() ? null : rows.get(0);
    }

    @Override
    public Waybill updateFreight(Waybill waybill, LoginEcoUser loginEcoUser) {
        LoginUser loginUser = new LoginUser(loginEcoUser.getUserId());
        Waybill wb = check(waybill, true);
        BigDecimal newEstimateFreight = waybill.getEstimateFreight();

        checkFright(newEstimateFreight, wb.getEstimateFreight());

        Waybill newWaybill = new Waybill();
        newWaybill.setWaybillId(waybill.getWaybillId());
        newWaybill.setEstimateFreight(newEstimateFreight);
        newWaybill.setCalculatedFreight(wb.getCalculatedFreight());
        newWaybill.setRebateRate(wb.getRebateRate());
        newWaybill.setAfterTaxFreight(truckTypeFreightService.getAfterTaxFright(null, newWaybill));

        // 重新计算司机结算价
        WaybillBo bo = new WaybillBo();
        bo.setWaybill(newWaybill);
        WaybillParam waybillParam = waybillParamService.findByWaybillId(newWaybill.getWaybillId());
        settingExtraFee(bo, waybillParam == null ? new WaybillParam() : waybillParam);

        if (null != newWaybill.getCalculatedFreight()) {
            waybill.setCompareResult(
                BaseUtil.calCompareResult(newWaybill.getCalculatedFreight().multiply(new BigDecimal("1.1")),
                    newWaybill.getEstimateFreight(), true));
        }
        update(newWaybill, loginUser);
        return wb;
    }

    @Override
    public Waybill updateFreight(Waybill waybill, LoginEmployee loginEmployee) throws BusinessException {
        LoginUser loginUser = new LoginUser(loginEmployee.getUserId());
        BigDecimal newEstimateFreight = waybill.getEstimateFreight();
        Waybill wb = waybillDao.get(waybill.getWaybillId());
        if (null == wb) return null;
        BigDecimal oldFreight = wb.getEstimateFreight();

        checkFright(newEstimateFreight, null);

        BigDecimal newShowDriverFreight = waybill.getShow4DriverFreight();
        checkFright(newShowDriverFreight, null);

        Waybill newWaybill = new Waybill();
        newWaybill.setWaybillId(waybill.getWaybillId());
        newWaybill.setCalculatedFreight(wb.getCalculatedFreight());
        newWaybill.setEstimateFreight(newEstimateFreight);
        newWaybill.setShow4DriverFreight(newShowDriverFreight);
        newWaybill.setRebateRate(wb.getRebateRate());
        String updateFreightRemark = waybill.getUpdateFreightRemark();
        // 重置
        newWaybill.setUpdateFreightRemark(StringUtils.isBlank(updateFreightRemark) ? " " : updateFreightRemark);

        // 运单预估费用不修改时，不重新计算税后价格
        if (null != waybill.getEstimateFreight()) {
            newWaybill.setAfterTaxFreight(truckTypeFreightService.getAfterTaxFright(null, newWaybill));
        }

        // 重新计算司机结算价
        if (null == newShowDriverFreight) {
            WaybillBo bo = new WaybillBo();
            bo.setWaybill(newWaybill);
            WaybillParam waybillParam = waybillParamService.findByWaybillId(newWaybill.getWaybillId());
            settingExtraFee(bo, waybillParam == null ? new WaybillParam() : waybillParam);
        }

        if (null != newWaybill.getCalculatedFreight()) {
            waybill.setCompareResult(
                BaseUtil.calCompareResult(newWaybill.getCalculatedFreight().multiply(new BigDecimal("1.1")),
                    newWaybill.getEstimateFreight(), true));
        }
        update(newWaybill, loginUser);

        if (newShowDriverFreight != null && !newShowDriverFreight.equals(wb.getShow4DriverFreight())) {
            msgWithBusinessService.pushChangePriceMsgToDriver(waybill.getWaybillId(), wb.getShow4DriverFreight());
        }

        if (!Constants.CUSTOMER_MANAGER_PERMISSION_KEY.equals(loginEmployee.getLoginPermissionKey())) {
            if (waybill.getEstimateFreight() != null && !waybill.getEstimateFreight().equals(oldFreight)) {
                msgWithBusinessService.pushChangePricelMsgToWaybillOwner(waybill.getWaybillId(), oldFreight, loginUser);
            }
        }
        return wb;
    }

    // 运单价格的验证：freight-前端回传的价格;oldFreight-运单原有的价格，新运单没有此价格，填写null
    private void checkFright(BigDecimal freight, BigDecimal oldFreight) {
        // if (null == freight) {
        // throw new BusinessException("freightNotNull",
        // "waybill.error.freightNotNull");
        // }
        if (null != freight) {
            if (freight.compareTo(new BigDecimal("999999.99")) > 0) {
                throw new BusinessException("freightTooMax", "waybill.error.freightTooMax");
            }
            if (oldFreight != null && freight.compareTo(oldFreight) < 1) {
                throw new BusinessException("updateFreightError", "waybill.error.updateFreightError");
            }
        }
    }

    @Override
    public void payCallback(TransactionResponse response) throws BusinessException {
        // v1cb(response);
        //v2cb(response);
    }

    /*private void v2cb(TransactionResponse response) {
        if (response.getType().equals(com.juma.wallet.common.Constants.PAYMENT_SUCCESS)) {
            log.info("TransactionResponse is {}.", JSON.toJSON(response));
            Waybill waybill = findWaybillByWaybillNo(response.getData().getVoucher().getMerchantOrderNo(),
                Constants.SYS_LOGIN_USER);
            if (waybill == null) {
                throw new BusinessException("waybillNotfound", "waybill.error.notfound");
            }

            if (waybill.getStatus() != Waybill.Status.PAIED.getCode()) {
                Waybill updateWaybill = new Waybill();
                updateWaybill.setWaybillId(waybill.getWaybillId());
                updateWaybill.setPayTransactionTime(new Date());
                // updateWaybill.setFreight(response.getData().getVoucher().getAmount());
                updateWaybill.setStatus(Waybill.Status.PAIED.getCode());
                updateWaybill.setStatusView(Waybill.StatusView.FINISH.getCode());
                updateWaybill.setReceiptType(Waybill.ReceiptType.WEIXINPAY.getCode());
                updateWaybill.setReconciliationStatus(Waybill.ReceiptStatus.HAS_COLLECTION.getCode());
                String customData = response.getData().getVoucher().getCustomData();
                if (StringUtils.isNotBlank(customData)) {
                    Map<String, Integer> map = JSON.parseObject(customData, Map.class);
                    updateWaybill.setPaymentRoute(map.get("paymentRoute"));
                } else {
                    updateWaybill.setPaymentRoute(Waybill.PaymentRoute.OWN_PAYMENT.getCode());
                }
                update(updateWaybill, Constants.SYS_LOGIN_USER);

                Driver driver = driverService.getDriver(waybill.getDriverId());
                if (driver == null) {
                    throw new BusinessException("driverNotfound", "driver.error.not.found");
                }

                messagePushService.payWaybillMessage(waybill.getWaybillId());

                Integer userId = getOwnerUserId(waybill);

                MerchantPaymentRequest request = new MerchantPaymentRequest();
                request.setAmount(waybill.getEstimateFreight());
                request.setDescription(waybill.getWaybillNo());
                request.setTitle("运单收入");
                request.setPayer(new Merchant("LOVEDRIVER"));
                request.setPayee(userService.loadUser(driver.getUserId()));
                paymentService.pay(request);
                // 支付完成用户评价司机
                buildRedis(Constants.APP_USER_PRVEFIEX + Constants.STAR_CUSTOMER + userId + waybill.getTenantId(),
                    waybill.getWaybillId(), waybill.getBusinessBranch());

                // 操作轨迹
                waybillOperateTrackService.insert(waybill.getWaybillId(), OperateType.PAIED,
                    OperateApplication.CUSTOMER_SYS, null, null);
            }
        }
    }*/


    @Override
    public WaybillInfo getWaybillInfo(LoginEcoUser loginEcoUser) {
        Driver driver = driverService.findDriverByUserId(loginEcoUser.getUserId());
        if (driver == null) {
            throw new BusinessException("driver.error.not.found", "driver.error.not.found");
        }
        WaybillInfo info = new WaybillInfo();

        // 组装条件
        PageCondition pageCondition = new PageCondition();
        Map<String, Object> filters = pageCondition.getFilters();
        pageCondition.setOrderBy("status_view desc, plan_delivery_time asc");
        pageCondition.setPageNo(1);
        filters.put("driverId", driver.getDriverId());
        filters.put("tenantId", loginEcoUser.getTenantId());

        // 今日运单数
        int count = todayWaybillInfo(pageCondition);
        info.setDestinationNo(count);
        pageCondition.setPageSize(count);

        // 一条配送中运单与一条待配送的运单(若有)
        buildDeliveryingInfo(info, pageCondition, loginEcoUser);

        // 今日已完成运单数
        pageCondition.getFilters().put("status", Waybill.Status.PAIED.getCode());
        int paiedNo = waybillDao.searchCount(pageCondition);
        info.setPaiedNo(paiedNo);
        return info;
    }

    // 今日运单数
    private Integer todayWaybillInfo(PageCondition pageCondition) {
        List<Integer> statusList = new ArrayList<Integer>();
        statusList.add(Waybill.Status.ASSIGNED.getCode());
        statusList.add(Waybill.Status.RECEIVED.getCode());
        statusList.add(Waybill.Status.DELIVERYING.getCode());
        statusList.add(Waybill.Status.DELIVERIED.getCode());
        statusList.add(Waybill.Status.PAYING.getCode());
        statusList.add(Waybill.Status.PAIED.getCode());
        pageCondition.getFilters().put("statusList", statusList);

        int count = waybillDao.searchCount(pageCondition);

        pageCondition.getFilters().remove("statusList");
        return count;
    }

    @Override
    public void updateOvertimeWaybill(LoginUser loginUser) {
        List<Waybill> list = waybillDao.getOvertimeWaybill();
        for (Waybill waybill : list) {

            loginUser.setTenantId(waybill.getTenantId());

            waybill.setStatus(Waybill.Status.SYS_CANCEL.getCode());
            waybill.setStatusView(Waybill.StatusView.HAS_TIMED_OUT.getCode());
            update(waybill, loginUser);

            // 清空客户经理订单超时提醒
            customerManWaybillUtils.delHandledWaybill(waybill.getWaybillId());
//            messagePushService.timeoutNoReceived(waybill.getWaybillId(), loginUser);
        }
    }

    @Override
    public void updateOvertimeWaybillHasClick(Waybill waybill, LoginUser loginUser) {
        check(waybill, false);
        waybill.setOrderNo(1);
        update(waybill, loginUser);
    }

    @Override
    public PrePaySign doPrePaySign(Waybill waybill) {
        Waybill wb = getWaybill(waybill.getWaybillId());
        if (wb == null) return null;
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put("merchantCode", "LOVEDRIVER");
        parameterMap.put("merchantOrderNo", wb.getWaybillNo());
        parameterMap.put("subject", "运单支付");
        parameterMap.put("body", "运单号：" + wb.getWaybillNo());
        parameterMap.put("amount", wb.getEstimateFreight());
        if (null != waybill.getPaymentRoute()
            && Waybill.PaymentRoute.OTHER_PAYMENT.getCode() == waybill.getPaymentRoute()) {
            Map<String, Integer> map = new HashMap<String, Integer>();
            map.put("paymentRoute", Waybill.PaymentRoute.OTHER_PAYMENT.getCode());
            parameterMap.put("customData", map);
        }
        String prePayJson = JSON.toJSONString(parameterMap);
        log.info("doPrePaySign-prePayJson: {}", prePayJson);
        String encryptString = encryptionService.encrypt(prePayJson);
        String secret = "LOVEDRIVER";
        String verificationString = encryptString + ":@" + secret;
        String sign = null;
        try {
            sign = EncryptionDevice.md5ByHexCode(verificationString);
        } catch (NoSuchAlgorithmException e) {
            log.warn(e.getMessage(), e);
        } catch (UnsupportedEncodingException e) {
            log.warn(e.getMessage(), e);
        }
        log.info("doPrePaySign-encryptString: {}", encryptString);
        log.info("doPrePaySign-sign: {}", sign);
        return new PrePaySign(encryptString, sign);
    }

    @Override
    public void updateArriveDepotTime(Waybill waybill, LoginUser loginUser) {
        Waybill wb = check(waybill, true);

        // 判断运单是否已经到仓:到仓时间不为空，则证明已经到仓
        if (null != wb.getArriveDepotTime()) {
            throw new BusinessException("driverHasArriveDepot", "waybill.error.driverHasArriveDepot");
        }

        this.checkTimeBeforeUpdateArriveDapot(wb.getPlanDeliveryTime());
        checkWaybill(wb, null);
        checkDriver(wb.getDriverId(), loginUser);

        wb.setArriveDepotTime(new Date());
        waybillCommonService.markLate(wb);

        update(wb, loginUser);

        // 标记取货地已到达
        WaybillDeliveryAddress deliveryAddress = waybillDeliveryAddressService.findByWaybillId(wb.getWaybillId());
        if (null != deliveryAddress) {
            deliveryAddress.setIsArrived(WaybillDeliveryAddress.Arrive.YES.getCode());
            waybillDeliveryAddressService.update(deliveryAddress, loginUser);
        }
        msgWithBusinessService.pushArriveDepotMsg(wb, loginUser);

        // 同步给转运方
        this.modifyTransformBillArriveDepotTime(wb.getWaybillId(), loginUser);

        mqAfterCommitSendUtil.afterWaybillOperateChangeCommit(wb.getWaybillId(), WaybillOperateChangeEnum.DRIVER_ARRIVE_DEPOT);
    }

    @Override
    public void checkTimeBeforeUpdateArriveDapot(Date planDeliveryTime) throws BusinessException {
        if (null == planDeliveryTime) {
            return;
        }

        List<ConfParamOption> paramOptions = null;
        // 获取限制的时间
        try {
            paramOptions =
                    confParamService.findParamOptions(Constants.DRIVER_CLICK_ARRIVE_DOPT_TIME_LIMIT_KEY);
        } catch (Exception e) {
            return;
        }

        if (CollectionUtils.isEmpty(paramOptions)) {
            return;
        }

        Integer forwardMin = 0;
        Integer backwardMin = 0;

        for (ConfParamOption p : paramOptions) {
            if (StringUtils.isBlank(p.getOptionName())) {
                continue;
            }
            if (StringUtils.isBlank(p.getOptionValue()) || !StringUtils.isNumeric(p.getOptionValue())) {
                continue;
            }

            if (p.getOptionName().equals("forward")) {
                forwardMin = Integer.parseInt(p.getOptionValue());
            }

            if (p.getOptionName().equals("backward")) {
                backwardMin = Integer.parseInt(p.getOptionValue());
            }
        }

        if (new Date().before(DateUtil.addMinutes(planDeliveryTime, -forwardMin))) {
            throw new BusinessException("dateIsTooSoon", "errors.common.prompt", "距用车时间过早，请勿操作运单到仓！");
        }

        if (new Date().after(DateUtil.addMinutes(planDeliveryTime, backwardMin))) {
            throw new BusinessException("dateIsTooLate", "errors.common.prompt", "超出用车时间过长，无法操作运单到仓！");
        }
    }

    // 同步给转运方
    private void modifyTransformBillArriveDepotTime(Integer transformBillLinkId, LoginUser loginUser) {
        Waybill waybill = waybillCommonService.findWaybillByTransformBillId(transformBillLinkId);
        if (null == waybill) {
            return;
        }

        // 重新组装loginUser
        loginUser.setTenantId(waybill.getTenantId());
        loginUser.setTenantCode(waybill.getTenantCode());

        waybill.setArriveDepotTime(new Date());
        waybillCommonService.markLate(waybill);
        update(waybill, loginUser);

        // 标记取货地已到达
        WaybillDeliveryAddress deliveryAddress = waybillDeliveryAddressService.findByWaybillId(waybill.getWaybillId());
        if (null != deliveryAddress) {
            deliveryAddress.setIsArrived(WaybillDeliveryAddress.Arrive.YES.getCode());
            waybillDeliveryAddressService.update(deliveryAddress, loginUser);
        }
        msgWithBusinessService.pushArriveDepotMsg(waybill, loginUser);
    }

    private void checkDriver(Integer driverId, LoginUser loginUser) {
        Driver driver = driverService.findDriverByUserId(loginUser.getUserId());
        if (driver == null) {
            throw new BusinessException("driver.error.not.found", "driver.error.not.found");
        }
        BaseUtil.checkSelf(driver.getDriverId(), driverId);
    }

    @Override
    public void updateLeaveDepotTime(Waybill waybill, LoginUser loginUser) {
        Waybill wb = check(waybill, true);

        if (null != wb.getDeliveryTime()) {
            // 若司机已经离仓，但运单状态还是待配送，更改运单配送状态为配送中
            if (NumberUtils.compare(wb.getStatusView(), Waybill.StatusView.WATING_DELIVERY.getCode()) == 0
                    || NumberUtils.compare(wb.getStatus(), Waybill.Status.ASSIGNED.getCode()) == 0
                    || NumberUtils.compare(wb.getStatus(), Waybill.Status.RECEIVED.getCode()) == 0) {
                wb.setStatus(Waybill.Status.DELIVERYING.getCode());
                wb.setStatusView(Waybill.StatusView.DELIVERYING.getCode());
                update(wb, loginUser);
                return;
            }
            throw new BusinessException("driverHasLeaveDepot", "waybill.error.driverHasLeaveDepot");
        }

        checkWaybill(wb, OperateType.LEAVE_DEPOT);
        checkDriver(wb.getDriverId(), loginUser);
        this.checkTimeLimit(wb.getArriveDepotTime());

        // 落地配运单重新算价(计算超时等待费)
        // BigDecimal estimateFreight = wb.getEstimateFreight();

        wb.setDeliveryTime(new Date());
        // 将运单更改为配送中
        wb.setStatus(Waybill.Status.DELIVERYING.getCode());
        wb.setStatusView(Waybill.StatusView.DELIVERYING.getCode());

        // 温度监控
        temperatureAlertService.startUp(wb.getWaybillId());

        // calPriceAgain(wb, loginEcoUser);
        update(wb, loginUser);

        // 更新司机状态为配送中
        driverService.updateDriverTaskStatus(wb.getDriverId(), Driver.TaskStatus.DISPATCHING);
        msgWithBusinessService.pushLeaveDepotMsg(wb, loginUser);

        // 若需要经纪人填写，则通知经纪人
        if (waybillCheckService.checkProjectIsWorkload(wb.getWaybillId())
                && !waybillCheckService.checkIsDriverWriteWork(wb.getWaybillId())) {
            msgWithBusinessService.pushNoticeCustomerManageCompleteWork(wb.getWaybillId(), loginUser);
        }

       // 司机离駏写redis，为了以后通知订单时间已经结束，司机没有去点
        driverArriveRedisUtils.driverArriveDept(wb.getWaybillId());
        // 同步给转运方
        this.modifyTransformBillLeaveDepotTime(wb.getWaybillId(), loginUser);

        mqAfterCommitSendUtil.afterWaybillOperateChangeCommit(wb.getWaybillId(), WaybillOperateChangeEnum.DRIVER_LEAVE_DEPOT);
    }

    // 同步给转运方
    private void modifyTransformBillLeaveDepotTime(Integer transformBillLinkId, LoginUser loginUser) {
        Waybill waybill = waybillCommonService.findWaybillByTransformBillId(transformBillLinkId);
        if (null == waybill) {
            return;
        }

        // 重新组装loginUser
        loginUser.setTenantId(waybill.getTenantId());
        loginUser.setTenantCode(waybill.getTenantCode());

        waybill.setDeliveryTime(new Date());
        // 将运单更改为配送中
        waybill.setStatus(Waybill.Status.DELIVERYING.getCode());
        waybill.setStatusView(Waybill.StatusView.DELIVERYING.getCode());
        update(waybill, loginUser);

        // 推送消息
        msgWithBusinessService.pushLeaveDepotMsg(waybill, loginUser);
    }

    // 点击离仓按钮时间限制
    private void checkTimeLimit(Date arriveDepotTime) {
        if (null == arriveDepotTime) {
            return;
        }

        List<ConfParamOption> paramOptions = null;
        // 获取限制的时间
        try {
            paramOptions =
                    confParamService.findParamOptions(Constants.DRIVER_CLICK_LEAVE_DOPT_TIME_LIMIT_KEY);
        } catch (Exception e) {
            return;
        }

        if (CollectionUtils.isEmpty(paramOptions)
                || StringUtils.isBlank(paramOptions.get(0).getOptionValue())
                || !StringUtils.isNumeric(paramOptions.get(0).getOptionValue())) {
            return;
        }

        if (new Date().getTime() <= DateUtil.addMinutes(arriveDepotTime, Integer.parseInt(paramOptions.get(0).getOptionValue())).getTime()) {
            throw new BusinessException("pleaseDoNotClickContinuously", "waybill.error.pleaseDoNotClickContinuously");
        }
    }

    private void checkWaybill(Waybill waybill, OperateType operateType) {

        if (waybill.getBusinessBranch() != null) {
            if (waybill.getBusinessBranch() == Waybill.BusinessBranch.SPECIAL_CAR.getCode()) {
                if (DateUtil.addHours(new Date(), 2).before(waybill.getPlanDeliveryTime())) {
                    throw new BusinessException("timeTooSoon", "waybill.error.timeTooSoon");
                }
                if (null != operateType && OperateType.LEAVE_DEPOT.equals(operateType)
                    && null == waybill.getArriveDepotTime()) {
                    throw new BusinessException("nhotArriveDepot", "waybill.error.notArriveDepot");
                }
            }
        }
        Integer status = waybill.getStatus();
        if (!(Waybill.Status.ASSIGNED.getCode() == status || Waybill.Status.RECEIVED.getCode() == status
            || Waybill.Status.DELIVERYING.getCode() == status)) {
            throw new BusinessException("statusErr", "errors.statusErr");
        }
    }

    @Override
    public WaybillInfo updateConfirmToDepot(Waybill waybill, LoginUser loginUser) {
        WaybillInfo info = new WaybillInfo();
        Waybill wb = getWaybill(waybill.getWaybillId());
        if (null == wb) {
            throw new BusinessException("waybillNotfound", "waybill.error.notfound");
        }
        checkDriver(wb.getDriverId(), loginUser);

        WaybillDeliveryAddress waybillDeliveryAddress = waybillDeliveryAddressService
            .findSimpleByWaybillId(wb.getWaybillId());
        if (null == waybillDeliveryAddress) {
            info.setIdentification(false);
            return info;
        }
        String startCoordinates = waybillDeliveryAddress.getCoordinates();

        String endCoordinates = gaoDeMapService.convertCoordinate(waybill.getLocation(),
            Constants.Coordsys.GPS.toString());

        DistanceAndPriceData data = gaoDeMapService.getDistanceSimple(startCoordinates, endCoordinates);
        if (null != data) {
            Integer distance = data.getDistance();
            info.setIdentification(
                distance == null ? false : (distance.compareTo(Constants.DEFAULT_DISTANCE) == 1 ? true : false));
            info.setDistance(distance);
        }
        return info;
    }

    @Override
    public void pushUseCarTimeMsg() {
        Waybill waybill = new Waybill();
        waybill.setPlanDeliveryTime(DateUtil.addMinutes(new Date(), 30));
        List<Waybill> list = waybillDao.getWillUseCarWaybill(waybill);
        for (Waybill wb : list) {
            Driver driver = driverService.getDriver(wb.getDriverId());
            if (null == driver) {
                continue;
            }

            Map<String, Object> extras = messagePushService.buildAppMsgParameter(wb.getWaybillId());
            LoginUser loginUser = new LoginUser();
            loginUser.setTenantId(wb.getTenantId());
            log.info("30min remind driver :{}.", wb.getWaybillId());
//            if (null != driver.getSmsRemindSwitch()
//                    && NumberUtils.compare(driver.getSmsRemindSwitch(), Driver.RemindSwitchValue.ON.getCode()) == 0) {
//                messagePushService.pushSmsMessage("WILL_BE_DELIVERY", extras, loginUser, driver.getContactPhone());
//            }
            messagePushService.pushAppMessage("WILL_BE_DELIVERY", extras, loginUser, driver.getUserId() + "");
        }
    }

    // 校验参数 callbackWaybill 检查运单是否存在并返回数据中的运单； false：不进行检查
    private Waybill check(Waybill waybill, boolean callbackWaybill) {
        if (null == waybill) {
            throw new BusinessException("validationFailure", "errors.validation.failure");
        }
        Integer waybillId = waybill.getWaybillId();
        if (waybillId == null) {
            throw new BusinessException("validationFailure", "errors.validation.failure");
        }
        if (callbackWaybill) {
            Waybill wb = getWaybill(waybill.getWaybillId());
            if (null == wb) {
                throw new BusinessException("waybillNotfound", "waybill.error.notfound");
            }

            return wb;
        }
        return waybill;
    }

    //V3对账版本后停用
    @Deprecated
    @Override
    public void updateConfirmReceivedFreight(Waybill waybill, LoginEcoUser loginEcoUser) {
        Waybill wb = check(waybill, true);
        Integer waybillId = waybill.getWaybillId();
        if (wb.getStatus() != Waybill.Status.DELIVERIED.getCode()) {
            throw new BusinessException("notDeliveried", "waybill.error.notDeliveried");
        }
        if (NumberUtils.compare(wb.getStatus(), Waybill.Status.PAIED.getCode()) == 0) {
            throw new BusinessException("waybillHasFinished", "waybill.error.waybillHasFinished");
        }
        if (wb.getReceiptType() != Waybill.ReceiptType.OFFLINEPAY.getCode()
            && wb.getReceiptType() != Waybill.ReceiptType.DRIVER_CHEQUES.getCode()) {
            throw new BusinessException("notDriverCheques", "waybill.error.notDriverCheques");
        }
        checkDriver(wb.getDriverId(), loginEcoUser);
        waybill.setStatus(Waybill.Status.PAIED.getCode());
        waybill.setStatusView(Waybill.StatusView.FINISH.getCode());
        // 对账状态
        waybill.setReconciliationStatus(Waybill.ReconciliationStatus.HAS_RECONCILIATION.getCode());
        // 结算状态
        waybill.setReceiptStatus(Waybill.SettlementStatus.HAS_CLEAR.getCode());
        // 收款状态
        waybill.setReceiptStatus(Waybill.ReceiptStatus.HAS_COLLECTION.getCode());
        update(waybill, loginEcoUser);
        // 更新企业客户订单数量
        waybillCommonService.increaseWaybillCount(wb);
    }

    // 配送中或待配送的运单状态集合
    private List<Integer> buildDeliveryingStatus() {
        List<Integer> statusArray = new ArrayList<Integer>();
        statusArray.add(Waybill.Status.RECEIVED.getCode());
        statusArray.add(Waybill.Status.ASSIGNED.getCode());
        statusArray.add(Waybill.Status.DELIVERYING.getCode());
        return statusArray;
    }

    private WaybillInfo buildDeliveryingInfo(WaybillInfo info, PageCondition pageCondition, LoginUser loginUser) {
        if (null == info) {
            info = new WaybillInfo();
        }
        pageCondition.getFilters().put("statusList", buildDeliveryingStatus());

        List<Waybill> rows = waybillDao.search(pageCondition);
        if (CollectionUtils.isNotEmpty(rows)) {
            // 是不是配送中的运单
            boolean isDeliverying = false;
            for (Waybill waybill : rows) {
                if (!isDeliverying && Waybill.Status.DELIVERYING.getCode() == waybill.getStatus()) {
                    WaybillBo bo = transferToBo(waybill, loginUser);
                    bo.setPlanDeliveryDate(
                        DateFormatUtils.format(waybill.getPlanDeliveryTime(), Constants.HHMM.toPattern()));
                    isDeliverying = true;
                    info.setReceiveingWaybill(bo);
                }
                if (Waybill.Status.RECEIVED.getCode() == waybill.getStatus()
                    || Waybill.Status.ASSIGNED.getCode() == waybill.getStatus()) {
                    WaybillBo bo = transferToBo(waybill, loginUser);
                    bo.setPlanDeliveryDate(
                        DateFormatUtils.format(waybill.getPlanDeliveryTime(), Constants.HHMM.toPattern()));
                    info.setNextWaybill(bo);
                    break;
                }
            }
        }
        pageCondition.getFilters().remove("statusList");
        return info;
    }

    @Override
    public Page<WaybillBo> getTodayWaitList(PageCondition pageCondition, LoginEcoUser loginEcoUser) {
        Driver driver = driverService.findDriverByUserId(loginEcoUser.getUserId());
        if (driver == null) {
            throw new BusinessException("driver.error.not.found", "driver.error.not.found");
        }
        pageCondition.getFilters().put("driverId", driver.getDriverId());
        pageCondition.getFilters().put("tenantId", loginEcoUser.getTenantId());
        List<Integer> statusList = new ArrayList<Integer>();
        statusList.add(Waybill.Status.ASSIGNED.getCode());
        statusList.add(Waybill.Status.RECEIVED.getCode());
        pageCondition.getFilters().put("statusList", statusList);
        pageCondition.setOrderBy(" plan_delivery_time asc ");
        return getWaybillList(pageCondition, loginEcoUser);
    }

    private DriverBaseInfo buildDeliverying(DriverBaseInfo info, PageCondition pageCondition) {
        List<Waybill> rows = waybillDao.search(pageCondition);
        if (CollectionUtils.isNotEmpty(rows)) {
            // 是不是配送中的运单
            boolean isDeliverying = false;
            for (Waybill waybill : rows) {
                //
                if (!isDeliverying && Waybill.Status.DELIVERYING.getCode() == waybill.getStatus()) {
                    isDeliverying = true;
                    info.setDeliveryingWaybillId(waybill.getWaybillId());
                }
                if (Waybill.Status.RECEIVED.getCode() == waybill.getStatus()
                    || Waybill.Status.ASSIGNED.getCode() == waybill.getStatus()) {
                    info.setWillDeliveryWaybill(waybill);
                    break;
                }
            }
        }
        return info;
    }

    @Override
    public DriverBaseInfo buildOngoingWaybill(DriverBaseInfo info, LoginEcoUser loginEcoUser) {
        // 配送中或即将配送的运单
        List<Integer> statusArray = buildDeliveryingStatus();
        PageCondition pageCondition = new PageCondition();
        pageCondition.setPageNo(1);
        pageCondition.setPageSize(5);
        Map<String, Object> filters = pageCondition.getFilters();
        Driver driver = driverService.findDriverByUserId(loginEcoUser.getUserId());
        if (driver != null) {
            filters.put("driverId", driver.getDriverId());
        }
        filters.put("tenantId", loginEcoUser.getTenantId());
        filters.put("statusList", statusArray);
        pageCondition.setOrderBy("status desc, plan_delivery_time asc");
        return buildDeliverying(info, pageCondition);
    }

    @Override
    public List<Waybill> findRunningWaybillByDriverId(Integer driverId, Integer maxBackNum, String orderBy,
                                                      LoginUser loginUser) {
        if (null == driverId || null == loginUser || null == loginUser.getTenantId()) {
            return new ArrayList<Waybill>();
        }

        // 查询并组装信息：进行中的运单
        PageCondition page = new PageCondition();
        Map<String, Object> filters = page.getFilters();
        filters.put("driverId", driverId);
        filters.put("tenantId", loginUser.getTenantId());
        filters.put("statusList", buildDeliveryingStatus());
        page.setPageNo(1);
        page.setPageSize(maxBackNum == null ? 10 : maxBackNum);
        page.setOrderBy(StringUtils.isBlank(orderBy) ? " status_view desc, plan_delivery_time asc " : orderBy);
        return waybillDao.search(page);
    }

    @Override
    public List<Waybill> findWaitingAcceptWaybill(LoginUser loginUser) {
        Driver driver = driverService.findDriverByUserId(loginUser.getUserId());
        if (driver == null) {
            throw new BusinessException("driver.error.not.found", "driver.error.not.found");
        }
        WaybillExample example = new WaybillExample();
        example.createCriteria().andTenantIdEqualTo(loginUser.getTenantId()).andDriverIdEqualTo(driver.getDriverId())
            .andStatusEqualTo((byte) Waybill.Status.WAITINT_DRIVER_ANSWER.getCode());
        return waybillCommonService.selectByExample(example);
    }

    @Override
    public WaybillInfo findHomePageInfo(LoginUser loginUser) {
        // 验证参数
        if (null == loginUser || null == loginUser.getUserId()) {
            return null;
        }

        log.info("进行中的运单列表findHomePageInfo-->reqest:{}", JSON.toJSON(loginUser));
        WaybillInfo waybillInfo = new WaybillInfo();
        Driver driver = driverService.findDriverByUserId(loginUser.getUserId());
        // 没有司机，直接返回，昨日信息返回默认值
        if (null == driver) {
            waybillInfo.setYesterdayIncomeInfo(new YesterdayIncomeInfo());
            return waybillInfo;
        }

        List<WaybillInfo> list = new ArrayList<WaybillInfo>();
        List<Waybill> rows = findRunningWaybillByDriverId(driver.getDriverId(), 10, null, loginUser);
        for (Waybill waybill : rows) {
            WaybillInfo info = new WaybillInfo();
            info.setWaybill(waybill);
            info.setTruckRequire(truckRequireService.findTruckRequireByWaybillId(waybill.getWaybillId(), loginUser));
            info.setTruckCustomer(findCustomerManger(waybill, loginUser));
            list.add(info);
        }
        waybillInfo.setWaybillInfoList(list);

        // 若进行中的运单为空，则获取昨日运单信息
        if (list.isEmpty()) {
            waybillInfo.setYesterdayIncomeInfo(buildYesterdayIncomeInfo(driver.getDriverId(), loginUser));
        }

        // 司机收入统计
        waybillInfo.setIncomeStatistics(incomeStatisticsService.findByDriverId(driver.getDriverId()));
        log.info("进行中的运单列表findHomePageInfo-->response:{}", JSON.toJSON(waybillInfo));
        return waybillInfo;
    }

    // 昨日运单信息
    private YesterdayIncomeInfo buildYesterdayIncomeInfo(Integer driverId, LoginUser loginUser) {
        YesterdayIncomeInfo info = new YesterdayIncomeInfo();

        PageCondition pageCondition = new PageCondition();
        pageCondition.getFilters().put("tenantId", loginUser.getTenantId());

        // 运单池数量
        info.setPoolCount(getAcceptableWaybillCount(pageCondition, loginUser));

        // 昨日运单总数(包含配送完成、已完成)
        PageCondition page = new PageCondition();
        Map<String, Object> filters = page.getFilters();
        filters.put("driverId", driverId);
        List<Integer> statusArray = new ArrayList<Integer>();
        statusArray.add(Waybill.Status.DELIVERIED.getCode());
        statusArray.add(Waybill.Status.PAIED.getCode());
        filters.put("statusList", statusArray);
        filters.put("startTime", DateUtil.dayAddStart(-1));
        filters.put("endTime", DateUtil.dayAddEnd(-1));
        info.setYesterdayWaybillTotal(searchCount(page, loginUser));
        log.info("进行中的运单列表YesterdayIncomeInfo-->response:{}", JSON.toJSON(info));
        return info;
    }

    @Override
    public void updateWaybill(WaybillBo waybillBo, LoginEmployee loginEmployee) throws BusinessException {
        Waybill waybill = waybillBo.getWaybill();
        TruckRequire truckRequire = waybillBo.getTruckRequire();
        List<WaybillDeliveryAddress> deliveryAddress = waybillBo.getDeliveryAddress();
        List<WaybillReceiveAddress> receiveAddress = waybillBo.getReceiveAddress();
        if (null == waybill.getPlanDeliveryTime()) {
            throw new BusinessException("planDeliveryTimeNotNull", "waybill.error.planDeliveryTimeNotNull");
        }
        if (waybill == null || truckRequire == null || deliveryAddress == null || receiveAddress == null) {
            throw new BusinessException("errors.validation.failure", "errors.validation.failure");
        }
        // 检查要不要入城证
        waybill.setEntryLicense(truckRequire.getEntryLicense());

        Waybill wb = check(waybill, true);
        if (Waybill.Status.PENDING_EXAMINE.getCode() != wb.getStatus()) {
            throw new BusinessException("WaybillHasReceived", "errors.validation.WaybillHasReceived");
        }
        checkCreateWaybill(waybill, truckRequire, deliveryAddress, receiveAddress);
        receiveAddress = buildReceiveAddress(receiveAddress);
        TruckRequire require = truckRequireService.findTruckRequireByWaybillId(waybill.getWaybillId(), loginEmployee);
        if (null != require) {
            truckRequireService.deleteTruckRequiredByWaybill(waybill.getWaybillId());
        }
        waybill = buildWaybill(waybill, deliveryAddress, receiveAddress);
        // 如果页面没有回传取货地的regionCode，则去取货地信息里面的regionCode
        if (StringUtils.isBlank(waybill.getRegionCode())) {
            String regionCode = deliveryAddress.get(0).getRegionCode();
            if (StringUtils.isBlank(regionCode)) {
                regionCode = regionTgmService.findRegionCodeByCoordinate(deliveryAddress.get(0).getCoordinates());
                if (regionCode.length() > 5) {
                    regionCode = regionCode.substring(0, 5);
                }
                deliveryAddress.get(0).setRegionCode(regionCode);
            }
            waybill.setRegionCode(regionCode);
        }
        this.buildWaybillInfoByReceiveAddressModify(waybill, truckRequire, receiveAddress);
        boolean needReceipt = truckRequireService.needNeedReceipt(truckRequire, null);
        waybill.setNeedReceipt(needReceipt ? Waybill.NeedReceipt.NOT_HAVE_UPLOAD.getCode()
            : Waybill.NeedReceipt.NO_NEED_RECEIPT.getCode());
        setCustomerManagerId(waybill);

        waybillBo.getWaybill().setRebateRate(wb.getRebateRate());
        if (null == waybill.getEstimateFreight()) {
            waybillBo.getWaybill().setEstimateFreight(wb.getEstimateFreight());
        }

        // 返点率入库
        BigDecimal rebateRate = customerInfoService.getRebateRate(waybill.getCustomerId());
        waybill.setRebateRate(rebateRate);

        WaybillParam waybillParam = settingExtraFee(waybillBo, null);

        // 添加重量
        if (null != truckRequire && StringUtils.isNotBlank(truckRequire.getGoodsWeight())) {
            waybill.setGoodsWeight(
                new BigDecimal(truckRequire.getGoodsWeight()).multiply(new BigDecimal("1000")).intValue());
        }
        // 添加体积
        if (null != truckRequire && StringUtils.isNotBlank(truckRequire.getGoodsVolume())) {
            waybill.setGoodsVolume(new BigDecimal(truckRequire.getGoodsVolume()));
        }

        // 客户名称
        CustomerInfo customerInfo = customerInfoService.findCusInfoById(waybill.getCustomerId());
        if (null != customerInfo) {
            waybill.setCustomerName(customerInfo.getCustomerName());
        }

        update(waybill, loginEmployee);
        Integer waybillId = waybill.getWaybillId();
        if (null == waybill.getCustomerId()) {
            waybillDao.removeCustomerId(waybillId);
        }
        waybillDeliveryAddressService.delete(waybillId);
        waybillDeliveryAddressService.insert(deliveryAddress, waybillId, loginEmployee);
        this.updateWaybillReceiveAddress(loginEmployee, receiveAddress, waybillId);
        // 用车要求
        truckRequire.setWaybillId(waybillId);
        if (StringUtils.isNotBlank(truckRequire.getGoodsWeight())) {
            truckRequire.setGoodsWeight(truckRequire.getGoodsWeight().trim());
        }

        String goodsVolume = truckRequire.getGoodsVolume();
        if (StringUtils.isNotBlank(goodsVolume)) {
            truckRequire.setGoodsVolume(goodsVolume);
        }

        this.saveOrUpdateTruckRequire(truckRequire);
        waybillCommonService.saveWaybillParam(waybillParam, waybill, loginEmployee);
    }

    /**
     * 更新收货地
     *
     * @param loginUser
     * @param receiveAddress
     * @param waybillId
     */
    private void updateWaybillReceiveAddress(LoginUser loginUser, List<WaybillReceiveAddress> receiveAddress,
                                             Integer waybillId) {
        waybillReceiveAddressService.deleteByWaybillId(waybillId);
        List<WaybillReceiveAddressResponse> listAddressResponse = new ArrayList<WaybillReceiveAddressResponse>();
        for (WaybillReceiveAddress address : receiveAddress) {
            listAddressResponse.add(new WaybillReceiveAddressResponse(address, null));
        }
        waybillReceiveAddressService.insert(listAddressResponse, waybillId, loginUser);
    }

    /**
     * 修改税后价格，最后配送点坐标
     *
     * @param waybill
     * @param truckRequire
     * @param receiveAddress
     */
    private void buildWaybillInfoByReceiveAddressModify(Waybill waybill, TruckRequire truckRequire,
                                                        List<WaybillReceiveAddress> receiveAddress) {
        waybill.setAfterTaxFreight(truckTypeFreightService.getAfterTaxFright(truckRequire, waybill));
        // 如果司机报价为空则设置为税后费用
        if (waybill.getShow4DriverFreight() == null) {
            waybill.setShow4DriverFreight(waybill.getAfterTaxFreight());
        }
        if (null != waybill.getCalculatedFreight()) {
            waybill.setCompareResult(
                BaseUtil.calCompareResult(waybill.getCalculatedFreight().multiply(new BigDecimal("1.1")),
                    waybill.getEstimateFreight(), true));
        }
    }

    /**
     * 通过企业id设置订单所属客户经理
     *
     * @param waybill
     */
    private void setCustomerManagerId(Waybill waybill) {
        // 保存客户经理
        if (waybill.getCustomerId() != null) {
            CustomerInfo customerInfo = customerInfoService.findCusInfoById(waybill.getCustomerId());
            if (customerInfo != null) {
                waybill.setCustomerManagerId(customerInfo.getCustomerManagerUserId());
                // 写入租户信息
                waybill.setTenantId(customerInfo.getTenantId());
                waybill.setTenantCode(customerInfo.getTenantCode());
            }
        }
    }

    // 后台完善运单验证
    @Override
    public void checkCreateWaybill(Waybill waybill, TruckRequire truckRequire,
                                   List<WaybillDeliveryAddress> deliveryAddress, List<WaybillReceiveAddress> receiveAddress) {
        if (null == truckRequire) {
            throw new BusinessException("truckTypeMustSelect", "truckTypeFreight.not.truckTypeMustSelect");
        }
        Date planDeliveryTime = waybill.getPlanDeliveryTime();
        if (DateUtil.checkOvertime(planDeliveryTime, (20 * 60)) == -1) {
            throw new BusinessException("planDeliveryTimeToLess", "waybill.error.planDeliveryTimeToLess");
        }
        if (StringUtils.isBlank(truckRequire.getGoodsType())) {
            throw new BusinessException("validationFailure", "errors.validation.goodsType");
        }

        if (StringUtils.isBlank(truckRequire.getGoodsWeight())
            || !BaseUtil.isNumOrDecimal(truckRequire.getGoodsWeight())
            || new BigDecimal(truckRequire.getGoodsWeight()).compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException("notNumeric", "errors.validation.notNumeric", "重量");
        }
        if (StringUtils.isNotBlank(truckRequire.getGoodsVolume())) {
            if (!BaseUtil.isNumOrDecimal(truckRequire.getGoodsVolume())
                && new BigDecimal(truckRequire.getGoodsVolume()).compareTo(BigDecimal.ZERO) <= 0) {
                throw new BusinessException("notNumeric", "errors.validation.notNumeric", "体积");
            }
        }
        if (CollectionUtils.isEmpty(deliveryAddress)) {
            throw new BusinessException("validationFailure", "errors.validation.srcAddress");
        }
        WaybillDeliveryAddress address = deliveryAddress.get(0);
        if (StringUtils.isBlank(address.getAddressName()) || StringUtils.isBlank(address.getAddressDetail())
            || StringUtils.isBlank(address.getCoordinates())) {
            throw new BusinessException("addressError", "errors.addressError");
        }

        if (StringUtils.isBlank(address.getContactName())) {
            throw new BusinessException("canNotBeBlank", "errors.validation.canNotBeBlank", "取货地联系人");
        }
        if (!BaseUtil.checkMobilePhone(address.getContactPhone())
            && !BaseUtil.checkTelephone(address.getContactPhone())) {
            throw new BusinessException("contactPhoneFmtError", "errors.contactPhoneFmtError", "取货地");
        }
        if (CollectionUtils.isEmpty(receiveAddress)) {
            throw new BusinessException("validationFailure", "errors.validation.toAddress");
        } else {
            // 检查收货地
            for (WaybillReceiveAddress waybillReceiveAddress : receiveAddress) {
                if (StringUtils.isBlank(waybillReceiveAddress.getAddressName())
                    || StringUtils.isBlank(waybillReceiveAddress.getAddressDetail())
                    || StringUtils.isBlank(waybillReceiveAddress.getCoordinates())) {
                    throw new BusinessException("addressError", "errors.validation.toAddress");
                }
            }
        }
        Integer receiptType = waybill.getReceiptType();
        if (null == receiptType) {
            throw new BusinessException("canNotBeBlank", "errors.validation.canNotBeBlank", "结算方式");
        }
    }

    // 获取预估时间
    private Waybill buildWaybill(Waybill waybill, List<WaybillDeliveryAddress> deliveryAddress,
                                 List<WaybillReceiveAddress> receiveAddress) {
        CityAdressData formAddress = new CityAdressData();
        formAddress.setCoordinate(deliveryAddress.get(0).getCoordinates());
        List<CityAdressData> toAddresss = new ArrayList<CityAdressData>();
        for (WaybillReceiveAddress address : receiveAddress) {
            CityAdressData ads = new CityAdressData();
            ads.setCoordinate(address.getCoordinates());
            toAddresss.add(ads);
        }
        DistanceAndPriceData data = gaoDeMapService.getDistanceInfo(formAddress, toAddresss);
        if (null != data) {
            waybill.setEstimateTimeConsumption(BaseUtil.strToNum(data.getDuration()));
            waybill.setEstimateDistance(data.getDistance());
        }
        return waybill;
    }

    // 构建目的地
    private List<WaybillReceiveAddress> buildReceiveAddress(List<WaybillReceiveAddress> receiveAddress) {
        List<WaybillReceiveAddress> newReceiveAddress = new ArrayList<WaybillReceiveAddress>();
        for (WaybillReceiveAddress address : receiveAddress) {
            if (StringUtils.isNotBlank(address.getAddressName())) {
                newReceiveAddress.add(address);
            }
            if (StringUtils.isNotBlank(address.getContactPhone())
                && !BaseUtil.checkMobilePhone(address.getContactPhone())
                && !BaseUtil.checkTelephone(address.getContactPhone())) {
                throw new BusinessException("contactPhoneFmtError", "errors.contactPhoneFmtError");
            }
        }
        return newReceiveAddress;
    }

    @Override
    public void saveWaybillSnapshot(WaybillBo waybillBo, LoginEmployee loginEmployee) throws BusinessException {
        Waybill waybill = waybillBo.getWaybill();
        String planDeliveryDate = waybillBo.getPlanDeliveryDate();
        if (StringUtils.isNotBlank(planDeliveryDate)) {
            waybill.setPlanDeliveryTime(DateUtil.parse(planDeliveryDate, Constants.YYYYMMDDHHMM.toPattern()));
        }
        TruckRequire truckRequire = waybillBo.getTruckRequire();
        // 检查要不要入城证
        waybill.setEntryLicense(truckRequire.getEntryLicense());
        List<WaybillDeliveryAddress> deliveryAddress = waybillBo.getDeliveryAddress();
        List<WaybillReceiveAddress> receiveAddress = waybillBo.getReceiveAddress();
        Waybill wb = check(waybill, true);
        if (Waybill.Status.PENDING_EXAMINE.getCode() != wb.getStatus()) {
            throw new BusinessException("WaybillHasReceived", "errors.validation.WaybillHasReceived");
        }
        Integer waybillId = waybill.getWaybillId();
        if (CollectionUtils.isNotEmpty(receiveAddress)) {
            receiveAddress = buildReceiveAddress(receiveAddress);
            if (CollectionUtils.isNotEmpty(receiveAddress)) {
                this.updateWaybillReceiveAddress(loginEmployee, receiveAddress, waybillId);
            } else {
                waybillReceiveAddressService.deleteByWaybillId(waybillId);
            }
        }

        // 如果页面没有回传取货地的regionCode，则去取货地信息里面的regionCode
        if (CollectionUtils.isNotEmpty(deliveryAddress)
            && StringUtils.isNotBlank(deliveryAddress.get(0).getAddressDetail())) {
            if (StringUtils.isBlank(waybill.getRegionCode())) {
                String regionCode = deliveryAddress.get(0).getRegionCode();
                if (StringUtils.isBlank(regionCode)) {
                    regionCode = regionTgmService.findRegionCodeByCoordinate(deliveryAddress.get(0).getCoordinates());
                    if (regionCode.length() > 5) {
                        regionCode = regionCode.substring(0, 5);
                    }
                    deliveryAddress.get(0).setRegionCode(regionCode);
                }
                waybill.setRegionCode(regionCode);
            }
            waybillDeliveryAddressService.delete(waybillId);
            waybillDeliveryAddressService.insert(deliveryAddress, waybillId, loginEmployee);
        } else {
            waybillDeliveryAddressService.delete(waybillId);
        }
        waybill.setAfterTaxFreight(truckTypeFreightService.getAfterTaxFright(truckRequire, waybill));
        boolean needReceipt = truckRequireService.needNeedReceipt(truckRequire, null);
        waybill.setNeedReceipt(needReceipt ? Waybill.NeedReceipt.NOT_HAVE_UPLOAD.getCode()
            : Waybill.NeedReceipt.NO_NEED_RECEIPT.getCode());

        // 添加重量
        if (null != truckRequire && StringUtils.isNotBlank(truckRequire.getGoodsWeight())) {
            waybill.setGoodsWeight(
                new BigDecimal(truckRequire.getGoodsWeight()).multiply(new BigDecimal("1000")).intValue());
        }
        // 添加体积
        if (null != truckRequire && StringUtils.isNotBlank(truckRequire.getGoodsVolume())) {
            waybill.setGoodsVolume(new BigDecimal(truckRequire.getGoodsVolume()));
        }

        // 客户名称
        CustomerInfo customerInfo = customerInfoService.findCusInfoById(waybill.getCustomerId());
        if (null != customerInfo) {
            waybill.setCustomerName(customerInfo.getCustomerName());
        }

        update(waybill, loginEmployee);
        if (null == waybill.getCustomerId()) {
            waybillDao.removeCustomerId(waybillId);
        }

        // 用车要求
        TruckRequire require = truckRequireService.findTruckRequireByWaybillId(waybill.getWaybillId(), loginEmployee);
        if (null != require) {
            truckRequireService.deleteTruckRequiredByWaybill(waybill.getWaybillId());
        }
        truckRequire.setWaybillId(waybillId);
        String goodsWeight = truckRequire.getGoodsWeight();
        String goodsVolume = truckRequire.getGoodsVolume();
        if (StringUtils.isNotBlank(goodsWeight)) {
            if (!BaseUtil.isNumOrDecimal(truckRequire.getGoodsWeight())
                && new BigDecimal(truckRequire.getGoodsWeight()).compareTo(BigDecimal.ZERO) <= 0) {
                throw new BusinessException("notNumeric", "errors.validation.notNumeric", "重量");
            }
            truckRequire.setGoodsWeight(goodsWeight);
        }
        if (StringUtils.isNotBlank(goodsVolume)) {
            if (!BaseUtil.isNumOrDecimal(truckRequire.getGoodsVolume())
                && new BigDecimal(truckRequire.getGoodsVolume()).compareTo(BigDecimal.ZERO) <= 0) {
                throw new BusinessException("notNumeric", "errors.validation.notNumeric", "体积");
            }
            truckRequire.setGoodsVolume(goodsVolume);
        }
        saveOrUpdateTruckRequire(truckRequire);
    }

    private void saveOrUpdateTruckRequire(TruckRequire truckRequire) {
        if (truckRequire.getTruckRequireId() == null) {
            truckRequireService.insert(truckRequire);
        } else {
            truckRequireService.update(truckRequire);
            truckRequireService.removeNullInfo(truckRequire);
        }
    }

    @Override
    public void getWaybillTraceInfo(Waybill waybill) {
        waybillTrackService.getWaybillTraceInfo(waybill);

    }

    @Override
    public PageCondition buildDataFilterCondByDepartment(PageCondition pageCondition, CurrentUser currentUser) {
        return pageCondition;
    }

    @Override
    public void updateToHasNeedReceipt(Integer waybillId, LoginUser loginUser) {
        Waybill waybill = new Waybill();
        waybill.setWaybillId(waybillId);
        // 验证运单是否存在
        Waybill wb = check(waybill, true);

        if (null != wb.getNeedReceipt() && Waybill.NeedReceipt.NO_NEED_RECEIPT.getCode() == wb.getNeedReceipt()) {
            throw new BusinessException("WaybillNotNeedReceiptErr", "waybill.eroor.WaybillNotNeedReceiptErr");
        }

        if (wb.getStatusView() != Waybill.StatusView.WATING_DELIVERY.getCode()
            && wb.getStatusView() != Waybill.StatusView.DELIVERYING.getCode()
            && wb.getStatusView() != Waybill.StatusView.WATING_PAY.getCode()
            && wb.getStatusView() != Waybill.StatusView.FINISH.getCode()) {
            throw new BusinessException("WaybillNotNeedReceiptErr", "waybill.eroor.WaybillNotNeedReceiptErr");
        }

        // 修改运单
        waybill.setNeedReceipt(Waybill.NeedReceipt.HAS_NEED_RECEIPT.getCode());
        update(waybill, loginUser);

        // 判断运单是否是转运方的运单,若是则同步更改承运商的运单信息
        WaybillParam transformBillParam = waybillParamService.findByWaybillId(waybillId);
        if (null != transformBillParam && null != transformBillParam.getTransformBillLinkId()) {
            Waybill transformBill = new Waybill();
            transformBill.setWaybillId(transformBillParam.getTransformBillLinkId());
            transformBill.setNeedReceipt(Waybill.NeedReceipt.HAS_NEED_RECEIPT.getCode());
            waybillCommonService.update(transformBill, loginUser);
        }

        // 判断运单是否是承运商的运单,若是则同步更改转运方的运单信息
        if (NumberUtils.compare(wb.getWaybillSource(), Waybill.WaybillSource.TRANSFORM_BILL.getCode()) == 0) {
            WaybillParam vendorBillParam = waybillParamService.findByTransformBillLinkId(waybillId);
            if (null != vendorBillParam) {
                Waybill vendorBill = new Waybill();
                vendorBill.setWaybillId(vendorBillParam.getWaybillId());
                vendorBill.setNeedReceipt(Waybill.NeedReceipt.HAS_NEED_RECEIPT.getCode());
                waybillCommonService.update(vendorBill, loginUser);
            }
        }
    }

    @Override
    public void updateAssignCarFeedback(Waybill waybill, LoginUser loginUser) {
        if (StringUtils.isBlank(waybill.getAssignCarFeedback())) {
            throw new BusinessException("assignCarFeedbackNotNull", "errors.assignCarFeedbackNotNull");
        }
        update(waybill, loginUser);
        Waybill originData = getWaybill(waybill.getWaybillId());
        managerScheduleService.saveOrUpdate(managerScheduleService.buildAssignFeedbackSchedule(originData,
            ManagerScheduleType.WAYBILL_TYPE_ASSIGN_FEEDBACK_TPL), loginUser);
        // 推送消息
        msgWithBusinessService.pushNotAssignCarFeedback(this.getWaybill(waybill.getWaybillId()), loginUser);

        // 更改被承运的运单信息
        Waybill transformBill = waybillCommonService.findWaybillByTransformBillId(waybill.getWaybillId());
        if (null == transformBill) {
            return;
        }

        // 重新组装loginUser
        loginUser.setTenantId(waybill.getTenantId());
        loginUser.setTenantCode(waybill.getTenantCode());

        transformBill.setAssignCarFeedback(waybill.getAssignCarFeedback());
        waybillCommonService.update(transformBill, loginUser);

        managerScheduleService.saveOrUpdate(managerScheduleService.buildAssignFeedbackSchedule(transformBill,
            ManagerScheduleType.WAYBILL_TYPE_ASSIGN_FEEDBACK_TPL), loginUser);
        // 推送消息
        msgWithBusinessService.pushNotAssignCarFeedback(transformBill, loginUser);
    }

    @Override
    public void modifyPlanDeliveryTimeByManager(Waybill waybill, LoginEmployee loginEmployee) throws BusinessException {
        modifyPlanDeliveryTime(waybill, loginEmployee);
        // 发送推送消息
        Waybill dbWaybill = this.getWaybill(waybill.getWaybillId());
        msgWithBusinessService.modifyPlanDeliveryTimeMsg(dbWaybill);
    }

    @Override
    public void modifyPlanDeliveryTimeBydriver(Waybill waybill, LoginEcoUser driverLoginEcoUser)
        throws BusinessException {
        modifyPlanDeliveryTime(waybill, driverLoginEcoUser);
    }

    private void modifyPlanDeliveryTime(Waybill waybill, LoginUser loginUser) throws BusinessException {
        if (waybill == null) {
            throw new BusinessException("waybillNull", "errors.paramError");
        }
        // this.canModifyPlanDeliveryTime(waybill);
        Waybill dbWaybill = this.getWaybill(waybill.getWaybillId());

        if (dbWaybill == null) {
            throw new BusinessException("waybillIdError", "errors.paramError");
        }
        if (waybill.getPlanDeliveryTime() == null) {
            throw new BusinessException("PlanDeliveryTimeError", "errors.paramErrorWithName", "计划用车时间");
        }

        // 计算并修改预估完成时间
        Date planTime = dbWaybill.getPlanDeliveryTime();
        Date guessEndTime = dbWaybill.getCmEstimateFinishTime();
        if (planTime != null && guessEndTime != null) {
            Long interval = guessEndTime.getTime() - planTime.getTime();
            if (interval < 0) interval = 0L;
            Date newPlanTime = waybill.getPlanDeliveryTime();

            Date newGuessEndTime = DateUtils.addMilliseconds(newPlanTime, interval.intValue());

            dbWaybill.setCmEstimateFinishTime(newGuessEndTime);
        }
        // 设置新的计划配送时间
        dbWaybill.setPlanDeliveryTime(waybill.getPlanDeliveryTime());

        this.update(dbWaybill, loginUser);

        // 修改电子围栏启动时间
        waybillAutoFenceServicve.changeDeliveryStartTime(waybill.getWaybillId(), loginUser);
        // 同步转运方用车时间
        this.modifyTransformBillPlanDeliveryTime(waybill.getWaybillId(), waybill.getPlanDeliveryTime(),
                dbWaybill.getCmEstimateFinishTime(), loginUser);
        // 同步承运方用车时间
        this.modifyVendorBillPlanDeliveryTime(waybill.getWaybillId(), waybill.getPlanDeliveryTime(),
                dbWaybill.getCmEstimateFinishTime(), loginUser);
    }

    // 乙方修改，用车时间同步给转运方
    private void modifyTransformBillPlanDeliveryTime(Integer transformBillLinkId, Date planDeliveryTime,
            Date cmEstimateFinishTime, LoginUser loginUser) {
        Waybill waybill = waybillCommonService.findWaybillByTransformBillId(transformBillLinkId);
        if (null == waybill) {
            return;
        }

        waybill.setPlanDeliveryTime(planDeliveryTime);
        waybill.setCmEstimateFinishTime(cmEstimateFinishTime);
        waybillCommonService.update(waybill, loginUser);
    }

    // 甲方修改，用车时间同步给承运方
    private void modifyVendorBillPlanDeliveryTime(Integer transformBillId, Date planDeliveryTime,
            Date cmEstimateFinishTime, LoginUser loginUser) {
        WaybillParam transformBillParam = waybillParamService.findByWaybillId(transformBillId);
        if (null == transformBillParam) {
            return;
        }

        Waybill vendorBill = waybillCommonService.getWaybillById(transformBillParam.getTransformBillLinkId());
        if (null == vendorBill) {
            return;
        }

        vendorBill.setPlanDeliveryTime(planDeliveryTime);
        vendorBill.setCmEstimateFinishTime(cmEstimateFinishTime);
        waybillCommonService.update(vendorBill, loginUser);
    }

    @Override
    public List<Waybill> findRunningWaybillByTruckId(Integer truckId) {
        if (null != truckId) {
            return waybillDao.findRunningWaybillByTruckId(truckId);
        }
        return new ArrayList<Waybill>();
    }

    @Override
    public boolean allowChangeCar(Waybill waybill) {
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

        Date planDeliveryTime = waybill.getPlanDeliveryTime();
        // 当前时间X分钟之后的时间
        List<ConfParamOption> options = confParamService.findParamOptions(Constants.TIME_LIMIT_ALLOW_UPDATE_CAR_KEY);
        String timeLimit = null;
        if (CollectionUtils.isNotEmpty(options)) {
            timeLimit = options.get(0).getOptionValue();
        }
        Date date = DateUtil.addMinutes(new Date(), StringUtils.isNotBlank(timeLimit) ? Integer.parseInt(timeLimit)
            : Constants.TIME_LIMIT_ALLOW_UPDATE_CAR);
        // 能够换车的条件：用车时间X分钟之前
        if (null == planDeliveryTime || !date.before(planDeliveryTime)) {
            return false;
        }
        return true;
    }

    @Override
    public WaybillMonitor fetchWaybill(String waybillNo) {
        WaybillMonitor waybillMonitor = new WaybillMonitor();
        Waybill waybill = findWaybillByWaybillNo(waybillNo, new LoginUser());
        if (waybill == null) {
            throw new BusinessException("waybill.error.notfound", "waybill.error.notfound");
        }
        waybillMonitor.setWaybillId(waybill.getWaybillId());
        waybillMonitor.setTruckId(waybill.getTruckId());
        waybillMonitor.setWaybillNo(waybill.getWaybillNo());

        waybillMonitor.setDeliveryAddr(waybillDeliveryAddressService.findAllByWaybillId(waybill.getWaybillId()));
        waybillMonitor.setReceiveAddr(waybillReceiveAddressService.findAllByWaybillId(waybill.getWaybillId()));

        Truck truck = truckService.getTruck(waybill.getTruckId());
        if (truck == null) {
            throw new BusinessException("truck.error.not.found", "truck.error.not.found");
        }
        waybillMonitor.setPlateNumber(truck.getPlateNumber());

        Driver driver = driverService.getDriver(waybill.getDriverId());
        if (driver == null) {
            throw new BusinessException("driver.error.not.found", "driver.error.not.found");
        }
        waybillMonitor.setDriverName(driver.getNickname());
        waybillMonitor.setDriverPhone(driver.getContactPhone());
        // 司机位置

        RealTimePosition position = realTimePositionService.queryLastPosByPlateNum(truck.getPlateNumber());
        if (position != null) {
            waybillMonitor.setPoi(position.getLng() + "," + position.getLat());
        }
        waybillMonitor.setStatusView(waybill.getStatusView());
        return waybillMonitor;
    }

    @Override
    public void updateWaybillByReceiveAddress(WaybillBo waybillBo, LoginUser loginUser) {
        Waybill waybill = this.getWaybill(waybillBo.getWaybillId());
        if (waybill == null) {
            throw new BusinessException("waybill.error.notfound", "errors.notFound");
        }
        List<WaybillReceiveAddress> receiveAddressList = waybillBo.getReceiveAddress();
        if (CollectionUtils.isEmpty(receiveAddressList)) {
            throw new BusinessException("ReceiveAddressNull", "errors.paramCanNotNull");
        }

        waybillBo.setWaybill(waybill);
        WaybillDeliveryAddress start = waybillDeliveryAddressService.findByWaybillId(waybill.getWaybillId());

        // 更新预估时间，公里数
        this.buildWaybill(waybill, Arrays.asList(new WaybillDeliveryAddress[]{start}), receiveAddressList);

        // 只有专车租户执行重新算价
        // 承运商的运单不应该执行算价程序
        if (NumberUtils.compare(waybill.getWaybillSource(), Waybill.WaybillSource.TRANSFORM_BILL.getCode()) != 0
                && waybill.getTenantId() == 2) {
            DistanceAndPriceData distanceAndPriceData = this.getDistanceAndPriceData(waybillBo, receiveAddressList,
                    start, loginUser);

            if (distanceAndPriceData.getPrice() != null) {
                // 更新系统报价
                waybill.setCalculatedFreight(distanceAndPriceData.getPrice());
            }

            // 更新费用信息
            this.buildWaybillInfoByReceiveAddressModify(waybill, waybillBo.getTruckRequire(), receiveAddressList);
        }

        // 标记线路已修改
        waybill.setIsChangeDeliveryPoint(Waybill.ChangeDeliveryPoint.HAS_UPDATE.getCode());
        this.update(waybill, loginUser);
        // 更新配送点信息
        this.updateWaybillReceiveAddress(loginUser, receiveAddressList, waybill.getWaybillId());

        // 操作轨迹
        waybillOperateTrackService.insert(waybill.getWaybillId(), OperateType.MODIFY_RECEIVE_POINT,
            OperateApplication.BACKGROUND_SYS, null, loginUser);
    }

    /**
     * 重新计算费用
     *
     * @param waybillBo          waybill 不能为空
     * @param receiveAddressList 配送地 不能为空
     * @param start              取货地 不能为空
     * @return
     */
    private DistanceAndPriceData getDistanceAndPriceData(WaybillBo waybillBo,
                                                         List<WaybillReceiveAddress> receiveAddressList, WaybillDeliveryAddress start, LoginUser loginUser) {
        // 构造取货地
        CityAdressData startCityData = new CityAdressData();
        startCityData.setAddress(start.getAddressDetail());
        startCityData.setCoordinate(start.getCoordinates());
        startCityData.setRegionCode(regionTgmService.findRegionCodeByCoordinate(start.getCoordinates()));
        startCityData.setCity(regionTgmService.findRegionNameByCode(start.getRegionCode()));

        // 构造收货地
        List<CityAdressData> terminateCityList = new ArrayList<>();

        CityAdressData terminateCityData = null;
        for (WaybillReceiveAddress address : receiveAddressList) {
            terminateCityData = new CityAdressData();

            terminateCityData.setRegionCode(regionTgmService.findRegionCodeByCoordinate(address.getCoordinates()));
            terminateCityData.setAddress(address.getAddressDetail());
            terminateCityData.setCity(regionTgmService.findRegionNameByCode(address.getRegionCode()));
            terminateCityData.setCoordinate(address.getCoordinates());

            terminateCityList.add(terminateCityData);
        }

        TruckRequire truckRequire = truckRequireService
            .findTruckRequireByWaybillId(waybillBo.getWaybill().getWaybillId(), loginUser);
        if (null != truckRequire) {
            waybillBo.setTruckRequire(truckRequire);
        }

        return this.calWaybillPrice(startCityData, terminateCityList, waybillBo, loginUser);
    }

    @Override
    public List<Waybill> findWaybillByStatusView(Integer statusView) {
        if (null == statusView) {
            return null;
        }

        PageCondition cond = new PageCondition();
        Map<String, Object> filters = new HashMap<String, Object>();
        filters.put("statusView", statusView);
        filters.put("startTime", new Date());
        filters.put("endTime", DateUtil.addDays(new Date(), -30));
        return waybillDao.search(cond);
    }

    @Override
    public Waybill getWaybillDeliverying(LoginUser loginUser) {
        Driver driver = driverService.findDriverByUserId(loginUser.getUserId());
        if (driver == null) return null;
        List<Integer> statusArray = buildDeliveryingStatus();
        PageCondition pageCondition = new PageCondition();
        Map<String, Object> filters = pageCondition.getFilters();
        pageCondition.setPageNo(1);
        pageCondition.setPageSize(1);
        filters.put("driverId", driver.getDriverId());
        filters.put("statusList", statusArray);
        pageCondition.setOrderBy("plan_delivery_time asc");
        List<Waybill> list = waybillDao.search(pageCondition);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public Page<Waybill> searchWaybillBasicInfo(PageCondition pageCondition) throws BusinessException {
        Page<Waybill> pages = new Page<>(pageCondition.getPageNo(), pageCondition.getPageSize(), 0, null);

        int count = waybillDao.searchCount(pageCondition);
        if (count <= 0) return pages;

        List<Waybill> dataList = waybillDao.search(pageCondition);
        pages.setResults(dataList);
        pages.setTotal(count);

        return pages;
    }

    @Override
    public Integer createWaybillForWechat(WaybillBo waybillBo, LoginUser loginUser) throws BusinessException {
        Waybill waybill = waybillBo.getWaybill();
        // 设置所属客户经理
        this.setCustomerManagerId(waybill);
        // 支持测试单
        waybill.setTest(loginUser.isTest());
        // 写入租户信息
        waybill.setTenantId(loginUser.getTenantId());
        waybill.setTenantCode(loginUser.getTenantCode());

        List<WaybillDeliveryAddress> deliveryAddress = waybillBo.getDeliveryAddress();
        List<WaybillReceiveAddress> receiveAddress = waybillBo.getReceiveAddress();

        // 检查微信端必填项
        this.checkCreateWaybillForWechat(waybill, deliveryAddress, receiveAddress, loginUser);

        // 保存运单信息
        this.insertWaybill(waybill, WaybillSource.WECHAT_CLIENT, waybillBo.getTruckRequire(), loginUser);

        // 保存发货地取货地
        waybillCommonService.insertLinkTable(waybillBo, loginUser);

        // 保存用户常用路线
        userRouteBusinessService.addUserRoute(deliveryAddress, receiveAddress,
            UserRouteMaster.BusinessBranch.TYPE_MULTIPLY, loginUser);

        Integer waybillId = waybill.getWaybillId();

        return waybillId;
    }

    @Override
    public Waybill customerManagerModifyFreight(Waybill waybill, LoginEmployee loginEmployee) throws BusinessException {
        if (waybill == null) throw new BusinessException("waybillNull", "errors.paramCanNotNullWithName", "运单");

        if (waybill.getWaybillId() == null)
            throw new BusinessException("waybillIdNull", "errors.paramCanNotNullWithName", "运单id");

        if (waybillCommonService.checkCustomerPriceUpperLimit(waybill.getEstimateFreight())) {
            throw new BusinessException("cannotAllowChangePrice", "errors.common.prompt", "运单税前费用过高，请认真核对！");
        }

        // 审核中不能再次更改
        Waybill db = waybillDao.get(waybill.getWaybillId());
        if (db.getUpdateFreightAuditStatus() != null
            && NumberUtils.compare(Waybill.UpdateFreightAuditStatus.WATING_AUDIT.getCode(),
            db.getUpdateFreightAuditStatus()) == 0) {
            throw new BusinessException("waybillPriceAuditError", "waybill.error.priceAudit");
        }

        if (waybill.getEstimateFreight() == null)
            throw new BusinessException("waybillNull", "errors.paramCanNotNullWithName", "运单价格");

        // 改价值必须大于等于0
        if (new BigDecimal(0).compareTo(waybill.getEstimateFreight()) > 0)
            throw new BusinessException("waybillPriceNull", "errors.paramErrorWithName", "运单价格");
        BigDecimal oldDriverFreight = db.getShow4DriverFreight();
        BigDecimal oldFreight = db.getEstimateFreight();

        WaybillBo bo = new WaybillBo();
        bo.setWaybill(db);
        // 派车前
        if (NumberUtils.compare(Waybill.StatusView.WATING_RECEIVE.getCode(), db.getStatusView()) == 0) {
            // 计算价格
            this.doChangePrice(waybill, loginEmployee, db, oldDriverFreight, oldFreight, bo);
            return db;
        }
        // 已派车
        if (NumberUtils.compare(Waybill.StatusView.WATING_DELIVERY.getCode(), db.getStatusView()) == 0
            || NumberUtils.compare(Waybill.StatusView.DELIVERYING.getCode(), db.getStatusView()) == 0
            || NumberUtils.compare(Waybill.StatusView.WATING_PAY.getCode(), db.getStatusView()) == 0) {

            if (db.getEstimateFreight().compareTo(waybill.getEstimateFreight()) < 0) {
                // 已派车--改高
                this.doChangePrice(waybill, loginEmployee, db, oldDriverFreight, oldFreight, bo);
                return db;
            } else if (db.getEstimateFreight().compareTo(waybill.getEstimateFreight()) > 0) {
                // 已派车--改低
                // 改低需要审核
                if (StringUtils.isBlank(waybill.getUpdateFreightAuditRemark())) {
                    throw new BusinessException("updateFreightAuditRemarkNull", "errors.paramCanNotNullWithName",
                        "改价备注");
                }
                db.setFreightToBeAudited(waybill.getEstimateFreight());
                db.setUpdateFreightAuditRemark(waybill.getUpdateFreightAuditRemark());
                db.setUpdateFreightAuditStatus(Waybill.UpdateFreightAuditStatus.WATING_AUDIT.getCode());

                this.update(db, loginEmployee);

                // 记录上一次税前费用
                WaybillParam waybillParam = waybillParamService.findByWaybillId(db.getWaybillId());
                if (null == waybillParam) {
                    return null;
                }
                waybillParam.setLastEstimateFreight(db.getEstimateFreight());
                waybillParamService.update(waybillParam, loginEmployee);

                return null;
            }
        }

        return null;
    }

    /**
     * 执行改价
     *
     * @param waybill
     * @param loginEmployee
     * @param db
     * @param oldDriverFreight
     * @param oldFreight
     * @param bo
     */
    private void doChangePrice(Waybill waybill, LoginEmployee loginEmployee, Waybill db, BigDecimal oldDriverFreight,
                               BigDecimal oldFreight, WaybillBo bo) {
        this.setCalculateFreight(bo, loginEmployee);
        this.getNewFreight(waybill, db);
        this.update(db, loginEmployee);

        // 发送推送消息
        this.modifyFreightPushMsg(db, oldDriverFreight, oldFreight, loginEmployee);
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

        // 运单生成后税前费用和司机结算价相互独立
        // 司机结算价
        // WaybillBo bo = new WaybillBo();
        // bo.setWaybill(waybillDb);
        // WaybillParam oldParam =
        // waybillParamService.findByWaybillId(waybillDb.getWaybillId());
        // WaybillParam wbParam = oldParam == null ? new WaybillParam() :
        // oldParam;
        // settingExtraFee(bo, wbParam);

        // 返点费-通过饭店率计算不需要保存到数据库

        waybillDb.setCompareResult(
            BaseUtil.calCompareResult(waybillDb.getCalculatedFreight().multiply(new BigDecimal("1.1")),
                waybillDb.getEstimateFreight(), true));
        return waybillDb;
    }

    /**
     * 设置系统报价 （标准计价）
     *
     * @param bo
     */
    private void setCalculateFreight(WaybillBo bo, LoginUser loginUser) {
        Waybill waybillDb = bo.getWaybill();
        if (waybillDb == null) {
            throw new BusinessException("waybillNull", "errors.paramErrorWithName", "waybill");
        }
        // 调用标准计价
        DistanceAndPriceData priceData = waybillCommonService.calculateStanderPrice(waybillDb.getWaybillId(),
            loginUser);

        if (priceData == null) return;

        waybillDb.setCalculatedFreight(priceData.getPrice());

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
        // if (!Constants.CUSTOMER_MANAGER_PERMISSION_KEY.equals(loginEmployee.getLoginPermissionKey())) {
        // msgWithBusinessService.pushChangePricelMsgToWaybillOwner(waybillDb.getWaybillId(), oldFreight,
        // loginEmployee);
        // }
//        msgWithBusinessService.modifyFreightPushMsg(waybillDb, oldDriverFreight, oldFreight, loginEmployee);
    }

    @Override
    public void customerManagerModifyShow4DriverFreight(Waybill waybill, LoginEmployee loginEmployee)
        throws BusinessException {
        if (waybill == null) throw new BusinessException("waybillNull", "errors.paramCanNotNullWithName", "运单信息");

        if (waybill.getWaybillId() == null)
            throw new BusinessException("waybillIdNull", "errors.paramCanNotNullWithName", "运单id");

        Waybill waybillOriginal = waybillDao.get(waybill.getWaybillId());

        if (waybillOriginal == null) throw new BusinessException("waybillNull", "errors.notFound");

        if (waybill.getShow4DriverFreight() == null)
            throw new BusinessException("Show4DriverFreightNull", "errors.paramCanNotNullWithName", "司机结算价");

        // 状态时已完成前的状态才能改价
        if (NumberUtils.compare(waybillOriginal.getStatusView(), Waybill.StatusView.FINISH.getCode()) >= 0 // 已完成后的不能修改
            && NumberUtils.compare(waybillOriginal.getStatusView(), Waybill.StatusView.DEFAULT.getCode()) <= 0) {// 异常订单不能修改
            throw new BusinessException("waybillStatusError", "errors.common.prompt", "不能修改已完成的运单");
        }
        BigDecimal originalPrice = waybillOriginal.getShow4DriverFreight();
        waybillOriginal.setShow4DriverFreight(waybill.getShow4DriverFreight());

        this.update(waybillOriginal, loginEmployee);
        BigDecimal newShowDriverFreight = waybill.getShow4DriverFreight();
        if (newShowDriverFreight != null && !newShowDriverFreight.equals(originalPrice)) {
            msgWithBusinessService.pushChangePriceMsgToDriver(waybill.getWaybillId(), originalPrice);
        }
    }

    @Override
    public String updateFreightAuditToPassOrFailed(Waybill waybill, LoginUser loginUser) throws BusinessException {
        if (null == waybill.getUpdateFreightAuditStatus()) {
            return null;
        }

        Waybill wb = getWaybillAndCheckExist(waybill.getWaybillId());

        // 改价审核通过更改运费信息
        if (NumberUtils.compare(Waybill.UpdateFreightAuditStatus.HAS_PASS.getCode(),
            waybill.getUpdateFreightAuditStatus()) == 0) {
            // 修改运单信息
            waybill.setEstimateFreight(
                wb.getFreightToBeAudited() == null ? BigDecimal.ZERO : wb.getFreightToBeAudited());
            waybill.setAfterTaxFreight(truckTypeFreightService.getAfterTaxFright(null, waybill));
            waybill.setUpdateFreightRemark(wb.getUpdateFreightAuditRemark());
            waybill.setUpdateFreightAuditRemark(waybill.getUpdateFreightAuditRemark());
            // 司机结算价
            WaybillBo bo = new WaybillBo();
            bo.setWaybill(waybill);
            WaybillParam waybillParam = waybillParamService.findByWaybillId(waybill.getWaybillId());
            settingExtraFee(bo, waybillParam == null ? new WaybillParam() : waybillParam);

            // 历史备注的处理
            if (StringUtils.isBlank(waybill.getUpdateFreightAuditRemark())) {
                waybill.setUpdateFreightAuditRemark(" ");
            }
        }
        update(waybill, loginUser);

        // 司机推送：原司机结算价不相等
        if (null != waybill.getShow4DriverFreight()
            && !waybill.getShow4DriverFreight().equals(wb.getShow4DriverFreight())) {
            msgWithBusinessService.pushChangePriceMsgToDriver(waybill.getWaybillId(), wb.getShow4DriverFreight());
        }
        if (waybill.getUpdateFreightAuditStatus() == Waybill.UpdateFreightAuditStatus.NOT_PASS.getCode()) {
            msgWithBusinessService.pushFreightAuditNoPassMsg(waybill.getWaybillId(), loginUser);
        }

        return "原税前费用：" + wb.getEstimateFreight() + "元；新税前费用：" + wb.getFreightToBeAudited();
    }

    @Override
    public Integer createWaybillWithDriver(WaybillBo waybillBo, WaybillSource waybillSource, LoginUser loginUser)
        throws BusinessException {
        Waybill waybill = waybillBo.getWaybill();

        if (waybill.getDriverId() == null) {
            throw new BusinessException("driverIdNull", "errors.paramCanNotNullWithName", "司机");
        }
        if (waybill.getTruckId() == null) {
            throw new BusinessException("truckIdNull", "driver.error.not.found", "车辆id");
        }

        Integer driverId = waybill.getDriverId();
        Integer truckId = waybill.getTruckId();

        waybill.setTest(loginUser.isTest());
        waybill.setDriverId(null);
        waybill.setTruckId(null);
        // 新建运单检查
        this.createWaybill(waybillBo, waybillSource, loginUser);
        // 获取班次信息

        // 指派运单
        Driver driver = driverService.getDriver(driverId);
        if (null == driver) {
            throw new BusinessException("driverNotFound", "driver.error.not.found");
        }

        String remark = "运单加跑";

        this.changeToAssigned(waybill.getWaybillId(), driverId, truckId, waybill.getVehicleToVendor(),
                waybill.getReceiveWay(), remark, loginUser);

        return waybill.getWaybillId();
    }

    @Override
    public CustomerPerformanceVo getCustomerManPerformanceOverall(LoginEmployee loginEmployee)
        throws BusinessException {
        return new CustomerPerformanceVo();
    }

    @Override
    public CustomerManagerDebtVo getCustomerManDebtOverall(LoginEmployee loginEmployee) throws BusinessException {
        return null;
    }

    @Override
    public CustomerManagerDebtOverviewVo getSeparationDebt(PageCondition pageCondition, LoginEmployee loginEmployee)
        throws BusinessException {

        return customerManagerPerformanceUtil.getDebtOverviewVo(pageCondition, loginEmployee.getEmployeeId());
    }

    @Override
    public CustomerManagerDebtDetailVo getCustomerDebtDetail(PageCondition pageCondition, LoginEmployee loginEmployee)
        throws BusinessException {
        return customerManagerPerformanceUtil.getDebtDetail(pageCondition, loginEmployee.getEmployeeId());
    }

    @Override
    public Waybill calculateProjectFreight(int waybillId, Waybill result) throws BusinessException {

        WaybillParam waybillParam = waybillParamService.findByWaybillId(waybillId);
        if (waybillParam == null || waybillParam.getProjectFreightRuleJson() == null
            || waybillParam.getProjectFreightRuleJson().length() == 0) {
            // throw new BusinessException("validationFailure", "errors.validation.failure");
            return result;
        }

        try {
            BigDecimal f = privateFreightFactorService.calFreightForProject(waybillParam.getValuationConstJson(),
                waybillParam.getProjectFreightRuleJson());
            result.setEstimateFreight(f.setScale(2, BigDecimal.ROUND_HALF_UP));
            result.setAfterTaxFreight(f.setScale(2, BigDecimal.ROUND_HALF_UP));
            TruckRequire truckRequire = truckRequireService.findTruckRequireByWaybillId(waybillId, null);
            if (truckRequire != null && truckRequire.getTaxRateValue() != null) {
                BigDecimal rate = truckRequire.getTaxRateValue().add(new BigDecimal(1));
                result.setAfterTaxFreight(f.divide(rate, 2, BigDecimal.ROUND_HALF_UP));// 不含税价
            }

            // 计算司机结算费=税后-小工搬运费
            BigDecimal show4DriverFreight = result.getAfterTaxFreight();

            if (waybillParam.getLaborerHandlingCost() != null) {
                show4DriverFreight = show4DriverFreight.subtract(waybillParam.getLaborerHandlingCost());
            }
            // 返点费
            if (result.getRebateRate() != null) {
                show4DriverFreight = show4DriverFreight.subtract(f.multiply(result.getRebateRate()));
            }
            result.setCalculatedFreight(f);
            result.setShow4DriverFreight(show4DriverFreight.setScale(2, BigDecimal.ROUND_HALF_UP));
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }
        return result;

    }

    @Override
    public void updateWaybillBatch(Waybill example, Waybill newValue, LoginUser loginUser) throws BusinessException {
        if (null != loginUser) {
            newValue.setLastUpdateUserId(loginUser.getUserId());
        }
        waybillDao.updateByExample(example, newValue);
    }

    @Override
    public void addPriceWaybill(Integer waybillId, Integer addPrice, LoginEmployee loginEmployee)
        throws BusinessException {
        if (waybillCommonService.checkCustomerPriceUpperLimit(new BigDecimal(addPrice + ""))) {
            throw new BusinessException("cannotAllowChangePrice", "errors.common.prompt", "运单税前费用过高，请认真核对！");
        }

        Waybill waybillDb = this.getWaybill(waybillId);
        if (waybillDb == null) return;
        Waybill waybill = new Waybill();
        waybill.setWaybillId(waybillId);
        waybill.setEstimateFreight(waybillDb.getEstimateFreight().add(new BigDecimal(addPrice)));
        this.update(waybill, loginEmployee);
    }

    @Override
    public void updatePayWaybillStatus(String waybillId, Integer status, LoginEmployee loginEmployee)
        throws BusinessException {
        if (Waybill.Status.DELIVERIED.getCode() == status || Waybill.Status.PAYING.getCode() == status) {
            Waybill waybill = this.findWaybillByWaybillNo(waybillId, loginEmployee);
            Waybill newWaybill = new Waybill();
            newWaybill.setStatusView(Waybill.StatusView.WATING_PAY.getCode());
            newWaybill.setWaybillId(waybill.getWaybillId());
            newWaybill.setStatus(status);
            waybillCommonService.update(newWaybill, loginEmployee);
        } else {
            throw new BusinessException("statusIllicit", "waybill.error.statusIllicit");
        }
    }

    @Override
    public void updateUndetermined(int waybillId, LoginUser loginUser) throws BusinessException {
        Waybill waybill = new Waybill();
        waybill.setWaybillId(waybillId);
        waybill.setStatus(Waybill.Status.UNDETERMINED.getCode());
        waybillCommonService.update(waybill, loginUser);

        // 更改被承运的运单信息
        Waybill transformBill = waybillCommonService.findWaybillByTransformBillId(waybill.getWaybillId());
        if (null != transformBill) {
            transformBill.setStatus(Waybill.Status.UNDETERMINED.getCode());
            waybillCommonService.update(transformBill, loginUser);
        }
    }

    @Override
    public void updateAreaCode(int waybillId, String areaCode, LoginUser loginUser) throws BusinessException {
        Waybill waybill = new Waybill();
        waybill.setWaybillId(waybillId);
        waybill.setAreaCode(areaCode);
        waybillCommonService.update(waybill, loginUser);
    }

    @Override
    public void updateCustomerManagerId(int waybillId, int customerManagerId, LoginUser loginUser)
        throws BusinessException {
        Waybill waybill = new Waybill();
        waybill.setWaybillId(waybillId);
        waybill.setCustomerManagerId(customerManagerId);

        User user = employeeService.loadUserByEmployeeId(customerManagerId, loginUser);
        if (null != user) {
            waybill.setCustomerManagerName(user.getName());
        }

        waybillCommonService.update(waybill, loginUser);
    }

    @Override
    public void doCloseWaybill(int waybillId, LoginUser loginUser) throws BusinessException {
        Waybill waybill = getWaybillAndCheckExist(waybillId);
        if (waybill.getStatus() != Waybill.Status.PAIED.getCode()) {
            throw new BusinessException("onlyHasPaiedWaybillCanColse", "waybill.error.onlyHasPaiedWaybillCanColse");
        }

        waybill.setIsDelete(true);
        update(waybill, loginUser);

        // 删除承运商的运单
        WaybillParam waybillParam = waybillParamService.findByWaybillId(waybill.getWaybillId());
        if (null == waybillParam || null == waybillParam.getTransformBillLinkId()) {
            return;
        }

        Waybill vendorWaybill = new Waybill();
        vendorWaybill.setWaybillId(waybillParam.getTransformBillLinkId());
        vendorWaybill.setIsDelete(true);
        update(vendorWaybill, loginUser);
    }

    @Override
    public List<Waybill> findByWaybillIds(List<Integer> waybillIds, LoginUser loginUser) throws BusinessException {
        if( CollectionUtils.isEmpty(waybillIds) ){ return Lists.newArrayList(); }
        WaybillExample waybillExample = new WaybillExample();
        WaybillExample.Criteria criteria = waybillExample.createCriteria();
        criteria.andTenantIdEqualTo(loginUser.getTenantId());//租户 id
        criteria.andWaybillIdIn(waybillIds);
        criteria.andIsDeleteEqualTo(false);
        return waybillDao.selectByExample(waybillExample);
    }

    @Override
    public List<Waybill> findByWaybillIds(List<Integer> waybillIds) throws BusinessException {
        if( CollectionUtils.isEmpty(waybillIds) ){ return Lists.newArrayList(); }
        WaybillExample waybillExample = new WaybillExample();
        WaybillExample.Criteria criteria = waybillExample.createCriteria();
        criteria.andWaybillIdIn(waybillIds);
        criteria.andIsDeleteEqualTo(false);
        return waybillDao.selectByExample(waybillExample);
    }

    @Override
    public List<Waybill> findByWaybillNos(List<String> waybillNos) throws BusinessException {
        if( CollectionUtils.isEmpty(waybillNos) ){ return Lists.newArrayList(); }
        WaybillExample waybillExample = new WaybillExample();
        WaybillExample.Criteria criteria = waybillExample.createCriteria();
        criteria.andWaybillNoIn(waybillNos);
        criteria.andIsDeleteEqualTo(false);
        return waybillDao.selectByExample(waybillExample);
    }

    @Override
    public List<Waybill> findByWaybillByFilter(WaybillFilter waybillFilter) throws BusinessException {
        if( null == waybillFilter.getStartTime()
                && null == waybillFilter.getEndTime()
                && CollectionUtils.isEmpty(waybillFilter.getStatusViews())
                && StringUtils.isBlank(waybillFilter.getReconciliationNo())){
            return Lists.newArrayList();
        }
        WaybillExample waybillExample = new WaybillExample();
        WaybillExample.Criteria criteria = waybillExample.createCriteria();
        if( null !=  waybillFilter.getStartTime()){
            criteria.andFinishTimeGreaterThan(waybillFilter.getStartTime());
        }
        if( null != waybillFilter.getEndTime() ){
            criteria.andFinishTimeLessThan(waybillFilter.getEndTime());
        }
        if( !CollectionUtils.isEmpty(waybillFilter.getStatusViews()) ){
            criteria.andStatusViewIn(waybillFilter.getStatusViews());
        }
        // 承运侧-对账单号
        if( StringUtils.isNotBlank(waybillFilter.getReconciliationNo()) ){
            criteria.andReconciliationNoEqualTo(waybillFilter.getReconciliationNo());
        }
        criteria.andIsDeleteEqualTo(false);
        return waybillDao.selectByExample(waybillExample);
    }

    @Override
    public void changeToAssignedBatch(List<Waybill> waybills, LoginUser loginUser) throws BusinessException {
        if (CollectionUtils.isEmpty(waybills))
            throw new BusinessException("waybillNull", "errors.paramCanNotNullWithName", "运单信息");

        for (Waybill waybill : waybills) {
            Waybill wb = waybillCommonService.getWaybillById(waybill.getWaybillId());
            if (null == wb) {
                throw new BusinessException("waybillNotfound", "waybill.error.notfound");
            }

            Driver driver = driverService.getDriver(waybill.getDriverId());
            if (null == driver) {
                throw new BusinessException("driverNotFound", "driver.error.not.found");
            }

            this.changeToAssigned(waybill.getWaybillId(), driver.getDriverId(), waybill.getTruckId(), waybill.getVehicleToVendor(),
                Waybill.ReceiveWay.ASSIGNED.getCode(), "", loginUser);
            // 操作轨迹
            waybillOperateTrackService.insert(waybill.getWaybillId(), OperateType.ASSIGNED_SYS,
                OperateApplication.CUSTOMER_SYS, null, loginUser);
        }
    }



    @Override
    public List<Waybill> findWaitingAcceptWaybillV2(LoginUser loginUser) {
        WaybillExample example = new WaybillExample();
        WaybillExample.Criteria criteria = example.createCriteria();
        Driver driver = driverService.findDriverByUserId(loginUser.getUserId());
        if (driver == null) {
            criteria.andDriverIdEqualTo(-1);
        } else {
            criteria.andDriverIdEqualTo(driver.getDriverId());
        }
        criteria.andStatusEqualTo((byte) Waybill.Status.WAITINT_DRIVER_ANSWER.getCode());
        List<Waybill> rows = waybillCommonService.selectByExample(example);
        for(Waybill row : rows) {
			loginUser.setTenantId(row.getTenantId());//更随运单租户
            row.setShowYourPrice(truckService.isShowYourPrice(row.getTruckId(), loginUser));
        }
        return rows;
    }

    // 昨日运单信息
    private YesterdayIncomeInfo buildYesterdayIncomeInfoV2(Integer driverId, LoginUser loginUser) {
        YesterdayIncomeInfo info = new YesterdayIncomeInfo();

        PageCondition pageCondition = new PageCondition();
        // 运单池数量
        info.setPoolCount(getAcceptableWaybillCount(pageCondition, loginUser));

        // 昨日运单总数(包含配送完成、已完成)
        PageCondition page = new PageCondition();
        Map<String, Object> filters = page.getFilters();
        filters.put("driverId", driverId);
        List<Integer> statusArray = new ArrayList<Integer>();
        statusArray.add(Waybill.Status.DELIVERIED.getCode());
        statusArray.add(Waybill.Status.PAIED.getCode());
        filters.put("statusList", statusArray);
        filters.put("startTime", DateUtil.dayAddStart(-1));
        filters.put("endTime", DateUtil.dayAddEnd(-1));
        info.setYesterdayWaybillTotal(searchCount(page, loginUser));
        log.info("进行中的运单列表YesterdayIncomeInfo-->response:{}", JSON.toJSON(info));
        return info;
    }

    @Override
    public WaybillInfo findHomePageInfoV2(LoginUser loginUser) {
        // 验证参数
        if (null == loginUser || null == loginUser.getUserId()) {
            return null;
        }

        log.info("进行中的运单列表findHomePageInfo-->reqest:{}", JSON.toJSON(loginUser));
        WaybillInfo waybillInfo = new WaybillInfo();
        Driver driver = driverService.findDriverByUserId(loginUser.getUserId());
        // 没有司机，直接返回，昨日信息返回默认值
        if (null == driver) {
            waybillInfo.setYesterdayIncomeInfo(new YesterdayIncomeInfo());
            return waybillInfo;
        }

        List<WaybillInfo> list = new ArrayList<WaybillInfo>();
        List<Waybill> rows = findRunningWaybillByDriverIdV2(driver.getDriverId(), 10, null, loginUser);
        for (Waybill waybill : rows) {
            WaybillInfo info = new WaybillInfo();
            loginUser.setTenantId(waybill.getTenantId());//更随运单租户
            waybill.setShowYourPrice(truckService.isShowYourPrice(waybill.getTruckId(), loginUser));
            waybill.setAsProjectWaybillHandle(this.checkAsProjectWaybill(waybill));
            info.setWaybill(waybill);
            info.setTruckRequire(truckRequireService.findTruckRequireByWaybillId(waybill.getWaybillId(), loginUser));
            info.setTruckCustomer(findCustomerManger(waybill, loginUser));
            list.add(info);
        }
        waybillInfo.setWaybillInfoList(list);

        // 若进行中的运单为空，则获取昨日运单信息
        if (list.isEmpty()) {
            waybillInfo.setYesterdayIncomeInfo(buildYesterdayIncomeInfoV2(driver.getDriverId(), loginUser));
        }

        // 司机收入统计
        //waybillInfo.setIncomeStatistics(incomeStatisticsService.findByDriverId(driver.getDriverId()));
        log.info("进行中的运单列表findHomePageInfo-->response:{}", JSON.toJSON(waybillInfo));
        return waybillInfo;
    }

    @Override
    public List<Waybill> findRunningWaybillByDriverIdV2(Integer driverId, Integer maxBackNum, String orderBy,
                                                        LoginUser loginUser) {
        if (null == driverId || null == loginUser || null == loginUser.getTenantId()) {
            return new ArrayList<Waybill>();
        }

        // 查询并组装信息：进行中的运单
        PageCondition page = new PageCondition();
        Map<String, Object> filters = page.getFilters();
        filters.put("driverId", driverId);
        filters.put("statusList", buildDeliveryingStatus());
        page.setPageNo(1);
        page.setPageSize(maxBackNum == null ? 10 : maxBackNum);
        page.setOrderBy(StringUtils.isBlank(orderBy) ? " status_view desc, plan_delivery_time asc " : orderBy);
        return waybillDao.search(page);
    }

    @Override
    public DistanceAndPriceData doCalculateJumaLogisticsPrice(DistanceAndPriceParamVo dp, LoginUser loginUser) throws BusinessException {

        WaybillBo bo = new WaybillBo();
        bo.setTruckRequire(dp.getTruckRequire());
        bo.setWaybill(dp.getWaybill());

        //专车计算的价格默认含税
        DistanceAndPriceData priceData = this.calWaybillPrice(dp.getSrcAddress(), dp.getToAddress(), bo, loginUser);

        Waybill waybill = dp.getWaybill();

        if (waybill == null)
            return priceData;

        if (waybill.getEstimateFreight() == null) {
            waybill.setEstimateFreight(priceData.getReferenceFreight());
        }

        // 司机结算价
        if (waybill.getCustomerId() != null) {
            // 获取返点数据
            BigDecimal rebateRate = customerInfoService.getRebateRate(waybill.getCustomerId());
            waybill.setRebateRate(rebateRate);
        }
        // --获取税后价格
        BigDecimal afterTaxFee = truckTypeFreightService.getAfterTaxFright(dp.getTruckRequire(), waybill);
        if (afterTaxFee == null)
            return priceData;// 没有税后价格则不能计算司机结算价

        waybill.setAfterTaxFreight(afterTaxFee);
        priceData.setWithoutTaxPrice(afterTaxFee);
        // --获取司机结算价格
        WaybillParam waybillParam = this.settingExtraFee(bo, null);
        BigDecimal show4DriverFreight = waybillParam.getShow4DriverFreight();
        if (show4DriverFreight != null) {
            priceData.setShow4DriverFreight(show4DriverFreight.setScale(2, BigDecimal.ROUND_HALF_UP));
        }

        return priceData;
    }

    @Override
    public void batchUpdateReceivableReconcilicationNo(List<Waybill> items) {
        waybillDao.batchUpdateReceivableWaybill(items);
    }

    /**
     * 运力校验
     *
     * @param waybill
     * @param truckRequire
     */
    private void checkCarryCapacity(Waybill waybill, TruckRequire truckRequire, TruckType truckType) {
        // 货物方吨件
        if (StringUtils.isBlank(truckRequire.getGoodsVolume()))
            throw new BusinessException("GoodsVolumeNull", "errors.validation.canNotBeBlank", "方量");
        double volume = new Double(truckRequire.getGoodsVolume());
        if (StringUtils.isBlank(truckRequire.getGoodsWeight()))
            throw new BusinessException("GoodsWeightNull", "errors.validation.canNotBeBlank", "重量");
        double weight = new Double(truckRequire.getGoodsWeight());
        Integer distance = waybill.getEstimateDistance();
        if (distance == null)
            throw new BusinessException("estimateDistanceNull", "errors.validation.canNotBeBlank", "距离");

        // 车型校验
        if (truckRequire.getTruckTypeId() == null)
            throw new BusinessException("truckTypeNull", "errors.validation.canNotBeBlank", "车型");

        if (truckType == null) throw new BusinessException("truckTypeNotExist", "errors.paramErrorWithName", "车型");

        if (truckType.getTruckTypeVolume() != null) {// FIXME 这里按立方米处理
            Double maxVolume = truckType.getTruckTypeVolume().doubleValue();
            if (NumberUtils.compare(volume, maxVolume) == 1) throw new BusinessException("GoodsVolumeOverCapacityError",
                "errors.common.prompt", "货物的吨方件超出整车单车最大装载值哦~请拆分成多车下单");
        }

        if (truckType.getTruckTypeLoad() != null) {// FIXME 这里按吨处理
            Double maxWeight = truckType.getTruckTypeLoad().doubleValue();
            if (NumberUtils.compare(weight, maxWeight) == 1) throw new BusinessException("GoodsWeightOverCapacityError",
                "errors.common.prompt", "货物的吨方件超出整车单车最大装载值哦~请拆分成多车下单");
        }
    }



    /**
     * 当运单为承运单时组装司机和承运商信息
     */
    public void setDriverAndTransformData(int waybillId, WaybillDetailInfo detailInfo) {

        WaybillParam waybillParam = waybillParamService.findByWaybillId(waybillId);
        if (waybillParam == null) {
            throw new RuntimeException("不存在运单号" + waybillId + "对应的运单参数");
        }

        Waybill waybill = this.getWaybill(waybillParam.getTransformBillLinkId());
        if (waybill == null) {
            throw new RuntimeException("不存在转运单号" + waybillParam.getTransformBillLinkId() + "对应的运单");
        }
        //设置司机属性
        detailInfo.setDriverTruckInfoBo(driverService.findDriverTruckInfoByWaybillId(waybill.getWaybillId()));
    }

    public static void main(String[] args) {

        String ruleJson = "{\"factorJson\": \"{\\\"estimateFreight\\\":1600}\",     \"roadMapId\": 7936,    \"truckTypeId\": 96,    \"valuationWay\": 2,    \"whoWriteWork\": 1 }";

        RoadMap roadMap = JSON.parseObject(ruleJson, RoadMap.class);

        System.out.println(roadMap);
    }

    @Override
    public SumForWaybill findUnReconciliationByPlateNumber(String plateNumber) {
        List<SumForWaybill> rows = waybillDao.findUnReconciliationByPlateNumber(plateNumber);
        if(rows.size() == 0) return null;
        SumForWaybill row = rows.get(0);
        return row;
    }

    @Override
    public void batchUpdateVendor(Integer vendorId, List<String> waybillNos) {

        if(vendorId == null || waybillNos == null || waybillNos.isEmpty()){
            return;
        }
        List<String> newWaybillNos = new ArrayList<>(waybillNos);

        //转运单甲方更新vendor_id，转运单乙方及普通单更新vehicle_to_vendor
        Iterator<String> iterator = newWaybillNos.iterator();
        while (iterator.hasNext()){
            Waybill waybill = this.findWaybillByWaybillNo(iterator.next(), new LoginUser());
            if(waybill.getReceiveWay().equals(Waybill.ReceiveWay.TRANSFORM_BILL.getCode())){
                Waybill updateWaybill = new Waybill();
                updateWaybill.setWaybillId(waybill.getWaybillId());
                updateWaybill.setVendorId(vendorId);
                waybillDao.update(updateWaybill);
                iterator.remove();
            }
        }

        if(!newWaybillNos.isEmpty()){
            waybillDao.batchUpdateVendor(vendorId, newWaybillNos);
        }
    }

    @Override
    public Integer countDriverNoUploadReceiptWaybill(List<Integer> listStatusView, LoginUser loginUser) throws BusinessException {
        if (null == loginUser || null == loginUser.getUserId()) {
            return 0;
        }

        // 获取司机信息
        com.juma.vms.driver.domain.Driver driver = vmsCommonService.loadDriverByUserId(loginUser.getUserId());
        if (null == driver) {
            return 0;
        }

        PageCondition condition = new PageCondition();
        Map<String, Object> filters = new HashMap<>();
        filters.put("driverId", driver.getDriverId());
        filters.put("needReceipt", NeedReceipt.NOT_HAVE_UPLOAD.getCode());
        if (CollectionUtils.isNotEmpty(listStatusView)) {
            filters.put("statusViewList", listStatusView);
        }
        filters.put("isDelete", false);
        condition.setFilters(filters);

        return waybillCommonService.searchCount(condition);
    }

    @Override
    public List<WaybillQuery> listWayillForDriver(WaybillFilter waybillFilter, Integer callbackPageSize, String orderBy,
        LoginUser loginUser)
        throws BusinessException {
        List<WaybillQuery> result = new ArrayList<>();
        callbackPageSize = callbackPageSize == null ? 15 : (callbackPageSize.compareTo(200) == 1 ? 200 :
            callbackPageSize);

        // 获取司机信息
        if (null == waybillFilter || null == loginUser || null == loginUser.getUserId()) {
            return result;
        }

        com.juma.vms.driver.domain.Driver driver = vmsCommonService.loadDriverByUserId(loginUser.getUserId());
        if (null == driver) {
            return result;
        }

        PageCondition condition = new PageCondition();
        Map<String, Object> filters = new HashMap<>();
        filters.put("driverId", driver.getDriverId());

        // 承运商ID
        if (null != waybillFilter.getVehicleToVendor()) {
            filters.put("vehicleToVendor", waybillFilter.getVehicleToVendor());
        }

        // 运单的配送状态
        if (null != waybillFilter.getStatusView()) {
            filters.put("statusView", waybillFilter.getStatusView());
        }

        // 用车时间
        if (StringUtils.isNotBlank(waybillFilter.getStartPlanDeliveryTime())) {
            filters.put("startTime", waybillFilter.getStartPlanDeliveryTime());
        }

        // 用车时间
        if (StringUtils.isNotBlank(waybillFilter.getEndPlanDeliveryTime())) {
            filters.put("endTime", waybillFilter.getEndPlanDeliveryTime());
        }

        filters.put("isDelete", false);
        condition.setFilters(filters);
        condition.setPageNo(1);
        condition.setPageSize(callbackPageSize);
        if (StringUtils.isNotBlank(orderBy) && (orderBy.toLowerCase().contains("asc") || orderBy.toLowerCase().contains("desc"))) {
            condition.setOrderBy(orderBy);
        } else {
            condition.setOrderBy(" plan_delivery_time desc");
        }

        List<Waybill> list = waybillCommonService.search(condition);
        for (Waybill waybill : list) {
            WaybillQuery query = new WaybillQuery();

            buildWorkLoad(waybill);

            // 线路名称
            RoadMap roadMap = roadMapService.getRoadMap(waybill.getRoadMapId());
            waybill.setRoadMapName(roadMap == null ? null : roadMap.getName());

            query.setWaybill(waybill);

            // 取货地信息
            query.setListWaybillDeliveryAddress(
                waybillDeliveryAddressService.findAllByWaybillId(waybill.getWaybillId()));
            // 配送地信息
            query.setListWaybillReceiveAddress(waybillReceiveAddressService.findAllByWaybillId(waybill.getWaybillId()));
            // 项目信息
            query.setProjectVo(projectService.getProjectVo(waybill.getProjectId(), loginUser));
            // 用车要求
            query.setTruckRequire(truckRequireService.loadReuckRequireByWaybillId(waybill.getWaybillId()));

            result.add(query);
        }

        return result;
    }

    // 填写工作量相关信息
    private void buildWorkLoad(Waybill waybill) {
        WaybillParam waybillParam = waybillParamService.findByWaybillId(waybill.getWaybillId());
        if (null != waybillParam && StringUtils.isNotBlank(waybillParam.getProjectFreightRuleJson())) {
            waybill.setValuationWays(
                transfer(waybillParam.getProjectFreightRuleJson(), waybillParam.getValuationConstJson()));
        }

        // 转运单需要根据原单确定司机端是否填写工作量
        if (NumberUtils.compare(waybill.getWaybillSource(), WaybillSource.TRANSFORM_BILL.getCode()) == 0) {
            WaybillParam transformBillParam = waybillParamService.findByTransformBillLinkId(waybill.getWaybillId());
            if (null != transformBillParam) {
                Waybill transformBill = this.getWaybill(transformBillParam.getWaybillId());
                if (null != transformBill && null != transformBill.getProjectId()) {
                    waybill.setValuationWays(transformBill.getValuationWays());
                    waybill.setWhoWriteWork(waybillCheckService.loadWhoWriteWork(waybill.getWaybillId()));
                    waybill.setCompleteWorkload(waybillCheckService.checkProjectIsWorkload(transformBill.getWaybillId())
                        && waybillCheckService.checkIsDriverWriteWork(waybill.getWaybillId()));
                }
            }
        } else {
            if (null != waybill.getProjectId()) {
                waybill.setWhoWriteWork(waybillCheckService.loadWhoWriteWork(waybill.getWaybillId()));
                waybill.setCompleteWorkload(waybillCheckService.checkProjectIsWorkload(waybill.getWaybillId())
                    && waybillCheckService.checkIsDriverWriteWork(waybill.getWaybillId()));
            }
        }
    }

    @Override
    public WaybillArriveDoptTimeLimit loadWaybillArriveDoptTimeLimit() {
        List<ConfParamOption> paramOptions = null;
        // 获取限制的时间
        try {
            paramOptions =
                confParamService.findParamOptions(Constants.DRIVER_CLICK_ARRIVE_DOPT_TIME_LIMIT_KEY);
        } catch (Exception e) {
            return null;
        }

        if (CollectionUtils.isNotEmpty(paramOptions)) {
            return null;
        }

        WaybillArriveDoptTimeLimit timeLimit = new WaybillArriveDoptTimeLimit();
        for (ConfParamOption option : paramOptions) {
            if (StringUtils.isBlank(option.getOptionName())) {
                continue;
            }
            if (StringUtils.isBlank(option.getOptionValue()) || !StringUtils.isNumeric(option.getOptionValue())) {
                continue;
            }

            if (option.getOptionName().equals("forward")) {
                timeLimit.setForward(Integer.parseInt(option.getOptionValue()));
            }

            if (option.getOptionName().equals("backward")) {
                timeLimit.setBackward(Integer.parseInt(option.getOptionValue()));
            }

        }

        return timeLimit;
    }

    @Override
    public void remindDriverClickArriveDopt(Integer aheadTime, Integer cycleMinutes, String appSceneKey,
        String smsSceneKey, Boolean isNeedTenant) {
        if (null == aheadTime || null == cycleMinutes || (StringUtils.isBlank(appSceneKey) && StringUtils.isBlank(smsSceneKey))) {
            return;
        }

        isNeedTenant = isNeedTenant == null ? false: isNeedTenant;

        Date date = new Date();
        Date startDate = DateUtil.addMinutes(date, -aheadTime);
        // 循环周期结束时间=开始时间+循环周期-1秒，防止重复发送通知
        Date endDate = DateUtil.addSeconds(DateUtil.addMinutes(startDate, cycleMinutes), -1);
        PageCondition cond = new PageCondition();
        Map<String, Object> filters = new HashMap<>();
        filters.put("startTime", startDate);
        filters.put("endTime", endDate);
        filters.put("statusView", StatusView.WATING_DELIVERY.getCode());
        cond.setFilters(filters);

        cond.setPageNo(1);
        cond.setPageSize(10000);
        cond.setOrderBy(" waybill_id desc ");
        List<Waybill> list = waybillCommonService.search(cond);

        // 发送消息
        for (Waybill w : list) {
            if (null != w.getArriveDepotTime()) {
                continue;
            }

            // 获取司机信息
            com.juma.vms.driver.domain.Driver driver = vmsCommonService.loadDriverByDriverId(w.getDriverId());
            if (null == driver) {
                continue;
            }

            Map<String, Object> extras = new HashMap<>();
            LoginUser loginUser = null;
            if (isNeedTenant) {
                loginUser = new LoginUser(w.getTenantId(), 1);
            }

            if (StringUtils.isNotBlank(appSceneKey)) {
                messagePushService.pushAppMessage(appSceneKey, extras, loginUser, driver.getUserId() + "");
            }

            if (StringUtils.isNotBlank(smsSceneKey)) {
                messagePushService.pushSmsMessage(smsSceneKey, extras, loginUser, driver.getPhone());
            }
        }
    }

    @Override
    public List<WaybillDriverVo> listWaybillDriverByVendorId(Integer vendorId, String startPlanDeliveryTime,
        String endPlanDeliveryTime,  List<Integer> listStatusView) {
        List<WaybillDriverVo> result = new ArrayList<>();
        if (null == vendorId) {
            return result;
        }

        List<com.juma.vms.driver.domain.Driver> drivers = vmsCommonService.listAllDriverByVendor(vendorId);
        for (com.juma.vms.driver.domain.Driver driver : drivers) {
            WaybillDriverVo vo = new WaybillDriverVo();
            vo.setDriver(driver);

            // 获取司机的运单数量
            PageCondition cond = new PageCondition();
            Map<String, Object> filters = new HashMap<>();
            filters.put("driverId", driver.getDriverId());
            if (CollectionUtils.isNotEmpty(listStatusView)) {
                filters.put("statusViewList", listStatusView);
            }

            if (StringUtils.isNotBlank(startPlanDeliveryTime) && StringUtils.isNotBlank(endPlanDeliveryTime)) {
                filters.put("startTime", startPlanDeliveryTime);
                filters.put("endTime", endPlanDeliveryTime);
            }

            cond.setFilters(filters);
            cond.setPageNo(1);
            cond.setPageSize(300);
            vo.setWaybillCount(waybillCommonService.searchCount(cond));

            result.add(vo);
        }

        return result;
    }

    @Override
    public Page<WaybillBo> searchForDriver(PageCondition pageCondition, LoginUser loginUser) throws BusinessException {
        if (null == loginUser || null == loginUser.getUserId()) {
            return new Page<>(pageCondition.getPageNo(), pageCondition.getPageSize(), 0, new ArrayList<WaybillBo>());
        }

        // 获取司机信息
        com.juma.vms.driver.domain.Driver driver = vmsCommonService.loadDriverByUserId(loginUser.getUserId());
        if (null == driver) {
            return new Page<>(pageCondition.getPageNo(), pageCondition.getPageSize(), 0, new ArrayList<WaybillBo>());
        }

        Map<String, Object> filters = pageCondition.getFilters();
        if (null == filters) {
            filters = new HashMap<>();
        }
        filters.put("driverId", driver.getDriverId());

        Page<WaybillBo> page = this.getPageForTodoWaybillList(pageCondition, loginUser);
        for (WaybillBo bo : page.getResults()) {
            bo.setVmsDriver(driver);
        }

        return page;
    }

    @Override
    public Page<WaybillBo> searchForVendor(PageCondition pageCondition, LoginUser loginUser) throws BusinessException {
        if (null == loginUser || null == loginUser.getUserId()) {
            return new Page<>(pageCondition.getPageNo(), pageCondition.getPageSize(), 0, new ArrayList<WaybillBo>());
        }

        // 获取承运商信息
        Vendor vendor = vmsCommonService.loadVendorByUserId(loginUser.getUserId());
        if (null == vendor) {
            return new Page<>(pageCondition.getPageNo(), pageCondition.getPageSize(), 0, new ArrayList<WaybillBo>());
        }

        Map<String, Object> filters = pageCondition.getFilters();
        if (null == filters) {
            filters = new HashMap<>();
        }
        filters.put("vehicleToVendor", vendor.getVendorId());

        Page<WaybillBo> page = this.getPageForTodoWaybillList(pageCondition, loginUser);
        for (WaybillBo bo : page.getResults()) {
            bo.setVmsDriver(vmsCommonService.loadDriverByDriverId(bo.getDriverId()));
        }

        return page;
    }
}
