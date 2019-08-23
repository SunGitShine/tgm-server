package com.juma.tgm.waybill.domain;

/**
 * Created by Administrator on 2017/3/9 0009.
 */
public class FilteredOrder {
    private String orderId;
    private String filterReason;


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getFilterReason() {
        return filterReason;
    }

    public void setFilterReason(String filterReason) {
        this.filterReason = filterReason;
    }

    public enum orderFilterReason{
        OVER_WEIGHT("货物超重需要分单"),
        OVER_VOLUME("货物体积超限需要分单"),
        BLACK_CUSTOMER("黑名单用户，不能派单"),
        BANNED_GOODS("违禁货物，不能派单"),
        UNSUITABLE("没有合适的车辆调派");

        private String reason;

        public String getReason() {
            return reason;
        }

        orderFilterReason(String reason) {
            this.reason=reason;

        }
    }


}
