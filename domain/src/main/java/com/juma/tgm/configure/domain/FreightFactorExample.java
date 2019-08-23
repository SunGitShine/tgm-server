package com.juma.tgm.configure.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FreightFactorExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int startOffSet = -1;

    protected int size = -1;

    public FreightFactorExample() {
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

        public Criteria andFreightFactorIdIsNull() {
            addCriterion("freight_factor_id is null");
            return (Criteria) this;
        }

        public Criteria andFreightFactorIdIsNotNull() {
            addCriterion("freight_factor_id is not null");
            return (Criteria) this;
        }

        public Criteria andFreightFactorIdEqualTo(Integer value) {
            addCriterion("freight_factor_id =", value, "freightFactorId");
            return (Criteria) this;
        }

        public Criteria andFreightFactorIdNotEqualTo(Integer value) {
            addCriterion("freight_factor_id <>", value, "freightFactorId");
            return (Criteria) this;
        }

        public Criteria andFreightFactorIdGreaterThan(Integer value) {
            addCriterion("freight_factor_id >", value, "freightFactorId");
            return (Criteria) this;
        }

        public Criteria andFreightFactorIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("freight_factor_id >=", value, "freightFactorId");
            return (Criteria) this;
        }

        public Criteria andFreightFactorIdLessThan(Integer value) {
            addCriterion("freight_factor_id <", value, "freightFactorId");
            return (Criteria) this;
        }

        public Criteria andFreightFactorIdLessThanOrEqualTo(Integer value) {
            addCriterion("freight_factor_id <=", value, "freightFactorId");
            return (Criteria) this;
        }

        public Criteria andFreightFactorIdIn(List<Integer> values) {
            addCriterion("freight_factor_id in", values, "freightFactorId");
            return (Criteria) this;
        }

        public Criteria andFreightFactorIdNotIn(List<Integer> values) {
            addCriterion("freight_factor_id not in", values, "freightFactorId");
            return (Criteria) this;
        }

        public Criteria andFreightFactorIdBetween(Integer value1, Integer value2) {
            addCriterion("freight_factor_id between", value1, value2, "freightFactorId");
            return (Criteria) this;
        }

        public Criteria andFreightFactorIdNotBetween(Integer value1, Integer value2) {
            addCriterion("freight_factor_id not between", value1, value2, "freightFactorId");
            return (Criteria) this;
        }

        public Criteria andLabelNameIsNull() {
            addCriterion("label_name is null");
            return (Criteria) this;
        }

        public Criteria andLabelNameIsNotNull() {
            addCriterion("label_name is not null");
            return (Criteria) this;
        }

        public Criteria andLabelNameEqualTo(String value) {
            addCriterion("label_name =", value, "labelName");
            return (Criteria) this;
        }

        public Criteria andLabelNameNotEqualTo(String value) {
            addCriterion("label_name <>", value, "labelName");
            return (Criteria) this;
        }

        public Criteria andLabelNameGreaterThan(String value) {
            addCriterion("label_name >", value, "labelName");
            return (Criteria) this;
        }

        public Criteria andLabelNameGreaterThanOrEqualTo(String value) {
            addCriterion("label_name >=", value, "labelName");
            return (Criteria) this;
        }

        public Criteria andLabelNameLessThan(String value) {
            addCriterion("label_name <", value, "labelName");
            return (Criteria) this;
        }

        public Criteria andLabelNameLessThanOrEqualTo(String value) {
            addCriterion("label_name <=", value, "labelName");
            return (Criteria) this;
        }

        public Criteria andLabelNameLike(String value) {
            addCriterion("label_name like", value, "labelName");
            return (Criteria) this;
        }

        public Criteria andLabelNameNotLike(String value) {
            addCriterion("label_name not like", value, "labelName");
            return (Criteria) this;
        }

        public Criteria andLabelNameIn(List<String> values) {
            addCriterion("label_name in", values, "labelName");
            return (Criteria) this;
        }

        public Criteria andLabelNameNotIn(List<String> values) {
            addCriterion("label_name not in", values, "labelName");
            return (Criteria) this;
        }

        public Criteria andLabelNameBetween(String value1, String value2) {
            addCriterion("label_name between", value1, value2, "labelName");
            return (Criteria) this;
        }

        public Criteria andLabelNameNotBetween(String value1, String value2) {
            addCriterion("label_name not between", value1, value2, "labelName");
            return (Criteria) this;
        }

        public Criteria andLabelInputNameIsNull() {
            addCriterion("label_input_name is null");
            return (Criteria) this;
        }

        public Criteria andLabelInputNameIsNotNull() {
            addCriterion("label_input_name is not null");
            return (Criteria) this;
        }

        public Criteria andLabelInputNameEqualTo(String value) {
            addCriterion("label_input_name =", value, "labelInputName");
            return (Criteria) this;
        }

        public Criteria andLabelInputNameNotEqualTo(String value) {
            addCriterion("label_input_name <>", value, "labelInputName");
            return (Criteria) this;
        }

        public Criteria andLabelInputNameGreaterThan(String value) {
            addCriterion("label_input_name >", value, "labelInputName");
            return (Criteria) this;
        }

        public Criteria andLabelInputNameGreaterThanOrEqualTo(String value) {
            addCriterion("label_input_name >=", value, "labelInputName");
            return (Criteria) this;
        }

        public Criteria andLabelInputNameLessThan(String value) {
            addCriterion("label_input_name <", value, "labelInputName");
            return (Criteria) this;
        }

        public Criteria andLabelInputNameLessThanOrEqualTo(String value) {
            addCriterion("label_input_name <=", value, "labelInputName");
            return (Criteria) this;
        }

        public Criteria andLabelInputNameLike(String value) {
            addCriterion("label_input_name like", value, "labelInputName");
            return (Criteria) this;
        }

        public Criteria andLabelInputNameNotLike(String value) {
            addCriterion("label_input_name not like", value, "labelInputName");
            return (Criteria) this;
        }

        public Criteria andLabelInputNameIn(List<String> values) {
            addCriterion("label_input_name in", values, "labelInputName");
            return (Criteria) this;
        }

        public Criteria andLabelInputNameNotIn(List<String> values) {
            addCriterion("label_input_name not in", values, "labelInputName");
            return (Criteria) this;
        }

        public Criteria andLabelInputNameBetween(String value1, String value2) {
            addCriterion("label_input_name between", value1, value2, "labelInputName");
            return (Criteria) this;
        }

        public Criteria andLabelInputNameNotBetween(String value1, String value2) {
            addCriterion("label_input_name not between", value1, value2, "labelInputName");
            return (Criteria) this;
        }

        public Criteria andLabelInputTypeIsNull() {
            addCriterion("label_input_type is null");
            return (Criteria) this;
        }

        public Criteria andLabelInputTypeIsNotNull() {
            addCriterion("label_input_type is not null");
            return (Criteria) this;
        }

        public Criteria andLabelInputTypeEqualTo(Integer value) {
            addCriterion("label_input_type =", value, "labelInputType");
            return (Criteria) this;
        }

        public Criteria andLabelInputTypeNotEqualTo(Integer value) {
            addCriterion("label_input_type <>", value, "labelInputType");
            return (Criteria) this;
        }

        public Criteria andLabelInputTypeGreaterThan(Integer value) {
            addCriterion("label_input_type >", value, "labelInputType");
            return (Criteria) this;
        }

        public Criteria andLabelInputTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("label_input_type >=", value, "labelInputType");
            return (Criteria) this;
        }

        public Criteria andLabelInputTypeLessThan(Integer value) {
            addCriterion("label_input_type <", value, "labelInputType");
            return (Criteria) this;
        }

        public Criteria andLabelInputTypeLessThanOrEqualTo(Integer value) {
            addCriterion("label_input_type <=", value, "labelInputType");
            return (Criteria) this;
        }

        public Criteria andLabelInputTypeIn(List<Integer> values) {
            addCriterion("label_input_type in", values, "labelInputType");
            return (Criteria) this;
        }

        public Criteria andLabelInputTypeNotIn(List<Integer> values) {
            addCriterion("label_input_type not in", values, "labelInputType");
            return (Criteria) this;
        }

        public Criteria andLabelInputTypeBetween(Integer value1, Integer value2) {
            addCriterion("label_input_type between", value1, value2, "labelInputType");
            return (Criteria) this;
        }

        public Criteria andLabelInputTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("label_input_type not between", value1, value2, "labelInputType");
            return (Criteria) this;
        }

        public Criteria andLabelValueTypeIsNull() {
            addCriterion("label_value_type is null");
            return (Criteria) this;
        }

        public Criteria andLabelValueTypeIsNotNull() {
            addCriterion("label_value_type is not null");
            return (Criteria) this;
        }

        public Criteria andLabelValueTypeEqualTo(String value) {
            addCriterion("label_value_type =", value, "labelValueType");
            return (Criteria) this;
        }

        public Criteria andLabelValueTypeNotEqualTo(String value) {
            addCriterion("label_value_type <>", value, "labelValueType");
            return (Criteria) this;
        }

        public Criteria andLabelValueTypeGreaterThan(String value) {
            addCriterion("label_value_type >", value, "labelValueType");
            return (Criteria) this;
        }

        public Criteria andLabelValueTypeGreaterThanOrEqualTo(String value) {
            addCriterion("label_value_type >=", value, "labelValueType");
            return (Criteria) this;
        }

        public Criteria andLabelValueTypeLessThan(String value) {
            addCriterion("label_value_type <", value, "labelValueType");
            return (Criteria) this;
        }

        public Criteria andLabelValueTypeLessThanOrEqualTo(String value) {
            addCriterion("label_value_type <=", value, "labelValueType");
            return (Criteria) this;
        }

        public Criteria andLabelValueTypeLike(String value) {
            addCriterion("label_value_type like", value, "labelValueType");
            return (Criteria) this;
        }

        public Criteria andLabelValueTypeNotLike(String value) {
            addCriterion("label_value_type not like", value, "labelValueType");
            return (Criteria) this;
        }

        public Criteria andLabelValueTypeIn(List<String> values) {
            addCriterion("label_value_type in", values, "labelValueType");
            return (Criteria) this;
        }

        public Criteria andLabelValueTypeNotIn(List<String> values) {
            addCriterion("label_value_type not in", values, "labelValueType");
            return (Criteria) this;
        }

        public Criteria andLabelValueTypeBetween(String value1, String value2) {
            addCriterion("label_value_type between", value1, value2, "labelValueType");
            return (Criteria) this;
        }

        public Criteria andLabelValueTypeNotBetween(String value1, String value2) {
            addCriterion("label_value_type not between", value1, value2, "labelValueType");
            return (Criteria) this;
        }

        public Criteria andRequiredIsNull() {
            addCriterion("required is null");
            return (Criteria) this;
        }

        public Criteria andRequiredIsNotNull() {
            addCriterion("required is not null");
            return (Criteria) this;
        }

        public Criteria andRequiredEqualTo(Boolean value) {
            addCriterion("required =", value, "required");
            return (Criteria) this;
        }

        public Criteria andRequiredNotEqualTo(Boolean value) {
            addCriterion("required <>", value, "required");
            return (Criteria) this;
        }

        public Criteria andRequiredGreaterThan(Boolean value) {
            addCriterion("required >", value, "required");
            return (Criteria) this;
        }

        public Criteria andRequiredGreaterThanOrEqualTo(Boolean value) {
            addCriterion("required >=", value, "required");
            return (Criteria) this;
        }

        public Criteria andRequiredLessThan(Boolean value) {
            addCriterion("required <", value, "required");
            return (Criteria) this;
        }

        public Criteria andRequiredLessThanOrEqualTo(Boolean value) {
            addCriterion("required <=", value, "required");
            return (Criteria) this;
        }

        public Criteria andRequiredIn(List<Boolean> values) {
            addCriterion("required in", values, "required");
            return (Criteria) this;
        }

        public Criteria andRequiredNotIn(List<Boolean> values) {
            addCriterion("required not in", values, "required");
            return (Criteria) this;
        }

        public Criteria andRequiredBetween(Boolean value1, Boolean value2) {
            addCriterion("required between", value1, value2, "required");
            return (Criteria) this;
        }

        public Criteria andRequiredNotBetween(Boolean value1, Boolean value2) {
            addCriterion("required not between", value1, value2, "required");
            return (Criteria) this;
        }

        public Criteria andRequiredMinValueIsNull() {
            addCriterion("required_min_value is null");
            return (Criteria) this;
        }

        public Criteria andRequiredMinValueIsNotNull() {
            addCriterion("required_min_value is not null");
            return (Criteria) this;
        }

        public Criteria andRequiredMinValueEqualTo(Integer value) {
            addCriterion("required_min_value =", value, "requiredMinValue");
            return (Criteria) this;
        }

        public Criteria andRequiredMinValueNotEqualTo(Integer value) {
            addCriterion("required_min_value <>", value, "requiredMinValue");
            return (Criteria) this;
        }

        public Criteria andRequiredMinValueGreaterThan(Integer value) {
            addCriterion("required_min_value >", value, "requiredMinValue");
            return (Criteria) this;
        }

        public Criteria andRequiredMinValueGreaterThanOrEqualTo(Integer value) {
            addCriterion("required_min_value >=", value, "requiredMinValue");
            return (Criteria) this;
        }

        public Criteria andRequiredMinValueLessThan(Integer value) {
            addCriterion("required_min_value <", value, "requiredMinValue");
            return (Criteria) this;
        }

        public Criteria andRequiredMinValueLessThanOrEqualTo(Integer value) {
            addCriterion("required_min_value <=", value, "requiredMinValue");
            return (Criteria) this;
        }

        public Criteria andRequiredMinValueIn(List<Integer> values) {
            addCriterion("required_min_value in", values, "requiredMinValue");
            return (Criteria) this;
        }

        public Criteria andRequiredMinValueNotIn(List<Integer> values) {
            addCriterion("required_min_value not in", values, "requiredMinValue");
            return (Criteria) this;
        }

        public Criteria andRequiredMinValueBetween(Integer value1, Integer value2) {
            addCriterion("required_min_value between", value1, value2, "requiredMinValue");
            return (Criteria) this;
        }

        public Criteria andRequiredMinValueNotBetween(Integer value1, Integer value2) {
            addCriterion("required_min_value not between", value1, value2, "requiredMinValue");
            return (Criteria) this;
        }

        public Criteria andRequiredMaxValueIsNull() {
            addCriterion("required_max_value is null");
            return (Criteria) this;
        }

        public Criteria andRequiredMaxValueIsNotNull() {
            addCriterion("required_max_value is not null");
            return (Criteria) this;
        }

        public Criteria andRequiredMaxValueEqualTo(Integer value) {
            addCriterion("required_max_value =", value, "requiredMaxValue");
            return (Criteria) this;
        }

        public Criteria andRequiredMaxValueNotEqualTo(Integer value) {
            addCriterion("required_max_value <>", value, "requiredMaxValue");
            return (Criteria) this;
        }

        public Criteria andRequiredMaxValueGreaterThan(Integer value) {
            addCriterion("required_max_value >", value, "requiredMaxValue");
            return (Criteria) this;
        }

        public Criteria andRequiredMaxValueGreaterThanOrEqualTo(Integer value) {
            addCriterion("required_max_value >=", value, "requiredMaxValue");
            return (Criteria) this;
        }

        public Criteria andRequiredMaxValueLessThan(Integer value) {
            addCriterion("required_max_value <", value, "requiredMaxValue");
            return (Criteria) this;
        }

        public Criteria andRequiredMaxValueLessThanOrEqualTo(Integer value) {
            addCriterion("required_max_value <=", value, "requiredMaxValue");
            return (Criteria) this;
        }

        public Criteria andRequiredMaxValueIn(List<Integer> values) {
            addCriterion("required_max_value in", values, "requiredMaxValue");
            return (Criteria) this;
        }

        public Criteria andRequiredMaxValueNotIn(List<Integer> values) {
            addCriterion("required_max_value not in", values, "requiredMaxValue");
            return (Criteria) this;
        }

        public Criteria andRequiredMaxValueBetween(Integer value1, Integer value2) {
            addCriterion("required_max_value between", value1, value2, "requiredMaxValue");
            return (Criteria) this;
        }

        public Criteria andRequiredMaxValueNotBetween(Integer value1, Integer value2) {
            addCriterion("required_max_value not between", value1, value2, "requiredMaxValue");
            return (Criteria) this;
        }

        public Criteria andShowOrderIsNull() {
            addCriterion("show_order is null");
            return (Criteria) this;
        }

        public Criteria andShowOrderIsNotNull() {
            addCriterion("show_order is not null");
            return (Criteria) this;
        }

        public Criteria andShowOrderEqualTo(Integer value) {
            addCriterion("show_order =", value, "showOrder");
            return (Criteria) this;
        }

        public Criteria andShowOrderNotEqualTo(Integer value) {
            addCriterion("show_order <>", value, "showOrder");
            return (Criteria) this;
        }

        public Criteria andShowOrderGreaterThan(Integer value) {
            addCriterion("show_order >", value, "showOrder");
            return (Criteria) this;
        }

        public Criteria andShowOrderGreaterThanOrEqualTo(Integer value) {
            addCriterion("show_order >=", value, "showOrder");
            return (Criteria) this;
        }

        public Criteria andShowOrderLessThan(Integer value) {
            addCriterion("show_order <", value, "showOrder");
            return (Criteria) this;
        }

        public Criteria andShowOrderLessThanOrEqualTo(Integer value) {
            addCriterion("show_order <=", value, "showOrder");
            return (Criteria) this;
        }

        public Criteria andShowOrderIn(List<Integer> values) {
            addCriterion("show_order in", values, "showOrder");
            return (Criteria) this;
        }

        public Criteria andShowOrderNotIn(List<Integer> values) {
            addCriterion("show_order not in", values, "showOrder");
            return (Criteria) this;
        }

        public Criteria andShowOrderBetween(Integer value1, Integer value2) {
            addCriterion("show_order between", value1, value2, "showOrder");
            return (Criteria) this;
        }

        public Criteria andShowOrderNotBetween(Integer value1, Integer value2) {
            addCriterion("show_order not between", value1, value2, "showOrder");
            return (Criteria) this;
        }

        public Criteria andIsEnableIsNull() {
            addCriterion("is_enable is null");
            return (Criteria) this;
        }

        public Criteria andIsEnableIsNotNull() {
            addCriterion("is_enable is not null");
            return (Criteria) this;
        }

        public Criteria andIsEnableEqualTo(Byte value) {
            addCriterion("is_enable =", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableNotEqualTo(Byte value) {
            addCriterion("is_enable <>", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableGreaterThan(Byte value) {
            addCriterion("is_enable >", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_enable >=", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableLessThan(Byte value) {
            addCriterion("is_enable <", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableLessThanOrEqualTo(Byte value) {
            addCriterion("is_enable <=", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableIn(List<Byte> values) {
            addCriterion("is_enable in", values, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableNotIn(List<Byte> values) {
            addCriterion("is_enable not in", values, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableBetween(Byte value1, Byte value2) {
            addCriterion("is_enable between", value1, value2, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableNotBetween(Byte value1, Byte value2) {
            addCriterion("is_enable not between", value1, value2, "isEnable");
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