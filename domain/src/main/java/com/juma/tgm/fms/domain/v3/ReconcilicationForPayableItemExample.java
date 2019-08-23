package com.juma.tgm.fms.domain.v3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ReconcilicationForPayableItemExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int startOffSet = -1;

    protected int size = -1;

    public ReconcilicationForPayableItemExample() {
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

        public Criteria andReconcilicationItemIdIsNull() {
            addCriterion("reconcilication_item_id is null");
            return (Criteria) this;
        }

        public Criteria andReconcilicationItemIdIsNotNull() {
            addCriterion("reconcilication_item_id is not null");
            return (Criteria) this;
        }

        public Criteria andReconcilicationItemIdEqualTo(Integer value) {
            addCriterion("reconcilication_item_id =", value, "reconcilicationItemId");
            return (Criteria) this;
        }

        public Criteria andReconcilicationItemIdNotEqualTo(Integer value) {
            addCriterion("reconcilication_item_id <>", value, "reconcilicationItemId");
            return (Criteria) this;
        }

        public Criteria andReconcilicationItemIdGreaterThan(Integer value) {
            addCriterion("reconcilication_item_id >", value, "reconcilicationItemId");
            return (Criteria) this;
        }

        public Criteria andReconcilicationItemIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("reconcilication_item_id >=", value, "reconcilicationItemId");
            return (Criteria) this;
        }

        public Criteria andReconcilicationItemIdLessThan(Integer value) {
            addCriterion("reconcilication_item_id <", value, "reconcilicationItemId");
            return (Criteria) this;
        }

        public Criteria andReconcilicationItemIdLessThanOrEqualTo(Integer value) {
            addCriterion("reconcilication_item_id <=", value, "reconcilicationItemId");
            return (Criteria) this;
        }

        public Criteria andReconcilicationItemIdIn(List<Integer> values) {
            addCriterion("reconcilication_item_id in", values, "reconcilicationItemId");
            return (Criteria) this;
        }

        public Criteria andReconcilicationItemIdNotIn(List<Integer> values) {
            addCriterion("reconcilication_item_id not in", values, "reconcilicationItemId");
            return (Criteria) this;
        }

        public Criteria andReconcilicationItemIdBetween(Integer value1, Integer value2) {
            addCriterion("reconcilication_item_id between", value1, value2, "reconcilicationItemId");
            return (Criteria) this;
        }

        public Criteria andReconcilicationItemIdNotBetween(Integer value1, Integer value2) {
            addCriterion("reconcilication_item_id not between", value1, value2, "reconcilicationItemId");
            return (Criteria) this;
        }

        public Criteria andReconcilicationIdIsNull() {
            addCriterion("reconcilication_id is null");
            return (Criteria) this;
        }

        public Criteria andReconcilicationIdIsNotNull() {
            addCriterion("reconcilication_id is not null");
            return (Criteria) this;
        }

        public Criteria andReconcilicationIdEqualTo(Integer value) {
            addCriterion("reconcilication_id =", value, "reconcilicationId");
            return (Criteria) this;
        }

        public Criteria andReconcilicationIdNotEqualTo(Integer value) {
            addCriterion("reconcilication_id <>", value, "reconcilicationId");
            return (Criteria) this;
        }

        public Criteria andReconcilicationIdGreaterThan(Integer value) {
            addCriterion("reconcilication_id >", value, "reconcilicationId");
            return (Criteria) this;
        }

        public Criteria andReconcilicationIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("reconcilication_id >=", value, "reconcilicationId");
            return (Criteria) this;
        }

        public Criteria andReconcilicationIdLessThan(Integer value) {
            addCriterion("reconcilication_id <", value, "reconcilicationId");
            return (Criteria) this;
        }

        public Criteria andReconcilicationIdLessThanOrEqualTo(Integer value) {
            addCriterion("reconcilication_id <=", value, "reconcilicationId");
            return (Criteria) this;
        }

        public Criteria andReconcilicationIdIn(List<Integer> values) {
            addCriterion("reconcilication_id in", values, "reconcilicationId");
            return (Criteria) this;
        }

        public Criteria andReconcilicationIdNotIn(List<Integer> values) {
            addCriterion("reconcilication_id not in", values, "reconcilicationId");
            return (Criteria) this;
        }

        public Criteria andReconcilicationIdBetween(Integer value1, Integer value2) {
            addCriterion("reconcilication_id between", value1, value2, "reconcilicationId");
            return (Criteria) this;
        }

        public Criteria andReconcilicationIdNotBetween(Integer value1, Integer value2) {
            addCriterion("reconcilication_id not between", value1, value2, "reconcilicationId");
            return (Criteria) this;
        }

        public Criteria andSettleTypeIsNull() {
            addCriterion("settle_type is null");
            return (Criteria) this;
        }

        public Criteria andSettleTypeIsNotNull() {
            addCriterion("settle_type is not null");
            return (Criteria) this;
        }

        public Criteria andSettleTypeEqualTo(Integer value) {
            addCriterion("settle_type =", value, "settleType");
            return (Criteria) this;
        }

        public Criteria andSettleTypeNotEqualTo(Integer value) {
            addCriterion("settle_type <>", value, "settleType");
            return (Criteria) this;
        }

        public Criteria andSettleTypeGreaterThan(Integer value) {
            addCriterion("settle_type >", value, "settleType");
            return (Criteria) this;
        }

        public Criteria andSettleTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("settle_type >=", value, "settleType");
            return (Criteria) this;
        }

        public Criteria andSettleTypeLessThan(Integer value) {
            addCriterion("settle_type <", value, "settleType");
            return (Criteria) this;
        }

        public Criteria andSettleTypeLessThanOrEqualTo(Integer value) {
            addCriterion("settle_type <=", value, "settleType");
            return (Criteria) this;
        }

        public Criteria andSettleTypeIn(List<Integer> values) {
            addCriterion("settle_type in", values, "settleType");
            return (Criteria) this;
        }

        public Criteria andSettleTypeNotIn(List<Integer> values) {
            addCriterion("settle_type not in", values, "settleType");
            return (Criteria) this;
        }

        public Criteria andSettleTypeBetween(Integer value1, Integer value2) {
            addCriterion("settle_type between", value1, value2, "settleType");
            return (Criteria) this;
        }

        public Criteria andSettleTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("settle_type not between", value1, value2, "settleType");
            return (Criteria) this;
        }

        public Criteria andSettleAccountIdIsNull() {
            addCriterion("settle_account_id is null");
            return (Criteria) this;
        }

        public Criteria andSettleAccountIdIsNotNull() {
            addCriterion("settle_account_id is not null");
            return (Criteria) this;
        }

        public Criteria andSettleAccountIdEqualTo(Integer value) {
            addCriterion("settle_account_id =", value, "settleAccountId");
            return (Criteria) this;
        }

        public Criteria andSettleAccountIdNotEqualTo(Integer value) {
            addCriterion("settle_account_id <>", value, "settleAccountId");
            return (Criteria) this;
        }

        public Criteria andSettleAccountIdGreaterThan(Integer value) {
            addCriterion("settle_account_id >", value, "settleAccountId");
            return (Criteria) this;
        }

        public Criteria andSettleAccountIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("settle_account_id >=", value, "settleAccountId");
            return (Criteria) this;
        }

        public Criteria andSettleAccountIdLessThan(Integer value) {
            addCriterion("settle_account_id <", value, "settleAccountId");
            return (Criteria) this;
        }

        public Criteria andSettleAccountIdLessThanOrEqualTo(Integer value) {
            addCriterion("settle_account_id <=", value, "settleAccountId");
            return (Criteria) this;
        }

        public Criteria andSettleAccountIdIn(List<Integer> values) {
            addCriterion("settle_account_id in", values, "settleAccountId");
            return (Criteria) this;
        }

        public Criteria andSettleAccountIdNotIn(List<Integer> values) {
            addCriterion("settle_account_id not in", values, "settleAccountId");
            return (Criteria) this;
        }

        public Criteria andSettleAccountIdBetween(Integer value1, Integer value2) {
            addCriterion("settle_account_id between", value1, value2, "settleAccountId");
            return (Criteria) this;
        }

        public Criteria andSettleAccountIdNotBetween(Integer value1, Integer value2) {
            addCriterion("settle_account_id not between", value1, value2, "settleAccountId");
            return (Criteria) this;
        }

        public Criteria andSettleAccountNameIsNull() {
            addCriterion("settle_account_name is null");
            return (Criteria) this;
        }

        public Criteria andSettleAccountNameIsNotNull() {
            addCriterion("settle_account_name is not null");
            return (Criteria) this;
        }

        public Criteria andSettleAccountNameEqualTo(String value) {
            addCriterion("settle_account_name =", value, "settleAccountName");
            return (Criteria) this;
        }

        public Criteria andSettleAccountNameNotEqualTo(String value) {
            addCriterion("settle_account_name <>", value, "settleAccountName");
            return (Criteria) this;
        }

        public Criteria andSettleAccountNameGreaterThan(String value) {
            addCriterion("settle_account_name >", value, "settleAccountName");
            return (Criteria) this;
        }

        public Criteria andSettleAccountNameGreaterThanOrEqualTo(String value) {
            addCriterion("settle_account_name >=", value, "settleAccountName");
            return (Criteria) this;
        }

        public Criteria andSettleAccountNameLessThan(String value) {
            addCriterion("settle_account_name <", value, "settleAccountName");
            return (Criteria) this;
        }

        public Criteria andSettleAccountNameLessThanOrEqualTo(String value) {
            addCriterion("settle_account_name <=", value, "settleAccountName");
            return (Criteria) this;
        }

        public Criteria andSettleAccountNameLike(String value) {
            addCriterion("settle_account_name like", value, "settleAccountName");
            return (Criteria) this;
        }

        public Criteria andSettleAccountNameNotLike(String value) {
            addCriterion("settle_account_name not like", value, "settleAccountName");
            return (Criteria) this;
        }

        public Criteria andSettleAccountNameIn(List<String> values) {
            addCriterion("settle_account_name in", values, "settleAccountName");
            return (Criteria) this;
        }

        public Criteria andSettleAccountNameNotIn(List<String> values) {
            addCriterion("settle_account_name not in", values, "settleAccountName");
            return (Criteria) this;
        }

        public Criteria andSettleAccountNameBetween(String value1, String value2) {
            addCriterion("settle_account_name between", value1, value2, "settleAccountName");
            return (Criteria) this;
        }

        public Criteria andSettleAccountNameNotBetween(String value1, String value2) {
            addCriterion("settle_account_name not between", value1, value2, "settleAccountName");
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
            addCriterion("plate_number like", value, "plateNumber");
            return (Criteria) this;
        }

        public Criteria andPlateNumberNotLike(String value) {
            addCriterion("plate_number not like", value, "plateNumber");
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

        public Criteria andVehicleFrameNoIsNull() {
            addCriterion("vehicle_frame_no is null");
            return (Criteria) this;
        }

        public Criteria andVehicleFrameNoIsNotNull() {
            addCriterion("vehicle_frame_no is not null");
            return (Criteria) this;
        }

        public Criteria andVehicleFrameNoEqualTo(String value) {
            addCriterion("vehicle_frame_no =", value, "vehicleFrameNo");
            return (Criteria) this;
        }

        public Criteria andVehicleFrameNoNotEqualTo(String value) {
            addCriterion("vehicle_frame_no <>", value, "vehicleFrameNo");
            return (Criteria) this;
        }

        public Criteria andVehicleFrameNoGreaterThan(String value) {
            addCriterion("vehicle_frame_no >", value, "vehicleFrameNo");
            return (Criteria) this;
        }

        public Criteria andVehicleFrameNoGreaterThanOrEqualTo(String value) {
            addCriterion("vehicle_frame_no >=", value, "vehicleFrameNo");
            return (Criteria) this;
        }

        public Criteria andVehicleFrameNoLessThan(String value) {
            addCriterion("vehicle_frame_no <", value, "vehicleFrameNo");
            return (Criteria) this;
        }

        public Criteria andVehicleFrameNoLessThanOrEqualTo(String value) {
            addCriterion("vehicle_frame_no <=", value, "vehicleFrameNo");
            return (Criteria) this;
        }

        public Criteria andVehicleFrameNoLike(String value) {
            addCriterion("vehicle_frame_no like", value, "vehicleFrameNo");
            return (Criteria) this;
        }

        public Criteria andVehicleFrameNoNotLike(String value) {
            addCriterion("vehicle_frame_no not like", value, "vehicleFrameNo");
            return (Criteria) this;
        }

        public Criteria andVehicleFrameNoIn(List<String> values) {
            addCriterion("vehicle_frame_no in", values, "vehicleFrameNo");
            return (Criteria) this;
        }

        public Criteria andVehicleFrameNoNotIn(List<String> values) {
            addCriterion("vehicle_frame_no not in", values, "vehicleFrameNo");
            return (Criteria) this;
        }

        public Criteria andVehicleFrameNoBetween(String value1, String value2) {
            addCriterion("vehicle_frame_no between", value1, value2, "vehicleFrameNo");
            return (Criteria) this;
        }

        public Criteria andVehicleFrameNoNotBetween(String value1, String value2) {
            addCriterion("vehicle_frame_no not between", value1, value2, "vehicleFrameNo");
            return (Criteria) this;
        }

        public Criteria andSettleFreightIsNull() {
            addCriterion("settle_freight is null");
            return (Criteria) this;
        }

        public Criteria andSettleFreightIsNotNull() {
            addCriterion("settle_freight is not null");
            return (Criteria) this;
        }

        public Criteria andSettleFreightEqualTo(BigDecimal value) {
            addCriterion("settle_freight =", value, "settleFreight");
            return (Criteria) this;
        }

        public Criteria andSettleFreightNotEqualTo(BigDecimal value) {
            addCriterion("settle_freight <>", value, "settleFreight");
            return (Criteria) this;
        }

        public Criteria andSettleFreightGreaterThan(BigDecimal value) {
            addCriterion("settle_freight >", value, "settleFreight");
            return (Criteria) this;
        }

        public Criteria andSettleFreightGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("settle_freight >=", value, "settleFreight");
            return (Criteria) this;
        }

        public Criteria andSettleFreightLessThan(BigDecimal value) {
            addCriterion("settle_freight <", value, "settleFreight");
            return (Criteria) this;
        }

        public Criteria andSettleFreightLessThanOrEqualTo(BigDecimal value) {
            addCriterion("settle_freight <=", value, "settleFreight");
            return (Criteria) this;
        }

        public Criteria andSettleFreightIn(List<BigDecimal> values) {
            addCriterion("settle_freight in", values, "settleFreight");
            return (Criteria) this;
        }

        public Criteria andSettleFreightNotIn(List<BigDecimal> values) {
            addCriterion("settle_freight not in", values, "settleFreight");
            return (Criteria) this;
        }

        public Criteria andSettleFreightBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("settle_freight between", value1, value2, "settleFreight");
            return (Criteria) this;
        }

        public Criteria andSettleFreightNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("settle_freight not between", value1, value2, "settleFreight");
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

        public Criteria andRebateFeeIsNull() {
            addCriterion("rebate_fee is null");
            return (Criteria) this;
        }

        public Criteria andRebateFeeIsNotNull() {
            addCriterion("rebate_fee is not null");
            return (Criteria) this;
        }

        public Criteria andRebateFeeEqualTo(BigDecimal value) {
            addCriterion("rebate_fee =", value, "rebateFee");
            return (Criteria) this;
        }

        public Criteria andRebateFeeNotEqualTo(BigDecimal value) {
            addCriterion("rebate_fee <>", value, "rebateFee");
            return (Criteria) this;
        }

        public Criteria andRebateFeeGreaterThan(BigDecimal value) {
            addCriterion("rebate_fee >", value, "rebateFee");
            return (Criteria) this;
        }

        public Criteria andRebateFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("rebate_fee >=", value, "rebateFee");
            return (Criteria) this;
        }

        public Criteria andRebateFeeLessThan(BigDecimal value) {
            addCriterion("rebate_fee <", value, "rebateFee");
            return (Criteria) this;
        }

        public Criteria andRebateFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("rebate_fee <=", value, "rebateFee");
            return (Criteria) this;
        }

        public Criteria andRebateFeeIn(List<BigDecimal> values) {
            addCriterion("rebate_fee in", values, "rebateFee");
            return (Criteria) this;
        }

        public Criteria andRebateFeeNotIn(List<BigDecimal> values) {
            addCriterion("rebate_fee not in", values, "rebateFee");
            return (Criteria) this;
        }

        public Criteria andRebateFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rebate_fee between", value1, value2, "rebateFee");
            return (Criteria) this;
        }

        public Criteria andRebateFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rebate_fee not between", value1, value2, "rebateFee");
            return (Criteria) this;
        }

        public Criteria andDriverTransportFeeIsNull() {
            addCriterion("driver_transport_fee is null");
            return (Criteria) this;
        }

        public Criteria andDriverTransportFeeIsNotNull() {
            addCriterion("driver_transport_fee is not null");
            return (Criteria) this;
        }

        public Criteria andDriverTransportFeeEqualTo(BigDecimal value) {
            addCriterion("driver_transport_fee =", value, "driverTransportFee");
            return (Criteria) this;
        }

        public Criteria andDriverTransportFeeNotEqualTo(BigDecimal value) {
            addCriterion("driver_transport_fee <>", value, "driverTransportFee");
            return (Criteria) this;
        }

        public Criteria andDriverTransportFeeGreaterThan(BigDecimal value) {
            addCriterion("driver_transport_fee >", value, "driverTransportFee");
            return (Criteria) this;
        }

        public Criteria andDriverTransportFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("driver_transport_fee >=", value, "driverTransportFee");
            return (Criteria) this;
        }

        public Criteria andDriverTransportFeeLessThan(BigDecimal value) {
            addCriterion("driver_transport_fee <", value, "driverTransportFee");
            return (Criteria) this;
        }

        public Criteria andDriverTransportFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("driver_transport_fee <=", value, "driverTransportFee");
            return (Criteria) this;
        }

        public Criteria andDriverTransportFeeIn(List<BigDecimal> values) {
            addCriterion("driver_transport_fee in", values, "driverTransportFee");
            return (Criteria) this;
        }

        public Criteria andDriverTransportFeeNotIn(List<BigDecimal> values) {
            addCriterion("driver_transport_fee not in", values, "driverTransportFee");
            return (Criteria) this;
        }

        public Criteria andDriverTransportFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("driver_transport_fee between", value1, value2, "driverTransportFee");
            return (Criteria) this;
        }

        public Criteria andDriverTransportFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("driver_transport_fee not between", value1, value2, "driverTransportFee");
            return (Criteria) this;
        }

        public Criteria andTemporaryTransportFeeIsNull() {
            addCriterion("temporary_transport_fee is null");
            return (Criteria) this;
        }

        public Criteria andTemporaryTransportFeeIsNotNull() {
            addCriterion("temporary_transport_fee is not null");
            return (Criteria) this;
        }

        public Criteria andTemporaryTransportFeeEqualTo(BigDecimal value) {
            addCriterion("temporary_transport_fee =", value, "temporaryTransportFee");
            return (Criteria) this;
        }

        public Criteria andTemporaryTransportFeeNotEqualTo(BigDecimal value) {
            addCriterion("temporary_transport_fee <>", value, "temporaryTransportFee");
            return (Criteria) this;
        }

        public Criteria andTemporaryTransportFeeGreaterThan(BigDecimal value) {
            addCriterion("temporary_transport_fee >", value, "temporaryTransportFee");
            return (Criteria) this;
        }

        public Criteria andTemporaryTransportFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("temporary_transport_fee >=", value, "temporaryTransportFee");
            return (Criteria) this;
        }

        public Criteria andTemporaryTransportFeeLessThan(BigDecimal value) {
            addCriterion("temporary_transport_fee <", value, "temporaryTransportFee");
            return (Criteria) this;
        }

        public Criteria andTemporaryTransportFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("temporary_transport_fee <=", value, "temporaryTransportFee");
            return (Criteria) this;
        }

        public Criteria andTemporaryTransportFeeIn(List<BigDecimal> values) {
            addCriterion("temporary_transport_fee in", values, "temporaryTransportFee");
            return (Criteria) this;
        }

        public Criteria andTemporaryTransportFeeNotIn(List<BigDecimal> values) {
            addCriterion("temporary_transport_fee not in", values, "temporaryTransportFee");
            return (Criteria) this;
        }

        public Criteria andTemporaryTransportFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("temporary_transport_fee between", value1, value2, "temporaryTransportFee");
            return (Criteria) this;
        }

        public Criteria andTemporaryTransportFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("temporary_transport_fee not between", value1, value2, "temporaryTransportFee");
            return (Criteria) this;
        }

        public Criteria andSettleStatusIsNull() {
            addCriterion("settle_status is null");
            return (Criteria) this;
        }

        public Criteria andSettleStatusIsNotNull() {
            addCriterion("settle_status is not null");
            return (Criteria) this;
        }

        public Criteria andSettleStatusEqualTo(Integer value) {
            addCriterion("settle_status =", value, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusNotEqualTo(Integer value) {
            addCriterion("settle_status <>", value, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusGreaterThan(Integer value) {
            addCriterion("settle_status >", value, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("settle_status >=", value, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusLessThan(Integer value) {
            addCriterion("settle_status <", value, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusLessThanOrEqualTo(Integer value) {
            addCriterion("settle_status <=", value, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusIn(List<Integer> values) {
            addCriterion("settle_status in", values, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusNotIn(List<Integer> values) {
            addCriterion("settle_status not in", values, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusBetween(Integer value1, Integer value2) {
            addCriterion("settle_status between", value1, value2, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("settle_status not between", value1, value2, "settleStatus");
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