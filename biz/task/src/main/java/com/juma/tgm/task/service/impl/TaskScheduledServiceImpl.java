package com.juma.tgm.task.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.mybatis.generator.my.mapper.Mapper;
import org.mybatis.generator.my.service.AbstractMybatisService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.giants.common.exception.BusinessException;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.juma.auth.conf.domain.BusinessArea;
import com.juma.auth.user.domain.LoginUser;
import com.juma.auth.user.domain.User;
import com.juma.auth.user.service.UserService;
import com.juma.conf.domain.ConfParamOption;
import com.juma.tgm.capacity.domian.vo.CapacityFilter;
import com.juma.tgm.common.DateUtil;
import com.juma.tgm.common.id.IdGeneratorTable;
import com.juma.tgm.common.query.QueryCond;
import com.juma.tgm.common.task.TaskConstants;
import com.juma.tgm.common.vo.Page;
import com.juma.tgm.configure.domain.FreightFactor;
import com.juma.tgm.configure.service.FreightFactorService;
import com.juma.tgm.crm.domain.CustomerInfo;
import com.juma.tgm.crm.service.CustomerInfoService;
import com.juma.tgm.id.service.IdGeneratorService;
import com.juma.tgm.project.domain.RoadMap;
import com.juma.tgm.project.domain.RoadMapPriceRule;
import com.juma.tgm.project.domain.v2.Project;
import com.juma.tgm.project.domain.v2.ProjectDepot;
import com.juma.tgm.project.domain.v2.ProjectDepotExample;
import com.juma.tgm.project.domain.v2.enums.ProjectEnum;
import com.juma.tgm.project.service.ProjectDepotService;
import com.juma.tgm.project.service.ProjectService;
import com.juma.tgm.project.service.RoadMapPriceRuleService;
import com.juma.tgm.project.service.RoadMapService;
import com.juma.tgm.task.dao.TaskScheduledMapper;
import com.juma.tgm.task.dao.ext.TaskCalendarExtMapper;
import com.juma.tgm.task.dao.ext.TaskFixedDeliveryExtMapper;
import com.juma.tgm.task.dao.ext.TaskScheduledExtMapper;
import com.juma.tgm.task.domain.TaskAck;
import com.juma.tgm.task.domain.TaskAckExample;
import com.juma.tgm.task.domain.TaskCalendar;
import com.juma.tgm.task.domain.TaskCalendarExample;
import com.juma.tgm.task.domain.TaskDevice;
import com.juma.tgm.task.domain.TaskDeviceExample;
import com.juma.tgm.task.domain.TaskFixedDelivery;
import com.juma.tgm.task.domain.TaskNotfixedDelivery;
import com.juma.tgm.task.domain.TaskParam;
import com.juma.tgm.task.domain.TaskPeriod;
import com.juma.tgm.task.domain.TaskScheduled;
import com.juma.tgm.task.domain.TaskScheduledExample;
import com.juma.tgm.task.domain.TaskTimeline;
import com.juma.tgm.task.domain.ext.GroupTaskCalendar;
import com.juma.tgm.task.domain.vo.TaskScheduledVO;
import com.juma.tgm.task.domain.vo.TaskTimeVO;
import com.juma.tgm.task.dto.gateway.GroupTaskCalendarFilter;
import com.juma.tgm.task.dto.manage.TaskFilter;
import com.juma.tgm.task.enums.AckStatus;
import com.juma.tgm.task.enums.TaskEnum;
import com.juma.tgm.task.service.TaskAckService;
import com.juma.tgm.task.service.TaskCalendarService;
import com.juma.tgm.task.service.TaskDeviceService;
import com.juma.tgm.task.service.TaskFacadeService;
import com.juma.tgm.task.service.TaskFixedDeliveryService;
import com.juma.tgm.task.service.TaskNotfixedDeliveryService;
import com.juma.tgm.task.service.TaskParamService;
import com.juma.tgm.task.service.TaskPeriodService;
import com.juma.tgm.task.service.TaskScheduledService;
import com.juma.tgm.task.service.TaskTimelineService;
import com.juma.tgm.task.vo.gateway.Depot;
import com.juma.tgm.task.vo.gateway.FixedDeliveryInfo;
import com.juma.tgm.task.vo.gateway.InviteRequest;
import com.juma.tgm.task.vo.gateway.NotFixedDeliveryInfo;
import com.juma.tgm.task.vo.gateway.TaskAckDetail;
import com.juma.tgm.task.vo.gateway.TaskAckPage;
import com.juma.tgm.task.vo.gateway.TaskCalendarByProject;
import com.juma.tgm.task.vo.gateway.TaskStatusCount;
import com.juma.tgm.task.vo.manage.CapacityPoolPage;
import com.juma.tgm.task.vo.manage.Task;
import com.juma.tgm.task.vo.manage.TaskAckVO;
import com.juma.tgm.task.vo.manage.TaskDelivery;
import com.juma.tgm.task.vo.manage.TaskDetail;
import com.juma.tgm.task.vo.manage.TaskLinkWaybill;
import com.juma.tgm.task.vo.manage.TaskScheduledDetail;
import com.juma.tgm.tools.service.AuthCommonService;
import com.juma.tgm.tools.service.MessagePushService;
import com.juma.tgm.tools.service.VmsCommonService;
import com.juma.tgm.truck.domain.AdditionalFunction;
import com.juma.tgm.truck.service.AdditionalFunctionService;
import com.juma.tgm.truck.service.TruckTypeService;
import com.juma.tgm.waybill.domain.WaybillParam;
import com.juma.tgm.waybill.service.WaybillParamService;
import com.juma.vms.driver.domain.Driver;
import com.juma.vms.external.service.VmsService;
import com.juma.vms.tool.service.MessageService;
import com.juma.vms.transport.domain.CapacityPool;
import com.juma.vms.truck.domain.Truck;
import com.juma.vms.truck.enumeration.TruckRunTypeEnum;
import com.juma.vms.vendor.domain.Vendor;
import com.juma.vms.vendor.enumeration.VendorSourceEnum;

@Service
public class TaskScheduledServiceImpl  extends AbstractMybatisService<TaskScheduled, TaskScheduledExample> implements TaskScheduledService {

    @Resource
    private TaskScheduledMapper taskScheduledMapper;

    @Resource
	private TaskScheduledExtMapper taskScheduledExtMapper;

    @Resource
    private ProjectService projectService;

    @Resource
    private RoadMapService roadMapService;

    @Resource
    private RoadMapPriceRuleService roadMapPriceRuleService;

    @Resource
    private IdGeneratorService idGeneratorService;

    @Resource
    private TaskParamService taskParamService;

    @Resource
    private TaskPeriodService taskPeriodService;

    @Resource
    private TaskDeviceService taskDeviceService;

    @Resource
    private TaskFixedDeliveryService taskFixedDeliveryService;

    @Resource
    private TaskNotfixedDeliveryService taskNotfixedDeliveryService;

    @Resource
    private TaskAckService taskAckService;

    @Resource
    private TaskFacadeService taskFacadeService;

    @Resource
    private VmsService vmsService;

    @Resource
	private TaskTimelineService taskTimelineService;

    @Resource
	private CustomerInfoService customerInfoService;

    @Resource
	private AuthCommonService authCommonService;

    @Resource
	private TruckTypeService truckTypeService;

    @Resource
	private AdditionalFunctionService additionalFunctionService;

    @Resource
	private FreightFactorService freightFactorService;

    @Resource
	private UserService userService;

    @Resource
	private ProjectDepotService projectDepotService;

    @Resource
	private VmsCommonService vmsCommonService;

    @Resource
	private MessageService messageService;

    @Resource
	private SendTaskServiceV2 sendTaskServiceV2;

    @Resource
	private TaskCalendarService taskCalendarService;

    @Resource
	private MessagePushService messagePushService;

    @Resource
	private TaskCalendarExtMapper taskCalendarExtMapper;

    @Resource
    private WaybillParamService waybillParamService;

    @Resource
	private TaskFixedDeliveryExtMapper taskFixedDeliveryExtMapper;

    @Override
    public Mapper<TaskScheduled, TaskScheduledExample> getMapper() {
        return taskScheduledMapper;
    }

    @Override
    public TaskScheduled withTaskId(Integer taskId) throws BusinessException {
        List<TaskScheduled> taskScheduledList = selectByExample(new TaskScheduledExample().limit(1, 1)
                .createCriteria().andTaskIdEqualTo(taskId).example());
        return taskScheduledList.isEmpty() ? null : taskScheduledList.get(0);
    }

	@Override
    public Integer createTaskScheduled(TaskScheduledVO taskScheduledVO, LoginUser loginUser) throws BusinessException{

        //校验参数
        checkTaskParam(taskScheduledVO, loginUser);
        //校验时间参数
        checkTaskParamDate(taskScheduledVO);
        //插入主表
        Integer taskId = insertTaskScheduled(taskScheduledVO, loginUser);
        //插入关联表
        insertTaskLink(taskId, taskScheduledVO);

        return taskId;
    }

	@Override
	public void updateTaskInvite(InviteRequest request, LoginUser loginUser) throws BusinessException{

		TaskScheduled taskScheduled = selectByPrimaryKey(request.getTaskId());
		Project project = projectService.getProjectV2(request.getProjectId());
		if(taskScheduled == null || project == null){
			throw new BusinessException("taskNotExist", "任务或项目不存在");
		}
		if(!taskScheduled.getCreateUserId().equals(loginUser.getUserId())
			&& !project.getProjectManagerUserId().equals(loginUser.getUserId())){
			throw new BusinessException("noPermission", "仅任务发布人或任务的项目经理可以操作");
		}
		if(TaskEnum.TaskStatus.Inviting.getCode() != taskScheduled.getTaskStatus()){
			throw new BusinessException("statusError", "只有邀请中的任务才能改派任务");
		}

        TaskAckExample example = new TaskAckExample().createCriteria()
            .andTaskIdEqualTo(request.getTaskId())
            .andAckStatusEqualTo(AckStatus.Waiting_Back.getCode()).example();
        List<TaskAck> taskAcks = taskAckService.selectByExample(example);
        if(taskAcks != null && taskAcks.size() != 0){
            //更新待确认的承运商为已失效
            TaskAck updateTask = new TaskAck();
            updateTask.setAckId(taskAcks.get(0).getAckId());
            updateTask.setAckStatus(AckStatus.Lose_Efficacy.getCode());
            taskAckService.updateByPrimaryKeySelective(updateTask);
        }

        taskFacadeService.inviteVendor(request, loginUser);

	}

    @Override
    public boolean haveWaitBack(Integer taskId) {

        TaskAck taskAck = taskAckService.withLastAck(taskId);
        return taskAck != null && taskAck.getAckStatus() == TaskEnum.TaskAckStatus.Waiting_Reply.getCode();
    }

	private List<TaskTimeVO> makeTaskTime(Integer taskId, TaskScheduled taskScheduled, TaskParam taskParam, TaskPeriod taskPeriod, boolean isFirst) {
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
		List<TaskTimeVO> taskTimeVOS = new ArrayList<>();
		for ( ;startDateTime.isBefore(endDateTime) || startDateTime.isEqual(endDateTime);) {
			//是否在配送周期
			if (!deliveryPeriods.contains(startDateTime.getDayOfWeek()+"")) {
				startDateTime = startDateTime.plusDays(1);
				continue;
			}

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

			TaskTimeVO taskTimeVO = new TaskTimeVO();
			taskTimeVO.setStartTime(startTime);
			taskTimeVO.setEndTime(endTime);
			taskTimeVOS.add(taskTimeVO);

            startDateTime = startDateTime.plusDays(1);
			if(isFirst && !taskTimeVOS.isEmpty()){
				break;
			}
		}

		return taskTimeVOS;
	}

	/**
     * 插入任务主表
     * @param taskScheduledVO
     * @param loginUser
     * @return
     */
    private Integer insertTaskScheduled(TaskScheduledVO taskScheduledVO, LoginUser loginUser){

        TaskScheduled taskScheduled = new TaskScheduled();
        BeanUtils.copyProperties(taskScheduledVO, taskScheduled);

        Project project = projectService.getProjectV2(taskScheduledVO.getProjectId());

        String taskNo = idGeneratorService.genTaskScheduledNo(IdGeneratorTable.IdType.RW);
        taskScheduled.setTaskNo(taskNo);
        taskScheduled.setTenantId(loginUser.getTenantId());
        taskScheduled.setRoadMapPriceRuleId(taskScheduledVO.getRoadMapPriceRuleId());
        taskScheduled.setAreaCode(project.getAreaCode());
        taskScheduled.setTaskStatus(TaskEnum.TaskStatus.Waiting_Invite.getCode());
        taskScheduled.setCustomerId(project.getCustomerId());
        taskScheduled.setCreateTime(new Date());
        taskScheduled.setCreateUserId(loginUser.getUserId());
        taskScheduled.setIsFixedDelivery(taskScheduledVO.getFixedDelivery());
		taskScheduled.setLastUpdateTime(new Date());
		taskScheduled.setLastUpdateUserId(loginUser.getUserId());
        insertSelective(taskScheduled);

        return taskScheduled.getTaskId();
    }

    private void insertTaskLink(Integer taskId, TaskScheduledVO taskScheduledVO){

        Project project = projectService.getProjectV2(taskScheduledVO.getProjectId());
        RoadMapPriceRule roadMapPriceRule = roadMapPriceRuleService.getRoadMapPriceModel(taskScheduledVO.getRoadMapPriceRuleId());

        //写入任务参数表
        TaskParam taskParam = new TaskParam();
        taskParam.setTaskId(taskId);
		Integer billPeriod = project.getBillPeriod() == null ? -1 : project.getBillPeriod();
        taskParam.setBillPeriod(billPeriod);
        taskParam.setGoodsType(project.getGoodsType());
        taskParam.setEstimateMileageRange(taskScheduledVO.getEstimateMileageRange());
        taskParam.setEstimateTimeCostRange(taskScheduledVO.getEstimateTimeCostRange());
        taskParam.setFunctionIds(taskScheduledVO.getFunctionIds());
        taskParam.setPricingMethod(roadMapPriceRule.getValuationWay().intValue());
        taskParam.setPricingRule(taskScheduledVO.getProjectFreightRuleJson());
		taskParam.setRequireMark(taskScheduledVO.getRequireMark());
        taskParamService.insertSelective(taskParam);

        //写入任务周期表
        TaskPeriod taskPeriod = new TaskPeriod();
        taskPeriod.setTaskId(taskId);
        taskPeriod.setIsStandardTime(taskScheduledVO.getStandardTime());
        taskPeriod.setDeliveryPeriod(taskScheduledVO.getDeliveryPeriod());
        taskPeriod.setDeliveryPeriodTime(taskScheduledVO.getDeliveryPeriodTime());
        taskPeriodService.insert(taskPeriod);

        //写入任务车型表
        List<TaskDevice> taskDevices = new ArrayList<>();
        for(Integer truckTypeId : taskScheduledVO.getTruckTypeIds()){
            TaskDevice taskDevice = new TaskDevice();
            taskDevice.setTaskId(taskId);
            taskDevice.setTruckTypeId(truckTypeId);
            taskDevices.add(taskDevice);
        }
        taskDeviceService.insertBatch(taskDevices);

        //写入任务固定点表
        if(taskScheduledVO.getFixedDelivery()){
            List<TaskFixedDelivery> taskFixedDeliveries = new ArrayList<>();
            for(TaskFixedDelivery taskFixedDelivery : taskScheduledVO.getTaskFixedDeliveries()){
                taskFixedDelivery.setTaskId(taskId);
                taskFixedDeliveries.add(taskFixedDelivery);
            }
            taskFixedDeliveryService.insertBatch(taskFixedDeliveries);
        }else{
            //写入任务非固定点表
            TaskNotfixedDelivery taskNotfixedDelivery = taskScheduledVO.getTaskNotfixedDelivery();
            taskNotfixedDelivery.setTaskId(taskId);
            taskNotfixedDeliveryService.insertSelective(taskNotfixedDelivery);
        }
    }

    /**
     * 校验普通参数正确性
     * @param taskScheduledVO
     * @param loginUser
     */
    private void checkTaskParam(TaskScheduledVO taskScheduledVO, LoginUser loginUser){

        if(taskScheduledVO == null || loginUser == null){
            throw new BusinessException("param", "参数不能为空");
        }

        //项目校验
        if(taskScheduledVO.getProjectId() == null){
            throw new BusinessException("projectNotNull", "项目不能为空");
        }
        Project project = projectService.getProjectV2(taskScheduledVO.getProjectId());
        if(taskScheduledVO.getProjectId() == null){
            throw new BusinessException("projectNotExist", "项目不存在");
        }
        //判断登录人是否是项目经理或运营专员
        boolean havePermission = projectService.isProjectManagerOrOperator(taskScheduledVO.getProjectId(), loginUser.getUserId());
        if(!havePermission){
            throw new BusinessException("projectNotPermission", "你无权选择该项目");
        }
        if(ProjectEnum.ProjectStatus.PAUSE.getCode() == project.getProjectStatus() ||
            ProjectEnum.ProjectStatus.END.getCode() == project.getProjectStatus()){
            throw new BusinessException("projectStatusError", "只能为项目状态为【未启动】或【运行中】的项目创建任务");
        }

        //线路校验
        if(taskScheduledVO.getRoadMapId() == null){
            throw new BusinessException("roadMapNotNull", "路线不能为空");
        }
        RoadMap roadMap = roadMapService.getRoadMap(taskScheduledVO.getRoadMapId());
        if(roadMap == null){
            throw new BusinessException("roadMapNotExist", "路线不存在");
        }
        if(!project.getProjectId().equals(roadMap.getProjectId())){
            throw new BusinessException("roadMapError", "路线不属于所选项目");
        }

        //计价方式
        if(taskScheduledVO.getRoadMapPriceRuleId() == null){
            throw new BusinessException("pricingMethodNotNull", "计价方式不能为空");
        }
        RoadMapPriceRule roadMapPriceRule = roadMapPriceRuleService.getRoadMapPriceModel(taskScheduledVO.getRoadMapPriceRuleId());
        if(roadMapPriceRule == null){
            throw new BusinessException("roadMapPriceRuleNotNull", "计价方式不能为空");
        }
        if(!roadMap.getRoadMapId().equals(roadMapPriceRule.getRoadMapId())){
            throw new BusinessException("roadMapPriceError", "计价方式不属于所选路线");
        }

        //车型
        if(taskScheduledVO.getTruckTypeIds() == null || taskScheduledVO.getTruckTypeIds().size() == 0){
            throw new BusinessException("truckTypeNotNull", "车型不能为空");
        }

        //配送点
        if(taskScheduledVO.getFixedDelivery()){
            if(taskScheduledVO.getTaskFixedDeliveries() == null || taskScheduledVO.getTaskFixedDeliveries().size() == 0){
                throw new BusinessException("taskFixedDeliverieNotNull", "固定点配送点不能为空");
            }
        }else{
            if(taskScheduledVO.getTaskNotfixedDelivery() == null || taskScheduledVO.getTaskNotfixedDelivery().getMaxStops() == null
                ||taskScheduledVO.getTaskNotfixedDelivery().getMinStops() == null){
                throw new BusinessException("taskNotFixedDeliverieNotNull", "非固定点配送点数量不能为空");
            }
        }

        if(taskScheduledVO.getDepotId() == null){
            throw new BusinessException("depotIdNotNull", "仓库不能为空");
        }

        if(StringUtils.isBlank(taskScheduledVO.getProjectFreightRuleJson())){
			throw new BusinessException("freightRuleJsonNull", "计价规则json不能为空");
		}

    }

    /**
     * 校验时间参数
     * @param taskScheduledVO
     */
    private void checkTaskParamDate(TaskScheduledVO taskScheduledVO){

        Project project = projectService.getProjectV2(taskScheduledVO.getProjectId());
        if(taskScheduledVO.getStartDate() == null || taskScheduledVO.getEndDate() == null){
            throw new BusinessException("dateNotNull", "开始日期和结束日期不能为空");
        }

        //任务开始结束日期
        LocalDate startDate = new LocalDate(taskScheduledVO.getStartDate());
        LocalDate endDate = new LocalDate(taskScheduledVO.getEndDate());
        //项目开始结束日期
        LocalDate projectStartDate = new LocalDate(project.getProjectStartDate());
        LocalDate projectEndDate = new LocalDate(project.getProjectEndDate());
        if(startDate.compareTo(endDate) > 0){
            throw new BusinessException("dateError", "结束时间不能小于开始时间");
        }
        if(startDate.compareTo(projectStartDate) < 0){
            throw new BusinessException("dateError", "任务开始时间不能小于项目开始时间");
        }
        LocalDate maxDate = startDate.plusDays(100);
        if(projectEndDate.compareTo(maxDate) < 0){
            maxDate = projectEndDate;
        }
        if(endDate.compareTo(maxDate) > 0){
            throw new BusinessException("dateError", "任务开始日期 距任务结束日期不可大于 100天且小于等于项目【截至时间】");
        }

        if(StringUtils.isBlank(taskScheduledVO.getDeliveryPeriod()) ||
            StringUtils.isBlank(taskScheduledVO.getDeliveryPeriodTime())){
            throw new BusinessException("deliveryPeriodNotNull", "配送周期和用车时间不能为空");
        }

        String[] deliveryPeriod = taskScheduledVO.getDeliveryPeriod().split(",");
        String[] deliveryPeriodTime = taskScheduledVO.getDeliveryPeriodTime().split(",");

        if(taskScheduledVO.getStandardTime() && deliveryPeriodTime.length > 1){
            throw new BusinessException("deliveryPeriodError", "统一时间：只能选择一个用车时间");
        }
        if(!taskScheduledVO.getStandardTime() && deliveryPeriod.length != deliveryPeriodTime.length){
            throw new BusinessException("deliveryPeriodError", "非统一时间：配送周期和用车时间不匹配");
        }
        //检查是否存在配送任务
		List<TaskTimeVO> timeVOS = checkExistDeliveryDate(taskScheduledVO);
		//检验任务时间和配送周期是否匹配
		checkWeek(taskScheduledVO.getStartDate(), taskScheduledVO.getEndDate(), taskScheduledVO.getDeliveryPeriod());

		Integer conflictHour = getConfig(TaskConstants.TRANSPORT_CONFLICT_VALID_REDUNDANT);
		DateTime conflictDate = new DateTime(timeVOS.get(0).getStartTime()).minusHours(conflictHour);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(conflictDate.toDate());
		calendar.add(Calendar.MINUTE, -30);// 30分钟之前的时间
		Date beforeTime = calendar.getTime();
		if(new Date().after(beforeTime)){
			throw new BusinessException("dateError", "第一次配送用车时间必须早于当前时间"+conflictHour+".5小时");
		}
	}

	@Override
	public List<TaskTimeVO> checkExistDeliveryDate(TaskScheduledVO taskScheduledVO){
		TaskScheduled taskScheduled = new TaskScheduled();
		taskScheduled.setStartDate(taskScheduledVO.getStartDate());
		taskScheduled.setEndDate(taskScheduledVO.getEndDate());
		TaskParam taskParam = new TaskParam();
		taskParam.setEstimateTimeCostRange(taskScheduledVO.getEstimateTimeCostRange());
		TaskPeriod taskPeriod = new TaskPeriod();
		taskPeriod.setDeliveryPeriod(taskScheduledVO.getDeliveryPeriod());
		taskPeriod.setDeliveryPeriodTime(taskScheduledVO.getDeliveryPeriodTime());
		taskPeriod.setIsStandardTime(taskScheduledVO.getStandardTime());
		//构造配送时间
		List<TaskTimeVO> timeVOS = makeTaskTime(0, taskScheduled, taskParam, taskPeriod, true);
		if(timeVOS.isEmpty()){
			throw new BusinessException("noTaskScheduled", "任务开始到结束日期段与选择的配送周期不匹配");
		}
		return timeVOS;
	}

	private void checkWeek(Date startTime, Date endTime, String deliveryPeriod){

    	if(startTime == null || endTime == null || StringUtils.isBlank(deliveryPeriod)){
			throw new BusinessException("paramError", "参数错误");
		}

		List<Integer> weekList = new ArrayList<>();
		List<Integer> deliveryPeriodWeekList = new ArrayList<>();
		String[] deliveryPeriodWeeks = deliveryPeriod.split(",");
		for(String week : deliveryPeriodWeeks){
			deliveryPeriodWeekList.add(Integer.parseInt(week));
		}

		DateTime startDateTime = new DateTime(startTime);
		DateTime endDateTime = new DateTime(endTime);

		for ( ;startDateTime.isBefore(endDateTime) || startDateTime.isEqual(endDateTime);) {
			weekList.add(startDateTime.getDayOfWeek());
			startDateTime = startDateTime.plusDays(1);
		}
		if(weekList.isEmpty() || deliveryPeriodWeekList.isEmpty()
			|| deliveryPeriodWeekList.size() > weekList.size()){
			throw new BusinessException("noTaskScheduled", "任务开始到结束日期段与选择的配送周期不匹配");
		}
		deliveryPeriodWeekList.removeAll(weekList);
		if(deliveryPeriodWeekList.size() != 0){
			throw new BusinessException("noTaskScheduled", "任务开始到结束日期段与选择的配送周期不匹配");
		}
	}

	@Override
	public Page<Task> findTaskByPage(QueryCond<TaskFilter> query, LoginUser loginUser) throws BusinessException {

		if (query == null
			|| query.getPageNo() == null
			|| query.getPageSize() == null
			|| query.getPageSize() > 200) {
			throw new BusinessException("paramError","参数错误");
		}
		if(query.getFilters() == null){
			TaskFilter taskFilter = new TaskFilter();
			query.setFilters(taskFilter);
		}
		query.getFilters().setTenantId(loginUser.getTenantId());

		int total = taskScheduledExtMapper.findTaskCount(query);
		if(total == 0){
			return new Page<>(query.getPageNo(),query.getPageSize(),0,new ArrayList<Task>());
		}

		List<Task> taskScheduledList = taskScheduledExtMapper.findTaskByPage(query);

		for ( Task task : taskScheduledList ) {

			//项目、客户
			Project project = projectService.getProjectV2(task.getProjectId());
			if(project != null){
				task.setProjectName(project.getName());
				task.setProjectAreaCode(project.getAreaCode());
				CustomerInfo customerInfo = customerInfoService.findCusInfoById(project.getCustomerId());
				task.setCustomerName(customerInfo == null ? "" : customerInfo.getCustomerName());
			}
			//路线
			RoadMap roadMap = roadMapService.getRoadMap(task.getRoadMapId());
			task.setRoadMapName(roadMap == null ? "" : roadMap.getName());
			//业务区域
			BusinessArea businessArea = authCommonService.loadBusinessArea(task.getAreaCode(), loginUser);
			task.setAreaCodeName(businessArea == null ? "" : businessArea.getAreaName());

			//任务周期
			TaskPeriod taskPeriod = taskPeriodService.withTaskId(task.getTaskId());
			if (taskPeriod != null) {
				task.setDeliveryPeriod(taskPeriod.getDeliveryPeriod());
				task.setDeliveryPeriodTime(taskPeriod.getDeliveryPeriodTime());
				task.setStandardTime(taskPeriod.getIsStandardTime());
			}
			//用车要求
			TaskParam taskParam = taskParamService.withTaskId(task.getTaskId());
			if(taskParam != null){
				String functionIds = taskParam.getFunctionIds();
				List<AdditionalFunction> additionalFunctionList = additionalFunctionService.getAdditionFunctionByIds(functionIds);
				List<String> functions = new ArrayList<>();
				for ( AdditionalFunction function : additionalFunctionList ) {
					functions.add(function.getFunctionName());
				}
				task.setFunctions(functions);
			}

			//任务车型列表
			List<TaskDevice> taskDevices = taskDeviceService.withTaskId(task.getTaskId());
			List<String> truckTypeNames = new ArrayList<>();
			for(TaskDevice taskDevice : taskDevices){
				truckTypeNames.add(truckTypeService.findTruckTypeNameByTypeId(taskDevice.getTruckTypeId()));
			}
			task.setTruckTypeNames(truckTypeNames);

			if (task.getTaskStatus() != TaskEnum.TaskStatus.Waiting_Invite.getCode()) {
				// 承运商相关信息
				Vendor vendor = vmsService.loadByVenorId(task.getVendorId());
				if (vendor != null) {
					task.setVendorName(vendor.getVendorName());
					task.setVendorRunTypeName(VendorSourceEnum.getSourceDesc(vendor.getVendorSource()));
				}
			}
			task.setNowDate(new Date());
		}

		return new Page<>(query.getPageNo(),query.getPageSize(),total,taskScheduledList);
	}

    @Override
    public TaskLinkWaybill findTaskLinkWaybill(Integer waybillId) throws BusinessException {
        if (waybillId == null || waybillId.equals(0)) return null;

        TaskLinkWaybill taskLinkWaybill = new TaskLinkWaybill();
        WaybillParam waybillParam = waybillParamService.findByWaybillId(waybillId);
        if (waybillParam == null) {
            throw new BusinessException("notFound","WaybillParam不存在：" + waybillId);
        }
        if (waybillParam.getTaskId() == null) {
            return taskLinkWaybill;
        }

        TaskScheduled taskScheduled = withTaskId(waybillParam.getTaskId());
        if (taskScheduled == null) {
            throw new BusinessException("notFound","TaskScheduled不存在：" + waybillParam.getTaskId());
        }

        taskLinkWaybill.setTaskId(taskScheduled.getTaskId());
        taskLinkWaybill.setTaskNo(taskScheduled.getTaskNo());
        taskLinkWaybill.setIsFixedDelivery(taskScheduled.getIsFixedDelivery());

        TaskParam taskParam = taskParamService.withTaskId(taskScheduled.getTaskId());
        if (taskParam == null) {
            throw new BusinessException("notFound","TaskScheduled不存在：" + waybillParam.getTaskId());
        }

        taskLinkWaybill.setEstimateTimeCostRange(taskParam.getEstimateTimeCostRange());
        taskLinkWaybill.setEstimateMileageRange(taskParam.getEstimateMileageRange());

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
                taskLinkWaybill.getFixedDeliveries().add(fixedDeliveryInfo);
            }
        } else {
            TaskNotfixedDelivery taskNotfixedDelivery = taskNotfixedDeliveryService.withTaskId(taskScheduled.getTaskId());
            if (taskNotfixedDelivery != null) {
                NotFixedDeliveryInfo notFixedDeliveryInfo = new NotFixedDeliveryInfo();
                notFixedDeliveryInfo.setMinStops(taskNotfixedDelivery.getMinStops());
                notFixedDeliveryInfo.setMaxStops(taskNotfixedDelivery.getMaxStops());
                notFixedDeliveryInfo.setAddressDetail(taskNotfixedDelivery.getAddressDetail());
                taskLinkWaybill.setNotFixedDeliveryInfo(notFixedDeliveryInfo);
            }
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
            taskLinkWaybill.setDepot(depot);
        }


        return taskLinkWaybill;
    }

    @Override
	public Page<TaskAckPage> findTaskAckPage(QueryCond<TaskAck> queryCond, LoginUser loginUser) throws BusinessException {

    	if(queryCond == null || queryCond.getFilters() == null || queryCond.getFilters().getTaskId() == null){
			throw new BusinessException("paramError","参数错误");
		}

		TaskAckExample example = new TaskAckExample().createCriteria()
			.andTaskIdEqualTo(queryCond.getFilters().getTaskId()).example().orderBy(TaskAck.Column.createTime.desc());
    	example.setStartOffSet(queryCond.getStartOffset());
    	example.setSize(queryCond.getPageSize());
		long taskAckCount = taskAckService.countByExample(example);
		if(taskAckCount == 0){
			return new Page<>(queryCond.getPageNo(), queryCond.getPageSize(), 0, new ArrayList<TaskAckPage>());
		}

		List<TaskAck> taskAcks = taskAckService.selectByExample(example);
		List<TaskAckPage> taskAckPages = new ArrayList<>();
		for(TaskAck taskAck : taskAcks){

			TaskAckPage taskAckPage = new TaskAckPage();
			BeanUtils.copyProperties(taskAck, taskAckPage);

			if(taskAckPage.getVendorId() != null){
				Vendor vendor = vmsService.loadByVenorId(taskAckPage.getVendorId());
				taskAckPage.setVendorName(vendor == null ? "" : vendor.getVendorName());
			}
			if(taskAckPage.getTruckId() != null){
				Truck truck = vmsService.loadByTruckId(taskAckPage.getTruckId());
				taskAckPage.setPlateNumber(truck == null ? "" : truck.getPlateNumber());
			}
			if(taskAckPage.getDriverId() != null){
				Driver driver = vmsService.loadByDriverId(taskAckPage.getDriverId());
				taskAckPage.setDriverName(driver == null ? "" : driver.getName());
			}

			taskAckPages.add(taskAckPage);
		}
		return new Page<>(queryCond.getPageNo(), queryCond.getPageSize(), (int)taskAckCount, taskAckPages);
	}

	@Override
	public TaskAckDetail findTaskAckDetail(Integer taskAckId, LoginUser loginUser) {

    	if(taskAckId == null){
    		return null;
		}
		TaskAck taskAck = taskAckService.selectByPrimaryKey(taskAckId);
    	if(taskAck == null){
			return null;
		}
		TaskAckDetail taskAckDetail = new TaskAckDetail();
    	BeanUtils.copyProperties(taskAck, taskAckDetail);

		Vendor vendor = vmsService.loadByVenorId(taskAck.getVendorId());
		if(vendor != null){
			taskAckDetail.setVendorName(vendor.getVendorName());
			taskAckDetail.setVendorContactPhone(vendor.getContactPhone());
		}
		Truck truck = vmsService.loadByTruckId(taskAck.getTruckId());
		if(truck != null){
			taskAckDetail.setPlateNumber(truck.getPlateNumber());
			taskAckDetail.setTruckTypeName(truckTypeService.findTruckTypeNameBy(truck.getVehicleBoxType(), truck.getVehicleBoxLength()));
		}
		Driver driver = vmsService.loadByDriverId(taskAck.getDriverId());
		if(driver != null){
			taskAckDetail.setDriverName(driver.getName());
			taskAckDetail.setDriverContactPhone(driver.getPhone());
		}

		return taskAckDetail;
	}

	@Override
	public TaskDetail findTaskDetail(Integer taskId, LoginUser loginUser) throws BusinessException {

    	if(taskId == null){
			return null;
		}
		TaskDetail taskDetail = new TaskDetail();
		TaskScheduledDetail taskScheduledDetail = getTaskScheduledDetail(taskId, loginUser);
		List<TaskAckVO> taskAckVOS = getTaskAcks(taskId);
		TaskDelivery taskDelivery = getTaskDelivery(taskScheduledDetail);

		taskDetail.setTaskScheduledDetail(taskScheduledDetail);
		taskDetail.setTaskAcks(taskAckVOS);
		taskDetail.setTaskDelivery(taskDelivery);
		return taskDetail;
	}

	private TaskDelivery getTaskDelivery(TaskScheduledDetail taskScheduledDetail){

		TaskDelivery taskDelivery = new TaskDelivery();

    	if(taskScheduledDetail.getDepotId() == null){
			taskDelivery.setProjectDepot(null);
		}
		ProjectDepot projectDepot = projectDepotService.selectByPrimaryKey(taskScheduledDetail.getDepotId());
		taskDelivery.setProjectDepot(projectDepot);
		taskDelivery.setFixedDelivery(taskScheduledDetail.getFixedDelivery());
		if(taskScheduledDetail.getFixedDelivery()){
			List<TaskFixedDelivery> taskFixedDeliveryList = taskFixedDeliveryService.withTaskId(taskScheduledDetail.getTaskId());
			taskDelivery.setTaskFixedDeliveries(taskFixedDeliveryList);
		}else{
			TaskNotfixedDelivery taskNotfixedDelivery = taskNotfixedDeliveryService.withTaskId(taskScheduledDetail.getTaskId());
			taskDelivery.setTaskNotfixedDelivery(taskNotfixedDelivery);
		}
		return taskDelivery;
	}

	/**
	 * 任务详情-任务邀请
	 * @param taskId
	 * @return
	 */
	private List<TaskAckVO> getTaskAcks(Integer taskId){

		TaskAckExample example = new TaskAckExample().
			orderBy(TaskAck.Column.createTime.desc()).createCriteria()
			.andTaskIdEqualTo(taskId).example();
		List<TaskAck> taskAcks = taskAckService.selectByExample(example);

		List<TaskAckVO> taskAckVOS = new ArrayList<>();
		for(TaskAck taskAck : taskAcks){
			TaskAckVO taskAckVO = new TaskAckVO();
			BeanUtils.copyProperties(taskAck, taskAckVO);
			//承运商
			Vendor vendor = vmsService.loadByVenorId(taskAck.getVendorId());
			if(vendor != null){
				taskAckVO.setVendorId(vendor.getVendorId());
				taskAckVO.setVendorName(vendor.getVendorName());
			}
			//车辆
			Truck truck = vmsService.loadByTruckId(taskAck.getTruckId());
			if(truck != null){
				taskAckVO.setPlateNumber(truck.getPlateNumber());
			}
			//司机
			Driver driver = vmsService.loadByDriverId(taskAck.getDriverId());
			if(driver != null){
				taskAckVO.setDriverName(driver.getName());
			}
			//发布人
			User user = userService.loadUser(taskAck.getCreateUserId());
			if (user != null) {
				taskAckVO.setCreateUserName(user.getName());
				taskAckVO.setCreateUserContactPhone(user.getMobileNumber());
			}
			taskAckVOS.add(taskAckVO);
		}
		return taskAckVOS;
	}

	/**
	 * 任务详情-任务信息
	 * @param taskId
	 * @return
	 */
	private TaskScheduledDetail getTaskScheduledDetail(Integer taskId, LoginUser loginUser) {

		TaskScheduled taskScheduled = this.withTaskId(taskId);
		if (taskScheduled == null) {
			throw new BusinessException("notFound","TaskScheduled不存在：" + taskId);
		}

		TaskScheduledDetail taskDetail = new TaskScheduledDetail();
		BeanUtils.copyProperties(taskScheduled, taskDetail);
		taskDetail.setFixedDelivery(taskScheduled.getIsFixedDelivery());

		Project project = projectService.getProjectV2(taskScheduled.getProjectId());
		if (project != null) {
			taskDetail.setProjectName(project.getName());
			taskDetail.setProjectNo(project.getProjectNo());
		}

		RoadMap roadMap = roadMapService.getRoadMap(taskScheduled.getRoadMapId());
		if (roadMap != null) {
			taskDetail.setRoadMapName(roadMap.getName());
		}
		//任务车型
		TaskDeviceExample example = new TaskDeviceExample().createCriteria()
			.andTaskIdEqualTo(taskId).example();
		List<TaskDevice> taskDevices = taskDeviceService.selectByExample(example);


		List<String> truckTypeNames = new ArrayList<>();
		for(TaskDevice taskDevice : taskDevices){
			String truckTypeName = truckTypeService.findTruckTypeNameByTypeId(taskDevice.getTruckTypeId());
			truckTypeNames.add(truckTypeName);
		}
		taskDetail.setTruckTypeNames(truckTypeNames);

		//承运商
		Vendor vendor = vmsService.loadByVenorId(taskScheduled.getVendorId());
		if(vendor != null){
			taskDetail.setVendorId(vendor.getVendorId());
			taskDetail.setVendorName(vendor.getVendorName());
			taskDetail.setVendorContactPhone(vendor.getContactPhone());
		}

		TaskParam taskParam = taskParamService.withTaskId(taskId);
		if (taskParam == null) {
			throw new BusinessException("notFound","TaskParam不存在：" + taskId);
		}
		taskDetail.setBillPeriod(taskParam.getBillPeriod());
		taskDetail.setGoodsType(taskParam.getGoodsType());
		taskDetail.setEstimateMileageRange(taskParam.getEstimateMileageRange());
		taskDetail.setEstimateTimeCostRange(taskParam.getEstimateTimeCostRange());

		//用车要求
		String functionIds = taskParam.getFunctionIds();
		List<AdditionalFunction> additionalFunctionList = additionalFunctionService.getAdditionFunctionByIds(functionIds);
		List<String> functions = new ArrayList<>();
		for ( AdditionalFunction function : additionalFunctionList ) {
			functions.add(function.getFunctionName());
		}
		taskDetail.setFunctions(functions);

		TaskPeriod taskPeriod = taskPeriodService.withTaskId(taskId);
		if (taskPeriod == null) {
			throw new BusinessException("notFound","TaskPeriod不存在：" + taskId);
		}
		taskDetail.setStandardTime(taskPeriod.getIsStandardTime());
		taskDetail.setDeliveryPeriod(taskPeriod.getDeliveryPeriod());
		taskDetail.setDeliveryPeriodTime(taskPeriod.getDeliveryPeriodTime());
		taskDetail.setPricingRule(pricingRuleView(taskParam.getPricingRule()));
		taskDetail.setStandardTime(taskPeriod.getIsStandardTime());

		//派车有效期
		List<TaskTimeVO> taskTimeVOS = makeTaskTime(taskId, taskScheduled, taskParam, taskPeriod, true);
		if(!taskTimeVOS.isEmpty()){
			DateTime dateTime = new DateTime(taskTimeVOS.get(0).getStartTime());
			dateTime = dateTime.minusHours(2);
			taskDetail.setDispatchVehicleDate(dateTime.toDate());
		}
		return taskDetail;
	}

	//构造计价规则
	private String pricingRuleView(String pricingRule) {
		if (org.apache.commons.lang3.StringUtils.isBlank(pricingRule)) return null;
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
	public Page<CapacityPoolPage> findCapacityPoolPage(QueryCond<CapacityFilter> queryCond,
		LoginUser loginUser) throws BusinessException {

		com.giants.common.tools.Page<CapacityPool> capacityPoolPage = vmsCommonService.searchCommonCapacity(queryCond, loginUser);

		List<CapacityPool> capacityPools = new ArrayList<>(capacityPoolPage.getResults());
		List<CapacityPoolPage> capacityPoolPages = new ArrayList<>();

		for(CapacityPool capacityPool : capacityPools){
			CapacityPoolPage capacity = new CapacityPoolPage();
			BeanUtils.copyProperties(capacityPool, capacity);
			//承运商
			Vendor vendor = vmsService.loadByVenorId(capacityPool.getVendorId());
			if(vendor != null){
				capacity.setVendorName(vendor.getVendorName());
				capacity.setVendorRunTypeName(VendorSourceEnum.getSourceDesc(vendor.getVendorSource()));
			}
			//车辆
			Truck truck = vmsService.loadByTruckId(capacityPool.getTruckId());
			if(truck != null){
				capacity.setPlateNumber(truck.getPlateNumber());
				capacity.setTruckRunTypeName(TruckRunTypeEnum.getDescByCode(truck.getTruckRunType()));
			}
			capacity.setTruckTypeName(truckTypeService.findTruckTypeNameBy(capacityPool.getVehicleBoxType(), capacityPool.getVehicleBoxLength()));
			//业务区域
			BusinessArea businessArea = authCommonService.loadBusinessArea(capacityPool.getAreaCode(), loginUser);
			capacity.setAreaCodeName(businessArea == null ? "" : businessArea.getAreaName());
			//司机
			Driver driver = vmsService.loadByDriverId(capacityPool.getDriverId());
			if(driver != null){
				capacity.setDriverName(driver.getName());
			}
			capacityPoolPages.add(capacity);
		}
		return new Page<>(capacityPoolPage.getPageNo(), capacityPoolPage.getPageSize(), capacityPoolPage.getTotal(), capacityPoolPages);
	}

	@Override
	public void inviteVendorMange(InviteRequest request, LoginUser loginUser) throws BusinessException {

		if (request == null || request.getVendorId() == null
			|| request.getTruckId() == null
			|| request.getDriverId() == null) {
			throw new BusinessException("paramError","参数vendorId或truckId或driverId为空");
		}
		TaskScheduled taskScheduled = this.withTaskId(request.getTaskId());
		TaskParam taskParam = taskParamService.withTaskId(request.getTaskId());
		if (taskScheduled == null) {
			throw new BusinessException("notFound","TaskScheduled不存在：" + request.getTaskId());
		}
		Project project = projectService.getProjectV2(taskScheduled.getProjectId());
		if (project == null) {
			throw new BusinessException("notFound","Project不存在：" + taskScheduled.getProjectId());
		}
		if (taskScheduled.getTaskStatus() != TaskEnum.TaskStatus.Waiting_Invite.getCode()
			&& taskScheduled.getTaskStatus() != TaskEnum.TaskStatus.Inviting.getCode()) {
			throw new BusinessException("taskStatusError","任务状态为待指派运力或邀请中才能发起邀请");
		}

		//检查承运商是不是自营
		Vendor vendor = vmsService.loadByVenorId(request.getVendorId());
		if (vendor == null) {
			throw new BusinessException("notFound","Vendor不存在：" + request.getVendorId());
		}
		boolean isOwenVendor = vendor.getVendorSource() != null && vendor.getVendorSource().equals(VendorSourceEnum.SELF_SUPPORT.getCode());
		//自营承运商邀请时自动生成任务日历
		if(isOwenVendor){
			TaskPeriod taskPeriod = taskPeriodService.withTaskId(request.getTaskId());
			taskFacadeService.makeTaskCalendar(true, true, request.getTaskId(),request.getVendorId(), request.getTruckId(),
				request.getDriverId(), taskScheduled, taskParam, taskPeriod, loginUser);
		}

		//邀请中的任务存在待回复的承运商，修改待回复为已失效
		if(taskScheduled.getTaskStatus() == TaskEnum.TaskStatus.Inviting.getCode()){
			List<TaskAck> taskAcks = taskAckService.selectByExample(new TaskAckExample().createCriteria()
			.andTaskIdEqualTo(request.getTaskId()).andAckStatusEqualTo(TaskEnum.TaskAckStatus.Waiting_Reply.getCode()).example());
			for(TaskAck taskAck : taskAcks){
				taskAck.setAckStatus(TaskEnum.TaskAckStatus.Invalided.getCode());
				taskAckService.updateByPrimaryKeySelective(taskAck);
			}
		}
		TaskAck taskAck = new TaskAck();
		taskAck.setTaskId(request.getTaskId());
		taskAck.setTenantId(loginUser.getTenantId());
		taskAck.setProjectId(request.getProjectId());
		taskAck.setVendorId(request.getVendorId());
		taskAck.setTruckId(request.getTruckId());
		taskAck.setDriverId(request.getDriverId());
		taskAck.setBillPeriod(taskParam.getBillPeriod());
		taskAck.setAckStatus(TaskEnum.TaskAckStatus.Waiting_Reply.getCode());
		taskAck.setCreateTime(new Date());
		taskAck.setCreateUserId(loginUser.getUserId());
		taskAck.setLastUpdateTime(new Date());
		taskAck.setLastUpdateUserId(loginUser.getUserId());
		taskAckService.insertSelective(taskAck);

		//当某一个配送日生成了运单的时候，任务状态要变更为运行中
		if (isOwenVendor) {
			if (!taskScheduled.getTaskStatus().equals(TaskEnum.TaskStatus.Running.getCode())) {
				taskScheduled.setTaskStatus(TaskEnum.TaskStatus.Waiting_Become.getCode());
			}
		} else {
			taskScheduled.setTaskStatus(TaskEnum.TaskStatus.Inviting.getCode());
		}
		taskScheduled.setDriverId(request.getDriverId());
		taskScheduled.setTruckId(request.getTruckId());
		taskScheduled.setVendorId(request.getVendorId());
		updateByPrimaryKeySelective(taskScheduled);

		Map<String,Object> params = new HashMap<>();
		params.put("vendorName", vendor.getVendorName());
		messageService.pushSmsMessage(loginUser,TaskConstants.INVITE_VENDOR_SMS,params,vendor.getContactPhone());

		Map<String,Object> extrasPush = new HashMap<>();
		extrasPush.put("taskId", request.getTaskId());
		messagePushService.pushAppMessage(TaskConstants.INVITE_VENDOR_PUSH, extrasPush, loginUser, vendor.getUserId()+"");
	}

	private void expireTask() {
        List<Integer> taskStatusList = new ArrayList<>();
        taskStatusList.add(TaskEnum.TaskStatus.Waiting_Invite.getCode());
        taskStatusList.add(TaskEnum.TaskStatus.Inviting.getCode());

        TaskScheduledExample example = new TaskScheduledExample().createCriteria()
                .andTaskStatusIn(taskStatusList).example();
        List<TaskScheduled> taskScheduledList = selectByExample(example);

        List<TaskScheduled> updateTaskScheduledList = new ArrayList<>();

        List<TaskAck> updateTaskAckList = new ArrayList<>();

        for (TaskScheduled taskScheduled : taskScheduledList) {
            //任务的第一配送日的用车时间 - 任务有效期冗余
			List<TaskTimeVO> timeVOS = makeTaskTime(taskScheduled.getTaskId(), taskScheduled,
			taskParamService.withTaskId(taskScheduled.getTaskId()), taskPeriodService.withTaskId(taskScheduled.getTaskId()), true);
			if(timeVOS.isEmpty()){
				continue;
			}
			TaskTimeVO timeVO = timeVOS.get(0);
			//任务第1个“配送日”
			DateTime startDateTime = new DateTime(timeVO.getStartTime());
			//任务有效期冗余
			Integer conflictDay = getConfig(TaskConstants.TRANSPORT_CONFLICT_VALID_REDUNDANT);
			startDateTime = startDateTime.minusHours(conflictDay);
            if (startDateTime.isBeforeNow()) {
                //更新任务过期
                taskScheduled.setTaskStatus(TaskEnum.TaskStatus.Expired.getCode());
                updateTaskScheduledList.add(taskScheduled);
                //云之家消息
                messagePushService.expireTask(taskScheduled.getTaskId());
                //更新邀请状态为失效
                List<TaskAck> taskAckList = taskAckService.selectByExample(new TaskAckExample().createCriteria()
                        .andTaskIdEqualTo(taskScheduled.getTaskId())
                        .andAckStatusEqualTo(TaskEnum.TaskAckStatus.Waiting_Reply.getCode()).example());
                if (taskAckList.isEmpty()) continue;
                for ( TaskAck taskAck : taskAckList ) {
                    taskAck.setAckStatus(TaskEnum.TaskAckStatus.Invalided.getCode());
                }
                updateTaskAckList.addAll(taskAckList);
            }
        }
        if (!updateTaskScheduledList.isEmpty()) {
            updateBatchByPrimaryKeySelective(updateTaskScheduledList);
        }
        if (!updateTaskAckList.isEmpty()) {
            taskAckService.updateBatchByPrimaryKeySelective(updateTaskAckList);
        }
    }

    private void finishTask() {
        List<Integer> taskStatusList = new ArrayList<>();
        taskStatusList.add(TaskEnum.TaskStatus.Waiting_Become.getCode());
        taskStatusList.add(TaskEnum.TaskStatus.Running.getCode());

        TaskScheduledExample example = new TaskScheduledExample().createCriteria()
                .andTaskStatusIn(taskStatusList).example();
        List<TaskScheduled> taskScheduledList = selectByExample(example);

        List<TaskScheduled> updateTaskScheduledList = new ArrayList<>();

	    for ( TaskScheduled taskScheduled : taskScheduledList ) {
            DateTime dateTime = new DateTime(DateUtil.dayEndForDate(taskScheduled.getEndDate()));
            if (dateTime.isBeforeNow()) {
                //云之家消息
                messagePushService.finishTask(taskScheduled.getTaskId());
                //任务结束
                taskScheduled.setTaskStatus(TaskEnum.TaskStatus.Finish.getCode());
                updateTaskScheduledList.add(taskScheduled);
            }
        }
	    if (!updateTaskScheduledList.isEmpty()) {
	        updateBatchByPrimaryKeySelective(updateTaskScheduledList);
        }

    }

	@Override
	public void updateTaskStatusTimer() throws BusinessException {
	    expireTask();
	    finishTask();

//        for(TaskScheduled taskScheduled : taskScheduledList){
//
//			Integer taskId = taskScheduled.getTaskId();
//			Integer taskStatus = null;
//			Integer taskAckStatus = null;
//
//
//			//到任务截止日期结束
//			if(taskScheduled.getEndDate().before(new Date())){
//				taskStatus = TaskEnum.TaskStatus.Finish.getCode();
//				taskAckStatus = TaskEnum.TaskAckStatus.Invalided.getCode();
//			}else{//派车有效期未确认承运商
//				List<TaskTimeVO> timeVOS = makeTaskTime(taskId, taskScheduled,
//					taskParamService.withTaskId(taskId), taskPeriodService.withTaskId(taskId), true);
//				if(timeVOS.isEmpty()){
//					continue;
//				}
//				TaskTimeVO timeVO = timeVOS.get(0);
//				//任务第1个“配送日”
//				DateTime startDateTime = new DateTime(timeVO.getStartTime());
//				//任务有效期冗余
//				Integer conflictDay = getConfig(TaskConstants.TRANSPORT_CONFLICT_VALID_REDUNDANT);
//				startDateTime = startDateTime.minusHours(conflictDay);
//				if(startDateTime.isEqualNow() || startDateTime.isBeforeNow()){
//					taskStatus = TaskEnum.TaskStatus.Expired.getCode();
//					taskAckStatus = TaskEnum.TaskAckStatus.Expired.getCode();
//				}
//			}
//			if(taskStatus == null){
//				continue;
//			}
//
//			//更新任务状态为
//			taskScheduled.setTaskStatus(taskStatus);
//			updateByPrimaryKeySelective(taskScheduled);
//			//更新邀请状态
//			List<TaskAck> taskAckList = taskAckService.selectByExample(new TaskAckExample()
//				.createCriteria()
//				.andTaskIdEqualTo(taskId)
//				.andAckStatusEqualTo(TaskEnum.TaskAckStatus.Waiting_Reply.getCode()).example());
//			if(taskAckList.isEmpty()){
//				continue;
//			}
//			for(TaskAck taskAck : taskAckList){
//				taskAck.setAckStatus(taskAckStatus);
//				taskAckService.updateByPrimaryKeySelective(taskAck);
//			}
//
//			//过期时发送通知创建人
//			if(taskStatus == TaskEnum.TaskStatus.Expired.getCode()){
//
//				Project projectV2 = projectService.getProjectV2(taskId);
//				if(projectV2 == null || taskScheduled.getCreateUserId() == null){
//					continue;
//				}
//				User user = userService.loadUser(taskScheduled.getCreateUserId());
//				if(user == null || StringUtils.isBlank(user.getMobileNumber())){
//					continue;
//				}
//				LoginUser loginUser = new LoginUser(taskScheduled.getTenantId(), taskScheduled.getCreateUserId());
//				String content = String.format(TaskConstants.TSAK_EXPIRED_PUSH, projectV2.getName());
//				List<String> phoneList = new ArrayList<>();
//				phoneList.add(user.getMobileNumber());
//				messagePushService.cloudMessagePush(Constants.AUTH_KEY_TGM_MANAGE, taskScheduled.getTaskId().toString(),
//					"无承运商接受任务，任务失效", content, phoneList, loginUser);
//			}
//		}
	}

	@Override
	public void executeTaskCreateWaybillTimer() {

		Integer maxDay = getConfig(TaskConstants.WAYBILL_MAX_CREATE_DAY);

		List<TaskCalendar> taskCalendars = taskCalendarService.selectByExample(new TaskCalendarExample().createCriteria()
		.andWorkStatusEqualTo(TaskEnum.WorkStatus.Running.getCode())
		.andWaybillIdEqualTo(0)
		.andWorkStartTimeGreaterThanOrEqualTo(new Date())
		.andWorkStartTimeLessThanOrEqualTo(new DateTime().plusHours(24*maxDay).toDate())
		.example());

		if(taskCalendars.isEmpty()){
			return;
		}
		for(TaskCalendar taskCalendar : taskCalendars){
			sendTaskServiceV2.send(taskCalendar);
		}

	}

	@Override
	public List<ProjectDepot> findDepotByProjectId(Integer projectId) throws BusinessException {
		if(projectId == null){
			return null;
		}
		return projectDepotService.selectByExample(new ProjectDepotExample().createCriteria()
			.andProjectIdEqualTo(projectId)
			.andIsDeleteEqualTo(false)
			.example());
	}

	@Override
	public int conflictInviteVendor(Integer taskId, Integer truckId, LoginUser loginUser) throws BusinessException {
		if(taskId == null || truckId == null){
			throw new BusinessException("paramsError", "参数错误");
		}

		List<TaskTimeVO> timeVOS = makeTaskTime(taskId, withTaskId(taskId), taskParamService.withTaskId(taskId),
			taskPeriodService.withTaskId(taskId), false);
		if(timeVOS.isEmpty()){
			return 0;
		}
		int conflictNum = 0;//冲突数量
		for(TaskTimeVO taskTimeVO : timeVOS){
			boolean result = isConflict(truckId, taskTimeVO.getStartTime(), taskTimeVO.getEndTime());
			if(result){
				conflictNum++;
			}
		}
		Integer conflictDayPercent = getConfig(TaskConstants.TIME_CONFLICT_DAY_PERCENT);
		//冲突比例不能超过配置
		float conflictPercent = (Float.valueOf(conflictNum+"")/timeVOS.size())*100;
		if(conflictPercent > conflictDayPercent) {
			throw new BusinessException("conflictGreaterThanError",conflictDayPercent.toString());
		} else {
			return conflictNum;
		}
	}

	private boolean isConflict(Integer truckId,Date startTime,Date endTime) {
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

	private Integer getConfig(String configKey){
		List<ConfParamOption> confParamOptions = authCommonService.listOption(configKey);
		if(confParamOptions.isEmpty()){
			throw new BusinessException("configNotExist", "未获取到配置，key:" + configKey);
		}
		String value = confParamOptions.get(0).getOptionValue();
		return Integer.parseInt(value);
	}

	@Override
	public int conflictChangeCapacity(Integer taskId, Date changeDate, Integer driverId, Integer truckId, Integer type,
		LoginUser loginUser) throws BusinessException {
		Integer calendarId = getCalenderId(taskId, changeDate ,type);
		return taskFacadeService.conflictChangeCapacity(calendarId, driverId, truckId, type, loginUser);
	}

	@Override
	public void doChangeCapacity(Integer taskId, Date changeDate, Integer driverId, Integer truckId, Integer type,
		LoginUser loginUser) throws BusinessException {
		Integer calendarId = getCalenderId(taskId, changeDate ,type);
		taskFacadeService.doChangeCapacity(calendarId, driverId, truckId, type, loginUser);
	}

	private Integer getCalenderId(Integer taskId, Date changeDate, Integer type){
		if(taskId == null || changeDate == null || type == null){
			throw new BusinessException("paramError", "参数异常");
		}

		TaskCalendarExample example;
		if(type == 1){
			example = new TaskCalendarExample().limit(1,1).createCriteria()
				.andTaskIdEqualTo(taskId)
				.andWorkStartTimeGreaterThanOrEqualTo(DateUtil.dayStartReturnDate(changeDate))
				.andWorkStartTimeLessThanOrEqualTo(DateUtil.dayEndReturnDate(changeDate)).example();
		}else if(type == 2){
			example = new TaskCalendarExample()
				.limit(1,1)
				.orderBy(TaskCalendar.Column.calendarId.asc())
				.createCriteria()
				.andTaskIdEqualTo(taskId)
				.andWorkStartTimeGreaterThanOrEqualTo(DateUtil.dayStartReturnDate(changeDate)).example();

		}else{
			throw new BusinessException("paramError", "参数异常");
		}
		List<TaskCalendar> taskCalendars = taskCalendarService.selectByExample(example);
		if(taskCalendars.isEmpty()){
			throw new BusinessException("taskCalendarNull", "所选择日期不是配送日，没有配送任务！请重新选择");
		}
		return taskCalendars.get(0).getCalendarId();
	}

	@Override
	public List<TaskFixedDelivery> findDeliveryByFiler(Integer projectId, String linkName, Integer baskPageSize,
		LoginUser loginUser) {

		baskPageSize = defaultBackSize(baskPageSize);
		if(projectId == null){
			return Lists.newArrayList();
		}
		TaskScheduledExample example = new TaskScheduledExample().createCriteria()
			.andProjectIdEqualTo(projectId).example();
		List<TaskScheduled> taskScheduleds = this.selectByExample(example);
		if(taskScheduleds.isEmpty()){
			return Lists.newArrayList();
		}

		List<Integer> taskIds = new ArrayList<>();
		for(TaskScheduled taskScheduled : taskScheduleds){
			taskIds.add(taskScheduled.getTaskId());
		}
		return taskFixedDeliveryExtMapper.groupTaskFixedDelivery(taskIds, linkName, baskPageSize);
	}

	private Integer defaultBackSize(Integer backPageSize){
		return backPageSize == null ? 15
			: (NumberUtils.compare(backPageSize, 200) == 1 ? 200 : backPageSize);
	}

	@Override
	public Integer taskNumByProject(Integer projectId, Integer taskStatus) {

		if(projectId == null || taskStatus == null){
			return  0;
		}

		TaskScheduledExample example = new TaskScheduledExample().createCriteria()
			.andProjectIdEqualTo(projectId).andTaskStatusEqualTo(taskStatus).example();
		return (int)countByExample(example);
	}

	@Override
	public TaskStatusCount taskNumByProject(Integer projectId) {

		if(projectId == null){
			throw new BusinessException("paramError", "参数异常");
		}

		Integer waitingCount = taskNumByProject(projectId, TaskEnum.TaskStatus.Waiting_Invite.getCode());
		Integer invitingCount = taskNumByProject(projectId, TaskEnum.TaskStatus.Inviting.getCode());

		TaskStatusCount taskStatusCount = new TaskStatusCount();
		taskStatusCount.setWaitingInviteCount(waitingCount);
		taskStatusCount.setInvitingCount(invitingCount);
		return taskStatusCount;
	}

	@Override
	public List<GroupTaskCalendar> groupTaskCalendarCount(GroupTaskCalendarFilter filter) {

		if(filter.getProjectId() == null || filter.getStartTime() == null || filter.getEndTime() == null){
			throw new BusinessException("paramError", "参数异常");
		}

		List<GroupTaskCalendar> returnGroupCountList = new ArrayList<>();
		//任务状态
		List<GroupTaskCalendar> workStatusCountList = taskCalendarExtMapper.groupByWorkStatusCount(filter);
		//运单配送状态
		List<GroupTaskCalendar> deliveryStatusCountList = taskCalendarExtMapper.groupByDeliveryStatusCount(filter);

		returnGroupCountList.addAll(getWorkStatusList(workStatusCountList));
		returnGroupCountList.addAll(getDeliveryStatusList(deliveryStatusCountList));
		return returnGroupCountList;
	}

	private List<GroupTaskCalendar> getWorkStatusList(List<GroupTaskCalendar> workStatusCountList) {

		List<GroupTaskCalendar> resultList = new ArrayList<>();

		Integer finishCount = 0;
		Integer notDeliveryCount = 0;//不配送包括【不配送】和【时间冲突】
		for (GroupTaskCalendar groupTaskCalendar : workStatusCountList) {
			if(groupTaskCalendar.getWorkStatus() == TaskEnum.WorkStatus.Finish.getCode()){
				finishCount = groupTaskCalendar.getTaskCalendarCount();
			}else if(groupTaskCalendar.getWorkStatus() == TaskEnum.WorkStatus.Not_delivery.getCode()
				|| groupTaskCalendar.getWorkStatus() == TaskEnum.WorkStatus.Conflict.getCode()){
				notDeliveryCount = notDeliveryCount + groupTaskCalendar.getTaskCalendarCount();
			}
		}
		GroupTaskCalendar notDelivery = new GroupTaskCalendar();
		notDelivery.setWorkStatus(TaskEnum.WorkStatus.Not_delivery.getCode());
		notDelivery.setTaskCalendarCount(notDeliveryCount);

		GroupTaskCalendar finish = new GroupTaskCalendar();
		finish.setWorkStatus(TaskEnum.WorkStatus.Finish.getCode());
		finish.setTaskCalendarCount(finishCount);

		resultList.add(notDelivery);
		resultList.add(finish);
		return resultList;
	}

	private List<GroupTaskCalendar> getDeliveryStatusList(List<GroupTaskCalendar> workStatusCountList) {

		List<GroupTaskCalendar> resultList = new ArrayList<>();

		Integer notSignIn = 0;
		Integer arriveDepot = 0;
		Integer leaveDepot = 0;

		for (GroupTaskCalendar groupTaskCalendar : workStatusCountList) {
			if(groupTaskCalendar.getDeliveryStatus() == TaskEnum.DeliveryStatus.Not_Sign_In.getCode()){
				notSignIn = groupTaskCalendar.getTaskCalendarCount();
			}else if(groupTaskCalendar.getDeliveryStatus() == TaskEnum.DeliveryStatus.Arrive_Depot.getCode()){
				arriveDepot = groupTaskCalendar.getTaskCalendarCount();
			}else if(groupTaskCalendar.getDeliveryStatus() == TaskEnum.DeliveryStatus.Leave_Depot.getCode()){
				leaveDepot = groupTaskCalendar.getTaskCalendarCount();
			}
		}
		GroupTaskCalendar notSignInOb = new GroupTaskCalendar();
		notSignInOb.setDeliveryStatus(TaskEnum.DeliveryStatus.Not_Sign_In.getCode());
		notSignInOb.setTaskCalendarCount(notSignIn);
		GroupTaskCalendar arriveDepotOb = new GroupTaskCalendar();
		arriveDepotOb.setDeliveryStatus(TaskEnum.DeliveryStatus.Arrive_Depot.getCode());
		arriveDepotOb.setTaskCalendarCount(arriveDepot);
		GroupTaskCalendar leaveDepotOb = new GroupTaskCalendar();
		leaveDepotOb.setDeliveryStatus(TaskEnum.DeliveryStatus.Leave_Depot.getCode());
		leaveDepotOb.setTaskCalendarCount(leaveDepot);

		resultList.add(notSignInOb);
		resultList.add(arriveDepotOb);
		resultList.add(leaveDepotOb);
		return resultList;
	}

	@Override
	public List<TaskCalendarByProject> getTaskCalendarBy(GroupTaskCalendarFilter filter) {

		if(filter.getProjectId() == null || filter.getStartTime() == null || filter.getEndTime() == null
			|| filter.getWorkStatus() == null){
			throw new BusinessException("paramError", "参数异常");
		}

		List<TaskCalendarByProject> taskcalendarList = taskCalendarExtMapper.getCalendarBy(filter);
		if(taskcalendarList.isEmpty()){
			return taskcalendarList;
		}

		for(TaskCalendarByProject taskCalendar : taskcalendarList){

			TaskScheduled taskScheduled = this.withTaskId(taskCalendar.getTaskId());
			taskCalendar.setRoadMapId(taskScheduled.getRoadMapId());
			taskCalendar.setTaskStatus(taskScheduled.getTaskStatus());
			RoadMap roadMap = roadMapService.getRoadMap(taskScheduled.getRoadMapId());
			taskCalendar.setRoadMapName(roadMap == null ? "" : roadMap.getName());

            Vendor vendor = vmsCommonService.loadVendorByVendorId(taskCalendar.getVendorId());
            if (vendor != null) {
                taskCalendar.setVendorId(vendor.getVendorId());
                taskCalendar.setVendorName(vendor.getVendorName());
            }

            Truck truck = vmsCommonService.loadTruckByTruckId(taskCalendar.getTruckId());
			taskCalendar.setPlateNumber(truck == null ? "" : truck.getPlateNumber());

			Driver driver = vmsCommonService.loadDriverByDriverId(taskCalendar.getDriverId());
			taskCalendar.setDriverName(driver == null ? "" : driver.getName());
		}

		if(filter.getOrderType() == 1) {
			Collections.sort(taskcalendarList, new Comparator<TaskCalendarByProject>() {
				@Override
				public int compare(TaskCalendarByProject t1, TaskCalendarByProject t2) {

					Integer workStartTimeResult = compareDate(t1.getWorkStartTime(), t2.getWorkStartTime());
					if(workStartTimeResult != 0){
						return workStartTimeResult;
					}
					Integer rodeMapResult = compareInt(t1.getRoadMapId(), t2.getRoadMapId(), true);
					if(rodeMapResult != 0){
						return rodeMapResult;
					}
					Integer vendorIdResult = compareInt(t1.getVendorId(), t2.getVendorId(), false);
					if(vendorIdResult != 0){
						return vendorIdResult;
					}
					return compareInt(t1.getTaskId(), t2.getTaskId(), false);
				}
			});
		}else{
			Collections.sort(taskcalendarList, new Comparator<TaskCalendarByProject>() {
				@Override
				public int compare(TaskCalendarByProject t1, TaskCalendarByProject t2) {

					Integer rodeMapResult = compareInt(t1.getRoadMapId(), t2.getRoadMapId(), true);
					if(rodeMapResult != 0){
						return rodeMapResult;
					}
					Integer workStartTimeResult = compareDate(t1.getWorkStartTime(), t2.getWorkStartTime());
					if(workStartTimeResult != 0){
						return workStartTimeResult;
					}
					Integer vendorIdResult = compareInt(t1.getVendorId(), t2.getVendorId(), false);
					if(vendorIdResult != 0){
						return vendorIdResult;
					}
					return compareInt(t1.getTaskId(), t2.getTaskId(), false);
				}
			});
		}

		return taskcalendarList;
	}

	private Integer compareInt(Integer int1, Integer int2, boolean isAsc){

		if(int1.equals(int2)){
			return 0;
		}
		if(isAsc){
			if(int1 > int2){
				return 1;
			}else{
				return -1;
			}
		}else{
			if(int1 > int2){
				return -1;
			}else{
				return 1;
			}
		}
	}

	private Integer compareDate(Date date1, Date date2){

		if(date1.equals(date2)){
			return 0;
		}
		if(date1.before(date2)){
			return -1;
		}else{
			return 1;
		}
	}
}
