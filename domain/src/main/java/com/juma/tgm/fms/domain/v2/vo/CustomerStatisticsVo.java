package com.juma.tgm.fms.domain.v2.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 客户统计信息
 *
 * @ClassName: CustomerStatisticsVo
 * @Description:
 * @author: liang
 * @date: 2018-06-10 15:20
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
public class CustomerStatisticsVo implements Serializable {

    /**
     * 客户
     */
    private String customerName;
    /**
     * 项目
     */
    private String projectName;
    /**
     * 对账单数量
     */
    private Integer reconciliationCount;
    /**
     * 税前总费用
     */
    private BigDecimal totalWithoutTaxFee;
    /**
     * 税后总费用
     */
    private BigDecimal totalWithTaxFee;


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

    public Integer getReconciliationCount() {
        return reconciliationCount;
    }

    public void setReconciliationCount(Integer reconciliationCount) {
        this.reconciliationCount = reconciliationCount;
    }

    public BigDecimal getTotalWithoutTaxFee() {
        return totalWithoutTaxFee;
    }

    public void setTotalWithoutTaxFee(BigDecimal totalWithoutTaxFee) {
        this.totalWithoutTaxFee = totalWithoutTaxFee;
    }

    public BigDecimal getTotalWithTaxFee() {
        return totalWithTaxFee;
    }

    public void setTotalWithTaxFee(BigDecimal totalWithTaxFee) {
        this.totalWithTaxFee = totalWithTaxFee;
    }
}
