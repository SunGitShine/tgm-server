package com.juma.tgm.common.workflow;

import org.apache.commons.lang3.StringUtils;

/**
 * 功能 :
 *
 * @author : Bruce(刘正航) 18:47 2019-04-03
 */
public enum ApprovalStatus {
    //
    APPROVAL_UN_SUBMIT(0,"UNSUBMIT","未提交"),
    APPROVAL_SUBMIT(1,"AUDITING","审核中"),
    APPROVAL_REJECTED(2,"REJECT","被驳回"),
    APPROVAL_AGREE(3,"AUDITED","已通过"),
    APPROVAL_REVOKE(4,"REVOKE","已撤销"),
    APPROVAL_GIVE_UP(5,"CANCEL","已放弃"),
    APPROVAL_PROCESS(6,"PROCESS","处理中"),

    ;
    private Integer code;
    private String workFlowKey;
    private String desc;

    ApprovalStatus(Integer code, String workFlowKey, String desc) {
        this.code = code;
        this.workFlowKey = workFlowKey;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getWorkFlowKey() {
        return workFlowKey;
    }

    public void setWorkFlowKey(String workFlowKey) {
        this.workFlowKey = workFlowKey;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static String getApprovalStatusDesc(Integer code){
        if( null == code ){
            return null;
        }
        for (ApprovalStatus status : ApprovalStatus.values()) {
            if( status.getCode().equals(code)){
                return status.getDesc();
            }
        }
        return null;
    }

    public static Integer getApprovalStatusWithWorkKey(String workFlowKey){
        if(StringUtils.isBlank(workFlowKey) ){
            return null;
        }
        for (ApprovalStatus status : ApprovalStatus.values()) {
            if( status.getWorkFlowKey().equals(workFlowKey) ){
                return status.getCode();
            }
        }
        return null;
    }

    public static ApprovalStatus getApprovalWithWorkKey(String workFlowKey){
        if(StringUtils.isBlank(workFlowKey) ){
            return null;
        }
        for (ApprovalStatus status : ApprovalStatus.values()) {
            if( status.getWorkFlowKey().equals(workFlowKey) ){
                return status;
            }
        }
        return null;
    }

    public static ApprovalStatus getApprovalWithCode(Integer code){
        if(null == code){
            return null;
        }
        for(ApprovalStatus status : ApprovalStatus.values()) {
            if(status.getCode().equals(code) ){
                return status;
            }
        }
        return null;
    }
}
