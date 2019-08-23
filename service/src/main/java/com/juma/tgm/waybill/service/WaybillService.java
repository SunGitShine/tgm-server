package com.juma.tgm.waybill.service;

import com.juma.tgm.waybill.vo.WaybillArriveDoptTimeLimit;
import com.juma.tgm.waybill.vo.WaybillDriverVo;
import com.juma.tgm.waybill.vo.WaybillFilter;
import com.juma.tgm.waybill.vo.WaybillQuery;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginEcoUser;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.base.domain.PageConditionDomain;
import com.juma.tgm.driver.domain.DriverBaseInfo;
import com.juma.tgm.pay.domain.TransactionResponse;
import com.juma.tgm.user.domain.CurrentUser;
import com.juma.tgm.waybill.domain.*;
import com.juma.tgm.waybill.domain.Waybill.WaybillSource;
import com.juma.tgm.waybill.domain.bo.PrePaySign;
import com.juma.tgm.waybill.domain.view.SumForWaybill;
import com.juma.tgm.waybill.domain.vo.*;
import com.juma.tgm.waybill.vo.WaybillArriveDoptTimeLimit;
import com.juma.tgm.waybill.vo.WaybillFilter;
import com.juma.tgm.waybill.vo.WaybillQuery;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 运单管理service
 */
public interface WaybillService {
    
    /**
     *
     * fms使用接口
     *
     * 车辆未对账数据
     */
    @Deprecated
    SumForWaybill findUnReconciliationByPlateNumber(String plateNumber);

    /**
     * @throws @Title: buildDataFilterCondByDepartment
     * @Description: 数据权限过滤
     * @param: @param pageCondition
     * @param: @param currentUser
     * @return: void
     */
    PageCondition buildDataFilterCondByDepartment(PageCondition pageCondition, CurrentUser currentUser);

    /**
     * @throws @Title: simpleSearch
     * @Description: 简单查询
     * @param: @return
     * @param: @throws BusinessException
     * @return: Page<WaybillBo>
     */
    Page<Waybill> search(LoginUser loginUser, PageCondition pageCondition) throws BusinessException;

    /**
     * @throws @Title: simpleSearch
     * @Description: 简单查询运单基本信息
     * @param: @return
     * @param: @throws BusinessException
     * @return: Page<WaybillBo>
     */
    Page<Waybill> searchBasicInfo(LoginUser loginUser, PageCondition pageCondition) throws BusinessException;

    /**
     * 后台管理系统分页列表
     *
     * @param loginUser
     * @param pageCondition
     * @return
     * @throws BusinessException
     */
    Page<WaybillVO> searchForManageSys(LoginUser loginUser, PageCondition pageCondition) throws BusinessException;

    /**
     * @param waybillId
     * @return
     * @Description 根据运单ID获取运单信息
     * @author Libin.Wei
     * @Date 2017年1月23日 下午5:40:10
     */
    WaybillBo getWaybillBoById(Integer waybillId, LoginUser loginUser);

    /**
     * @throws
     * @Title: getAcceptableWaybillCount
     * @Description: 司机可见运单池数量
     * @return: int
     */
    int getAcceptableWaybillCount(PageCondition pageCondition, LoginUser loginUser) throws BusinessException;

    /**
     * @throws
     * @Title: getPageForAcceptableWaybillList
     * @Description: 司机可见运单列表详情
     * @return: Page<WaybillBo>
     */
    Page<WaybillBo> getPageForAcceptableWaybillList(PageCondition pageCondition, LoginUser loginUser) throws BusinessException;

    /**
     * @throws
     * @Title: getPageForTodoWaybillList
     * @Description: 司机待办任务
     * @return: Page<WaybillBo>
     */
    Page<WaybillBo> getPageForTodoWaybillList(PageCondition pageCondition, LoginUser loginUser) throws BusinessException;

    /**
     * @throws
     * @Title: receiveWaybill
     * @Description: 司机接单
     * @return: void
     */
    void receiveWaybill(Waybill waybill, LoginEcoUser loginEcoUser) throws BusinessException;

    /**
     * @throws
     * @Title: cancelWaybill
     * @Description: 客户取消运单
     * @return: void
     */
//    void cancelWaybill(Waybill waybill, LoginEcoUser loginEcoUser) throws BusinessException;

    /**
     * @throws
     * @Title: cancelWaybill
     * @Description: 客户经理(后台)取消运单
     * @return: void
     */
    void cancelWaybill(Integer waybillId, Waybill.CancelChannel cancelChannel, String waybillCancelRemark, LoginEmployee loginEmployee) throws BusinessException;


    /**
     * 4.6.3 版本后只有落地配业务使用
     * @throws
     * @Title: changeToPaied
     * @Description: 付款
     * @return: void
     */
    void changeToPaied(Integer waybillId, LoginUser loginUser) throws BusinessException;


    /**
     * @throws
     * @Title: changeToDeliveried
     * @Description: 司机配送完成
     * @return: void
     */
    void changeToDeliveried(Waybill waybill, LoginUser loginUser) throws BusinessException;


    /**
     * @throws
     * @Title: changeNewToManual
     * @Description: 后台修改派车方式
     * @return: void
     */
    void changeNewToManual(int waybillId, LoginUser loginUser) throws BusinessException;

    /**
     * @throws
     * @Title: changeToAssigned
     * @Description: 指定车辆
     * @return: void
     */
    void changeToAssigned(int waybillId, int driverId, int truckId, Integer vendorId, int receiveWay, String remark,
                          LoginUser loginUser) throws BusinessException;

    /**
     * 指派多辆车
     * @param waybills
     * @param loginUser
     * @throws BusinessException
     */
    void changeToAssignedBatch(List<Waybill> waybills, LoginUser loginUser) throws BusinessException;

    /**
     * @throws
     * @Title: changeCar @Description:
     * 更换车辆:运单已经指派了车辆，车辆不符合要求或其他原因，人工更换车辆
     * @return: void
     */
    void changeCar(int waybillId, int driverId, int truckId, Integer vendorId, int receiveWay, String remark,
                   LoginUser loginUser) throws BusinessException;

    /**
     * @throws
     * @Title: changeToWaitingReceive
     * @Description: 系统派车  司机抢单
     * @return: void
     */
    void changeToWaitingReceive(int waybillId, LoginUser loginUser) throws BusinessException;

    /**
     * @throws
     * @Title: createWaybill
     * @Description: 用户、客户经理、后台人员建单
     * @return: Integer
     */
    @Deprecated
    Integer createWaybill(WaybillBo waybillBo, WaybillSource waybillSource, LoginUser loginUser) throws BusinessException;

    /**
     * @throws
     * @Title: saveWaybillSnapshot
     * @Description: 后台保存订单快照
     * @return: void
     */
    void saveWaybillSnapshot(WaybillBo waybillBo, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * @throws
     * @Title: getWaybillStatusNotEnd
     * @Description: 客户经理端 得到未完成订单个数
     * @return: Integer
     */
    Integer getWaybillStatusNotEnd(LoginEmployee loginEmployee);

    /**
     * @param pageCondition
     * @return
     * @Description 货主端运单列表
     * @author Libin.Wei
     * @Date 2017年1月12日 上午11:11:19
     */
    Page<WaybillDetailInfo> searchPageList(PageCondition pageCondition, LoginEmployee loginEmployee);

    /**
     * @param pageCondition
     * @return
     * @Description 货主端运单列表（微信货主端）
     * @author Libin.Wei
     * @Date 2017年1月12日 上午11:11:19
     */
    Page<WaybillDetailInfo> searchWechatPageList(PageCondition pageCondition, LoginEcoUser loginEcoUser);


    /**
     * 根据ID获取运单信息
     *
     * @param waybillId
     */
    Waybill getWaybill(Integer waybillId);

    /**
     * 根据ID获取运单信息
     *
     * @param waybillId
     * @return
     * @throws BusinessException
     * @author Libin.Wei
     * @Date 2017年7月27日 上午9:23:48
     */
    Waybill getWaybillAndCheckExist(Integer waybillId) throws BusinessException;


    void updateWaybillStatusToDeliverying(Waybill waybill, LoginUser loginUser);


    /**
     * 用车端运单详情
     *
     * @return WaybillDetailInfo
     */
    WaybillDetailInfo getWaybillInfo(Integer waybillId, LoginUser loginUser);

    /**
     * 
     * 管理系统运单详情
     * 
     * @author Libin.Wei
     * @Date 2017年12月22日 下午4:06:17
     * @param waybillId
     * @param loginUser
     * @return
     */
    WaybillDetailInfo findWaybillDetailById(Integer waybillId, LoginUser loginUser);

    /**
     * 判断发单人是否有未完成订单
     *
     * @return Waybill
     */
    Waybill findWaybillByOwnerUser(LoginEmployee loginEmployee);

    /**
     * @throws
     * @Title: updateFreight
     * @Description: 司机加价
     * @return: Waybill
     */
    @Deprecated
    Waybill updateFreight(Waybill waybill, LoginEcoUser loginEcoUser) throws BusinessException;

    /**
     * @throws
     * @Title: updateFreight
     * @Description: 后台改价
     * @return: Waybill
     */
    Waybill updateFreight(Waybill waybill, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * 支付回调处理
     */
    void payCallback(TransactionResponse response) throws BusinessException;

    /**
     * 欲支付订单加密
     */
    PrePaySign doPrePaySign(Waybill waybill);

    /**
     * @param loginEcoUser
     * @return WaybillInfo
     * @Title: getWaybillInfo
     * @Description: 司机今日运单
     */
    WaybillInfo getWaybillInfo(LoginEcoUser loginEcoUser);

    /**
     * @param pageCondition
     * @param loginEcoUser
     * @return Page<WaybillBo>
     * @Title: getTodayWaitList
     * @Description: 司机今日待配送运单列表
     */
    Page<WaybillBo> getTodayWaitList(PageCondition pageCondition, LoginEcoUser loginEcoUser);

    /**
     * @param loginUser 当前登录人信息(用户中心提供的结构)
     * @return WaybillInfo
     * @Description 进行中的运单列表(对外接口)
     * @author Libin.Wei
     * @Date 2016年12月23日 下午4:05:54
     */
    WaybillInfo findHomePageInfo(LoginUser loginUser);
    
    /**
     * 
     * @Title: findWaitingAcceptWaybillV2   
     * @Description: 查找待确认的运单
     * @param: @param loginUser
     * @param: @return      
     * @return: List<Waybill>      
     * @throws
     */
    List<Waybill> findWaitingAcceptWaybillV2(LoginUser loginUser);
    
    
    
    /**
     * @param loginUser 当前登录人信息(用户中心提供的结构)
     * @return WaybillInfo
     * @Description 进行中的运单列表(对外接口)
     * @author Libin.Wei
     * @Date 2016年12月23日 下午4:05:54
     */
    WaybillInfo findHomePageInfoV2(LoginUser loginUser);
    
    
    /**
     * 根据TMS系统的司机ID获取待配送和配送中的运单
     *
     * @param driverId   TMS系统的司机ID：为空返回空集合
     * @param maxBackNum 最大返回数量：默认返回10条
     * @param orderBy    排序：默认运单状态倒叙，用车时间正序
     * @return List<Waybill>
     * @author Libin.Wei
     * @Date 2017年7月21日 下午3:48:04
     */
    List<Waybill> findRunningWaybillByDriverIdV2(Integer driverId, Integer maxBackNum, String orderBy, LoginUser loginUser);
    
    
    /**
     * 
     * @Title: findWaitingAcceptWaybill   
     * @Description: 查找待确认的运单
     * @param: @param loginUser
     * @param: @return      
     * @return: List<Waybill>      
     * @throws
     */
    List<Waybill> findWaitingAcceptWaybill(LoginUser loginUser);
    
    

    /**
     * @param pageCondition
     * @return List<Waybill>
     * @Title: getWaybillListByParam
     * @Description: 根据司机获取运单列表
     */
    List<Waybill> getWaybillListByParam(PageCondition pageCondition);

    /**
     * @param pageCondition
     * @return Waybill
     * @Title: getFreight
     * @Description: 获取税前税后运费
     */
    WaybillCountResponse getFreight(PageCondition pageCondition, LoginUser loginUser);

    /**
     * @param pageCondition
     * @param loginUser
     * @return int
     * @Title: searchCount
     * @Description: 客户经理(后台) 获取司机数(前端)
     */
    int searchCount(PageCondition pageCondition, LoginUser loginUser);


    /**
     * @return void
     * @Title: updateOvertimeWaybill
     * @Description: 查询超时运单，更改运单状态
     */
    void updateOvertimeWaybill(LoginUser loginUser);

    /**
     * @param waybill
     * @param loginUser
     * @return void
     * @Title: updateOvertimeWaybillHasClick
     * @Description: 超时运单被查看，更改运单置顶状态
     */
    void updateOvertimeWaybillHasClick(Waybill waybill, LoginUser loginUser);


    /**
     * @throws
     * @Title: hasNoPayWaybill
     * @Description: 客户经理未付款
     * @return: WaybillInfo
     */
    WaybillInfo hasNoPayWaybill(LoginEmployee loginEmployee);

    /**
     * @param waybill
     * @param loginUser
     * @return void
     * @Title: updateArriveDepotTime
     * @Description: 到达取货地时间
     */
    void updateArriveDepotTime(Waybill waybill, LoginUser loginUser) throws BusinessException;

    /**
     *  检查当前时间是否允许点击到仓
     *
     * @param planDeliveryTime
     * @return
     * @throws BusinessException
     */
    void checkTimeBeforeUpdateArriveDapot(Date planDeliveryTime) throws BusinessException;

    /**
     * @param waybill
     * @param loginUser
     * @return void
     * @Title: updateLeaveDepotTime
     * @Description: 离开取货地时间
     */
    void updateLeaveDepotTime(Waybill waybill, LoginUser loginUser) throws BusinessException;

    /**
     * @param waybill
     * @param loginUser
     * @return WaybillInfo
     * @Title: confirmToDepot
     * @Description: 计算司机点击到达目的地与取货地的距离
     */
    WaybillInfo updateConfirmToDepot(Waybill waybill, LoginUser loginUser) throws BusinessException;

    /**
     * @return void
     * @Title: pushUseCarTimeMsg
     * @Description: 还有30分钟就需要配送的运单给司机推送APP消息
     */
    void pushUseCarTimeMsg();

    /**
     * 4.6.3 版本后只有落地配业务使用
     * @throws
     * @Title: updateConfirmReceivedFreight
     * @Description: 司机确认收到运费
     * @return: void
     */
    void updateConfirmReceivedFreight(Waybill waybill, LoginEcoUser loginEcoUser) throws BusinessException;


    /**
     * @throws
     * @Title: buildOngoingWaybill
     * @Description: 司机获取配送中或即将配送的运单ID
     * @return: DriverBaseInfo
     */
    DriverBaseInfo buildOngoingWaybill(DriverBaseInfo info, LoginEcoUser loginEcoUser);

    /**
     * @param pageCondition
     * @param loginEmployee
     * @return PageConditionDomain
     * @Title: buildNewPageCondition
     * @Description: 构造后台与导出列表的查询条件
     */
    PageConditionDomain buildNewPageCondition(PageCondition pageCondition, LoginEmployee loginEmployee);

    /**
     * @throws @Title: getWaybillNo
     * @Description: 运单编号
     * @param: @return
     * @return: String
     */
//    String getWaybillNo();

    /**
     * 查询司机正在运输中的订单
     *
     * @param driverId
     * @return
     */
    Waybill getDeliveringWaybill(int driverId) throws BusinessException;

    /**
     * 获取运单设备轨迹信息
     *
     * @param waybill
     * @return
     */
    void getWaybillTraceInfo(Waybill waybill);

    /**
     * 运单表单校验
     *
     * @param waybill
     * @param truckRequire
     * @param deliveryAddress 不能为空
     * @param receiveAddress  不能为空
     */
    void checkCreateWaybill(Waybill waybill, TruckRequire truckRequire, List<WaybillDeliveryAddress> deliveryAddress, List<WaybillReceiveAddress> receiveAddress)
            throws BusinessException;

    /**
     * @throws @Title: fetchWaybill
     * @Description: 特殊页面 运单监控
     * @param: @param waybillId
     * @param: @return
     * @return: WaybillMonitor
     */
    WaybillMonitor fetchWaybill(String waybillNo) throws BusinessException;

    /**
     * @throws @Title: saveWaybillViewHistory
     * @Description: 埋点
     * @param: @param waybillId
     * @param: @param driverId 当前登陆司机id
     * @return: void
     */
    void saveWaybillViewHistory(Integer waybillId, Integer driverId);

    /**
     * @param waybillId 运单ID
     * @Description 将运单修改为已回单
     * @author Libin.Wei
     * @Date 2017年3月27日 上午9:52:31
     */
    void updateToHasNeedReceipt(Integer waybillId, LoginUser loginUser) throws BusinessException;

    /**
     * @throws
     * @Title: updateAssignCarFeedback
     * @Description: 后台 不派车反馈内容
     * @return: void
     */
    void updateAssignCarFeedback(Waybill waybill, LoginUser loginUser) throws BusinessException;

    /**
     * @throws
     * @Title: modifyPlanDeliveryTime
     * @Description: 客户经理 修改计划用车时间
     * @return: void
     */
    void modifyPlanDeliveryTimeByManager(Waybill waybill, LoginEmployee loginEmployee) throws BusinessException;
    
    
    /**
     * @throws
     * @Title: modifyPlanDeliveryTime
     * @Description: 司机 修改计划用车时间
     * @return: void
     */
    void modifyPlanDeliveryTimeBydriver(Waybill waybill, LoginEcoUser driverLoginEcoUser) throws BusinessException;


    /**
     * 补充配送目的地时更新运单信息
     *
     * @param waybillBo
     * @param loginUser
     */
    void updateWaybillByReceiveAddress(WaybillBo waybillBo, LoginUser loginUser);

    /**
     * 根据TMS系统的车辆ID获取待配送和配送中的运单
     *
     * @param truckId TMS系统的车辆ID
     * @return List<Waybill>
     * @author Libin.Wei
     * @Date 2017年4月14日 下午2:34:12
     */
    List<Waybill> findRunningWaybillByTruckId(Integer truckId);
    
    
    /**
     * 根据TMS系统的司机ID获取待配送和配送中的运单
     *
     * @param driverId   TMS系统的司机ID：为空返回空集合
     * @param maxBackNum 最大返回数量：默认返回10条
     * @param orderBy    排序：默认运单状态倒叙，用车时间正序
     * @return List<Waybill>
     * @author Libin.Wei
     * @Date 2017年7月21日 下午3:48:04
     */
    List<Waybill> findRunningWaybillByDriverId(Integer driverId, Integer maxBackNum, String orderBy, LoginUser loginUser);

    /**
     * 判断已经派车的运单能不能更换车辆，能够换车的条件：1、人工派车；2、用车时间2小时之前
     *
     * @param waybill 数据库中完整的运单信息
     * @return true 可以换车； false 不能换车
     * @author Libin.Wei
     * @Date 2017年4月18日 下午4:55:33
     */
    boolean allowChangeCar(Waybill waybill);

    /**
     * 根据运单展示状态statusView获取运单信息
     *
     * @param statusView 运单展示状态
     * @return List<Waybill>
     * @author Libin.Wei
     * @Date 2017年5月9日 下午2:43:47
     */
    List<Waybill> findWaybillByStatusView(Integer statusView);

    WaybillBo getDeliveriedWaybill(Integer driverId, LoginUser loginUser) throws BusinessException;

    /**
     * @param waybillId
     * @return WaybillBo
     * @throws BusinessException
     * @Title: findWaybillBo
     * @Description: 根据运单ID获取运单信息
     */
    WaybillBo findWaybillBo(Integer waybillId, LoginUser loginUser) throws BusinessException;


    /**
     * 计算运单价格
     *
     * @param formAddress   源地址
     * @param toAddress    多个目的地址
     * @param waybillBo     用车信息
     * @param loginUser 建单来源
     * @return DistanceAndPriceData
     */
    DistanceAndPriceData calWaybillPrice(CityAdressData formAddress, List<CityAdressData> toAddress, WaybillBo waybillBo, LoginUser loginUser) throws BusinessException;

    /**
     * 重新计算价格(项目运单不重新计算价格)
     *
     * @param waybillId
     * @param actualMileage
     * @param loginUser
     * @return
     * @throws BusinessException
     * @author Libin.Wei
     * @Date 2017年7月20日 下午4:34:58
     */
    DistanceAndPriceData recountThePrice(Integer waybillId, Integer actualMileage, Date finishTime, LoginUser loginUser) throws BusinessException;

    List<WaybillDeliveryAddress> getWaybillDeliveryLastAddress(Integer userId);

    List<WaybillReceiveAddress> getWaybillReceiveLastAddress(Integer userId);

    List<Map<Integer, Object>> getWaybillStatus();

    Waybill findWaybillByWaybillNo(String waybillNo, LoginUser loginUser);

    void updateWaybillByWbNo(Waybill waybill, LoginUser loginUser);

    /**
     *
     * @Title: createWaybill @Description: 创建运单 @param: @param waybillBo @param: @param waybillSource @param: @param ownerUserId @param: @param
     * createUserId @param: @return @param: @throws BusinessException @return: Integer @throws
     */

    /**
     * 批量创建订单，在同一个事务中多次调用建单方法
     *
     * @param waybillBo
     * @param waybillSource
     * @param customerLoginUser
     * @return
     * @throws BusinessException
     */
    // List<Integer> createWaybillBatch(WaybillBo waybillBo, WaybillSource waybillSource, CustomerLoginUser customerLoginUser) throws BusinessException;

    /**
     * @param waybillBo
     * @param loginEmployee
     * @return Integer
     * @throws BusinessException
     * @Title: updateWaybill
     * @Description: 后台完善运单
     */
    void updateWaybill(WaybillBo waybillBo, LoginEmployee loginEmployee) throws BusinessException;


    /**
     * @throws
     * @Title: getWaybillDeliverying
     * @Description: 配送中运单
     * @return: Waybill
     */
    Waybill getWaybillDeliverying(LoginUser loginUser);


    /**
     * 搜索运单基础信息
     *
     * @param pageCondition
     * @return
     * @throws BusinessException
     */
    Page<Waybill> searchWaybillBasicInfo(PageCondition pageCondition) throws BusinessException;


    /**
     * 微信端建单
     *
     * @param waybillBo
     * @param loginUser
     * @return
     * @throws BusinessException
     */
    Integer createWaybillForWechat(WaybillBo waybillBo, LoginUser loginUser) throws BusinessException;

    /**
     * @throws
     * @Title: settingExtraFee
     * @Description: 主要算司机结算价
     * @return: WaybillParam
     */
    WaybillParam settingExtraFee(WaybillBo waybillBo, WaybillParam waybillParam);


    /**
     * 客户经理修改运费价格
     * 1.派车前的运单，可随意修改价格
     * 2.派车后的运单，若修改的价格比之前的价格低，则修改成功后，提示价格审核中，且此时不能再修改价格
     * 3.审核通过，直接显示修改后的价格；审核拒绝，提示审核不通过，并可重新修改价格
     * 4.若是修改的价格比之前的高，则直接修改成功；且可以再次修改
     *
     * @param waybill
     * @param loginEmployee
     */
    Waybill customerManagerModifyFreight(Waybill waybill, LoginEmployee loginEmployee) throws BusinessException;


    /**
     * 客户经理修改司机结算价
     * @param waybill
     * @param loginEmployee
     * @throws BusinessException
     */
    void customerManagerModifyShow4DriverFreight(Waybill waybill, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * 改价审核通过：返回改价的变动信息
     *
     * @param waybill
     * @param loginUser
     * @return String 改价的变动信息
     * @throws BusinessException
     * @author Libin.Wei
     * @Date 2017年7月28日 下午6:08:56
     */
    String updateFreightAuditToPassOrFailed(Waybill waybill, LoginUser loginUser) throws BusinessException;


    /**
     * 运单加跑;包含原运单司机信息;重新指定用车时间
     * （可能存在司机班次冲突）
     *
     * @param waybillBo
     * @param waybillSource
     * @param loginUser
     * @return
     * @throws BusinessException
     */
    Integer createWaybillWithDriver(WaybillBo waybillBo, WaybillSource waybillSource, LoginUser loginUser) throws BusinessException;


    /**
     * 业绩
     *
     * @param loginEmployee
     * @return
     * @throws BusinessException
     */
    CustomerPerformanceVo getCustomerManPerformanceOverall(LoginEmployee loginEmployee) throws BusinessException;

    /**
     * 欠款统计
     *
     * @return
     * @throws BusinessException
     */
    CustomerManagerDebtVo getCustomerManDebtOverall(LoginEmployee loginEmployee) throws BusinessException;

    /**
     * 欠款详情
     *
     * @param pageCondition timeRange
     * @param loginEmployee
     * @return
     * @throws BusinessException
     */
    CustomerManagerDebtOverviewVo getSeparationDebt(PageCondition pageCondition, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * 客户欠款明细
     *
     * @param pageCondition timeRange customerId
     * @param loginEmployee
     * @return
     * @throws BusinessException
     */
    CustomerManagerDebtDetailVo getCustomerDebtDetail(PageCondition pageCondition, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * 获取搬运费
     * @param waybillBo
     * @return
     */
    WaybillParam buildCarryFee(WaybillBo waybillBo);



    /**
     * 根据example参数，批量更新newValue字段
     * @param example 条件
     * @param newValue 新的值
     * @param loginUser
     * @throws BusinessException
     */
    void updateWaybillBatch(Waybill example, Waybill newValue, LoginUser loginUser) throws BusinessException;


    /**
     * 计算项目运单费用
     * @param waybillId
     * @param result
     * @param result
     * @throws BusinessException
     */
    Waybill calculateProjectFreight(int waybillId, Waybill result) throws BusinessException;

    /**
     * 增加运费
     *
     * @param waybillId
     * @param loginEmployee
     * @throws BusinessException
     */
    void addPriceWaybill(Integer waybillId, Integer addPrice, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * 修改运单状态
     * @param waybillId
     * @param loginEmployee
     * @throws BusinessException
     */
    void updatePayWaybillStatus(String waybillId, Integer status, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * 
     * 派车待定
     * 
     * @author Libin.Wei
     * @Date 2017年11月20日 上午11:25:44
     * @param waybillId
     * @throws BusinessException
     */
    void updateUndetermined(int waybillId, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * 更改业务区域
     * 
     * @author Libin.Wei
     * @Date 2017年11月20日 上午11:25:44
     * @param waybillId
     * @throws BusinessException
     */
    void updateAreaCode(int waybillId, String areaCode, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * 更改客户经理
     * 
     * @author Libin.Wei
     * @Date 2017年11月20日 上午11:25:44
     * @param waybillId
     * @throws BusinessException
     */
    void updateCustomerManagerId(int waybillId, int customerManagerId, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * 关闭运单
     * @author Libin.Wei
     * @Date 2017年12月8日 下午5:37:43
     * @throws BusinessException
     */
    void doCloseWaybill(int waybillId, LoginUser loginUser) throws BusinessException;


    /***
     *
     * 按运单 ids 获取 运单列表
     *
     * @param waybillIds 运单 ids
     *
     * @param loginUser 登录用户
     * */
    List<Waybill> findByWaybillIds ( List<Integer> waybillIds , LoginUser loginUser ) throws  BusinessException;

    /**根据运单ID集合,获取运单数据**/
    List<Waybill> findByWaybillIds(List<Integer> waybillIds) throws BusinessException;

    /**
     * 按运单 nos 获取 运单列表
     * @param waybillNos
     * @return
     * @throws BusinessException
     */
    List<Waybill> findByWaybillNos(List<String> waybillNos) throws BusinessException;

    /**根据时间过滤**/
    List<Waybill> findByWaybillByFilter(WaybillFilter waybillFilter) throws BusinessException;

    /**
     * 计算并组装专车价格 <br/>
     * <b>注意：这是非标准计价方式</b>
     * @param dp
     * @param loginUser
     * @return
     */
    DistanceAndPriceData doCalculateJumaLogisticsPrice(DistanceAndPriceParamVo dp, LoginUser loginUser) throws BusinessException;

    /**
     * 批量更新应收对账单号
     * @param items
     */
    void batchUpdateReceivableReconcilicationNo(List<Waybill> items);

    /**
     * 批量更新运单承运商id
     * @param vendorId
     * @param waybillNos
     */
    void batchUpdateVendor(Integer vendorId, List<String> waybillNos);

    /**
     * 司机未上传回单的运单数量：司机平台化使用
     *
     * @param listStatusView 运单配送的状态
     * @param loginUser
     *
     * @return 默认返回0
     *
     * @throws BusinessException
     */
    Integer countDriverNoUploadReceiptWaybill(List<Integer> listStatusView, LoginUser loginUser)
        throws BusinessException;

    /**
     * 根据条件返回运单列表：司机平台化使用
     *
     * @param waybillFilter
     * @param callbackPageSize 返回条数，最大200
     * @param orderBy 排序：XXX ASC、XXX DESC
     * @param loginUser
     *
     * @return
     *
     * @throws BusinessException
     */
    List<WaybillQuery> listWayillForDriver(WaybillFilter waybillFilter, Integer callbackPageSize, String orderBy,
        LoginUser loginUser) throws BusinessException;

    /**
     * 获取司机到仓签到时间限制：司机平台化使用
     *
     * @return
     */
    WaybillArriveDoptTimeLimit loadWaybillArriveDoptTimeLimit();

    /**
     * 司机到仓签到提醒：司机平台化使用
     *
     * @param aheadTime    提前提醒时间，单位分钟，必填
     * @param cycleMinutes 循环周期，单位分钟，必填
     * @param appSceneKey  配置在消息中心的APP模版key，如果需要推送APP消息，则必填
     * @param smsSceneKey  配置在消息中心的短信模版key，如果推送短信，则必填
     * @param isNeedTenant 是否需要租户，若需要，信息会根据运单的租户推送，默认不需要
     */
    void remindDriverClickArriveDopt(Integer aheadTime, Integer cycleMinutes, String appSceneKey, String smsSceneKey,
        Boolean isNeedTenant);

    /**
     * 承运商下的所有司机新消息及司机的运单数量：司机平台化使用
     * @param vendorId
     * @param startPlanDeliveryTime 非必填，但是必须和endPlanDeliveryTime成对使用：yyyy-mm-dd HH:MM:SS
     * @param endPlanDeliveryTime 非必填，但是必须和startPlanDeliveryTime成对使用：yyyy-mm-dd HH:MM:SS
     * @param listStatusView 非必填
     * @return
     */
    List<WaybillDriverVo> listWaybillDriverByVendorId(Integer vendorId, String startPlanDeliveryTime,
        String endPlanDeliveryTime, List<Integer> listStatusView);

    /**
     * 司机端分页列表：司机平台化使用
     *
     * @param pageCondition
     * @param loginUser
     *
     * @return
     *
     * @throws BusinessException
     */
    Page<WaybillBo> searchForDriver(PageCondition pageCondition, LoginUser loginUser) throws BusinessException;

    /**
     * 承运商端分页列表：司机平台化使用
     *
     * @param pageCondition
     * @param loginUser
     *
     * @return
     *
     * @throws BusinessException
     */
    Page<WaybillBo> searchForVendor(PageCondition pageCondition, LoginUser loginUser) throws BusinessException;
}