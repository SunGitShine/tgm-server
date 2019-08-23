package com.juma.tgm.mq.rabbit.receive;

import java.nio.charset.Charset;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import com.alibaba.fastjson.JSONObject;
import com.juma.auth.user.domain.LoginUser;
import com.juma.auth.user.service.UserService;
import com.juma.tgm.project.service.ProjectService;
import com.juma.tgm.task.domain.TaskCalendar;
import com.juma.tgm.task.domain.TaskScheduled;
import com.juma.tgm.task.enums.TaskEnum;
import com.juma.tgm.task.service.TaskCalendarService;
import com.juma.tgm.task.service.TaskFacadeService;
import com.juma.tgm.task.service.TaskScheduledService;
import com.juma.tgm.task.service.TaskTimelineService;
import com.juma.tgm.tools.service.MessagePushService;

/**
 * @description: ${description}
 *
 * @author: xieqiang
 *
 * @create: 2019-07-19 16:05
 **/
public class MessageTaskCreateWaybillListener implements MessageListener {

	private final Logger log = LoggerFactory.getLogger(MessageTaskCreateWaybillListener.class);

	@Resource
	private TaskFacadeService taskFacadeService;

	@Resource
	private TaskScheduledService taskScheduledService;

	@Resource
	private TaskCalendarService taskCalendarService;

	@Resource
	private MessagePushService messagePushService;

	@Resource
	private ProjectService projectService;

	@Resource
	private UserService userService;

	@Resource
	private TaskTimelineService taskTimelineService;

	@Override
	public void onMessage(Message message) {

		log.info("任务建单开始处理...");
		String jsonStr = new String(message.getBody(), Charset.forName("utf-8"));
		log.info("任务建单消息内容：{}", jsonStr);
		TaskCalendar taskCalendar = JSONObject.parseObject(jsonStr, TaskCalendar.class);
		TaskScheduled taskScheduled = taskScheduledService.withTaskId(taskCalendar.getTaskId());
		LoginUser loginUser = new LoginUser(taskScheduled.getTenantId(), taskScheduled.getCreateUserId());
		try {
			//创建运单
			Integer waybillId = taskFacadeService.createWaybillByTaskCalendar(taskCalendar, loginUser);
			//更新任务日历中运单id
			taskCalendar.setWaybillId(waybillId);
			taskCalendarService.updateByPrimaryKeySelective(taskCalendar);

			//更新待生效的任务为运行中
			if(TaskEnum.TaskStatus.Waiting_Become.getCode() == taskScheduled.getTaskStatus()){
				taskScheduled.setTaskStatus(TaskEnum.TaskStatus.Running.getCode());
				taskScheduledService.updateByPrimaryKeySelective(taskScheduled);
			}

			log.info("任务建单成功taskId：{}，taskCalendarId：{}", taskCalendar.getTaskId(), taskCalendar.getCalendarId());
		} catch (Exception e) {

//			String resultMsg = "系统错误;";
//			if (e instanceof GiantsException) {
//				resultMsg = ((GiantsException) e).getErrorMessage();
//			} else {
//				resultMsg += "taskCalendarId id:" + taskCalendar.getCalendarId();
//			}
//
//			taskCalendar.setWorkStatus(TaskEnum.WorkStatus.Not_delivery.getCode());
//			taskCalendar.setFailureReason(resultMsg);
//			taskCalendarService.updateByPrimaryKeySelective(taskCalendar);
//
//			taskTimelineService.deleteByPrimaryKey(taskCalendar.getTimelineId());
//			try {
//				Project projectV2 = projectService.getProjectV2(taskScheduled.getProjectId());
//
//				if(projectV2 != null){
//					User user = userService.loadUser(projectV2.getProjectManagerUserId());
//
//					if(user != null && StringUtils.isNotBlank(user.getMobileNumber())){
//						String content = String.format(TaskConstants.CREATE_WAYBILL_FAILURE_PUSH, projectV2.getName(), projectV2.getProjectNo(), e.getMessage());
//						List<String> phoneList = new ArrayList<>();
//						phoneList.add(user.getMobileNumber());
//						messagePushService.cloudMessagePush(Constants.AUTH_KEY_TGM_MANAGE,taskScheduled.getTaskId().toString(), "任务自动创建运单失败", content, phoneList, loginUser);
//					}
//				}
//			} catch (Exception e1) {
//				log.info("任务建单发生错误，推送云之家消息错误", e1);
//			}
			log.info("任务建单发生错误", e);
		}
	}
}
