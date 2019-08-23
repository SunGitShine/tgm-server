package com.juma.tgm.fms.domain.v3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReconciliationExtraForPayableExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int startOffSet = -1;

    protected int size = -1;

    public ReconciliationExtraForPayableExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria(this);
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setStartOffSet(int startOffSet) {
        this.startOffSet=startOffSet;
    }

    public int getStartOffSet() {
        return startOffSet;
    }

    public void setSize(int size) {
        this.size=size;
    }

    public int getSize() {
        return size;
    }

    public ReconciliationExtraForPayableExample orderBy(String ... orderByClauses) {
        StringBuilder buffer = new StringBuilder();
        if(orderByClauses == null) throw new RuntimeException("order by field cannot be null");
        for(String field : orderByClauses) {
            if(field == null || field.trim().length() == 0) throw new RuntimeException("order by field cannot be null");
            buffer.append(field);
            buffer.append(",");
        }
        if(buffer.length() == 0) return this;
        this.setOrderByClause(buffer.substring(0, buffer.length() - 1));
        return this;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andExtraIdIsNull() {
            addCriterion("extra_id is null");
            return (Criteria) this;
        }

        public Criteria andExtraIdIsNotNull() {
            addCriterion("extra_id is not null");
            return (Criteria) this;
        }

        public Criteria andExtraIdEqualTo(Integer value) {
            addCriterion("extra_id =", value, "extraId");
            return (Criteria) this;
        }

        public Criteria andExtraIdNotEqualTo(Integer value) {
            addCriterion("extra_id <>", value, "extraId");
            return (Criteria) this;
        }

        public Criteria andExtraIdGreaterThan(Integer value) {
            addCriterion("extra_id >", value, "extraId");
            return (Criteria) this;
        }

        public Criteria andExtraIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("extra_id >=", value, "extraId");
            return (Criteria) this;
        }

        public Criteria andExtraIdLessThan(Integer value) {
            addCriterion("extra_id <", value, "extraId");
            return (Criteria) this;
        }

        public Criteria andExtraIdLessThanOrEqualTo(Integer value) {
            addCriterion("extra_id <=", value, "extraId");
            return (Criteria) this;
        }

        public Criteria andExtraIdIn(List<Integer> values) {
            addCriterion("extra_id in", values, "extraId");
            return (Criteria) this;
        }

        public Criteria andExtraIdNotIn(List<Integer> values) {
            addCriterion("extra_id not in", values, "extraId");
            return (Criteria) this;
        }

        public Criteria andExtraIdBetween(Integer value1, Integer value2) {
            addCriterion("extra_id between", value1, value2, "extraId");
            return (Criteria) this;
        }

        public Criteria andExtraIdNotBetween(Integer value1, Integer value2) {
            addCriterion("extra_id not between", value1, value2, "extraId");
            return (Criteria) this;
        }

        public Criteria andReconciliationIdIsNull() {
            addCriterion("reconciliation_id is null");
            return (Criteria) this;
        }

        public Criteria andReconciliationIdIsNotNull() {
            addCriterion("reconciliation_id is not null");
            return (Criteria) this;
        }

        public Criteria andReconciliationIdEqualTo(Integer value) {
            addCriterion("reconciliation_id =", value, "reconciliationId");
            return (Criteria) this;
        }

        public Criteria andReconciliationIdNotEqualTo(Integer value) {
            addCriterion("reconciliation_id <>", value, "reconciliationId");
            return (Criteria) this;
        }

        public Criteria andReconciliationIdGreaterThan(Integer value) {
            addCriterion("reconciliation_id >", value, "reconciliationId");
            return (Criteria) this;
        }

        public Criteria andReconciliationIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("reconciliation_id >=", value, "reconciliationId");
            return (Criteria) this;
        }

        public Criteria andReconciliationIdLessThan(Integer value) {
            addCriterion("reconciliation_id <", value, "reconciliationId");
            return (Criteria) this;
        }

        public Criteria andReconciliationIdLessThanOrEqualTo(Integer value) {
            addCriterion("reconciliation_id <=", value, "reconciliationId");
            return (Criteria) this;
        }

        public Criteria andReconciliationIdIn(List<Integer> values) {
            addCriterion("reconciliation_id in", values, "reconciliationId");
            return (Criteria) this;
        }

        public Criteria andReconciliationIdNotIn(List<Integer> values) {
            addCriterion("reconciliation_id not in", values, "reconciliationId");
            return (Criteria) this;
        }

        public Criteria andReconciliationIdBetween(Integer value1, Integer value2) {
            addCriterion("reconciliation_id between", value1, value2, "reconciliationId");
            return (Criteria) this;
        }

        public Criteria andReconciliationIdNotBetween(Integer value1, Integer value2) {
            addCriterion("reconciliation_id not between", value1, value2, "reconciliationId");
            return (Criteria) this;
        }

        public Criteria andVendorIdIsNull() {
            addCriterion("vendor_id is null");
            return (Criteria) this;
        }

        public Criteria andVendorIdIsNotNull() {
            addCriterion("vendor_id is not null");
            return (Criteria) this;
        }

        public Criteria andVendorIdEqualTo(Integer value) {
            addCriterion("vendor_id =", value, "vendorId");
            return (Criteria) this;
        }

        public Criteria andVendorIdNotEqualTo(Integer value) {
            addCriterion("vendor_id <>", value, "vendorId");
            return (Criteria) this;
        }

        public Criteria andVendorIdGreaterThan(Integer value) {
            addCriterion("vendor_id >", value, "vendorId");
            return (Criteria) this;
        }

        public Criteria andVendorIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("vendor_id >=", value, "vendorId");
            return (Criteria) this;
        }

        public Criteria andVendorIdLessThan(Integer value) {
            addCriterion("vendor_id <", value, "vendorId");
            return (Criteria) this;
        }

        public Criteria andVendorIdLessThanOrEqualTo(Integer value) {
            addCriterion("vendor_id <=", value, "vendorId");
            return (Criteria) this;
        }

        public Criteria andVendorIdIn(List<Integer> values) {
            addCriterion("vendor_id in", values, "vendorId");
            return (Criteria) this;
        }

        public Criteria andVendorIdNotIn(List<Integer> values) {
            addCriterion("vendor_id not in", values, "vendorId");
            return (Criteria) this;
        }

        public Criteria andVendorIdBetween(Integer value1, Integer value2) {
            addCriterion("vendor_id between", value1, value2, "vendorId");
            return (Criteria) this;
        }

        public Criteria andVendorIdNotBetween(Integer value1, Integer value2) {
            addCriterion("vendor_id not between", value1, value2, "vendorId");
            return (Criteria) this;
        }

        public Criteria andOilCardFeeIsNull() {
            addCriterion("oil_card_fee is null");
            return (Criteria) this;
        }

        public Criteria andOilCardFeeIsNotNull() {
            addCriterion("oil_card_fee is not null");
            return (Criteria) this;
        }

        public Criteria andOilCardFeeEqualTo(BigDecimal value) {
            addCriterion("oil_card_fee =", value, "oilCardFee");
            return (Criteria) this;
        }

        public Criteria andOilCardFeeNotEqualTo(BigDecimal value) {
            addCriterion("oil_card_fee <>", value, "oilCardFee");
            return (Criteria) this;
        }

        public Criteria andOilCardFeeGreaterThan(BigDecimal value) {
            addCriterion("oil_card_fee >", value, "oilCardFee");
            return (Criteria) this;
        }

        public Criteria andOilCardFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("oil_card_fee >=", value, "oilCardFee");
            return (Criteria) this;
        }

        public Criteria andOilCardFeeLessThan(BigDecimal value) {
            addCriterion("oil_card_fee <", value, "oilCardFee");
            return (Criteria) this;
        }

        public Criteria andOilCardFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("oil_card_fee <=", value, "oilCardFee");
            return (Criteria) this;
        }

        public Criteria andOilCardFeeIn(List<BigDecimal> values) {
            addCriterion("oil_card_fee in", values, "oilCardFee");
            return (Criteria) this;
        }

        public Criteria andOilCardFeeNotIn(List<BigDecimal> values) {
            addCriterion("oil_card_fee not in", values, "oilCardFee");
            return (Criteria) this;
        }

        public Criteria andOilCardFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("oil_card_fee between", value1, value2, "oilCardFee");
            return (Criteria) this;
        }

        public Criteria andOilCardFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("oil_card_fee not between", value1, value2, "oilCardFee");
            return (Criteria) this;
        }

        public Criteria andManagementFeeIsNull() {
            addCriterion("management_fee is null");
            return (Criteria) this;
        }

        public Criteria andManagementFeeIsNotNull() {
            addCriterion("management_fee is not null");
            return (Criteria) this;
        }

        public Criteria andManagementFeeEqualTo(BigDecimal value) {
            addCriterion("management_fee =", value, "managementFee");
            return (Criteria) this;
        }

        public Criteria andManagementFeeNotEqualTo(BigDecimal value) {
            addCriterion("management_fee <>", value, "managementFee");
            return (Criteria) this;
        }

        public Criteria andManagementFeeGreaterThan(BigDecimal value) {
            addCriterion("management_fee >", value, "managementFee");
            return (Criteria) this;
        }

        public Criteria andManagementFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("management_fee >=", value, "managementFee");
            return (Criteria) this;
        }

        public Criteria andManagementFeeLessThan(BigDecimal value) {
            addCriterion("management_fee <", value, "managementFee");
            return (Criteria) this;
        }

        public Criteria andManagementFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("management_fee <=", value, "managementFee");
            return (Criteria) this;
        }

        public Criteria andManagementFeeIn(List<BigDecimal> values) {
            addCriterion("management_fee in", values, "managementFee");
            return (Criteria) this;
        }

        public Criteria andManagementFeeNotIn(List<BigDecimal> values) {
            addCriterion("management_fee not in", values, "managementFee");
            return (Criteria) this;
        }

        public Criteria andManagementFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("management_fee between", value1, value2, "managementFee");
            return (Criteria) this;
        }

        public Criteria andManagementFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("management_fee not between", value1, value2, "managementFee");
            return (Criteria) this;
        }

        public Criteria andIsInvoiceIsNull() {
            addCriterion("is_invoice is null");
            return (Criteria) this;
        }

        public Criteria andIsInvoiceIsNotNull() {
            addCriterion("is_invoice is not null");
            return (Criteria) this;
        }

        public Criteria andIsInvoiceEqualTo(Boolean value) {
            addCriterion("is_invoice =", value, "isInvoice");
            return (Criteria) this;
        }

        public Criteria andIsInvoiceNotEqualTo(Boolean value) {
            addCriterion("is_invoice <>", value, "isInvoice");
            return (Criteria) this;
        }

        public Criteria andIsInvoiceGreaterThan(Boolean value) {
            addCriterion("is_invoice >", value, "isInvoice");
            return (Criteria) this;
        }

        public Criteria andIsInvoiceGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_invoice >=", value, "isInvoice");
            return (Criteria) this;
        }

        public Criteria andIsInvoiceLessThan(Boolean value) {
            addCriterion("is_invoice <", value, "isInvoice");
            return (Criteria) this;
        }

        public Criteria andIsInvoiceLessThanOrEqualTo(Boolean value) {
            addCriterion("is_invoice <=", value, "isInvoice");
            return (Criteria) this;
        }

        public Criteria andIsInvoiceIn(List<Boolean> values) {
            addCriterion("is_invoice in", values, "isInvoice");
            return (Criteria) this;
        }

        public Criteria andIsInvoiceNotIn(List<Boolean> values) {
            addCriterion("is_invoice not in", values, "isInvoice");
            return (Criteria) this;
        }

        public Criteria andIsInvoiceBetween(Boolean value1, Boolean value2) {
            addCriterion("is_invoice between", value1, value2, "isInvoice");
            return (Criteria) this;
        }

        public Criteria andIsInvoiceNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_invoice not between", value1, value2, "isInvoice");
            return (Criteria) this;
        }

        public Criteria andTaxRateIsNull() {
            addCriterion("tax_rate is null");
            return (Criteria) this;
        }

        public Criteria andTaxRateIsNotNull() {
            addCriterion("tax_rate is not null");
            return (Criteria) this;
        }

        public Criteria andTaxRateEqualTo(BigDecimal value) {
            addCriterion("tax_rate =", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateNotEqualTo(BigDecimal value) {
            addCriterion("tax_rate <>", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateGreaterThan(BigDecimal value) {
            addCriterion("tax_rate >", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("tax_rate >=", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateLessThan(BigDecimal value) {
            addCriterion("tax_rate <", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("tax_rate <=", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateIn(List<BigDecimal> values) {
            addCriterion("tax_rate in", values, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateNotIn(List<BigDecimal> values) {
            addCriterion("tax_rate not in", values, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("tax_rate between", value1, value2, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("tax_rate not between", value1, value2, "taxRate");
            return (Criteria) this;
        }

        public Criteria andReferenceTaxFeeIsNull() {
            addCriterion("reference_tax_fee is null");
            return (Criteria) this;
        }

        public Criteria andReferenceTaxFeeIsNotNull() {
            addCriterion("reference_tax_fee is not null");
            return (Criteria) this;
        }

        public Criteria andReferenceTaxFeeEqualTo(BigDecimal value) {
            addCriterion("reference_tax_fee =", value, "referenceTaxFee");
            return (Criteria) this;
        }

        public Criteria andReferenceTaxFeeNotEqualTo(BigDecimal value) {
            addCriterion("reference_tax_fee <>", value, "referenceTaxFee");
            return (Criteria) this;
        }

        public Criteria andReferenceTaxFeeGreaterThan(BigDecimal value) {
            addCriterion("reference_tax_fee >", value, "referenceTaxFee");
            return (Criteria) this;
        }

        public Criteria andReferenceTaxFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("reference_tax_fee >=", value, "referenceTaxFee");
            return (Criteria) this;
        }

        public Criteria andReferenceTaxFeeLessThan(BigDecimal value) {
            addCriterion("reference_tax_fee <", value, "referenceTaxFee");
            return (Criteria) this;
        }

        public Criteria andReferenceTaxFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("reference_tax_fee <=", value, "referenceTaxFee");
            return (Criteria) this;
        }

        public Criteria andReferenceTaxFeeIn(List<BigDecimal> values) {
            addCriterion("reference_tax_fee in", values, "referenceTaxFee");
            return (Criteria) this;
        }

        public Criteria andReferenceTaxFeeNotIn(List<BigDecimal> values) {
            addCriterion("reference_tax_fee not in", values, "referenceTaxFee");
            return (Criteria) this;
        }

        public Criteria andReferenceTaxFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("reference_tax_fee between", value1, value2, "referenceTaxFee");
            return (Criteria) this;
        }

        public Criteria andReferenceTaxFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("reference_tax_fee not between", value1, value2, "referenceTaxFee");
            return (Criteria) this;
        }

        public Criteria andVendorTaxRateIsNull() {
            addCriterion("vendor_tax_rate is null");
            return (Criteria) this;
        }

        public Criteria andVendorTaxRateIsNotNull() {
            addCriterion("vendor_tax_rate is not null");
            return (Criteria) this;
        }

        public Criteria andVendorTaxRateEqualTo(BigDecimal value) {
            addCriterion("vendor_tax_rate =", value, "vendorTaxRate");
            return (Criteria) this;
        }

        public Criteria andVendorTaxRateNotEqualTo(BigDecimal value) {
            addCriterion("vendor_tax_rate <>", value, "vendorTaxRate");
            return (Criteria) this;
        }

        public Criteria andVendorTaxRateGreaterThan(BigDecimal value) {
            addCriterion("vendor_tax_rate >", value, "vendorTaxRate");
            return (Criteria) this;
        }

        public Criteria andVendorTaxRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("vendor_tax_rate >=", value, "vendorTaxRate");
            return (Criteria) this;
        }

        public Criteria andVendorTaxRateLessThan(BigDecimal value) {
            addCriterion("vendor_tax_rate <", value, "vendorTaxRate");
            return (Criteria) this;
        }

        public Criteria andVendorTaxRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("vendor_tax_rate <=", value, "vendorTaxRate");
            return (Criteria) this;
        }

        public Criteria andVendorTaxRateIn(List<BigDecimal> values) {
            addCriterion("vendor_tax_rate in", values, "vendorTaxRate");
            return (Criteria) this;
        }

        public Criteria andVendorTaxRateNotIn(List<BigDecimal> values) {
            addCriterion("vendor_tax_rate not in", values, "vendorTaxRate");
            return (Criteria) this;
        }

        public Criteria andVendorTaxRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("vendor_tax_rate between", value1, value2, "vendorTaxRate");
            return (Criteria) this;
        }

        public Criteria andVendorTaxRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("vendor_tax_rate not between", value1, value2, "vendorTaxRate");
            return (Criteria) this;
        }

        public Criteria andDeductionTaxFeeIsNull() {
            addCriterion("deduction_tax_fee is null");
            return (Criteria) this;
        }

        public Criteria andDeductionTaxFeeIsNotNull() {
            addCriterion("deduction_tax_fee is not null");
            return (Criteria) this;
        }

        public Criteria andDeductionTaxFeeEqualTo(BigDecimal value) {
            addCriterion("deduction_tax_fee =", value, "deductionTaxFee");
            return (Criteria) this;
        }

        public Criteria andDeductionTaxFeeNotEqualTo(BigDecimal value) {
            addCriterion("deduction_tax_fee <>", value, "deductionTaxFee");
            return (Criteria) this;
        }

        public Criteria andDeductionTaxFeeGreaterThan(BigDecimal value) {
            addCriterion("deduction_tax_fee >", value, "deductionTaxFee");
            return (Criteria) this;
        }

        public Criteria andDeductionTaxFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("deduction_tax_fee >=", value, "deductionTaxFee");
            return (Criteria) this;
        }

        public Criteria andDeductionTaxFeeLessThan(BigDecimal value) {
            addCriterion("deduction_tax_fee <", value, "deductionTaxFee");
            return (Criteria) this;
        }

        public Criteria andDeductionTaxFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("deduction_tax_fee <=", value, "deductionTaxFee");
            return (Criteria) this;
        }

        public Criteria andDeductionTaxFeeIn(List<BigDecimal> values) {
            addCriterion("deduction_tax_fee in", values, "deductionTaxFee");
            return (Criteria) this;
        }

        public Criteria andDeductionTaxFeeNotIn(List<BigDecimal> values) {
            addCriterion("deduction_tax_fee not in", values, "deductionTaxFee");
            return (Criteria) this;
        }

        public Criteria andDeductionTaxFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deduction_tax_fee between", value1, value2, "deductionTaxFee");
            return (Criteria) this;
        }

        public Criteria andDeductionTaxFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deduction_tax_fee not between", value1, value2, "deductionTaxFee");
            return (Criteria) this;
        }

        public Criteria andAdjustTimeIsNull() {
            addCriterion("adjust_time is null");
            return (Criteria) this;
        }

        public Criteria andAdjustTimeIsNotNull() {
            addCriterion("adjust_time is not null");
            return (Criteria) this;
        }

        public Criteria andAdjustTimeEqualTo(Date value) {
            addCriterion("adjust_time =", value, "adjustTime");
            return (Criteria) this;
        }

        public Criteria andAdjustTimeNotEqualTo(Date value) {
            addCriterion("adjust_time <>", value, "adjustTime");
            return (Criteria) this;
        }

        public Criteria andAdjustTimeGreaterThan(Date value) {
            addCriterion("adjust_time >", value, "adjustTime");
            return (Criteria) this;
        }

        public Criteria andAdjustTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("adjust_time >=", value, "adjustTime");
            return (Criteria) this;
        }

        public Criteria andAdjustTimeLessThan(Date value) {
            addCriterion("adjust_time <", value, "adjustTime");
            return (Criteria) this;
        }

        public Criteria andAdjustTimeLessThanOrEqualTo(Date value) {
            addCriterion("adjust_time <=", value, "adjustTime");
            return (Criteria) this;
        }

        public Criteria andAdjustTimeIn(List<Date> values) {
            addCriterion("adjust_time in", values, "adjustTime");
            return (Criteria) this;
        }

        public Criteria andAdjustTimeNotIn(List<Date> values) {
            addCriterion("adjust_time not in", values, "adjustTime");
            return (Criteria) this;
        }

        public Criteria andAdjustTimeBetween(Date value1, Date value2) {
            addCriterion("adjust_time between", value1, value2, "adjustTime");
            return (Criteria) this;
        }

        public Criteria andAdjustTimeNotBetween(Date value1, Date value2) {
            addCriterion("adjust_time not between", value1, value2, "adjustTime");
            return (Criteria) this;
        }

        public Criteria andAdjustUserIdIsNull() {
            addCriterion("adjust_user_id is null");
            return (Criteria) this;
        }

        public Criteria andAdjustUserIdIsNotNull() {
            addCriterion("adjust_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andAdjustUserIdEqualTo(Integer value) {
            addCriterion("adjust_user_id =", value, "adjustUserId");
            return (Criteria) this;
        }

        public Criteria andAdjustUserIdNotEqualTo(Integer value) {
            addCriterion("adjust_user_id <>", value, "adjustUserId");
            return (Criteria) this;
        }

        public Criteria andAdjustUserIdGreaterThan(Integer value) {
            addCriterion("adjust_user_id >", value, "adjustUserId");
            return (Criteria) this;
        }

        public Criteria andAdjustUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("adjust_user_id >=", value, "adjustUserId");
            return (Criteria) this;
        }

        public Criteria andAdjustUserIdLessThan(Integer value) {
            addCriterion("adjust_user_id <", value, "adjustUserId");
            return (Criteria) this;
        }

        public Criteria andAdjustUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("adjust_user_id <=", value, "adjustUserId");
            return (Criteria) this;
        }

        public Criteria andAdjustUserIdIn(List<Integer> values) {
            addCriterion("adjust_user_id in", values, "adjustUserId");
            return (Criteria) this;
        }

        public Criteria andAdjustUserIdNotIn(List<Integer> values) {
            addCriterion("adjust_user_id not in", values, "adjustUserId");
            return (Criteria) this;
        }

        public Criteria andAdjustUserIdBetween(Integer value1, Integer value2) {
            addCriterion("adjust_user_id between", value1, value2, "adjustUserId");
            return (Criteria) this;
        }

        public Criteria andAdjustUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("adjust_user_id not between", value1, value2, "adjustUserId");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        private ReconciliationExtraForPayableExample example;

        protected Criteria(ReconciliationExtraForPayableExample example) {
            super();
            this.example = example;
        }

        public ReconciliationExtraForPayableExample example() {
            return this.example;
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean likeListValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isLikeListValue() {
            return likeListValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                List<?> l = (List<?>)value;
                if(!l.isEmpty()) {
                    if(condition.contains("like")) {
                        this.likeListValue = true;
                    } else if(condition.contains("in")) {
                        this.listValue = true;
                    }
                }
            } else {
                this.singleValue = true;
                if(value == null || value.toString().trim().length() == 0) {
                    this.noValue = true;
                }
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}