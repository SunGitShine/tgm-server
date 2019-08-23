package com.juma.tgm.configure.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServiceConfItemExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int startOffSet = -1;

    protected int size = -1;

    public ServiceConfItemExample() {
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

        public Criteria andServiceConfItemIdIsNull() {
            addCriterion("service_conf_item_id is null");
            return (Criteria) this;
        }

        public Criteria andServiceConfItemIdIsNotNull() {
            addCriterion("service_conf_item_id is not null");
            return (Criteria) this;
        }

        public Criteria andServiceConfItemIdEqualTo(Integer value) {
            addCriterion("service_conf_item_id =", value, "serviceConfItemId");
            return (Criteria) this;
        }

        public Criteria andServiceConfItemIdNotEqualTo(Integer value) {
            addCriterion("service_conf_item_id <>", value, "serviceConfItemId");
            return (Criteria) this;
        }

        public Criteria andServiceConfItemIdGreaterThan(Integer value) {
            addCriterion("service_conf_item_id >", value, "serviceConfItemId");
            return (Criteria) this;
        }

        public Criteria andServiceConfItemIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("service_conf_item_id >=", value, "serviceConfItemId");
            return (Criteria) this;
        }

        public Criteria andServiceConfItemIdLessThan(Integer value) {
            addCriterion("service_conf_item_id <", value, "serviceConfItemId");
            return (Criteria) this;
        }

        public Criteria andServiceConfItemIdLessThanOrEqualTo(Integer value) {
            addCriterion("service_conf_item_id <=", value, "serviceConfItemId");
            return (Criteria) this;
        }

        public Criteria andServiceConfItemIdIn(List<Integer> values) {
            addCriterion("service_conf_item_id in", values, "serviceConfItemId");
            return (Criteria) this;
        }

        public Criteria andServiceConfItemIdNotIn(List<Integer> values) {
            addCriterion("service_conf_item_id not in", values, "serviceConfItemId");
            return (Criteria) this;
        }

        public Criteria andServiceConfItemIdBetween(Integer value1, Integer value2) {
            addCriterion("service_conf_item_id between", value1, value2, "serviceConfItemId");
            return (Criteria) this;
        }

        public Criteria andServiceConfItemIdNotBetween(Integer value1, Integer value2) {
            addCriterion("service_conf_item_id not between", value1, value2, "serviceConfItemId");
            return (Criteria) this;
        }

        public Criteria andServiceConfIdIsNull() {
            addCriterion("service_conf_id is null");
            return (Criteria) this;
        }

        public Criteria andServiceConfIdIsNotNull() {
            addCriterion("service_conf_id is not null");
            return (Criteria) this;
        }

        public Criteria andServiceConfIdEqualTo(Integer value) {
            addCriterion("service_conf_id =", value, "serviceConfId");
            return (Criteria) this;
        }

        public Criteria andServiceConfIdNotEqualTo(Integer value) {
            addCriterion("service_conf_id <>", value, "serviceConfId");
            return (Criteria) this;
        }

        public Criteria andServiceConfIdGreaterThan(Integer value) {
            addCriterion("service_conf_id >", value, "serviceConfId");
            return (Criteria) this;
        }

        public Criteria andServiceConfIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("service_conf_id >=", value, "serviceConfId");
            return (Criteria) this;
        }

        public Criteria andServiceConfIdLessThan(Integer value) {
            addCriterion("service_conf_id <", value, "serviceConfId");
            return (Criteria) this;
        }

        public Criteria andServiceConfIdLessThanOrEqualTo(Integer value) {
            addCriterion("service_conf_id <=", value, "serviceConfId");
            return (Criteria) this;
        }

        public Criteria andServiceConfIdIn(List<Integer> values) {
            addCriterion("service_conf_id in", values, "serviceConfId");
            return (Criteria) this;
        }

        public Criteria andServiceConfIdNotIn(List<Integer> values) {
            addCriterion("service_conf_id not in", values, "serviceConfId");
            return (Criteria) this;
        }

        public Criteria andServiceConfIdBetween(Integer value1, Integer value2) {
            addCriterion("service_conf_id between", value1, value2, "serviceConfId");
            return (Criteria) this;
        }

        public Criteria andServiceConfIdNotBetween(Integer value1, Integer value2) {
            addCriterion("service_conf_id not between", value1, value2, "serviceConfId");
            return (Criteria) this;
        }

        public Criteria andFenceIdIsNull() {
            addCriterion("fence_id is null");
            return (Criteria) this;
        }

        public Criteria andFenceIdIsNotNull() {
            addCriterion("fence_id is not null");
            return (Criteria) this;
        }

        public Criteria andFenceIdEqualTo(Integer value) {
            addCriterion("fence_id =", value, "fenceId");
            return (Criteria) this;
        }

        public Criteria andFenceIdNotEqualTo(Integer value) {
            addCriterion("fence_id <>", value, "fenceId");
            return (Criteria) this;
        }

        public Criteria andFenceIdGreaterThan(Integer value) {
            addCriterion("fence_id >", value, "fenceId");
            return (Criteria) this;
        }

        public Criteria andFenceIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("fence_id >=", value, "fenceId");
            return (Criteria) this;
        }

        public Criteria andFenceIdLessThan(Integer value) {
            addCriterion("fence_id <", value, "fenceId");
            return (Criteria) this;
        }

        public Criteria andFenceIdLessThanOrEqualTo(Integer value) {
            addCriterion("fence_id <=", value, "fenceId");
            return (Criteria) this;
        }

        public Criteria andFenceIdIn(List<Integer> values) {
            addCriterion("fence_id in", values, "fenceId");
            return (Criteria) this;
        }

        public Criteria andFenceIdNotIn(List<Integer> values) {
            addCriterion("fence_id not in", values, "fenceId");
            return (Criteria) this;
        }

        public Criteria andFenceIdBetween(Integer value1, Integer value2) {
            addCriterion("fence_id between", value1, value2, "fenceId");
            return (Criteria) this;
        }

        public Criteria andFenceIdNotBetween(Integer value1, Integer value2) {
            addCriterion("fence_id not between", value1, value2, "fenceId");
            return (Criteria) this;
        }

        public Criteria andFenceNameIsNull() {
            addCriterion("fence_name is null");
            return (Criteria) this;
        }

        public Criteria andFenceNameIsNotNull() {
            addCriterion("fence_name is not null");
            return (Criteria) this;
        }

        public Criteria andFenceNameEqualTo(String value) {
            addCriterion("fence_name =", value, "fenceName");
            return (Criteria) this;
        }

        public Criteria andFenceNameNotEqualTo(String value) {
            addCriterion("fence_name <>", value, "fenceName");
            return (Criteria) this;
        }

        public Criteria andFenceNameGreaterThan(String value) {
            addCriterion("fence_name >", value, "fenceName");
            return (Criteria) this;
        }

        public Criteria andFenceNameGreaterThanOrEqualTo(String value) {
            addCriterion("fence_name >=", value, "fenceName");
            return (Criteria) this;
        }

        public Criteria andFenceNameLessThan(String value) {
            addCriterion("fence_name <", value, "fenceName");
            return (Criteria) this;
        }

        public Criteria andFenceNameLessThanOrEqualTo(String value) {
            addCriterion("fence_name <=", value, "fenceName");
            return (Criteria) this;
        }

        public Criteria andFenceNameLike(String value) {
            addCriterion("fence_name like", value, "fenceName");
            return (Criteria) this;
        }

        public Criteria andFenceNameNotLike(String value) {
            addCriterion("fence_name not like", value, "fenceName");
            return (Criteria) this;
        }

        public Criteria andFenceNameIn(List<String> values) {
            addCriterion("fence_name in", values, "fenceName");
            return (Criteria) this;
        }

        public Criteria andFenceNameNotIn(List<String> values) {
            addCriterion("fence_name not in", values, "fenceName");
            return (Criteria) this;
        }

        public Criteria andFenceNameBetween(String value1, String value2) {
            addCriterion("fence_name between", value1, value2, "fenceName");
            return (Criteria) this;
        }

        public Criteria andFenceNameNotBetween(String value1, String value2) {
            addCriterion("fence_name not between", value1, value2, "fenceName");
            return (Criteria) this;
        }

        public Criteria andFenceTypeIsNull() {
            addCriterion("fence_type is null");
            return (Criteria) this;
        }

        public Criteria andFenceTypeIsNotNull() {
            addCriterion("fence_type is not null");
            return (Criteria) this;
        }

        public Criteria andFenceTypeEqualTo(Integer value) {
            addCriterion("fence_type =", value, "fenceType");
            return (Criteria) this;
        }

        public Criteria andFenceTypeNotEqualTo(Integer value) {
            addCriterion("fence_type <>", value, "fenceType");
            return (Criteria) this;
        }

        public Criteria andFenceTypeGreaterThan(Integer value) {
            addCriterion("fence_type >", value, "fenceType");
            return (Criteria) this;
        }

        public Criteria andFenceTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("fence_type >=", value, "fenceType");
            return (Criteria) this;
        }

        public Criteria andFenceTypeLessThan(Integer value) {
            addCriterion("fence_type <", value, "fenceType");
            return (Criteria) this;
        }

        public Criteria andFenceTypeLessThanOrEqualTo(Integer value) {
            addCriterion("fence_type <=", value, "fenceType");
            return (Criteria) this;
        }

        public Criteria andFenceTypeIn(List<Integer> values) {
            addCriterion("fence_type in", values, "fenceType");
            return (Criteria) this;
        }

        public Criteria andFenceTypeNotIn(List<Integer> values) {
            addCriterion("fence_type not in", values, "fenceType");
            return (Criteria) this;
        }

        public Criteria andFenceTypeBetween(Integer value1, Integer value2) {
            addCriterion("fence_type between", value1, value2, "fenceType");
            return (Criteria) this;
        }

        public Criteria andFenceTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("fence_type not between", value1, value2, "fenceType");
            return (Criteria) this;
        }

        public Criteria andFenceAddressIsNull() {
            addCriterion("fence_address is null");
            return (Criteria) this;
        }

        public Criteria andFenceAddressIsNotNull() {
            addCriterion("fence_address is not null");
            return (Criteria) this;
        }

        public Criteria andFenceAddressEqualTo(String value) {
            addCriterion("fence_address =", value, "fenceAddress");
            return (Criteria) this;
        }

        public Criteria andFenceAddressNotEqualTo(String value) {
            addCriterion("fence_address <>", value, "fenceAddress");
            return (Criteria) this;
        }

        public Criteria andFenceAddressGreaterThan(String value) {
            addCriterion("fence_address >", value, "fenceAddress");
            return (Criteria) this;
        }

        public Criteria andFenceAddressGreaterThanOrEqualTo(String value) {
            addCriterion("fence_address >=", value, "fenceAddress");
            return (Criteria) this;
        }

        public Criteria andFenceAddressLessThan(String value) {
            addCriterion("fence_address <", value, "fenceAddress");
            return (Criteria) this;
        }

        public Criteria andFenceAddressLessThanOrEqualTo(String value) {
            addCriterion("fence_address <=", value, "fenceAddress");
            return (Criteria) this;
        }

        public Criteria andFenceAddressIn(List<String> values) {
            addCriterion("fence_address in", values, "fenceAddress");
            return (Criteria) this;
        }

        public Criteria andFenceAddressNotIn(List<String> values) {
            addCriterion("fence_address not in", values, "fenceAddress");
            return (Criteria) this;
        }

        public Criteria andFenceAddressBetween(String value1, String value2) {
            addCriterion("fence_address between", value1, value2, "fenceAddress");
            return (Criteria) this;
        }

        public Criteria andFenceAddressNotBetween(String value1, String value2) {
            addCriterion("fence_address not between", value1, value2, "fenceAddress");
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