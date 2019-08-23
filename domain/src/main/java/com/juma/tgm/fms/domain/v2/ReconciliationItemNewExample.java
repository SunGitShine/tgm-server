package com.juma.tgm.fms.domain.v2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReconciliationItemNewExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int startOffSet = -1;

    protected int size = -1;

    public ReconciliationItemNewExample() {
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

        public Criteria andReconciliationItemIdIsNull() {
            addCriterion("reconciliation_item_id is null");
            return (Criteria) this;
        }

        public Criteria andReconciliationItemIdIsNotNull() {
            addCriterion("reconciliation_item_id is not null");
            return (Criteria) this;
        }

        public Criteria andReconciliationItemIdEqualTo(Integer value) {
            addCriterion("reconciliation_item_id =", value, "reconciliationItemId");
            return (Criteria) this;
        }

        public Criteria andReconciliationItemIdNotEqualTo(Integer value) {
            addCriterion("reconciliation_item_id <>", value, "reconciliationItemId");
            return (Criteria) this;
        }

        public Criteria andReconciliationItemIdGreaterThan(Integer value) {
            addCriterion("reconciliation_item_id >", value, "reconciliationItemId");
            return (Criteria) this;
        }

        public Criteria andReconciliationItemIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("reconciliation_item_id >=", value, "reconciliationItemId");
            return (Criteria) this;
        }

        public Criteria andReconciliationItemIdLessThan(Integer value) {
            addCriterion("reconciliation_item_id <", value, "reconciliationItemId");
            return (Criteria) this;
        }

        public Criteria andReconciliationItemIdLessThanOrEqualTo(Integer value) {
            addCriterion("reconciliation_item_id <=", value, "reconciliationItemId");
            return (Criteria) this;
        }

        public Criteria andReconciliationItemIdIn(List<Integer> values) {
            addCriterion("reconciliation_item_id in", values, "reconciliationItemId");
            return (Criteria) this;
        }

        public Criteria andReconciliationItemIdNotIn(List<Integer> values) {
            addCriterion("reconciliation_item_id not in", values, "reconciliationItemId");
            return (Criteria) this;
        }

        public Criteria andReconciliationItemIdBetween(Integer value1, Integer value2) {
            addCriterion("reconciliation_item_id between", value1, value2, "reconciliationItemId");
            return (Criteria) this;
        }

        public Criteria andReconciliationItemIdNotBetween(Integer value1, Integer value2) {
            addCriterion("reconciliation_item_id not between", value1, value2, "reconciliationItemId");
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

        public Criteria andAreaCodeIsNull() {
            addCriterion("area_code is null");
            return (Criteria) this;
        }

        public Criteria andAreaCodeIsNotNull() {
            addCriterion("area_code is not null");
            return (Criteria) this;
        }

        public Criteria andAreaCodeEqualTo(String value) {
            addCriterion("area_code =", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeNotEqualTo(String value) {
            addCriterion("area_code <>", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeGreaterThan(String value) {
            addCriterion("area_code >", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeGreaterThanOrEqualTo(String value) {
            addCriterion("area_code >=", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeLessThan(String value) {
            addCriterion("area_code <", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeLessThanOrEqualTo(String value) {
            addCriterion("area_code <=", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeLike(String value) {
            addCriterion("area_code like", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeNotLike(String value) {
            addCriterion("area_code not like", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeIn(List<String> values) {
            addCriterion("area_code in", values, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeNotIn(List<String> values) {
            addCriterion("area_code not in", values, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeBetween(String value1, String value2) {
            addCriterion("area_code between", value1, value2, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeNotBetween(String value1, String value2) {
            addCriterion("area_code not between", value1, value2, "areaCode");
            return (Criteria) this;
        }

        public Criteria andCustomerIdIsNull() {
            addCriterion("customer_id is null");
            return (Criteria) this;
        }

        public Criteria andCustomerIdIsNotNull() {
            addCriterion("customer_id is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerIdEqualTo(Integer value) {
            addCriterion("customer_id =", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotEqualTo(Integer value) {
            addCriterion("customer_id <>", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdGreaterThan(Integer value) {
            addCriterion("customer_id >", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("customer_id >=", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdLessThan(Integer value) {
            addCriterion("customer_id <", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdLessThanOrEqualTo(Integer value) {
            addCriterion("customer_id <=", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdIn(List<Integer> values) {
            addCriterion("customer_id in", values, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotIn(List<Integer> values) {
            addCriterion("customer_id not in", values, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdBetween(Integer value1, Integer value2) {
            addCriterion("customer_id between", value1, value2, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("customer_id not between", value1, value2, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIsNull() {
            addCriterion("customer_name is null");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIsNotNull() {
            addCriterion("customer_name is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerNameEqualTo(String value) {
            addCriterion("customer_name =", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotEqualTo(String value) {
            addCriterion("customer_name <>", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameGreaterThan(String value) {
            addCriterion("customer_name >", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameGreaterThanOrEqualTo(String value) {
            addCriterion("customer_name >=", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLessThan(String value) {
            addCriterion("customer_name <", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLessThanOrEqualTo(String value) {
            addCriterion("customer_name <=", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLike(String value) {
            addCriterion("customer_name like", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotLike(String value) {
            addCriterion("customer_name not like", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIn(List<String> values) {
            addCriterion("customer_name in", values, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotIn(List<String> values) {
            addCriterion("customer_name not in", values, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameBetween(String value1, String value2) {
            addCriterion("customer_name between", value1, value2, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotBetween(String value1, String value2) {
            addCriterion("customer_name not between", value1, value2, "customerName");
            return (Criteria) this;
        }

        public Criteria andProjectIdIsNull() {
            addCriterion("project_id is null");
            return (Criteria) this;
        }

        public Criteria andProjectIdIsNotNull() {
            addCriterion("project_id is not null");
            return (Criteria) this;
        }

        public Criteria andProjectIdEqualTo(Integer value) {
            addCriterion("project_id =", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotEqualTo(Integer value) {
            addCriterion("project_id <>", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThan(Integer value) {
            addCriterion("project_id >", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("project_id >=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThan(Integer value) {
            addCriterion("project_id <", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThanOrEqualTo(Integer value) {
            addCriterion("project_id <=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdIn(List<Integer> values) {
            addCriterion("project_id in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotIn(List<Integer> values) {
            addCriterion("project_id not in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdBetween(Integer value1, Integer value2) {
            addCriterion("project_id between", value1, value2, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotBetween(Integer value1, Integer value2) {
            addCriterion("project_id not between", value1, value2, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectNameIsNull() {
            addCriterion("project_name is null");
            return (Criteria) this;
        }

        public Criteria andProjectNameIsNotNull() {
            addCriterion("project_name is not null");
            return (Criteria) this;
        }

        public Criteria andProjectNameEqualTo(String value) {
            addCriterion("project_name =", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotEqualTo(String value) {
            addCriterion("project_name <>", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameGreaterThan(String value) {
            addCriterion("project_name >", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameGreaterThanOrEqualTo(String value) {
            addCriterion("project_name >=", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLessThan(String value) {
            addCriterion("project_name <", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLessThanOrEqualTo(String value) {
            addCriterion("project_name <=", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLike(String value) {
            addCriterion("project_name like", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotLike(String value) {
            addCriterion("project_name not like", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameIn(List<String> values) {
            addCriterion("project_name in", values, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotIn(List<String> values) {
            addCriterion("project_name not in", values, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameBetween(String value1, String value2) {
            addCriterion("project_name between", value1, value2, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotBetween(String value1, String value2) {
            addCriterion("project_name not between", value1, value2, "projectName");
            return (Criteria) this;
        }

        public Criteria andTruckIdIsNull() {
            addCriterion("truck_id is null");
            return (Criteria) this;
        }

        public Criteria andTruckIdIsNotNull() {
            addCriterion("truck_id is not null");
            return (Criteria) this;
        }

        public Criteria andTruckIdEqualTo(Integer value) {
            addCriterion("truck_id =", value, "truckId");
            return (Criteria) this;
        }

        public Criteria andTruckIdNotEqualTo(Integer value) {
            addCriterion("truck_id <>", value, "truckId");
            return (Criteria) this;
        }

        public Criteria andTruckIdGreaterThan(Integer value) {
            addCriterion("truck_id >", value, "truckId");
            return (Criteria) this;
        }

        public Criteria andTruckIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("truck_id >=", value, "truckId");
            return (Criteria) this;
        }

        public Criteria andTruckIdLessThan(Integer value) {
            addCriterion("truck_id <", value, "truckId");
            return (Criteria) this;
        }

        public Criteria andTruckIdLessThanOrEqualTo(Integer value) {
            addCriterion("truck_id <=", value, "truckId");
            return (Criteria) this;
        }

        public Criteria andTruckIdIn(List<Integer> values) {
            addCriterion("truck_id in", values, "truckId");
            return (Criteria) this;
        }

        public Criteria andTruckIdNotIn(List<Integer> values) {
            addCriterion("truck_id not in", values, "truckId");
            return (Criteria) this;
        }

        public Criteria andTruckIdBetween(Integer value1, Integer value2) {
            addCriterion("truck_id between", value1, value2, "truckId");
            return (Criteria) this;
        }

        public Criteria andTruckIdNotBetween(Integer value1, Integer value2) {
            addCriterion("truck_id not between", value1, value2, "truckId");
            return (Criteria) this;
        }

        public Criteria andPlateNumberIsNull() {
            addCriterion("plate_number is null");
            return (Criteria) this;
        }

        public Criteria andPlateNumberIsNotNull() {
            addCriterion("plate_number is not null");
            return (Criteria) this;
        }

        public Criteria andPlateNumberEqualTo(String value) {
            addCriterion("plate_number =", value, "plateNumber");
            return (Criteria) this;
        }

        public Criteria andPlateNumberNotEqualTo(String value) {
            addCriterion("plate_number <>", value, "plateNumber");
            return (Criteria) this;
        }

        public Criteria andPlateNumberGreaterThan(String value) {
            addCriterion("plate_number >", value, "plateNumber");
            return (Criteria) this;
        }

        public Criteria andPlateNumberGreaterThanOrEqualTo(String value) {
            addCriterion("plate_number >=", value, "plateNumber");
            return (Criteria) this;
        }

        public Criteria andPlateNumberLessThan(String value) {
            addCriterion("plate_number <", value, "plateNumber");
            return (Criteria) this;
        }

        public Criteria andPlateNumberLessThanOrEqualTo(String value) {
            addCriterion("plate_number <=", value, "plateNumber");
            return (Criteria) this;
        }

        public Criteria andPlateNumberLike(String value) {
            addCriterion("plate_number like", value, "plateNumber");
            return (Criteria) this;
        }

        public Criteria andPlateNumberNotLike(String value) {
            addCriterion("plate_number not like", value, "plateNumber");
            return (Criteria) this;
        }

        public Criteria andPlateNumberIn(List<String> values) {
            addCriterion("plate_number in", values, "plateNumber");
            return (Criteria) this;
        }

        public Criteria andPlateNumberNotIn(List<String> values) {
            addCriterion("plate_number not in", values, "plateNumber");
            return (Criteria) this;
        }

        public Criteria andPlateNumberBetween(String value1, String value2) {
            addCriterion("plate_number between", value1, value2, "plateNumber");
            return (Criteria) this;
        }

        public Criteria andPlateNumberNotBetween(String value1, String value2) {
            addCriterion("plate_number not between", value1, value2, "plateNumber");
            return (Criteria) this;
        }

        public Criteria andTaxRateValueIsNull() {
            addCriterion("tax_rate_value is null");
            return (Criteria) this;
        }

        public Criteria andTaxRateValueIsNotNull() {
            addCriterion("tax_rate_value is not null");
            return (Criteria) this;
        }

        public Criteria andTaxRateValueEqualTo(BigDecimal value) {
            addCriterion("tax_rate_value =", value, "taxRateValue");
            return (Criteria) this;
        }

        public Criteria andTaxRateValueNotEqualTo(BigDecimal value) {
            addCriterion("tax_rate_value <>", value, "taxRateValue");
            return (Criteria) this;
        }

        public Criteria andTaxRateValueGreaterThan(BigDecimal value) {
            addCriterion("tax_rate_value >", value, "taxRateValue");
            return (Criteria) this;
        }

        public Criteria andTaxRateValueGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("tax_rate_value >=", value, "taxRateValue");
            return (Criteria) this;
        }

        public Criteria andTaxRateValueLessThan(BigDecimal value) {
            addCriterion("tax_rate_value <", value, "taxRateValue");
            return (Criteria) this;
        }

        public Criteria andTaxRateValueLessThanOrEqualTo(BigDecimal value) {
            addCriterion("tax_rate_value <=", value, "taxRateValue");
            return (Criteria) this;
        }

        public Criteria andTaxRateValueIn(List<BigDecimal> values) {
            addCriterion("tax_rate_value in", values, "taxRateValue");
            return (Criteria) this;
        }

        public Criteria andTaxRateValueNotIn(List<BigDecimal> values) {
            addCriterion("tax_rate_value not in", values, "taxRateValue");
            return (Criteria) this;
        }

        public Criteria andTaxRateValueBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("tax_rate_value between", value1, value2, "taxRateValue");
            return (Criteria) this;
        }

        public Criteria andTaxRateValueNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("tax_rate_value not between", value1, value2, "taxRateValue");
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

        public Criteria andDriverHandlingFeeIsNull() {
            addCriterion("driver_handling_fee is null");
            return (Criteria) this;
        }

        public Criteria andDriverHandlingFeeIsNotNull() {
            addCriterion("driver_handling_fee is not null");
            return (Criteria) this;
        }

        public Criteria andDriverHandlingFeeEqualTo(BigDecimal value) {
            addCriterion("driver_handling_fee =", value, "driverHandlingFee");
            return (Criteria) this;
        }

        public Criteria andDriverHandlingFeeNotEqualTo(BigDecimal value) {
            addCriterion("driver_handling_fee <>", value, "driverHandlingFee");
            return (Criteria) this;
        }

        public Criteria andDriverHandlingFeeGreaterThan(BigDecimal value) {
            addCriterion("driver_handling_fee >", value, "driverHandlingFee");
            return (Criteria) this;
        }

        public Criteria andDriverHandlingFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("driver_handling_fee >=", value, "driverHandlingFee");
            return (Criteria) this;
        }

        public Criteria andDriverHandlingFeeLessThan(BigDecimal value) {
            addCriterion("driver_handling_fee <", value, "driverHandlingFee");
            return (Criteria) this;
        }

        public Criteria andDriverHandlingFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("driver_handling_fee <=", value, "driverHandlingFee");
            return (Criteria) this;
        }

        public Criteria andDriverHandlingFeeIn(List<BigDecimal> values) {
            addCriterion("driver_handling_fee in", values, "driverHandlingFee");
            return (Criteria) this;
        }

        public Criteria andDriverHandlingFeeNotIn(List<BigDecimal> values) {
            addCriterion("driver_handling_fee not in", values, "driverHandlingFee");
            return (Criteria) this;
        }

        public Criteria andDriverHandlingFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("driver_handling_fee between", value1, value2, "driverHandlingFee");
            return (Criteria) this;
        }

        public Criteria andDriverHandlingFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("driver_handling_fee not between", value1, value2, "driverHandlingFee");
            return (Criteria) this;
        }

        public Criteria andLaborerHandlingFeeIsNull() {
            addCriterion("laborer_handling_fee is null");
            return (Criteria) this;
        }

        public Criteria andLaborerHandlingFeeIsNotNull() {
            addCriterion("laborer_handling_fee is not null");
            return (Criteria) this;
        }

        public Criteria andLaborerHandlingFeeEqualTo(BigDecimal value) {
            addCriterion("laborer_handling_fee =", value, "laborerHandlingFee");
            return (Criteria) this;
        }

        public Criteria andLaborerHandlingFeeNotEqualTo(BigDecimal value) {
            addCriterion("laborer_handling_fee <>", value, "laborerHandlingFee");
            return (Criteria) this;
        }

        public Criteria andLaborerHandlingFeeGreaterThan(BigDecimal value) {
            addCriterion("laborer_handling_fee >", value, "laborerHandlingFee");
            return (Criteria) this;
        }

        public Criteria andLaborerHandlingFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("laborer_handling_fee >=", value, "laborerHandlingFee");
            return (Criteria) this;
        }

        public Criteria andLaborerHandlingFeeLessThan(BigDecimal value) {
            addCriterion("laborer_handling_fee <", value, "laborerHandlingFee");
            return (Criteria) this;
        }

        public Criteria andLaborerHandlingFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("laborer_handling_fee <=", value, "laborerHandlingFee");
            return (Criteria) this;
        }

        public Criteria andLaborerHandlingFeeIn(List<BigDecimal> values) {
            addCriterion("laborer_handling_fee in", values, "laborerHandlingFee");
            return (Criteria) this;
        }

        public Criteria andLaborerHandlingFeeNotIn(List<BigDecimal> values) {
            addCriterion("laborer_handling_fee not in", values, "laborerHandlingFee");
            return (Criteria) this;
        }

        public Criteria andLaborerHandlingFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("laborer_handling_fee between", value1, value2, "laborerHandlingFee");
            return (Criteria) this;
        }

        public Criteria andLaborerHandlingFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("laborer_handling_fee not between", value1, value2, "laborerHandlingFee");
            return (Criteria) this;
        }

        public Criteria andHasPayFreightIsNull() {
            addCriterion("has_pay_freight is null");
            return (Criteria) this;
        }

        public Criteria andHasPayFreightIsNotNull() {
            addCriterion("has_pay_freight is not null");
            return (Criteria) this;
        }

        public Criteria andHasPayFreightEqualTo(BigDecimal value) {
            addCriterion("has_pay_freight =", value, "hasPayFreight");
            return (Criteria) this;
        }

        public Criteria andHasPayFreightNotEqualTo(BigDecimal value) {
            addCriterion("has_pay_freight <>", value, "hasPayFreight");
            return (Criteria) this;
        }

        public Criteria andHasPayFreightGreaterThan(BigDecimal value) {
            addCriterion("has_pay_freight >", value, "hasPayFreight");
            return (Criteria) this;
        }

        public Criteria andHasPayFreightGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("has_pay_freight >=", value, "hasPayFreight");
            return (Criteria) this;
        }

        public Criteria andHasPayFreightLessThan(BigDecimal value) {
            addCriterion("has_pay_freight <", value, "hasPayFreight");
            return (Criteria) this;
        }

        public Criteria andHasPayFreightLessThanOrEqualTo(BigDecimal value) {
            addCriterion("has_pay_freight <=", value, "hasPayFreight");
            return (Criteria) this;
        }

        public Criteria andHasPayFreightIn(List<BigDecimal> values) {
            addCriterion("has_pay_freight in", values, "hasPayFreight");
            return (Criteria) this;
        }

        public Criteria andHasPayFreightNotIn(List<BigDecimal> values) {
            addCriterion("has_pay_freight not in", values, "hasPayFreight");
            return (Criteria) this;
        }

        public Criteria andHasPayFreightBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("has_pay_freight between", value1, value2, "hasPayFreight");
            return (Criteria) this;
        }

        public Criteria andHasPayFreightNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("has_pay_freight not between", value1, value2, "hasPayFreight");
            return (Criteria) this;
        }

        public Criteria andPayStatusIsNull() {
            addCriterion("pay_status is null");
            return (Criteria) this;
        }

        public Criteria andPayStatusIsNotNull() {
            addCriterion("pay_status is not null");
            return (Criteria) this;
        }

        public Criteria andPayStatusEqualTo(Byte value) {
            addCriterion("pay_status =", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusNotEqualTo(Byte value) {
            addCriterion("pay_status <>", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusGreaterThan(Byte value) {
            addCriterion("pay_status >", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("pay_status >=", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusLessThan(Byte value) {
            addCriterion("pay_status <", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusLessThanOrEqualTo(Byte value) {
            addCriterion("pay_status <=", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusIn(List<Byte> values) {
            addCriterion("pay_status in", values, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusNotIn(List<Byte> values) {
            addCriterion("pay_status not in", values, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusBetween(Byte value1, Byte value2) {
            addCriterion("pay_status between", value1, value2, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("pay_status not between", value1, value2, "payStatus");
            return (Criteria) this;
        }

        public Criteria andLesseeNameIsNull() {
            addCriterion("lessee_name is null");
            return (Criteria) this;
        }

        public Criteria andLesseeNameIsNotNull() {
            addCriterion("lessee_name is not null");
            return (Criteria) this;
        }

        public Criteria andLesseeNameEqualTo(String value) {
            addCriterion("lessee_name =", value, "lesseeName");
            return (Criteria) this;
        }

        public Criteria andLesseeNameNotEqualTo(String value) {
            addCriterion("lessee_name <>", value, "lesseeName");
            return (Criteria) this;
        }

        public Criteria andLesseeNameGreaterThan(String value) {
            addCriterion("lessee_name >", value, "lesseeName");
            return (Criteria) this;
        }

        public Criteria andLesseeNameGreaterThanOrEqualTo(String value) {
            addCriterion("lessee_name >=", value, "lesseeName");
            return (Criteria) this;
        }

        public Criteria andLesseeNameLessThan(String value) {
            addCriterion("lessee_name <", value, "lesseeName");
            return (Criteria) this;
        }

        public Criteria andLesseeNameLessThanOrEqualTo(String value) {
            addCriterion("lessee_name <=", value, "lesseeName");
            return (Criteria) this;
        }

        public Criteria andLesseeNameLike(String value) {
            addCriterion("lessee_name like", value, "lesseeName");
            return (Criteria) this;
        }

        public Criteria andLesseeNameNotLike(String value) {
            addCriterion("lessee_name not like", value, "lesseeName");
            return (Criteria) this;
        }

        public Criteria andLesseeNameIn(List<String> values) {
            addCriterion("lessee_name in", values, "lesseeName");
            return (Criteria) this;
        }

        public Criteria andLesseeNameNotIn(List<String> values) {
            addCriterion("lessee_name not in", values, "lesseeName");
            return (Criteria) this;
        }

        public Criteria andLesseeNameBetween(String value1, String value2) {
            addCriterion("lessee_name between", value1, value2, "lesseeName");
            return (Criteria) this;
        }

        public Criteria andLesseeNameNotBetween(String value1, String value2) {
            addCriterion("lessee_name not between", value1, value2, "lesseeName");
            return (Criteria) this;
        }

        public Criteria andLesseeIdIsNull() {
            addCriterion("lessee_id is null");
            return (Criteria) this;
        }

        public Criteria andLesseeIdIsNotNull() {
            addCriterion("lessee_id is not null");
            return (Criteria) this;
        }

        public Criteria andLesseeIdEqualTo(Integer value) {
            addCriterion("lessee_id =", value, "lesseeId");
            return (Criteria) this;
        }

        public Criteria andLesseeIdNotEqualTo(Integer value) {
            addCriterion("lessee_id <>", value, "lesseeId");
            return (Criteria) this;
        }

        public Criteria andLesseeIdGreaterThan(Integer value) {
            addCriterion("lessee_id >", value, "lesseeId");
            return (Criteria) this;
        }

        public Criteria andLesseeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("lessee_id >=", value, "lesseeId");
            return (Criteria) this;
        }

        public Criteria andLesseeIdLessThan(Integer value) {
            addCriterion("lessee_id <", value, "lesseeId");
            return (Criteria) this;
        }

        public Criteria andLesseeIdLessThanOrEqualTo(Integer value) {
            addCriterion("lessee_id <=", value, "lesseeId");
            return (Criteria) this;
        }

        public Criteria andLesseeIdIn(List<Integer> values) {
            addCriterion("lessee_id in", values, "lesseeId");
            return (Criteria) this;
        }

        public Criteria andLesseeIdNotIn(List<Integer> values) {
            addCriterion("lessee_id not in", values, "lesseeId");
            return (Criteria) this;
        }

        public Criteria andLesseeIdBetween(Integer value1, Integer value2) {
            addCriterion("lessee_id between", value1, value2, "lesseeId");
            return (Criteria) this;
        }

        public Criteria andLesseeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("lessee_id not between", value1, value2, "lesseeId");
            return (Criteria) this;
        }

        public Criteria andDriverInitialBeforeTaxIsNull() {
            addCriterion("driver_initial_before_tax is null");
            return (Criteria) this;
        }

        public Criteria andDriverInitialBeforeTaxIsNotNull() {
            addCriterion("driver_initial_before_tax is not null");
            return (Criteria) this;
        }

        public Criteria andDriverInitialBeforeTaxEqualTo(BigDecimal value) {
            addCriterion("driver_initial_before_tax =", value, "driverInitialBeforeTax");
            return (Criteria) this;
        }

        public Criteria andDriverInitialBeforeTaxNotEqualTo(BigDecimal value) {
            addCriterion("driver_initial_before_tax <>", value, "driverInitialBeforeTax");
            return (Criteria) this;
        }

        public Criteria andDriverInitialBeforeTaxGreaterThan(BigDecimal value) {
            addCriterion("driver_initial_before_tax >", value, "driverInitialBeforeTax");
            return (Criteria) this;
        }

        public Criteria andDriverInitialBeforeTaxGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("driver_initial_before_tax >=", value, "driverInitialBeforeTax");
            return (Criteria) this;
        }

        public Criteria andDriverInitialBeforeTaxLessThan(BigDecimal value) {
            addCriterion("driver_initial_before_tax <", value, "driverInitialBeforeTax");
            return (Criteria) this;
        }

        public Criteria andDriverInitialBeforeTaxLessThanOrEqualTo(BigDecimal value) {
            addCriterion("driver_initial_before_tax <=", value, "driverInitialBeforeTax");
            return (Criteria) this;
        }

        public Criteria andDriverInitialBeforeTaxIn(List<BigDecimal> values) {
            addCriterion("driver_initial_before_tax in", values, "driverInitialBeforeTax");
            return (Criteria) this;
        }

        public Criteria andDriverInitialBeforeTaxNotIn(List<BigDecimal> values) {
            addCriterion("driver_initial_before_tax not in", values, "driverInitialBeforeTax");
            return (Criteria) this;
        }

        public Criteria andDriverInitialBeforeTaxBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("driver_initial_before_tax between", value1, value2, "driverInitialBeforeTax");
            return (Criteria) this;
        }

        public Criteria andDriverInitialBeforeTaxNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("driver_initial_before_tax not between", value1, value2, "driverInitialBeforeTax");
            return (Criteria) this;
        }

        public Criteria andDriverInitialAfterTaxIsNull() {
            addCriterion("driver_initial_after_tax is null");
            return (Criteria) this;
        }

        public Criteria andDriverInitialAfterTaxIsNotNull() {
            addCriterion("driver_initial_after_tax is not null");
            return (Criteria) this;
        }

        public Criteria andDriverInitialAfterTaxEqualTo(BigDecimal value) {
            addCriterion("driver_initial_after_tax =", value, "driverInitialAfterTax");
            return (Criteria) this;
        }

        public Criteria andDriverInitialAfterTaxNotEqualTo(BigDecimal value) {
            addCriterion("driver_initial_after_tax <>", value, "driverInitialAfterTax");
            return (Criteria) this;
        }

        public Criteria andDriverInitialAfterTaxGreaterThan(BigDecimal value) {
            addCriterion("driver_initial_after_tax >", value, "driverInitialAfterTax");
            return (Criteria) this;
        }

        public Criteria andDriverInitialAfterTaxGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("driver_initial_after_tax >=", value, "driverInitialAfterTax");
            return (Criteria) this;
        }

        public Criteria andDriverInitialAfterTaxLessThan(BigDecimal value) {
            addCriterion("driver_initial_after_tax <", value, "driverInitialAfterTax");
            return (Criteria) this;
        }

        public Criteria andDriverInitialAfterTaxLessThanOrEqualTo(BigDecimal value) {
            addCriterion("driver_initial_after_tax <=", value, "driverInitialAfterTax");
            return (Criteria) this;
        }

        public Criteria andDriverInitialAfterTaxIn(List<BigDecimal> values) {
            addCriterion("driver_initial_after_tax in", values, "driverInitialAfterTax");
            return (Criteria) this;
        }

        public Criteria andDriverInitialAfterTaxNotIn(List<BigDecimal> values) {
            addCriterion("driver_initial_after_tax not in", values, "driverInitialAfterTax");
            return (Criteria) this;
        }

        public Criteria andDriverInitialAfterTaxBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("driver_initial_after_tax between", value1, value2, "driverInitialAfterTax");
            return (Criteria) this;
        }

        public Criteria andDriverInitialAfterTaxNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("driver_initial_after_tax not between", value1, value2, "driverInitialAfterTax");
            return (Criteria) this;
        }

        public Criteria andDriverFinalBeforeTaxIsNull() {
            addCriterion("driver_final_before_tax is null");
            return (Criteria) this;
        }

        public Criteria andDriverFinalBeforeTaxIsNotNull() {
            addCriterion("driver_final_before_tax is not null");
            return (Criteria) this;
        }

        public Criteria andDriverFinalBeforeTaxEqualTo(BigDecimal value) {
            addCriterion("driver_final_before_tax =", value, "driverFinalBeforeTax");
            return (Criteria) this;
        }

        public Criteria andDriverFinalBeforeTaxNotEqualTo(BigDecimal value) {
            addCriterion("driver_final_before_tax <>", value, "driverFinalBeforeTax");
            return (Criteria) this;
        }

        public Criteria andDriverFinalBeforeTaxGreaterThan(BigDecimal value) {
            addCriterion("driver_final_before_tax >", value, "driverFinalBeforeTax");
            return (Criteria) this;
        }

        public Criteria andDriverFinalBeforeTaxGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("driver_final_before_tax >=", value, "driverFinalBeforeTax");
            return (Criteria) this;
        }

        public Criteria andDriverFinalBeforeTaxLessThan(BigDecimal value) {
            addCriterion("driver_final_before_tax <", value, "driverFinalBeforeTax");
            return (Criteria) this;
        }

        public Criteria andDriverFinalBeforeTaxLessThanOrEqualTo(BigDecimal value) {
            addCriterion("driver_final_before_tax <=", value, "driverFinalBeforeTax");
            return (Criteria) this;
        }

        public Criteria andDriverFinalBeforeTaxIn(List<BigDecimal> values) {
            addCriterion("driver_final_before_tax in", values, "driverFinalBeforeTax");
            return (Criteria) this;
        }

        public Criteria andDriverFinalBeforeTaxNotIn(List<BigDecimal> values) {
            addCriterion("driver_final_before_tax not in", values, "driverFinalBeforeTax");
            return (Criteria) this;
        }

        public Criteria andDriverFinalBeforeTaxBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("driver_final_before_tax between", value1, value2, "driverFinalBeforeTax");
            return (Criteria) this;
        }

        public Criteria andDriverFinalBeforeTaxNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("driver_final_before_tax not between", value1, value2, "driverFinalBeforeTax");
            return (Criteria) this;
        }

        public Criteria andDriverFinalAfterTaxIsNull() {
            addCriterion("driver_final_after_tax is null");
            return (Criteria) this;
        }

        public Criteria andDriverFinalAfterTaxIsNotNull() {
            addCriterion("driver_final_after_tax is not null");
            return (Criteria) this;
        }

        public Criteria andDriverFinalAfterTaxEqualTo(BigDecimal value) {
            addCriterion("driver_final_after_tax =", value, "driverFinalAfterTax");
            return (Criteria) this;
        }

        public Criteria andDriverFinalAfterTaxNotEqualTo(BigDecimal value) {
            addCriterion("driver_final_after_tax <>", value, "driverFinalAfterTax");
            return (Criteria) this;
        }

        public Criteria andDriverFinalAfterTaxGreaterThan(BigDecimal value) {
            addCriterion("driver_final_after_tax >", value, "driverFinalAfterTax");
            return (Criteria) this;
        }

        public Criteria andDriverFinalAfterTaxGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("driver_final_after_tax >=", value, "driverFinalAfterTax");
            return (Criteria) this;
        }

        public Criteria andDriverFinalAfterTaxLessThan(BigDecimal value) {
            addCriterion("driver_final_after_tax <", value, "driverFinalAfterTax");
            return (Criteria) this;
        }

        public Criteria andDriverFinalAfterTaxLessThanOrEqualTo(BigDecimal value) {
            addCriterion("driver_final_after_tax <=", value, "driverFinalAfterTax");
            return (Criteria) this;
        }

        public Criteria andDriverFinalAfterTaxIn(List<BigDecimal> values) {
            addCriterion("driver_final_after_tax in", values, "driverFinalAfterTax");
            return (Criteria) this;
        }

        public Criteria andDriverFinalAfterTaxNotIn(List<BigDecimal> values) {
            addCriterion("driver_final_after_tax not in", values, "driverFinalAfterTax");
            return (Criteria) this;
        }

        public Criteria andDriverFinalAfterTaxBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("driver_final_after_tax between", value1, value2, "driverFinalAfterTax");
            return (Criteria) this;
        }

        public Criteria andDriverFinalAfterTaxNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("driver_final_after_tax not between", value1, value2, "driverFinalAfterTax");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIsNull() {
            addCriterion("create_user_id is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIsNotNull() {
            addCriterion("create_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdEqualTo(Integer value) {
            addCriterion("create_user_id =", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotEqualTo(Integer value) {
            addCriterion("create_user_id <>", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThan(Integer value) {
            addCriterion("create_user_id >", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("create_user_id >=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThan(Integer value) {
            addCriterion("create_user_id <", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("create_user_id <=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIn(List<Integer> values) {
            addCriterion("create_user_id in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotIn(List<Integer> values) {
            addCriterion("create_user_id not in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdBetween(Integer value1, Integer value2) {
            addCriterion("create_user_id between", value1, value2, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("create_user_id not between", value1, value2, "createUserId");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeIsNull() {
            addCriterion("last_update_time is null");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeIsNotNull() {
            addCriterion("last_update_time is not null");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeEqualTo(Date value) {
            addCriterion("last_update_time =", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeNotEqualTo(Date value) {
            addCriterion("last_update_time <>", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeGreaterThan(Date value) {
            addCriterion("last_update_time >", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("last_update_time >=", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeLessThan(Date value) {
            addCriterion("last_update_time <", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("last_update_time <=", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeIn(List<Date> values) {
            addCriterion("last_update_time in", values, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeNotIn(List<Date> values) {
            addCriterion("last_update_time not in", values, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("last_update_time between", value1, value2, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("last_update_time not between", value1, value2, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateUserIdIsNull() {
            addCriterion("last_update_user_id is null");
            return (Criteria) this;
        }

        public Criteria andLastUpdateUserIdIsNotNull() {
            addCriterion("last_update_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andLastUpdateUserIdEqualTo(Integer value) {
            addCriterion("last_update_user_id =", value, "lastUpdateUserId");
            return (Criteria) this;
        }

        public Criteria andLastUpdateUserIdNotEqualTo(Integer value) {
            addCriterion("last_update_user_id <>", value, "lastUpdateUserId");
            return (Criteria) this;
        }

        public Criteria andLastUpdateUserIdGreaterThan(Integer value) {
            addCriterion("last_update_user_id >", value, "lastUpdateUserId");
            return (Criteria) this;
        }

        public Criteria andLastUpdateUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("last_update_user_id >=", value, "lastUpdateUserId");
            return (Criteria) this;
        }

        public Criteria andLastUpdateUserIdLessThan(Integer value) {
            addCriterion("last_update_user_id <", value, "lastUpdateUserId");
            return (Criteria) this;
        }

        public Criteria andLastUpdateUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("last_update_user_id <=", value, "lastUpdateUserId");
            return (Criteria) this;
        }

        public Criteria andLastUpdateUserIdIn(List<Integer> values) {
            addCriterion("last_update_user_id in", values, "lastUpdateUserId");
            return (Criteria) this;
        }

        public Criteria andLastUpdateUserIdNotIn(List<Integer> values) {
            addCriterion("last_update_user_id not in", values, "lastUpdateUserId");
            return (Criteria) this;
        }

        public Criteria andLastUpdateUserIdBetween(Integer value1, Integer value2) {
            addCriterion("last_update_user_id between", value1, value2, "lastUpdateUserId");
            return (Criteria) this;
        }

        public Criteria andLastUpdateUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("last_update_user_id not between", value1, value2, "lastUpdateUserId");
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

        public Criteria andVendorNameIsNull() {
            addCriterion("vendor_name is null");
            return (Criteria) this;
        }

        public Criteria andVendorNameIsNotNull() {
            addCriterion("vendor_name is not null");
            return (Criteria) this;
        }

        public Criteria andVendorNameEqualTo(String value) {
            addCriterion("vendor_name =", value, "vendorName");
            return (Criteria) this;
        }

        public Criteria andVendorNameNotEqualTo(String value) {
            addCriterion("vendor_name <>", value, "vendorName");
            return (Criteria) this;
        }

        public Criteria andVendorNameGreaterThan(String value) {
            addCriterion("vendor_name >", value, "vendorName");
            return (Criteria) this;
        }

        public Criteria andVendorNameGreaterThanOrEqualTo(String value) {
            addCriterion("vendor_name >=", value, "vendorName");
            return (Criteria) this;
        }

        public Criteria andVendorNameLessThan(String value) {
            addCriterion("vendor_name <", value, "vendorName");
            return (Criteria) this;
        }

        public Criteria andVendorNameLessThanOrEqualTo(String value) {
            addCriterion("vendor_name <=", value, "vendorName");
            return (Criteria) this;
        }

        public Criteria andVendorNameLike(String value) {
            addCriterion("vendor_name like", value, "vendorName");
            return (Criteria) this;
        }

        public Criteria andVendorNameNotLike(String value) {
            addCriterion("vendor_name not like", value, "vendorName");
            return (Criteria) this;
        }

        public Criteria andVendorNameIn(List<String> values) {
            addCriterion("vendor_name in", values, "vendorName");
            return (Criteria) this;
        }

        public Criteria andVendorNameNotIn(List<String> values) {
            addCriterion("vendor_name not in", values, "vendorName");
            return (Criteria) this;
        }

        public Criteria andVendorNameBetween(String value1, String value2) {
            addCriterion("vendor_name between", value1, value2, "vendorName");
            return (Criteria) this;
        }

        public Criteria andVendorNameNotBetween(String value1, String value2) {
            addCriterion("vendor_name not between", value1, value2, "vendorName");
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