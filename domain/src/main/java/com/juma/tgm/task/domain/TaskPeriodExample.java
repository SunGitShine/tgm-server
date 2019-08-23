package com.juma.tgm.task.domain;

import java.util.ArrayList;
import java.util.List;

public class TaskPeriodExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int startOffSet = -1;

    protected int size = -1;

    public TaskPeriodExample() {
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

    public TaskPeriodExample orderBy(String ... orderByClauses) {
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

    public TaskPeriodExample limit(int pageNo, int pageSize) {
        this.size = pageSize;
        this.startOffSet = (pageNo - 1) * pageSize;
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

        public Criteria andPeriodIdIsNull() {
            addCriterion("period_id is null");
            return (Criteria) this;
        }

        public Criteria andPeriodIdIsNotNull() {
            addCriterion("period_id is not null");
            return (Criteria) this;
        }

        public Criteria andPeriodIdEqualTo(Integer value) {
            addCriterion("period_id =", value, "periodId");
            return (Criteria) this;
        }

        public Criteria andPeriodIdNotEqualTo(Integer value) {
            addCriterion("period_id <>", value, "periodId");
            return (Criteria) this;
        }

        public Criteria andPeriodIdGreaterThan(Integer value) {
            addCriterion("period_id >", value, "periodId");
            return (Criteria) this;
        }

        public Criteria andPeriodIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("period_id >=", value, "periodId");
            return (Criteria) this;
        }

        public Criteria andPeriodIdLessThan(Integer value) {
            addCriterion("period_id <", value, "periodId");
            return (Criteria) this;
        }

        public Criteria andPeriodIdLessThanOrEqualTo(Integer value) {
            addCriterion("period_id <=", value, "periodId");
            return (Criteria) this;
        }

        public Criteria andPeriodIdIn(List<Integer> values) {
            addCriterion("period_id in", values, "periodId");
            return (Criteria) this;
        }

        public Criteria andPeriodIdNotIn(List<Integer> values) {
            addCriterion("period_id not in", values, "periodId");
            return (Criteria) this;
        }

        public Criteria andPeriodIdBetween(Integer value1, Integer value2) {
            addCriterion("period_id between", value1, value2, "periodId");
            return (Criteria) this;
        }

        public Criteria andPeriodIdNotBetween(Integer value1, Integer value2) {
            addCriterion("period_id not between", value1, value2, "periodId");
            return (Criteria) this;
        }

        public Criteria andTaskIdIsNull() {
            addCriterion("task_id is null");
            return (Criteria) this;
        }

        public Criteria andTaskIdIsNotNull() {
            addCriterion("task_id is not null");
            return (Criteria) this;
        }

        public Criteria andTaskIdEqualTo(Integer value) {
            addCriterion("task_id =", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotEqualTo(Integer value) {
            addCriterion("task_id <>", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThan(Integer value) {
            addCriterion("task_id >", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("task_id >=", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThan(Integer value) {
            addCriterion("task_id <", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThanOrEqualTo(Integer value) {
            addCriterion("task_id <=", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdIn(List<Integer> values) {
            addCriterion("task_id in", values, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotIn(List<Integer> values) {
            addCriterion("task_id not in", values, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdBetween(Integer value1, Integer value2) {
            addCriterion("task_id between", value1, value2, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotBetween(Integer value1, Integer value2) {
            addCriterion("task_id not between", value1, value2, "taskId");
            return (Criteria) this;
        }

        public Criteria andIsStandardTimeIsNull() {
            addCriterion("is_standard_time is null");
            return (Criteria) this;
        }

        public Criteria andIsStandardTimeIsNotNull() {
            addCriterion("is_standard_time is not null");
            return (Criteria) this;
        }

        public Criteria andIsStandardTimeEqualTo(Boolean value) {
            addCriterion("is_standard_time =", value, "isStandardTime");
            return (Criteria) this;
        }

        public Criteria andIsStandardTimeNotEqualTo(Boolean value) {
            addCriterion("is_standard_time <>", value, "isStandardTime");
            return (Criteria) this;
        }

        public Criteria andIsStandardTimeGreaterThan(Boolean value) {
            addCriterion("is_standard_time >", value, "isStandardTime");
            return (Criteria) this;
        }

        public Criteria andIsStandardTimeGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_standard_time >=", value, "isStandardTime");
            return (Criteria) this;
        }

        public Criteria andIsStandardTimeLessThan(Boolean value) {
            addCriterion("is_standard_time <", value, "isStandardTime");
            return (Criteria) this;
        }

        public Criteria andIsStandardTimeLessThanOrEqualTo(Boolean value) {
            addCriterion("is_standard_time <=", value, "isStandardTime");
            return (Criteria) this;
        }

        public Criteria andIsStandardTimeIn(List<Boolean> values) {
            addCriterion("is_standard_time in", values, "isStandardTime");
            return (Criteria) this;
        }

        public Criteria andIsStandardTimeNotIn(List<Boolean> values) {
            addCriterion("is_standard_time not in", values, "isStandardTime");
            return (Criteria) this;
        }

        public Criteria andIsStandardTimeBetween(Boolean value1, Boolean value2) {
            addCriterion("is_standard_time between", value1, value2, "isStandardTime");
            return (Criteria) this;
        }

        public Criteria andIsStandardTimeNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_standard_time not between", value1, value2, "isStandardTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryPeriodIsNull() {
            addCriterion("delivery_period is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryPeriodIsNotNull() {
            addCriterion("delivery_period is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryPeriodEqualTo(String value) {
            addCriterion("delivery_period =", value, "deliveryPeriod");
            return (Criteria) this;
        }

        public Criteria andDeliveryPeriodNotEqualTo(String value) {
            addCriterion("delivery_period <>", value, "deliveryPeriod");
            return (Criteria) this;
        }

        public Criteria andDeliveryPeriodGreaterThan(String value) {
            addCriterion("delivery_period >", value, "deliveryPeriod");
            return (Criteria) this;
        }

        public Criteria andDeliveryPeriodGreaterThanOrEqualTo(String value) {
            addCriterion("delivery_period >=", value, "deliveryPeriod");
            return (Criteria) this;
        }

        public Criteria andDeliveryPeriodLessThan(String value) {
            addCriterion("delivery_period <", value, "deliveryPeriod");
            return (Criteria) this;
        }

        public Criteria andDeliveryPeriodLessThanOrEqualTo(String value) {
            addCriterion("delivery_period <=", value, "deliveryPeriod");
            return (Criteria) this;
        }

        public Criteria andDeliveryPeriodLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("delivery_period like", value, "deliveryPeriod");
            return (Criteria) this;
        }

        public Criteria andDeliveryPeriodNotLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("delivery_period not like", value, "deliveryPeriod");
            return (Criteria) this;
        }

        public Criteria andDeliveryPeriodLikeList(List<String> values) {
            addCriterion("delivery_period like", values, "deliveryPeriod");
            return (Criteria) this;
        }

        public Criteria andDeliveryPeriodIn(List<String> values) {
            addCriterion("delivery_period in", values, "deliveryPeriod");
            return (Criteria) this;
        }

        public Criteria andDeliveryPeriodNotIn(List<String> values) {
            addCriterion("delivery_period not in", values, "deliveryPeriod");
            return (Criteria) this;
        }

        public Criteria andDeliveryPeriodBetween(String value1, String value2) {
            addCriterion("delivery_period between", value1, value2, "deliveryPeriod");
            return (Criteria) this;
        }

        public Criteria andDeliveryPeriodNotBetween(String value1, String value2) {
            addCriterion("delivery_period not between", value1, value2, "deliveryPeriod");
            return (Criteria) this;
        }

        public Criteria andDeliveryPeriodTimeIsNull() {
            addCriterion("delivery_period_time is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryPeriodTimeIsNotNull() {
            addCriterion("delivery_period_time is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryPeriodTimeEqualTo(String value) {
            addCriterion("delivery_period_time =", value, "deliveryPeriodTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryPeriodTimeNotEqualTo(String value) {
            addCriterion("delivery_period_time <>", value, "deliveryPeriodTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryPeriodTimeGreaterThan(String value) {
            addCriterion("delivery_period_time >", value, "deliveryPeriodTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryPeriodTimeGreaterThanOrEqualTo(String value) {
            addCriterion("delivery_period_time >=", value, "deliveryPeriodTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryPeriodTimeLessThan(String value) {
            addCriterion("delivery_period_time <", value, "deliveryPeriodTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryPeriodTimeLessThanOrEqualTo(String value) {
            addCriterion("delivery_period_time <=", value, "deliveryPeriodTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryPeriodTimeLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("delivery_period_time like", value, "deliveryPeriodTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryPeriodTimeNotLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("delivery_period_time not like", value, "deliveryPeriodTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryPeriodTimeLikeList(List<String> values) {
            addCriterion("delivery_period_time like", values, "deliveryPeriodTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryPeriodTimeIn(List<String> values) {
            addCriterion("delivery_period_time in", values, "deliveryPeriodTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryPeriodTimeNotIn(List<String> values) {
            addCriterion("delivery_period_time not in", values, "deliveryPeriodTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryPeriodTimeBetween(String value1, String value2) {
            addCriterion("delivery_period_time between", value1, value2, "deliveryPeriodTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryPeriodTimeNotBetween(String value1, String value2) {
            addCriterion("delivery_period_time not between", value1, value2, "deliveryPeriodTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        private TaskPeriodExample example;

        protected Criteria(TaskPeriodExample example) {
            super();
            this.example = example;
        }

        public TaskPeriodExample example() {
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