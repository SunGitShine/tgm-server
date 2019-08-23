package com.juma.tgm.waybill.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaxRateExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int startOffSet = -1;

    protected int size = -1;

    public TaxRateExample() {
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

        public Criteria andTaxRateIdIsNull() {
            addCriterion("tax_rate_id is null");
            return (Criteria) this;
        }

        public Criteria andTaxRateIdIsNotNull() {
            addCriterion("tax_rate_id is not null");
            return (Criteria) this;
        }

        public Criteria andTaxRateIdEqualTo(Integer value) {
            addCriterion("tax_rate_id =", value, "taxRateId");
            return (Criteria) this;
        }

        public Criteria andTaxRateIdNotEqualTo(Integer value) {
            addCriterion("tax_rate_id <>", value, "taxRateId");
            return (Criteria) this;
        }

        public Criteria andTaxRateIdGreaterThan(Integer value) {
            addCriterion("tax_rate_id >", value, "taxRateId");
            return (Criteria) this;
        }

        public Criteria andTaxRateIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("tax_rate_id >=", value, "taxRateId");
            return (Criteria) this;
        }

        public Criteria andTaxRateIdLessThan(Integer value) {
            addCriterion("tax_rate_id <", value, "taxRateId");
            return (Criteria) this;
        }

        public Criteria andTaxRateIdLessThanOrEqualTo(Integer value) {
            addCriterion("tax_rate_id <=", value, "taxRateId");
            return (Criteria) this;
        }

        public Criteria andTaxRateIdIn(List<Integer> values) {
            addCriterion("tax_rate_id in", values, "taxRateId");
            return (Criteria) this;
        }

        public Criteria andTaxRateIdNotIn(List<Integer> values) {
            addCriterion("tax_rate_id not in", values, "taxRateId");
            return (Criteria) this;
        }

        public Criteria andTaxRateIdBetween(Integer value1, Integer value2) {
            addCriterion("tax_rate_id between", value1, value2, "taxRateId");
            return (Criteria) this;
        }

        public Criteria andTaxRateIdNotBetween(Integer value1, Integer value2) {
            addCriterion("tax_rate_id not between", value1, value2, "taxRateId");
            return (Criteria) this;
        }

        public Criteria andTaxRateNameIsNull() {
            addCriterion("tax_rate_name is null");
            return (Criteria) this;
        }

        public Criteria andTaxRateNameIsNotNull() {
            addCriterion("tax_rate_name is not null");
            return (Criteria) this;
        }

        public Criteria andTaxRateNameEqualTo(String value) {
            addCriterion("tax_rate_name =", value, "taxRateName");
            return (Criteria) this;
        }

        public Criteria andTaxRateNameNotEqualTo(String value) {
            addCriterion("tax_rate_name <>", value, "taxRateName");
            return (Criteria) this;
        }

        public Criteria andTaxRateNameGreaterThan(String value) {
            addCriterion("tax_rate_name >", value, "taxRateName");
            return (Criteria) this;
        }

        public Criteria andTaxRateNameGreaterThanOrEqualTo(String value) {
            addCriterion("tax_rate_name >=", value, "taxRateName");
            return (Criteria) this;
        }

        public Criteria andTaxRateNameLessThan(String value) {
            addCriterion("tax_rate_name <", value, "taxRateName");
            return (Criteria) this;
        }

        public Criteria andTaxRateNameLessThanOrEqualTo(String value) {
            addCriterion("tax_rate_name <=", value, "taxRateName");
            return (Criteria) this;
        }

        public Criteria andTaxRateNameLike(String value) {
            addCriterion("tax_rate_name like", value, "taxRateName");
            return (Criteria) this;
        }

        public Criteria andTaxRateNameNotLike(String value) {
            addCriterion("tax_rate_name not like", value, "taxRateName");
            return (Criteria) this;
        }

        public Criteria andTaxRateNameIn(List<String> values) {
            addCriterion("tax_rate_name in", values, "taxRateName");
            return (Criteria) this;
        }

        public Criteria andTaxRateNameNotIn(List<String> values) {
            addCriterion("tax_rate_name not in", values, "taxRateName");
            return (Criteria) this;
        }

        public Criteria andTaxRateNameBetween(String value1, String value2) {
            addCriterion("tax_rate_name between", value1, value2, "taxRateName");
            return (Criteria) this;
        }

        public Criteria andTaxRateNameNotBetween(String value1, String value2) {
            addCriterion("tax_rate_name not between", value1, value2, "taxRateName");
            return (Criteria) this;
        }

        public Criteria andTaxRateValueIsNull() {
            addCriterion("tax_rate_value is null");
            return (Criteria) this;
        }

        public Criteria andTaxRateValueIsNotNull() {
            addCriterion("tax_rate_value is not null");
            return (Criteria) this;
        }

        public Criteria andTaxRateValueEqualTo(BigDecimal value) {
            addCriterion("tax_rate_value =", value, "taxRateValue");
            return (Criteria) this;
        }

        public Criteria andTaxRateValueNotEqualTo(BigDecimal value) {
            addCriterion("tax_rate_value <>", value, "taxRateValue");
            return (Criteria) this;
        }

        public Criteria andTaxRateValueGreaterThan(BigDecimal value) {
            addCriterion("tax_rate_value >", value, "taxRateValue");
            return (Criteria) this;
        }

        public Criteria andTaxRateValueGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("tax_rate_value >=", value, "taxRateValue");
            return (Criteria) this;
        }

        public Criteria andTaxRateValueLessThan(BigDecimal value) {
            addCriterion("tax_rate_value <", value, "taxRateValue");
            return (Criteria) this;
        }

        public Criteria andTaxRateValueLessThanOrEqualTo(BigDecimal value) {
            addCriterion("tax_rate_value <=", value, "taxRateValue");
            return (Criteria) this;
        }

        public Criteria andTaxRateValueIn(List<BigDecimal> values) {
            addCriterion("tax_rate_value in", values, "taxRateValue");
            return (Criteria) this;
        }

        public Criteria andTaxRateValueNotIn(List<BigDecimal> values) {
            addCriterion("tax_rate_value not in", values, "taxRateValue");
            return (Criteria) this;
        }

        public Criteria andTaxRateValueBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("tax_rate_value between", value1, value2, "taxRateValue");
            return (Criteria) this;
        }

        public Criteria andTaxRateValueNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("tax_rate_value not between", value1, value2, "taxRateValue");
            return (Criteria) this;
        }

        public Criteria andTaxRateValueTextIsNull() {
            addCriterion("tax_rate_value_text is null");
            return (Criteria) this;
        }

        public Criteria andTaxRateValueTextIsNotNull() {
            addCriterion("tax_rate_value_text is not null");
            return (Criteria) this;
        }

        public Criteria andTaxRateValueTextEqualTo(String value) {
            addCriterion("tax_rate_value_text =", value, "taxRateValueText");
            return (Criteria) this;
        }

        public Criteria andTaxRateValueTextNotEqualTo(String value) {
            addCriterion("tax_rate_value_text <>", value, "taxRateValueText");
            return (Criteria) this;
        }

        public Criteria andTaxRateValueTextGreaterThan(String value) {
            addCriterion("tax_rate_value_text >", value, "taxRateValueText");
            return (Criteria) this;
        }

        public Criteria andTaxRateValueTextGreaterThanOrEqualTo(String value) {
            addCriterion("tax_rate_value_text >=", value, "taxRateValueText");
            return (Criteria) this;
        }

        public Criteria andTaxRateValueTextLessThan(String value) {
            addCriterion("tax_rate_value_text <", value, "taxRateValueText");
            return (Criteria) this;
        }

        public Criteria andTaxRateValueTextLessThanOrEqualTo(String value) {
            addCriterion("tax_rate_value_text <=", value, "taxRateValueText");
            return (Criteria) this;
        }

        public Criteria andTaxRateValueTextLike(String value) {
            addCriterion("tax_rate_value_text like", value, "taxRateValueText");
            return (Criteria) this;
        }

        public Criteria andTaxRateValueTextNotLike(String value) {
            addCriterion("tax_rate_value_text not like", value, "taxRateValueText");
            return (Criteria) this;
        }

        public Criteria andTaxRateValueTextIn(List<String> values) {
            addCriterion("tax_rate_value_text in", values, "taxRateValueText");
            return (Criteria) this;
        }

        public Criteria andTaxRateValueTextNotIn(List<String> values) {
            addCriterion("tax_rate_value_text not in", values, "taxRateValueText");
            return (Criteria) this;
        }

        public Criteria andTaxRateValueTextBetween(String value1, String value2) {
            addCriterion("tax_rate_value_text between", value1, value2, "taxRateValueText");
            return (Criteria) this;
        }

        public Criteria andTaxRateValueTextNotBetween(String value1, String value2) {
            addCriterion("tax_rate_value_text not between", value1, value2, "taxRateValueText");
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