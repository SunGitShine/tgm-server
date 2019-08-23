package com.juma.tgm.fms.domain.v3.vo;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
 * 功能 : 
 * 错误校验信息搜集
 * @author : Bruce(刘正航) 09:57 2019-05-23
 */
public class AdjustItemValidHolder implements Serializable {
    /**是否跳过刷新临时表**/
    private boolean skipFreshTemp;
    /**是否有行级别的错误**/
    private boolean hasLineError;
    /**没有对账单号的运单**/
    private List<String> noReconciliationNos = Lists.newArrayList();
    /**有对账单号的运单**/
    private List<String> hasReconciliationNos = Lists.newArrayList();
    /**对账单号集合**/
    private Set<String> reconciliations = Sets.newConcurrentHashSet();
    /**重复运单数据集合**/
    private List<String> repeatWaybillNos = Lists.newArrayList();
    /**承运商-对账后-承运商ID集合**/
    private List<String> vendorIds = Lists.newArrayList();
    /**客户侧-对账前-客户ID**/
    private Set<Integer> customerIds = Sets.newConcurrentHashSet();
    /**运单对应的业务范围**/
    private Set<String> areaCodes = Sets.newConcurrentHashSet();
    /**日期限制**/
    private Integer dayLimit;
    /**运单可调金额上限**/
    private BigDecimal ceiling;
    /**运单可调金额下限**/
    private BigDecimal floor;
    /**调整单金额汇总**/
    private WaybillStatisticsAmountVO waybillStatisticsAmountVO;

    public boolean isSkipFreshTemp() {
        return skipFreshTemp;
    }

    public void setSkipFreshTemp(final boolean skipFreshTemp) {
        this.skipFreshTemp = skipFreshTemp;
    }

    public boolean isHasLineError() {
        return hasLineError;
    }

    public void setHasLineError(final boolean hasLineError) {
        this.hasLineError = hasLineError;
    }

    public List<String> getNoReconciliationNos() {
        return noReconciliationNos;
    }

    public void setNoReconciliationNos(final List<String> noReconciliationNos) {
        this.noReconciliationNos = noReconciliationNos;
    }

    public List<String> getHasReconciliationNos() {
        return hasReconciliationNos;
    }

    public void setHasReconciliationNos(final List<String> hasReconciliationNos) {
        this.hasReconciliationNos = hasReconciliationNos;
    }

    public Set<String> getReconciliations() {
        return reconciliations;
    }

    public void setReconciliations(final Set<String> reconciliations) {
        this.reconciliations = reconciliations;
    }

    public List<String> getRepeatWaybillNos() {
        return repeatWaybillNos;
    }

    public void setRepeatWaybillNos(final List<String> repeatWaybillNos) {
        this.repeatWaybillNos = repeatWaybillNos;
    }

    public List<String> getVendorIds() {
        return vendorIds;
    }

    public void setVendorIds(List<String> vendorIds) {
        this.vendorIds = vendorIds;
    }

    public Set<Integer> getCustomerIds() {
        return customerIds;
    }

    public void setCustomerIds(Set<Integer> customerIds) {
        this.customerIds = customerIds;
    }

    public Set<String> getAreaCodes() {
        return areaCodes;
    }

    public void setAreaCodes(Set<String> areaCodes) {
        this.areaCodes = areaCodes;
    }

    public Integer getDayLimit() {
        return dayLimit;
    }

    public void setDayLimit(Integer dayLimit) {
        this.dayLimit = dayLimit;
    }

    public BigDecimal getCeiling() {
        return ceiling;
    }

    public void setCeiling(BigDecimal ceiling) {
        this.ceiling = ceiling;
    }

    public BigDecimal getFloor() {
        return floor;
    }

    public void setFloor(BigDecimal floor) {
        this.floor = floor;
    }

    public WaybillStatisticsAmountVO getWaybillStatisticsAmountVO() {
        return waybillStatisticsAmountVO;
    }

    public void setWaybillStatisticsAmountVO(WaybillStatisticsAmountVO waybillStatisticsAmountVO) {
        this.waybillStatisticsAmountVO = waybillStatisticsAmountVO;
    }
}
