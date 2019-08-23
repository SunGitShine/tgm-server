package com.juma.tgm.customerManager.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.giants.common.collections.CollectionUtils;
import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageQueryCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.customerManager.dao.Task4WaybillReportExtendMapper;
import com.juma.tgm.customerManager.dao.Task4WaybillReportMapper;
import com.juma.tgm.customerManager.domain.Task4Waybill;
import com.juma.tgm.customerManager.domain.Task4WaybillReport;
import com.juma.tgm.customerManager.domain.Task4WaybillReportExample;
import com.juma.tgm.customerManager.domain.TaskWaybillTemplate;
import com.juma.tgm.customerManager.domain.vo.taskWaybill.Task4WaybillReportCountVo;
import com.juma.tgm.customerManager.domain.vo.taskWaybill.Task4WaybillReportDetailVo;
import com.juma.tgm.customerManager.domain.vo.taskWaybill.Task4WaybillReportQueryVo;
import com.juma.tgm.customerManager.domain.vo.taskWaybill.TaskWaybillTemplateEnum;
import com.juma.tgm.customerManager.service.Task4WaybillReportService;
import com.juma.tgm.customerManager.service.Task4WaybillService;
import com.juma.tgm.customerManager.service.TaskWaybillTemplateService;
import com.juma.tgm.project.domain.Project;
import com.juma.tgm.project.domain.RoadMap;
import com.juma.tgm.project.service.ProjectService;
import com.juma.tgm.project.service.RoadMapService;

/**
 * 定时下单业务类
 *
 * @ClassName: Task4WaybillReportServiceImpl
 * @Description:
 * @author: liang
 * @date: 2018-10-08 11:30
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
@Service
public class Task4WaybillReportServiceImpl implements Task4WaybillReportService {

    private static final Logger log = LoggerFactory.getLogger(Task4WaybillReportServiceImpl.class);

    @Resource
    private Task4WaybillReportMapper task4WaybillReportMapper;

    @Resource
    private Task4WaybillReportExtendMapper task4WaybillReportExtendMapper;

    @Resource
    private TaskWaybillTemplateService taskWaybillTemplateService;

    @Resource
    private Task4WaybillService task4WaybillService;

    @Resource
    private ProjectService projectService;

    @Resource
    private RoadMapService roadMapService;

    @Override
    public Page<Task4WaybillReportDetailVo> searchForView(PageQueryCondition<Task4WaybillReportQueryVo> pageQueryCondition) throws BusinessException {
        if (pageQueryCondition == null)
            throw new BusinessException("queryConditionNullError", "errors.paramCanNotNullWithName", "报告查询参数");

        Task4WaybillReportQueryVo filter = pageQueryCondition.getFilters();
        if (filter == null)
            throw new BusinessException("queryFilterNullError", "errors.paramCanNotNullWithName", "报告查询参数");
        if (filter.getTaskId() == null)
            throw new BusinessException("taskIdNullError", "errors.paramCanNotNullWithName", "任务id");

        Task4WaybillReportExample example = new Task4WaybillReportExample();
        if (StringUtils.isNotEmpty(pageQueryCondition.getOrderBy())) {
            example.setOrderByClause(pageQueryCondition.getOrderBy() + " " + pageQueryCondition.getOrderSort());
        }

        example.setSize(pageQueryCondition.getPageSize());
        example.setStartOffSet(pageQueryCondition.getStartOffSet());

        Task4WaybillReportExample.Criteria criteria = example.createCriteria();
        criteria.andTaskIdEqualTo(filter.getTaskId());

        int count = task4WaybillReportMapper.countByExample(example);

        Page<Task4WaybillReportDetailVo> pageData = new Page<>(pageQueryCondition.getPageNo(), pageQueryCondition.getPageSize(), 0, null);

        if (count <= 0) return pageData;

        List<Task4WaybillReport> listData = task4WaybillReportMapper.selectByExample(example);

        if (CollectionUtils.isEmpty(listData)) return pageData;

        List<Task4WaybillReportDetailVo> detailVoList = new ArrayList<>();
        Task4WaybillReportDetailVo detailVo = null;
        for (Task4WaybillReport report : listData) {
            detailVo = new Task4WaybillReportDetailVo();
            detailVo.setTask4WaybillReport(report);

            detailVoList.add(detailVo);
        }

        pageData.setTotal(count);
        pageData.setResults(detailVoList);
        return pageData;
    }

    @Override
    public Page<Task4WaybillReportDetailVo> searchForDetailList(PageQueryCondition<Task4WaybillReportQueryVo> pageQueryCondition) throws BusinessException {
        if (pageQueryCondition == null)
            throw new BusinessException("queryConditionNullError", "errors.paramCanNotNullWithName", "报告查询参数");

        Task4WaybillReportQueryVo filter = pageQueryCondition.getFilters();
        if (filter == null)
            throw new BusinessException("queryFilterNullError", "errors.paramCanNotNullWithName", "报告查询参数");
//        if (filter.getCreateUserId() == null)
//            throw new BusinessException("userIdNullError", "errors.paramCanNotNullWithName", "用户id");

        Task4WaybillReportExample example = new Task4WaybillReportExample();

        if (StringUtils.isNotEmpty(pageQueryCondition.getOrderBy())) {
            example.setOrderByClause(pageQueryCondition.getOrderBy() + " " + pageQueryCondition.getOrderSort());
        }
        example.setSize(pageQueryCondition.getPageSize());
        example.setStartOffSet(pageQueryCondition.getStartOffSet());

        Task4WaybillReportExample.Criteria criteria = example.createCriteria();
        this.buildQueryParam(filter, criteria);

        int count = task4WaybillReportMapper.countByExample(example);

        Page<Task4WaybillReportDetailVo> pageData = new Page<>(pageQueryCondition.getPageNo(), pageQueryCondition.getPageSize(), 0, null);

        if (count <= 0) return pageData;

        List<Task4WaybillReport> listData = task4WaybillReportMapper.selectByExample(example);

        if (CollectionUtils.isEmpty(listData)) return pageData;

        List<Task4WaybillReportDetailVo> detailVoList = new ArrayList<>();
        Task4WaybillReportDetailVo detailVo = null;
        for (Task4WaybillReport report : listData) {
            detailVo = new Task4WaybillReportDetailVo();
            detailVo.setTask4WaybillReport(report);
            this.buildTaskReportDetail(detailVo);

            detailVoList.add(detailVo);
        }

        pageData.setTotal(count);
        pageData.setResults(detailVoList);
        return pageData;
    }

    /**
     * 组装查询参数
     *
     * @param filter
     * @param criteria
     */
    private void buildQueryParam(Task4WaybillReportQueryVo filter, Task4WaybillReportExample.Criteria criteria) {
//        criteria.andCreateUserIdEqualTo(filter.getCreateUserId());
        criteria.andEmployeeIdEqualTo(filter.getEmployeeId());

        if (CollectionUtils.isNotEmpty(filter.getExecuteStatus())) {
            criteria.andTaskStatusIn(filter.getExecuteStatus());
        }

        Date executeDate = filter.getTaskExcuteDate();
        if (executeDate == null) return;

        //开始时间
        Date begin = DateUtils.setMinutes(DateUtils.setHours(executeDate, 0), 0);
        Date end = DateUtils.setMinutes(DateUtils.setHours(executeDate, 23), 59);
        //结束时间
        criteria.andTaskExcuteDateBetween(begin, end);
    }

    /**
     * 组装需要展示的数据
     *
     * @param detailVo
     */
    private void buildTaskReportDetail(Task4WaybillReportDetailVo detailVo) {
        Task4WaybillReport report = detailVo.getTask4WaybillReport();
        //任务
        Task4Waybill task4Waybill = task4WaybillService.get(report.getTaskId());
        if (task4Waybill == null)
            throw new BusinessException("Task4WaybillNotExist", "errors.notExistWithName", "定时下单任务");
        detailVo.setTask4Waybill(task4Waybill);

        //任务模板
        TaskWaybillTemplate template = taskWaybillTemplateService.get(task4Waybill.getTaskWaybillTemplateId());
        if (template == null)
            throw new BusinessException("TaskWaybillTemplateNotExist", "errors.notExistWithName", "定时下单任务模板");
        detailVo.setTaskWaybillTemplate(template);

        //项目
        Project project = projectService.getProject(template.getProjectId());
        if (project == null) throw new BusinessException("ProjectNotExist", "errors.notExistWithName", "项目");
        detailVo.setProject(project);
        //线路
        if (template.getRoadMapId() != null) {
            RoadMap roadMap = roadMapService.getRoadMap(template.getRoadMapId());

            detailVo.setRoadMap(roadMap);
        }

    }


    @Override
    public Task4WaybillReportCountVo overViewCount(PageQueryCondition<Task4WaybillReportQueryVo> pageQueryCondition) throws BusinessException {
        if (pageQueryCondition == null)
            throw new BusinessException("queryConditionNullError", "errors.paramCanNotNullWithName", "报告查询参数");

        Task4WaybillReportQueryVo filter = pageQueryCondition.getFilters();
        if (filter == null)
            throw new BusinessException("queryFilterNullError", "errors.paramCanNotNullWithName", "报告查询参数");

        Task4WaybillReportExample successExample = new Task4WaybillReportExample();
        //成功数量
        Task4WaybillReportExample.Criteria successCriteria = successExample.createCriteria();
        List<Byte> successParam = new ArrayList<>();
        successParam.add(TaskWaybillTemplateEnum.TaskStatusEnum.TASK_STATUS_SUCCESS.getCode());
        filter.setExecuteStatus(successParam);
        this.buildQueryParam(filter, successCriteria);
        int successCount = task4WaybillReportMapper.countByExample(successExample);

        //失败数量
        Task4WaybillReportExample failExample = new Task4WaybillReportExample();
        Task4WaybillReportExample.Criteria failCriteria = failExample.createCriteria();
        List<Byte> failParam = new ArrayList<>();
        failParam.add(TaskWaybillTemplateEnum.TaskStatusEnum.TASK_STATUS_FAIL.getCode());
        failParam.add(TaskWaybillTemplateEnum.TaskStatusEnum.TASK_STATUS_HANDLED.getCode());
        filter.setExecuteStatus(failParam);
        this.buildQueryParam(filter, failCriteria);
        int failCount = task4WaybillReportMapper.countByExample(failExample);

        Task4WaybillReportCountVo countVo = new Task4WaybillReportCountVo();
        countVo.setFailCount(failCount);
        countVo.setSuccessCount(successCount);

        return countVo;
    }


    @Override
    public Page<Task4WaybillReportCountVo> overViewList(PageQueryCondition<Task4WaybillReportQueryVo> pageQueryCondition) throws BusinessException {
        if (pageQueryCondition == null)
            throw new BusinessException("queryConditionNullError", "errors.paramCanNotNullWithName", "报告查询参数");

        Task4WaybillReportQueryVo filter = pageQueryCondition.getFilters();
        if (filter == null)
            throw new BusinessException("queryFilterNullError", "errors.paramCanNotNullWithName", "报告查询参数");

        Page<Task4WaybillReportCountVo> pageData = new Page<>(pageQueryCondition.getPageNo(), pageQueryCondition.getPageSize(), 0);

        int count = task4WaybillReportExtendMapper.countReportOverview(pageQueryCondition);

        if (count <= 0) return pageData;

        List<Task4WaybillReportCountVo> rawData = task4WaybillReportExtendMapper.countReportOverviewList(pageQueryCondition);

        pageData.setTotal(count);
        pageData.setResults(rawData);

        return pageData;
    }

    @Override
    public int addReport(Task4WaybillReport task4WaybillReport) throws BusinessException {
        if (task4WaybillReport == null)
            throw new BusinessException("task4WaybillReportNullError", "errors.paramCanNotNullWithName", "执行结果报告参数");

        if (task4WaybillReport.getTaskStatus() == null)
            throw new BusinessException("taskStatusNullError", "errors.paramCanNotNullWithName", "执行结果参数");

        if (task4WaybillReport.getTaskId() == null)
            throw new BusinessException("taskIdNullError", "errors.paramCanNotNullWithName", "报告所属任务");

        if (task4WaybillReport.getTaskExcuteDate() == null)
            throw new BusinessException("taskExcuteDateNullError", "errors.paramCanNotNullWithName", "任务执行日期");

        if (task4WaybillReport.getTaskResult() == null)
            throw new BusinessException("taskStatusNullError", "errors.paramCanNotNullWithName", "执行详情参数");

        task4WaybillReport.setHasRead(TaskWaybillTemplateEnum.TaskReportReadStatusEnum.report_read_status_none.getCode());

        return task4WaybillReportMapper.insert(task4WaybillReport);
    }


    @Override
    public void modifyReportStatus(Task4WaybillReport task4WaybillReport, LoginUser loginUser) throws BusinessException {
        if (task4WaybillReport == null)
            throw new BusinessException("task4WaybillReportNullError", "errors.paramCanNotNullWithName", "报告详情");

        if (task4WaybillReport.getTaskReportId() == null)
            throw new BusinessException("task4WaybillReportIdNullError", "errors.paramCanNotNullWithName", "报告id");
        if (task4WaybillReport.getTaskStatus() == null)
            throw new BusinessException("task4WaybillReportStatusNullError", "errors.paramCanNotNullWithName", "报告状态");

        task4WaybillReportMapper.updateByPrimaryKeySelective(task4WaybillReport);

    }


    @Override
    public Task4WaybillReport getLastReportByTaskId(Integer taskId) throws BusinessException {
        if (taskId == null) return null;

        Task4WaybillReportExample example = new Task4WaybillReportExample();
        example.setSize(1);
        example.setOrderByClause("task_report_id desc");

        Task4WaybillReportExample.Criteria criteria = example.createCriteria();

        criteria.andTaskIdEqualTo(taskId);
        List<Task4WaybillReport> rawData = task4WaybillReportMapper.selectByExample(example);

        if (CollectionUtils.isEmpty(rawData)) return null;

        return rawData.get(0);
    }

    @Override
    public Task4WaybillReportCountVo findReportOverview(Task4WaybillReportQueryVo queryVo) throws BusinessException {
        if(queryVo == null) return null;
        if(queryVo.getCreateUserId() == null) return null;
        if(queryVo.getTaskExcuteDate() == null) return null;

        Task4WaybillReportCountVo reportCountVo = task4WaybillReportExtendMapper.findReportOverview(queryVo);
        return reportCountVo;
    }


    @Override
    public void modifyReportReadStatus(Integer taskId, LoginEmployee loginEmployee) throws BusinessException {
        Task4WaybillReportExample example = new Task4WaybillReportExample();

        Task4WaybillReportExample.Criteria criteria = example.createCriteria();

        criteria.andEmployeeIdEqualTo(loginEmployee.getEmployeeId());
        Task4WaybillReport report = new Task4WaybillReport();
        report.setHasRead(TaskWaybillTemplateEnum.TaskReportReadStatusEnum.report_read_status_done.getCode());
        task4WaybillReportMapper.updateByExampleSelective(report, example);
    }

    @Override
    public Boolean hasUnreadReport(LoginEmployee loginEmployee) throws BusinessException {
        Task4WaybillReportExample example = new Task4WaybillReportExample();
        Task4WaybillReportExample.Criteria criteria = example.createCriteria();
        criteria.andCreateUserIdEqualTo(loginEmployee.getUserId());
        criteria.andEmployeeIdEqualTo(loginEmployee.getEmployeeId());
        criteria.andHasReadEqualTo(TaskWaybillTemplateEnum.TaskReportReadStatusEnum.report_read_status_none.getCode());

        int count = task4WaybillReportMapper.countByExample(example);
        return count == 0 ? false : true;
    }

    @Override
    public Task4WaybillReport getTask4WaybillReport(Integer reportId) throws BusinessException {
        return task4WaybillReportMapper.selectByPrimaryKey( reportId );
    }


    @Override
    public void delTaskReport(Integer taskId) throws BusinessException {
        if(taskId == null) return ;
        Task4WaybillReportExample example = new Task4WaybillReportExample();
        Task4WaybillReportExample.Criteria criteria = example.createCriteria();
        criteria.andTaskIdEqualTo(taskId);

        task4WaybillReportMapper.deleteByExample(example);

    }
}
