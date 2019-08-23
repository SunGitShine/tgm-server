package com.juma.tgm.fms.domain.v3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReconcilicationForReceivableItemExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int startOffSet = -1;

    protected int size = -1;

    public ReconcilicationForReceivableItemExample() {
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
        Criteria criteria = new Criteria();
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
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andReconcilicationItemIdIsNull() {
            addCriterion("reconcilication_item_id is null");
            return (Criteria) this;
        }

        public Criteria andReconcilicationItemIdIsNotNull() {
            addCriterion("reconcilication_item_id is not null");
            return (Criteria) this;
        }

        public Criteria andReconcilicationItemIdEqualTo(Integer value) {
            addCriterion("reconcilication_item_id =", value, "reconcilicationItemId");
            return (Criteria) this;
        }

        public Criteria andReconcilicationItemIdNotEqualTo(Integer value) {
            addCriterion("reconcilication_item_id <>", value, "reconcilicationItemId");
            return (Criteria) this;
        }

        public Criteria andReconcilicationItemIdGreaterThan(Integer value) {
            addCriterion("reconcilication_item_id >", value, "reconcilicationItemId");
            return (Criteria) this;
        }

        public Criteria andReconcilicationItemIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("reconcilication_item_id >=", value, "reconcilicationItemId");
            return (Criteria) this;
        }

        public Criteria andReconcilicationItemIdLessThan(Integer value) {
            addCriterion("reconcilication_item_id <", value, "reconcilicationItemId");
            return (Criteria) this;
        }

        public Criteria andReconcilicationItemIdLessThanOrEqualTo(Integer value) {
            addCriterion("reconcilication_item_id <=", value, "reconcilicationItemId");
            return (Criteria) this;
        }

        public Criteria andReconcilicationItemIdIn(List<Integer> values) {
            addCriterion("reconcilication_item_id in", values, "reconcilicationItemId");
            return (Criteria) this;
        }

        public Criteria andReconcilicationItemIdNotIn(List<Integer> values) {
            addCriterion("reconcilication_item_id not in", values, "reconcilicationItemId");
            return (Criteria) this;
        }

        public Criteria andReconcilicationItemIdBetween(Integer value1, Integer value2) {
            addCriterion("reconcilication_item_id between", value1, value2, "reconcilicationItemId");
            return (Criteria) this;
        }

        public Criteria andReconcilicationItemIdNotBetween(Integer value1, Integer value2) {
            addCriterion("reconcilication_item_id not between", value1, value2, "reconcilicationItemId");
            return (Criteria) this;
        }

        public Criteria andReconcilicationIdIsNull() {
            addCriterion("reconcilication_id is null");
            return (Criteria) this;
        }

        public Criteria andReconcilicationIdIsNotNull() {
            addCriterion("reconcilication_id is not null");
            return (Criteria) this;
        }

        public Criteria andReconcilicationIdEqualTo(Integer value) {
            addCriterion("reconcilication_id =", value, "reconcilicationId");
            return (Criteria) this;
        }

        public Criteria andReconcilicationIdNotEqualTo(Integer value) {
            addCriterion("reconcilication_id <>", value, "reconcilicationId");
            return (Criteria) this;
        }

        public Criteria andReconcilicationIdGreaterThan(Integer value) {
            addCriterion("reconcilication_id >", value, "reconcilicationId");
            return (Criteria) this;
        }

        public Criteria andReconcilicationIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("reconcilication_id >=", value, "reconcilicationId");
            return (Criteria) this;
        }

        public Criteria andReconcilicationIdLessThan(Integer value) {
            addCriterion("reconcilication_id <", value, "reconcilicationId");
            return (Criteria) this;
        }

        public Criteria andReconcilicationIdLessThanOrEqualTo(Integer value) {
            addCriterion("reconcilication_id <=", value, "reconcilicationId");
            return (Criteria) this;
        }

        public Criteria andReconcilicationIdIn(List<Integer> values) {
            addCriterion("reconcilication_id in", values, "reconcilicationId");
            return (Criteria) this;
        }

        public Criteria andReconcilicationIdNotIn(List<Integer> values) {
            addCriterion("reconcilication_id not in", values, "reconcilicationId");
            return (Criteria) this;
        }

        public Criteria andReconcilicationIdBetween(Integer value1, Integer value2) {
            addCriterion("reconcilication_id between", value1, value2, "reconcilicationId");
            return (Criteria) this;
        }

        public Criteria andReconcilicationIdNotBetween(Integer value1, Integer value2) {
            addCriterion("reconcilication_id not between", value1, value2, "reconcilicationId");
            return (Criteria) this;
        }

        public Criteria andWaybillIdIsNull() {
            addCriterion("waybill_id is null");
            return (Criteria) this;
        }

        public Criteria andWaybillIdIsNotNull() {
            addCriterion("waybill_id is not null");
            return (Criteria) this;
        }

        public Criteria andWaybillIdEqualTo(Integer value) {
            addCriterion("waybill_id =", value, "waybillId");
            return (Criteria) this;
        }

        public Criteria andWaybillIdNotEqualTo(Integer value) {
            addCriterion("waybill_id <>", value, "waybillId");
            return (Criteria) this;
        }

        public Criteria andWaybillIdGreaterThan(Integer value) {
            addCriterion("waybill_id >", value, "waybillId");
            return (Criteria) this;
        }

        public Criteria andWaybillIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("waybill_id >=", value, "waybillId");
            return (Criteria) this;
        }

        public Criteria andWaybillIdLessThan(Integer value) {
            addCriterion("waybill_id <", value, "waybillId");
            return (Criteria) this;
        }

        public Criteria andWaybillIdLessThanOrEqualTo(Integer value) {
            addCriterion("waybill_id <=", value, "waybillId");
            return (Criteria) this;
        }

        public Criteria andWaybillIdIn(List<Integer> values) {
            addCriterion("waybill_id in", values, "waybillId");
            return (Criteria) this;
        }

        public Criteria andWaybillIdNotIn(List<Integer> values) {
            addCriterion("waybill_id not in", values, "waybillId");
            return (Criteria) this;
        }

        public Criteria andWaybillIdBetween(Integer value1, Integer value2) {
            addCriterion("waybill_id between", value1, value2, "waybillId");
            return (Criteria) this;
        }

        public Criteria andWaybillIdNotBetween(Integer value1, Integer value2) {
            addCriterion("waybill_id not between", value1, value2, "waybillId");
            return (Criteria) this;
        }

        public Criteria andWaybillNoIsNull() {
            addCriterion("waybill_no is null");
            return (Criteria) this;
        }

        public Criteria andWaybillNoIsNotNull() {
            addCriterion("waybill_no is not null");
            return (Criteria) this;
        }

        public Criteria andWaybillNoEqualTo(String value) {
            addCriterion("waybill_no =", value, "waybillNo");
            return (Criteria) this;
        }

        public Criteria andWaybillNoNotEqualTo(String value) {
            addCriterion("waybill_no <>", value, "waybillNo");
            return (Criteria) this;
        }

        public Criteria andWaybillNoGreaterThan(String value) {
            addCriterion("waybill_no >", value, "waybillNo");
            return (Criteria) this;
        }

        public Criteria andWaybillNoGreaterThanOrEqualTo(String value) {
            addCriterion("waybill_no >=", value, "waybillNo");
            return (Criteria) this;
        }

        public Criteria andWaybillNoLessThan(String value) {
            addCriterion("waybill_no <", value, "waybillNo");
            return (Criteria) this;
        }

        public Criteria andWaybillNoLessThanOrEqualTo(String value) {
            addCriterion("waybill_no <=", value, "waybillNo");
            return (Criteria) this;
        }

        public Criteria andWaybillNoLike(String value) {
            addCriterion("waybill_no like", value, "waybillNo");
            return (Criteria) this;
        }

        public Criteria andWaybillNoNotLike(String value) {
            addCriterion("waybill_no not like", value, "waybillNo");
            return (Criteria) this;
        }

        public Criteria andWaybillNoIn(List<String> values) {
            addCriterion("waybill_no in", values, "waybillNo");
            return (Criteria) this;
        }

        public Criteria andWaybillNoNotIn(List<String> values) {
            addCriterion("waybill_no not in", values, "waybillNo");
            return (Criteria) this;
        }

        public Criteria andWaybillNoBetween(String value1, String value2) {
            addCriterion("waybill_no between", value1, value2, "waybillNo");
            return (Criteria) this;
        }

        public Criteria andWaybillNoNotBetween(String value1, String value2) {
            addCriterion("waybill_no not between", value1, value2, "waybillNo");
            return (Criteria) this;
        }

        public Criteria andPlanDeliveryTimeIsNull() {
            addCriterion("plan_delivery_time is null");
            return (Criteria) this;
        }

        public Criteria andPlanDeliveryTimeIsNotNull() {
            addCriterion("plan_delivery_time is not null");
            return (Criteria) this;
        }

        public Criteria andPlanDeliveryTimeEqualTo(Date value) {
            addCriterion("plan_delivery_time =", value, "planDeliveryTime");
            return (Criteria) this;
        }

        public Criteria andPlanDeliveryTimeNotEqualTo(Date value) {
            addCriterion("plan_delivery_time <>", value, "planDeliveryTime");
            return (Criteria) this;
        }

        public Criteria andPlanDeliveryTimeGreaterThan(Date value) {
            addCriterion("plan_delivery_time >", value, "planDeliveryTime");
            return (Criteria) this;
        }

        public Criteria andPlanDeliveryTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("plan_delivery_time >=", value, "planDeliveryTime");
            return (Criteria) this;
        }

        public Criteria andPlanDeliveryTimeLessThan(Date value) {
            addCriterion("plan_delivery_time <", value, "planDeliveryTime");
            return (Criteria) this;
        }

        public Criteria andPlanDeliveryTimeLessThanOrEqualTo(Date value) {
            addCriterion("plan_delivery_time <=", value, "planDeliveryTime");
            return (Criteria) this;
        }

        public Criteria andPlanDeliveryTimeIn(List<Date> values) {
            addCriterion("plan_delivery_time in", values, "planDeliveryTime");
            return (Criteria) this;
        }

        public Criteria andPlanDeliveryTimeNotIn(List<Date> values) {
            addCriterion("plan_delivery_time not in", values, "planDeliveryTime");
            return (Criteria) this;
        }

        public Criteria andPlanDeliveryTimeBetween(Date value1, Date value2) {
            addCriterion("plan_delivery_time between", value1, value2, "planDeliveryTime");
            return (Criteria) this;
        }

        public Criteria andPlanDeliveryTimeNotBetween(Date value1, Date value2) {
            addCriterion("plan_delivery_time not between", value1, value2, "planDeliveryTime");
            return (Criteria) this;
        }

        public Criteria andReceivableWithTaxIsNull() {
            addCriterion("receivable_with_tax is null");
            return (Criteria) this;
        }

        public Criteria andReceivableWithTaxIsNotNull() {
            addCriterion("receivable_with_tax is not null");
            return (Criteria) this;
        }

        public Criteria andReceivableWithTaxEqualTo(BigDecimal value) {
            addCriterion("receivable_with_tax =", value, "receivableWithTax");
            return (Criteria) this;
        }

        public Criteria andReceivableWithTaxNotEqualTo(BigDecimal value) {
            addCriterion("receivable_with_tax <>", value, "receivableWithTax");
            return (Criteria) this;
        }

        public Criteria andReceivableWithTaxGreaterThan(BigDecimal value) {
            addCriterion("receivable_with_tax >", value, "receivableWithTax");
            return (Criteria) this;
        }

        public Criteria andReceivableWithTaxGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("receivable_with_tax >=", value, "receivableWithTax");
            return (Criteria) this;
        }

        public Criteria andReceivableWithTaxLessThan(BigDecimal value) {
            addCriterion("receivable_with_tax <", value, "receivableWithTax");
            return (Criteria) this;
        }

        public Criteria andReceivableWithTaxLessThanOrEqualTo(BigDecimal value) {
            addCriterion("receivable_with_tax <=", value, "receivableWithTax");
            return (Criteria) this;
        }

        public Criteria andReceivableWithTaxIn(List<BigDecimal> values) {
            addCriterion("receivable_with_tax in", values, "receivableWithTax");
            return (Criteria) this;
        }

        public Criteria andReceivableWithTaxNotIn(List<BigDecimal> values) {
            addCriterion("receivable_with_tax not in", values, "receivableWithTax");
            return (Criteria) this;
        }

        public Criteria andReceivableWithTaxBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("receivable_with_tax between", value1, value2, "receivableWithTax");
            return (Criteria) this;
        }

        public Criteria andReceivableWithTaxNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("receivable_with_tax not between", value1, value2, "receivableWithTax");
            return (Criteria) this;
        }

        public Criteria andReceivableWithoutTaxIsNull() {
            addCriterion("receivable_without_tax is null");
            return (Criteria) this;
        }

        public Criteria andReceivableWithoutTaxIsNotNull() {
            addCriterion("receivable_without_tax is not null");
            return (Criteria) this;
        }

        public Criteria andReceivableWithoutTaxEqualTo(BigDecimal value) {
            addCriterion("receivable_without_tax =", value, "receivableWithoutTax");
            return (Criteria) this;
        }

        public Criteria andReceivableWithoutTaxNotEqualTo(BigDecimal value) {
            addCriterion("receivable_without_tax <>", value, "receivableWithoutTax");
            return (Criteria) this;
        }

        public Criteria andReceivableWithoutTaxGreaterThan(BigDecimal value) {
            addCriterion("receivable_without_tax >", value, "receivableWithoutTax");
            return (Criteria) this;
        }

        public Criteria andReceivableWithoutTaxGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("receivable_without_tax >=", value, "receivableWithoutTax");
            return (Criteria) this;
        }

        public Criteria andReceivableWithoutTaxLessThan(BigDecimal value) {
            addCriterion("receivable_without_tax <", value, "receivableWithoutTax");
            return (Criteria) this;
        }

        public Criteria andReceivableWithoutTaxLessThanOrEqualTo(BigDecimal value) {
            addCriterion("receivable_without_tax <=", value, "receivableWithoutTax");
            return (Criteria) this;
        }

        public Criteria andReceivableWithoutTaxIn(List<BigDecimal> values) {
            addCriterion("receivable_without_tax in", values, "receivableWithoutTax");
            return (Criteria) this;
        }

        public Criteria andReceivableWithoutTaxNotIn(List<BigDecimal> values) {
            addCriterion("receivable_without_tax not in", values, "receivableWithoutTax");
            return (Criteria) this;
        }

        public Criteria andReceivableWithoutTaxBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("receivable_without_tax between", value1, value2, "receivableWithoutTax");
            return (Criteria) this;
        }

        public Criteria andReceivableWithoutTaxNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("receivable_without_tax not between", value1, value2, "receivableWithoutTax");
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

        public Criteria andRebateFeeIsNull() {
            addCriterion("rebate_fee is null");
            return (Criteria) this;
        }

        public Criteria andRebateFeeIsNotNull() {
            addCriterion("rebate_fee is not null");
            return (Criteria) this;
        }

        public Criteria andRebateFeeEqualTo(BigDecimal value) {
            addCriterion("rebate_fee =", value, "rebateFee");
            return (Criteria) this;
        }

        public Criteria andRebateFeeNotEqualTo(BigDecimal value) {
            addCriterion("rebate_fee <>", value, "rebateFee");
            return (Criteria) this;
        }

        public Criteria andRebateFeeGreaterThan(BigDecimal value) {
            addCriterion("rebate_fee >", value, "rebateFee");
            return (Criteria) this;
        }

        public Criteria andRebateFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("rebate_fee >=", value, "rebateFee");
            return (Criteria) this;
        }

        public Criteria andRebateFeeLessThan(BigDecimal value) {
            addCriterion("rebate_fee <", value, "rebateFee");
            return (Criteria) this;
        }

        public Criteria andRebateFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("rebate_fee <=", value, "rebateFee");
            return (Criteria) this;
        }

        public Criteria andRebateFeeIn(List<BigDecimal> values) {
            addCriterion("rebate_fee in", values, "rebateFee");
            return (Criteria) this;
        }

        public Criteria andRebateFeeNotIn(List<BigDecimal> values) {
            addCriterion("rebate_fee not in", values, "rebateFee");
            return (Criteria) this;
        }

        public Criteria andRebateFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rebate_fee between", value1, value2, "rebateFee");
            return (Criteria) this;
        }

        public Criteria andRebateFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rebate_fee not between", value1, value2, "rebateFee");
            return (Criteria) this;
        }

        public Criteria andSettleStatusIsNull() {
            addCriterion("settle_status is null");
            return (Criteria) this;
        }

        public Criteria andSettleStatusIsNotNull() {
            addCriterion("settle_status is not null");
            return (Criteria) this;
        }

        public Criteria andSettleStatusEqualTo(Integer value) {
            addCriterion("settle_status =", value, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusNotEqualTo(Integer value) {
            addCriterion("settle_status <>", value, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusGreaterThan(Integer value) {
            addCriterion("settle_status >", value, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("settle_status >=", value, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusLessThan(Integer value) {
            addCriterion("settle_status <", value, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusLessThanOrEqualTo(Integer value) {
            addCriterion("settle_status <=", value, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusIn(List<Integer> values) {
            addCriterion("settle_status in", values, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusNotIn(List<Integer> values) {
            addCriterion("settle_status not in", values, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusBetween(Integer value1, Integer value2) {
            addCriterion("settle_status between", value1, value2, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("settle_status not between", value1, value2, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andPayableReconcilicationStatusIsNull() {
            addCriterion("payable_reconcilication_status is null");
            return (Criteria) this;
        }

        public Criteria andPayableReconcilicationStatusIsNotNull() {
            addCriterion("payable_reconcilication_status is not null");
            return (Criteria) this;
        }

        public Criteria andPayableReconcilicationStatusEqualTo(Integer value) {
            addCriterion("payable_reconcilication_status =", value, "payableReconcilicationStatus");
            return (Criteria) this;
        }

        public Criteria andPayableReconcilicationStatusNotEqualTo(Integer value) {
            addCriterion("payable_reconcilication_status <>", value, "payableReconcilicationStatus");
            return (Criteria) this;
        }

        public Criteria andPayableReconcilicationStatusGreaterThan(Integer value) {
            addCriterion("payable_reconcilication_status >", value, "payableReconcilicationStatus");
            return (Criteria) this;
        }

        public Criteria andPayableReconcilicationStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("payable_reconcilication_status >=", value, "payableReconcilicationStatus");
            return (Criteria) this;
        }

        public Criteria andPayableReconcilicationStatusLessThan(Integer value) {
            addCriterion("payable_reconcilication_status <", value, "payableReconcilicationStatus");
            return (Criteria) this;
        }

        public Criteria andPayableReconcilicationStatusLessThanOrEqualTo(Integer value) {
            addCriterion("payable_reconcilication_status <=", value, "payableReconcilicationStatus");
            return (Criteria) this;
        }

        public Criteria andPayableReconcilicationStatusIn(List<Integer> values) {
            addCriterion("payable_reconcilication_status in", values, "payableReconcilicationStatus");
            return (Criteria) this;
        }

        public Criteria andPayableReconcilicationStatusNotIn(List<Integer> values) {
            addCriterion("payable_reconcilication_status not in", values, "payableReconcilicationStatus");
            return (Criteria) this;
        }

        public Criteria andPayableReconcilicationStatusBetween(Integer value1, Integer value2) {
            addCriterion("payable_reconcilication_status between", value1, value2, "payableReconcilicationStatus");
            return (Criteria) this;
        }

        public Criteria andPayableReconcilicationStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("payable_reconcilication_status not between", value1, value2, "payableReconcilicationStatus");
            return (Criteria) this;
        }

        public Criteria andPayableWithTaxIsNull() {
            addCriterion("payable_with_tax is null");
            return (Criteria) this;
        }

        public Criteria andPayableWithTaxIsNotNull() {
            addCriterion("payable_with_tax is not null");
            return (Criteria) this;
        }

        public Criteria andPayableWithTaxEqualTo(BigDecimal value) {
            addCriterion("payable_with_tax =", value, "payableWithTax");
            return (Criteria) this;
        }

        public Criteria andPayableWithTaxNotEqualTo(BigDecimal value) {
            addCriterion("payable_with_tax <>", value, "payableWithTax");
            return (Criteria) this;
        }

        public Criteria andPayableWithTaxGreaterThan(BigDecimal value) {
            addCriterion("payable_with_tax >", value, "payableWithTax");
            return (Criteria) this;
        }

        public Criteria andPayableWithTaxGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("payable_with_tax >=", value, "payableWithTax");
            return (Criteria) this;
        }

        public Criteria andPayableWithTaxLessThan(BigDecimal value) {
            addCriterion("payable_with_tax <", value, "payableWithTax");
            return (Criteria) this;
        }

        public Criteria andPayableWithTaxLessThanOrEqualTo(BigDecimal value) {
            addCriterion("payable_with_tax <=", value, "payableWithTax");
            return (Criteria) this;
        }

        public Criteria andPayableWithTaxIn(List<BigDecimal> values) {
            addCriterion("payable_with_tax in", values, "payableWithTax");
            return (Criteria) this;
        }

        public Criteria andPayableWithTaxNotIn(List<BigDecimal> values) {
            addCriterion("payable_with_tax not in", values, "payableWithTax");
            return (Criteria) this;
        }

        public Criteria andPayableWithTaxBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("payable_with_tax between", value1, value2, "payableWithTax");
            return (Criteria) this;
        }

        public Criteria andPayableWithTaxNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("payable_with_tax not between", value1, value2, "payableWithTax");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

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
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
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