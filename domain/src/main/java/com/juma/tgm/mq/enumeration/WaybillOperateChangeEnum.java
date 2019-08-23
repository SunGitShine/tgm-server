package com.juma.tgm.mq.enumeration;

/**
 * @ClassName WaybillOperateChangeEnum
 * @Description TODO
 * @Author weilibin
 * @Date 2019-08-06 16:39
 * @Version 1.0.0
 */

public enum WaybillOperateChangeEnum {

    WAYBILL_ASSIGN_TRUCK("运单派车"),
    WAYBILL_CHANGE_TRUCK("运单换车"),
    DRIVER_ARRIVE_DEPOT("司机到仓"),
    DRIVER_LEAVE_DEPOT("司机离仓"),
    WAYBILL_FINISH("运单完成配送"),
    WAYBILL_CANCEL("取消运单");


    private String desc;

    WaybillOperateChangeEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }}
