package com.juma.tgm.customerManager.service;

import com.giants.common.exception.BusinessException;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.customerManager.domain.TaskWaybillTemplate;
import com.juma.tgm.customerManager.domain.TaskWaybillTemplateDestAddress;
import com.juma.tgm.customerManager.domain.TaskWaybillTemplateSrcAddress;
import com.juma.tgm.customerManager.domain.TaskWaybillTemplateTruckBinding;
import com.juma.tgm.customerManager.domain.vo.taskWaybill.TaskWaybillTemplateCreateVo;
import com.juma.tgm.customerManager.domain.vo.taskWaybill.TaskWaybillTemplateDetailVo;
import com.juma.tgm.waybill.domain.WaybillDetailInfo;

import java.util.List;

/**
 * @ClassName: TaskWaybillTemplateService
 * @Description:
 * @author: liang
 * @date: 2018-09-28 13:52
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
public interface TaskWaybillTemplateService {

    /**
     * 新增定时下单任务
     *
     * @param taskWaybillTemplateCreateVo
     * @param loginEmployee
     * @return
     * @throws BusinessException
     */
    int addTaskWaybill(TaskWaybillTemplateCreateVo taskWaybillTemplateCreateVo, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * 修改定时下单任务
     *
     * @param taskWaybillTemplateCreateVo
     * @param loginEmployee
     * @throws BusinessException
     */
    void updateTaskWaybill(TaskWaybillTemplateCreateVo taskWaybillTemplateCreateVo, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * 删除定时下单任务
     *
     * @param taskWaybillTemplateId
     * @throws BusinessException
     */
    void delTaskWaybillTemplate(Integer taskWaybillTemplateId) throws BusinessException;

    /**
     * 通过id获取模板基础信息
     *
     * @param taskWaybillTemplateId
     * @return
     * @throws BusinessException
     */
    TaskWaybillTemplate get(Integer taskWaybillTemplateId) throws BusinessException;


    /**
     * 获取定时下单任务详情
     *
     * @param taskWaybillTemplateId
     * @return
     * @throws BusinessException
     */
    TaskWaybillTemplateDetailVo getDetail(Integer taskWaybillTemplateId, LoginUser loginUser) throws BusinessException;

    /**
     * 通过模板id获取取货地列表
     * @param taskWaybillTemplateId
     * @return
     * @throws BusinessException
     */
    List<TaskWaybillTemplateSrcAddress> getTemplateSrcAddressByTemplateId(Integer taskWaybillTemplateId) throws BusinessException;

    /**
     * 通过模板id获取配送地列表
     * @param taskWaybillTemplateId
     * @return
     * @throws BusinessException
     */
    List<TaskWaybillTemplateDestAddress> getTemplateDestAddressByTemplateId(Integer taskWaybillTemplateId) throws BusinessException;

    /**
     * 通过模板id获取所绑定的车辆
     * @param taskWaybillTemplateId
     * @return
     * @throws BusinessException
     */
    List<TaskWaybillTemplateTruckBinding> getTruckBindingByTemplateId(Integer taskWaybillTemplateId) throws BusinessException;


    /**
     * 执行定时下单
     *
     * @throws BusinessException
     */
    void doTask() throws BusinessException;


    /***
     * 通过id 获取 下单所需要的信息
     *
     * @param taskWaybillTemplateId
     *
     * @throws BusinessException
     * */
    WaybillDetailInfo getWaybillDetailInfo(Integer taskWaybillTemplateId, LoginUser loginUser) throws BusinessException;


    /***
     *  通过 客户id 查询相关模板
     *
     *
     * @param customerId 客户id
     * */
    List<TaskWaybillTemplate> findByCustomerId( Integer customerId ) throws BusinessException ;


    /***
     *
     * 更新模板
     *
     * @param taskWaybillTemplate 模板
     *
     * @param loginUser 登录用户
     *
     * */
    void updateTaskWaybillTemplate(TaskWaybillTemplate taskWaybillTemplate, LoginUser loginUser, Boolean isUpdateCustomerManagerId) throws BusinessException;
}
