package com.juma.tgm.common;

/**
 * 消息场景key
 * @ClassName: MsgScensekeyConstant
 * @Description:
 * @author: liang
 * @date: 2017-11-22 14:51
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
public interface MsgScenekeyConstant {

    /**
     * 落地配-货主端-客户经理代发单
     */
    String SCATTERED_MANAGER_CREATE_BILL_APP = "SCATTERED_MANAGER_CREATE_BILL_APP";

    /**
     * 落地配-货主端-派车成功
     */
    String SCATTERED_ASSIGNED_BILL = "SCATTERED_ASSIGNED_BILL";

    /**
     * 落地配-货主端-开始配送
     */
    String SCATTERED_START_DELIVERY = "SCATTERED_START_DELIVERY";

    /**
     * 落地配-货主端-完成配送
     */
    String SCATTERED_COMPLETE_DELIVERY = "SCATTERED_COMPLETE_DELIVERY";

    /**
     * 落地配-货主端-客户经理确认收到运费-app
     */
    String SCATTERED_CONFIRM_FREIGHT_APP = "SCATTERED_CONFIRM_FREIGHT_APP";

    /**
     * 落地配-货主端-客户经理确认已支付代收货款-app
     */
    String SCATTERED_CONFIRM_MAN_GOODS_FEE_APP = "SCATTERED_CONFIRM_MAN_GOODS_FEE_APP";

    /**
     * 落地配-货主端-取消运单-app
     */
    String SCATTERED_CANCEL_BILL_APP = "SCATTERED_CANCEL_BILL_APP";

    /**
     * 落地配-货主端-客户经理代发单-sms
     */
    String SCATTERED_MANAGER_CREATE_BILL_SMS = "SCATTERED_MANAGER_CREATE_BILL_SMS";

    /**
     * 落地配-货主端-经纪人确认已收款-sms
     */
    String SCATTERED_CONFIRM_FREIGHT_SMS = "SCATTERED_CONFIRM_FREIGHT_SMS";

    /**
     * 落地配-货主端-客户经理确认已支付代收货款-sms
     */
    String SCATTERED_CONFIRM_MAN_GOODS_FEE_SMS = "SCATTERED_CONFIRM_MAN_GOODS_FEE_SMS";

    /**
     * 落地配-货主端-取消运单-sms
     */
    String SCATTERED_CANCEL_BILL_SMS = "SCATTERED_CANCEL_BILL_SMS";

    /**
     * 落地配-客户经理-客户已下单-app
     */
    String SCATTERED_CARGOOWNER_CREATE_BILL_APP = "SCATTERED_CARGOOWNER_CREATE_BILL_APP";

    /**
     * 落地配-客户经理-派车成功-app
     */
    String SCATTERED_ASSIGNED_BILL_APP_FOR_MAN = "SCATTERED_ASSIGNED_BILL_APP_FOR_MAN";

    /**
     * 落地配-客户经理-开始配送-app
     */
    String SCATTERED_START_DELIVERY_APP_FOR_MAN = "SCATTERED_START_DELIVERY_APP_FOR_MAN";

    /**
     * 落地配-客户经理-结束配送-app
     */
    String SCATTERED_COMPLETE_DELIVERY_APP_FOR_MAN = "SCATTERED_COMPLETE_DELIVERY_APP_FOR_MAN";


    /**
     * 落地配-客户经理-运单被取消-app
     */
    String SCATTERED_CANCEL_BILL_APP_FOR_MAN = "SCATTERED_CANCEL_BILL_APP_FOR_MAN";

    /**
     * 落地配-客户经理-运单被取消-sms
     */
    String SCATTERED_CANCEL_BILL_SMS_FOR_MAN = "SCATTERED_CANCEL_BILL_SMS_FOR_MAN";

    /**
     * 落地配-客户经理-结束配送-sms
     */
    String SCATTERED_COMPLETE_DELIVERY_SMS_FOR_MAN = "SCATTERED_COMPLETE_DELIVERY_SMS_FOR_MAN";


}
