package com.juma.tgm.task.dao.ext;


import com.juma.tgm.common.query.QueryCond;
import com.juma.tgm.task.domain.TaskScheduled;
import com.juma.tgm.task.domain.ext.GroupTaskByProject;
import com.juma.tgm.task.dto.gateway.GroupTaskFilter;
import com.juma.tgm.task.dto.manage.TaskFilter;
import com.juma.tgm.task.vo.gateway.GroupTaskCount;
import com.juma.tgm.task.vo.manage.Task;

import java.util.List;

public interface TaskScheduledExtMapper {

    List<GroupTaskByProject> groupTaskByProject(QueryCond<GroupTaskFilter> query);

    int groupTaskByProjectTotal(QueryCond<GroupTaskFilter> query);

    List<Task> findTaskByPage(QueryCond<TaskFilter> taskFilter);

    int findTaskCount(QueryCond<TaskFilter> taskFilter);

    List<GroupTaskCount> groupTaskCount(List<Integer> projectIds);

}