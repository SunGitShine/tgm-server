package com.juma.tgm.vendor.domain;

import java.util.ArrayList;
import java.util.List;

public class VendorProjectMappingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int startOffSet = -1;

    protected int size = -1;

    public VendorProjectMappingExample() {
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

        public Criteria andVendorProjectMappingIdIsNull() {
            addCriterion("vendor_project_mapping_id is null");
            return (Criteria) this;
        }

        public Criteria andVendorProjectMappingIdIsNotNull() {
            addCriterion("vendor_project_mapping_id is not null");
            return (Criteria) this;
        }

        public Criteria andVendorProjectMappingIdEqualTo(Integer value) {
            addCriterion("vendor_project_mapping_id =", value, "vendorProjectMappingId");
            return (Criteria) this;
        }

        public Criteria andVendorProjectMappingIdNotEqualTo(Integer value) {
            addCriterion("vendor_project_mapping_id <>", value, "vendorProjectMappingId");
            return (Criteria) this;
        }

        public Criteria andVendorProjectMappingIdGreaterThan(Integer value) {
            addCriterion("vendor_project_mapping_id >", value, "vendorProjectMappingId");
            return (Criteria) this;
        }

        public Criteria andVendorProjectMappingIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("vendor_project_mapping_id >=", value, "vendorProjectMappingId");
            return (Criteria) this;
        }

        public Criteria andVendorProjectMappingIdLessThan(Integer value) {
            addCriterion("vendor_project_mapping_id <", value, "vendorProjectMappingId");
            return (Criteria) this;
        }

        public Criteria andVendorProjectMappingIdLessThanOrEqualTo(Integer value) {
            addCriterion("vendor_project_mapping_id <=", value, "vendorProjectMappingId");
            return (Criteria) this;
        }

        public Criteria andVendorProjectMappingIdIn(List<Integer> values) {
            addCriterion("vendor_project_mapping_id in", values, "vendorProjectMappingId");
            return (Criteria) this;
        }

        public Criteria andVendorProjectMappingIdNotIn(List<Integer> values) {
            addCriterion("vendor_project_mapping_id not in", values, "vendorProjectMappingId");
            return (Criteria) this;
        }

        public Criteria andVendorProjectMappingIdBetween(Integer value1, Integer value2) {
            addCriterion("vendor_project_mapping_id between", value1, value2, "vendorProjectMappingId");
            return (Criteria) this;
        }

        public Criteria andVendorProjectMappingIdNotBetween(Integer value1, Integer value2) {
            addCriterion("vendor_project_mapping_id not between", value1, value2, "vendorProjectMappingId");
            return (Criteria) this;
        }

        public Criteria andVendorMappingIdIsNull() {
            addCriterion("vendor_mapping_id is null");
            return (Criteria) this;
        }

        public Criteria andVendorMappingIdIsNotNull() {
            addCriterion("vendor_mapping_id is not null");
            return (Criteria) this;
        }

        public Criteria andVendorMappingIdEqualTo(Integer value) {
            addCriterion("vendor_mapping_id =", value, "vendorMappingId");
            return (Criteria) this;
        }

        public Criteria andVendorMappingIdNotEqualTo(Integer value) {
            addCriterion("vendor_mapping_id <>", value, "vendorMappingId");
            return (Criteria) this;
        }

        public Criteria andVendorMappingIdGreaterThan(Integer value) {
            addCriterion("vendor_mapping_id >", value, "vendorMappingId");
            return (Criteria) this;
        }

        public Criteria andVendorMappingIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("vendor_mapping_id >=", value, "vendorMappingId");
            return (Criteria) this;
        }

        public Criteria andVendorMappingIdLessThan(Integer value) {
            addCriterion("vendor_mapping_id <", value, "vendorMappingId");
            return (Criteria) this;
        }

        public Criteria andVendorMappingIdLessThanOrEqualTo(Integer value) {
            addCriterion("vendor_mapping_id <=", value, "vendorMappingId");
            return (Criteria) this;
        }

        public Criteria andVendorMappingIdIn(List<Integer> values) {
            addCriterion("vendor_mapping_id in", values, "vendorMappingId");
            return (Criteria) this;
        }

        public Criteria andVendorMappingIdNotIn(List<Integer> values) {
            addCriterion("vendor_mapping_id not in", values, "vendorMappingId");
            return (Criteria) this;
        }

        public Criteria andVendorMappingIdBetween(Integer value1, Integer value2) {
            addCriterion("vendor_mapping_id between", value1, value2, "vendorMappingId");
            return (Criteria) this;
        }

        public Criteria andVendorMappingIdNotBetween(Integer value1, Integer value2) {
            addCriterion("vendor_mapping_id not between", value1, value2, "vendorMappingId");
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

        public Criteria andVendorProjectIdIsNull() {
            addCriterion("vendor_project_id is null");
            return (Criteria) this;
        }

        public Criteria andVendorProjectIdIsNotNull() {
            addCriterion("vendor_project_id is not null");
            return (Criteria) this;
        }

        public Criteria andVendorProjectIdEqualTo(Integer value) {
            addCriterion("vendor_project_id =", value, "vendorProjectId");
            return (Criteria) this;
        }

        public Criteria andVendorProjectIdNotEqualTo(Integer value) {
            addCriterion("vendor_project_id <>", value, "vendorProjectId");
            return (Criteria) this;
        }

        public Criteria andVendorProjectIdGreaterThan(Integer value) {
            addCriterion("vendor_project_id >", value, "vendorProjectId");
            return (Criteria) this;
        }

        public Criteria andVendorProjectIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("vendor_project_id >=", value, "vendorProjectId");
            return (Criteria) this;
        }

        public Criteria andVendorProjectIdLessThan(Integer value) {
            addCriterion("vendor_project_id <", value, "vendorProjectId");
            return (Criteria) this;
        }

        public Criteria andVendorProjectIdLessThanOrEqualTo(Integer value) {
            addCriterion("vendor_project_id <=", value, "vendorProjectId");
            return (Criteria) this;
        }

        public Criteria andVendorProjectIdIn(List<Integer> values) {
            addCriterion("vendor_project_id in", values, "vendorProjectId");
            return (Criteria) this;
        }

        public Criteria andVendorProjectIdNotIn(List<Integer> values) {
            addCriterion("vendor_project_id not in", values, "vendorProjectId");
            return (Criteria) this;
        }

        public Criteria andVendorProjectIdBetween(Integer value1, Integer value2) {
            addCriterion("vendor_project_id between", value1, value2, "vendorProjectId");
            return (Criteria) this;
        }

        public Criteria andVendorProjectIdNotBetween(Integer value1, Integer value2) {
            addCriterion("vendor_project_id not between", value1, value2, "vendorProjectId");
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