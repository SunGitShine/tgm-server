package com.juma.tgm.fms.domain.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ReconciliationToFms implements Serializable {

    private static final long serialVersionUID = -3361704119801784452L;
    private Integer tenantId;
    private String tenantCode;
    private Integer reconciliationId;
    private String reconciliationNo;
    // 含税总费用—对账单
    private BigDecimal beforeTaxCostReconciliation;
    // 税费—对账单
    private BigDecimal taxAmountReconciliation;
    // 不含税总费用—对账单
    private BigDecimal afterTaxCostReconciliation;

    private List<ReconciliationItemToFms> listReconciliationItemToFms = new ArrayList<ReconciliationItemToFms>();

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

    public Integer getReconciliationId() {
        return reconciliationId;
    }

    public void setReconciliationId(Integer reconciliationId) {
        this.reconciliationId = reconciliationId;
    }

    public String getReconciliationNo() {
        return reconciliationNo;
    }

    public void setReconciliationNo(String reconciliationNo) {
        this.reconciliationNo = reconciliationNo;
    }

    public BigDecimal getBeforeTaxCostReconciliation() {
        return beforeTaxCostReconciliation;
    }

    public void setBeforeTaxCostReconciliation(BigDecimal beforeTaxCostReconciliation) {
        this.beforeTaxCostReconciliation = beforeTaxCostReconciliation;
    }

    public BigDecimal getTaxAmountReconciliation() {
        return taxAmountReconciliation;
    }

    public void setTaxAmountReconciliation(BigDecimal taxAmountReconciliation) {
        this.taxAmountReconciliation = taxAmountReconciliation;
    }

    public BigDecimal getAfterTaxCostReconciliation() {
        return afterTaxCostReconciliation;
    }

    public void setAfterTaxCostReconciliation(BigDecimal afterTaxCostReconciliation) {
        this.afterTaxCostReconciliation = afterTaxCostReconciliation;
    }

    public List<ReconciliationItemToFms> getListReconciliationItemToFms() {
        return listReconciliationItemToFms;
    }

    public void setListReconciliationItemToFms(List<ReconciliationItemToFms> listReconciliationItemToFms) {
        this.listReconciliationItemToFms = listReconciliationItemToFms;
    }

    @Override
    public String toString() {
        return "ReconciliationToFms [tenantId=" + tenantId + ", tenantCode=" + tenantCode + ", reconciliationId="
                + reconciliationId + ", reconciliationNo=" + reconciliationNo + ", beforeTaxCostReconciliation="
                + beforeTaxCostReconciliation + ", taxAmountReconciliation=" + taxAmountReconciliation
                + ", afterTaxCostReconciliation=" + afterTaxCostReconciliation + ", listReconciliationItemToFms="
                + listReconciliationItemToFms + "]";
    }

}
