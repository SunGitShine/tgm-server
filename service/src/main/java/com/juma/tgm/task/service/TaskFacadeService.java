package com.juma.tgm.task.service;

import com.giants.common.exception.BusinessException;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.common.query.QueryCond;
import com.juma.tgm.common.vo.Page;
import com.juma.tgm.task.domain.TaskCalendar;
import com.juma.tgm.task.domain.TaskParam;
import com.juma.tgm.task.domain.TaskPeriod;
import com.juma.tgm.task.domain.TaskScheduled;
import com.juma.tgm.task.dto.gateway.GroupTaskFilter;
import com.juma.tgm.task.dto.gateway.TaskFilter;
import com.juma.tgm.task.vo.gateway.*;

import java.util.Date;
import java.util.List;

/**
 * 任务门面
 */
public interface TaskFacadeService {

    /**
     * 权限检查
     * @param taskId
     * @param loginUser
     * @return
     */
    TaskScheduled checkPermission(Integer taskId, LoginUser loginUser) throws BusinessException;

    /**
     * 经济人端:任务管理
     * @param query
     * @param loginUser
     * @return
     * @throws BusinessException
     */
    Page<Task> pageOfTask(QueryCond<TaskFilter> query, LoginUser loginUser) throws BusinessException;


    /**
     * 经济人端:任务状态分组数量
     * @param loginUser
     * @return
     */
    List<GroupTaskCount> groupTaskCount(LoginUser loginUser);

    /**
     * 司机端:任务分组按项目
     * @param query
     * @param loginUser
     * @return
     * @throws BusinessException
     */
    Page<GroupTask> pageOfGroupTask(QueryCond<GroupTaskFilter> query, LoginUser loginUser) throws BusinessException;


    /**
     * 司机端:任务列表按项目（或承运商）
     * @param query
     * @param loginUser
     * @return
     * @throws BusinessException
     */
    Page<GroupTaskDetail> pageOfGroupTaskDetail(QueryCond<GroupTaskFilter> query, LoginUser loginUser) throws BusinessException;


    /**
     * 司机端:待接任务邀请列表按承运商
     * @param query
     * @param loginUser
     * @return
     * @throws BusinessException
     */
    Page<GroupTaskDetail> pageOfTaskAck(QueryCond<GroupTaskFilter> query, LoginUser loginUser) throws BusinessException;

    /**
     * 司机端:承运商接受任务
     * @param taskId
     * @param loginUser
     */
    void updateToVendorReceive(Integer taskId,Integer vendorId,LoginUser loginUser) throws BusinessException;


    /**
     * 司机端:承运商接受任务前的冲突检查
     * @param taskId
     * @param loginUser
     */
    int conflictVendorReceive(Integer taskId,Integer vendorId,LoginUser loginUser) throws BusinessException;

    /**
     * 生成任务日历
     * @param isUpdate
     * @param isThrowException
     * @param taskId
     * @param truckId
     * @param driverId
     * @param taskScheduled
     * @param taskParam
     * @param taskPeriod
     * @param loginUser
     * @return
     */
    int makeTaskCalendar(boolean isUpdate,boolean isThrowException,Integer taskId,Integer vendorId,Integer truckId,Integer driverId,
        TaskScheduled taskScheduled, TaskParam taskParam, TaskPeriod taskPeriod,LoginUser loginUser) throws BusinessException;

    /**
     * 司机端:承运商拒绝任务
     * @param taskId
     * @param loginUser
     */
    void updateToVendorRefuse(Integer taskId,Integer vendorId,String reason,LoginUser loginUser) throws BusinessException;


    /**
     * 司机端:运单任务
     * @param waybillId
     */
    TaskWaybill taskWaybill(int waybillId) throws BusinessException;



    /**
     * 司机端:进行中+待生效任务数量
     * @param vendorId
     * @return
     * @throws BusinessException
     */
    int taskCountWithVendorId(Integer vendorId) throws BusinessException;

    /**
     * 更改账期
     * @param taskId
     * @param billPeriod
     * @param billPeriodReason
     * @param loginUser
     * @throws BusinessException
     */
    void updateBillPeriod(Integer taskId,Integer billPeriod,String billPeriodReason,LoginUser loginUser) throws BusinessException;

    /**
     * 取消任务
     * @param taskId
     * @param cancelReason
     * @throws BusinessException
     */
    void cancelTask(Integer taskId,String cancelReason,LoginUser loginUser) throws BusinessException;

    /**
     * 邀请承运商
     * @param loginUser
     * @throws BusinessException
     */
    void inviteVendor(InviteRequest request, LoginUser loginUser) throws BusinessException;

    /**
     * 司机端或经济人端:任务详情
     * @param taskId
     * @return
     * @throws BusinessException
     */
    TaskDetail getTaskDetail(Integer taskId) throws BusinessException;



    /**
     * 任务日程
     * @param taskId
     * @param startDate
     * @return
     * @throws BusinessException
     */
    TaskCalendarMaster getTaskCalendarMaster(Integer taskId, Date startDate,boolean isIncludeHeader) throws BusinessException;


    /**
     * 更新为不配送
     * @param calendarId
     * @param reason
     * @throws BusinessException
     */
    void updateToNotDelivery(Integer calendarId,Integer reasonSort,String reason,LoginUser loginUser) throws BusinessException;

    /**
     * 恢复配送
     * @param calendarId
     * @param reason
     * @param loginUser
     * @throws BusinessException
     */
    void updateToRecoverDelivery(Integer calendarId,Integer driverId,Integer truckId,String reason,LoginUser loginUser) throws BusinessException;


    /**
     * 更换运力
     * @param calendarId
     * @param driverId
     * @param truckId
     * @param type  1:更换选中的日期运力  2:更换剩余全部运力
     * @param loginUser
     * @throws BusinessException
     */
    void doChangeCapacity(Integer calendarId, Integer driverId, Integer truckId,Integer type, LoginUser loginUser) throws BusinessException;

    /**
     * 更换运力 冲突检查
     * @param calendarId
     * @param driverId
     * @param truckId
     * @param type
     * @param loginUser
     * @throws BusinessException
     */
    int conflictChangeCapacity(Integer calendarId, Integer driverId, Integer truckId,Integer type, LoginUser loginUser) throws BusinessException;

    /**
     * 根据任务日历生成运单
     * @param taskCalendar
     * @param loginUser
     * @return
     * @throws BusinessException
     */
    Integer createWaybillByTaskCalendar(TaskCalendar taskCalendar, LoginUser loginUser) throws BusinessException;

    /**
     * 任务改派
     * @param request
     * @param loginUser
     * @throws BusinessException
     */
    void changeVendor(InviteRequest request, LoginUser loginUser) throws BusinessException;

    /**
     * 不配送原因
     * @return
     * @throws BusinessException
     */
    NotDeliveryReasonSort notDeliveryReasonSort() throws BusinessException;

    /**
     * 结束任务
     * @param taskId
     * @param loginEmployee
     */
    void endTask(Integer taskId, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * 恢复任务
     * @param taskId
     * @param loginUser
     */
    void recoverTask(Integer taskId, LoginUser loginUser) throws BusinessException;
}
