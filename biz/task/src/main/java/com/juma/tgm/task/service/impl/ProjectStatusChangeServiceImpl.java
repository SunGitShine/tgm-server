package com.juma.tgm.task.service.impl;

import com.giants.common.exception.BusinessException;
import com.google.common.collect.Lists;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.auth.user.domain.User;
import com.juma.auth.user.service.UserService;
import com.juma.log.domain.OperationLogInfo;
import com.juma.tgm.common.Constants;
import com.juma.tgm.common.StringTemplateUtils;
import com.juma.tgm.fms.domain.Task;
import com.juma.tgm.project.domain.v2.Project;
import com.juma.tgm.project.service.ProjectService;
import com.juma.tgm.task.domain.*;
import com.juma.tgm.task.enums.TaskEnum;
import com.juma.tgm.task.service.*;
import com.juma.tgm.tools.service.MessagePushService;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.service.WaybillCommonService;
import com.juma.tgm.waybill.service.WaybillService;
import com.juma.vms.operateLog.service.OperateLogService;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;


@Slf4j
@Service
public class ProjectStatusChangeServiceImpl implements ProjectStatusChangeService {

    @Resource
    private TaskScheduledService taskScheduledService;

    @Resource
    private TaskCalendarService taskCalendarService;

    @Resource
    private TaskTimelineService taskTimelineService;

    @Resource
    private TaskParamService taskParamService;

    @Resource
    private TaskFacadeService taskFacadeService;

    @Resource
    private WaybillCommonService waybillCommonService;

    @Resource
    private WaybillService waybillService;

    @Resource
    private MessagePushService messagePushService;

    @Resource
    private OperateLogService operateLogService;

    @Resource
    private ProjectService projectService;

    @Resource
    private UserService userService;

    @Override
    public void pause(Integer projectId) {
        log.info("project status pause : {}.",projectId);
        changeTask(projectId
                ,TaskEnum.TaskStatus.Pause.getCode()
        );
    }

    @Override
    public void finish(Integer projectId) {
        log.info("project status finish : {}.",projectId);
        changeTask(projectId
                ,TaskEnum.TaskStatus.Finish.getCode()
        );
    }

    private void pauseLog(Integer tenantId,Integer taskId) {
        OperationLogInfo operationLogInfo = new OperationLogInfo();
        operationLogInfo.setMethodName("com.juma.tgm.task.service.ProjectStatusChangeService.pause");
        operationLogInfo.setParam("{\"remark\":\"项目暂停\",\"taskId\":"+taskId+"}");
        LoginUser loginUser = new LoginUser(tenantId,1);
        operateLogService.writelog(operationLogInfo,loginUser);
    }

    private void finishLog(Integer tenantId,Integer taskId) {
        OperationLogInfo operationLogInfo = new OperationLogInfo();
        operationLogInfo.setMethodName("com.juma.tgm.task.service.ProjectStatusChangeService.finish");
        operationLogInfo.setParam("{\"remark\":\"项目结束\",\"taskId\":"+taskId+"}");
        LoginUser loginUser = new LoginUser(tenantId,1);
        operateLogService.writelog(operationLogInfo,loginUser);
    }

    private void changeTask(Integer projectId,Integer toTaskStatus) {
        if (projectId == null || toTaskStatus == null) {
            throw new BusinessException("paramError","参数异常");
        }
        //任务状态
        List<TaskScheduled> taskScheduledList = taskScheduledService.selectByExample(new TaskScheduledExample().createCriteria()
                .andProjectIdEqualTo(projectId)
                .andIsDeleteEqualTo(false).example());

        if (taskScheduledList.isEmpty()) return;

        List<Integer> taskIds = new ArrayList<>();
        List<TaskScheduled> updateTaskScheduledList = new ArrayList<>();

        Map<Integer,Integer> projectTaskCountMap = new HashMap<>();
        boolean isUpdate = false;
        for ( TaskScheduled taskScheduled : taskScheduledList) {
            LoginUser loginUser = new LoginUser(taskScheduled.getTenantId(), taskScheduled.getCreateUserId());
            if (toTaskStatus == TaskEnum.TaskStatus.Pause.getCode()) {
                TaskEnum.TaskStatus taskStatusEnum = TaskEnum.TaskStatus.getTaskStatusByCode(taskScheduled.getTaskStatus());
                if (taskStatusEnum == null) continue;
                switch (taskStatusEnum) {
                    case Waiting_Invite:
                    case Inviting:
                        taskFacadeService.cancelTask(taskScheduled.getTaskId(),"项目暂停", loginUser);
                        break;
                    case Waiting_Become:
                    case Running:
                        messagePushService.pauseTask(taskScheduled.getTaskId());//推送消息
                        pauseLog(taskScheduled.getTenantId(),taskScheduled.getTaskId());//写日志
                        isUpdate = true;
                        if (projectTaskCountMap.containsKey(taskScheduled.getProjectId())) {
                            Integer i = projectTaskCountMap.get(taskScheduled.getProjectId());
                            projectTaskCountMap.put(taskScheduled.getProjectId(),i+1);
                        } else {
                            projectTaskCountMap.put(taskScheduled.getProjectId(),1);
                        }
                        taskScheduled.setTaskStatus(TaskEnum.TaskStatus.Pause.getCode());
                        break;
                }
            } else if(toTaskStatus == TaskEnum.TaskStatus.Finish.getCode()) {
                TaskEnum.TaskStatus taskStatusEnum = TaskEnum.TaskStatus.getTaskStatusByCode(taskScheduled.getTaskStatus());
                if (taskStatusEnum == null) continue;
                switch (taskStatusEnum) {
                    case Waiting_Invite:
                    case Inviting:
                        taskFacadeService.cancelTask(taskScheduled.getTaskId(),"项目结束", loginUser);
                        break;
                    case Waiting_Become:
                    case Running:
                    case Pause:
                        messagePushService.finishTask(taskScheduled.getTaskId());//推送消息
                        finishLog(taskScheduled.getTenantId(),taskScheduled.getTaskId());//写日志
                        isUpdate = true;
                        taskScheduled.setTaskStatus(TaskEnum.TaskStatus.Finish.getCode());
                        break;
                }
            }
            if (isUpdate) {
                taskScheduled.setLastUpdateTime(new Date());
                taskScheduled.setLastUpdateUserId(1);
                updateTaskScheduledList.add(taskScheduled);
                taskIds.add(taskScheduled.getTaskId());
            }
        }

        //云之家通知项目经理 有多少个任务被暂停了
        Set<Map.Entry<Integer, Integer>> entrySet = projectTaskCountMap.entrySet();
        for (Map.Entry<Integer, Integer> entry : entrySet ) {
            Integer projId = entry.getKey();
            Integer count = entry.getValue();

            Project projectV2 = projectService.getProjectV2(projId);
            if (projectV2 == null || projectV2.getProjectManagerUserId() == null) continue;
            User user = userService.loadUser(projectV2.getProjectManagerUserId());
            if (user == null) continue;
            Map<String,String> context = new HashMap<>();
            context.put("projectName",projectV2.getName());
            context.put("projectNo",projectV2.getProjectNo());
            context.put("count",count+"");
            String content = "项目：${projectName}(${projectNo})，有${count}个任务因为项目暂停已被系统自动设置为暂停，将不能继续配送。";
            List<String> phoneList = new ArrayList<>();
            phoneList.add(user.getMobileNumber());

            messagePushService.cloudMessagePush(Constants.AUTH_KEY_TGM_MANAGE,projId + "","项目暂停", StringTemplateUtils.render(content,context),phoneList,Constants.SYS_LOGIN_USER);
        }

        if (!updateTaskScheduledList.isEmpty()) {
            taskScheduledService.updateBatchByPrimaryKeySelective(updateTaskScheduledList);
        }

        List<TaskCalendar> taskCalendarListAll = new ArrayList<>();
        //配送日状态
        for ( Integer taskId : taskIds ) {
            ArrayList<Integer> workStatusList = Lists.newArrayList();
            workStatusList.add(TaskEnum.WorkStatus.Conflict.getCode());
            workStatusList.add(TaskEnum.WorkStatus.Running.getCode());
            List<TaskCalendar> taskCalendarList = taskCalendarService.selectByExample(new TaskCalendarExample().createCriteria()
                    .andTaskIdEqualTo(taskId)
                    .andWorkStatusIn(workStatusList).example());
            taskCalendarListAll.addAll(taskCalendarList);
        }

        for ( TaskCalendar taskCalendar : taskCalendarListAll ) {
            TaskScheduled taskScheduled = taskScheduledService.withTaskId(taskCalendar.getTaskId());
            if (taskScheduled == null) continue;
            TaskParam taskParam = taskParamService.withTaskId(taskCalendar.getTaskId());
            if (taskParam == null) continue;
            if (taskCalendar.getWaybillId() !=0) {
                Waybill waybill = waybillService.getWaybill(taskCalendar.getWaybillId());
                if (waybill == null || waybill.getStatusView() == null) continue;
                if (waybill.getStatusView() == Waybill.StatusView.WATING_DELIVERY.getCode()) {
                    //取消运单
                    LoginEmployee loginEmployee = new LoginEmployee();
                    loginEmployee.setUserId(1);
                    loginEmployee.setTenantId(taskScheduled.getTenantId());
                    if (toTaskStatus == TaskEnum.TaskStatus.Pause.getCode()) {
                        waybill.setWaybillCancelRemark("项目暂停");
                    } else if (toTaskStatus == TaskEnum.TaskStatus.Finish.getCode()) {
                        waybill.setWaybillCancelRemark("项目结束");
                    }
                    waybill.setCancelChannel(Waybill.CancelChannel.TASK_CALENDAR.getCode());
                    waybillCommonService.doCancelWaybill(waybill, loginEmployee, Waybill.Status.SYS_CANCEL);
                } else if(waybill.getStatusView() == Waybill.StatusView.DELIVERYING.getCode()) {
                    continue;
                }
            }
            //删除时间线
            taskTimelineService.deleteByPrimaryKey(taskCalendar.getTimelineId());
            taskCalendar.setTimelineId(0);
            taskCalendar.setWorkStatus(TaskEnum.WorkStatus.Not_delivery.getCode());
        }
        if (taskCalendarListAll.isEmpty()) return;
        taskCalendarService.updateBatchByPrimaryKeySelective(taskCalendarListAll);
    }
}
