package com.juma.tgm.waybill.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WaybillOperateTrackExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int startOffSet = -1;

    protected int size = -1;

    public WaybillOperateTrackExample() {
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

    public WaybillOperateTrackExample orderBy(String ... orderByClauses) {
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

        public Criteria andTrackIdIsNull() {
            addCriterion("track_id is null");
            return (Criteria) this;
        }

        public Criteria andTrackIdIsNotNull() {
            addCriterion("track_id is not null");
            return (Criteria) this;
        }

        public Criteria andTrackIdEqualTo(Integer value) {
            addCriterion("track_id =", value, "trackId");
            return (Criteria) this;
        }

        public Criteria andTrackIdNotEqualTo(Integer value) {
            addCriterion("track_id <>", value, "trackId");
            return (Criteria) this;
        }

        public Criteria andTrackIdGreaterThan(Integer value) {
            addCriterion("track_id >", value, "trackId");
            return (Criteria) this;
        }

        public Criteria andTrackIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("track_id >=", value, "trackId");
            return (Criteria) this;
        }

        public Criteria andTrackIdLessThan(Integer value) {
            addCriterion("track_id <", value, "trackId");
            return (Criteria) this;
        }

        public Criteria andTrackIdLessThanOrEqualTo(Integer value) {
            addCriterion("track_id <=", value, "trackId");
            return (Criteria) this;
        }

        public Criteria andTrackIdIn(List<Integer> values) {
            addCriterion("track_id in", values, "trackId");
            return (Criteria) this;
        }

        public Criteria andTrackIdNotIn(List<Integer> values) {
            addCriterion("track_id not in", values, "trackId");
            return (Criteria) this;
        }

        public Criteria andTrackIdBetween(Integer value1, Integer value2) {
            addCriterion("track_id between", value1, value2, "trackId");
            return (Criteria) this;
        }

        public Criteria andTrackIdNotBetween(Integer value1, Integer value2) {
            addCriterion("track_id not between", value1, value2, "trackId");
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

        public Criteria andOperateTypeIsNull() {
            addCriterion("operate_type is null");
            return (Criteria) this;
        }

        public Criteria andOperateTypeIsNotNull() {
            addCriterion("operate_type is not null");
            return (Criteria) this;
        }

        public Criteria andOperateTypeEqualTo(Integer value) {
            addCriterion("operate_type =", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeNotEqualTo(Integer value) {
            addCriterion("operate_type <>", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeGreaterThan(Integer value) {
            addCriterion("operate_type >", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("operate_type >=", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeLessThan(Integer value) {
            addCriterion("operate_type <", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeLessThanOrEqualTo(Integer value) {
            addCriterion("operate_type <=", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeIn(List<Integer> values) {
            addCriterion("operate_type in", values, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeNotIn(List<Integer> values) {
            addCriterion("operate_type not in", values, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeBetween(Integer value1, Integer value2) {
            addCriterion("operate_type between", value1, value2, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("operate_type not between", value1, value2, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateApplicationIsNull() {
            addCriterion("operate_application is null");
            return (Criteria) this;
        }

        public Criteria andOperateApplicationIsNotNull() {
            addCriterion("operate_application is not null");
            return (Criteria) this;
        }

        public Criteria andOperateApplicationEqualTo(Integer value) {
            addCriterion("operate_application =", value, "operateApplication");
            return (Criteria) this;
        }

        public Criteria andOperateApplicationNotEqualTo(Integer value) {
            addCriterion("operate_application <>", value, "operateApplication");
            return (Criteria) this;
        }

        public Criteria andOperateApplicationGreaterThan(Integer value) {
            addCriterion("operate_application >", value, "operateApplication");
            return (Criteria) this;
        }

        public Criteria andOperateApplicationGreaterThanOrEqualTo(Integer value) {
            addCriterion("operate_application >=", value, "operateApplication");
            return (Criteria) this;
        }

        public Criteria andOperateApplicationLessThan(Integer value) {
            addCriterion("operate_application <", value, "operateApplication");
            return (Criteria) this;
        }

        public Criteria andOperateApplicationLessThanOrEqualTo(Integer value) {
            addCriterion("operate_application <=", value, "operateApplication");
            return (Criteria) this;
        }

        public Criteria andOperateApplicationIn(List<Integer> values) {
            addCriterion("operate_application in", values, "operateApplication");
            return (Criteria) this;
        }

        public Criteria andOperateApplicationNotIn(List<Integer> values) {
            addCriterion("operate_application not in", values, "operateApplication");
            return (Criteria) this;
        }

        public Criteria andOperateApplicationBetween(Integer value1, Integer value2) {
            addCriterion("operate_application between", value1, value2, "operateApplication");
            return (Criteria) this;
        }

        public Criteria andOperateApplicationNotBetween(Integer value1, Integer value2) {
            addCriterion("operate_application not between", value1, value2, "operateApplication");
            return (Criteria) this;
        }

        public Criteria andOperateAddressIsNull() {
            addCriterion("operate_address is null");
            return (Criteria) this;
        }

        public Criteria andOperateAddressIsNotNull() {
            addCriterion("operate_address is not null");
            return (Criteria) this;
        }

        public Criteria andOperateAddressEqualTo(String value) {
            addCriterion("operate_address =", value, "operateAddress");
            return (Criteria) this;
        }

        public Criteria andOperateAddressNotEqualTo(String value) {
            addCriterion("operate_address <>", value, "operateAddress");
            return (Criteria) this;
        }

        public Criteria andOperateAddressGreaterThan(String value) {
            addCriterion("operate_address >", value, "operateAddress");
            return (Criteria) this;
        }

        public Criteria andOperateAddressGreaterThanOrEqualTo(String value) {
            addCriterion("operate_address >=", value, "operateAddress");
            return (Criteria) this;
        }

        public Criteria andOperateAddressLessThan(String value) {
            addCriterion("operate_address <", value, "operateAddress");
            return (Criteria) this;
        }

        public Criteria andOperateAddressLessThanOrEqualTo(String value) {
            addCriterion("operate_address <=", value, "operateAddress");
            return (Criteria) this;
        }

        public Criteria andOperateAddressLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("operate_address like", value, "operateAddress");
            return (Criteria) this;
        }

        public Criteria andOperateAddressNotLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("operate_address not like", value, "operateAddress");
            return (Criteria) this;
        }

        public Criteria andOperateAddressLikeList(List<String> values) {
            addCriterion("operate_address like", values, "operateAddress");
            return (Criteria) this;
        }

        public Criteria andOperateAddressIn(List<String> values) {
            addCriterion("operate_address in", values, "operateAddress");
            return (Criteria) this;
        }

        public Criteria andOperateAddressNotIn(List<String> values) {
            addCriterion("operate_address not in", values, "operateAddress");
            return (Criteria) this;
        }

        public Criteria andOperateAddressBetween(String value1, String value2) {
            addCriterion("operate_address between", value1, value2, "operateAddress");
            return (Criteria) this;
        }

        public Criteria andOperateAddressNotBetween(String value1, String value2) {
            addCriterion("operate_address not between", value1, value2, "operateAddress");
            return (Criteria) this;
        }

        public Criteria andOperateAddressCoordinatesIsNull() {
            addCriterion("operate_address_coordinates is null");
            return (Criteria) this;
        }

        public Criteria andOperateAddressCoordinatesIsNotNull() {
            addCriterion("operate_address_coordinates is not null");
            return (Criteria) this;
        }

        public Criteria andOperateAddressCoordinatesEqualTo(String value) {
            addCriterion("operate_address_coordinates =", value, "operateAddressCoordinates");
            return (Criteria) this;
        }

        public Criteria andOperateAddressCoordinatesNotEqualTo(String value) {
            addCriterion("operate_address_coordinates <>", value, "operateAddressCoordinates");
            return (Criteria) this;
        }

        public Criteria andOperateAddressCoordinatesGreaterThan(String value) {
            addCriterion("operate_address_coordinates >", value, "operateAddressCoordinates");
            return (Criteria) this;
        }

        public Criteria andOperateAddressCoordinatesGreaterThanOrEqualTo(String value) {
            addCriterion("operate_address_coordinates >=", value, "operateAddressCoordinates");
            return (Criteria) this;
        }

        public Criteria andOperateAddressCoordinatesLessThan(String value) {
            addCriterion("operate_address_coordinates <", value, "operateAddressCoordinates");
            return (Criteria) this;
        }

        public Criteria andOperateAddressCoordinatesLessThanOrEqualTo(String value) {
            addCriterion("operate_address_coordinates <=", value, "operateAddressCoordinates");
            return (Criteria) this;
        }

        public Criteria andOperateAddressCoordinatesLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("operate_address_coordinates like", value, "operateAddressCoordinates");
            return (Criteria) this;
        }

        public Criteria andOperateAddressCoordinatesNotLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("operate_address_coordinates not like", value, "operateAddressCoordinates");
            return (Criteria) this;
        }

        public Criteria andOperateAddressCoordinatesLikeList(List<String> values) {
            addCriterion("operate_address_coordinates like", values, "operateAddressCoordinates");
            return (Criteria) this;
        }

        public Criteria andOperateAddressCoordinatesIn(List<String> values) {
            addCriterion("operate_address_coordinates in", values, "operateAddressCoordinates");
            return (Criteria) this;
        }

        public Criteria andOperateAddressCoordinatesNotIn(List<String> values) {
            addCriterion("operate_address_coordinates not in", values, "operateAddressCoordinates");
            return (Criteria) this;
        }

        public Criteria andOperateAddressCoordinatesBetween(String value1, String value2) {
            addCriterion("operate_address_coordinates between", value1, value2, "operateAddressCoordinates");
            return (Criteria) this;
        }

        public Criteria andOperateAddressCoordinatesNotBetween(String value1, String value2) {
            addCriterion("operate_address_coordinates not between", value1, value2, "operateAddressCoordinates");
            return (Criteria) this;
        }

        public Criteria andActualAddressIsNull() {
            addCriterion("actual_address is null");
            return (Criteria) this;
        }

        public Criteria andActualAddressIsNotNull() {
            addCriterion("actual_address is not null");
            return (Criteria) this;
        }

        public Criteria andActualAddressEqualTo(String value) {
            addCriterion("actual_address =", value, "actualAddress");
            return (Criteria) this;
        }

        public Criteria andActualAddressNotEqualTo(String value) {
            addCriterion("actual_address <>", value, "actualAddress");
            return (Criteria) this;
        }

        public Criteria andActualAddressGreaterThan(String value) {
            addCriterion("actual_address >", value, "actualAddress");
            return (Criteria) this;
        }

        public Criteria andActualAddressGreaterThanOrEqualTo(String value) {
            addCriterion("actual_address >=", value, "actualAddress");
            return (Criteria) this;
        }

        public Criteria andActualAddressLessThan(String value) {
            addCriterion("actual_address <", value, "actualAddress");
            return (Criteria) this;
        }

        public Criteria andActualAddressLessThanOrEqualTo(String value) {
            addCriterion("actual_address <=", value, "actualAddress");
            return (Criteria) this;
        }

        public Criteria andActualAddressLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("actual_address like", value, "actualAddress");
            return (Criteria) this;
        }

        public Criteria andActualAddressNotLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("actual_address not like", value, "actualAddress");
            return (Criteria) this;
        }

        public Criteria andActualAddressLikeList(List<String> values) {
            addCriterion("actual_address like", values, "actualAddress");
            return (Criteria) this;
        }

        public Criteria andActualAddressIn(List<String> values) {
            addCriterion("actual_address in", values, "actualAddress");
            return (Criteria) this;
        }

        public Criteria andActualAddressNotIn(List<String> values) {
            addCriterion("actual_address not in", values, "actualAddress");
            return (Criteria) this;
        }

        public Criteria andActualAddressBetween(String value1, String value2) {
            addCriterion("actual_address between", value1, value2, "actualAddress");
            return (Criteria) this;
        }

        public Criteria andActualAddressNotBetween(String value1, String value2) {
            addCriterion("actual_address not between", value1, value2, "actualAddress");
            return (Criteria) this;
        }

        public Criteria andActualAddressCoordinatesIsNull() {
            addCriterion("actual_address_coordinates is null");
            return (Criteria) this;
        }

        public Criteria andActualAddressCoordinatesIsNotNull() {
            addCriterion("actual_address_coordinates is not null");
            return (Criteria) this;
        }

        public Criteria andActualAddressCoordinatesEqualTo(String value) {
            addCriterion("actual_address_coordinates =", value, "actualAddressCoordinates");
            return (Criteria) this;
        }

        public Criteria andActualAddressCoordinatesNotEqualTo(String value) {
            addCriterion("actual_address_coordinates <>", value, "actualAddressCoordinates");
            return (Criteria) this;
        }

        public Criteria andActualAddressCoordinatesGreaterThan(String value) {
            addCriterion("actual_address_coordinates >", value, "actualAddressCoordinates");
            return (Criteria) this;
        }

        public Criteria andActualAddressCoordinatesGreaterThanOrEqualTo(String value) {
            addCriterion("actual_address_coordinates >=", value, "actualAddressCoordinates");
            return (Criteria) this;
        }

        public Criteria andActualAddressCoordinatesLessThan(String value) {
            addCriterion("actual_address_coordinates <", value, "actualAddressCoordinates");
            return (Criteria) this;
        }

        public Criteria andActualAddressCoordinatesLessThanOrEqualTo(String value) {
            addCriterion("actual_address_coordinates <=", value, "actualAddressCoordinates");
            return (Criteria) this;
        }

        public Criteria andActualAddressCoordinatesLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("actual_address_coordinates like", value, "actualAddressCoordinates");
            return (Criteria) this;
        }

        public Criteria andActualAddressCoordinatesNotLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("actual_address_coordinates not like", value, "actualAddressCoordinates");
            return (Criteria) this;
        }

        public Criteria andActualAddressCoordinatesLikeList(List<String> values) {
            addCriterion("actual_address_coordinates like", values, "actualAddressCoordinates");
            return (Criteria) this;
        }

        public Criteria andActualAddressCoordinatesIn(List<String> values) {
            addCriterion("actual_address_coordinates in", values, "actualAddressCoordinates");
            return (Criteria) this;
        }

        public Criteria andActualAddressCoordinatesNotIn(List<String> values) {
            addCriterion("actual_address_coordinates not in", values, "actualAddressCoordinates");
            return (Criteria) this;
        }

        public Criteria andActualAddressCoordinatesBetween(String value1, String value2) {
            addCriterion("actual_address_coordinates between", value1, value2, "actualAddressCoordinates");
            return (Criteria) this;
        }

        public Criteria andActualAddressCoordinatesNotBetween(String value1, String value2) {
            addCriterion("actual_address_coordinates not between", value1, value2, "actualAddressCoordinates");
            return (Criteria) this;
        }

        public Criteria andDistanceIsNull() {
            addCriterion("distance is null");
            return (Criteria) this;
        }

        public Criteria andDistanceIsNotNull() {
            addCriterion("distance is not null");
            return (Criteria) this;
        }

        public Criteria andDistanceEqualTo(Integer value) {
            addCriterion("distance =", value, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceNotEqualTo(Integer value) {
            addCriterion("distance <>", value, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceGreaterThan(Integer value) {
            addCriterion("distance >", value, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceGreaterThanOrEqualTo(Integer value) {
            addCriterion("distance >=", value, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceLessThan(Integer value) {
            addCriterion("distance <", value, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceLessThanOrEqualTo(Integer value) {
            addCriterion("distance <=", value, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceIn(List<Integer> values) {
            addCriterion("distance in", values, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceNotIn(List<Integer> values) {
            addCriterion("distance not in", values, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceBetween(Integer value1, Integer value2) {
            addCriterion("distance between", value1, value2, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceNotBetween(Integer value1, Integer value2) {
            addCriterion("distance not between", value1, value2, "distance");
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

        public Criteria andDataSourceIsNull() {
            addCriterion("data_source is null");
            return (Criteria) this;
        }

        public Criteria andDataSourceIsNotNull() {
            addCriterion("data_source is not null");
            return (Criteria) this;
        }

        public Criteria andDataSourceEqualTo(Integer value) {
            addCriterion("data_source =", value, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceNotEqualTo(Integer value) {
            addCriterion("data_source <>", value, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceGreaterThan(Integer value) {
            addCriterion("data_source >", value, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceGreaterThanOrEqualTo(Integer value) {
            addCriterion("data_source >=", value, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceLessThan(Integer value) {
            addCriterion("data_source <", value, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceLessThanOrEqualTo(Integer value) {
            addCriterion("data_source <=", value, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceIn(List<Integer> values) {
            addCriterion("data_source in", values, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceNotIn(List<Integer> values) {
            addCriterion("data_source not in", values, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceBetween(Integer value1, Integer value2) {
            addCriterion("data_source between", value1, value2, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceNotBetween(Integer value1, Integer value2) {
            addCriterion("data_source not between", value1, value2, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDeviceNoIsNull() {
            addCriterion("device_no is null");
            return (Criteria) this;
        }

        public Criteria andDeviceNoIsNotNull() {
            addCriterion("device_no is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceNoEqualTo(String value) {
            addCriterion("device_no =", value, "deviceNo");
            return (Criteria) this;
        }

        public Criteria andDeviceNoNotEqualTo(String value) {
            addCriterion("device_no <>", value, "deviceNo");
            return (Criteria) this;
        }

        public Criteria andDeviceNoGreaterThan(String value) {
            addCriterion("device_no >", value, "deviceNo");
            return (Criteria) this;
        }

        public Criteria andDeviceNoGreaterThanOrEqualTo(String value) {
            addCriterion("device_no >=", value, "deviceNo");
            return (Criteria) this;
        }

        public Criteria andDeviceNoLessThan(String value) {
            addCriterion("device_no <", value, "deviceNo");
            return (Criteria) this;
        }

        public Criteria andDeviceNoLessThanOrEqualTo(String value) {
            addCriterion("device_no <=", value, "deviceNo");
            return (Criteria) this;
        }

        public Criteria andDeviceNoLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("device_no like", value, "deviceNo");
            return (Criteria) this;
        }

        public Criteria andDeviceNoNotLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("device_no not like", value, "deviceNo");
            return (Criteria) this;
        }

        public Criteria andDeviceNoLikeList(List<String> values) {
            addCriterion("device_no like", values, "deviceNo");
            return (Criteria) this;
        }

        public Criteria andDeviceNoIn(List<String> values) {
            addCriterion("device_no in", values, "deviceNo");
            return (Criteria) this;
        }

        public Criteria andDeviceNoNotIn(List<String> values) {
            addCriterion("device_no not in", values, "deviceNo");
            return (Criteria) this;
        }

        public Criteria andDeviceNoBetween(String value1, String value2) {
            addCriterion("device_no between", value1, value2, "deviceNo");
            return (Criteria) this;
        }

        public Criteria andDeviceNoNotBetween(String value1, String value2) {
            addCriterion("device_no not between", value1, value2, "deviceNo");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeIsNull() {
            addCriterion("device_type is null");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeIsNotNull() {
            addCriterion("device_type is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeEqualTo(Integer value) {
            addCriterion("device_type =", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNotEqualTo(Integer value) {
            addCriterion("device_type <>", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeGreaterThan(Integer value) {
            addCriterion("device_type >", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("device_type >=", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeLessThan(Integer value) {
            addCriterion("device_type <", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeLessThanOrEqualTo(Integer value) {
            addCriterion("device_type <=", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeIn(List<Integer> values) {
            addCriterion("device_type in", values, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNotIn(List<Integer> values) {
            addCriterion("device_type not in", values, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeBetween(Integer value1, Integer value2) {
            addCriterion("device_type between", value1, value2, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("device_type not between", value1, value2, "deviceType");
            return (Criteria) this;
        }

        public Criteria andFenceDeviceNoIsNull() {
            addCriterion("fence_device_no is null");
            return (Criteria) this;
        }

        public Criteria andFenceDeviceNoIsNotNull() {
            addCriterion("fence_device_no is not null");
            return (Criteria) this;
        }

        public Criteria andFenceDeviceNoEqualTo(String value) {
            addCriterion("fence_device_no =", value, "fenceDeviceNo");
            return (Criteria) this;
        }

        public Criteria andFenceDeviceNoNotEqualTo(String value) {
            addCriterion("fence_device_no <>", value, "fenceDeviceNo");
            return (Criteria) this;
        }

        public Criteria andFenceDeviceNoGreaterThan(String value) {
            addCriterion("fence_device_no >", value, "fenceDeviceNo");
            return (Criteria) this;
        }

        public Criteria andFenceDeviceNoGreaterThanOrEqualTo(String value) {
            addCriterion("fence_device_no >=", value, "fenceDeviceNo");
            return (Criteria) this;
        }

        public Criteria andFenceDeviceNoLessThan(String value) {
            addCriterion("fence_device_no <", value, "fenceDeviceNo");
            return (Criteria) this;
        }

        public Criteria andFenceDeviceNoLessThanOrEqualTo(String value) {
            addCriterion("fence_device_no <=", value, "fenceDeviceNo");
            return (Criteria) this;
        }

        public Criteria andFenceDeviceNoLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("fence_device_no like", value, "fenceDeviceNo");
            return (Criteria) this;
        }

        public Criteria andFenceDeviceNoNotLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("fence_device_no not like", value, "fenceDeviceNo");
            return (Criteria) this;
        }

        public Criteria andFenceDeviceNoLikeList(List<String> values) {
            addCriterion("fence_device_no like", values, "fenceDeviceNo");
            return (Criteria) this;
        }

        public Criteria andFenceDeviceNoIn(List<String> values) {
            addCriterion("fence_device_no in", values, "fenceDeviceNo");
            return (Criteria) this;
        }

        public Criteria andFenceDeviceNoNotIn(List<String> values) {
            addCriterion("fence_device_no not in", values, "fenceDeviceNo");
            return (Criteria) this;
        }

        public Criteria andFenceDeviceNoBetween(String value1, String value2) {
            addCriterion("fence_device_no between", value1, value2, "fenceDeviceNo");
            return (Criteria) this;
        }

        public Criteria andFenceDeviceNoNotBetween(String value1, String value2) {
            addCriterion("fence_device_no not between", value1, value2, "fenceDeviceNo");
            return (Criteria) this;
        }

        public Criteria andFenceDeviceTypeIsNull() {
            addCriterion("fence_device_Type is null");
            return (Criteria) this;
        }

        public Criteria andFenceDeviceTypeIsNotNull() {
            addCriterion("fence_device_Type is not null");
            return (Criteria) this;
        }

        public Criteria andFenceDeviceTypeEqualTo(Integer value) {
            addCriterion("fence_device_Type =", value, "fenceDeviceType");
            return (Criteria) this;
        }

        public Criteria andFenceDeviceTypeNotEqualTo(Integer value) {
            addCriterion("fence_device_Type <>", value, "fenceDeviceType");
            return (Criteria) this;
        }

        public Criteria andFenceDeviceTypeGreaterThan(Integer value) {
            addCriterion("fence_device_Type >", value, "fenceDeviceType");
            return (Criteria) this;
        }

        public Criteria andFenceDeviceTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("fence_device_Type >=", value, "fenceDeviceType");
            return (Criteria) this;
        }

        public Criteria andFenceDeviceTypeLessThan(Integer value) {
            addCriterion("fence_device_Type <", value, "fenceDeviceType");
            return (Criteria) this;
        }

        public Criteria andFenceDeviceTypeLessThanOrEqualTo(Integer value) {
            addCriterion("fence_device_Type <=", value, "fenceDeviceType");
            return (Criteria) this;
        }

        public Criteria andFenceDeviceTypeIn(List<Integer> values) {
            addCriterion("fence_device_Type in", values, "fenceDeviceType");
            return (Criteria) this;
        }

        public Criteria andFenceDeviceTypeNotIn(List<Integer> values) {
            addCriterion("fence_device_Type not in", values, "fenceDeviceType");
            return (Criteria) this;
        }

        public Criteria andFenceDeviceTypeBetween(Integer value1, Integer value2) {
            addCriterion("fence_device_Type between", value1, value2, "fenceDeviceType");
            return (Criteria) this;
        }

        public Criteria andFenceDeviceTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("fence_device_Type not between", value1, value2, "fenceDeviceType");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLikeList(List<String> values) {
            addCriterion("remark like", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andDeclareTimeIsNull() {
            addCriterion("declare_time is null");
            return (Criteria) this;
        }

        public Criteria andDeclareTimeIsNotNull() {
            addCriterion("declare_time is not null");
            return (Criteria) this;
        }

        public Criteria andDeclareTimeEqualTo(Date value) {
            addCriterion("declare_time =", value, "declareTime");
            return (Criteria) this;
        }

        public Criteria andDeclareTimeNotEqualTo(Date value) {
            addCriterion("declare_time <>", value, "declareTime");
            return (Criteria) this;
        }

        public Criteria andDeclareTimeGreaterThan(Date value) {
            addCriterion("declare_time >", value, "declareTime");
            return (Criteria) this;
        }

        public Criteria andDeclareTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("declare_time >=", value, "declareTime");
            return (Criteria) this;
        }

        public Criteria andDeclareTimeLessThan(Date value) {
            addCriterion("declare_time <", value, "declareTime");
            return (Criteria) this;
        }

        public Criteria andDeclareTimeLessThanOrEqualTo(Date value) {
            addCriterion("declare_time <=", value, "declareTime");
            return (Criteria) this;
        }

        public Criteria andDeclareTimeIn(List<Date> values) {
            addCriterion("declare_time in", values, "declareTime");
            return (Criteria) this;
        }

        public Criteria andDeclareTimeNotIn(List<Date> values) {
            addCriterion("declare_time not in", values, "declareTime");
            return (Criteria) this;
        }

        public Criteria andDeclareTimeBetween(Date value1, Date value2) {
            addCriterion("declare_time between", value1, value2, "declareTime");
            return (Criteria) this;
        }

        public Criteria andDeclareTimeNotBetween(Date value1, Date value2) {
            addCriterion("declare_time not between", value1, value2, "declareTime");
            return (Criteria) this;
        }

        public Criteria andPlateNumberIsNull() {
            addCriterion("plate_number is null");
            return (Criteria) this;
        }

        public Criteria andPlateNumberIsNotNull() {
            addCriterion("plate_number is not null");
            return (Criteria) this;
        }

        public Criteria andPlateNumberEqualTo(String value) {
            addCriterion("plate_number =", value, "plateNumber");
            return (Criteria) this;
        }

        public Criteria andPlateNumberNotEqualTo(String value) {
            addCriterion("plate_number <>", value, "plateNumber");
            return (Criteria) this;
        }

        public Criteria andPlateNumberGreaterThan(String value) {
            addCriterion("plate_number >", value, "plateNumber");
            return (Criteria) this;
        }

        public Criteria andPlateNumberGreaterThanOrEqualTo(String value) {
            addCriterion("plate_number >=", value, "plateNumber");
            return (Criteria) this;
        }

        public Criteria andPlateNumberLessThan(String value) {
            addCriterion("plate_number <", value, "plateNumber");
            return (Criteria) this;
        }

        public Criteria andPlateNumberLessThanOrEqualTo(String value) {
            addCriterion("plate_number <=", value, "plateNumber");
            return (Criteria) this;
        }

        public Criteria andPlateNumberLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("plate_number like", value, "plateNumber");
            return (Criteria) this;
        }

        public Criteria andPlateNumberNotLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("plate_number not like", value, "plateNumber");
            return (Criteria) this;
        }

        public Criteria andPlateNumberLikeList(List<String> values) {
            addCriterion("plate_number like", values, "plateNumber");
            return (Criteria) this;
        }

        public Criteria andPlateNumberIn(List<String> values) {
            addCriterion("plate_number in", values, "plateNumber");
            return (Criteria) this;
        }

        public Criteria andPlateNumberNotIn(List<String> values) {
            addCriterion("plate_number not in", values, "plateNumber");
            return (Criteria) this;
        }

        public Criteria andPlateNumberBetween(String value1, String value2) {
            addCriterion("plate_number between", value1, value2, "plateNumber");
            return (Criteria) this;
        }

        public Criteria andPlateNumberNotBetween(String value1, String value2) {
            addCriterion("plate_number not between", value1, value2, "plateNumber");
            return (Criteria) this;
        }

        public Criteria andDriverIdIsNull() {
            addCriterion("driver_id is null");
            return (Criteria) this;
        }

        public Criteria andDriverIdIsNotNull() {
            addCriterion("driver_id is not null");
            return (Criteria) this;
        }

        public Criteria andDriverIdEqualTo(Integer value) {
            addCriterion("driver_id =", value, "driverId");
            return (Criteria) this;
        }

        public Criteria andDriverIdNotEqualTo(Integer value) {
            addCriterion("driver_id <>", value, "driverId");
            return (Criteria) this;
        }

        public Criteria andDriverIdGreaterThan(Integer value) {
            addCriterion("driver_id >", value, "driverId");
            return (Criteria) this;
        }

        public Criteria andDriverIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("driver_id >=", value, "driverId");
            return (Criteria) this;
        }

        public Criteria andDriverIdLessThan(Integer value) {
            addCriterion("driver_id <", value, "driverId");
            return (Criteria) this;
        }

        public Criteria andDriverIdLessThanOrEqualTo(Integer value) {
            addCriterion("driver_id <=", value, "driverId");
            return (Criteria) this;
        }

        public Criteria andDriverIdIn(List<Integer> values) {
            addCriterion("driver_id in", values, "driverId");
            return (Criteria) this;
        }

        public Criteria andDriverIdNotIn(List<Integer> values) {
            addCriterion("driver_id not in", values, "driverId");
            return (Criteria) this;
        }

        public Criteria andDriverIdBetween(Integer value1, Integer value2) {
            addCriterion("driver_id between", value1, value2, "driverId");
            return (Criteria) this;
        }

        public Criteria andDriverIdNotBetween(Integer value1, Integer value2) {
            addCriterion("driver_id not between", value1, value2, "driverId");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        private WaybillOperateTrackExample example;

        protected Criteria(WaybillOperateTrackExample example) {
            super();
            this.example = example;
        }

        public WaybillOperateTrackExample example() {
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