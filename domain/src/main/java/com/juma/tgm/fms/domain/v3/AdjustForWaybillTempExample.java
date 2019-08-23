package com.juma.tgm.fms.domain.v3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdjustForWaybillTempExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int startOffSet = -1;

    protected int size = -1;

    public AdjustForWaybillTempExample() {
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

    public AdjustForWaybillTempExample orderBy(String ... orderByClauses) {
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

        public Criteria andAdjustTempIdIsNull() {
            addCriterion("adjust_temp_id is null");
            return (Criteria) this;
        }

        public Criteria andAdjustTempIdIsNotNull() {
            addCriterion("adjust_temp_id is not null");
            return (Criteria) this;
        }

        public Criteria andAdjustTempIdEqualTo(Integer value) {
            addCriterion("adjust_temp_id =", value, "adjustTempId");
            return (Criteria) this;
        }

        public Criteria andAdjustTempIdNotEqualTo(Integer value) {
            addCriterion("adjust_temp_id <>", value, "adjustTempId");
            return (Criteria) this;
        }

        public Criteria andAdjustTempIdGreaterThan(Integer value) {
            addCriterion("adjust_temp_id >", value, "adjustTempId");
            return (Criteria) this;
        }

        public Criteria andAdjustTempIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("adjust_temp_id >=", value, "adjustTempId");
            return (Criteria) this;
        }

        public Criteria andAdjustTempIdLessThan(Integer value) {
            addCriterion("adjust_temp_id <", value, "adjustTempId");
            return (Criteria) this;
        }

        public Criteria andAdjustTempIdLessThanOrEqualTo(Integer value) {
            addCriterion("adjust_temp_id <=", value, "adjustTempId");
            return (Criteria) this;
        }

        public Criteria andAdjustTempIdIn(List<Integer> values) {
            addCriterion("adjust_temp_id in", values, "adjustTempId");
            return (Criteria) this;
        }

        public Criteria andAdjustTempIdNotIn(List<Integer> values) {
            addCriterion("adjust_temp_id not in", values, "adjustTempId");
            return (Criteria) this;
        }

        public Criteria andAdjustTempIdBetween(Integer value1, Integer value2) {
            addCriterion("adjust_temp_id between", value1, value2, "adjustTempId");
            return (Criteria) this;
        }

        public Criteria andAdjustTempIdNotBetween(Integer value1, Integer value2) {
            addCriterion("adjust_temp_id not between", value1, value2, "adjustTempId");
            return (Criteria) this;
        }

        public Criteria andAdjustIdIsNull() {
            addCriterion("adjust_id is null");
            return (Criteria) this;
        }

        public Criteria andAdjustIdIsNotNull() {
            addCriterion("adjust_id is not null");
            return (Criteria) this;
        }

        public Criteria andAdjustIdEqualTo(Integer value) {
            addCriterion("adjust_id =", value, "adjustId");
            return (Criteria) this;
        }

        public Criteria andAdjustIdNotEqualTo(Integer value) {
            addCriterion("adjust_id <>", value, "adjustId");
            return (Criteria) this;
        }

        public Criteria andAdjustIdGreaterThan(Integer value) {
            addCriterion("adjust_id >", value, "adjustId");
            return (Criteria) this;
        }

        public Criteria andAdjustIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("adjust_id >=", value, "adjustId");
            return (Criteria) this;
        }

        public Criteria andAdjustIdLessThan(Integer value) {
            addCriterion("adjust_id <", value, "adjustId");
            return (Criteria) this;
        }

        public Criteria andAdjustIdLessThanOrEqualTo(Integer value) {
            addCriterion("adjust_id <=", value, "adjustId");
            return (Criteria) this;
        }

        public Criteria andAdjustIdIn(List<Integer> values) {
            addCriterion("adjust_id in", values, "adjustId");
            return (Criteria) this;
        }

        public Criteria andAdjustIdNotIn(List<Integer> values) {
            addCriterion("adjust_id not in", values, "adjustId");
            return (Criteria) this;
        }

        public Criteria andAdjustIdBetween(Integer value1, Integer value2) {
            addCriterion("adjust_id between", value1, value2, "adjustId");
            return (Criteria) this;
        }

        public Criteria andAdjustIdNotBetween(Integer value1, Integer value2) {
            addCriterion("adjust_id not between", value1, value2, "adjustId");
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
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("tenant_code like", value, "tenantCode");
            return (Criteria) this;
        }

        public Criteria andTenantCodeNotLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("tenant_code not like", value, "tenantCode");
            return (Criteria) this;
        }

        public Criteria andTenantCodeLikeList(List<String> values) {
            addCriterion("tenant_code like", values, "tenantCode");
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
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("waybill_no like", value, "waybillNo");
            return (Criteria) this;
        }

        public Criteria andWaybillNoNotLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("waybill_no not like", value, "waybillNo");
            return (Criteria) this;
        }

        public Criteria andWaybillNoLikeList(List<String> values) {
            addCriterion("waybill_no like", values, "waybillNo");
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

        public Criteria andVendorIdIsNull() {
            addCriterion("vendor_id is null");
            return (Criteria) this;
        }

        public Criteria andVendorIdIsNotNull() {
            addCriterion("vendor_id is not null");
            return (Criteria) this;
        }

        public Criteria andVendorIdEqualTo(Integer value) {
            addCriterion("vendor_id =", value, "vendorId");
            return (Criteria) this;
        }

        public Criteria andVendorIdNotEqualTo(Integer value) {
            addCriterion("vendor_id <>", value, "vendorId");
            return (Criteria) this;
        }

        public Criteria andVendorIdGreaterThan(Integer value) {
            addCriterion("vendor_id >", value, "vendorId");
            return (Criteria) this;
        }

        public Criteria andVendorIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("vendor_id >=", value, "vendorId");
            return (Criteria) this;
        }

        public Criteria andVendorIdLessThan(Integer value) {
            addCriterion("vendor_id <", value, "vendorId");
            return (Criteria) this;
        }

        public Criteria andVendorIdLessThanOrEqualTo(Integer value) {
            addCriterion("vendor_id <=", value, "vendorId");
            return (Criteria) this;
        }

        public Criteria andVendorIdIn(List<Integer> values) {
            addCriterion("vendor_id in", values, "vendorId");
            return (Criteria) this;
        }

        public Criteria andVendorIdNotIn(List<Integer> values) {
            addCriterion("vendor_id not in", values, "vendorId");
            return (Criteria) this;
        }

        public Criteria andVendorIdBetween(Integer value1, Integer value2) {
            addCriterion("vendor_id between", value1, value2, "vendorId");
            return (Criteria) this;
        }

        public Criteria andVendorIdNotBetween(Integer value1, Integer value2) {
            addCriterion("vendor_id not between", value1, value2, "vendorId");
            return (Criteria) this;
        }

        public Criteria andFreightWithTaxIsNull() {
            addCriterion("freight_with_tax is null");
            return (Criteria) this;
        }

        public Criteria andFreightWithTaxIsNotNull() {
            addCriterion("freight_with_tax is not null");
            return (Criteria) this;
        }

        public Criteria andFreightWithTaxEqualTo(BigDecimal value) {
            addCriterion("freight_with_tax =", value, "freightWithTax");
            return (Criteria) this;
        }

        public Criteria andFreightWithTaxNotEqualTo(BigDecimal value) {
            addCriterion("freight_with_tax <>", value, "freightWithTax");
            return (Criteria) this;
        }

        public Criteria andFreightWithTaxGreaterThan(BigDecimal value) {
            addCriterion("freight_with_tax >", value, "freightWithTax");
            return (Criteria) this;
        }

        public Criteria andFreightWithTaxGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("freight_with_tax >=", value, "freightWithTax");
            return (Criteria) this;
        }

        public Criteria andFreightWithTaxLessThan(BigDecimal value) {
            addCriterion("freight_with_tax <", value, "freightWithTax");
            return (Criteria) this;
        }

        public Criteria andFreightWithTaxLessThanOrEqualTo(BigDecimal value) {
            addCriterion("freight_with_tax <=", value, "freightWithTax");
            return (Criteria) this;
        }

        public Criteria andFreightWithTaxIn(List<BigDecimal> values) {
            addCriterion("freight_with_tax in", values, "freightWithTax");
            return (Criteria) this;
        }

        public Criteria andFreightWithTaxNotIn(List<BigDecimal> values) {
            addCriterion("freight_with_tax not in", values, "freightWithTax");
            return (Criteria) this;
        }

        public Criteria andFreightWithTaxBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("freight_with_tax between", value1, value2, "freightWithTax");
            return (Criteria) this;
        }

        public Criteria andFreightWithTaxNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("freight_with_tax not between", value1, value2, "freightWithTax");
            return (Criteria) this;
        }

        public Criteria andOtherSideWithTaxIsNull() {
            addCriterion("other_side_with_tax is null");
            return (Criteria) this;
        }

        public Criteria andOtherSideWithTaxIsNotNull() {
            addCriterion("other_side_with_tax is not null");
            return (Criteria) this;
        }

        public Criteria andOtherSideWithTaxEqualTo(BigDecimal value) {
            addCriterion("other_side_with_tax =", value, "otherSideWithTax");
            return (Criteria) this;
        }

        public Criteria andOtherSideWithTaxNotEqualTo(BigDecimal value) {
            addCriterion("other_side_with_tax <>", value, "otherSideWithTax");
            return (Criteria) this;
        }

        public Criteria andOtherSideWithTaxGreaterThan(BigDecimal value) {
            addCriterion("other_side_with_tax >", value, "otherSideWithTax");
            return (Criteria) this;
        }

        public Criteria andOtherSideWithTaxGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("other_side_with_tax >=", value, "otherSideWithTax");
            return (Criteria) this;
        }

        public Criteria andOtherSideWithTaxLessThan(BigDecimal value) {
            addCriterion("other_side_with_tax <", value, "otherSideWithTax");
            return (Criteria) this;
        }

        public Criteria andOtherSideWithTaxLessThanOrEqualTo(BigDecimal value) {
            addCriterion("other_side_with_tax <=", value, "otherSideWithTax");
            return (Criteria) this;
        }

        public Criteria andOtherSideWithTaxIn(List<BigDecimal> values) {
            addCriterion("other_side_with_tax in", values, "otherSideWithTax");
            return (Criteria) this;
        }

        public Criteria andOtherSideWithTaxNotIn(List<BigDecimal> values) {
            addCriterion("other_side_with_tax not in", values, "otherSideWithTax");
            return (Criteria) this;
        }

        public Criteria andOtherSideWithTaxBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("other_side_with_tax between", value1, value2, "otherSideWithTax");
            return (Criteria) this;
        }

        public Criteria andOtherSideWithTaxNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("other_side_with_tax not between", value1, value2, "otherSideWithTax");
            return (Criteria) this;
        }

        public Criteria andAdjustForFreightIsNull() {
            addCriterion("adjust_for_freight is null");
            return (Criteria) this;
        }

        public Criteria andAdjustForFreightIsNotNull() {
            addCriterion("adjust_for_freight is not null");
            return (Criteria) this;
        }

        public Criteria andAdjustForFreightEqualTo(BigDecimal value) {
            addCriterion("adjust_for_freight =", value, "adjustForFreight");
            return (Criteria) this;
        }

        public Criteria andAdjustForFreightNotEqualTo(BigDecimal value) {
            addCriterion("adjust_for_freight <>", value, "adjustForFreight");
            return (Criteria) this;
        }

        public Criteria andAdjustForFreightGreaterThan(BigDecimal value) {
            addCriterion("adjust_for_freight >", value, "adjustForFreight");
            return (Criteria) this;
        }

        public Criteria andAdjustForFreightGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("adjust_for_freight >=", value, "adjustForFreight");
            return (Criteria) this;
        }

        public Criteria andAdjustForFreightLessThan(BigDecimal value) {
            addCriterion("adjust_for_freight <", value, "adjustForFreight");
            return (Criteria) this;
        }

        public Criteria andAdjustForFreightLessThanOrEqualTo(BigDecimal value) {
            addCriterion("adjust_for_freight <=", value, "adjustForFreight");
            return (Criteria) this;
        }

        public Criteria andAdjustForFreightIn(List<BigDecimal> values) {
            addCriterion("adjust_for_freight in", values, "adjustForFreight");
            return (Criteria) this;
        }

        public Criteria andAdjustForFreightNotIn(List<BigDecimal> values) {
            addCriterion("adjust_for_freight not in", values, "adjustForFreight");
            return (Criteria) this;
        }

        public Criteria andAdjustForFreightBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("adjust_for_freight between", value1, value2, "adjustForFreight");
            return (Criteria) this;
        }

        public Criteria andAdjustForFreightNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("adjust_for_freight not between", value1, value2, "adjustForFreight");
            return (Criteria) this;
        }

        public Criteria andAdjustForCarryIsNull() {
            addCriterion("adjust_for_carry is null");
            return (Criteria) this;
        }

        public Criteria andAdjustForCarryIsNotNull() {
            addCriterion("adjust_for_carry is not null");
            return (Criteria) this;
        }

        public Criteria andAdjustForCarryEqualTo(BigDecimal value) {
            addCriterion("adjust_for_carry =", value, "adjustForCarry");
            return (Criteria) this;
        }

        public Criteria andAdjustForCarryNotEqualTo(BigDecimal value) {
            addCriterion("adjust_for_carry <>", value, "adjustForCarry");
            return (Criteria) this;
        }

        public Criteria andAdjustForCarryGreaterThan(BigDecimal value) {
            addCriterion("adjust_for_carry >", value, "adjustForCarry");
            return (Criteria) this;
        }

        public Criteria andAdjustForCarryGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("adjust_for_carry >=", value, "adjustForCarry");
            return (Criteria) this;
        }

        public Criteria andAdjustForCarryLessThan(BigDecimal value) {
            addCriterion("adjust_for_carry <", value, "adjustForCarry");
            return (Criteria) this;
        }

        public Criteria andAdjustForCarryLessThanOrEqualTo(BigDecimal value) {
            addCriterion("adjust_for_carry <=", value, "adjustForCarry");
            return (Criteria) this;
        }

        public Criteria andAdjustForCarryIn(List<BigDecimal> values) {
            addCriterion("adjust_for_carry in", values, "adjustForCarry");
            return (Criteria) this;
        }

        public Criteria andAdjustForCarryNotIn(List<BigDecimal> values) {
            addCriterion("adjust_for_carry not in", values, "adjustForCarry");
            return (Criteria) this;
        }

        public Criteria andAdjustForCarryBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("adjust_for_carry between", value1, value2, "adjustForCarry");
            return (Criteria) this;
        }

        public Criteria andAdjustForCarryNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("adjust_for_carry not between", value1, value2, "adjustForCarry");
            return (Criteria) this;
        }

        public Criteria andAdjustForWorkloadIsNull() {
            addCriterion("adjust_for_workload is null");
            return (Criteria) this;
        }

        public Criteria andAdjustForWorkloadIsNotNull() {
            addCriterion("adjust_for_workload is not null");
            return (Criteria) this;
        }

        public Criteria andAdjustForWorkloadEqualTo(BigDecimal value) {
            addCriterion("adjust_for_workload =", value, "adjustForWorkload");
            return (Criteria) this;
        }

        public Criteria andAdjustForWorkloadNotEqualTo(BigDecimal value) {
            addCriterion("adjust_for_workload <>", value, "adjustForWorkload");
            return (Criteria) this;
        }

        public Criteria andAdjustForWorkloadGreaterThan(BigDecimal value) {
            addCriterion("adjust_for_workload >", value, "adjustForWorkload");
            return (Criteria) this;
        }

        public Criteria andAdjustForWorkloadGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("adjust_for_workload >=", value, "adjustForWorkload");
            return (Criteria) this;
        }

        public Criteria andAdjustForWorkloadLessThan(BigDecimal value) {
            addCriterion("adjust_for_workload <", value, "adjustForWorkload");
            return (Criteria) this;
        }

        public Criteria andAdjustForWorkloadLessThanOrEqualTo(BigDecimal value) {
            addCriterion("adjust_for_workload <=", value, "adjustForWorkload");
            return (Criteria) this;
        }

        public Criteria andAdjustForWorkloadIn(List<BigDecimal> values) {
            addCriterion("adjust_for_workload in", values, "adjustForWorkload");
            return (Criteria) this;
        }

        public Criteria andAdjustForWorkloadNotIn(List<BigDecimal> values) {
            addCriterion("adjust_for_workload not in", values, "adjustForWorkload");
            return (Criteria) this;
        }

        public Criteria andAdjustForWorkloadBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("adjust_for_workload between", value1, value2, "adjustForWorkload");
            return (Criteria) this;
        }

        public Criteria andAdjustForWorkloadNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("adjust_for_workload not between", value1, value2, "adjustForWorkload");
            return (Criteria) this;
        }

        public Criteria andAdjustForUpstairsIsNull() {
            addCriterion("adjust_for_upstairs is null");
            return (Criteria) this;
        }

        public Criteria andAdjustForUpstairsIsNotNull() {
            addCriterion("adjust_for_upstairs is not null");
            return (Criteria) this;
        }

        public Criteria andAdjustForUpstairsEqualTo(BigDecimal value) {
            addCriterion("adjust_for_upstairs =", value, "adjustForUpstairs");
            return (Criteria) this;
        }

        public Criteria andAdjustForUpstairsNotEqualTo(BigDecimal value) {
            addCriterion("adjust_for_upstairs <>", value, "adjustForUpstairs");
            return (Criteria) this;
        }

        public Criteria andAdjustForUpstairsGreaterThan(BigDecimal value) {
            addCriterion("adjust_for_upstairs >", value, "adjustForUpstairs");
            return (Criteria) this;
        }

        public Criteria andAdjustForUpstairsGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("adjust_for_upstairs >=", value, "adjustForUpstairs");
            return (Criteria) this;
        }

        public Criteria andAdjustForUpstairsLessThan(BigDecimal value) {
            addCriterion("adjust_for_upstairs <", value, "adjustForUpstairs");
            return (Criteria) this;
        }

        public Criteria andAdjustForUpstairsLessThanOrEqualTo(BigDecimal value) {
            addCriterion("adjust_for_upstairs <=", value, "adjustForUpstairs");
            return (Criteria) this;
        }

        public Criteria andAdjustForUpstairsIn(List<BigDecimal> values) {
            addCriterion("adjust_for_upstairs in", values, "adjustForUpstairs");
            return (Criteria) this;
        }

        public Criteria andAdjustForUpstairsNotIn(List<BigDecimal> values) {
            addCriterion("adjust_for_upstairs not in", values, "adjustForUpstairs");
            return (Criteria) this;
        }

        public Criteria andAdjustForUpstairsBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("adjust_for_upstairs between", value1, value2, "adjustForUpstairs");
            return (Criteria) this;
        }

        public Criteria andAdjustForUpstairsNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("adjust_for_upstairs not between", value1, value2, "adjustForUpstairs");
            return (Criteria) this;
        }

        public Criteria andAdjustForFineIsNull() {
            addCriterion("adjust_for_fine is null");
            return (Criteria) this;
        }

        public Criteria andAdjustForFineIsNotNull() {
            addCriterion("adjust_for_fine is not null");
            return (Criteria) this;
        }

        public Criteria andAdjustForFineEqualTo(BigDecimal value) {
            addCriterion("adjust_for_fine =", value, "adjustForFine");
            return (Criteria) this;
        }

        public Criteria andAdjustForFineNotEqualTo(BigDecimal value) {
            addCriterion("adjust_for_fine <>", value, "adjustForFine");
            return (Criteria) this;
        }

        public Criteria andAdjustForFineGreaterThan(BigDecimal value) {
            addCriterion("adjust_for_fine >", value, "adjustForFine");
            return (Criteria) this;
        }

        public Criteria andAdjustForFineGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("adjust_for_fine >=", value, "adjustForFine");
            return (Criteria) this;
        }

        public Criteria andAdjustForFineLessThan(BigDecimal value) {
            addCriterion("adjust_for_fine <", value, "adjustForFine");
            return (Criteria) this;
        }

        public Criteria andAdjustForFineLessThanOrEqualTo(BigDecimal value) {
            addCriterion("adjust_for_fine <=", value, "adjustForFine");
            return (Criteria) this;
        }

        public Criteria andAdjustForFineIn(List<BigDecimal> values) {
            addCriterion("adjust_for_fine in", values, "adjustForFine");
            return (Criteria) this;
        }

        public Criteria andAdjustForFineNotIn(List<BigDecimal> values) {
            addCriterion("adjust_for_fine not in", values, "adjustForFine");
            return (Criteria) this;
        }

        public Criteria andAdjustForFineBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("adjust_for_fine between", value1, value2, "adjustForFine");
            return (Criteria) this;
        }

        public Criteria andAdjustForFineNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("adjust_for_fine not between", value1, value2, "adjustForFine");
            return (Criteria) this;
        }

        public Criteria andAdjustForCargoLossIsNull() {
            addCriterion("adjust_for_cargo_loss is null");
            return (Criteria) this;
        }

        public Criteria andAdjustForCargoLossIsNotNull() {
            addCriterion("adjust_for_cargo_loss is not null");
            return (Criteria) this;
        }

        public Criteria andAdjustForCargoLossEqualTo(BigDecimal value) {
            addCriterion("adjust_for_cargo_loss =", value, "adjustForCargoLoss");
            return (Criteria) this;
        }

        public Criteria andAdjustForCargoLossNotEqualTo(BigDecimal value) {
            addCriterion("adjust_for_cargo_loss <>", value, "adjustForCargoLoss");
            return (Criteria) this;
        }

        public Criteria andAdjustForCargoLossGreaterThan(BigDecimal value) {
            addCriterion("adjust_for_cargo_loss >", value, "adjustForCargoLoss");
            return (Criteria) this;
        }

        public Criteria andAdjustForCargoLossGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("adjust_for_cargo_loss >=", value, "adjustForCargoLoss");
            return (Criteria) this;
        }

        public Criteria andAdjustForCargoLossLessThan(BigDecimal value) {
            addCriterion("adjust_for_cargo_loss <", value, "adjustForCargoLoss");
            return (Criteria) this;
        }

        public Criteria andAdjustForCargoLossLessThanOrEqualTo(BigDecimal value) {
            addCriterion("adjust_for_cargo_loss <=", value, "adjustForCargoLoss");
            return (Criteria) this;
        }

        public Criteria andAdjustForCargoLossIn(List<BigDecimal> values) {
            addCriterion("adjust_for_cargo_loss in", values, "adjustForCargoLoss");
            return (Criteria) this;
        }

        public Criteria andAdjustForCargoLossNotIn(List<BigDecimal> values) {
            addCriterion("adjust_for_cargo_loss not in", values, "adjustForCargoLoss");
            return (Criteria) this;
        }

        public Criteria andAdjustForCargoLossBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("adjust_for_cargo_loss between", value1, value2, "adjustForCargoLoss");
            return (Criteria) this;
        }

        public Criteria andAdjustForCargoLossNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("adjust_for_cargo_loss not between", value1, value2, "adjustForCargoLoss");
            return (Criteria) this;
        }

        public Criteria andAdjustForReasonIsNull() {
            addCriterion("adjust_for_reason is null");
            return (Criteria) this;
        }

        public Criteria andAdjustForReasonIsNotNull() {
            addCriterion("adjust_for_reason is not null");
            return (Criteria) this;
        }

        public Criteria andAdjustForReasonEqualTo(String value) {
            addCriterion("adjust_for_reason =", value, "adjustForReason");
            return (Criteria) this;
        }

        public Criteria andAdjustForReasonNotEqualTo(String value) {
            addCriterion("adjust_for_reason <>", value, "adjustForReason");
            return (Criteria) this;
        }

        public Criteria andAdjustForReasonGreaterThan(String value) {
            addCriterion("adjust_for_reason >", value, "adjustForReason");
            return (Criteria) this;
        }

        public Criteria andAdjustForReasonGreaterThanOrEqualTo(String value) {
            addCriterion("adjust_for_reason >=", value, "adjustForReason");
            return (Criteria) this;
        }

        public Criteria andAdjustForReasonLessThan(String value) {
            addCriterion("adjust_for_reason <", value, "adjustForReason");
            return (Criteria) this;
        }

        public Criteria andAdjustForReasonLessThanOrEqualTo(String value) {
            addCriterion("adjust_for_reason <=", value, "adjustForReason");
            return (Criteria) this;
        }

        public Criteria andAdjustForReasonLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("adjust_for_reason like", value, "adjustForReason");
            return (Criteria) this;
        }

        public Criteria andAdjustForReasonNotLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("adjust_for_reason not like", value, "adjustForReason");
            return (Criteria) this;
        }

        public Criteria andAdjustForReasonLikeList(List<String> values) {
            addCriterion("adjust_for_reason like", values, "adjustForReason");
            return (Criteria) this;
        }

        public Criteria andAdjustForReasonIn(List<String> values) {
            addCriterion("adjust_for_reason in", values, "adjustForReason");
            return (Criteria) this;
        }

        public Criteria andAdjustForReasonNotIn(List<String> values) {
            addCriterion("adjust_for_reason not in", values, "adjustForReason");
            return (Criteria) this;
        }

        public Criteria andAdjustForReasonBetween(String value1, String value2) {
            addCriterion("adjust_for_reason between", value1, value2, "adjustForReason");
            return (Criteria) this;
        }

        public Criteria andAdjustForReasonNotBetween(String value1, String value2) {
            addCriterion("adjust_for_reason not between", value1, value2, "adjustForReason");
            return (Criteria) this;
        }

        public Criteria andValidStatusIsNull() {
            addCriterion("valid_status is null");
            return (Criteria) this;
        }

        public Criteria andValidStatusIsNotNull() {
            addCriterion("valid_status is not null");
            return (Criteria) this;
        }

        public Criteria andValidStatusEqualTo(Integer value) {
            addCriterion("valid_status =", value, "validStatus");
            return (Criteria) this;
        }

        public Criteria andValidStatusNotEqualTo(Integer value) {
            addCriterion("valid_status <>", value, "validStatus");
            return (Criteria) this;
        }

        public Criteria andValidStatusGreaterThan(Integer value) {
            addCriterion("valid_status >", value, "validStatus");
            return (Criteria) this;
        }

        public Criteria andValidStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("valid_status >=", value, "validStatus");
            return (Criteria) this;
        }

        public Criteria andValidStatusLessThan(Integer value) {
            addCriterion("valid_status <", value, "validStatus");
            return (Criteria) this;
        }

        public Criteria andValidStatusLessThanOrEqualTo(Integer value) {
            addCriterion("valid_status <=", value, "validStatus");
            return (Criteria) this;
        }

        public Criteria andValidStatusIn(List<Integer> values) {
            addCriterion("valid_status in", values, "validStatus");
            return (Criteria) this;
        }

        public Criteria andValidStatusNotIn(List<Integer> values) {
            addCriterion("valid_status not in", values, "validStatus");
            return (Criteria) this;
        }

        public Criteria andValidStatusBetween(Integer value1, Integer value2) {
            addCriterion("valid_status between", value1, value2, "validStatus");
            return (Criteria) this;
        }

        public Criteria andValidStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("valid_status not between", value1, value2, "validStatus");
            return (Criteria) this;
        }

        public Criteria andValidResultIsNull() {
            addCriterion("valid_result is null");
            return (Criteria) this;
        }

        public Criteria andValidResultIsNotNull() {
            addCriterion("valid_result is not null");
            return (Criteria) this;
        }

        public Criteria andValidResultEqualTo(String value) {
            addCriterion("valid_result =", value, "validResult");
            return (Criteria) this;
        }

        public Criteria andValidResultNotEqualTo(String value) {
            addCriterion("valid_result <>", value, "validResult");
            return (Criteria) this;
        }

        public Criteria andValidResultGreaterThan(String value) {
            addCriterion("valid_result >", value, "validResult");
            return (Criteria) this;
        }

        public Criteria andValidResultGreaterThanOrEqualTo(String value) {
            addCriterion("valid_result >=", value, "validResult");
            return (Criteria) this;
        }

        public Criteria andValidResultLessThan(String value) {
            addCriterion("valid_result <", value, "validResult");
            return (Criteria) this;
        }

        public Criteria andValidResultLessThanOrEqualTo(String value) {
            addCriterion("valid_result <=", value, "validResult");
            return (Criteria) this;
        }

        public Criteria andValidResultLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("valid_result like", value, "validResult");
            return (Criteria) this;
        }

        public Criteria andValidResultNotLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("valid_result not like", value, "validResult");
            return (Criteria) this;
        }

        public Criteria andValidResultLikeList(List<String> values) {
            addCriterion("valid_result like", values, "validResult");
            return (Criteria) this;
        }

        public Criteria andValidResultIn(List<String> values) {
            addCriterion("valid_result in", values, "validResult");
            return (Criteria) this;
        }

        public Criteria andValidResultNotIn(List<String> values) {
            addCriterion("valid_result not in", values, "validResult");
            return (Criteria) this;
        }

        public Criteria andValidResultBetween(String value1, String value2) {
            addCriterion("valid_result between", value1, value2, "validResult");
            return (Criteria) this;
        }

        public Criteria andValidResultNotBetween(String value1, String value2) {
            addCriterion("valid_result not between", value1, value2, "validResult");
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
        private AdjustForWaybillTempExample example;

        protected Criteria(AdjustForWaybillTempExample example) {
            super();
            this.example = example;
        }

        public AdjustForWaybillTempExample example() {
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