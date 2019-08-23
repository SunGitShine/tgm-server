package com.juma.tgm.waybill.domain.vo;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/8/23 0023.
 */
public class ManagerAchieveMent implements Serializable {
    private static final long serialVersionUID = -2789647194248002484L;
    private Integer orderMoney;
    private Integer orderCount;
    private Integer customerCount;
    private String dateType;
    private String dateRangeStartDay;

    public ManagerAchieveMent() {
        this.orderMoney=0;
        this.orderCount=0;
        this.customerCount=0;
    }

    public String getDateType() {
        return dateType;
    }

    public void setDateType(String dateType) {
        this.dateType = dateType;
    }

    public String getDateRangeStartDay() {
        return dateRangeStartDay;
    }

    public void setDateRangeStartDay(String dateRangeStartDay) {
        this.dateRangeStartDay = dateRangeStartDay;
    }

    public Integer getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(Integer orderMoney) {
        this.orderMoney = orderMoney;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public Integer getCustomerCount() {
        return customerCount;
    }

    public void setCustomerCount(Integer customerCount) {
        this.customerCount = customerCount;
    }
}
