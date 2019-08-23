package com.juma.tgm.truck.domain.vo;

import com.juma.tgm.waybill.domain.TruckRequire;
import com.juma.tgm.waybill.domain.Waybill;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 车队搜索参数vo
 *
 * @ClassName: TruckFleetQueryVo
 * @Description:
 * @author: liang
 * @date: 2018-09-02 15:25
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
@ApiModel(value="TruckFleetQueryVo", description="车队搜索参数vo")
public class TruckFleetQueryVo implements Serializable {

    /**
     * 运单信息
     */
    private Waybill waybill;

    /**
     * 用车要求
     */
    private TruckRequire truckRequire;

    /**
     * 车队名称
     */
    private String keyword;

    /**
     * 车辆ID
     */
    private Integer truckId;


    /**
     * 客户经理id
     *
     */
    private Integer employeeId;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getTruckId() {
        return truckId;
    }

    public void setTruckId(Integer truckId) {
        this.truckId = truckId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    @ApiModelProperty(value = "运单信息,用于判断用车时间和车辆班次是否冲突", allowableValues = "planDeliveryTime")
    public Waybill getWaybill() {
        return waybill;
    }

    public void setWaybill(Waybill waybill) {
        this.waybill = waybill;
    }

    @ApiModelProperty(value = "用车要求,用于过滤车队信息", allowableValues = "TruckTypeId,AdditionalFunctionIds")
    public TruckRequire getTruckRequire() {
        return truckRequire;
    }

    public void setTruckRequire(TruckRequire truckRequire) {
        this.truckRequire = truckRequire;
    }
}
