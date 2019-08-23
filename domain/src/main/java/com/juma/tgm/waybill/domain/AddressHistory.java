/**
* @Title: AddressHistory.java
* @Package com.juma.tgm.waybill.domain
*<B>Copyright</B> Copyright (c) 2016 www.jumapeisong.com All rights reserved. <br />
* 本软件源代码版权归驹马,未经许可不得任意复制与传播.<br />
* <B>Company</B> 驹马配送
* @date 2016年7月4日 下午1:35:50
* @version V1.0  
 */
package com.juma.tgm.waybill.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.juma.tgm.base.domain.BaseDomain;

/**
 * @Description: 历史地址
 * @author Administrator
 * @date 2016年7月4日 下午1:35:50
 * @version V1.0
 */
public class AddressHistory extends BaseDomain {

    private static final long serialVersionUID = -8519729934832958400L;

    private Integer addressId;


    private String regionCode;

    private String addressName;

    private String addressDetail;

    private String location;

    /**
     * 出发地或到达地
     */
    private Integer addressType;

    /**
     * 当前登录人id ????
     */
    private Integer userId;

    private Date createTime;

    private List<ContactHistory> contact = new ArrayList<ContactHistory>();

    public enum AddressType {
        START(0, "出发地"), END(1, "目的地");

        private int code;

        private String descr;

        private AddressType(int code, String descr) {
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

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getAddressType() {
        return addressType;
    }

    public void setAddressType(Integer addressType) {
        this.addressType = addressType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public List<ContactHistory> getContact() {
        return contact;
    }

    public void setContact(List<ContactHistory> contact) {
        this.contact = contact;
    }

}
