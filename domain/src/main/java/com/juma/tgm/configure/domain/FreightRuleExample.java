package com.juma.tgm.configure.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FreightRuleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int startOffSet = -1;

    protected int size = -1;

    public FreightRuleExample() {
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

        public Criteria andFreightRuleIdIsNull() {
            addCriterion("freight_rule_id is null");
            return (Criteria) this;
        }

        public Criteria andFreightRuleIdIsNotNull() {
            addCriterion("freight_rule_id is not null");
            return (Criteria) this;
        }

        public Criteria andFreightRuleIdEqualTo(Integer value) {
            addCriterion("freight_rule_id =", value, "freightRuleId");
            return (Criteria) this;
        }

        public Criteria andFreightRuleIdNotEqualTo(Integer value) {
            addCriterion("freight_rule_id <>", value, "freightRuleId");
            return (Criteria) this;
        }

        public Criteria andFreightRuleIdGreaterThan(Integer value) {
            addCriterion("freight_rule_id >", value, "freightRuleId");
            return (Criteria) this;
        }

        public Criteria andFreightRuleIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("freight_rule_id >=", value, "freightRuleId");
            return (Criteria) this;
        }

        public Criteria andFreightRuleIdLessThan(Integer value) {
            addCriterion("freight_rule_id <", value, "freightRuleId");
            return (Criteria) this;
        }

        public Criteria andFreightRuleIdLessThanOrEqualTo(Integer value) {
            addCriterion("freight_rule_id <=", value, "freightRuleId");
            return (Criteria) this;
        }

        public Criteria andFreightRuleIdIn(List<Integer> values) {
            addCriterion("freight_rule_id in", values, "freightRuleId");
            return (Criteria) this;
        }

        public Criteria andFreightRuleIdNotIn(List<Integer> values) {
            addCriterion("freight_rule_id not in", values, "freightRuleId");
            return (Criteria) this;
        }

        public Criteria andFreightRuleIdBetween(Integer value1, Integer value2) {
            addCriterion("freight_rule_id between", value1, value2, "freightRuleId");
            return (Criteria) this;
        }

        public Criteria andFreightRuleIdNotBetween(Integer value1, Integer value2) {
            addCriterion("freight_rule_id not between", value1, value2, "freightRuleId");
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

        public Criteria andBaseMilesIsNull() {
            addCriterion("base_miles is null");
            return (Criteria) this;
        }

        public Criteria andBaseMilesIsNotNull() {
            addCriterion("base_miles is not null");
            return (Criteria) this;
        }

        public Criteria andBaseMilesEqualTo(Integer value) {
            addCriterion("base_miles =", value, "baseMiles");
            return (Criteria) this;
        }

        public Criteria andBaseMilesNotEqualTo(Integer value) {
            addCriterion("base_miles <>", value, "baseMiles");
            return (Criteria) this;
        }

        public Criteria andBaseMilesGreaterThan(Integer value) {
            addCriterion("base_miles >", value, "baseMiles");
            return (Criteria) this;
        }

        public Criteria andBaseMilesGreaterThanOrEqualTo(Integer value) {
            addCriterion("base_miles >=", value, "baseMiles");
            return (Criteria) this;
        }

        public Criteria andBaseMilesLessThan(Integer value) {
            addCriterion("base_miles <", value, "baseMiles");
            return (Criteria) this;
        }

        public Criteria andBaseMilesLessThanOrEqualTo(Integer value) {
            addCriterion("base_miles <=", value, "baseMiles");
            return (Criteria) this;
        }

        public Criteria andBaseMilesIn(List<Integer> values) {
            addCriterion("base_miles in", values, "baseMiles");
            return (Criteria) this;
        }

        public Criteria andBaseMilesNotIn(List<Integer> values) {
            addCriterion("base_miles not in", values, "baseMiles");
            return (Criteria) this;
        }

        public Criteria andBaseMilesBetween(Integer value1, Integer value2) {
            addCriterion("base_miles between", value1, value2, "baseMiles");
            return (Criteria) this;
        }

        public Criteria andBaseMilesNotBetween(Integer value1, Integer value2) {
            addCriterion("base_miles not between", value1, value2, "baseMiles");
            return (Criteria) this;
        }

        public Criteria andBaseVolumeIsNull() {
            addCriterion("base_volume is null");
            return (Criteria) this;
        }

        public Criteria andBaseVolumeIsNotNull() {
            addCriterion("base_volume is not null");
            return (Criteria) this;
        }

        public Criteria andBaseVolumeEqualTo(Integer value) {
            addCriterion("base_volume =", value, "baseVolume");
            return (Criteria) this;
        }

        public Criteria andBaseVolumeNotEqualTo(Integer value) {
            addCriterion("base_volume <>", value, "baseVolume");
            return (Criteria) this;
        }

        public Criteria andBaseVolumeGreaterThan(Integer value) {
            addCriterion("base_volume >", value, "baseVolume");
            return (Criteria) this;
        }

        public Criteria andBaseVolumeGreaterThanOrEqualTo(Integer value) {
            addCriterion("base_volume >=", value, "baseVolume");
            return (Criteria) this;
        }

        public Criteria andBaseVolumeLessThan(Integer value) {
            addCriterion("base_volume <", value, "baseVolume");
            return (Criteria) this;
        }

        public Criteria andBaseVolumeLessThanOrEqualTo(Integer value) {
            addCriterion("base_volume <=", value, "baseVolume");
            return (Criteria) this;
        }

        public Criteria andBaseVolumeIn(List<Integer> values) {
            addCriterion("base_volume in", values, "baseVolume");
            return (Criteria) this;
        }

        public Criteria andBaseVolumeNotIn(List<Integer> values) {
            addCriterion("base_volume not in", values, "baseVolume");
            return (Criteria) this;
        }

        public Criteria andBaseVolumeBetween(Integer value1, Integer value2) {
            addCriterion("base_volume between", value1, value2, "baseVolume");
            return (Criteria) this;
        }

        public Criteria andBaseVolumeNotBetween(Integer value1, Integer value2) {
            addCriterion("base_volume not between", value1, value2, "baseVolume");
            return (Criteria) this;
        }

        public Criteria andBaseWeightIsNull() {
            addCriterion("base_weight is null");
            return (Criteria) this;
        }

        public Criteria andBaseWeightIsNotNull() {
            addCriterion("base_weight is not null");
            return (Criteria) this;
        }

        public Criteria andBaseWeightEqualTo(Integer value) {
            addCriterion("base_weight =", value, "baseWeight");
            return (Criteria) this;
        }

        public Criteria andBaseWeightNotEqualTo(Integer value) {
            addCriterion("base_weight <>", value, "baseWeight");
            return (Criteria) this;
        }

        public Criteria andBaseWeightGreaterThan(Integer value) {
            addCriterion("base_weight >", value, "baseWeight");
            return (Criteria) this;
        }

        public Criteria andBaseWeightGreaterThanOrEqualTo(Integer value) {
            addCriterion("base_weight >=", value, "baseWeight");
            return (Criteria) this;
        }

        public Criteria andBaseWeightLessThan(Integer value) {
            addCriterion("base_weight <", value, "baseWeight");
            return (Criteria) this;
        }

        public Criteria andBaseWeightLessThanOrEqualTo(Integer value) {
            addCriterion("base_weight <=", value, "baseWeight");
            return (Criteria) this;
        }

        public Criteria andBaseWeightIn(List<Integer> values) {
            addCriterion("base_weight in", values, "baseWeight");
            return (Criteria) this;
        }

        public Criteria andBaseWeightNotIn(List<Integer> values) {
            addCriterion("base_weight not in", values, "baseWeight");
            return (Criteria) this;
        }

        public Criteria andBaseWeightBetween(Integer value1, Integer value2) {
            addCriterion("base_weight between", value1, value2, "baseWeight");
            return (Criteria) this;
        }

        public Criteria andBaseWeightNotBetween(Integer value1, Integer value2) {
            addCriterion("base_weight not between", value1, value2, "baseWeight");
            return (Criteria) this;
        }

        public Criteria andBaseFreightIsNull() {
            addCriterion("base_freight is null");
            return (Criteria) this;
        }

        public Criteria andBaseFreightIsNotNull() {
            addCriterion("base_freight is not null");
            return (Criteria) this;
        }

        public Criteria andBaseFreightEqualTo(BigDecimal value) {
            addCriterion("base_freight =", value, "baseFreight");
            return (Criteria) this;
        }

        public Criteria andBaseFreightNotEqualTo(BigDecimal value) {
            addCriterion("base_freight <>", value, "baseFreight");
            return (Criteria) this;
        }

        public Criteria andBaseFreightGreaterThan(BigDecimal value) {
            addCriterion("base_freight >", value, "baseFreight");
            return (Criteria) this;
        }

        public Criteria andBaseFreightGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("base_freight >=", value, "baseFreight");
            return (Criteria) this;
        }

        public Criteria andBaseFreightLessThan(BigDecimal value) {
            addCriterion("base_freight <", value, "baseFreight");
            return (Criteria) this;
        }

        public Criteria andBaseFreightLessThanOrEqualTo(BigDecimal value) {
            addCriterion("base_freight <=", value, "baseFreight");
            return (Criteria) this;
        }

        public Criteria andBaseFreightIn(List<BigDecimal> values) {
            addCriterion("base_freight in", values, "baseFreight");
            return (Criteria) this;
        }

        public Criteria andBaseFreightNotIn(List<BigDecimal> values) {
            addCriterion("base_freight not in", values, "baseFreight");
            return (Criteria) this;
        }

        public Criteria andBaseFreightBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("base_freight between", value1, value2, "baseFreight");
            return (Criteria) this;
        }

        public Criteria andBaseFreightNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("base_freight not between", value1, value2, "baseFreight");
            return (Criteria) this;
        }

        public Criteria andOverMilesUnitIsNull() {
            addCriterion("over_miles_unit is null");
            return (Criteria) this;
        }

        public Criteria andOverMilesUnitIsNotNull() {
            addCriterion("over_miles_unit is not null");
            return (Criteria) this;
        }

        public Criteria andOverMilesUnitEqualTo(BigDecimal value) {
            addCriterion("over_miles_unit =", value, "overMilesUnit");
            return (Criteria) this;
        }

        public Criteria andOverMilesUnitNotEqualTo(BigDecimal value) {
            addCriterion("over_miles_unit <>", value, "overMilesUnit");
            return (Criteria) this;
        }

        public Criteria andOverMilesUnitGreaterThan(BigDecimal value) {
            addCriterion("over_miles_unit >", value, "overMilesUnit");
            return (Criteria) this;
        }

        public Criteria andOverMilesUnitGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("over_miles_unit >=", value, "overMilesUnit");
            return (Criteria) this;
        }

        public Criteria andOverMilesUnitLessThan(BigDecimal value) {
            addCriterion("over_miles_unit <", value, "overMilesUnit");
            return (Criteria) this;
        }

        public Criteria andOverMilesUnitLessThanOrEqualTo(BigDecimal value) {
            addCriterion("over_miles_unit <=", value, "overMilesUnit");
            return (Criteria) this;
        }

        public Criteria andOverMilesUnitIn(List<BigDecimal> values) {
            addCriterion("over_miles_unit in", values, "overMilesUnit");
            return (Criteria) this;
        }

        public Criteria andOverMilesUnitNotIn(List<BigDecimal> values) {
            addCriterion("over_miles_unit not in", values, "overMilesUnit");
            return (Criteria) this;
        }

        public Criteria andOverMilesUnitBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("over_miles_unit between", value1, value2, "overMilesUnit");
            return (Criteria) this;
        }

        public Criteria andOverMilesUnitNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("over_miles_unit not between", value1, value2, "overMilesUnit");
            return (Criteria) this;
        }

        public Criteria andOverVolumeUnitIsNull() {
            addCriterion("over_volume_unit is null");
            return (Criteria) this;
        }

        public Criteria andOverVolumeUnitIsNotNull() {
            addCriterion("over_volume_unit is not null");
            return (Criteria) this;
        }

        public Criteria andOverVolumeUnitEqualTo(BigDecimal value) {
            addCriterion("over_volume_unit =", value, "overVolumeUnit");
            return (Criteria) this;
        }

        public Criteria andOverVolumeUnitNotEqualTo(BigDecimal value) {
            addCriterion("over_volume_unit <>", value, "overVolumeUnit");
            return (Criteria) this;
        }

        public Criteria andOverVolumeUnitGreaterThan(BigDecimal value) {
            addCriterion("over_volume_unit >", value, "overVolumeUnit");
            return (Criteria) this;
        }

        public Criteria andOverVolumeUnitGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("over_volume_unit >=", value, "overVolumeUnit");
            return (Criteria) this;
        }

        public Criteria andOverVolumeUnitLessThan(BigDecimal value) {
            addCriterion("over_volume_unit <", value, "overVolumeUnit");
            return (Criteria) this;
        }

        public Criteria andOverVolumeUnitLessThanOrEqualTo(BigDecimal value) {
            addCriterion("over_volume_unit <=", value, "overVolumeUnit");
            return (Criteria) this;
        }

        public Criteria andOverVolumeUnitIn(List<BigDecimal> values) {
            addCriterion("over_volume_unit in", values, "overVolumeUnit");
            return (Criteria) this;
        }

        public Criteria andOverVolumeUnitNotIn(List<BigDecimal> values) {
            addCriterion("over_volume_unit not in", values, "overVolumeUnit");
            return (Criteria) this;
        }

        public Criteria andOverVolumeUnitBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("over_volume_unit between", value1, value2, "overVolumeUnit");
            return (Criteria) this;
        }

        public Criteria andOverVolumeUnitNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("over_volume_unit not between", value1, value2, "overVolumeUnit");
            return (Criteria) this;
        }

        public Criteria andOverWeightUnitIsNull() {
            addCriterion("over_weight_unit is null");
            return (Criteria) this;
        }

        public Criteria andOverWeightUnitIsNotNull() {
            addCriterion("over_weight_unit is not null");
            return (Criteria) this;
        }

        public Criteria andOverWeightUnitEqualTo(BigDecimal value) {
            addCriterion("over_weight_unit =", value, "overWeightUnit");
            return (Criteria) this;
        }

        public Criteria andOverWeightUnitNotEqualTo(BigDecimal value) {
            addCriterion("over_weight_unit <>", value, "overWeightUnit");
            return (Criteria) this;
        }

        public Criteria andOverWeightUnitGreaterThan(BigDecimal value) {
            addCriterion("over_weight_unit >", value, "overWeightUnit");
            return (Criteria) this;
        }

        public Criteria andOverWeightUnitGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("over_weight_unit >=", value, "overWeightUnit");
            return (Criteria) this;
        }

        public Criteria andOverWeightUnitLessThan(BigDecimal value) {
            addCriterion("over_weight_unit <", value, "overWeightUnit");
            return (Criteria) this;
        }

        public Criteria andOverWeightUnitLessThanOrEqualTo(BigDecimal value) {
            addCriterion("over_weight_unit <=", value, "overWeightUnit");
            return (Criteria) this;
        }

        public Criteria andOverWeightUnitIn(List<BigDecimal> values) {
            addCriterion("over_weight_unit in", values, "overWeightUnit");
            return (Criteria) this;
        }

        public Criteria andOverWeightUnitNotIn(List<BigDecimal> values) {
            addCriterion("over_weight_unit not in", values, "overWeightUnit");
            return (Criteria) this;
        }

        public Criteria andOverWeightUnitBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("over_weight_unit between", value1, value2, "overWeightUnit");
            return (Criteria) this;
        }

        public Criteria andOverWeightUnitNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("over_weight_unit not between", value1, value2, "overWeightUnit");
            return (Criteria) this;
        }

        public Criteria andMaxMilesIsNull() {
            addCriterion("max_miles is null");
            return (Criteria) this;
        }

        public Criteria andMaxMilesIsNotNull() {
            addCriterion("max_miles is not null");
            return (Criteria) this;
        }

        public Criteria andMaxMilesEqualTo(Integer value) {
            addCriterion("max_miles =", value, "maxMiles");
            return (Criteria) this;
        }

        public Criteria andMaxMilesNotEqualTo(Integer value) {
            addCriterion("max_miles <>", value, "maxMiles");
            return (Criteria) this;
        }

        public Criteria andMaxMilesGreaterThan(Integer value) {
            addCriterion("max_miles >", value, "maxMiles");
            return (Criteria) this;
        }

        public Criteria andMaxMilesGreaterThanOrEqualTo(Integer value) {
            addCriterion("max_miles >=", value, "maxMiles");
            return (Criteria) this;
        }

        public Criteria andMaxMilesLessThan(Integer value) {
            addCriterion("max_miles <", value, "maxMiles");
            return (Criteria) this;
        }

        public Criteria andMaxMilesLessThanOrEqualTo(Integer value) {
            addCriterion("max_miles <=", value, "maxMiles");
            return (Criteria) this;
        }

        public Criteria andMaxMilesIn(List<Integer> values) {
            addCriterion("max_miles in", values, "maxMiles");
            return (Criteria) this;
        }

        public Criteria andMaxMilesNotIn(List<Integer> values) {
            addCriterion("max_miles not in", values, "maxMiles");
            return (Criteria) this;
        }

        public Criteria andMaxMilesBetween(Integer value1, Integer value2) {
            addCriterion("max_miles between", value1, value2, "maxMiles");
            return (Criteria) this;
        }

        public Criteria andMaxMilesNotBetween(Integer value1, Integer value2) {
            addCriterion("max_miles not between", value1, value2, "maxMiles");
            return (Criteria) this;
        }

        public Criteria andMaxWeightIsNull() {
            addCriterion("max_weight is null");
            return (Criteria) this;
        }

        public Criteria andMaxWeightIsNotNull() {
            addCriterion("max_weight is not null");
            return (Criteria) this;
        }

        public Criteria andMaxWeightEqualTo(Integer value) {
            addCriterion("max_weight =", value, "maxWeight");
            return (Criteria) this;
        }

        public Criteria andMaxWeightNotEqualTo(Integer value) {
            addCriterion("max_weight <>", value, "maxWeight");
            return (Criteria) this;
        }

        public Criteria andMaxWeightGreaterThan(Integer value) {
            addCriterion("max_weight >", value, "maxWeight");
            return (Criteria) this;
        }

        public Criteria andMaxWeightGreaterThanOrEqualTo(Integer value) {
            addCriterion("max_weight >=", value, "maxWeight");
            return (Criteria) this;
        }

        public Criteria andMaxWeightLessThan(Integer value) {
            addCriterion("max_weight <", value, "maxWeight");
            return (Criteria) this;
        }

        public Criteria andMaxWeightLessThanOrEqualTo(Integer value) {
            addCriterion("max_weight <=", value, "maxWeight");
            return (Criteria) this;
        }

        public Criteria andMaxWeightIn(List<Integer> values) {
            addCriterion("max_weight in", values, "maxWeight");
            return (Criteria) this;
        }

        public Criteria andMaxWeightNotIn(List<Integer> values) {
            addCriterion("max_weight not in", values, "maxWeight");
            return (Criteria) this;
        }

        public Criteria andMaxWeightBetween(Integer value1, Integer value2) {
            addCriterion("max_weight between", value1, value2, "maxWeight");
            return (Criteria) this;
        }

        public Criteria andMaxWeightNotBetween(Integer value1, Integer value2) {
            addCriterion("max_weight not between", value1, value2, "maxWeight");
            return (Criteria) this;
        }

        public Criteria andMaxVolumeIsNull() {
            addCriterion("max_volume is null");
            return (Criteria) this;
        }

        public Criteria andMaxVolumeIsNotNull() {
            addCriterion("max_volume is not null");
            return (Criteria) this;
        }

        public Criteria andMaxVolumeEqualTo(Integer value) {
            addCriterion("max_volume =", value, "maxVolume");
            return (Criteria) this;
        }

        public Criteria andMaxVolumeNotEqualTo(Integer value) {
            addCriterion("max_volume <>", value, "maxVolume");
            return (Criteria) this;
        }

        public Criteria andMaxVolumeGreaterThan(Integer value) {
            addCriterion("max_volume >", value, "maxVolume");
            return (Criteria) this;
        }

        public Criteria andMaxVolumeGreaterThanOrEqualTo(Integer value) {
            addCriterion("max_volume >=", value, "maxVolume");
            return (Criteria) this;
        }

        public Criteria andMaxVolumeLessThan(Integer value) {
            addCriterion("max_volume <", value, "maxVolume");
            return (Criteria) this;
        }

        public Criteria andMaxVolumeLessThanOrEqualTo(Integer value) {
            addCriterion("max_volume <=", value, "maxVolume");
            return (Criteria) this;
        }

        public Criteria andMaxVolumeIn(List<Integer> values) {
            addCriterion("max_volume in", values, "maxVolume");
            return (Criteria) this;
        }

        public Criteria andMaxVolumeNotIn(List<Integer> values) {
            addCriterion("max_volume not in", values, "maxVolume");
            return (Criteria) this;
        }

        public Criteria andMaxVolumeBetween(Integer value1, Integer value2) {
            addCriterion("max_volume between", value1, value2, "maxVolume");
            return (Criteria) this;
        }

        public Criteria andMaxVolumeNotBetween(Integer value1, Integer value2) {
            addCriterion("max_volume not between", value1, value2, "maxVolume");
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

        public Criteria andIsEnableEqualTo(Boolean value) {
            addCriterion("is_enable =", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableNotEqualTo(Boolean value) {
            addCriterion("is_enable <>", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableGreaterThan(Boolean value) {
            addCriterion("is_enable >", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_enable >=", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableLessThan(Boolean value) {
            addCriterion("is_enable <", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableLessThanOrEqualTo(Boolean value) {
            addCriterion("is_enable <=", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableIn(List<Boolean> values) {
            addCriterion("is_enable in", values, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableNotIn(List<Boolean> values) {
            addCriterion("is_enable not in", values, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableBetween(Boolean value1, Boolean value2) {
            addCriterion("is_enable between", value1, value2, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableNotBetween(Boolean value1, Boolean value2) {
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