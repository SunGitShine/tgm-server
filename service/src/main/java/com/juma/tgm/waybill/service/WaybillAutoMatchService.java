package com.juma.tgm.waybill.service;

import java.util.List;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.waybill.domain.ToAutoMatchWaybill;
import com.juma.tgm.waybill.domain.WaybillBo;
import com.juma.tgm.waybill.domain.WaybillNoFinish;

public interface WaybillAutoMatchService {

    /**
     * @throws @Title:
     *             fetchWaybillPlan
     * @Description: 获取预约订单 自动派车
     * @param: @return
     * @return: List<WaybillPlan>
     */
    ToAutoMatchWaybill findToAutoMatchWaybillPlan(List<String> areaCodes, LoginUser loginUser);

    /**
     * @throws @Title:
     *             searchNoFinishWaybill
     * @throws @Title:
     *             searchNoFinishWaybill
     * @Description: 已接单未配送完成的单子
     * @param: @param
     *             condition
     * @param: @return
     * @return: Page<WaybillNoFinish>
     */
    Page<WaybillNoFinish> searchNoFinishWaybill(PageCondition condition);

    /**
     * @param condition
     * @param loginEmployee
     * @return
     * @Description 匹配成功的运单列表
     * @author Libin.Wei
     * @Date 2017年3月27日 下午5:27:10
     */
    Page<WaybillBo> findToAutoMatchWaybillList(PageCondition condition, LoginEmployee loginEmployee);

    /**
     * 
     * 解绑原因
     * 
     * @author Libin.Wei
     * @Date 2017年11月20日 上午11:21:24
     * @param waybillId
     * @param unbundlingReason
     * @throws BusinessException
     */
    void updateWaybillUnbundling(int waybillId, String unbundlingReason, LoginUser loginUser) throws BusinessException;
}
