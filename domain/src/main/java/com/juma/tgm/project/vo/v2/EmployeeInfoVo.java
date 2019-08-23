package com.juma.tgm.project.vo.v2;

import java.io.Serializable;
import java.util.Objects;

public class EmployeeInfoVo implements Serializable {

    private Integer employeeId;
    private Integer userId;
    private String name;
    private String mobileNumber;

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeInfoVo that = (EmployeeInfoVo) o;
        return Objects.equals(employeeId, that.employeeId) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(mobileNumber, that.mobileNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, userId, name, mobileNumber);
    }
}
