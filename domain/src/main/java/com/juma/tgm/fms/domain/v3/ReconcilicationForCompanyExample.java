package com.juma.tgm.fms.domain.v3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReconcilicationForCompanyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int startOffSet = -1;

    protected int size = -1;

    public ReconcilicationForCompanyExample() {
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

    public ReconcilicationForCompanyExample orderBy(String ... orderByClauses) {
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

        public Criteria andReconcilicationCompanyIdIsNull() {
            addCriterion("reconcilication_company_id is null");
            return (Criteria) this;
        }

        public Criteria andReconcilicationCompanyIdIsNotNull() {
            addCriterion("reconcilication_company_id is not null");
            return (Criteria) this;
        }

        public Criteria andReconcilicationCompanyIdEqualTo(Integer value) {
            addCriterion("reconcilication_company_id =", value, "reconcilicationCompanyId");
            return (Criteria) this;
        }

        public Criteria andReconcilicationCompanyIdNotEqualTo(Integer value) {
            addCriterion("reconcilication_company_id <>", value, "reconcilicationCompanyId");
            return (Criteria) this;
        }

        public Criteria andReconcilicationCompanyIdGreaterThan(Integer value) {
            addCriterion("reconcilication_company_id >", value, "reconcilicationCompanyId");
            return (Criteria) this;
        }

        public Criteria andReconcilicationCompanyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("reconcilication_company_id >=", value, "reconcilicationCompanyId");
            return (Criteria) this;
        }

        public Criteria andReconcilicationCompanyIdLessThan(Integer value) {
            addCriterion("reconcilication_company_id <", value, "reconcilicationCompanyId");
            return (Criteria) this;
        }

        public Criteria andReconcilicationCompanyIdLessThanOrEqualTo(Integer value) {
            addCriterion("reconcilication_company_id <=", value, "reconcilicationCompanyId");
            return (Criteria) this;
        }

        public Criteria andReconcilicationCompanyIdIn(List<Integer> values) {
            addCriterion("reconcilication_company_id in", values, "reconcilicationCompanyId");
            return (Criteria) this;
        }

        public Criteria andReconcilicationCompanyIdNotIn(List<Integer> values) {
            addCriterion("reconcilication_company_id not in", values, "reconcilicationCompanyId");
            return (Criteria) this;
        }

        public Criteria andReconcilicationCompanyIdBetween(Integer value1, Integer value2) {
            addCriterion("reconcilication_company_id between", value1, value2, "reconcilicationCompanyId");
            return (Criteria) this;
        }

        public Criteria andReconcilicationCompanyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("reconcilication_company_id not between", value1, value2, "reconcilicationCompanyId");
            return (Criteria) this;
        }

        public Criteria andReconcilicationCompanyNoIsNull() {
            addCriterion("reconcilication_company_no is null");
            return (Criteria) this;
        }

        public Criteria andReconcilicationCompanyNoIsNotNull() {
            addCriterion("reconcilication_company_no is not null");
            return (Criteria) this;
        }

        public Criteria andReconcilicationCompanyNoEqualTo(String value) {
            addCriterion("reconcilication_company_no =", value, "reconcilicationCompanyNo");
            return (Criteria) this;
        }

        public Criteria andReconcilicationCompanyNoNotEqualTo(String value) {
            addCriterion("reconcilication_company_no <>", value, "reconcilicationCompanyNo");
            return (Criteria) this;
        }

        public Criteria andReconcilicationCompanyNoGreaterThan(String value) {
            addCriterion("reconcilication_company_no >", value, "reconcilicationCompanyNo");
            return (Criteria) this;
        }

        public Criteria andReconcilicationCompanyNoGreaterThanOrEqualTo(String value) {
            addCriterion("reconcilication_company_no >=", value, "reconcilicationCompanyNo");
            return (Criteria) this;
        }

        public Criteria andReconcilicationCompanyNoLessThan(String value) {
            addCriterion("reconcilication_company_no <", value, "reconcilicationCompanyNo");
            return (Criteria) this;
        }

        public Criteria andReconcilicationCompanyNoLessThanOrEqualTo(String value) {
            addCriterion("reconcilication_company_no <=", value, "reconcilicationCompanyNo");
            return (Criteria) this;
        }

        public Criteria andReconcilicationCompanyNoLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("reconcilication_company_no like", value, "reconcilicationCompanyNo");
            return (Criteria) this;
        }

        public Criteria andReconcilicationCompanyNoNotLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("reconcilication_company_no not like", value, "reconcilicationCompanyNo");
            return (Criteria) this;
        }

        public Criteria andReconcilicationCompanyNoLikeList(List<String> values) {
            addCriterion("reconcilication_company_no like", values, "reconcilicationCompanyNo");
            return (Criteria) this;
        }

        public Criteria andReconcilicationCompanyNoIn(List<String> values) {
            addCriterion("reconcilication_company_no in", values, "reconcilicationCompanyNo");
            return (Criteria) this;
        }

        public Criteria andReconcilicationCompanyNoNotIn(List<String> values) {
            addCriterion("reconcilication_company_no not in", values, "reconcilicationCompanyNo");
            return (Criteria) this;
        }

        public Criteria andReconcilicationCompanyNoBetween(String value1, String value2) {
            addCriterion("reconcilication_company_no between", value1, value2, "reconcilicationCompanyNo");
            return (Criteria) this;
        }

        public Criteria andReconcilicationCompanyNoNotBetween(String value1, String value2) {
            addCriterion("reconcilication_company_no not between", value1, value2, "reconcilicationCompanyNo");
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

        public Criteria andReconcilicationReceivableIdIsNull() {
            addCriterion("reconcilication_receivable_id is null");
            return (Criteria) this;
        }

        public Criteria andReconcilicationReceivableIdIsNotNull() {
            addCriterion("reconcilication_receivable_id is not null");
            return (Criteria) this;
        }

        public Criteria andReconcilicationReceivableIdEqualTo(Integer value) {
            addCriterion("reconcilication_receivable_id =", value, "reconcilicationReceivableId");
            return (Criteria) this;
        }

        public Criteria andReconcilicationReceivableIdNotEqualTo(Integer value) {
            addCriterion("reconcilication_receivable_id <>", value, "reconcilicationReceivableId");
            return (Criteria) this;
        }

        public Criteria andReconcilicationReceivableIdGreaterThan(Integer value) {
            addCriterion("reconcilication_receivable_id >", value, "reconcilicationReceivableId");
            return (Criteria) this;
        }

        public Criteria andReconcilicationReceivableIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("reconcilication_receivable_id >=", value, "reconcilicationReceivableId");
            return (Criteria) this;
        }

        public Criteria andReconcilicationReceivableIdLessThan(Integer value) {
            addCriterion("reconcilication_receivable_id <", value, "reconcilicationReceivableId");
            return (Criteria) this;
        }

        public Criteria andReconcilicationReceivableIdLessThanOrEqualTo(Integer value) {
            addCriterion("reconcilication_receivable_id <=", value, "reconcilicationReceivableId");
            return (Criteria) this;
        }

        public Criteria andReconcilicationReceivableIdIn(List<Integer> values) {
            addCriterion("reconcilication_receivable_id in", values, "reconcilicationReceivableId");
            return (Criteria) this;
        }

        public Criteria andReconcilicationReceivableIdNotIn(List<Integer> values) {
            addCriterion("reconcilication_receivable_id not in", values, "reconcilicationReceivableId");
            return (Criteria) this;
        }

        public Criteria andReconcilicationReceivableIdBetween(Integer value1, Integer value2) {
            addCriterion("reconcilication_receivable_id between", value1, value2, "reconcilicationReceivableId");
            return (Criteria) this;
        }

        public Criteria andReconcilicationReceivableIdNotBetween(Integer value1, Integer value2) {
            addCriterion("reconcilication_receivable_id not between", value1, value2, "reconcilicationReceivableId");
            return (Criteria) this;
        }

        public Criteria andContractToCompanyIsNull() {
            addCriterion("contract_to_company is null");
            return (Criteria) this;
        }

        public Criteria andContractToCompanyIsNotNull() {
            addCriterion("contract_to_company is not null");
            return (Criteria) this;
        }

        public Criteria andContractToCompanyEqualTo(Integer value) {
            addCriterion("contract_to_company =", value, "contractToCompany");
            return (Criteria) this;
        }

        public Criteria andContractToCompanyNotEqualTo(Integer value) {
            addCriterion("contract_to_company <>", value, "contractToCompany");
            return (Criteria) this;
        }

        public Criteria andContractToCompanyGreaterThan(Integer value) {
            addCriterion("contract_to_company >", value, "contractToCompany");
            return (Criteria) this;
        }

        public Criteria andContractToCompanyGreaterThanOrEqualTo(Integer value) {
            addCriterion("contract_to_company >=", value, "contractToCompany");
            return (Criteria) this;
        }

        public Criteria andContractToCompanyLessThan(Integer value) {
            addCriterion("contract_to_company <", value, "contractToCompany");
            return (Criteria) this;
        }

        public Criteria andContractToCompanyLessThanOrEqualTo(Integer value) {
            addCriterion("contract_to_company <=", value, "contractToCompany");
            return (Criteria) this;
        }

        public Criteria andContractToCompanyIn(List<Integer> values) {
            addCriterion("contract_to_company in", values, "contractToCompany");
            return (Criteria) this;
        }

        public Criteria andContractToCompanyNotIn(List<Integer> values) {
            addCriterion("contract_to_company not in", values, "contractToCompany");
            return (Criteria) this;
        }

        public Criteria andContractToCompanyBetween(Integer value1, Integer value2) {
            addCriterion("contract_to_company between", value1, value2, "contractToCompany");
            return (Criteria) this;
        }

        public Criteria andContractToCompanyNotBetween(Integer value1, Integer value2) {
            addCriterion("contract_to_company not between", value1, value2, "contractToCompany");
            return (Criteria) this;
        }

        public Criteria andContractToCompanyCreditCodeIsNull() {
            addCriterion("contract_to_company_credit_code is null");
            return (Criteria) this;
        }

        public Criteria andContractToCompanyCreditCodeIsNotNull() {
            addCriterion("contract_to_company_credit_code is not null");
            return (Criteria) this;
        }

        public Criteria andContractToCompanyCreditCodeEqualTo(String value) {
            addCriterion("contract_to_company_credit_code =", value, "contractToCompanyCreditCode");
            return (Criteria) this;
        }

        public Criteria andContractToCompanyCreditCodeNotEqualTo(String value) {
            addCriterion("contract_to_company_credit_code <>", value, "contractToCompanyCreditCode");
            return (Criteria) this;
        }

        public Criteria andContractToCompanyCreditCodeGreaterThan(String value) {
            addCriterion("contract_to_company_credit_code >", value, "contractToCompanyCreditCode");
            return (Criteria) this;
        }

        public Criteria andContractToCompanyCreditCodeGreaterThanOrEqualTo(String value) {
            addCriterion("contract_to_company_credit_code >=", value, "contractToCompanyCreditCode");
            return (Criteria) this;
        }

        public Criteria andContractToCompanyCreditCodeLessThan(String value) {
            addCriterion("contract_to_company_credit_code <", value, "contractToCompanyCreditCode");
            return (Criteria) this;
        }

        public Criteria andContractToCompanyCreditCodeLessThanOrEqualTo(String value) {
            addCriterion("contract_to_company_credit_code <=", value, "contractToCompanyCreditCode");
            return (Criteria) this;
        }

        public Criteria andContractToCompanyCreditCodeLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("contract_to_company_credit_code like", value, "contractToCompanyCreditCode");
            return (Criteria) this;
        }

        public Criteria andContractToCompanyCreditCodeNotLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("contract_to_company_credit_code not like", value, "contractToCompanyCreditCode");
            return (Criteria) this;
        }

        public Criteria andContractToCompanyCreditCodeLikeList(List<String> values) {
            addCriterion("contract_to_company_credit_code like", values, "contractToCompanyCreditCode");
            return (Criteria) this;
        }

        public Criteria andContractToCompanyCreditCodeIn(List<String> values) {
            addCriterion("contract_to_company_credit_code in", values, "contractToCompanyCreditCode");
            return (Criteria) this;
        }

        public Criteria andContractToCompanyCreditCodeNotIn(List<String> values) {
            addCriterion("contract_to_company_credit_code not in", values, "contractToCompanyCreditCode");
            return (Criteria) this;
        }

        public Criteria andContractToCompanyCreditCodeBetween(String value1, String value2) {
            addCriterion("contract_to_company_credit_code between", value1, value2, "contractToCompanyCreditCode");
            return (Criteria) this;
        }

        public Criteria andContractToCompanyCreditCodeNotBetween(String value1, String value2) {
            addCriterion("contract_to_company_credit_code not between", value1, value2, "contractToCompanyCreditCode");
            return (Criteria) this;
        }

        public Criteria andPayToCompanyIsNull() {
            addCriterion("pay_to_company is null");
            return (Criteria) this;
        }

        public Criteria andPayToCompanyIsNotNull() {
            addCriterion("pay_to_company is not null");
            return (Criteria) this;
        }

        public Criteria andPayToCompanyEqualTo(Integer value) {
            addCriterion("pay_to_company =", value, "payToCompany");
            return (Criteria) this;
        }

        public Criteria andPayToCompanyNotEqualTo(Integer value) {
            addCriterion("pay_to_company <>", value, "payToCompany");
            return (Criteria) this;
        }

        public Criteria andPayToCompanyGreaterThan(Integer value) {
            addCriterion("pay_to_company >", value, "payToCompany");
            return (Criteria) this;
        }

        public Criteria andPayToCompanyGreaterThanOrEqualTo(Integer value) {
            addCriterion("pay_to_company >=", value, "payToCompany");
            return (Criteria) this;
        }

        public Criteria andPayToCompanyLessThan(Integer value) {
            addCriterion("pay_to_company <", value, "payToCompany");
            return (Criteria) this;
        }

        public Criteria andPayToCompanyLessThanOrEqualTo(Integer value) {
            addCriterion("pay_to_company <=", value, "payToCompany");
            return (Criteria) this;
        }

        public Criteria andPayToCompanyIn(List<Integer> values) {
            addCriterion("pay_to_company in", values, "payToCompany");
            return (Criteria) this;
        }

        public Criteria andPayToCompanyNotIn(List<Integer> values) {
            addCriterion("pay_to_company not in", values, "payToCompany");
            return (Criteria) this;
        }

        public Criteria andPayToCompanyBetween(Integer value1, Integer value2) {
            addCriterion("pay_to_company between", value1, value2, "payToCompany");
            return (Criteria) this;
        }

        public Criteria andPayToCompanyNotBetween(Integer value1, Integer value2) {
            addCriterion("pay_to_company not between", value1, value2, "payToCompany");
            return (Criteria) this;
        }

        public Criteria andPayToCompanyCreditCodeIsNull() {
            addCriterion("pay_to_company_credit_code is null");
            return (Criteria) this;
        }

        public Criteria andPayToCompanyCreditCodeIsNotNull() {
            addCriterion("pay_to_company_credit_code is not null");
            return (Criteria) this;
        }

        public Criteria andPayToCompanyCreditCodeEqualTo(String value) {
            addCriterion("pay_to_company_credit_code =", value, "payToCompanyCreditCode");
            return (Criteria) this;
        }

        public Criteria andPayToCompanyCreditCodeNotEqualTo(String value) {
            addCriterion("pay_to_company_credit_code <>", value, "payToCompanyCreditCode");
            return (Criteria) this;
        }

        public Criteria andPayToCompanyCreditCodeGreaterThan(String value) {
            addCriterion("pay_to_company_credit_code >", value, "payToCompanyCreditCode");
            return (Criteria) this;
        }

        public Criteria andPayToCompanyCreditCodeGreaterThanOrEqualTo(String value) {
            addCriterion("pay_to_company_credit_code >=", value, "payToCompanyCreditCode");
            return (Criteria) this;
        }

        public Criteria andPayToCompanyCreditCodeLessThan(String value) {
            addCriterion("pay_to_company_credit_code <", value, "payToCompanyCreditCode");
            return (Criteria) this;
        }

        public Criteria andPayToCompanyCreditCodeLessThanOrEqualTo(String value) {
            addCriterion("pay_to_company_credit_code <=", value, "payToCompanyCreditCode");
            return (Criteria) this;
        }

        public Criteria andPayToCompanyCreditCodeLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("pay_to_company_credit_code like", value, "payToCompanyCreditCode");
            return (Criteria) this;
        }

        public Criteria andPayToCompanyCreditCodeNotLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("pay_to_company_credit_code not like", value, "payToCompanyCreditCode");
            return (Criteria) this;
        }

        public Criteria andPayToCompanyCreditCodeLikeList(List<String> values) {
            addCriterion("pay_to_company_credit_code like", values, "payToCompanyCreditCode");
            return (Criteria) this;
        }

        public Criteria andPayToCompanyCreditCodeIn(List<String> values) {
            addCriterion("pay_to_company_credit_code in", values, "payToCompanyCreditCode");
            return (Criteria) this;
        }

        public Criteria andPayToCompanyCreditCodeNotIn(List<String> values) {
            addCriterion("pay_to_company_credit_code not in", values, "payToCompanyCreditCode");
            return (Criteria) this;
        }

        public Criteria andPayToCompanyCreditCodeBetween(String value1, String value2) {
            addCriterion("pay_to_company_credit_code between", value1, value2, "payToCompanyCreditCode");
            return (Criteria) this;
        }

        public Criteria andPayToCompanyCreditCodeNotBetween(String value1, String value2) {
            addCriterion("pay_to_company_credit_code not between", value1, value2, "payToCompanyCreditCode");
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
        private ReconcilicationForCompanyExample example;

        protected Criteria(ReconcilicationForCompanyExample example) {
            super();
            this.example = example;
        }

        public ReconcilicationForCompanyExample example() {
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