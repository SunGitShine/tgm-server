package com.juma.tgm.vendor.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VendorMappingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int startOffSet = -1;

    protected int size = -1;

    public VendorMappingExample() {
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

        public Criteria andVendorMappingIdIsNull() {
            addCriterion("vendor_mapping_id is null");
            return (Criteria) this;
        }

        public Criteria andVendorMappingIdIsNotNull() {
            addCriterion("vendor_mapping_id is not null");
            return (Criteria) this;
        }

        public Criteria andVendorMappingIdEqualTo(Integer value) {
            addCriterion("vendor_mapping_id =", value, "vendorMappingId");
            return (Criteria) this;
        }

        public Criteria andVendorMappingIdNotEqualTo(Integer value) {
            addCriterion("vendor_mapping_id <>", value, "vendorMappingId");
            return (Criteria) this;
        }

        public Criteria andVendorMappingIdGreaterThan(Integer value) {
            addCriterion("vendor_mapping_id >", value, "vendorMappingId");
            return (Criteria) this;
        }

        public Criteria andVendorMappingIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("vendor_mapping_id >=", value, "vendorMappingId");
            return (Criteria) this;
        }

        public Criteria andVendorMappingIdLessThan(Integer value) {
            addCriterion("vendor_mapping_id <", value, "vendorMappingId");
            return (Criteria) this;
        }

        public Criteria andVendorMappingIdLessThanOrEqualTo(Integer value) {
            addCriterion("vendor_mapping_id <=", value, "vendorMappingId");
            return (Criteria) this;
        }

        public Criteria andVendorMappingIdIn(List<Integer> values) {
            addCriterion("vendor_mapping_id in", values, "vendorMappingId");
            return (Criteria) this;
        }

        public Criteria andVendorMappingIdNotIn(List<Integer> values) {
            addCriterion("vendor_mapping_id not in", values, "vendorMappingId");
            return (Criteria) this;
        }

        public Criteria andVendorMappingIdBetween(Integer value1, Integer value2) {
            addCriterion("vendor_mapping_id between", value1, value2, "vendorMappingId");
            return (Criteria) this;
        }

        public Criteria andVendorMappingIdNotBetween(Integer value1, Integer value2) {
            addCriterion("vendor_mapping_id not between", value1, value2, "vendorMappingId");
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

        public Criteria andVendorTenantIdIsNull() {
            addCriterion("vendor_tenant_id is null");
            return (Criteria) this;
        }

        public Criteria andVendorTenantIdIsNotNull() {
            addCriterion("vendor_tenant_id is not null");
            return (Criteria) this;
        }

        public Criteria andVendorTenantIdEqualTo(Integer value) {
            addCriterion("vendor_tenant_id =", value, "vendorTenantId");
            return (Criteria) this;
        }

        public Criteria andVendorTenantIdNotEqualTo(Integer value) {
            addCriterion("vendor_tenant_id <>", value, "vendorTenantId");
            return (Criteria) this;
        }

        public Criteria andVendorTenantIdGreaterThan(Integer value) {
            addCriterion("vendor_tenant_id >", value, "vendorTenantId");
            return (Criteria) this;
        }

        public Criteria andVendorTenantIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("vendor_tenant_id >=", value, "vendorTenantId");
            return (Criteria) this;
        }

        public Criteria andVendorTenantIdLessThan(Integer value) {
            addCriterion("vendor_tenant_id <", value, "vendorTenantId");
            return (Criteria) this;
        }

        public Criteria andVendorTenantIdLessThanOrEqualTo(Integer value) {
            addCriterion("vendor_tenant_id <=", value, "vendorTenantId");
            return (Criteria) this;
        }

        public Criteria andVendorTenantIdIn(List<Integer> values) {
            addCriterion("vendor_tenant_id in", values, "vendorTenantId");
            return (Criteria) this;
        }

        public Criteria andVendorTenantIdNotIn(List<Integer> values) {
            addCriterion("vendor_tenant_id not in", values, "vendorTenantId");
            return (Criteria) this;
        }

        public Criteria andVendorTenantIdBetween(Integer value1, Integer value2) {
            addCriterion("vendor_tenant_id between", value1, value2, "vendorTenantId");
            return (Criteria) this;
        }

        public Criteria andVendorTenantIdNotBetween(Integer value1, Integer value2) {
            addCriterion("vendor_tenant_id not between", value1, value2, "vendorTenantId");
            return (Criteria) this;
        }

        public Criteria andVendorNameIsNull() {
            addCriterion("vendor_name is null");
            return (Criteria) this;
        }

        public Criteria andVendorNameIsNotNull() {
            addCriterion("vendor_name is not null");
            return (Criteria) this;
        }

        public Criteria andVendorNameEqualTo(String value) {
            addCriterion("vendor_name =", value, "vendorName");
            return (Criteria) this;
        }

        public Criteria andVendorNameNotEqualTo(String value) {
            addCriterion("vendor_name <>", value, "vendorName");
            return (Criteria) this;
        }

        public Criteria andVendorNameGreaterThan(String value) {
            addCriterion("vendor_name >", value, "vendorName");
            return (Criteria) this;
        }

        public Criteria andVendorNameGreaterThanOrEqualTo(String value) {
            addCriterion("vendor_name >=", value, "vendorName");
            return (Criteria) this;
        }

        public Criteria andVendorNameLessThan(String value) {
            addCriterion("vendor_name <", value, "vendorName");
            return (Criteria) this;
        }

        public Criteria andVendorNameLessThanOrEqualTo(String value) {
            addCriterion("vendor_name <=", value, "vendorName");
            return (Criteria) this;
        }

        public Criteria andVendorNameLike(String value) {
            addCriterion("vendor_name like", value, "vendorName");
            return (Criteria) this;
        }

        public Criteria andVendorNameNotLike(String value) {
            addCriterion("vendor_name not like", value, "vendorName");
            return (Criteria) this;
        }

        public Criteria andVendorNameIn(List<String> values) {
            addCriterion("vendor_name in", values, "vendorName");
            return (Criteria) this;
        }

        public Criteria andVendorNameNotIn(List<String> values) {
            addCriterion("vendor_name not in", values, "vendorName");
            return (Criteria) this;
        }

        public Criteria andVendorNameBetween(String value1, String value2) {
            addCriterion("vendor_name between", value1, value2, "vendorName");
            return (Criteria) this;
        }

        public Criteria andVendorNameNotBetween(String value1, String value2) {
            addCriterion("vendor_name not between", value1, value2, "vendorName");
            return (Criteria) this;
        }

        public Criteria andVendorCustomerIdIsNull() {
            addCriterion("vendor_customer_id is null");
            return (Criteria) this;
        }

        public Criteria andVendorCustomerIdIsNotNull() {
            addCriterion("vendor_customer_id is not null");
            return (Criteria) this;
        }

        public Criteria andVendorCustomerIdEqualTo(Integer value) {
            addCriterion("vendor_customer_id =", value, "vendorCustomerId");
            return (Criteria) this;
        }

        public Criteria andVendorCustomerIdNotEqualTo(Integer value) {
            addCriterion("vendor_customer_id <>", value, "vendorCustomerId");
            return (Criteria) this;
        }

        public Criteria andVendorCustomerIdGreaterThan(Integer value) {
            addCriterion("vendor_customer_id >", value, "vendorCustomerId");
            return (Criteria) this;
        }

        public Criteria andVendorCustomerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("vendor_customer_id >=", value, "vendorCustomerId");
            return (Criteria) this;
        }

        public Criteria andVendorCustomerIdLessThan(Integer value) {
            addCriterion("vendor_customer_id <", value, "vendorCustomerId");
            return (Criteria) this;
        }

        public Criteria andVendorCustomerIdLessThanOrEqualTo(Integer value) {
            addCriterion("vendor_customer_id <=", value, "vendorCustomerId");
            return (Criteria) this;
        }

        public Criteria andVendorCustomerIdIn(List<Integer> values) {
            addCriterion("vendor_customer_id in", values, "vendorCustomerId");
            return (Criteria) this;
        }

        public Criteria andVendorCustomerIdNotIn(List<Integer> values) {
            addCriterion("vendor_customer_id not in", values, "vendorCustomerId");
            return (Criteria) this;
        }

        public Criteria andVendorCustomerIdBetween(Integer value1, Integer value2) {
            addCriterion("vendor_customer_id between", value1, value2, "vendorCustomerId");
            return (Criteria) this;
        }

        public Criteria andVendorCustomerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("vendor_customer_id not between", value1, value2, "vendorCustomerId");
            return (Criteria) this;
        }

        public Criteria andCustomerTenantIdIsNull() {
            addCriterion("customer_tenant_id is null");
            return (Criteria) this;
        }

        public Criteria andCustomerTenantIdIsNotNull() {
            addCriterion("customer_tenant_id is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerTenantIdEqualTo(Integer value) {
            addCriterion("customer_tenant_id =", value, "customerTenantId");
            return (Criteria) this;
        }

        public Criteria andCustomerTenantIdNotEqualTo(Integer value) {
            addCriterion("customer_tenant_id <>", value, "customerTenantId");
            return (Criteria) this;
        }

        public Criteria andCustomerTenantIdGreaterThan(Integer value) {
            addCriterion("customer_tenant_id >", value, "customerTenantId");
            return (Criteria) this;
        }

        public Criteria andCustomerTenantIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("customer_tenant_id >=", value, "customerTenantId");
            return (Criteria) this;
        }

        public Criteria andCustomerTenantIdLessThan(Integer value) {
            addCriterion("customer_tenant_id <", value, "customerTenantId");
            return (Criteria) this;
        }

        public Criteria andCustomerTenantIdLessThanOrEqualTo(Integer value) {
            addCriterion("customer_tenant_id <=", value, "customerTenantId");
            return (Criteria) this;
        }

        public Criteria andCustomerTenantIdIn(List<Integer> values) {
            addCriterion("customer_tenant_id in", values, "customerTenantId");
            return (Criteria) this;
        }

        public Criteria andCustomerTenantIdNotIn(List<Integer> values) {
            addCriterion("customer_tenant_id not in", values, "customerTenantId");
            return (Criteria) this;
        }

        public Criteria andCustomerTenantIdBetween(Integer value1, Integer value2) {
            addCriterion("customer_tenant_id between", value1, value2, "customerTenantId");
            return (Criteria) this;
        }

        public Criteria andCustomerTenantIdNotBetween(Integer value1, Integer value2) {
            addCriterion("customer_tenant_id not between", value1, value2, "customerTenantId");
            return (Criteria) this;
        }

        public Criteria andVendorCustomerNameIsNull() {
            addCriterion("vendor_customer_name is null");
            return (Criteria) this;
        }

        public Criteria andVendorCustomerNameIsNotNull() {
            addCriterion("vendor_customer_name is not null");
            return (Criteria) this;
        }

        public Criteria andVendorCustomerNameEqualTo(String value) {
            addCriterion("vendor_customer_name =", value, "vendorCustomerName");
            return (Criteria) this;
        }

        public Criteria andVendorCustomerNameNotEqualTo(String value) {
            addCriterion("vendor_customer_name <>", value, "vendorCustomerName");
            return (Criteria) this;
        }

        public Criteria andVendorCustomerNameGreaterThan(String value) {
            addCriterion("vendor_customer_name >", value, "vendorCustomerName");
            return (Criteria) this;
        }

        public Criteria andVendorCustomerNameGreaterThanOrEqualTo(String value) {
            addCriterion("vendor_customer_name >=", value, "vendorCustomerName");
            return (Criteria) this;
        }

        public Criteria andVendorCustomerNameLessThan(String value) {
            addCriterion("vendor_customer_name <", value, "vendorCustomerName");
            return (Criteria) this;
        }

        public Criteria andVendorCustomerNameLessThanOrEqualTo(String value) {
            addCriterion("vendor_customer_name <=", value, "vendorCustomerName");
            return (Criteria) this;
        }

        public Criteria andVendorCustomerNameLike(String value) {
            addCriterion("vendor_customer_name like", value, "vendorCustomerName");
            return (Criteria) this;
        }

        public Criteria andVendorCustomerNameNotLike(String value) {
            addCriterion("vendor_customer_name not like", value, "vendorCustomerName");
            return (Criteria) this;
        }

        public Criteria andVendorCustomerNameIn(List<String> values) {
            addCriterion("vendor_customer_name in", values, "vendorCustomerName");
            return (Criteria) this;
        }

        public Criteria andVendorCustomerNameNotIn(List<String> values) {
            addCriterion("vendor_customer_name not in", values, "vendorCustomerName");
            return (Criteria) this;
        }

        public Criteria andVendorCustomerNameBetween(String value1, String value2) {
            addCriterion("vendor_customer_name between", value1, value2, "vendorCustomerName");
            return (Criteria) this;
        }

        public Criteria andVendorCustomerNameNotBetween(String value1, String value2) {
            addCriterion("vendor_customer_name not between", value1, value2, "vendorCustomerName");
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

        public Criteria andDepartmentCodeIsNull() {
            addCriterion("department_code is null");
            return (Criteria) this;
        }

        public Criteria andDepartmentCodeIsNotNull() {
            addCriterion("department_code is not null");
            return (Criteria) this;
        }

        public Criteria andDepartmentCodeEqualTo(String value) {
            addCriterion("department_code =", value, "departmentCode");
            return (Criteria) this;
        }

        public Criteria andDepartmentCodeNotEqualTo(String value) {
            addCriterion("department_code <>", value, "departmentCode");
            return (Criteria) this;
        }

        public Criteria andDepartmentCodeGreaterThan(String value) {
            addCriterion("department_code >", value, "departmentCode");
            return (Criteria) this;
        }

        public Criteria andDepartmentCodeGreaterThanOrEqualTo(String value) {
            addCriterion("department_code >=", value, "departmentCode");
            return (Criteria) this;
        }

        public Criteria andDepartmentCodeLessThan(String value) {
            addCriterion("department_code <", value, "departmentCode");
            return (Criteria) this;
        }

        public Criteria andDepartmentCodeLessThanOrEqualTo(String value) {
            addCriterion("department_code <=", value, "departmentCode");
            return (Criteria) this;
        }

        public Criteria andDepartmentCodeLike(String value) {
            addCriterion("department_code like", value, "departmentCode");
            return (Criteria) this;
        }

        public Criteria andDepartmentCodeNotLike(String value) {
            addCriterion("department_code not like", value, "departmentCode");
            return (Criteria) this;
        }

        public Criteria andDepartmentCodeIn(List<String> values) {
            addCriterion("department_code in", values, "departmentCode");
            return (Criteria) this;
        }

        public Criteria andDepartmentCodeNotIn(List<String> values) {
            addCriterion("department_code not in", values, "departmentCode");
            return (Criteria) this;
        }

        public Criteria andDepartmentCodeBetween(String value1, String value2) {
            addCriterion("department_code between", value1, value2, "departmentCode");
            return (Criteria) this;
        }

        public Criteria andDepartmentCodeNotBetween(String value1, String value2) {
            addCriterion("department_code not between", value1, value2, "departmentCode");
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

        public Criteria andLastUpdateUserNameIsNull() {
            addCriterion("last_update_user_name is null");
            return (Criteria) this;
        }

        public Criteria andLastUpdateUserNameIsNotNull() {
            addCriterion("last_update_user_name is not null");
            return (Criteria) this;
        }

        public Criteria andLastUpdateUserNameEqualTo(Date value) {
            addCriterion("last_update_user_name =", value, "lastUpdateUserName");
            return (Criteria) this;
        }

        public Criteria andLastUpdateUserNameNotEqualTo(Date value) {
            addCriterion("last_update_user_name <>", value, "lastUpdateUserName");
            return (Criteria) this;
        }

        public Criteria andLastUpdateUserNameGreaterThan(Date value) {
            addCriterion("last_update_user_name >", value, "lastUpdateUserName");
            return (Criteria) this;
        }

        public Criteria andLastUpdateUserNameGreaterThanOrEqualTo(Date value) {
            addCriterion("last_update_user_name >=", value, "lastUpdateUserName");
            return (Criteria) this;
        }

        public Criteria andLastUpdateUserNameLessThan(Date value) {
            addCriterion("last_update_user_name <", value, "lastUpdateUserName");
            return (Criteria) this;
        }

        public Criteria andLastUpdateUserNameLessThanOrEqualTo(Date value) {
            addCriterion("last_update_user_name <=", value, "lastUpdateUserName");
            return (Criteria) this;
        }

        public Criteria andLastUpdateUserNameIn(List<Date> values) {
            addCriterion("last_update_user_name in", values, "lastUpdateUserName");
            return (Criteria) this;
        }

        public Criteria andLastUpdateUserNameNotIn(List<Date> values) {
            addCriterion("last_update_user_name not in", values, "lastUpdateUserName");
            return (Criteria) this;
        }

        public Criteria andLastUpdateUserNameBetween(Date value1, Date value2) {
            addCriterion("last_update_user_name between", value1, value2, "lastUpdateUserName");
            return (Criteria) this;
        }

        public Criteria andLastUpdateUserNameNotBetween(Date value1, Date value2) {
            addCriterion("last_update_user_name not between", value1, value2, "lastUpdateUserName");
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