/**
* @Title: TruckCustomerBo.java
* @Package com.juma.tgm.customer.domain
*<B>Copyright</B> Copyright (c) 2016 www.jumapeisong.com All rights reserved. <br />
* 本软件源代码版权归驹马,未经许可不得任意复制与传播.<br />
* <B>Company</B> 驹马配送
* @date 2016年5月16日 下午5:57:46
* @version V1.0  
 */
package com.juma.tgm.customer.domain;

import java.io.Serializable;
import java.util.Date;

import com.juma.auth.user.domain.User;


/**
 * @Description:
 * @author hugo
 * @date 2016年5月16日 下午5:57:46
 * @version V1.0
 */
public class TruckCustomerBo implements Serializable {

    private static final long serialVersionUID = -9058682862393710756L;
    private TruckCustomer truckCustomer;
    private User user;
    
    /** 所属区域 */
    private String region;
    /** 注册时间 */
    private String createDate;
    /** 邀请人 */
    private String inviteUserName;
    /** 注册时间 */
    private Date createTime;
    /** 平均分 */
    private float score;
    private boolean hasTruckFleet = false;
    /**客户经理客户数*/
    private int count;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TruckCustomer getTruckCustomer() {
        return truckCustomer;
    }

    public void setTruckCustomer(TruckCustomer truckCustomer) {
        this.truckCustomer = truckCustomer;
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

    public float getScore() {
        return score == 0.0f ? 5f : score;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getInviteUserName() {
        return inviteUserName;
    }

    public void setInviteUserName(String inviteUserName) {
        this.inviteUserName = inviteUserName;
    }

}
