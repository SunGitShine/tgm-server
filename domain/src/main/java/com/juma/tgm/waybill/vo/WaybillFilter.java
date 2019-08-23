package com.juma.tgm.waybill.vo;

import com.juma.tgm.waybill.domain.Waybill;

import java.util.Date;
import java.util.List;

/**
 * @ClassName WaybillFilter
 * @Description TODO
 * @Author weilibin
 * @Date 2019-06-12 16:22
 * @Version 1.0.0
 */

public class WaybillFilter extends Waybill {

    private String startPlanDeliveryTime;
    private String endPlanDeliveryTime;
    private List<String> areaCodeList;
    private List<Integer> statusViews;
    private Date startTime;
    private Date endTime;

    public String getStartPlanDeliveryTime() {
        return startPlanDeliveryTime;
    }

    public void setStartPlanDeliveryTime(String startPlanDeliveryTime) {
        this.startPlanDeliveryTime = startPlanDeliveryTime;
    }

    public String getEndPlanDeliveryTime() {
        return endPlanDeliveryTime;
    }

    public void setEndPlanDeliveryTime(String endPlanDeliveryTime) {
        this.endPlanDeliveryTime = endPlanDeliveryTime;
    }

    public List<String> getAreaCodeList() {
        return areaCodeList;
    }

    public void setAreaCodeList(List<String> areaCodeList) {
        this.areaCodeList = areaCodeList;
    }

    public List<Integer> getStatusViews() {
        return statusViews;
    }

    public void setStatusViews(List<Integer> statusViews) {
        this.statusViews = statusViews;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
