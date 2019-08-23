package com.juma.tgm.manage.domain;

import java.util.Date;

import com.juma.tgm.base.domain.BaseDomain;

/**
 * operation_user - 后台用户
 * 
 * @author 2016-05-09
 * @version 1.0
 */
public class OperationUser extends BaseDomain {

    private static final long serialVersionUID = -3802497901190011207L;
    private Integer operationUserId;
    private Integer companyId;
    private Integer userId;
    private Integer status;
    private Integer sex;
    private Date birthday;
    private String contactPhone;

    public Integer getOperationUserId() {
        return operationUserId;
    }

    public void setOperationUserId(Integer operationUserId) {
        this.operationUserId = operationUserId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

}