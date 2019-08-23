package com.juma.tgm.task.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.capacity.domian.vo.CapacityFilter;
import com.juma.tgm.common.DateUtil;
import com.juma.tgm.common.query.QueryCond;
import com.juma.tgm.common.vo.Page;
import com.juma.tgm.task.domain.TaskFixedDelivery;
import com.juma.tgm.task.dto.gateway.GroupTaskCalendarFilter;
import com.juma.tgm.task.dto.manage.TaskFilter;
import com.juma.tgm.task.service.ProjectStatusChangeService;
import com.juma.tgm.task.service.TaskFacadeService;
import com.juma.tgm.task.service.TaskParamService;
import com.juma.tgm.task.service.TaskPeriodService;
import com.juma.tgm.task.service.TaskScheduledService;
import com.juma.tgm.task.vo.gateway.InviteRequest;
import com.juma.tgm.task.vo.manage.Task;
import testng.BaseTestNGTest;

/**
 * @description: ${description}
 *
 * @author: xieqiang
 *
 * @create: 2019-07-10 09:40
 **/
public class TaskScheduledServiceTest extends BaseTestNGTest {

	@Autowired
	private TaskScheduledService taskScheduledService;

	@Autowired
	private TaskFacadeService taskFacadeService;

	@Autowired
	private TaskParamService taskParamService;

	@Autowired
	private TaskPeriodService taskPeriodService;

	@Autowired
	private ProjectStatusChangeService projectStatusChangeService;

	@Test
	public void findPageTest(){
		QueryCond<TaskFilter> queryCond = new QueryCond<>();
		queryCond.setPageNo(1);
		queryCond.setPageSize(10);
		List<String> areaCodeList = new ArrayList<>();
		areaCodeList.add("00");
		TaskFilter taskFilter = new TaskFilter();
		taskFilter.setAreaCodeList(areaCodeList);
		queryCond.setFilters(taskFilter);
		Page<Task> taskPage = taskScheduledService.findTaskByPage(queryCond, new LoginUser(19, null));
		System.out.println(JSON.toJSONString(taskPage));
	}

	@Test
	public void updateToVendorReceive(){
		taskFacadeService.updateToVendorReceive(41, 9665, new LoginUser(19, 1));
	}

	@Test
	public void changeVendor(){

	//{taskId:10,vendorId:9665,driverId:2,truckId:2,billPeriod:15,billPeriodReason:"原因"}
		InviteRequest request = new InviteRequest();
		request.setTaskId(10);
		request.setVendorId(9665);
		request.setDriverId(2);
		request.setTruckId(2);
		request.setBillPeriod(15);
		request.setBillPeriodReason("123");
		taskFacadeService.changeVendor(request,new LoginUser(16, 11600));
	}

	@Test
	public void makeTaskTime(){

		Integer taskId = 27;
//		List<TaskTimeVO> timeVOS = taskScheduledService.makeTaskTime(taskId, taskScheduledService.withTaskId(taskId), taskParamService.withTaskId(taskId), taskPeriodService.withTaskId(taskId), true);
//		System.out.println(JSON.toJSONString(timeVOS));
	}

	@Test
	public void inviteVendor(){

		InviteRequest request = new InviteRequest();
		request.setTruckId(6);
		request.setTaskId(35);
		request.setVendorId(10171);
		request.setDriverId(9);
		request.setBillPeriod(15);
		request.setBillPeriodReason("2");
		taskFacadeService.inviteVendor(request, new LoginUser(19, 11600));
	}

	@Test
	public void timer(){
		taskScheduledService.executeTaskCreateWaybillTimer();
	}

	@Test
	public void conflict(){
		taskScheduledService.conflictInviteVendor(42, 284, new LoginUser(19, 11600));
	}

	@Test
	public void findDetail(){
		System.out.println(JSON.toJSONString(taskScheduledService.findTaskDetail(47, new LoginUser(19, 11600))));
	}

	@Test
	public void projectTest(){
		projectStatusChangeService.finish(8386);
	}

	@Test
	public void findCapacityPoolPage(){

		QueryCond<CapacityFilter> queryCond = new QueryCond<>();
		CapacityFilter capacityFilter = new CapacityFilter();
		capacityFilter.setTruckTypeId(1);
		queryCond.setFilters(capacityFilter);
		queryCond.setPageNo(1);
		queryCond.setPageSize(15);
		System.out.println(JSON.toJSONString(taskScheduledService.findCapacityPoolPage(queryCond, new LoginUser(19, 11600))));
	}

	@Test
	public void groupTaskCalendarCountTest(){

		GroupTaskCalendarFilter filter = new GroupTaskCalendarFilter();
		filter.setProjectId(8552);
		filter.setStartTime(DateUtil.dayStartReturnDate(DateUtil.parse("2019-08-01","yyyy-MM-dd HH:mm:ss")));
		filter.setEndTime(DateUtil.dayEndReturnDate(DateUtil.parse("2019-08-01","yyyy-MM-dd HH:mm:ss")));
		System.out.println(JSON.toJSONString(taskScheduledService.groupTaskCalendarCount(filter)));
	}

	@Test
	public void updateTaskStatusTimerTest(){
		taskScheduledService.updateTaskStatusTimer();
	}

	@Test
	public void getTaskCalendarByTest(){
		GroupTaskCalendarFilter filter = new GroupTaskCalendarFilter();
		filter.setProjectId(8692);
		filter.setStartTime(DateUtil.parse("2019-08-19 00:00:00.00", DateUtil.YYYYMMDDHHMMSS));
		filter.setEndTime(DateUtil.parse("2019-08-19 23:59:59.00", DateUtil.YYYYMMDDHHMMSS));
		filter.setWorkStatus(2);
		taskScheduledService.getTaskCalendarBy(filter);
	}

	@Test
	public void findDeliveryByFilerTest(){

		List<TaskFixedDelivery> deliveryList = taskScheduledService.findDeliveryByFiler(8730, "罗", 15, new LoginUser(19, 1));
		System.out.println(JSON.toJSONString(deliveryList));
	}

	@Test
	public void endTaskTest(){
		taskFacadeService.endTask(434, new LoginEmployee());
	}

	public static void main(String[] args) {
		List list1 =new ArrayList();
		list1.add(1);
		list1.add(2);
		list1.add(3);

		List list2 =new ArrayList();
		list2.add(2);

		list2.removeAll(list1);
		System.out.println(list2);
	}
}
