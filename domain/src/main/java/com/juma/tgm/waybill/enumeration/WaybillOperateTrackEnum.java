package com.juma.tgm.waybill.enumeration;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName WaybillOperateTrackEnum
 * @Description 运单操作轨迹枚举
 * @Author weilibin
 * @Date 2019-05-16 10:41
 * @Version 1.0.0
 */

public class WaybillOperateTrackEnum {

    public enum OperateType {
        CREATE_WAYBILL(1, "发单"),
        MANUAL_ASSIGN(2, "调度指派"),
        ASSIGNED(3, "司机抢单"),
        ASSIGNED_SYS(4, "指派车辆"),
        RECEIVED(5, "接单"),
        ARRIVE_DEPOT(6, "到达取货地"),
        LEAVE_DEPOT(7, "离开取货地"),
        DELIVERYING(8, "结束配送"),
        PAIED(9, "支付"),
        EVALUATE(10, "评价"),
        CANCEL(11, "取消运单"),
        RECEIVED_FREIGHT(12, "收到运费"),
        UPDATE_FREIGHT(13, "改价"),
        PERFECT_INFO_SAVE(14, "补充信息(保存)"),
        PERFECT_INFO(15, "补充信息(派车)"),
        UPDATE_WAITING_FREIGHT(16, "待支付改价"),
        ARRIVE_DESTINATION(17, "到达目的地"),
        LEAVE_DESTINATION(18, "离开目的地"),
        MODIFY_RECEIVE_POINT(19, "修改线路"),
        MODIFY_POINT_TRUCK(20, "更改派车"),
        ASSIGN_FEED_BACK(21, "不派车反馈"),
        ADD_RECEIVE_ADDRESS(22, "添加目的地"),
        DEL_RECEIVE_ADDRESS(23, "删除目的地"),
        MODIFY_PLAN_DELIVERY_TIME(24, "修改用车时间"),
        ADD_CARRY_COST(25, "添加搬运费"),
        UPDATE_RECEIVE_ADDRESS(26, "修改目的地"),
        ADD_DELIVERY_ADDRESS(27, "添加取货地"),
        UPDATE_DELIVERY_ADDRESS(28, "修改取货地"),
        DEL_DELIVERY_ADDRESS(29, "删除取货地"),
        UPDATE_FREIGHT_PASS(30, "改价审核已通过"),
        UPDATE_FREIGHT_NO_PASS(31, "改价审核未通过"),
        START_DELIVERY(32, "开始配送"),
        SHARE_BILL(33, "分享运单"),
        UPDATE_SHOW_4_DRIVER_FREIGHT(34, "分享运单"),
        CONFIRM_PROJECT_BILL_FREIGHT(35, "确认项目运单运费"),
        RECONCILIATION_FINISH(36, "对账完成"),
        CONSTRAINT_FINISH_WAYBILL(37, "运单强制结束配送"),
        COLSE_WAYBILL(38, "删除运单"),
        AI_MATCH_SUCCESS(39, "自动派车成功"),
        AI_DRIVER_NO_ANSWER(40, "自动派车司机没有响应"),
        AI_MATCH_EMPTY(41, "自动派车没有匹配的司机"),
        AI_MATCH_TIMEOUT(42, "自动派车匹配超时"),
        AI_DRIVER_ANSWER(43, "自动派车司机确认"),
        CONFIRM_RECEIPT(44, "确认收款"),
        IMPORT_WAYBILL(45, "导入运单"),
        CONFIRM_NEED_RECEIPT(46, "确认回单"),
        TRANSFORM_BILL(47, "转承运商");

        private int code;

        private String desc;

        private OperateType(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public int getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }

        private static final Map<Integer, OperateType> intToEnum = new HashMap<Integer, OperateType>();
        static {
            for (OperateType operateType : values()) {
                intToEnum.put(operateType.getCode(), operateType);
            }
        }

        public static OperateType fromInt(Integer symbol) {
            return intToEnum.get(symbol);
        }
    }

    public enum OperateApplication {
        CUSTOMER_SYS(1, "用车端"),
        DRIVER_SYS(2, "司机端"),
        BACKGROUND_SYS(3, "后台"),
        FRNCE(4, "电子围栏"),
        BACKGROUND_MAP_MONITOR(5, "在途监控"),
        CARGO_OWNER_CLINET(6, "货主端"),
        AI_SYS(7, "自动派车"),
        INTERFACE(8, "外部接口"),
        TASK_SYS(9, "系统任务");

        private int code;

        private String desc;

        private OperateApplication(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public int getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }

        private static final Map<Integer, OperateApplication> intToEnum = new HashMap<Integer, OperateApplication>();
        static {
            for (OperateApplication operatePort : values()) {
                intToEnum.put(operatePort.getCode(), operatePort);
            }
        }

        public static OperateApplication fromInt(Integer symbol) {
            return intToEnum.get(symbol);
        }
    }

    public enum DataSource {
        UNKNOWN(0, "未知来源"), PHONE_REPORT(1, "设备上报"), MONITOR_FIND(3, "在途监控"), BACKGROUND_SYS(4, "后台系统");

        private int code;

        private String desc;

        private DataSource(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public int getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }

        public static String getDescr(int code) {
            for (DataSource data : DataSource.values()) {
                if (code == data.getCode()) {
                    return data.getDesc();
                }
            }
            return null;
        }
    }
}
