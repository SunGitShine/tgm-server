package com.juma.tgm.mq.enumeration;

/**
 * @ClassName MsgEventEnum
 * @Description 消息调用接口枚举
 * @Author weilibin
 * @Date 2019-05-15 17:06
 * @Version 1.0.0
 */

public enum MsgEventEnum {
    //  标准
    STANDARD,
    // 需要租户
    WITH_TENANT,
    //
    NEED_MUST_ID;
}
