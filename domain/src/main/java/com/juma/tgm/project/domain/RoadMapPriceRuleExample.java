package com.juma.tgm.project.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RoadMapPriceRuleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int startOffSet = -1;

    protected int size = -1;

    public RoadMapPriceRuleExample() {
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

        public Criteria andRoadMapPriceRuleIdIsNull() {
            addCriterion("road_map_price_rule_id is null");
            return (Criteria) this;
        }

        public Criteria andRoadMapPriceRuleIdIsNotNull() {
            addCriterion("road_map_price_rule_id is not null");
            return (Criteria) this;
        }

        public Criteria andRoadMapPriceRuleIdEqualTo(Integer value) {
            addCriterion("road_map_price_rule_id =", value, "roadMapPriceRuleId");
            return (Criteria) this;
        }

        public Criteria andRoadMapPriceRuleIdNotEqualTo(Integer value) {
            addCriterion("road_map_price_rule_id <>", value, "roadMapPriceRuleId");
            return (Criteria) this;
        }

        public Criteria andRoadMapPriceRuleIdGreaterThan(Integer value) {
            addCriterion("road_map_price_rule_id >", value, "roadMapPriceRuleId");
            return (Criteria) this;
        }

        public Criteria andRoadMapPriceRuleIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("road_map_price_rule_id >=", value, "roadMapPriceRuleId");
            return (Criteria) this;
        }

        public Criteria andRoadMapPriceRuleIdLessThan(Integer value) {
            addCriterion("road_map_price_rule_id <", value, "roadMapPriceRuleId");
            return (Criteria) this;
        }

        public Criteria andRoadMapPriceRuleIdLessThanOrEqualTo(Integer value) {
            addCriterion("road_map_price_rule_id <=", value, "roadMapPriceRuleId");
            return (Criteria) this;
        }

        public Criteria andRoadMapPriceRuleIdIn(List<Integer> values) {
            addCriterion("road_map_price_rule_id in", values, "roadMapPriceRuleId");
            return (Criteria) this;
        }

        public Criteria andRoadMapPriceRuleIdNotIn(List<Integer> values) {
            addCriterion("road_map_price_rule_id not in", values, "roadMapPriceRuleId");
            return (Criteria) this;
        }

        public Criteria andRoadMapPriceRuleIdBetween(Integer value1, Integer value2) {
            addCriterion("road_map_price_rule_id between", value1, value2, "roadMapPriceRuleId");
            return (Criteria) this;
        }

        public Criteria andRoadMapPriceRuleIdNotBetween(Integer value1, Integer value2) {
            addCriterion("road_map_price_rule_id not between", value1, value2, "roadMapPriceRuleId");
            return (Criteria) this;
        }

        public Criteria andRoadMapIdIsNull() {
            addCriterion("road_map_id is null");
            return (Criteria) this;
        }

        public Criteria andRoadMapIdIsNotNull() {
            addCriterion("road_map_id is not null");
            return (Criteria) this;
        }

        public Criteria andRoadMapIdEqualTo(Integer value) {
            addCriterion("road_map_id =", value, "roadMapId");
            return (Criteria) this;
        }

        public Criteria andRoadMapIdNotEqualTo(Integer value) {
            addCriterion("road_map_id <>", value, "roadMapId");
            return (Criteria) this;
        }

        public Criteria andRoadMapIdGreaterThan(Integer value) {
            addCriterion("road_map_id >", value, "roadMapId");
            return (Criteria) this;
        }

        public Criteria andRoadMapIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("road_map_id >=", value, "roadMapId");
            return (Criteria) this;
        }

        public Criteria andRoadMapIdLessThan(Integer value) {
            addCriterion("road_map_id <", value, "roadMapId");
            return (Criteria) this;
        }

        public Criteria andRoadMapIdLessThanOrEqualTo(Integer value) {
            addCriterion("road_map_id <=", value, "roadMapId");
            return (Criteria) this;
        }

        public Criteria andRoadMapIdIn(List<Integer> values) {
            addCriterion("road_map_id in", values, "roadMapId");
            return (Criteria) this;
        }

        public Criteria andRoadMapIdNotIn(List<Integer> values) {
            addCriterion("road_map_id not in", values, "roadMapId");
            return (Criteria) this;
        }

        public Criteria andRoadMapIdBetween(Integer value1, Integer value2) {
            addCriterion("road_map_id between", value1, value2, "roadMapId");
            return (Criteria) this;
        }

        public Criteria andRoadMapIdNotBetween(Integer value1, Integer value2) {
            addCriterion("road_map_id not between", value1, value2, "roadMapId");
            return (Criteria) this;
        }

        public Criteria andValuationWayIsNull() {
            addCriterion("valuation_way is null");
            return (Criteria) this;
        }

        public Criteria andValuationWayIsNotNull() {
            addCriterion("valuation_way is not null");
            return (Criteria) this;
        }

        public Criteria andValuationWayEqualTo(Byte value) {
            addCriterion("valuation_way =", value, "valuationWay");
            return (Criteria) this;
        }

        public Criteria andValuationWayNotEqualTo(Byte value) {
            addCriterion("valuation_way <>", value, "valuationWay");
            return (Criteria) this;
        }

        public Criteria andValuationWayGreaterThan(Byte value) {
            addCriterion("valuation_way >", value, "valuationWay");
            return (Criteria) this;
        }

        public Criteria andValuationWayGreaterThanOrEqualTo(Byte value) {
            addCriterion("valuation_way >=", value, "valuationWay");
            return (Criteria) this;
        }

        public Criteria andValuationWayLessThan(Byte value) {
            addCriterion("valuation_way <", value, "valuationWay");
            return (Criteria) this;
        }

        public Criteria andValuationWayLessThanOrEqualTo(Byte value) {
            addCriterion("valuation_way <=", value, "valuationWay");
            return (Criteria) this;
        }

        public Criteria andValuationWayIn(List<Byte> values) {
            addCriterion("valuation_way in", values, "valuationWay");
            return (Criteria) this;
        }

        public Criteria andValuationWayNotIn(List<Byte> values) {
            addCriterion("valuation_way not in", values, "valuationWay");
            return (Criteria) this;
        }

        public Criteria andValuationWayBetween(Byte value1, Byte value2) {
            addCriterion("valuation_way between", value1, value2, "valuationWay");
            return (Criteria) this;
        }

        public Criteria andValuationWayNotBetween(Byte value1, Byte value2) {
            addCriterion("valuation_way not between", value1, value2, "valuationWay");
            return (Criteria) this;
        }

        public Criteria andTruckTypeIdIsNull() {
            addCriterion("truck_type_id is null");
            return (Criteria) this;
        }

        public Criteria andTruckTypeIdIsNotNull() {
            addCriterion("truck_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andTruckTypeIdEqualTo(Integer value) {
            addCriterion("truck_type_id =", value, "truckTypeId");
            return (Criteria) this;
        }

        public Criteria andTruckTypeIdNotEqualTo(Integer value) {
            addCriterion("truck_type_id <>", value, "truckTypeId");
            return (Criteria) this;
        }

        public Criteria andTruckTypeIdGreaterThan(Integer value) {
            addCriterion("truck_type_id >", value, "truckTypeId");
            return (Criteria) this;
        }

        public Criteria andTruckTypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("truck_type_id >=", value, "truckTypeId");
            return (Criteria) this;
        }

        public Criteria andTruckTypeIdLessThan(Integer value) {
            addCriterion("truck_type_id <", value, "truckTypeId");
            return (Criteria) this;
        }

        public Criteria andTruckTypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("truck_type_id <=", value, "truckTypeId");
            return (Criteria) this;
        }

        public Criteria andTruckTypeIdIn(List<Integer> values) {
            addCriterion("truck_type_id in", values, "truckTypeId");
            return (Criteria) this;
        }

        public Criteria andTruckTypeIdNotIn(List<Integer> values) {
            addCriterion("truck_type_id not in", values, "truckTypeId");
            return (Criteria) this;
        }

        public Criteria andTruckTypeIdBetween(Integer value1, Integer value2) {
            addCriterion("truck_type_id between", value1, value2, "truckTypeId");
            return (Criteria) this;
        }

        public Criteria andTruckTypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("truck_type_id not between", value1, value2, "truckTypeId");
            return (Criteria) this;
        }

        public Criteria andValuationModelJsonIsNull() {
            addCriterion("valuation_model_json is null");
            return (Criteria) this;
        }

        public Criteria andValuationModelJsonIsNotNull() {
            addCriterion("valuation_model_json is not null");
            return (Criteria) this;
        }

        public Criteria andValuationModelJsonEqualTo(String value) {
            addCriterion("valuation_model_json =", value, "valuationModelJson");
            return (Criteria) this;
        }

        public Criteria andValuationModelJsonNotEqualTo(String value) {
            addCriterion("valuation_model_json <>", value, "valuationModelJson");
            return (Criteria) this;
        }

        public Criteria andValuationModelJsonGreaterThan(String value) {
            addCriterion("valuation_model_json >", value, "valuationModelJson");
            return (Criteria) this;
        }

        public Criteria andValuationModelJsonGreaterThanOrEqualTo(String value) {
            addCriterion("valuation_model_json >=", value, "valuationModelJson");
            return (Criteria) this;
        }

        public Criteria andValuationModelJsonLessThan(String value) {
            addCriterion("valuation_model_json <", value, "valuationModelJson");
            return (Criteria) this;
        }

        public Criteria andValuationModelJsonLessThanOrEqualTo(String value) {
            addCriterion("valuation_model_json <=", value, "valuationModelJson");
            return (Criteria) this;
        }

        public Criteria andValuationModelJsonLike(String value) {
            addCriterion("valuation_model_json like", value, "valuationModelJson");
            return (Criteria) this;
        }

        public Criteria andValuationModelJsonNotLike(String value) {
            addCriterion("valuation_model_json not like", value, "valuationModelJson");
            return (Criteria) this;
        }

        public Criteria andValuationModelJsonIn(List<String> values) {
            addCriterion("valuation_model_json in", values, "valuationModelJson");
            return (Criteria) this;
        }

        public Criteria andValuationModelJsonNotIn(List<String> values) {
            addCriterion("valuation_model_json not in", values, "valuationModelJson");
            return (Criteria) this;
        }

        public Criteria andValuationModelJsonBetween(String value1, String value2) {
            addCriterion("valuation_model_json between", value1, value2, "valuationModelJson");
            return (Criteria) this;
        }

        public Criteria andValuationModelJsonNotBetween(String value1, String value2) {
            addCriterion("valuation_model_json not between", value1, value2, "valuationModelJson");
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