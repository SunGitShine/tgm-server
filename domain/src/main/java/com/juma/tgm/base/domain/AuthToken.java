/**
* @Title: AuthToken.java
* @Package com.juma.tgm.gateway.customer.vo
*<B>Copyright</B> Copyright (c) 2016 www.jumapeisong.com All rights reserved. <br />
* 本软件源代码版权归驹马,未经许可不得任意复制与传播.<br />
* <B>Company</B> 驹马配送
* @date 2016年9月14日 上午11:30:08
* @version V1.0  
 */
package com.juma.tgm.base.domain;

import java.io.Serializable;

/**
 * @Description:
 * @author Administrator
 * @date 2016年9月14日 上午11:30:08
 * @version V1.0
 */
public class AuthToken implements Serializable {

    private static final long serialVersionUID = 8511493471159238436L;
    private String token;
    private String location;
    private String deviceNo;
    private Integer deviceType;
    private String inviteCode;
    private String userType;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * 登录是区分用户类型
     */
    public enum UserType{
        SINGAL(1, "个人货主"),
        AGENT(2, "客户经理");
        private int code;
        private String desc;

         UserType(int code, String desc){
             this.code = code;
             this.desc = desc;
        }

        public int getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }

    }
}
