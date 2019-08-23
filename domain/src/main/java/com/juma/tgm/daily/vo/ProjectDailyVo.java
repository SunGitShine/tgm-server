package com.juma.tgm.daily.vo;

import com.juma.tgm.daily.domain.ProjectDaily;
import com.juma.tgm.daily.enums.ProjectDailyEnum;
import lombok.Data;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;

/**
 * @ClassName ProjectDailyVo
 * @Description 日报输出
 * @Author weilibin
 * @Date 2019-07-18 20:01
 * @Version 1.0.0
 */
public class ProjectDailyVo extends ProjectDaily {
    /**项目承诺毛利率*/
    private BigDecimal projectProfitRate;

    private String customerName;
    private String projectName;

    private Integer waybillTotal;

    private Integer waybillNotFinishNo;

    private Integer waybillNotConfirmedNo;

    private BigDecimal customerFreightWithTax;

    private BigDecimal vendorFreightWithTax;

    private BigDecimal lastCustomerFreightWithTax;

    private BigDecimal lastVendorFreightWithTax;

    private Integer importWaybillNumber;

    private String areaName;

    private boolean couldUpdate;

    private String dailyStatusDesc;
    private String standingBookStatusDesc;
    private String freightStatusDesc;

    private Boolean isHaveAdjustForWaybill;

    private BigDecimal customerFreightNotSelfVendorWithTax;

    private BigDecimal vendorFreightNotSelfVendorWithTax;

    public String getDailyStatusDesc() {
        if (StringUtils.isNotBlank(dailyStatusDesc)) {
            return dailyStatusDesc;
        }

        return ProjectDailyEnum.DailyStatus.getDescByCode(super.getDailyStatus());
    }

    public void setDailyStatusDesc(String dailyStatusDesc) {
        this.dailyStatusDesc = dailyStatusDesc;
    }

    public String getStandingBookStatusDesc() {
        if (StringUtils.isNotBlank(standingBookStatusDesc)) {
            return standingBookStatusDesc;
        }

        return ProjectDailyEnum.StandingBookStatus.getDescByCode(super.getStandingBookStatus());
    }

    public void setStandingBookStatusDesc(String standingBookStatusDesc) {
        this.standingBookStatusDesc = standingBookStatusDesc;
    }

    public String getFreightStatusDesc() {
        if (StringUtils.isNotBlank(standingBookStatusDesc)) {
            return freightStatusDesc;
        }

        return ProjectDailyEnum.FreightStatus.getDescByCode(super.getFreightStatus());
    }

    public void setFreightStatusDesc(String freightStatusDesc) {
        this.freightStatusDesc = freightStatusDesc;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }


    public BigDecimal getCustomerFreightWithTax() {
        return customerFreightWithTax;
    }

    public void setCustomerFreightWithTax(BigDecimal customerFreightWithTax) {
        this.customerFreightWithTax = customerFreightWithTax;
    }

    public BigDecimal getVendorFreightWithTax() {
        return vendorFreightWithTax;
    }

    public void setVendorFreightWithTax(BigDecimal vendorFreightWithTax) {
        this.vendorFreightWithTax = vendorFreightWithTax;
    }

    public BigDecimal getLastCustomerFreightWithTax() {
        return lastCustomerFreightWithTax;
    }

    public void setLastCustomerFreightWithTax(BigDecimal lastCustomerFreightWithTax) {
        this.lastCustomerFreightWithTax = lastCustomerFreightWithTax;
    }

    public BigDecimal getLastVendorFreightWithTax() {
        return lastVendorFreightWithTax;
    }

    public void setLastVendorFreightWithTax(BigDecimal lastVendorFreightWithTax) {
        this.lastVendorFreightWithTax = lastVendorFreightWithTax;
    }


    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getWaybillTotal() {
        return waybillTotal;
    }

    public void setWaybillTotal(Integer waybillTotal) {
        this.waybillTotal = waybillTotal;
    }

    public Integer getWaybillNotFinishNo() {
        return waybillNotFinishNo;
    }

    public void setWaybillNotFinishNo(Integer waybillNotFinishNo) {
        this.waybillNotFinishNo = waybillNotFinishNo;
    }

    public Integer getWaybillNotConfirmedNo() {
        return waybillNotConfirmedNo;
    }

    public void setWaybillNotConfirmedNo(Integer waybillNotConfirmedNo) {
        this.waybillNotConfirmedNo = waybillNotConfirmedNo;
    }

    public void setCouldUpdate(boolean couldUpdate) {
        this.couldUpdate = couldUpdate;
    }

    public boolean isCouldUpdate() {
        return couldUpdate;
    }

    public Integer getImportWaybillNumber() {
        return importWaybillNumber;
    }

    public void setImportWaybillNumber(Integer importWaybillNumber) {
        this.importWaybillNumber = importWaybillNumber;
    }

    public BigDecimal getProjectProfitRate() {
        return projectProfitRate;
    }

    public void setProjectProfitRate(BigDecimal projectProfitRate) {
        this.projectProfitRate = projectProfitRate;
    }

    public Boolean getHaveAdjustForWaybill() {
        return isHaveAdjustForWaybill;
    }

    public void setHaveAdjustForWaybill(Boolean haveAdjustForWaybill) {
        isHaveAdjustForWaybill = haveAdjustForWaybill;
    }

    public BigDecimal getCustomerFreightNotSelfVendorWithTax() {
        return customerFreightNotSelfVendorWithTax;
    }

    public void setCustomerFreightNotSelfVendorWithTax(BigDecimal customerFreightNotSelfVendorWithTax) {
        this.customerFreightNotSelfVendorWithTax = customerFreightNotSelfVendorWithTax;
    }

    public BigDecimal getVendorFreightNotSelfVendorWithTax() {
        return vendorFreightNotSelfVendorWithTax;
    }

    public void setVendorFreightNotSelfVendorWithTax(BigDecimal vendorFreightNotSelfVendorWithTax) {
        this.vendorFreightNotSelfVendorWithTax = vendorFreightNotSelfVendorWithTax;
    }
}
