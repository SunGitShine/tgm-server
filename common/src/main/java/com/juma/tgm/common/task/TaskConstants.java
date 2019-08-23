package com.juma.tgm.common.task;

/**
 * @description: ${description}
 *
 * @author: xieqiang
 *
 * @create: 2019-07-15 17:30
 **/
public class TaskConstants {

	//用于检测任务运力指派时的时段冲突，即在新任务的开始时间中减去当前冗余值作为新任务的开始时间（单位小时）
	public static final String TRANSPORT_CONFLICT_VALID_REDUNDANT = "transport_conflict_valid_redundant";

	//创建任务时选择任务”开始日期”距当前日期最大可选日期天数(单位天)
	public static final String TAX_MAX_PRE_START_DAY = "tax_max_pre_start_date";

	//任务有效期最大天数(单位天)
	public static final String TAX_MAX_VALID_DAY = "tax_max_valid_day";

	//承运商可选结算帐期(单位天)
	public static final String VENDOR_ACCOUNT_PERIOD = "vendor_account_period";

	//运单最长生成天数(单位天)
	public static final String WAYBILL_MAX_CREATE_DAY = "waybill_max_create_day";

	//时段冲突允许通过天数比例
	public static final String TIME_CONFLICT_DAY_PERCENT = "time_conflict_day_percent";

	//时段冲突允许通过天数
	public static final String TIME_CONFLICT_DAY = "time_conflict_day";

	//承运商不配送原因
	public static final String VENDOR_NOT_DELIVERY_REASON = "vendor_not_delivery_reason";

	//客户不配送原因
	public static final String CUSTOMER_NOT_DELIVERY_REASON = "customer_not_delivery_reason";

	//我方不配送原因
	public static final String OWEN_NOT_DELIVERY_REASON = "owen_not_delivery_reason";

	//结束任务原因
	public static final String END_TASK_REASON = "end_task_reason";

	//任务派车/任务改派短信通知承运商
	public static final String INVITE_VENDOR_SMS = "INVITE_VENDOR_SMS";

	//任务派车/任务改派PUSH通知承运商
	public static final String INVITE_VENDOR_PUSH = "INVITE_VENDOR_PUSH";

	//结束任务短信通知承运商
	public static final String END_TASK_SMS = "END_TASK_SMS";

	//结束任务PUSH通知承运商
	public static final String END_TASK_PUSH = "END_TASK_PUSH";

	//创建运单失败-云之家推送
	public static final String CREATE_WAYBILL_FAILURE_PUSH = "项目：%s，有1个任务（编号%s）因%s，系统创建运单失败,已设置配送日为【不配送】，若要继续配送请去APP任务管理>任务日程中操作【恢复配送】";

	//任务过期-云之家推送
	public static final String TSAK_EXPIRED_PUSH = "有项目名称为%s的1个任务在派车有效期内没有承运商接受任务，已过期！请到市场助手APP查看。";

}
