package com.juma.tgm.customerManager.service;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageQueryCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.customerManager.domain.Task4WaybillReport;
import com.juma.tgm.customerManager.domain.vo.taskWaybill.Task4WaybillReportCountVo;
import com.juma.tgm.customerManager.domain.vo.taskWaybill.Task4WaybillReportDetailVo;
import com.juma.tgm.customerManager.domain.vo.taskWaybill.Task4WaybillReportQueryVo;

/**
 * 定时下单执行报告
 *
 * @ClassName: Task4WaybillReportService
 * @Description:
 * @author: liang
 * @date: 2018-10-08 11:06
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
public interface Task4WaybillReportService {

    /**
     * 获取任务报告列表 for template view
     *
     * @param pageQueryCondition
     * @return
     * @throws BusinessException
     */
    Page<Task4WaybillReportDetailVo> searchForView(PageQueryCondition<Task4WaybillReportQueryVo> pageQueryCondition) throws BusinessException;

    /**
     * 获取任务报告 for report list view
     *
     * @param pageQueryCondition 执行时间，客户经理id
     * @return
     * @throws BusinessException
     */
    Page<Task4WaybillReportDetailVo> searchForDetailList(PageQueryCondition<Task4WaybillReportQueryVo> pageQueryCondition) throws BusinessException;


    /**
     * 获取任务执行结果计数
     *
     * @param pageQueryCondition
     * @return
     * @throws BusinessException 执行时间，客户经理user_id
     */
    Task4WaybillReportCountVo overViewCount(PageQueryCondition<Task4WaybillReportQueryVo> pageQueryCondition) throws BusinessException;


    /**
     * 获取任务报告聚合信息
     *
     * @param pageQueryCondition 客户经理user_id
     * @return
     * @throws BusinessException
     */
    Page<Task4WaybillReportCountVo> overViewList(PageQueryCondition<Task4WaybillReportQueryVo> pageQueryCondition) throws BusinessException;


    /**
     * 新增执行报告（一般是后台任务使用）
     *
     * @param task4WaybillReport
     * @return
     * @throws BusinessException
     */
    int addReport(Task4WaybillReport task4WaybillReport) throws BusinessException;

    /**
     * 获取最后一次执行结果
     *
     * @param taskId
     * @return
     * @throws BusinessException
     */
    Task4WaybillReport getLastReportByTaskId(Integer taskId) throws BusinessException;

    /**
     * 修改任务执行报告状态
     *
     * @param task4WaybillReport
     * @param loginUser
     * @throws BusinessException
     */
    void modifyReportStatus(Task4WaybillReport task4WaybillReport, LoginUser loginUser) throws BusinessException;

    /**
     * 按用户和时间查询任务执行情况
     *
     * @param queryVo
     * @return
     * @throws BusinessException
     */
    Task4WaybillReportCountVo findReportOverview(Task4WaybillReportQueryVo queryVo) throws BusinessException;


    /**
     * 报告状态已读
     * @param taskId
     * @param loginEmployee
     * @throws BusinessException
     */
    void modifyReportReadStatus(Integer taskId, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * 是否有未读报告
     * @param loginEmployee
     * @return
     * @throws BusinessException
     */
    Boolean hasUnreadReport(LoginEmployee loginEmployee) throws BusinessException;

    /**
     *按id 获取 report
     *
     * @param reportId
     * */
    Task4WaybillReport getTask4WaybillReport( Integer reportId ) throws BusinessException;


    /**
     * 根据taskId删除报告
     * @param taskId
     * @throws BusinessException
     */
    void delTaskReport(Integer taskId) throws BusinessException;

}
