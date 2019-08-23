package com.juma.tgm.waybill.domain;

import java.util.ArrayList;
import java.util.List;

public class WaybillReceiveAddressCargoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int startOffSet = -1;

    protected int size = -1;

    public WaybillReceiveAddressCargoExample() {
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

        public Criteria andCargoIdIsNull() {
            addCriterion("cargo_id is null");
            return (Criteria) this;
        }

        public Criteria andCargoIdIsNotNull() {
            addCriterion("cargo_id is not null");
            return (Criteria) this;
        }

        public Criteria andCargoIdEqualTo(Integer value) {
            addCriterion("cargo_id =", value, "cargoId");
            return (Criteria) this;
        }

        public Criteria andCargoIdNotEqualTo(Integer value) {
            addCriterion("cargo_id <>", value, "cargoId");
            return (Criteria) this;
        }

        public Criteria andCargoIdGreaterThan(Integer value) {
            addCriterion("cargo_id >", value, "cargoId");
            return (Criteria) this;
        }

        public Criteria andCargoIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("cargo_id >=", value, "cargoId");
            return (Criteria) this;
        }

        public Criteria andCargoIdLessThan(Integer value) {
            addCriterion("cargo_id <", value, "cargoId");
            return (Criteria) this;
        }

        public Criteria andCargoIdLessThanOrEqualTo(Integer value) {
            addCriterion("cargo_id <=", value, "cargoId");
            return (Criteria) this;
        }

        public Criteria andCargoIdIn(List<Integer> values) {
            addCriterion("cargo_id in", values, "cargoId");
            return (Criteria) this;
        }

        public Criteria andCargoIdNotIn(List<Integer> values) {
            addCriterion("cargo_id not in", values, "cargoId");
            return (Criteria) this;
        }

        public Criteria andCargoIdBetween(Integer value1, Integer value2) {
            addCriterion("cargo_id between", value1, value2, "cargoId");
            return (Criteria) this;
        }

        public Criteria andCargoIdNotBetween(Integer value1, Integer value2) {
            addCriterion("cargo_id not between", value1, value2, "cargoId");
            return (Criteria) this;
        }

        public Criteria andAddressIdIsNull() {
            addCriterion("address_id is null");
            return (Criteria) this;
        }

        public Criteria andAddressIdIsNotNull() {
            addCriterion("address_id is not null");
            return (Criteria) this;
        }

        public Criteria andAddressIdEqualTo(Integer value) {
            addCriterion("address_id =", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdNotEqualTo(Integer value) {
            addCriterion("address_id <>", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdGreaterThan(Integer value) {
            addCriterion("address_id >", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("address_id >=", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdLessThan(Integer value) {
            addCriterion("address_id <", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdLessThanOrEqualTo(Integer value) {
            addCriterion("address_id <=", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdIn(List<Integer> values) {
            addCriterion("address_id in", values, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdNotIn(List<Integer> values) {
            addCriterion("address_id not in", values, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdBetween(Integer value1, Integer value2) {
            addCriterion("address_id between", value1, value2, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdNotBetween(Integer value1, Integer value2) {
            addCriterion("address_id not between", value1, value2, "addressId");
            return (Criteria) this;
        }

        public Criteria andCargoNameIsNull() {
            addCriterion("cargo_name is null");
            return (Criteria) this;
        }

        public Criteria andCargoNameIsNotNull() {
            addCriterion("cargo_name is not null");
            return (Criteria) this;
        }

        public Criteria andCargoNameEqualTo(String value) {
            addCriterion("cargo_name =", value, "cargoName");
            return (Criteria) this;
        }

        public Criteria andCargoNameNotEqualTo(String value) {
            addCriterion("cargo_name <>", value, "cargoName");
            return (Criteria) this;
        }

        public Criteria andCargoNameGreaterThan(String value) {
            addCriterion("cargo_name >", value, "cargoName");
            return (Criteria) this;
        }

        public Criteria andCargoNameGreaterThanOrEqualTo(String value) {
            addCriterion("cargo_name >=", value, "cargoName");
            return (Criteria) this;
        }

        public Criteria andCargoNameLessThan(String value) {
            addCriterion("cargo_name <", value, "cargoName");
            return (Criteria) this;
        }

        public Criteria andCargoNameLessThanOrEqualTo(String value) {
            addCriterion("cargo_name <=", value, "cargoName");
            return (Criteria) this;
        }

        public Criteria andCargoNameLike(String value) {
            addCriterion("cargo_name like", value, "cargoName");
            return (Criteria) this;
        }

        public Criteria andCargoNameNotLike(String value) {
            addCriterion("cargo_name not like", value, "cargoName");
            return (Criteria) this;
        }

        public Criteria andCargoNameIn(List<String> values) {
            addCriterion("cargo_name in", values, "cargoName");
            return (Criteria) this;
        }

        public Criteria andCargoNameNotIn(List<String> values) {
            addCriterion("cargo_name not in", values, "cargoName");
            return (Criteria) this;
        }

        public Criteria andCargoNameBetween(String value1, String value2) {
            addCriterion("cargo_name between", value1, value2, "cargoName");
            return (Criteria) this;
        }

        public Criteria andCargoNameNotBetween(String value1, String value2) {
            addCriterion("cargo_name not between", value1, value2, "cargoName");
            return (Criteria) this;
        }

        public Criteria andCargoTypeIsNull() {
            addCriterion("cargo_type is null");
            return (Criteria) this;
        }

        public Criteria andCargoTypeIsNotNull() {
            addCriterion("cargo_type is not null");
            return (Criteria) this;
        }

        public Criteria andCargoTypeEqualTo(String value) {
            addCriterion("cargo_type =", value, "cargoType");
            return (Criteria) this;
        }

        public Criteria andCargoTypeNotEqualTo(String value) {
            addCriterion("cargo_type <>", value, "cargoType");
            return (Criteria) this;
        }

        public Criteria andCargoTypeGreaterThan(String value) {
            addCriterion("cargo_type >", value, "cargoType");
            return (Criteria) this;
        }

        public Criteria andCargoTypeGreaterThanOrEqualTo(String value) {
            addCriterion("cargo_type >=", value, "cargoType");
            return (Criteria) this;
        }

        public Criteria andCargoTypeLessThan(String value) {
            addCriterion("cargo_type <", value, "cargoType");
            return (Criteria) this;
        }

        public Criteria andCargoTypeLessThanOrEqualTo(String value) {
            addCriterion("cargo_type <=", value, "cargoType");
            return (Criteria) this;
        }

        public Criteria andCargoTypeLike(String value) {
            addCriterion("cargo_type like", value, "cargoType");
            return (Criteria) this;
        }

        public Criteria andCargoTypeNotLike(String value) {
            addCriterion("cargo_type not like", value, "cargoType");
            return (Criteria) this;
        }

        public Criteria andCargoTypeIn(List<String> values) {
            addCriterion("cargo_type in", values, "cargoType");
            return (Criteria) this;
        }

        public Criteria andCargoTypeNotIn(List<String> values) {
            addCriterion("cargo_type not in", values, "cargoType");
            return (Criteria) this;
        }

        public Criteria andCargoTypeBetween(String value1, String value2) {
            addCriterion("cargo_type between", value1, value2, "cargoType");
            return (Criteria) this;
        }

        public Criteria andCargoTypeNotBetween(String value1, String value2) {
            addCriterion("cargo_type not between", value1, value2, "cargoType");
            return (Criteria) this;
        }

        public Criteria andCargoVolumeIsNull() {
            addCriterion("cargo_volume is null");
            return (Criteria) this;
        }

        public Criteria andCargoVolumeIsNotNull() {
            addCriterion("cargo_volume is not null");
            return (Criteria) this;
        }

        public Criteria andCargoVolumeEqualTo(Float value) {
            addCriterion("cargo_volume =", value, "cargoVolume");
            return (Criteria) this;
        }

        public Criteria andCargoVolumeNotEqualTo(Float value) {
            addCriterion("cargo_volume <>", value, "cargoVolume");
            return (Criteria) this;
        }

        public Criteria andCargoVolumeGreaterThan(Float value) {
            addCriterion("cargo_volume >", value, "cargoVolume");
            return (Criteria) this;
        }

        public Criteria andCargoVolumeGreaterThanOrEqualTo(Float value) {
            addCriterion("cargo_volume >=", value, "cargoVolume");
            return (Criteria) this;
        }

        public Criteria andCargoVolumeLessThan(Float value) {
            addCriterion("cargo_volume <", value, "cargoVolume");
            return (Criteria) this;
        }

        public Criteria andCargoVolumeLessThanOrEqualTo(Float value) {
            addCriterion("cargo_volume <=", value, "cargoVolume");
            return (Criteria) this;
        }

        public Criteria andCargoVolumeIn(List<Float> values) {
            addCriterion("cargo_volume in", values, "cargoVolume");
            return (Criteria) this;
        }

        public Criteria andCargoVolumeNotIn(List<Float> values) {
            addCriterion("cargo_volume not in", values, "cargoVolume");
            return (Criteria) this;
        }

        public Criteria andCargoVolumeBetween(Float value1, Float value2) {
            addCriterion("cargo_volume between", value1, value2, "cargoVolume");
            return (Criteria) this;
        }

        public Criteria andCargoVolumeNotBetween(Float value1, Float value2) {
            addCriterion("cargo_volume not between", value1, value2, "cargoVolume");
            return (Criteria) this;
        }

        public Criteria andCargoPackagesIsNull() {
            addCriterion("cargo_packages is null");
            return (Criteria) this;
        }

        public Criteria andCargoPackagesIsNotNull() {
            addCriterion("cargo_packages is not null");
            return (Criteria) this;
        }

        public Criteria andCargoPackagesEqualTo(Integer value) {
            addCriterion("cargo_packages =", value, "cargoPackages");
            return (Criteria) this;
        }

        public Criteria andCargoPackagesNotEqualTo(Integer value) {
            addCriterion("cargo_packages <>", value, "cargoPackages");
            return (Criteria) this;
        }

        public Criteria andCargoPackagesGreaterThan(Integer value) {
            addCriterion("cargo_packages >", value, "cargoPackages");
            return (Criteria) this;
        }

        public Criteria andCargoPackagesGreaterThanOrEqualTo(Integer value) {
            addCriterion("cargo_packages >=", value, "cargoPackages");
            return (Criteria) this;
        }

        public Criteria andCargoPackagesLessThan(Integer value) {
            addCriterion("cargo_packages <", value, "cargoPackages");
            return (Criteria) this;
        }

        public Criteria andCargoPackagesLessThanOrEqualTo(Integer value) {
            addCriterion("cargo_packages <=", value, "cargoPackages");
            return (Criteria) this;
        }

        public Criteria andCargoPackagesIn(List<Integer> values) {
            addCriterion("cargo_packages in", values, "cargoPackages");
            return (Criteria) this;
        }

        public Criteria andCargoPackagesNotIn(List<Integer> values) {
            addCriterion("cargo_packages not in", values, "cargoPackages");
            return (Criteria) this;
        }

        public Criteria andCargoPackagesBetween(Integer value1, Integer value2) {
            addCriterion("cargo_packages between", value1, value2, "cargoPackages");
            return (Criteria) this;
        }

        public Criteria andCargoPackagesNotBetween(Integer value1, Integer value2) {
            addCriterion("cargo_packages not between", value1, value2, "cargoPackages");
            return (Criteria) this;
        }

        public Criteria andCargoWeightIsNull() {
            addCriterion("cargo_weight is null");
            return (Criteria) this;
        }

        public Criteria andCargoWeightIsNotNull() {
            addCriterion("cargo_weight is not null");
            return (Criteria) this;
        }

        public Criteria andCargoWeightEqualTo(Float value) {
            addCriterion("cargo_weight =", value, "cargoWeight");
            return (Criteria) this;
        }

        public Criteria andCargoWeightNotEqualTo(Float value) {
            addCriterion("cargo_weight <>", value, "cargoWeight");
            return (Criteria) this;
        }

        public Criteria andCargoWeightGreaterThan(Float value) {
            addCriterion("cargo_weight >", value, "cargoWeight");
            return (Criteria) this;
        }

        public Criteria andCargoWeightGreaterThanOrEqualTo(Float value) {
            addCriterion("cargo_weight >=", value, "cargoWeight");
            return (Criteria) this;
        }

        public Criteria andCargoWeightLessThan(Float value) {
            addCriterion("cargo_weight <", value, "cargoWeight");
            return (Criteria) this;
        }

        public Criteria andCargoWeightLessThanOrEqualTo(Float value) {
            addCriterion("cargo_weight <=", value, "cargoWeight");
            return (Criteria) this;
        }

        public Criteria andCargoWeightIn(List<Float> values) {
            addCriterion("cargo_weight in", values, "cargoWeight");
            return (Criteria) this;
        }

        public Criteria andCargoWeightNotIn(List<Float> values) {
            addCriterion("cargo_weight not in", values, "cargoWeight");
            return (Criteria) this;
        }

        public Criteria andCargoWeightBetween(Float value1, Float value2) {
            addCriterion("cargo_weight between", value1, value2, "cargoWeight");
            return (Criteria) this;
        }

        public Criteria andCargoWeightNotBetween(Float value1, Float value2) {
            addCriterion("cargo_weight not between", value1, value2, "cargoWeight");
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