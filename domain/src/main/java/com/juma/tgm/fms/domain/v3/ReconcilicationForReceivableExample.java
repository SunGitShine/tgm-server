package com.juma.tgm.fms.domain.v3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReconcilicationForReceivableExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int startOffSet = -1;

    protected int size = -1;

    public ReconcilicationForReceivableExample() {
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

        public Criteria andReconcilicationNoIsNull() {
            addCriterion("reconcilication_no is null");
            return (Criteria) this;
        }

        public Criteria andReconcilicationNoIsNotNull() {
            addCriterion("reconcilication_no is not null");
            return (Criteria) this;
        }

        public Criteria andReconcilicationNoEqualTo(String value) {
            addCriterion("reconcilication_no =", value, "reconcilicationNo");
            return (Criteria) this;
        }

        public Criteria andReconcilicationNoNotEqualTo(String value) {
            addCriterion("reconcilication_no <>", value, "reconcilicationNo");
            return (Criteria) this;
        }

        public Criteria andReconcilicationNoGreaterThan(String value) {
            addCriterion("reconcilication_no >", value, "reconcilicationNo");
            return (Criteria) this;
        }

        public Criteria andReconcilicationNoGreaterThanOrEqualTo(String value) {
            addCriterion("reconcilication_no >=", value, "reconcilicationNo");
            return (Criteria) this;
        }

        public Criteria andReconcilicationNoLessThan(String value) {
            addCriterion("reconcilication_no <", value, "reconcilicationNo");
            return (Criteria) this;
        }

        public Criteria andReconcilicationNoLessThanOrEqualTo(String value) {
            addCriterion("reconcilication_no <=", value, "reconcilicationNo");
            return (Criteria) this;
        }

        public Criteria andReconcilicationNoLike(String value) {
            addCriterion("reconcilication_no like", value, "reconcilicationNo");
            return (Criteria) this;
        }

        public Criteria andReconcilicationNoNotLike(String value) {
            addCriterion("reconcilication_no not like", value, "reconcilicationNo");
            return (Criteria) this;
        }

        public Criteria andReconcilicationNoIn(List<String> values) {
            addCriterion("reconcilication_no in", values, "reconcilicationNo");
            return (Criteria) this;
        }

        public Criteria andReconcilicationNoNotIn(List<String> values) {
            addCriterion("reconcilication_no not in", values, "reconcilicationNo");
            return (Criteria) this;
        }

        public Criteria andReconcilicationNoBetween(String value1, String value2) {
            addCriterion("reconcilication_no between", value1, value2, "reconcilicationNo");
            return (Criteria) this;
        }

        public Criteria andReconcilicationNoNotBetween(String value1, String value2) {
            addCriterion("reconcilication_no not between", value1, value2, "reconcilicationNo");
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

        public Criteria andSubmitUserIdIsNull() {
            addCriterion("submit_user_id is null");
            return (Criteria) this;
        }

        public Criteria andSubmitUserIdIsNotNull() {
            addCriterion("submit_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andSubmitUserIdEqualTo(Integer value) {
            addCriterion("submit_user_id =", value, "submitUserId");
            return (Criteria) this;
        }

        public Criteria andSubmitUserIdNotEqualTo(Integer value) {
            addCriterion("submit_user_id <>", value, "submitUserId");
            return (Criteria) this;
        }

        public Criteria andSubmitUserIdGreaterThan(Integer value) {
            addCriterion("submit_user_id >", value, "submitUserId");
            return (Criteria) this;
        }

        public Criteria andSubmitUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("submit_user_id >=", value, "submitUserId");
            return (Criteria) this;
        }

        public Criteria andSubmitUserIdLessThan(Integer value) {
            addCriterion("submit_user_id <", value, "submitUserId");
            return (Criteria) this;
        }

        public Criteria andSubmitUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("submit_user_id <=", value, "submitUserId");
            return (Criteria) this;
        }

        public Criteria andSubmitUserIdIn(List<Integer> values) {
            addCriterion("submit_user_id in", values, "submitUserId");
            return (Criteria) this;
        }

        public Criteria andSubmitUserIdNotIn(List<Integer> values) {
            addCriterion("submit_user_id not in", values, "submitUserId");
            return (Criteria) this;
        }

        public Criteria andSubmitUserIdBetween(Integer value1, Integer value2) {
            addCriterion("submit_user_id between", value1, value2, "submitUserId");
            return (Criteria) this;
        }

        public Criteria andSubmitUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("submit_user_id not between", value1, value2, "submitUserId");
            return (Criteria) this;
        }

        public Criteria andApprovalStatusIsNull() {
            addCriterion("approval_status is null");
            return (Criteria) this;
        }

        public Criteria andApprovalStatusIsNotNull() {
            addCriterion("approval_status is not null");
            return (Criteria) this;
        }

        public Criteria andApprovalStatusEqualTo(Integer value) {
            addCriterion("approval_status =", value, "approvalStatus");
            return (Criteria) this;
        }

        public Criteria andApprovalStatusNotEqualTo(Integer value) {
            addCriterion("approval_status <>", value, "approvalStatus");
            return (Criteria) this;
        }

        public Criteria andApprovalStatusGreaterThan(Integer value) {
            addCriterion("approval_status >", value, "approvalStatus");
            return (Criteria) this;
        }

        public Criteria andApprovalStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("approval_status >=", value, "approvalStatus");
            return (Criteria) this;
        }

        public Criteria andApprovalStatusLessThan(Integer value) {
            addCriterion("approval_status <", value, "approvalStatus");
            return (Criteria) this;
        }

        public Criteria andApprovalStatusLessThanOrEqualTo(Integer value) {
            addCriterion("approval_status <=", value, "approvalStatus");
            return (Criteria) this;
        }

        public Criteria andApprovalStatusIn(List<Integer> values) {
            addCriterion("approval_status in", values, "approvalStatus");
            return (Criteria) this;
        }

        public Criteria andApprovalStatusNotIn(List<Integer> values) {
            addCriterion("approval_status not in", values, "approvalStatus");
            return (Criteria) this;
        }

        public Criteria andApprovalStatusBetween(Integer value1, Integer value2) {
            addCriterion("approval_status between", value1, value2, "approvalStatus");
            return (Criteria) this;
        }

        public Criteria andApprovalStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("approval_status not between", value1, value2, "approvalStatus");
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

        public Criteria andReceiveStatusEqualTo(Integer value) {
            addCriterion("receive_status =", value, "receiveStatus");
            return (Criteria) this;
        }

        public Criteria andReceiveStatusNotEqualTo(Integer value) {
            addCriterion("receive_status <>", value, "receiveStatus");
            return (Criteria) this;
        }

        public Criteria andReceiveStatusGreaterThan(Integer value) {
            addCriterion("receive_status >", value, "receiveStatus");
            return (Criteria) this;
        }

        public Criteria andReceiveStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("receive_status >=", value, "receiveStatus");
            return (Criteria) this;
        }

        public Criteria andReceiveStatusLessThan(Integer value) {
            addCriterion("receive_status <", value, "receiveStatus");
            return (Criteria) this;
        }

        public Criteria andReceiveStatusLessThanOrEqualTo(Integer value) {
            addCriterion("receive_status <=", value, "receiveStatus");
            return (Criteria) this;
        }

        public Criteria andReceiveStatusIn(List<Integer> values) {
            addCriterion("receive_status in", values, "receiveStatus");
            return (Criteria) this;
        }

        public Criteria andReceiveStatusNotIn(List<Integer> values) {
            addCriterion("receive_status not in", values, "receiveStatus");
            return (Criteria) this;
        }

        public Criteria andReceiveStatusBetween(Integer value1, Integer value2) {
            addCriterion("receive_status between", value1, value2, "receiveStatus");
            return (Criteria) this;
        }

        public Criteria andReceiveStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("receive_status not between", value1, value2, "receiveStatus");
            return (Criteria) this;
        }

        public Criteria andInvoiceStatusIsNull() {
            addCriterion("invoice_status is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceStatusIsNotNull() {
            addCriterion("invoice_status is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceStatusEqualTo(Integer value) {
            addCriterion("invoice_status =", value, "invoiceStatus");
            return (Criteria) this;
        }

        public Criteria andInvoiceStatusNotEqualTo(Integer value) {
            addCriterion("invoice_status <>", value, "invoiceStatus");
            return (Criteria) this;
        }

        public Criteria andInvoiceStatusGreaterThan(Integer value) {
            addCriterion("invoice_status >", value, "invoiceStatus");
            return (Criteria) this;
        }

        public Criteria andInvoiceStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("invoice_status >=", value, "invoiceStatus");
            return (Criteria) this;
        }

        public Criteria andInvoiceStatusLessThan(Integer value) {
            addCriterion("invoice_status <", value, "invoiceStatus");
            return (Criteria) this;
        }

        public Criteria andInvoiceStatusLessThanOrEqualTo(Integer value) {
            addCriterion("invoice_status <=", value, "invoiceStatus");
            return (Criteria) this;
        }

        public Criteria andInvoiceStatusIn(List<Integer> values) {
            addCriterion("invoice_status in", values, "invoiceStatus");
            return (Criteria) this;
        }

        public Criteria andInvoiceStatusNotIn(List<Integer> values) {
            addCriterion("invoice_status not in", values, "invoiceStatus");
            return (Criteria) this;
        }

        public Criteria andInvoiceStatusBetween(Integer value1, Integer value2) {
            addCriterion("invoice_status between", value1, value2, "invoiceStatus");
            return (Criteria) this;
        }

        public Criteria andInvoiceStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("invoice_status not between", value1, value2, "invoiceStatus");
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