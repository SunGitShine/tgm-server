package com.juma.tgm.configure.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServiceConfExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int startOffSet = -1;

    protected int size = -1;

    public ServiceConfExample() {
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

        public Criteria andParentRegionCodeIsNull() {
            addCriterion("parent_region_code is null");
            return (Criteria) this;
        }

        public Criteria andParentRegionCodeIsNotNull() {
            addCriterion("parent_region_code is not null");
            return (Criteria) this;
        }

        public Criteria andParentRegionCodeEqualTo(String value) {
            addCriterion("parent_region_code =", value, "parentRegionCode");
            return (Criteria) this;
        }

        public Criteria andParentRegionCodeNotEqualTo(String value) {
            addCriterion("parent_region_code <>", value, "parentRegionCode");
            return (Criteria) this;
        }

        public Criteria andParentRegionCodeGreaterThan(String value) {
            addCriterion("parent_region_code >", value, "parentRegionCode");
            return (Criteria) this;
        }

        public Criteria andParentRegionCodeGreaterThanOrEqualTo(String value) {
            addCriterion("parent_region_code >=", value, "parentRegionCode");
            return (Criteria) this;
        }

        public Criteria andParentRegionCodeLessThan(String value) {
            addCriterion("parent_region_code <", value, "parentRegionCode");
            return (Criteria) this;
        }

        public Criteria andParentRegionCodeLessThanOrEqualTo(String value) {
            addCriterion("parent_region_code <=", value, "parentRegionCode");
            return (Criteria) this;
        }

        public Criteria andParentRegionCodeLike(String value) {
            addCriterion("parent_region_code like", value, "parentRegionCode");
            return (Criteria) this;
        }

        public Criteria andParentRegionCodeNotLike(String value) {
            addCriterion("parent_region_code not like", value, "parentRegionCode");
            return (Criteria) this;
        }

        public Criteria andParentRegionCodeIn(List<String> values) {
            addCriterion("parent_region_code in", values, "parentRegionCode");
            return (Criteria) this;
        }

        public Criteria andParentRegionCodeNotIn(List<String> values) {
            addCriterion("parent_region_code not in", values, "parentRegionCode");
            return (Criteria) this;
        }

        public Criteria andParentRegionCodeBetween(String value1, String value2) {
            addCriterion("parent_region_code between", value1, value2, "parentRegionCode");
            return (Criteria) this;
        }

        public Criteria andParentRegionCodeNotBetween(String value1, String value2) {
            addCriterion("parent_region_code not between", value1, value2, "parentRegionCode");
            return (Criteria) this;
        }

        public Criteria andParentRegionNameIsNull() {
            addCriterion("parent_region_name is null");
            return (Criteria) this;
        }

        public Criteria andParentRegionNameIsNotNull() {
            addCriterion("parent_region_name is not null");
            return (Criteria) this;
        }

        public Criteria andParentRegionNameEqualTo(String value) {
            addCriterion("parent_region_name =", value, "parentRegionName");
            return (Criteria) this;
        }

        public Criteria andParentRegionNameNotEqualTo(String value) {
            addCriterion("parent_region_name <>", value, "parentRegionName");
            return (Criteria) this;
        }

        public Criteria andParentRegionNameGreaterThan(String value) {
            addCriterion("parent_region_name >", value, "parentRegionName");
            return (Criteria) this;
        }

        public Criteria andParentRegionNameGreaterThanOrEqualTo(String value) {
            addCriterion("parent_region_name >=", value, "parentRegionName");
            return (Criteria) this;
        }

        public Criteria andParentRegionNameLessThan(String value) {
            addCriterion("parent_region_name <", value, "parentRegionName");
            return (Criteria) this;
        }

        public Criteria andParentRegionNameLessThanOrEqualTo(String value) {
            addCriterion("parent_region_name <=", value, "parentRegionName");
            return (Criteria) this;
        }

        public Criteria andParentRegionNameLike(String value) {
            addCriterion("parent_region_name like", value, "parentRegionName");
            return (Criteria) this;
        }

        public Criteria andParentRegionNameNotLike(String value) {
            addCriterion("parent_region_name not like", value, "parentRegionName");
            return (Criteria) this;
        }

        public Criteria andParentRegionNameIn(List<String> values) {
            addCriterion("parent_region_name in", values, "parentRegionName");
            return (Criteria) this;
        }

        public Criteria andParentRegionNameNotIn(List<String> values) {
            addCriterion("parent_region_name not in", values, "parentRegionName");
            return (Criteria) this;
        }

        public Criteria andParentRegionNameBetween(String value1, String value2) {
            addCriterion("parent_region_name between", value1, value2, "parentRegionName");
            return (Criteria) this;
        }

        public Criteria andParentRegionNameNotBetween(String value1, String value2) {
            addCriterion("parent_region_name not between", value1, value2, "parentRegionName");
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

        public Criteria andIsOpenServiceRegionIsNull() {
            addCriterion("is_open_service_region is null");
            return (Criteria) this;
        }

        public Criteria andIsOpenServiceRegionIsNotNull() {
            addCriterion("is_open_service_region is not null");
            return (Criteria) this;
        }

        public Criteria andIsOpenServiceRegionEqualTo(Boolean value) {
            addCriterion("is_open_service_region =", value, "isOpenServiceRegion");
            return (Criteria) this;
        }

        public Criteria andIsOpenServiceRegionNotEqualTo(Boolean value) {
            addCriterion("is_open_service_region <>", value, "isOpenServiceRegion");
            return (Criteria) this;
        }

        public Criteria andIsOpenServiceRegionGreaterThan(Boolean value) {
            addCriterion("is_open_service_region >", value, "isOpenServiceRegion");
            return (Criteria) this;
        }

        public Criteria andIsOpenServiceRegionGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_open_service_region >=", value, "isOpenServiceRegion");
            return (Criteria) this;
        }

        public Criteria andIsOpenServiceRegionLessThan(Boolean value) {
            addCriterion("is_open_service_region <", value, "isOpenServiceRegion");
            return (Criteria) this;
        }

        public Criteria andIsOpenServiceRegionLessThanOrEqualTo(Boolean value) {
            addCriterion("is_open_service_region <=", value, "isOpenServiceRegion");
            return (Criteria) this;
        }

        public Criteria andIsOpenServiceRegionIn(List<Boolean> values) {
            addCriterion("is_open_service_region in", values, "isOpenServiceRegion");
            return (Criteria) this;
        }

        public Criteria andIsOpenServiceRegionNotIn(List<Boolean> values) {
            addCriterion("is_open_service_region not in", values, "isOpenServiceRegion");
            return (Criteria) this;
        }

        public Criteria andIsOpenServiceRegionBetween(Boolean value1, Boolean value2) {
            addCriterion("is_open_service_region between", value1, value2, "isOpenServiceRegion");
            return (Criteria) this;
        }

        public Criteria andIsOpenServiceRegionNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_open_service_region not between", value1, value2, "isOpenServiceRegion");
            return (Criteria) this;
        }

        public Criteria andIsOpenEntryRegionIsNull() {
            addCriterion("is_open_entry_region is null");
            return (Criteria) this;
        }

        public Criteria andIsOpenEntryRegionIsNotNull() {
            addCriterion("is_open_entry_region is not null");
            return (Criteria) this;
        }

        public Criteria andIsOpenEntryRegionEqualTo(Boolean value) {
            addCriterion("is_open_entry_region =", value, "isOpenEntryRegion");
            return (Criteria) this;
        }

        public Criteria andIsOpenEntryRegionNotEqualTo(Boolean value) {
            addCriterion("is_open_entry_region <>", value, "isOpenEntryRegion");
            return (Criteria) this;
        }

        public Criteria andIsOpenEntryRegionGreaterThan(Boolean value) {
            addCriterion("is_open_entry_region >", value, "isOpenEntryRegion");
            return (Criteria) this;
        }

        public Criteria andIsOpenEntryRegionGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_open_entry_region >=", value, "isOpenEntryRegion");
            return (Criteria) this;
        }

        public Criteria andIsOpenEntryRegionLessThan(Boolean value) {
            addCriterion("is_open_entry_region <", value, "isOpenEntryRegion");
            return (Criteria) this;
        }

        public Criteria andIsOpenEntryRegionLessThanOrEqualTo(Boolean value) {
            addCriterion("is_open_entry_region <=", value, "isOpenEntryRegion");
            return (Criteria) this;
        }

        public Criteria andIsOpenEntryRegionIn(List<Boolean> values) {
            addCriterion("is_open_entry_region in", values, "isOpenEntryRegion");
            return (Criteria) this;
        }

        public Criteria andIsOpenEntryRegionNotIn(List<Boolean> values) {
            addCriterion("is_open_entry_region not in", values, "isOpenEntryRegion");
            return (Criteria) this;
        }

        public Criteria andIsOpenEntryRegionBetween(Boolean value1, Boolean value2) {
            addCriterion("is_open_entry_region between", value1, value2, "isOpenEntryRegion");
            return (Criteria) this;
        }

        public Criteria andIsOpenEntryRegionNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_open_entry_region not between", value1, value2, "isOpenEntryRegion");
            return (Criteria) this;
        }

        public Criteria andIsProhibitionZoneIsNull() {
            addCriterion("is_prohibition_zone is null");
            return (Criteria) this;
        }

        public Criteria andIsProhibitionZoneIsNotNull() {
            addCriterion("is_prohibition_zone is not null");
            return (Criteria) this;
        }

        public Criteria andIsProhibitionZoneEqualTo(Boolean value) {
            addCriterion("is_prohibition_zone =", value, "isProhibitionZone");
            return (Criteria) this;
        }

        public Criteria andIsProhibitionZoneNotEqualTo(Boolean value) {
            addCriterion("is_prohibition_zone <>", value, "isProhibitionZone");
            return (Criteria) this;
        }

        public Criteria andIsProhibitionZoneGreaterThan(Boolean value) {
            addCriterion("is_prohibition_zone >", value, "isProhibitionZone");
            return (Criteria) this;
        }

        public Criteria andIsProhibitionZoneGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_prohibition_zone >=", value, "isProhibitionZone");
            return (Criteria) this;
        }

        public Criteria andIsProhibitionZoneLessThan(Boolean value) {
            addCriterion("is_prohibition_zone <", value, "isProhibitionZone");
            return (Criteria) this;
        }

        public Criteria andIsProhibitionZoneLessThanOrEqualTo(Boolean value) {
            addCriterion("is_prohibition_zone <=", value, "isProhibitionZone");
            return (Criteria) this;
        }

        public Criteria andIsProhibitionZoneIn(List<Boolean> values) {
            addCriterion("is_prohibition_zone in", values, "isProhibitionZone");
            return (Criteria) this;
        }

        public Criteria andIsProhibitionZoneNotIn(List<Boolean> values) {
            addCriterion("is_prohibition_zone not in", values, "isProhibitionZone");
            return (Criteria) this;
        }

        public Criteria andIsProhibitionZoneBetween(Boolean value1, Boolean value2) {
            addCriterion("is_prohibition_zone between", value1, value2, "isProhibitionZone");
            return (Criteria) this;
        }

        public Criteria andIsProhibitionZoneNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_prohibition_zone not between", value1, value2, "isProhibitionZone");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceTelIsNull() {
            addCriterion("customer_service_tel is null");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceTelIsNotNull() {
            addCriterion("customer_service_tel is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceTelEqualTo(String value) {
            addCriterion("customer_service_tel =", value, "customerServiceTel");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceTelNotEqualTo(String value) {
            addCriterion("customer_service_tel <>", value, "customerServiceTel");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceTelGreaterThan(String value) {
            addCriterion("customer_service_tel >", value, "customerServiceTel");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceTelGreaterThanOrEqualTo(String value) {
            addCriterion("customer_service_tel >=", value, "customerServiceTel");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceTelLessThan(String value) {
            addCriterion("customer_service_tel <", value, "customerServiceTel");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceTelLessThanOrEqualTo(String value) {
            addCriterion("customer_service_tel <=", value, "customerServiceTel");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceTelLike(String value) {
            addCriterion("customer_service_tel like", value, "customerServiceTel");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceTelNotLike(String value) {
            addCriterion("customer_service_tel not like", value, "customerServiceTel");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceTelIn(List<String> values) {
            addCriterion("customer_service_tel in", values, "customerServiceTel");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceTelNotIn(List<String> values) {
            addCriterion("customer_service_tel not in", values, "customerServiceTel");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceTelBetween(String value1, String value2) {
            addCriterion("customer_service_tel between", value1, value2, "customerServiceTel");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceTelNotBetween(String value1, String value2) {
            addCriterion("customer_service_tel not between", value1, value2, "customerServiceTel");
            return (Criteria) this;
        }

        public Criteria andOperationSpecialistIsNull() {
            addCriterion("operation_specialist is null");
            return (Criteria) this;
        }

        public Criteria andOperationSpecialistIsNotNull() {
            addCriterion("operation_specialist is not null");
            return (Criteria) this;
        }

        public Criteria andOperationSpecialistEqualTo(String value) {
            addCriterion("operation_specialist =", value, "operationSpecialist");
            return (Criteria) this;
        }

        public Criteria andOperationSpecialistNotEqualTo(String value) {
            addCriterion("operation_specialist <>", value, "operationSpecialist");
            return (Criteria) this;
        }

        public Criteria andOperationSpecialistGreaterThan(String value) {
            addCriterion("operation_specialist >", value, "operationSpecialist");
            return (Criteria) this;
        }

        public Criteria andOperationSpecialistGreaterThanOrEqualTo(String value) {
            addCriterion("operation_specialist >=", value, "operationSpecialist");
            return (Criteria) this;
        }

        public Criteria andOperationSpecialistLessThan(String value) {
            addCriterion("operation_specialist <", value, "operationSpecialist");
            return (Criteria) this;
        }

        public Criteria andOperationSpecialistLessThanOrEqualTo(String value) {
            addCriterion("operation_specialist <=", value, "operationSpecialist");
            return (Criteria) this;
        }

        public Criteria andOperationSpecialistLike(String value) {
            addCriterion("operation_specialist like", value, "operationSpecialist");
            return (Criteria) this;
        }

        public Criteria andOperationSpecialistNotLike(String value) {
            addCriterion("operation_specialist not like", value, "operationSpecialist");
            return (Criteria) this;
        }

        public Criteria andOperationSpecialistIn(List<String> values) {
            addCriterion("operation_specialist in", values, "operationSpecialist");
            return (Criteria) this;
        }

        public Criteria andOperationSpecialistNotIn(List<String> values) {
            addCriterion("operation_specialist not in", values, "operationSpecialist");
            return (Criteria) this;
        }

        public Criteria andOperationSpecialistBetween(String value1, String value2) {
            addCriterion("operation_specialist between", value1, value2, "operationSpecialist");
            return (Criteria) this;
        }

        public Criteria andOperationSpecialistNotBetween(String value1, String value2) {
            addCriterion("operation_specialist not between", value1, value2, "operationSpecialist");
            return (Criteria) this;
        }

        public Criteria andOperationSpecialistTelIsNull() {
            addCriterion("operation_specialist_tel is null");
            return (Criteria) this;
        }

        public Criteria andOperationSpecialistTelIsNotNull() {
            addCriterion("operation_specialist_tel is not null");
            return (Criteria) this;
        }

        public Criteria andOperationSpecialistTelEqualTo(String value) {
            addCriterion("operation_specialist_tel =", value, "operationSpecialistTel");
            return (Criteria) this;
        }

        public Criteria andOperationSpecialistTelNotEqualTo(String value) {
            addCriterion("operation_specialist_tel <>", value, "operationSpecialistTel");
            return (Criteria) this;
        }

        public Criteria andOperationSpecialistTelGreaterThan(String value) {
            addCriterion("operation_specialist_tel >", value, "operationSpecialistTel");
            return (Criteria) this;
        }

        public Criteria andOperationSpecialistTelGreaterThanOrEqualTo(String value) {
            addCriterion("operation_specialist_tel >=", value, "operationSpecialistTel");
            return (Criteria) this;
        }

        public Criteria andOperationSpecialistTelLessThan(String value) {
            addCriterion("operation_specialist_tel <", value, "operationSpecialistTel");
            return (Criteria) this;
        }

        public Criteria andOperationSpecialistTelLessThanOrEqualTo(String value) {
            addCriterion("operation_specialist_tel <=", value, "operationSpecialistTel");
            return (Criteria) this;
        }

        public Criteria andOperationSpecialistTelLike(String value) {
            addCriterion("operation_specialist_tel like", value, "operationSpecialistTel");
            return (Criteria) this;
        }

        public Criteria andOperationSpecialistTelNotLike(String value) {
            addCriterion("operation_specialist_tel not like", value, "operationSpecialistTel");
            return (Criteria) this;
        }

        public Criteria andOperationSpecialistTelIn(List<String> values) {
            addCriterion("operation_specialist_tel in", values, "operationSpecialistTel");
            return (Criteria) this;
        }

        public Criteria andOperationSpecialistTelNotIn(List<String> values) {
            addCriterion("operation_specialist_tel not in", values, "operationSpecialistTel");
            return (Criteria) this;
        }

        public Criteria andOperationSpecialistTelBetween(String value1, String value2) {
            addCriterion("operation_specialist_tel between", value1, value2, "operationSpecialistTel");
            return (Criteria) this;
        }

        public Criteria andOperationSpecialistTelNotBetween(String value1, String value2) {
            addCriterion("operation_specialist_tel not between", value1, value2, "operationSpecialistTel");
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