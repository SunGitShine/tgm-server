package com.juma.tgm.customer.domain.vo;

import com.juma.auth.user.domain.UserInfo;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: CargoOwnerUserInfo
 * @Description:
 * @author: liang
 * @date: 2017-11-17 14:47
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
public class CargoOwnerUserInfo implements Serializable {
    //用户中心基础数据
    private UserInfo userInfo;
    //crm货主业务数据

    //tgm业务数据
    /**
     * 累计运费
     */
    private String totalFreight;

    /**
     * 累计运单数
     */
    private String totalBillAmount;

    /**
     * 服务器时间
     */
    private Date cDate;


    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getTotalFreight() {
        return totalFreight;
    }

    public void setTotalFreight(String totalFreight) {
        this.totalFreight = totalFreight;
    }

    public String getTotalBillAmount() {
        return totalBillAmount;
    }

    public void setTotalBillAmount(String totalBillAmount) {
        this.totalBillAmount = totalBillAmount;
    }

    public Date getcDate() {
        if(this.cDate == null){
            this.cDate = new Date();
        }
        return cDate;
    }

    public void setcDate(Date cDate) {
        this.cDate = cDate;
    }
}
