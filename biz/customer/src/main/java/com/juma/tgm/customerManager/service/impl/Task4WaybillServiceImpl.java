package com.juma.tgm.customerManager.service.impl;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.giants.common.tools.PageQueryCondition;
import com.juma.auth.employee.domain.Employee;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.employee.service.EmployeeService;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.customerManager.dao.Task4WaybillExtendMapper;
import com.juma.tgm.customerManager.dao.Task4WaybillMapper;
import com.juma.tgm.customerManager.dao.TaskWaybillTemplateMapper;
import com.juma.tgm.customerManager.domain.*;
import com.juma.tgm.customerManager.domain.vo.taskWaybill.Task4WaybillListCountVo;
import com.juma.tgm.customerManager.domain.vo.taskWaybill.Task4WaybillListVo;
import com.juma.tgm.customerManager.domain.vo.taskWaybill.Task4WaybillQueryVo;
import com.juma.tgm.customerManager.domain.vo.taskWaybill.Task4WaybillRelationVo;
import com.juma.tgm.customerManager.domain.vo.taskWaybill.TaskWaybillTemplateEnum;
import com.juma.tgm.customerManager.service.Task4WaybillReportService;
import com.juma.tgm.customerManager.service.Task4WaybillService;
import com.juma.tgm.customerManager.service.TaskWaybillTemplateService;
import com.juma.tgm.driver.domain.Driver;
import com.juma.tgm.driver.service.DriverService;
import com.juma.tgm.project.domain.Project;
import com.juma.tgm.project.service.ProjectMemberService;
import com.juma.tgm.project.service.ProjectService;
import com.juma.tgm.tools.service.VmsCommonService;
import com.juma.tgm.truck.domain.Truck;
import com.juma.tgm.truck.service.TruckService;
import com.juma.vms.transport.domain.CapacityPool;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @ClassName: Task4WaybillServiceImpl
 * @Description:
 * @author: liang
 * @date: 2018-09-30 11:36
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
@Service
public class Task4WaybillServiceImpl implements Task4WaybillService {
    
    private static final Logger log = LoggerFactory.getLogger(Task4WaybillServiceImpl.class);

    @Resource
    private Task4WaybillMapper task4WaybillMapper;

    @Resource
    private Task4WaybillExtendMapper task4WaybillExtendMapper;

    @Resource
    private ProjectService projectService;

    @Resource
    private TaskWaybillTemplateService taskWaybillTemplateService;

    @Resource
    private TaskWaybillTemplateMapper taskWaybillTemplateMapper;

    @Resource
    private Task4WaybillReportService task4WaybillReportService;

    @Resource
    private TruckService truckService;

    @Resource
    private DriverService driverService;
    
    @Resource
    private EmployeeService employeeService;
    
    @Resource
    private ApplicationEventPublisher publisher;

    @Resource
    private ProjectMemberService projectMemberService;

    @Resource
    private VmsCommonService vmsCommonService;

    @Override
    public int addTask4Waybill(Task4Waybill task4Waybill, LoginEmployee loginEmployee) throws BusinessException {
        return 0;
    }

    @Override
    public void delTask4Waybill(Integer taskId) throws BusinessException {
        if (taskId == null) return;

        task4WaybillMapper.deleteByPrimaryKey(taskId);
    }

    @Override
    public void updateTask4Waybill(Task4Waybill task4Waybill, LoginEmployee loginEmployee) throws BusinessException {
        if (task4Waybill == null)
            throw new BusinessException("task4WaybillNullError", "errors.paramCanNotNullWithName", "任务参数");

        if (task4Waybill.getTaskId() == null)
            throw new BusinessException("task4WaybillIdNullError", "errors.paramCanNotNullWithName", "任务id");

        if (task4Waybill.getTaskWaybillTemplateId() == null)
            throw new BusinessException("templateIdNullError", "errors.paramCanNotNullWithName", "任务模板");

        task4Waybill.setLastUpdateUserId(loginEmployee.getUserId());
        task4Waybill.setLastUpdateTime(new Date());
        task4WaybillMapper.updateByPrimaryKeySelective(task4Waybill);

    }

    @Override
    public int addTask4WaybillReport(Task4WaybillReport task4WaybillReport, LoginEmployee loginEmployee) throws BusinessException {
        return 0;
    }

    @Override
    public Page<Task4WaybillReport> findSimpleReportList(PageCondition pageCondition) throws BusinessException {
        return null;
    }

    @Override
    public Task4Waybill get(Integer task4WaybillId) throws BusinessException {
        if (task4WaybillId == null)
            throw new BusinessException("task4WaybillIdNullError", "errors.paramCanNotNullWithName", "任务id");

        Task4Waybill task4Waybill = task4WaybillMapper.selectByPrimaryKey(task4WaybillId);

        return task4Waybill;
    }


    @Override
    public Page<Task4WaybillListVo> searchTask(PageQueryCondition<Task4WaybillQueryVo> queryCondition, LoginEmployee loginEmployee) throws BusinessException {
        if (queryCondition == null)
            throw new BusinessException("searchTaskConditionNullError", "errors.paramCanNotNullWithName", "查询参数");

//        if (queryCondition.getFilters().getEmployeeId() == null)
//            throw new BusinessException("searchTaskConditionNullError", "errors.paramCanNotNullWithName", "所属用户");

        List<Integer> projectIdList = projectMemberService.findProjectIdsByUserId(queryCondition.getFilters().getUserId(), loginEmployee.getTenantId());
        queryCondition.getFilters().setProjectIds(projectIdList);

        Page<Task4WaybillListVo> pageData = new Page<>(queryCondition.getPageNo(), queryCondition.getPageSize(), 0);
        if(projectIdList.isEmpty()){
            return pageData;
        }

        int count = task4WaybillExtendMapper.searchCount(queryCondition);
        if (count <= 0) return pageData;
        List<Task4WaybillRelationVo> rawData = task4WaybillExtendMapper.search(queryCondition);

        List<Task4WaybillListVo> listData = new ArrayList<>();
        Task4WaybillListVo task4WaybillListVo = null;
        for (Task4WaybillRelationVo relationVo : rawData) {
            task4WaybillListVo = this.buildTask4WaybillListVo(relationVo);
            listData.add(task4WaybillListVo);
        }

        pageData.setTotal(count);
        pageData.setResults(listData);

        return pageData;
    }


    private Task4WaybillListVo buildTask4WaybillListVo(Task4WaybillRelationVo relationVo) {
        Task4WaybillListVo vo = new Task4WaybillListVo();
        //项目
        if (relationVo.getProjectId() != null) {
            Project project = projectService.getProject(relationVo.getProjectId());
            vo.setProject(project);
        }

        //任务模板
        TaskWaybillTemplate template = taskWaybillTemplateService.get(relationVo.getTaskWaybillTemplateId());
        vo.setTaskWaybillTemplate(template);
        //任务
        Task4Waybill task4Waybill = this.get(relationVo.getTaskId());
        vo.setTask4Waybill(task4Waybill);

        // 获取租户
        if (null != task4Waybill.getEmployeeId()) {
            Employee employee = employeeService.loadEmployee(task4Waybill.getEmployeeId());
            if (null != employee) {
                LoginUser loginUser = new LoginUser();
                loginUser.setTenantId(employee.getTenantId());
                //司机
                vo.setDrivers(this.getDriverList(template.getTaskWaybillTemplateId(), loginUser));
            }

        }

        //最后一次执行结果
        Task4WaybillReport report = task4WaybillReportService.getLastReportByTaskId(relationVo.getTaskId());
        vo.setLastTask4WaybillReport(report);

        return vo;

    }

    //司机信息
    private List<Driver> getDriverList(Integer templateId, LoginUser loginUser) {
        List<TaskWaybillTemplateTruckBinding> truckBindingList = taskWaybillTemplateService.getTruckBindingByTemplateId(templateId);

        if (CollectionUtils.isEmpty(truckBindingList)) return null;

        List<Driver> drivers = new ArrayList<>();

        for (TaskWaybillTemplateTruckBinding truckBinding : truckBindingList) {
            Truck truck = truckService.getTruck(truckBinding.getTruckId());

            if (null == truck) {
                continue;
            }

            CapacityPool capacityPool = vmsCommonService.loadCapacityByTruckId(truck.getTruckId(), loginUser);
            if (null != capacityPool && null != capacityPool.getStatus() && capacityPool.getStatus()) {
                Driver driver = driverService.getDriver(capacityPool.getDriverId());
                drivers.add(driver);
            }
        }

        return drivers;

    }


    @Override
    public Task4Waybill getByTemplateId(Integer taskTemplateId) throws BusinessException {
        if (taskTemplateId == null)
            throw new BusinessException("taskTemplateIdNullError", "errors.paramCanNotNullWithName", "任务模板id");

        Task4WaybillExample example = new Task4WaybillExample();
        example.setSize(1);
        Task4WaybillExample.Criteria criteria = example.createCriteria();
        criteria.andTaskWaybillTemplateIdEqualTo(taskTemplateId);

        List<Task4Waybill> rawData = task4WaybillMapper.selectByExample(example);

        if (CollectionUtils.isEmpty(rawData)) return null;

        return rawData.get(0);

    }


    @Override
    public void delByTaskTemplateId(Integer taskTemplateId) throws BusinessException {
        if (taskTemplateId == null) return;

        Task4WaybillExample task4WaybillExample = new Task4WaybillExample();
        Task4WaybillExample.Criteria task4WaybillCriteria = task4WaybillExample.createCriteria();
        task4WaybillCriteria.andTaskWaybillTemplateIdEqualTo(taskTemplateId);
        task4WaybillMapper.deleteByExample(task4WaybillExample);
    }


    @Override
    public int countTaskByProject(Integer projectId) throws BusinessException {
        if (projectId == null) return 0;

        //项目下的任务模板
        TaskWaybillTemplateExample example = new TaskWaybillTemplateExample();
        TaskWaybillTemplateExample.Criteria criteria = example.createCriteria();

        criteria.andProjectIdEqualTo(projectId);
        List<TaskWaybillTemplate> templates = taskWaybillTemplateMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(templates)) return 0;

        List<Integer> templateIds = new ArrayList<>();
        for (TaskWaybillTemplate template : templates) {
            templateIds.add(template.getTaskWaybillTemplateId());
        }

        Task4WaybillExample task4WaybillExample = new Task4WaybillExample();
        Task4WaybillExample.Criteria task4WaybillCriteria = task4WaybillExample.createCriteria();
//        task4WaybillCriteria.andIsCloseEqualTo(TaskWaybillTemplateEnum.AutoCreateBillEnum.AUTO_CREATE.getCode());
        task4WaybillCriteria.andTaskWaybillTemplateIdIn(templateIds);

        return task4WaybillMapper.countByExample(task4WaybillExample);
    }


    @Override
    public Task4WaybillListCountVo taskCount(PageQueryCondition<Task4WaybillQueryVo> queryCondition, LoginEmployee loginEmployee) throws BusinessException {
		//可见的项目id
    	List<Integer> projectIds = projectMemberService.findProjectIdsByUserId(loginEmployee.getUserId(), loginEmployee.getTenantId());

		Task4WaybillListCountVo countVo = new Task4WaybillListCountVo();//未读报告
		boolean readStatus = task4WaybillReportService.hasUnreadReport(loginEmployee);
		countVo.setUnreadReport(readStatus);

		if(projectIds.isEmpty()){
			countVo.setTodayCount(0);
			countVo.setTotal(0);
			return countVo;
		}
		queryCondition.getFilters().setProjectIds(projectIds);
        //所有任务数量
        queryCondition.getFilters().setTodayTask(false);
        int total = task4WaybillExtendMapper.searchCount(queryCondition);
        //今日任务数量

        queryCondition.getFilters().setTodayTask(true);
        int todayCount = task4WaybillExtendMapper.searchCount(queryCondition);

        countVo.setTodayCount(todayCount);
        countVo.setTotal(total);

        return countVo;
    }

    @Override
    public void addRedisCache() throws BusinessException {
        Task4WaybillExample example = new Task4WaybillExample();
        List<Task4Waybill> rows  = task4WaybillMapper.selectByExample(example);
        for(Task4Waybill task : rows) {
            Employee employee = null;
            try {
                employee = employeeService.loadEmployee(task.getEmployeeId());
                if(employee != null && employee.getTenantId() == 19) {
                    publisher.publishEvent(task);
                }
            } catch (Exception e) {
                log.error(e.getMessage(),e);
            }
        }
    }

    @Override
    public List<Task4Waybill> findCanExecutableTask() {

        //查询自动下单且满足执行日期的定时任务
        Task4WaybillExample example = new Task4WaybillExample();
        Task4WaybillExample.Criteria criteria = example.createCriteria();
        criteria.andIsCloseEqualTo(TaskWaybillTemplateEnum.AutoCreateBillEnum.AUTO_CREATE.getCode());
        criteria.andTaskStartDateLessThanOrEqualTo(new Date());
        criteria.andTaskEndDateGreaterThanOrEqualTo(new Date());

        List<Task4Waybill> task4Waybills = task4WaybillMapper.selectByExample(example);

        Iterator<Task4Waybill> iterator = task4Waybills.iterator();
        while (iterator.hasNext()){

            Task4Waybill task4Waybill = iterator.next();
            boolean executable = judgeExecutable(task4Waybill);
            if(!executable){
                iterator.remove();
            }
        }
        return task4Waybills;
    }

    private boolean judgeExecutable(Task4Waybill task4Waybill) {
        //是否开启定时任务
        //是否在时间范围内

        //检查是否今天下单
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        int dayCode = calendar.get(Calendar.DAY_OF_WEEK);
        String day = TaskWaybillTemplateEnum.dayOfWeek.get(dayCode);

        if (StringUtils.isEmpty(task4Waybill.getTaskWeekDays())) return false;

        //用车日期
        Date deliveryDate = calendar.getTime();
        Date startDate = task4Waybill.getTaskStartDate();//用车时间

        Date endDate = task4Waybill.getTaskEndDate();//用车时间

        //用车日期应该在范围内
        //还没到自动下单日期
        if (DateUtils.truncatedCompareTo(deliveryDate, startDate, Calendar.DATE) < 0) return false;
        //超过了自动下单日期
        if (DateUtils.truncatedCompareTo(deliveryDate, endDate, Calendar.DATE) > 0) return false;

        //包含明天
        String[] availableDays = StringUtils.split(task4Waybill.getTaskWeekDays(), ",");
        Arrays.sort(availableDays);
        if (Arrays.binarySearch(availableDays, day) >= 0) return true;

        return false;
    }
}
