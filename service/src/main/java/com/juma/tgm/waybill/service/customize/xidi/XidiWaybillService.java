package com.juma.tgm.waybill.service.customize.xidi;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginEcoUser;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillBo;
import com.juma.tgm.waybill.domain.WaybillDetailInfo;

/**
 * 希地租户运单定制需求类
 *
 * @ClassName: XidiWaybillService
 * @Description:
 * @author: liang
 * @date: 2018-03-07 11:01
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
public interface XidiWaybillService {

    /**
     * 重新派车
     * @param waybillId
     * @param driverId
     * @param truckId
     * @param flightId
     * @param receiveWay
     * @param remark
     * @param loginUser
     * @throws BusinessException
     */
    void changeCar(int waybillId, int driverId, int truckId, int flightId, int receiveWay, String remark, LoginUser loginUser) throws BusinessException;


    /**
     * 取消运单
     *
     * @param waybillId
     * @param cancelChannel
     * @param waybillCancelRemark
     * @param loginEmployee
     * @throws BusinessException
     */
    void cancelWaybill(Integer waybillId, Waybill.CancelChannel cancelChannel, String waybillCancelRemark, LoginEmployee loginEmployee) throws BusinessException;


    /**
     * 司机到仓
     * @param waybill
     * @param loginEcoUser
     * @throws BusinessException
     */
    void updateArriveDepotTime(Waybill waybill, LoginEcoUser loginEcoUser) throws BusinessException;


    /**
     * 司机离仓
     * @param waybill
     * @param loginEcoUser
     * @throws BusinessException
     */
    void updateLeaveDepotTime(Waybill waybill, LoginEcoUser loginEcoUser) throws BusinessException;

    /**
     * 任务列表
     * @param pageCondition
     * @param loginUser
     * @return
     * @throws BusinessException
     */
    Page<WaybillBo> getPageForTodoWaybillList(PageCondition pageCondition, LoginUser loginUser) throws BusinessException;

    /**
     * 运单池(待接单)
     * @param pageCondition
     * @param loginUser
     * @return
     * @throws BusinessException
     */
    Page<WaybillBo> getPageForAcceptableWaybillList(PageCondition pageCondition, LoginUser loginUser) throws BusinessException;
    
    /**
     * 用车端运单详情
     * @param waybillId
     * @param loginUser
     * @return
     * @throws BusinessException
     */
    WaybillDetailInfo getWaybillInfo(Integer waybillId, LoginUser loginUser) throws BusinessException;

    

    /**
     * 判断已经派车的运单能不能更换车辆，能够换车的条件：1、人工派车；2、用车时间2小时之前
     * @param waybill
     * @return
     * @throws BusinessException
     */
    boolean allowChangeCar(Waybill waybill) throws BusinessException;

    /**
     * 希地运单搜索
     * @param pageCondition
     * @param loginUser
     * @return
     * @throws BusinessException
     */
    Page<Waybill> search(PageCondition pageCondition, LoginUser loginUser) throws BusinessException;


    /**
     * 指派车辆
     * @param waybillId
     * @param driverId
     * @param truckId
     * @param flightId
     * @param receiveWay
     * @param remark
     * @param loginUser
     * @throws BusinessException
     */
    void changeToAssigned(int waybillId, int driverId, int truckId, int flightId, int receiveWay, String remark, LoginUser loginUser) throws BusinessException;

    /**
     * 获取运单详细信息
     * @param waybillId
     * @param loginUser
     * @return
     * @throws BusinessException
     */
    WaybillDetailInfo findWaybillDetailById(Integer waybillId, LoginUser loginUser) throws BusinessException;


    /**
     * 临时添加
     * @throws
     * @Title: changeToDeliveried
     * @Description: 司机配送完成
     * @return: void
     */
    void changeToDeliveried(Waybill waybill, LoginUser loginUser) throws BusinessException;

}
