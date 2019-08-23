package com.juma.tgm.fms.domain.v3;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReconcilicationForSubPayableExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int startOffSet = -1;

    protected int size = -1;

    public ReconcilicationForSubPayableExample() {
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

        public Criteria andSubReconcilicationIdIsNull() {
            addCriterion("sub_reconcilication_id is null");
            return (Criteria) this;
        }

        public Criteria andSubReconcilicationIdIsNotNull() {
            addCriterion("sub_reconcilication_id is not null");
            return (Criteria) this;
        }

        public Criteria andSubReconcilicationIdEqualTo(Integer value) {
            addCriterion("sub_reconcilication_id =", value, "subReconcilicationId");
            return (Criteria) this;
        }

        public Criteria andSubReconcilicationIdNotEqualTo(Integer value) {
            addCriterion("sub_reconcilication_id <>", value, "subReconcilicationId");
            return (Criteria) this;
        }

        public Criteria andSubReconcilicationIdGreaterThan(Integer value) {
            addCriterion("sub_reconcilication_id >", value, "subReconcilicationId");
            return (Criteria) this;
        }

        public Criteria andSubReconcilicationIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("sub_reconcilication_id >=", value, "subReconcilicationId");
            return (Criteria) this;
        }

        public Criteria andSubReconcilicationIdLessThan(Integer value) {
            addCriterion("sub_reconcilication_id <", value, "subReconcilicationId");
            return (Criteria) this;
        }

        public Criteria andSubReconcilicationIdLessThanOrEqualTo(Integer value) {
            addCriterion("sub_reconcilication_id <=", value, "subReconcilicationId");
            return (Criteria) this;
        }

        public Criteria andSubReconcilicationIdIn(List<Integer> values) {
            addCriterion("sub_reconcilication_id in", values, "subReconcilicationId");
            return (Criteria) this;
        }

        public Criteria andSubReconcilicationIdNotIn(List<Integer> values) {
            addCriterion("sub_reconcilication_id not in", values, "subReconcilicationId");
            return (Criteria) this;
        }

        public Criteria andSubReconcilicationIdBetween(Integer value1, Integer value2) {
            addCriterion("sub_reconcilication_id between", value1, value2, "subReconcilicationId");
            return (Criteria) this;
        }

        public Criteria andSubReconcilicationIdNotBetween(Integer value1, Integer value2) {
            addCriterion("sub_reconcilication_id not between", value1, value2, "subReconcilicationId");
            return (Criteria) this;
        }

        public Criteria andSubReconcilicationNoIsNull() {
            addCriterion("sub_reconcilication_no is null");
            return (Criteria) this;
        }

        public Criteria andSubReconcilicationNoIsNotNull() {
            addCriterion("sub_reconcilication_no is not null");
            return (Criteria) this;
        }

        public Criteria andSubReconcilicationNoEqualTo(String value) {
            addCriterion("sub_reconcilication_no =", value, "subReconcilicationNo");
            return (Criteria) this;
        }

        public Criteria andSubReconcilicationNoNotEqualTo(String value) {
            addCriterion("sub_reconcilication_no <>", value, "subReconcilicationNo");
            return (Criteria) this;
        }

        public Criteria andSubReconcilicationNoGreaterThan(String value) {
            addCriterion("sub_reconcilication_no >", value, "subReconcilicationNo");
            return (Criteria) this;
        }

        public Criteria andSubReconcilicationNoGreaterThanOrEqualTo(String value) {
            addCriterion("sub_reconcilication_no >=", value, "subReconcilicationNo");
            return (Criteria) this;
        }

        public Criteria andSubReconcilicationNoLessThan(String value) {
            addCriterion("sub_reconcilication_no <", value, "subReconcilicationNo");
            return (Criteria) this;
        }

        public Criteria andSubReconcilicationNoLessThanOrEqualTo(String value) {
            addCriterion("sub_reconcilication_no <=", value, "subReconcilicationNo");
            return (Criteria) this;
        }

        public Criteria andSubReconcilicationNoLike(String value) {
            addCriterion("sub_reconcilication_no like", value, "subReconcilicationNo");
            return (Criteria) this;
        }

        public Criteria andSubReconcilicationNoNotLike(String value) {
            addCriterion("sub_reconcilication_no not like", value, "subReconcilicationNo");
            return (Criteria) this;
        }

        public Criteria andSubReconcilicationNoIn(List<String> values) {
            addCriterion("sub_reconcilication_no in", values, "subReconcilicationNo");
            return (Criteria) this;
        }

        public Criteria andSubReconcilicationNoNotIn(List<String> values) {
            addCriterion("sub_reconcilication_no not in", values, "subReconcilicationNo");
            return (Criteria) this;
        }

        public Criteria andSubReconcilicationNoBetween(String value1, String value2) {
            addCriterion("sub_reconcilication_no between", value1, value2, "subReconcilicationNo");
            return (Criteria) this;
        }

        public Criteria andSubReconcilicationNoNotBetween(String value1, String value2) {
            addCriterion("sub_reconcilication_no not between", value1, value2, "subReconcilicationNo");
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

        public Criteria andSettleStatusIsNull() {
            addCriterion("settle_status is null");
            return (Criteria) this;
        }

        public Criteria andSettleStatusIsNotNull() {
            addCriterion("settle_status is not null");
            return (Criteria) this;
        }

        public Criteria andSettleStatusEqualTo(Byte value) {
            addCriterion("settle_status =", value, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusNotEqualTo(Byte value) {
            addCriterion("settle_status <>", value, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusGreaterThan(Byte value) {
            addCriterion("settle_status >", value, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("settle_status >=", value, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusLessThan(Byte value) {
            addCriterion("settle_status <", value, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusLessThanOrEqualTo(Byte value) {
            addCriterion("settle_status <=", value, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusIn(List<Byte> values) {
            addCriterion("settle_status in", values, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusNotIn(List<Byte> values) {
            addCriterion("settle_status not in", values, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusBetween(Byte value1, Byte value2) {
            addCriterion("settle_status between", value1, value2, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("settle_status not between", value1, value2, "settleStatus");
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