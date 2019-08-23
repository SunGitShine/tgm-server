package com.juma.tgm.fms.domain.v2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReconciliationNewExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int startOffSet = -1;

    protected int size = -1;

    public ReconciliationNewExample() {
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

            Criterion c = new Criterion(condition, value);
            if(condition.contains("like") && value instanceof List<?>) {
                c.orLikeListValue = true;
                c.listValue = false;
            } else if(condition.contains("in") && value instanceof List<?>) {
                c.listValue = true;
            }

            criteria.add(c);
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
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

        public Criteria andReconciliationNoIsNull() {
            addCriterion("reconciliation_no is null");
            return (Criteria) this;
        }

        public Criteria andReconciliationNoIsNotNull() {
            addCriterion("reconciliation_no is not null");
            return (Criteria) this;
        }

        public Criteria andReconciliationNoEqualTo(String value) {
            addCriterion("reconciliation_no =", value, "reconciliationNo");
            return (Criteria) this;
        }

        public Criteria andReconciliationNoNotEqualTo(String value) {
            addCriterion("reconciliation_no <>", value, "reconciliationNo");
            return (Criteria) this;
        }

        public Criteria andReconciliationNoGreaterThan(String value) {
            addCriterion("reconciliation_no >", value, "reconciliationNo");
            return (Criteria) this;
        }

        public Criteria andReconciliationNoGreaterThanOrEqualTo(String value) {
            addCriterion("reconciliation_no >=", value, "reconciliationNo");
            return (Criteria) this;
        }

        public Criteria andReconciliationNoLessThan(String value) {
            addCriterion("reconciliation_no <", value, "reconciliationNo");
            return (Criteria) this;
        }

        public Criteria andReconciliationNoLessThanOrEqualTo(String value) {
            addCriterion("reconciliation_no <=", value, "reconciliationNo");
            return (Criteria) this;
        }

        public Criteria andReconciliationNoLike(String value) {
            addCriterion("reconciliation_no like", value, "reconciliationNo");
            return (Criteria) this;
        }

        public Criteria andReconciliationNoNotLike(String value) {
            addCriterion("reconciliation_no not like", value, "reconciliationNo");
            return (Criteria) this;
        }

        public Criteria andReconciliationNoIn(List<String> values) {
            addCriterion("reconciliation_no in", values, "reconciliationNo");
            return (Criteria) this;
        }

        public Criteria andReconciliationNoNotIn(List<String> values) {
            addCriterion("reconciliation_no not in", values, "reconciliationNo");
            return (Criteria) this;
        }

        public Criteria andReconciliationNoBetween(String value1, String value2) {
            addCriterion("reconciliation_no between", value1, value2, "reconciliationNo");
            return (Criteria) this;
        }

        public Criteria andReconciliationNoNotBetween(String value1, String value2) {
            addCriterion("reconciliation_no not between", value1, value2, "reconciliationNo");
            return (Criteria) this;
        }

        public Criteria andReconciliationTypeIsNull() {
            addCriterion("reconciliation_type is null");
            return (Criteria) this;
        }

        public Criteria andReconciliationTypeIsNotNull() {
            addCriterion("reconciliation_type is not null");
            return (Criteria) this;
        }

        public Criteria andReconciliationTypeEqualTo(Integer value) {
            addCriterion("reconciliation_type =", value, "reconciliationType");
            return (Criteria) this;
        }

        public Criteria andReconciliationTypeNotEqualTo(Integer value) {
            addCriterion("reconciliation_type <>", value, "reconciliationType");
            return (Criteria) this;
        }

        public Criteria andReconciliationTypeGreaterThan(Integer value) {
            addCriterion("reconciliation_type >", value, "reconciliationType");
            return (Criteria) this;
        }

        public Criteria andReconciliationTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("reconciliation_type >=", value, "reconciliationType");
            return (Criteria) this;
        }

        public Criteria andReconciliationTypeLessThan(Integer value) {
            addCriterion("reconciliation_type <", value, "reconciliationType");
            return (Criteria) this;
        }

        public Criteria andReconciliationTypeLessThanOrEqualTo(Integer value) {
            addCriterion("reconciliation_type <=", value, "reconciliationType");
            return (Criteria) this;
        }

        public Criteria andReconciliationTypeIn(List<Integer> values) {
            addCriterion("reconciliation_type in", values, "reconciliationType");
            return (Criteria) this;
        }

        public Criteria andReconciliationTypeNotIn(List<Integer> values) {
            addCriterion("reconciliation_type not in", values, "reconciliationType");
            return (Criteria) this;
        }

        public Criteria andReconciliationTypeBetween(Integer value1, Integer value2) {
            addCriterion("reconciliation_type between", value1, value2, "reconciliationType");
            return (Criteria) this;
        }

        public Criteria andReconciliationTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("reconciliation_type not between", value1, value2, "reconciliationType");
            return (Criteria) this;
        }

        public Criteria andReconciliationStatusIsNull() {
            addCriterion("reconciliation_status is null");
            return (Criteria) this;
        }

        public Criteria andReconciliationStatusIsNotNull() {
            addCriterion("reconciliation_status is not null");
            return (Criteria) this;
        }

        public Criteria andReconciliationStatusEqualTo(Byte value) {
            addCriterion("reconciliation_status =", value, "reconciliationStatus");
            return (Criteria) this;
        }

        public Criteria andReconciliationStatusNotEqualTo(Byte value) {
            addCriterion("reconciliation_status <>", value, "reconciliationStatus");
            return (Criteria) this;
        }

        public Criteria andReconciliationStatusGreaterThan(Byte value) {
            addCriterion("reconciliation_status >", value, "reconciliationStatus");
            return (Criteria) this;
        }

        public Criteria andReconciliationStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("reconciliation_status >=", value, "reconciliationStatus");
            return (Criteria) this;
        }

        public Criteria andReconciliationStatusLessThan(Byte value) {
            addCriterion("reconciliation_status <", value, "reconciliationStatus");
            return (Criteria) this;
        }

        public Criteria andReconciliationStatusLessThanOrEqualTo(Byte value) {
            addCriterion("reconciliation_status <=", value, "reconciliationStatus");
            return (Criteria) this;
        }

        public Criteria andReconciliationStatusIn(List<Byte> values) {
            addCriterion("reconciliation_status in", values, "reconciliationStatus");
            return (Criteria) this;
        }

        public Criteria andReconciliationStatusNotIn(List<Byte> values) {
            addCriterion("reconciliation_status not in", values, "reconciliationStatus");
            return (Criteria) this;
        }

        public Criteria andReconciliationStatusBetween(Byte value1, Byte value2) {
            addCriterion("reconciliation_status between", value1, value2, "reconciliationStatus");
            return (Criteria) this;
        }

        public Criteria andReconciliationStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("reconciliation_status not between", value1, value2, "reconciliationStatus");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeIsNull() {
            addCriterion("submit_time is null");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeIsNotNull() {
            addCriterion("submit_time is not null");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeEqualTo(Date value) {
            addCriterion("submit_time =", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeNotEqualTo(Date value) {
            addCriterion("submit_time <>", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeGreaterThan(Date value) {
            addCriterion("submit_time >", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("submit_time >=", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeLessThan(Date value) {
            addCriterion("submit_time <", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeLessThanOrEqualTo(Date value) {
            addCriterion("submit_time <=", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeIn(List<Date> values) {
            addCriterion("submit_time in", values, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeNotIn(List<Date> values) {
            addCriterion("submit_time not in", values, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeBetween(Date value1, Date value2) {
            addCriterion("submit_time between", value1, value2, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeNotBetween(Date value1, Date value2) {
            addCriterion("submit_time not between", value1, value2, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitterIsNull() {
            addCriterion("submitter is null");
            return (Criteria) this;
        }

        public Criteria andSubmitterIsNotNull() {
            addCriterion("submitter is not null");
            return (Criteria) this;
        }

        public Criteria andSubmitterEqualTo(Integer value) {
            addCriterion("submitter =", value, "submitter");
            return (Criteria) this;
        }

        public Criteria andSubmitterNotEqualTo(Integer value) {
            addCriterion("submitter <>", value, "submitter");
            return (Criteria) this;
        }

        public Criteria andSubmitterGreaterThan(Integer value) {
            addCriterion("submitter >", value, "submitter");
            return (Criteria) this;
        }

        public Criteria andSubmitterGreaterThanOrEqualTo(Integer value) {
            addCriterion("submitter >=", value, "submitter");
            return (Criteria) this;
        }

        public Criteria andSubmitterLessThan(Integer value) {
            addCriterion("submitter <", value, "submitter");
            return (Criteria) this;
        }

        public Criteria andSubmitterLessThanOrEqualTo(Integer value) {
            addCriterion("submitter <=", value, "submitter");
            return (Criteria) this;
        }

        public Criteria andSubmitterIn(List<Integer> values) {
            addCriterion("submitter in", values, "submitter");
            return (Criteria) this;
        }

        public Criteria andSubmitterNotIn(List<Integer> values) {
            addCriterion("submitter not in", values, "submitter");
            return (Criteria) this;
        }

        public Criteria andSubmitterBetween(Integer value1, Integer value2) {
            addCriterion("submitter between", value1, value2, "submitter");
            return (Criteria) this;
        }

        public Criteria andSubmitterNotBetween(Integer value1, Integer value2) {
            addCriterion("submitter not between", value1, value2, "submitter");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdIsNull() {
            addCriterion("process_instance_id is null");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdIsNotNull() {
            addCriterion("process_instance_id is not null");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdEqualTo(String value) {
            addCriterion("process_instance_id =", value, "processInstanceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdNotEqualTo(String value) {
            addCriterion("process_instance_id <>", value, "processInstanceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdGreaterThan(String value) {
            addCriterion("process_instance_id >", value, "processInstanceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdGreaterThanOrEqualTo(String value) {
            addCriterion("process_instance_id >=", value, "processInstanceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdLessThan(String value) {
            addCriterion("process_instance_id <", value, "processInstanceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdLessThanOrEqualTo(String value) {
            addCriterion("process_instance_id <=", value, "processInstanceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdLike(String value) {
            addCriterion("process_instance_id like", value, "processInstanceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdNotLike(String value) {
            addCriterion("process_instance_id not like", value, "processInstanceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdIn(List<String> values) {
            addCriterion("process_instance_id in", values, "processInstanceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdNotIn(List<String> values) {
            addCriterion("process_instance_id not in", values, "processInstanceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdBetween(String value1, String value2) {
            addCriterion("process_instance_id between", value1, value2, "processInstanceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdNotBetween(String value1, String value2) {
            addCriterion("process_instance_id not between", value1, value2, "processInstanceId");
            return (Criteria) this;
        }

        public Criteria andTenantIdIsNull() {
            addCriterion("tenant_id is null");
            return (Criteria) this;
        }

        public Criteria andTenantIdIsNotNull() {
            addCriterion("tenant_id is not null");
            return (Criteria) this;
        }

        public Criteria andTenantIdEqualTo(Integer value) {
            addCriterion("tenant_id =", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotEqualTo(Integer value) {
            addCriterion("tenant_id <>", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdGreaterThan(Integer value) {
            addCriterion("tenant_id >", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("tenant_id >=", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdLessThan(Integer value) {
            addCriterion("tenant_id <", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdLessThanOrEqualTo(Integer value) {
            addCriterion("tenant_id <=", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdIn(List<Integer> values) {
            addCriterion("tenant_id in", values, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotIn(List<Integer> values) {
            addCriterion("tenant_id not in", values, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdBetween(Integer value1, Integer value2) {
            addCriterion("tenant_id between", value1, value2, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotBetween(Integer value1, Integer value2) {
            addCriterion("tenant_id not between", value1, value2, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantCodeIsNull() {
            addCriterion("tenant_code is null");
            return (Criteria) this;
        }

        public Criteria andTenantCodeIsNotNull() {
            addCriterion("tenant_code is not null");
            return (Criteria) this;
        }

        public Criteria andTenantCodeEqualTo(String value) {
            addCriterion("tenant_code =", value, "tenantCode");
            return (Criteria) this;
        }

        public Criteria andTenantCodeNotEqualTo(String value) {
            addCriterion("tenant_code <>", value, "tenantCode");
            return (Criteria) this;
        }

        public Criteria andTenantCodeGreaterThan(String value) {
            addCriterion("tenant_code >", value, "tenantCode");
            return (Criteria) this;
        }

        public Criteria andTenantCodeGreaterThanOrEqualTo(String value) {
            addCriterion("tenant_code >=", value, "tenantCode");
            return (Criteria) this;
        }

        public Criteria andTenantCodeLessThan(String value) {
            addCriterion("tenant_code <", value, "tenantCode");
            return (Criteria) this;
        }

        public Criteria andTenantCodeLessThanOrEqualTo(String value) {
            addCriterion("tenant_code <=", value, "tenantCode");
            return (Criteria) this;
        }

        public Criteria andTenantCodeLike(String value) {
            addCriterion("tenant_code like", value, "tenantCode");
            return (Criteria) this;
        }

        public Criteria andTenantCodeNotLike(String value) {
            addCriterion("tenant_code not like", value, "tenantCode");
            return (Criteria) this;
        }

        public Criteria andTenantCodeIn(List<String> values) {
            addCriterion("tenant_code in", values, "tenantCode");
            return (Criteria) this;
        }

        public Criteria andTenantCodeNotIn(List<String> values) {
            addCriterion("tenant_code not in", values, "tenantCode");
            return (Criteria) this;
        }

        public Criteria andTenantCodeBetween(String value1, String value2) {
            addCriterion("tenant_code between", value1, value2, "tenantCode");
            return (Criteria) this;
        }

        public Criteria andTenantCodeNotBetween(String value1, String value2) {
            addCriterion("tenant_code not between", value1, value2, "tenantCode");
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

        public Criteria andHasReceiveFreightIsNull() {
            addCriterion("has_receive_freight is null");
            return (Criteria) this;
        }

        public Criteria andHasReceiveFreightIsNotNull() {
            addCriterion("has_receive_freight is not null");
            return (Criteria) this;
        }

        public Criteria andHasReceiveFreightEqualTo(BigDecimal value) {
            addCriterion("has_receive_freight =", value, "hasReceiveFreight");
            return (Criteria) this;
        }

        public Criteria andHasReceiveFreightNotEqualTo(BigDecimal value) {
            addCriterion("has_receive_freight <>", value, "hasReceiveFreight");
            return (Criteria) this;
        }

        public Criteria andHasReceiveFreightGreaterThan(BigDecimal value) {
            addCriterion("has_receive_freight >", value, "hasReceiveFreight");
            return (Criteria) this;
        }

        public Criteria andHasReceiveFreightGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("has_receive_freight >=", value, "hasReceiveFreight");
            return (Criteria) this;
        }

        public Criteria andHasReceiveFreightLessThan(BigDecimal value) {
            addCriterion("has_receive_freight <", value, "hasReceiveFreight");
            return (Criteria) this;
        }

        public Criteria andHasReceiveFreightLessThanOrEqualTo(BigDecimal value) {
            addCriterion("has_receive_freight <=", value, "hasReceiveFreight");
            return (Criteria) this;
        }

        public Criteria andHasReceiveFreightIn(List<BigDecimal> values) {
            addCriterion("has_receive_freight in", values, "hasReceiveFreight");
            return (Criteria) this;
        }

        public Criteria andHasReceiveFreightNotIn(List<BigDecimal> values) {
            addCriterion("has_receive_freight not in", values, "hasReceiveFreight");
            return (Criteria) this;
        }

        public Criteria andHasReceiveFreightBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("has_receive_freight between", value1, value2, "hasReceiveFreight");
            return (Criteria) this;
        }

        public Criteria andHasReceiveFreightNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("has_receive_freight not between", value1, value2, "hasReceiveFreight");
            return (Criteria) this;
        }

        public Criteria andReceiveStatusIsNull() {
            addCriterion("receive_status is null");
            return (Criteria) this;
        }

        public Criteria andReceiveStatusIsNotNull() {
            addCriterion("receive_status is not null");
            return (Criteria) this;
        }

        public Criteria andReceiveStatusEqualTo(Byte value) {
            addCriterion("receive_status =", value, "receiveStatus");
            return (Criteria) this;
        }

        public Criteria andReceiveStatusNotEqualTo(Byte value) {
            addCriterion("receive_status <>", value, "receiveStatus");
            return (Criteria) this;
        }

        public Criteria andReceiveStatusGreaterThan(Byte value) {
            addCriterion("receive_status >", value, "receiveStatus");
            return (Criteria) this;
        }

        public Criteria andReceiveStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("receive_status >=", value, "receiveStatus");
            return (Criteria) this;
        }

        public Criteria andReceiveStatusLessThan(Byte value) {
            addCriterion("receive_status <", value, "receiveStatus");
            return (Criteria) this;
        }

        public Criteria andReceiveStatusLessThanOrEqualTo(Byte value) {
            addCriterion("receive_status <=", value, "receiveStatus");
            return (Criteria) this;
        }

        public Criteria andReceiveStatusIn(List<Byte> values) {
            addCriterion("receive_status in", values, "receiveStatus");
            return (Criteria) this;
        }

        public Criteria andReceiveStatusNotIn(List<Byte> values) {
            addCriterion("receive_status not in", values, "receiveStatus");
            return (Criteria) this;
        }

        public Criteria andReceiveStatusBetween(Byte value1, Byte value2) {
            addCriterion("receive_status between", value1, value2, "receiveStatus");
            return (Criteria) this;
        }

        public Criteria andReceiveStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("receive_status not between", value1, value2, "receiveStatus");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoIsNull() {
            addCriterion("invoice_no is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoIsNotNull() {
            addCriterion("invoice_no is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoEqualTo(String value) {
            addCriterion("invoice_no =", value, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoNotEqualTo(String value) {
            addCriterion("invoice_no <>", value, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoGreaterThan(String value) {
            addCriterion("invoice_no >", value, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoGreaterThanOrEqualTo(String value) {
            addCriterion("invoice_no >=", value, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoLessThan(String value) {
            addCriterion("invoice_no <", value, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoLessThanOrEqualTo(String value) {
            addCriterion("invoice_no <=", value, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoLike(String value) {
            addCriterion("invoice_no like", value, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoNotLike(String value) {
            addCriterion("invoice_no not like", value, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoIn(List<String> values) {
            addCriterion("invoice_no in", values, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoNotIn(List<String> values) {
            addCriterion("invoice_no not in", values, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoBetween(String value1, String value2) {
            addCriterion("invoice_no between", value1, value2, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoNotBetween(String value1, String value2) {
            addCriterion("invoice_no not between", value1, value2, "invoiceNo");
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

        public Criteria andCustomerInitialBeforeTaxIsNull() {
            addCriterion("customer_initial_before_tax is null");
            return (Criteria) this;
        }

        public Criteria andCustomerInitialBeforeTaxIsNotNull() {
            addCriterion("customer_initial_before_tax is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerInitialBeforeTaxEqualTo(BigDecimal value) {
            addCriterion("customer_initial_before_tax =", value, "customerInitialBeforeTax");
            return (Criteria) this;
        }

        public Criteria andCustomerInitialBeforeTaxNotEqualTo(BigDecimal value) {
            addCriterion("customer_initial_before_tax <>", value, "customerInitialBeforeTax");
            return (Criteria) this;
        }

        public Criteria andCustomerInitialBeforeTaxGreaterThan(BigDecimal value) {
            addCriterion("customer_initial_before_tax >", value, "customerInitialBeforeTax");
            return (Criteria) this;
        }

        public Criteria andCustomerInitialBeforeTaxGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("customer_initial_before_tax >=", value, "customerInitialBeforeTax");
            return (Criteria) this;
        }

        public Criteria andCustomerInitialBeforeTaxLessThan(BigDecimal value) {
            addCriterion("customer_initial_before_tax <", value, "customerInitialBeforeTax");
            return (Criteria) this;
        }

        public Criteria andCustomerInitialBeforeTaxLessThanOrEqualTo(BigDecimal value) {
            addCriterion("customer_initial_before_tax <=", value, "customerInitialBeforeTax");
            return (Criteria) this;
        }

        public Criteria andCustomerInitialBeforeTaxIn(List<BigDecimal> values) {
            addCriterion("customer_initial_before_tax in", values, "customerInitialBeforeTax");
            return (Criteria) this;
        }

        public Criteria andCustomerInitialBeforeTaxNotIn(List<BigDecimal> values) {
            addCriterion("customer_initial_before_tax not in", values, "customerInitialBeforeTax");
            return (Criteria) this;
        }

        public Criteria andCustomerInitialBeforeTaxBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("customer_initial_before_tax between", value1, value2, "customerInitialBeforeTax");
            return (Criteria) this;
        }

        public Criteria andCustomerInitialBeforeTaxNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("customer_initial_before_tax not between", value1, value2, "customerInitialBeforeTax");
            return (Criteria) this;
        }

        public Criteria andCustomerInitialAfterTaxIsNull() {
            addCriterion("customer_initial_after_tax is null");
            return (Criteria) this;
        }

        public Criteria andCustomerInitialAfterTaxIsNotNull() {
            addCriterion("customer_initial_after_tax is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerInitialAfterTaxEqualTo(BigDecimal value) {
            addCriterion("customer_initial_after_tax =", value, "customerInitialAfterTax");
            return (Criteria) this;
        }

        public Criteria andCustomerInitialAfterTaxNotEqualTo(BigDecimal value) {
            addCriterion("customer_initial_after_tax <>", value, "customerInitialAfterTax");
            return (Criteria) this;
        }

        public Criteria andCustomerInitialAfterTaxGreaterThan(BigDecimal value) {
            addCriterion("customer_initial_after_tax >", value, "customerInitialAfterTax");
            return (Criteria) this;
        }

        public Criteria andCustomerInitialAfterTaxGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("customer_initial_after_tax >=", value, "customerInitialAfterTax");
            return (Criteria) this;
        }

        public Criteria andCustomerInitialAfterTaxLessThan(BigDecimal value) {
            addCriterion("customer_initial_after_tax <", value, "customerInitialAfterTax");
            return (Criteria) this;
        }

        public Criteria andCustomerInitialAfterTaxLessThanOrEqualTo(BigDecimal value) {
            addCriterion("customer_initial_after_tax <=", value, "customerInitialAfterTax");
            return (Criteria) this;
        }

        public Criteria andCustomerInitialAfterTaxIn(List<BigDecimal> values) {
            addCriterion("customer_initial_after_tax in", values, "customerInitialAfterTax");
            return (Criteria) this;
        }

        public Criteria andCustomerInitialAfterTaxNotIn(List<BigDecimal> values) {
            addCriterion("customer_initial_after_tax not in", values, "customerInitialAfterTax");
            return (Criteria) this;
        }

        public Criteria andCustomerInitialAfterTaxBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("customer_initial_after_tax between", value1, value2, "customerInitialAfterTax");
            return (Criteria) this;
        }

        public Criteria andCustomerInitialAfterTaxNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("customer_initial_after_tax not between", value1, value2, "customerInitialAfterTax");
            return (Criteria) this;
        }

        public Criteria andCustomerFinalBeforeTaxIsNull() {
            addCriterion("customer_final_before_tax is null");
            return (Criteria) this;
        }

        public Criteria andCustomerFinalBeforeTaxIsNotNull() {
            addCriterion("customer_final_before_tax is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerFinalBeforeTaxEqualTo(BigDecimal value) {
            addCriterion("customer_final_before_tax =", value, "customerFinalBeforeTax");
            return (Criteria) this;
        }

        public Criteria andCustomerFinalBeforeTaxNotEqualTo(BigDecimal value) {
            addCriterion("customer_final_before_tax <>", value, "customerFinalBeforeTax");
            return (Criteria) this;
        }

        public Criteria andCustomerFinalBeforeTaxGreaterThan(BigDecimal value) {
            addCriterion("customer_final_before_tax >", value, "customerFinalBeforeTax");
            return (Criteria) this;
        }

        public Criteria andCustomerFinalBeforeTaxGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("customer_final_before_tax >=", value, "customerFinalBeforeTax");
            return (Criteria) this;
        }

        public Criteria andCustomerFinalBeforeTaxLessThan(BigDecimal value) {
            addCriterion("customer_final_before_tax <", value, "customerFinalBeforeTax");
            return (Criteria) this;
        }

        public Criteria andCustomerFinalBeforeTaxLessThanOrEqualTo(BigDecimal value) {
            addCriterion("customer_final_before_tax <=", value, "customerFinalBeforeTax");
            return (Criteria) this;
        }

        public Criteria andCustomerFinalBeforeTaxIn(List<BigDecimal> values) {
            addCriterion("customer_final_before_tax in", values, "customerFinalBeforeTax");
            return (Criteria) this;
        }

        public Criteria andCustomerFinalBeforeTaxNotIn(List<BigDecimal> values) {
            addCriterion("customer_final_before_tax not in", values, "customerFinalBeforeTax");
            return (Criteria) this;
        }

        public Criteria andCustomerFinalBeforeTaxBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("customer_final_before_tax between", value1, value2, "customerFinalBeforeTax");
            return (Criteria) this;
        }

        public Criteria andCustomerFinalBeforeTaxNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("customer_final_before_tax not between", value1, value2, "customerFinalBeforeTax");
            return (Criteria) this;
        }

        public Criteria andCustomerFinalAfterTaxIsNull() {
            addCriterion("customer_final_after_tax is null");
            return (Criteria) this;
        }

        public Criteria andCustomerFinalAfterTaxIsNotNull() {
            addCriterion("customer_final_after_tax is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerFinalAfterTaxEqualTo(BigDecimal value) {
            addCriterion("customer_final_after_tax =", value, "customerFinalAfterTax");
            return (Criteria) this;
        }

        public Criteria andCustomerFinalAfterTaxNotEqualTo(BigDecimal value) {
            addCriterion("customer_final_after_tax <>", value, "customerFinalAfterTax");
            return (Criteria) this;
        }

        public Criteria andCustomerFinalAfterTaxGreaterThan(BigDecimal value) {
            addCriterion("customer_final_after_tax >", value, "customerFinalAfterTax");
            return (Criteria) this;
        }

        public Criteria andCustomerFinalAfterTaxGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("customer_final_after_tax >=", value, "customerFinalAfterTax");
            return (Criteria) this;
        }

        public Criteria andCustomerFinalAfterTaxLessThan(BigDecimal value) {
            addCriterion("customer_final_after_tax <", value, "customerFinalAfterTax");
            return (Criteria) this;
        }

        public Criteria andCustomerFinalAfterTaxLessThanOrEqualTo(BigDecimal value) {
            addCriterion("customer_final_after_tax <=", value, "customerFinalAfterTax");
            return (Criteria) this;
        }

        public Criteria andCustomerFinalAfterTaxIn(List<BigDecimal> values) {
            addCriterion("customer_final_after_tax in", values, "customerFinalAfterTax");
            return (Criteria) this;
        }

        public Criteria andCustomerFinalAfterTaxNotIn(List<BigDecimal> values) {
            addCriterion("customer_final_after_tax not in", values, "customerFinalAfterTax");
            return (Criteria) this;
        }

        public Criteria andCustomerFinalAfterTaxBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("customer_final_after_tax between", value1, value2, "customerFinalAfterTax");
            return (Criteria) this;
        }

        public Criteria andCustomerFinalAfterTaxNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("customer_final_after_tax not between", value1, value2, "customerFinalAfterTax");
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

        public Criteria andAreaCodeOrLikeIn(List<String> values) {
            addCriterion("area_code like", values, "areaCode");
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

        private boolean orLikeListValue;

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

        public boolean isOrLikeListValue() {
            return orLikeListValue;
        }
    }
}