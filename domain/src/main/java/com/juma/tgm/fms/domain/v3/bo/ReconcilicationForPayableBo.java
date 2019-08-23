package com.juma.tgm.fms.domain.v3.bo;

import com.juma.tgm.fms.domain.v3.ReconcilicationForPayable;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ReconcilicationForPayableBo extends ReconcilicationForPayable implements Serializable {

    /**承运商数量*/
    private Integer vendorCount;
    /**运单数量*/
    private Integer waybillCount;
    /**承运商含税总额*/
    private BigDecimal sumSettleFreight;
    /**承运商不含税总额*/
    private BigDecimal sumNonSettleFreight;
    private List<Integer> reconciliationIds = new ArrayList<Integer>();
    /**子公司名称*/
    private String departmentName;
    /**子公司更改记录*/
    private String changRecord;
    /**业务区域名称*/
    private String areaCodeName;
    /**签约子公司*/
    private String signedDepartmentName;
    /**是否有对账凭证，0：未上传，1：已上传*/
    private boolean hasEvidence;
    /**调整金额*/
    private BigDecimal adjustFreight;
    /**对账后调整金额*/
    private BigDecimal sumAdjustFreight;
    /**最终含税金额*/
    private BigDecimal sumFreightWithTax;
    /**客户侧含税金额*/
    private BigDecimal customerFreightWithTax;
    /**承诺毛利率*/
    private String profitRatePromise;
    /**对账时毛利额*/
    private BigDecimal profit;
    /**对账时毛利率*/
    private String profitRate;
    /**对账时与承诺毛利差*/
    private String profitRateDiffer;
    /**调整后的毛利额*/
    private BigDecimal adjustProfit;
    /**调整后的毛利率*/
    private String adjustProfitRate;
    /**调整后与承诺毛利率差*/
    private String adjustProfitRateDiffer;
    /**项目年份*/
    private Integer year;
    /**项目月份*/
    private Integer month;
    /**按月项目整体毛利额*/
    private BigDecimal monthProfit;
    /**按月项目整体毛利率*/
    private String monthProfitRate;
    /**按月项目整体毛利率差额*/
    private String monthProfitRateDiffer;

    public BigDecimal getCustomerFreightWithTax() {
        return customerFreightWithTax;
    }

    public void setCustomerFreightWithTax(BigDecimal customerFreightWithTax) {
        this.customerFreightWithTax = customerFreightWithTax;
    }

    public String getProfitRatePromise() {
        return profitRatePromise;
    }

    public void setProfitRatePromise(String profitRatePromise) {
        this.profitRatePromise = profitRatePromise;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public String getProfitRate() {
        return profitRate;
    }

    public void setProfitRate(String profitRate) {
        this.profitRate = profitRate;
    }

    public String getProfitRateDiffer() {
        return profitRateDiffer;
    }

    public void setProfitRateDiffer(String profitRateDiffer) {
        this.profitRateDiffer = profitRateDiffer;
    }

    public String getAdjustProfitRate() {
        return adjustProfitRate;
    }

    public void setAdjustProfitRate(String adjustProfitRate) {
        this.adjustProfitRate = adjustProfitRate;
    }

    public String getAdjustProfitRateDiffer() {
        return adjustProfitRateDiffer;
    }

    public void setAdjustProfitRateDiffer(String adjustProfitRateDiffer) {
        this.adjustProfitRateDiffer = adjustProfitRateDiffer;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public BigDecimal getMonthProfit() {
        return monthProfit;
    }

    public void setMonthProfit(BigDecimal monthProfit) {
        this.monthProfit = monthProfit;
    }

    public String getMonthProfitRate() {
        return monthProfitRate;
    }

    public void setMonthProfitRate(String monthProfitRate) {
        this.monthProfitRate = monthProfitRate;
    }

    public String getMonthProfitRateDiffer() {
        return monthProfitRateDiffer;
    }

    public void setMonthProfitRateDiffer(String monthProfitRateDiffer) {
        this.monthProfitRateDiffer = monthProfitRateDiffer;
    }

    public BigDecimal getAdjustProfit() {
        return adjustProfit;
    }

    public void setAdjustProfit(BigDecimal adjustProfit) {
        this.adjustProfit = adjustProfit;
    }

    public BigDecimal getSumAdjustFreight() {
        return sumAdjustFreight;
    }

    public void setSumAdjustFreight(BigDecimal sumAdjustFreight) {
        this.sumAdjustFreight = sumAdjustFreight;
    }

    public BigDecimal getSumFreightWithTax() {
        return sumFreightWithTax;
    }

    public void setSumFreightWithTax(BigDecimal sumFreightWithTax) {
        this.sumFreightWithTax = sumFreightWithTax;
    }

    public BigDecimal getAdjustFreight() {
        return adjustFreight;
    }

    public void setAdjustFreight(BigDecimal adjustFreight) {
        this.adjustFreight = adjustFreight;
    }

    /**
     * 对账单-对账状态
     */
    public enum ReconciliationStatus {

        Append(0,"未提交"),SUBMIT(1,"审核中"),REJECT(2,"被驳回"),AGREE(3,"已通过");

        private int code;

        private String msg;

        private ReconciliationStatus(int code,String msg) {
            this.code = code;
            this.msg = msg;
        }
        public int getCode() {
            return code;
        }
        public void setCode(int code) {
            this.code = code;
        }
        public String getMsg() {
            return msg;
        }
        public void setMsg(String msg) {
            this.msg = msg;
        }

    }

    public List<Integer> getReconciliationIds() {
        return reconciliationIds;
    }

    public void setReconciliationIds(List<Integer> reconciliationIds) {
        this.reconciliationIds = reconciliationIds;
    }

    public Integer getVendorCount() {
        return vendorCount;
    }

    public void setVendorCount(Integer vendorCount) {
        this.vendorCount = vendorCount;
    }

    public Integer getWaybillCount() {
        return waybillCount;
    }

    public void setWaybillCount(Integer waybillCount) {
        this.waybillCount = waybillCount;
    }

    public BigDecimal getSumSettleFreight() {
        return sumSettleFreight;
    }

    public void setSumSettleFreight(BigDecimal sumSettleFreight) {
        this.sumSettleFreight = sumSettleFreight;
    }

    public BigDecimal getSumNonSettleFreight() {
        return sumNonSettleFreight;
    }

    public void setSumNonSettleFreight(BigDecimal sumNonSettleFreight) {
        this.sumNonSettleFreight = sumNonSettleFreight;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getChangRecord() {
        return changRecord;
    }

    public void setChangRecord(String changRecord) {
        this.changRecord = changRecord;
    }

    public String getAreaCodeName() {
        return areaCodeName;
    }

    public void setAreaCodeName(String areaCodeName) {
        this.areaCodeName = areaCodeName;
    }

    public String getSignedDepartmentName() {
        return signedDepartmentName;
    }

    public void setSignedDepartmentName(String signedDepartmentName) {
        this.signedDepartmentName = signedDepartmentName;
    }

    public boolean isHasEvidence() {
        return hasEvidence;
    }

    public void setHasEvidence(boolean hasEvidence) {
        this.hasEvidence = hasEvidence;
    }
}