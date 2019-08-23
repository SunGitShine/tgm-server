package com.juma.tgm.project.domain.v2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ProjectExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int startOffSet = -1;

    protected int size = -1;

    public ProjectExample() {
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

    public ProjectExample orderBy(String ... orderByClauses) {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andProjectIdIsNull() {
            addCriterion("project_id is null");
            return (Criteria) this;
        }

        public Criteria andProjectIdIsNotNull() {
            addCriterion("project_id is not null");
            return (Criteria) this;
        }

        public Criteria andProjectIdEqualTo(Integer value) {
            addCriterion("project_id =", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotEqualTo(Integer value) {
            addCriterion("project_id <>", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThan(Integer value) {
            addCriterion("project_id >", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("project_id >=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThan(Integer value) {
            addCriterion("project_id <", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThanOrEqualTo(Integer value) {
            addCriterion("project_id <=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdIn(List<Integer> values) {
            addCriterion("project_id in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotIn(List<Integer> values) {
            addCriterion("project_id not in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdBetween(Integer value1, Integer value2) {
            addCriterion("project_id between", value1, value2, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotBetween(Integer value1, Integer value2) {
            addCriterion("project_id not between", value1, value2, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectNoIsNull() {
            addCriterion("project_no is null");
            return (Criteria) this;
        }

        public Criteria andProjectNoIsNotNull() {
            addCriterion("project_no is not null");
            return (Criteria) this;
        }

        public Criteria andProjectNoEqualTo(String value) {
            addCriterion("project_no =", value, "projectNo");
            return (Criteria) this;
        }

        public Criteria andProjectNoNotEqualTo(String value) {
            addCriterion("project_no <>", value, "projectNo");
            return (Criteria) this;
        }

        public Criteria andProjectNoGreaterThan(String value) {
            addCriterion("project_no >", value, "projectNo");
            return (Criteria) this;
        }

        public Criteria andProjectNoGreaterThanOrEqualTo(String value) {
            addCriterion("project_no >=", value, "projectNo");
            return (Criteria) this;
        }

        public Criteria andProjectNoLessThan(String value) {
            addCriterion("project_no <", value, "projectNo");
            return (Criteria) this;
        }

        public Criteria andProjectNoLessThanOrEqualTo(String value) {
            addCriterion("project_no <=", value, "projectNo");
            return (Criteria) this;
        }

        public Criteria andProjectNoLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("project_no like", value, "projectNo");
            return (Criteria) this;
        }

        public Criteria andProjectNoNotLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("project_no not like", value, "projectNo");
            return (Criteria) this;
        }

        public Criteria andProjectNoLikeList(List<String> values) {
            addCriterion("project_no like", values, "projectNo");
            return (Criteria) this;
        }

        public Criteria andProjectNoIn(List<String> values) {
            addCriterion("project_no in", values, "projectNo");
            return (Criteria) this;
        }

        public Criteria andProjectNoNotIn(List<String> values) {
            addCriterion("project_no not in", values, "projectNo");
            return (Criteria) this;
        }

        public Criteria andProjectNoBetween(String value1, String value2) {
            addCriterion("project_no between", value1, value2, "projectNo");
            return (Criteria) this;
        }

        public Criteria andProjectNoNotBetween(String value1, String value2) {
            addCriterion("project_no not between", value1, value2, "projectNo");
            return (Criteria) this;
        }

        public Criteria andProjectTypeIsNull() {
            addCriterion("project_type is null");
            return (Criteria) this;
        }

        public Criteria andProjectTypeIsNotNull() {
            addCriterion("project_type is not null");
            return (Criteria) this;
        }

        public Criteria andProjectTypeEqualTo(Integer value) {
            addCriterion("project_type =", value, "projectType");
            return (Criteria) this;
        }

        public Criteria andProjectTypeNotEqualTo(Integer value) {
            addCriterion("project_type <>", value, "projectType");
            return (Criteria) this;
        }

        public Criteria andProjectTypeGreaterThan(Integer value) {
            addCriterion("project_type >", value, "projectType");
            return (Criteria) this;
        }

        public Criteria andProjectTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("project_type >=", value, "projectType");
            return (Criteria) this;
        }

        public Criteria andProjectTypeLessThan(Integer value) {
            addCriterion("project_type <", value, "projectType");
            return (Criteria) this;
        }

        public Criteria andProjectTypeLessThanOrEqualTo(Integer value) {
            addCriterion("project_type <=", value, "projectType");
            return (Criteria) this;
        }

        public Criteria andProjectTypeIn(List<Integer> values) {
            addCriterion("project_type in", values, "projectType");
            return (Criteria) this;
        }

        public Criteria andProjectTypeNotIn(List<Integer> values) {
            addCriterion("project_type not in", values, "projectType");
            return (Criteria) this;
        }

        public Criteria andProjectTypeBetween(Integer value1, Integer value2) {
            addCriterion("project_type between", value1, value2, "projectType");
            return (Criteria) this;
        }

        public Criteria andProjectTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("project_type not between", value1, value2, "projectType");
            return (Criteria) this;
        }

        public Criteria andProjectStatusIsNull() {
            addCriterion("project_status is null");
            return (Criteria) this;
        }

        public Criteria andProjectStatusIsNotNull() {
            addCriterion("project_status is not null");
            return (Criteria) this;
        }

        public Criteria andProjectStatusEqualTo(Integer value) {
            addCriterion("project_status =", value, "projectStatus");
            return (Criteria) this;
        }

        public Criteria andProjectStatusNotEqualTo(Integer value) {
            addCriterion("project_status <>", value, "projectStatus");
            return (Criteria) this;
        }

        public Criteria andProjectStatusGreaterThan(Integer value) {
            addCriterion("project_status >", value, "projectStatus");
            return (Criteria) this;
        }

        public Criteria andProjectStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("project_status >=", value, "projectStatus");
            return (Criteria) this;
        }

        public Criteria andProjectStatusLessThan(Integer value) {
            addCriterion("project_status <", value, "projectStatus");
            return (Criteria) this;
        }

        public Criteria andProjectStatusLessThanOrEqualTo(Integer value) {
            addCriterion("project_status <=", value, "projectStatus");
            return (Criteria) this;
        }

        public Criteria andProjectStatusIn(List<Integer> values) {
            addCriterion("project_status in", values, "projectStatus");
            return (Criteria) this;
        }

        public Criteria andProjectStatusNotIn(List<Integer> values) {
            addCriterion("project_status not in", values, "projectStatus");
            return (Criteria) this;
        }

        public Criteria andProjectStatusBetween(Integer value1, Integer value2) {
            addCriterion("project_status between", value1, value2, "projectStatus");
            return (Criteria) this;
        }

        public Criteria andProjectStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("project_status not between", value1, value2, "projectStatus");
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

        public Criteria andContractNoIsNull() {
            addCriterion("contract_no is null");
            return (Criteria) this;
        }

        public Criteria andContractNoIsNotNull() {
            addCriterion("contract_no is not null");
            return (Criteria) this;
        }

        public Criteria andContractNoEqualTo(String value) {
            addCriterion("contract_no =", value, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoNotEqualTo(String value) {
            addCriterion("contract_no <>", value, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoGreaterThan(String value) {
            addCriterion("contract_no >", value, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoGreaterThanOrEqualTo(String value) {
            addCriterion("contract_no >=", value, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoLessThan(String value) {
            addCriterion("contract_no <", value, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoLessThanOrEqualTo(String value) {
            addCriterion("contract_no <=", value, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("contract_no like", value, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoNotLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("contract_no not like", value, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoLikeList(List<String> values) {
            addCriterion("contract_no like", values, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoIn(List<String> values) {
            addCriterion("contract_no in", values, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoNotIn(List<String> values) {
            addCriterion("contract_no not in", values, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoBetween(String value1, String value2) {
            addCriterion("contract_no between", value1, value2, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoNotBetween(String value1, String value2) {
            addCriterion("contract_no not between", value1, value2, "contractNo");
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

        public Criteria andProjectManagerUserIdIsNull() {
            addCriterion("project_manager_user_id is null");
            return (Criteria) this;
        }

        public Criteria andProjectManagerUserIdIsNotNull() {
            addCriterion("project_manager_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andProjectManagerUserIdEqualTo(Integer value) {
            addCriterion("project_manager_user_id =", value, "projectManagerUserId");
            return (Criteria) this;
        }

        public Criteria andProjectManagerUserIdNotEqualTo(Integer value) {
            addCriterion("project_manager_user_id <>", value, "projectManagerUserId");
            return (Criteria) this;
        }

        public Criteria andProjectManagerUserIdGreaterThan(Integer value) {
            addCriterion("project_manager_user_id >", value, "projectManagerUserId");
            return (Criteria) this;
        }

        public Criteria andProjectManagerUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("project_manager_user_id >=", value, "projectManagerUserId");
            return (Criteria) this;
        }

        public Criteria andProjectManagerUserIdLessThan(Integer value) {
            addCriterion("project_manager_user_id <", value, "projectManagerUserId");
            return (Criteria) this;
        }

        public Criteria andProjectManagerUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("project_manager_user_id <=", value, "projectManagerUserId");
            return (Criteria) this;
        }

        public Criteria andProjectManagerUserIdIn(List<Integer> values) {
            addCriterion("project_manager_user_id in", values, "projectManagerUserId");
            return (Criteria) this;
        }

        public Criteria andProjectManagerUserIdNotIn(List<Integer> values) {
            addCriterion("project_manager_user_id not in", values, "projectManagerUserId");
            return (Criteria) this;
        }

        public Criteria andProjectManagerUserIdBetween(Integer value1, Integer value2) {
            addCriterion("project_manager_user_id between", value1, value2, "projectManagerUserId");
            return (Criteria) this;
        }

        public Criteria andProjectManagerUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("project_manager_user_id not between", value1, value2, "projectManagerUserId");
            return (Criteria) this;
        }

        public Criteria andTryWorkPassAttachmentIsNull() {
            addCriterion("try_work_pass_attachment is null");
            return (Criteria) this;
        }

        public Criteria andTryWorkPassAttachmentIsNotNull() {
            addCriterion("try_work_pass_attachment is not null");
            return (Criteria) this;
        }

        public Criteria andTryWorkPassAttachmentEqualTo(String value) {
            addCriterion("try_work_pass_attachment =", value, "tryWorkPassAttachment");
            return (Criteria) this;
        }

        public Criteria andTryWorkPassAttachmentNotEqualTo(String value) {
            addCriterion("try_work_pass_attachment <>", value, "tryWorkPassAttachment");
            return (Criteria) this;
        }

        public Criteria andTryWorkPassAttachmentGreaterThan(String value) {
            addCriterion("try_work_pass_attachment >", value, "tryWorkPassAttachment");
            return (Criteria) this;
        }

        public Criteria andTryWorkPassAttachmentGreaterThanOrEqualTo(String value) {
            addCriterion("try_work_pass_attachment >=", value, "tryWorkPassAttachment");
            return (Criteria) this;
        }

        public Criteria andTryWorkPassAttachmentLessThan(String value) {
            addCriterion("try_work_pass_attachment <", value, "tryWorkPassAttachment");
            return (Criteria) this;
        }

        public Criteria andTryWorkPassAttachmentLessThanOrEqualTo(String value) {
            addCriterion("try_work_pass_attachment <=", value, "tryWorkPassAttachment");
            return (Criteria) this;
        }

        public Criteria andTryWorkPassAttachmentLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("try_work_pass_attachment like", value, "tryWorkPassAttachment");
            return (Criteria) this;
        }

        public Criteria andTryWorkPassAttachmentNotLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("try_work_pass_attachment not like", value, "tryWorkPassAttachment");
            return (Criteria) this;
        }

        public Criteria andTryWorkPassAttachmentLikeList(List<String> values) {
            addCriterion("try_work_pass_attachment like", values, "tryWorkPassAttachment");
            return (Criteria) this;
        }

        public Criteria andTryWorkPassAttachmentIn(List<String> values) {
            addCriterion("try_work_pass_attachment in", values, "tryWorkPassAttachment");
            return (Criteria) this;
        }

        public Criteria andTryWorkPassAttachmentNotIn(List<String> values) {
            addCriterion("try_work_pass_attachment not in", values, "tryWorkPassAttachment");
            return (Criteria) this;
        }

        public Criteria andTryWorkPassAttachmentBetween(String value1, String value2) {
            addCriterion("try_work_pass_attachment between", value1, value2, "tryWorkPassAttachment");
            return (Criteria) this;
        }

        public Criteria andTryWorkPassAttachmentNotBetween(String value1, String value2) {
            addCriterion("try_work_pass_attachment not between", value1, value2, "tryWorkPassAttachment");
            return (Criteria) this;
        }

        public Criteria andTryWorkProtocolIsNull() {
            addCriterion("try_work_protocol is null");
            return (Criteria) this;
        }

        public Criteria andTryWorkProtocolIsNotNull() {
            addCriterion("try_work_protocol is not null");
            return (Criteria) this;
        }

        public Criteria andTryWorkProtocolEqualTo(String value) {
            addCriterion("try_work_protocol =", value, "tryWorkProtocol");
            return (Criteria) this;
        }

        public Criteria andTryWorkProtocolNotEqualTo(String value) {
            addCriterion("try_work_protocol <>", value, "tryWorkProtocol");
            return (Criteria) this;
        }

        public Criteria andTryWorkProtocolGreaterThan(String value) {
            addCriterion("try_work_protocol >", value, "tryWorkProtocol");
            return (Criteria) this;
        }

        public Criteria andTryWorkProtocolGreaterThanOrEqualTo(String value) {
            addCriterion("try_work_protocol >=", value, "tryWorkProtocol");
            return (Criteria) this;
        }

        public Criteria andTryWorkProtocolLessThan(String value) {
            addCriterion("try_work_protocol <", value, "tryWorkProtocol");
            return (Criteria) this;
        }

        public Criteria andTryWorkProtocolLessThanOrEqualTo(String value) {
            addCriterion("try_work_protocol <=", value, "tryWorkProtocol");
            return (Criteria) this;
        }

        public Criteria andTryWorkProtocolLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("try_work_protocol like", value, "tryWorkProtocol");
            return (Criteria) this;
        }

        public Criteria andTryWorkProtocolNotLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("try_work_protocol not like", value, "tryWorkProtocol");
            return (Criteria) this;
        }

        public Criteria andTryWorkProtocolLikeList(List<String> values) {
            addCriterion("try_work_protocol like", values, "tryWorkProtocol");
            return (Criteria) this;
        }

        public Criteria andTryWorkProtocolIn(List<String> values) {
            addCriterion("try_work_protocol in", values, "tryWorkProtocol");
            return (Criteria) this;
        }

        public Criteria andTryWorkProtocolNotIn(List<String> values) {
            addCriterion("try_work_protocol not in", values, "tryWorkProtocol");
            return (Criteria) this;
        }

        public Criteria andTryWorkProtocolBetween(String value1, String value2) {
            addCriterion("try_work_protocol between", value1, value2, "tryWorkProtocol");
            return (Criteria) this;
        }

        public Criteria andTryWorkProtocolNotBetween(String value1, String value2) {
            addCriterion("try_work_protocol not between", value1, value2, "tryWorkProtocol");
            return (Criteria) this;
        }

        public Criteria andProjectStartDateIsNull() {
            addCriterion("project_start_date is null");
            return (Criteria) this;
        }

        public Criteria andProjectStartDateIsNotNull() {
            addCriterion("project_start_date is not null");
            return (Criteria) this;
        }

        public Criteria andProjectStartDateEqualTo(Date value) {
            addCriterionForJDBCDate("project_start_date =", value, "projectStartDate");
            return (Criteria) this;
        }

        public Criteria andProjectStartDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("project_start_date <>", value, "projectStartDate");
            return (Criteria) this;
        }

        public Criteria andProjectStartDateGreaterThan(Date value) {
            addCriterionForJDBCDate("project_start_date >", value, "projectStartDate");
            return (Criteria) this;
        }

        public Criteria andProjectStartDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("project_start_date >=", value, "projectStartDate");
            return (Criteria) this;
        }

        public Criteria andProjectStartDateLessThan(Date value) {
            addCriterionForJDBCDate("project_start_date <", value, "projectStartDate");
            return (Criteria) this;
        }

        public Criteria andProjectStartDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("project_start_date <=", value, "projectStartDate");
            return (Criteria) this;
        }

        public Criteria andProjectStartDateIn(List<Date> values) {
            addCriterionForJDBCDate("project_start_date in", values, "projectStartDate");
            return (Criteria) this;
        }

        public Criteria andProjectStartDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("project_start_date not in", values, "projectStartDate");
            return (Criteria) this;
        }

        public Criteria andProjectStartDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("project_start_date between", value1, value2, "projectStartDate");
            return (Criteria) this;
        }

        public Criteria andProjectStartDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("project_start_date not between", value1, value2, "projectStartDate");
            return (Criteria) this;
        }

        public Criteria andProjectEndDateIsNull() {
            addCriterion("project_end_date is null");
            return (Criteria) this;
        }

        public Criteria andProjectEndDateIsNotNull() {
            addCriterion("project_end_date is not null");
            return (Criteria) this;
        }

        public Criteria andProjectEndDateEqualTo(Date value) {
            addCriterionForJDBCDate("project_end_date =", value, "projectEndDate");
            return (Criteria) this;
        }

        public Criteria andProjectEndDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("project_end_date <>", value, "projectEndDate");
            return (Criteria) this;
        }

        public Criteria andProjectEndDateGreaterThan(Date value) {
            addCriterionForJDBCDate("project_end_date >", value, "projectEndDate");
            return (Criteria) this;
        }

        public Criteria andProjectEndDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("project_end_date >=", value, "projectEndDate");
            return (Criteria) this;
        }

        public Criteria andProjectEndDateLessThan(Date value) {
            addCriterionForJDBCDate("project_end_date <", value, "projectEndDate");
            return (Criteria) this;
        }

        public Criteria andProjectEndDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("project_end_date <=", value, "projectEndDate");
            return (Criteria) this;
        }

        public Criteria andProjectEndDateIn(List<Date> values) {
            addCriterionForJDBCDate("project_end_date in", values, "projectEndDate");
            return (Criteria) this;
        }

        public Criteria andProjectEndDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("project_end_date not in", values, "projectEndDate");
            return (Criteria) this;
        }

        public Criteria andProjectEndDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("project_end_date between", value1, value2, "projectEndDate");
            return (Criteria) this;
        }

        public Criteria andProjectEndDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("project_end_date not between", value1, value2, "projectEndDate");
            return (Criteria) this;
        }

        public Criteria andIsRunningIsNull() {
            addCriterion("is_running is null");
            return (Criteria) this;
        }

        public Criteria andIsRunningIsNotNull() {
            addCriterion("is_running is not null");
            return (Criteria) this;
        }

        public Criteria andIsRunningEqualTo(Boolean value) {
            addCriterion("is_running =", value, "isRunning");
            return (Criteria) this;
        }

        public Criteria andIsRunningNotEqualTo(Boolean value) {
            addCriterion("is_running <>", value, "isRunning");
            return (Criteria) this;
        }

        public Criteria andIsRunningGreaterThan(Boolean value) {
            addCriterion("is_running >", value, "isRunning");
            return (Criteria) this;
        }

        public Criteria andIsRunningGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_running >=", value, "isRunning");
            return (Criteria) this;
        }

        public Criteria andIsRunningLessThan(Boolean value) {
            addCriterion("is_running <", value, "isRunning");
            return (Criteria) this;
        }

        public Criteria andIsRunningLessThanOrEqualTo(Boolean value) {
            addCriterion("is_running <=", value, "isRunning");
            return (Criteria) this;
        }

        public Criteria andIsRunningIn(List<Boolean> values) {
            addCriterion("is_running in", values, "isRunning");
            return (Criteria) this;
        }

        public Criteria andIsRunningNotIn(List<Boolean> values) {
            addCriterion("is_running not in", values, "isRunning");
            return (Criteria) this;
        }

        public Criteria andIsRunningBetween(Boolean value1, Boolean value2) {
            addCriterion("is_running between", value1, value2, "isRunning");
            return (Criteria) this;
        }

        public Criteria andIsRunningNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_running not between", value1, value2, "isRunning");
            return (Criteria) this;
        }

        public Criteria andRunningTimeIsNull() {
            addCriterion("running_time is null");
            return (Criteria) this;
        }

        public Criteria andRunningTimeIsNotNull() {
            addCriterion("running_time is not null");
            return (Criteria) this;
        }

        public Criteria andRunningTimeEqualTo(Date value) {
            addCriterionForJDBCDate("running_time =", value, "runningTime");
            return (Criteria) this;
        }

        public Criteria andRunningTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("running_time <>", value, "runningTime");
            return (Criteria) this;
        }

        public Criteria andRunningTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("running_time >", value, "runningTime");
            return (Criteria) this;
        }

        public Criteria andRunningTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("running_time >=", value, "runningTime");
            return (Criteria) this;
        }

        public Criteria andRunningTimeLessThan(Date value) {
            addCriterionForJDBCDate("running_time <", value, "runningTime");
            return (Criteria) this;
        }

        public Criteria andRunningTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("running_time <=", value, "runningTime");
            return (Criteria) this;
        }

        public Criteria andRunningTimeIn(List<Date> values) {
            addCriterionForJDBCDate("running_time in", values, "runningTime");
            return (Criteria) this;
        }

        public Criteria andRunningTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("running_time not in", values, "runningTime");
            return (Criteria) this;
        }

        public Criteria andRunningTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("running_time between", value1, value2, "runningTime");
            return (Criteria) this;
        }

        public Criteria andRunningTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("running_time not between", value1, value2, "runningTime");
            return (Criteria) this;
        }

        public Criteria andBusinessLinkmanIsNull() {
            addCriterion("business_linkman is null");
            return (Criteria) this;
        }

        public Criteria andBusinessLinkmanIsNotNull() {
            addCriterion("business_linkman is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessLinkmanEqualTo(String value) {
            addCriterion("business_linkman =", value, "businessLinkman");
            return (Criteria) this;
        }

        public Criteria andBusinessLinkmanNotEqualTo(String value) {
            addCriterion("business_linkman <>", value, "businessLinkman");
            return (Criteria) this;
        }

        public Criteria andBusinessLinkmanGreaterThan(String value) {
            addCriterion("business_linkman >", value, "businessLinkman");
            return (Criteria) this;
        }

        public Criteria andBusinessLinkmanGreaterThanOrEqualTo(String value) {
            addCriterion("business_linkman >=", value, "businessLinkman");
            return (Criteria) this;
        }

        public Criteria andBusinessLinkmanLessThan(String value) {
            addCriterion("business_linkman <", value, "businessLinkman");
            return (Criteria) this;
        }

        public Criteria andBusinessLinkmanLessThanOrEqualTo(String value) {
            addCriterion("business_linkman <=", value, "businessLinkman");
            return (Criteria) this;
        }

        public Criteria andBusinessLinkmanLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("business_linkman like", value, "businessLinkman");
            return (Criteria) this;
        }

        public Criteria andBusinessLinkmanNotLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("business_linkman not like", value, "businessLinkman");
            return (Criteria) this;
        }

        public Criteria andBusinessLinkmanLikeList(List<String> values) {
            addCriterion("business_linkman like", values, "businessLinkman");
            return (Criteria) this;
        }

        public Criteria andBusinessLinkmanIn(List<String> values) {
            addCriterion("business_linkman in", values, "businessLinkman");
            return (Criteria) this;
        }

        public Criteria andBusinessLinkmanNotIn(List<String> values) {
            addCriterion("business_linkman not in", values, "businessLinkman");
            return (Criteria) this;
        }

        public Criteria andBusinessLinkmanBetween(String value1, String value2) {
            addCriterion("business_linkman between", value1, value2, "businessLinkman");
            return (Criteria) this;
        }

        public Criteria andBusinessLinkmanNotBetween(String value1, String value2) {
            addCriterion("business_linkman not between", value1, value2, "businessLinkman");
            return (Criteria) this;
        }

        public Criteria andBusinessLinktelIsNull() {
            addCriterion("business_linktel is null");
            return (Criteria) this;
        }

        public Criteria andBusinessLinktelIsNotNull() {
            addCriterion("business_linktel is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessLinktelEqualTo(String value) {
            addCriterion("business_linktel =", value, "businessLinktel");
            return (Criteria) this;
        }

        public Criteria andBusinessLinktelNotEqualTo(String value) {
            addCriterion("business_linktel <>", value, "businessLinktel");
            return (Criteria) this;
        }

        public Criteria andBusinessLinktelGreaterThan(String value) {
            addCriterion("business_linktel >", value, "businessLinktel");
            return (Criteria) this;
        }

        public Criteria andBusinessLinktelGreaterThanOrEqualTo(String value) {
            addCriterion("business_linktel >=", value, "businessLinktel");
            return (Criteria) this;
        }

        public Criteria andBusinessLinktelLessThan(String value) {
            addCriterion("business_linktel <", value, "businessLinktel");
            return (Criteria) this;
        }

        public Criteria andBusinessLinktelLessThanOrEqualTo(String value) {
            addCriterion("business_linktel <=", value, "businessLinktel");
            return (Criteria) this;
        }

        public Criteria andBusinessLinktelLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("business_linktel like", value, "businessLinktel");
            return (Criteria) this;
        }

        public Criteria andBusinessLinktelNotLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("business_linktel not like", value, "businessLinktel");
            return (Criteria) this;
        }

        public Criteria andBusinessLinktelLikeList(List<String> values) {
            addCriterion("business_linktel like", values, "businessLinktel");
            return (Criteria) this;
        }

        public Criteria andBusinessLinktelIn(List<String> values) {
            addCriterion("business_linktel in", values, "businessLinktel");
            return (Criteria) this;
        }

        public Criteria andBusinessLinktelNotIn(List<String> values) {
            addCriterion("business_linktel not in", values, "businessLinktel");
            return (Criteria) this;
        }

        public Criteria andBusinessLinktelBetween(String value1, String value2) {
            addCriterion("business_linktel between", value1, value2, "businessLinktel");
            return (Criteria) this;
        }

        public Criteria andBusinessLinktelNotBetween(String value1, String value2) {
            addCriterion("business_linktel not between", value1, value2, "businessLinktel");
            return (Criteria) this;
        }

        public Criteria andBusinessLinkemailIsNull() {
            addCriterion("business_linkemail is null");
            return (Criteria) this;
        }

        public Criteria andBusinessLinkemailIsNotNull() {
            addCriterion("business_linkemail is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessLinkemailEqualTo(String value) {
            addCriterion("business_linkemail =", value, "businessLinkemail");
            return (Criteria) this;
        }

        public Criteria andBusinessLinkemailNotEqualTo(String value) {
            addCriterion("business_linkemail <>", value, "businessLinkemail");
            return (Criteria) this;
        }

        public Criteria andBusinessLinkemailGreaterThan(String value) {
            addCriterion("business_linkemail >", value, "businessLinkemail");
            return (Criteria) this;
        }

        public Criteria andBusinessLinkemailGreaterThanOrEqualTo(String value) {
            addCriterion("business_linkemail >=", value, "businessLinkemail");
            return (Criteria) this;
        }

        public Criteria andBusinessLinkemailLessThan(String value) {
            addCriterion("business_linkemail <", value, "businessLinkemail");
            return (Criteria) this;
        }

        public Criteria andBusinessLinkemailLessThanOrEqualTo(String value) {
            addCriterion("business_linkemail <=", value, "businessLinkemail");
            return (Criteria) this;
        }

        public Criteria andBusinessLinkemailLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("business_linkemail like", value, "businessLinkemail");
            return (Criteria) this;
        }

        public Criteria andBusinessLinkemailNotLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("business_linkemail not like", value, "businessLinkemail");
            return (Criteria) this;
        }

        public Criteria andBusinessLinkemailLikeList(List<String> values) {
            addCriterion("business_linkemail like", values, "businessLinkemail");
            return (Criteria) this;
        }

        public Criteria andBusinessLinkemailIn(List<String> values) {
            addCriterion("business_linkemail in", values, "businessLinkemail");
            return (Criteria) this;
        }

        public Criteria andBusinessLinkemailNotIn(List<String> values) {
            addCriterion("business_linkemail not in", values, "businessLinkemail");
            return (Criteria) this;
        }

        public Criteria andBusinessLinkemailBetween(String value1, String value2) {
            addCriterion("business_linkemail between", value1, value2, "businessLinkemail");
            return (Criteria) this;
        }

        public Criteria andBusinessLinkemailNotBetween(String value1, String value2) {
            addCriterion("business_linkemail not between", value1, value2, "businessLinkemail");
            return (Criteria) this;
        }

        public Criteria andFinanceLinkmanIsNull() {
            addCriterion("finance_linkman is null");
            return (Criteria) this;
        }

        public Criteria andFinanceLinkmanIsNotNull() {
            addCriterion("finance_linkman is not null");
            return (Criteria) this;
        }

        public Criteria andFinanceLinkmanEqualTo(String value) {
            addCriterion("finance_linkman =", value, "financeLinkman");
            return (Criteria) this;
        }

        public Criteria andFinanceLinkmanNotEqualTo(String value) {
            addCriterion("finance_linkman <>", value, "financeLinkman");
            return (Criteria) this;
        }

        public Criteria andFinanceLinkmanGreaterThan(String value) {
            addCriterion("finance_linkman >", value, "financeLinkman");
            return (Criteria) this;
        }

        public Criteria andFinanceLinkmanGreaterThanOrEqualTo(String value) {
            addCriterion("finance_linkman >=", value, "financeLinkman");
            return (Criteria) this;
        }

        public Criteria andFinanceLinkmanLessThan(String value) {
            addCriterion("finance_linkman <", value, "financeLinkman");
            return (Criteria) this;
        }

        public Criteria andFinanceLinkmanLessThanOrEqualTo(String value) {
            addCriterion("finance_linkman <=", value, "financeLinkman");
            return (Criteria) this;
        }

        public Criteria andFinanceLinkmanLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("finance_linkman like", value, "financeLinkman");
            return (Criteria) this;
        }

        public Criteria andFinanceLinkmanNotLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("finance_linkman not like", value, "financeLinkman");
            return (Criteria) this;
        }

        public Criteria andFinanceLinkmanLikeList(List<String> values) {
            addCriterion("finance_linkman like", values, "financeLinkman");
            return (Criteria) this;
        }

        public Criteria andFinanceLinkmanIn(List<String> values) {
            addCriterion("finance_linkman in", values, "financeLinkman");
            return (Criteria) this;
        }

        public Criteria andFinanceLinkmanNotIn(List<String> values) {
            addCriterion("finance_linkman not in", values, "financeLinkman");
            return (Criteria) this;
        }

        public Criteria andFinanceLinkmanBetween(String value1, String value2) {
            addCriterion("finance_linkman between", value1, value2, "financeLinkman");
            return (Criteria) this;
        }

        public Criteria andFinanceLinkmanNotBetween(String value1, String value2) {
            addCriterion("finance_linkman not between", value1, value2, "financeLinkman");
            return (Criteria) this;
        }

        public Criteria andFinanceLinktelIsNull() {
            addCriterion("finance_linktel is null");
            return (Criteria) this;
        }

        public Criteria andFinanceLinktelIsNotNull() {
            addCriterion("finance_linktel is not null");
            return (Criteria) this;
        }

        public Criteria andFinanceLinktelEqualTo(String value) {
            addCriterion("finance_linktel =", value, "financeLinktel");
            return (Criteria) this;
        }

        public Criteria andFinanceLinktelNotEqualTo(String value) {
            addCriterion("finance_linktel <>", value, "financeLinktel");
            return (Criteria) this;
        }

        public Criteria andFinanceLinktelGreaterThan(String value) {
            addCriterion("finance_linktel >", value, "financeLinktel");
            return (Criteria) this;
        }

        public Criteria andFinanceLinktelGreaterThanOrEqualTo(String value) {
            addCriterion("finance_linktel >=", value, "financeLinktel");
            return (Criteria) this;
        }

        public Criteria andFinanceLinktelLessThan(String value) {
            addCriterion("finance_linktel <", value, "financeLinktel");
            return (Criteria) this;
        }

        public Criteria andFinanceLinktelLessThanOrEqualTo(String value) {
            addCriterion("finance_linktel <=", value, "financeLinktel");
            return (Criteria) this;
        }

        public Criteria andFinanceLinktelLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("finance_linktel like", value, "financeLinktel");
            return (Criteria) this;
        }

        public Criteria andFinanceLinktelNotLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("finance_linktel not like", value, "financeLinktel");
            return (Criteria) this;
        }

        public Criteria andFinanceLinktelLikeList(List<String> values) {
            addCriterion("finance_linktel like", values, "financeLinktel");
            return (Criteria) this;
        }

        public Criteria andFinanceLinktelIn(List<String> values) {
            addCriterion("finance_linktel in", values, "financeLinktel");
            return (Criteria) this;
        }

        public Criteria andFinanceLinktelNotIn(List<String> values) {
            addCriterion("finance_linktel not in", values, "financeLinktel");
            return (Criteria) this;
        }

        public Criteria andFinanceLinktelBetween(String value1, String value2) {
            addCriterion("finance_linktel between", value1, value2, "financeLinktel");
            return (Criteria) this;
        }

        public Criteria andFinanceLinktelNotBetween(String value1, String value2) {
            addCriterion("finance_linktel not between", value1, value2, "financeLinktel");
            return (Criteria) this;
        }

        public Criteria andFinanceLinkemailIsNull() {
            addCriterion("finance_linkemail is null");
            return (Criteria) this;
        }

        public Criteria andFinanceLinkemailIsNotNull() {
            addCriterion("finance_linkemail is not null");
            return (Criteria) this;
        }

        public Criteria andFinanceLinkemailEqualTo(String value) {
            addCriterion("finance_linkemail =", value, "financeLinkemail");
            return (Criteria) this;
        }

        public Criteria andFinanceLinkemailNotEqualTo(String value) {
            addCriterion("finance_linkemail <>", value, "financeLinkemail");
            return (Criteria) this;
        }

        public Criteria andFinanceLinkemailGreaterThan(String value) {
            addCriterion("finance_linkemail >", value, "financeLinkemail");
            return (Criteria) this;
        }

        public Criteria andFinanceLinkemailGreaterThanOrEqualTo(String value) {
            addCriterion("finance_linkemail >=", value, "financeLinkemail");
            return (Criteria) this;
        }

        public Criteria andFinanceLinkemailLessThan(String value) {
            addCriterion("finance_linkemail <", value, "financeLinkemail");
            return (Criteria) this;
        }

        public Criteria andFinanceLinkemailLessThanOrEqualTo(String value) {
            addCriterion("finance_linkemail <=", value, "financeLinkemail");
            return (Criteria) this;
        }

        public Criteria andFinanceLinkemailLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("finance_linkemail like", value, "financeLinkemail");
            return (Criteria) this;
        }

        public Criteria andFinanceLinkemailNotLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("finance_linkemail not like", value, "financeLinkemail");
            return (Criteria) this;
        }

        public Criteria andFinanceLinkemailLikeList(List<String> values) {
            addCriterion("finance_linkemail like", values, "financeLinkemail");
            return (Criteria) this;
        }

        public Criteria andFinanceLinkemailIn(List<String> values) {
            addCriterion("finance_linkemail in", values, "financeLinkemail");
            return (Criteria) this;
        }

        public Criteria andFinanceLinkemailNotIn(List<String> values) {
            addCriterion("finance_linkemail not in", values, "financeLinkemail");
            return (Criteria) this;
        }

        public Criteria andFinanceLinkemailBetween(String value1, String value2) {
            addCriterion("finance_linkemail between", value1, value2, "financeLinkemail");
            return (Criteria) this;
        }

        public Criteria andFinanceLinkemailNotBetween(String value1, String value2) {
            addCriterion("finance_linkemail not between", value1, value2, "financeLinkemail");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressTypeIsNull() {
            addCriterion("delivery_address_type is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressTypeIsNotNull() {
            addCriterion("delivery_address_type is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressTypeEqualTo(String value) {
            addCriterion("delivery_address_type =", value, "deliveryAddressType");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressTypeNotEqualTo(String value) {
            addCriterion("delivery_address_type <>", value, "deliveryAddressType");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressTypeGreaterThan(String value) {
            addCriterion("delivery_address_type >", value, "deliveryAddressType");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressTypeGreaterThanOrEqualTo(String value) {
            addCriterion("delivery_address_type >=", value, "deliveryAddressType");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressTypeLessThan(String value) {
            addCriterion("delivery_address_type <", value, "deliveryAddressType");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressTypeLessThanOrEqualTo(String value) {
            addCriterion("delivery_address_type <=", value, "deliveryAddressType");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressTypeLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("delivery_address_type like", value, "deliveryAddressType");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressTypeNotLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("delivery_address_type not like", value, "deliveryAddressType");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressTypeLikeList(List<String> values) {
            addCriterion("delivery_address_type like", values, "deliveryAddressType");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressTypeIn(List<String> values) {
            addCriterion("delivery_address_type in", values, "deliveryAddressType");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressTypeNotIn(List<String> values) {
            addCriterion("delivery_address_type not in", values, "deliveryAddressType");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressTypeBetween(String value1, String value2) {
            addCriterion("delivery_address_type between", value1, value2, "deliveryAddressType");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressTypeNotBetween(String value1, String value2) {
            addCriterion("delivery_address_type not between", value1, value2, "deliveryAddressType");
            return (Criteria) this;
        }

        public Criteria andOldIdIsNull() {
            addCriterion("old_id is null");
            return (Criteria) this;
        }

        public Criteria andOldIdIsNotNull() {
            addCriterion("old_id is not null");
            return (Criteria) this;
        }

        public Criteria andOldIdEqualTo(Integer value) {
            addCriterion("old_id =", value, "oldId");
            return (Criteria) this;
        }

        public Criteria andOldIdNotEqualTo(Integer value) {
            addCriterion("old_id <>", value, "oldId");
            return (Criteria) this;
        }

        public Criteria andOldIdGreaterThan(Integer value) {
            addCriterion("old_id >", value, "oldId");
            return (Criteria) this;
        }

        public Criteria andOldIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("old_id >=", value, "oldId");
            return (Criteria) this;
        }

        public Criteria andOldIdLessThan(Integer value) {
            addCriterion("old_id <", value, "oldId");
            return (Criteria) this;
        }

        public Criteria andOldIdLessThanOrEqualTo(Integer value) {
            addCriterion("old_id <=", value, "oldId");
            return (Criteria) this;
        }

        public Criteria andOldIdIn(List<Integer> values) {
            addCriterion("old_id in", values, "oldId");
            return (Criteria) this;
        }

        public Criteria andOldIdNotIn(List<Integer> values) {
            addCriterion("old_id not in", values, "oldId");
            return (Criteria) this;
        }

        public Criteria andOldIdBetween(Integer value1, Integer value2) {
            addCriterion("old_id between", value1, value2, "oldId");
            return (Criteria) this;
        }

        public Criteria andOldIdNotBetween(Integer value1, Integer value2) {
            addCriterion("old_id not between", value1, value2, "oldId");
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

        public Criteria andRebateRateIsNull() {
            addCriterion("rebate_rate is null");
            return (Criteria) this;
        }

        public Criteria andRebateRateIsNotNull() {
            addCriterion("rebate_rate is not null");
            return (Criteria) this;
        }

        public Criteria andRebateRateEqualTo(BigDecimal value) {
            addCriterion("rebate_rate =", value, "rebateRate");
            return (Criteria) this;
        }

        public Criteria andRebateRateNotEqualTo(BigDecimal value) {
            addCriterion("rebate_rate <>", value, "rebateRate");
            return (Criteria) this;
        }

        public Criteria andRebateRateGreaterThan(BigDecimal value) {
            addCriterion("rebate_rate >", value, "rebateRate");
            return (Criteria) this;
        }

        public Criteria andRebateRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("rebate_rate >=", value, "rebateRate");
            return (Criteria) this;
        }

        public Criteria andRebateRateLessThan(BigDecimal value) {
            addCriterion("rebate_rate <", value, "rebateRate");
            return (Criteria) this;
        }

        public Criteria andRebateRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("rebate_rate <=", value, "rebateRate");
            return (Criteria) this;
        }

        public Criteria andRebateRateIn(List<BigDecimal> values) {
            addCriterion("rebate_rate in", values, "rebateRate");
            return (Criteria) this;
        }

        public Criteria andRebateRateNotIn(List<BigDecimal> values) {
            addCriterion("rebate_rate not in", values, "rebateRate");
            return (Criteria) this;
        }

        public Criteria andRebateRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rebate_rate between", value1, value2, "rebateRate");
            return (Criteria) this;
        }

        public Criteria andRebateRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rebate_rate not between", value1, value2, "rebateRate");
            return (Criteria) this;
        }

        public Criteria andEstimateTimeConsumptionIsNull() {
            addCriterion("estimate_time_consumption is null");
            return (Criteria) this;
        }

        public Criteria andEstimateTimeConsumptionIsNotNull() {
            addCriterion("estimate_time_consumption is not null");
            return (Criteria) this;
        }

        public Criteria andEstimateTimeConsumptionEqualTo(Integer value) {
            addCriterion("estimate_time_consumption =", value, "estimateTimeConsumption");
            return (Criteria) this;
        }

        public Criteria andEstimateTimeConsumptionNotEqualTo(Integer value) {
            addCriterion("estimate_time_consumption <>", value, "estimateTimeConsumption");
            return (Criteria) this;
        }

        public Criteria andEstimateTimeConsumptionGreaterThan(Integer value) {
            addCriterion("estimate_time_consumption >", value, "estimateTimeConsumption");
            return (Criteria) this;
        }

        public Criteria andEstimateTimeConsumptionGreaterThanOrEqualTo(Integer value) {
            addCriterion("estimate_time_consumption >=", value, "estimateTimeConsumption");
            return (Criteria) this;
        }

        public Criteria andEstimateTimeConsumptionLessThan(Integer value) {
            addCriterion("estimate_time_consumption <", value, "estimateTimeConsumption");
            return (Criteria) this;
        }

        public Criteria andEstimateTimeConsumptionLessThanOrEqualTo(Integer value) {
            addCriterion("estimate_time_consumption <=", value, "estimateTimeConsumption");
            return (Criteria) this;
        }

        public Criteria andEstimateTimeConsumptionIn(List<Integer> values) {
            addCriterion("estimate_time_consumption in", values, "estimateTimeConsumption");
            return (Criteria) this;
        }

        public Criteria andEstimateTimeConsumptionNotIn(List<Integer> values) {
            addCriterion("estimate_time_consumption not in", values, "estimateTimeConsumption");
            return (Criteria) this;
        }

        public Criteria andEstimateTimeConsumptionBetween(Integer value1, Integer value2) {
            addCriterion("estimate_time_consumption between", value1, value2, "estimateTimeConsumption");
            return (Criteria) this;
        }

        public Criteria andEstimateTimeConsumptionNotBetween(Integer value1, Integer value2) {
            addCriterion("estimate_time_consumption not between", value1, value2, "estimateTimeConsumption");
            return (Criteria) this;
        }

        public Criteria andTruckRequireRemarkIsNull() {
            addCriterion("truck_require_remark is null");
            return (Criteria) this;
        }

        public Criteria andTruckRequireRemarkIsNotNull() {
            addCriterion("truck_require_remark is not null");
            return (Criteria) this;
        }

        public Criteria andTruckRequireRemarkEqualTo(String value) {
            addCriterion("truck_require_remark =", value, "truckRequireRemark");
            return (Criteria) this;
        }

        public Criteria andTruckRequireRemarkNotEqualTo(String value) {
            addCriterion("truck_require_remark <>", value, "truckRequireRemark");
            return (Criteria) this;
        }

        public Criteria andTruckRequireRemarkGreaterThan(String value) {
            addCriterion("truck_require_remark >", value, "truckRequireRemark");
            return (Criteria) this;
        }

        public Criteria andTruckRequireRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("truck_require_remark >=", value, "truckRequireRemark");
            return (Criteria) this;
        }

        public Criteria andTruckRequireRemarkLessThan(String value) {
            addCriterion("truck_require_remark <", value, "truckRequireRemark");
            return (Criteria) this;
        }

        public Criteria andTruckRequireRemarkLessThanOrEqualTo(String value) {
            addCriterion("truck_require_remark <=", value, "truckRequireRemark");
            return (Criteria) this;
        }

        public Criteria andTruckRequireRemarkLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("truck_require_remark like", value, "truckRequireRemark");
            return (Criteria) this;
        }

        public Criteria andTruckRequireRemarkNotLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("truck_require_remark not like", value, "truckRequireRemark");
            return (Criteria) this;
        }

        public Criteria andTruckRequireRemarkLikeList(List<String> values) {
            addCriterion("truck_require_remark like", values, "truckRequireRemark");
            return (Criteria) this;
        }

        public Criteria andTruckRequireRemarkIn(List<String> values) {
            addCriterion("truck_require_remark in", values, "truckRequireRemark");
            return (Criteria) this;
        }

        public Criteria andTruckRequireRemarkNotIn(List<String> values) {
            addCriterion("truck_require_remark not in", values, "truckRequireRemark");
            return (Criteria) this;
        }

        public Criteria andTruckRequireRemarkBetween(String value1, String value2) {
            addCriterion("truck_require_remark between", value1, value2, "truckRequireRemark");
            return (Criteria) this;
        }

        public Criteria andTruckRequireRemarkNotBetween(String value1, String value2) {
            addCriterion("truck_require_remark not between", value1, value2, "truckRequireRemark");
            return (Criteria) this;
        }

        public Criteria andAdditionalFunctionIdsIsNull() {
            addCriterion("additional_function_ids is null");
            return (Criteria) this;
        }

        public Criteria andAdditionalFunctionIdsIsNotNull() {
            addCriterion("additional_function_ids is not null");
            return (Criteria) this;
        }

        public Criteria andAdditionalFunctionIdsEqualTo(String value) {
            addCriterion("additional_function_ids =", value, "additionalFunctionIds");
            return (Criteria) this;
        }

        public Criteria andAdditionalFunctionIdsNotEqualTo(String value) {
            addCriterion("additional_function_ids <>", value, "additionalFunctionIds");
            return (Criteria) this;
        }

        public Criteria andAdditionalFunctionIdsGreaterThan(String value) {
            addCriterion("additional_function_ids >", value, "additionalFunctionIds");
            return (Criteria) this;
        }

        public Criteria andAdditionalFunctionIdsGreaterThanOrEqualTo(String value) {
            addCriterion("additional_function_ids >=", value, "additionalFunctionIds");
            return (Criteria) this;
        }

        public Criteria andAdditionalFunctionIdsLessThan(String value) {
            addCriterion("additional_function_ids <", value, "additionalFunctionIds");
            return (Criteria) this;
        }

        public Criteria andAdditionalFunctionIdsLessThanOrEqualTo(String value) {
            addCriterion("additional_function_ids <=", value, "additionalFunctionIds");
            return (Criteria) this;
        }

        public Criteria andAdditionalFunctionIdsLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("additional_function_ids like", value, "additionalFunctionIds");
            return (Criteria) this;
        }

        public Criteria andAdditionalFunctionIdsNotLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("additional_function_ids not like", value, "additionalFunctionIds");
            return (Criteria) this;
        }

        public Criteria andAdditionalFunctionIdsLikeList(List<String> values) {
            addCriterion("additional_function_ids like", values, "additionalFunctionIds");
            return (Criteria) this;
        }

        public Criteria andAdditionalFunctionIdsIn(List<String> values) {
            addCriterion("additional_function_ids in", values, "additionalFunctionIds");
            return (Criteria) this;
        }

        public Criteria andAdditionalFunctionIdsNotIn(List<String> values) {
            addCriterion("additional_function_ids not in", values, "additionalFunctionIds");
            return (Criteria) this;
        }

        public Criteria andAdditionalFunctionIdsBetween(String value1, String value2) {
            addCriterion("additional_function_ids between", value1, value2, "additionalFunctionIds");
            return (Criteria) this;
        }

        public Criteria andAdditionalFunctionIdsNotBetween(String value1, String value2) {
            addCriterion("additional_function_ids not between", value1, value2, "additionalFunctionIds");
            return (Criteria) this;
        }

        public Criteria andFixedNoIsNull() {
            addCriterion("fixed_no is null");
            return (Criteria) this;
        }

        public Criteria andFixedNoIsNotNull() {
            addCriterion("fixed_no is not null");
            return (Criteria) this;
        }

        public Criteria andFixedNoEqualTo(Integer value) {
            addCriterion("fixed_no =", value, "fixedNo");
            return (Criteria) this;
        }

        public Criteria andFixedNoNotEqualTo(Integer value) {
            addCriterion("fixed_no <>", value, "fixedNo");
            return (Criteria) this;
        }

        public Criteria andFixedNoGreaterThan(Integer value) {
            addCriterion("fixed_no >", value, "fixedNo");
            return (Criteria) this;
        }

        public Criteria andFixedNoGreaterThanOrEqualTo(Integer value) {
            addCriterion("fixed_no >=", value, "fixedNo");
            return (Criteria) this;
        }

        public Criteria andFixedNoLessThan(Integer value) {
            addCriterion("fixed_no <", value, "fixedNo");
            return (Criteria) this;
        }

        public Criteria andFixedNoLessThanOrEqualTo(Integer value) {
            addCriterion("fixed_no <=", value, "fixedNo");
            return (Criteria) this;
        }

        public Criteria andFixedNoIn(List<Integer> values) {
            addCriterion("fixed_no in", values, "fixedNo");
            return (Criteria) this;
        }

        public Criteria andFixedNoNotIn(List<Integer> values) {
            addCriterion("fixed_no not in", values, "fixedNo");
            return (Criteria) this;
        }

        public Criteria andFixedNoBetween(Integer value1, Integer value2) {
            addCriterion("fixed_no between", value1, value2, "fixedNo");
            return (Criteria) this;
        }

        public Criteria andFixedNoNotBetween(Integer value1, Integer value2) {
            addCriterion("fixed_no not between", value1, value2, "fixedNo");
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

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLikeList(List<String> values) {
            addCriterion("name like", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andLogisticsLabelIsNull() {
            addCriterion("logistics_label is null");
            return (Criteria) this;
        }

        public Criteria andLogisticsLabelIsNotNull() {
            addCriterion("logistics_label is not null");
            return (Criteria) this;
        }

        public Criteria andLogisticsLabelEqualTo(String value) {
            addCriterion("logistics_label =", value, "logisticsLabel");
            return (Criteria) this;
        }

        public Criteria andLogisticsLabelNotEqualTo(String value) {
            addCriterion("logistics_label <>", value, "logisticsLabel");
            return (Criteria) this;
        }

        public Criteria andLogisticsLabelGreaterThan(String value) {
            addCriterion("logistics_label >", value, "logisticsLabel");
            return (Criteria) this;
        }

        public Criteria andLogisticsLabelGreaterThanOrEqualTo(String value) {
            addCriterion("logistics_label >=", value, "logisticsLabel");
            return (Criteria) this;
        }

        public Criteria andLogisticsLabelLessThan(String value) {
            addCriterion("logistics_label <", value, "logisticsLabel");
            return (Criteria) this;
        }

        public Criteria andLogisticsLabelLessThanOrEqualTo(String value) {
            addCriterion("logistics_label <=", value, "logisticsLabel");
            return (Criteria) this;
        }

        public Criteria andLogisticsLabelLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("logistics_label like", value, "logisticsLabel");
            return (Criteria) this;
        }

        public Criteria andLogisticsLabelNotLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("logistics_label not like", value, "logisticsLabel");
            return (Criteria) this;
        }

        public Criteria andLogisticsLabelLikeList(List<String> values) {
            addCriterion("logistics_label like", values, "logisticsLabel");
            return (Criteria) this;
        }

        public Criteria andLogisticsLabelIn(List<String> values) {
            addCriterion("logistics_label in", values, "logisticsLabel");
            return (Criteria) this;
        }

        public Criteria andLogisticsLabelNotIn(List<String> values) {
            addCriterion("logistics_label not in", values, "logisticsLabel");
            return (Criteria) this;
        }

        public Criteria andLogisticsLabelBetween(String value1, String value2) {
            addCriterion("logistics_label between", value1, value2, "logisticsLabel");
            return (Criteria) this;
        }

        public Criteria andLogisticsLabelNotBetween(String value1, String value2) {
            addCriterion("logistics_label not between", value1, value2, "logisticsLabel");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Byte value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Byte value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Byte value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Byte value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Byte value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Byte> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Byte> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Byte value1, Byte value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andCustomerIdIsNull() {
            addCriterion("customer_id is null");
            return (Criteria) this;
        }

        public Criteria andCustomerIdIsNotNull() {
            addCriterion("customer_id is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerIdEqualTo(Integer value) {
            addCriterion("customer_id =", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotEqualTo(Integer value) {
            addCriterion("customer_id <>", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdGreaterThan(Integer value) {
            addCriterion("customer_id >", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("customer_id >=", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdLessThan(Integer value) {
            addCriterion("customer_id <", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdLessThanOrEqualTo(Integer value) {
            addCriterion("customer_id <=", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdIn(List<Integer> values) {
            addCriterion("customer_id in", values, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotIn(List<Integer> values) {
            addCriterion("customer_id not in", values, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdBetween(Integer value1, Integer value2) {
            addCriterion("customer_id between", value1, value2, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("customer_id not between", value1, value2, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIsNull() {
            addCriterion("customer_name is null");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIsNotNull() {
            addCriterion("customer_name is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerNameEqualTo(String value) {
            addCriterion("customer_name =", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotEqualTo(String value) {
            addCriterion("customer_name <>", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameGreaterThan(String value) {
            addCriterion("customer_name >", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameGreaterThanOrEqualTo(String value) {
            addCriterion("customer_name >=", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLessThan(String value) {
            addCriterion("customer_name <", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLessThanOrEqualTo(String value) {
            addCriterion("customer_name <=", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("customer_name like", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("customer_name not like", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLikeList(List<String> values) {
            addCriterion("customer_name like", values, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIn(List<String> values) {
            addCriterion("customer_name in", values, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotIn(List<String> values) {
            addCriterion("customer_name not in", values, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameBetween(String value1, String value2) {
            addCriterion("customer_name between", value1, value2, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotBetween(String value1, String value2) {
            addCriterion("customer_name not between", value1, value2, "customerName");
            return (Criteria) this;
        }

        public Criteria andTruckCustomerIdIsNull() {
            addCriterion("truck_customer_id is null");
            return (Criteria) this;
        }

        public Criteria andTruckCustomerIdIsNotNull() {
            addCriterion("truck_customer_id is not null");
            return (Criteria) this;
        }

        public Criteria andTruckCustomerIdEqualTo(Integer value) {
            addCriterion("truck_customer_id =", value, "truckCustomerId");
            return (Criteria) this;
        }

        public Criteria andTruckCustomerIdNotEqualTo(Integer value) {
            addCriterion("truck_customer_id <>", value, "truckCustomerId");
            return (Criteria) this;
        }

        public Criteria andTruckCustomerIdGreaterThan(Integer value) {
            addCriterion("truck_customer_id >", value, "truckCustomerId");
            return (Criteria) this;
        }

        public Criteria andTruckCustomerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("truck_customer_id >=", value, "truckCustomerId");
            return (Criteria) this;
        }

        public Criteria andTruckCustomerIdLessThan(Integer value) {
            addCriterion("truck_customer_id <", value, "truckCustomerId");
            return (Criteria) this;
        }

        public Criteria andTruckCustomerIdLessThanOrEqualTo(Integer value) {
            addCriterion("truck_customer_id <=", value, "truckCustomerId");
            return (Criteria) this;
        }

        public Criteria andTruckCustomerIdIn(List<Integer> values) {
            addCriterion("truck_customer_id in", values, "truckCustomerId");
            return (Criteria) this;
        }

        public Criteria andTruckCustomerIdNotIn(List<Integer> values) {
            addCriterion("truck_customer_id not in", values, "truckCustomerId");
            return (Criteria) this;
        }

        public Criteria andTruckCustomerIdBetween(Integer value1, Integer value2) {
            addCriterion("truck_customer_id between", value1, value2, "truckCustomerId");
            return (Criteria) this;
        }

        public Criteria andTruckCustomerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("truck_customer_id not between", value1, value2, "truckCustomerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdIsNull() {
            addCriterion("manager_id is null");
            return (Criteria) this;
        }

        public Criteria andManagerIdIsNotNull() {
            addCriterion("manager_id is not null");
            return (Criteria) this;
        }

        public Criteria andManagerIdEqualTo(Integer value) {
            addCriterion("manager_id =", value, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdNotEqualTo(Integer value) {
            addCriterion("manager_id <>", value, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdGreaterThan(Integer value) {
            addCriterion("manager_id >", value, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("manager_id >=", value, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdLessThan(Integer value) {
            addCriterion("manager_id <", value, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdLessThanOrEqualTo(Integer value) {
            addCriterion("manager_id <=", value, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdIn(List<Integer> values) {
            addCriterion("manager_id in", values, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdNotIn(List<Integer> values) {
            addCriterion("manager_id not in", values, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdBetween(Integer value1, Integer value2) {
            addCriterion("manager_id between", value1, value2, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("manager_id not between", value1, value2, "managerId");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeIsNull() {
            addCriterion("goods_type is null");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeIsNotNull() {
            addCriterion("goods_type is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeEqualTo(String value) {
            addCriterion("goods_type =", value, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeNotEqualTo(String value) {
            addCriterion("goods_type <>", value, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeGreaterThan(String value) {
            addCriterion("goods_type >", value, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeGreaterThanOrEqualTo(String value) {
            addCriterion("goods_type >=", value, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeLessThan(String value) {
            addCriterion("goods_type <", value, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeLessThanOrEqualTo(String value) {
            addCriterion("goods_type <=", value, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("goods_type like", value, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeNotLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("goods_type not like", value, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeLikeList(List<String> values) {
            addCriterion("goods_type like", values, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeIn(List<String> values) {
            addCriterion("goods_type in", values, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeNotIn(List<String> values) {
            addCriterion("goods_type not in", values, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeBetween(String value1, String value2) {
            addCriterion("goods_type between", value1, value2, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeNotBetween(String value1, String value2) {
            addCriterion("goods_type not between", value1, value2, "goodsType");
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

        public Criteria andIsReceivableFirstIsNull() {
            addCriterion("is_receivable_first is null");
            return (Criteria) this;
        }

        public Criteria andIsReceivableFirstIsNotNull() {
            addCriterion("is_receivable_first is not null");
            return (Criteria) this;
        }

        public Criteria andIsReceivableFirstEqualTo(Boolean value) {
            addCriterion("is_receivable_first =", value, "isReceivableFirst");
            return (Criteria) this;
        }

        public Criteria andIsReceivableFirstNotEqualTo(Boolean value) {
            addCriterion("is_receivable_first <>", value, "isReceivableFirst");
            return (Criteria) this;
        }

        public Criteria andIsReceivableFirstGreaterThan(Boolean value) {
            addCriterion("is_receivable_first >", value, "isReceivableFirst");
            return (Criteria) this;
        }

        public Criteria andIsReceivableFirstGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_receivable_first >=", value, "isReceivableFirst");
            return (Criteria) this;
        }

        public Criteria andIsReceivableFirstLessThan(Boolean value) {
            addCriterion("is_receivable_first <", value, "isReceivableFirst");
            return (Criteria) this;
        }

        public Criteria andIsReceivableFirstLessThanOrEqualTo(Boolean value) {
            addCriterion("is_receivable_first <=", value, "isReceivableFirst");
            return (Criteria) this;
        }

        public Criteria andIsReceivableFirstIn(List<Boolean> values) {
            addCriterion("is_receivable_first in", values, "isReceivableFirst");
            return (Criteria) this;
        }

        public Criteria andIsReceivableFirstNotIn(List<Boolean> values) {
            addCriterion("is_receivable_first not in", values, "isReceivableFirst");
            return (Criteria) this;
        }

        public Criteria andIsReceivableFirstBetween(Boolean value1, Boolean value2) {
            addCriterion("is_receivable_first between", value1, value2, "isReceivableFirst");
            return (Criteria) this;
        }

        public Criteria andIsReceivableFirstNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_receivable_first not between", value1, value2, "isReceivableFirst");
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

        public Criteria andOnlyLoadCargoIsNull() {
            addCriterion("only_load_cargo is null");
            return (Criteria) this;
        }

        public Criteria andOnlyLoadCargoIsNotNull() {
            addCriterion("only_load_cargo is not null");
            return (Criteria) this;
        }

        public Criteria andOnlyLoadCargoEqualTo(Byte value) {
            addCriterion("only_load_cargo =", value, "onlyLoadCargo");
            return (Criteria) this;
        }

        public Criteria andOnlyLoadCargoNotEqualTo(Byte value) {
            addCriterion("only_load_cargo <>", value, "onlyLoadCargo");
            return (Criteria) this;
        }

        public Criteria andOnlyLoadCargoGreaterThan(Byte value) {
            addCriterion("only_load_cargo >", value, "onlyLoadCargo");
            return (Criteria) this;
        }

        public Criteria andOnlyLoadCargoGreaterThanOrEqualTo(Byte value) {
            addCriterion("only_load_cargo >=", value, "onlyLoadCargo");
            return (Criteria) this;
        }

        public Criteria andOnlyLoadCargoLessThan(Byte value) {
            addCriterion("only_load_cargo <", value, "onlyLoadCargo");
            return (Criteria) this;
        }

        public Criteria andOnlyLoadCargoLessThanOrEqualTo(Byte value) {
            addCriterion("only_load_cargo <=", value, "onlyLoadCargo");
            return (Criteria) this;
        }

        public Criteria andOnlyLoadCargoIn(List<Byte> values) {
            addCriterion("only_load_cargo in", values, "onlyLoadCargo");
            return (Criteria) this;
        }

        public Criteria andOnlyLoadCargoNotIn(List<Byte> values) {
            addCriterion("only_load_cargo not in", values, "onlyLoadCargo");
            return (Criteria) this;
        }

        public Criteria andOnlyLoadCargoBetween(Byte value1, Byte value2) {
            addCriterion("only_load_cargo between", value1, value2, "onlyLoadCargo");
            return (Criteria) this;
        }

        public Criteria andOnlyLoadCargoNotBetween(Byte value1, Byte value2) {
            addCriterion("only_load_cargo not between", value1, value2, "onlyLoadCargo");
            return (Criteria) this;
        }

        public Criteria andNeedDeliveryPointNoteIsNull() {
            addCriterion("need_delivery_point_note is null");
            return (Criteria) this;
        }

        public Criteria andNeedDeliveryPointNoteIsNotNull() {
            addCriterion("need_delivery_point_note is not null");
            return (Criteria) this;
        }

        public Criteria andNeedDeliveryPointNoteEqualTo(Byte value) {
            addCriterion("need_delivery_point_note =", value, "needDeliveryPointNote");
            return (Criteria) this;
        }

        public Criteria andNeedDeliveryPointNoteNotEqualTo(Byte value) {
            addCriterion("need_delivery_point_note <>", value, "needDeliveryPointNote");
            return (Criteria) this;
        }

        public Criteria andNeedDeliveryPointNoteGreaterThan(Byte value) {
            addCriterion("need_delivery_point_note >", value, "needDeliveryPointNote");
            return (Criteria) this;
        }

        public Criteria andNeedDeliveryPointNoteGreaterThanOrEqualTo(Byte value) {
            addCriterion("need_delivery_point_note >=", value, "needDeliveryPointNote");
            return (Criteria) this;
        }

        public Criteria andNeedDeliveryPointNoteLessThan(Byte value) {
            addCriterion("need_delivery_point_note <", value, "needDeliveryPointNote");
            return (Criteria) this;
        }

        public Criteria andNeedDeliveryPointNoteLessThanOrEqualTo(Byte value) {
            addCriterion("need_delivery_point_note <=", value, "needDeliveryPointNote");
            return (Criteria) this;
        }

        public Criteria andNeedDeliveryPointNoteIn(List<Byte> values) {
            addCriterion("need_delivery_point_note in", values, "needDeliveryPointNote");
            return (Criteria) this;
        }

        public Criteria andNeedDeliveryPointNoteNotIn(List<Byte> values) {
            addCriterion("need_delivery_point_note not in", values, "needDeliveryPointNote");
            return (Criteria) this;
        }

        public Criteria andNeedDeliveryPointNoteBetween(Byte value1, Byte value2) {
            addCriterion("need_delivery_point_note between", value1, value2, "needDeliveryPointNote");
            return (Criteria) this;
        }

        public Criteria andNeedDeliveryPointNoteNotBetween(Byte value1, Byte value2) {
            addCriterion("need_delivery_point_note not between", value1, value2, "needDeliveryPointNote");
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

        public Criteria andIsReceiveDailySmsIsNull() {
            addCriterion("is_receive_daily_sms is null");
            return (Criteria) this;
        }

        public Criteria andIsReceiveDailySmsIsNotNull() {
            addCriterion("is_receive_daily_sms is not null");
            return (Criteria) this;
        }

        public Criteria andIsReceiveDailySmsEqualTo(Byte value) {
            addCriterion("is_receive_daily_sms =", value, "isReceiveDailySms");
            return (Criteria) this;
        }

        public Criteria andIsReceiveDailySmsNotEqualTo(Byte value) {
            addCriterion("is_receive_daily_sms <>", value, "isReceiveDailySms");
            return (Criteria) this;
        }

        public Criteria andIsReceiveDailySmsGreaterThan(Byte value) {
            addCriterion("is_receive_daily_sms >", value, "isReceiveDailySms");
            return (Criteria) this;
        }

        public Criteria andIsReceiveDailySmsGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_receive_daily_sms >=", value, "isReceiveDailySms");
            return (Criteria) this;
        }

        public Criteria andIsReceiveDailySmsLessThan(Byte value) {
            addCriterion("is_receive_daily_sms <", value, "isReceiveDailySms");
            return (Criteria) this;
        }

        public Criteria andIsReceiveDailySmsLessThanOrEqualTo(Byte value) {
            addCriterion("is_receive_daily_sms <=", value, "isReceiveDailySms");
            return (Criteria) this;
        }

        public Criteria andIsReceiveDailySmsIn(List<Byte> values) {
            addCriterion("is_receive_daily_sms in", values, "isReceiveDailySms");
            return (Criteria) this;
        }

        public Criteria andIsReceiveDailySmsNotIn(List<Byte> values) {
            addCriterion("is_receive_daily_sms not in", values, "isReceiveDailySms");
            return (Criteria) this;
        }

        public Criteria andIsReceiveDailySmsBetween(Byte value1, Byte value2) {
            addCriterion("is_receive_daily_sms between", value1, value2, "isReceiveDailySms");
            return (Criteria) this;
        }

        public Criteria andIsReceiveDailySmsNotBetween(Byte value1, Byte value2) {
            addCriterion("is_receive_daily_sms not between", value1, value2, "isReceiveDailySms");
            return (Criteria) this;
        }

        public Criteria andProfitRateIsNull() {
            addCriterion("profit_rate is null");
            return (Criteria) this;
        }

        public Criteria andProfitRateIsNotNull() {
            addCriterion("profit_rate is not null");
            return (Criteria) this;
        }

        public Criteria andProfitRateEqualTo(BigDecimal value) {
            addCriterion("profit_rate =", value, "profitRate");
            return (Criteria) this;
        }

        public Criteria andProfitRateNotEqualTo(BigDecimal value) {
            addCriterion("profit_rate <>", value, "profitRate");
            return (Criteria) this;
        }

        public Criteria andProfitRateGreaterThan(BigDecimal value) {
            addCriterion("profit_rate >", value, "profitRate");
            return (Criteria) this;
        }

        public Criteria andProfitRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("profit_rate >=", value, "profitRate");
            return (Criteria) this;
        }

        public Criteria andProfitRateLessThan(BigDecimal value) {
            addCriterion("profit_rate <", value, "profitRate");
            return (Criteria) this;
        }

        public Criteria andProfitRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("profit_rate <=", value, "profitRate");
            return (Criteria) this;
        }

        public Criteria andProfitRateIn(List<BigDecimal> values) {
            addCriterion("profit_rate in", values, "profitRate");
            return (Criteria) this;
        }

        public Criteria andProfitRateNotIn(List<BigDecimal> values) {
            addCriterion("profit_rate not in", values, "profitRate");
            return (Criteria) this;
        }

        public Criteria andProfitRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("profit_rate between", value1, value2, "profitRate");
            return (Criteria) this;
        }

        public Criteria andProfitRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("profit_rate not between", value1, value2, "profitRate");
            return (Criteria) this;
        }

        public Criteria andBillPeriodIsNull() {
            addCriterion("bill_period is null");
            return (Criteria) this;
        }

        public Criteria andBillPeriodIsNotNull() {
            addCriterion("bill_period is not null");
            return (Criteria) this;
        }

        public Criteria andBillPeriodEqualTo(Integer value) {
            addCriterion("bill_period =", value, "billPeriod");
            return (Criteria) this;
        }

        public Criteria andBillPeriodNotEqualTo(Integer value) {
            addCriterion("bill_period <>", value, "billPeriod");
            return (Criteria) this;
        }

        public Criteria andBillPeriodGreaterThan(Integer value) {
            addCriterion("bill_period >", value, "billPeriod");
            return (Criteria) this;
        }

        public Criteria andBillPeriodGreaterThanOrEqualTo(Integer value) {
            addCriterion("bill_period >=", value, "billPeriod");
            return (Criteria) this;
        }

        public Criteria andBillPeriodLessThan(Integer value) {
            addCriterion("bill_period <", value, "billPeriod");
            return (Criteria) this;
        }

        public Criteria andBillPeriodLessThanOrEqualTo(Integer value) {
            addCriterion("bill_period <=", value, "billPeriod");
            return (Criteria) this;
        }

        public Criteria andBillPeriodIn(List<Integer> values) {
            addCriterion("bill_period in", values, "billPeriod");
            return (Criteria) this;
        }

        public Criteria andBillPeriodNotIn(List<Integer> values) {
            addCriterion("bill_period not in", values, "billPeriod");
            return (Criteria) this;
        }

        public Criteria andBillPeriodBetween(Integer value1, Integer value2) {
            addCriterion("bill_period between", value1, value2, "billPeriod");
            return (Criteria) this;
        }

        public Criteria andBillPeriodNotBetween(Integer value1, Integer value2) {
            addCriterion("bill_period not between", value1, value2, "billPeriod");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        private ProjectExample example;

        protected Criteria(ProjectExample example) {
            super();
            this.example = example;
        }

        public ProjectExample example() {
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