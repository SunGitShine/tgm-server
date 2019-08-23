package com.juma.tgm.fms.domain.v3;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReconciliationUpdateVendorMqExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int startOffSet = -1;

    protected int size = -1;

    public ReconciliationUpdateVendorMqExample() {
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

    public ReconciliationUpdateVendorMqExample orderBy(String ... orderByClauses) {
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

        public Criteria andMqIdIsNull() {
            addCriterion("mq_id is null");
            return (Criteria) this;
        }

        public Criteria andMqIdIsNotNull() {
            addCriterion("mq_id is not null");
            return (Criteria) this;
        }

        public Criteria andMqIdEqualTo(Integer value) {
            addCriterion("mq_id =", value, "mqId");
            return (Criteria) this;
        }

        public Criteria andMqIdNotEqualTo(Integer value) {
            addCriterion("mq_id <>", value, "mqId");
            return (Criteria) this;
        }

        public Criteria andMqIdGreaterThan(Integer value) {
            addCriterion("mq_id >", value, "mqId");
            return (Criteria) this;
        }

        public Criteria andMqIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("mq_id >=", value, "mqId");
            return (Criteria) this;
        }

        public Criteria andMqIdLessThan(Integer value) {
            addCriterion("mq_id <", value, "mqId");
            return (Criteria) this;
        }

        public Criteria andMqIdLessThanOrEqualTo(Integer value) {
            addCriterion("mq_id <=", value, "mqId");
            return (Criteria) this;
        }

        public Criteria andMqIdIn(List<Integer> values) {
            addCriterion("mq_id in", values, "mqId");
            return (Criteria) this;
        }

        public Criteria andMqIdNotIn(List<Integer> values) {
            addCriterion("mq_id not in", values, "mqId");
            return (Criteria) this;
        }

        public Criteria andMqIdBetween(Integer value1, Integer value2) {
            addCriterion("mq_id between", value1, value2, "mqId");
            return (Criteria) this;
        }

        public Criteria andMqIdNotBetween(Integer value1, Integer value2) {
            addCriterion("mq_id not between", value1, value2, "mqId");
            return (Criteria) this;
        }

        public Criteria andOldVendorIdIsNull() {
            addCriterion("old_vendor_id is null");
            return (Criteria) this;
        }

        public Criteria andOldVendorIdIsNotNull() {
            addCriterion("old_vendor_id is not null");
            return (Criteria) this;
        }

        public Criteria andOldVendorIdEqualTo(Integer value) {
            addCriterion("old_vendor_id =", value, "oldVendorId");
            return (Criteria) this;
        }

        public Criteria andOldVendorIdNotEqualTo(Integer value) {
            addCriterion("old_vendor_id <>", value, "oldVendorId");
            return (Criteria) this;
        }

        public Criteria andOldVendorIdGreaterThan(Integer value) {
            addCriterion("old_vendor_id >", value, "oldVendorId");
            return (Criteria) this;
        }

        public Criteria andOldVendorIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("old_vendor_id >=", value, "oldVendorId");
            return (Criteria) this;
        }

        public Criteria andOldVendorIdLessThan(Integer value) {
            addCriterion("old_vendor_id <", value, "oldVendorId");
            return (Criteria) this;
        }

        public Criteria andOldVendorIdLessThanOrEqualTo(Integer value) {
            addCriterion("old_vendor_id <=", value, "oldVendorId");
            return (Criteria) this;
        }

        public Criteria andOldVendorIdIn(List<Integer> values) {
            addCriterion("old_vendor_id in", values, "oldVendorId");
            return (Criteria) this;
        }

        public Criteria andOldVendorIdNotIn(List<Integer> values) {
            addCriterion("old_vendor_id not in", values, "oldVendorId");
            return (Criteria) this;
        }

        public Criteria andOldVendorIdBetween(Integer value1, Integer value2) {
            addCriterion("old_vendor_id between", value1, value2, "oldVendorId");
            return (Criteria) this;
        }

        public Criteria andOldVendorIdNotBetween(Integer value1, Integer value2) {
            addCriterion("old_vendor_id not between", value1, value2, "oldVendorId");
            return (Criteria) this;
        }

        public Criteria andOldVendorNameIsNull() {
            addCriterion("old_vendor_name is null");
            return (Criteria) this;
        }

        public Criteria andOldVendorNameIsNotNull() {
            addCriterion("old_vendor_name is not null");
            return (Criteria) this;
        }

        public Criteria andOldVendorNameEqualTo(String value) {
            addCriterion("old_vendor_name =", value, "oldVendorName");
            return (Criteria) this;
        }

        public Criteria andOldVendorNameNotEqualTo(String value) {
            addCriterion("old_vendor_name <>", value, "oldVendorName");
            return (Criteria) this;
        }

        public Criteria andOldVendorNameGreaterThan(String value) {
            addCriterion("old_vendor_name >", value, "oldVendorName");
            return (Criteria) this;
        }

        public Criteria andOldVendorNameGreaterThanOrEqualTo(String value) {
            addCriterion("old_vendor_name >=", value, "oldVendorName");
            return (Criteria) this;
        }

        public Criteria andOldVendorNameLessThan(String value) {
            addCriterion("old_vendor_name <", value, "oldVendorName");
            return (Criteria) this;
        }

        public Criteria andOldVendorNameLessThanOrEqualTo(String value) {
            addCriterion("old_vendor_name <=", value, "oldVendorName");
            return (Criteria) this;
        }

        public Criteria andOldVendorNameLike(String value) {
            addCriterion("old_vendor_name like", value, "oldVendorName");
            return (Criteria) this;
        }

        public Criteria andOldVendorNameNotLike(String value) {
            addCriterion("old_vendor_name not like", value, "oldVendorName");
            return (Criteria) this;
        }

        public Criteria andOldVendorNameLikeList(List<String> values) {
            addCriterion("old_vendor_name like", values, "oldVendorName");
            return (Criteria) this;
        }

        public Criteria andOldVendorNameIn(List<String> values) {
            addCriterion("old_vendor_name in", values, "oldVendorName");
            return (Criteria) this;
        }

        public Criteria andOldVendorNameNotIn(List<String> values) {
            addCriterion("old_vendor_name not in", values, "oldVendorName");
            return (Criteria) this;
        }

        public Criteria andOldVendorNameBetween(String value1, String value2) {
            addCriterion("old_vendor_name between", value1, value2, "oldVendorName");
            return (Criteria) this;
        }

        public Criteria andOldVendorNameNotBetween(String value1, String value2) {
            addCriterion("old_vendor_name not between", value1, value2, "oldVendorName");
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

        public Criteria andVendorNameLikeList(List<String> values) {
            addCriterion("vendor_name like", values, "vendorName");
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

        public Criteria andExcuteResultIsNull() {
            addCriterion("excute_result is null");
            return (Criteria) this;
        }

        public Criteria andExcuteResultIsNotNull() {
            addCriterion("excute_result is not null");
            return (Criteria) this;
        }

        public Criteria andExcuteResultEqualTo(String value) {
            addCriterion("excute_result =", value, "excuteResult");
            return (Criteria) this;
        }

        public Criteria andExcuteResultNotEqualTo(String value) {
            addCriterion("excute_result <>", value, "excuteResult");
            return (Criteria) this;
        }

        public Criteria andExcuteResultGreaterThan(String value) {
            addCriterion("excute_result >", value, "excuteResult");
            return (Criteria) this;
        }

        public Criteria andExcuteResultGreaterThanOrEqualTo(String value) {
            addCriterion("excute_result >=", value, "excuteResult");
            return (Criteria) this;
        }

        public Criteria andExcuteResultLessThan(String value) {
            addCriterion("excute_result <", value, "excuteResult");
            return (Criteria) this;
        }

        public Criteria andExcuteResultLessThanOrEqualTo(String value) {
            addCriterion("excute_result <=", value, "excuteResult");
            return (Criteria) this;
        }

        public Criteria andExcuteResultLike(String value) {
            addCriterion("excute_result like", value, "excuteResult");
            return (Criteria) this;
        }

        public Criteria andExcuteResultNotLike(String value) {
            addCriterion("excute_result not like", value, "excuteResult");
            return (Criteria) this;
        }

        public Criteria andExcuteResultLikeList(List<String> values) {
            addCriterion("excute_result like", values, "excuteResult");
            return (Criteria) this;
        }

        public Criteria andExcuteResultIn(List<String> values) {
            addCriterion("excute_result in", values, "excuteResult");
            return (Criteria) this;
        }

        public Criteria andExcuteResultNotIn(List<String> values) {
            addCriterion("excute_result not in", values, "excuteResult");
            return (Criteria) this;
        }

        public Criteria andExcuteResultBetween(String value1, String value2) {
            addCriterion("excute_result between", value1, value2, "excuteResult");
            return (Criteria) this;
        }

        public Criteria andExcuteResultNotBetween(String value1, String value2) {
            addCriterion("excute_result not between", value1, value2, "excuteResult");
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
        private ReconciliationUpdateVendorMqExample example;

        protected Criteria(ReconciliationUpdateVendorMqExample example) {
            super();
            this.example = example;
        }

        public ReconciliationUpdateVendorMqExample example() {
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