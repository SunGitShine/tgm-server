package com.juma.tgm.task.service.impl;

import com.alibaba.fastjson.JSON;
import com.giants.common.exception.BusinessException;
import com.giants.common.exception.GiantsException;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.auth.user.domain.User;
import com.juma.auth.user.service.UserService;
import com.juma.conf.domain.ConfParamOption;
import com.juma.conf.domain.Region;
import com.juma.conf.service.RegionService;
import com.juma.tgm.common.Constants;
import com.juma.tgm.common.DateUtil;
import com.juma.tgm.common.query.QueryCond;
import com.juma.tgm.common.task.TaskConstants;
import com.juma.tgm.common.vo.Page;
import com.juma.tgm.configure.domain.FreightFactor;
import com.juma.tgm.configure.service.FreightFactorService;
import com.juma.tgm.crm.domain.CustomerInfo;
import com.juma.tgm.crm.service.CustomerInfoService;
import com.juma.tgm.filiale.service.FilialeBillService;
import com.juma.tgm.project.domain.RoadMap;
import com.juma.tgm.project.domain.RoadMapPriceRule;
import com.juma.tgm.project.domain.v2.Project;
import com.juma.tgm.project.domain.v2.ProjectDepot;
import com.juma.tgm.project.domain.v2.enums.ProjectEnum;
import com.juma.tgm.project.service.*;
import com.juma.tgm.project.vo.ProjectBillVo;
import com.juma.tgm.task.dao.ext.TaskScheduledExtMapper;
import com.juma.tgm.task.domain.TaskAck;
import com.juma.tgm.task.domain.TaskAckExample;
import com.juma.tgm.task.domain.TaskCalendar;
import com.juma.tgm.task.domain.TaskCalendarExample;
import com.juma.tgm.task.domain.TaskCalendarReason;
import com.juma.tgm.task.domain.TaskDevice;
import com.juma.tgm.task.domain.TaskFixedDelivery;
import com.juma.tgm.task.domain.TaskNotfixedDelivery;
import com.juma.tgm.task.domain.TaskParam;
import com.juma.tgm.task.domain.TaskPeriod;
import com.juma.tgm.task.domain.TaskScheduled;
import com.juma.tgm.task.domain.TaskScheduledExample;
import com.juma.tgm.task.domain.TaskTimeline;
import com.juma.tgm.task.domain.ext.GroupTaskByProject;
import com.juma.tgm.task.domain.ext.GroupTaskWorkStatus;
import com.juma.tgm.task.dto.gateway.GroupTaskFilter;
import com.juma.tgm.task.dto.gateway.TaskFilter;
import com.juma.tgm.task.enums.PricingMethod;
import com.juma.tgm.task.enums.TaskCalendarEnum;
import com.juma.tgm.task.enums.TaskCalendarReasonEnum;
import com.juma.tgm.task.enums.TaskEnum;
import com.juma.tgm.task.service.TaskAckService;
import com.juma.tgm.task.service.TaskCalendarReasonService;
import com.juma.tgm.task.service.TaskCalendarService;
import com.juma.tgm.task.service.TaskDeviceService;
import com.juma.tgm.task.service.TaskFacadeService;
import com.juma.tgm.task.service.TaskFixedDeliveryService;
import com.juma.tgm.task.service.TaskNotfixedDeliveryService;
import com.juma.tgm.task.service.TaskParamService;
import com.juma.tgm.task.service.TaskPeriodService;
import com.juma.tgm.task.service.TaskScheduledService;
import com.juma.tgm.task.service.TaskTimelineService;
import com.juma.tgm.task.vo.gateway.*;
import com.juma.tgm.tools.service.AuthCommonService;
import com.juma.tgm.tools.service.MessagePushService;
import com.juma.tgm.truck.domain.AdditionalFunction;
import com.juma.tgm.truck.domain.TruckType;
import com.juma.tgm.truck.service.AdditionalFunctionService;
import com.juma.tgm.truck.service.TruckTypeService;
import com.juma.tgm.waybill.domain.TruckRequire;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillDeliveryAddress;
import com.juma.tgm.waybill.domain.WaybillParam;
import com.juma.tgm.waybill.domain.WaybillReceiveAddress;
import com.juma.tgm.waybill.service.WaybillCommonService;
import com.juma.tgm.waybill.service.WaybillParamService;
import com.juma.tgm.waybill.service.WaybillService;
import com.juma.vms.driver.domain.Driver;
import com.juma.vms.external.service.VmsService;
import com.juma.vms.tool.service.MessageService;
import com.juma.vms.transport.domain.CapacityPool;
import com.juma.vms.truck.domain.Truck;
import com.juma.vms.vendor.domain.Vendor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;

import com.juma.vms.vendor.enumeration.VendorSourceEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TaskFacadeServiceImpl implements TaskFacadeService {

    @Resource
    private FilialeBillService filialeBillService;

    @Resource
    private TaskScheduledExtMapper taskScheduledExtMapper;

    @Resource
    private TaskScheduledService taskScheduledService;

    @Resource
    private TaskPeriodService taskPeriodService;

    @Resource
    private TaskAckService taskAckService;

    @Resource
    private TaskParamService taskParamService;

    @Resource
    private VmsService vmsService;

    @Resource
    private ProjectMemberService projectMemberService;

    @Resource
    private TruckTypeService truckTypeService;

    @Resource
    private TaskFixedDeliveryService taskFixedDeliveryService;

    @Resource
    private TaskNotfixedDeliveryService taskNotfixedDeliveryService;

    @Resource
    private ProjectService projectService;

    @Resource
    private CustomerInfoService customerInfoService;

    @Resource
    private RoadMapService roadMapService;

    @Resource
    private UserService userService;

    @Resource
    private AdditionalFunctionService additionalFunctionService;

    @Resource
    private TaskCalendarService taskCalendarService;

    @Resource
    private TaskCalendarReasonService taskCalendarReasonService;

    @Resource
    private TaskTimelineService taskTimelineService;

    @Resource
    private WaybillService waybillService;

    @Resource
    private WaybillCommonService waybillCommonService;

    @Resource
    private ProjectDepotService projectDepotService;

    @Resource
    private FreightFactorService freightFactorService;

    @Resource
    private TaskDeviceService taskDeviceService;

    @Resource
    private AuthCommonService authCommonService;

    @Resource
    private MessagePushService messagePushService;

    @Resource
    private MessageService messageService;

    @Resource
    private RegionService regionService;

    @Resource
    private WaybillParamService waybillParamService;

    @Resource
    private RoadMapPriceRuleService roadMapPriceRuleService;

    private void checkLoginUser(LoginUser loginUser) {
        if (loginUser == null
                || loginUser.getUserId() == null
                || loginUser.getTenantId() == null) {
            throw new BusinessException("notLoginUser","LoginUser不能为空");
        }
    }


    @Override
    public Page<Task> pageOfTask(QueryCond<TaskFilter> query, LoginUser loginUser) throws BusinessException {
        checkLoginUser(loginUser);
        if (query == null
                || query.getPageNo() == null
                || query.getPageSize() == null
                || query.getFilters() == null
                || query.getPageSize() > 300) {
            throw new BusinessException("paramError","参数错误或pageSize不能大于300");
        }
        List<Integer> projectIds = projectMemberService.findProjectIdsByUserId(loginUser.getUserId(), loginUser.getTenantId());
        if (projectIds.isEmpty()) return new Page<>();

        TaskScheduledExample example = new TaskScheduledExample()
                .limit(query.getPageNo(),query.getPageSize())
                .orderBy(TaskScheduled.Column.taskId.desc()).createCriteria()
                .andTenantIdEqualTo(loginUser.getTenantId())
                .andCustomerIdEqualTo(query.getFilters().getCustomerId())
                .andProjectIdEqualTo(query.getFilters().getProjectId())
                .andProjectIdIn(projectIds)
                .andTaskStatusIn(query.getFilters().getTaskStatusList())
                .andIsDeleteEqualTo(false).example();
        List<TaskScheduled> taskScheduledList = taskScheduledService.selectByExample(example);

        List<Task> tasks = new ArrayList<Task>();
        for ( TaskScheduled taskScheduled : taskScheduledList ) {
            Task task = new Task();
            task.setTaskId(taskScheduled.getTaskId());
            task.setProjectId(taskScheduled.getProjectId());
            task.setStartDate(taskScheduled.getStartDate());
            task.setEndDate(taskScheduled.getEndDate());
            task.setTaskStatus(taskScheduled.getTaskStatus());

            //项目
            Project projectV2 = projectService.getProjectV2(taskScheduled.getProjectId());
            if (projectV2 != null) {
                task.setProjectId(projectV2.getProjectId());
                task.setProjectName(projectV2.getName());
                task.setProjectBillPeriod(projectV2.getBillPeriod());
                task.setProjectStatus(projectV2.getProjectStatus());
            }

            //线路
            RoadMap roadMap = roadMapService.getRoadMap(taskScheduled.getRoadMapId());
            if (roadMap != null) {
                task.setRoadMapName(roadMap.getName());
            }

            //任务周期
            TaskPeriod taskPeriod = taskPeriodService.withTaskId(taskScheduled.getTaskId());
            if (taskPeriod != null) {
                task.setDeliveryPeriod(taskPeriod.getDeliveryPeriod());
                task.setDeliveryPeriodTime(taskPeriod.getDeliveryPeriodTime());
                task.setIsStandardTime(taskPeriod.getIsStandardTime());
            }
            // 承运商相关信息
            Vendor vendor = vmsService.loadByVenorId(taskScheduled.getVendorId());
            if (vendor != null) {
                task.setVendorId(vendor.getVendorId());
                task.setVendorName(vendor.getVendorName());
                task.setVendorContactPhone(vendor.getContactPhone());
            }
            //司机信息
            Driver driver = vmsService.loadByDriverId(taskScheduled.getDriverId());
            if (driver != null) {
                task.setDriverName(driver.getName());
            }
            //车辆信息
            Truck truck = vmsService.loadByTruckId(taskScheduled.getTruckId());
            if (truck != null) {
                task.setPlateNumber(truck.getPlateNumber());
                String truckTypeName = truckTypeService.findTruckTypeNameBy(truck.getVehicleBoxType(),truck.getVehicleBoxLength());
                task.setTruckTypeName(truckTypeName);
            }
            //任务邀请信息
            TaskAck taskAck = taskAckService.withLastAck(taskScheduled.getTaskId());
            if (taskAck != null) {
                task.setAckStatus(taskAck.getAckStatus());
                task.setRefuseReason(taskAck.getRefuseReason());
            }
            //账期
            TaskParam taskParam = taskParamService.withTaskId(taskScheduled.getTaskId());
            if (taskParam != null) {
                task.setBillPeriod(taskParam.getBillPeriod());
            }
            //任务车型
            List<TaskDevice> taskDevices = taskDeviceService.withTaskId(taskScheduled.getTaskId());
            for ( TaskDevice taskDevice : taskDevices ) {
                task.getTruckTypeNames().add(truckTypeService.findTruckTypeNameByTypeId(taskDevice.getTruckTypeId()));
            }
            tasks.add(task);
        }

        int total = (int)taskScheduledService.countByExample(example);
        return new Page<Task>(query.getPageNo(),query.getPageSize(),total,tasks);
    }

    @Override
    public List<GroupTaskCount> groupTaskCount(LoginUser loginUser) {
        checkLoginUser(loginUser);
        List<Integer> projectIds = projectMemberService.findProjectIdsByUserId(loginUser.getUserId(), loginUser.getTenantId());
        if (projectIds.isEmpty()) return Lists.newArrayList();
        return taskScheduledExtMapper.groupTaskCount(projectIds);
    }

    private TaskCalendar getTaskCalendar(Integer taskId) {
        List<Integer> workStatusList = new ArrayList<>();
        workStatusList.add(TaskEnum.WorkStatus.Conflict.getCode());
        workStatusList.add(TaskEnum.WorkStatus.Running.getCode());
        workStatusList.add(TaskEnum.WorkStatus.Finish.getCode());
        Date nowDate = new DateTime().withHourOfDay(0)
                .withMinuteOfHour(0)
                .withSecondOfMinute(0)
                .withMillisOfSecond(0).toDate();
        return taskCalendarService.withTaskId(taskId, workStatusList, nowDate);
    }

    @Override
    public Page<GroupTask> pageOfGroupTask(QueryCond<GroupTaskFilter> query, LoginUser loginUser) throws BusinessException {
        checkPageOf(query, loginUser);
        List<GroupTask> groupTasks = new ArrayList<>();
        List<GroupTaskByProject> groupTaskByProjects = taskScheduledExtMapper.groupTaskByProject(query);
        int total = taskScheduledExtMapper.groupTaskByProjectTotal(query);
        for ( GroupTaskByProject groupTaskByProject : groupTaskByProjects ) {
            GroupTask groupTask = new GroupTask();
            groupTask.setProjectId(groupTaskByProject.getProjectId());
            groupTask.setCount(groupTaskByProject.getCount());
            Project project = projectService.getProjectV2(groupTaskByProject.getProjectId());
            if (project != null) {
                groupTask.setProjectNo(project.getProjectNo());
                groupTask.setProjectName(project.getName());
                User user = userService.loadUser(project.getProjectManagerUserId());
                if (user != null) {
                    groupTask.setProjectManagerName(user.getName());
                    groupTask.setProjectManagerTel(user.getMobileNumber());
                }
            }
            groupTasks.add(groupTask);
        }
        return new Page<>(query.getPageNo(),query.getPageSize(),total,groupTasks);
    }

    private void checkPageOf(QueryCond<GroupTaskFilter> query, LoginUser loginUser) {
        checkLoginUser(loginUser);
        if (query == null
                || query.getPageNo() == null
                || query.getPageSize() == null
                || query.getFilters() == null
                || query.getPageSize() > 300) {
            throw new BusinessException("paramError","参数错误或pageSize不能大于300");
        }
    }

    @Override
    public Page<GroupTaskDetail> pageOfGroupTaskDetail(QueryCond<GroupTaskFilter> query, LoginUser loginUser) throws BusinessException {
        checkPageOf(query, loginUser);
        List<GroupTaskDetail> groupTaskDetails = new ArrayList<>();
        TaskScheduledExample example = new TaskScheduledExample().createCriteria()
                .andTenantIdEqualTo(query.getFilters().getTenantId())
                .andVendorIdEqualTo(query.getFilters().getVendorId())
                .andProjectIdEqualTo(query.getFilters().getProjectId())
                .andTaskStatusEqualTo(query.getFilters().getTaskStatus()).example();

        int total = (int)taskScheduledService.countByExample(example);
        //分组明细
        List<TaskScheduled> taskScheduledList = taskScheduledService.selectByExample(example);
        for (TaskScheduled taskScheduled : taskScheduledList) {
            GroupTaskDetail groupTaskDetail = buildGroupTaskDetail(taskScheduled);
            //注意取司机、车逻辑
            TaskCalendar taskCalendar = getTaskCalendar(taskScheduled.getTaskId());
            if (taskCalendar != null) {
                groupTaskDetail.setDriverId(taskCalendar.getDriverId());
                groupTaskDetail.setTruckId(taskCalendar.getTruckId());
            }
            groupTaskDetails.add(groupTaskDetail);
        }
        return new Page<>(query.getPageNo(),query.getPageSize(),total,groupTaskDetails);
    }

    private GroupTaskDetail buildGroupTaskDetail(TaskScheduled taskScheduled) {
        GroupTaskDetail groupTaskDetail = new GroupTaskDetail();
        groupTaskDetail.setTaskId(taskScheduled.getTaskId());
        groupTaskDetail.setTaskNo(taskScheduled.getTaskNo());
        groupTaskDetail.setTaskStatus(taskScheduled.getTaskStatus());
        groupTaskDetail.setProjectId(taskScheduled.getProjectId());
        groupTaskDetail.setRoadMapId(taskScheduled.getRoadMapId());
        groupTaskDetail.setStartDate(taskScheduled.getStartDate());
        groupTaskDetail.setEndDate(taskScheduled.getEndDate());
        groupTaskDetail.setIsFixedDelivery(taskScheduled.getIsFixedDelivery());

        //项目名称
        Project projectV2 = projectService.getProjectV2(taskScheduled.getProjectId());
        if (projectV2 != null) {
            groupTaskDetail.setProjectName(projectV2.getName());
        }
        //仓库
        ProjectDepot projectDepot = projectDepotService.withDepotId(taskScheduled.getDepotId());
        if (projectDepot != null) {
            Depot depot = new Depot();
            depot.setDepotName(projectDepot.getDepotName());
            depot.setDepotAddress(projectDepot.getDepotAddress());
            depot.setDepotCoordinates(projectDepot.getDepotCoordinates());
            depot.setLinkMan(projectDepot.getLinkMan());
            depot.setLinkManTel(projectDepot.getLinkManPhone());
            groupTaskDetail.setDepot(depot);
        }
        //固定地址  非固定地址
        if (taskScheduled.getIsFixedDelivery()) {
            List<TaskFixedDelivery> taskFixedDeliveryList = taskFixedDeliveryService.withTaskId(taskScheduled.getTaskId());
            for ( TaskFixedDelivery taskFixedDelivery : taskFixedDeliveryList ) {
                FixedDeliveryInfo fixedDeliveryInfo = new FixedDeliveryInfo();
                fixedDeliveryInfo.setAddressName(taskFixedDelivery.getAddressName());
                fixedDeliveryInfo.setAddressDetail(taskFixedDelivery.getAddressDetail());
                fixedDeliveryInfo.setCoordinates(taskFixedDelivery.getCoordinates());
                fixedDeliveryInfo.setLinkMan(taskFixedDelivery.getLinkMan());
                fixedDeliveryInfo.setLinkManTel(taskFixedDelivery.getLinkManTel());
                groupTaskDetail.getFixedDeliveries().add(fixedDeliveryInfo);
            }
        } else {
            TaskNotfixedDelivery taskNotfixedDelivery = taskNotfixedDeliveryService.withTaskId(taskScheduled.getTaskId());
            if (taskNotfixedDelivery != null) {
                NotFixedDeliveryInfo notFixedDeliveryInfo = new NotFixedDeliveryInfo();
                notFixedDeliveryInfo.setMinStops(taskNotfixedDelivery.getMinStops());
                notFixedDeliveryInfo.setMaxStops(taskNotfixedDelivery.getMaxStops());
                notFixedDeliveryInfo.setAddressDetail(taskNotfixedDelivery.getAddressDetail());
                groupTaskDetail.setNotFixedDeliveryInfo(notFixedDeliveryInfo);
            }
        }

        RoadMap roadMap = roadMapService.getRoadMap(taskScheduled.getRoadMapId());
        if (roadMap != null) {
            groupTaskDetail.setRoadMapName(roadMap.getName());
        }
        TaskParam taskParam = taskParamService.withTaskId(taskScheduled.getTaskId());
        if (taskParam != null) {
            groupTaskDetail.setBillPeriod(taskParam.getBillPeriod());
            String functionIds = taskParam.getFunctionIds();
            List<AdditionalFunction> additionalFunctionList = additionalFunctionService.getAdditionFunctionByIds(functionIds);
            List<String> functions = new ArrayList<>();
            for ( AdditionalFunction function : additionalFunctionList ) {
                functions.add(function.getFunctionName());
            }
            groupTaskDetail.setFunctions(Joiner.on(",").join(functions));
            groupTaskDetail.setRequireMark(taskParam.getRequireMark());
        }
        TaskPeriod taskPeriod = taskPeriodService.withTaskId(taskScheduled.getTaskId());
        if (taskPeriod != null) {
            groupTaskDetail.setIsStandardTime(taskPeriod.getIsStandardTime());
            groupTaskDetail.setDeliveryPeriod(taskPeriod.getDeliveryPeriod());
            groupTaskDetail.setDeliveryPeriodTime(taskPeriod.getDeliveryPeriodTime());
        }

        return groupTaskDetail;
    }

    @Override
    public Page<GroupTaskDetail> pageOfTaskAck(QueryCond<GroupTaskFilter> query, LoginUser loginUser) throws BusinessException {
        checkPageOf(query,loginUser);
        List<GroupTaskDetail> groupTaskDetails = new ArrayList<>();
        TaskAckExample example = new TaskAckExample().createCriteria()
                .andVendorIdEqualTo(query.getFilters().getVendorId())
                .andAckStatusEqualTo(TaskEnum.TaskAckStatus.Waiting_Reply.getCode()).example();
        List<TaskAck> taskAckList = taskAckService.selectByExample(example);
        int total = (int) taskAckService.countByExample(example);
        for ( TaskAck taskAck : taskAckList ) {
            TaskScheduled taskScheduled = taskScheduledService.withTaskId(taskAck.getTaskId());
            if (taskScheduled == null) continue;
            GroupTaskDetail groupTaskDetail = buildGroupTaskDetail(taskScheduled);
            groupTaskDetail.setInviteTime(taskAck.getCreateTime());//承运商邀请时间
            groupTaskDetail.setAckStatus(taskAck.getAckStatus());//承运商邀请状态
            groupTaskDetail.setDriverId(taskAck.getDriverId());
            groupTaskDetail.setTruckId(taskAck.getTruckId());
            groupTaskDetails.add(groupTaskDetail);
        }
        return new Page<>(query.getPageNo(),query.getPageSize(),total,groupTaskDetails);
    }

    @Override
    public void updateBillPeriod(Integer taskId,Integer billPeriod, String billPeriodReason, LoginUser loginUser) throws BusinessException {
        if (taskId == null || billPeriod == null) {
            throw new BusinessException("paramError","参数taskId或billPeriod为空");
        }
        TaskScheduled taskScheduled = checkPermission(taskId,loginUser);

        TaskParam taskParam = taskParamService.withTaskId(taskId);
        if (taskParam == null) {
            throw new BusinessException("notFound","TaskParam不存在：" + taskId);
        }

        Project projectV2 = projectService.getProjectV2(taskScheduled.getProjectId());
        if (projectV2 == null) {
            throw new BusinessException("notFound","Project不存在：" + taskScheduled.getProjectId());
        }

        if (projectV2.getBillPeriod() != null && !billPeriod.equals(projectV2.getBillPeriod())) {
            if (StringUtils.isBlank(billPeriodReason)) {
                throw new BusinessException("paramError","账期原因不能为空");
            }
            if (billPeriodReason.length() > 60) {
                throw new BusinessException("paramError","账期原因长度不能超过60");
            }
        }

        TaskAck taskAck = taskAckService.withLastAck(taskId);
        if (taskAck != null
                && taskAck.getAckStatus() != null
                && taskAck.getAckStatus().equals(TaskEnum.TaskAckStatus.Waiting_Reply.getCode())) {
            taskAck.setBillPeriod(billPeriod);
            taskAck.setBillPeriodReason(billPeriodReason);
            taskAck.setLastUpdateUserId(loginUser.getUserId());
            taskAck.setLastUpdateTime(new Date());
            taskAckService.updateByPrimaryKeySelective(taskAck);
        }
        TaskParam taskParam2 = new TaskParam();
        taskParam2.setParamId(taskParam.getParamId());
        taskParam2.setBillPeriod(billPeriod);
        taskParamService.updateByPrimaryKeySelective(taskParam2);
    }

    @Override
    public void cancelTask(Integer taskId, String cancelReason,LoginUser loginUser) throws BusinessException {
        if (taskId == null || StringUtils.isBlank(cancelReason)) {
            throw new BusinessException("paramError","参数taskId或cancelReason为空");
        }
        TaskScheduled taskScheduled = checkPermission(taskId, loginUser);
        if (taskScheduled.getTaskStatus() != null && taskScheduled.getTaskStatus() != TaskEnum.TaskStatus.Inviting.getCode()
                && taskScheduled.getTaskStatus() != TaskEnum.TaskStatus.Waiting_Invite.getCode()) {
            throw new BusinessException("taskStatusError","任务状态为待指派运力或邀请中才能操作");
        }
        TaskScheduled taskScheduled2 = new TaskScheduled();
        taskScheduled2.setTaskId(taskId);
        taskScheduled2.setTaskStatus(TaskEnum.TaskStatus.Cancel.getCode());
        taskScheduled2.setLastUpdateUserId(loginUser.getUserId());
        taskScheduled2.setLastUpdateTime(new Date());
        taskScheduledService.updateByPrimaryKeySelective(taskScheduled2);

        TaskParam taskParam = taskParamService.withTaskId(taskId);
        if (taskParam == null) {
            throw new BusinessException("notFound","TaskParam不存在：" + taskId);
        }

        TaskParam taskParam2 = new TaskParam();
        taskParam2.setParamId(taskParam.getParamId());
        taskParam2.setCancelReason(cancelReason);
        taskParamService.updateByPrimaryKeySelective(taskParam2);

        List<TaskAck> taskAckList = taskAckService.withTaskId(taskId);
        for ( TaskAck taskAck: taskAckList) {
            taskAck.setLastUpdateUserId(loginUser.getUserId());
            taskAck.setLastUpdateTime(new Date());
            taskAck.setAckStatus(TaskEnum.TaskAckStatus.Invalided.getCode());
        }
        if (!taskAckList.isEmpty()) {
            taskAckService.updateBatchByPrimaryKeySelective(taskAckList);
        }
    }

    @Override
    public TaskScheduled checkPermission(Integer taskId,LoginUser loginUser) {
        checkLoginUser(loginUser);
        TaskScheduled taskScheduled = taskScheduledService.withTaskId(taskId);
        if (taskScheduled == null) {
            throw new BusinessException("notFound","TaskScheduled不存在：" + taskId);
        }
        Project project = projectService.getProjectV2(taskScheduled.getProjectId());
        if (project == null) {
            throw new BusinessException("notFound","Project不存在：" + taskScheduled.getProjectId());
        }
        if (!loginUser.getUserId().equals(taskScheduled.getCreateUserId()) && !loginUser.getUserId().equals(project.getProjectManagerUserId())) {
            throw new BusinessException("authorError","仅任务发布人或项目经理有权限操作");
        }
        return taskScheduled;
    }

    @Override
    public void inviteVendor(InviteRequest request, LoginUser loginUser) throws BusinessException {
        if (request == null
                || request.getVendorId() == null
                || request.getTaskId() == null
                || request.getDriverId() == null
                || request.getTruckId() == null) {
            throw new BusinessException("paramError","参数taskId或vendorId或truckId或driverId为空");
        }

        isValidCapacity(request.getVendorId(),request.getTruckId(),loginUser);

        Integer taskId = request.getTaskId();
        TaskScheduled taskScheduled = checkPermission(request.getTaskId(), loginUser);
        if (taskScheduled.getTaskStatus() != null
                && taskScheduled.getTaskStatus() != TaskEnum.TaskStatus.Waiting_Invite.getCode()
                && taskScheduled.getTaskStatus() != TaskEnum.TaskStatus.Inviting.getCode()) {
            throw new BusinessException("taskStatusError","任务状态为待指派运力或邀请中才能操作");
        }

        TaskParam taskParam = taskParamService.withTaskId(taskId);
        if (taskParam == null) {
            throw new BusinessException("notFound","TaskParam不存在：" + taskId);
        }
        TaskPeriod taskPeriod = taskPeriodService.withTaskId(taskId);
        if (taskPeriod == null) {
            throw new BusinessException("notFound","TaskPeriod不存在：" + taskId);
        }

        Project projectV2 = projectService.getProjectV2(taskScheduled.getProjectId());
        if (projectV2 == null) {
            throw new BusinessException("notFound","Project不存在：" + taskScheduled.getProjectId());
        }

        if (projectV2.getBillPeriod() != null && !projectV2.getBillPeriod().equals(request.getBillPeriod())) {
            if (StringUtils.isBlank(request.getBillPeriodReason())) {
                throw new BusinessException("paramError","账期原因不能为空");
            }
            if (request.getBillPeriodReason().length() > 60) {
                throw new BusinessException("paramError","账期原因长度不能超过60");
            }
        }


        //检查承运商是不是自营
        Vendor vendor = vmsService.loadByVenorId(request.getVendorId());
        if (vendor == null) {
            throw new BusinessException("notFound","Vendor不存在：" + request.getVendorId());
        }

        boolean b = vendor.getVendorSource() != null && vendor.getVendorSource().equals(VendorSourceEnum.SELF_SUPPORT.getCode());


        //更新任务状态
        taskScheduled.setVendorId(request.getVendorId());
        taskScheduled.setTruckId(request.getTruckId());
        taskScheduled.setDriverId(request.getDriverId());

        //自营要生成配送日
        makeTaskCalendar(b,true,taskId,request.getVendorId(),request.getTruckId(),request.getDriverId(),taskScheduled,taskParam,taskPeriod,loginUser);

        //更新账期
        taskParam.setBillPeriod(request.getBillPeriod());
        taskParamService.updateByPrimaryKeySelective(taskParam);

        //当某一个配送日生成了运单的时候，任务状态要变更为运行中
        if (b) {
            if (!taskScheduled.getTaskStatus().equals(TaskEnum.TaskStatus.Running.getCode())) {
                taskScheduled.setTaskStatus(TaskEnum.TaskStatus.Waiting_Become.getCode());
            }
        } else {
            taskScheduled.setTaskStatus(TaskEnum.TaskStatus.Inviting.getCode());
        }
        taskScheduled.setLastUpdateTime(new Date());
        taskScheduled.setLastUpdateUserId(loginUser.getUserId());
        taskScheduledService.updateByPrimaryKeySelective(taskScheduled);

        //时间冲突

        TaskAck taskAck = new TaskAck();
        taskAck.setTenantId(taskScheduled.getTenantId());
        taskAck.setProjectId(taskScheduled.getProjectId());
        taskAck.setTaskId(request.getTaskId());
        taskAck.setVendorId(request.getVendorId());
        taskAck.setTruckId(request.getTruckId());
        taskAck.setDriverId(request.getDriverId());
        taskAck.setBillPeriod(request.getBillPeriod());
        taskAck.setBillPeriodReason(request.getBillPeriodReason());
        taskAck.setAckStatus(b ? TaskEnum.TaskAckStatus.Received.getCode() : TaskEnum.TaskAckStatus.Waiting_Reply.getCode());
        taskAck.setCreateTime(new Date());
        taskAck.setCreateUserId(loginUser.getUserId());
        taskAck.setLastUpdateTime(new Date());
        taskAck.setLastUpdateUserId(loginUser.getUserId());
        taskAckService.insertSelective(taskAck);

        //发送短信与push
        messagePushService.inviteVendor(request.getTaskId(),request.getVendorId(),loginUser);
    }

    @Override
    public TaskDetail getTaskDetail(Integer taskId) throws BusinessException {
        if (taskId == null) {
            throw new BusinessException("paramError","参数taskId为空");
        }

        TaskScheduled taskScheduled = taskScheduledService.withTaskId(taskId);
        if (taskScheduled == null) {
            throw new BusinessException("notFound","TaskScheduled不存在：" + taskId);
        }

        TaskDetail taskDetail = new TaskDetail();
        taskDetail.setTaskId(taskScheduled.getTaskId());
        taskDetail.setTaskNo(taskScheduled.getTaskNo());
        taskDetail.setTaskStatus(taskScheduled.getTaskStatus());
        taskDetail.setRegionCode(taskScheduled.getRegionCode());
        taskDetail.setRoadMapPriceRuleId(taskScheduled.getRoadMapPriceRuleId());
        Region region = regionService.loadRegion(taskScheduled.getRegionCode());
        if (region != null) {
            taskDetail.setRegionCodeName(region.getRegionName());
        }
        taskDetail.setCreateTime(taskScheduled.getCreateTime());
        taskDetail.setIsFixedDelivery(taskScheduled.getIsFixedDelivery());

        //任务车型ids
        List<TaskDevice> taskDeviceList = taskDeviceService.withTaskId(taskScheduled.getTaskId());
        for ( TaskDevice taskDevice : taskDeviceList ) {
            taskDetail.getTruckTypeIds().add(taskDevice.getTruckTypeId());
        }

        ProjectDepot projectDepot = projectDepotService.withDepotId(taskScheduled.getDepotId());
        if (projectDepot != null) {
            Depot depot = new Depot();
            depot.setDepotId(projectDepot.getDepotId());
            depot.setDepotName(projectDepot.getDepotName());
            depot.setDepotAddress(projectDepot.getDepotAddress());
            depot.setDepotCoordinates(projectDepot.getDepotCoordinates());
            depot.setLinkMan(projectDepot.getLinkMan());
            depot.setLinkManTel(projectDepot.getLinkManPhone());
            taskDetail.setDepot(depot);
        }

        Project project = projectService.getProjectV2(taskScheduled.getProjectId());
        if (project != null) {
            taskDetail.setProjectId(project.getProjectId());
            taskDetail.setProjectName(project.getName());
            taskDetail.setProjectBillPeriod(project.getBillPeriod());
            User user = userService.loadUser(project.getProjectManagerUserId());
            if (user != null) {
                taskDetail.setProjectManagerName(user.getName());
                taskDetail.setProjectManagerPhone(user.getMobileNumber());
            }
            try {
                CustomerInfo customerInfo = customerInfoService.findCusInfoById(project.getCustomerId());
                if (customerInfo != null) {
                    taskDetail.setCustomerName(customerInfo.getCustomerName());
                }
            } catch (Exception e) {
                log.error(e.getMessage(),e);
            }
        }
        RoadMap roadMap = roadMapService.getRoadMap(taskScheduled.getRoadMapId());
        if (roadMap != null) {
            taskDetail.setRoadMapId(roadMap.getRoadMapId());
            taskDetail.setRoadMapName(roadMap.getName());

            RoadMapVo roadMapVo = new RoadMapVo();
            roadMapVo.setRoadMapId(roadMap.getRoadMapId());
            roadMapVo.setName(roadMap.getName());
            taskDetail.setRoadMapVo(roadMapVo);

            List<RoadMapPriceRule> roadMapPriceRuleList = roadMapPriceRuleService.listByRoadMapId(roadMap.getRoadMapId());
            for ( RoadMapPriceRule roadMapPriceRule : roadMapPriceRuleList ) {
                RoadMapPriceRuleVo roadMapPriceRuleVo = new RoadMapPriceRuleVo();
                roadMapPriceRuleVo.setRoadMapId(roadMapPriceRule.getRoadMapId());
                roadMapPriceRuleVo.setRoadMapPriceRuleId(roadMapPriceRule.getRoadMapPriceRuleId());
                roadMapPriceRuleVo.setValuationWay(roadMapPriceRule.getValuationWay() == null ? 0 : roadMapPriceRule.getValuationWay().intValue());
                roadMapPriceRuleVo.setValuationModelJson(roadMapPriceRule.getValuationModelJson());
                roadMapPriceRuleVo.setTruckTypeId(roadMapPriceRule.getTruckTypeId());
                truckTypeService.findTruckTypeNameByTypeId(roadMapPriceRule.getTruckTypeId());
                taskDetail.getRoadMapPriceRuleVos().add(roadMapPriceRuleVo);
            }
        }
        taskDetail.setStartDate(taskScheduled.getStartDate());
        taskDetail.setEndDate(taskScheduled.getEndDate());

        if (taskScheduled.getTaskStatus() == TaskEnum.TaskStatus.Inviting.getCode()) {
            taskDetail.setTruckId(taskScheduled.getTruckId());
            taskDetail.setDriverId(taskScheduled.getDriverId());
        } else {
            //司机端显示
            TaskCalendar taskCalendar = getTaskCalendar(taskId);
            if (taskCalendar != null) {
                taskDetail.setTruckId(taskScheduled.getTruckId());
                taskDetail.setDriverId(taskScheduled.getDriverId());
            }
        }

        Truck truck = vmsService.loadByTruckId(taskScheduled.getTruckId());
        if (truck != null) {
            //车型名称
            String truckTypeName = truckTypeService.findTruckTypeNameBy(truck.getVehicleBoxType(),truck.getVehicleBoxLength());
            taskDetail.setTruckTypeName(truckTypeName);
        }

        if (taskScheduled.getIsFixedDelivery()) {
            List<TaskFixedDelivery> taskFixedDeliveryList = taskFixedDeliveryService.withTaskId(taskId);
            for ( TaskFixedDelivery taskFixedDelivery : taskFixedDeliveryList ) {
                FixedDeliveryInfo fixedDeliveryInfo = new FixedDeliveryInfo();
                fixedDeliveryInfo.setAddressName(taskFixedDelivery.getAddressName());
                fixedDeliveryInfo.setAddressDetail(taskFixedDelivery.getAddressDetail());
                fixedDeliveryInfo.setCoordinates(taskFixedDelivery.getCoordinates());
                fixedDeliveryInfo.setLinkMan(taskFixedDelivery.getLinkMan());
                fixedDeliveryInfo.setLinkManTel(taskFixedDelivery.getLinkManTel());
                taskDetail.getFixedDeliveries().add(fixedDeliveryInfo);
            }
        } else {
            TaskNotfixedDelivery taskNotfixedDelivery = taskNotfixedDeliveryService.withTaskId(taskId);
            if (taskNotfixedDelivery != null) {
                NotFixedDeliveryInfo notFixedDeliveryInfo = new NotFixedDeliveryInfo();
                notFixedDeliveryInfo.setMinStops(taskNotfixedDelivery.getMinStops());
                notFixedDeliveryInfo.setMaxStops(taskNotfixedDelivery.getMaxStops());
                notFixedDeliveryInfo.setAddressDetail(taskNotfixedDelivery.getAddressDetail());
                taskDetail.setNotFixedDeliveryInfo(notFixedDeliveryInfo);
            }
        }

        TaskParam taskParam = taskParamService.withTaskId(taskId);
        if (taskParam == null) {
            throw new BusinessException("notFound","TaskParam不存在：" + taskId);
        }
        taskDetail.setBillPeriod(taskParam.getBillPeriod());
        taskDetail.setGoodsType(taskParam.getGoodsType());
        taskDetail.setEstimateMileageRange(taskParam.getEstimateMileageRange());
        taskDetail.setEstimateTimeCostRange(taskParam.getEstimateTimeCostRange());
        taskDetail.setPricingMethod(taskParam.getPricingMethod());
        taskDetail.setPricingRule(pricingRuleView(taskParam.getPricingRule()));
        taskDetail.setPricingRuleJson(taskParam.getPricingRule());

        //用车要求 用车备注
        String functionIds = taskParam.getFunctionIds();
        taskDetail.setFunctionIds(functionIds);
        List<AdditionalFunction> additionalFunctionList = additionalFunctionService.getAdditionFunctionByIds(functionIds);
        List<String> functions = new ArrayList<>();
        for ( AdditionalFunction function : additionalFunctionList ) {
            functions.add(function.getFunctionName());
        }
        taskDetail.setFunctions(Joiner.on(",").join(functions));
        taskDetail.setRequireMark(taskParam.getRequireMark());

        TaskPeriod taskPeriod = taskPeriodService.withTaskId(taskId);
        if (taskPeriod == null) {
            throw new BusinessException("notFound","TaskPeriod不存在：" + taskId);
        }
        taskDetail.setIsStandardTime(taskPeriod.getIsStandardTime());
        taskDetail.setDeliveryPeriod(taskPeriod.getDeliveryPeriod());
        taskDetail.setDeliveryPeriodTime(taskPeriod.getDeliveryPeriodTime());

        //邀请状态
        TaskAck taskAck = taskAckService.withLastAck(taskId);
        if (taskAck != null) {
            taskDetail.setAckStatus(taskAck.getAckStatus());
            taskDetail.setInviteTime(taskAck.getCreateTime());
        }

        //发布人
        User user = userService.loadUser(taskScheduled.getCreateUserId());
        if (user != null) {
            taskDetail.setCreateUserName(user.getName());
            taskDetail.setCreateUserContactPhone(user.getMobileNumber());
        }
        return taskDetail;
    }

    private String pricingRuleView(String pricingRule) {
        if (StringUtils.isBlank(pricingRule)) return null;
        Map<String, FreightFactor> allFactorMap = freightFactorService.mapInputNameToValue();
        Map<String,Object> pricingRuleJsonMap = JSON.parseObject(pricingRule, Map.class);
        List<String> pricingRules = new ArrayList<>();
        if (pricingRuleJsonMap != null && !pricingRuleJsonMap.isEmpty() && pricingRuleJsonMap.containsKey("factorJson")) {
            Map<String,Object> factorJsonMap = JSON.parseObject(pricingRuleJsonMap.get("factorJson").toString(),Map.class);
            if (factorJsonMap != null && !factorJsonMap.isEmpty()) {
                Iterator<Map.Entry<String, Object>> it = factorJsonMap.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<String, Object> next = it.next();
                    String key = next.getKey();
                    String value = next.getValue() == null ? "" : next.getValue().toString();
                    //一口价
                    if ("estimateFreight".equals(key)) {
                        pricingRules.add(value + "元");
                    } else {
                        String label = allFactorMap.get(key).getLabelName();
                        if ("initiateRate".equals(key)) {
                            pricingRules.add(0,label + value + "元");
                        } else {
                            pricingRules.add(value + "元/" + label);
                        }
                    }
                }
            }
        }
        return Joiner.on(" ").join(pricingRules);
    }

    @Override
    public TaskCalendarMaster getTaskCalendarMaster(Integer taskId, Date startDate,boolean isIncludeHeader) throws BusinessException {
        TaskScheduled taskScheduled = taskScheduledService.withTaskId(taskId);
        if (taskScheduled == null) {
            throw new BusinessException("notFound","TaskScheduled不存在：" + taskId);
        }
        //主信息
        TaskCalendarMaster taskCalendarMaster = new TaskCalendarMaster();
        taskCalendarMaster.setTaskId(taskScheduled.getTaskId());
        if (isIncludeHeader) {
            Project project = projectService.getProjectV2(taskScheduled.getProjectId());
            if (project != null) {
                taskCalendarMaster.setProjectName(project.getName());
            }
            RoadMap roadMap = roadMapService.getRoadMap(taskScheduled.getRoadMapId());
            if (roadMap != null) {
                taskCalendarMaster.setRoadMapName(roadMap.getName());
            }
            //任务的统计信息
            List<GroupTaskWorkStatus> groupTaskWorkStatusList = taskCalendarService.groupByTaskWorkStatus(taskId);
            taskCalendarMaster.setGroupTaskWorkStatusList(groupTaskWorkStatusList);
        }

        DateTime dateTime = new DateTime(startDate);

        //月份第一天
        Date date1 = dateTime.dayOfMonth().withMinimumValue()
                .withHourOfDay(0)
                .withMinuteOfHour(0)
                .withSecondOfMinute(0)
                .withMillisOfSecond(0).toDate();
        //月份最后一天
        Date date2 = dateTime.dayOfMonth().withMaximumValue()
                .withHourOfDay(23)
                .withMinuteOfHour(59)
                .withSecondOfMinute(59)
                .withMillisOfSecond(0).toDate();

        List<TaskCalendar> taskCalendarList = taskCalendarService.withTaskId(taskCalendarMaster.getTaskId(), date1, date2);
        for ( TaskCalendar taskCalendar: taskCalendarList) {
            TaskCalendarDetail taskCalendarDetail = new TaskCalendarDetail();
            taskCalendarDetail.setCalendarId(taskCalendar.getCalendarId());
            taskCalendarDetail.setVendorId(taskScheduled.getVendorId());
            Driver driver = vmsService.loadByDriverId(taskCalendar.getDriverId());
            if (driver != null) {
                taskCalendarDetail.setDriverId(driver.getDriverId());
                taskCalendarDetail.setDriverContactPhone(driver.getPhone());
                taskCalendarDetail.setDriverName(driver.getName());
            }
            TaskParam taskParam = taskParamService.withTaskId(taskId);
            if (taskParam != null) {
                taskCalendarDetail.setEstimateTimeCostRange(taskParam.getEstimateTimeCostRange());
            }
            //车辆信息
            Truck truck = vmsService.loadByTruckId(taskCalendar.getTruckId());
            if (truck != null) {
                taskCalendarDetail.setTruckId(truck.getTruckId());
                taskCalendarDetail.setPlateNumber(truck.getPlateNumber());
                //车型名称
                String truckTypeName = truckTypeService.findTruckTypeNameBy(truck.getVehicleBoxType(),truck.getVehicleBoxLength());
                taskCalendarDetail.setTruckTypeName(truckTypeName);
            }
            //不配送
            if (taskCalendar.getWorkStatus() != null && taskCalendar.getWorkStatus() == TaskEnum.WorkStatus.Not_delivery.getCode()) {
                if (!StringUtils.isBlank(taskCalendar.getFailureReason())) {
                    //运单失败的时候，也会设置不配送，也要展示原因
                    taskCalendarDetail.setNotDeliveryReason(taskCalendar.getFailureReason());
                } else {
                    TaskCalendarReason taskCalendarReason = taskCalendarReasonService.withLastCalendarReason(taskCalendar.getCalendarId());
                    if (taskCalendarReason != null) {
                        taskCalendarDetail.setReasonSort(taskCalendarReason.getReasonSort());
                        taskCalendarDetail.setNotDeliveryReason(taskCalendarReason.getReason());
                    }
                }
            }

            taskCalendarDetail.setWorkTime(taskCalendar.getWorkStartTime());
            taskCalendarDetail.setWorkStatus(taskCalendar.getWorkStatus());
            taskCalendarMaster.getTaskCalendars().add(taskCalendarDetail);
        }
        return taskCalendarMaster;
    }

    @Override
    public void updateToNotDelivery(Integer calendarId,Integer reasonSort,String reason,LoginUser loginUser) throws BusinessException {
        if (calendarId == null || reasonSort == null) {
            throw new BusinessException("paramError","参数错误");
        }
        TaskCalendar taskCalendar = checkNotDelivery(calendarId);
        checkPermission(taskCalendar.getTaskId(), loginUser);
        if (taskCalendar.getWorkStatus() != null
                && taskCalendar.getWorkStatus() != TaskEnum.WorkStatus.Running.getCode()
                && taskCalendar.getWorkStatus() != TaskEnum.WorkStatus.Conflict.getCode()) {
            throw new BusinessException("taskStatusError","配送日状态不运行中或运力冲突才能操作");
        }

        DateTime nowDate = new DateTime().withHourOfDay(0)
                .withMinuteOfHour(0)
                .withSecondOfMinute(0)
                .withMillisOfSecond(0);
        DateTime workDate = new DateTime(taskCalendar.getWorkStartTime()).withHourOfDay(0)
                .withMinuteOfHour(0)
                .withSecondOfMinute(0)
                .withMillisOfSecond(0);
        int days = Days.daysBetween(nowDate,workDate).getDays();
        if (days < 0 || days > 7) {
            throw new BusinessException("dateError","请在任务日期是当前日期的7天内进行操作");
        }

        if (taskCalendar.getWaybillId() != 0 ) {
            Waybill waybill = waybillService.getWaybill(taskCalendar.getWaybillId());
            if (waybill != null && waybill.getStatusView() != null) {
                if (waybill.getStatusView().equals(Waybill.StatusView.DELIVERYING.getCode())) {
                    throw new BusinessException("waybillStatusError","当前日期运单已在配送中，不可设置不配送");
                }
                if (waybill.getStatusView().equals(Waybill.StatusView.FINISH.getCode())) {
                    throw new BusinessException("waybillStatusError","当前日期运单已完成，不可设置不配送");
                }
            }
            //取消运单
            LoginEmployee loginEmployee = new LoginEmployee();
            loginEmployee.setUserId(loginUser.getUserId());
            loginEmployee.setTenantId(loginUser.getTenantId());
            waybill.setWaybillCancelRemark(reason);
            waybill.setCancelChannel(Waybill.CancelChannel.TASK_CALENDAR.getCode());
            waybillCommonService.doCancelWaybill(waybill, loginEmployee, Waybill.Status.SYS_CANCEL);
        }
        //删除时间线
        taskTimelineService.deleteByPrimaryKey(taskCalendar.getTimelineId());

        //清除时间线绑定
        taskCalendar.setWaybillId(0);
        taskCalendar.setTimelineId(0);
        taskCalendar.setWorkStatus(TaskCalendarEnum.WorkStatus.NotDelivery.getCode());
        taskCalendarService.updateByPrimaryKeySelective(taskCalendar);

        //任务日历原因表
        TaskCalendarReason taskCalendarReason = new TaskCalendarReason();
        taskCalendarReason.setCalendarId(calendarId);
        taskCalendarReason.setReasonSort(reasonSort);
        taskCalendarReason.setReason(reason);
        taskCalendarReason.setReasonType(TaskCalendarReasonEnum.ReasonType.NotDelivery.getCode());
        taskCalendarReason.setCreateTime(new Date());
        taskCalendarReason.setCreateUserId(loginUser.getUserId());
        taskCalendarReason.setLastUpdateTime(taskCalendarReason.getCreateTime());
        taskCalendarReason.setLastUpdateUserId(loginUser.getUserId());
        taskCalendarReasonService.insertSelective(taskCalendarReason);
    }

    @Override
    public void updateToRecoverDelivery(Integer calendarId,Integer driverId,Integer truckId,String reason, LoginUser loginUser) throws BusinessException {
        if (calendarId == null || driverId == null || truckId == null) {
            throw new BusinessException("paramError","参数错误");
        }

        TaskCalendar taskCalendar = checkRecover(calendarId);
        if (taskCalendar.getWorkStatus() == TaskEnum.WorkStatus.Conflict.getCode()) {
            reason = "";
        } else {
            if (StringUtils.isBlank(reason)) {
                throw new BusinessException("paramError","原因不能为空");
            }
            if (reason.length() < 5 || reason.length() >100) {
                throw new BusinessException("paramError","原因内容长度在5-100");
            }
        }

        TaskScheduled taskScheduled = checkPermission(taskCalendar.getTaskId(), loginUser);

        isValidCapacity(taskScheduled.getVendorId(),truckId,loginUser);

        if (taskScheduled.getTaskStatus() != null
                && taskScheduled.getTaskStatus() != TaskEnum.TaskStatus.Waiting_Become.getCode()
                && taskScheduled.getTaskStatus() != TaskEnum.TaskStatus.Running.getCode()) {
            throw new BusinessException("taskStatusError","任务状态为待生效或运行中才能操作");
        }
        if (taskCalendar.getWorkStatus() != TaskEnum.WorkStatus.Not_delivery.getCode()
                && taskCalendar.getWorkStatus() != TaskEnum.WorkStatus.Conflict.getCode()) {
            throw new BusinessException("taskStatusError","配送日状态为不配送或运力冲突才能操作");
        }
        TaskParam taskParam = taskParamService.withTaskId(taskCalendar.getTaskId());
        if (taskParam == null) {
            throw new BusinessException("notFound", "TaskParam不存在：" + taskCalendar.getTaskId());
        }

        if (isConflict(truckId,taskCalendar,taskParam)) {
            throw new BusinessException("conflictError","所选运力用车时段与已有任务用车时段有时间冲突，请重新选择车辆后再恢复配送");
        }
        //新增任务时间线
        TaskTimeline taskTimeline = new TaskTimeline();
        taskTimeline.setTenantId(taskScheduled.getTenantId());
        taskTimeline.setDriverId(driverId);
        taskTimeline.setTruckId(truckId);
        taskTimeline.setStartTime(taskCalendar.getWorkStartTime());
        taskTimeline.setEndTime(taskCalendar.getWorkEndTime());
        taskTimelineService.insertSelective(taskTimeline);

        //任务日历
        taskCalendar.setDriverId(driverId);
        taskCalendar.setTruckId(truckId);
        taskCalendar.setWorkEndTime(taskCalendar.getWorkEndTime());
        taskCalendar.setTimelineId(taskTimeline.getTimelineId());//更新时间线
        taskCalendar.setWorkStatus(TaskCalendarEnum.WorkStatus.Running.getCode());
        taskCalendarService.updateByPrimaryKeySelective(taskCalendar);

        makeWaybill(loginUser, taskCalendar, taskScheduled);

        //不配送或恢复原因
        TaskCalendarReason taskCalendarReason = new TaskCalendarReason();
        taskCalendarReason.setCalendarId(calendarId);
        taskCalendarReason.setReasonSort(0);
        taskCalendarReason.setReason(reason);
        taskCalendarReason.setReasonType(TaskCalendarReasonEnum.ReasonType.RecoverDelivery.getCode());
        taskCalendarReason.setCreateTime(new Date());
        taskCalendarReason.setCreateUserId(loginUser.getUserId());
        taskCalendarReason.setLastUpdateTime(taskCalendarReason.getCreateTime());
        taskCalendarReason.setLastUpdateUserId(loginUser.getUserId());
        taskCalendarReasonService.insertSelective(taskCalendarReason);
    }

    private void makeWaybill(LoginUser loginUser, TaskCalendar taskCalendar, TaskScheduled taskScheduled) {
        //是否在12小时内
        if (new DateTime(taskCalendar.getWorkStartTime()).isBefore(new DateTime().plusHours(12))) {
            Integer waybillId = createWaybillByTaskCalendar(taskCalendar,loginUser);
            taskCalendar.setWaybillId(waybillId);
            taskCalendarService.updateByPrimaryKeySelective(taskCalendar);
            //任务状态要变成已生效
            taskScheduled.setTaskStatus(TaskEnum.TaskStatus.Running.getCode());
            taskScheduled.setLastUpdateUserId(loginUser.getUserId());
            taskScheduled.setLastUpdateTime(new Date());
            taskScheduledService.updateByPrimaryKeySelective(taskScheduled);
        }
    }

    private TaskCalendar checkNotDelivery(Integer calendarId) {
        TaskCalendar taskCalendar = taskCalendarService.withCalendarId(calendarId);
        if (taskCalendar == null) {
            throw new BusinessException("notFound","任务日历没有找到：" + calendarId);
        }
        if(taskCalendar.getWorkStatus() != null
                && taskCalendar.getWorkStatus() != TaskEnum.WorkStatus.Running.getCode()
                && taskCalendar.getWorkStatus() != TaskEnum.WorkStatus.Conflict.getCode()) {
            throw new BusinessException("statusError","配送日状态为运行中或运力冲突的任务才能操作");
        }
        return taskCalendar;
    }

    private TaskCalendar checkRecover(Integer calendarId) {
        TaskCalendar taskCalendar = taskCalendarService.withCalendarId(calendarId);
        if (taskCalendar == null) {
            throw new BusinessException("notFound","任务日历没有找到：" + calendarId);
        }
        if (taskCalendar.getWorkStatus() == null) {
            throw new BusinessException("statusError","配送日状态异常：" + calendarId);
        }
        DateTime workDate = new DateTime(taskCalendar.getWorkStartTime()).withHourOfDay(0)
                .withMinuteOfHour(0)
                .withSecondOfMinute(0)
                .withMillisOfSecond(0);
        DateTime currDate = new DateTime().withHourOfDay(0)
                .withMinuteOfHour(0)
                .withSecondOfMinute(0)
                .withMillisOfSecond(0);
        if (workDate.isBefore(currDate)) {
            throw new BusinessException("paramError","选择的日期要大于等于当前日期");
        }
        if(taskCalendar.getWorkStatus() != TaskEnum.WorkStatus.Not_delivery.getCode()
                && taskCalendar.getWorkStatus() != TaskEnum.WorkStatus.Conflict.getCode()) {
            throw new BusinessException("statusError","配送日状态为不配送或运力冲突的任务才能操作");
        }

        TaskScheduled taskScheduled = taskScheduledService.withTaskId(taskCalendar.getTaskId());
        if (taskScheduled == null) {
            throw new BusinessException("notFound","任务不存在：" + taskCalendar.getTaskId());
        }
        if (taskScheduled.getTaskStatus() != null
                && taskScheduled.getTaskStatus() != TaskEnum.TaskStatus.Waiting_Become.getCode()
                && taskScheduled.getTaskStatus() != TaskEnum.TaskStatus.Running.getCode()) {
            throw new BusinessException("statusError","状态为待生效或运行中的任务才能操作");
        }
        return taskCalendar;
    }

    //新任务对比时间段 = (用车时间 - 运力冲突有效冗余) 到 预估用时中的最大值
    private boolean isConflict(Integer truckId,TaskCalendar taskCalendar,TaskParam taskParam) {
        //原有运力是否变更
        if (truckId.equals(taskCalendar.getTruckId())
            && taskCalendar.getWorkStatus() != null
            && taskCalendar.getWorkStatus().equals(TaskEnum.WorkStatus.Running.getCode())) return false;
        Date startTime = new DateTime(taskCalendar.getWorkStartTime()).minusHours(2).toDate();
        Date endTime = taskCalendar.getWorkEndTime();
        return isConflict(truckId,startTime,endTime);
    }

    private boolean isConflict(Integer truckId,Date startTime,Date endTime) {
        //需要查询出这个车
        List<TaskTimeline> taskTimelineList = taskTimelineService.rangTimeline(truckId);
        for (TaskTimeline taskTimeline : taskTimelineList ) {
            long t1 = startTime == null ? 0 : startTime.getTime();
            long t2 = endTime == null ? 0 : endTime.getTime();
            long t3 = taskTimeline.getStartTime() == null ? 0 : taskTimeline.getStartTime().getTime();
            long t4 = taskTimeline.getEndTime() == null ? 0 : taskTimeline.getEndTime().getTime();
            if (!(t1 > t4 || t2 < t3)) {
                return true;
            }
        }
        return false;
    }

    private CapacityPool isValidCapacity(Integer vendorId,Integer truckId,LoginUser loginUser) {
        CapacityPool capacityPool = vmsService.loadCapacityPoolByTruckId(truckId, loginUser);
        if(capacityPool == null) {
            throw new BusinessException("capacityPoolNotExist", "所选运力不存在请重新选择");
        }
        if(!capacityPool.getStatus()) {
            throw new BusinessException("capacityPoolNotExist", "所选运力不是有效运力请重新选择");
        }
        if (capacityPool.getVendorId() == null) {
            throw new BusinessException("capacityPoolNotExist", "运力没有归属承运商");
        }
        return capacityPool;
    }

    private void beforeChangeCapacity(TaskCalendar taskCalendar) {
        if (taskCalendar.getWaybillId() == null || taskCalendar.getWaybillId() == 0) return;
        Waybill waybill = waybillService.getWaybill(taskCalendar.getWaybillId());
        if (waybill == null || waybill.getStatusView() == null) return;
        if (waybill.getStatusView() != Waybill.StatusView.WATING_DELIVERY.getCode()) {
            throw new BusinessException("waybillStatusError", "日期：" + new DateTime(taskCalendar.getWorkStartTime()).toString("yyyy-MM-dd") + "运单状态不是待配送不可更换运力");
        }
    }

    private void updateWaybillCapacity(Integer waybillId,Integer truckId,Integer driverId,LoginUser loginUser) {
        Truck truck = vmsService.loadByTruckId(truckId);
        Driver driver = vmsService.loadByDriverId(driverId);
        Waybill waybill = new Waybill();
        waybill.setWaybillId(waybillId);
        waybill.setDriverId(driverId);
        waybill.setTruckId(truckId);
        waybill.setReceivingTime(new Date());
        if (truck != null) {
            waybill.setPlateNumber(truck.getPlateNumber());
        }
        if (driver != null) {
            waybill.setDriverName(driver.getName());
            waybill.setDeviceType(driver.getCanDriveType() == null ? 0:driver.getCanDriveType().intValue());
        }
        waybillCommonService.update(waybill,loginUser);
    }

    @Override
    public void doChangeCapacity(Integer calendarId, Integer driverId, Integer truckId,Integer type, LoginUser loginUser) throws BusinessException {
        CheckChangeCapacity checkChangeCapacity = new CheckChangeCapacity(calendarId, driverId, truckId, type).invoke();
        TaskCalendar taskCalendar = checkChangeCapacity.getTaskCalendar();
        TaskParam taskParam = checkChangeCapacity.getTaskParam();
        TaskScheduled taskScheduled = checkChangeCapacity.getTaskScheduled();

        isValidCapacity(taskScheduled.getVendorId(),truckId,loginUser);

        if (type == 1) {
            //检查状态
            beforeChangeCapacity(taskCalendar);
            if (isConflict(truckId,taskCalendar,taskParam)) {
                throw new BusinessException("conflictError","当前运力冲突不可更换");
            }
            taskCalendar.setTruckId(truckId);
            taskCalendar.setDriverId(driverId);
            taskCalendarService.updateByPrimaryKeySelective(taskCalendar);

            //检查是否有运单
            if (!taskCalendar.getWaybillId().equals(0)) {
                updateWaybillCapacity(taskCalendar.getWaybillId(),truckId,driverId,loginUser);
            }

            //更新时间线对应的司机、车
            TaskTimeline taskTimeline = taskTimelineService.withTimelineId(taskCalendar.getTimelineId());
            if (taskTimeline != null) {
                taskTimeline.setDriverId(driverId);
                taskTimeline.setTruckId(truckId);
                taskTimelineService.updateByPrimaryKeySelective(taskTimeline);
            }
        } else if(type == 2) {
            //后续任务
            List<TaskCalendar> taskCalendarList = taskCalendarService.selectByExample(new TaskCalendarExample().createCriteria()
                    .andTaskIdEqualTo(taskCalendar.getTaskId())
                    .andWorkStartTimeGreaterThanOrEqualTo(taskCalendar.getWorkStartTime())
                    .andWorkStatusEqualTo(TaskEnum.WorkStatus.Running.getCode())
                    .example());
            if(taskCalendarList.isEmpty()) return;
            List<TaskTimeline> updateTaskTimelineList = new ArrayList<>();

            List<Integer> waybillIds = new ArrayList<>();

            int i = 0;
            for (TaskCalendar taskCalendar1 : taskCalendarList ) {
                //检查状态
                beforeChangeCapacity(taskCalendar);
                //与历史用车冲突
                if (isConflict(truckId,taskCalendar1,taskParam)) {
                    i++;
                    taskCalendar1.setWorkStatus(TaskEnum.WorkStatus.Conflict.getCode());
                    //删除原timeline
                    taskTimelineService.deleteByPrimaryKey(taskCalendar1.getTimelineId());
                    taskCalendar1.setTimelineId(0);
                } else {
                    //检查是否有运单  更换司机、车
                    if (!"0".equals(taskCalendar1.getWaybillId() + "")) {
                        waybillIds.add(taskCalendar1.getWaybillId());
                    }
                    //可能原来是冲突的
                    if (TaskEnum.WorkStatus.Conflict.getCode() == taskCalendar1.getWorkStatus()) {
                        TaskTimeline taskTimeline = new TaskTimeline();
                        taskTimeline.setTenantId(taskScheduled.getTenantId());
                        taskTimeline.setTruckId(truckId);
                        taskTimeline.setDriverId(driverId);
                        taskTimeline.setStartTime(taskCalendar1.getWorkStartTime());
                        taskTimeline.setEndTime(taskCalendar1.getWorkEndTime());
                        taskTimelineService.insertSelective(taskTimeline);

                        taskCalendar1.setWorkStatus(TaskEnum.WorkStatus.Running.getCode());
                        taskCalendar1.setTimelineId(taskTimeline.getTimelineId());
                    } else if (TaskEnum.WorkStatus.Running.getCode() == taskCalendar1.getWorkStatus()){
                        //更新时间线对应的司机、车
                        TaskTimeline taskTimeline = new TaskTimeline();
                        taskTimeline.setTimelineId(taskCalendar1.getTimelineId());
                        taskTimeline.setTruckId(truckId);
                        taskTimeline.setDriverId(driverId);
                        updateTaskTimelineList.add(taskTimeline);
                    }
                    //没有冲突才写司机、车
                    taskCalendar1.setDriverId(driverId);
                    taskCalendar1.setTruckId(truckId);
                }
            }
            Integer conflictDayPercent = getConfig(TaskConstants.TIME_CONFLICT_DAY_PERCENT);
            if((Float.parseFloat(i+"")/taskCalendarList.size())*100 > conflictDayPercent) {
                throw new BusinessException("conflictGreaterThanError", conflictDayPercent+"");
            }

            //运单更换司机、车
            for ( Integer waybillId : waybillIds ) {
                updateWaybillCapacity(waybillId,truckId,driverId,loginUser);
            }

            if (!updateTaskTimelineList.isEmpty()) {
                taskTimelineService.updateBatchByPrimaryKeySelective(updateTaskTimelineList);
            }
            taskCalendarService.updateBatchByPrimaryKey(taskCalendarList);
        } else {
            throw new BusinessException("paramError","type参数错误");
        }
        taskScheduled.setTruckId(truckId);
        taskScheduled.setDriverId(driverId);
        taskScheduled.setLastUpdateUserId(loginUser.getUserId());
        taskScheduled.setLastUpdateTime(new Date());
        taskScheduledService.updateByPrimaryKeySelective(taskScheduled);
    }

    @Override
    public int conflictChangeCapacity(Integer calendarId, Integer driverId, Integer truckId, Integer type, LoginUser loginUser) throws BusinessException {
        CheckChangeCapacity checkChangeCapacity = new CheckChangeCapacity(calendarId, driverId, truckId, type).invoke();
        TaskCalendar taskCalendar = checkChangeCapacity.getTaskCalendar();
        TaskParam taskParam = checkChangeCapacity.getTaskParam();
        TaskScheduled taskScheduled = checkChangeCapacity.getTaskScheduled();

        isValidCapacity(taskScheduled.getVendorId(),truckId,loginUser);

        //新任务对比时间段 = 用车时间 - 运力冲突有效冗余  支预估用时中的最大值
        //检查冲突的时间
        if (type == 1) {
            //检查状态
            beforeChangeCapacity(taskCalendar);
            if (isConflict(truckId,taskCalendar,taskParam)) {
                throw new BusinessException("conflictError","当前运力冲突不可更换");
            }
        } else if(type == 2) {
            List<TaskCalendar> taskCalendarList = taskCalendarService.selectByExample(new TaskCalendarExample().createCriteria()
                    .andTaskIdEqualTo(taskCalendar.getTaskId())
                    .andWorkStartTimeGreaterThanOrEqualTo(taskCalendar.getWorkStartTime())
                    .andWorkStatusEqualTo(TaskEnum.WorkStatus.Running.getCode())
                    .example());
            if(taskCalendarList.isEmpty()) return 0;
            //区分标准时间与非标准时间
            int i = 0;
            for (TaskCalendar taskCalendar1 : taskCalendarList ) {
                //检查状态
                beforeChangeCapacity(taskCalendar);
                if (isConflict(truckId,taskCalendar1,taskParam)) {
                    i++;
                }
            }
			Integer conflictDayPercent = getConfig(TaskConstants.TIME_CONFLICT_DAY_PERCENT);
            if((Float.parseFloat(i+"")/taskCalendarList.size())*100 > conflictDayPercent) {
                throw new BusinessException("conflictGreaterThanError",conflictDayPercent+"");
            } else {
                return i;
            }
        } else {
            throw new BusinessException("paramError","type参数错误");
        }
        return 0;
    }

    public Integer createWaybillByTaskCalendar(TaskCalendar taskCalendar, LoginUser loginUser) {
        ProjectBillVo projectBillVo = new ProjectBillVo();

        TaskScheduled taskScheduled = taskScheduledService.withTaskId(taskCalendar.getTaskId());
        if (taskScheduled == null) {
            throw new BusinessException("notFound","Task不存在：" + taskCalendar.getTaskId());
        }

        Project projectV2 = projectService.getProjectV2(taskScheduled.getProjectId());
        if (projectV2 == null) {
            throw new BusinessException("notFound","Project不存在：" + taskScheduled.getProjectId());
        }

        TaskParam taskParam = taskParamService.withTaskId(taskCalendar.getTaskId());
        if (taskParam == null) {
            throw new BusinessException("notFound","TaskParam不存在：" + taskCalendar.getTaskId());
        }

        Truck truck = vmsService.loadByTruckId(taskCalendar.getTruckId());
        if (truck == null) {
            throw new BusinessException("notFound","Truck不存在：" + taskCalendar.getTruckId());
        }

        //运单主表
        Waybill waybill = new Waybill();
        waybill.setCustomerId(projectV2.getCustomerId());
        waybill.setPlanDeliveryTime(taskCalendar.getWorkStartTime());
        waybill.setReceiptType(Waybill.ReceiptType.PROJECTPAY.getCode());
        waybill.setRegionCode(taskScheduled.getRegionCode());
        waybill.setBusinessBranch(Waybill.BusinessBranch.BRANCH_FULL.getCode());
        waybill.setRoadMapId(taskScheduled.getRoadMapId());
        waybill.setWaybillSource(Waybill.WaybillSource.FORM_TASK.getCode());
        waybill.setReceiveWay(Waybill.ReceiveWay.ASSIGNED.getCode());
        waybill.setReceivingTime(new Date());
        waybill.setDriverId(taskCalendar.getDriverId());

        //如果是一口价，要计算这两个值
        if (taskParam.getPricingMethod() != null && taskParam.getPricingMethod().equals(PricingMethod.By_Pack.getCode())) {
            Map<String,Object> pricingRuleJsonMap = JSON.parseObject(taskParam.getPricingRule(), Map.class);
            if (pricingRuleJsonMap != null && !pricingRuleJsonMap.isEmpty() && pricingRuleJsonMap.containsKey("factorJson")) {
                Map<String, Object> factorJsonMap = JSON.parseObject(pricingRuleJsonMap.get("factorJson").toString(), Map.class);
                if (factorJsonMap != null && !factorJsonMap.isEmpty() && factorJsonMap.containsKey("estimateFreight")) {
                    String estimateFreight = factorJsonMap.get("estimateFreight").toString();
                    waybill.setEstimateFreight(new BigDecimal(estimateFreight));
                    waybill.setAfterTaxFreight(new BigDecimal(estimateFreight));
                    waybill.setCalculatedFreight(new BigDecimal(estimateFreight));
                }
            }
        } else {
            waybill.setEstimateFreight(BigDecimal.ZERO);
            waybill.setAfterTaxFreight(BigDecimal.ZERO);
            waybill.setCalculatedFreight(BigDecimal.ZERO);
        }

        waybill.setCmEstimateFinishTime(taskCalendar.getWorkEndTime());
        waybill.setOnlyLoadCargo(0);
        waybill.setNeedDeliveryPointNote(0);

        //车辆id
        Set<Integer> fixedTruckIds = new HashSet<>();
        fixedTruckIds.add(taskCalendar.getTruckId());
        projectBillVo.setFixedTruckIds(fixedTruckIds);

        projectBillVo.setWaybill(waybill);

        //项目
        Project project = new Project();
        project.setProjectId(projectV2.getProjectId());
        projectBillVo.setProject(project);

        //用车要求
        TruckRequire truckRequire = new TruckRequire();
        truckRequire.setGoodsType(taskParam.getGoodsType());
        truckRequire.setGoodsVolume("0");
        truckRequire.setGoodsWeight("0");
        truckRequire.setAdditionalFunctionIds(taskParam.getFunctionIds());
        truckRequire.setTaxRateValue(projectV2.getTaxRateValue());
        truckRequire.setRemark(taskParam.getRequireMark());

        TruckType truckType = truckTypeService.findByBoxAndLength(truck.getVehicleBoxType(), truck.getVehicleBoxLength(), taskScheduled.getTenantId());
        if (truckType != null) {
            truckRequire.setTruckTypeId(truckType.getTruckTypeId());
        }
        projectBillVo.setTruckRequire(truckRequire);

        //取货地  就是仓库
        ProjectDepot projectDepot = projectDepotService.withDepotId(taskScheduled.getDepotId());
        if (projectDepot == null) {
            throw new BusinessException("notFound","仓库不存在：" + taskScheduled.getDepotId());
        }
        WaybillDeliveryAddress waybillDeliveryAddress = new WaybillDeliveryAddress();
        waybillDeliveryAddress.setRegionCode(taskScheduled.getRegionCode());
        waybillDeliveryAddress.setAddressName(projectDepot.getDepotName());
        waybillDeliveryAddress.setAddressDetail(projectDepot.getDepotAddress());
        waybillDeliveryAddress.setContactName(projectDepot.getLinkMan());
        waybillDeliveryAddress.setContactPhone(projectDepot.getLinkManPhone());
        waybillDeliveryAddress.setCoordinates(projectDepot.getDepotCoordinates());
        projectBillVo.getDeliveryAddress().add(waybillDeliveryAddress);

        //配送地
        List<TaskFixedDelivery> taskFixedDeliveryList = taskFixedDeliveryService.withTaskId(taskCalendar.getTaskId());
        for ( TaskFixedDelivery fixedDelivery : taskFixedDeliveryList ) {
            WaybillReceiveAddress waybillReceiveAddress = new WaybillReceiveAddress();
            waybillReceiveAddress.setRegionCode(taskScheduled.getRegionCode());
            waybillReceiveAddress.setAddressName(fixedDelivery.getAddressName());
            waybillReceiveAddress.setAddressDetail(fixedDelivery.getAddressDetail());
            waybillReceiveAddress.setContactName(fixedDelivery.getLinkMan());
            waybillReceiveAddress.setContactPhone(fixedDelivery.getLinkManTel());
            waybillReceiveAddress.setCoordinates(fixedDelivery.getCoordinates());
            projectBillVo.getReceiveAddress().add(waybillReceiveAddress);
        }

        //运单参数表
        WaybillParam waybillParam = new WaybillParam();
        waybillParam.setProjectFreightRuleJson(taskParam.getPricingRule());
        waybillParam.setTaskId(taskCalendar.getTaskId());
        projectBillVo.setWaybillParam(waybillParam);

        //LoginUser 要构造项目经理
        LoginUser managerUser = new LoginUser(projectV2.getTenantId(),projectV2.getProjectManagerUserId());

        try {
            List<Integer> waybillIds = filialeBillService.createProjectBill(projectBillVo, managerUser);
            return waybillIds == null || waybillIds.isEmpty() ? 0 : waybillIds.get(0);
        } catch (Exception e) {
            String resultMsg = "系统错误;";
            if (e instanceof GiantsException) {
                resultMsg = ((GiantsException) e).getErrorMessage();
            } else {
                resultMsg += "taskCalendarId date:" + taskCalendar.getWorkStartTime();
            }
            taskCalendar.setWorkStatus(TaskEnum.WorkStatus.Not_delivery.getCode());
            taskCalendar.setTimelineId(0);
            taskCalendar.setFailureReason(resultMsg);
            taskCalendarService.updateByPrimaryKeySelective(taskCalendar);

            taskTimelineService.deleteByPrimaryKey(taskCalendar.getTimelineId());
            User user = userService.loadUser(projectV2.getProjectManagerUserId());
            if(user != null) {
                String content = String.format(TaskConstants.CREATE_WAYBILL_FAILURE_PUSH, projectV2.getName(), taskScheduled.getTaskNo(), resultMsg);
                List<String> phoneList = new ArrayList<>();
                phoneList.add(user.getMobileNumber());
                messagePushService.cloudMessagePush(Constants.AUTH_KEY_TGM_MANAGE,taskScheduled.getTaskId().toString(), "创建运单失败", content, phoneList, loginUser);
            }
        }
        return 0;
    }

    @Override
    public void changeVendor(InviteRequest request, LoginUser loginUser) throws BusinessException {
        if (request == null
                || request.getTaskId() == null
                || request.getVendorId() == null
                || request.getTruckId() == null
                || request.getDriverId() == null) {
            throw new BusinessException("paramError","参数taskId或vendorId或truckId或driverId为空");
        }

        TaskAck taskAck = taskAckService.withLastAck(request.getTaskId());
        //待回复的才变成失效
        if (taskAck != null
                && taskAck.getAckStatus() != null
                && taskAck.getAckStatus().equals(TaskEnum.TaskAckStatus.Waiting_Reply.getCode())) {
            taskAck.setAckStatus(TaskEnum.TaskAckStatus.Invalided.getCode());
            taskAck.setLastUpdateTime(new Date());
            taskAck.setLastUpdateUserId(loginUser.getUserId());
            taskAckService.updateByPrimaryKeySelective(taskAck);
        }
        inviteVendor(request,loginUser);
    }

    @Override
    public void updateToVendorReceive(Integer taskId,Integer vendorId, LoginUser loginUser) {
        checkLoginUser(loginUser);
        if (taskId == null || vendorId == null) {
            throw new BusinessException("paramError","taskId或vendorId不能为空");
        }
        TaskScheduled taskScheduled = taskScheduledService.withTaskId(taskId);
        if (taskScheduled == null || taskScheduled.getTaskStatus() == null) {
            throw new BusinessException("notFound","Task不存在：" + taskScheduled.getTaskId());
        }
        if (taskScheduled.getTaskStatus() != TaskEnum.TaskStatus.Inviting.getCode()) {
            throw new BusinessException("taskStatusError","任务状态为邀请中的才能操作");
        }
        List<TaskAck> taskAckList = taskAckService.selectByExample(new TaskAckExample().createCriteria().andTaskIdEqualTo(taskId)
                .andVendorIdEqualTo(vendorId).andAckStatusEqualTo(TaskEnum.TaskAckStatus.Waiting_Reply.getCode()).example());
        if (taskAckList.isEmpty()) {
            throw new BusinessException("notFound","没有待这个承运商回复的邀请：" + taskId + "，" + vendorId);
        }
        TaskAck taskAck = taskAckList.get(0);
        TaskParam taskParam = taskParamService.withTaskId(taskId);
        if (taskParam == null) {
            throw new BusinessException("notFound","TaskParam不存在：" + taskId);
        }
        TaskPeriod taskPeriod = taskPeriodService.withTaskId(taskId);
        if (taskPeriod == null) {
            throw new BusinessException("notFound","TaskPeriod不存在：" + taskId);
        }

        makeTaskCalendar(true,false,taskId,vendorId, taskAck.getTruckId(),taskAck.getDriverId(),taskScheduled, taskParam, taskPeriod,loginUser);

        long l = taskCalendarService.countByExample(new TaskCalendarExample().createCriteria()
                .andTaskIdEqualTo(taskId)
                .andWaybillIdNotEqualTo(0).example());
        if (l == 0) {
            taskScheduled.setTaskStatus(TaskEnum.TaskStatus.Waiting_Become.getCode());
        } else {
            taskScheduled.setTaskStatus(TaskEnum.TaskStatus.Running.getCode());
        }

        taskScheduled.setLastUpdateUserId(loginUser.getUserId());
        taskScheduled.setLastUpdateTime(new Date());
        taskScheduledService.updateByPrimaryKeySelective(taskScheduled);

        taskAck.setAckStatus(TaskEnum.TaskAckStatus.Received.getCode());
        taskAck.setLastUpdateUserId(loginUser.getUserId());
        taskAck.setLastUpdateTime(new Date());
        taskAckService.updateByPrimaryKeySelective(taskAck);

        messagePushService.vendorReceive(taskId,vendorId,loginUser);
    }

    @Override
    public int conflictVendorReceive(Integer taskId, Integer vendorId, LoginUser loginUser) throws BusinessException {
        checkLoginUser(loginUser);
        if (taskId == null || vendorId == null) {
            throw new BusinessException("paramError","taskId或vendorId不能为空");
        }
        TaskScheduled taskScheduled = taskScheduledService.withTaskId(taskId);
        if (taskScheduled == null || taskScheduled.getTaskStatus() == null) {
            throw new BusinessException("notFound","Task不存在：" + taskScheduled.getTaskId());
        }
        if (taskScheduled.getTaskStatus() != TaskEnum.TaskStatus.Inviting.getCode()) {
            throw new BusinessException("taskStatusError","任务状态为邀请中的才能操作");
        }
        TaskAck taskAck = taskAckService.withLastAck(taskId, vendorId);
        if (taskAck == null || taskAck.getAckStatus() == null) {
            throw new BusinessException("notFound","TaskAck不存在：" + taskAck.getAckId());
        }
        if (taskAck.getAckStatus() != TaskEnum.TaskAckStatus.Waiting_Reply.getCode()) {
            throw new BusinessException("taskStatusError","邀请状态为待回复的才能操作");
        }
        TaskParam taskParam = taskParamService.withTaskId(taskId);
        if (taskParam == null) {
            throw new BusinessException("notFound","TaskParam不存在：" + taskId);
        }
        TaskPeriod taskPeriod = taskPeriodService.withTaskId(taskId);
        if (taskPeriod == null) {
            throw new BusinessException("notFound","TaskPeriod不存在：" + taskId);
        }

        return makeTaskCalendar(false,false,taskId, vendorId,taskAck.getTruckId(), taskAck.getDriverId(),taskScheduled, taskParam, taskPeriod,loginUser);
    }

    @Override
    public int makeTaskCalendar(boolean isUpdate,boolean isThrowException,Integer taskId,Integer vendorId,Integer truckId,Integer driverId,
                                 TaskScheduled taskScheduled, TaskParam taskParam, TaskPeriod taskPeriod,LoginUser loginUser) {

        LoginUser l = new LoginUser();
        l.setTenantId(taskScheduled.getTenantId());
        isValidCapacity(vendorId,truckId,l);

        DateTime startDateTime = new DateTime(taskScheduled.getStartDate());
        DateTime endDateTime = new DateTime(taskScheduled.getEndDate());

        List<String> deliveryPeriods = Splitter.on(',').splitToList(taskPeriod.getDeliveryPeriod());
        List<String> deliveryPeriodTimes = Splitter.on(',').splitToList(taskPeriod.getDeliveryPeriodTime());
        if (deliveryPeriods.isEmpty() || deliveryPeriodTimes.isEmpty()) {
            throw new BusinessException("paramError","配送周期数据异常：" + taskId);
        }

        if (!taskPeriod.getIsStandardTime() && deliveryPeriods.size() != deliveryPeriodTimes.size()) {
            throw new BusinessException("paramError","配送周期数据结构异常：" + taskId);
        }

        //periodTimeMap  结构{"1":"09:00"}
        Map<String,String> periodTimeMap = new HashMap<>();
        if (!taskPeriod.getIsStandardTime()) {
            for ( int i=0;i<deliveryPeriods.size();i++ ) {
                periodTimeMap.put(deliveryPeriods.get(i),deliveryPeriodTimes.get(i));
            }
        }

        List<String> times = Splitter.on(":").splitToList(deliveryPeriodTimes.get(0));

        int i = 0;
        int j = 0;
        for ( ;startDateTime.isBefore(endDateTime) || startDateTime.isEqual(endDateTime);) {
            //是否在配送周期
            if (!deliveryPeriods.contains(startDateTime.getDayOfWeek() + "")) {
                startDateTime = startDateTime.plusDays(1);
                continue;
            }
            j++;

            Date startTime;
            if (taskPeriod.getIsStandardTime()) {
                startTime = new DateTime(startDateTime.getYear()
                        ,startDateTime.getMonthOfYear()
                        ,startDateTime.getDayOfMonth()
                        ,Integer.parseInt(times.get(0))
                        ,Integer.parseInt(times.get(1))).toDate();
            } else {
                String s = periodTimeMap.get(startDateTime.getDayOfWeek() + "");
                times = Splitter.on(":").splitToList(s);
                startTime = new DateTime(startDateTime.getYear()
                        ,startDateTime.getMonthOfYear()
                        ,startDateTime.getDayOfMonth()
                        ,Integer.parseInt(times.get(0))
                        ,Integer.parseInt(times.get(1))).toDate();
            }

            int maxTimeCost = Integer.parseInt(taskParam.getEstimateTimeCostRange().split("-")[1]);
            Date endTime = new DateTime(startTime).plusHours(maxTimeCost).toDate();


            //运力冲突检查  这里开始时间结束时间
            boolean isConflicted = isConflict(truckId,startTime,endTime);
            if (isConflicted) {
                if (isThrowException) {
                    throw new BusinessException("conflictError","当前运力与已有的任务时间段冲突");
                }
                i++;
            }

            if (isUpdate) {
                //任务日历
                TaskCalendar taskCalendar = new TaskCalendar();
                taskCalendar.setVendorId(vendorId);
                taskCalendar.setWaybillId(0);
                taskCalendar.setTaskId(taskId);
                taskCalendar.setDriverId(driverId);
                taskCalendar.setTruckId(truckId);
                taskCalendar.setWorkStartTime(startTime);
                taskCalendar.setWorkEndTime(endTime);

                if (!isConflicted) {
                    //任务时间线
                    TaskTimeline taskTimeline = new TaskTimeline();
                    taskTimeline.setTenantId(taskScheduled.getTenantId());
                    taskTimeline.setDriverId(driverId);
                    taskTimeline.setTruckId(truckId);
                    taskTimeline.setStartTime(startTime);
                    taskTimeline.setEndTime(endTime);
                    taskTimelineService.insertSelective(taskTimeline);

                    //不冲突
                    taskCalendar.setTimelineId(taskTimeline.getTimelineId());
                    taskCalendar.setWorkStatus(TaskCalendarEnum.WorkStatus.Running.getCode());

                    //是否在12小时内
                    if (new DateTime(startTime).isBefore(new DateTime().plusHours(12))) {
                        Integer waybillId = createWaybillByTaskCalendar(taskCalendar,loginUser);
                        taskCalendar.setWaybillId(waybillId);
                        //任务要变成运行中
                        taskScheduled.setTaskStatus(TaskEnum.TaskStatus.Running.getCode());
                    }
                } else {
                    taskCalendar.setDriverId(0);
                    taskCalendar.setTruckId(0);
                    taskCalendar.setTimelineId(0);
                    taskCalendar.setWorkStatus(TaskCalendarEnum.WorkStatus.Conflict.getCode());
                }
                taskCalendarService.insertSelective(taskCalendar);
            }
            startDateTime = startDateTime.plusDays(1);
        }
        if (j == 0) return 0;
        Integer conflictDayPercent = getConfig(TaskConstants.TIME_CONFLICT_DAY_PERCENT);
        if ((Float.parseFloat(i+"")/j)*100 > conflictDayPercent) {
            throw new BusinessException("conflictGreaterThanError",conflictDayPercent+"");
        }
        return i;
    }

    @Override
    public void updateToVendorRefuse(Integer taskId,Integer vendorId, String reason, LoginUser loginUser) {
        checkLoginUser(loginUser);
        if (taskId == null || vendorId == null) {
            throw new BusinessException("paramError","taskId或vendorId不能为空");
        }
        if (StringUtils.isBlank(reason)) {
            throw new BusinessException("paramError","reason不能为空");
        }
        TaskScheduled taskScheduled = taskScheduledService.withTaskId(taskId);
        if (taskScheduled == null || taskScheduled.getTaskStatus() == null) {
            throw new BusinessException("notFound","Task不存在：" + taskId);
        }
        if (taskScheduled.getTaskStatus() != TaskEnum.TaskStatus.Inviting.getCode()) {
            throw new BusinessException("taskStatusError","任务状态为邀请中的才能操作");
        }
        TaskAck taskAck = taskAckService.withLastAck(taskId, vendorId);
        if (taskAck == null || taskAck.getAckStatus() == null) {
            throw new BusinessException("notFound","TaskAck不存在：" + taskId);
        }
        if (taskAck.getAckStatus() != TaskEnum.TaskAckStatus.Waiting_Reply.getCode()) {
            throw new BusinessException("notFound","邀请状态为待回复的才能操作");
        }
        taskAck.setAckStatus(TaskEnum.TaskAckStatus.Refused.getCode());
        taskAck.setRefuseReason(reason);
        taskAck.setLastUpdateUserId(loginUser.getUserId());
        taskAck.setLastUpdateTime(new Date());
        taskAckService.updateByPrimaryKeySelective(taskAck);

        messagePushService.vendorRefuse(taskId,vendorId,loginUser);
    }

    @Override
    public TaskWaybill taskWaybill(int waybillId) throws BusinessException {
        TaskWaybill taskWaybill = new TaskWaybill();
        if (waybillId == 0) return taskWaybill;
        WaybillParam waybillParam = waybillParamService.findByWaybillId(waybillId);
        if (waybillParam == null) {
            throw new BusinessException("notFound","WaybillParam不存在：" + waybillId);
        }
        if (waybillParam.getTaskId() == null) {
            return taskWaybill;
        }

        TaskScheduled taskScheduled = taskScheduledService.withTaskId(waybillParam.getTaskId());
        if (taskScheduled == null) {
            throw new BusinessException("notFound","TaskScheduled不存在：" + waybillParam.getTaskId());
        }
        TaskPeriod taskPeriod = taskPeriodService.withTaskId(waybillParam.getTaskId());
        if (taskPeriod == null) {
            throw new BusinessException("notFound","TaskPeriod不存在：" + waybillParam.getTaskId());
        }

        taskWaybill.setTaskStartDate(taskScheduled.getStartDate());
        taskWaybill.setTaskEndDate(taskScheduled.getEndDate());
        taskWaybill.setIsStandardTime(taskPeriod.getIsStandardTime());
        taskWaybill.setDeliveryPeriod(taskPeriod.getDeliveryPeriod());
        taskWaybill.setDeliveryPeriodTime(taskPeriod.getDeliveryPeriodTime());
        return taskWaybill;
    }

    @Override
    public int taskCountWithVendorId(Integer vendorId) throws BusinessException {
        List<Integer> taskStatusList = new ArrayList<>();
        taskStatusList.add(TaskEnum.TaskStatus.Running.getCode());
        taskStatusList.add(TaskEnum.TaskStatus.Waiting_Become.getCode());
        long l = taskScheduledService.countByExample(new TaskScheduledExample().createCriteria()
                .andVendorIdEqualTo(vendorId)
                .andTaskStatusIn(taskStatusList)
                .andIsDeleteEqualTo(false)
                .example());
        return (int) l;
    }

    @Override
    public NotDeliveryReasonSort notDeliveryReasonSort() throws BusinessException {
        NotDeliveryReasonSort notDeliveryReasonSort = new NotDeliveryReasonSort();
        List<ConfParamOption> vendorReason = authCommonService.listOption(TaskConstants.VENDOR_NOT_DELIVERY_REASON);
        List<ConfParamOption> companyReason = authCommonService.listOption(TaskConstants.OWEN_NOT_DELIVERY_REASON);
        List<ConfParamOption> customerReason = authCommonService.listOption(TaskConstants.CUSTOMER_NOT_DELIVERY_REASON);
        for ( ConfParamOption confParamOption : vendorReason ) {
            notDeliveryReasonSort.getVendorReasonSort().add(confParamOption.getOptionName());
        }
        for ( ConfParamOption confParamOption : companyReason ) {
            notDeliveryReasonSort.getCompanyReasonSort().add(confParamOption.getOptionName());
        }
        for ( ConfParamOption confParamOption : customerReason ) {
            notDeliveryReasonSort.getCustomerReasonSort().add(confParamOption.getOptionName());
        }
        return notDeliveryReasonSort;
    }

    public static void main(String[] args) {


        Integer i = 1;

        Float j = 3f;

        System.out.println(i/j);

        System.out.println(i/j * 100 > 30);

        DateTime d1 = new DateTime(2019,7,1,0,0,0,0);
        DateTime d2 = new DateTime(2019,8,1,0,0,0,0);

        System.out.println(d1.getDayOfWeek());

        for(;d1.isBefore(d2);) {

            System.out.println(d1);
            d1 = d1.plusDays(1);

        }


        DateTime dateTime = new DateTime(2019,7,6,0,0,0);

        System.out.println(dateTime.getDayOfWeek());

        //月份第一天
        Date date1 = dateTime.dayOfMonth().withMinimumValue()
                .withHourOfDay(0)
                .withMinuteOfHour(0)
                .withSecondOfMinute(0)
                .withMillisOfSecond(0).toDate();
        //月份最后一天
        Date date2 = dateTime.dayOfMonth().withMaximumValue()
                .withHourOfDay(0)
                .withMinuteOfHour(0)
                .withSecondOfMinute(0)
                .withMillisOfSecond(0).toDate();

        System.out.println(date1);

        System.out.println(date2);

        Days days = Days.daysBetween(new DateTime(2019, 7, 1, 0, 0, 0)
                , new DateTime(2019, 6, 1, 0, 0, 0));
        System.out.println(days.getDays());




    }


    private class CheckChangeCapacity {
        private Integer calendarId;
        private Integer driverId;
        private Integer truckId;
        private Integer type;
        private TaskCalendar taskCalendar;
        private TaskParam taskParam;
        private TaskScheduled taskScheduled;

        public CheckChangeCapacity(Integer calendarId, Integer driverId, Integer truckId, Integer type) {
            this.calendarId = calendarId;
            this.driverId = driverId;
            this.truckId = truckId;
            this.type = type;
        }

        public TaskCalendar getTaskCalendar() {
            return taskCalendar;
        }

        public TaskParam getTaskParam() {
            return taskParam;
        }

        public TaskScheduled getTaskScheduled() {
            return taskScheduled;
        }

        public CheckChangeCapacity invoke() {
            if (calendarId == null || driverId == null || truckId == null || type == null) {
                throw new BusinessException("paramError","参数错误");
            }
            taskCalendar = taskCalendarService.selectByPrimaryKey(calendarId);
            if (taskCalendar == null || taskCalendar.getWorkStatus() == null) {
                throw new BusinessException("notFound","TaskCalendar不存在：" + calendarId);
            }
            if (taskCalendar.getWorkStatus() == TaskEnum.WorkStatus.Finish.getCode()) {
                throw new BusinessException("workStatusError","配送日状态为已完成不能操作");
            }
            taskScheduled = taskScheduledService.withTaskId(taskCalendar.getTaskId());
            if (taskScheduled == null || taskScheduled.getTaskStatus() == null) {
                throw new BusinessException("notFound","Task不存在：" + taskCalendar.getTaskId());
            }
            if (taskScheduled.getTaskStatus() != TaskEnum.TaskStatus.Waiting_Become.getCode()
                    && taskScheduled.getTaskStatus() != TaskEnum.TaskStatus.Running.getCode()) {
                throw new BusinessException("taskStatusError","任务状态不为待生效或运行中不能操作");
            }
            Waybill waybill = waybillService.getWaybill(taskCalendar.getWaybillId());
            if (waybill != null && waybill.getStatusView() != null && waybill.getStatusView() != Waybill.StatusView.WATING_DELIVERY.getCode()) {
                throw new BusinessException("waybillStatusError", "当前日期运单状态不是【待配送】不可更换运力");
            }
            taskParam = taskParamService.withTaskId(taskCalendar.getTaskId());
            if (taskParam == null) {
                throw new BusinessException("notFound","TaskParam不存在：" + taskCalendar.getTaskId());
            }
            return this;
        }
    }


    @Override
    public void endTask(Integer taskId, LoginEmployee loginEmployee) {
        if(taskId == null)
            throw new BusinessException("paramError", "taskId不能为空");

        TaskScheduled taskScheduledDB = taskScheduledService.withTaskId(taskId);

        TaskScheduled task = new TaskScheduled();
        task.setTaskId(taskId);
        task.setTaskStatus(TaskEnum.TaskStatus.Finish.getCode());
        taskScheduledService.updateByPrimaryKeySelective(task);

        //取消运单
        TaskCalendarExample example = new TaskCalendarExample().createCriteria()
            .andTaskIdEqualTo(taskId)
            .andWorkStartTimeGreaterThan(DateUtil.dayStartReturnDate(new Date())).example();
        List<TaskCalendar> taskCalendars = taskCalendarService.selectByExample(example);
        for(TaskCalendar taskCalendar : taskCalendars){
            if(taskCalendar.getWorkStartTime().compareTo(DateUtil.dayStartReturnDate(new Date())) >= 0
                && taskCalendar.getWorkStartTime().compareTo(DateUtil.dayEndForDate(new Date())) <= 0){
                Waybill waybillDB = waybillService.getWaybill(taskCalendar.getWaybillId());
                //取消运单
                if(taskCalendar.getWorkStatus().equals(TaskEnum.WorkStatus.Running.getCode())
                    && taskCalendar.getWaybillId() != 0 && waybillDB != null
                    && waybillDB.getStatusView() != null && waybillDB.getStatusView().equals(Waybill.StatusView.WATING_DELIVERY.getCode())){
                    //取消运单
                    Waybill waybill = new Waybill();
                    waybill.setWaybillId(taskCalendar.getWaybillId());
                    waybill.setWaybillCancelRemark("结束任务");
                    waybill.setCancelChannel(Waybill.CancelChannel.TASK_CALENDAR.getCode());
                    waybillCommonService.doCancelWaybill(waybill, loginEmployee, Waybill.Status.SYS_CANCEL);
                    //释放运力
                    taskTimelineService.deleteByPrimaryKey(taskCalendar.getTimelineId());
                    //任务日历修改为不配送
                    taskCalendar.setTimelineId(0);
                    taskCalendar.setWaybillId(0);
                    taskCalendar.setWorkStatus(TaskEnum.WorkStatus.Not_delivery.getCode());
                    taskCalendarService.updateByPrimaryKeySelective(taskCalendar);
                }
            }else{
                //释放运力
                taskTimelineService.deleteByPrimaryKey(taskCalendar.getTimelineId());
                //任务日历修改为不配送
                taskCalendar.setTimelineId(0);
                taskCalendar.setWaybillId(0);
                taskCalendar.setWorkStatus(TaskEnum.WorkStatus.Not_delivery.getCode());
                taskCalendarService.updateByPrimaryKeySelective(taskCalendar);
            }

        }

        Vendor vendor = vmsService.loadByVenorId(taskScheduledDB.getVendorId());
		Project projectV2 = projectService.getProjectV2(taskScheduledDB.getProjectId());
		if(vendor == null || projectV2 == null || StringUtils.isBlank(vendor.getContactPhone())){
			return;
		}
		Map<String,Object> extras = new HashMap<>();
        extras.put("vendorName", vendor.getVendorName());
        extras.put("projectName", projectV2.getName());
        messageService.pushSmsMessage(loginEmployee, TaskConstants.END_TASK_SMS, extras, vendor.getContactPhone());

        Map<String,Object> extrasPush = new HashMap<>();
        extrasPush.put("taskId", taskId);
        messagePushService.pushAppMessage(TaskConstants.END_TASK_PUSH, extrasPush, loginEmployee, vendor.getUserId().toString());
    }

    @Override
    public void recoverTask(Integer taskId, LoginUser loginUser) {

        if(taskId == null)
            throw new BusinessException("paramError", "taskId不能为空");
        TaskScheduled taskScheduled = taskScheduledService.withTaskId(taskId);
        if(taskScheduled == null || taskScheduled.getTaskStatus() == null){
            throw new BusinessException("taskNotExist", "taskScheduled不存在");
        }
        if(!taskScheduled.getTaskStatus().equals(TaskEnum.TaskStatus.Pause.getCode())){
            throw new BusinessException("taskStatusError", "只有已暂停的任务才能恢复");
        }
        Project project = projectService.getProjectV2(taskScheduled.getProjectId());
        if(project == null || project.getProjectStatus() == null){
            throw new BusinessException("projectNotExist", "项目不存在");
        }
        if(!project.getProjectStatus().equals(ProjectEnum.ProjectStatus.RUNING.getCode())){
            throw new BusinessException("projectStatusError", "只能启用项目状态为【运行中】的任务");
        }

        TaskScheduled updateTaskScheduled = new TaskScheduled();
        updateTaskScheduled.setTaskId(taskId);
        updateTaskScheduled.setTaskStatus(TaskEnum.TaskStatus.Running.getCode());
        taskScheduledService.updateByPrimaryKeySelective(updateTaskScheduled);

    }

    private Integer getConfig(String configKey){
		List<ConfParamOption> confParamOptions = authCommonService.listOption(configKey);
		if(confParamOptions.isEmpty()){
			throw new BusinessException("configNotExist", "未获取到配置，key:" + configKey);
		}
		String value = confParamOptions.get(0).getOptionValue();
		return Integer.parseInt(value);
	}

}
