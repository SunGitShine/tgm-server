package com.juma.tgm.mq.rabbit.receive;

import java.nio.charset.Charset;
import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import com.alibaba.fastjson.JSONObject;
import com.giants.common.exception.GiantsException;
import com.juma.tgm.cron.service.CronjobService;
import com.juma.tgm.customerManager.domain.Task4Waybill;
import com.juma.tgm.customerManager.domain.Task4WaybillReport;
import com.juma.tgm.customerManager.domain.vo.taskWaybill.TaskWaybillTemplateEnum;
import com.juma.tgm.customerManager.service.Task4WaybillReportService;

/**
 * @description: ${description}
 *
 * @author: xieqiang
 *
 * @create: 2019-05-31 15:32
 **/
public class MessageTask4WaybillListener implements MessageListener {

	private final Logger log = LoggerFactory.getLogger(MessageTask4WaybillListener.class);

	@Resource
	private CronjobService cronjobService;
	@Resource
	private Task4WaybillReportService task4WaybillReportService;

	@Override
	public void onMessage(Message message) {

		log.info("定时建单消息开始处理...");
		String jsonStr = new String(message.getBody(), Charset.forName("utf-8"));
		log.info("定时建单消息内容：{}", jsonStr);
		Task4Waybill task4Waybill = new Task4Waybill();
		try {
			task4Waybill = JSONObject.parseObject(jsonStr, Task4Waybill.class);
			//创建运单
			cronjobService.doTaskAutoCreateWaybill(task4Waybill);
			handleCreateWaybillReport(task4Waybill, null);
			log.info("定时建单成功taskId：{}", task4Waybill.getTaskId());
			//发送执行结果推送
			cronjobService.pushTaskReportMsg(task4Waybill);
		} catch (Exception e) {
			log.info("自动建单发生错误", e);
			handleCreateWaybillReport(task4Waybill, e);
		}
	}

	/**
	 * 保存每个执行结果
	 *
	 * @param task4Waybill
	 * @param e
	 */
	private void handleCreateWaybillReport(Task4Waybill task4Waybill, Exception e) {

		Task4WaybillReport task4WaybillReport = new Task4WaybillReport();

		byte taskStatus;
		String resultMsg;
		if(null != e){
			taskStatus = TaskWaybillTemplateEnum.TaskStatusEnum.TASK_STATUS_FAIL.getCode();
			resultMsg = "系统错误;";

			if (e instanceof GiantsException) {
				resultMsg = ((GiantsException) e).getErrorMessage();
			} else {
				resultMsg += "task id:" + task4Waybill.getTaskId();
			}
		}else {
			resultMsg = "成功";
			taskStatus = TaskWaybillTemplateEnum.TaskStatusEnum.TASK_STATUS_SUCCESS.getCode();
		}

		task4WaybillReport.setTaskStatus(taskStatus);
		task4WaybillReport.setTaskId(task4Waybill.getTaskId());
		task4WaybillReport.setTaskResult(resultMsg);
		task4WaybillReport.setCreateTime(new Date());
		task4WaybillReport.setTaskExcuteDate(task4WaybillReport.getCreateTime());
		task4WaybillReport.setCreateUserId(task4Waybill.getCreateUserId());
		task4WaybillReport.setEmployeeId(task4Waybill.getEmployeeId());

		task4WaybillReportService.addReport(task4WaybillReport);
	}
}
