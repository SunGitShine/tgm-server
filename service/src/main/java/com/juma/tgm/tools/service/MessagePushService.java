package com.juma.tgm.tools.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.giants.common.exception.BusinessException;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.configure.domain.ConfigParam.ParamKey;
import com.juma.tgm.driver.domain.Driver;
import com.juma.tgm.waybill.domain.Waybill;

public interface MessagePushService {

    /**
     * 推送APP信息,用于通知使用，不支持关联推送
     * 
     * @param sceneKey
     *            配置KEY
     * @param extras
     *            参数
     * @param extMsgId
     *            外部消息Id(业务系统消息内容id 比如公告模块)
     * @param tos
     *            用户USERID
     */
    void pushAppMessage(String sceneKey, Map<String, Object> extras, boolean isMustRead, String extMsgId, String... tos);

    /**
     * 推送APP信息
     * 
     * @param sceneKey
     *            配置KEY
     * @param extras
     *            参数
     * @param tos
     *            用户USERID
     */
    void pushAppMessage(String sceneKey, Map<String, Object> extras, LoginUser loginUser, String... tos);

    /**
     * 推送Email信息
     * 
     * @param sceneKey
     *            配置KEY
     * @param extras
     *            参数
     * @param email
     *            邮箱
     */
    void pushEmailMessage(String sceneKey, Map<String, Object> extras, String... email);

    /**
     * 推送短信信息
     * 
     * @param sceneKey
     *            配置KEY
     * @param extras
     *            参数
     * @param mobile
     *            手机号
     */
    void pushSmsMessage(String sceneKey, Map<String, Object> extras, LoginUser loginUser, String... mobile);

    /**
     * 推送语音信息
     * 
     * @param sceneKey
     *            配置KEY
     * @param extras
     *            参数
     * @param mobile
     *            手机号
     */
    void pushVoiceMessage(String sceneKey, Map<String, Object> extras, String extMsgId, LoginUser loginUser,String... mobile);

    /**
     * 
     * @Description 微信推送消息
     * @author Libin.Wei
     * @Date 2017年1月10日 下午5:43:53
     * @param toUser
     * @param templateId
     *            模板ID
     * @param url
     *            跳转URL
     * @param dataMap
     */
    void pushWechatMessage(String toUser, String templateId, String url, Map<String, Object> dataMap);

    /**
     * 发送云之家通知
     * @param sourceId  业务主键id
     * @param title
     * @param content
     * @param phoneList
     */
    void cloudMessagePush(String authKey,String sourceId, String title, String content, List<String> phoneList, LoginUser loginUser) throws
            BusinessException;

    // ==================以下是业务消息块==============================

    /**
     * 邀请承运商
     * @param taskId
     * @param vendorId
     */
    void inviteVendor(Integer taskId,Integer vendorId,LoginUser loginUser);

    /**
     * 承运商接受任务
     * @param taskId
     * @param vendorId
     */
    void vendorReceive(Integer taskId,Integer vendorId,LoginUser loginUser);

    /**
     * 承运商拒绝任务
     * @param taskId
     * @param vendorId
     */
    void vendorRefuse(Integer taskId,Integer vendorId,LoginUser loginUser);

    /**
     * 结束任务
     * @param taskId
     */
    void finishTask(Integer taskId);

    /**
     * 暂停任务
     * @param taskId
     */
    void pauseTask(Integer taskId);

    /**
     * 过期任务
     * @param taskId
     */
    void expireTask(Integer taskId);

    /**
     * 
     * @Title: buildAppMsgParameter @Description: App消息参数 @param: @param
     *         waybillId @param: @return @return: Map<String,Object> @throws
     */
    Map<String, Object> buildAppMsgParameter(int waybillId);

    /**
     * 
     * @Title: robWaybillMessage @Description: 抢单 @param: @param
     *         waybillId @return: void @throws
     */
    void robWaybillMessage(int waybillId, LoginUser loginUser);

    /**
     * 
     * @Title: passDriverMessage @Description: 司机认证 @param: @return:
     *         void @throws
     */
    @Deprecated
    void passDriverMessage(int driverId);

    /**
     * 
     * @Title: carryFeeMessgae @Description: 搬运通知 @param: @param
     *         waybillId @return: void @throws
     */
    void carryFeeMessage(int waybillId,LoginUser loginUser);

    /**
     * 
     * @Title: truckLeaveFenceMessage @Description: 离开电子围栏 @param: @param
     *         waybillId @return: void @throws
     */
    void truckExitFenceMessage(int waybillId);

    /**
     * 
     * @Title: truckEntryFenceMessage @Description: 进入电子围栏 @param: @param
     *         waybillId @return: void @throws
     */
    void truckEntryFenceMessage(int waybillId);

    /**
     * 
     * @Title: robWaybillTimeoutMessage @Description: 抢单超时通知 @param: @param
     *         waybillId @return: void @throws
     */
    void robWaybillTimeoutMessage(int waybillId, Map<String, String> cacheMap,LoginUser loginUser);

    /**
     * 
     * @Title: planTimeChangeMessage @Description: 用车时间变化通知 @param: @param
     *         waybillId @return: void @throws
     */
    void planTimeChangeMessage(int waybillId);

    /**
     * 
     * @Title: timeoutNoReceived @Description: 超时未接单 @param: @param
     *         waybillId @return: void @throws
     */
    @Deprecated
    void timeoutNoReceived(int waybillId,LoginUser loginUser);

    /**
     * 
     * @Title: planDeliveryTimeRemind @Description: 用车时间提醒 @return: void @throws
     */
    void planDeliveryTimeRemind(int waybillId);

    /**
     * 
     * @Title: driverConfirmAssigned @Description: 司机确认被指派 @return: void @throws
     */
    void driverConfirmAssigned(int waybillId,LoginUser loginUser);

    /**
     * 
     * @Description 配置消息通知
     * @author Libin.Wei
     * @Date 2017年1月20日 上午11:32:04
     * @param smsKey
     *            短消息KEY，为空时不发送
     * @param emailKey
     *            邮件消息KEY，为空时不发送
     * @param tenantId
     *            部门ID
     * @param paramKey
     *            业务
     * @param dataMap
     *            参数
     */
    void pushConfigureMessage(String smsKey, String emailKey, Integer tenantId, String areaCode, ParamKey paramKey,
            Map<String, Object> dataMap, LoginUser loginUser);

    /**
     * 
     * @Description 派车通知
     * @author Libin.Wei
     * @Date 2017年1月12日 上午10:25:52
     * @param driver
     *            司机
     * @param goodsInfo
     *            货物信息
     * @param planDeliveryTime
     *            用车时间
     * @return
     */
    @Deprecated
    Map<String, Object> assignCarSuccessTemplate(Driver driver, String goodsInfo, Date planDeliveryTime);

    /**
     * 
     * @Description 司机到仓
     * @author Libin.Wei
     * @Date 2017年1月12日 上午10:25:52
     * @param plateNumber
     *            车牌号
     * @param arriveDepotTime
     *            到仓时间
     * @return
     */
    @Deprecated
    Map<String, Object> arriveDepotTemplate(String plateNumber, Date arriveDepotTime);

    /**
     * 
     * @Description 司机离仓
     * @author Libin.Wei
     * @Date 2017年1月12日 上午10:25:52
     * @param waybill
     *            运单
     * @param driver
     *            司机
     * @param truckTypeName
     *            车型
     * @param goodsInfo
     *            货物信息
     * @param plateNumber
     *            车牌号
     * @return
     */
    @Deprecated
    Map<String, Object> leaveDepotTemplate(Waybill waybill, Driver driver, String truckTypeName, String goodsInfo,
            String plateNumber);

    /**
     * 
     * @Description 配送完成
     * @author Libin.Wei
     * @Date 2017年1月12日 上午10:25:52
     * @param waybill
     *            运单
     * @return
     */
    @Deprecated
    Map<String, Object> waybillDeliveriedTemplate(Waybill waybill, String plateNumber);

    /**
     * 
     * @Description 提醒支付
     * @author Libin.Wei
     * @Date 2017年1月12日 上午10:25:52
     * @param waybill
     *            运单
     * @param goodsWeight
     *            货物重量
     * @return
     */
    @Deprecated
    Map<String, Object> waybillWatingPayTemplate(Waybill waybill, String goodsWeight);

    /**
     * 
     * @Description 提醒评价
     * @author Libin.Wei
     * @Date 2017年1月12日 上午10:25:52
     * @param waybill
     *            运单
     * @return
     */
    @Deprecated
    Map<String, Object> waybillEvaluateTemplate(Waybill waybill, Driver driver);

    /**
     * 
     * @Description 收到回单
     * @author Libin.Wei
     * @Date 2017年1月12日 上午10:25:52
     * @param waybill
     *            运单
     * @return
     */
    @Deprecated
    Map<String, Object> waybillHasNeedReceipTTemplate(Waybill waybill);
    
    /**
     * 
     * @Title: temperatureAlert   
     * @Description: 温度预警 
     * @param: @param waybillId
     * @param: @param loginUser      
     * @return: void      
     * @throws
     */
    void temperatureAlert(Integer waybillId,LoginUser loginUser);

}
