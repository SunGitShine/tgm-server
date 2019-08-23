package com.juma.tgm.cron.bussinessModules;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.giants.cache.redis.RedisClient;
import com.giants.common.exception.BusinessException;
import com.giants.common.exception.GiantsException;
import com.juma.auth.conf.domain.BusinessArea;
import com.juma.auth.conf.service.BusinessAreaService;
import com.juma.auth.employee.domain.Employee;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.employee.service.EmployeeService;
import com.juma.auth.user.domain.LoginUser;
import com.juma.auth.user.domain.User;
import com.juma.auth.user.service.UserService;
import com.juma.conf.domain.Region;
import com.juma.tgm.common.Constants;
import com.juma.tgm.common.RedisKeyConstants;
import com.juma.tgm.crm.domain.CustomerInfo;
import com.juma.tgm.crm.service.CustomerInfoService;
import com.juma.tgm.cron.service.impl.SendTaskService;
import com.juma.tgm.customerManager.domain.Task4Waybill;
import com.juma.tgm.customerManager.domain.Task4WaybillReport;
import com.juma.tgm.customerManager.domain.TaskWaybillTemplate;
import com.juma.tgm.customerManager.domain.TaskWaybillTemplateDestAddress;
import com.juma.tgm.customerManager.domain.TaskWaybillTemplateSrcAddress;
import com.juma.tgm.customerManager.domain.TaskWaybillTemplateTruckBinding;
import com.juma.tgm.customerManager.domain.vo.taskWaybill.Task4WaybillReportCountVo;
import com.juma.tgm.customerManager.domain.vo.taskWaybill.Task4WaybillReportQueryVo;
import com.juma.tgm.customerManager.domain.vo.taskWaybill.TaskWaybillTemplateEnum;
import com.juma.tgm.customerManager.service.Task4WaybillReportService;
import com.juma.tgm.customerManager.service.Task4WaybillService;
import com.juma.tgm.customerManager.service.TaskWaybillTemplateService;
import com.juma.tgm.filiale.service.FilialeBillService;
import com.juma.tgm.landingWaybill.domain.AtFenceResultVo;
import com.juma.tgm.project.domain.ProjectFreightRule;
import com.juma.tgm.project.domain.v2.Project;
import com.juma.tgm.project.domain.v2.enums.ProjectEnum;
import com.juma.tgm.project.enumeration.ValuationWayEnum;
import com.juma.tgm.project.service.ProjectService;
import com.juma.tgm.project.vo.ProjectBillVo;
import com.juma.tgm.scatteredWaybill.service.ScatteredWaybillService;
import com.juma.tgm.sop.domain.Sop;
import com.juma.tgm.sop.service.SopService;
import com.juma.tgm.tools.service.MessagePushService;
import com.juma.tgm.truck.domain.AdditionalFunction;
import com.juma.tgm.truck.domain.Truck;
import com.juma.tgm.truck.service.AdditionalFunctionService;
import com.juma.tgm.truck.service.TruckService;
import com.juma.tgm.waybill.domain.CityAdressData;
import com.juma.tgm.waybill.domain.DistanceAndPriceData;
import com.juma.tgm.waybill.domain.TruckRequire;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillDeliveryAddress;
import com.juma.tgm.waybill.domain.WaybillParam;
import com.juma.tgm.waybill.domain.WaybillReceiveAddress;
import com.juma.tgm.waybill.service.WaybillCommonService;

/**
 * @ClassName: Task4WaybillModule
 * @Description:
 * @author: liang
 * @date: 2018-10-12 14:11
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
@Component
public class Task4WaybillModule {

    private static final Logger log = LoggerFactory.getLogger(Task4WaybillModule.class);

    /**
     * 定时下单任务app消息key
     */
    private static final String TMS_TASK_WAYBILL_REPORT_APP = "TMS_TASK_WAYBILL_REPORT_APP";

    /**
     * 定时下单任务短信消息key
     */
    private static final String TMS_TASK_WAYBILL_REPORT_SMS = "TMS_TASK_WAYBILL_REPORT_SMS";


    @Resource
    private RedisClient redisClient;
    @Resource
    private FilialeBillService filialeBillService;
    @Resource
    private ApplicationEventPublisher publisher;
    @Resource
    private TaskWaybillTemplateService taskWaybillTemplateService;
    @Resource
    private ProjectService projectService;
    @Resource
    private CustomerInfoService customerInfoService;
    @Resource
    private SopService sopService;
    @Resource
    private EmployeeService employeeService;
    @Resource
    private TruckService truckService;
    @Resource
    private Task4WaybillReportService task4WaybillReportService;
    @Resource
    private MessagePushService messagePushService;
    @Resource
    private UserService userService;
    @Resource
    private Task4WaybillService task4WaybillService;
    @Resource
    private WaybillCommonService waybillCommonService;
    @Resource
    private BusinessAreaService businessAreaService;
    @Resource
    private ScatteredWaybillService scatteredWaybillService;
    @Resource
    private AdditionalFunctionService additionalFunctionService;
    @Resource
    private SendTaskService sendTaskService;


//    /**
//     * 读取redis
//     *
//     * @return
//     */
//    public List<Task4Waybill> getRedisTask() {
//        log.info("开始自动建单");
//        //从redis中获取需要自动建单的数据
//        Set<String> keys = redisClient.keys(RedisKeyConstants.TMS_TASK_4_WAYBILL_AUTO_CREATE_BILL_KEY + "*");
//        if (CollectionUtils.isEmpty(keys)) {
//            log.info("自动建单redis为空");
//            return null;
//        }
//
//        List<Task4Waybill> task4WaybillList = new ArrayList<>();
//        Task4Waybill task4Waybill = null;
//        for (String key : keys) {
//            task4Waybill = (Task4Waybill) redisClient.get(key);
//            if (task4Waybill == null || task4Waybill.getTaskId() == null) continue;
//           //查下数据库里面是否还有
//            Task4Waybill _fromDB = task4WaybillService.get(task4Waybill.getTaskId());
//            if(_fromDB == null) {
//                publisher.publishEvent(task4Waybill.getTaskId());
//            } else {
//                task4WaybillList.add(_fromDB);
//            }
//        }
//
//        return task4WaybillList;
//
//    }
//
    /**
     * 校验是否在执行范围内
     *
     * @param task4Waybill
     * @return
     */
//    public boolean judgeExecutable(Task4Waybill task4Waybill) {
//        //是否开启定时任务
//        //是否在时间范围内
//
//        //检查是否今天下单
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.DATE, 1);
//        int dayCode = calendar.get(Calendar.DAY_OF_WEEK);
//        String day = TaskWaybillTemplateEnum.dayOfWeek.get(dayCode);
//
//        if (StringUtils.isEmpty(task4Waybill.getTaskWeekDays())) return false;
//
//        //用车日期
//        Date deliveryDate = calendar.getTime();
//        Date startDate = task4Waybill.getTaskStartDate();//用车时间
//
//        Date endDate = task4Waybill.getTaskEndDate();//用车时间
//
//        //用车日期应该在范围内
//        //还没到自动下单日期
//        if (DateUtils.truncatedCompareTo(deliveryDate, startDate, Calendar.DATE) < 0) return false;
//        //超过了自动下单日期
//        if (DateUtils.truncatedCompareTo(deliveryDate, endDate, Calendar.DATE) > 0) return false;
//
//        //包含明天
//        String[] availableDays = StringUtils.split(task4Waybill.getTaskWeekDays(), ",");
//        Arrays.sort(availableDays);
//        if (Arrays.binarySearch(availableDays, day) >= 0) return true;
//
//        return false;
//    }

//    public ProjectBillVo buildProjectBillVo(Task4Waybill task4Waybill) {
//        Integer templateId = task4Waybill.getTaskWaybillTemplateId();
//
//        if (templateId == null)
//            throw new BusinessException("task4WaybillTaskWaybillTemplateIdNullError", "errors.common.prompt", "任务模板配置错误");
//
//        //任务模板
//        TaskWaybillTemplate taskWaybillTemplate = taskWaybillTemplateService.get(templateId);
//        if (taskWaybillTemplate == null)
//            throw new BusinessException("taskWaybillTemplateNotExistError", "errors.common.prompt", "任务模板不存在");
//        //运单信息
//
//        //用车要求
//        if (StringUtils.isEmpty(taskWaybillTemplate.getRequireJson()))
//            throw new BusinessException("taskTemplateTruckRequireNullError", "errors.common.prompt", "任务模板用车要求为空");
//        TruckRequire truckRequire = JSONObject.parseObject(taskWaybillTemplate.getRequireJson(), TruckRequire.class);
//        //计价规则
//        WaybillParam waybillParam = new WaybillParam();
//        waybillParam.setProjectFreightRuleJson(taskWaybillTemplate.getProjectFreightRuleJson());
//
//        //项目信息
//        Project project = projectService.getProject(taskWaybillTemplate.getProjectId());
//        if (project == null) throw new BusinessException("projectNotExistError", "errors.common.prompt", "项目不存在");
//
//        //线路信息
//
//        //固定车辆
//
//        ProjectBillVo billVo = new ProjectBillVo();
//        billVo.setProject(project);
//        billVo.setTruckRequire(truckRequire);
//        billVo.setCreateBatchAmount(taskWaybillTemplate.getVehicleCount());
//
//        return null;
//
//    }

    /**
     * 组装项目单参数
     *
     * @param taskWaybillTemplate
     * @param createEmployee
     * @return
     */
    private ProjectBillVo buildProjectBillVo(TaskWaybillTemplate taskWaybillTemplate, LoginEmployee createEmployee, Task4Waybill task4Waybill) {
        if (StringUtils.isEmpty(taskWaybillTemplate.getProjectFreightRuleJson()))
            throw new BusinessException("projectFreightRuleNullError", "errors.common.prompt", "计价规则为空");

        if (taskWaybillTemplate.getValuationWay() == null)
            throw new BusinessException("valuationWayNullError", "errors.common.prompt", "计价方式");

        Waybill waybill = new Waybill();
        //一口价则直接设置价格
        if (NumberUtils.compare(taskWaybillTemplate.getValuationWay(), ValuationWayEnum.FIXED_PRICE.getCode()) == 0) {
            ProjectFreightRule projectFreightRule = JSONObject.parseObject(taskWaybillTemplate.getProjectFreightRuleJson(), ProjectFreightRule.class);
            Map<String, BigDecimal> factor = JSONObject.parseObject(projectFreightRule.getFactorJson(), new TypeReference<Map<String, BigDecimal>>() {});
            if (factor == null) throw new BusinessException("freightRuleNullError", "errors.common.prompt", "一口价设置为空");
            waybill.setEstimateFreight(factor.get("estimateFreight"));
        }

        Date planDeliveryTime = null;
        try {
            planDeliveryTime = this.buildDate(taskWaybillTemplate.getDeliveryTimePoint());
        } catch (Exception e) {
            log.warn("自动建单失败,用车时间构造失败,{}", taskWaybillTemplate.getDeliveryTimePoint(), e);
            throw new BusinessException("planDeliveryTimeFormatError", "errors.common.prompt", "用车时间格式错误");
        }
        //项目信息
        com.juma.tgm.project.domain.v2.Project project = projectService.getProjectV2(taskWaybillTemplate.getProjectId());
        if (project == null) throw new BusinessException("projectNotExistError", "errors.common.prompt", "项目不存在");
        //线路信息
        List<TaskWaybillTemplateSrcAddress> srcAddressList = taskWaybillTemplateService.getTemplateSrcAddressByTemplateId(taskWaybillTemplate.getTaskWaybillTemplateId());
        List<TaskWaybillTemplateDestAddress> destAddressList = taskWaybillTemplateService.getTemplateDestAddressByTemplateId(taskWaybillTemplate.getTaskWaybillTemplateId());

        ProjectBillVo billVo = new ProjectBillVo();
        billVo.setReceiveWay(taskWaybillTemplate.getReceiveWay().intValue());
        billVo.setTruckRequire(JSON.parseObject(taskWaybillTemplate.getRequireJson(), TruckRequire.class));
        billVo.setAutoCreate(true);
        billVo.setCreateBatchAmount(taskWaybillTemplate.getVehicleCount());
        billVo.setDeliveryAddress(this.transformToSrc(srcAddressList));
        billVo.setReceiveAddress(this.transformToDest(destAddressList));
        billVo.setProject(project);

        //构造运单登录人
        CustomerInfo customerInfo = customerInfoService.findCusInfoById(taskWaybillTemplate.getCustomerId());
        createEmployee.setTenantId(customerInfo.getTenantId());
        createEmployee.setTenantCode(customerInfo.getTenantCode());

        Sop sop = sopService.findNewestVersionSopByTenantId(createEmployee.getTenantId());
        if (sop == null) {
            throw new BusinessException("errors.notFound.Sop", "errors.notFound.Sop", createEmployee.getTenantId());
        }

        //计价规则
        WaybillParam waybillParam = new WaybillParam();
        billVo.setWaybillParam(waybillParam);
        waybillParam.setProjectFreightRuleJson(taskWaybillTemplate.getProjectFreightRuleJson());
        waybillParam.setRequiredMinTemperature(taskWaybillTemplate.getRequiredMinTemperature());
        waybillParam.setRequiredMaxTemperature(taskWaybillTemplate.getRequiredMaxTemperature());
        waybillParam.setSopId(sop.getSopId());
        if (billVo.getTruckRequire().isCollectPayment()) {
            waybillParam.setAgencyTakeFreight(taskWaybillTemplate.getAgencyTakeFreight());
        }
        //组装运单信息
        billVo.setWaybill(waybill);

        waybill.setWaybillSource(Waybill.WaybillSource.FIXED_DEMAND_AUTO_CREATE.getCode());
        waybill.setReceiveWay(Waybill.ReceiveWay.ASSIGNED.getCode());
        Integer onlyLoadCargo = taskWaybillTemplate.getOnlyLoadCargo() == null ? null : taskWaybillTemplate.getOnlyLoadCargo().intValue();
        waybill.setOnlyLoadCargo(onlyLoadCargo);
        waybill.setRebateRate(project.getRebateRate());
        waybill.setCustomerManagerId(taskWaybillTemplate.getCustomerManagerId());
        waybill.setAfterTaxFreight(new BigDecimal("0"));
        waybill.setPlanDeliveryTime(planDeliveryTime);
        waybill.setReceiptType(Waybill.ReceiptType.PROJECTPAY.getCode());
        waybill.setRoadMapId(taskWaybillTemplate.getRoadMapId());
        waybill.setCustomerId(taskWaybillTemplate.getCustomerId());
        waybill.setWaybillRemark(taskWaybillTemplate.getRemark());
        Integer businessBranch = taskWaybillTemplate.getBusinessBranch() == null ? null : taskWaybillTemplate.getBusinessBranch().intValue();
        waybill.setBusinessBranch(businessBranch);
        waybill.setLogisticsLabel(project.getLogisticsLabel());
        waybill.setTest(false);

        Date estimateFinishTime = DateUtils.addMinutes(waybill.getPlanDeliveryTime(), taskWaybillTemplate.getFinishTimePoint());
        waybill.setCmEstimateFinishTime(estimateFinishTime);

        //客户经理
        Employee customerMan = employeeService.loadEmployee(taskWaybillTemplate.getCustomerManagerId());
//        //客户经理和客户不匹配
//        if (customerMan == null) {
//            log.warn("自动建单失败,客户经理找不到,{}", taskWaybillTemplate.getCustomerManagerId());
//            throw new BusinessException("customerManagerNotExist", "errors.common.prompt", "客户经理不存在");
//        }
//        if (!customerInfoService.customerBelongToManager(taskWaybillTemplate.getCustomerId(), taskWaybillTemplate.getCustomerManagerId())) {
//            throw new BusinessException("customerManagerNotMatchCustomer", "errors.common.prompt", "客户不属于客户经理");
//        }

        createEmployee.setUserId(task4Waybill.getLastUpdateUserId());
        if(customerMan != null){
            createEmployee.setEmployeeId(customerMan.getEmployeeId());
        }

        waybill.setTruckCustomerId(taskWaybillTemplate.getTruckCustomerId());

        this.transformTaxRate(billVo);

        return billVo;

    }

    private void transformTaxRate(ProjectBillVo billVo) {
        TruckRequire truckRequire = billVo.getTruckRequire();
        if (truckRequire != null && truckRequire.getTaxRateValue() == null) {
            truckRequire.setTaxRateValue(BigDecimal.ZERO);
        }
    }


    /**
     * 转为运单发货地
     *
     * @param srcPoint
     * @return
     */
    private List<WaybillDeliveryAddress> transformToSrc(List<TaskWaybillTemplateSrcAddress> srcPoint) {
        if (CollectionUtils.isEmpty(srcPoint)) return null;

        TaskWaybillTemplateSrcAddress src = srcPoint.get(0);
        WaybillDeliveryAddress srcAddr = new WaybillDeliveryAddress();
        srcAddr.setAddressDetail(src.getAddressDetail());
        srcAddr.setAddressName(src.getAddressName());
        srcAddr.setContactName(src.getContactName());
        srcAddr.setContactPhone(src.getContactPhone());
        srcAddr.setCoordinates(src.getCoordinates());
        srcAddr.setRegionCode(src.getRegionCode());

        List<WaybillDeliveryAddress> all = new ArrayList<>();
        all.add(srcAddr);

        return all;
    }

    /**
     * 转为运单收货地
     *
     * @param destPoint
     * @return
     */
    private List<WaybillReceiveAddress> transformToDest(List<TaskWaybillTemplateDestAddress> destPoint) {
        if (CollectionUtils.isEmpty(destPoint)) return null;

        List<WaybillReceiveAddress> all = new ArrayList<>();

        WaybillReceiveAddress recAddr = null;
        for (TaskWaybillTemplateDestAddress point : destPoint) {
            recAddr = new WaybillReceiveAddress();
            recAddr.setRegionCode(point.getRegionCode());
            recAddr.setCoordinates(point.getCoordinates());
            recAddr.setContactPhone(point.getContactPhone());
            recAddr.setContactName(point.getContactName());
            recAddr.setAddressDetail(point.getAddressDetail());
            recAddr.setAddressName(point.getAddressName());

            all.add(recAddr);
        }

        return all;
    }


    /**
     * 时间点转日期（hh:mm）
     *
     * @param timePointString
     * @return
     */
    private Date buildDate(String timePointString) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_WEEK, 1);

        String dateStr = Constants.YYYYMMDDHHMM.format(calendar.getTime());

        String planDeliveryTimeStr = StringUtils.left(dateStr, 11) + timePointString;

        Date planDeliveryTime = null;
        try {
            planDeliveryTime = Constants.YYYYMMDDHHMM.parse(planDeliveryTimeStr);
        } catch (ParseException e) {
            log.warn("固定需求自动建单计划用车时间格式错误.", e);
            throw new BusinessException("fixedDemandAutoCreateBillPlanDeliveryTimeError", "errors.paramErrorWithName", "固定需求自动建单计划用车时间");
        }
        return planDeliveryTime;
    }

    /**
     * 指定任务id执行任务
     *
     * @param taskIds
     */
    public void createBillByTaskIds(List<Integer> taskIds) {
        if (CollectionUtils.isEmpty(taskIds)) {
            log.info("指定执行任务id为空,退出执行");
            return;
        }

        for (Integer taskId : taskIds) {
            Task4Waybill task4Waybill = task4WaybillService.get(taskId);
            if (task4Waybill == null) continue;
            try {
                this.processTask4Waybill(task4Waybill);
            } catch (Exception e) {
                log.info("自动建单发生错误", e);
                handleExceptionReport(task4Waybill, e);
            }
        }
    }

    /**
     * 执行下单逻辑
     */
    public void sendTaskToMQ() {
        log.info("开始发送定时发单消息到MQ");
        List<Task4Waybill> task4WaybillList = task4WaybillService.findCanExecutableTask();

        if (CollectionUtils.isEmpty(task4WaybillList)) {
            log.info("建单任务为空,程序退出执行.");
            return;
        }

        for (Task4Waybill task4Waybill : task4WaybillList) {

            log.info("任务消息：" + JSON.toJSONString(task4Waybill));
            sendTaskService.send(task4Waybill);
        }

        log.info("定时发单消息发送结束");

    }


    /**
     * 处理运单任务
     *
     * @param task4Waybill
     */
    public void processTask4Waybill(Task4Waybill task4Waybill) {

        if (null == task4Waybill) return;

        LoginEmployee createEmployee = new LoginEmployee();

        Integer templateId = task4Waybill.getTaskWaybillTemplateId();

        if (templateId == null)
            throw new BusinessException("task4WaybillTaskWaybillTemplateIdNullError", "errors.common.prompt", "任务模板配置错误");

        //任务模板
        TaskWaybillTemplate taskWaybillTemplate = taskWaybillTemplateService.get(templateId);
        if (taskWaybillTemplate == null)
            throw new BusinessException("taskWaybillTemplateNotExistError", "errors.common.prompt", "任务模板不存在");

        ProjectBillVo billVo = this.buildProjectBillVo(taskWaybillTemplate, createEmployee, task4Waybill);

        Project project = billVo.getProject();
        if(project.getProjectStatus() == null || project.getProjectStatus().compareTo(ProjectEnum.ProjectStatus.RUNING.getCode()) != 0)
            throw new BusinessException("projectStatusError", "errors.common.prompt", "项目状态不是运行中");

        if (StringUtils.equals(createEmployee.getTenantCode(), Constants.TENANT_CODE_XIDI_LOGISTICS)) {
            this.buildXidiBusinessData(billVo.getDeliveryAddress(),billVo.getReceiveAddress(), createEmployee,billVo.getWaybill(), billVo.getTruckRequire());
        }

        this.doCreateBill(taskWaybillTemplate, billVo, createEmployee);
    }


    /**
     * 希地业务参数
     *
     * @param srcAddress
     * @param destAddress
     * @param loginUser
     * @param waybill
     * @param truckRequire
     */
    private void buildXidiBusinessData(List<WaybillDeliveryAddress> srcAddress, List<WaybillReceiveAddress> destAddress, LoginUser loginUser, Waybill waybill, TruckRequire truckRequire) {
        CityAdressData sourceCityAddress = this.buildWaybillDeliveryAddress2CityAddressData(srcAddress);
        List<CityAdressData> destCityAddress = this.buildWaybillReceiveAddress2CityAdressData(destAddress);
        AtFenceResultVo atFenceResult = scatteredWaybillService.isAtFenceArea(sourceCityAddress, destCityAddress, loginUser);
        if (atFenceResult != null) {
            //禁货区域
            if (atFenceResult.isAtForbiddenArea()) {
                StringBuilder tip = new StringBuilder("");
                if (NumberUtils.compare(atFenceResult.getForBiddenType().getCode(), AtFenceResultVo.ForbiddenType.sourceArea.getCode()) == 0) {
                    tip.append(atFenceResult.getForBiddenType().getDesc());
                } else if (NumberUtils.compare(atFenceResult.getForBiddenType().getCode(), AtFenceResultVo.ForbiddenType.destinationArea.getCode()) == 0) {
                    tip.append("第" + atFenceResult.getForbiddenAreaIndex() + "个配送地");
                } else {
                    throw new BusinessException("atFenceTypeError", "errors.common.prompt", "禁货入城区域类型错误");
                }
                tip.append("在禁货区");

                throw new BusinessException("deliveryAddressError", "errors.common.prompt", tip.toString());
            }

            //是否在业务区域内
            if (!atFenceResult.getAtBusinessArea()) {
                throw new BusinessException("deliveryAddressBusinessError", "errors.common.prompt", "该地区暂未开通业务");
            }

            //希地单需要判断入城证（入城区域）
            this.addEntryLicenseFunction(atFenceResult.getAtCity(), truckRequire);

            DistanceAndPriceData rst = new DistanceAndPriceData();
            this.buildRegionData(sourceCityAddress, destCityAddress, rst, loginUser);
            //预估距离
            waybill.setEstimateDistance(rst.getDistance());
            //regionCode
            waybill.setRegionCode(rst.getRegionCode());
            //高速费用
            waybill.setTolls(rst.getTolls());
        }
    }


    /**
     * 取货地转城市地址信息
     * for 查询入城/禁货区域
     *
     * @param srcAddress 取货地
     * @return
     */
    private CityAdressData buildWaybillDeliveryAddress2CityAddressData(List<WaybillDeliveryAddress> srcAddress) {

        if (CollectionUtils.isEmpty(srcAddress))
            throw new BusinessException("srcAddressNullError", "errors.paramCanNotNullWithName", "转运单取货地");

        WaybillDeliveryAddress deliveryAddress = srcAddress.get(0);

        CityAdressData cityAdressData = new CityAdressData();

        cityAdressData.setCoordinate(deliveryAddress.getCoordinates());
        cityAdressData.setRegionCode(deliveryAddress.getRegionCode());
        cityAdressData.setAddress(deliveryAddress.getAddressName());
        cityAdressData.setAddressDetail(deliveryAddress.getAddressDetail());
        cityAdressData.setCity(deliveryAddress.getCityname());

        return cityAdressData;
    }

    /**
     * 配送地转城市地址信息
     * for 查询入城/禁货区域
     *
     * @param destAddress
     * @return
     */
    private List<CityAdressData> buildWaybillReceiveAddress2CityAdressData(List<WaybillReceiveAddress> destAddress) {
        if (CollectionUtils.isEmpty(destAddress))
            throw new BusinessException("srcAddressNullError", "errors.paramCanNotNullWithName", "转运单配送地");

        List<CityAdressData> destCityAddresses = new ArrayList<>();

        CityAdressData cityAdressData = null;

        for (WaybillReceiveAddress receiveAddress : destAddress) {
            cityAdressData = new CityAdressData();

            cityAdressData.setCity(receiveAddress.getCityname());
            cityAdressData.setAddressDetail(receiveAddress.getAddressDetail());
            cityAdressData.setAddress(receiveAddress.getAddressName());
            cityAdressData.setRegionCode(receiveAddress.getRegionCode());
            cityAdressData.setCoordinate(receiveAddress.getCoordinates());

            destCityAddresses.add(cityAdressData);
        }

        return destCityAddresses;
    }

    /**
     * 用车要求添加入城证
     *
     * @param inCity
     * @param truckRequire
     */
    private void addEntryLicenseFunction(boolean inCity, TruckRequire truckRequire) {
        if (!inCity) return;

        if (truckRequire == null) return;
        AdditionalFunction function = additionalFunctionService.findAdditionFunctionByKey(AdditionalFunction.FunctionKeys.ENTRY_LICENSE.name());

        if (function == null) {
            log.warn("找不到入城证配置:{}", new String[]{AdditionalFunction.FunctionKeys.ENTRY_LICENSE.name()});
            return;
        }

        //没有填用车要求
        if (StringUtils.isBlank(truckRequire.getAdditionalFunctionIds())) {
            truckRequire.setAdditionalFunctionIds(function.getAdditionalFunctionId() + "");
            return;
        }

        //填写了用车要求
        //是否有入城证id
        String[] ids = StringUtils.split(truckRequire.getAdditionalFunctionIds(), ",");

        Arrays.sort(ids);
        if (Arrays.binarySearch(ids, function.getAdditionalFunctionId() + "") > 0) return;

        String[] nIds = (String[]) ArrayUtils.add(ids, function.getAdditionalFunctionId() + "");

        StringBuffer sb = new StringBuffer();
        for (String id : nIds) {
            sb.append(id);
            sb.append(",");
        }
        String target = StringUtils.removeEnd(sb.toString(), ",");
        truckRequire.setAdditionalFunctionIds(target);
    }

    /**
     * 获取距离区域信息
     *
     * @param srcAddress
     * @param destAddresses
     * @param rst
     * @param loginUser
     */
    private void buildRegionData(CityAdressData srcAddress, List<CityAdressData> destAddresses, DistanceAndPriceData rst, LoginUser loginUser) {
        DistanceAndPriceData distanceData = waybillCommonService.getGaodeMapInfo(srcAddress, destAddresses);

        if (distanceData == null) return;

        rst.setDistance(distanceData.getDistance());
        rst.setDuration(distanceData.getDuration());
        rst.setRegionCode(distanceData.getRegionCode());
        rst.setTollDistance(distanceData.getTollDistance());
        rst.setTolls(distanceData.getTolls());

        //获取业务区域
        if (distanceData.getRegion() == null) return;

        Region region = distanceData.getRegion();
        rst.setRegionCode(StringUtils.left(region.getRegionCode(), 5));
        BusinessArea businessArea = businessAreaService.loadBelongingBusinessArea(loginUser.getTenantId(), region);
        distanceData.setRegion(null);
        if (businessArea == null) {
            //找不到业务区域则将运单归属到总部
            businessArea = businessAreaService.loadLogicBusinessArea("00", loginUser);
        }

        if (businessArea == null) return;
        //行政区域到市一级
        rst.setWaybillAreaCode(businessArea.getAreaCode());

    }

    private void doCreateBill(TaskWaybillTemplate taskWaybillTemplate, ProjectBillVo billVo, LoginEmployee createEmployee) {

        if (NumberUtils.compare(taskWaybillTemplate.getReceiveWay(), Waybill.ReceiveWay.MANUAL_ASSIGN.getCode()) == 0) {
            //调度指派
            billVo.getWaybill().setReceiveWay(Waybill.ReceiveWay.MANUAL_ASSIGN.getCode());

        } else if (NumberUtils.compare(taskWaybillTemplate.getReceiveWay(), Waybill.ReceiveWay.ASSIGNED.getCode()) == 0) {
            //固定车辆
            //获取绑定的车辆
            List<TaskWaybillTemplateTruckBinding> truckBindingList = taskWaybillTemplateService.getTruckBindingByTemplateId(taskWaybillTemplate.getTaskWaybillTemplateId());

            if (CollectionUtils.isEmpty(truckBindingList))
                throw new BusinessException("truckBindingNotExistError", "errors.common.prompt", "没有绑定车辆");

            this.fixedDemandTruckToProjectFixedTruck(billVo, truckBindingList);
        }

        List<Integer> waybillIds = filialeBillService.createProjectBill(billVo, createEmployee);
        log.info("自动建单成功-waybillIds:{}", JSON.toJSONString(waybillIds));
    }

    /**
     * 固定需求固定车辆转项目固定车辆
     *
     * @param billVo
     * @param truckBindingList
     */
    private void fixedDemandTruckToProjectFixedTruck(ProjectBillVo billVo, List<TaskWaybillTemplateTruckBinding> truckBindingList) {
        if (billVo == null) return;

        if (CollectionUtils.isEmpty(truckBindingList)) return;

        List<Integer> truckIds = new ArrayList<>();

        Map<String, Integer> truckTypeCounter = new HashMap<>();
        for (TaskWaybillTemplateTruckBinding truckBinding : truckBindingList) {
            Truck truck = truckService.getTruck(truckBinding.getTruckId());
            if (truck == null) continue;

            Integer vehicleBoxType = truck.getVehicleBoxType();
            Integer vehicleBoxLength = truck.getVehicleBoxLength();
            if (vehicleBoxType == null || vehicleBoxLength == null) continue;
            String key = vehicleBoxType + "," + vehicleBoxLength;
            truckIds.add(truck.getTruckId());

            if (truckTypeCounter.containsKey(key)) {
                Integer count = truckTypeCounter.get(key) + 1;
                truckTypeCounter.put(key, count);
            } else {
                truckTypeCounter.put(key, 1);
            }
        }

        Set<ProjectBillVo.TruckValidator> truckValidatorList = new HashSet<>();
        ProjectBillVo.TruckValidator validator = null;
        for (String key : truckTypeCounter.keySet()) {
            validator = new ProjectBillVo.TruckValidator();
            String[] values = key.split(",");
            Integer vehicleBoxType = Integer.valueOf(values[0]);
            Integer vehicleBoxLength = Integer.valueOf(values[1]);

            validator.setVehicleBoxLength(vehicleBoxLength);
            validator.setVehicleBoxType(vehicleBoxType);
            validator.setAmount(truckTypeCounter.get(key));

            truckValidatorList.add(validator);
        }

        billVo.setTruckValidator(truckValidatorList);
        billVo.setFixedTruckIds(new HashSet<Integer>(truckIds));

    }

    /**
     * 处理执行异常，保存每个执行结果
     *
     * @param task4Waybill
     * @param e
     */
    private void handleExceptionReport(Task4Waybill task4Waybill, Exception e) {
        String errorMsg = "系统错误;";

        if (e instanceof GiantsException) {
            errorMsg = ((GiantsException) e).getErrorMessage();
        } else {
            errorMsg += "task id:" + task4Waybill.getTaskId();
        }

        Task4WaybillReport task4WaybillReport = new Task4WaybillReport();
        task4WaybillReport.setTaskStatus(TaskWaybillTemplateEnum.TaskStatusEnum.TASK_STATUS_FAIL.getCode());
        task4WaybillReport.setTaskResult(errorMsg);
        task4WaybillReport.setTaskId(task4Waybill.getTaskId());
        task4WaybillReport.setCreateTime(new Date());
        task4WaybillReport.setTaskExcuteDate(task4WaybillReport.getCreateTime());
        task4WaybillReport.setCreateUserId(task4Waybill.getCreateUserId());
        task4WaybillReport.setEmployeeId(task4Waybill.getEmployeeId());

        task4WaybillReportService.addReport(task4WaybillReport);
    }


    /**
     * 处理正常执行的任务
     *
     * @param task4Waybill
     */
    public void handleNormalReport(Task4Waybill task4Waybill) {
        Task4WaybillReport task4WaybillReport = new Task4WaybillReport();
        task4WaybillReport.setTaskStatus(TaskWaybillTemplateEnum.TaskStatusEnum.TASK_STATUS_SUCCESS.getCode());
        task4WaybillReport.setTaskResult("发单成功");
        task4WaybillReport.setTaskId(task4Waybill.getTaskId());
        task4WaybillReport.setCreateTime(new Date());
        task4WaybillReport.setTaskExcuteDate(task4WaybillReport.getCreateTime());
        task4WaybillReport.setCreateUserId(task4Waybill.getCreateUserId());
        task4WaybillReport.setEmployeeId(task4Waybill.getEmployeeId());

        task4WaybillReportService.addReport(task4WaybillReport);
    }


    /**
     * 执行发推送
     *
     * @param task4Waybill
     */
    public void pushTaskReportMsg(Task4Waybill task4Waybill) {
        Employee employee = this.getReportUserForPush(task4Waybill);

        if (null == employee) {
            log.warn("没有需要发送消息的用户");
            return;
        }

        this.buildMsgContent(employee);

    }

    /**
     * 递归获取当日需要推送消息的用户
     *
     * @param task4Waybill
     * @return
     */
    private Employee getReportUserForPush(Task4Waybill task4Waybill) {

        if(task4Waybill == null || task4Waybill.getEmployeeId() == null){
            return null;
        }

        return employeeService.loadEmployee(task4Waybill.getEmployeeId());
    }

    /**
     * 按用户推送执行报告
     *
     * @param employee
     */
    private void buildMsgContent(Employee employee) {
        Task4WaybillReportQueryVo queryVo = new Task4WaybillReportQueryVo();
        queryVo.setTaskExcuteDate(new Date());

        if (employee == null) return;
        if (employee.getUserId() == null) return;

        queryVo.setCreateUserId(employee.getUserId());
        queryVo.setEmployeeId(employee.getEmployeeId());

        Task4WaybillReportCountVo countVo = task4WaybillReportService.findReportOverview(queryVo);

        this.doPushMsg(countVo, employee);

    }

    private void doPushMsg(Task4WaybillReportCountVo countVo, Employee employee) {
        Map<String, Object> content = new HashMap<>();
        content.put("total", countVo.getTotal());
        content.put("successCount", countVo.getSuccessCount());
        content.put("failCount", countVo.getFailCount());

        LoginUser loginUser = new LoginUser();
        loginUser.setTenantId(employee.getTenantId());
        loginUser.setTenantCode(employee.getTenantCode());
        loginUser.setUserId(employee.getUserId());

        log.info("发送任务报告:{},客户经理:{}", content, employee);

        messagePushService.pushAppMessage(TMS_TASK_WAYBILL_REPORT_APP, content, loginUser, employee.getUserId().toString());

        messagePushService.pushSmsMessage(TMS_TASK_WAYBILL_REPORT_SMS, content, loginUser, this.getUser(employee.getUserId()).getMobileNumber());
    }

    /**
     * 通过employeeId获取user
     *
     * @param userId
     * @return
     */
    private User getUser(int userId) {
        User user = userService.findUser(userId);
        return user;
    }

}
