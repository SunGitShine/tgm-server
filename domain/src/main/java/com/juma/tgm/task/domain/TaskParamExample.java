package com.juma.tgm.task.domain;

import java.util.ArrayList;
import java.util.List;

public class TaskParamExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int startOffSet = -1;

    protected int size = -1;

    public TaskParamExample() {
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

    public TaskParamExample orderBy(String ... orderByClauses) {
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

    public TaskParamExample limit(int pageNo, int pageSize) {
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

        public Criteria andParamIdIsNull() {
            addCriterion("param_id is null");
            return (Criteria) this;
        }

        public Criteria andParamIdIsNotNull() {
            addCriterion("param_id is not null");
            return (Criteria) this;
        }

        public Criteria andParamIdEqualTo(Integer value) {
            addCriterion("param_id =", value, "paramId");
            return (Criteria) this;
        }

        public Criteria andParamIdNotEqualTo(Integer value) {
            addCriterion("param_id <>", value, "paramId");
            return (Criteria) this;
        }

        public Criteria andParamIdGreaterThan(Integer value) {
            addCriterion("param_id >", value, "paramId");
            return (Criteria) this;
        }

        public Criteria andParamIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("param_id >=", value, "paramId");
            return (Criteria) this;
        }

        public Criteria andParamIdLessThan(Integer value) {
            addCriterion("param_id <", value, "paramId");
            return (Criteria) this;
        }

        public Criteria andParamIdLessThanOrEqualTo(Integer value) {
            addCriterion("param_id <=", value, "paramId");
            return (Criteria) this;
        }

        public Criteria andParamIdIn(List<Integer> values) {
            addCriterion("param_id in", values, "paramId");
            return (Criteria) this;
        }

        public Criteria andParamIdNotIn(List<Integer> values) {
            addCriterion("param_id not in", values, "paramId");
            return (Criteria) this;
        }

        public Criteria andParamIdBetween(Integer value1, Integer value2) {
            addCriterion("param_id between", value1, value2, "paramId");
            return (Criteria) this;
        }

        public Criteria andParamIdNotBetween(Integer value1, Integer value2) {
            addCriterion("param_id not between", value1, value2, "paramId");
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

        public Criteria andBillPeriodIsNull() {
            addCriterion("bill_period is null");
            return (Criteria) this;
        }

        public Criteria andBillPeriodIsNotNull() {
            addCriterion("bill_period is not null");
            return (Criteria) this;
        }

        public Criteria andBillPeriodEqualTo(Integer value) {
            addCriterion("bill_period =", value, "billPeriod");
            return (Criteria) this;
        }

        public Criteria andBillPeriodNotEqualTo(Integer value) {
            addCriterion("bill_period <>", value, "billPeriod");
            return (Criteria) this;
        }

        public Criteria andBillPeriodGreaterThan(Integer value) {
            addCriterion("bill_period >", value, "billPeriod");
            return (Criteria) this;
        }

        public Criteria andBillPeriodGreaterThanOrEqualTo(Integer value) {
            addCriterion("bill_period >=", value, "billPeriod");
            return (Criteria) this;
        }

        public Criteria andBillPeriodLessThan(Integer value) {
            addCriterion("bill_period <", value, "billPeriod");
            return (Criteria) this;
        }

        public Criteria andBillPeriodLessThanOrEqualTo(Integer value) {
            addCriterion("bill_period <=", value, "billPeriod");
            return (Criteria) this;
        }

        public Criteria andBillPeriodIn(List<Integer> values) {
            addCriterion("bill_period in", values, "billPeriod");
            return (Criteria) this;
        }

        public Criteria andBillPeriodNotIn(List<Integer> values) {
            addCriterion("bill_period not in", values, "billPeriod");
            return (Criteria) this;
        }

        public Criteria andBillPeriodBetween(Integer value1, Integer value2) {
            addCriterion("bill_period between", value1, value2, "billPeriod");
            return (Criteria) this;
        }

        public Criteria andBillPeriodNotBetween(Integer value1, Integer value2) {
            addCriterion("bill_period not between", value1, value2, "billPeriod");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeIsNull() {
            addCriterion("goods_type is null");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeIsNotNull() {
            addCriterion("goods_type is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeEqualTo(String value) {
            addCriterion("goods_type =", value, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeNotEqualTo(String value) {
            addCriterion("goods_type <>", value, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeGreaterThan(String value) {
            addCriterion("goods_type >", value, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeGreaterThanOrEqualTo(String value) {
            addCriterion("goods_type >=", value, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeLessThan(String value) {
            addCriterion("goods_type <", value, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeLessThanOrEqualTo(String value) {
            addCriterion("goods_type <=", value, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("goods_type like", value, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeNotLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("goods_type not like", value, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeLikeList(List<String> values) {
            addCriterion("goods_type like", values, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeIn(List<String> values) {
            addCriterion("goods_type in", values, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeNotIn(List<String> values) {
            addCriterion("goods_type not in", values, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeBetween(String value1, String value2) {
            addCriterion("goods_type between", value1, value2, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeNotBetween(String value1, String value2) {
            addCriterion("goods_type not between", value1, value2, "goodsType");
            return (Criteria) this;
        }

        public Criteria andEstimateMileageRangeIsNull() {
            addCriterion("estimate_mileage_range is null");
            return (Criteria) this;
        }

        public Criteria andEstimateMileageRangeIsNotNull() {
            addCriterion("estimate_mileage_range is not null");
            return (Criteria) this;
        }

        public Criteria andEstimateMileageRangeEqualTo(String value) {
            addCriterion("estimate_mileage_range =", value, "estimateMileageRange");
            return (Criteria) this;
        }

        public Criteria andEstimateMileageRangeNotEqualTo(String value) {
            addCriterion("estimate_mileage_range <>", value, "estimateMileageRange");
            return (Criteria) this;
        }

        public Criteria andEstimateMileageRangeGreaterThan(String value) {
            addCriterion("estimate_mileage_range >", value, "estimateMileageRange");
            return (Criteria) this;
        }

        public Criteria andEstimateMileageRangeGreaterThanOrEqualTo(String value) {
            addCriterion("estimate_mileage_range >=", value, "estimateMileageRange");
            return (Criteria) this;
        }

        public Criteria andEstimateMileageRangeLessThan(String value) {
            addCriterion("estimate_mileage_range <", value, "estimateMileageRange");
            return (Criteria) this;
        }

        public Criteria andEstimateMileageRangeLessThanOrEqualTo(String value) {
            addCriterion("estimate_mileage_range <=", value, "estimateMileageRange");
            return (Criteria) this;
        }

        public Criteria andEstimateMileageRangeLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("estimate_mileage_range like", value, "estimateMileageRange");
            return (Criteria) this;
        }

        public Criteria andEstimateMileageRangeNotLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("estimate_mileage_range not like", value, "estimateMileageRange");
            return (Criteria) this;
        }

        public Criteria andEstimateMileageRangeLikeList(List<String> values) {
            addCriterion("estimate_mileage_range like", values, "estimateMileageRange");
            return (Criteria) this;
        }

        public Criteria andEstimateMileageRangeIn(List<String> values) {
            addCriterion("estimate_mileage_range in", values, "estimateMileageRange");
            return (Criteria) this;
        }

        public Criteria andEstimateMileageRangeNotIn(List<String> values) {
            addCriterion("estimate_mileage_range not in", values, "estimateMileageRange");
            return (Criteria) this;
        }

        public Criteria andEstimateMileageRangeBetween(String value1, String value2) {
            addCriterion("estimate_mileage_range between", value1, value2, "estimateMileageRange");
            return (Criteria) this;
        }

        public Criteria andEstimateMileageRangeNotBetween(String value1, String value2) {
            addCriterion("estimate_mileage_range not between", value1, value2, "estimateMileageRange");
            return (Criteria) this;
        }

        public Criteria andEstimateTimeCostRangeIsNull() {
            addCriterion("estimate_time_cost_range is null");
            return (Criteria) this;
        }

        public Criteria andEstimateTimeCostRangeIsNotNull() {
            addCriterion("estimate_time_cost_range is not null");
            return (Criteria) this;
        }

        public Criteria andEstimateTimeCostRangeEqualTo(String value) {
            addCriterion("estimate_time_cost_range =", value, "estimateTimeCostRange");
            return (Criteria) this;
        }

        public Criteria andEstimateTimeCostRangeNotEqualTo(String value) {
            addCriterion("estimate_time_cost_range <>", value, "estimateTimeCostRange");
            return (Criteria) this;
        }

        public Criteria andEstimateTimeCostRangeGreaterThan(String value) {
            addCriterion("estimate_time_cost_range >", value, "estimateTimeCostRange");
            return (Criteria) this;
        }

        public Criteria andEstimateTimeCostRangeGreaterThanOrEqualTo(String value) {
            addCriterion("estimate_time_cost_range >=", value, "estimateTimeCostRange");
            return (Criteria) this;
        }

        public Criteria andEstimateTimeCostRangeLessThan(String value) {
            addCriterion("estimate_time_cost_range <", value, "estimateTimeCostRange");
            return (Criteria) this;
        }

        public Criteria andEstimateTimeCostRangeLessThanOrEqualTo(String value) {
            addCriterion("estimate_time_cost_range <=", value, "estimateTimeCostRange");
            return (Criteria) this;
        }

        public Criteria andEstimateTimeCostRangeLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("estimate_time_cost_range like", value, "estimateTimeCostRange");
            return (Criteria) this;
        }

        public Criteria andEstimateTimeCostRangeNotLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("estimate_time_cost_range not like", value, "estimateTimeCostRange");
            return (Criteria) this;
        }

        public Criteria andEstimateTimeCostRangeLikeList(List<String> values) {
            addCriterion("estimate_time_cost_range like", values, "estimateTimeCostRange");
            return (Criteria) this;
        }

        public Criteria andEstimateTimeCostRangeIn(List<String> values) {
            addCriterion("estimate_time_cost_range in", values, "estimateTimeCostRange");
            return (Criteria) this;
        }

        public Criteria andEstimateTimeCostRangeNotIn(List<String> values) {
            addCriterion("estimate_time_cost_range not in", values, "estimateTimeCostRange");
            return (Criteria) this;
        }

        public Criteria andEstimateTimeCostRangeBetween(String value1, String value2) {
            addCriterion("estimate_time_cost_range between", value1, value2, "estimateTimeCostRange");
            return (Criteria) this;
        }

        public Criteria andEstimateTimeCostRangeNotBetween(String value1, String value2) {
            addCriterion("estimate_time_cost_range not between", value1, value2, "estimateTimeCostRange");
            return (Criteria) this;
        }

        public Criteria andFunctionIdsIsNull() {
            addCriterion("function_ids is null");
            return (Criteria) this;
        }

        public Criteria andFunctionIdsIsNotNull() {
            addCriterion("function_ids is not null");
            return (Criteria) this;
        }

        public Criteria andFunctionIdsEqualTo(String value) {
            addCriterion("function_ids =", value, "functionIds");
            return (Criteria) this;
        }

        public Criteria andFunctionIdsNotEqualTo(String value) {
            addCriterion("function_ids <>", value, "functionIds");
            return (Criteria) this;
        }

        public Criteria andFunctionIdsGreaterThan(String value) {
            addCriterion("function_ids >", value, "functionIds");
            return (Criteria) this;
        }

        public Criteria andFunctionIdsGreaterThanOrEqualTo(String value) {
            addCriterion("function_ids >=", value, "functionIds");
            return (Criteria) this;
        }

        public Criteria andFunctionIdsLessThan(String value) {
            addCriterion("function_ids <", value, "functionIds");
            return (Criteria) this;
        }

        public Criteria andFunctionIdsLessThanOrEqualTo(String value) {
            addCriterion("function_ids <=", value, "functionIds");
            return (Criteria) this;
        }

        public Criteria andFunctionIdsLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("function_ids like", value, "functionIds");
            return (Criteria) this;
        }

        public Criteria andFunctionIdsNotLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("function_ids not like", value, "functionIds");
            return (Criteria) this;
        }

        public Criteria andFunctionIdsLikeList(List<String> values) {
            addCriterion("function_ids like", values, "functionIds");
            return (Criteria) this;
        }

        public Criteria andFunctionIdsIn(List<String> values) {
            addCriterion("function_ids in", values, "functionIds");
            return (Criteria) this;
        }

        public Criteria andFunctionIdsNotIn(List<String> values) {
            addCriterion("function_ids not in", values, "functionIds");
            return (Criteria) this;
        }

        public Criteria andFunctionIdsBetween(String value1, String value2) {
            addCriterion("function_ids between", value1, value2, "functionIds");
            return (Criteria) this;
        }

        public Criteria andFunctionIdsNotBetween(String value1, String value2) {
            addCriterion("function_ids not between", value1, value2, "functionIds");
            return (Criteria) this;
        }

        public Criteria andRequireMarkIsNull() {
            addCriterion("require_mark is null");
            return (Criteria) this;
        }

        public Criteria andRequireMarkIsNotNull() {
            addCriterion("require_mark is not null");
            return (Criteria) this;
        }

        public Criteria andRequireMarkEqualTo(String value) {
            addCriterion("require_mark =", value, "requireMark");
            return (Criteria) this;
        }

        public Criteria andRequireMarkNotEqualTo(String value) {
            addCriterion("require_mark <>", value, "requireMark");
            return (Criteria) this;
        }

        public Criteria andRequireMarkGreaterThan(String value) {
            addCriterion("require_mark >", value, "requireMark");
            return (Criteria) this;
        }

        public Criteria andRequireMarkGreaterThanOrEqualTo(String value) {
            addCriterion("require_mark >=", value, "requireMark");
            return (Criteria) this;
        }

        public Criteria andRequireMarkLessThan(String value) {
            addCriterion("require_mark <", value, "requireMark");
            return (Criteria) this;
        }

        public Criteria andRequireMarkLessThanOrEqualTo(String value) {
            addCriterion("require_mark <=", value, "requireMark");
            return (Criteria) this;
        }

        public Criteria andRequireMarkLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("require_mark like", value, "requireMark");
            return (Criteria) this;
        }

        public Criteria andRequireMarkNotLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("require_mark not like", value, "requireMark");
            return (Criteria) this;
        }

        public Criteria andRequireMarkLikeList(List<String> values) {
            addCriterion("require_mark like", values, "requireMark");
            return (Criteria) this;
        }

        public Criteria andRequireMarkIn(List<String> values) {
            addCriterion("require_mark in", values, "requireMark");
            return (Criteria) this;
        }

        public Criteria andRequireMarkNotIn(List<String> values) {
            addCriterion("require_mark not in", values, "requireMark");
            return (Criteria) this;
        }

        public Criteria andRequireMarkBetween(String value1, String value2) {
            addCriterion("require_mark between", value1, value2, "requireMark");
            return (Criteria) this;
        }

        public Criteria andRequireMarkNotBetween(String value1, String value2) {
            addCriterion("require_mark not between", value1, value2, "requireMark");
            return (Criteria) this;
        }

        public Criteria andPricingMethodIsNull() {
            addCriterion("pricing_method is null");
            return (Criteria) this;
        }

        public Criteria andPricingMethodIsNotNull() {
            addCriterion("pricing_method is not null");
            return (Criteria) this;
        }

        public Criteria andPricingMethodEqualTo(Integer value) {
            addCriterion("pricing_method =", value, "pricingMethod");
            return (Criteria) this;
        }

        public Criteria andPricingMethodNotEqualTo(Integer value) {
            addCriterion("pricing_method <>", value, "pricingMethod");
            return (Criteria) this;
        }

        public Criteria andPricingMethodGreaterThan(Integer value) {
            addCriterion("pricing_method >", value, "pricingMethod");
            return (Criteria) this;
        }

        public Criteria andPricingMethodGreaterThanOrEqualTo(Integer value) {
            addCriterion("pricing_method >=", value, "pricingMethod");
            return (Criteria) this;
        }

        public Criteria andPricingMethodLessThan(Integer value) {
            addCriterion("pricing_method <", value, "pricingMethod");
            return (Criteria) this;
        }

        public Criteria andPricingMethodLessThanOrEqualTo(Integer value) {
            addCriterion("pricing_method <=", value, "pricingMethod");
            return (Criteria) this;
        }

        public Criteria andPricingMethodIn(List<Integer> values) {
            addCriterion("pricing_method in", values, "pricingMethod");
            return (Criteria) this;
        }

        public Criteria andPricingMethodNotIn(List<Integer> values) {
            addCriterion("pricing_method not in", values, "pricingMethod");
            return (Criteria) this;
        }

        public Criteria andPricingMethodBetween(Integer value1, Integer value2) {
            addCriterion("pricing_method between", value1, value2, "pricingMethod");
            return (Criteria) this;
        }

        public Criteria andPricingMethodNotBetween(Integer value1, Integer value2) {
            addCriterion("pricing_method not between", value1, value2, "pricingMethod");
            return (Criteria) this;
        }

        public Criteria andPricingRuleIsNull() {
            addCriterion("pricing_rule is null");
            return (Criteria) this;
        }

        public Criteria andPricingRuleIsNotNull() {
            addCriterion("pricing_rule is not null");
            return (Criteria) this;
        }

        public Criteria andPricingRuleEqualTo(String value) {
            addCriterion("pricing_rule =", value, "pricingRule");
            return (Criteria) this;
        }

        public Criteria andPricingRuleNotEqualTo(String value) {
            addCriterion("pricing_rule <>", value, "pricingRule");
            return (Criteria) this;
        }

        public Criteria andPricingRuleGreaterThan(String value) {
            addCriterion("pricing_rule >", value, "pricingRule");
            return (Criteria) this;
        }

        public Criteria andPricingRuleGreaterThanOrEqualTo(String value) {
            addCriterion("pricing_rule >=", value, "pricingRule");
            return (Criteria) this;
        }

        public Criteria andPricingRuleLessThan(String value) {
            addCriterion("pricing_rule <", value, "pricingRule");
            return (Criteria) this;
        }

        public Criteria andPricingRuleLessThanOrEqualTo(String value) {
            addCriterion("pricing_rule <=", value, "pricingRule");
            return (Criteria) this;
        }

        public Criteria andPricingRuleLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("pricing_rule like", value, "pricingRule");
            return (Criteria) this;
        }

        public Criteria andPricingRuleNotLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("pricing_rule not like", value, "pricingRule");
            return (Criteria) this;
        }

        public Criteria andPricingRuleLikeList(List<String> values) {
            addCriterion("pricing_rule like", values, "pricingRule");
            return (Criteria) this;
        }

        public Criteria andPricingRuleIn(List<String> values) {
            addCriterion("pricing_rule in", values, "pricingRule");
            return (Criteria) this;
        }

        public Criteria andPricingRuleNotIn(List<String> values) {
            addCriterion("pricing_rule not in", values, "pricingRule");
            return (Criteria) this;
        }

        public Criteria andPricingRuleBetween(String value1, String value2) {
            addCriterion("pricing_rule between", value1, value2, "pricingRule");
            return (Criteria) this;
        }

        public Criteria andPricingRuleNotBetween(String value1, String value2) {
            addCriterion("pricing_rule not between", value1, value2, "pricingRule");
            return (Criteria) this;
        }

        public Criteria andCancelReasonIsNull() {
            addCriterion("cancel_reason is null");
            return (Criteria) this;
        }

        public Criteria andCancelReasonIsNotNull() {
            addCriterion("cancel_reason is not null");
            return (Criteria) this;
        }

        public Criteria andCancelReasonEqualTo(String value) {
            addCriterion("cancel_reason =", value, "cancelReason");
            return (Criteria) this;
        }

        public Criteria andCancelReasonNotEqualTo(String value) {
            addCriterion("cancel_reason <>", value, "cancelReason");
            return (Criteria) this;
        }

        public Criteria andCancelReasonGreaterThan(String value) {
            addCriterion("cancel_reason >", value, "cancelReason");
            return (Criteria) this;
        }

        public Criteria andCancelReasonGreaterThanOrEqualTo(String value) {
            addCriterion("cancel_reason >=", value, "cancelReason");
            return (Criteria) this;
        }

        public Criteria andCancelReasonLessThan(String value) {
            addCriterion("cancel_reason <", value, "cancelReason");
            return (Criteria) this;
        }

        public Criteria andCancelReasonLessThanOrEqualTo(String value) {
            addCriterion("cancel_reason <=", value, "cancelReason");
            return (Criteria) this;
        }

        public Criteria andCancelReasonLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("cancel_reason like", value, "cancelReason");
            return (Criteria) this;
        }

        public Criteria andCancelReasonNotLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("cancel_reason not like", value, "cancelReason");
            return (Criteria) this;
        }

        public Criteria andCancelReasonLikeList(List<String> values) {
            addCriterion("cancel_reason like", values, "cancelReason");
            return (Criteria) this;
        }

        public Criteria andCancelReasonIn(List<String> values) {
            addCriterion("cancel_reason in", values, "cancelReason");
            return (Criteria) this;
        }

        public Criteria andCancelReasonNotIn(List<String> values) {
            addCriterion("cancel_reason not in", values, "cancelReason");
            return (Criteria) this;
        }

        public Criteria andCancelReasonBetween(String value1, String value2) {
            addCriterion("cancel_reason between", value1, value2, "cancelReason");
            return (Criteria) this;
        }

        public Criteria andCancelReasonNotBetween(String value1, String value2) {
            addCriterion("cancel_reason not between", value1, value2, "cancelReason");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        private TaskParamExample example;

        protected Criteria(TaskParamExample example) {
            super();
            this.example = example;
        }

        public TaskParamExample example() {
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