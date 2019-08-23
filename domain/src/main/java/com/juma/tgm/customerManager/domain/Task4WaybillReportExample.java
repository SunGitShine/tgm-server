package com.juma.tgm.customerManager.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Task4WaybillReportExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int startOffSet = -1;

    protected int size = -1;

    public Task4WaybillReportExample() {
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

        public Criteria andTaskReportIdIsNull() {
            addCriterion("task_report_id is null");
            return (Criteria) this;
        }

        public Criteria andTaskReportIdIsNotNull() {
            addCriterion("task_report_id is not null");
            return (Criteria) this;
        }

        public Criteria andTaskReportIdEqualTo(Integer value) {
            addCriterion("task_report_id =", value, "taskReportId");
            return (Criteria) this;
        }

        public Criteria andTaskReportIdNotEqualTo(Integer value) {
            addCriterion("task_report_id <>", value, "taskReportId");
            return (Criteria) this;
        }

        public Criteria andTaskReportIdGreaterThan(Integer value) {
            addCriterion("task_report_id >", value, "taskReportId");
            return (Criteria) this;
        }

        public Criteria andTaskReportIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("task_report_id >=", value, "taskReportId");
            return (Criteria) this;
        }

        public Criteria andTaskReportIdLessThan(Integer value) {
            addCriterion("task_report_id <", value, "taskReportId");
            return (Criteria) this;
        }

        public Criteria andTaskReportIdLessThanOrEqualTo(Integer value) {
            addCriterion("task_report_id <=", value, "taskReportId");
            return (Criteria) this;
        }

        public Criteria andTaskReportIdIn(List<Integer> values) {
            addCriterion("task_report_id in", values, "taskReportId");
            return (Criteria) this;
        }

        public Criteria andTaskReportIdNotIn(List<Integer> values) {
            addCriterion("task_report_id not in", values, "taskReportId");
            return (Criteria) this;
        }

        public Criteria andTaskReportIdBetween(Integer value1, Integer value2) {
            addCriterion("task_report_id between", value1, value2, "taskReportId");
            return (Criteria) this;
        }

        public Criteria andTaskReportIdNotBetween(Integer value1, Integer value2) {
            addCriterion("task_report_id not between", value1, value2, "taskReportId");
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

        public Criteria andTaskExcuteDateIsNull() {
            addCriterion("task_excute_date is null");
            return (Criteria) this;
        }

        public Criteria andTaskExcuteDateIsNotNull() {
            addCriterion("task_excute_date is not null");
            return (Criteria) this;
        }

        public Criteria andTaskExcuteDateEqualTo(Date value) {
            addCriterionForJDBCDate("task_excute_date =", value, "taskExcuteDate");
            return (Criteria) this;
        }

        public Criteria andTaskExcuteDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("task_excute_date <>", value, "taskExcuteDate");
            return (Criteria) this;
        }

        public Criteria andTaskExcuteDateGreaterThan(Date value) {
            addCriterionForJDBCDate("task_excute_date >", value, "taskExcuteDate");
            return (Criteria) this;
        }

        public Criteria andTaskExcuteDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("task_excute_date >=", value, "taskExcuteDate");
            return (Criteria) this;
        }

        public Criteria andTaskExcuteDateLessThan(Date value) {
            addCriterionForJDBCDate("task_excute_date <", value, "taskExcuteDate");
            return (Criteria) this;
        }

        public Criteria andTaskExcuteDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("task_excute_date <=", value, "taskExcuteDate");
            return (Criteria) this;
        }

        public Criteria andTaskExcuteDateIn(List<Date> values) {
            addCriterionForJDBCDate("task_excute_date in", values, "taskExcuteDate");
            return (Criteria) this;
        }

        public Criteria andTaskExcuteDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("task_excute_date not in", values, "taskExcuteDate");
            return (Criteria) this;
        }

        public Criteria andTaskExcuteDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("task_excute_date between", value1, value2, "taskExcuteDate");
            return (Criteria) this;
        }

        public Criteria andTaskExcuteDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("task_excute_date not between", value1, value2, "taskExcuteDate");
            return (Criteria) this;
        }

        public Criteria andTaskStatusIsNull() {
            addCriterion("task_status is null");
            return (Criteria) this;
        }

        public Criteria andTaskStatusIsNotNull() {
            addCriterion("task_status is not null");
            return (Criteria) this;
        }

        public Criteria andTaskStatusEqualTo(Byte value) {
            addCriterion("task_status =", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusNotEqualTo(Byte value) {
            addCriterion("task_status <>", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusGreaterThan(Byte value) {
            addCriterion("task_status >", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("task_status >=", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusLessThan(Byte value) {
            addCriterion("task_status <", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusLessThanOrEqualTo(Byte value) {
            addCriterion("task_status <=", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusIn(List<Byte> values) {
            addCriterion("task_status in", values, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusNotIn(List<Byte> values) {
            addCriterion("task_status not in", values, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusBetween(Byte value1, Byte value2) {
            addCriterion("task_status between", value1, value2, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("task_status not between", value1, value2, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskResultIsNull() {
            addCriterion("task_result is null");
            return (Criteria) this;
        }

        public Criteria andTaskResultIsNotNull() {
            addCriterion("task_result is not null");
            return (Criteria) this;
        }

        public Criteria andTaskResultEqualTo(String value) {
            addCriterion("task_result =", value, "taskResult");
            return (Criteria) this;
        }

        public Criteria andTaskResultNotEqualTo(String value) {
            addCriterion("task_result <>", value, "taskResult");
            return (Criteria) this;
        }

        public Criteria andTaskResultGreaterThan(String value) {
            addCriterion("task_result >", value, "taskResult");
            return (Criteria) this;
        }

        public Criteria andTaskResultGreaterThanOrEqualTo(String value) {
            addCriterion("task_result >=", value, "taskResult");
            return (Criteria) this;
        }

        public Criteria andTaskResultLessThan(String value) {
            addCriterion("task_result <", value, "taskResult");
            return (Criteria) this;
        }

        public Criteria andTaskResultLessThanOrEqualTo(String value) {
            addCriterion("task_result <=", value, "taskResult");
            return (Criteria) this;
        }

        public Criteria andTaskResultLike(String value) {
            addCriterion("task_result like", value, "taskResult");
            return (Criteria) this;
        }

        public Criteria andTaskResultNotLike(String value) {
            addCriterion("task_result not like", value, "taskResult");
            return (Criteria) this;
        }

        public Criteria andTaskResultIn(List<String> values) {
            addCriterion("task_result in", values, "taskResult");
            return (Criteria) this;
        }

        public Criteria andTaskResultNotIn(List<String> values) {
            addCriterion("task_result not in", values, "taskResult");
            return (Criteria) this;
        }

        public Criteria andTaskResultBetween(String value1, String value2) {
            addCriterion("task_result between", value1, value2, "taskResult");
            return (Criteria) this;
        }

        public Criteria andTaskResultNotBetween(String value1, String value2) {
            addCriterion("task_result not between", value1, value2, "taskResult");
            return (Criteria) this;
        }

        public Criteria andHasReadIsNull() {
            addCriterion("has_read is null");
            return (Criteria) this;
        }

        public Criteria andHasReadIsNotNull() {
            addCriterion("has_read is not null");
            return (Criteria) this;
        }

        public Criteria andHasReadEqualTo(Byte value) {
            addCriterion("has_read =", value, "hasRead");
            return (Criteria) this;
        }

        public Criteria andHasReadNotEqualTo(Byte value) {
            addCriterion("has_read <>", value, "hasRead");
            return (Criteria) this;
        }

        public Criteria andHasReadGreaterThan(Byte value) {
            addCriterion("has_read >", value, "hasRead");
            return (Criteria) this;
        }

        public Criteria andHasReadGreaterThanOrEqualTo(Byte value) {
            addCriterion("has_read >=", value, "hasRead");
            return (Criteria) this;
        }

        public Criteria andHasReadLessThan(Byte value) {
            addCriterion("has_read <", value, "hasRead");
            return (Criteria) this;
        }

        public Criteria andHasReadLessThanOrEqualTo(Byte value) {
            addCriterion("has_read <=", value, "hasRead");
            return (Criteria) this;
        }

        public Criteria andHasReadIn(List<Byte> values) {
            addCriterion("has_read in", values, "hasRead");
            return (Criteria) this;
        }

        public Criteria andHasReadNotIn(List<Byte> values) {
            addCriterion("has_read not in", values, "hasRead");
            return (Criteria) this;
        }

        public Criteria andHasReadBetween(Byte value1, Byte value2) {
            addCriterion("has_read between", value1, value2, "hasRead");
            return (Criteria) this;
        }

        public Criteria andHasReadNotBetween(Byte value1, Byte value2) {
            addCriterion("has_read not between", value1, value2, "hasRead");
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