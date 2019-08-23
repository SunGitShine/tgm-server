package com.juma.tgm.fms.domain.v2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReconciliationChangeLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int startOffSet = -1;

    protected int size = -1;

    public ReconciliationChangeLogExample() {
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

        public Criteria andReconciliationChangeLogIdIsNull() {
            addCriterion("reconciliation_change_log_id is null");
            return (Criteria) this;
        }

        public Criteria andReconciliationChangeLogIdIsNotNull() {
            addCriterion("reconciliation_change_log_id is not null");
            return (Criteria) this;
        }

        public Criteria andReconciliationChangeLogIdEqualTo(Integer value) {
            addCriterion("reconciliation_change_log_id =", value, "reconciliationChangeLogId");
            return (Criteria) this;
        }

        public Criteria andReconciliationChangeLogIdNotEqualTo(Integer value) {
            addCriterion("reconciliation_change_log_id <>", value, "reconciliationChangeLogId");
            return (Criteria) this;
        }

        public Criteria andReconciliationChangeLogIdGreaterThan(Integer value) {
            addCriterion("reconciliation_change_log_id >", value, "reconciliationChangeLogId");
            return (Criteria) this;
        }

        public Criteria andReconciliationChangeLogIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("reconciliation_change_log_id >=", value, "reconciliationChangeLogId");
            return (Criteria) this;
        }

        public Criteria andReconciliationChangeLogIdLessThan(Integer value) {
            addCriterion("reconciliation_change_log_id <", value, "reconciliationChangeLogId");
            return (Criteria) this;
        }

        public Criteria andReconciliationChangeLogIdLessThanOrEqualTo(Integer value) {
            addCriterion("reconciliation_change_log_id <=", value, "reconciliationChangeLogId");
            return (Criteria) this;
        }

        public Criteria andReconciliationChangeLogIdIn(List<Integer> values) {
            addCriterion("reconciliation_change_log_id in", values, "reconciliationChangeLogId");
            return (Criteria) this;
        }

        public Criteria andReconciliationChangeLogIdNotIn(List<Integer> values) {
            addCriterion("reconciliation_change_log_id not in", values, "reconciliationChangeLogId");
            return (Criteria) this;
        }

        public Criteria andReconciliationChangeLogIdBetween(Integer value1, Integer value2) {
            addCriterion("reconciliation_change_log_id between", value1, value2, "reconciliationChangeLogId");
            return (Criteria) this;
        }

        public Criteria andReconciliationChangeLogIdNotBetween(Integer value1, Integer value2) {
            addCriterion("reconciliation_change_log_id not between", value1, value2, "reconciliationChangeLogId");
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

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andDriverNameIsNull() {
            addCriterion("driver_name is null");
            return (Criteria) this;
        }

        public Criteria andDriverNameIsNotNull() {
            addCriterion("driver_name is not null");
            return (Criteria) this;
        }

        public Criteria andDriverNameEqualTo(String value) {
            addCriterion("driver_name =", value, "driverName");
            return (Criteria) this;
        }

        public Criteria andDriverNameNotEqualTo(String value) {
            addCriterion("driver_name <>", value, "driverName");
            return (Criteria) this;
        }

        public Criteria andDriverNameGreaterThan(String value) {
            addCriterion("driver_name >", value, "driverName");
            return (Criteria) this;
        }

        public Criteria andDriverNameGreaterThanOrEqualTo(String value) {
            addCriterion("driver_name >=", value, "driverName");
            return (Criteria) this;
        }

        public Criteria andDriverNameLessThan(String value) {
            addCriterion("driver_name <", value, "driverName");
            return (Criteria) this;
        }

        public Criteria andDriverNameLessThanOrEqualTo(String value) {
            addCriterion("driver_name <=", value, "driverName");
            return (Criteria) this;
        }

        public Criteria andDriverNameLike(String value) {
            addCriterion("driver_name like", value, "driverName");
            return (Criteria) this;
        }

        public Criteria andDriverNameNotLike(String value) {
            addCriterion("driver_name not like", value, "driverName");
            return (Criteria) this;
        }

        public Criteria andDriverNameIn(List<String> values) {
            addCriterion("driver_name in", values, "driverName");
            return (Criteria) this;
        }

        public Criteria andDriverNameNotIn(List<String> values) {
            addCriterion("driver_name not in", values, "driverName");
            return (Criteria) this;
        }

        public Criteria andDriverNameBetween(String value1, String value2) {
            addCriterion("driver_name between", value1, value2, "driverName");
            return (Criteria) this;
        }

        public Criteria andDriverNameNotBetween(String value1, String value2) {
            addCriterion("driver_name not between", value1, value2, "driverName");
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

        public Criteria andBeforeTaxFreightIsNull() {
            addCriterion("before_tax_freight is null");
            return (Criteria) this;
        }

        public Criteria andBeforeTaxFreightIsNotNull() {
            addCriterion("before_tax_freight is not null");
            return (Criteria) this;
        }

        public Criteria andBeforeTaxFreightEqualTo(BigDecimal value) {
            addCriterion("before_tax_freight =", value, "beforeTaxFreight");
            return (Criteria) this;
        }

        public Criteria andBeforeTaxFreightNotEqualTo(BigDecimal value) {
            addCriterion("before_tax_freight <>", value, "beforeTaxFreight");
            return (Criteria) this;
        }

        public Criteria andBeforeTaxFreightGreaterThan(BigDecimal value) {
            addCriterion("before_tax_freight >", value, "beforeTaxFreight");
            return (Criteria) this;
        }

        public Criteria andBeforeTaxFreightGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("before_tax_freight >=", value, "beforeTaxFreight");
            return (Criteria) this;
        }

        public Criteria andBeforeTaxFreightLessThan(BigDecimal value) {
            addCriterion("before_tax_freight <", value, "beforeTaxFreight");
            return (Criteria) this;
        }

        public Criteria andBeforeTaxFreightLessThanOrEqualTo(BigDecimal value) {
            addCriterion("before_tax_freight <=", value, "beforeTaxFreight");
            return (Criteria) this;
        }

        public Criteria andBeforeTaxFreightIn(List<BigDecimal> values) {
            addCriterion("before_tax_freight in", values, "beforeTaxFreight");
            return (Criteria) this;
        }

        public Criteria andBeforeTaxFreightNotIn(List<BigDecimal> values) {
            addCriterion("before_tax_freight not in", values, "beforeTaxFreight");
            return (Criteria) this;
        }

        public Criteria andBeforeTaxFreightBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("before_tax_freight between", value1, value2, "beforeTaxFreight");
            return (Criteria) this;
        }

        public Criteria andBeforeTaxFreightNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("before_tax_freight not between", value1, value2, "beforeTaxFreight");
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

        public Criteria andAfterTaxFreightIsNull() {
            addCriterion("after_tax_freight is null");
            return (Criteria) this;
        }

        public Criteria andAfterTaxFreightIsNotNull() {
            addCriterion("after_tax_freight is not null");
            return (Criteria) this;
        }

        public Criteria andAfterTaxFreightEqualTo(BigDecimal value) {
            addCriterion("after_tax_freight =", value, "afterTaxFreight");
            return (Criteria) this;
        }

        public Criteria andAfterTaxFreightNotEqualTo(BigDecimal value) {
            addCriterion("after_tax_freight <>", value, "afterTaxFreight");
            return (Criteria) this;
        }

        public Criteria andAfterTaxFreightGreaterThan(BigDecimal value) {
            addCriterion("after_tax_freight >", value, "afterTaxFreight");
            return (Criteria) this;
        }

        public Criteria andAfterTaxFreightGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("after_tax_freight >=", value, "afterTaxFreight");
            return (Criteria) this;
        }

        public Criteria andAfterTaxFreightLessThan(BigDecimal value) {
            addCriterion("after_tax_freight <", value, "afterTaxFreight");
            return (Criteria) this;
        }

        public Criteria andAfterTaxFreightLessThanOrEqualTo(BigDecimal value) {
            addCriterion("after_tax_freight <=", value, "afterTaxFreight");
            return (Criteria) this;
        }

        public Criteria andAfterTaxFreightIn(List<BigDecimal> values) {
            addCriterion("after_tax_freight in", values, "afterTaxFreight");
            return (Criteria) this;
        }

        public Criteria andAfterTaxFreightNotIn(List<BigDecimal> values) {
            addCriterion("after_tax_freight not in", values, "afterTaxFreight");
            return (Criteria) this;
        }

        public Criteria andAfterTaxFreightBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("after_tax_freight between", value1, value2, "afterTaxFreight");
            return (Criteria) this;
        }

        public Criteria andAfterTaxFreightNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("after_tax_freight not between", value1, value2, "afterTaxFreight");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNull() {
            addCriterion("user_name is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("user_name is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("user_name =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("user_name <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("user_name >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("user_name >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("user_name <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("user_name <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("user_name like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("user_name not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("user_name in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("user_name not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("user_name between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("user_name not between", value1, value2, "userName");
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

        public Criteria andNoteIsNull() {
            addCriterion("note is null");
            return (Criteria) this;
        }

        public Criteria andNoteIsNotNull() {
            addCriterion("note is not null");
            return (Criteria) this;
        }

        public Criteria andNoteEqualTo(String value) {
            addCriterion("note =", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotEqualTo(String value) {
            addCriterion("note <>", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThan(String value) {
            addCriterion("note >", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThanOrEqualTo(String value) {
            addCriterion("note >=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThan(String value) {
            addCriterion("note <", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThanOrEqualTo(String value) {
            addCriterion("note <=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLike(String value) {
            addCriterion("note like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotLike(String value) {
            addCriterion("note not like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteIn(List<String> values) {
            addCriterion("note in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotIn(List<String> values) {
            addCriterion("note not in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteBetween(String value1, String value2) {
            addCriterion("note between", value1, value2, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotBetween(String value1, String value2) {
            addCriterion("note not between", value1, value2, "note");
            return (Criteria) this;
        }

        public Criteria andVehicleUseTimeIsNull() {
            addCriterion("vehicle_use_time is null");
            return (Criteria) this;
        }

        public Criteria andVehicleUseTimeIsNotNull() {
            addCriterion("vehicle_use_time is not null");
            return (Criteria) this;
        }

        public Criteria andVehicleUseTimeEqualTo(Date value) {
            addCriterion("vehicle_use_time =", value, "vehicleUseTime");
            return (Criteria) this;
        }

        public Criteria andVehicleUseTimeNotEqualTo(Date value) {
            addCriterion("vehicle_use_time <>", value, "vehicleUseTime");
            return (Criteria) this;
        }

        public Criteria andVehicleUseTimeGreaterThan(Date value) {
            addCriterion("vehicle_use_time >", value, "vehicleUseTime");
            return (Criteria) this;
        }

        public Criteria andVehicleUseTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("vehicle_use_time >=", value, "vehicleUseTime");
            return (Criteria) this;
        }

        public Criteria andVehicleUseTimeLessThan(Date value) {
            addCriterion("vehicle_use_time <", value, "vehicleUseTime");
            return (Criteria) this;
        }

        public Criteria andVehicleUseTimeLessThanOrEqualTo(Date value) {
            addCriterion("vehicle_use_time <=", value, "vehicleUseTime");
            return (Criteria) this;
        }

        public Criteria andVehicleUseTimeIn(List<Date> values) {
            addCriterion("vehicle_use_time in", values, "vehicleUseTime");
            return (Criteria) this;
        }

        public Criteria andVehicleUseTimeNotIn(List<Date> values) {
            addCriterion("vehicle_use_time not in", values, "vehicleUseTime");
            return (Criteria) this;
        }

        public Criteria andVehicleUseTimeBetween(Date value1, Date value2) {
            addCriterion("vehicle_use_time between", value1, value2, "vehicleUseTime");
            return (Criteria) this;
        }

        public Criteria andVehicleUseTimeNotBetween(Date value1, Date value2) {
            addCriterion("vehicle_use_time not between", value1, value2, "vehicleUseTime");
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