package com.juma.tgm.task.domain;

import java.util.ArrayList;
import java.util.List;

public class TaskNotfixedDeliveryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int startOffSet = -1;

    protected int size = -1;

    public TaskNotfixedDeliveryExample() {
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

    public TaskNotfixedDeliveryExample orderBy(String ... orderByClauses) {
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

    public TaskNotfixedDeliveryExample limit(int pageNo, int pageSize) {
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

        public Criteria andNotfixedIdIsNull() {
            addCriterion("notfixed_id is null");
            return (Criteria) this;
        }

        public Criteria andNotfixedIdIsNotNull() {
            addCriterion("notfixed_id is not null");
            return (Criteria) this;
        }

        public Criteria andNotfixedIdEqualTo(Integer value) {
            addCriterion("notfixed_id =", value, "notfixedId");
            return (Criteria) this;
        }

        public Criteria andNotfixedIdNotEqualTo(Integer value) {
            addCriterion("notfixed_id <>", value, "notfixedId");
            return (Criteria) this;
        }

        public Criteria andNotfixedIdGreaterThan(Integer value) {
            addCriterion("notfixed_id >", value, "notfixedId");
            return (Criteria) this;
        }

        public Criteria andNotfixedIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("notfixed_id >=", value, "notfixedId");
            return (Criteria) this;
        }

        public Criteria andNotfixedIdLessThan(Integer value) {
            addCriterion("notfixed_id <", value, "notfixedId");
            return (Criteria) this;
        }

        public Criteria andNotfixedIdLessThanOrEqualTo(Integer value) {
            addCriterion("notfixed_id <=", value, "notfixedId");
            return (Criteria) this;
        }

        public Criteria andNotfixedIdIn(List<Integer> values) {
            addCriterion("notfixed_id in", values, "notfixedId");
            return (Criteria) this;
        }

        public Criteria andNotfixedIdNotIn(List<Integer> values) {
            addCriterion("notfixed_id not in", values, "notfixedId");
            return (Criteria) this;
        }

        public Criteria andNotfixedIdBetween(Integer value1, Integer value2) {
            addCriterion("notfixed_id between", value1, value2, "notfixedId");
            return (Criteria) this;
        }

        public Criteria andNotfixedIdNotBetween(Integer value1, Integer value2) {
            addCriterion("notfixed_id not between", value1, value2, "notfixedId");
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

        public Criteria andMinStopsIsNull() {
            addCriterion("min_stops is null");
            return (Criteria) this;
        }

        public Criteria andMinStopsIsNotNull() {
            addCriterion("min_stops is not null");
            return (Criteria) this;
        }

        public Criteria andMinStopsEqualTo(Integer value) {
            addCriterion("min_stops =", value, "minStops");
            return (Criteria) this;
        }

        public Criteria andMinStopsNotEqualTo(Integer value) {
            addCriterion("min_stops <>", value, "minStops");
            return (Criteria) this;
        }

        public Criteria andMinStopsGreaterThan(Integer value) {
            addCriterion("min_stops >", value, "minStops");
            return (Criteria) this;
        }

        public Criteria andMinStopsGreaterThanOrEqualTo(Integer value) {
            addCriterion("min_stops >=", value, "minStops");
            return (Criteria) this;
        }

        public Criteria andMinStopsLessThan(Integer value) {
            addCriterion("min_stops <", value, "minStops");
            return (Criteria) this;
        }

        public Criteria andMinStopsLessThanOrEqualTo(Integer value) {
            addCriterion("min_stops <=", value, "minStops");
            return (Criteria) this;
        }

        public Criteria andMinStopsIn(List<Integer> values) {
            addCriterion("min_stops in", values, "minStops");
            return (Criteria) this;
        }

        public Criteria andMinStopsNotIn(List<Integer> values) {
            addCriterion("min_stops not in", values, "minStops");
            return (Criteria) this;
        }

        public Criteria andMinStopsBetween(Integer value1, Integer value2) {
            addCriterion("min_stops between", value1, value2, "minStops");
            return (Criteria) this;
        }

        public Criteria andMinStopsNotBetween(Integer value1, Integer value2) {
            addCriterion("min_stops not between", value1, value2, "minStops");
            return (Criteria) this;
        }

        public Criteria andMaxStopsIsNull() {
            addCriterion("max_stops is null");
            return (Criteria) this;
        }

        public Criteria andMaxStopsIsNotNull() {
            addCriterion("max_stops is not null");
            return (Criteria) this;
        }

        public Criteria andMaxStopsEqualTo(Integer value) {
            addCriterion("max_stops =", value, "maxStops");
            return (Criteria) this;
        }

        public Criteria andMaxStopsNotEqualTo(Integer value) {
            addCriterion("max_stops <>", value, "maxStops");
            return (Criteria) this;
        }

        public Criteria andMaxStopsGreaterThan(Integer value) {
            addCriterion("max_stops >", value, "maxStops");
            return (Criteria) this;
        }

        public Criteria andMaxStopsGreaterThanOrEqualTo(Integer value) {
            addCriterion("max_stops >=", value, "maxStops");
            return (Criteria) this;
        }

        public Criteria andMaxStopsLessThan(Integer value) {
            addCriterion("max_stops <", value, "maxStops");
            return (Criteria) this;
        }

        public Criteria andMaxStopsLessThanOrEqualTo(Integer value) {
            addCriterion("max_stops <=", value, "maxStops");
            return (Criteria) this;
        }

        public Criteria andMaxStopsIn(List<Integer> values) {
            addCriterion("max_stops in", values, "maxStops");
            return (Criteria) this;
        }

        public Criteria andMaxStopsNotIn(List<Integer> values) {
            addCriterion("max_stops not in", values, "maxStops");
            return (Criteria) this;
        }

        public Criteria andMaxStopsBetween(Integer value1, Integer value2) {
            addCriterion("max_stops between", value1, value2, "maxStops");
            return (Criteria) this;
        }

        public Criteria andMaxStopsNotBetween(Integer value1, Integer value2) {
            addCriterion("max_stops not between", value1, value2, "maxStops");
            return (Criteria) this;
        }

        public Criteria andAddressDetailIsNull() {
            addCriterion("address_detail is null");
            return (Criteria) this;
        }

        public Criteria andAddressDetailIsNotNull() {
            addCriterion("address_detail is not null");
            return (Criteria) this;
        }

        public Criteria andAddressDetailEqualTo(String value) {
            addCriterion("address_detail =", value, "addressDetail");
            return (Criteria) this;
        }

        public Criteria andAddressDetailNotEqualTo(String value) {
            addCriterion("address_detail <>", value, "addressDetail");
            return (Criteria) this;
        }

        public Criteria andAddressDetailGreaterThan(String value) {
            addCriterion("address_detail >", value, "addressDetail");
            return (Criteria) this;
        }

        public Criteria andAddressDetailGreaterThanOrEqualTo(String value) {
            addCriterion("address_detail >=", value, "addressDetail");
            return (Criteria) this;
        }

        public Criteria andAddressDetailLessThan(String value) {
            addCriterion("address_detail <", value, "addressDetail");
            return (Criteria) this;
        }

        public Criteria andAddressDetailLessThanOrEqualTo(String value) {
            addCriterion("address_detail <=", value, "addressDetail");
            return (Criteria) this;
        }

        public Criteria andAddressDetailLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("address_detail like", value, "addressDetail");
            return (Criteria) this;
        }

        public Criteria andAddressDetailNotLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("address_detail not like", value, "addressDetail");
            return (Criteria) this;
        }

        public Criteria andAddressDetailLikeList(List<String> values) {
            addCriterion("address_detail like", values, "addressDetail");
            return (Criteria) this;
        }

        public Criteria andAddressDetailIn(List<String> values) {
            addCriterion("address_detail in", values, "addressDetail");
            return (Criteria) this;
        }

        public Criteria andAddressDetailNotIn(List<String> values) {
            addCriterion("address_detail not in", values, "addressDetail");
            return (Criteria) this;
        }

        public Criteria andAddressDetailBetween(String value1, String value2) {
            addCriterion("address_detail between", value1, value2, "addressDetail");
            return (Criteria) this;
        }

        public Criteria andAddressDetailNotBetween(String value1, String value2) {
            addCriterion("address_detail not between", value1, value2, "addressDetail");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        private TaskNotfixedDeliveryExample example;

        protected Criteria(TaskNotfixedDeliveryExample example) {
            super();
            this.example = example;
        }

        public TaskNotfixedDeliveryExample example() {
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