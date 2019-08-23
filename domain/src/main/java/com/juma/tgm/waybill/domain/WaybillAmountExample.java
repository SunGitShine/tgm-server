package com.juma.tgm.waybill.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WaybillAmountExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int startOffSet = -1;

    protected int size = -1;

    public WaybillAmountExample() {
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

    public WaybillAmountExample orderBy(String ... orderByClauses) {
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

        public Criteria andWaybillAmountIdIsNull() {
            addCriterion("waybill_amount_id is null");
            return (Criteria) this;
        }

        public Criteria andWaybillAmountIdIsNotNull() {
            addCriterion("waybill_amount_id is not null");
            return (Criteria) this;
        }

        public Criteria andWaybillAmountIdEqualTo(Integer value) {
            addCriterion("waybill_amount_id =", value, "waybillAmountId");
            return (Criteria) this;
        }

        public Criteria andWaybillAmountIdNotEqualTo(Integer value) {
            addCriterion("waybill_amount_id <>", value, "waybillAmountId");
            return (Criteria) this;
        }

        public Criteria andWaybillAmountIdGreaterThan(Integer value) {
            addCriterion("waybill_amount_id >", value, "waybillAmountId");
            return (Criteria) this;
        }

        public Criteria andWaybillAmountIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("waybill_amount_id >=", value, "waybillAmountId");
            return (Criteria) this;
        }

        public Criteria andWaybillAmountIdLessThan(Integer value) {
            addCriterion("waybill_amount_id <", value, "waybillAmountId");
            return (Criteria) this;
        }

        public Criteria andWaybillAmountIdLessThanOrEqualTo(Integer value) {
            addCriterion("waybill_amount_id <=", value, "waybillAmountId");
            return (Criteria) this;
        }

        public Criteria andWaybillAmountIdIn(List<Integer> values) {
            addCriterion("waybill_amount_id in", values, "waybillAmountId");
            return (Criteria) this;
        }

        public Criteria andWaybillAmountIdNotIn(List<Integer> values) {
            addCriterion("waybill_amount_id not in", values, "waybillAmountId");
            return (Criteria) this;
        }

        public Criteria andWaybillAmountIdBetween(Integer value1, Integer value2) {
            addCriterion("waybill_amount_id between", value1, value2, "waybillAmountId");
            return (Criteria) this;
        }

        public Criteria andWaybillAmountIdNotBetween(Integer value1, Integer value2) {
            addCriterion("waybill_amount_id not between", value1, value2, "waybillAmountId");
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

        public Criteria andCustomerFreightWithTaxIsNull() {
            addCriterion("customer_freight_with_tax is null");
            return (Criteria) this;
        }

        public Criteria andCustomerFreightWithTaxIsNotNull() {
            addCriterion("customer_freight_with_tax is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerFreightWithTaxEqualTo(BigDecimal value) {
            addCriterion("customer_freight_with_tax =", value, "customerFreightWithTax");
            return (Criteria) this;
        }

        public Criteria andCustomerFreightWithTaxNotEqualTo(BigDecimal value) {
            addCriterion("customer_freight_with_tax <>", value, "customerFreightWithTax");
            return (Criteria) this;
        }

        public Criteria andCustomerFreightWithTaxGreaterThan(BigDecimal value) {
            addCriterion("customer_freight_with_tax >", value, "customerFreightWithTax");
            return (Criteria) this;
        }

        public Criteria andCustomerFreightWithTaxGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("customer_freight_with_tax >=", value, "customerFreightWithTax");
            return (Criteria) this;
        }

        public Criteria andCustomerFreightWithTaxLessThan(BigDecimal value) {
            addCriterion("customer_freight_with_tax <", value, "customerFreightWithTax");
            return (Criteria) this;
        }

        public Criteria andCustomerFreightWithTaxLessThanOrEqualTo(BigDecimal value) {
            addCriterion("customer_freight_with_tax <=", value, "customerFreightWithTax");
            return (Criteria) this;
        }

        public Criteria andCustomerFreightWithTaxIn(List<BigDecimal> values) {
            addCriterion("customer_freight_with_tax in", values, "customerFreightWithTax");
            return (Criteria) this;
        }

        public Criteria andCustomerFreightWithTaxNotIn(List<BigDecimal> values) {
            addCriterion("customer_freight_with_tax not in", values, "customerFreightWithTax");
            return (Criteria) this;
        }

        public Criteria andCustomerFreightWithTaxBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("customer_freight_with_tax between", value1, value2, "customerFreightWithTax");
            return (Criteria) this;
        }

        public Criteria andCustomerFreightWithTaxNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("customer_freight_with_tax not between", value1, value2, "customerFreightWithTax");
            return (Criteria) this;
        }

        public Criteria andVendorFreightWithTaxIsNull() {
            addCriterion("vendor_freight_with_tax is null");
            return (Criteria) this;
        }

        public Criteria andVendorFreightWithTaxIsNotNull() {
            addCriterion("vendor_freight_with_tax is not null");
            return (Criteria) this;
        }

        public Criteria andVendorFreightWithTaxEqualTo(BigDecimal value) {
            addCriterion("vendor_freight_with_tax =", value, "vendorFreightWithTax");
            return (Criteria) this;
        }

        public Criteria andVendorFreightWithTaxNotEqualTo(BigDecimal value) {
            addCriterion("vendor_freight_with_tax <>", value, "vendorFreightWithTax");
            return (Criteria) this;
        }

        public Criteria andVendorFreightWithTaxGreaterThan(BigDecimal value) {
            addCriterion("vendor_freight_with_tax >", value, "vendorFreightWithTax");
            return (Criteria) this;
        }

        public Criteria andVendorFreightWithTaxGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("vendor_freight_with_tax >=", value, "vendorFreightWithTax");
            return (Criteria) this;
        }

        public Criteria andVendorFreightWithTaxLessThan(BigDecimal value) {
            addCriterion("vendor_freight_with_tax <", value, "vendorFreightWithTax");
            return (Criteria) this;
        }

        public Criteria andVendorFreightWithTaxLessThanOrEqualTo(BigDecimal value) {
            addCriterion("vendor_freight_with_tax <=", value, "vendorFreightWithTax");
            return (Criteria) this;
        }

        public Criteria andVendorFreightWithTaxIn(List<BigDecimal> values) {
            addCriterion("vendor_freight_with_tax in", values, "vendorFreightWithTax");
            return (Criteria) this;
        }

        public Criteria andVendorFreightWithTaxNotIn(List<BigDecimal> values) {
            addCriterion("vendor_freight_with_tax not in", values, "vendorFreightWithTax");
            return (Criteria) this;
        }

        public Criteria andVendorFreightWithTaxBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("vendor_freight_with_tax between", value1, value2, "vendorFreightWithTax");
            return (Criteria) this;
        }

        public Criteria andVendorFreightWithTaxNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("vendor_freight_with_tax not between", value1, value2, "vendorFreightWithTax");
            return (Criteria) this;
        }

        public Criteria andLastCustomerFreightWithTaxIsNull() {
            addCriterion("last_customer_freight_with_tax is null");
            return (Criteria) this;
        }

        public Criteria andLastCustomerFreightWithTaxIsNotNull() {
            addCriterion("last_customer_freight_with_tax is not null");
            return (Criteria) this;
        }

        public Criteria andLastCustomerFreightWithTaxEqualTo(BigDecimal value) {
            addCriterion("last_customer_freight_with_tax =", value, "lastCustomerFreightWithTax");
            return (Criteria) this;
        }

        public Criteria andLastCustomerFreightWithTaxNotEqualTo(BigDecimal value) {
            addCriterion("last_customer_freight_with_tax <>", value, "lastCustomerFreightWithTax");
            return (Criteria) this;
        }

        public Criteria andLastCustomerFreightWithTaxGreaterThan(BigDecimal value) {
            addCriterion("last_customer_freight_with_tax >", value, "lastCustomerFreightWithTax");
            return (Criteria) this;
        }

        public Criteria andLastCustomerFreightWithTaxGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("last_customer_freight_with_tax >=", value, "lastCustomerFreightWithTax");
            return (Criteria) this;
        }

        public Criteria andLastCustomerFreightWithTaxLessThan(BigDecimal value) {
            addCriterion("last_customer_freight_with_tax <", value, "lastCustomerFreightWithTax");
            return (Criteria) this;
        }

        public Criteria andLastCustomerFreightWithTaxLessThanOrEqualTo(BigDecimal value) {
            addCriterion("last_customer_freight_with_tax <=", value, "lastCustomerFreightWithTax");
            return (Criteria) this;
        }

        public Criteria andLastCustomerFreightWithTaxIn(List<BigDecimal> values) {
            addCriterion("last_customer_freight_with_tax in", values, "lastCustomerFreightWithTax");
            return (Criteria) this;
        }

        public Criteria andLastCustomerFreightWithTaxNotIn(List<BigDecimal> values) {
            addCriterion("last_customer_freight_with_tax not in", values, "lastCustomerFreightWithTax");
            return (Criteria) this;
        }

        public Criteria andLastCustomerFreightWithTaxBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("last_customer_freight_with_tax between", value1, value2, "lastCustomerFreightWithTax");
            return (Criteria) this;
        }

        public Criteria andLastCustomerFreightWithTaxNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("last_customer_freight_with_tax not between", value1, value2, "lastCustomerFreightWithTax");
            return (Criteria) this;
        }

        public Criteria andLastVendorFreightWithTaxIsNull() {
            addCriterion("last_vendor_freight_with_tax is null");
            return (Criteria) this;
        }

        public Criteria andLastVendorFreightWithTaxIsNotNull() {
            addCriterion("last_vendor_freight_with_tax is not null");
            return (Criteria) this;
        }

        public Criteria andLastVendorFreightWithTaxEqualTo(BigDecimal value) {
            addCriterion("last_vendor_freight_with_tax =", value, "lastVendorFreightWithTax");
            return (Criteria) this;
        }

        public Criteria andLastVendorFreightWithTaxNotEqualTo(BigDecimal value) {
            addCriterion("last_vendor_freight_with_tax <>", value, "lastVendorFreightWithTax");
            return (Criteria) this;
        }

        public Criteria andLastVendorFreightWithTaxGreaterThan(BigDecimal value) {
            addCriterion("last_vendor_freight_with_tax >", value, "lastVendorFreightWithTax");
            return (Criteria) this;
        }

        public Criteria andLastVendorFreightWithTaxGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("last_vendor_freight_with_tax >=", value, "lastVendorFreightWithTax");
            return (Criteria) this;
        }

        public Criteria andLastVendorFreightWithTaxLessThan(BigDecimal value) {
            addCriterion("last_vendor_freight_with_tax <", value, "lastVendorFreightWithTax");
            return (Criteria) this;
        }

        public Criteria andLastVendorFreightWithTaxLessThanOrEqualTo(BigDecimal value) {
            addCriterion("last_vendor_freight_with_tax <=", value, "lastVendorFreightWithTax");
            return (Criteria) this;
        }

        public Criteria andLastVendorFreightWithTaxIn(List<BigDecimal> values) {
            addCriterion("last_vendor_freight_with_tax in", values, "lastVendorFreightWithTax");
            return (Criteria) this;
        }

        public Criteria andLastVendorFreightWithTaxNotIn(List<BigDecimal> values) {
            addCriterion("last_vendor_freight_with_tax not in", values, "lastVendorFreightWithTax");
            return (Criteria) this;
        }

        public Criteria andLastVendorFreightWithTaxBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("last_vendor_freight_with_tax between", value1, value2, "lastVendorFreightWithTax");
            return (Criteria) this;
        }

        public Criteria andLastVendorFreightWithTaxNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("last_vendor_freight_with_tax not between", value1, value2, "lastVendorFreightWithTax");
            return (Criteria) this;
        }

        public Criteria andAmountStatusIsNull() {
            addCriterion("amount_status is null");
            return (Criteria) this;
        }

        public Criteria andAmountStatusIsNotNull() {
            addCriterion("amount_status is not null");
            return (Criteria) this;
        }

        public Criteria andAmountStatusEqualTo(Integer value) {
            addCriterion("amount_status =", value, "amountStatus");
            return (Criteria) this;
        }

        public Criteria andAmountStatusNotEqualTo(Integer value) {
            addCriterion("amount_status <>", value, "amountStatus");
            return (Criteria) this;
        }

        public Criteria andAmountStatusGreaterThan(Integer value) {
            addCriterion("amount_status >", value, "amountStatus");
            return (Criteria) this;
        }

        public Criteria andAmountStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("amount_status >=", value, "amountStatus");
            return (Criteria) this;
        }

        public Criteria andAmountStatusLessThan(Integer value) {
            addCriterion("amount_status <", value, "amountStatus");
            return (Criteria) this;
        }

        public Criteria andAmountStatusLessThanOrEqualTo(Integer value) {
            addCriterion("amount_status <=", value, "amountStatus");
            return (Criteria) this;
        }

        public Criteria andAmountStatusIn(List<Integer> values) {
            addCriterion("amount_status in", values, "amountStatus");
            return (Criteria) this;
        }

        public Criteria andAmountStatusNotIn(List<Integer> values) {
            addCriterion("amount_status not in", values, "amountStatus");
            return (Criteria) this;
        }

        public Criteria andAmountStatusBetween(Integer value1, Integer value2) {
            addCriterion("amount_status between", value1, value2, "amountStatus");
            return (Criteria) this;
        }

        public Criteria andAmountStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("amount_status not between", value1, value2, "amountStatus");
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
        private WaybillAmountExample example;

        protected Criteria(WaybillAmountExample example) {
            super();
            this.example = example;
        }

        public WaybillAmountExample example() {
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