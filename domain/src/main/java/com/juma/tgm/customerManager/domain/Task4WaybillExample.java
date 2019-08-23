package com.juma.tgm.customerManager.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Task4WaybillExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int startOffSet = -1;

    protected int size = -1;

    public Task4WaybillExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andTaskWaybillTemplateIdIsNull() {
            addCriterion("task_waybill_template_id is null");
            return (Criteria) this;
        }

        public Criteria andTaskWaybillTemplateIdIsNotNull() {
            addCriterion("task_waybill_template_id is not null");
            return (Criteria) this;
        }

        public Criteria andTaskWaybillTemplateIdEqualTo(Integer value) {
            addCriterion("task_waybill_template_id =", value, "taskWaybillTemplateId");
            return (Criteria) this;
        }

        public Criteria andTaskWaybillTemplateIdNotEqualTo(Integer value) {
            addCriterion("task_waybill_template_id <>", value, "taskWaybillTemplateId");
            return (Criteria) this;
        }

        public Criteria andTaskWaybillTemplateIdGreaterThan(Integer value) {
            addCriterion("task_waybill_template_id >", value, "taskWaybillTemplateId");
            return (Criteria) this;
        }

        public Criteria andTaskWaybillTemplateIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("task_waybill_template_id >=", value, "taskWaybillTemplateId");
            return (Criteria) this;
        }

        public Criteria andTaskWaybillTemplateIdLessThan(Integer value) {
            addCriterion("task_waybill_template_id <", value, "taskWaybillTemplateId");
            return (Criteria) this;
        }

        public Criteria andTaskWaybillTemplateIdLessThanOrEqualTo(Integer value) {
            addCriterion("task_waybill_template_id <=", value, "taskWaybillTemplateId");
            return (Criteria) this;
        }

        public Criteria andTaskWaybillTemplateIdIn(List<Integer> values) {
            addCriterion("task_waybill_template_id in", values, "taskWaybillTemplateId");
            return (Criteria) this;
        }

        public Criteria andTaskWaybillTemplateIdNotIn(List<Integer> values) {
            addCriterion("task_waybill_template_id not in", values, "taskWaybillTemplateId");
            return (Criteria) this;
        }

        public Criteria andTaskWaybillTemplateIdBetween(Integer value1, Integer value2) {
            addCriterion("task_waybill_template_id between", value1, value2, "taskWaybillTemplateId");
            return (Criteria) this;
        }

        public Criteria andTaskWaybillTemplateIdNotBetween(Integer value1, Integer value2) {
            addCriterion("task_waybill_template_id not between", value1, value2, "taskWaybillTemplateId");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdIsNull() {
            addCriterion("employee_id is null");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdIsNotNull() {
            addCriterion("employee_id is not null");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdEqualTo(Integer value) {
            addCriterion("employee_id =", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdNotEqualTo(Integer value) {
            addCriterion("employee_id <>", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdGreaterThan(Integer value) {
            addCriterion("employee_id >", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("employee_id >=", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdLessThan(Integer value) {
            addCriterion("employee_id <", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdLessThanOrEqualTo(Integer value) {
            addCriterion("employee_id <=", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdIn(List<Integer> values) {
            addCriterion("employee_id in", values, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdNotIn(List<Integer> values) {
            addCriterion("employee_id not in", values, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdBetween(Integer value1, Integer value2) {
            addCriterion("employee_id between", value1, value2, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("employee_id not between", value1, value2, "employeeId");
            return (Criteria) this;
        }

        public Criteria andTaskStartDateIsNull() {
            addCriterion("task_start_date is null");
            return (Criteria) this;
        }

        public Criteria andTaskStartDateIsNotNull() {
            addCriterion("task_start_date is not null");
            return (Criteria) this;
        }

        public Criteria andTaskStartDateEqualTo(Date value) {
            addCriterionForJDBCDate("task_start_date =", value, "taskStartDate");
            return (Criteria) this;
        }

        public Criteria andTaskStartDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("task_start_date <>", value, "taskStartDate");
            return (Criteria) this;
        }

        public Criteria andTaskStartDateGreaterThan(Date value) {
            addCriterionForJDBCDate("task_start_date >", value, "taskStartDate");
            return (Criteria) this;
        }

        public Criteria andTaskStartDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("task_start_date >=", value, "taskStartDate");
            return (Criteria) this;
        }

        public Criteria andTaskStartDateLessThan(Date value) {
            addCriterionForJDBCDate("task_start_date <", value, "taskStartDate");
            return (Criteria) this;
        }

        public Criteria andTaskStartDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("task_start_date <=", value, "taskStartDate");
            return (Criteria) this;
        }

        public Criteria andTaskStartDateIn(List<Date> values) {
            addCriterionForJDBCDate("task_start_date in", values, "taskStartDate");
            return (Criteria) this;
        }

        public Criteria andTaskStartDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("task_start_date not in", values, "taskStartDate");
            return (Criteria) this;
        }

        public Criteria andTaskStartDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("task_start_date between", value1, value2, "taskStartDate");
            return (Criteria) this;
        }

        public Criteria andTaskStartDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("task_start_date not between", value1, value2, "taskStartDate");
            return (Criteria) this;
        }

        public Criteria andTaskEndDateIsNull() {
            addCriterion("task_end_date is null");
            return (Criteria) this;
        }

        public Criteria andTaskEndDateIsNotNull() {
            addCriterion("task_end_date is not null");
            return (Criteria) this;
        }

        public Criteria andTaskEndDateEqualTo(Date value) {
            addCriterionForJDBCDate("task_end_date =", value, "taskEndDate");
            return (Criteria) this;
        }

        public Criteria andTaskEndDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("task_end_date <>", value, "taskEndDate");
            return (Criteria) this;
        }

        public Criteria andTaskEndDateGreaterThan(Date value) {
            addCriterionForJDBCDate("task_end_date >", value, "taskEndDate");
            return (Criteria) this;
        }

        public Criteria andTaskEndDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("task_end_date >=", value, "taskEndDate");
            return (Criteria) this;
        }

        public Criteria andTaskEndDateLessThan(Date value) {
            addCriterionForJDBCDate("task_end_date <", value, "taskEndDate");
            return (Criteria) this;
        }

        public Criteria andTaskEndDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("task_end_date <=", value, "taskEndDate");
            return (Criteria) this;
        }

        public Criteria andTaskEndDateIn(List<Date> values) {
            addCriterionForJDBCDate("task_end_date in", values, "taskEndDate");
            return (Criteria) this;
        }

        public Criteria andTaskEndDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("task_end_date not in", values, "taskEndDate");
            return (Criteria) this;
        }

        public Criteria andTaskEndDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("task_end_date between", value1, value2, "taskEndDate");
            return (Criteria) this;
        }

        public Criteria andTaskEndDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("task_end_date not between", value1, value2, "taskEndDate");
            return (Criteria) this;
        }

        public Criteria andTaskWeekDaysIsNull() {
            addCriterion("task_week_days is null");
            return (Criteria) this;
        }

        public Criteria andTaskWeekDaysIsNotNull() {
            addCriterion("task_week_days is not null");
            return (Criteria) this;
        }

        public Criteria andTaskWeekDaysEqualTo(String value) {
            addCriterion("task_week_days =", value, "taskWeekDays");
            return (Criteria) this;
        }

        public Criteria andTaskWeekDaysNotEqualTo(String value) {
            addCriterion("task_week_days <>", value, "taskWeekDays");
            return (Criteria) this;
        }

        public Criteria andTaskWeekDaysGreaterThan(String value) {
            addCriterion("task_week_days >", value, "taskWeekDays");
            return (Criteria) this;
        }

        public Criteria andTaskWeekDaysGreaterThanOrEqualTo(String value) {
            addCriterion("task_week_days >=", value, "taskWeekDays");
            return (Criteria) this;
        }

        public Criteria andTaskWeekDaysLessThan(String value) {
            addCriterion("task_week_days <", value, "taskWeekDays");
            return (Criteria) this;
        }

        public Criteria andTaskWeekDaysLessThanOrEqualTo(String value) {
            addCriterion("task_week_days <=", value, "taskWeekDays");
            return (Criteria) this;
        }

        public Criteria andTaskWeekDaysLike(String value) {
            addCriterion("task_week_days like", value, "taskWeekDays");
            return (Criteria) this;
        }

        public Criteria andTaskWeekDaysNotLike(String value) {
            addCriterion("task_week_days not like", value, "taskWeekDays");
            return (Criteria) this;
        }

        public Criteria andTaskWeekDaysIn(List<String> values) {
            addCriterion("task_week_days in", values, "taskWeekDays");
            return (Criteria) this;
        }

        public Criteria andTaskWeekDaysNotIn(List<String> values) {
            addCriterion("task_week_days not in", values, "taskWeekDays");
            return (Criteria) this;
        }

        public Criteria andTaskWeekDaysBetween(String value1, String value2) {
            addCriterion("task_week_days between", value1, value2, "taskWeekDays");
            return (Criteria) this;
        }

        public Criteria andTaskWeekDaysNotBetween(String value1, String value2) {
            addCriterion("task_week_days not between", value1, value2, "taskWeekDays");
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

        public Criteria andIsCloseIsNull() {
            addCriterion("is_close is null");
            return (Criteria) this;
        }

        public Criteria andIsCloseIsNotNull() {
            addCriterion("is_close is not null");
            return (Criteria) this;
        }

        public Criteria andIsCloseEqualTo(Byte value) {
            addCriterion("is_close =", value, "isClose");
            return (Criteria) this;
        }

        public Criteria andIsCloseNotEqualTo(Byte value) {
            addCriterion("is_close <>", value, "isClose");
            return (Criteria) this;
        }

        public Criteria andIsCloseGreaterThan(Byte value) {
            addCriterion("is_close >", value, "isClose");
            return (Criteria) this;
        }

        public Criteria andIsCloseGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_close >=", value, "isClose");
            return (Criteria) this;
        }

        public Criteria andIsCloseLessThan(Byte value) {
            addCriterion("is_close <", value, "isClose");
            return (Criteria) this;
        }

        public Criteria andIsCloseLessThanOrEqualTo(Byte value) {
            addCriterion("is_close <=", value, "isClose");
            return (Criteria) this;
        }

        public Criteria andIsCloseIn(List<Byte> values) {
            addCriterion("is_close in", values, "isClose");
            return (Criteria) this;
        }

        public Criteria andIsCloseNotIn(List<Byte> values) {
            addCriterion("is_close not in", values, "isClose");
            return (Criteria) this;
        }

        public Criteria andIsCloseBetween(Byte value1, Byte value2) {
            addCriterion("is_close between", value1, value2, "isClose");
            return (Criteria) this;
        }

        public Criteria andIsCloseNotBetween(Byte value1, Byte value2) {
            addCriterion("is_close not between", value1, value2, "isClose");
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