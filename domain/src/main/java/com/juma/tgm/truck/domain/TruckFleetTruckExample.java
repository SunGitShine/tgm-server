package com.juma.tgm.truck.domain;

import java.util.ArrayList;
import java.util.List;

public class TruckFleetTruckExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int startOffSet = -1;

    protected int size = -1;

    public TruckFleetTruckExample() {
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

    public TruckFleetTruckExample orderBy(String ... orderByClauses) {
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

        public Criteria andTruckFleetTruckIdIsNull() {
            addCriterion("truck_fleet_truck_id is null");
            return (Criteria) this;
        }

        public Criteria andTruckFleetTruckIdIsNotNull() {
            addCriterion("truck_fleet_truck_id is not null");
            return (Criteria) this;
        }

        public Criteria andTruckFleetTruckIdEqualTo(Integer value) {
            addCriterion("truck_fleet_truck_id =", value, "truckFleetTruckId");
            return (Criteria) this;
        }

        public Criteria andTruckFleetTruckIdNotEqualTo(Integer value) {
            addCriterion("truck_fleet_truck_id <>", value, "truckFleetTruckId");
            return (Criteria) this;
        }

        public Criteria andTruckFleetTruckIdGreaterThan(Integer value) {
            addCriterion("truck_fleet_truck_id >", value, "truckFleetTruckId");
            return (Criteria) this;
        }

        public Criteria andTruckFleetTruckIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("truck_fleet_truck_id >=", value, "truckFleetTruckId");
            return (Criteria) this;
        }

        public Criteria andTruckFleetTruckIdLessThan(Integer value) {
            addCriterion("truck_fleet_truck_id <", value, "truckFleetTruckId");
            return (Criteria) this;
        }

        public Criteria andTruckFleetTruckIdLessThanOrEqualTo(Integer value) {
            addCriterion("truck_fleet_truck_id <=", value, "truckFleetTruckId");
            return (Criteria) this;
        }

        public Criteria andTruckFleetTruckIdIn(List<Integer> values) {
            addCriterion("truck_fleet_truck_id in", values, "truckFleetTruckId");
            return (Criteria) this;
        }

        public Criteria andTruckFleetTruckIdNotIn(List<Integer> values) {
            addCriterion("truck_fleet_truck_id not in", values, "truckFleetTruckId");
            return (Criteria) this;
        }

        public Criteria andTruckFleetTruckIdBetween(Integer value1, Integer value2) {
            addCriterion("truck_fleet_truck_id between", value1, value2, "truckFleetTruckId");
            return (Criteria) this;
        }

        public Criteria andTruckFleetTruckIdNotBetween(Integer value1, Integer value2) {
            addCriterion("truck_fleet_truck_id not between", value1, value2, "truckFleetTruckId");
            return (Criteria) this;
        }

        public Criteria andTruckFleetIdIsNull() {
            addCriterion("truck_fleet_id is null");
            return (Criteria) this;
        }

        public Criteria andTruckFleetIdIsNotNull() {
            addCriterion("truck_fleet_id is not null");
            return (Criteria) this;
        }

        public Criteria andTruckFleetIdEqualTo(Integer value) {
            addCriterion("truck_fleet_id =", value, "truckFleetId");
            return (Criteria) this;
        }

        public Criteria andTruckFleetIdNotEqualTo(Integer value) {
            addCriterion("truck_fleet_id <>", value, "truckFleetId");
            return (Criteria) this;
        }

        public Criteria andTruckFleetIdGreaterThan(Integer value) {
            addCriterion("truck_fleet_id >", value, "truckFleetId");
            return (Criteria) this;
        }

        public Criteria andTruckFleetIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("truck_fleet_id >=", value, "truckFleetId");
            return (Criteria) this;
        }

        public Criteria andTruckFleetIdLessThan(Integer value) {
            addCriterion("truck_fleet_id <", value, "truckFleetId");
            return (Criteria) this;
        }

        public Criteria andTruckFleetIdLessThanOrEqualTo(Integer value) {
            addCriterion("truck_fleet_id <=", value, "truckFleetId");
            return (Criteria) this;
        }

        public Criteria andTruckFleetIdIn(List<Integer> values) {
            addCriterion("truck_fleet_id in", values, "truckFleetId");
            return (Criteria) this;
        }

        public Criteria andTruckFleetIdNotIn(List<Integer> values) {
            addCriterion("truck_fleet_id not in", values, "truckFleetId");
            return (Criteria) this;
        }

        public Criteria andTruckFleetIdBetween(Integer value1, Integer value2) {
            addCriterion("truck_fleet_id between", value1, value2, "truckFleetId");
            return (Criteria) this;
        }

        public Criteria andTruckFleetIdNotBetween(Integer value1, Integer value2) {
            addCriterion("truck_fleet_id not between", value1, value2, "truckFleetId");
            return (Criteria) this;
        }

        public Criteria andTruckIdIsNull() {
            addCriterion("truck_id is null");
            return (Criteria) this;
        }

        public Criteria andTruckIdIsNotNull() {
            addCriterion("truck_id is not null");
            return (Criteria) this;
        }

        public Criteria andTruckIdEqualTo(Integer value) {
            addCriterion("truck_id =", value, "truckId");
            return (Criteria) this;
        }

        public Criteria andTruckIdNotEqualTo(Integer value) {
            addCriterion("truck_id <>", value, "truckId");
            return (Criteria) this;
        }

        public Criteria andTruckIdGreaterThan(Integer value) {
            addCriterion("truck_id >", value, "truckId");
            return (Criteria) this;
        }

        public Criteria andTruckIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("truck_id >=", value, "truckId");
            return (Criteria) this;
        }

        public Criteria andTruckIdLessThan(Integer value) {
            addCriterion("truck_id <", value, "truckId");
            return (Criteria) this;
        }

        public Criteria andTruckIdLessThanOrEqualTo(Integer value) {
            addCriterion("truck_id <=", value, "truckId");
            return (Criteria) this;
        }

        public Criteria andTruckIdIn(List<Integer> values) {
            addCriterion("truck_id in", values, "truckId");
            return (Criteria) this;
        }

        public Criteria andTruckIdNotIn(List<Integer> values) {
            addCriterion("truck_id not in", values, "truckId");
            return (Criteria) this;
        }

        public Criteria andTruckIdBetween(Integer value1, Integer value2) {
            addCriterion("truck_id between", value1, value2, "truckId");
            return (Criteria) this;
        }

        public Criteria andTruckIdNotBetween(Integer value1, Integer value2) {
            addCriterion("truck_id not between", value1, value2, "truckId");
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

        public Criteria andNoteIsNull() {
            addCriterion("note is null");
            return (Criteria) this;
        }

        public Criteria andNoteIsNotNull() {
            addCriterion("note is not null");
            return (Criteria) this;
        }

        public Criteria andNoteEqualTo(String value) {
            addCriterion("note =", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotEqualTo(String value) {
            addCriterion("note <>", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThan(String value) {
            addCriterion("note >", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThanOrEqualTo(String value) {
            addCriterion("note >=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThan(String value) {
            addCriterion("note <", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThanOrEqualTo(String value) {
            addCriterion("note <=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("note like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("note not like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLikeList(List<String> values) {
            addCriterion("note like", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteIn(List<String> values) {
            addCriterion("note in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotIn(List<String> values) {
            addCriterion("note not in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteBetween(String value1, String value2) {
            addCriterion("note between", value1, value2, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotBetween(String value1, String value2) {
            addCriterion("note not between", value1, value2, "note");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        private TruckFleetTruckExample example;

        protected Criteria(TruckFleetTruckExample example) {
            super();
            this.example = example;
        }

        public TruckFleetTruckExample example() {
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