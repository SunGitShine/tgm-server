package com.juma.tgm.waybill.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class QueryRule implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 8774408853784334119L;

    private List<String> ruleKeys;
    
    private List<String> roleKeys;
    
    private List<Integer> departmentIds = new ArrayList<Integer>();
    
    private List<String> regionCodes = new ArrayList<String>();
    
    
    public enum RuleKey {
        DEPARTMENT(1,"本组织"),NODEPARTMET(2,"非本组织");
        
        private int code;
        
        private String descr;
        
        private RuleKey(int code,String descr) {
            this.code = code;
            this.descr = descr;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDescr() {
            return descr;
        }

        public void setDescr(String descr) {
            this.descr = descr;
        }
    }



    public List<String> getRuleKeys() {
        return ruleKeys;
    }



    public void setRuleKeys(List<String> ruleKeys) {
        this.ruleKeys = ruleKeys;
    }



    public List<String> getRoleKeys() {
        return roleKeys;
    }



    public void setRoleKeys(List<String> roleKeys) {
        this.roleKeys = roleKeys;
    }



    public List<Integer> getDepartmentIds() {
        return departmentIds;
    }



    public void setDepartmentIds(List<Integer> departmentIds) {
        this.departmentIds = departmentIds;
    }



    public List<String> getRegionCodes() {
        return regionCodes;
    }



    public void setRegionCodes(List<String> regionCodes) {
        this.regionCodes = regionCodes;
    }
}
