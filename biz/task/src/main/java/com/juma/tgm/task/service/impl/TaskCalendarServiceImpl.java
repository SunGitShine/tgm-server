package com.juma.tgm.task.service.impl;

import javax.annotation.Resource;

import com.google.common.collect.Lists;
import com.juma.tgm.task.dao.ext.TaskCalendarExtMapper;
import com.juma.tgm.task.domain.TaskScheduled;
import com.juma.tgm.task.domain.ext.GroupTaskWorkStatus;
import com.juma.tgm.task.enums.TaskEnum;
import com.juma.tgm.task.service.TaskScheduledService;
import com.juma.tgm.task.service.TaskTimelineService;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.mybatis.generator.my.mapper.Mapper;
import org.mybatis.generator.my.service.AbstractMybatisService;
import org.springframework.stereotype.Service;

import com.juma.tgm.task.dao.TaskCalendarMapper;
import com.juma.tgm.task.domain.TaskCalendar;
import com.juma.tgm.task.domain.TaskCalendarExample;
import com.juma.tgm.task.service.TaskCalendarService;

import java.util.Date;
import java.util.List;

@Service
public class TaskCalendarServiceImpl extends AbstractMybatisService<TaskCalendar, TaskCalendarExample> implements TaskCalendarService {

    @Resource
    private TaskCalendarMapper taskCalendarMapper;

    @Resource
    private TaskCalendarExtMapper taskCalendarExtMapper;

    @Resource
    private TaskScheduledService taskScheduledService;

    @Resource
    private TaskTimelineService taskTimelineService;
    
    @Override
    public Mapper<TaskCalendar, TaskCalendarExample> getMapper() {
        return taskCalendarMapper;
    }

    @Override
    public TaskCalendar withTaskId(Integer taskId, List<Integer> workStatusList, Date startDate) {
        if (taskId == null || workStatusList == null || startDate == null || workStatusList.isEmpty()) return null;
        List<TaskCalendar> taskCalendarList = selectByExample(new TaskCalendarExample()
                .limit(1,1).createCriteria()
                .andTaskIdEqualTo(taskId)
                .andWorkStatusIn(workStatusList)
                .andWorkStartTimeGreaterThanOrEqualTo(startDate)
                .example());
        return taskCalendarList.isEmpty() ? null : taskCalendarList.get(0);
    }

    @Override
    public void updateByWaybillId(Integer waybillId) {
        List<TaskCalendar> taskCalendarList = selectByExample(new TaskCalendarExample().limit(1, 1).createCriteria().andWaybillIdEqualTo(waybillId).example());
        if (taskCalendarList.isEmpty()) return;
        taskCalendarList.get(0).setWorkStatus(TaskEnum.WorkStatus.Finish.getCode());
        updateByPrimaryKeySelective(taskCalendarList.get(0));

        TaskScheduled taskScheduled = taskScheduledService.withTaskId(taskCalendarList.get(0).getTaskId());
        if (taskScheduled == null) return;
        //任务结束时间  当前配送日
        LocalDate localDate = new DateTime(taskCalendarList.get(0).getWorkEndTime()).toLocalDate();
        LocalDate localDate1 = new DateTime(taskScheduled.getEndDate()).toLocalDate();
        if (localDate.equals(localDate1)) {
            taskScheduled.setTaskStatus(TaskEnum.TaskStatus.Finish.getCode());
            taskScheduledService.updateByPrimaryKeySelective(taskScheduled);
        }
    }

    @Override
    public void updateDeliveryStatus(Integer waybillId, TaskEnum.DeliveryStatus deliveryStatus) {
        if (waybillId == null || deliveryStatus == null) return;
        List<TaskCalendar> taskCalendarList = selectByExample(new TaskCalendarExample().createCriteria().andWaybillIdEqualTo(waybillId).example());
        if (taskCalendarList.isEmpty()) return;
        for (TaskCalendar taskCalendar : taskCalendarList ) {
            taskCalendar.setDeliveryStatus(deliveryStatus.getCode());
        }
        updateBatchByPrimaryKeySelective(taskCalendarList);
    }

    @Override
    public List<TaskCalendar> withTaskId(Integer taskId, Date startDate, Date endDate) {
        if (taskId == null || startDate == null || endDate == null) return Lists.newArrayList();
        List<TaskCalendar> taskCalendarList = selectByExample(new TaskCalendarExample().createCriteria()
                .andTaskIdEqualTo(taskId)
                .andWorkStartTimeGreaterThanOrEqualTo(startDate)
                .andWorkStartTimeLessThanOrEqualTo(endDate)
                .example());
        return taskCalendarList;
    }

    @Override
    public void createWaybillFail(Integer calendarId,Integer timelineId, String failMsg) {

        TaskCalendar taskCalendar = new TaskCalendar();
        taskCalendar.setCalendarId(calendarId);
        taskCalendar.setTimelineId(0);
        taskCalendar.setWorkStatus(TaskEnum.WorkStatus.Not_delivery.getCode());
        taskCalendar.setFailureReason(failMsg);
        updateByPrimaryKeySelective(taskCalendar);

        taskTimelineService.deleteByPrimaryKey(timelineId);
    }

    @Override
    public TaskCalendar withCalendarId(Integer calendarId) {
        if (calendarId == null) return null;
        List<TaskCalendar> taskCalendarList = selectByExample(new TaskCalendarExample()
                .limit(1,1)
                .createCriteria()
                .andCalendarIdEqualTo(calendarId).example());
        return taskCalendarList.isEmpty() ? null : taskCalendarList.get(0);
    }

    @Override
    public List<GroupTaskWorkStatus> groupByTaskWorkStatus(Integer taskId) {
        return taskCalendarExtMapper.groupByTaskWorkStatus(taskId);
    }

    public static void main(String[] args) {
        System.out.println(new LocalDate(new Date()));
    }

}
