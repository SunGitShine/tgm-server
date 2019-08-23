package com.juma.tgm.waybill.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.conf.service.BusinessAreaService;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.employee.service.EmployeeService;
import com.juma.auth.user.domain.LoginEcoUser;
import com.juma.auth.user.domain.LoginUser;
import com.juma.conf.domain.ConfParamOption;
import com.juma.conf.service.ConfParamService;
import com.juma.message.domain.MsgConf;
import com.juma.message.domain.MsgConf.MsgType;
import com.juma.message.gateway.service.MessageServiceProvider;
import com.juma.tgm.common.Constants;
import com.juma.tgm.common.query.QueryCond;
import com.juma.tgm.configure.domain.PrivateFreightContext;
import com.juma.tgm.configure.domain.ServiceConf;
import com.juma.tgm.configure.service.PrivateFreightFactorService;
import com.juma.tgm.configure.service.ServiceConfService;
import com.juma.tgm.fms.domain.v3.enums.AdjustMasterType;
import com.juma.tgm.fms.domain.v3.enums.AdjustType;
import com.juma.tgm.fms.service.ReconciliationService;
import com.juma.tgm.fms.service.v3.AdjustForItemService;
import com.juma.tgm.landing.waybill.service.DispatchingTruckService;
import com.juma.tgm.project.domain.Project;
import com.juma.tgm.project.service.ProjectService;
import com.juma.tgm.redis.service.TemperatureAlertService;
import com.juma.tgm.scatteredWaybill.service.ScatteredMsgService;
import com.juma.tgm.scatteredWaybill.service.ScatteredWaybillService;
import com.juma.tgm.tools.service.MessagePushService;
import com.juma.tgm.vendor.domain.VendorProjectMapping;
import com.juma.tgm.vendor.service.VendorMappingService;
import com.juma.tgm.waybill.domain.AddressHistory;
import com.juma.tgm.waybill.domain.DistanceAndPriceData;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.Waybill.Status;
import com.juma.tgm.waybill.domain.WaybillBo;
import com.juma.tgm.waybill.domain.WaybillDetailInfo;
import com.juma.tgm.waybill.domain.WaybillInfo;
import com.juma.tgm.waybill.domain.WaybillMap;
import com.juma.tgm.waybill.domain.WaybillParam;
import com.juma.tgm.waybill.domain.WaybillReceiveAddress;
import com.juma.tgm.waybill.domain.vo.WaybillCarrierVo;
import com.juma.tgm.waybill.enumeration.WaybillOperateTrackEnum.OperateApplication;
import com.juma.tgm.waybill.enumeration.WaybillOperateTrackEnum.OperateType;
import com.juma.tgm.waybill.service.AddressHistoryService;
import com.juma.tgm.waybill.service.TaxRateService;
import com.juma.tgm.waybill.service.WaybillCommonService;
import com.juma.tgm.waybill.service.WaybillOperateTrackService;
import com.juma.tgm.waybill.service.WaybillParamService;
import com.juma.tgm.waybill.service.WaybillQueryService;
import com.juma.tgm.waybill.service.WaybillReceiveAddressService;
import com.juma.tgm.waybill.service.WaybillService;
import com.juma.tgm.waybill.service.WaybillViewService;
import com.juma.tgm.waybill.service.customize.jumaPs.JumaPsWaybillService;
import com.juma.tgm.waybill.vo.WaybillAdjustPriceVo;
import com.juma.tgm.waybill.vo.WaybillFilter;
import com.juma.tgm.waybill.vo.WaybillOperateTrackFilter;
import com.juma.tgm.waybill.vo.WaybillOperateTrackQuery;
import com.juma.tgm.waybill.vo.WaybillQuery;
import com.juma.tgm.waybillReconciliation.domain.WaybillReconciliation;
import com.juma.tgm.waybillReconciliation.service.WaybillReconciliationService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import testng.BaseTestNGTest;

public class WaybillServiceImplTest extends BaseTestNGTest {

    @Resource
    private WaybillService waybillService;
    
    @Resource
    private ServiceConfService serviceConfService;
    
    @Resource
    private ConfParamService confParamService;
    
    @Resource
    private WaybillCommonService waybillCommonService;

    @Resource
    private AddressHistoryService addressHistoryService;
    
    @Resource
    private WaybillQueryService waybillQueryService;
    
    @Resource
    private ScatteredWaybillService scatteredWaybillService;
    
    @Resource
    private DispatchingTruckService dispatchingTruckService;
    
    @Resource
    private MessageServiceProvider messageServiceProvider;
    
    @Resource
    private ScatteredMsgService scatteredMsgService;
    
    @Resource
    private WaybillReconciliationService waybillReconciliationService;
    
    @Resource
    private ReconciliationService reconciliationService;
    
    @Resource
    private MessagePushService messagePushService;
    
    @Resource
    private BusinessAreaService businessAreaService;
    
    @Resource
    private EmployeeService employeeService;
    
    @Resource
    private PrivateFreightFactorService privateFreightFactorService;
    
    @Resource
    private TemperatureAlertService temperatureAlertService;
    
    @Resource
    private JumaPsWaybillService jumaPsWaybillService;
    
    @Resource
    private WaybillParamService waybillParamService;
    
    @Resource
    private VendorMappingService vendorMappingService;
    
    @Resource
    private ProjectService projectService;
    
    @Resource
    private TaxRateService taxRateService;

    @Resource
    private WaybillViewService waybillViewService;

    @Resource
    private WaybillReceiveAddressService waybillReceiveAddressService;

    @Resource
    private WaybillOperateTrackService waybillOperateTrackService;
    @Resource
    private AdjustForItemService adjustForItemService;

    @Test
    public void updateBatchByPrimaryKeySelective() {
        List<WaybillReceiveAddress> rows = new ArrayList<>();
        WaybillReceiveAddress a = new WaybillReceiveAddress();
        a.setWaybillId(10086);
        a.setAddressId(1560420);
        a.setSequence(1);


        rows.add(a);
        waybillReceiveAddressService.updateBatchByPrimaryKeySelective(rows);
    }

    @Test
    public void demo() {
        
        temperatureAlertService.startUp(1);
        temperatureAlertService.scan();
       // temperatureAlertService.close("川A12345");
        
        String view = temperatureAlertService.viewRedisToString();
        System.out.println(view);
    }
    
    @Test
    public void dataTransfer() {
        LoginUser loginUser = new LoginUser();
        loginUser.setUserId(4524);
        loginUser.setTenantId(26);
        
        PrivateFreightContext context = new PrivateFreightContext();
        privateFreightFactorService.calFreight("22000", 1, 61, context, loginUser);
        
        /*Waybill waybill = new Waybill();
        waybill.setWaybillId(156823);
        
        waybillService.changeToDeliveried(waybill, loginUser);*/
        
    }
    
    @Test
    public void getWaybill() {

        
        
        
        
        LoginUser loginUser = new LoginUser();
        loginUser.setUserId(4235);
        loginUser.setTenantId(3);
        
        
        PageCondition pageCondition = new PageCondition();
        //pageCondition.put("receiveUserId", "4235");
        pageCondition.setPageNo(1);
        pageCondition.setPageSize(20);
        /*Page p = messageServiceProvider.searchDetails(pageCondition, loginUser);
        System.out.println(p);*/
        
        waybillService.pushUseCarTimeMsg();
        
        MsgConf msgConf = messageServiceProvider.getMsgTemplate(3, "SCATTERED_ASSIGNED_WAYBILL", MsgType.APP);
        System.out.println(msgConf);
        
        //waybillCommonService.findWaybillMapPoint(252, 1);
        
        /*Waybill waybill = waybillCommonService.getWaybillById(13773);
        waybillService.updateLeaveDepotTime(waybill, loginEcoUser);
        
        scatteredWaybillService.changeToDeliveried(waybill, loginUser);
        
        Assert.assertEquals(waybill.getWaybillId() + "", 12771 + "");*/

        
       /* LoginUser loginUser = new LoginUser();
        loginUser.setUserId(5);
        loginUser.setTenantId(2);
        
        List<Waybill> rows = waybillService.findWaitingAcceptWaybill(loginUser);
        System.out.println(rows);*/
        
        //dispatchingTruckService.findTruck(13773, loginUser);
        
        /*dispatchingTruckService.buildFeature(13773, Feature.Operate.Dispatcher_Arrange);
        
        dispatchingTruckService.buildFeature(13773, Feature.Operate.Start_Delivery);
        
        dispatchingTruckService.buildFeature(13773, Feature.Operate.Finish_Delivery);*/
        
        /*dispatchingTruckService.buildFeature(13773, Feature.Operate.Dispatcher_Cancel);
        
        dispatchingTruckService.buildFeature(13773, Feature.Operate.Driver_No_Response);*/
        
        /*List<ConfParamOption> options = confParamService.findParamOptions("NO_DRIVER_ANSWER");
        int min = options == null || options.isEmpty() || options.get(0).getOptionValue() == null? 6 : Integer.valueOf(options.get(0).getOptionValue());
        System.out.println(min);
        List<Waybill> waybills = new ArrayList<Waybill>();
        Waybill waybill = new Waybill();
        waybill.setWaybillId(13832);
        waybill.setDriverId(null);//取消绑定关系  司机没有响应 不可见
        waybill.setStatus(Waybill.Status.WATING_RECEIVE.getCode());
        waybill.setStatusView(Waybill.StatusView.WATING_RECEIVE.getCode());
        waybills.add(waybill);*/
        //waybillCommonService.batchUpdate(waybills);
    }
    
    @Test
    public void findWaybillMapPoint() {
        //waybillCommonService.findWaybillMapPoint(172, 1);
    }


    @Test
    public void getPageForAcceptableWaybillList() {
        
        PageCondition pageCondition = new PageCondition();
        pageCondition.setPageNo(1);
        pageCondition.setPageSize(10);
        
        LoginUser user  =new LoginUser(2,2);
        
//        BusinessArea businessArea = businessAreaService.loadLogicBusinessArea("000106", user);
//        System.out.println(businessArea);
        
        Page<WaybillBo> page = waybillService.getPageForAcceptableWaybillList(pageCondition, user);
        
        Assert.assertNotNull(page.getResults());
        
    }

    @Test
    public void lastReceiveAddrs() {

        PageCondition pageCondition = new PageCondition();

        pageCondition.setPageNo(1);
        pageCondition.setPageSize(20);
        //pageCondition.put("addressType", AddressHistory.AddressType.END.getCode());
        LoginUser loginUser = new LoginUser();
        loginUser.setUserId(3212);

        List<AddressHistory> a = addressHistoryService.getAddressAndContactHistory(pageCondition, loginUser);

        System.out.println(JSONObject.toJSONString(a));
    }


    /**
     * 运单加跑
     */
    @Test
    public void createWaybillWithDriverTest() {
        String waybillBoJson = "{\"canUseCustomerInfo\":true,\"cdate\":\"2017-08-08 13:42:19\",\"coldChain\":0,\"customerId\":141,\"" +
                "customerInfo\":{\"areaCode\":\"000400\",\"company\":\"1313\",\"createTime\":\"2017-05-09 17:25:09\",\"crmCustomerId\":375,\"customerId\":141,\"customerManagerUserId\":87,\"customerName\":\"12312312\",\"customerType\":2,\"delete\":false,\"employeeId\":87,\"industry\":\"123\",\"isProjectCheckOut\":false,\"lastUpdateTime\":\"2017-08-08 13:42:19\",\"linkMan\":\"1\",\"orgCodeCertificate\":\"1\",\"regionCode\":\"00200\",\"tenantCode\":\"12\",\"waybillCount\":54}," +
                "\"driverTruckInfoBo\":{\"contactPhone\":\"15882104171\",\"deriverId\":251,\"deriverStatus\":2,\"dervicerName\":\"sll\",\"entryLicense\":0,\"header\":\"upload\\/images\\/3668c6cd11ab2580687ad5d53c16e126.png\",\"isAcceptAllocateOrders\":0,\"plateNumber\":\"川A7V903\",\"starLevel\":\"5.0\",\"truckId\":75,\"truckTypeName\":\" 厢式4.2米\"}," +
                "\"expireTimeLength\":300000,\"goodsInfoStr\":\"粮油 1吨 1方\"," +
                "\"reportInfoList\":[{\"reportInfoType\":2,\"reportInfoTypeView\":\"封路\",\"reportTime\":\"2017-07-19 15:11:14\"},{\"reportInfoType\":2,\"reportInfoTypeView\":\"封路\",\"reportTime\":\"2017-07-19 15:11:53\"}]," +
                "\"truckCustomer\":{\"contactPhone\":\"15882104186\",\"createTime\":\"2017-08-08 13:42:19\",\"delete\":false,\"lastUpdateTime\":\"2017-08-08 13:42:19\",\"nickname\":\"量11\"}," +
                "\"truckCustomerForUserCar\":{\"areaCode\":\"2\",\"classId\":2,\"contactPhone\":\"18428380000\",\"createTime\":\"2017-04-11 21:33:53\",\"createUserId\":2921,\"delete\":false,\"lastUpdateTime\":\"2017-08-08 13:42:19\",\"nickname\":\"测试0\",\"regionCode\":\"32000\",\"status\":1,\"truckCustomerId\":134,\"userId\":3041}," +
                "\"truckRequire\":{\"collectPayment\":false,\"createTime\":\"2017-08-08 13:42:19\",\"delete\":false,\"entryLicense\":0,\"goodsType\":\"粮油\",\"goodsVolume\":\"1\",\"goodsWeight\":\"1\",\"isTailBoard\":0,\"lastUpdateTime\":\"2017-08-08 13:42:19\",\"receipt\":false,\"remark\":\"\",\"truckRequireId\":4931,\"truckTypeId\":11,\"waybillId\":12770}," +
                "\"truckRequireStr\":\"高栏3.7米\"," +
                "\"waybill\":{\"afterTaxFreight\":0.00,\"areaCode\":\"000400\",\"arriveDepotTime\":\"2017-07-19 15:12:08\",\"calculatedFreight\":26300.46,\"checkout\":false,\"compareResult\":95.54,\"createTime\":\"2017-07-19 15:08:40\",\"createUserId\":2975,\"customerId\":141,\"customerManagerId\":87,\"delete\":false,\"driverId\":251,\"endCoordinates\":\"104.396603,36.052938\",\"entryLicense\":0,\"estimateDistance\":1000,\"estimateFreight\":26300.46,\"estimateTimeConsumption\":799,\"finishTime\":\"2017-07-19 15:16:39\",\"flightUsageId\":108,\"freight\":0.00,\"isChangeDeliveryPoint\":1,\"isSubmitMatch\":0,\"lastUpdateTime\":\"2017-08-08 13:42:19\",\"needDeliveryPointNote\":1,\"needReceipt\":0,\"onlyLoadCargo\":0,\"orderNo\":0,\"ownerEmployeeId\":87,\"payTransactionTime\":\"2017-07-19 15:16:42\",\"planDeliveryTime\":\"2017-07-19 15:15:00\",\"rebateFee\":0.00,\"rebateRate\":0.10,\"receiptType\":4,\"receiveWay\":3,\"reconciliationStatus\":1,\"regionCode\":\"22000\",\"selfWaybill\":true,\"show4DriverFreight\":0.00,\"startCoordinates\":\"104.119480,30.943721\",\"status\":7,\"statusView\":5,\"statusViewText\":\"已完成\",\"submitToErp\":false,\"test\":false,\"truckCustomerId\":134,\"truckId\":75,\"updateFreightAuditStatus\":0,\"updateFreightRemark\":\"企鹅王芙蓉区\",\"vehicleBoxType\":2,\"waybillId\":12770,\"waybillIds\":[],\"waybillNo\":\"2017071985519012770\",\"waybillSource\":1}," +
                "\"waybillDeliveryAddresses\":[{\"addressDetail\":\"四川省成都市彭州市蒙阳镇农产品交易中心\",\"addressId\":5008,\"addressName\":\"雨润蔬菜\",\"cityname\":\"成都市\",\"contactName\":\"吉利\",\"contactPhone\":\"15882104180\",\"coordinates\":\"104.119480,30.943721\",\"createTime\":\"2017-07-19 15:08:40\",\"createUserId\":2975,\"delete\":false,\"isArrived\":1,\"lastUpdateTime\":\"2017-07-19 15:12:08\",\"lastUpdateUserId\":3255,\"regionCode\":\"22000\",\"simpleAddress\":\"成都市彭州市蒙阳镇农产品交易中心\",\"spareContactPhone\":\"\",\"waybillId\":12770}],\"waybillParam\":{\"createTime\":\"2017-07-19 15:10:39\",\"createUserId\":3255,\"delete\":false,\"driverHandlingCost\":0.00,\"driverRead\":0,\"laborerHandlingCost\":0.00,\"lastUpdateTime\":\"2017-08-08 13:42:19\",\"paramId\":826,\"updateDeliveryPointSupplementTime\":\"2017-07-20 12:41:39\",\"uploadDeliveryPointSupplementTime\":\"2017-07-19 15:10:39\",\"waybillId\":12770},\"waybillReceiveAddresses\":[{\"addressDetail\":\"四川省成都市双流区新下街242号\",\"addressId\":11496,\"addressName\":\"锦华农贸商城(新下街)\",\"cityname\":\"成都市\",\"coordinates\":\"104.087482,30.551331\",\"createTime\":\"2017-07-20 12:41:32\",\"createUserId\":1,\"delete\":false,\"isArrived\":0,\"lastUpdateTime\":\"2017-07-20 12:41:35\",\"regionCode\":\"22000\",\"simpleAddress\":\"成都市双流区新下街242号\",\"waybillId\":12770},{\"addressDetail\":\"甘肃省兰州市榆中县榆中县\",\"addressId\":11497,\"addressName\":\"观天湾\",\"cityname\":\"兰州市\",\"coordinates\":\"104.396603,36.052938\",\"createTime\":\"2017-07-20 12:41:32\",\"createUserId\":1,\"delete\":false,\"isArrived\":0,\"lastUpdateTime\":\"2017-07-20 12:41:35\",\"regionCode\":\"27000\",\"simpleAddress\":\"兰州市榆中县榆中县\",\"waybillId\":12770}]}";
        WaybillDetailInfo detailInfo = JSONObject.parseObject(waybillBoJson, WaybillDetailInfo.class);
        Waybill waybill = detailInfo.getWaybill();

        Date planTime = DateUtils.addMinutes(new Date(), 30);
        waybill.setPlanDeliveryTime(planTime);
        Date cmEstimateFinishTime = DateUtils.addHours(planTime, 5);
        waybill.setCmEstimateFinishTime(cmEstimateFinishTime);

        WaybillBo bo = new WaybillBo();
        bo.setWaybill(waybill);
        bo.setTruckRequire(detailInfo.getTruckRequire());
        bo.setDeliveryAddress(detailInfo.getWaybillDeliveryAddresses());
        bo.setReceiveAddress(detailInfo.getWaybillReceiveAddresses());

        LoginUser loginUser = new LoginUser();
        loginUser.setUserId(2975);

        waybillService.createWaybillWithDriver(bo, Waybill.WaybillSource.JUMA_CLIENT, loginUser);



    }

    // 未付款运单
    @Test
    public void hasNoPayWaybill() {
        LoginEmployee loginEmployee = new LoginEmployee();
        loginEmployee.setEmployeeId(87);
        WaybillInfo waybillInfo = waybillService.hasNoPayWaybill(loginEmployee);

        Assert.assertNotNull(waybillInfo);
    }

    // 客户经理修改用车时间
    @Test
    public void modifyPlanDeliveryTimeByManager() {
        LoginEmployee loginEmployee = new LoginEmployee();
        loginEmployee.setEmployeeId(275);
        loginEmployee.setUserId(3212);
        
        Waybill waybill = new Waybill();
        waybill.setWaybillId(12961);
        waybill.setPlanDeliveryTime(new Date());
        
        waybillService.modifyPlanDeliveryTimeByManager(waybill, loginEmployee);
    }
    
    // 司机修改用车时间
    @Test
    public void modifyPlanDeliveryTimeByDriver() {
        LoginEcoUser driverLoginEcoUser = new LoginEcoUser();
        driverLoginEcoUser.setEcoUserId(4251);
        driverLoginEcoUser.setUserId(3256);
        
        
        Waybill waybill = new Waybill();
        waybill.setWaybillId(12961);
        waybill.setPlanDeliveryTime(new Date());
        
        waybillService.modifyPlanDeliveryTimeBydriver(waybill, driverLoginEcoUser);
    }

    @Test
    public void finish() {
        Waybill waybill = new Waybill();
        waybill.setWaybillId(23065);
        
        waybillService.changeToDeliveried(waybill, new LoginUser(5938));
    }

    @Test
    private void recountThePrice() {
        DistanceAndPriceData recountThePrice = waybillService.recountThePrice(38532, null, null, new LoginUser(3255));
        System.out.println(JSON.toJSONString(recountThePrice));
    }
    
    @Test
    private void receiveWaybill() {
        final Waybill waybill = new Waybill();
        waybill.setWaybillId(13218);

        final LoginEcoUser loginEcoUser = new LoginEcoUser();
        loginEcoUser.setUserId(2);
//        loginEcoUser.setEcoUserId(4250);

        final LoginEcoUser loginEcoUser1 = new LoginEcoUser();
        loginEcoUser1.setUserId(3290);
        loginEcoUser1.setEcoUserId(4285);
        waybillService.receiveWaybill(waybill, loginEcoUser);

//        SpringContextHelper.getSpringBean(TaskExecutor.class).execute(new Runnable() {
//            @Override
//            public void run() {
//                waybillService.receiveWaybill(waybill, loginEcoUser);
//            }
//        });
//        SpringContextHelper.getSpringBean(TaskExecutor.class).execute(new Runnable() {
//            @Override
//            public void run() {
//                waybillService.receiveWaybill(waybill, loginEcoUser1);
//            }
//        });
//
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
    
    @Test
    private void strategySearch() {
        // {"filters":{"areaCodeList":"00"}}
        
        PageCondition page = new PageCondition();
        page.setPageNo(1);
        page.setPageSize(Integer.MAX_VALUE);
        
        List<String> areaCodeList = new ArrayList<String>();
        areaCodeList.add("00");
        
        //page.put("areaCodeList", areaCodeList);
        //page.put("startTime", "2016-07-01 00:00:00");
        //page.put("endTime", "2017-10-01 00:00:00");
        
        long begin = System.currentTimeMillis();
        
//        Page<WaybillBo> search = waybillService.strategySearch(page, null);
//        long end = System.currentTimeMillis();
//        System.out.println("总共：" + search.getTotal() + "条");
//        System.out.println("总共耗时：" + (end - begin) + "MS");
    }

    @Test
    public void updateByExampleTest(){
        Waybill example = new Waybill();
        example.setProjectId(1);

        Waybill newValue = new Waybill();
        newValue.setProjectName("new Project Name");

        waybillService.updateWaybillBatch(example, newValue, Constants.SYS_LOGIN_USER);
    }
    
    @Test
    public void test() {
        //waybillCronService.cronUpdateYesActualMileage();
    }
    
    @Test
    public void buildCustomerServiceTel() {
        ServiceConf serviceConf =  serviceConfService.findByRegionCode("asd", Constants.SYS_LOGIN_USER);
        if(serviceConf == null || serviceConf.getCustomerServiceTel() == null) {
            List<ConfParamOption> options = confParamService.findParamOptions("CUSTOMER_SERVICE_TEL");
            System.out.println(options);
        }
    }
    
    @Test
    public void cancel() {
        LoginEmployee loginEmployee = new LoginEmployee();
        loginEmployee.setUserId(1);
        loginEmployee.setTenantId(3);
        
        waybillService.cancelWaybill(13856, Waybill.CancelChannel.BACKGROUND_IMPORT, "testng测试", loginEmployee);
    }
    
    @Test
    public void allowChangeCar() {
        Waybill waybill = waybillService.getWaybill(13868);
        
        boolean allowChangeCar = waybillService.allowChangeCar(waybill);
        
        System.out.println(allowChangeCar);
    }
    
    @Test
    public void push() {
        Waybill waybill = waybillService.getWaybill(13868);
        
        LoginUser loginUser = Constants.SYS_LOGIN_USER;
        loginUser.setTenantId(3);
        
        scatteredMsgService.pushAssignedWaybillToDriverSmsMsg(waybill, loginUser);
    }
    
    @Test
    public void testR() {
        WaybillReconciliation r = new WaybillReconciliation();
        r.setWaybillId(13589);
        
        LoginEmployee loginEmployee = new LoginEmployee();
        loginEmployee.setUserId(1);
        loginEmployee.setTenantId(2);
        waybillReconciliationService.update(r, new ArrayList<String>(), loginEmployee);
    }
    
    @Test
    public void findByReconciliationId() {
//        List<Waybill> list = waybillCommonService.findByReconciliationId(126);
//        System.out.println(JSON.toJSONString(list));

        List<Waybill> waybills = waybillCommonService.findByReconciliationId(19);

        for (Waybill waybill : waybills) {
            if (NumberUtils.compare(Waybill.ReconciliationStatus.NOT_RECONCILIATION.getCode(),
                    waybill.getReconciliationStatus()) == 0) {
                waybill.setReconciliationStatus(Waybill.ReconciliationStatus.HAS_RECONCILIATION.getCode());
            }
        }

        waybillCommonService.batchUpdateHasReconciliation(waybills);
    }

    @Test
    public void testC() {
        reconciliationService.test();
//        LoginUser loginUser = Constants.SYS_LOGIN_USER;
//        loginUser.setTenantId(4);
//        messagePushService.driverConfirmAssigned(144688, loginUser);
    }
    
    @Test
    public void assignedWaybillV2() {
        Waybill waybill = new Waybill();
        LoginEcoUser driverLoginEcoUser = new LoginEcoUser();
        driverLoginEcoUser.setUserId(8);
        driverLoginEcoUser.setEcoUserId(2);
        
        waybill.setWaybillId(144504);
        
        if (waybill.getWaybillId() == null) {
            throw new BusinessException("validationFailure", "errors.validation.failure");
        }

        Waybill waybillDb = waybillService.getWaybill(waybill.getWaybillId());
        if (waybillDb == null) {
            throw new BusinessException("waybillNotfound", "waybill.error.notfound");
        }
        
        driverLoginEcoUser.setTenantId(waybillDb.getTenantId());
        waybillService.changeToDeliveried(waybill, driverLoginEcoUser);
        System.out.println(1111111);

    }
    
    @Test
    public void customerManagerModifyFreight() {
        Waybill w = new Waybill();
        w.setWaybillId(999070);
        w.setEstimateFreight(BigDecimal.ZERO);
        w.setUpdateFreightAuditRemark("123454");
        
        LoginEmployee l = new LoginEmployee();
        l.setTenantId(2);
        l.setUserId(7);
        l.setEmployeeId(6);
        
        jumaPsWaybillService.customerManagerModifyFreight(w, l);
    }

    @Test
    public void modofyTransformBillToDeliveried() {
        WaybillParam waybillParam = waybillParamService.findByTransformBillLinkId(999290);
        if (null == waybillParam) {
            return;
        }
        Waybill waybill = waybillCommonService.getWaybillById(waybillParam.getWaybillId());

        waybill.setFinishTime(new Date());
        waybill.setStatus(Status.PAIED.getCode());
        waybill.setStatusView(Waybill.StatusView.FINISH.getCode());

        waybillCommonService.update(waybill, Constants.SYS_LOGIN_USER);
    }

    @Test
    public void buildWaybillCarrierVo() {
        WaybillCarrierVo vo = new WaybillCarrierVo();
        VendorProjectMapping projectMapping = vendorMappingService.findVendorProjectMappingBy(123,
                85895, 1, Constants.SYS_LOGIN_USER);
        if (null == projectMapping || null == projectMapping.getVendorProjectId()) {
            return;
        }

        Project project = projectService.getProject(projectMapping.getVendorProjectId());
        if (null == project) {
            return;
        }

        vo.setProjectId(project.getProjectId());

//        TaxRate taxRate = taxRateService.getTaxRate(project.getTaxRateId());
//        if (null == taxRate) {
//            return;
//        }
        vo.setBillTaxRate(project.getTaxRateValue());
    }

    @Test
    public void testNoDepartment() {
        PageCondition pageCondition = new PageCondition();

        Map<String, Object> filters = new HashMap<>();
        filters.put("backstage", true);

        List<Integer> statusViewList = new ArrayList<Integer>();
        statusViewList.add(Waybill.StatusView.WATING_RECEIVE.getCode());
        statusViewList.add(Waybill.StatusView.WATING_DELIVERY.getCode());
        statusViewList.add(Waybill.StatusView.DELIVERYING.getCode());
        statusViewList.add(Waybill.StatusView.FINISH.getCode());
        filters.put("statusViewList", statusViewList);
        filters.put("queryDepartmentIsNull", "queryDepartmentIsNull");
        filters.put("reconciliationStatus", Waybill.ReconciliationStatus.NOT_RECONCILIATION.getCode());
        pageCondition.setFilters(filters);
        pageCondition.setOrderBy(
                StringUtils.isBlank(pageCondition.getOrderBy()) ? " planDeliveryTime " : pageCondition.getOrderBy());
        pageCondition.setOrderSort(
                StringUtils.isBlank(pageCondition.getOrderSort()) ? " desc " : pageCondition.getOrderSort());
        Page<Waybill> page = waybillService.search(new LoginUser(2, 1), pageCondition);
        System.out.println(JSON.toJSON(page));
    }

    @Test
    public void addDepartment() {
        Waybill waybill = new Waybill();
        waybill.setVehicleToVendor(12);
        List<Integer> waybillIds = new ArrayList<>();
        waybillIds.add(999791);
        waybill.setWaybillIds(waybillIds);
        Waybill updateWaybill = new Waybill();
        updateWaybill.setVehicleToVendor(waybill.getVehicleToVendor());
        for (Integer waybillId : waybill.getWaybillIds()) {
            Waybill wb = waybillCommonService.getWaybillById(waybillId);
            if (null == wb) {
                continue;
            }

            if (NumberUtils.compare(wb.getReceiveWay(), Waybill.ReceiveWay.TRANSFORM_BILL.getCode()) != 0) {
                updateWaybill.setWaybillId(waybillId);
            }
            waybillCommonService.update(updateWaybill, new LoginUser(2, 1));
        }
    }

    @Test
    public void searchPageList(){
        LoginEmployee l = new LoginEmployee();
        l.setTenantId(19);
        l.setUserId(29961);
        l.setEmployeeId(6);
        PageCondition pageCondition = new PageCondition();
        pageCondition.setPageNo(1);
        pageCondition.setPageSize(10);
        pageCondition.getFilters().put("tenantId", 19);
        pageCondition.getFilters().put("userId", 29961);
        pageCondition.getFilters().put("_hasParams", false);
        pageCondition.getFilters().put("customerManagerId", 9907);
        pageCondition.getFilters().put("createUserId", 29961);
        pageCondition.getFilters().put("projectManagerUserId", 29961);
        waybillService.searchPageList(pageCondition, l);
    }

@Test
    public void findWaybillDetailById() {
        waybillService.findWaybillDetailById(1513103, new LoginUser(2, 1));

    }


    @Test
    public void changeToWaitingReceive() {
        waybillService.changeToWaitingReceive(1513103, new LoginUser(2, 1));
    }

    @Test
    public void supplement() {
        PageCondition pageCondition = new PageCondition();

        Map<String, Object> filters = new HashMap<>();
//        filters.put("backstage", true);

//        List<Integer> statusViewList = new ArrayList<Integer>();
//        statusViewList.add(Waybill.StatusView.WATING_RECEIVE.getCode());
//        statusViewList.add(Waybill.StatusView.WATING_DELIVERY.getCode());
//        statusViewList.add(Waybill.StatusView.DELIVERYING.getCode());
//        statusViewList.add(Waybill.StatusView.FINISH.getCode());
//        filters.put("statusViewList", statusViewList);
//        filters.put("departmentIdIsNull", "departmentIdIsNull");
//        filters.put("finishMonthDate", "2018-11");
        pageCondition.setFilters(filters);
        pageCondition.setPageSize(15);
        pageCondition.setPageNo(1);

        pageCondition.setOrderBy(
                StringUtils.isBlank(pageCondition.getOrderBy()) ? " planDeliveryTime " : pageCondition.getOrderBy());
        pageCondition.setOrderSort(
                StringUtils.isBlank(pageCondition.getOrderSort()) ? " desc " : pageCondition.getOrderSort());
        Page<Waybill> page = waybillService.search(new LoginUser(9, 1), pageCondition);
    }


    @Test
    public void createWaybill() {
        String json =  "{\\\"waybillBo\\\":{\\\"allowAddHandlingCost\\\":true,\\\"allowCancel\\\":false,\\\"allowChangeCar\\\":false,\\\"allowSendCar\\\":true,\\\"asProjectWaybillHandle\\\":false,\\\"completeWorkload\\\":false,\\\"createBatchAmount\\\":1,\\\"createTime\\\":\\\"2019-05-28 16:52:31\\\",\\\"delete\\\":false,\\\"deliveryAddress\\\":[{\\\"addressDetail\\\":\\\"湖南省长\n"
            + "沙市雨花区[]\\\",\\\"addressName\\\":\\\"德永仓储\\\",\\\"contactName\\\":\\\"李滔\\\",\\\"contactPhone\\\":\\\"17508413650\\\",\\\"coordinates\\\":\\\"113.062103,28.059353\\\",\\\"createTime\\\":\\\"2019-01-13 09:37:23\\\",\\\"createUserId\\\":29533,\\\"delete\\\":false,\\\"lastUpdateTime\\\":\\\"2019-05-28 16:52:31\\\",\\\"regionCode\\\":\\\"17000\\\"}],\\\"deliveryPointSupplementList\\\":[],\\\"driverHasRead\\\":false,\\\"hasReplaceCar\\\":false,\\\"isSubmitMatch\\\":0,\\\"lastUpdateTime\\\":\\\"2019-05-28 16:52:31\\\",\\\"listReceiveAddressResponse\\\":[],\\\"lowerCalculatedFreight\\\":false,\\\"ownerAreaCanOperate\\\":true,\\\"priceExceptionFlag\\\":false,\\\"receiptImageList\\\":[],\\\"receiveAddress\\\":[{\\\"addressDetail\\\":\\\"湖南省长沙市雨花区长沙雨花经济开发区环保科技园\\\",\\\"addressName\\\":\\\"环保科技园\\\",\\\"contactName\\\":\\\"\\\",\\\"contactPhone\\\":\\\"\\\",\\\"coordinates\\\":\\\"113.03686700000003,28.069248\\\",\\\"createTime\\\":\\\"2019-01-13 09:37:23\\\",\\\"createUserId\\\":29533,\\\"regionCode\\\":\\\"17000\\\"}],\\\"selfWaybill\\\":true,\\\"shareAreaCanOperate\\\":true,\\\"showYourPrice\\\":false,\\\"truckRequire\\\":{\\\"additionalFunctionIds\\\":\\\"7,6\\\",\\\"coldChain\\\":false,\\\"collectPayment\\\":false,\\\"createTime\\\":\\\"2019-05-28 16:52:31\\\",\\\"delete\\\":false,\\\"entryLicense\\\":0,\\\"goodsAmount\\\":\\\"1\\\",\\\"goodsType\\\":\\\"食品\\\",\\\"goodsVolume\\\":\\\"1\\\",\\\"goodsWeight\\\":\\\"1\\\",\\\"lastUpdateTime\\\":\\\"2019-05-28 16:52:31\\\",\\\"needBackStorage\\\":true,\\\"receipt\\\":true,\\\"taxRateValue\\\":0.09,\\\"truckTypeId\\\":520},\\\"uploadedReceipt\\\":false,\\\"valuationWays\\\":[],\\\"waybill\\\":{\\\"allowCancel\\\":false,\\\"allowChangeCar\\\":false,\\\"allowSendCar\\\":true,\\\"areaCode\\\":\\\"000500\\\",\\\"asProjectWaybillHandle\\\":false,\\\"businessBranch\\\":3,\\\"calculatedFreight\\\":0.00,\\\"cmEstimateFinishTime\\\":\\\"2019-05-28 21:50:28\\\",\\\"completeWorkload\\\":false,\\\"customerId\\\":104031,\\\"customerName\\\":\\\"宁波瓜瓜农业科技有限公司(长沙)-G\\\",\\\"delete\\\":false,\\\"departmentId\\\":412,\\\"driverHasRead\\\":false,\\\"estimateFreight\\\":1000,\\\"isSubmitMatch\\\":0,\\\"lastUpdateTime\\\":\\\"2019-05-28 16:52:31\\\",\\\"logisticsLabel\\\":\\\"2\\\",\\\"miniFreight\\\":0.00,\\\"ownerAreaCanOperate\\\":true,\\\"planDeliveryTime\\\":\\\"2019-05-28 17:20:28\\\",\\\"priceExceptionFlag\\\":false,\\\"projectId\\\":6019,\\\"projectManagerUserId\\\":1,\\\"projectManagerUserName\\\":\\\"超级管理员\\\",\\\"projectName\\\":\\\"美鲜团购配送\\\",\\\"receiptType\\\":3,\\\"receiptTypeText\\\":\\\"项目结算\\\",\\\"receiveWay\\\":3,\\\"referenceFreight\\\":0.00,\\\"roadMapId\\\":11643,\\\"roadMapName\\\":\\\"环保 暮云\\\",\\\"selfWaybill\\\":true,\\\"shareAreaCanOperate\\\":true,\\\"showYourPrice\\\":false,\\\"statusViewText\\\":\\\"\\\",\\\"truckCustomerId\\\":14274,\\\"valuationWays\\\":[],\\\"waybillIds\\\":[],\\\"waybillNo\\\":\\\"\\\",\\\"waybillSource\\\":4,\\\"waybillSourceText\\\":\\\"后台下单\\\",\\\"whoWriteWork\\\":1},\\\"waybillIds\\\":[],\\\"waybillParam\\\":{\\\"createTime\\\":\\\"2019-05-28 16:52:31\\\",\\\"delete\\\":false,\\\"lastUpdateTime\\\":\\\"2019-05-28 16:52:31\\\",\\\"projectFreightRuleJson\\\":\\\"{\\\\\\\"truckTypeId\\\\\\\":520,\\\\\\\"roadMapId\\\\\\\":11643,\\\\\\\"valuationWay\\\\\\\":2,\\\\\\\"factorJson\\\\\\\":\\\\\\\"{\\\\\\\\\\\\\\\"estimateFreight\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\"1000\\\\\\\\\\\\\\\"}\\\\\\\"}\\\",\\\"valuationWays\\\":[]}}}";

        WaybillBo waybillBo = JSON.parseObject(json, WaybillBo.class);

        Integer id = waybillService.createWaybill(waybillBo, Waybill.WaybillSource.BACKGROUND_NEW, new LoginUser(4, 29961));

    }

    @Test
    public void findWaybillMapById() {
        WaybillMap map = waybillQueryService.findWaybillMapById(1512882);
    }

    @Test
    public void operateTrackInsert() {
        waybillOperateTrackService.insert(1513134, OperateType.CREATE_WAYBILL,
            OperateApplication.BACKGROUND_SYS, null,
            new LoginUser(19, 1));
    }

    @Test
    public void operateTrackSearch() {
        QueryCond<WaybillOperateTrackFilter> queryCond = new QueryCond();
        WaybillOperateTrackFilter filter = new WaybillOperateTrackFilter();
        filter.setWaybillId(1513126);
        queryCond.setFilters(filter);
        queryCond.setPageNo(1);
        queryCond.setPageSize(8);

        Page<WaybillOperateTrackQuery> search = waybillOperateTrackService.search(queryCond);
    }

    @Test
    public void loadAdjustAbsFreightByWaybillIdAnd() {
        adjustForItemService.loadAdjustAbsFreightByWaybillIdAnd(1390012, AdjustType.BEFORE.getCode(),
                AdjustMasterType.CUSTOMER.getCode());
    }

    @Test
    public void doCloseWaybill() {
        waybillService.doCloseWaybill(1513129, null);
    }

    @Test
    public void addPriceWaybill() {
        waybillService.addPriceWaybill(1513131, 10000, null);
    }

    @Test
    public void findByWaybillNo() {
        Waybill waybill = waybillCommonService.findByWaybillNo("2019042674323057965");
    }

    @Test
    public void listWayillForDriver() {
        waybillService
            .listWayillForDriver(new WaybillFilter(), 20, "plan_delivery_time asc", new LoginUser(19, 11292));
    }

}
