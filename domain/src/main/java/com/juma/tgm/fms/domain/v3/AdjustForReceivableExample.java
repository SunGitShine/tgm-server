package com.juma.tgm.fms.domain.v3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdjustForReceivableExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int startOffSet = -1;

    protected int size = -1;

    public AdjustForReceivableExample() {
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

        public Criteria andReceivableWithTaxIsNull() {
            addCriterion("receivable_with_tax is null");
            return (Criteria) this;
        }

        public Criteria andReceivableWithTaxIsNotNull() {
            addCriterion("receivable_with_tax is not null");
            return (Criteria) this;
        }

        public Criteria andReceivableWithTaxEqualTo(BigDecimal value) {
            addCriterion("receivable_with_tax =", value, "receivableWithTax");
            return (Criteria) this;
        }

        public Criteria andReceivableWithTaxNotEqualTo(BigDecimal value) {
            addCriterion("receivable_with_tax <>", value, "receivableWithTax");
            return (Criteria) this;
        }

        public Criteria andReceivableWithTaxGreaterThan(BigDecimal value) {
            addCriterion("receivable_with_tax >", value, "receivableWithTax");
            return (Criteria) this;
        }

        public Criteria andReceivableWithTaxGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("receivable_with_tax >=", value, "receivableWithTax");
            return (Criteria) this;
        }

        public Criteria andReceivableWithTaxLessThan(BigDecimal value) {
            addCriterion("receivable_with_tax <", value, "receivableWithTax");
            return (Criteria) this;
        }

        public Criteria andReceivableWithTaxLessThanOrEqualTo(BigDecimal value) {
            addCriterion("receivable_with_tax <=", value, "receivableWithTax");
            return (Criteria) this;
        }

        public Criteria andReceivableWithTaxIn(List<BigDecimal> values) {
            addCriterion("receivable_with_tax in", values, "receivableWithTax");
            return (Criteria) this;
        }

        public Criteria andReceivableWithTaxNotIn(List<BigDecimal> values) {
            addCriterion("receivable_with_tax not in", values, "receivableWithTax");
            return (Criteria) this;
        }

        public Criteria andReceivableWithTaxBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("receivable_with_tax between", value1, value2, "receivableWithTax");
            return (Criteria) this;
        }

        public Criteria andReceivableWithTaxNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("receivable_with_tax not between", value1, value2, "receivableWithTax");
            return (Criteria) this;
        }

        public Criteria andReceivableWithTaxAdjustIsNull() {
            addCriterion("receivable_with_tax_adjust is null");
            return (Criteria) this;
        }

        public Criteria andReceivableWithTaxAdjustIsNotNull() {
            addCriterion("receivable_with_tax_adjust is not null");
            return (Criteria) this;
        }

        public Criteria andReceivableWithTaxAdjustEqualTo(BigDecimal value) {
            addCriterion("receivable_with_tax_adjust =", value, "receivableWithTaxAdjust");
            return (Criteria) this;
        }

        public Criteria andReceivableWithTaxAdjustNotEqualTo(BigDecimal value) {
            addCriterion("receivable_with_tax_adjust <>", value, "receivableWithTaxAdjust");
            return (Criteria) this;
        }

        public Criteria andReceivableWithTaxAdjustGreaterThan(BigDecimal value) {
            addCriterion("receivable_with_tax_adjust >", value, "receivableWithTaxAdjust");
            return (Criteria) this;
        }

        public Criteria andReceivableWithTaxAdjustGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("receivable_with_tax_adjust >=", value, "receivableWithTaxAdjust");
            return (Criteria) this;
        }

        public Criteria andReceivableWithTaxAdjustLessThan(BigDecimal value) {
            addCriterion("receivable_with_tax_adjust <", value, "receivableWithTaxAdjust");
            return (Criteria) this;
        }

        public Criteria andReceivableWithTaxAdjustLessThanOrEqualTo(BigDecimal value) {
            addCriterion("receivable_with_tax_adjust <=", value, "receivableWithTaxAdjust");
            return (Criteria) this;
        }

        public Criteria andReceivableWithTaxAdjustIn(List<BigDecimal> values) {
            addCriterion("receivable_with_tax_adjust in", values, "receivableWithTaxAdjust");
            return (Criteria) this;
        }

        public Criteria andReceivableWithTaxAdjustNotIn(List<BigDecimal> values) {
            addCriterion("receivable_with_tax_adjust not in", values, "receivableWithTaxAdjust");
            return (Criteria) this;
        }

        public Criteria andReceivableWithTaxAdjustBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("receivable_with_tax_adjust between", value1, value2, "receivableWithTaxAdjust");
            return (Criteria) this;
        }

        public Criteria andReceivableWithTaxAdjustNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("receivable_with_tax_adjust not between", value1, value2, "receivableWithTaxAdjust");
            return (Criteria) this;
        }

        public Criteria andTaxRateIsNull() {
            addCriterion("tax_rate is null");
            return (Criteria) this;
        }

        public Criteria andTaxRateIsNotNull() {
            addCriterion("tax_rate is not null");
            return (Criteria) this;
        }

        public Criteria andTaxRateEqualTo(BigDecimal value) {
            addCriterion("tax_rate =", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateNotEqualTo(BigDecimal value) {
            addCriterion("tax_rate <>", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateGreaterThan(BigDecimal value) {
            addCriterion("tax_rate >", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("tax_rate >=", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateLessThan(BigDecimal value) {
            addCriterion("tax_rate <", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("tax_rate <=", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateIn(List<BigDecimal> values) {
            addCriterion("tax_rate in", values, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateNotIn(List<BigDecimal> values) {
            addCriterion("tax_rate not in", values, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("tax_rate between", value1, value2, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("tax_rate not between", value1, value2, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateAdjustIsNull() {
            addCriterion("tax_rate_adjust is null");
            return (Criteria) this;
        }

        public Criteria andTaxRateAdjustIsNotNull() {
            addCriterion("tax_rate_adjust is not null");
            return (Criteria) this;
        }

        public Criteria andTaxRateAdjustEqualTo(BigDecimal value) {
            addCriterion("tax_rate_adjust =", value, "taxRateAdjust");
            return (Criteria) this;
        }

        public Criteria andTaxRateAdjustNotEqualTo(BigDecimal value) {
            addCriterion("tax_rate_adjust <>", value, "taxRateAdjust");
            return (Criteria) this;
        }

        public Criteria andTaxRateAdjustGreaterThan(BigDecimal value) {
            addCriterion("tax_rate_adjust >", value, "taxRateAdjust");
            return (Criteria) this;
        }

        public Criteria andTaxRateAdjustGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("tax_rate_adjust >=", value, "taxRateAdjust");
            return (Criteria) this;
        }

        public Criteria andTaxRateAdjustLessThan(BigDecimal value) {
            addCriterion("tax_rate_adjust <", value, "taxRateAdjust");
            return (Criteria) this;
        }

        public Criteria andTaxRateAdjustLessThanOrEqualTo(BigDecimal value) {
            addCriterion("tax_rate_adjust <=", value, "taxRateAdjust");
            return (Criteria) this;
        }

        public Criteria andTaxRateAdjustIn(List<BigDecimal> values) {
            addCriterion("tax_rate_adjust in", values, "taxRateAdjust");
            return (Criteria) this;
        }

        public Criteria andTaxRateAdjustNotIn(List<BigDecimal> values) {
            addCriterion("tax_rate_adjust not in", values, "taxRateAdjust");
            return (Criteria) this;
        }

        public Criteria andTaxRateAdjustBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("tax_rate_adjust between", value1, value2, "taxRateAdjust");
            return (Criteria) this;
        }

        public Criteria andTaxRateAdjustNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("tax_rate_adjust not between", value1, value2, "taxRateAdjust");
            return (Criteria) this;
        }

        public Criteria andAdjustRemarkIsNull() {
            addCriterion("adjust_remark is null");
            return (Criteria) this;
        }

        public Criteria andAdjustRemarkIsNotNull() {
            addCriterion("adjust_remark is not null");
            return (Criteria) this;
        }

        public Criteria andAdjustRemarkEqualTo(String value) {
            addCriterion("adjust_remark =", value, "adjustRemark");
            return (Criteria) this;
        }

        public Criteria andAdjustRemarkNotEqualTo(String value) {
            addCriterion("adjust_remark <>", value, "adjustRemark");
            return (Criteria) this;
        }

        public Criteria andAdjustRemarkGreaterThan(String value) {
            addCriterion("adjust_remark >", value, "adjustRemark");
            return (Criteria) this;
        }

        public Criteria andAdjustRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("adjust_remark >=", value, "adjustRemark");
            return (Criteria) this;
        }

        public Criteria andAdjustRemarkLessThan(String value) {
            addCriterion("adjust_remark <", value, "adjustRemark");
            return (Criteria) this;
        }

        public Criteria andAdjustRemarkLessThanOrEqualTo(String value) {
            addCriterion("adjust_remark <=", value, "adjustRemark");
            return (Criteria) this;
        }

        public Criteria andAdjustRemarkLike(String value) {
            addCriterion("adjust_remark like", value, "adjustRemark");
            return (Criteria) this;
        }

        public Criteria andAdjustRemarkNotLike(String value) {
            addCriterion("adjust_remark not like", value, "adjustRemark");
            return (Criteria) this;
        }

        public Criteria andAdjustRemarkIn(List<String> values) {
            addCriterion("adjust_remark in", values, "adjustRemark");
            return (Criteria) this;
        }

        public Criteria andAdjustRemarkNotIn(List<String> values) {
            addCriterion("adjust_remark not in", values, "adjustRemark");
            return (Criteria) this;
        }

        public Criteria andAdjustRemarkBetween(String value1, String value2) {
            addCriterion("adjust_remark between", value1, value2, "adjustRemark");
            return (Criteria) this;
        }

        public Criteria andAdjustRemarkNotBetween(String value1, String value2) {
            addCriterion("adjust_remark not between", value1, value2, "adjustRemark");
            return (Criteria) this;
        }

        public Criteria andAdjustUserIdIsNull() {
            addCriterion("adjust_user_id is null");
            return (Criteria) this;
        }

        public Criteria andAdjustUserIdIsNotNull() {
            addCriterion("adjust_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andAdjustUserIdEqualTo(Integer value) {
            addCriterion("adjust_user_id =", value, "adjustUserId");
            return (Criteria) this;
        }

        public Criteria andAdjustUserIdNotEqualTo(Integer value) {
            addCriterion("adjust_user_id <>", value, "adjustUserId");
            return (Criteria) this;
        }

        public Criteria andAdjustUserIdGreaterThan(Integer value) {
            addCriterion("adjust_user_id >", value, "adjustUserId");
            return (Criteria) this;
        }

        public Criteria andAdjustUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("adjust_user_id >=", value, "adjustUserId");
            return (Criteria) this;
        }

        public Criteria andAdjustUserIdLessThan(Integer value) {
            addCriterion("adjust_user_id <", value, "adjustUserId");
            return (Criteria) this;
        }

        public Criteria andAdjustUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("adjust_user_id <=", value, "adjustUserId");
            return (Criteria) this;
        }

        public Criteria andAdjustUserIdIn(List<Integer> values) {
            addCriterion("adjust_user_id in", values, "adjustUserId");
            return (Criteria) this;
        }

        public Criteria andAdjustUserIdNotIn(List<Integer> values) {
            addCriterion("adjust_user_id not in", values, "adjustUserId");
            return (Criteria) this;
        }

        public Criteria andAdjustUserIdBetween(Integer value1, Integer value2) {
            addCriterion("adjust_user_id between", value1, value2, "adjustUserId");
            return (Criteria) this;
        }

        public Criteria andAdjustUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("adjust_user_id not between", value1, value2, "adjustUserId");
            return (Criteria) this;
        }

        public Criteria andAdjustTimeIsNull() {
            addCriterion("adjust_time is null");
            return (Criteria) this;
        }

        public Criteria andAdjustTimeIsNotNull() {
            addCriterion("adjust_time is not null");
            return (Criteria) this;
        }

        public Criteria andAdjustTimeEqualTo(Date value) {
            addCriterion("adjust_time =", value, "adjustTime");
            return (Criteria) this;
        }

        public Criteria andAdjustTimeNotEqualTo(Date value) {
            addCriterion("adjust_time <>", value, "adjustTime");
            return (Criteria) this;
        }

        public Criteria andAdjustTimeGreaterThan(Date value) {
            addCriterion("adjust_time >", value, "adjustTime");
            return (Criteria) this;
        }

        public Criteria andAdjustTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("adjust_time >=", value, "adjustTime");
            return (Criteria) this;
        }

        public Criteria andAdjustTimeLessThan(Date value) {
            addCriterion("adjust_time <", value, "adjustTime");
            return (Criteria) this;
        }

        public Criteria andAdjustTimeLessThanOrEqualTo(Date value) {
            addCriterion("adjust_time <=", value, "adjustTime");
            return (Criteria) this;
        }

        public Criteria andAdjustTimeIn(List<Date> values) {
            addCriterion("adjust_time in", values, "adjustTime");
            return (Criteria) this;
        }

        public Criteria andAdjustTimeNotIn(List<Date> values) {
            addCriterion("adjust_time not in", values, "adjustTime");
            return (Criteria) this;
        }

        public Criteria andAdjustTimeBetween(Date value1, Date value2) {
            addCriterion("adjust_time between", value1, value2, "adjustTime");
            return (Criteria) this;
        }

        public Criteria andAdjustTimeNotBetween(Date value1, Date value2) {
            addCriterion("adjust_time not between", value1, value2, "adjustTime");
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