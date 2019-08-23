package com.juma.tgm.fms.domain.v3;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdjustForFreightAttachExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int startOffSet = -1;

    protected int size = -1;

    public AdjustForFreightAttachExample() {
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

    public AdjustForFreightAttachExample orderBy(String ... orderByClauses) {
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

        public Criteria andAttachIdIsNull() {
            addCriterion("attach_id is null");
            return (Criteria) this;
        }

        public Criteria andAttachIdIsNotNull() {
            addCriterion("attach_id is not null");
            return (Criteria) this;
        }

        public Criteria andAttachIdEqualTo(Integer value) {
            addCriterion("attach_id =", value, "attachId");
            return (Criteria) this;
        }

        public Criteria andAttachIdNotEqualTo(Integer value) {
            addCriterion("attach_id <>", value, "attachId");
            return (Criteria) this;
        }

        public Criteria andAttachIdGreaterThan(Integer value) {
            addCriterion("attach_id >", value, "attachId");
            return (Criteria) this;
        }

        public Criteria andAttachIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("attach_id >=", value, "attachId");
            return (Criteria) this;
        }

        public Criteria andAttachIdLessThan(Integer value) {
            addCriterion("attach_id <", value, "attachId");
            return (Criteria) this;
        }

        public Criteria andAttachIdLessThanOrEqualTo(Integer value) {
            addCriterion("attach_id <=", value, "attachId");
            return (Criteria) this;
        }

        public Criteria andAttachIdIn(List<Integer> values) {
            addCriterion("attach_id in", values, "attachId");
            return (Criteria) this;
        }

        public Criteria andAttachIdNotIn(List<Integer> values) {
            addCriterion("attach_id not in", values, "attachId");
            return (Criteria) this;
        }

        public Criteria andAttachIdBetween(Integer value1, Integer value2) {
            addCriterion("attach_id between", value1, value2, "attachId");
            return (Criteria) this;
        }

        public Criteria andAttachIdNotBetween(Integer value1, Integer value2) {
            addCriterion("attach_id not between", value1, value2, "attachId");
            return (Criteria) this;
        }

        public Criteria andAdjustIdIsNull() {
            addCriterion("adjust_id is null");
            return (Criteria) this;
        }

        public Criteria andAdjustIdIsNotNull() {
            addCriterion("adjust_id is not null");
            return (Criteria) this;
        }

        public Criteria andAdjustIdEqualTo(Integer value) {
            addCriterion("adjust_id =", value, "adjustId");
            return (Criteria) this;
        }

        public Criteria andAdjustIdNotEqualTo(Integer value) {
            addCriterion("adjust_id <>", value, "adjustId");
            return (Criteria) this;
        }

        public Criteria andAdjustIdGreaterThan(Integer value) {
            addCriterion("adjust_id >", value, "adjustId");
            return (Criteria) this;
        }

        public Criteria andAdjustIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("adjust_id >=", value, "adjustId");
            return (Criteria) this;
        }

        public Criteria andAdjustIdLessThan(Integer value) {
            addCriterion("adjust_id <", value, "adjustId");
            return (Criteria) this;
        }

        public Criteria andAdjustIdLessThanOrEqualTo(Integer value) {
            addCriterion("adjust_id <=", value, "adjustId");
            return (Criteria) this;
        }

        public Criteria andAdjustIdIn(List<Integer> values) {
            addCriterion("adjust_id in", values, "adjustId");
            return (Criteria) this;
        }

        public Criteria andAdjustIdNotIn(List<Integer> values) {
            addCriterion("adjust_id not in", values, "adjustId");
            return (Criteria) this;
        }

        public Criteria andAdjustIdBetween(Integer value1, Integer value2) {
            addCriterion("adjust_id between", value1, value2, "adjustId");
            return (Criteria) this;
        }

        public Criteria andAdjustIdNotBetween(Integer value1, Integer value2) {
            addCriterion("adjust_id not between", value1, value2, "adjustId");
            return (Criteria) this;
        }

        public Criteria andCarryProofAttachIsNull() {
            addCriterion("carry_proof_attach is null");
            return (Criteria) this;
        }

        public Criteria andCarryProofAttachIsNotNull() {
            addCriterion("carry_proof_attach is not null");
            return (Criteria) this;
        }

        public Criteria andCarryProofAttachEqualTo(String value) {
            addCriterion("carry_proof_attach =", value, "carryProofAttach");
            return (Criteria) this;
        }

        public Criteria andCarryProofAttachNotEqualTo(String value) {
            addCriterion("carry_proof_attach <>", value, "carryProofAttach");
            return (Criteria) this;
        }

        public Criteria andCarryProofAttachGreaterThan(String value) {
            addCriterion("carry_proof_attach >", value, "carryProofAttach");
            return (Criteria) this;
        }

        public Criteria andCarryProofAttachGreaterThanOrEqualTo(String value) {
            addCriterion("carry_proof_attach >=", value, "carryProofAttach");
            return (Criteria) this;
        }

        public Criteria andCarryProofAttachLessThan(String value) {
            addCriterion("carry_proof_attach <", value, "carryProofAttach");
            return (Criteria) this;
        }

        public Criteria andCarryProofAttachLessThanOrEqualTo(String value) {
            addCriterion("carry_proof_attach <=", value, "carryProofAttach");
            return (Criteria) this;
        }

        public Criteria andCarryProofAttachLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("carry_proof_attach like", value, "carryProofAttach");
            return (Criteria) this;
        }

        public Criteria andCarryProofAttachNotLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("carry_proof_attach not like", value, "carryProofAttach");
            return (Criteria) this;
        }

        public Criteria andCarryProofAttachLikeList(List<String> values) {
            addCriterion("carry_proof_attach like", values, "carryProofAttach");
            return (Criteria) this;
        }

        public Criteria andCarryProofAttachIn(List<String> values) {
            addCriterion("carry_proof_attach in", values, "carryProofAttach");
            return (Criteria) this;
        }

        public Criteria andCarryProofAttachNotIn(List<String> values) {
            addCriterion("carry_proof_attach not in", values, "carryProofAttach");
            return (Criteria) this;
        }

        public Criteria andCarryProofAttachBetween(String value1, String value2) {
            addCriterion("carry_proof_attach between", value1, value2, "carryProofAttach");
            return (Criteria) this;
        }

        public Criteria andCarryProofAttachNotBetween(String value1, String value2) {
            addCriterion("carry_proof_attach not between", value1, value2, "carryProofAttach");
            return (Criteria) this;
        }

        public Criteria andWorkloadProofAttachIsNull() {
            addCriterion("workload_proof_attach is null");
            return (Criteria) this;
        }

        public Criteria andWorkloadProofAttachIsNotNull() {
            addCriterion("workload_proof_attach is not null");
            return (Criteria) this;
        }

        public Criteria andWorkloadProofAttachEqualTo(String value) {
            addCriterion("workload_proof_attach =", value, "workloadProofAttach");
            return (Criteria) this;
        }

        public Criteria andWorkloadProofAttachNotEqualTo(String value) {
            addCriterion("workload_proof_attach <>", value, "workloadProofAttach");
            return (Criteria) this;
        }

        public Criteria andWorkloadProofAttachGreaterThan(String value) {
            addCriterion("workload_proof_attach >", value, "workloadProofAttach");
            return (Criteria) this;
        }

        public Criteria andWorkloadProofAttachGreaterThanOrEqualTo(String value) {
            addCriterion("workload_proof_attach >=", value, "workloadProofAttach");
            return (Criteria) this;
        }

        public Criteria andWorkloadProofAttachLessThan(String value) {
            addCriterion("workload_proof_attach <", value, "workloadProofAttach");
            return (Criteria) this;
        }

        public Criteria andWorkloadProofAttachLessThanOrEqualTo(String value) {
            addCriterion("workload_proof_attach <=", value, "workloadProofAttach");
            return (Criteria) this;
        }

        public Criteria andWorkloadProofAttachLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("workload_proof_attach like", value, "workloadProofAttach");
            return (Criteria) this;
        }

        public Criteria andWorkloadProofAttachNotLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("workload_proof_attach not like", value, "workloadProofAttach");
            return (Criteria) this;
        }

        public Criteria andWorkloadProofAttachLikeList(List<String> values) {
            addCriterion("workload_proof_attach like", values, "workloadProofAttach");
            return (Criteria) this;
        }

        public Criteria andWorkloadProofAttachIn(List<String> values) {
            addCriterion("workload_proof_attach in", values, "workloadProofAttach");
            return (Criteria) this;
        }

        public Criteria andWorkloadProofAttachNotIn(List<String> values) {
            addCriterion("workload_proof_attach not in", values, "workloadProofAttach");
            return (Criteria) this;
        }

        public Criteria andWorkloadProofAttachBetween(String value1, String value2) {
            addCriterion("workload_proof_attach between", value1, value2, "workloadProofAttach");
            return (Criteria) this;
        }

        public Criteria andWorkloadProofAttachNotBetween(String value1, String value2) {
            addCriterion("workload_proof_attach not between", value1, value2, "workloadProofAttach");
            return (Criteria) this;
        }

        public Criteria andUpstairsProofAttachIsNull() {
            addCriterion("upstairs_proof_attach is null");
            return (Criteria) this;
        }

        public Criteria andUpstairsProofAttachIsNotNull() {
            addCriterion("upstairs_proof_attach is not null");
            return (Criteria) this;
        }

        public Criteria andUpstairsProofAttachEqualTo(String value) {
            addCriterion("upstairs_proof_attach =", value, "upstairsProofAttach");
            return (Criteria) this;
        }

        public Criteria andUpstairsProofAttachNotEqualTo(String value) {
            addCriterion("upstairs_proof_attach <>", value, "upstairsProofAttach");
            return (Criteria) this;
        }

        public Criteria andUpstairsProofAttachGreaterThan(String value) {
            addCriterion("upstairs_proof_attach >", value, "upstairsProofAttach");
            return (Criteria) this;
        }

        public Criteria andUpstairsProofAttachGreaterThanOrEqualTo(String value) {
            addCriterion("upstairs_proof_attach >=", value, "upstairsProofAttach");
            return (Criteria) this;
        }

        public Criteria andUpstairsProofAttachLessThan(String value) {
            addCriterion("upstairs_proof_attach <", value, "upstairsProofAttach");
            return (Criteria) this;
        }

        public Criteria andUpstairsProofAttachLessThanOrEqualTo(String value) {
            addCriterion("upstairs_proof_attach <=", value, "upstairsProofAttach");
            return (Criteria) this;
        }

        public Criteria andUpstairsProofAttachLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("upstairs_proof_attach like", value, "upstairsProofAttach");
            return (Criteria) this;
        }

        public Criteria andUpstairsProofAttachNotLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("upstairs_proof_attach not like", value, "upstairsProofAttach");
            return (Criteria) this;
        }

        public Criteria andUpstairsProofAttachLikeList(List<String> values) {
            addCriterion("upstairs_proof_attach like", values, "upstairsProofAttach");
            return (Criteria) this;
        }

        public Criteria andUpstairsProofAttachIn(List<String> values) {
            addCriterion("upstairs_proof_attach in", values, "upstairsProofAttach");
            return (Criteria) this;
        }

        public Criteria andUpstairsProofAttachNotIn(List<String> values) {
            addCriterion("upstairs_proof_attach not in", values, "upstairsProofAttach");
            return (Criteria) this;
        }

        public Criteria andUpstairsProofAttachBetween(String value1, String value2) {
            addCriterion("upstairs_proof_attach between", value1, value2, "upstairsProofAttach");
            return (Criteria) this;
        }

        public Criteria andUpstairsProofAttachNotBetween(String value1, String value2) {
            addCriterion("upstairs_proof_attach not between", value1, value2, "upstairsProofAttach");
            return (Criteria) this;
        }

        public Criteria andFineProofAttachIsNull() {
            addCriterion("fine_proof_attach is null");
            return (Criteria) this;
        }

        public Criteria andFineProofAttachIsNotNull() {
            addCriterion("fine_proof_attach is not null");
            return (Criteria) this;
        }

        public Criteria andFineProofAttachEqualTo(String value) {
            addCriterion("fine_proof_attach =", value, "fineProofAttach");
            return (Criteria) this;
        }

        public Criteria andFineProofAttachNotEqualTo(String value) {
            addCriterion("fine_proof_attach <>", value, "fineProofAttach");
            return (Criteria) this;
        }

        public Criteria andFineProofAttachGreaterThan(String value) {
            addCriterion("fine_proof_attach >", value, "fineProofAttach");
            return (Criteria) this;
        }

        public Criteria andFineProofAttachGreaterThanOrEqualTo(String value) {
            addCriterion("fine_proof_attach >=", value, "fineProofAttach");
            return (Criteria) this;
        }

        public Criteria andFineProofAttachLessThan(String value) {
            addCriterion("fine_proof_attach <", value, "fineProofAttach");
            return (Criteria) this;
        }

        public Criteria andFineProofAttachLessThanOrEqualTo(String value) {
            addCriterion("fine_proof_attach <=", value, "fineProofAttach");
            return (Criteria) this;
        }

        public Criteria andFineProofAttachLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("fine_proof_attach like", value, "fineProofAttach");
            return (Criteria) this;
        }

        public Criteria andFineProofAttachNotLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("fine_proof_attach not like", value, "fineProofAttach");
            return (Criteria) this;
        }

        public Criteria andFineProofAttachLikeList(List<String> values) {
            addCriterion("fine_proof_attach like", values, "fineProofAttach");
            return (Criteria) this;
        }

        public Criteria andFineProofAttachIn(List<String> values) {
            addCriterion("fine_proof_attach in", values, "fineProofAttach");
            return (Criteria) this;
        }

        public Criteria andFineProofAttachNotIn(List<String> values) {
            addCriterion("fine_proof_attach not in", values, "fineProofAttach");
            return (Criteria) this;
        }

        public Criteria andFineProofAttachBetween(String value1, String value2) {
            addCriterion("fine_proof_attach between", value1, value2, "fineProofAttach");
            return (Criteria) this;
        }

        public Criteria andFineProofAttachNotBetween(String value1, String value2) {
            addCriterion("fine_proof_attach not between", value1, value2, "fineProofAttach");
            return (Criteria) this;
        }

        public Criteria andCargoLossProofAttachIsNull() {
            addCriterion("cargo_loss_proof_attach is null");
            return (Criteria) this;
        }

        public Criteria andCargoLossProofAttachIsNotNull() {
            addCriterion("cargo_loss_proof_attach is not null");
            return (Criteria) this;
        }

        public Criteria andCargoLossProofAttachEqualTo(String value) {
            addCriterion("cargo_loss_proof_attach =", value, "cargoLossProofAttach");
            return (Criteria) this;
        }

        public Criteria andCargoLossProofAttachNotEqualTo(String value) {
            addCriterion("cargo_loss_proof_attach <>", value, "cargoLossProofAttach");
            return (Criteria) this;
        }

        public Criteria andCargoLossProofAttachGreaterThan(String value) {
            addCriterion("cargo_loss_proof_attach >", value, "cargoLossProofAttach");
            return (Criteria) this;
        }

        public Criteria andCargoLossProofAttachGreaterThanOrEqualTo(String value) {
            addCriterion("cargo_loss_proof_attach >=", value, "cargoLossProofAttach");
            return (Criteria) this;
        }

        public Criteria andCargoLossProofAttachLessThan(String value) {
            addCriterion("cargo_loss_proof_attach <", value, "cargoLossProofAttach");
            return (Criteria) this;
        }

        public Criteria andCargoLossProofAttachLessThanOrEqualTo(String value) {
            addCriterion("cargo_loss_proof_attach <=", value, "cargoLossProofAttach");
            return (Criteria) this;
        }

        public Criteria andCargoLossProofAttachLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("cargo_loss_proof_attach like", value, "cargoLossProofAttach");
            return (Criteria) this;
        }

        public Criteria andCargoLossProofAttachNotLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("cargo_loss_proof_attach not like", value, "cargoLossProofAttach");
            return (Criteria) this;
        }

        public Criteria andCargoLossProofAttachLikeList(List<String> values) {
            addCriterion("cargo_loss_proof_attach like", values, "cargoLossProofAttach");
            return (Criteria) this;
        }

        public Criteria andCargoLossProofAttachIn(List<String> values) {
            addCriterion("cargo_loss_proof_attach in", values, "cargoLossProofAttach");
            return (Criteria) this;
        }

        public Criteria andCargoLossProofAttachNotIn(List<String> values) {
            addCriterion("cargo_loss_proof_attach not in", values, "cargoLossProofAttach");
            return (Criteria) this;
        }

        public Criteria andCargoLossProofAttachBetween(String value1, String value2) {
            addCriterion("cargo_loss_proof_attach between", value1, value2, "cargoLossProofAttach");
            return (Criteria) this;
        }

        public Criteria andCargoLossProofAttachNotBetween(String value1, String value2) {
            addCriterion("cargo_loss_proof_attach not between", value1, value2, "cargoLossProofAttach");
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
    }

    public static class Criteria extends GeneratedCriteria {
        private AdjustForFreightAttachExample example;

        protected Criteria(AdjustForFreightAttachExample example) {
            super();
            this.example = example;
        }

        public AdjustForFreightAttachExample example() {
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