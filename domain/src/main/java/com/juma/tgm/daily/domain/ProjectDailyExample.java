package com.juma.tgm.daily.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ProjectDailyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int startOffSet = -1;

    protected int size = -1;

    public ProjectDailyExample() {
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

    public ProjectDailyExample orderBy(String ... orderByClauses) {
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

    public ProjectDailyExample limit(int pageNo, int pageSize) {
        this.size = pageSize;
        this.startOffSet = (pageNo - 1) * pageSize;
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

        public Criteria andProjectDailyIdIsNull() {
            addCriterion("project_daily_id is null");
            return (Criteria) this;
        }

        public Criteria andProjectDailyIdIsNotNull() {
            addCriterion("project_daily_id is not null");
            return (Criteria) this;
        }

        public Criteria andProjectDailyIdEqualTo(Integer value) {
            addCriterion("project_daily_id =", value, "projectDailyId");
            return (Criteria) this;
        }

        public Criteria andProjectDailyIdNotEqualTo(Integer value) {
            addCriterion("project_daily_id <>", value, "projectDailyId");
            return (Criteria) this;
        }

        public Criteria andProjectDailyIdGreaterThan(Integer value) {
            addCriterion("project_daily_id >", value, "projectDailyId");
            return (Criteria) this;
        }

        public Criteria andProjectDailyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("project_daily_id >=", value, "projectDailyId");
            return (Criteria) this;
        }

        public Criteria andProjectDailyIdLessThan(Integer value) {
            addCriterion("project_daily_id <", value, "projectDailyId");
            return (Criteria) this;
        }

        public Criteria andProjectDailyIdLessThanOrEqualTo(Integer value) {
            addCriterion("project_daily_id <=", value, "projectDailyId");
            return (Criteria) this;
        }

        public Criteria andProjectDailyIdIn(List<Integer> values) {
            addCriterion("project_daily_id in", values, "projectDailyId");
            return (Criteria) this;
        }

        public Criteria andProjectDailyIdNotIn(List<Integer> values) {
            addCriterion("project_daily_id not in", values, "projectDailyId");
            return (Criteria) this;
        }

        public Criteria andProjectDailyIdBetween(Integer value1, Integer value2) {
            addCriterion("project_daily_id between", value1, value2, "projectDailyId");
            return (Criteria) this;
        }

        public Criteria andProjectDailyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("project_daily_id not between", value1, value2, "projectDailyId");
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

        public Criteria andProjectDailyNoIsNull() {
            addCriterion("project_daily_no is null");
            return (Criteria) this;
        }

        public Criteria andProjectDailyNoIsNotNull() {
            addCriterion("project_daily_no is not null");
            return (Criteria) this;
        }

        public Criteria andProjectDailyNoEqualTo(String value) {
            addCriterion("project_daily_no =", value, "projectDailyNo");
            return (Criteria) this;
        }

        public Criteria andProjectDailyNoNotEqualTo(String value) {
            addCriterion("project_daily_no <>", value, "projectDailyNo");
            return (Criteria) this;
        }

        public Criteria andProjectDailyNoGreaterThan(String value) {
            addCriterion("project_daily_no >", value, "projectDailyNo");
            return (Criteria) this;
        }

        public Criteria andProjectDailyNoGreaterThanOrEqualTo(String value) {
            addCriterion("project_daily_no >=", value, "projectDailyNo");
            return (Criteria) this;
        }

        public Criteria andProjectDailyNoLessThan(String value) {
            addCriterion("project_daily_no <", value, "projectDailyNo");
            return (Criteria) this;
        }

        public Criteria andProjectDailyNoLessThanOrEqualTo(String value) {
            addCriterion("project_daily_no <=", value, "projectDailyNo");
            return (Criteria) this;
        }

        public Criteria andProjectDailyNoLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("project_daily_no like", value, "projectDailyNo");
            return (Criteria) this;
        }

        public Criteria andProjectDailyNoNotLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("project_daily_no not like", value, "projectDailyNo");
            return (Criteria) this;
        }

        public Criteria andProjectDailyNoLikeList(List<String> values) {
            addCriterion("project_daily_no like", values, "projectDailyNo");
            return (Criteria) this;
        }

        public Criteria andProjectDailyNoIn(List<String> values) {
            addCriterion("project_daily_no in", values, "projectDailyNo");
            return (Criteria) this;
        }

        public Criteria andProjectDailyNoNotIn(List<String> values) {
            addCriterion("project_daily_no not in", values, "projectDailyNo");
            return (Criteria) this;
        }

        public Criteria andProjectDailyNoBetween(String value1, String value2) {
            addCriterion("project_daily_no between", value1, value2, "projectDailyNo");
            return (Criteria) this;
        }

        public Criteria andProjectDailyNoNotBetween(String value1, String value2) {
            addCriterion("project_daily_no not between", value1, value2, "projectDailyNo");
            return (Criteria) this;
        }

        public Criteria andProjectDailyDateIsNull() {
            addCriterion("project_daily_date is null");
            return (Criteria) this;
        }

        public Criteria andProjectDailyDateIsNotNull() {
            addCriterion("project_daily_date is not null");
            return (Criteria) this;
        }

        public Criteria andProjectDailyDateEqualTo(Date value) {
            addCriterionForJDBCDate("project_daily_date =", value, "projectDailyDate");
            return (Criteria) this;
        }

        public Criteria andProjectDailyDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("project_daily_date <>", value, "projectDailyDate");
            return (Criteria) this;
        }

        public Criteria andProjectDailyDateGreaterThan(Date value) {
            addCriterionForJDBCDate("project_daily_date >", value, "projectDailyDate");
            return (Criteria) this;
        }

        public Criteria andProjectDailyDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("project_daily_date >=", value, "projectDailyDate");
            return (Criteria) this;
        }

        public Criteria andProjectDailyDateLessThan(Date value) {
            addCriterionForJDBCDate("project_daily_date <", value, "projectDailyDate");
            return (Criteria) this;
        }

        public Criteria andProjectDailyDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("project_daily_date <=", value, "projectDailyDate");
            return (Criteria) this;
        }

        public Criteria andProjectDailyDateIn(List<Date> values) {
            addCriterionForJDBCDate("project_daily_date in", values, "projectDailyDate");
            return (Criteria) this;
        }

        public Criteria andProjectDailyDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("project_daily_date not in", values, "projectDailyDate");
            return (Criteria) this;
        }

        public Criteria andProjectDailyDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("project_daily_date between", value1, value2, "projectDailyDate");
            return (Criteria) this;
        }

        public Criteria andProjectDailyDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("project_daily_date not between", value1, value2, "projectDailyDate");
            return (Criteria) this;
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

        public Criteria andDailyStatusIsNull() {
            addCriterion("daily_status is null");
            return (Criteria) this;
        }

        public Criteria andDailyStatusIsNotNull() {
            addCriterion("daily_status is not null");
            return (Criteria) this;
        }

        public Criteria andDailyStatusEqualTo(Integer value) {
            addCriterion("daily_status =", value, "dailyStatus");
            return (Criteria) this;
        }

        public Criteria andDailyStatusNotEqualTo(Integer value) {
            addCriterion("daily_status <>", value, "dailyStatus");
            return (Criteria) this;
        }

        public Criteria andDailyStatusGreaterThan(Integer value) {
            addCriterion("daily_status >", value, "dailyStatus");
            return (Criteria) this;
        }

        public Criteria andDailyStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("daily_status >=", value, "dailyStatus");
            return (Criteria) this;
        }

        public Criteria andDailyStatusLessThan(Integer value) {
            addCriterion("daily_status <", value, "dailyStatus");
            return (Criteria) this;
        }

        public Criteria andDailyStatusLessThanOrEqualTo(Integer value) {
            addCriterion("daily_status <=", value, "dailyStatus");
            return (Criteria) this;
        }

        public Criteria andDailyStatusIn(List<Integer> values) {
            addCriterion("daily_status in", values, "dailyStatus");
            return (Criteria) this;
        }

        public Criteria andDailyStatusNotIn(List<Integer> values) {
            addCriterion("daily_status not in", values, "dailyStatus");
            return (Criteria) this;
        }

        public Criteria andDailyStatusBetween(Integer value1, Integer value2) {
            addCriterion("daily_status between", value1, value2, "dailyStatus");
            return (Criteria) this;
        }

        public Criteria andDailyStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("daily_status not between", value1, value2, "dailyStatus");
            return (Criteria) this;
        }

        public Criteria andStandingBookStatusIsNull() {
            addCriterion("standing_book_status is null");
            return (Criteria) this;
        }

        public Criteria andStandingBookStatusIsNotNull() {
            addCriterion("standing_book_status is not null");
            return (Criteria) this;
        }

        public Criteria andStandingBookStatusEqualTo(Integer value) {
            addCriterion("standing_book_status =", value, "standingBookStatus");
            return (Criteria) this;
        }

        public Criteria andStandingBookStatusNotEqualTo(Integer value) {
            addCriterion("standing_book_status <>", value, "standingBookStatus");
            return (Criteria) this;
        }

        public Criteria andStandingBookStatusGreaterThan(Integer value) {
            addCriterion("standing_book_status >", value, "standingBookStatus");
            return (Criteria) this;
        }

        public Criteria andStandingBookStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("standing_book_status >=", value, "standingBookStatus");
            return (Criteria) this;
        }

        public Criteria andStandingBookStatusLessThan(Integer value) {
            addCriterion("standing_book_status <", value, "standingBookStatus");
            return (Criteria) this;
        }

        public Criteria andStandingBookStatusLessThanOrEqualTo(Integer value) {
            addCriterion("standing_book_status <=", value, "standingBookStatus");
            return (Criteria) this;
        }

        public Criteria andStandingBookStatusIn(List<Integer> values) {
            addCriterion("standing_book_status in", values, "standingBookStatus");
            return (Criteria) this;
        }

        public Criteria andStandingBookStatusNotIn(List<Integer> values) {
            addCriterion("standing_book_status not in", values, "standingBookStatus");
            return (Criteria) this;
        }

        public Criteria andStandingBookStatusBetween(Integer value1, Integer value2) {
            addCriterion("standing_book_status between", value1, value2, "standingBookStatus");
            return (Criteria) this;
        }

        public Criteria andStandingBookStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("standing_book_status not between", value1, value2, "standingBookStatus");
            return (Criteria) this;
        }

        public Criteria andFreightStatusIsNull() {
            addCriterion("freight_status is null");
            return (Criteria) this;
        }

        public Criteria andFreightStatusIsNotNull() {
            addCriterion("freight_status is not null");
            return (Criteria) this;
        }

        public Criteria andFreightStatusEqualTo(Integer value) {
            addCriterion("freight_status =", value, "freightStatus");
            return (Criteria) this;
        }

        public Criteria andFreightStatusNotEqualTo(Integer value) {
            addCriterion("freight_status <>", value, "freightStatus");
            return (Criteria) this;
        }

        public Criteria andFreightStatusGreaterThan(Integer value) {
            addCriterion("freight_status >", value, "freightStatus");
            return (Criteria) this;
        }

        public Criteria andFreightStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("freight_status >=", value, "freightStatus");
            return (Criteria) this;
        }

        public Criteria andFreightStatusLessThan(Integer value) {
            addCriterion("freight_status <", value, "freightStatus");
            return (Criteria) this;
        }

        public Criteria andFreightStatusLessThanOrEqualTo(Integer value) {
            addCriterion("freight_status <=", value, "freightStatus");
            return (Criteria) this;
        }

        public Criteria andFreightStatusIn(List<Integer> values) {
            addCriterion("freight_status in", values, "freightStatus");
            return (Criteria) this;
        }

        public Criteria andFreightStatusNotIn(List<Integer> values) {
            addCriterion("freight_status not in", values, "freightStatus");
            return (Criteria) this;
        }

        public Criteria andFreightStatusBetween(Integer value1, Integer value2) {
            addCriterion("freight_status between", value1, value2, "freightStatus");
            return (Criteria) this;
        }

        public Criteria andFreightStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("freight_status not between", value1, value2, "freightStatus");
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
        private ProjectDailyExample example;

        protected Criteria(ProjectDailyExample example) {
            super();
            this.example = example;
        }

        public ProjectDailyExample example() {
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