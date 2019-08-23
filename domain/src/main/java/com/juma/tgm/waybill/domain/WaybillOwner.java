package com.juma.tgm.waybill.domain;

import java.io.Serializable;

public class WaybillOwner implements Serializable {


    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 6783369357047836057L;

    private Integer employeeId;
    
    private Integer userId;
    
    private String nickname;
    
    private String contactPhone;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
