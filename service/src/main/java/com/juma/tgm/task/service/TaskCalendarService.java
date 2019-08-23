package com.juma.tgm.task.service;

import com.juma.tgm.task.domain.ext.GroupTaskWorkStatus;
import com.juma.tgm.task.enums.TaskEnum;
import org.mybatis.generator.my.service.MybatisService;

import com.juma.tgm.task.domain.TaskCalendar;
import com.juma.tgm.task.domain.TaskCalendarExample;

import java.util.Date;
import java.util.List;

public interface TaskCalendarService extends MybatisService<TaskCalendar,TaskCalendarExample> {

    List<TaskCalendar> withTaskId(Integer taskId, Date startDate,Date endDate);

    TaskCalendar withCalendarId(Integer calendarId);

    List<GroupTaskWorkStatus> groupByTaskWorkStatus(Integer taskId);

    //返回这个时间段之后第一条运行中或运力冲突的记录
    TaskCalendar withTaskId(Integer taskId,List<Integer> workStatusList,Date startDate);

    void updateByWaybillId(Integer waybillId);

    //更新配送状态
    void updateDeliveryStatus(Integer waybillId, TaskEnum.DeliveryStatus deliveryStatus);

    //建单失败
    void createWaybillFail(Integer calendarId,Integer timelineId,String failMsg);

}
