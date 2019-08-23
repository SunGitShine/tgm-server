package com.juma.tgm.customer.domain.vo;

import com.juma.auth.employee.domain.EmployeeInfo;
import com.juma.auth.user.domain.User;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: CustomerManager
 * @Description:
 * @author: liang
 * @date: 2017-05-26 14:55
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
public class CustomerManagerVo implements Serializable {

    /**
     * 客户经理基础信息
     */
    private User user;

    /**
     * 客户经理信息
     */
    private EmployeeInfo employeeInfo;

    /** 所属区域 */
    private String region;
    /** 注册时间 */
    private String createDate;
    /** 注册时间 */
    private Date createTime;
    /** 平均分 */
    private float score;
    /**
     * 是否有车队
     */
    private boolean hasTruckFleet = false;
    /**客户经理客户数*/
    private int count;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public boolean isHasTruckFleet() {
        return hasTruckFleet;
    }

    public void setHasTruckFleet(boolean hasTruckFleet) {
        this.hasTruckFleet = hasTruckFleet;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public EmployeeInfo getEmployeeInfo() {
        return employeeInfo;
    }

    public void setEmployeeInfo(EmployeeInfo employeeInfo) {
        this.employeeInfo = employeeInfo;
    }
}
