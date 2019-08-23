package com.juma.tgm.customerManager.domain;

import java.util.ArrayList;
import java.util.List;

public class TaskWaybillTemplateTruckBindingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int startOffSet = -1;

    protected int size = -1;

    public TaskWaybillTemplateTruckBindingExample() {
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

        public Criteria andTaskWaybillTemplateTruckBindingIdIsNull() {
            addCriterion("task_waybill_template_truck_binding_id is null");
            return (Criteria) this;
        }

        public Criteria andTaskWaybillTemplateTruckBindingIdIsNotNull() {
            addCriterion("task_waybill_template_truck_binding_id is not null");
            return (Criteria) this;
        }

        public Criteria andTaskWaybillTemplateTruckBindingIdEqualTo(Integer value) {
            addCriterion("task_waybill_template_truck_binding_id =", value, "taskWaybillTemplateTruckBindingId");
            return (Criteria) this;
        }

        public Criteria andTaskWaybillTemplateTruckBindingIdNotEqualTo(Integer value) {
            addCriterion("task_waybill_template_truck_binding_id <>", value, "taskWaybillTemplateTruckBindingId");
            return (Criteria) this;
        }

        public Criteria andTaskWaybillTemplateTruckBindingIdGreaterThan(Integer value) {
            addCriterion("task_waybill_template_truck_binding_id >", value, "taskWaybillTemplateTruckBindingId");
            return (Criteria) this;
        }

        public Criteria andTaskWaybillTemplateTruckBindingIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("task_waybill_template_truck_binding_id >=", value, "taskWaybillTemplateTruckBindingId");
            return (Criteria) this;
        }

        public Criteria andTaskWaybillTemplateTruckBindingIdLessThan(Integer value) {
            addCriterion("task_waybill_template_truck_binding_id <", value, "taskWaybillTemplateTruckBindingId");
            return (Criteria) this;
        }

        public Criteria andTaskWaybillTemplateTruckBindingIdLessThanOrEqualTo(Integer value) {
            addCriterion("task_waybill_template_truck_binding_id <=", value, "taskWaybillTemplateTruckBindingId");
            return (Criteria) this;
        }

        public Criteria andTaskWaybillTemplateTruckBindingIdIn(List<Integer> values) {
            addCriterion("task_waybill_template_truck_binding_id in", values, "taskWaybillTemplateTruckBindingId");
            return (Criteria) this;
        }

        public Criteria andTaskWaybillTemplateTruckBindingIdNotIn(List<Integer> values) {
            addCriterion("task_waybill_template_truck_binding_id not in", values, "taskWaybillTemplateTruckBindingId");
            return (Criteria) this;
        }

        public Criteria andTaskWaybillTemplateTruckBindingIdBetween(Integer value1, Integer value2) {
            addCriterion("task_waybill_template_truck_binding_id between", value1, value2, "taskWaybillTemplateTruckBindingId");
            return (Criteria) this;
        }

        public Criteria andTaskWaybillTemplateTruckBindingIdNotBetween(Integer value1, Integer value2) {
            addCriterion("task_waybill_template_truck_binding_id not between", value1, value2, "taskWaybillTemplateTruckBindingId");
            return (Criteria) this;
        }

        public Criteria andTaskWaybillTemplateIdIsNull() {
            addCriterion("task_waybill_template_id is null");
            return (Criteria) this;
        }

        public Criteria andTaskWaybillTemplateIdIsNotNull() {
            addCriterion("task_waybill_template_id is not null");
            return (Criteria) this;
        }

        public Criteria andTaskWaybillTemplateIdEqualTo(Integer value) {
            addCriterion("task_waybill_template_id =", value, "taskWaybillTemplateId");
            return (Criteria) this;
        }

        public Criteria andTaskWaybillTemplateIdNotEqualTo(Integer value) {
            addCriterion("task_waybill_template_id <>", value, "taskWaybillTemplateId");
            return (Criteria) this;
        }

        public Criteria andTaskWaybillTemplateIdGreaterThan(Integer value) {
            addCriterion("task_waybill_template_id >", value, "taskWaybillTemplateId");
            return (Criteria) this;
        }

        public Criteria andTaskWaybillTemplateIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("task_waybill_template_id >=", value, "taskWaybillTemplateId");
            return (Criteria) this;
        }

        public Criteria andTaskWaybillTemplateIdLessThan(Integer value) {
            addCriterion("task_waybill_template_id <", value, "taskWaybillTemplateId");
            return (Criteria) this;
        }

        public Criteria andTaskWaybillTemplateIdLessThanOrEqualTo(Integer value) {
            addCriterion("task_waybill_template_id <=", value, "taskWaybillTemplateId");
            return (Criteria) this;
        }

        public Criteria andTaskWaybillTemplateIdIn(List<Integer> values) {
            addCriterion("task_waybill_template_id in", values, "taskWaybillTemplateId");
            return (Criteria) this;
        }

        public Criteria andTaskWaybillTemplateIdNotIn(List<Integer> values) {
            addCriterion("task_waybill_template_id not in", values, "taskWaybillTemplateId");
            return (Criteria) this;
        }

        public Criteria andTaskWaybillTemplateIdBetween(Integer value1, Integer value2) {
            addCriterion("task_waybill_template_id between", value1, value2, "taskWaybillTemplateId");
            return (Criteria) this;
        }

        public Criteria andTaskWaybillTemplateIdNotBetween(Integer value1, Integer value2) {
            addCriterion("task_waybill_template_id not between", value1, value2, "taskWaybillTemplateId");
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