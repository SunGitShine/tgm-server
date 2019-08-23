package com.juma.tgm.waybill.domain.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WaybillDeliveryAddressExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WaybillDeliveryAddressExample() {
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

        public Criteria andAddressIdIsNull() {
            addCriterion("address_id is null");
            return (Criteria) this;
        }

        public Criteria andAddressIdIsNotNull() {
            addCriterion("address_id is not null");
            return (Criteria) this;
        }

        public Criteria andAddressIdEqualTo(Integer value) {
            addCriterion("address_id =", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdNotEqualTo(Integer value) {
            addCriterion("address_id <>", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdGreaterThan(Integer value) {
            addCriterion("address_id >", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("address_id >=", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdLessThan(Integer value) {
            addCriterion("address_id <", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdLessThanOrEqualTo(Integer value) {
            addCriterion("address_id <=", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdIn(List<Integer> values) {
            addCriterion("address_id in", values, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdNotIn(List<Integer> values) {
            addCriterion("address_id not in", values, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdBetween(Integer value1, Integer value2) {
            addCriterion("address_id between", value1, value2, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdNotBetween(Integer value1, Integer value2) {
            addCriterion("address_id not between", value1, value2, "addressId");
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

        public Criteria andAddressNameIsNull() {
            addCriterion("address_name is null");
            return (Criteria) this;
        }

        public Criteria andAddressNameIsNotNull() {
            addCriterion("address_name is not null");
            return (Criteria) this;
        }

        public Criteria andAddressNameEqualTo(String value) {
            addCriterion("address_name =", value, "addressName");
            return (Criteria) this;
        }

        public Criteria andAddressNameNotEqualTo(String value) {
            addCriterion("address_name <>", value, "addressName");
            return (Criteria) this;
        }

        public Criteria andAddressNameGreaterThan(String value) {
            addCriterion("address_name >", value, "addressName");
            return (Criteria) this;
        }

        public Criteria andAddressNameGreaterThanOrEqualTo(String value) {
            addCriterion("address_name >=", value, "addressName");
            return (Criteria) this;
        }

        public Criteria andAddressNameLessThan(String value) {
            addCriterion("address_name <", value, "addressName");
            return (Criteria) this;
        }

        public Criteria andAddressNameLessThanOrEqualTo(String value) {
            addCriterion("address_name <=", value, "addressName");
            return (Criteria) this;
        }

        public Criteria andAddressNameLike(String value) {
            addCriterion("address_name like", value, "addressName");
            return (Criteria) this;
        }

        public Criteria andAddressNameNotLike(String value) {
            addCriterion("address_name not like", value, "addressName");
            return (Criteria) this;
        }

        public Criteria andAddressNameIn(List<String> values) {
            addCriterion("address_name in", values, "addressName");
            return (Criteria) this;
        }

        public Criteria andAddressNameNotIn(List<String> values) {
            addCriterion("address_name not in", values, "addressName");
            return (Criteria) this;
        }

        public Criteria andAddressNameBetween(String value1, String value2) {
            addCriterion("address_name between", value1, value2, "addressName");
            return (Criteria) this;
        }

        public Criteria andAddressNameNotBetween(String value1, String value2) {
            addCriterion("address_name not between", value1, value2, "addressName");
            return (Criteria) this;
        }

        public Criteria andAddressDetailIsNull() {
            addCriterion("address_detail is null");
            return (Criteria) this;
        }

        public Criteria andAddressDetailIsNotNull() {
            addCriterion("address_detail is not null");
            return (Criteria) this;
        }

        public Criteria andAddressDetailEqualTo(String value) {
            addCriterion("address_detail =", value, "addressDetail");
            return (Criteria) this;
        }

        public Criteria andAddressDetailNotEqualTo(String value) {
            addCriterion("address_detail <>", value, "addressDetail");
            return (Criteria) this;
        }

        public Criteria andAddressDetailGreaterThan(String value) {
            addCriterion("address_detail >", value, "addressDetail");
            return (Criteria) this;
        }

        public Criteria andAddressDetailGreaterThanOrEqualTo(String value) {
            addCriterion("address_detail >=", value, "addressDetail");
            return (Criteria) this;
        }

        public Criteria andAddressDetailLessThan(String value) {
            addCriterion("address_detail <", value, "addressDetail");
            return (Criteria) this;
        }

        public Criteria andAddressDetailLessThanOrEqualTo(String value) {
            addCriterion("address_detail <=", value, "addressDetail");
            return (Criteria) this;
        }

        public Criteria andAddressDetailLike(String value) {
            addCriterion("address_detail like", value, "addressDetail");
            return (Criteria) this;
        }

        public Criteria andAddressDetailNotLike(String value) {
            addCriterion("address_detail not like", value, "addressDetail");
            return (Criteria) this;
        }

        public Criteria andAddressDetailIn(List<String> values) {
            addCriterion("address_detail in", values, "addressDetail");
            return (Criteria) this;
        }

        public Criteria andAddressDetailNotIn(List<String> values) {
            addCriterion("address_detail not in", values, "addressDetail");
            return (Criteria) this;
        }

        public Criteria andAddressDetailBetween(String value1, String value2) {
            addCriterion("address_detail between", value1, value2, "addressDetail");
            return (Criteria) this;
        }

        public Criteria andAddressDetailNotBetween(String value1, String value2) {
            addCriterion("address_detail not between", value1, value2, "addressDetail");
            return (Criteria) this;
        }

        public Criteria andContactNameIsNull() {
            addCriterion("contact_name is null");
            return (Criteria) this;
        }

        public Criteria andContactNameIsNotNull() {
            addCriterion("contact_name is not null");
            return (Criteria) this;
        }

        public Criteria andContactNameEqualTo(String value) {
            addCriterion("contact_name =", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameNotEqualTo(String value) {
            addCriterion("contact_name <>", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameGreaterThan(String value) {
            addCriterion("contact_name >", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameGreaterThanOrEqualTo(String value) {
            addCriterion("contact_name >=", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameLessThan(String value) {
            addCriterion("contact_name <", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameLessThanOrEqualTo(String value) {
            addCriterion("contact_name <=", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameLike(String value) {
            addCriterion("contact_name like", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameNotLike(String value) {
            addCriterion("contact_name not like", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameIn(List<String> values) {
            addCriterion("contact_name in", values, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameNotIn(List<String> values) {
            addCriterion("contact_name not in", values, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameBetween(String value1, String value2) {
            addCriterion("contact_name between", value1, value2, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameNotBetween(String value1, String value2) {
            addCriterion("contact_name not between", value1, value2, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactPhoneIsNull() {
            addCriterion("contact_phone is null");
            return (Criteria) this;
        }

        public Criteria andContactPhoneIsNotNull() {
            addCriterion("contact_phone is not null");
            return (Criteria) this;
        }

        public Criteria andContactPhoneEqualTo(String value) {
            addCriterion("contact_phone =", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneNotEqualTo(String value) {
            addCriterion("contact_phone <>", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneGreaterThan(String value) {
            addCriterion("contact_phone >", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("contact_phone >=", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneLessThan(String value) {
            addCriterion("contact_phone <", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneLessThanOrEqualTo(String value) {
            addCriterion("contact_phone <=", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneLike(String value) {
            addCriterion("contact_phone like", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneNotLike(String value) {
            addCriterion("contact_phone not like", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneIn(List<String> values) {
            addCriterion("contact_phone in", values, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneNotIn(List<String> values) {
            addCriterion("contact_phone not in", values, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneBetween(String value1, String value2) {
            addCriterion("contact_phone between", value1, value2, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneNotBetween(String value1, String value2) {
            addCriterion("contact_phone not between", value1, value2, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andCoordinatesIsNull() {
            addCriterion("coordinates is null");
            return (Criteria) this;
        }

        public Criteria andCoordinatesIsNotNull() {
            addCriterion("coordinates is not null");
            return (Criteria) this;
        }

        public Criteria andCoordinatesEqualTo(String value) {
            addCriterion("coordinates =", value, "coordinates");
            return (Criteria) this;
        }

        public Criteria andCoordinatesNotEqualTo(String value) {
            addCriterion("coordinates <>", value, "coordinates");
            return (Criteria) this;
        }

        public Criteria andCoordinatesGreaterThan(String value) {
            addCriterion("coordinates >", value, "coordinates");
            return (Criteria) this;
        }

        public Criteria andCoordinatesGreaterThanOrEqualTo(String value) {
            addCriterion("coordinates >=", value, "coordinates");
            return (Criteria) this;
        }

        public Criteria andCoordinatesLessThan(String value) {
            addCriterion("coordinates <", value, "coordinates");
            return (Criteria) this;
        }

        public Criteria andCoordinatesLessThanOrEqualTo(String value) {
            addCriterion("coordinates <=", value, "coordinates");
            return (Criteria) this;
        }

        public Criteria andCoordinatesLike(String value) {
            addCriterion("coordinates like", value, "coordinates");
            return (Criteria) this;
        }

        public Criteria andCoordinatesNotLike(String value) {
            addCriterion("coordinates not like", value, "coordinates");
            return (Criteria) this;
        }

        public Criteria andCoordinatesIn(List<String> values) {
            addCriterion("coordinates in", values, "coordinates");
            return (Criteria) this;
        }

        public Criteria andCoordinatesNotIn(List<String> values) {
            addCriterion("coordinates not in", values, "coordinates");
            return (Criteria) this;
        }

        public Criteria andCoordinatesBetween(String value1, String value2) {
            addCriterion("coordinates between", value1, value2, "coordinates");
            return (Criteria) this;
        }

        public Criteria andCoordinatesNotBetween(String value1, String value2) {
            addCriterion("coordinates not between", value1, value2, "coordinates");
            return (Criteria) this;
        }

        public Criteria andIsArrivedIsNull() {
            addCriterion("is_arrived is null");
            return (Criteria) this;
        }

        public Criteria andIsArrivedIsNotNull() {
            addCriterion("is_arrived is not null");
            return (Criteria) this;
        }

        public Criteria andIsArrivedEqualTo(Boolean value) {
            addCriterion("is_arrived =", value, "isArrived");
            return (Criteria) this;
        }

        public Criteria andIsArrivedNotEqualTo(Boolean value) {
            addCriterion("is_arrived <>", value, "isArrived");
            return (Criteria) this;
        }

        public Criteria andIsArrivedGreaterThan(Boolean value) {
            addCriterion("is_arrived >", value, "isArrived");
            return (Criteria) this;
        }

        public Criteria andIsArrivedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_arrived >=", value, "isArrived");
            return (Criteria) this;
        }

        public Criteria andIsArrivedLessThan(Boolean value) {
            addCriterion("is_arrived <", value, "isArrived");
            return (Criteria) this;
        }

        public Criteria andIsArrivedLessThanOrEqualTo(Boolean value) {
            addCriterion("is_arrived <=", value, "isArrived");
            return (Criteria) this;
        }

        public Criteria andIsArrivedIn(List<Boolean> values) {
            addCriterion("is_arrived in", values, "isArrived");
            return (Criteria) this;
        }

        public Criteria andIsArrivedNotIn(List<Boolean> values) {
            addCriterion("is_arrived not in", values, "isArrived");
            return (Criteria) this;
        }

        public Criteria andIsArrivedBetween(Boolean value1, Boolean value2) {
            addCriterion("is_arrived between", value1, value2, "isArrived");
            return (Criteria) this;
        }

        public Criteria andIsArrivedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_arrived not between", value1, value2, "isArrived");
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

        public Criteria andSpareContactPhoneIsNull() {
            addCriterion("spare_contact_phone is null");
            return (Criteria) this;
        }

        public Criteria andSpareContactPhoneIsNotNull() {
            addCriterion("spare_contact_phone is not null");
            return (Criteria) this;
        }

        public Criteria andSpareContactPhoneEqualTo(String value) {
            addCriterion("spare_contact_phone =", value, "spareContactPhone");
            return (Criteria) this;
        }

        public Criteria andSpareContactPhoneNotEqualTo(String value) {
            addCriterion("spare_contact_phone <>", value, "spareContactPhone");
            return (Criteria) this;
        }

        public Criteria andSpareContactPhoneGreaterThan(String value) {
            addCriterion("spare_contact_phone >", value, "spareContactPhone");
            return (Criteria) this;
        }

        public Criteria andSpareContactPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("spare_contact_phone >=", value, "spareContactPhone");
            return (Criteria) this;
        }

        public Criteria andSpareContactPhoneLessThan(String value) {
            addCriterion("spare_contact_phone <", value, "spareContactPhone");
            return (Criteria) this;
        }

        public Criteria andSpareContactPhoneLessThanOrEqualTo(String value) {
            addCriterion("spare_contact_phone <=", value, "spareContactPhone");
            return (Criteria) this;
        }

        public Criteria andSpareContactPhoneLike(String value) {
            addCriterion("spare_contact_phone like", value, "spareContactPhone");
            return (Criteria) this;
        }

        public Criteria andSpareContactPhoneNotLike(String value) {
            addCriterion("spare_contact_phone not like", value, "spareContactPhone");
            return (Criteria) this;
        }

        public Criteria andSpareContactPhoneIn(List<String> values) {
            addCriterion("spare_contact_phone in", values, "spareContactPhone");
            return (Criteria) this;
        }

        public Criteria andSpareContactPhoneNotIn(List<String> values) {
            addCriterion("spare_contact_phone not in", values, "spareContactPhone");
            return (Criteria) this;
        }

        public Criteria andSpareContactPhoneBetween(String value1, String value2) {
            addCriterion("spare_contact_phone between", value1, value2, "spareContactPhone");
            return (Criteria) this;
        }

        public Criteria andSpareContactPhoneNotBetween(String value1, String value2) {
            addCriterion("spare_contact_phone not between", value1, value2, "spareContactPhone");
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