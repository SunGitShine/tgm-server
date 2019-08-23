package com.juma.tgm.crm.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @Description: 昨日收入
 * @author weilibin
 * @date 2016年9月26日 下午2:10:42
 * @version V1.0
 */

public class YesterdayIncomeInfo implements Serializable {

    private static final long serialVersionUID = 7704178875268877281L;
    /** 税前收入 */
    private BigDecimal preTaxIncome = BigDecimal.ZERO;
    /** 税后收入 */
    private BigDecimal afterTaxIncome = BigDecimal.ZERO;
    /** 司机收入 */
    private BigDecimal show4DriverFreightIncome = BigDecimal.ZERO;
    /** 昨天总运单数 */
    private Integer yesterdayWaybillTotal = 0;
    /** 运单池数量 */
    private Integer poolCount = 0;

    public BigDecimal getPreTaxIncome() {
        return preTaxIncome;
    }

    public void setPreTaxIncome(BigDecimal preTaxIncome) {
        this.preTaxIncome = preTaxIncome;
    }

    public BigDecimal getAfterTaxIncome() {
        return afterTaxIncome;
    }

    public void setAfterTaxIncome(BigDecimal afterTaxIncome) {
        this.afterTaxIncome = afterTaxIncome;
    }

    public BigDecimal getShow4DriverFreightIncome() {
        return show4DriverFreightIncome;
    }

    public void setShow4DriverFreightIncome(BigDecimal show4DriverFreightIncome) {
        this.show4DriverFreightIncome = show4DriverFreightIncome;
    }

    public Integer getYesterdayWaybillTotal() {
        return yesterdayWaybillTotal;
    }

    public void setYesterdayWaybillTotal(Integer yesterdayWaybillTotal) {
        this.yesterdayWaybillTotal = yesterdayWaybillTotal;
    }

    public Integer getPoolCount() {
        return poolCount;
    }

    public void setPoolCount(Integer poolCount) {
        this.poolCount = poolCount;
    }

    @Override
    public String toString() {
        return "YesterdayIncomeInfo [preTaxIncome=" + preTaxIncome + ", afterTaxIncome=" + afterTaxIncome
                + ", show4DriverFreightIncome=" + show4DriverFreightIncome + ", yesterdayWaybillTotal="
                + yesterdayWaybillTotal + ", poolCount=" + poolCount + "]";
    }

}
