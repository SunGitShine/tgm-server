package com.juma.tgm.project.domain.v2;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ProjectWorkflowExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int startOffSet = -1;

    protected int size = -1;

    public ProjectWorkflowExample() {
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

    public ProjectWorkflowExample orderBy(String ... orderByClauses) {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
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

        public Criteria andProjectWorkflowIdIsNull() {
            addCriterion("project_workflow_id is null");
            return (Criteria) this;
        }

        public Criteria andProjectWorkflowIdIsNotNull() {
            addCriterion("project_workflow_id is not null");
            return (Criteria) this;
        }

        public Criteria andProjectWorkflowIdEqualTo(Integer value) {
            addCriterion("project_workflow_id =", value, "projectWorkflowId");
            return (Criteria) this;
        }

        public Criteria andProjectWorkflowIdNotEqualTo(Integer value) {
            addCriterion("project_workflow_id <>", value, "projectWorkflowId");
            return (Criteria) this;
        }

        public Criteria andProjectWorkflowIdGreaterThan(Integer value) {
            addCriterion("project_workflow_id >", value, "projectWorkflowId");
            return (Criteria) this;
        }

        public Criteria andProjectWorkflowIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("project_workflow_id >=", value, "projectWorkflowId");
            return (Criteria) this;
        }

        public Criteria andProjectWorkflowIdLessThan(Integer value) {
            addCriterion("project_workflow_id <", value, "projectWorkflowId");
            return (Criteria) this;
        }

        public Criteria andProjectWorkflowIdLessThanOrEqualTo(Integer value) {
            addCriterion("project_workflow_id <=", value, "projectWorkflowId");
            return (Criteria) this;
        }

        public Criteria andProjectWorkflowIdIn(List<Integer> values) {
            addCriterion("project_workflow_id in", values, "projectWorkflowId");
            return (Criteria) this;
        }

        public Criteria andProjectWorkflowIdNotIn(List<Integer> values) {
            addCriterion("project_workflow_id not in", values, "projectWorkflowId");
            return (Criteria) this;
        }

        public Criteria andProjectWorkflowIdBetween(Integer value1, Integer value2) {
            addCriterion("project_workflow_id between", value1, value2, "projectWorkflowId");
            return (Criteria) this;
        }

        public Criteria andProjectWorkflowIdNotBetween(Integer value1, Integer value2) {
            addCriterion("project_workflow_id not between", value1, value2, "projectWorkflowId");
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

        public Criteria andProjectWorkflowTypeIsNull() {
            addCriterion("project_workflow_type is null");
            return (Criteria) this;
        }

        public Criteria andProjectWorkflowTypeIsNotNull() {
            addCriterion("project_workflow_type is not null");
            return (Criteria) this;
        }

        public Criteria andProjectWorkflowTypeEqualTo(Integer value) {
            addCriterion("project_workflow_type =", value, "projectWorkflowType");
            return (Criteria) this;
        }

        public Criteria andProjectWorkflowTypeNotEqualTo(Integer value) {
            addCriterion("project_workflow_type <>", value, "projectWorkflowType");
            return (Criteria) this;
        }

        public Criteria andProjectWorkflowTypeGreaterThan(Integer value) {
            addCriterion("project_workflow_type >", value, "projectWorkflowType");
            return (Criteria) this;
        }

        public Criteria andProjectWorkflowTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("project_workflow_type >=", value, "projectWorkflowType");
            return (Criteria) this;
        }

        public Criteria andProjectWorkflowTypeLessThan(Integer value) {
            addCriterion("project_workflow_type <", value, "projectWorkflowType");
            return (Criteria) this;
        }

        public Criteria andProjectWorkflowTypeLessThanOrEqualTo(Integer value) {
            addCriterion("project_workflow_type <=", value, "projectWorkflowType");
            return (Criteria) this;
        }

        public Criteria andProjectWorkflowTypeIn(List<Integer> values) {
            addCriterion("project_workflow_type in", values, "projectWorkflowType");
            return (Criteria) this;
        }

        public Criteria andProjectWorkflowTypeNotIn(List<Integer> values) {
            addCriterion("project_workflow_type not in", values, "projectWorkflowType");
            return (Criteria) this;
        }

        public Criteria andProjectWorkflowTypeBetween(Integer value1, Integer value2) {
            addCriterion("project_workflow_type between", value1, value2, "projectWorkflowType");
            return (Criteria) this;
        }

        public Criteria andProjectWorkflowTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("project_workflow_type not between", value1, value2, "projectWorkflowType");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIsNull() {
            addCriterion("audit_status is null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIsNotNull() {
            addCriterion("audit_status is not null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusEqualTo(Integer value) {
            addCriterion("audit_status =", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNotEqualTo(Integer value) {
            addCriterion("audit_status <>", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusGreaterThan(Integer value) {
            addCriterion("audit_status >", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("audit_status >=", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusLessThan(Integer value) {
            addCriterion("audit_status <", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusLessThanOrEqualTo(Integer value) {
            addCriterion("audit_status <=", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIn(List<Integer> values) {
            addCriterion("audit_status in", values, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNotIn(List<Integer> values) {
            addCriterion("audit_status not in", values, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusBetween(Integer value1, Integer value2) {
            addCriterion("audit_status between", value1, value2, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("audit_status not between", value1, value2, "auditStatus");
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

        public Criteria andProcessInstanceIdLikeList(List<String> values) {
            addCriterion("process_instance_id like", values, "processInstanceId");
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

        public Criteria andExcuteTimeIsNull() {
            addCriterion("excute_time is null");
            return (Criteria) this;
        }

        public Criteria andExcuteTimeIsNotNull() {
            addCriterion("excute_time is not null");
            return (Criteria) this;
        }

        public Criteria andExcuteTimeEqualTo(Date value) {
            addCriterionForJDBCDate("excute_time =", value, "excuteTime");
            return (Criteria) this;
        }

        public Criteria andExcuteTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("excute_time <>", value, "excuteTime");
            return (Criteria) this;
        }

        public Criteria andExcuteTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("excute_time >", value, "excuteTime");
            return (Criteria) this;
        }

        public Criteria andExcuteTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("excute_time >=", value, "excuteTime");
            return (Criteria) this;
        }

        public Criteria andExcuteTimeLessThan(Date value) {
            addCriterionForJDBCDate("excute_time <", value, "excuteTime");
            return (Criteria) this;
        }

        public Criteria andExcuteTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("excute_time <=", value, "excuteTime");
            return (Criteria) this;
        }

        public Criteria andExcuteTimeIn(List<Date> values) {
            addCriterionForJDBCDate("excute_time in", values, "excuteTime");
            return (Criteria) this;
        }

        public Criteria andExcuteTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("excute_time not in", values, "excuteTime");
            return (Criteria) this;
        }

        public Criteria andExcuteTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("excute_time between", value1, value2, "excuteTime");
            return (Criteria) this;
        }

        public Criteria andExcuteTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("excute_time not between", value1, value2, "excuteTime");
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
            addCriterion("reason like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotLike(String value) {
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

        public Criteria andAttachmentIsNull() {
            addCriterion("attachment is null");
            return (Criteria) this;
        }

        public Criteria andAttachmentIsNotNull() {
            addCriterion("attachment is not null");
            return (Criteria) this;
        }

        public Criteria andAttachmentEqualTo(String value) {
            addCriterion("attachment =", value, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentNotEqualTo(String value) {
            addCriterion("attachment <>", value, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentGreaterThan(String value) {
            addCriterion("attachment >", value, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentGreaterThanOrEqualTo(String value) {
            addCriterion("attachment >=", value, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentLessThan(String value) {
            addCriterion("attachment <", value, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentLessThanOrEqualTo(String value) {
            addCriterion("attachment <=", value, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentLike(String value) {
            addCriterion("attachment like", value, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentNotLike(String value) {
            addCriterion("attachment not like", value, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentLikeList(List<String> values) {
            addCriterion("attachment like", values, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentIn(List<String> values) {
            addCriterion("attachment in", values, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentNotIn(List<String> values) {
            addCriterion("attachment not in", values, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentBetween(String value1, String value2) {
            addCriterion("attachment between", value1, value2, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentNotBetween(String value1, String value2) {
            addCriterion("attachment not between", value1, value2, "attachment");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        private ProjectWorkflowExample example;

        protected Criteria(ProjectWorkflowExample example) {
            super();
            this.example = example;
        }

        public ProjectWorkflowExample example() {
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
                if(condition.contains("like")) {
                    this.likeListValue = true;
                } else if(condition.contains("in")) {
                    this.listValue = true;
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