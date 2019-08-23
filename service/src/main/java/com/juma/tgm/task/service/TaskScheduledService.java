package com.juma.tgm.task.service;

import java.util.Date;
import java.util.List;

import com.giants.common.exception.BusinessException;
import com.juma.tgm.task.domain.TaskFixedDelivery;
import com.juma.tgm.task.domain.ext.GroupTaskCalendar;
import com.juma.tgm.task.dto.gateway.GroupTaskCalendarFilter;
import com.juma.tgm.task.vo.gateway.TaskCalendarByProject;
import com.juma.tgm.task.vo.gateway.TaskStatusCount;
import com.juma.tgm.task.vo.manage.TaskLinkWaybill;
import org.mybatis.generator.my.service.MybatisService;

import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.capacity.domian.vo.CapacityFilter;
import com.juma.tgm.common.query.QueryCond;
import com.juma.tgm.common.vo.Page;
import com.juma.tgm.project.domain.v2.ProjectDepot;
import com.juma.tgm.task.domain.TaskAck;
import com.juma.tgm.task.domain.TaskScheduled;
import com.juma.tgm.task.domain.TaskScheduledExample;
import com.juma.tgm.task.domain.vo.TaskScheduledVO;
import com.juma.tgm.task.domain.vo.TaskTimeVO;
import com.juma.tgm.task.dto.manage.TaskFilter;
import com.juma.tgm.task.vo.gateway.InviteRequest;
import com.juma.tgm.task.vo.gateway.TaskAckDetail;
import com.juma.tgm.task.vo.gateway.TaskAckPage;
import com.juma.tgm.task.vo.manage.CapacityPoolPage;
import com.juma.tgm.task.vo.manage.Task;
import com.juma.tgm.task.vo.manage.TaskDetail;

public interface TaskScheduledService extends MybatisService<TaskScheduled, TaskScheduledExample> {

    TaskScheduled withTaskId(Integer taskId) throws BusinessException;

	/**
	 * 新增任务
	 * @param taskScheduledVO
	 * @param loginUser
	 */
	Integer createTaskScheduled(TaskScheduledVO taskScheduledVO, LoginUser loginUser) throws BusinessException;

	/**
	 * 任务改派
	 * @param request
	 * @param loginUser
	 */
	void updateTaskInvite(InviteRequest request, LoginUser loginUser) throws BusinessException;

	/**
	 * 是否存在待回复的邀请
	 * @param taskId
	 * @return
	 */
	boolean haveWaitBack(Integer taskId) throws BusinessException;

	/**
	 * 分页查询任务列表（后台）
	 * @param query
	 * @param loginUser
	 * @return
	 * @throws BusinessException
	 */
	Page<Task> findTaskByPage(QueryCond<TaskFilter> query, LoginUser loginUser) throws BusinessException;

	/**
	 * 任务关联运单
	 * @param waybillId
	 * @return
	 * @throws BusinessException
	 */
	TaskLinkWaybill findTaskLinkWaybill(Integer waybillId) throws BusinessException;

	/**
	 * 任务邀请分页查询
	 * @param queryCond
	 * @param loginUser
	 * @return
	 * @throws BusinessException
	 */
	Page<TaskAckPage> findTaskAckPage(QueryCond<TaskAck> queryCond, LoginUser loginUser) throws BusinessException;

	/**
	 * 任务邀请详情
	 * @param taskAckId
	 * @param loginUser
	 * @return
	 */
	TaskAckDetail findTaskAckDetail(Integer taskAckId, LoginUser loginUser) throws BusinessException;

	/**
	 * 查询任务详情（后台）
	 * @param taskId
	 * @return
	 * @throws BusinessException
	 */
	TaskDetail findTaskDetail(Integer taskId, LoginUser loginUser) throws BusinessException;

	/**
	 * 分页查询运力列表(后台)
	 * @return
	 * @throws BusinessException
	 */
	Page<CapacityPoolPage> findCapacityPoolPage(QueryCond<CapacityFilter> queryCond, LoginUser loginUser) throws BusinessException;

	/**
	 * 任务指派(后台)
	 * @param loginUser
	 * @throws BusinessException
	 */
	void inviteVendorMange(InviteRequest request, LoginUser loginUser) throws BusinessException;

	/**
	 * 定时任务更新任务状态和承运商邀请状态
	 * @throws BusinessException
	 */
	void updateTaskStatusTimer();

	void executeTaskCreateWaybillTimer();

	/**
	 * 查询项目仓库列表
	 * @param projectId
	 * @return
	 * @throws BusinessException
	 */
	List<ProjectDepot> findDepotByProjectId(Integer projectId) throws BusinessException;

	/**
	 * 邀请承运商时校验车辆冲突比例
	 * @param taskId
	 * @param truckId
	 * @param loginUser
	 * @return
	 * @throws BusinessException
	 */
	int conflictInviteVendor(Integer taskId, Integer truckId, LoginUser loginUser) throws BusinessException;

	/**
	 *更换运力前校验
	 * @param changeDate
	 * @param driverId
	 * @param truckId
	 * @param type
	 * @param loginUser
	 * @return
	 * @throws BusinessException
	 */
	int conflictChangeCapacity(Integer taskId, Date changeDate, Integer driverId, Integer truckId,Integer type, LoginUser loginUser) throws BusinessException;

	/**
	 *	更换运力
	 * @param taskId
	 * @param changeDate
	 * @param driverId
	 * @param truckId
	 * @param type
	 * @param loginUser
	 * @throws BusinessException
	 */
	void doChangeCapacity(Integer taskId, Date changeDate, Integer driverId, Integer truckId,Integer type, LoginUser loginUser) throws BusinessException;

	/**
	 * 根据联系人姓名模糊查询配送点信息
	 * @param projectId
	 * @param linkName
	 * @param baskPageSize
	 * @param loginUser
	 * @return
	 */
	List<TaskFixedDelivery> findDeliveryByFiler(Integer projectId, String linkName, Integer baskPageSize, LoginUser loginUser);

	/**
	 * 检查是否存在配送周期
	 * @param taskScheduledVO
	 * @return
	 */
	List<TaskTimeVO> checkExistDeliveryDate(TaskScheduledVO taskScheduledVO);

	/**
	 * 查询项目对应任务状态的数量
	 * @param projectId
	 * @param taskStatus
	 * @return
	 */
	Integer	taskNumByProject(Integer projectId, Integer taskStatus);

	/**
	 * 查询项目待指派运力和承运商邀请中数量
	 * @param projectId
	 * @return
	 */
	TaskStatusCount taskNumByProject(Integer projectId);

	/**
	 * 已项目维度统计每天不同任务状态的数量
	 * @param filter
	 * @return
	 */
	List<GroupTaskCalendar> groupTaskCalendarCount(GroupTaskCalendarFilter filter);

	/**
	 * 查询任务日历列表
	 * @param filter
	 * @return
	 */
	List<TaskCalendarByProject> getTaskCalendarBy(GroupTaskCalendarFilter filter);
}
