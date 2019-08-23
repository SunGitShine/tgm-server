package com.juma.tgm.operateLog.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OperateLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int startOffSet = -1;

    protected int size = -1;

    public OperateLogExample() {
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

        public Criteria andOperateLogIdIsNull() {
            addCriterion("operate_log_id is null");
            return (Criteria) this;
        }

        public Criteria andOperateLogIdIsNotNull() {
            addCriterion("operate_log_id is not null");
            return (Criteria) this;
        }

        public Criteria andOperateLogIdEqualTo(Integer value) {
            addCriterion("operate_log_id =", value, "operateLogId");
            return (Criteria) this;
        }

        public Criteria andOperateLogIdNotEqualTo(Integer value) {
            addCriterion("operate_log_id <>", value, "operateLogId");
            return (Criteria) this;
        }

        public Criteria andOperateLogIdGreaterThan(Integer value) {
            addCriterion("operate_log_id >", value, "operateLogId");
            return (Criteria) this;
        }

        public Criteria andOperateLogIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("operate_log_id >=", value, "operateLogId");
            return (Criteria) this;
        }

        public Criteria andOperateLogIdLessThan(Integer value) {
            addCriterion("operate_log_id <", value, "operateLogId");
            return (Criteria) this;
        }

        public Criteria andOperateLogIdLessThanOrEqualTo(Integer value) {
            addCriterion("operate_log_id <=", value, "operateLogId");
            return (Criteria) this;
        }

        public Criteria andOperateLogIdIn(List<Integer> values) {
            addCriterion("operate_log_id in", values, "operateLogId");
            return (Criteria) this;
        }

        public Criteria andOperateLogIdNotIn(List<Integer> values) {
            addCriterion("operate_log_id not in", values, "operateLogId");
            return (Criteria) this;
        }

        public Criteria andOperateLogIdBetween(Integer value1, Integer value2) {
            addCriterion("operate_log_id between", value1, value2, "operateLogId");
            return (Criteria) this;
        }

        public Criteria andOperateLogIdNotBetween(Integer value1, Integer value2) {
            addCriterion("operate_log_id not between", value1, value2, "operateLogId");
            return (Criteria) this;
        }

        public Criteria andLogSignIsNull() {
            addCriterion("log_sign is null");
            return (Criteria) this;
        }

        public Criteria andLogSignIsNotNull() {
            addCriterion("log_sign is not null");
            return (Criteria) this;
        }

        public Criteria andLogSignEqualTo(Byte value) {
            addCriterion("log_sign =", value, "logSign");
            return (Criteria) this;
        }

        public Criteria andLogSignNotEqualTo(Byte value) {
            addCriterion("log_sign <>", value, "logSign");
            return (Criteria) this;
        }

        public Criteria andLogSignGreaterThan(Byte value) {
            addCriterion("log_sign >", value, "logSign");
            return (Criteria) this;
        }

        public Criteria andLogSignGreaterThanOrEqualTo(Byte value) {
            addCriterion("log_sign >=", value, "logSign");
            return (Criteria) this;
        }

        public Criteria andLogSignLessThan(Byte value) {
            addCriterion("log_sign <", value, "logSign");
            return (Criteria) this;
        }

        public Criteria andLogSignLessThanOrEqualTo(Byte value) {
            addCriterion("log_sign <=", value, "logSign");
            return (Criteria) this;
        }

        public Criteria andLogSignIn(List<Byte> values) {
            addCriterion("log_sign in", values, "logSign");
            return (Criteria) this;
        }

        public Criteria andLogSignNotIn(List<Byte> values) {
            addCriterion("log_sign not in", values, "logSign");
            return (Criteria) this;
        }

        public Criteria andLogSignBetween(Byte value1, Byte value2) {
            addCriterion("log_sign between", value1, value2, "logSign");
            return (Criteria) this;
        }

        public Criteria andLogSignNotBetween(Byte value1, Byte value2) {
            addCriterion("log_sign not between", value1, value2, "logSign");
            return (Criteria) this;
        }

        public Criteria andRelationTableIdIsNull() {
            addCriterion("relation_table_id is null");
            return (Criteria) this;
        }

        public Criteria andRelationTableIdIsNotNull() {
            addCriterion("relation_table_id is not null");
            return (Criteria) this;
        }

        public Criteria andRelationTableIdEqualTo(Integer value) {
            addCriterion("relation_table_id =", value, "relationTableId");
            return (Criteria) this;
        }

        public Criteria andRelationTableIdNotEqualTo(Integer value) {
            addCriterion("relation_table_id <>", value, "relationTableId");
            return (Criteria) this;
        }

        public Criteria andRelationTableIdGreaterThan(Integer value) {
            addCriterion("relation_table_id >", value, "relationTableId");
            return (Criteria) this;
        }

        public Criteria andRelationTableIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("relation_table_id >=", value, "relationTableId");
            return (Criteria) this;
        }

        public Criteria andRelationTableIdLessThan(Integer value) {
            addCriterion("relation_table_id <", value, "relationTableId");
            return (Criteria) this;
        }

        public Criteria andRelationTableIdLessThanOrEqualTo(Integer value) {
            addCriterion("relation_table_id <=", value, "relationTableId");
            return (Criteria) this;
        }

        public Criteria andRelationTableIdIn(List<Integer> values) {
            addCriterion("relation_table_id in", values, "relationTableId");
            return (Criteria) this;
        }

        public Criteria andRelationTableIdNotIn(List<Integer> values) {
            addCriterion("relation_table_id not in", values, "relationTableId");
            return (Criteria) this;
        }

        public Criteria andRelationTableIdBetween(Integer value1, Integer value2) {
            addCriterion("relation_table_id between", value1, value2, "relationTableId");
            return (Criteria) this;
        }

        public Criteria andRelationTableIdNotBetween(Integer value1, Integer value2) {
            addCriterion("relation_table_id not between", value1, value2, "relationTableId");
            return (Criteria) this;
        }

        public Criteria andOperateTypeIsNull() {
            addCriterion("operate_type is null");
            return (Criteria) this;
        }

        public Criteria andOperateTypeIsNotNull() {
            addCriterion("operate_type is not null");
            return (Criteria) this;
        }

        public Criteria andOperateTypeEqualTo(Byte value) {
            addCriterion("operate_type =", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeNotEqualTo(Byte value) {
            addCriterion("operate_type <>", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeGreaterThan(Byte value) {
            addCriterion("operate_type >", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("operate_type >=", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeLessThan(Byte value) {
            addCriterion("operate_type <", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeLessThanOrEqualTo(Byte value) {
            addCriterion("operate_type <=", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeIn(List<Byte> values) {
            addCriterion("operate_type in", values, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeNotIn(List<Byte> values) {
            addCriterion("operate_type not in", values, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeBetween(Byte value1, Byte value2) {
            addCriterion("operate_type between", value1, value2, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("operate_type not between", value1, value2, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateApplicatoinIsNull() {
            addCriterion("operate_applicatoin is null");
            return (Criteria) this;
        }

        public Criteria andOperateApplicatoinIsNotNull() {
            addCriterion("operate_applicatoin is not null");
            return (Criteria) this;
        }

        public Criteria andOperateApplicatoinEqualTo(Byte value) {
            addCriterion("operate_applicatoin =", value, "operateApplicatoin");
            return (Criteria) this;
        }

        public Criteria andOperateApplicatoinNotEqualTo(Byte value) {
            addCriterion("operate_applicatoin <>", value, "operateApplicatoin");
            return (Criteria) this;
        }

        public Criteria andOperateApplicatoinGreaterThan(Byte value) {
            addCriterion("operate_applicatoin >", value, "operateApplicatoin");
            return (Criteria) this;
        }

        public Criteria andOperateApplicatoinGreaterThanOrEqualTo(Byte value) {
            addCriterion("operate_applicatoin >=", value, "operateApplicatoin");
            return (Criteria) this;
        }

        public Criteria andOperateApplicatoinLessThan(Byte value) {
            addCriterion("operate_applicatoin <", value, "operateApplicatoin");
            return (Criteria) this;
        }

        public Criteria andOperateApplicatoinLessThanOrEqualTo(Byte value) {
            addCriterion("operate_applicatoin <=", value, "operateApplicatoin");
            return (Criteria) this;
        }

        public Criteria andOperateApplicatoinIn(List<Byte> values) {
            addCriterion("operate_applicatoin in", values, "operateApplicatoin");
            return (Criteria) this;
        }

        public Criteria andOperateApplicatoinNotIn(List<Byte> values) {
            addCriterion("operate_applicatoin not in", values, "operateApplicatoin");
            return (Criteria) this;
        }

        public Criteria andOperateApplicatoinBetween(Byte value1, Byte value2) {
            addCriterion("operate_applicatoin between", value1, value2, "operateApplicatoin");
            return (Criteria) this;
        }

        public Criteria andOperateApplicatoinNotBetween(Byte value1, Byte value2) {
            addCriterion("operate_applicatoin not between", value1, value2, "operateApplicatoin");
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

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
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