package com.juma.tgm.waybill.domain.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WaybillExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int startOffSet = -1;

    protected int size = -1;

    public WaybillExample() {
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

        public Criteria andWaybillIdIsNull() {
            addCriterion("waybill_id is null");
            return (Criteria) this;
        }

        public Criteria andWaybillIdIsNotNull() {
            addCriterion("waybill_id is not null");
            return (Criteria) this;
        }

        public Criteria andWaybillIdEqualTo(Integer value) {
            addCriterion("waybill_id =", value, "waybillId");
            return (Criteria) this;
        }

        public Criteria andWaybillIdNotEqualTo(Integer value) {
            addCriterion("waybill_id <>", value, "waybillId");
            return (Criteria) this;
        }

        public Criteria andWaybillIdGreaterThan(Integer value) {
            addCriterion("waybill_id >", value, "waybillId");
            return (Criteria) this;
        }

        public Criteria andWaybillIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("waybill_id >=", value, "waybillId");
            return (Criteria) this;
        }

        public Criteria andWaybillIdLessThan(Integer value) {
            addCriterion("waybill_id <", value, "waybillId");
            return (Criteria) this;
        }

        public Criteria andWaybillIdLessThanOrEqualTo(Integer value) {
            addCriterion("waybill_id <=", value, "waybillId");
            return (Criteria) this;
        }

        public Criteria andWaybillIdIn(List<Integer> values) {
            addCriterion("waybill_id in", values, "waybillId");
            return (Criteria) this;
        }

        public Criteria andWaybillIdNotIn(List<Integer> values) {
            addCriterion("waybill_id not in", values, "waybillId");
            return (Criteria) this;
        }

        public Criteria andWaybillIdBetween(Integer value1, Integer value2) {
            addCriterion("waybill_id between", value1, value2, "waybillId");
            return (Criteria) this;
        }

        public Criteria andWaybillIdNotBetween(Integer value1, Integer value2) {
            addCriterion("waybill_id not between", value1, value2, "waybillId");
            return (Criteria) this;
        }

        public Criteria andPlanDeliveryTimeIsNull() {
            addCriterion("plan_delivery_time is null");
            return (Criteria) this;
        }

        public Criteria andPlanDeliveryTimeIsNotNull() {
            addCriterion("plan_delivery_time is not null");
            return (Criteria) this;
        }

        public Criteria andPlanDeliveryTimeEqualTo(Date value) {
            addCriterion("plan_delivery_time =", value, "planDeliveryTime");
            return (Criteria) this;
        }

        public Criteria andPlanDeliveryTimeNotEqualTo(Date value) {
            addCriterion("plan_delivery_time <>", value, "planDeliveryTime");
            return (Criteria) this;
        }

        public Criteria andPlanDeliveryTimeGreaterThan(Date value) {
            addCriterion("plan_delivery_time >", value, "planDeliveryTime");
            return (Criteria) this;
        }

        public Criteria andPlanDeliveryTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("plan_delivery_time >=", value, "planDeliveryTime");
            return (Criteria) this;
        }

        public Criteria andPlanDeliveryTimeLessThan(Date value) {
            addCriterion("plan_delivery_time <", value, "planDeliveryTime");
            return (Criteria) this;
        }

        public Criteria andPlanDeliveryTimeLessThanOrEqualTo(Date value) {
            addCriterion("plan_delivery_time <=", value, "planDeliveryTime");
            return (Criteria) this;
        }

        public Criteria andPlanDeliveryTimeIn(List<Date> values) {
            addCriterion("plan_delivery_time in", values, "planDeliveryTime");
            return (Criteria) this;
        }

        public Criteria andPlanDeliveryTimeNotIn(List<Date> values) {
            addCriterion("plan_delivery_time not in", values, "planDeliveryTime");
            return (Criteria) this;
        }

        public Criteria andPlanDeliveryTimeBetween(Date value1, Date value2) {
            addCriterion("plan_delivery_time between", value1, value2, "planDeliveryTime");
            return (Criteria) this;
        }

        public Criteria andPlanDeliveryTimeNotBetween(Date value1, Date value2) {
            addCriterion("plan_delivery_time not between", value1, value2, "planDeliveryTime");
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

        public Criteria andTruckCustomerNameIsNull() {
            addCriterion("truck_customer_name is null");
            return (Criteria) this;
        }

        public Criteria andTruckCustomerNameIsNotNull() {
            addCriterion("truck_customer_name is not null");
            return (Criteria) this;
        }

        public Criteria andTruckCustomerNameEqualTo(String value) {
            addCriterion("truck_customer_name =", value, "truckCustomerName");
            return (Criteria) this;
        }

        public Criteria andTruckCustomerNameNotEqualTo(String value) {
            addCriterion("truck_customer_name <>", value, "truckCustomerName");
            return (Criteria) this;
        }

        public Criteria andTruckCustomerNameGreaterThan(String value) {
            addCriterion("truck_customer_name >", value, "truckCustomerName");
            return (Criteria) this;
        }

        public Criteria andTruckCustomerNameGreaterThanOrEqualTo(String value) {
            addCriterion("truck_customer_name >=", value, "truckCustomerName");
            return (Criteria) this;
        }

        public Criteria andTruckCustomerNameLessThan(String value) {
            addCriterion("truck_customer_name <", value, "truckCustomerName");
            return (Criteria) this;
        }

        public Criteria andTruckCustomerNameLessThanOrEqualTo(String value) {
            addCriterion("truck_customer_name <=", value, "truckCustomerName");
            return (Criteria) this;
        }

        public Criteria andTruckCustomerNameLike(String value) {
            addCriterion("truck_customer_name like", value, "truckCustomerName");
            return (Criteria) this;
        }

        public Criteria andTruckCustomerNameNotLike(String value) {
            addCriterion("truck_customer_name not like", value, "truckCustomerName");
            return (Criteria) this;
        }

        public Criteria andTruckCustomerNameIn(List<String> values) {
            addCriterion("truck_customer_name in", values, "truckCustomerName");
            return (Criteria) this;
        }

        public Criteria andTruckCustomerNameNotIn(List<String> values) {
            addCriterion("truck_customer_name not in", values, "truckCustomerName");
            return (Criteria) this;
        }

        public Criteria andTruckCustomerNameBetween(String value1, String value2) {
            addCriterion("truck_customer_name between", value1, value2, "truckCustomerName");
            return (Criteria) this;
        }

        public Criteria andTruckCustomerNameNotBetween(String value1, String value2) {
            addCriterion("truck_customer_name not between", value1, value2, "truckCustomerName");
            return (Criteria) this;
        }

        public Criteria andDriverIdIsNull() {
            addCriterion("driver_id is null");
            return (Criteria) this;
        }

        public Criteria andDriverIdIsNotNull() {
            addCriterion("driver_id is not null");
            return (Criteria) this;
        }

        public Criteria andDriverIdEqualTo(Integer value) {
            addCriterion("driver_id =", value, "driverId");
            return (Criteria) this;
        }

        public Criteria andDriverIdNotEqualTo(Integer value) {
            addCriterion("driver_id <>", value, "driverId");
            return (Criteria) this;
        }

        public Criteria andDriverIdGreaterThan(Integer value) {
            addCriterion("driver_id >", value, "driverId");
            return (Criteria) this;
        }

        public Criteria andDriverIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("driver_id >=", value, "driverId");
            return (Criteria) this;
        }

        public Criteria andDriverIdLessThan(Integer value) {
            addCriterion("driver_id <", value, "driverId");
            return (Criteria) this;
        }

        public Criteria andDriverIdLessThanOrEqualTo(Integer value) {
            addCriterion("driver_id <=", value, "driverId");
            return (Criteria) this;
        }

        public Criteria andDriverIdIn(List<Integer> values) {
            addCriterion("driver_id in", values, "driverId");
            return (Criteria) this;
        }

        public Criteria andDriverIdNotIn(List<Integer> values) {
            addCriterion("driver_id not in", values, "driverId");
            return (Criteria) this;
        }

        public Criteria andDriverIdBetween(Integer value1, Integer value2) {
            addCriterion("driver_id between", value1, value2, "driverId");
            return (Criteria) this;
        }

        public Criteria andDriverIdNotBetween(Integer value1, Integer value2) {
            addCriterion("driver_id not between", value1, value2, "driverId");
            return (Criteria) this;
        }

        public Criteria andDriverNameIsNull() {
            addCriterion("driver_name is null");
            return (Criteria) this;
        }

        public Criteria andDriverNameIsNotNull() {
            addCriterion("driver_name is not null");
            return (Criteria) this;
        }

        public Criteria andDriverNameEqualTo(String value) {
            addCriterion("driver_name =", value, "driverName");
            return (Criteria) this;
        }

        public Criteria andDriverNameNotEqualTo(String value) {
            addCriterion("driver_name <>", value, "driverName");
            return (Criteria) this;
        }

        public Criteria andDriverNameGreaterThan(String value) {
            addCriterion("driver_name >", value, "driverName");
            return (Criteria) this;
        }

        public Criteria andDriverNameGreaterThanOrEqualTo(String value) {
            addCriterion("driver_name >=", value, "driverName");
            return (Criteria) this;
        }

        public Criteria andDriverNameLessThan(String value) {
            addCriterion("driver_name <", value, "driverName");
            return (Criteria) this;
        }

        public Criteria andDriverNameLessThanOrEqualTo(String value) {
            addCriterion("driver_name <=", value, "driverName");
            return (Criteria) this;
        }

        public Criteria andDriverNameLike(String value) {
            addCriterion("driver_name like", value, "driverName");
            return (Criteria) this;
        }

        public Criteria andDriverNameNotLike(String value) {
            addCriterion("driver_name not like", value, "driverName");
            return (Criteria) this;
        }

        public Criteria andDriverNameIn(List<String> values) {
            addCriterion("driver_name in", values, "driverName");
            return (Criteria) this;
        }

        public Criteria andDriverNameNotIn(List<String> values) {
            addCriterion("driver_name not in", values, "driverName");
            return (Criteria) this;
        }

        public Criteria andDriverNameBetween(String value1, String value2) {
            addCriterion("driver_name between", value1, value2, "driverName");
            return (Criteria) this;
        }

        public Criteria andDriverNameNotBetween(String value1, String value2) {
            addCriterion("driver_name not between", value1, value2, "driverName");
            return (Criteria) this;
        }

        public Criteria andDriverTypeIsNull() {
            addCriterion("driver_type is null");
            return (Criteria) this;
        }

        public Criteria andDriverTypeIsNotNull() {
            addCriterion("driver_type is not null");
            return (Criteria) this;
        }

        public Criteria andDriverTypeEqualTo(Byte value) {
            addCriterion("driver_type =", value, "driverType");
            return (Criteria) this;
        }

        public Criteria andDriverTypeNotEqualTo(Byte value) {
            addCriterion("driver_type <>", value, "driverType");
            return (Criteria) this;
        }

        public Criteria andDriverTypeGreaterThan(Byte value) {
            addCriterion("driver_type >", value, "driverType");
            return (Criteria) this;
        }

        public Criteria andDriverTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("driver_type >=", value, "driverType");
            return (Criteria) this;
        }

        public Criteria andDriverTypeLessThan(Byte value) {
            addCriterion("driver_type <", value, "driverType");
            return (Criteria) this;
        }

        public Criteria andDriverTypeLessThanOrEqualTo(Byte value) {
            addCriterion("driver_type <=", value, "driverType");
            return (Criteria) this;
        }

        public Criteria andDriverTypeIn(List<Byte> values) {
            addCriterion("driver_type in", values, "driverType");
            return (Criteria) this;
        }

        public Criteria andDriverTypeNotIn(List<Byte> values) {
            addCriterion("driver_type not in", values, "driverType");
            return (Criteria) this;
        }

        public Criteria andDriverTypeBetween(Byte value1, Byte value2) {
            addCriterion("driver_type between", value1, value2, "driverType");
            return (Criteria) this;
        }

        public Criteria andDriverTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("driver_type not between", value1, value2, "driverType");
            return (Criteria) this;
        }

        public Criteria andTruckIdIsNull() {
            addCriterion("truck_id is null");
            return (Criteria) this;
        }

        public Criteria andTruckIdIsNotNull() {
            addCriterion("truck_id is not null");
            return (Criteria) this;
        }

        public Criteria andTruckIdEqualTo(Integer value) {
            addCriterion("truck_id =", value, "truckId");
            return (Criteria) this;
        }

        public Criteria andTruckIdNotEqualTo(Integer value) {
            addCriterion("truck_id <>", value, "truckId");
            return (Criteria) this;
        }

        public Criteria andTruckIdGreaterThan(Integer value) {
            addCriterion("truck_id >", value, "truckId");
            return (Criteria) this;
        }

        public Criteria andTruckIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("truck_id >=", value, "truckId");
            return (Criteria) this;
        }

        public Criteria andTruckIdLessThan(Integer value) {
            addCriterion("truck_id <", value, "truckId");
            return (Criteria) this;
        }

        public Criteria andTruckIdLessThanOrEqualTo(Integer value) {
            addCriterion("truck_id <=", value, "truckId");
            return (Criteria) this;
        }

        public Criteria andTruckIdIn(List<Integer> values) {
            addCriterion("truck_id in", values, "truckId");
            return (Criteria) this;
        }

        public Criteria andTruckIdNotIn(List<Integer> values) {
            addCriterion("truck_id not in", values, "truckId");
            return (Criteria) this;
        }

        public Criteria andTruckIdBetween(Integer value1, Integer value2) {
            addCriterion("truck_id between", value1, value2, "truckId");
            return (Criteria) this;
        }

        public Criteria andTruckIdNotBetween(Integer value1, Integer value2) {
            addCriterion("truck_id not between", value1, value2, "truckId");
            return (Criteria) this;
        }

        public Criteria andPlateNumberIsNull() {
            addCriterion("plate_number is null");
            return (Criteria) this;
        }

        public Criteria andPlateNumberIsNotNull() {
            addCriterion("plate_number is not null");
            return (Criteria) this;
        }

        public Criteria andPlateNumberEqualTo(String value) {
            addCriterion("plate_number =", value, "plateNumber");
            return (Criteria) this;
        }

        public Criteria andPlateNumberNotEqualTo(String value) {
            addCriterion("plate_number <>", value, "plateNumber");
            return (Criteria) this;
        }

        public Criteria andPlateNumberGreaterThan(String value) {
            addCriterion("plate_number >", value, "plateNumber");
            return (Criteria) this;
        }

        public Criteria andPlateNumberGreaterThanOrEqualTo(String value) {
            addCriterion("plate_number >=", value, "plateNumber");
            return (Criteria) this;
        }

        public Criteria andPlateNumberLessThan(String value) {
            addCriterion("plate_number <", value, "plateNumber");
            return (Criteria) this;
        }

        public Criteria andPlateNumberLessThanOrEqualTo(String value) {
            addCriterion("plate_number <=", value, "plateNumber");
            return (Criteria) this;
        }

        public Criteria andPlateNumberLike(String value) {
            addCriterion("plate_number like", value, "plateNumber");
            return (Criteria) this;
        }

        public Criteria andPlateNumberNotLike(String value) {
            addCriterion("plate_number not like", value, "plateNumber");
            return (Criteria) this;
        }

        public Criteria andPlateNumberIn(List<String> values) {
            addCriterion("plate_number in", values, "plateNumber");
            return (Criteria) this;
        }

        public Criteria andPlateNumberNotIn(List<String> values) {
            addCriterion("plate_number not in", values, "plateNumber");
            return (Criteria) this;
        }

        public Criteria andPlateNumberBetween(String value1, String value2) {
            addCriterion("plate_number between", value1, value2, "plateNumber");
            return (Criteria) this;
        }

        public Criteria andPlateNumberNotBetween(String value1, String value2) {
            addCriterion("plate_number not between", value1, value2, "plateNumber");
            return (Criteria) this;
        }

        public Criteria andOwnerEcoUserIdIsNull() {
            addCriterion("owner_eco_user_id is null");
            return (Criteria) this;
        }

        public Criteria andOwnerEcoUserIdIsNotNull() {
            addCriterion("owner_eco_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andOwnerEcoUserIdEqualTo(Integer value) {
            addCriterion("owner_eco_user_id =", value, "ownerEcoUserId");
            return (Criteria) this;
        }

        public Criteria andOwnerEcoUserIdNotEqualTo(Integer value) {
            addCriterion("owner_eco_user_id <>", value, "ownerEcoUserId");
            return (Criteria) this;
        }

        public Criteria andOwnerEcoUserIdGreaterThan(Integer value) {
            addCriterion("owner_eco_user_id >", value, "ownerEcoUserId");
            return (Criteria) this;
        }

        public Criteria andOwnerEcoUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("owner_eco_user_id >=", value, "ownerEcoUserId");
            return (Criteria) this;
        }

        public Criteria andOwnerEcoUserIdLessThan(Integer value) {
            addCriterion("owner_eco_user_id <", value, "ownerEcoUserId");
            return (Criteria) this;
        }

        public Criteria andOwnerEcoUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("owner_eco_user_id <=", value, "ownerEcoUserId");
            return (Criteria) this;
        }

        public Criteria andOwnerEcoUserIdIn(List<Integer> values) {
            addCriterion("owner_eco_user_id in", values, "ownerEcoUserId");
            return (Criteria) this;
        }

        public Criteria andOwnerEcoUserIdNotIn(List<Integer> values) {
            addCriterion("owner_eco_user_id not in", values, "ownerEcoUserId");
            return (Criteria) this;
        }

        public Criteria andOwnerEcoUserIdBetween(Integer value1, Integer value2) {
            addCriterion("owner_eco_user_id between", value1, value2, "ownerEcoUserId");
            return (Criteria) this;
        }

        public Criteria andOwnerEcoUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("owner_eco_user_id not between", value1, value2, "ownerEcoUserId");
            return (Criteria) this;
        }

        public Criteria andOwnerEmployeeIdIsNull() {
            addCriterion("owner_employee_id is null");
            return (Criteria) this;
        }

        public Criteria andOwnerEmployeeIdIsNotNull() {
            addCriterion("owner_employee_id is not null");
            return (Criteria) this;
        }

        public Criteria andOwnerEmployeeIdEqualTo(Integer value) {
            addCriterion("owner_employee_id =", value, "ownerEmployeeId");
            return (Criteria) this;
        }

        public Criteria andOwnerEmployeeIdNotEqualTo(Integer value) {
            addCriterion("owner_employee_id <>", value, "ownerEmployeeId");
            return (Criteria) this;
        }

        public Criteria andOwnerEmployeeIdGreaterThan(Integer value) {
            addCriterion("owner_employee_id >", value, "ownerEmployeeId");
            return (Criteria) this;
        }

        public Criteria andOwnerEmployeeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("owner_employee_id >=", value, "ownerEmployeeId");
            return (Criteria) this;
        }

        public Criteria andOwnerEmployeeIdLessThan(Integer value) {
            addCriterion("owner_employee_id <", value, "ownerEmployeeId");
            return (Criteria) this;
        }

        public Criteria andOwnerEmployeeIdLessThanOrEqualTo(Integer value) {
            addCriterion("owner_employee_id <=", value, "ownerEmployeeId");
            return (Criteria) this;
        }

        public Criteria andOwnerEmployeeIdIn(List<Integer> values) {
            addCriterion("owner_employee_id in", values, "ownerEmployeeId");
            return (Criteria) this;
        }

        public Criteria andOwnerEmployeeIdNotIn(List<Integer> values) {
            addCriterion("owner_employee_id not in", values, "ownerEmployeeId");
            return (Criteria) this;
        }

        public Criteria andOwnerEmployeeIdBetween(Integer value1, Integer value2) {
            addCriterion("owner_employee_id between", value1, value2, "ownerEmployeeId");
            return (Criteria) this;
        }

        public Criteria andOwnerEmployeeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("owner_employee_id not between", value1, value2, "ownerEmployeeId");
            return (Criteria) this;
        }

        public Criteria andVehicleBoxTypeIsNull() {
            addCriterion("vehicle_box_type is null");
            return (Criteria) this;
        }

        public Criteria andVehicleBoxTypeIsNotNull() {
            addCriterion("vehicle_box_type is not null");
            return (Criteria) this;
        }

        public Criteria andVehicleBoxTypeEqualTo(Byte value) {
            addCriterion("vehicle_box_type =", value, "vehicleBoxType");
            return (Criteria) this;
        }

        public Criteria andVehicleBoxTypeNotEqualTo(Byte value) {
            addCriterion("vehicle_box_type <>", value, "vehicleBoxType");
            return (Criteria) this;
        }

        public Criteria andVehicleBoxTypeGreaterThan(Byte value) {
            addCriterion("vehicle_box_type >", value, "vehicleBoxType");
            return (Criteria) this;
        }

        public Criteria andVehicleBoxTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("vehicle_box_type >=", value, "vehicleBoxType");
            return (Criteria) this;
        }

        public Criteria andVehicleBoxTypeLessThan(Byte value) {
            addCriterion("vehicle_box_type <", value, "vehicleBoxType");
            return (Criteria) this;
        }

        public Criteria andVehicleBoxTypeLessThanOrEqualTo(Byte value) {
            addCriterion("vehicle_box_type <=", value, "vehicleBoxType");
            return (Criteria) this;
        }

        public Criteria andVehicleBoxTypeIn(List<Byte> values) {
            addCriterion("vehicle_box_type in", values, "vehicleBoxType");
            return (Criteria) this;
        }

        public Criteria andVehicleBoxTypeNotIn(List<Byte> values) {
            addCriterion("vehicle_box_type not in", values, "vehicleBoxType");
            return (Criteria) this;
        }

        public Criteria andVehicleBoxTypeBetween(Byte value1, Byte value2) {
            addCriterion("vehicle_box_type between", value1, value2, "vehicleBoxType");
            return (Criteria) this;
        }

        public Criteria andVehicleBoxTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("vehicle_box_type not between", value1, value2, "vehicleBoxType");
            return (Criteria) this;
        }

        public Criteria andIsTestIsNull() {
            addCriterion("is_test is null");
            return (Criteria) this;
        }

        public Criteria andIsTestIsNotNull() {
            addCriterion("is_test is not null");
            return (Criteria) this;
        }

        public Criteria andIsTestEqualTo(Byte value) {
            addCriterion("is_test =", value, "isTest");
            return (Criteria) this;
        }

        public Criteria andIsTestNotEqualTo(Byte value) {
            addCriterion("is_test <>", value, "isTest");
            return (Criteria) this;
        }

        public Criteria andIsTestGreaterThan(Byte value) {
            addCriterion("is_test >", value, "isTest");
            return (Criteria) this;
        }

        public Criteria andIsTestGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_test >=", value, "isTest");
            return (Criteria) this;
        }

        public Criteria andIsTestLessThan(Byte value) {
            addCriterion("is_test <", value, "isTest");
            return (Criteria) this;
        }

        public Criteria andIsTestLessThanOrEqualTo(Byte value) {
            addCriterion("is_test <=", value, "isTest");
            return (Criteria) this;
        }

        public Criteria andIsTestIn(List<Byte> values) {
            addCriterion("is_test in", values, "isTest");
            return (Criteria) this;
        }

        public Criteria andIsTestNotIn(List<Byte> values) {
            addCriterion("is_test not in", values, "isTest");
            return (Criteria) this;
        }

        public Criteria andIsTestBetween(Byte value1, Byte value2) {
            addCriterion("is_test between", value1, value2, "isTest");
            return (Criteria) this;
        }

        public Criteria andIsTestNotBetween(Byte value1, Byte value2) {
            addCriterion("is_test not between", value1, value2, "isTest");
            return (Criteria) this;
        }

        public Criteria andIsSubmitToErpIsNull() {
            addCriterion("is_submit_to_erp is null");
            return (Criteria) this;
        }

        public Criteria andIsSubmitToErpIsNotNull() {
            addCriterion("is_submit_to_erp is not null");
            return (Criteria) this;
        }

        public Criteria andIsSubmitToErpEqualTo(Byte value) {
            addCriterion("is_submit_to_erp =", value, "isSubmitToErp");
            return (Criteria) this;
        }

        public Criteria andIsSubmitToErpNotEqualTo(Byte value) {
            addCriterion("is_submit_to_erp <>", value, "isSubmitToErp");
            return (Criteria) this;
        }

        public Criteria andIsSubmitToErpGreaterThan(Byte value) {
            addCriterion("is_submit_to_erp >", value, "isSubmitToErp");
            return (Criteria) this;
        }

        public Criteria andIsSubmitToErpGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_submit_to_erp >=", value, "isSubmitToErp");
            return (Criteria) this;
        }

        public Criteria andIsSubmitToErpLessThan(Byte value) {
            addCriterion("is_submit_to_erp <", value, "isSubmitToErp");
            return (Criteria) this;
        }

        public Criteria andIsSubmitToErpLessThanOrEqualTo(Byte value) {
            addCriterion("is_submit_to_erp <=", value, "isSubmitToErp");
            return (Criteria) this;
        }

        public Criteria andIsSubmitToErpIn(List<Byte> values) {
            addCriterion("is_submit_to_erp in", values, "isSubmitToErp");
            return (Criteria) this;
        }

        public Criteria andIsSubmitToErpNotIn(List<Byte> values) {
            addCriterion("is_submit_to_erp not in", values, "isSubmitToErp");
            return (Criteria) this;
        }

        public Criteria andIsSubmitToErpBetween(Byte value1, Byte value2) {
            addCriterion("is_submit_to_erp between", value1, value2, "isSubmitToErp");
            return (Criteria) this;
        }

        public Criteria andIsSubmitToErpNotBetween(Byte value1, Byte value2) {
            addCriterion("is_submit_to_erp not between", value1, value2, "isSubmitToErp");
            return (Criteria) this;
        }

        public Criteria andWaybillNoIsNull() {
            addCriterion("waybill_no is null");
            return (Criteria) this;
        }

        public Criteria andWaybillNoIsNotNull() {
            addCriterion("waybill_no is not null");
            return (Criteria) this;
        }

        public Criteria andWaybillNoEqualTo(String value) {
            addCriterion("waybill_no =", value, "waybillNo");
            return (Criteria) this;
        }

        public Criteria andWaybillNoNotEqualTo(String value) {
            addCriterion("waybill_no <>", value, "waybillNo");
            return (Criteria) this;
        }

        public Criteria andWaybillNoGreaterThan(String value) {
            addCriterion("waybill_no >", value, "waybillNo");
            return (Criteria) this;
        }

        public Criteria andWaybillNoGreaterThanOrEqualTo(String value) {
            addCriterion("waybill_no >=", value, "waybillNo");
            return (Criteria) this;
        }

        public Criteria andWaybillNoLessThan(String value) {
            addCriterion("waybill_no <", value, "waybillNo");
            return (Criteria) this;
        }

        public Criteria andWaybillNoLessThanOrEqualTo(String value) {
            addCriterion("waybill_no <=", value, "waybillNo");
            return (Criteria) this;
        }

        public Criteria andWaybillNoLike(String value) {
            addCriterion("waybill_no like", value, "waybillNo");
            return (Criteria) this;
        }

        public Criteria andWaybillNoNotLike(String value) {
            addCriterion("waybill_no not like", value, "waybillNo");
            return (Criteria) this;
        }

        public Criteria andWaybillNoIn(List<String> values) {
            addCriterion("waybill_no in", values, "waybillNo");
            return (Criteria) this;
        }

        public Criteria andWaybillNoNotIn(List<String> values) {
            addCriterion("waybill_no not in", values, "waybillNo");
            return (Criteria) this;
        }

        public Criteria andWaybillNoBetween(String value1, String value2) {
            addCriterion("waybill_no between", value1, value2, "waybillNo");
            return (Criteria) this;
        }

        public Criteria andWaybillNoNotBetween(String value1, String value2) {
            addCriterion("waybill_no not between", value1, value2, "waybillNo");
            return (Criteria) this;
        }

        public Criteria andEstimateTimeConsumptionIsNull() {
            addCriterion("estimate_time_consumption is null");
            return (Criteria) this;
        }

        public Criteria andEstimateTimeConsumptionIsNotNull() {
            addCriterion("estimate_time_consumption is not null");
            return (Criteria) this;
        }

        public Criteria andEstimateTimeConsumptionEqualTo(Integer value) {
            addCriterion("estimate_time_consumption =", value, "estimateTimeConsumption");
            return (Criteria) this;
        }

        public Criteria andEstimateTimeConsumptionNotEqualTo(Integer value) {
            addCriterion("estimate_time_consumption <>", value, "estimateTimeConsumption");
            return (Criteria) this;
        }

        public Criteria andEstimateTimeConsumptionGreaterThan(Integer value) {
            addCriterion("estimate_time_consumption >", value, "estimateTimeConsumption");
            return (Criteria) this;
        }

        public Criteria andEstimateTimeConsumptionGreaterThanOrEqualTo(Integer value) {
            addCriterion("estimate_time_consumption >=", value, "estimateTimeConsumption");
            return (Criteria) this;
        }

        public Criteria andEstimateTimeConsumptionLessThan(Integer value) {
            addCriterion("estimate_time_consumption <", value, "estimateTimeConsumption");
            return (Criteria) this;
        }

        public Criteria andEstimateTimeConsumptionLessThanOrEqualTo(Integer value) {
            addCriterion("estimate_time_consumption <=", value, "estimateTimeConsumption");
            return (Criteria) this;
        }

        public Criteria andEstimateTimeConsumptionIn(List<Integer> values) {
            addCriterion("estimate_time_consumption in", values, "estimateTimeConsumption");
            return (Criteria) this;
        }

        public Criteria andEstimateTimeConsumptionNotIn(List<Integer> values) {
            addCriterion("estimate_time_consumption not in", values, "estimateTimeConsumption");
            return (Criteria) this;
        }

        public Criteria andEstimateTimeConsumptionBetween(Integer value1, Integer value2) {
            addCriterion("estimate_time_consumption between", value1, value2, "estimateTimeConsumption");
            return (Criteria) this;
        }

        public Criteria andEstimateTimeConsumptionNotBetween(Integer value1, Integer value2) {
            addCriterion("estimate_time_consumption not between", value1, value2, "estimateTimeConsumption");
            return (Criteria) this;
        }

        public Criteria andCmEstimateFinishTimeIsNull() {
            addCriterion("cm_estimate_finish_time is null");
            return (Criteria) this;
        }

        public Criteria andCmEstimateFinishTimeIsNotNull() {
            addCriterion("cm_estimate_finish_time is not null");
            return (Criteria) this;
        }

        public Criteria andCmEstimateFinishTimeEqualTo(Date value) {
            addCriterion("cm_estimate_finish_time =", value, "cmEstimateFinishTime");
            return (Criteria) this;
        }

        public Criteria andCmEstimateFinishTimeNotEqualTo(Date value) {
            addCriterion("cm_estimate_finish_time <>", value, "cmEstimateFinishTime");
            return (Criteria) this;
        }

        public Criteria andCmEstimateFinishTimeGreaterThan(Date value) {
            addCriterion("cm_estimate_finish_time >", value, "cmEstimateFinishTime");
            return (Criteria) this;
        }

        public Criteria andCmEstimateFinishTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("cm_estimate_finish_time >=", value, "cmEstimateFinishTime");
            return (Criteria) this;
        }

        public Criteria andCmEstimateFinishTimeLessThan(Date value) {
            addCriterion("cm_estimate_finish_time <", value, "cmEstimateFinishTime");
            return (Criteria) this;
        }

        public Criteria andCmEstimateFinishTimeLessThanOrEqualTo(Date value) {
            addCriterion("cm_estimate_finish_time <=", value, "cmEstimateFinishTime");
            return (Criteria) this;
        }

        public Criteria andCmEstimateFinishTimeIn(List<Date> values) {
            addCriterion("cm_estimate_finish_time in", values, "cmEstimateFinishTime");
            return (Criteria) this;
        }

        public Criteria andCmEstimateFinishTimeNotIn(List<Date> values) {
            addCriterion("cm_estimate_finish_time not in", values, "cmEstimateFinishTime");
            return (Criteria) this;
        }

        public Criteria andCmEstimateFinishTimeBetween(Date value1, Date value2) {
            addCriterion("cm_estimate_finish_time between", value1, value2, "cmEstimateFinishTime");
            return (Criteria) this;
        }

        public Criteria andCmEstimateFinishTimeNotBetween(Date value1, Date value2) {
            addCriterion("cm_estimate_finish_time not between", value1, value2, "cmEstimateFinishTime");
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

        public Criteria andStatusViewIsNull() {
            addCriterion("status_view is null");
            return (Criteria) this;
        }

        public Criteria andStatusViewIsNotNull() {
            addCriterion("status_view is not null");
            return (Criteria) this;
        }

        public Criteria andStatusViewEqualTo(Integer value) {
            addCriterion("status_view =", value, "statusView");
            return (Criteria) this;
        }

        public Criteria andStatusViewNotEqualTo(Integer value) {
            addCriterion("status_view <>", value, "statusView");
            return (Criteria) this;
        }

        public Criteria andStatusViewGreaterThan(Integer value) {
            addCriterion("status_view >", value, "statusView");
            return (Criteria) this;
        }

        public Criteria andStatusViewGreaterThanOrEqualTo(Integer value) {
            addCriterion("status_view >=", value, "statusView");
            return (Criteria) this;
        }

        public Criteria andStatusViewLessThan(Integer value) {
            addCriterion("status_view <", value, "statusView");
            return (Criteria) this;
        }

        public Criteria andStatusViewLessThanOrEqualTo(Integer value) {
            addCriterion("status_view <=", value, "statusView");
            return (Criteria) this;
        }

        public Criteria andStatusViewIn(List<Integer> values) {
            addCriterion("status_view in", values, "statusView");
            return (Criteria) this;
        }

        public Criteria andStatusViewNotIn(List<Integer> values) {
            addCriterion("status_view not in", values, "statusView");
            return (Criteria) this;
        }

        public Criteria andStatusViewBetween(Integer value1, Integer value2) {
            addCriterion("status_view between", value1, value2, "statusView");
            return (Criteria) this;
        }

        public Criteria andStatusViewNotBetween(Integer value1, Integer value2) {
            addCriterion("status_view not between", value1, value2, "statusView");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Byte value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Byte value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Byte value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Byte value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Byte value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Byte> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Byte> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Byte value1, Byte value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("status not between", value1, value2, "status");
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

        public Criteria andReceiveWayEqualTo(Integer value) {
            addCriterion("receive_way =", value, "receiveWay");
            return (Criteria) this;
        }

        public Criteria andReceiveWayNotEqualTo(Integer value) {
            addCriterion("receive_way <>", value, "receiveWay");
            return (Criteria) this;
        }

        public Criteria andReceiveWayGreaterThan(Integer value) {
            addCriterion("receive_way >", value, "receiveWay");
            return (Criteria) this;
        }

        public Criteria andReceiveWayGreaterThanOrEqualTo(Integer value) {
            addCriterion("receive_way >=", value, "receiveWay");
            return (Criteria) this;
        }

        public Criteria andReceiveWayLessThan(Integer value) {
            addCriterion("receive_way <", value, "receiveWay");
            return (Criteria) this;
        }

        public Criteria andReceiveWayLessThanOrEqualTo(Integer value) {
            addCriterion("receive_way <=", value, "receiveWay");
            return (Criteria) this;
        }

        public Criteria andReceiveWayIn(List<Integer> values) {
            addCriterion("receive_way in", values, "receiveWay");
            return (Criteria) this;
        }

        public Criteria andReceiveWayNotIn(List<Integer> values) {
            addCriterion("receive_way not in", values, "receiveWay");
            return (Criteria) this;
        }

        public Criteria andReceiveWayBetween(Integer value1, Integer value2) {
            addCriterion("receive_way between", value1, value2, "receiveWay");
            return (Criteria) this;
        }

        public Criteria andReceiveWayNotBetween(Integer value1, Integer value2) {
            addCriterion("receive_way not between", value1, value2, "receiveWay");
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

        public Criteria andProjectNameIsNull() {
            addCriterion("project_name is null");
            return (Criteria) this;
        }

        public Criteria andProjectNameIsNotNull() {
            addCriterion("project_name is not null");
            return (Criteria) this;
        }

        public Criteria andProjectNameEqualTo(String value) {
            addCriterion("project_name =", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotEqualTo(String value) {
            addCriterion("project_name <>", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameGreaterThan(String value) {
            addCriterion("project_name >", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameGreaterThanOrEqualTo(String value) {
            addCriterion("project_name >=", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLessThan(String value) {
            addCriterion("project_name <", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLessThanOrEqualTo(String value) {
            addCriterion("project_name <=", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLike(String value) {
            addCriterion("project_name like", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotLike(String value) {
            addCriterion("project_name not like", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameIn(List<String> values) {
            addCriterion("project_name in", values, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotIn(List<String> values) {
            addCriterion("project_name not in", values, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameBetween(String value1, String value2) {
            addCriterion("project_name between", value1, value2, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotBetween(String value1, String value2) {
            addCriterion("project_name not between", value1, value2, "projectName");
            return (Criteria) this;
        }

        public Criteria andAccountTypeIsNull() {
            addCriterion("account_type is null");
            return (Criteria) this;
        }

        public Criteria andAccountTypeIsNotNull() {
            addCriterion("account_type is not null");
            return (Criteria) this;
        }

        public Criteria andAccountTypeEqualTo(Byte value) {
            addCriterion("account_type =", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeNotEqualTo(Byte value) {
            addCriterion("account_type <>", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeGreaterThan(Byte value) {
            addCriterion("account_type >", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("account_type >=", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeLessThan(Byte value) {
            addCriterion("account_type <", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeLessThanOrEqualTo(Byte value) {
            addCriterion("account_type <=", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeIn(List<Byte> values) {
            addCriterion("account_type in", values, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeNotIn(List<Byte> values) {
            addCriterion("account_type not in", values, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeBetween(Byte value1, Byte value2) {
            addCriterion("account_type between", value1, value2, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("account_type not between", value1, value2, "accountType");
            return (Criteria) this;
        }

        public Criteria andReconciliationNoIsNull() {
            addCriterion("reconciliation_no is null");
            return (Criteria) this;
        }

        public Criteria andReconciliationNoIsNotNull() {
            addCriterion("reconciliation_no is not null");
            return (Criteria) this;
        }

        public Criteria andReconciliationNoEqualTo(String value) {
            addCriterion("reconciliation_no =", value, "reconciliationNo");
            return (Criteria) this;
        }

        public Criteria andReconciliationNoNotEqualTo(String value) {
            addCriterion("reconciliation_no <>", value, "reconciliationNo");
            return (Criteria) this;
        }

        public Criteria andReconciliationNoGreaterThan(String value) {
            addCriterion("reconciliation_no >", value, "reconciliationNo");
            return (Criteria) this;
        }

        public Criteria andReconciliationNoGreaterThanOrEqualTo(String value) {
            addCriterion("reconciliation_no >=", value, "reconciliationNo");
            return (Criteria) this;
        }

        public Criteria andReconciliationNoLessThan(String value) {
            addCriterion("reconciliation_no <", value, "reconciliationNo");
            return (Criteria) this;
        }

        public Criteria andReconciliationNoLessThanOrEqualTo(String value) {
            addCriterion("reconciliation_no <=", value, "reconciliationNo");
            return (Criteria) this;
        }

        public Criteria andReconciliationNoLike(String value) {
            addCriterion("reconciliation_no like", value, "reconciliationNo");
            return (Criteria) this;
        }

        public Criteria andReconciliationNoNotLike(String value) {
            addCriterion("reconciliation_no not like", value, "reconciliationNo");
            return (Criteria) this;
        }

        public Criteria andReconciliationNoIn(List<String> values) {
            addCriterion("reconciliation_no in", values, "reconciliationNo");
            return (Criteria) this;
        }

        public Criteria andReconciliationNoNotIn(List<String> values) {
            addCriterion("reconciliation_no not in", values, "reconciliationNo");
            return (Criteria) this;
        }

        public Criteria andReconciliationNoBetween(String value1, String value2) {
            addCriterion("reconciliation_no between", value1, value2, "reconciliationNo");
            return (Criteria) this;
        }

        public Criteria andReconciliationNoNotBetween(String value1, String value2) {
            addCriterion("reconciliation_no not between", value1, value2, "reconciliationNo");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIsNull() {
            addCriterion("customer_name is null");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIsNotNull() {
            addCriterion("customer_name is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerNameEqualTo(String value) {
            addCriterion("customer_name =", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotEqualTo(String value) {
            addCriterion("customer_name <>", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameGreaterThan(String value) {
            addCriterion("customer_name >", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameGreaterThanOrEqualTo(String value) {
            addCriterion("customer_name >=", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLessThan(String value) {
            addCriterion("customer_name <", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLessThanOrEqualTo(String value) {
            addCriterion("customer_name <=", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLike(String value) {
            addCriterion("customer_name like", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotLike(String value) {
            addCriterion("customer_name not like", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIn(List<String> values) {
            addCriterion("customer_name in", values, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotIn(List<String> values) {
            addCriterion("customer_name not in", values, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameBetween(String value1, String value2) {
            addCriterion("customer_name between", value1, value2, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotBetween(String value1, String value2) {
            addCriterion("customer_name not between", value1, value2, "customerName");
            return (Criteria) this;
        }

        public Criteria andRebateRateIsNull() {
            addCriterion("rebate_rate is null");
            return (Criteria) this;
        }

        public Criteria andRebateRateIsNotNull() {
            addCriterion("rebate_rate is not null");
            return (Criteria) this;
        }

        public Criteria andRebateRateEqualTo(BigDecimal value) {
            addCriterion("rebate_rate =", value, "rebateRate");
            return (Criteria) this;
        }

        public Criteria andRebateRateNotEqualTo(BigDecimal value) {
            addCriterion("rebate_rate <>", value, "rebateRate");
            return (Criteria) this;
        }

        public Criteria andRebateRateGreaterThan(BigDecimal value) {
            addCriterion("rebate_rate >", value, "rebateRate");
            return (Criteria) this;
        }

        public Criteria andRebateRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("rebate_rate >=", value, "rebateRate");
            return (Criteria) this;
        }

        public Criteria andRebateRateLessThan(BigDecimal value) {
            addCriterion("rebate_rate <", value, "rebateRate");
            return (Criteria) this;
        }

        public Criteria andRebateRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("rebate_rate <=", value, "rebateRate");
            return (Criteria) this;
        }

        public Criteria andRebateRateIn(List<BigDecimal> values) {
            addCriterion("rebate_rate in", values, "rebateRate");
            return (Criteria) this;
        }

        public Criteria andRebateRateNotIn(List<BigDecimal> values) {
            addCriterion("rebate_rate not in", values, "rebateRate");
            return (Criteria) this;
        }

        public Criteria andRebateRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rebate_rate between", value1, value2, "rebateRate");
            return (Criteria) this;
        }

        public Criteria andRebateRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rebate_rate not between", value1, value2, "rebateRate");
            return (Criteria) this;
        }

        public Criteria andWaybillSourceIsNull() {
            addCriterion("waybill_source is null");
            return (Criteria) this;
        }

        public Criteria andWaybillSourceIsNotNull() {
            addCriterion("waybill_source is not null");
            return (Criteria) this;
        }

        public Criteria andWaybillSourceEqualTo(Byte value) {
            addCriterion("waybill_source =", value, "waybillSource");
            return (Criteria) this;
        }

        public Criteria andWaybillSourceNotEqualTo(Byte value) {
            addCriterion("waybill_source <>", value, "waybillSource");
            return (Criteria) this;
        }

        public Criteria andWaybillSourceGreaterThan(Byte value) {
            addCriterion("waybill_source >", value, "waybillSource");
            return (Criteria) this;
        }

        public Criteria andWaybillSourceGreaterThanOrEqualTo(Byte value) {
            addCriterion("waybill_source >=", value, "waybillSource");
            return (Criteria) this;
        }

        public Criteria andWaybillSourceLessThan(Byte value) {
            addCriterion("waybill_source <", value, "waybillSource");
            return (Criteria) this;
        }

        public Criteria andWaybillSourceLessThanOrEqualTo(Byte value) {
            addCriterion("waybill_source <=", value, "waybillSource");
            return (Criteria) this;
        }

        public Criteria andWaybillSourceIn(List<Byte> values) {
            addCriterion("waybill_source in", values, "waybillSource");
            return (Criteria) this;
        }

        public Criteria andWaybillSourceNotIn(List<Byte> values) {
            addCriterion("waybill_source not in", values, "waybillSource");
            return (Criteria) this;
        }

        public Criteria andWaybillSourceBetween(Byte value1, Byte value2) {
            addCriterion("waybill_source between", value1, value2, "waybillSource");
            return (Criteria) this;
        }

        public Criteria andWaybillSourceNotBetween(Byte value1, Byte value2) {
            addCriterion("waybill_source not between", value1, value2, "waybillSource");
            return (Criteria) this;
        }

        public Criteria andEstimateDistanceIsNull() {
            addCriterion("estimate_distance is null");
            return (Criteria) this;
        }

        public Criteria andEstimateDistanceIsNotNull() {
            addCriterion("estimate_distance is not null");
            return (Criteria) this;
        }

        public Criteria andEstimateDistanceEqualTo(Integer value) {
            addCriterion("estimate_distance =", value, "estimateDistance");
            return (Criteria) this;
        }

        public Criteria andEstimateDistanceNotEqualTo(Integer value) {
            addCriterion("estimate_distance <>", value, "estimateDistance");
            return (Criteria) this;
        }

        public Criteria andEstimateDistanceGreaterThan(Integer value) {
            addCriterion("estimate_distance >", value, "estimateDistance");
            return (Criteria) this;
        }

        public Criteria andEstimateDistanceGreaterThanOrEqualTo(Integer value) {
            addCriterion("estimate_distance >=", value, "estimateDistance");
            return (Criteria) this;
        }

        public Criteria andEstimateDistanceLessThan(Integer value) {
            addCriterion("estimate_distance <", value, "estimateDistance");
            return (Criteria) this;
        }

        public Criteria andEstimateDistanceLessThanOrEqualTo(Integer value) {
            addCriterion("estimate_distance <=", value, "estimateDistance");
            return (Criteria) this;
        }

        public Criteria andEstimateDistanceIn(List<Integer> values) {
            addCriterion("estimate_distance in", values, "estimateDistance");
            return (Criteria) this;
        }

        public Criteria andEstimateDistanceNotIn(List<Integer> values) {
            addCriterion("estimate_distance not in", values, "estimateDistance");
            return (Criteria) this;
        }

        public Criteria andEstimateDistanceBetween(Integer value1, Integer value2) {
            addCriterion("estimate_distance between", value1, value2, "estimateDistance");
            return (Criteria) this;
        }

        public Criteria andEstimateDistanceNotBetween(Integer value1, Integer value2) {
            addCriterion("estimate_distance not between", value1, value2, "estimateDistance");
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

        public Criteria andDeliveryTimeIsNull() {
            addCriterion("delivery_time is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeIsNotNull() {
            addCriterion("delivery_time is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeEqualTo(Date value) {
            addCriterion("delivery_time =", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeNotEqualTo(Date value) {
            addCriterion("delivery_time <>", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeGreaterThan(Date value) {
            addCriterion("delivery_time >", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("delivery_time >=", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeLessThan(Date value) {
            addCriterion("delivery_time <", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeLessThanOrEqualTo(Date value) {
            addCriterion("delivery_time <=", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeIn(List<Date> values) {
            addCriterion("delivery_time in", values, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeNotIn(List<Date> values) {
            addCriterion("delivery_time not in", values, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeBetween(Date value1, Date value2) {
            addCriterion("delivery_time between", value1, value2, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeNotBetween(Date value1, Date value2) {
            addCriterion("delivery_time not between", value1, value2, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andWaybillRemarkIsNull() {
            addCriterion("waybill_remark is null");
            return (Criteria) this;
        }

        public Criteria andWaybillRemarkIsNotNull() {
            addCriterion("waybill_remark is not null");
            return (Criteria) this;
        }

        public Criteria andWaybillRemarkEqualTo(String value) {
            addCriterion("waybill_remark =", value, "waybillRemark");
            return (Criteria) this;
        }

        public Criteria andWaybillRemarkNotEqualTo(String value) {
            addCriterion("waybill_remark <>", value, "waybillRemark");
            return (Criteria) this;
        }

        public Criteria andWaybillRemarkGreaterThan(String value) {
            addCriterion("waybill_remark >", value, "waybillRemark");
            return (Criteria) this;
        }

        public Criteria andWaybillRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("waybill_remark >=", value, "waybillRemark");
            return (Criteria) this;
        }

        public Criteria andWaybillRemarkLessThan(String value) {
            addCriterion("waybill_remark <", value, "waybillRemark");
            return (Criteria) this;
        }

        public Criteria andWaybillRemarkLessThanOrEqualTo(String value) {
            addCriterion("waybill_remark <=", value, "waybillRemark");
            return (Criteria) this;
        }

        public Criteria andWaybillRemarkLike(String value) {
            addCriterion("waybill_remark like", value, "waybillRemark");
            return (Criteria) this;
        }

        public Criteria andWaybillRemarkNotLike(String value) {
            addCriterion("waybill_remark not like", value, "waybillRemark");
            return (Criteria) this;
        }

        public Criteria andWaybillRemarkIn(List<String> values) {
            addCriterion("waybill_remark in", values, "waybillRemark");
            return (Criteria) this;
        }

        public Criteria andWaybillRemarkNotIn(List<String> values) {
            addCriterion("waybill_remark not in", values, "waybillRemark");
            return (Criteria) this;
        }

        public Criteria andWaybillRemarkBetween(String value1, String value2) {
            addCriterion("waybill_remark between", value1, value2, "waybillRemark");
            return (Criteria) this;
        }

        public Criteria andWaybillRemarkNotBetween(String value1, String value2) {
            addCriterion("waybill_remark not between", value1, value2, "waybillRemark");
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

        public Criteria andReceivingTimeIsNull() {
            addCriterion("receiving_time is null");
            return (Criteria) this;
        }

        public Criteria andReceivingTimeIsNotNull() {
            addCriterion("receiving_time is not null");
            return (Criteria) this;
        }

        public Criteria andReceivingTimeEqualTo(Date value) {
            addCriterion("receiving_time =", value, "receivingTime");
            return (Criteria) this;
        }

        public Criteria andReceivingTimeNotEqualTo(Date value) {
            addCriterion("receiving_time <>", value, "receivingTime");
            return (Criteria) this;
        }

        public Criteria andReceivingTimeGreaterThan(Date value) {
            addCriterion("receiving_time >", value, "receivingTime");
            return (Criteria) this;
        }

        public Criteria andReceivingTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("receiving_time >=", value, "receivingTime");
            return (Criteria) this;
        }

        public Criteria andReceivingTimeLessThan(Date value) {
            addCriterion("receiving_time <", value, "receivingTime");
            return (Criteria) this;
        }

        public Criteria andReceivingTimeLessThanOrEqualTo(Date value) {
            addCriterion("receiving_time <=", value, "receivingTime");
            return (Criteria) this;
        }

        public Criteria andReceivingTimeIn(List<Date> values) {
            addCriterion("receiving_time in", values, "receivingTime");
            return (Criteria) this;
        }

        public Criteria andReceivingTimeNotIn(List<Date> values) {
            addCriterion("receiving_time not in", values, "receivingTime");
            return (Criteria) this;
        }

        public Criteria andReceivingTimeBetween(Date value1, Date value2) {
            addCriterion("receiving_time between", value1, value2, "receivingTime");
            return (Criteria) this;
        }

        public Criteria andReceivingTimeNotBetween(Date value1, Date value2) {
            addCriterion("receiving_time not between", value1, value2, "receivingTime");
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

        public Criteria andIsDeleteIsNull() {
            addCriterion("is_delete is null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNotNull() {
            addCriterion("is_delete is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteEqualTo(Boolean value) {
            addCriterion("is_delete =", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotEqualTo(Boolean value) {
            addCriterion("is_delete <>", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThan(Boolean value) {
            addCriterion("is_delete >", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_delete >=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThan(Boolean value) {
            addCriterion("is_delete <", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThanOrEqualTo(Boolean value) {
            addCriterion("is_delete <=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIn(List<Boolean> values) {
            addCriterion("is_delete in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotIn(List<Boolean> values) {
            addCriterion("is_delete not in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteBetween(Boolean value1, Boolean value2) {
            addCriterion("is_delete between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_delete not between", value1, value2, "isDelete");
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

        public Criteria andRegionCodeIsNull() {
            addCriterion("region_code is null");
            return (Criteria) this;
        }

        public Criteria andRegionCodeIsNotNull() {
            addCriterion("region_code is not null");
            return (Criteria) this;
        }

        public Criteria andRegionCodeEqualTo(String value) {
            addCriterion("region_code =", value, "regionCode");
            return (Criteria) this;
        }

        public Criteria andRegionCodeNotEqualTo(String value) {
            addCriterion("region_code <>", value, "regionCode");
            return (Criteria) this;
        }

        public Criteria andRegionCodeGreaterThan(String value) {
            addCriterion("region_code >", value, "regionCode");
            return (Criteria) this;
        }

        public Criteria andRegionCodeGreaterThanOrEqualTo(String value) {
            addCriterion("region_code >=", value, "regionCode");
            return (Criteria) this;
        }

        public Criteria andRegionCodeLessThan(String value) {
            addCriterion("region_code <", value, "regionCode");
            return (Criteria) this;
        }

        public Criteria andRegionCodeLessThanOrEqualTo(String value) {
            addCriterion("region_code <=", value, "regionCode");
            return (Criteria) this;
        }

        public Criteria andRegionCodeLike(String value) {
            addCriterion("region_code like", value, "regionCode");
            return (Criteria) this;
        }

        public Criteria andRegionCodeNotLike(String value) {
            addCriterion("region_code not like", value, "regionCode");
            return (Criteria) this;
        }

        public Criteria andRegionCodeIn(List<String> values) {
            addCriterion("region_code in", values, "regionCode");
            return (Criteria) this;
        }

        public Criteria andRegionCodeNotIn(List<String> values) {
            addCriterion("region_code not in", values, "regionCode");
            return (Criteria) this;
        }

        public Criteria andRegionCodeBetween(String value1, String value2) {
            addCriterion("region_code between", value1, value2, "regionCode");
            return (Criteria) this;
        }

        public Criteria andRegionCodeNotBetween(String value1, String value2) {
            addCriterion("region_code not between", value1, value2, "regionCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeIsNull() {
            addCriterion("area_code is null");
            return (Criteria) this;
        }

        public Criteria andAreaCodeIsNotNull() {
            addCriterion("area_code is not null");
            return (Criteria) this;
        }

        public Criteria andAreaCodeEqualTo(String value) {
            addCriterion("area_code =", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeNotEqualTo(String value) {
            addCriterion("area_code <>", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeGreaterThan(String value) {
            addCriterion("area_code >", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeGreaterThanOrEqualTo(String value) {
            addCriterion("area_code >=", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeLessThan(String value) {
            addCriterion("area_code <", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeLessThanOrEqualTo(String value) {
            addCriterion("area_code <=", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeLike(String value) {
            addCriterion("area_code like", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeNotLike(String value) {
            addCriterion("area_code not like", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeIn(List<String> values) {
            addCriterion("area_code in", values, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeNotIn(List<String> values) {
            addCriterion("area_code not in", values, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeBetween(String value1, String value2) {
            addCriterion("area_code between", value1, value2, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeNotBetween(String value1, String value2) {
            addCriterion("area_code not between", value1, value2, "areaCode");
            return (Criteria) this;
        }

        public Criteria andIsShareIsNull() {
            addCriterion("is_share is null");
            return (Criteria) this;
        }

        public Criteria andIsShareIsNotNull() {
            addCriterion("is_share is not null");
            return (Criteria) this;
        }

        public Criteria andIsShareEqualTo(Byte value) {
            addCriterion("is_share =", value, "isShare");
            return (Criteria) this;
        }

        public Criteria andIsShareNotEqualTo(Byte value) {
            addCriterion("is_share <>", value, "isShare");
            return (Criteria) this;
        }

        public Criteria andIsShareGreaterThan(Byte value) {
            addCriterion("is_share >", value, "isShare");
            return (Criteria) this;
        }

        public Criteria andIsShareGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_share >=", value, "isShare");
            return (Criteria) this;
        }

        public Criteria andIsShareLessThan(Byte value) {
            addCriterion("is_share <", value, "isShare");
            return (Criteria) this;
        }

        public Criteria andIsShareLessThanOrEqualTo(Byte value) {
            addCriterion("is_share <=", value, "isShare");
            return (Criteria) this;
        }

        public Criteria andIsShareIn(List<Byte> values) {
            addCriterion("is_share in", values, "isShare");
            return (Criteria) this;
        }

        public Criteria andIsShareNotIn(List<Byte> values) {
            addCriterion("is_share not in", values, "isShare");
            return (Criteria) this;
        }

        public Criteria andIsShareBetween(Byte value1, Byte value2) {
            addCriterion("is_share between", value1, value2, "isShare");
            return (Criteria) this;
        }

        public Criteria andIsShareNotBetween(Byte value1, Byte value2) {
            addCriterion("is_share not between", value1, value2, "isShare");
            return (Criteria) this;
        }

        public Criteria andShareAreaCodeIsNull() {
            addCriterion("share_area_code is null");
            return (Criteria) this;
        }

        public Criteria andShareAreaCodeIsNotNull() {
            addCriterion("share_area_code is not null");
            return (Criteria) this;
        }

        public Criteria andShareAreaCodeEqualTo(String value) {
            addCriterion("share_area_code =", value, "shareAreaCode");
            return (Criteria) this;
        }

        public Criteria andShareAreaCodeNotEqualTo(String value) {
            addCriterion("share_area_code <>", value, "shareAreaCode");
            return (Criteria) this;
        }

        public Criteria andShareAreaCodeGreaterThan(String value) {
            addCriterion("share_area_code >", value, "shareAreaCode");
            return (Criteria) this;
        }

        public Criteria andShareAreaCodeGreaterThanOrEqualTo(String value) {
            addCriterion("share_area_code >=", value, "shareAreaCode");
            return (Criteria) this;
        }

        public Criteria andShareAreaCodeLessThan(String value) {
            addCriterion("share_area_code <", value, "shareAreaCode");
            return (Criteria) this;
        }

        public Criteria andShareAreaCodeLessThanOrEqualTo(String value) {
            addCriterion("share_area_code <=", value, "shareAreaCode");
            return (Criteria) this;
        }

        public Criteria andShareAreaCodeLike(String value) {
            addCriterion("share_area_code like", value, "shareAreaCode");
            return (Criteria) this;
        }

        public Criteria andShareAreaCodeNotLike(String value) {
            addCriterion("share_area_code not like", value, "shareAreaCode");
            return (Criteria) this;
        }

        public Criteria andShareAreaCodeIn(List<String> values) {
            addCriterion("share_area_code in", values, "shareAreaCode");
            return (Criteria) this;
        }

        public Criteria andShareAreaCodeNotIn(List<String> values) {
            addCriterion("share_area_code not in", values, "shareAreaCode");
            return (Criteria) this;
        }

        public Criteria andShareAreaCodeBetween(String value1, String value2) {
            addCriterion("share_area_code between", value1, value2, "shareAreaCode");
            return (Criteria) this;
        }

        public Criteria andShareAreaCodeNotBetween(String value1, String value2) {
            addCriterion("share_area_code not between", value1, value2, "shareAreaCode");
            return (Criteria) this;
        }

        public Criteria andTenantCodeIsNull() {
            addCriterion("tenant_code is null");
            return (Criteria) this;
        }

        public Criteria andTenantCodeIsNotNull() {
            addCriterion("tenant_code is not null");
            return (Criteria) this;
        }

        public Criteria andTenantCodeEqualTo(String value) {
            addCriterion("tenant_code =", value, "tenantCode");
            return (Criteria) this;
        }

        public Criteria andTenantCodeNotEqualTo(String value) {
            addCriterion("tenant_code <>", value, "tenantCode");
            return (Criteria) this;
        }

        public Criteria andTenantCodeGreaterThan(String value) {
            addCriterion("tenant_code >", value, "tenantCode");
            return (Criteria) this;
        }

        public Criteria andTenantCodeGreaterThanOrEqualTo(String value) {
            addCriterion("tenant_code >=", value, "tenantCode");
            return (Criteria) this;
        }

        public Criteria andTenantCodeLessThan(String value) {
            addCriterion("tenant_code <", value, "tenantCode");
            return (Criteria) this;
        }

        public Criteria andTenantCodeLessThanOrEqualTo(String value) {
            addCriterion("tenant_code <=", value, "tenantCode");
            return (Criteria) this;
        }

        public Criteria andTenantCodeLike(String value) {
            addCriterion("tenant_code like", value, "tenantCode");
            return (Criteria) this;
        }

        public Criteria andTenantCodeNotLike(String value) {
            addCriterion("tenant_code not like", value, "tenantCode");
            return (Criteria) this;
        }

        public Criteria andTenantCodeIn(List<String> values) {
            addCriterion("tenant_code in", values, "tenantCode");
            return (Criteria) this;
        }

        public Criteria andTenantCodeNotIn(List<String> values) {
            addCriterion("tenant_code not in", values, "tenantCode");
            return (Criteria) this;
        }

        public Criteria andTenantCodeBetween(String value1, String value2) {
            addCriterion("tenant_code between", value1, value2, "tenantCode");
            return (Criteria) this;
        }

        public Criteria andTenantCodeNotBetween(String value1, String value2) {
            addCriterion("tenant_code not between", value1, value2, "tenantCode");
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

        public Criteria andTenantIdIsNull() {
            addCriterion("tenant_id is null");
            return (Criteria) this;
        }

        public Criteria andTenantIdIsNotNull() {
            addCriterion("tenant_id is not null");
            return (Criteria) this;
        }

        public Criteria andTenantIdEqualTo(Integer value) {
            addCriterion("tenant_id =", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotEqualTo(Integer value) {
            addCriterion("tenant_id <>", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdGreaterThan(Integer value) {
            addCriterion("tenant_id >", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("tenant_id >=", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdLessThan(Integer value) {
            addCriterion("tenant_id <", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdLessThanOrEqualTo(Integer value) {
            addCriterion("tenant_id <=", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdIn(List<Integer> values) {
            addCriterion("tenant_id in", values, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotIn(List<Integer> values) {
            addCriterion("tenant_id not in", values, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdBetween(Integer value1, Integer value2) {
            addCriterion("tenant_id between", value1, value2, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotBetween(Integer value1, Integer value2) {
            addCriterion("tenant_id not between", value1, value2, "tenantId");
            return (Criteria) this;
        }

        public Criteria andFlightUsageIdIsNull() {
            addCriterion("flight_usage_id is null");
            return (Criteria) this;
        }

        public Criteria andFlightUsageIdIsNotNull() {
            addCriterion("flight_usage_id is not null");
            return (Criteria) this;
        }

        public Criteria andFlightUsageIdEqualTo(Integer value) {
            addCriterion("flight_usage_id =", value, "flightUsageId");
            return (Criteria) this;
        }

        public Criteria andFlightUsageIdNotEqualTo(Integer value) {
            addCriterion("flight_usage_id <>", value, "flightUsageId");
            return (Criteria) this;
        }

        public Criteria andFlightUsageIdGreaterThan(Integer value) {
            addCriterion("flight_usage_id >", value, "flightUsageId");
            return (Criteria) this;
        }

        public Criteria andFlightUsageIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("flight_usage_id >=", value, "flightUsageId");
            return (Criteria) this;
        }

        public Criteria andFlightUsageIdLessThan(Integer value) {
            addCriterion("flight_usage_id <", value, "flightUsageId");
            return (Criteria) this;
        }

        public Criteria andFlightUsageIdLessThanOrEqualTo(Integer value) {
            addCriterion("flight_usage_id <=", value, "flightUsageId");
            return (Criteria) this;
        }

        public Criteria andFlightUsageIdIn(List<Integer> values) {
            addCriterion("flight_usage_id in", values, "flightUsageId");
            return (Criteria) this;
        }

        public Criteria andFlightUsageIdNotIn(List<Integer> values) {
            addCriterion("flight_usage_id not in", values, "flightUsageId");
            return (Criteria) this;
        }

        public Criteria andFlightUsageIdBetween(Integer value1, Integer value2) {
            addCriterion("flight_usage_id between", value1, value2, "flightUsageId");
            return (Criteria) this;
        }

        public Criteria andFlightUsageIdNotBetween(Integer value1, Integer value2) {
            addCriterion("flight_usage_id not between", value1, value2, "flightUsageId");
            return (Criteria) this;
        }

        public Criteria andFinishTimeIsNull() {
            addCriterion("finish_time is null");
            return (Criteria) this;
        }

        public Criteria andFinishTimeIsNotNull() {
            addCriterion("finish_time is not null");
            return (Criteria) this;
        }

        public Criteria andFinishTimeEqualTo(Date value) {
            addCriterion("finish_time =", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeNotEqualTo(Date value) {
            addCriterion("finish_time <>", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeGreaterThan(Date value) {
            addCriterion("finish_time >", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("finish_time >=", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeLessThan(Date value) {
            addCriterion("finish_time <", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeLessThanOrEqualTo(Date value) {
            addCriterion("finish_time <=", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeIn(List<Date> values) {
            addCriterion("finish_time in", values, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeNotIn(List<Date> values) {
            addCriterion("finish_time not in", values, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeBetween(Date value1, Date value2) {
            addCriterion("finish_time between", value1, value2, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeNotBetween(Date value1, Date value2) {
            addCriterion("finish_time not between", value1, value2, "finishTime");
            return (Criteria) this;
        }

        public Criteria andCalculatedFreightIsNull() {
            addCriterion("calculated_freight is null");
            return (Criteria) this;
        }

        public Criteria andCalculatedFreightIsNotNull() {
            addCriterion("calculated_freight is not null");
            return (Criteria) this;
        }

        public Criteria andCalculatedFreightEqualTo(BigDecimal value) {
            addCriterion("calculated_freight =", value, "calculatedFreight");
            return (Criteria) this;
        }

        public Criteria andCalculatedFreightNotEqualTo(BigDecimal value) {
            addCriterion("calculated_freight <>", value, "calculatedFreight");
            return (Criteria) this;
        }

        public Criteria andCalculatedFreightGreaterThan(BigDecimal value) {
            addCriterion("calculated_freight >", value, "calculatedFreight");
            return (Criteria) this;
        }

        public Criteria andCalculatedFreightGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("calculated_freight >=", value, "calculatedFreight");
            return (Criteria) this;
        }

        public Criteria andCalculatedFreightLessThan(BigDecimal value) {
            addCriterion("calculated_freight <", value, "calculatedFreight");
            return (Criteria) this;
        }

        public Criteria andCalculatedFreightLessThanOrEqualTo(BigDecimal value) {
            addCriterion("calculated_freight <=", value, "calculatedFreight");
            return (Criteria) this;
        }

        public Criteria andCalculatedFreightIn(List<BigDecimal> values) {
            addCriterion("calculated_freight in", values, "calculatedFreight");
            return (Criteria) this;
        }

        public Criteria andCalculatedFreightNotIn(List<BigDecimal> values) {
            addCriterion("calculated_freight not in", values, "calculatedFreight");
            return (Criteria) this;
        }

        public Criteria andCalculatedFreightBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("calculated_freight between", value1, value2, "calculatedFreight");
            return (Criteria) this;
        }

        public Criteria andCalculatedFreightNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("calculated_freight not between", value1, value2, "calculatedFreight");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNull() {
            addCriterion("order_no is null");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNotNull() {
            addCriterion("order_no is not null");
            return (Criteria) this;
        }

        public Criteria andOrderNoEqualTo(Integer value) {
            addCriterion("order_no =", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotEqualTo(Integer value) {
            addCriterion("order_no <>", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThan(Integer value) {
            addCriterion("order_no >", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_no >=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThan(Integer value) {
            addCriterion("order_no <", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThanOrEqualTo(Integer value) {
            addCriterion("order_no <=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoIn(List<Integer> values) {
            addCriterion("order_no in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotIn(List<Integer> values) {
            addCriterion("order_no not in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoBetween(Integer value1, Integer value2) {
            addCriterion("order_no between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotBetween(Integer value1, Integer value2) {
            addCriterion("order_no not between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andAfterTaxFreightIsNull() {
            addCriterion("after_tax_freight is null");
            return (Criteria) this;
        }

        public Criteria andAfterTaxFreightIsNotNull() {
            addCriterion("after_tax_freight is not null");
            return (Criteria) this;
        }

        public Criteria andAfterTaxFreightEqualTo(BigDecimal value) {
            addCriterion("after_tax_freight =", value, "afterTaxFreight");
            return (Criteria) this;
        }

        public Criteria andAfterTaxFreightNotEqualTo(BigDecimal value) {
            addCriterion("after_tax_freight <>", value, "afterTaxFreight");
            return (Criteria) this;
        }

        public Criteria andAfterTaxFreightGreaterThan(BigDecimal value) {
            addCriterion("after_tax_freight >", value, "afterTaxFreight");
            return (Criteria) this;
        }

        public Criteria andAfterTaxFreightGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("after_tax_freight >=", value, "afterTaxFreight");
            return (Criteria) this;
        }

        public Criteria andAfterTaxFreightLessThan(BigDecimal value) {
            addCriterion("after_tax_freight <", value, "afterTaxFreight");
            return (Criteria) this;
        }

        public Criteria andAfterTaxFreightLessThanOrEqualTo(BigDecimal value) {
            addCriterion("after_tax_freight <=", value, "afterTaxFreight");
            return (Criteria) this;
        }

        public Criteria andAfterTaxFreightIn(List<BigDecimal> values) {
            addCriterion("after_tax_freight in", values, "afterTaxFreight");
            return (Criteria) this;
        }

        public Criteria andAfterTaxFreightNotIn(List<BigDecimal> values) {
            addCriterion("after_tax_freight not in", values, "afterTaxFreight");
            return (Criteria) this;
        }

        public Criteria andAfterTaxFreightBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("after_tax_freight between", value1, value2, "afterTaxFreight");
            return (Criteria) this;
        }

        public Criteria andAfterTaxFreightNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("after_tax_freight not between", value1, value2, "afterTaxFreight");
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

        public Criteria andArriveDepotTimeIsNull() {
            addCriterion("arrive_depot_time is null");
            return (Criteria) this;
        }

        public Criteria andArriveDepotTimeIsNotNull() {
            addCriterion("arrive_depot_time is not null");
            return (Criteria) this;
        }

        public Criteria andArriveDepotTimeEqualTo(Date value) {
            addCriterion("arrive_depot_time =", value, "arriveDepotTime");
            return (Criteria) this;
        }

        public Criteria andArriveDepotTimeNotEqualTo(Date value) {
            addCriterion("arrive_depot_time <>", value, "arriveDepotTime");
            return (Criteria) this;
        }

        public Criteria andArriveDepotTimeGreaterThan(Date value) {
            addCriterion("arrive_depot_time >", value, "arriveDepotTime");
            return (Criteria) this;
        }

        public Criteria andArriveDepotTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("arrive_depot_time >=", value, "arriveDepotTime");
            return (Criteria) this;
        }

        public Criteria andArriveDepotTimeLessThan(Date value) {
            addCriterion("arrive_depot_time <", value, "arriveDepotTime");
            return (Criteria) this;
        }

        public Criteria andArriveDepotTimeLessThanOrEqualTo(Date value) {
            addCriterion("arrive_depot_time <=", value, "arriveDepotTime");
            return (Criteria) this;
        }

        public Criteria andArriveDepotTimeIn(List<Date> values) {
            addCriterion("arrive_depot_time in", values, "arriveDepotTime");
            return (Criteria) this;
        }

        public Criteria andArriveDepotTimeNotIn(List<Date> values) {
            addCriterion("arrive_depot_time not in", values, "arriveDepotTime");
            return (Criteria) this;
        }

        public Criteria andArriveDepotTimeBetween(Date value1, Date value2) {
            addCriterion("arrive_depot_time between", value1, value2, "arriveDepotTime");
            return (Criteria) this;
        }

        public Criteria andArriveDepotTimeNotBetween(Date value1, Date value2) {
            addCriterion("arrive_depot_time not between", value1, value2, "arriveDepotTime");
            return (Criteria) this;
        }

        public Criteria andIsLateIsNull() {
            addCriterion("is_late is null");
            return (Criteria) this;
        }

        public Criteria andIsLateIsNotNull() {
            addCriterion("is_late is not null");
            return (Criteria) this;
        }

        public Criteria andIsLateEqualTo(Boolean value) {
            addCriterion("is_late =", value, "isLate");
            return (Criteria) this;
        }

        public Criteria andIsLateNotEqualTo(Boolean value) {
            addCriterion("is_late <>", value, "isLate");
            return (Criteria) this;
        }

        public Criteria andIsLateGreaterThan(Boolean value) {
            addCriterion("is_late >", value, "isLate");
            return (Criteria) this;
        }

        public Criteria andIsLateGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_late >=", value, "isLate");
            return (Criteria) this;
        }

        public Criteria andIsLateLessThan(Boolean value) {
            addCriterion("is_late <", value, "isLate");
            return (Criteria) this;
        }

        public Criteria andIsLateLessThanOrEqualTo(Boolean value) {
            addCriterion("is_late <=", value, "isLate");
            return (Criteria) this;
        }

        public Criteria andIsLateIn(List<Boolean> values) {
            addCriterion("is_late in", values, "isLate");
            return (Criteria) this;
        }

        public Criteria andIsLateNotIn(List<Boolean> values) {
            addCriterion("is_late not in", values, "isLate");
            return (Criteria) this;
        }

        public Criteria andIsLateBetween(Boolean value1, Boolean value2) {
            addCriterion("is_late between", value1, value2, "isLate");
            return (Criteria) this;
        }

        public Criteria andIsLateNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_late not between", value1, value2, "isLate");
            return (Criteria) this;
        }

        public Criteria andUpdateFreightRemarkIsNull() {
            addCriterion("update_freight_remark is null");
            return (Criteria) this;
        }

        public Criteria andUpdateFreightRemarkIsNotNull() {
            addCriterion("update_freight_remark is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateFreightRemarkEqualTo(String value) {
            addCriterion("update_freight_remark =", value, "updateFreightRemark");
            return (Criteria) this;
        }

        public Criteria andUpdateFreightRemarkNotEqualTo(String value) {
            addCriterion("update_freight_remark <>", value, "updateFreightRemark");
            return (Criteria) this;
        }

        public Criteria andUpdateFreightRemarkGreaterThan(String value) {
            addCriterion("update_freight_remark >", value, "updateFreightRemark");
            return (Criteria) this;
        }

        public Criteria andUpdateFreightRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("update_freight_remark >=", value, "updateFreightRemark");
            return (Criteria) this;
        }

        public Criteria andUpdateFreightRemarkLessThan(String value) {
            addCriterion("update_freight_remark <", value, "updateFreightRemark");
            return (Criteria) this;
        }

        public Criteria andUpdateFreightRemarkLessThanOrEqualTo(String value) {
            addCriterion("update_freight_remark <=", value, "updateFreightRemark");
            return (Criteria) this;
        }

        public Criteria andUpdateFreightRemarkLike(String value) {
            addCriterion("update_freight_remark like", value, "updateFreightRemark");
            return (Criteria) this;
        }

        public Criteria andUpdateFreightRemarkNotLike(String value) {
            addCriterion("update_freight_remark not like", value, "updateFreightRemark");
            return (Criteria) this;
        }

        public Criteria andUpdateFreightRemarkIn(List<String> values) {
            addCriterion("update_freight_remark in", values, "updateFreightRemark");
            return (Criteria) this;
        }

        public Criteria andUpdateFreightRemarkNotIn(List<String> values) {
            addCriterion("update_freight_remark not in", values, "updateFreightRemark");
            return (Criteria) this;
        }

        public Criteria andUpdateFreightRemarkBetween(String value1, String value2) {
            addCriterion("update_freight_remark between", value1, value2, "updateFreightRemark");
            return (Criteria) this;
        }

        public Criteria andUpdateFreightRemarkNotBetween(String value1, String value2) {
            addCriterion("update_freight_remark not between", value1, value2, "updateFreightRemark");
            return (Criteria) this;
        }

        public Criteria andUpdateFreightAuditStatusIsNull() {
            addCriterion("update_freight_audit_status is null");
            return (Criteria) this;
        }

        public Criteria andUpdateFreightAuditStatusIsNotNull() {
            addCriterion("update_freight_audit_status is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateFreightAuditStatusEqualTo(Byte value) {
            addCriterion("update_freight_audit_status =", value, "updateFreightAuditStatus");
            return (Criteria) this;
        }

        public Criteria andUpdateFreightAuditStatusNotEqualTo(Byte value) {
            addCriterion("update_freight_audit_status <>", value, "updateFreightAuditStatus");
            return (Criteria) this;
        }

        public Criteria andUpdateFreightAuditStatusGreaterThan(Byte value) {
            addCriterion("update_freight_audit_status >", value, "updateFreightAuditStatus");
            return (Criteria) this;
        }

        public Criteria andUpdateFreightAuditStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("update_freight_audit_status >=", value, "updateFreightAuditStatus");
            return (Criteria) this;
        }

        public Criteria andUpdateFreightAuditStatusLessThan(Byte value) {
            addCriterion("update_freight_audit_status <", value, "updateFreightAuditStatus");
            return (Criteria) this;
        }

        public Criteria andUpdateFreightAuditStatusLessThanOrEqualTo(Byte value) {
            addCriterion("update_freight_audit_status <=", value, "updateFreightAuditStatus");
            return (Criteria) this;
        }

        public Criteria andUpdateFreightAuditStatusIn(List<Byte> values) {
            addCriterion("update_freight_audit_status in", values, "updateFreightAuditStatus");
            return (Criteria) this;
        }

        public Criteria andUpdateFreightAuditStatusNotIn(List<Byte> values) {
            addCriterion("update_freight_audit_status not in", values, "updateFreightAuditStatus");
            return (Criteria) this;
        }

        public Criteria andUpdateFreightAuditStatusBetween(Byte value1, Byte value2) {
            addCriterion("update_freight_audit_status between", value1, value2, "updateFreightAuditStatus");
            return (Criteria) this;
        }

        public Criteria andUpdateFreightAuditStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("update_freight_audit_status not between", value1, value2, "updateFreightAuditStatus");
            return (Criteria) this;
        }

        public Criteria andFreightToBeAuditedIsNull() {
            addCriterion("freight_to_be_audited is null");
            return (Criteria) this;
        }

        public Criteria andFreightToBeAuditedIsNotNull() {
            addCriterion("freight_to_be_audited is not null");
            return (Criteria) this;
        }

        public Criteria andFreightToBeAuditedEqualTo(BigDecimal value) {
            addCriterion("freight_to_be_audited =", value, "freightToBeAudited");
            return (Criteria) this;
        }

        public Criteria andFreightToBeAuditedNotEqualTo(BigDecimal value) {
            addCriterion("freight_to_be_audited <>", value, "freightToBeAudited");
            return (Criteria) this;
        }

        public Criteria andFreightToBeAuditedGreaterThan(BigDecimal value) {
            addCriterion("freight_to_be_audited >", value, "freightToBeAudited");
            return (Criteria) this;
        }

        public Criteria andFreightToBeAuditedGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("freight_to_be_audited >=", value, "freightToBeAudited");
            return (Criteria) this;
        }

        public Criteria andFreightToBeAuditedLessThan(BigDecimal value) {
            addCriterion("freight_to_be_audited <", value, "freightToBeAudited");
            return (Criteria) this;
        }

        public Criteria andFreightToBeAuditedLessThanOrEqualTo(BigDecimal value) {
            addCriterion("freight_to_be_audited <=", value, "freightToBeAudited");
            return (Criteria) this;
        }

        public Criteria andFreightToBeAuditedIn(List<BigDecimal> values) {
            addCriterion("freight_to_be_audited in", values, "freightToBeAudited");
            return (Criteria) this;
        }

        public Criteria andFreightToBeAuditedNotIn(List<BigDecimal> values) {
            addCriterion("freight_to_be_audited not in", values, "freightToBeAudited");
            return (Criteria) this;
        }

        public Criteria andFreightToBeAuditedBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("freight_to_be_audited between", value1, value2, "freightToBeAudited");
            return (Criteria) this;
        }

        public Criteria andFreightToBeAuditedNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("freight_to_be_audited not between", value1, value2, "freightToBeAudited");
            return (Criteria) this;
        }

        public Criteria andUpdateFreightAuditRemarkIsNull() {
            addCriterion("update_freight_audit_remark is null");
            return (Criteria) this;
        }

        public Criteria andUpdateFreightAuditRemarkIsNotNull() {
            addCriterion("update_freight_audit_remark is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateFreightAuditRemarkEqualTo(String value) {
            addCriterion("update_freight_audit_remark =", value, "updateFreightAuditRemark");
            return (Criteria) this;
        }

        public Criteria andUpdateFreightAuditRemarkNotEqualTo(String value) {
            addCriterion("update_freight_audit_remark <>", value, "updateFreightAuditRemark");
            return (Criteria) this;
        }

        public Criteria andUpdateFreightAuditRemarkGreaterThan(String value) {
            addCriterion("update_freight_audit_remark >", value, "updateFreightAuditRemark");
            return (Criteria) this;
        }

        public Criteria andUpdateFreightAuditRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("update_freight_audit_remark >=", value, "updateFreightAuditRemark");
            return (Criteria) this;
        }

        public Criteria andUpdateFreightAuditRemarkLessThan(String value) {
            addCriterion("update_freight_audit_remark <", value, "updateFreightAuditRemark");
            return (Criteria) this;
        }

        public Criteria andUpdateFreightAuditRemarkLessThanOrEqualTo(String value) {
            addCriterion("update_freight_audit_remark <=", value, "updateFreightAuditRemark");
            return (Criteria) this;
        }

        public Criteria andUpdateFreightAuditRemarkLike(String value) {
            addCriterion("update_freight_audit_remark like", value, "updateFreightAuditRemark");
            return (Criteria) this;
        }

        public Criteria andUpdateFreightAuditRemarkNotLike(String value) {
            addCriterion("update_freight_audit_remark not like", value, "updateFreightAuditRemark");
            return (Criteria) this;
        }

        public Criteria andUpdateFreightAuditRemarkIn(List<String> values) {
            addCriterion("update_freight_audit_remark in", values, "updateFreightAuditRemark");
            return (Criteria) this;
        }

        public Criteria andUpdateFreightAuditRemarkNotIn(List<String> values) {
            addCriterion("update_freight_audit_remark not in", values, "updateFreightAuditRemark");
            return (Criteria) this;
        }

        public Criteria andUpdateFreightAuditRemarkBetween(String value1, String value2) {
            addCriterion("update_freight_audit_remark between", value1, value2, "updateFreightAuditRemark");
            return (Criteria) this;
        }

        public Criteria andUpdateFreightAuditRemarkNotBetween(String value1, String value2) {
            addCriterion("update_freight_audit_remark not between", value1, value2, "updateFreightAuditRemark");
            return (Criteria) this;
        }

        public Criteria andAssignWaybillRemarkIsNull() {
            addCriterion("assign_waybill_remark is null");
            return (Criteria) this;
        }

        public Criteria andAssignWaybillRemarkIsNotNull() {
            addCriterion("assign_waybill_remark is not null");
            return (Criteria) this;
        }

        public Criteria andAssignWaybillRemarkEqualTo(String value) {
            addCriterion("assign_waybill_remark =", value, "assignWaybillRemark");
            return (Criteria) this;
        }

        public Criteria andAssignWaybillRemarkNotEqualTo(String value) {
            addCriterion("assign_waybill_remark <>", value, "assignWaybillRemark");
            return (Criteria) this;
        }

        public Criteria andAssignWaybillRemarkGreaterThan(String value) {
            addCriterion("assign_waybill_remark >", value, "assignWaybillRemark");
            return (Criteria) this;
        }

        public Criteria andAssignWaybillRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("assign_waybill_remark >=", value, "assignWaybillRemark");
            return (Criteria) this;
        }

        public Criteria andAssignWaybillRemarkLessThan(String value) {
            addCriterion("assign_waybill_remark <", value, "assignWaybillRemark");
            return (Criteria) this;
        }

        public Criteria andAssignWaybillRemarkLessThanOrEqualTo(String value) {
            addCriterion("assign_waybill_remark <=", value, "assignWaybillRemark");
            return (Criteria) this;
        }

        public Criteria andAssignWaybillRemarkLike(String value) {
            addCriterion("assign_waybill_remark like", value, "assignWaybillRemark");
            return (Criteria) this;
        }

        public Criteria andAssignWaybillRemarkNotLike(String value) {
            addCriterion("assign_waybill_remark not like", value, "assignWaybillRemark");
            return (Criteria) this;
        }

        public Criteria andAssignWaybillRemarkIn(List<String> values) {
            addCriterion("assign_waybill_remark in", values, "assignWaybillRemark");
            return (Criteria) this;
        }

        public Criteria andAssignWaybillRemarkNotIn(List<String> values) {
            addCriterion("assign_waybill_remark not in", values, "assignWaybillRemark");
            return (Criteria) this;
        }

        public Criteria andAssignWaybillRemarkBetween(String value1, String value2) {
            addCriterion("assign_waybill_remark between", value1, value2, "assignWaybillRemark");
            return (Criteria) this;
        }

        public Criteria andAssignWaybillRemarkNotBetween(String value1, String value2) {
            addCriterion("assign_waybill_remark not between", value1, value2, "assignWaybillRemark");
            return (Criteria) this;
        }

        public Criteria andPaymentRouteIsNull() {
            addCriterion("payment_route is null");
            return (Criteria) this;
        }

        public Criteria andPaymentRouteIsNotNull() {
            addCriterion("payment_route is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentRouteEqualTo(Byte value) {
            addCriterion("payment_route =", value, "paymentRoute");
            return (Criteria) this;
        }

        public Criteria andPaymentRouteNotEqualTo(Byte value) {
            addCriterion("payment_route <>", value, "paymentRoute");
            return (Criteria) this;
        }

        public Criteria andPaymentRouteGreaterThan(Byte value) {
            addCriterion("payment_route >", value, "paymentRoute");
            return (Criteria) this;
        }

        public Criteria andPaymentRouteGreaterThanOrEqualTo(Byte value) {
            addCriterion("payment_route >=", value, "paymentRoute");
            return (Criteria) this;
        }

        public Criteria andPaymentRouteLessThan(Byte value) {
            addCriterion("payment_route <", value, "paymentRoute");
            return (Criteria) this;
        }

        public Criteria andPaymentRouteLessThanOrEqualTo(Byte value) {
            addCriterion("payment_route <=", value, "paymentRoute");
            return (Criteria) this;
        }

        public Criteria andPaymentRouteIn(List<Byte> values) {
            addCriterion("payment_route in", values, "paymentRoute");
            return (Criteria) this;
        }

        public Criteria andPaymentRouteNotIn(List<Byte> values) {
            addCriterion("payment_route not in", values, "paymentRoute");
            return (Criteria) this;
        }

        public Criteria andPaymentRouteBetween(Byte value1, Byte value2) {
            addCriterion("payment_route between", value1, value2, "paymentRoute");
            return (Criteria) this;
        }

        public Criteria andPaymentRouteNotBetween(Byte value1, Byte value2) {
            addCriterion("payment_route not between", value1, value2, "paymentRoute");
            return (Criteria) this;
        }

        public Criteria andCompareResultIsNull() {
            addCriterion("compare_result is null");
            return (Criteria) this;
        }

        public Criteria andCompareResultIsNotNull() {
            addCriterion("compare_result is not null");
            return (Criteria) this;
        }

        public Criteria andCompareResultEqualTo(BigDecimal value) {
            addCriterion("compare_result =", value, "compareResult");
            return (Criteria) this;
        }

        public Criteria andCompareResultNotEqualTo(BigDecimal value) {
            addCriterion("compare_result <>", value, "compareResult");
            return (Criteria) this;
        }

        public Criteria andCompareResultGreaterThan(BigDecimal value) {
            addCriterion("compare_result >", value, "compareResult");
            return (Criteria) this;
        }

        public Criteria andCompareResultGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("compare_result >=", value, "compareResult");
            return (Criteria) this;
        }

        public Criteria andCompareResultLessThan(BigDecimal value) {
            addCriterion("compare_result <", value, "compareResult");
            return (Criteria) this;
        }

        public Criteria andCompareResultLessThanOrEqualTo(BigDecimal value) {
            addCriterion("compare_result <=", value, "compareResult");
            return (Criteria) this;
        }

        public Criteria andCompareResultIn(List<BigDecimal> values) {
            addCriterion("compare_result in", values, "compareResult");
            return (Criteria) this;
        }

        public Criteria andCompareResultNotIn(List<BigDecimal> values) {
            addCriterion("compare_result not in", values, "compareResult");
            return (Criteria) this;
        }

        public Criteria andCompareResultBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("compare_result between", value1, value2, "compareResult");
            return (Criteria) this;
        }

        public Criteria andCompareResultNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("compare_result not between", value1, value2, "compareResult");
            return (Criteria) this;
        }

        public Criteria andActualMileageIsNull() {
            addCriterion("actual_mileage is null");
            return (Criteria) this;
        }

        public Criteria andActualMileageIsNotNull() {
            addCriterion("actual_mileage is not null");
            return (Criteria) this;
        }

        public Criteria andActualMileageEqualTo(Integer value) {
            addCriterion("actual_mileage =", value, "actualMileage");
            return (Criteria) this;
        }

        public Criteria andActualMileageNotEqualTo(Integer value) {
            addCriterion("actual_mileage <>", value, "actualMileage");
            return (Criteria) this;
        }

        public Criteria andActualMileageGreaterThan(Integer value) {
            addCriterion("actual_mileage >", value, "actualMileage");
            return (Criteria) this;
        }

        public Criteria andActualMileageGreaterThanOrEqualTo(Integer value) {
            addCriterion("actual_mileage >=", value, "actualMileage");
            return (Criteria) this;
        }

        public Criteria andActualMileageLessThan(Integer value) {
            addCriterion("actual_mileage <", value, "actualMileage");
            return (Criteria) this;
        }

        public Criteria andActualMileageLessThanOrEqualTo(Integer value) {
            addCriterion("actual_mileage <=", value, "actualMileage");
            return (Criteria) this;
        }

        public Criteria andActualMileageIn(List<Integer> values) {
            addCriterion("actual_mileage in", values, "actualMileage");
            return (Criteria) this;
        }

        public Criteria andActualMileageNotIn(List<Integer> values) {
            addCriterion("actual_mileage not in", values, "actualMileage");
            return (Criteria) this;
        }

        public Criteria andActualMileageBetween(Integer value1, Integer value2) {
            addCriterion("actual_mileage between", value1, value2, "actualMileage");
            return (Criteria) this;
        }

        public Criteria andActualMileageNotBetween(Integer value1, Integer value2) {
            addCriterion("actual_mileage not between", value1, value2, "actualMileage");
            return (Criteria) this;
        }

        public Criteria andIsCheckoutIsNull() {
            addCriterion("is_checkout is null");
            return (Criteria) this;
        }

        public Criteria andIsCheckoutIsNotNull() {
            addCriterion("is_checkout is not null");
            return (Criteria) this;
        }

        public Criteria andIsCheckoutEqualTo(Boolean value) {
            addCriterion("is_checkout =", value, "isCheckout");
            return (Criteria) this;
        }

        public Criteria andIsCheckoutNotEqualTo(Boolean value) {
            addCriterion("is_checkout <>", value, "isCheckout");
            return (Criteria) this;
        }

        public Criteria andIsCheckoutGreaterThan(Boolean value) {
            addCriterion("is_checkout >", value, "isCheckout");
            return (Criteria) this;
        }

        public Criteria andIsCheckoutGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_checkout >=", value, "isCheckout");
            return (Criteria) this;
        }

        public Criteria andIsCheckoutLessThan(Boolean value) {
            addCriterion("is_checkout <", value, "isCheckout");
            return (Criteria) this;
        }

        public Criteria andIsCheckoutLessThanOrEqualTo(Boolean value) {
            addCriterion("is_checkout <=", value, "isCheckout");
            return (Criteria) this;
        }

        public Criteria andIsCheckoutIn(List<Boolean> values) {
            addCriterion("is_checkout in", values, "isCheckout");
            return (Criteria) this;
        }

        public Criteria andIsCheckoutNotIn(List<Boolean> values) {
            addCriterion("is_checkout not in", values, "isCheckout");
            return (Criteria) this;
        }

        public Criteria andIsCheckoutBetween(Boolean value1, Boolean value2) {
            addCriterion("is_checkout between", value1, value2, "isCheckout");
            return (Criteria) this;
        }

        public Criteria andIsCheckoutNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_checkout not between", value1, value2, "isCheckout");
            return (Criteria) this;
        }

        public Criteria andCheckoutTimeIsNull() {
            addCriterion("checkout_time is null");
            return (Criteria) this;
        }

        public Criteria andCheckoutTimeIsNotNull() {
            addCriterion("checkout_time is not null");
            return (Criteria) this;
        }

        public Criteria andCheckoutTimeEqualTo(Date value) {
            addCriterion("checkout_time =", value, "checkoutTime");
            return (Criteria) this;
        }

        public Criteria andCheckoutTimeNotEqualTo(Date value) {
            addCriterion("checkout_time <>", value, "checkoutTime");
            return (Criteria) this;
        }

        public Criteria andCheckoutTimeGreaterThan(Date value) {
            addCriterion("checkout_time >", value, "checkoutTime");
            return (Criteria) this;
        }

        public Criteria andCheckoutTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("checkout_time >=", value, "checkoutTime");
            return (Criteria) this;
        }

        public Criteria andCheckoutTimeLessThan(Date value) {
            addCriterion("checkout_time <", value, "checkoutTime");
            return (Criteria) this;
        }

        public Criteria andCheckoutTimeLessThanOrEqualTo(Date value) {
            addCriterion("checkout_time <=", value, "checkoutTime");
            return (Criteria) this;
        }

        public Criteria andCheckoutTimeIn(List<Date> values) {
            addCriterion("checkout_time in", values, "checkoutTime");
            return (Criteria) this;
        }

        public Criteria andCheckoutTimeNotIn(List<Date> values) {
            addCriterion("checkout_time not in", values, "checkoutTime");
            return (Criteria) this;
        }

        public Criteria andCheckoutTimeBetween(Date value1, Date value2) {
            addCriterion("checkout_time between", value1, value2, "checkoutTime");
            return (Criteria) this;
        }

        public Criteria andCheckoutTimeNotBetween(Date value1, Date value2) {
            addCriterion("checkout_time not between", value1, value2, "checkoutTime");
            return (Criteria) this;
        }

        public Criteria andReconciliationStatusIsNull() {
            addCriterion("reconciliation_status is null");
            return (Criteria) this;
        }

        public Criteria andReconciliationStatusIsNotNull() {
            addCriterion("reconciliation_status is not null");
            return (Criteria) this;
        }

        public Criteria andReconciliationStatusEqualTo(Byte value) {
            addCriterion("reconciliation_status =", value, "reconciliationStatus");
            return (Criteria) this;
        }

        public Criteria andReconciliationStatusNotEqualTo(Byte value) {
            addCriterion("reconciliation_status <>", value, "reconciliationStatus");
            return (Criteria) this;
        }

        public Criteria andReconciliationStatusGreaterThan(Byte value) {
            addCriterion("reconciliation_status >", value, "reconciliationStatus");
            return (Criteria) this;
        }

        public Criteria andReconciliationStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("reconciliation_status >=", value, "reconciliationStatus");
            return (Criteria) this;
        }

        public Criteria andReconciliationStatusLessThan(Byte value) {
            addCriterion("reconciliation_status <", value, "reconciliationStatus");
            return (Criteria) this;
        }

        public Criteria andReconciliationStatusLessThanOrEqualTo(Byte value) {
            addCriterion("reconciliation_status <=", value, "reconciliationStatus");
            return (Criteria) this;
        }

        public Criteria andReconciliationStatusIn(List<Byte> values) {
            addCriterion("reconciliation_status in", values, "reconciliationStatus");
            return (Criteria) this;
        }

        public Criteria andReconciliationStatusNotIn(List<Byte> values) {
            addCriterion("reconciliation_status not in", values, "reconciliationStatus");
            return (Criteria) this;
        }

        public Criteria andReconciliationStatusBetween(Byte value1, Byte value2) {
            addCriterion("reconciliation_status between", value1, value2, "reconciliationStatus");
            return (Criteria) this;
        }

        public Criteria andReconciliationStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("reconciliation_status not between", value1, value2, "reconciliationStatus");
            return (Criteria) this;
        }

        public Criteria andTollsIsNull() {
            addCriterion("tolls is null");
            return (Criteria) this;
        }

        public Criteria andTollsIsNotNull() {
            addCriterion("tolls is not null");
            return (Criteria) this;
        }

        public Criteria andTollsEqualTo(BigDecimal value) {
            addCriterion("tolls =", value, "tolls");
            return (Criteria) this;
        }

        public Criteria andTollsNotEqualTo(BigDecimal value) {
            addCriterion("tolls <>", value, "tolls");
            return (Criteria) this;
        }

        public Criteria andTollsGreaterThan(BigDecimal value) {
            addCriterion("tolls >", value, "tolls");
            return (Criteria) this;
        }

        public Criteria andTollsGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("tolls >=", value, "tolls");
            return (Criteria) this;
        }

        public Criteria andTollsLessThan(BigDecimal value) {
            addCriterion("tolls <", value, "tolls");
            return (Criteria) this;
        }

        public Criteria andTollsLessThanOrEqualTo(BigDecimal value) {
            addCriterion("tolls <=", value, "tolls");
            return (Criteria) this;
        }

        public Criteria andTollsIn(List<BigDecimal> values) {
            addCriterion("tolls in", values, "tolls");
            return (Criteria) this;
        }

        public Criteria andTollsNotIn(List<BigDecimal> values) {
            addCriterion("tolls not in", values, "tolls");
            return (Criteria) this;
        }

        public Criteria andTollsBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("tolls between", value1, value2, "tolls");
            return (Criteria) this;
        }

        public Criteria andTollsNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("tolls not between", value1, value2, "tolls");
            return (Criteria) this;
        }

        public Criteria andNeedReceiptIsNull() {
            addCriterion("need_receipt is null");
            return (Criteria) this;
        }

        public Criteria andNeedReceiptIsNotNull() {
            addCriterion("need_receipt is not null");
            return (Criteria) this;
        }

        public Criteria andNeedReceiptEqualTo(Byte value) {
            addCriterion("need_receipt =", value, "needReceipt");
            return (Criteria) this;
        }

        public Criteria andNeedReceiptNotEqualTo(Byte value) {
            addCriterion("need_receipt <>", value, "needReceipt");
            return (Criteria) this;
        }

        public Criteria andNeedReceiptGreaterThan(Byte value) {
            addCriterion("need_receipt >", value, "needReceipt");
            return (Criteria) this;
        }

        public Criteria andNeedReceiptGreaterThanOrEqualTo(Byte value) {
            addCriterion("need_receipt >=", value, "needReceipt");
            return (Criteria) this;
        }

        public Criteria andNeedReceiptLessThan(Byte value) {
            addCriterion("need_receipt <", value, "needReceipt");
            return (Criteria) this;
        }

        public Criteria andNeedReceiptLessThanOrEqualTo(Byte value) {
            addCriterion("need_receipt <=", value, "needReceipt");
            return (Criteria) this;
        }

        public Criteria andNeedReceiptIn(List<Byte> values) {
            addCriterion("need_receipt in", values, "needReceipt");
            return (Criteria) this;
        }

        public Criteria andNeedReceiptNotIn(List<Byte> values) {
            addCriterion("need_receipt not in", values, "needReceipt");
            return (Criteria) this;
        }

        public Criteria andNeedReceiptBetween(Byte value1, Byte value2) {
            addCriterion("need_receipt between", value1, value2, "needReceipt");
            return (Criteria) this;
        }

        public Criteria andNeedReceiptNotBetween(Byte value1, Byte value2) {
            addCriterion("need_receipt not between", value1, value2, "needReceipt");
            return (Criteria) this;
        }

        public Criteria andWaybillCancelRemarkIsNull() {
            addCriterion("waybill_cancel_remark is null");
            return (Criteria) this;
        }

        public Criteria andWaybillCancelRemarkIsNotNull() {
            addCriterion("waybill_cancel_remark is not null");
            return (Criteria) this;
        }

        public Criteria andWaybillCancelRemarkEqualTo(String value) {
            addCriterion("waybill_cancel_remark =", value, "waybillCancelRemark");
            return (Criteria) this;
        }

        public Criteria andWaybillCancelRemarkNotEqualTo(String value) {
            addCriterion("waybill_cancel_remark <>", value, "waybillCancelRemark");
            return (Criteria) this;
        }

        public Criteria andWaybillCancelRemarkGreaterThan(String value) {
            addCriterion("waybill_cancel_remark >", value, "waybillCancelRemark");
            return (Criteria) this;
        }

        public Criteria andWaybillCancelRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("waybill_cancel_remark >=", value, "waybillCancelRemark");
            return (Criteria) this;
        }

        public Criteria andWaybillCancelRemarkLessThan(String value) {
            addCriterion("waybill_cancel_remark <", value, "waybillCancelRemark");
            return (Criteria) this;
        }

        public Criteria andWaybillCancelRemarkLessThanOrEqualTo(String value) {
            addCriterion("waybill_cancel_remark <=", value, "waybillCancelRemark");
            return (Criteria) this;
        }

        public Criteria andWaybillCancelRemarkLike(String value) {
            addCriterion("waybill_cancel_remark like", value, "waybillCancelRemark");
            return (Criteria) this;
        }

        public Criteria andWaybillCancelRemarkNotLike(String value) {
            addCriterion("waybill_cancel_remark not like", value, "waybillCancelRemark");
            return (Criteria) this;
        }

        public Criteria andWaybillCancelRemarkIn(List<String> values) {
            addCriterion("waybill_cancel_remark in", values, "waybillCancelRemark");
            return (Criteria) this;
        }

        public Criteria andWaybillCancelRemarkNotIn(List<String> values) {
            addCriterion("waybill_cancel_remark not in", values, "waybillCancelRemark");
            return (Criteria) this;
        }

        public Criteria andWaybillCancelRemarkBetween(String value1, String value2) {
            addCriterion("waybill_cancel_remark between", value1, value2, "waybillCancelRemark");
            return (Criteria) this;
        }

        public Criteria andWaybillCancelRemarkNotBetween(String value1, String value2) {
            addCriterion("waybill_cancel_remark not between", value1, value2, "waybillCancelRemark");
            return (Criteria) this;
        }

        public Criteria andIsSubmitMatchIsNull() {
            addCriterion("is_submit_match is null");
            return (Criteria) this;
        }

        public Criteria andIsSubmitMatchIsNotNull() {
            addCriterion("is_submit_match is not null");
            return (Criteria) this;
        }

        public Criteria andIsSubmitMatchEqualTo(Boolean value) {
            addCriterion("is_submit_match =", value, "isSubmitMatch");
            return (Criteria) this;
        }

        public Criteria andIsSubmitMatchNotEqualTo(Boolean value) {
            addCriterion("is_submit_match <>", value, "isSubmitMatch");
            return (Criteria) this;
        }

        public Criteria andIsSubmitMatchGreaterThan(Boolean value) {
            addCriterion("is_submit_match >", value, "isSubmitMatch");
            return (Criteria) this;
        }

        public Criteria andIsSubmitMatchGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_submit_match >=", value, "isSubmitMatch");
            return (Criteria) this;
        }

        public Criteria andIsSubmitMatchLessThan(Boolean value) {
            addCriterion("is_submit_match <", value, "isSubmitMatch");
            return (Criteria) this;
        }

        public Criteria andIsSubmitMatchLessThanOrEqualTo(Boolean value) {
            addCriterion("is_submit_match <=", value, "isSubmitMatch");
            return (Criteria) this;
        }

        public Criteria andIsSubmitMatchIn(List<Boolean> values) {
            addCriterion("is_submit_match in", values, "isSubmitMatch");
            return (Criteria) this;
        }

        public Criteria andIsSubmitMatchNotIn(List<Boolean> values) {
            addCriterion("is_submit_match not in", values, "isSubmitMatch");
            return (Criteria) this;
        }

        public Criteria andIsSubmitMatchBetween(Boolean value1, Boolean value2) {
            addCriterion("is_submit_match between", value1, value2, "isSubmitMatch");
            return (Criteria) this;
        }

        public Criteria andIsSubmitMatchNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_submit_match not between", value1, value2, "isSubmitMatch");
            return (Criteria) this;
        }

        public Criteria andWaybillUnbundlingReasonIsNull() {
            addCriterion("waybill_unbundling_reason is null");
            return (Criteria) this;
        }

        public Criteria andWaybillUnbundlingReasonIsNotNull() {
            addCriterion("waybill_unbundling_reason is not null");
            return (Criteria) this;
        }

        public Criteria andWaybillUnbundlingReasonEqualTo(String value) {
            addCriterion("waybill_unbundling_reason =", value, "waybillUnbundlingReason");
            return (Criteria) this;
        }

        public Criteria andWaybillUnbundlingReasonNotEqualTo(String value) {
            addCriterion("waybill_unbundling_reason <>", value, "waybillUnbundlingReason");
            return (Criteria) this;
        }

        public Criteria andWaybillUnbundlingReasonGreaterThan(String value) {
            addCriterion("waybill_unbundling_reason >", value, "waybillUnbundlingReason");
            return (Criteria) this;
        }

        public Criteria andWaybillUnbundlingReasonGreaterThanOrEqualTo(String value) {
            addCriterion("waybill_unbundling_reason >=", value, "waybillUnbundlingReason");
            return (Criteria) this;
        }

        public Criteria andWaybillUnbundlingReasonLessThan(String value) {
            addCriterion("waybill_unbundling_reason <", value, "waybillUnbundlingReason");
            return (Criteria) this;
        }

        public Criteria andWaybillUnbundlingReasonLessThanOrEqualTo(String value) {
            addCriterion("waybill_unbundling_reason <=", value, "waybillUnbundlingReason");
            return (Criteria) this;
        }

        public Criteria andWaybillUnbundlingReasonLike(String value) {
            addCriterion("waybill_unbundling_reason like", value, "waybillUnbundlingReason");
            return (Criteria) this;
        }

        public Criteria andWaybillUnbundlingReasonNotLike(String value) {
            addCriterion("waybill_unbundling_reason not like", value, "waybillUnbundlingReason");
            return (Criteria) this;
        }

        public Criteria andWaybillUnbundlingReasonIn(List<String> values) {
            addCriterion("waybill_unbundling_reason in", values, "waybillUnbundlingReason");
            return (Criteria) this;
        }

        public Criteria andWaybillUnbundlingReasonNotIn(List<String> values) {
            addCriterion("waybill_unbundling_reason not in", values, "waybillUnbundlingReason");
            return (Criteria) this;
        }

        public Criteria andWaybillUnbundlingReasonBetween(String value1, String value2) {
            addCriterion("waybill_unbundling_reason between", value1, value2, "waybillUnbundlingReason");
            return (Criteria) this;
        }

        public Criteria andWaybillUnbundlingReasonNotBetween(String value1, String value2) {
            addCriterion("waybill_unbundling_reason not between", value1, value2, "waybillUnbundlingReason");
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

        public Criteria andCustomerManagerNameIsNull() {
            addCriterion("customer_manager_name is null");
            return (Criteria) this;
        }

        public Criteria andCustomerManagerNameIsNotNull() {
            addCriterion("customer_manager_name is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerManagerNameEqualTo(String value) {
            addCriterion("customer_manager_name =", value, "customerManagerName");
            return (Criteria) this;
        }

        public Criteria andCustomerManagerNameNotEqualTo(String value) {
            addCriterion("customer_manager_name <>", value, "customerManagerName");
            return (Criteria) this;
        }

        public Criteria andCustomerManagerNameGreaterThan(String value) {
            addCriterion("customer_manager_name >", value, "customerManagerName");
            return (Criteria) this;
        }

        public Criteria andCustomerManagerNameGreaterThanOrEqualTo(String value) {
            addCriterion("customer_manager_name >=", value, "customerManagerName");
            return (Criteria) this;
        }

        public Criteria andCustomerManagerNameLessThan(String value) {
            addCriterion("customer_manager_name <", value, "customerManagerName");
            return (Criteria) this;
        }

        public Criteria andCustomerManagerNameLessThanOrEqualTo(String value) {
            addCriterion("customer_manager_name <=", value, "customerManagerName");
            return (Criteria) this;
        }

        public Criteria andCustomerManagerNameLike(String value) {
            addCriterion("customer_manager_name like", value, "customerManagerName");
            return (Criteria) this;
        }

        public Criteria andCustomerManagerNameNotLike(String value) {
            addCriterion("customer_manager_name not like", value, "customerManagerName");
            return (Criteria) this;
        }

        public Criteria andCustomerManagerNameIn(List<String> values) {
            addCriterion("customer_manager_name in", values, "customerManagerName");
            return (Criteria) this;
        }

        public Criteria andCustomerManagerNameNotIn(List<String> values) {
            addCriterion("customer_manager_name not in", values, "customerManagerName");
            return (Criteria) this;
        }

        public Criteria andCustomerManagerNameBetween(String value1, String value2) {
            addCriterion("customer_manager_name between", value1, value2, "customerManagerName");
            return (Criteria) this;
        }

        public Criteria andCustomerManagerNameNotBetween(String value1, String value2) {
            addCriterion("customer_manager_name not between", value1, value2, "customerManagerName");
            return (Criteria) this;
        }

        public Criteria andAssignCarFeedbackIsNull() {
            addCriterion("assign_car_feedback is null");
            return (Criteria) this;
        }

        public Criteria andAssignCarFeedbackIsNotNull() {
            addCriterion("assign_car_feedback is not null");
            return (Criteria) this;
        }

        public Criteria andAssignCarFeedbackEqualTo(String value) {
            addCriterion("assign_car_feedback =", value, "assignCarFeedback");
            return (Criteria) this;
        }

        public Criteria andAssignCarFeedbackNotEqualTo(String value) {
            addCriterion("assign_car_feedback <>", value, "assignCarFeedback");
            return (Criteria) this;
        }

        public Criteria andAssignCarFeedbackGreaterThan(String value) {
            addCriterion("assign_car_feedback >", value, "assignCarFeedback");
            return (Criteria) this;
        }

        public Criteria andAssignCarFeedbackGreaterThanOrEqualTo(String value) {
            addCriterion("assign_car_feedback >=", value, "assignCarFeedback");
            return (Criteria) this;
        }

        public Criteria andAssignCarFeedbackLessThan(String value) {
            addCriterion("assign_car_feedback <", value, "assignCarFeedback");
            return (Criteria) this;
        }

        public Criteria andAssignCarFeedbackLessThanOrEqualTo(String value) {
            addCriterion("assign_car_feedback <=", value, "assignCarFeedback");
            return (Criteria) this;
        }

        public Criteria andAssignCarFeedbackLike(String value) {
            addCriterion("assign_car_feedback like", value, "assignCarFeedback");
            return (Criteria) this;
        }

        public Criteria andAssignCarFeedbackNotLike(String value) {
            addCriterion("assign_car_feedback not like", value, "assignCarFeedback");
            return (Criteria) this;
        }

        public Criteria andAssignCarFeedbackIn(List<String> values) {
            addCriterion("assign_car_feedback in", values, "assignCarFeedback");
            return (Criteria) this;
        }

        public Criteria andAssignCarFeedbackNotIn(List<String> values) {
            addCriterion("assign_car_feedback not in", values, "assignCarFeedback");
            return (Criteria) this;
        }

        public Criteria andAssignCarFeedbackBetween(String value1, String value2) {
            addCriterion("assign_car_feedback between", value1, value2, "assignCarFeedback");
            return (Criteria) this;
        }

        public Criteria andAssignCarFeedbackNotBetween(String value1, String value2) {
            addCriterion("assign_car_feedback not between", value1, value2, "assignCarFeedback");
            return (Criteria) this;
        }

        public Criteria andEntryLicenseIsNull() {
            addCriterion("entry_license is null");
            return (Criteria) this;
        }

        public Criteria andEntryLicenseIsNotNull() {
            addCriterion("entry_license is not null");
            return (Criteria) this;
        }

        public Criteria andEntryLicenseEqualTo(Byte value) {
            addCriterion("entry_license =", value, "entryLicense");
            return (Criteria) this;
        }

        public Criteria andEntryLicenseNotEqualTo(Byte value) {
            addCriterion("entry_license <>", value, "entryLicense");
            return (Criteria) this;
        }

        public Criteria andEntryLicenseGreaterThan(Byte value) {
            addCriterion("entry_license >", value, "entryLicense");
            return (Criteria) this;
        }

        public Criteria andEntryLicenseGreaterThanOrEqualTo(Byte value) {
            addCriterion("entry_license >=", value, "entryLicense");
            return (Criteria) this;
        }

        public Criteria andEntryLicenseLessThan(Byte value) {
            addCriterion("entry_license <", value, "entryLicense");
            return (Criteria) this;
        }

        public Criteria andEntryLicenseLessThanOrEqualTo(Byte value) {
            addCriterion("entry_license <=", value, "entryLicense");
            return (Criteria) this;
        }

        public Criteria andEntryLicenseIn(List<Byte> values) {
            addCriterion("entry_license in", values, "entryLicense");
            return (Criteria) this;
        }

        public Criteria andEntryLicenseNotIn(List<Byte> values) {
            addCriterion("entry_license not in", values, "entryLicense");
            return (Criteria) this;
        }

        public Criteria andEntryLicenseBetween(Byte value1, Byte value2) {
            addCriterion("entry_license between", value1, value2, "entryLicense");
            return (Criteria) this;
        }

        public Criteria andEntryLicenseNotBetween(Byte value1, Byte value2) {
            addCriterion("entry_license not between", value1, value2, "entryLicense");
            return (Criteria) this;
        }

        public Criteria andGoodsWeightIsNull() {
            addCriterion("goods_weight is null");
            return (Criteria) this;
        }

        public Criteria andGoodsWeightIsNotNull() {
            addCriterion("goods_weight is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsWeightEqualTo(Integer value) {
            addCriterion("goods_weight =", value, "goodsWeight");
            return (Criteria) this;
        }

        public Criteria andGoodsWeightNotEqualTo(Integer value) {
            addCriterion("goods_weight <>", value, "goodsWeight");
            return (Criteria) this;
        }

        public Criteria andGoodsWeightGreaterThan(Integer value) {
            addCriterion("goods_weight >", value, "goodsWeight");
            return (Criteria) this;
        }

        public Criteria andGoodsWeightGreaterThanOrEqualTo(Integer value) {
            addCriterion("goods_weight >=", value, "goodsWeight");
            return (Criteria) this;
        }

        public Criteria andGoodsWeightLessThan(Integer value) {
            addCriterion("goods_weight <", value, "goodsWeight");
            return (Criteria) this;
        }

        public Criteria andGoodsWeightLessThanOrEqualTo(Integer value) {
            addCriterion("goods_weight <=", value, "goodsWeight");
            return (Criteria) this;
        }

        public Criteria andGoodsWeightIn(List<Integer> values) {
            addCriterion("goods_weight in", values, "goodsWeight");
            return (Criteria) this;
        }

        public Criteria andGoodsWeightNotIn(List<Integer> values) {
            addCriterion("goods_weight not in", values, "goodsWeight");
            return (Criteria) this;
        }

        public Criteria andGoodsWeightBetween(Integer value1, Integer value2) {
            addCriterion("goods_weight between", value1, value2, "goodsWeight");
            return (Criteria) this;
        }

        public Criteria andGoodsWeightNotBetween(Integer value1, Integer value2) {
            addCriterion("goods_weight not between", value1, value2, "goodsWeight");
            return (Criteria) this;
        }

        public Criteria andGoodsVolumeIsNull() {
            addCriterion("goods_volume is null");
            return (Criteria) this;
        }

        public Criteria andGoodsVolumeIsNotNull() {
            addCriterion("goods_volume is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsVolumeEqualTo(BigDecimal value) {
            addCriterion("goods_volume =", value, "goodsVolume");
            return (Criteria) this;
        }

        public Criteria andGoodsVolumeNotEqualTo(BigDecimal value) {
            addCriterion("goods_volume <>", value, "goodsVolume");
            return (Criteria) this;
        }

        public Criteria andGoodsVolumeGreaterThan(BigDecimal value) {
            addCriterion("goods_volume >", value, "goodsVolume");
            return (Criteria) this;
        }

        public Criteria andGoodsVolumeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("goods_volume >=", value, "goodsVolume");
            return (Criteria) this;
        }

        public Criteria andGoodsVolumeLessThan(BigDecimal value) {
            addCriterion("goods_volume <", value, "goodsVolume");
            return (Criteria) this;
        }

        public Criteria andGoodsVolumeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("goods_volume <=", value, "goodsVolume");
            return (Criteria) this;
        }

        public Criteria andGoodsVolumeIn(List<BigDecimal> values) {
            addCriterion("goods_volume in", values, "goodsVolume");
            return (Criteria) this;
        }

        public Criteria andGoodsVolumeNotIn(List<BigDecimal> values) {
            addCriterion("goods_volume not in", values, "goodsVolume");
            return (Criteria) this;
        }

        public Criteria andGoodsVolumeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("goods_volume between", value1, value2, "goodsVolume");
            return (Criteria) this;
        }

        public Criteria andGoodsVolumeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("goods_volume not between", value1, value2, "goodsVolume");
            return (Criteria) this;
        }

        public Criteria andCancelChannelIsNull() {
            addCriterion("cancel_channel is null");
            return (Criteria) this;
        }

        public Criteria andCancelChannelIsNotNull() {
            addCriterion("cancel_channel is not null");
            return (Criteria) this;
        }

        public Criteria andCancelChannelEqualTo(Byte value) {
            addCriterion("cancel_channel =", value, "cancelChannel");
            return (Criteria) this;
        }

        public Criteria andCancelChannelNotEqualTo(Byte value) {
            addCriterion("cancel_channel <>", value, "cancelChannel");
            return (Criteria) this;
        }

        public Criteria andCancelChannelGreaterThan(Byte value) {
            addCriterion("cancel_channel >", value, "cancelChannel");
            return (Criteria) this;
        }

        public Criteria andCancelChannelGreaterThanOrEqualTo(Byte value) {
            addCriterion("cancel_channel >=", value, "cancelChannel");
            return (Criteria) this;
        }

        public Criteria andCancelChannelLessThan(Byte value) {
            addCriterion("cancel_channel <", value, "cancelChannel");
            return (Criteria) this;
        }

        public Criteria andCancelChannelLessThanOrEqualTo(Byte value) {
            addCriterion("cancel_channel <=", value, "cancelChannel");
            return (Criteria) this;
        }

        public Criteria andCancelChannelIn(List<Byte> values) {
            addCriterion("cancel_channel in", values, "cancelChannel");
            return (Criteria) this;
        }

        public Criteria andCancelChannelNotIn(List<Byte> values) {
            addCriterion("cancel_channel not in", values, "cancelChannel");
            return (Criteria) this;
        }

        public Criteria andCancelChannelBetween(Byte value1, Byte value2) {
            addCriterion("cancel_channel between", value1, value2, "cancelChannel");
            return (Criteria) this;
        }

        public Criteria andCancelChannelNotBetween(Byte value1, Byte value2) {
            addCriterion("cancel_channel not between", value1, value2, "cancelChannel");
            return (Criteria) this;
        }

        public Criteria andIsChangeDeliveryPointIsNull() {
            addCriterion("is_change_delivery_point is null");
            return (Criteria) this;
        }

        public Criteria andIsChangeDeliveryPointIsNotNull() {
            addCriterion("is_change_delivery_point is not null");
            return (Criteria) this;
        }

        public Criteria andIsChangeDeliveryPointEqualTo(Byte value) {
            addCriterion("is_change_delivery_point =", value, "isChangeDeliveryPoint");
            return (Criteria) this;
        }

        public Criteria andIsChangeDeliveryPointNotEqualTo(Byte value) {
            addCriterion("is_change_delivery_point <>", value, "isChangeDeliveryPoint");
            return (Criteria) this;
        }

        public Criteria andIsChangeDeliveryPointGreaterThan(Byte value) {
            addCriterion("is_change_delivery_point >", value, "isChangeDeliveryPoint");
            return (Criteria) this;
        }

        public Criteria andIsChangeDeliveryPointGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_change_delivery_point >=", value, "isChangeDeliveryPoint");
            return (Criteria) this;
        }

        public Criteria andIsChangeDeliveryPointLessThan(Byte value) {
            addCriterion("is_change_delivery_point <", value, "isChangeDeliveryPoint");
            return (Criteria) this;
        }

        public Criteria andIsChangeDeliveryPointLessThanOrEqualTo(Byte value) {
            addCriterion("is_change_delivery_point <=", value, "isChangeDeliveryPoint");
            return (Criteria) this;
        }

        public Criteria andIsChangeDeliveryPointIn(List<Byte> values) {
            addCriterion("is_change_delivery_point in", values, "isChangeDeliveryPoint");
            return (Criteria) this;
        }

        public Criteria andIsChangeDeliveryPointNotIn(List<Byte> values) {
            addCriterion("is_change_delivery_point not in", values, "isChangeDeliveryPoint");
            return (Criteria) this;
        }

        public Criteria andIsChangeDeliveryPointBetween(Byte value1, Byte value2) {
            addCriterion("is_change_delivery_point between", value1, value2, "isChangeDeliveryPoint");
            return (Criteria) this;
        }

        public Criteria andIsChangeDeliveryPointNotBetween(Byte value1, Byte value2) {
            addCriterion("is_change_delivery_point not between", value1, value2, "isChangeDeliveryPoint");
            return (Criteria) this;
        }

        public Criteria andSettlementStatusIsNull() {
            addCriterion("settlement_status is null");
            return (Criteria) this;
        }

        public Criteria andSettlementStatusIsNotNull() {
            addCriterion("settlement_status is not null");
            return (Criteria) this;
        }

        public Criteria andSettlementStatusEqualTo(Byte value) {
            addCriterion("settlement_status =", value, "settlementStatus");
            return (Criteria) this;
        }

        public Criteria andSettlementStatusNotEqualTo(Byte value) {
            addCriterion("settlement_status <>", value, "settlementStatus");
            return (Criteria) this;
        }

        public Criteria andSettlementStatusGreaterThan(Byte value) {
            addCriterion("settlement_status >", value, "settlementStatus");
            return (Criteria) this;
        }

        public Criteria andSettlementStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("settlement_status >=", value, "settlementStatus");
            return (Criteria) this;
        }

        public Criteria andSettlementStatusLessThan(Byte value) {
            addCriterion("settlement_status <", value, "settlementStatus");
            return (Criteria) this;
        }

        public Criteria andSettlementStatusLessThanOrEqualTo(Byte value) {
            addCriterion("settlement_status <=", value, "settlementStatus");
            return (Criteria) this;
        }

        public Criteria andSettlementStatusIn(List<Byte> values) {
            addCriterion("settlement_status in", values, "settlementStatus");
            return (Criteria) this;
        }

        public Criteria andSettlementStatusNotIn(List<Byte> values) {
            addCriterion("settlement_status not in", values, "settlementStatus");
            return (Criteria) this;
        }

        public Criteria andSettlementStatusBetween(Byte value1, Byte value2) {
            addCriterion("settlement_status between", value1, value2, "settlementStatus");
            return (Criteria) this;
        }

        public Criteria andSettlementStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("settlement_status not between", value1, value2, "settlementStatus");
            return (Criteria) this;
        }

        public Criteria andReceiptStatusIsNull() {
            addCriterion("receipt_status is null");
            return (Criteria) this;
        }

        public Criteria andReceiptStatusIsNotNull() {
            addCriterion("receipt_status is not null");
            return (Criteria) this;
        }

        public Criteria andReceiptStatusEqualTo(Byte value) {
            addCriterion("receipt_status =", value, "receiptStatus");
            return (Criteria) this;
        }

        public Criteria andReceiptStatusNotEqualTo(Byte value) {
            addCriterion("receipt_status <>", value, "receiptStatus");
            return (Criteria) this;
        }

        public Criteria andReceiptStatusGreaterThan(Byte value) {
            addCriterion("receipt_status >", value, "receiptStatus");
            return (Criteria) this;
        }

        public Criteria andReceiptStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("receipt_status >=", value, "receiptStatus");
            return (Criteria) this;
        }

        public Criteria andReceiptStatusLessThan(Byte value) {
            addCriterion("receipt_status <", value, "receiptStatus");
            return (Criteria) this;
        }

        public Criteria andReceiptStatusLessThanOrEqualTo(Byte value) {
            addCriterion("receipt_status <=", value, "receiptStatus");
            return (Criteria) this;
        }

        public Criteria andReceiptStatusIn(List<Byte> values) {
            addCriterion("receipt_status in", values, "receiptStatus");
            return (Criteria) this;
        }

        public Criteria andReceiptStatusNotIn(List<Byte> values) {
            addCriterion("receipt_status not in", values, "receiptStatus");
            return (Criteria) this;
        }

        public Criteria andReceiptStatusBetween(Byte value1, Byte value2) {
            addCriterion("receipt_status between", value1, value2, "receiptStatus");
            return (Criteria) this;
        }

        public Criteria andReceiptStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("receipt_status not between", value1, value2, "receiptStatus");
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