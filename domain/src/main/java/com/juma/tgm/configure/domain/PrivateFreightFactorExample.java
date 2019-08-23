package com.juma.tgm.configure.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PrivateFreightFactorExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int startOffSet = -1;

    protected int size = -1;

    public PrivateFreightFactorExample() {
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

        public Criteria andPrivateFreightFactorIdIsNull() {
            addCriterion("private_freight_factor_id is null");
            return (Criteria) this;
        }

        public Criteria andPrivateFreightFactorIdIsNotNull() {
            addCriterion("private_freight_factor_id is not null");
            return (Criteria) this;
        }

        public Criteria andPrivateFreightFactorIdEqualTo(Integer value) {
            addCriterion("private_freight_factor_id =", value, "privateFreightFactorId");
            return (Criteria) this;
        }

        public Criteria andPrivateFreightFactorIdNotEqualTo(Integer value) {
            addCriterion("private_freight_factor_id <>", value, "privateFreightFactorId");
            return (Criteria) this;
        }

        public Criteria andPrivateFreightFactorIdGreaterThan(Integer value) {
            addCriterion("private_freight_factor_id >", value, "privateFreightFactorId");
            return (Criteria) this;
        }

        public Criteria andPrivateFreightFactorIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("private_freight_factor_id >=", value, "privateFreightFactorId");
            return (Criteria) this;
        }

        public Criteria andPrivateFreightFactorIdLessThan(Integer value) {
            addCriterion("private_freight_factor_id <", value, "privateFreightFactorId");
            return (Criteria) this;
        }

        public Criteria andPrivateFreightFactorIdLessThanOrEqualTo(Integer value) {
            addCriterion("private_freight_factor_id <=", value, "privateFreightFactorId");
            return (Criteria) this;
        }

        public Criteria andPrivateFreightFactorIdIn(List<Integer> values) {
            addCriterion("private_freight_factor_id in", values, "privateFreightFactorId");
            return (Criteria) this;
        }

        public Criteria andPrivateFreightFactorIdNotIn(List<Integer> values) {
            addCriterion("private_freight_factor_id not in", values, "privateFreightFactorId");
            return (Criteria) this;
        }

        public Criteria andPrivateFreightFactorIdBetween(Integer value1, Integer value2) {
            addCriterion("private_freight_factor_id between", value1, value2, "privateFreightFactorId");
            return (Criteria) this;
        }

        public Criteria andPrivateFreightFactorIdNotBetween(Integer value1, Integer value2) {
            addCriterion("private_freight_factor_id not between", value1, value2, "privateFreightFactorId");
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

        public Criteria andRegionNameIsNull() {
            addCriterion("region_name is null");
            return (Criteria) this;
        }

        public Criteria andRegionNameIsNotNull() {
            addCriterion("region_name is not null");
            return (Criteria) this;
        }

        public Criteria andRegionNameEqualTo(String value) {
            addCriterion("region_name =", value, "regionName");
            return (Criteria) this;
        }

        public Criteria andRegionNameNotEqualTo(String value) {
            addCriterion("region_name <>", value, "regionName");
            return (Criteria) this;
        }

        public Criteria andRegionNameGreaterThan(String value) {
            addCriterion("region_name >", value, "regionName");
            return (Criteria) this;
        }

        public Criteria andRegionNameGreaterThanOrEqualTo(String value) {
            addCriterion("region_name >=", value, "regionName");
            return (Criteria) this;
        }

        public Criteria andRegionNameLessThan(String value) {
            addCriterion("region_name <", value, "regionName");
            return (Criteria) this;
        }

        public Criteria andRegionNameLessThanOrEqualTo(String value) {
            addCriterion("region_name <=", value, "regionName");
            return (Criteria) this;
        }

        public Criteria andRegionNameLike(String value) {
            addCriterion("region_name like", value, "regionName");
            return (Criteria) this;
        }

        public Criteria andRegionNameNotLike(String value) {
            addCriterion("region_name not like", value, "regionName");
            return (Criteria) this;
        }

        public Criteria andRegionNameIn(List<String> values) {
            addCriterion("region_name in", values, "regionName");
            return (Criteria) this;
        }

        public Criteria andRegionNameNotIn(List<String> values) {
            addCriterion("region_name not in", values, "regionName");
            return (Criteria) this;
        }

        public Criteria andRegionNameBetween(String value1, String value2) {
            addCriterion("region_name between", value1, value2, "regionName");
            return (Criteria) this;
        }

        public Criteria andRegionNameNotBetween(String value1, String value2) {
            addCriterion("region_name not between", value1, value2, "regionName");
            return (Criteria) this;
        }

        public Criteria andTruckTypeNameIsNull() {
            addCriterion("truck_type_name is null");
            return (Criteria) this;
        }

        public Criteria andTruckTypeNameIsNotNull() {
            addCriterion("truck_type_name is not null");
            return (Criteria) this;
        }

        public Criteria andTruckTypeNameEqualTo(String value) {
            addCriterion("truck_type_name =", value, "truckTypeName");
            return (Criteria) this;
        }

        public Criteria andTruckTypeNameNotEqualTo(String value) {
            addCriterion("truck_type_name <>", value, "truckTypeName");
            return (Criteria) this;
        }

        public Criteria andTruckTypeNameGreaterThan(String value) {
            addCriterion("truck_type_name >", value, "truckTypeName");
            return (Criteria) this;
        }

        public Criteria andTruckTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("truck_type_name >=", value, "truckTypeName");
            return (Criteria) this;
        }

        public Criteria andTruckTypeNameLessThan(String value) {
            addCriterion("truck_type_name <", value, "truckTypeName");
            return (Criteria) this;
        }

        public Criteria andTruckTypeNameLessThanOrEqualTo(String value) {
            addCriterion("truck_type_name <=", value, "truckTypeName");
            return (Criteria) this;
        }

        public Criteria andTruckTypeNameLike(String value) {
            addCriterion("truck_type_name like", value, "truckTypeName");
            return (Criteria) this;
        }

        public Criteria andTruckTypeNameNotLike(String value) {
            addCriterion("truck_type_name not like", value, "truckTypeName");
            return (Criteria) this;
        }

        public Criteria andTruckTypeNameIn(List<String> values) {
            addCriterion("truck_type_name in", values, "truckTypeName");
            return (Criteria) this;
        }

        public Criteria andTruckTypeNameNotIn(List<String> values) {
            addCriterion("truck_type_name not in", values, "truckTypeName");
            return (Criteria) this;
        }

        public Criteria andTruckTypeNameBetween(String value1, String value2) {
            addCriterion("truck_type_name between", value1, value2, "truckTypeName");
            return (Criteria) this;
        }

        public Criteria andTruckTypeNameNotBetween(String value1, String value2) {
            addCriterion("truck_type_name not between", value1, value2, "truckTypeName");
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

        public Criteria andFreightWayIsNull() {
            addCriterion("freight_way is null");
            return (Criteria) this;
        }

        public Criteria andFreightWayIsNotNull() {
            addCriterion("freight_way is not null");
            return (Criteria) this;
        }

        public Criteria andFreightWayEqualTo(Integer value) {
            addCriterion("freight_way =", value, "freightWay");
            return (Criteria) this;
        }

        public Criteria andFreightWayNotEqualTo(Integer value) {
            addCriterion("freight_way <>", value, "freightWay");
            return (Criteria) this;
        }

        public Criteria andFreightWayGreaterThan(Integer value) {
            addCriterion("freight_way >", value, "freightWay");
            return (Criteria) this;
        }

        public Criteria andFreightWayGreaterThanOrEqualTo(Integer value) {
            addCriterion("freight_way >=", value, "freightWay");
            return (Criteria) this;
        }

        public Criteria andFreightWayLessThan(Integer value) {
            addCriterion("freight_way <", value, "freightWay");
            return (Criteria) this;
        }

        public Criteria andFreightWayLessThanOrEqualTo(Integer value) {
            addCriterion("freight_way <=", value, "freightWay");
            return (Criteria) this;
        }

        public Criteria andFreightWayIn(List<Integer> values) {
            addCriterion("freight_way in", values, "freightWay");
            return (Criteria) this;
        }

        public Criteria andFreightWayNotIn(List<Integer> values) {
            addCriterion("freight_way not in", values, "freightWay");
            return (Criteria) this;
        }

        public Criteria andFreightWayBetween(Integer value1, Integer value2) {
            addCriterion("freight_way between", value1, value2, "freightWay");
            return (Criteria) this;
        }

        public Criteria andFreightWayNotBetween(Integer value1, Integer value2) {
            addCriterion("freight_way not between", value1, value2, "freightWay");
            return (Criteria) this;
        }

        public Criteria andFactorJsonIsNull() {
            addCriterion("factor_json is null");
            return (Criteria) this;
        }

        public Criteria andFactorJsonIsNotNull() {
            addCriterion("factor_json is not null");
            return (Criteria) this;
        }

        public Criteria andFactorJsonEqualTo(String value) {
            addCriterion("factor_json =", value, "factorJson");
            return (Criteria) this;
        }

        public Criteria andFactorJsonNotEqualTo(String value) {
            addCriterion("factor_json <>", value, "factorJson");
            return (Criteria) this;
        }

        public Criteria andFactorJsonGreaterThan(String value) {
            addCriterion("factor_json >", value, "factorJson");
            return (Criteria) this;
        }

        public Criteria andFactorJsonGreaterThanOrEqualTo(String value) {
            addCriterion("factor_json >=", value, "factorJson");
            return (Criteria) this;
        }

        public Criteria andFactorJsonLessThan(String value) {
            addCriterion("factor_json <", value, "factorJson");
            return (Criteria) this;
        }

        public Criteria andFactorJsonLessThanOrEqualTo(String value) {
            addCriterion("factor_json <=", value, "factorJson");
            return (Criteria) this;
        }

        public Criteria andFactorJsonLike(String value) {
            addCriterion("factor_json like", value, "factorJson");
            return (Criteria) this;
        }

        public Criteria andFactorJsonNotLike(String value) {
            addCriterion("factor_json not like", value, "factorJson");
            return (Criteria) this;
        }

        public Criteria andFactorJsonIn(List<String> values) {
            addCriterion("factor_json in", values, "factorJson");
            return (Criteria) this;
        }

        public Criteria andFactorJsonNotIn(List<String> values) {
            addCriterion("factor_json not in", values, "factorJson");
            return (Criteria) this;
        }

        public Criteria andFactorJsonBetween(String value1, String value2) {
            addCriterion("factor_json between", value1, value2, "factorJson");
            return (Criteria) this;
        }

        public Criteria andFactorJsonNotBetween(String value1, String value2) {
            addCriterion("factor_json not between", value1, value2, "factorJson");
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