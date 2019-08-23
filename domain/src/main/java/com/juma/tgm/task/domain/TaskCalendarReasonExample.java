package com.juma.tgm.task.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskCalendarReasonExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int startOffSet = -1;

    protected int size = -1;

    public TaskCalendarReasonExample() {
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

    public TaskCalendarReasonExample orderBy(String ... orderByClauses) {
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

    public TaskCalendarReasonExample limit(int pageNo, int pageSize) {
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

        public Criteria andReasonIdIsNull() {
            addCriterion("reason_id is null");
            return (Criteria) this;
        }

        public Criteria andReasonIdIsNotNull() {
            addCriterion("reason_id is not null");
            return (Criteria) this;
        }

        public Criteria andReasonIdEqualTo(Integer value) {
            addCriterion("reason_id =", value, "reasonId");
            return (Criteria) this;
        }

        public Criteria andReasonIdNotEqualTo(Integer value) {
            addCriterion("reason_id <>", value, "reasonId");
            return (Criteria) this;
        }

        public Criteria andReasonIdGreaterThan(Integer value) {
            addCriterion("reason_id >", value, "reasonId");
            return (Criteria) this;
        }

        public Criteria andReasonIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("reason_id >=", value, "reasonId");
            return (Criteria) this;
        }

        public Criteria andReasonIdLessThan(Integer value) {
            addCriterion("reason_id <", value, "reasonId");
            return (Criteria) this;
        }

        public Criteria andReasonIdLessThanOrEqualTo(Integer value) {
            addCriterion("reason_id <=", value, "reasonId");
            return (Criteria) this;
        }

        public Criteria andReasonIdIn(List<Integer> values) {
            addCriterion("reason_id in", values, "reasonId");
            return (Criteria) this;
        }

        public Criteria andReasonIdNotIn(List<Integer> values) {
            addCriterion("reason_id not in", values, "reasonId");
            return (Criteria) this;
        }

        public Criteria andReasonIdBetween(Integer value1, Integer value2) {
            addCriterion("reason_id between", value1, value2, "reasonId");
            return (Criteria) this;
        }

        public Criteria andReasonIdNotBetween(Integer value1, Integer value2) {
            addCriterion("reason_id not between", value1, value2, "reasonId");
            return (Criteria) this;
        }

        public Criteria andCalendarIdIsNull() {
            addCriterion("calendar_id is null");
            return (Criteria) this;
        }

        public Criteria andCalendarIdIsNotNull() {
            addCriterion("calendar_id is not null");
            return (Criteria) this;
        }

        public Criteria andCalendarIdEqualTo(Integer value) {
            addCriterion("calendar_id =", value, "calendarId");
            return (Criteria) this;
        }

        public Criteria andCalendarIdNotEqualTo(Integer value) {
            addCriterion("calendar_id <>", value, "calendarId");
            return (Criteria) this;
        }

        public Criteria andCalendarIdGreaterThan(Integer value) {
            addCriterion("calendar_id >", value, "calendarId");
            return (Criteria) this;
        }

        public Criteria andCalendarIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("calendar_id >=", value, "calendarId");
            return (Criteria) this;
        }

        public Criteria andCalendarIdLessThan(Integer value) {
            addCriterion("calendar_id <", value, "calendarId");
            return (Criteria) this;
        }

        public Criteria andCalendarIdLessThanOrEqualTo(Integer value) {
            addCriterion("calendar_id <=", value, "calendarId");
            return (Criteria) this;
        }

        public Criteria andCalendarIdIn(List<Integer> values) {
            addCriterion("calendar_id in", values, "calendarId");
            return (Criteria) this;
        }

        public Criteria andCalendarIdNotIn(List<Integer> values) {
            addCriterion("calendar_id not in", values, "calendarId");
            return (Criteria) this;
        }

        public Criteria andCalendarIdBetween(Integer value1, Integer value2) {
            addCriterion("calendar_id between", value1, value2, "calendarId");
            return (Criteria) this;
        }

        public Criteria andCalendarIdNotBetween(Integer value1, Integer value2) {
            addCriterion("calendar_id not between", value1, value2, "calendarId");
            return (Criteria) this;
        }

        public Criteria andReasonTypeIsNull() {
            addCriterion("reason_type is null");
            return (Criteria) this;
        }

        public Criteria andReasonTypeIsNotNull() {
            addCriterion("reason_type is not null");
            return (Criteria) this;
        }

        public Criteria andReasonTypeEqualTo(Integer value) {
            addCriterion("reason_type =", value, "reasonType");
            return (Criteria) this;
        }

        public Criteria andReasonTypeNotEqualTo(Integer value) {
            addCriterion("reason_type <>", value, "reasonType");
            return (Criteria) this;
        }

        public Criteria andReasonTypeGreaterThan(Integer value) {
            addCriterion("reason_type >", value, "reasonType");
            return (Criteria) this;
        }

        public Criteria andReasonTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("reason_type >=", value, "reasonType");
            return (Criteria) this;
        }

        public Criteria andReasonTypeLessThan(Integer value) {
            addCriterion("reason_type <", value, "reasonType");
            return (Criteria) this;
        }

        public Criteria andReasonTypeLessThanOrEqualTo(Integer value) {
            addCriterion("reason_type <=", value, "reasonType");
            return (Criteria) this;
        }

        public Criteria andReasonTypeIn(List<Integer> values) {
            addCriterion("reason_type in", values, "reasonType");
            return (Criteria) this;
        }

        public Criteria andReasonTypeNotIn(List<Integer> values) {
            addCriterion("reason_type not in", values, "reasonType");
            return (Criteria) this;
        }

        public Criteria andReasonTypeBetween(Integer value1, Integer value2) {
            addCriterion("reason_type between", value1, value2, "reasonType");
            return (Criteria) this;
        }

        public Criteria andReasonTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("reason_type not between", value1, value2, "reasonType");
            return (Criteria) this;
        }

        public Criteria andReasonSortIsNull() {
            addCriterion("reason_sort is null");
            return (Criteria) this;
        }

        public Criteria andReasonSortIsNotNull() {
            addCriterion("reason_sort is not null");
            return (Criteria) this;
        }

        public Criteria andReasonSortEqualTo(Integer value) {
            addCriterion("reason_sort =", value, "reasonSort");
            return (Criteria) this;
        }

        public Criteria andReasonSortNotEqualTo(Integer value) {
            addCriterion("reason_sort <>", value, "reasonSort");
            return (Criteria) this;
        }

        public Criteria andReasonSortGreaterThan(Integer value) {
            addCriterion("reason_sort >", value, "reasonSort");
            return (Criteria) this;
        }

        public Criteria andReasonSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("reason_sort >=", value, "reasonSort");
            return (Criteria) this;
        }

        public Criteria andReasonSortLessThan(Integer value) {
            addCriterion("reason_sort <", value, "reasonSort");
            return (Criteria) this;
        }

        public Criteria andReasonSortLessThanOrEqualTo(Integer value) {
            addCriterion("reason_sort <=", value, "reasonSort");
            return (Criteria) this;
        }

        public Criteria andReasonSortIn(List<Integer> values) {
            addCriterion("reason_sort in", values, "reasonSort");
            return (Criteria) this;
        }

        public Criteria andReasonSortNotIn(List<Integer> values) {
            addCriterion("reason_sort not in", values, "reasonSort");
            return (Criteria) this;
        }

        public Criteria andReasonSortBetween(Integer value1, Integer value2) {
            addCriterion("reason_sort between", value1, value2, "reasonSort");
            return (Criteria) this;
        }

        public Criteria andReasonSortNotBetween(Integer value1, Integer value2) {
            addCriterion("reason_sort not between", value1, value2, "reasonSort");
            return (Criteria) this;
        }

        public Criteria andReasonIsNull() {
            addCriterion("reason is null");
            return (Criteria) this;
        }

        public Criteria andReasonIsNotNull() {
            addCriterion("reason is not null");
            return (Criteria) this;
        }

        public Criteria andReasonEqualTo(String value) {
            addCriterion("reason =", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotEqualTo(String value) {
            addCriterion("reason <>", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThan(String value) {
            addCriterion("reason >", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThanOrEqualTo(String value) {
            addCriterion("reason >=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThan(String value) {
            addCriterion("reason <", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThanOrEqualTo(String value) {
            addCriterion("reason <=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("reason like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("reason not like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLikeList(List<String> values) {
            addCriterion("reason like", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonIn(List<String> values) {
            addCriterion("reason in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotIn(List<String> values) {
            addCriterion("reason not in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonBetween(String value1, String value2) {
            addCriterion("reason between", value1, value2, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotBetween(String value1, String value2) {
            addCriterion("reason not between", value1, value2, "reason");
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
        private TaskCalendarReasonExample example;

        protected Criteria(TaskCalendarReasonExample example) {
            super();
            this.example = example;
        }

        public TaskCalendarReasonExample example() {
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