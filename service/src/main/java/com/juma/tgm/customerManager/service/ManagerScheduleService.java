package com.juma.tgm.customerManager.service;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.common.ManagerScheduleType;
import com.juma.tgm.customerManager.domain.ManagerSchedule;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillParam;

/**
 *
 * 维护客户经理日程
 *
 * @ClassName: ManagerScheduleService
 * @Description:
 * @author: liang
 * @date: 2017-06-15 17:56
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
public interface ManagerScheduleService {

    /** 添加日程
     *
     * @param managerSchedule
     * @param loginUser
     * @return
     * @throws BusinessException
     */
    int add(ManagerSchedule managerSchedule, LoginUser loginUser) throws BusinessException;

    /**
     * 更新日程
     * @param managerSchedule
     * @param loginUser
     * @return
     * @throws BusinessException
     */
    void update(ManagerSchedule managerSchedule, LoginUser loginUser) throws BusinessException;

    /**
     * 添加过更新日程
     * @param managerSchedule
     * @param loginUser
     * @throws BusinessException
     */
    void saveOrUpdate(ManagerSchedule managerSchedule, LoginUser loginUser) throws BusinessException;


    /** 标记已读
     * @param  managerScheduleId
     * @param loginUser
     * @throws BusinessException
     */
    void Handled(int managerScheduleId, LoginUser loginUser) throws BusinessException;

    /** 未处理数量
     *
     * @param loginEmployee
     * @return
     * @throws BusinessException
     */
    int unhandledCount(LoginEmployee loginEmployee) throws BusinessException;

    /**
     *  按类型获取未完成列表
     *
     *  @param pageCondition
     * @param loginEmployee
     * @return
     * @throws BusinessException
     */
    Page<ManagerSchedule> getUnhandledList(PageCondition pageCondition, LoginEmployee loginEmployee) throws BusinessException;


    /**
     * 工具方法将模板转换为业务数据
     * @param scheduleTemplate
     * @param args
     * @return
     * @throws BusinessException
     */
    String convertTemplate( String scheduleTemplate, String... args) throws BusinessException;


    /**
     * 组装增加运费未完成事项
     * @param waybillParam
     * @param type
     * @return
     * @throws BusinessException
     */
    ManagerSchedule buildIncreaseCarryfeeSchedule(Waybill waybill, WaybillParam waybillParam, ManagerScheduleType type) throws BusinessException;


    /**
     *
     * 组装派车反馈未完成事项
     * @param type
     * @return
     * @throws BusinessException
     */
    ManagerSchedule buildAssignFeedbackSchedule(Waybill waybill, ManagerScheduleType type) throws BusinessException;


}
