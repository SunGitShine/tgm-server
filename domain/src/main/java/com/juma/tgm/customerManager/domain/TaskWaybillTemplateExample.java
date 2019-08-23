package com.juma.tgm.customerManager.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskWaybillTemplateExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int startOffSet = -1;

    protected int size = -1;

    public TaskWaybillTemplateExample() {
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

        public Criteria andCustomerManagerIdIsNull() {
            addCriterion("customer_manager_id is null");
            return (Criteria) this;
        }

        public Criteria andCustomerManagerIdIsNotNull() {
            addCriterion("customer_manager_id is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerManagerIdEqualTo(Integer value) {
            addCriterion("customer_manager_id =", value, "customerManagerId");
            return (Criteria) this;
        }

        public Criteria andCustomerManagerIdNotEqualTo(Integer value) {
            addCriterion("customer_manager_id <>", value, "customerManagerId");
            return (Criteria) this;
        }

        public Criteria andCustomerManagerIdGreaterThan(Integer value) {
            addCriterion("customer_manager_id >", value, "customerManagerId");
            return (Criteria) this;
        }

        public Criteria andCustomerManagerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("customer_manager_id >=", value, "customerManagerId");
            return (Criteria) this;
        }

        public Criteria andCustomerManagerIdLessThan(Integer value) {
            addCriterion("customer_manager_id <", value, "customerManagerId");
            return (Criteria) this;
        }

        public Criteria andCustomerManagerIdLessThanOrEqualTo(Integer value) {
            addCriterion("customer_manager_id <=", value, "customerManagerId");
            return (Criteria) this;
        }

        public Criteria andCustomerManagerIdIn(List<Integer> values) {
            addCriterion("customer_manager_id in", values, "customerManagerId");
            return (Criteria) this;
        }

        public Criteria andCustomerManagerIdNotIn(List<Integer> values) {
            addCriterion("customer_manager_id not in", values, "customerManagerId");
            return (Criteria) this;
        }

        public Criteria andCustomerManagerIdBetween(Integer value1, Integer value2) {
            addCriterion("customer_manager_id between", value1, value2, "customerManagerId");
            return (Criteria) this;
        }

        public Criteria andCustomerManagerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("customer_manager_id not between", value1, value2, "customerManagerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdIsNull() {
            addCriterion("customer_id is null");
            return (Criteria) this;
        }

        public Criteria andCustomerIdIsNotNull() {
            addCriterion("customer_id is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerIdEqualTo(Integer value) {
            addCriterion("customer_id =", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotEqualTo(Integer value) {
            addCriterion("customer_id <>", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdGreaterThan(Integer value) {
            addCriterion("customer_id >", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("customer_id >=", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdLessThan(Integer value) {
            addCriterion("customer_id <", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdLessThanOrEqualTo(Integer value) {
            addCriterion("customer_id <=", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdIn(List<Integer> values) {
            addCriterion("customer_id in", values, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotIn(List<Integer> values) {
            addCriterion("customer_id not in", values, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdBetween(Integer value1, Integer value2) {
            addCriterion("customer_id between", value1, value2, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("customer_id not between", value1, value2, "customerId");
            return (Criteria) this;
        }

        public Criteria andTruckCustomerIdIsNull() {
            addCriterion("truck_customer_id is null");
            return (Criteria) this;
        }

        public Criteria andTruckCustomerIdIsNotNull() {
            addCriterion("truck_customer_id is not null");
            return (Criteria) this;
        }

        public Criteria andTruckCustomerIdEqualTo(Integer value) {
            addCriterion("truck_customer_id =", value, "truckCustomerId");
            return (Criteria) this;
        }

        public Criteria andTruckCustomerIdNotEqualTo(Integer value) {
            addCriterion("truck_customer_id <>", value, "truckCustomerId");
            return (Criteria) this;
        }

        public Criteria andTruckCustomerIdGreaterThan(Integer value) {
            addCriterion("truck_customer_id >", value, "truckCustomerId");
            return (Criteria) this;
        }

        public Criteria andTruckCustomerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("truck_customer_id >=", value, "truckCustomerId");
            return (Criteria) this;
        }

        public Criteria andTruckCustomerIdLessThan(Integer value) {
            addCriterion("truck_customer_id <", value, "truckCustomerId");
            return (Criteria) this;
        }

        public Criteria andTruckCustomerIdLessThanOrEqualTo(Integer value) {
            addCriterion("truck_customer_id <=", value, "truckCustomerId");
            return (Criteria) this;
        }

        public Criteria andTruckCustomerIdIn(List<Integer> values) {
            addCriterion("truck_customer_id in", values, "truckCustomerId");
            return (Criteria) this;
        }

        public Criteria andTruckCustomerIdNotIn(List<Integer> values) {
            addCriterion("truck_customer_id not in", values, "truckCustomerId");
            return (Criteria) this;
        }

        public Criteria andTruckCustomerIdBetween(Integer value1, Integer value2) {
            addCriterion("truck_customer_id between", value1, value2, "truckCustomerId");
            return (Criteria) this;
        }

        public Criteria andTruckCustomerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("truck_customer_id not between", value1, value2, "truckCustomerId");
            return (Criteria) this;
        }

        public Criteria andBusinessBranchIsNull() {
            addCriterion("business_branch is null");
            return (Criteria) this;
        }

        public Criteria andBusinessBranchIsNotNull() {
            addCriterion("business_branch is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessBranchEqualTo(Byte value) {
            addCriterion("business_branch =", value, "businessBranch");
            return (Criteria) this;
        }

        public Criteria andBusinessBranchNotEqualTo(Byte value) {
            addCriterion("business_branch <>", value, "businessBranch");
            return (Criteria) this;
        }

        public Criteria andBusinessBranchGreaterThan(Byte value) {
            addCriterion("business_branch >", value, "businessBranch");
            return (Criteria) this;
        }

        public Criteria andBusinessBranchGreaterThanOrEqualTo(Byte value) {
            addCriterion("business_branch >=", value, "businessBranch");
            return (Criteria) this;
        }

        public Criteria andBusinessBranchLessThan(Byte value) {
            addCriterion("business_branch <", value, "businessBranch");
            return (Criteria) this;
        }

        public Criteria andBusinessBranchLessThanOrEqualTo(Byte value) {
            addCriterion("business_branch <=", value, "businessBranch");
            return (Criteria) this;
        }

        public Criteria andBusinessBranchIn(List<Byte> values) {
            addCriterion("business_branch in", values, "businessBranch");
            return (Criteria) this;
        }

        public Criteria andBusinessBranchNotIn(List<Byte> values) {
            addCriterion("business_branch not in", values, "businessBranch");
            return (Criteria) this;
        }

        public Criteria andBusinessBranchBetween(Byte value1, Byte value2) {
            addCriterion("business_branch between", value1, value2, "businessBranch");
            return (Criteria) this;
        }

        public Criteria andBusinessBranchNotBetween(Byte value1, Byte value2) {
            addCriterion("business_branch not between", value1, value2, "businessBranch");
            return (Criteria) this;
        }

        public Criteria andRequiredMinTemperatureIsNull() {
            addCriterion("required_min_temperature is null");
            return (Criteria) this;
        }

        public Criteria andRequiredMinTemperatureIsNotNull() {
            addCriterion("required_min_temperature is not null");
            return (Criteria) this;
        }

        public Criteria andRequiredMinTemperatureEqualTo(Float value) {
            addCriterion("required_min_temperature =", value, "requiredMinTemperature");
            return (Criteria) this;
        }

        public Criteria andRequiredMinTemperatureNotEqualTo(Float value) {
            addCriterion("required_min_temperature <>", value, "requiredMinTemperature");
            return (Criteria) this;
        }

        public Criteria andRequiredMinTemperatureGreaterThan(Float value) {
            addCriterion("required_min_temperature >", value, "requiredMinTemperature");
            return (Criteria) this;
        }

        public Criteria andRequiredMinTemperatureGreaterThanOrEqualTo(Float value) {
            addCriterion("required_min_temperature >=", value, "requiredMinTemperature");
            return (Criteria) this;
        }

        public Criteria andRequiredMinTemperatureLessThan(Float value) {
            addCriterion("required_min_temperature <", value, "requiredMinTemperature");
            return (Criteria) this;
        }

        public Criteria andRequiredMinTemperatureLessThanOrEqualTo(Float value) {
            addCriterion("required_min_temperature <=", value, "requiredMinTemperature");
            return (Criteria) this;
        }

        public Criteria andRequiredMinTemperatureIn(List<Float> values) {
            addCriterion("required_min_temperature in", values, "requiredMinTemperature");
            return (Criteria) this;
        }

        public Criteria andRequiredMinTemperatureNotIn(List<Float> values) {
            addCriterion("required_min_temperature not in", values, "requiredMinTemperature");
            return (Criteria) this;
        }

        public Criteria andRequiredMinTemperatureBetween(Float value1, Float value2) {
            addCriterion("required_min_temperature between", value1, value2, "requiredMinTemperature");
            return (Criteria) this;
        }

        public Criteria andRequiredMinTemperatureNotBetween(Float value1, Float value2) {
            addCriterion("required_min_temperature not between", value1, value2, "requiredMinTemperature");
            return (Criteria) this;
        }

        public Criteria andRequiredMaxTemperatureIsNull() {
            addCriterion("required_max_temperature is null");
            return (Criteria) this;
        }

        public Criteria andRequiredMaxTemperatureIsNotNull() {
            addCriterion("required_max_temperature is not null");
            return (Criteria) this;
        }

        public Criteria andRequiredMaxTemperatureEqualTo(Float value) {
            addCriterion("required_max_temperature =", value, "requiredMaxTemperature");
            return (Criteria) this;
        }

        public Criteria andRequiredMaxTemperatureNotEqualTo(Float value) {
            addCriterion("required_max_temperature <>", value, "requiredMaxTemperature");
            return (Criteria) this;
        }

        public Criteria andRequiredMaxTemperatureGreaterThan(Float value) {
            addCriterion("required_max_temperature >", value, "requiredMaxTemperature");
            return (Criteria) this;
        }

        public Criteria andRequiredMaxTemperatureGreaterThanOrEqualTo(Float value) {
            addCriterion("required_max_temperature >=", value, "requiredMaxTemperature");
            return (Criteria) this;
        }

        public Criteria andRequiredMaxTemperatureLessThan(Float value) {
            addCriterion("required_max_temperature <", value, "requiredMaxTemperature");
            return (Criteria) this;
        }

        public Criteria andRequiredMaxTemperatureLessThanOrEqualTo(Float value) {
            addCriterion("required_max_temperature <=", value, "requiredMaxTemperature");
            return (Criteria) this;
        }

        public Criteria andRequiredMaxTemperatureIn(List<Float> values) {
            addCriterion("required_max_temperature in", values, "requiredMaxTemperature");
            return (Criteria) this;
        }

        public Criteria andRequiredMaxTemperatureNotIn(List<Float> values) {
            addCriterion("required_max_temperature not in", values, "requiredMaxTemperature");
            return (Criteria) this;
        }

        public Criteria andRequiredMaxTemperatureBetween(Float value1, Float value2) {
            addCriterion("required_max_temperature between", value1, value2, "requiredMaxTemperature");
            return (Criteria) this;
        }

        public Criteria andRequiredMaxTemperatureNotBetween(Float value1, Float value2) {
            addCriterion("required_max_temperature not between", value1, value2, "requiredMaxTemperature");
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

        public Criteria andEstimateFreightIsNull() {
            addCriterion("estimate_freight is null");
            return (Criteria) this;
        }

        public Criteria andEstimateFreightIsNotNull() {
            addCriterion("estimate_freight is not null");
            return (Criteria) this;
        }

        public Criteria andEstimateFreightEqualTo(BigDecimal value) {
            addCriterion("estimate_freight =", value, "estimateFreight");
            return (Criteria) this;
        }

        public Criteria andEstimateFreightNotEqualTo(BigDecimal value) {
            addCriterion("estimate_freight <>", value, "estimateFreight");
            return (Criteria) this;
        }

        public Criteria andEstimateFreightGreaterThan(BigDecimal value) {
            addCriterion("estimate_freight >", value, "estimateFreight");
            return (Criteria) this;
        }

        public Criteria andEstimateFreightGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("estimate_freight >=", value, "estimateFreight");
            return (Criteria) this;
        }

        public Criteria andEstimateFreightLessThan(BigDecimal value) {
            addCriterion("estimate_freight <", value, "estimateFreight");
            return (Criteria) this;
        }

        public Criteria andEstimateFreightLessThanOrEqualTo(BigDecimal value) {
            addCriterion("estimate_freight <=", value, "estimateFreight");
            return (Criteria) this;
        }

        public Criteria andEstimateFreightIn(List<BigDecimal> values) {
            addCriterion("estimate_freight in", values, "estimateFreight");
            return (Criteria) this;
        }

        public Criteria andEstimateFreightNotIn(List<BigDecimal> values) {
            addCriterion("estimate_freight not in", values, "estimateFreight");
            return (Criteria) this;
        }

        public Criteria andEstimateFreightBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("estimate_freight between", value1, value2, "estimateFreight");
            return (Criteria) this;
        }

        public Criteria andEstimateFreightNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("estimate_freight not between", value1, value2, "estimateFreight");
            return (Criteria) this;
        }

        public Criteria andShow4DriverFreightIsNull() {
            addCriterion("show4_driver_freight is null");
            return (Criteria) this;
        }

        public Criteria andShow4DriverFreightIsNotNull() {
            addCriterion("show4_driver_freight is not null");
            return (Criteria) this;
        }

        public Criteria andShow4DriverFreightEqualTo(BigDecimal value) {
            addCriterion("show4_driver_freight =", value, "show4DriverFreight");
            return (Criteria) this;
        }

        public Criteria andShow4DriverFreightNotEqualTo(BigDecimal value) {
            addCriterion("show4_driver_freight <>", value, "show4DriverFreight");
            return (Criteria) this;
        }

        public Criteria andShow4DriverFreightGreaterThan(BigDecimal value) {
            addCriterion("show4_driver_freight >", value, "show4DriverFreight");
            return (Criteria) this;
        }

        public Criteria andShow4DriverFreightGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("show4_driver_freight >=", value, "show4DriverFreight");
            return (Criteria) this;
        }

        public Criteria andShow4DriverFreightLessThan(BigDecimal value) {
            addCriterion("show4_driver_freight <", value, "show4DriverFreight");
            return (Criteria) this;
        }

        public Criteria andShow4DriverFreightLessThanOrEqualTo(BigDecimal value) {
            addCriterion("show4_driver_freight <=", value, "show4DriverFreight");
            return (Criteria) this;
        }

        public Criteria andShow4DriverFreightIn(List<BigDecimal> values) {
            addCriterion("show4_driver_freight in", values, "show4DriverFreight");
            return (Criteria) this;
        }

        public Criteria andShow4DriverFreightNotIn(List<BigDecimal> values) {
            addCriterion("show4_driver_freight not in", values, "show4DriverFreight");
            return (Criteria) this;
        }

        public Criteria andShow4DriverFreightBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("show4_driver_freight between", value1, value2, "show4DriverFreight");
            return (Criteria) this;
        }

        public Criteria andShow4DriverFreightNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("show4_driver_freight not between", value1, value2, "show4DriverFreight");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimePointIsNull() {
            addCriterion("delivery_time_point is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimePointIsNotNull() {
            addCriterion("delivery_time_point is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimePointEqualTo(String value) {
            addCriterion("delivery_time_point =", value, "deliveryTimePoint");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimePointNotEqualTo(String value) {
            addCriterion("delivery_time_point <>", value, "deliveryTimePoint");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimePointGreaterThan(String value) {
            addCriterion("delivery_time_point >", value, "deliveryTimePoint");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimePointGreaterThanOrEqualTo(String value) {
            addCriterion("delivery_time_point >=", value, "deliveryTimePoint");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimePointLessThan(String value) {
            addCriterion("delivery_time_point <", value, "deliveryTimePoint");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimePointLessThanOrEqualTo(String value) {
            addCriterion("delivery_time_point <=", value, "deliveryTimePoint");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimePointLike(String value) {
            addCriterion("delivery_time_point like", value, "deliveryTimePoint");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimePointNotLike(String value) {
            addCriterion("delivery_time_point not like", value, "deliveryTimePoint");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimePointIn(List<String> values) {
            addCriterion("delivery_time_point in", values, "deliveryTimePoint");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimePointNotIn(List<String> values) {
            addCriterion("delivery_time_point not in", values, "deliveryTimePoint");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimePointBetween(String value1, String value2) {
            addCriterion("delivery_time_point between", value1, value2, "deliveryTimePoint");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimePointNotBetween(String value1, String value2) {
            addCriterion("delivery_time_point not between", value1, value2, "deliveryTimePoint");
            return (Criteria) this;
        }

        public Criteria andFinishTimePointIsNull() {
            addCriterion("finish_time_point is null");
            return (Criteria) this;
        }

        public Criteria andFinishTimePointIsNotNull() {
            addCriterion("finish_time_point is not null");
            return (Criteria) this;
        }

        public Criteria andFinishTimePointEqualTo(Integer value) {
            addCriterion("finish_time_point =", value, "finishTimePoint");
            return (Criteria) this;
        }

        public Criteria andFinishTimePointNotEqualTo(Integer value) {
            addCriterion("finish_time_point <>", value, "finishTimePoint");
            return (Criteria) this;
        }

        public Criteria andFinishTimePointGreaterThan(Integer value) {
            addCriterion("finish_time_point >", value, "finishTimePoint");
            return (Criteria) this;
        }

        public Criteria andFinishTimePointGreaterThanOrEqualTo(Integer value) {
            addCriterion("finish_time_point >=", value, "finishTimePoint");
            return (Criteria) this;
        }

        public Criteria andFinishTimePointLessThan(Integer value) {
            addCriterion("finish_time_point <", value, "finishTimePoint");
            return (Criteria) this;
        }

        public Criteria andFinishTimePointLessThanOrEqualTo(Integer value) {
            addCriterion("finish_time_point <=", value, "finishTimePoint");
            return (Criteria) this;
        }

        public Criteria andFinishTimePointIn(List<Integer> values) {
            addCriterion("finish_time_point in", values, "finishTimePoint");
            return (Criteria) this;
        }

        public Criteria andFinishTimePointNotIn(List<Integer> values) {
            addCriterion("finish_time_point not in", values, "finishTimePoint");
            return (Criteria) this;
        }

        public Criteria andFinishTimePointBetween(Integer value1, Integer value2) {
            addCriterion("finish_time_point between", value1, value2, "finishTimePoint");
            return (Criteria) this;
        }

        public Criteria andFinishTimePointNotBetween(Integer value1, Integer value2) {
            addCriterion("finish_time_point not between", value1, value2, "finishTimePoint");
            return (Criteria) this;
        }

        public Criteria andReceiptTypeIsNull() {
            addCriterion("receipt_type is null");
            return (Criteria) this;
        }

        public Criteria andReceiptTypeIsNotNull() {
            addCriterion("receipt_type is not null");
            return (Criteria) this;
        }

        public Criteria andReceiptTypeEqualTo(Byte value) {
            addCriterion("receipt_type =", value, "receiptType");
            return (Criteria) this;
        }

        public Criteria andReceiptTypeNotEqualTo(Byte value) {
            addCriterion("receipt_type <>", value, "receiptType");
            return (Criteria) this;
        }

        public Criteria andReceiptTypeGreaterThan(Byte value) {
            addCriterion("receipt_type >", value, "receiptType");
            return (Criteria) this;
        }

        public Criteria andReceiptTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("receipt_type >=", value, "receiptType");
            return (Criteria) this;
        }

        public Criteria andReceiptTypeLessThan(Byte value) {
            addCriterion("receipt_type <", value, "receiptType");
            return (Criteria) this;
        }

        public Criteria andReceiptTypeLessThanOrEqualTo(Byte value) {
            addCriterion("receipt_type <=", value, "receiptType");
            return (Criteria) this;
        }

        public Criteria andReceiptTypeIn(List<Byte> values) {
            addCriterion("receipt_type in", values, "receiptType");
            return (Criteria) this;
        }

        public Criteria andReceiptTypeNotIn(List<Byte> values) {
            addCriterion("receipt_type not in", values, "receiptType");
            return (Criteria) this;
        }

        public Criteria andReceiptTypeBetween(Byte value1, Byte value2) {
            addCriterion("receipt_type between", value1, value2, "receiptType");
            return (Criteria) this;
        }

        public Criteria andReceiptTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("receipt_type not between", value1, value2, "receiptType");
            return (Criteria) this;
        }

        public Criteria andRequireJsonIsNull() {
            addCriterion("require_json is null");
            return (Criteria) this;
        }

        public Criteria andRequireJsonIsNotNull() {
            addCriterion("require_json is not null");
            return (Criteria) this;
        }

        public Criteria andRequireJsonEqualTo(String value) {
            addCriterion("require_json =", value, "requireJson");
            return (Criteria) this;
        }

        public Criteria andRequireJsonNotEqualTo(String value) {
            addCriterion("require_json <>", value, "requireJson");
            return (Criteria) this;
        }

        public Criteria andRequireJsonGreaterThan(String value) {
            addCriterion("require_json >", value, "requireJson");
            return (Criteria) this;
        }

        public Criteria andRequireJsonGreaterThanOrEqualTo(String value) {
            addCriterion("require_json >=", value, "requireJson");
            return (Criteria) this;
        }

        public Criteria andRequireJsonLessThan(String value) {
            addCriterion("require_json <", value, "requireJson");
            return (Criteria) this;
        }

        public Criteria andRequireJsonLessThanOrEqualTo(String value) {
            addCriterion("require_json <=", value, "requireJson");
            return (Criteria) this;
        }

        public Criteria andRequireJsonLike(String value) {
            addCriterion("require_json like", value, "requireJson");
            return (Criteria) this;
        }

        public Criteria andRequireJsonNotLike(String value) {
            addCriterion("require_json not like", value, "requireJson");
            return (Criteria) this;
        }

        public Criteria andRequireJsonIn(List<String> values) {
            addCriterion("require_json in", values, "requireJson");
            return (Criteria) this;
        }

        public Criteria andRequireJsonNotIn(List<String> values) {
            addCriterion("require_json not in", values, "requireJson");
            return (Criteria) this;
        }

        public Criteria andRequireJsonBetween(String value1, String value2) {
            addCriterion("require_json between", value1, value2, "requireJson");
            return (Criteria) this;
        }

        public Criteria andRequireJsonNotBetween(String value1, String value2) {
            addCriterion("require_json not between", value1, value2, "requireJson");
            return (Criteria) this;
        }

        public Criteria andVehicleCountIsNull() {
            addCriterion("vehicle_count is null");
            return (Criteria) this;
        }

        public Criteria andVehicleCountIsNotNull() {
            addCriterion("vehicle_count is not null");
            return (Criteria) this;
        }

        public Criteria andVehicleCountEqualTo(Integer value) {
            addCriterion("vehicle_count =", value, "vehicleCount");
            return (Criteria) this;
        }

        public Criteria andVehicleCountNotEqualTo(Integer value) {
            addCriterion("vehicle_count <>", value, "vehicleCount");
            return (Criteria) this;
        }

        public Criteria andVehicleCountGreaterThan(Integer value) {
            addCriterion("vehicle_count >", value, "vehicleCount");
            return (Criteria) this;
        }

        public Criteria andVehicleCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("vehicle_count >=", value, "vehicleCount");
            return (Criteria) this;
        }

        public Criteria andVehicleCountLessThan(Integer value) {
            addCriterion("vehicle_count <", value, "vehicleCount");
            return (Criteria) this;
        }

        public Criteria andVehicleCountLessThanOrEqualTo(Integer value) {
            addCriterion("vehicle_count <=", value, "vehicleCount");
            return (Criteria) this;
        }

        public Criteria andVehicleCountIn(List<Integer> values) {
            addCriterion("vehicle_count in", values, "vehicleCount");
            return (Criteria) this;
        }

        public Criteria andVehicleCountNotIn(List<Integer> values) {
            addCriterion("vehicle_count not in", values, "vehicleCount");
            return (Criteria) this;
        }

        public Criteria andVehicleCountBetween(Integer value1, Integer value2) {
            addCriterion("vehicle_count between", value1, value2, "vehicleCount");
            return (Criteria) this;
        }

        public Criteria andVehicleCountNotBetween(Integer value1, Integer value2) {
            addCriterion("vehicle_count not between", value1, value2, "vehicleCount");
            return (Criteria) this;
        }

        public Criteria andNeedDeliveryPointNoteIsNull() {
            addCriterion("need_delivery_point_note is null");
            return (Criteria) this;
        }

        public Criteria andNeedDeliveryPointNoteIsNotNull() {
            addCriterion("need_delivery_point_note is not null");
            return (Criteria) this;
        }

        public Criteria andNeedDeliveryPointNoteEqualTo(Byte value) {
            addCriterion("need_delivery_point_note =", value, "needDeliveryPointNote");
            return (Criteria) this;
        }

        public Criteria andNeedDeliveryPointNoteNotEqualTo(Byte value) {
            addCriterion("need_delivery_point_note <>", value, "needDeliveryPointNote");
            return (Criteria) this;
        }

        public Criteria andNeedDeliveryPointNoteGreaterThan(Byte value) {
            addCriterion("need_delivery_point_note >", value, "needDeliveryPointNote");
            return (Criteria) this;
        }

        public Criteria andNeedDeliveryPointNoteGreaterThanOrEqualTo(Byte value) {
            addCriterion("need_delivery_point_note >=", value, "needDeliveryPointNote");
            return (Criteria) this;
        }

        public Criteria andNeedDeliveryPointNoteLessThan(Byte value) {
            addCriterion("need_delivery_point_note <", value, "needDeliveryPointNote");
            return (Criteria) this;
        }

        public Criteria andNeedDeliveryPointNoteLessThanOrEqualTo(Byte value) {
            addCriterion("need_delivery_point_note <=", value, "needDeliveryPointNote");
            return (Criteria) this;
        }

        public Criteria andNeedDeliveryPointNoteIn(List<Byte> values) {
            addCriterion("need_delivery_point_note in", values, "needDeliveryPointNote");
            return (Criteria) this;
        }

        public Criteria andNeedDeliveryPointNoteNotIn(List<Byte> values) {
            addCriterion("need_delivery_point_note not in", values, "needDeliveryPointNote");
            return (Criteria) this;
        }

        public Criteria andNeedDeliveryPointNoteBetween(Byte value1, Byte value2) {
            addCriterion("need_delivery_point_note between", value1, value2, "needDeliveryPointNote");
            return (Criteria) this;
        }

        public Criteria andNeedDeliveryPointNoteNotBetween(Byte value1, Byte value2) {
            addCriterion("need_delivery_point_note not between", value1, value2, "needDeliveryPointNote");
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

        public Criteria andReceiveWayIsNull() {
            addCriterion("receive_way is null");
            return (Criteria) this;
        }

        public Criteria andReceiveWayIsNotNull() {
            addCriterion("receive_way is not null");
            return (Criteria) this;
        }

        public Criteria andReceiveWayEqualTo(Byte value) {
            addCriterion("receive_way =", value, "receiveWay");
            return (Criteria) this;
        }

        public Criteria andReceiveWayNotEqualTo(Byte value) {
            addCriterion("receive_way <>", value, "receiveWay");
            return (Criteria) this;
        }

        public Criteria andReceiveWayGreaterThan(Byte value) {
            addCriterion("receive_way >", value, "receiveWay");
            return (Criteria) this;
        }

        public Criteria andReceiveWayGreaterThanOrEqualTo(Byte value) {
            addCriterion("receive_way >=", value, "receiveWay");
            return (Criteria) this;
        }

        public Criteria andReceiveWayLessThan(Byte value) {
            addCriterion("receive_way <", value, "receiveWay");
            return (Criteria) this;
        }

        public Criteria andReceiveWayLessThanOrEqualTo(Byte value) {
            addCriterion("receive_way <=", value, "receiveWay");
            return (Criteria) this;
        }

        public Criteria andReceiveWayIn(List<Byte> values) {
            addCriterion("receive_way in", values, "receiveWay");
            return (Criteria) this;
        }

        public Criteria andReceiveWayNotIn(List<Byte> values) {
            addCriterion("receive_way not in", values, "receiveWay");
            return (Criteria) this;
        }

        public Criteria andReceiveWayBetween(Byte value1, Byte value2) {
            addCriterion("receive_way between", value1, value2, "receiveWay");
            return (Criteria) this;
        }

        public Criteria andReceiveWayNotBetween(Byte value1, Byte value2) {
            addCriterion("receive_way not between", value1, value2, "receiveWay");
            return (Criteria) this;
        }

        public Criteria andOnlyLoadCargoIsNull() {
            addCriterion("only_load_cargo is null");
            return (Criteria) this;
        }

        public Criteria andOnlyLoadCargoIsNotNull() {
            addCriterion("only_load_cargo is not null");
            return (Criteria) this;
        }

        public Criteria andOnlyLoadCargoEqualTo(Byte value) {
            addCriterion("only_load_cargo =", value, "onlyLoadCargo");
            return (Criteria) this;
        }

        public Criteria andOnlyLoadCargoNotEqualTo(Byte value) {
            addCriterion("only_load_cargo <>", value, "onlyLoadCargo");
            return (Criteria) this;
        }

        public Criteria andOnlyLoadCargoGreaterThan(Byte value) {
            addCriterion("only_load_cargo >", value, "onlyLoadCargo");
            return (Criteria) this;
        }

        public Criteria andOnlyLoadCargoGreaterThanOrEqualTo(Byte value) {
            addCriterion("only_load_cargo >=", value, "onlyLoadCargo");
            return (Criteria) this;
        }

        public Criteria andOnlyLoadCargoLessThan(Byte value) {
            addCriterion("only_load_cargo <", value, "onlyLoadCargo");
            return (Criteria) this;
        }

        public Criteria andOnlyLoadCargoLessThanOrEqualTo(Byte value) {
            addCriterion("only_load_cargo <=", value, "onlyLoadCargo");
            return (Criteria) this;
        }

        public Criteria andOnlyLoadCargoIn(List<Byte> values) {
            addCriterion("only_load_cargo in", values, "onlyLoadCargo");
            return (Criteria) this;
        }

        public Criteria andOnlyLoadCargoNotIn(List<Byte> values) {
            addCriterion("only_load_cargo not in", values, "onlyLoadCargo");
            return (Criteria) this;
        }

        public Criteria andOnlyLoadCargoBetween(Byte value1, Byte value2) {
            addCriterion("only_load_cargo between", value1, value2, "onlyLoadCargo");
            return (Criteria) this;
        }

        public Criteria andOnlyLoadCargoNotBetween(Byte value1, Byte value2) {
            addCriterion("only_load_cargo not between", value1, value2, "onlyLoadCargo");
            return (Criteria) this;
        }

        public Criteria andAgencyTakeFreightIsNull() {
            addCriterion("agency_take_freight is null");
            return (Criteria) this;
        }

        public Criteria andAgencyTakeFreightIsNotNull() {
            addCriterion("agency_take_freight is not null");
            return (Criteria) this;
        }

        public Criteria andAgencyTakeFreightEqualTo(BigDecimal value) {
            addCriterion("agency_take_freight =", value, "agencyTakeFreight");
            return (Criteria) this;
        }

        public Criteria andAgencyTakeFreightNotEqualTo(BigDecimal value) {
            addCriterion("agency_take_freight <>", value, "agencyTakeFreight");
            return (Criteria) this;
        }

        public Criteria andAgencyTakeFreightGreaterThan(BigDecimal value) {
            addCriterion("agency_take_freight >", value, "agencyTakeFreight");
            return (Criteria) this;
        }

        public Criteria andAgencyTakeFreightGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("agency_take_freight >=", value, "agencyTakeFreight");
            return (Criteria) this;
        }

        public Criteria andAgencyTakeFreightLessThan(BigDecimal value) {
            addCriterion("agency_take_freight <", value, "agencyTakeFreight");
            return (Criteria) this;
        }

        public Criteria andAgencyTakeFreightLessThanOrEqualTo(BigDecimal value) {
            addCriterion("agency_take_freight <=", value, "agencyTakeFreight");
            return (Criteria) this;
        }

        public Criteria andAgencyTakeFreightIn(List<BigDecimal> values) {
            addCriterion("agency_take_freight in", values, "agencyTakeFreight");
            return (Criteria) this;
        }

        public Criteria andAgencyTakeFreightNotIn(List<BigDecimal> values) {
            addCriterion("agency_take_freight not in", values, "agencyTakeFreight");
            return (Criteria) this;
        }

        public Criteria andAgencyTakeFreightBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("agency_take_freight between", value1, value2, "agencyTakeFreight");
            return (Criteria) this;
        }

        public Criteria andAgencyTakeFreightNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("agency_take_freight not between", value1, value2, "agencyTakeFreight");
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

        public Criteria andProjectFreightRuleJsonIsNull() {
            addCriterion("project_freight_rule_json is null");
            return (Criteria) this;
        }

        public Criteria andProjectFreightRuleJsonIsNotNull() {
            addCriterion("project_freight_rule_json is not null");
            return (Criteria) this;
        }

        public Criteria andProjectFreightRuleJsonEqualTo(String value) {
            addCriterion("project_freight_rule_json =", value, "projectFreightRuleJson");
            return (Criteria) this;
        }

        public Criteria andProjectFreightRuleJsonNotEqualTo(String value) {
            addCriterion("project_freight_rule_json <>", value, "projectFreightRuleJson");
            return (Criteria) this;
        }

        public Criteria andProjectFreightRuleJsonGreaterThan(String value) {
            addCriterion("project_freight_rule_json >", value, "projectFreightRuleJson");
            return (Criteria) this;
        }

        public Criteria andProjectFreightRuleJsonGreaterThanOrEqualTo(String value) {
            addCriterion("project_freight_rule_json >=", value, "projectFreightRuleJson");
            return (Criteria) this;
        }

        public Criteria andProjectFreightRuleJsonLessThan(String value) {
            addCriterion("project_freight_rule_json <", value, "projectFreightRuleJson");
            return (Criteria) this;
        }

        public Criteria andProjectFreightRuleJsonLessThanOrEqualTo(String value) {
            addCriterion("project_freight_rule_json <=", value, "projectFreightRuleJson");
            return (Criteria) this;
        }

        public Criteria andProjectFreightRuleJsonLike(String value) {
            addCriterion("project_freight_rule_json like", value, "projectFreightRuleJson");
            return (Criteria) this;
        }

        public Criteria andProjectFreightRuleJsonNotLike(String value) {
            addCriterion("project_freight_rule_json not like", value, "projectFreightRuleJson");
            return (Criteria) this;
        }

        public Criteria andProjectFreightRuleJsonIn(List<String> values) {
            addCriterion("project_freight_rule_json in", values, "projectFreightRuleJson");
            return (Criteria) this;
        }

        public Criteria andProjectFreightRuleJsonNotIn(List<String> values) {
            addCriterion("project_freight_rule_json not in", values, "projectFreightRuleJson");
            return (Criteria) this;
        }

        public Criteria andProjectFreightRuleJsonBetween(String value1, String value2) {
            addCriterion("project_freight_rule_json between", value1, value2, "projectFreightRuleJson");
            return (Criteria) this;
        }

        public Criteria andProjectFreightRuleJsonNotBetween(String value1, String value2) {
            addCriterion("project_freight_rule_json not between", value1, value2, "projectFreightRuleJson");
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