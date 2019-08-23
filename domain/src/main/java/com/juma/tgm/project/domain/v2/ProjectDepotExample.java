package com.juma.tgm.project.domain.v2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectDepotExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int startOffSet = -1;

    protected int size = -1;

    public ProjectDepotExample() {
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

    public ProjectDepotExample orderBy(String ... orderByClauses) {
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

    public ProjectDepotExample limit(int pageNo, int pageSize) {
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

        public Criteria andDepotIdIsNull() {
            addCriterion("depot_id is null");
            return (Criteria) this;
        }

        public Criteria andDepotIdIsNotNull() {
            addCriterion("depot_id is not null");
            return (Criteria) this;
        }

        public Criteria andDepotIdEqualTo(Integer value) {
            addCriterion("depot_id =", value, "depotId");
            return (Criteria) this;
        }

        public Criteria andDepotIdNotEqualTo(Integer value) {
            addCriterion("depot_id <>", value, "depotId");
            return (Criteria) this;
        }

        public Criteria andDepotIdGreaterThan(Integer value) {
            addCriterion("depot_id >", value, "depotId");
            return (Criteria) this;
        }

        public Criteria andDepotIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("depot_id >=", value, "depotId");
            return (Criteria) this;
        }

        public Criteria andDepotIdLessThan(Integer value) {
            addCriterion("depot_id <", value, "depotId");
            return (Criteria) this;
        }

        public Criteria andDepotIdLessThanOrEqualTo(Integer value) {
            addCriterion("depot_id <=", value, "depotId");
            return (Criteria) this;
        }

        public Criteria andDepotIdIn(List<Integer> values) {
            addCriterion("depot_id in", values, "depotId");
            return (Criteria) this;
        }

        public Criteria andDepotIdNotIn(List<Integer> values) {
            addCriterion("depot_id not in", values, "depotId");
            return (Criteria) this;
        }

        public Criteria andDepotIdBetween(Integer value1, Integer value2) {
            addCriterion("depot_id between", value1, value2, "depotId");
            return (Criteria) this;
        }

        public Criteria andDepotIdNotBetween(Integer value1, Integer value2) {
            addCriterion("depot_id not between", value1, value2, "depotId");
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

        public Criteria andDepotNameIsNull() {
            addCriterion("depot_name is null");
            return (Criteria) this;
        }

        public Criteria andDepotNameIsNotNull() {
            addCriterion("depot_name is not null");
            return (Criteria) this;
        }

        public Criteria andDepotNameEqualTo(String value) {
            addCriterion("depot_name =", value, "depotName");
            return (Criteria) this;
        }

        public Criteria andDepotNameNotEqualTo(String value) {
            addCriterion("depot_name <>", value, "depotName");
            return (Criteria) this;
        }

        public Criteria andDepotNameGreaterThan(String value) {
            addCriterion("depot_name >", value, "depotName");
            return (Criteria) this;
        }

        public Criteria andDepotNameGreaterThanOrEqualTo(String value) {
            addCriterion("depot_name >=", value, "depotName");
            return (Criteria) this;
        }

        public Criteria andDepotNameLessThan(String value) {
            addCriterion("depot_name <", value, "depotName");
            return (Criteria) this;
        }

        public Criteria andDepotNameLessThanOrEqualTo(String value) {
            addCriterion("depot_name <=", value, "depotName");
            return (Criteria) this;
        }

        public Criteria andDepotNameLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("depot_name like", value, "depotName");
            return (Criteria) this;
        }

        public Criteria andDepotNameNotLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("depot_name not like", value, "depotName");
            return (Criteria) this;
        }

        public Criteria andDepotNameLikeList(List<String> values) {
            addCriterion("depot_name like", values, "depotName");
            return (Criteria) this;
        }

        public Criteria andDepotNameIn(List<String> values) {
            addCriterion("depot_name in", values, "depotName");
            return (Criteria) this;
        }

        public Criteria andDepotNameNotIn(List<String> values) {
            addCriterion("depot_name not in", values, "depotName");
            return (Criteria) this;
        }

        public Criteria andDepotNameBetween(String value1, String value2) {
            addCriterion("depot_name between", value1, value2, "depotName");
            return (Criteria) this;
        }

        public Criteria andDepotNameNotBetween(String value1, String value2) {
            addCriterion("depot_name not between", value1, value2, "depotName");
            return (Criteria) this;
        }

        public Criteria andDepotAddressIsNull() {
            addCriterion("depot_address is null");
            return (Criteria) this;
        }

        public Criteria andDepotAddressIsNotNull() {
            addCriterion("depot_address is not null");
            return (Criteria) this;
        }

        public Criteria andDepotAddressEqualTo(String value) {
            addCriterion("depot_address =", value, "depotAddress");
            return (Criteria) this;
        }

        public Criteria andDepotAddressNotEqualTo(String value) {
            addCriterion("depot_address <>", value, "depotAddress");
            return (Criteria) this;
        }

        public Criteria andDepotAddressGreaterThan(String value) {
            addCriterion("depot_address >", value, "depotAddress");
            return (Criteria) this;
        }

        public Criteria andDepotAddressGreaterThanOrEqualTo(String value) {
            addCriterion("depot_address >=", value, "depotAddress");
            return (Criteria) this;
        }

        public Criteria andDepotAddressLessThan(String value) {
            addCriterion("depot_address <", value, "depotAddress");
            return (Criteria) this;
        }

        public Criteria andDepotAddressLessThanOrEqualTo(String value) {
            addCriterion("depot_address <=", value, "depotAddress");
            return (Criteria) this;
        }

        public Criteria andDepotAddressLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("depot_address like", value, "depotAddress");
            return (Criteria) this;
        }

        public Criteria andDepotAddressNotLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("depot_address not like", value, "depotAddress");
            return (Criteria) this;
        }

        public Criteria andDepotAddressLikeList(List<String> values) {
            addCriterion("depot_address like", values, "depotAddress");
            return (Criteria) this;
        }

        public Criteria andDepotAddressIn(List<String> values) {
            addCriterion("depot_address in", values, "depotAddress");
            return (Criteria) this;
        }

        public Criteria andDepotAddressNotIn(List<String> values) {
            addCriterion("depot_address not in", values, "depotAddress");
            return (Criteria) this;
        }

        public Criteria andDepotAddressBetween(String value1, String value2) {
            addCriterion("depot_address between", value1, value2, "depotAddress");
            return (Criteria) this;
        }

        public Criteria andDepotAddressNotBetween(String value1, String value2) {
            addCriterion("depot_address not between", value1, value2, "depotAddress");
            return (Criteria) this;
        }

        public Criteria andDepotCoordinatesIsNull() {
            addCriterion("depot_coordinates is null");
            return (Criteria) this;
        }

        public Criteria andDepotCoordinatesIsNotNull() {
            addCriterion("depot_coordinates is not null");
            return (Criteria) this;
        }

        public Criteria andDepotCoordinatesEqualTo(String value) {
            addCriterion("depot_coordinates =", value, "depotCoordinates");
            return (Criteria) this;
        }

        public Criteria andDepotCoordinatesNotEqualTo(String value) {
            addCriterion("depot_coordinates <>", value, "depotCoordinates");
            return (Criteria) this;
        }

        public Criteria andDepotCoordinatesGreaterThan(String value) {
            addCriterion("depot_coordinates >", value, "depotCoordinates");
            return (Criteria) this;
        }

        public Criteria andDepotCoordinatesGreaterThanOrEqualTo(String value) {
            addCriterion("depot_coordinates >=", value, "depotCoordinates");
            return (Criteria) this;
        }

        public Criteria andDepotCoordinatesLessThan(String value) {
            addCriterion("depot_coordinates <", value, "depotCoordinates");
            return (Criteria) this;
        }

        public Criteria andDepotCoordinatesLessThanOrEqualTo(String value) {
            addCriterion("depot_coordinates <=", value, "depotCoordinates");
            return (Criteria) this;
        }

        public Criteria andDepotCoordinatesLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("depot_coordinates like", value, "depotCoordinates");
            return (Criteria) this;
        }

        public Criteria andDepotCoordinatesNotLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("depot_coordinates not like", value, "depotCoordinates");
            return (Criteria) this;
        }

        public Criteria andDepotCoordinatesLikeList(List<String> values) {
            addCriterion("depot_coordinates like", values, "depotCoordinates");
            return (Criteria) this;
        }

        public Criteria andDepotCoordinatesIn(List<String> values) {
            addCriterion("depot_coordinates in", values, "depotCoordinates");
            return (Criteria) this;
        }

        public Criteria andDepotCoordinatesNotIn(List<String> values) {
            addCriterion("depot_coordinates not in", values, "depotCoordinates");
            return (Criteria) this;
        }

        public Criteria andDepotCoordinatesBetween(String value1, String value2) {
            addCriterion("depot_coordinates between", value1, value2, "depotCoordinates");
            return (Criteria) this;
        }

        public Criteria andDepotCoordinatesNotBetween(String value1, String value2) {
            addCriterion("depot_coordinates not between", value1, value2, "depotCoordinates");
            return (Criteria) this;
        }

        public Criteria andLinkManIsNull() {
            addCriterion("link_man is null");
            return (Criteria) this;
        }

        public Criteria andLinkManIsNotNull() {
            addCriterion("link_man is not null");
            return (Criteria) this;
        }

        public Criteria andLinkManEqualTo(String value) {
            addCriterion("link_man =", value, "linkMan");
            return (Criteria) this;
        }

        public Criteria andLinkManNotEqualTo(String value) {
            addCriterion("link_man <>", value, "linkMan");
            return (Criteria) this;
        }

        public Criteria andLinkManGreaterThan(String value) {
            addCriterion("link_man >", value, "linkMan");
            return (Criteria) this;
        }

        public Criteria andLinkManGreaterThanOrEqualTo(String value) {
            addCriterion("link_man >=", value, "linkMan");
            return (Criteria) this;
        }

        public Criteria andLinkManLessThan(String value) {
            addCriterion("link_man <", value, "linkMan");
            return (Criteria) this;
        }

        public Criteria andLinkManLessThanOrEqualTo(String value) {
            addCriterion("link_man <=", value, "linkMan");
            return (Criteria) this;
        }

        public Criteria andLinkManLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("link_man like", value, "linkMan");
            return (Criteria) this;
        }

        public Criteria andLinkManNotLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("link_man not like", value, "linkMan");
            return (Criteria) this;
        }

        public Criteria andLinkManLikeList(List<String> values) {
            addCriterion("link_man like", values, "linkMan");
            return (Criteria) this;
        }

        public Criteria andLinkManIn(List<String> values) {
            addCriterion("link_man in", values, "linkMan");
            return (Criteria) this;
        }

        public Criteria andLinkManNotIn(List<String> values) {
            addCriterion("link_man not in", values, "linkMan");
            return (Criteria) this;
        }

        public Criteria andLinkManBetween(String value1, String value2) {
            addCriterion("link_man between", value1, value2, "linkMan");
            return (Criteria) this;
        }

        public Criteria andLinkManNotBetween(String value1, String value2) {
            addCriterion("link_man not between", value1, value2, "linkMan");
            return (Criteria) this;
        }

        public Criteria andLinkManPhoneIsNull() {
            addCriterion("link_man_phone is null");
            return (Criteria) this;
        }

        public Criteria andLinkManPhoneIsNotNull() {
            addCriterion("link_man_phone is not null");
            return (Criteria) this;
        }

        public Criteria andLinkManPhoneEqualTo(String value) {
            addCriterion("link_man_phone =", value, "linkManPhone");
            return (Criteria) this;
        }

        public Criteria andLinkManPhoneNotEqualTo(String value) {
            addCriterion("link_man_phone <>", value, "linkManPhone");
            return (Criteria) this;
        }

        public Criteria andLinkManPhoneGreaterThan(String value) {
            addCriterion("link_man_phone >", value, "linkManPhone");
            return (Criteria) this;
        }

        public Criteria andLinkManPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("link_man_phone >=", value, "linkManPhone");
            return (Criteria) this;
        }

        public Criteria andLinkManPhoneLessThan(String value) {
            addCriterion("link_man_phone <", value, "linkManPhone");
            return (Criteria) this;
        }

        public Criteria andLinkManPhoneLessThanOrEqualTo(String value) {
            addCriterion("link_man_phone <=", value, "linkManPhone");
            return (Criteria) this;
        }

        public Criteria andLinkManPhoneLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("link_man_phone like", value, "linkManPhone");
            return (Criteria) this;
        }

        public Criteria andLinkManPhoneNotLike(String value) {
            if(value == null || value.trim().length() == 0) return (Criteria) this;
            String newVaule = value.replaceAll("%", "").trim();
            if(newVaule.contains("null")) return (Criteria) this;
            addCriterion("link_man_phone not like", value, "linkManPhone");
            return (Criteria) this;
        }

        public Criteria andLinkManPhoneLikeList(List<String> values) {
            addCriterion("link_man_phone like", values, "linkManPhone");
            return (Criteria) this;
        }

        public Criteria andLinkManPhoneIn(List<String> values) {
            addCriterion("link_man_phone in", values, "linkManPhone");
            return (Criteria) this;
        }

        public Criteria andLinkManPhoneNotIn(List<String> values) {
            addCriterion("link_man_phone not in", values, "linkManPhone");
            return (Criteria) this;
        }

        public Criteria andLinkManPhoneBetween(String value1, String value2) {
            addCriterion("link_man_phone between", value1, value2, "linkManPhone");
            return (Criteria) this;
        }

        public Criteria andLinkManPhoneNotBetween(String value1, String value2) {
            addCriterion("link_man_phone not between", value1, value2, "linkManPhone");
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
        private ProjectDepotExample example;

        protected Criteria(ProjectDepotExample example) {
            super();
            this.example = example;
        }

        public ProjectDepotExample example() {
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