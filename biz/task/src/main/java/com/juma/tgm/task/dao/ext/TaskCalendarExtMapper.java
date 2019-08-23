package com.juma.tgm.task.dao.ext;

import com.juma.tgm.task.domain.ext.GroupTaskCalendar;
import com.juma.tgm.task.domain.ext.GroupTaskWorkStatus;
import com.juma.tgm.task.dto.gateway.GroupTaskCalendarFilter;
import com.juma.tgm.task.vo.gateway.TaskCalendarByProject;

import java.util.List;

public interface TaskCalendarExtMapper {

    List<GroupTaskWorkStatus> groupByTaskWorkStatus(Integer taskId);

    List<GroupTaskCalendar> groupByWorkStatusCount(GroupTaskCalendarFilter groupTaskCalendarFilter);

    List<GroupTaskCalendar> groupByDeliveryStatusCount(GroupTaskCalendarFilter groupTaskCalendarFilter);

    List<TaskCalendarByProject> getCalendarBy(GroupTaskCalendarFilter groupTaskCalendarFilter);
}
