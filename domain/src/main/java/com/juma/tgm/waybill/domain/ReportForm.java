package com.juma.tgm.waybill.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;

import com.giants.common.tools.Page;

/**
 * 
 * @Description: 报表数据
 * @author weilibin
 * @date 2016年8月1日 下午5:08:56
 * @version V1.0
 */

public class ReportForm implements Serializable {

    private static final long serialVersionUID = 4534435365111371737L;
    /** 司机数 */
    private Integer driverTotal = 0;
    /** 本月税前总运费 */
    private BigDecimal preTaxTotal = BigDecimal.ZERO;
    /** 本月税后总运费 */
    private BigDecimal afterTaxTotal = BigDecimal.ZERO;
    /** 本月司机结算 */
    private BigDecimal show4DriverFreightTotal = BigDecimal.ZERO;
    /** 总运单数 */
    private Integer waybillTotal = 0;
    /** 税前总运费 */
    private BigDecimal allPreTaxTotal = BigDecimal.ZERO;
    /** 税后总运费 */
    private BigDecimal allAfterTaxTotal = BigDecimal.ZERO;
    /** 司机结算总费用 */
    private BigDecimal allShow4DriverFreightTotal = BigDecimal.ZERO;
    /** 昨天收入 */
    private BigDecimal yesterdayIncome = BigDecimal.ZERO;
    /** 昨天总运单数 */
    private Integer yesterdayWaybillTotal = 0;
    /** 单月搬运费之和 */
    private BigDecimal everyMonthHandlingCostSum = BigDecimal.ZERO;
    /** 单月未结算费用之和 */
    private BigDecimal everyMonthNotCheckCostSum = BigDecimal.ZERO;
    /** 截止时间 */
    private String deadline = DateFormatUtils.format(new Date(), "yyyy年MM月dd日");
    /** 报表数据明细 */
    private Page<ReportFormDetail> reportPage;

    public Integer getDriverTotal() {
        return driverTotal;
    }

    public void setDriverTotal(Integer driverTotal) {
        this.driverTotal = driverTotal;
    }

    public BigDecimal getPreTaxTotal() {
        return preTaxTotal;
    }

    public void setPreTaxTotal(BigDecimal preTaxTotal) {
        this.preTaxTotal = preTaxTotal;
    }

    public BigDecimal getAfterTaxTotal() {
        return afterTaxTotal;
    }

    public BigDecimal getShow4DriverFreightTotal() {
        return show4DriverFreightTotal;
    }

    public void setShow4DriverFreightTotal(BigDecimal show4DriverFreightTotal) {
        this.show4DriverFreightTotal = show4DriverFreightTotal;
    }

    public void setAfterTaxTotal(BigDecimal afterTaxTotal) {
        this.afterTaxTotal = afterTaxTotal;
    }

    public Integer getWaybillTotal() {
        return waybillTotal;
    }

    public void setWaybillTotal(Integer waybillTotal) {
        this.waybillTotal = waybillTotal;
    }

    public BigDecimal getAllPreTaxTotal() {
        return allPreTaxTotal;
    }

    public void setAllPreTaxTotal(BigDecimal allPreTaxTotal) {
        this.allPreTaxTotal = allPreTaxTotal;
    }

    public BigDecimal getAllAfterTaxTotal() {
        return allAfterTaxTotal;
    }

    public void setAllAfterTaxTotal(BigDecimal allAfterTaxTotal) {
        this.allAfterTaxTotal = allAfterTaxTotal;
    }

    public BigDecimal getAllShow4DriverFreightTotal() {
        return allShow4DriverFreightTotal;
    }

    public void setAllShow4DriverFreightTotal(BigDecimal allShow4DriverFreightTotal) {
        this.allShow4DriverFreightTotal = allShow4DriverFreightTotal;
    }

    public BigDecimal getYesterdayIncome() {
        return yesterdayIncome;
    }

    public void setYesterdayIncome(BigDecimal yesterdayIncome) {
        this.yesterdayIncome = yesterdayIncome;
    }

    public Integer getYesterdayWaybillTotal() {
        return yesterdayWaybillTotal;
    }

    public BigDecimal getEveryMonthHandlingCostSum() {
        return everyMonthHandlingCostSum;
    }

    public void setEveryMonthHandlingCostSum(BigDecimal everyMonthHandlingCostSum) {
        this.everyMonthHandlingCostSum = everyMonthHandlingCostSum;
    }

    public BigDecimal getEveryMonthNotCheckCostSum() {
        return everyMonthNotCheckCostSum;
    }

    public void setEveryMonthNotCheckCostSum(BigDecimal everyMonthNotCheckCostSum) {
        this.everyMonthNotCheckCostSum = everyMonthNotCheckCostSum;
    }

    public void setYesterdayWaybillTotal(Integer yesterdayWaybillTotal) {
        this.yesterdayWaybillTotal = yesterdayWaybillTotal;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public Page<ReportFormDetail> getReportPage() {
        return reportPage;
    }

    public void setReportPage(Page<ReportFormDetail> reportPage) {
        this.reportPage = reportPage;
    }

}
