package com.juma.tgm.project.vo;

import com.juma.tgm.project.domain.Project;

import java.io.Serializable;
import java.math.BigDecimal;


public class ProjectHeadDetailVo extends Project implements Serializable {
    /**
     * 累计运费
     */
    private String cumulativeFreigh;
    /**
     *累计运单数
     */
    private String cumulativeWaybillNum;
    /**
     *服务车辆数
     */
    private String serviceVehicleNum;
    /**
     *累计请款金额
     */
    private BigDecimal cumulativeRequestFreigh;
    /**
     *7日累计运费
     */
    private String cumulativeFreigh7Days;
    /**
     *累计运费环比
     */
    private String ringRationOfFreigh;
    /**
     *7日累计运单数
     */
    private String cumulativeWaybillNum7Days;
    /**
     *累计运单数环比
     */
    private String ringRationOfWaybillNum;


    public String getCumulativeFreigh() {
        return cumulativeFreigh;
    }

    public void setCumulativeFreigh(String cumulativeFreigh) {
        this.cumulativeFreigh = cumulativeFreigh;
    }

    public String getCumulativeWaybillNum() {
        return cumulativeWaybillNum;
    }

    public void setCumulativeWaybillNum(String cumulativeWaybillNum) {
        this.cumulativeWaybillNum = cumulativeWaybillNum;
    }

    public String getServiceVehicleNum() {
        return serviceVehicleNum;
    }

    public void setServiceVehicleNum(String serviceVehicleNum) {
        this.serviceVehicleNum = serviceVehicleNum;
    }

    public BigDecimal getCumulativeRequestFreigh() {
        return cumulativeRequestFreigh;
    }

    public void setCumulativeRequestFreigh(BigDecimal cumulativeRequestFreigh) {
        this.cumulativeRequestFreigh = cumulativeRequestFreigh;
    }

    public String getCumulativeFreigh7Days() {
        return cumulativeFreigh7Days;
    }

    public void setCumulativeFreigh7Days(String cumulativeFreigh7Days) {
        this.cumulativeFreigh7Days = cumulativeFreigh7Days;
    }

    public String getRingRationOfFreigh() {
        return ringRationOfFreigh;
    }

    public void setRingRationOfFreigh(String ringRationOfFreigh) {
        this.ringRationOfFreigh = ringRationOfFreigh;
    }

    public String getCumulativeWaybillNum7Days() {
        return cumulativeWaybillNum7Days;
    }

    public void setCumulativeWaybillNum7Days(String cumulativeWaybillNum7Days) {
        this.cumulativeWaybillNum7Days = cumulativeWaybillNum7Days;
    }

    public String getRingRationOfWaybillNum() {
        return ringRationOfWaybillNum;
    }

    public void setRingRationOfWaybillNum(String ringRationOfWaybillNum) {
        this.ringRationOfWaybillNum = ringRationOfWaybillNum;
    }
}
