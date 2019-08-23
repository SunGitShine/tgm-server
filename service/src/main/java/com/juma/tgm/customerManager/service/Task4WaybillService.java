package com.juma.tgm.customerManager.service;

import java.util.List;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.giants.common.tools.PageQueryCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.tgm.customerManager.domain.Task4Waybill;
import com.juma.tgm.customerManager.domain.Task4WaybillReport;
import com.juma.tgm.customerManager.domain.vo.taskWaybill.Task4WaybillListCountVo;
import com.juma.tgm.customerManager.domain.vo.taskWaybill.Task4WaybillListVo;
import com.juma.tgm.customerManager.domain.vo.taskWaybill.Task4WaybillQueryVo;

/**
 * @ClassName: Task4WaybillService
 * @Description:
 * @author: liang
 * @date: 2018-09-30 10:57
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
public interface Task4WaybillService {

    
    /**
     * 
     * @Title: addRedisCache   
     * @Description: 不要用
     * @param: @throws BusinessException      
     * @return: void      
     * @throws
     */
    
    @Deprecated
    void addRedisCache() throws BusinessException;
    
    /**
     * 新增任务
     *
     * @param task4Waybill
     * @param loginEmployee
     * @return
     * @throws BusinessException
     */
    int addTask4Waybill(Task4Waybill task4Waybill, LoginEmployee loginEmployee) throws BusinessException;

    

    /**
     * 通过模板id删除任务
     * @param taskTemplateId
     * @throws BusinessException
     */
    void delByTaskTemplateId(Integer taskTemplateId) throws BusinessException;

    /**
     * 删除任务
     *
     * @param taskId
     * @throws BusinessException
     */
    void delTask4Waybill(Integer taskId) throws BusinessException;

    /**
     * 更新任务
     *
     * @param task4Waybill
     * @param loginEmployee
     * @throws BusinessException
     */
    void updateTask4Waybill(Task4Waybill task4Waybill, LoginEmployee loginEmployee) throws BusinessException;

    //任务报告新增
    int addTask4WaybillReport(Task4WaybillReport task4WaybillReport, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * 通过任务id获取任务详情
     *
     * @param task4WaybillId
     * @return
     * @throws BusinessException
     */
    Task4Waybill get(Integer task4WaybillId) throws BusinessException;

    /**
     * 通过任务id获取任务报告列表简略信息(taskId)
     *
     * @param pageCondition
     * @return
     * @throws BusinessException
     */
    Page<Task4WaybillReport> findSimpleReportList(PageCondition pageCondition) throws BusinessException;

    /**
     * 任务列表搜索
     *
     * @param queryCondition 客户名称，用车人，项目名称，今日任务
     * @return
     * @throws BusinessException
     */
    Page<Task4WaybillListVo> searchTask(PageQueryCondition<Task4WaybillQueryVo> queryCondition, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * 任务列表页计数
     * @param queryCondition
     * @return
     * @throws BusinessException
     */
    Task4WaybillListCountVo taskCount(PageQueryCondition<Task4WaybillQueryVo> queryCondition, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * 通过运单模板id获取任务
     * @param taskTemplateId
     * @return
     * @throws BusinessException
     */
    Task4Waybill getByTemplateId(Integer taskTemplateId) throws BusinessException;

    /**
     * 计算项目下开启自动下单任务的数量
     * @param projectId
     * @return
     * @throws BusinessException
     */
    int countTaskByProject(Integer projectId) throws BusinessException;

    List<Task4Waybill> findCanExecutableTask();

}
