package com.juma.tgm.waybill.domain;

import java.io.Serializable;
import java.util.List;

import com.juma.tgm.crm.domain.IncomeStatistics;
import com.juma.tgm.crm.domain.YesterdayIncomeInfo;
import com.juma.tgm.customer.domain.TruckCustomer;

public class WaybillInfo implements Serializable {

    private static final long serialVersionUID = 6665703517567334978L;
    // 配送中的运单
    private WaybillBo receiveingWaybill;
    // 用车要求
    private TruckRequire truckRequire;
    // 下一单
    private WaybillBo nextWaybill;
    // 运单信息
    private Waybill waybill;
    // 运单信息列表
    private List<WaybillInfo> waybillInfoList;
    // 收入统计
    private IncomeStatistics incomeStatistics;
    // 昨日收入信息
    private YesterdayIncomeInfo yesterdayIncomeInfo;
    // 用车人
    private TruckCustomer truckCustomer;
    // 目的地数量
    private Integer destinationNo;
    // 已完成数量
    private Integer paiedNo;
    // 标识
    private boolean identification = false;
    // 距离
    private Integer distance;
    
    public WaybillBo getReceiveingWaybill() {
        return receiveingWaybill;
    }

    public void setReceiveingWaybill(WaybillBo receiveingWaybill) {
        this.receiveingWaybill = receiveingWaybill;
    }

    public TruckRequire getTruckRequire() {
        return truckRequire;
    }

    public void setTruckRequire(TruckRequire truckRequire) {
        this.truckRequire = truckRequire;
    }

    public WaybillBo getNextWaybill() {
        return nextWaybill;
    }

    public void setNextWaybill(WaybillBo nextWaybill) {
        this.nextWaybill = nextWaybill;
    }

    public Waybill getWaybill() {
        return waybill;
    }

    public void setWaybill(Waybill waybill) {
        this.waybill = waybill;
    }

    public List<WaybillInfo> getWaybillInfoList() {
        return waybillInfoList;
    }

    public void setWaybillInfoList(List<WaybillInfo> waybillInfoList) {
        this.waybillInfoList = waybillInfoList;
    }

    public IncomeStatistics getIncomeStatistics() {
        return incomeStatistics;
    }

    public void setIncomeStatistics(IncomeStatistics incomeStatistics) {
        this.incomeStatistics = incomeStatistics;
    }

    public YesterdayIncomeInfo getYesterdayIncomeInfo() {
        return yesterdayIncomeInfo;
    }

    public void setYesterdayIncomeInfo(YesterdayIncomeInfo yesterdayIncomeInfo) {
        this.yesterdayIncomeInfo = yesterdayIncomeInfo;
    }

    public TruckCustomer getTruckCustomer() {
        return truckCustomer;
    }

    public void setTruckCustomer(TruckCustomer truckCustomer) {
        this.truckCustomer = truckCustomer;
    }

    public Integer getDestinationNo() {
        return destinationNo;
    }

    public void setDestinationNo(Integer destinationNo) {
        this.destinationNo = destinationNo;
    }

    public Integer getPaiedNo() {
        return paiedNo;
    }

    public void setPaiedNo(Integer paiedNo) {
        this.paiedNo = paiedNo;
    }

    public boolean isIdentification() {
        return identification;
    }

    public void setIdentification(boolean identification) {
        this.identification = identification;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

}
