package com.juma.tgm.waybill.domain.vo;

import com.giants.common.tools.Page;

import java.io.Serializable;

/**
 * @ClassName: CustomerManagerDebtOverviewVo
 * @Description:
 * @author: liang
 * @date: 2017-08-22 15:32
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
public class CustomerManagerDebtOverviewVo implements Serializable {

    private String timeRange;
    private String timeRangeName;

    private String fee;

    private Page<CustomerManagerDebtDetailVo> customerList;


    public String getTimeRange() {
        return timeRange;
    }

    public void setTimeRange(String timeRange) {
        this.timeRange = timeRange;
    }

    public String getTimeRangeName() {
        return timeRangeName;
    }

    public void setTimeRangeName(String timeRangeName) {
        this.timeRangeName = timeRangeName;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public Page<CustomerManagerDebtDetailVo> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(Page<CustomerManagerDebtDetailVo> customerList) {
        this.customerList = customerList;
    }
}
