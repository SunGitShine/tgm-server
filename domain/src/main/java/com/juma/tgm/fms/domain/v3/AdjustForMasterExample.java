package com.juma.tgm.fms.domain.v3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdjustForMasterExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int startOffSet = -1;

    protected int size = -1;

    public AdjustForMasterExample() {
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

    public AdjustForMasterExample orderBy(String ... orderByClauses) {
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

        public Criteria andAdjustNoIsNull() {
            addCriterion("adjust_no is null");
            return (Criteria) this;
        }

        public Criteria andAdjustNoIsNotNull() {
            addCriterion("adjust_no is not null");
            return (Criteria) this;
        }

        public Criteria andAdjustNoEqualTo(String value) {
            addCriterion("adjust_no =", value, "adjustNo");
            return (Criteria) this;
        }

        public Criteria andAdjustNoNotEqualTo(String value) {
            addCriterion("adjust_no <>", value, "adjustNo");
            return (Criteria) this;
        }

        public Criteria andAdjustNoGreaterThan(String value) {
            addCriterion("adjust_no >", value, "adjustNo");
            return (Criteria) this;
        }

        public Criteria andAdjustNoGreaterThanOrEqualTo(String value) {
            addCriterion("adjust_no >=", value, "adjustNo");
            return (Criteria) this;
        }

        public Criteria andAdjustNoLessThan(String value) {
            addCriterion("adjust_no <", value, "adjustNo");
            return (Criteria) this;
        }

        public Criteria andAdjustNoLessThanOrEqualTo(String value) {
            addCriterion("adjust_no <=", value, "adjustNo");
            return (Criteria) this;
        }

        public Criteria andAdjustNoLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("adjust_no like", value, "adjustNo");
            return (Criteria) this;
        }

        public Criteria andAdjustNoNotLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("adjust_no not like", value, "adjustNo");
            return (Criteria) this;
        }

        public Criteria andAdjustNoLikeList(List<String> values) {
            addCriterion("adjust_no like", values, "adjustNo");
            return (Criteria) this;
        }

        public Criteria andAdjustNoIn(List<String> values) {
            addCriterion("adjust_no in", values, "adjustNo");
            return (Criteria) this;
        }

        public Criteria andAdjustNoNotIn(List<String> values) {
            addCriterion("adjust_no not in", values, "adjustNo");
            return (Criteria) this;
        }

        public Criteria andAdjustNoBetween(String value1, String value2) {
            addCriterion("adjust_no between", value1, value2, "adjustNo");
            return (Criteria) this;
        }

        public Criteria andAdjustNoNotBetween(String value1, String value2) {
            addCriterion("adjust_no not between", value1, value2, "adjustNo");
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
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("area_code like", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeNotLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("area_code not like", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeLikeList(List<String> values) {
            addCriterion("area_code like", values, "areaCode");
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

        public Criteria andReconcilicationNoIsNull() {
            addCriterion("reconcilication_no is null");
            return (Criteria) this;
        }

        public Criteria andReconcilicationNoIsNotNull() {
            addCriterion("reconcilication_no is not null");
            return (Criteria) this;
        }

        public Criteria andReconcilicationNoEqualTo(String value) {
            addCriterion("reconcilication_no =", value, "reconcilicationNo");
            return (Criteria) this;
        }

        public Criteria andReconcilicationNoNotEqualTo(String value) {
            addCriterion("reconcilication_no <>", value, "reconcilicationNo");
            return (Criteria) this;
        }

        public Criteria andReconcilicationNoGreaterThan(String value) {
            addCriterion("reconcilication_no >", value, "reconcilicationNo");
            return (Criteria) this;
        }

        public Criteria andReconcilicationNoGreaterThanOrEqualTo(String value) {
            addCriterion("reconcilication_no >=", value, "reconcilicationNo");
            return (Criteria) this;
        }

        public Criteria andReconcilicationNoLessThan(String value) {
            addCriterion("reconcilication_no <", value, "reconcilicationNo");
            return (Criteria) this;
        }

        public Criteria andReconcilicationNoLessThanOrEqualTo(String value) {
            addCriterion("reconcilication_no <=", value, "reconcilicationNo");
            return (Criteria) this;
        }

        public Criteria andReconcilicationNoLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("reconcilication_no like", value, "reconcilicationNo");
            return (Criteria) this;
        }

        public Criteria andReconcilicationNoNotLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("reconcilication_no not like", value, "reconcilicationNo");
            return (Criteria) this;
        }

        public Criteria andReconcilicationNoLikeList(List<String> values) {
            addCriterion("reconcilication_no like", values, "reconcilicationNo");
            return (Criteria) this;
        }

        public Criteria andReconcilicationNoIn(List<String> values) {
            addCriterion("reconcilication_no in", values, "reconcilicationNo");
            return (Criteria) this;
        }

        public Criteria andReconcilicationNoNotIn(List<String> values) {
            addCriterion("reconcilication_no not in", values, "reconcilicationNo");
            return (Criteria) this;
        }

        public Criteria andReconcilicationNoBetween(String value1, String value2) {
            addCriterion("reconcilication_no between", value1, value2, "reconcilicationNo");
            return (Criteria) this;
        }

        public Criteria andReconcilicationNoNotBetween(String value1, String value2) {
            addCriterion("reconcilication_no not between", value1, value2, "reconcilicationNo");
            return (Criteria) this;
        }

        public Criteria andBeforeAdjustAmountIsNull() {
            addCriterion("before_adjust_amount is null");
            return (Criteria) this;
        }

        public Criteria andBeforeAdjustAmountIsNotNull() {
            addCriterion("before_adjust_amount is not null");
            return (Criteria) this;
        }

        public Criteria andBeforeAdjustAmountEqualTo(BigDecimal value) {
            addCriterion("before_adjust_amount =", value, "beforeAdjustAmount");
            return (Criteria) this;
        }

        public Criteria andBeforeAdjustAmountNotEqualTo(BigDecimal value) {
            addCriterion("before_adjust_amount <>", value, "beforeAdjustAmount");
            return (Criteria) this;
        }

        public Criteria andBeforeAdjustAmountGreaterThan(BigDecimal value) {
            addCriterion("before_adjust_amount >", value, "beforeAdjustAmount");
            return (Criteria) this;
        }

        public Criteria andBeforeAdjustAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("before_adjust_amount >=", value, "beforeAdjustAmount");
            return (Criteria) this;
        }

        public Criteria andBeforeAdjustAmountLessThan(BigDecimal value) {
            addCriterion("before_adjust_amount <", value, "beforeAdjustAmount");
            return (Criteria) this;
        }

        public Criteria andBeforeAdjustAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("before_adjust_amount <=", value, "beforeAdjustAmount");
            return (Criteria) this;
        }

        public Criteria andBeforeAdjustAmountIn(List<BigDecimal> values) {
            addCriterion("before_adjust_amount in", values, "beforeAdjustAmount");
            return (Criteria) this;
        }

        public Criteria andBeforeAdjustAmountNotIn(List<BigDecimal> values) {
            addCriterion("before_adjust_amount not in", values, "beforeAdjustAmount");
            return (Criteria) this;
        }

        public Criteria andBeforeAdjustAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("before_adjust_amount between", value1, value2, "beforeAdjustAmount");
            return (Criteria) this;
        }

        public Criteria andBeforeAdjustAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("before_adjust_amount not between", value1, value2, "beforeAdjustAmount");
            return (Criteria) this;
        }

        public Criteria andBeforeAdjustWithselfAmountIsNull() {
            addCriterion("before_adjust_withself_amount is null");
            return (Criteria) this;
        }

        public Criteria andBeforeAdjustWithselfAmountIsNotNull() {
            addCriterion("before_adjust_withself_amount is not null");
            return (Criteria) this;
        }

        public Criteria andBeforeAdjustWithselfAmountEqualTo(BigDecimal value) {
            addCriterion("before_adjust_withself_amount =", value, "beforeAdjustWithselfAmount");
            return (Criteria) this;
        }

        public Criteria andBeforeAdjustWithselfAmountNotEqualTo(BigDecimal value) {
            addCriterion("before_adjust_withself_amount <>", value, "beforeAdjustWithselfAmount");
            return (Criteria) this;
        }

        public Criteria andBeforeAdjustWithselfAmountGreaterThan(BigDecimal value) {
            addCriterion("before_adjust_withself_amount >", value, "beforeAdjustWithselfAmount");
            return (Criteria) this;
        }

        public Criteria andBeforeAdjustWithselfAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("before_adjust_withself_amount >=", value, "beforeAdjustWithselfAmount");
            return (Criteria) this;
        }

        public Criteria andBeforeAdjustWithselfAmountLessThan(BigDecimal value) {
            addCriterion("before_adjust_withself_amount <", value, "beforeAdjustWithselfAmount");
            return (Criteria) this;
        }

        public Criteria andBeforeAdjustWithselfAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("before_adjust_withself_amount <=", value, "beforeAdjustWithselfAmount");
            return (Criteria) this;
        }

        public Criteria andBeforeAdjustWithselfAmountIn(List<BigDecimal> values) {
            addCriterion("before_adjust_withself_amount in", values, "beforeAdjustWithselfAmount");
            return (Criteria) this;
        }

        public Criteria andBeforeAdjustWithselfAmountNotIn(List<BigDecimal> values) {
            addCriterion("before_adjust_withself_amount not in", values, "beforeAdjustWithselfAmount");
            return (Criteria) this;
        }

        public Criteria andBeforeAdjustWithselfAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("before_adjust_withself_amount between", value1, value2, "beforeAdjustWithselfAmount");
            return (Criteria) this;
        }

        public Criteria andBeforeAdjustWithselfAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("before_adjust_withself_amount not between", value1, value2, "beforeAdjustWithselfAmount");
            return (Criteria) this;
        }

        public Criteria andAdjustAmountIsNull() {
            addCriterion("adjust_amount is null");
            return (Criteria) this;
        }

        public Criteria andAdjustAmountIsNotNull() {
            addCriterion("adjust_amount is not null");
            return (Criteria) this;
        }

        public Criteria andAdjustAmountEqualTo(BigDecimal value) {
            addCriterion("adjust_amount =", value, "adjustAmount");
            return (Criteria) this;
        }

        public Criteria andAdjustAmountNotEqualTo(BigDecimal value) {
            addCriterion("adjust_amount <>", value, "adjustAmount");
            return (Criteria) this;
        }

        public Criteria andAdjustAmountGreaterThan(BigDecimal value) {
            addCriterion("adjust_amount >", value, "adjustAmount");
            return (Criteria) this;
        }

        public Criteria andAdjustAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("adjust_amount >=", value, "adjustAmount");
            return (Criteria) this;
        }

        public Criteria andAdjustAmountLessThan(BigDecimal value) {
            addCriterion("adjust_amount <", value, "adjustAmount");
            return (Criteria) this;
        }

        public Criteria andAdjustAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("adjust_amount <=", value, "adjustAmount");
            return (Criteria) this;
        }

        public Criteria andAdjustAmountIn(List<BigDecimal> values) {
            addCriterion("adjust_amount in", values, "adjustAmount");
            return (Criteria) this;
        }

        public Criteria andAdjustAmountNotIn(List<BigDecimal> values) {
            addCriterion("adjust_amount not in", values, "adjustAmount");
            return (Criteria) this;
        }

        public Criteria andAdjustAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("adjust_amount between", value1, value2, "adjustAmount");
            return (Criteria) this;
        }

        public Criteria andAdjustAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("adjust_amount not between", value1, value2, "adjustAmount");
            return (Criteria) this;
        }

        public Criteria andAdjustAbsAmountIsNull() {
            addCriterion("adjust_abs_amount is null");
            return (Criteria) this;
        }

        public Criteria andAdjustAbsAmountIsNotNull() {
            addCriterion("adjust_abs_amount is not null");
            return (Criteria) this;
        }

        public Criteria andAdjustAbsAmountEqualTo(BigDecimal value) {
            addCriterion("adjust_abs_amount =", value, "adjustAbsAmount");
            return (Criteria) this;
        }

        public Criteria andAdjustAbsAmountNotEqualTo(BigDecimal value) {
            addCriterion("adjust_abs_amount <>", value, "adjustAbsAmount");
            return (Criteria) this;
        }

        public Criteria andAdjustAbsAmountGreaterThan(BigDecimal value) {
            addCriterion("adjust_abs_amount >", value, "adjustAbsAmount");
            return (Criteria) this;
        }

        public Criteria andAdjustAbsAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("adjust_abs_amount >=", value, "adjustAbsAmount");
            return (Criteria) this;
        }

        public Criteria andAdjustAbsAmountLessThan(BigDecimal value) {
            addCriterion("adjust_abs_amount <", value, "adjustAbsAmount");
            return (Criteria) this;
        }

        public Criteria andAdjustAbsAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("adjust_abs_amount <=", value, "adjustAbsAmount");
            return (Criteria) this;
        }

        public Criteria andAdjustAbsAmountIn(List<BigDecimal> values) {
            addCriterion("adjust_abs_amount in", values, "adjustAbsAmount");
            return (Criteria) this;
        }

        public Criteria andAdjustAbsAmountNotIn(List<BigDecimal> values) {
            addCriterion("adjust_abs_amount not in", values, "adjustAbsAmount");
            return (Criteria) this;
        }

        public Criteria andAdjustAbsAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("adjust_abs_amount between", value1, value2, "adjustAbsAmount");
            return (Criteria) this;
        }

        public Criteria andAdjustAbsAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("adjust_abs_amount not between", value1, value2, "adjustAbsAmount");
            return (Criteria) this;
        }

        public Criteria andOtherSideAmountIsNull() {
            addCriterion("other_side_amount is null");
            return (Criteria) this;
        }

        public Criteria andOtherSideAmountIsNotNull() {
            addCriterion("other_side_amount is not null");
            return (Criteria) this;
        }

        public Criteria andOtherSideAmountEqualTo(BigDecimal value) {
            addCriterion("other_side_amount =", value, "otherSideAmount");
            return (Criteria) this;
        }

        public Criteria andOtherSideAmountNotEqualTo(BigDecimal value) {
            addCriterion("other_side_amount <>", value, "otherSideAmount");
            return (Criteria) this;
        }

        public Criteria andOtherSideAmountGreaterThan(BigDecimal value) {
            addCriterion("other_side_amount >", value, "otherSideAmount");
            return (Criteria) this;
        }

        public Criteria andOtherSideAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("other_side_amount >=", value, "otherSideAmount");
            return (Criteria) this;
        }

        public Criteria andOtherSideAmountLessThan(BigDecimal value) {
            addCriterion("other_side_amount <", value, "otherSideAmount");
            return (Criteria) this;
        }

        public Criteria andOtherSideAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("other_side_amount <=", value, "otherSideAmount");
            return (Criteria) this;
        }

        public Criteria andOtherSideAmountIn(List<BigDecimal> values) {
            addCriterion("other_side_amount in", values, "otherSideAmount");
            return (Criteria) this;
        }

        public Criteria andOtherSideAmountNotIn(List<BigDecimal> values) {
            addCriterion("other_side_amount not in", values, "otherSideAmount");
            return (Criteria) this;
        }

        public Criteria andOtherSideAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("other_side_amount between", value1, value2, "otherSideAmount");
            return (Criteria) this;
        }

        public Criteria andOtherSideAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("other_side_amount not between", value1, value2, "otherSideAmount");
            return (Criteria) this;
        }

        public Criteria andOtherSideWithselfAmountIsNull() {
            addCriterion("other_side_withself_amount is null");
            return (Criteria) this;
        }

        public Criteria andOtherSideWithselfAmountIsNotNull() {
            addCriterion("other_side_withself_amount is not null");
            return (Criteria) this;
        }

        public Criteria andOtherSideWithselfAmountEqualTo(BigDecimal value) {
            addCriterion("other_side_withself_amount =", value, "otherSideWithselfAmount");
            return (Criteria) this;
        }

        public Criteria andOtherSideWithselfAmountNotEqualTo(BigDecimal value) {
            addCriterion("other_side_withself_amount <>", value, "otherSideWithselfAmount");
            return (Criteria) this;
        }

        public Criteria andOtherSideWithselfAmountGreaterThan(BigDecimal value) {
            addCriterion("other_side_withself_amount >", value, "otherSideWithselfAmount");
            return (Criteria) this;
        }

        public Criteria andOtherSideWithselfAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("other_side_withself_amount >=", value, "otherSideWithselfAmount");
            return (Criteria) this;
        }

        public Criteria andOtherSideWithselfAmountLessThan(BigDecimal value) {
            addCriterion("other_side_withself_amount <", value, "otherSideWithselfAmount");
            return (Criteria) this;
        }

        public Criteria andOtherSideWithselfAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("other_side_withself_amount <=", value, "otherSideWithselfAmount");
            return (Criteria) this;
        }

        public Criteria andOtherSideWithselfAmountIn(List<BigDecimal> values) {
            addCriterion("other_side_withself_amount in", values, "otherSideWithselfAmount");
            return (Criteria) this;
        }

        public Criteria andOtherSideWithselfAmountNotIn(List<BigDecimal> values) {
            addCriterion("other_side_withself_amount not in", values, "otherSideWithselfAmount");
            return (Criteria) this;
        }

        public Criteria andOtherSideWithselfAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("other_side_withself_amount between", value1, value2, "otherSideWithselfAmount");
            return (Criteria) this;
        }

        public Criteria andOtherSideWithselfAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("other_side_withself_amount not between", value1, value2, "otherSideWithselfAmount");
            return (Criteria) this;
        }

        public Criteria andApprovalTimeIsNull() {
            addCriterion("approval_time is null");
            return (Criteria) this;
        }

        public Criteria andApprovalTimeIsNotNull() {
            addCriterion("approval_time is not null");
            return (Criteria) this;
        }

        public Criteria andApprovalTimeEqualTo(Date value) {
            addCriterion("approval_time =", value, "approvalTime");
            return (Criteria) this;
        }

        public Criteria andApprovalTimeNotEqualTo(Date value) {
            addCriterion("approval_time <>", value, "approvalTime");
            return (Criteria) this;
        }

        public Criteria andApprovalTimeGreaterThan(Date value) {
            addCriterion("approval_time >", value, "approvalTime");
            return (Criteria) this;
        }

        public Criteria andApprovalTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("approval_time >=", value, "approvalTime");
            return (Criteria) this;
        }

        public Criteria andApprovalTimeLessThan(Date value) {
            addCriterion("approval_time <", value, "approvalTime");
            return (Criteria) this;
        }

        public Criteria andApprovalTimeLessThanOrEqualTo(Date value) {
            addCriterion("approval_time <=", value, "approvalTime");
            return (Criteria) this;
        }

        public Criteria andApprovalTimeIn(List<Date> values) {
            addCriterion("approval_time in", values, "approvalTime");
            return (Criteria) this;
        }

        public Criteria andApprovalTimeNotIn(List<Date> values) {
            addCriterion("approval_time not in", values, "approvalTime");
            return (Criteria) this;
        }

        public Criteria andApprovalTimeBetween(Date value1, Date value2) {
            addCriterion("approval_time between", value1, value2, "approvalTime");
            return (Criteria) this;
        }

        public Criteria andApprovalTimeNotBetween(Date value1, Date value2) {
            addCriterion("approval_time not between", value1, value2, "approvalTime");
            return (Criteria) this;
        }

        public Criteria andApprovalStatusIsNull() {
            addCriterion("approval_status is null");
            return (Criteria) this;
        }

        public Criteria andApprovalStatusIsNotNull() {
            addCriterion("approval_status is not null");
            return (Criteria) this;
        }

        public Criteria andApprovalStatusEqualTo(Integer value) {
            addCriterion("approval_status =", value, "approvalStatus");
            return (Criteria) this;
        }

        public Criteria andApprovalStatusNotEqualTo(Integer value) {
            addCriterion("approval_status <>", value, "approvalStatus");
            return (Criteria) this;
        }

        public Criteria andApprovalStatusGreaterThan(Integer value) {
            addCriterion("approval_status >", value, "approvalStatus");
            return (Criteria) this;
        }

        public Criteria andApprovalStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("approval_status >=", value, "approvalStatus");
            return (Criteria) this;
        }

        public Criteria andApprovalStatusLessThan(Integer value) {
            addCriterion("approval_status <", value, "approvalStatus");
            return (Criteria) this;
        }

        public Criteria andApprovalStatusLessThanOrEqualTo(Integer value) {
            addCriterion("approval_status <=", value, "approvalStatus");
            return (Criteria) this;
        }

        public Criteria andApprovalStatusIn(List<Integer> values) {
            addCriterion("approval_status in", values, "approvalStatus");
            return (Criteria) this;
        }

        public Criteria andApprovalStatusNotIn(List<Integer> values) {
            addCriterion("approval_status not in", values, "approvalStatus");
            return (Criteria) this;
        }

        public Criteria andApprovalStatusBetween(Integer value1, Integer value2) {
            addCriterion("approval_status between", value1, value2, "approvalStatus");
            return (Criteria) this;
        }

        public Criteria andApprovalStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("approval_status not between", value1, value2, "approvalStatus");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdIsNull() {
            addCriterion("process_instance_id is null");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdIsNotNull() {
            addCriterion("process_instance_id is not null");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdEqualTo(String value) {
            addCriterion("process_instance_id =", value, "processInstanceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdNotEqualTo(String value) {
            addCriterion("process_instance_id <>", value, "processInstanceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdGreaterThan(String value) {
            addCriterion("process_instance_id >", value, "processInstanceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdGreaterThanOrEqualTo(String value) {
            addCriterion("process_instance_id >=", value, "processInstanceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdLessThan(String value) {
            addCriterion("process_instance_id <", value, "processInstanceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdLessThanOrEqualTo(String value) {
            addCriterion("process_instance_id <=", value, "processInstanceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("process_instance_id like", value, "processInstanceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdNotLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("process_instance_id not like", value, "processInstanceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdLikeList(List<String> values) {
            addCriterion("process_instance_id like", values, "processInstanceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdIn(List<String> values) {
            addCriterion("process_instance_id in", values, "processInstanceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdNotIn(List<String> values) {
            addCriterion("process_instance_id not in", values, "processInstanceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdBetween(String value1, String value2) {
            addCriterion("process_instance_id between", value1, value2, "processInstanceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdNotBetween(String value1, String value2) {
            addCriterion("process_instance_id not between", value1, value2, "processInstanceId");
            return (Criteria) this;
        }

        public Criteria andAdjustTypeIsNull() {
            addCriterion("adjust_type is null");
            return (Criteria) this;
        }

        public Criteria andAdjustTypeIsNotNull() {
            addCriterion("adjust_type is not null");
            return (Criteria) this;
        }

        public Criteria andAdjustTypeEqualTo(Integer value) {
            addCriterion("adjust_type =", value, "adjustType");
            return (Criteria) this;
        }

        public Criteria andAdjustTypeNotEqualTo(Integer value) {
            addCriterion("adjust_type <>", value, "adjustType");
            return (Criteria) this;
        }

        public Criteria andAdjustTypeGreaterThan(Integer value) {
            addCriterion("adjust_type >", value, "adjustType");
            return (Criteria) this;
        }

        public Criteria andAdjustTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("adjust_type >=", value, "adjustType");
            return (Criteria) this;
        }

        public Criteria andAdjustTypeLessThan(Integer value) {
            addCriterion("adjust_type <", value, "adjustType");
            return (Criteria) this;
        }

        public Criteria andAdjustTypeLessThanOrEqualTo(Integer value) {
            addCriterion("adjust_type <=", value, "adjustType");
            return (Criteria) this;
        }

        public Criteria andAdjustTypeIn(List<Integer> values) {
            addCriterion("adjust_type in", values, "adjustType");
            return (Criteria) this;
        }

        public Criteria andAdjustTypeNotIn(List<Integer> values) {
            addCriterion("adjust_type not in", values, "adjustType");
            return (Criteria) this;
        }

        public Criteria andAdjustTypeBetween(Integer value1, Integer value2) {
            addCriterion("adjust_type between", value1, value2, "adjustType");
            return (Criteria) this;
        }

        public Criteria andAdjustTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("adjust_type not between", value1, value2, "adjustType");
            return (Criteria) this;
        }

        public Criteria andAdjustForWhoIsNull() {
            addCriterion("adjust_for_who is null");
            return (Criteria) this;
        }

        public Criteria andAdjustForWhoIsNotNull() {
            addCriterion("adjust_for_who is not null");
            return (Criteria) this;
        }

        public Criteria andAdjustForWhoEqualTo(Integer value) {
            addCriterion("adjust_for_who =", value, "adjustForWho");
            return (Criteria) this;
        }

        public Criteria andAdjustForWhoNotEqualTo(Integer value) {
            addCriterion("adjust_for_who <>", value, "adjustForWho");
            return (Criteria) this;
        }

        public Criteria andAdjustForWhoGreaterThan(Integer value) {
            addCriterion("adjust_for_who >", value, "adjustForWho");
            return (Criteria) this;
        }

        public Criteria andAdjustForWhoGreaterThanOrEqualTo(Integer value) {
            addCriterion("adjust_for_who >=", value, "adjustForWho");
            return (Criteria) this;
        }

        public Criteria andAdjustForWhoLessThan(Integer value) {
            addCriterion("adjust_for_who <", value, "adjustForWho");
            return (Criteria) this;
        }

        public Criteria andAdjustForWhoLessThanOrEqualTo(Integer value) {
            addCriterion("adjust_for_who <=", value, "adjustForWho");
            return (Criteria) this;
        }

        public Criteria andAdjustForWhoIn(List<Integer> values) {
            addCriterion("adjust_for_who in", values, "adjustForWho");
            return (Criteria) this;
        }

        public Criteria andAdjustForWhoNotIn(List<Integer> values) {
            addCriterion("adjust_for_who not in", values, "adjustForWho");
            return (Criteria) this;
        }

        public Criteria andAdjustForWhoBetween(Integer value1, Integer value2) {
            addCriterion("adjust_for_who between", value1, value2, "adjustForWho");
            return (Criteria) this;
        }

        public Criteria andAdjustForWhoNotBetween(Integer value1, Integer value2) {
            addCriterion("adjust_for_who not between", value1, value2, "adjustForWho");
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

        public Criteria andCreateUserNameIsNull() {
            addCriterion("create_user_name is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserNameIsNotNull() {
            addCriterion("create_user_name is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserNameEqualTo(String value) {
            addCriterion("create_user_name =", value, "createUserName");
            return (Criteria) this;
        }

        public Criteria andCreateUserNameNotEqualTo(String value) {
            addCriterion("create_user_name <>", value, "createUserName");
            return (Criteria) this;
        }

        public Criteria andCreateUserNameGreaterThan(String value) {
            addCriterion("create_user_name >", value, "createUserName");
            return (Criteria) this;
        }

        public Criteria andCreateUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("create_user_name >=", value, "createUserName");
            return (Criteria) this;
        }

        public Criteria andCreateUserNameLessThan(String value) {
            addCriterion("create_user_name <", value, "createUserName");
            return (Criteria) this;
        }

        public Criteria andCreateUserNameLessThanOrEqualTo(String value) {
            addCriterion("create_user_name <=", value, "createUserName");
            return (Criteria) this;
        }

        public Criteria andCreateUserNameLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("create_user_name like", value, "createUserName");
            return (Criteria) this;
        }

        public Criteria andCreateUserNameNotLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("create_user_name not like", value, "createUserName");
            return (Criteria) this;
        }

        public Criteria andCreateUserNameLikeList(List<String> values) {
            addCriterion("create_user_name like", values, "createUserName");
            return (Criteria) this;
        }

        public Criteria andCreateUserNameIn(List<String> values) {
            addCriterion("create_user_name in", values, "createUserName");
            return (Criteria) this;
        }

        public Criteria andCreateUserNameNotIn(List<String> values) {
            addCriterion("create_user_name not in", values, "createUserName");
            return (Criteria) this;
        }

        public Criteria andCreateUserNameBetween(String value1, String value2) {
            addCriterion("create_user_name between", value1, value2, "createUserName");
            return (Criteria) this;
        }

        public Criteria andCreateUserNameNotBetween(String value1, String value2) {
            addCriterion("create_user_name not between", value1, value2, "createUserName");
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

        public Criteria andIsDeleteIsNull() {
            addCriterion("is_delete is null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNotNull() {
            addCriterion("is_delete is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteEqualTo(Integer value) {
            addCriterion("is_delete =", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotEqualTo(Integer value) {
            addCriterion("is_delete <>", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThan(Integer value) {
            addCriterion("is_delete >", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_delete >=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThan(Integer value) {
            addCriterion("is_delete <", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThanOrEqualTo(Integer value) {
            addCriterion("is_delete <=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIn(List<Integer> values) {
            addCriterion("is_delete in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotIn(List<Integer> values) {
            addCriterion("is_delete not in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteBetween(Integer value1, Integer value2) {
            addCriterion("is_delete between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotBetween(Integer value1, Integer value2) {
            addCriterion("is_delete not between", value1, value2, "isDelete");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        private AdjustForMasterExample example;

        protected Criteria(AdjustForMasterExample example) {
            super();
            this.example = example;
        }

        public AdjustForMasterExample example() {
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