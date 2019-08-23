package com.juma.tgm.customer.domain;

import java.io.Serializable;


import com.juma.auth.user.domain.User;
import me.about.poi.ExcelColumn;

/**
 * @ClassName TruckCustomerExport.java
 * @Description 货主信息导出
 * @author Libin.Wei
 * @Date 2017年2月21日 下午2:08:39
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class TruckCustomerExport implements Serializable {

    private static final long serialVersionUID = -1824592817581301229L;
    @ExcelColumn(name = "姓名", width = 30)
    private String nickname;
    @ExcelColumn(name = "联系电话", width = 30)
    private String contactPhone;
    @ExcelColumn(name = "身份证号", width = 30)
    private String identityCardNo;
    @ExcelColumn(name = "注册时间", width = 30)
    private String registerDate;
    @ExcelColumn(name = "居住地", width = 30)
    private String domicile;
    @ExcelColumn(name = "所属组织", width = 30)
    private String departmentName;
    @ExcelColumn(name = "邀请码", width = 30)
    private String inviteCode;

    private Integer truckCustomerId;
    private Integer userId;

    public TruckCustomerExport() {
    }

    public TruckCustomerExport(User user, TruckCustomer truckCustomer) {
        super();
        this.nickname = user.getName();
        this.contactPhone = user.getMobileNumber();
        this.identityCardNo = user.getIdentityCardNo();
        if (null != truckCustomer) {
            this.truckCustomerId = truckCustomer.getTruckCustomerId();
            this.userId = truckCustomer.getUserId();
        }
    }

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

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getDomicile() {
        return domicile;
    }

    public void setDomicile(String domicile) {
        this.domicile = domicile;
    }

    public String getIdentityCardNo() {
        return identityCardNo;
    }

    public void setIdentityCardNo(String identityCardNo) {
        this.identityCardNo = identityCardNo;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public Integer getTruckCustomerId() {
        return truckCustomerId;
    }

    public void setTruckCustomerId(Integer truckCustomerId) {
        this.truckCustomerId = truckCustomerId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

}
