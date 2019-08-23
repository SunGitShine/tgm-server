package com.juma.tgm.mq.domain;

import com.juma.tgm.mq.enumeration.WaybillOperateChangeEnum;
import java.io.Serializable;

/**
 * @ClassName WaybillOperateChangeEvent
 * @Description 运单变化的事件
 * @Author weilibin
 * @Date 2019-08-06 16:38
 * @Version 1.0.0
 */

public class WaybillOperateChangeEvent implements Serializable {

    private Integer waybillId;
    private WaybillOperateChangeEnum waybillOperateChangeEnum;

    public Integer getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(Integer waybillId) {
        this.waybillId = waybillId;
    }

    public WaybillOperateChangeEnum getWaybillOperateChangeEnum() {
        return waybillOperateChangeEnum;
    }

    public void setWaybillOperateChangeEnum(WaybillOperateChangeEnum waybillOperateChangeEnum) {
        this.waybillOperateChangeEnum = waybillOperateChangeEnum;
    }
}
