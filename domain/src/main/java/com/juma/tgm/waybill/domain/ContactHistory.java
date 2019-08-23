/**
* @Title: ContactsHistory.java
* @Package com.juma.tgm.waybill.domain
*<B>Copyright</B> Copyright (c) 2016 www.jumapeisong.com All rights reserved. <br />
* 本软件源代码版权归驹马,未经许可不得任意复制与传播.<br />
* <B>Company</B> 驹马配送
* @date 2016年7月4日 下午1:36:37
* @version V1.0  
 */
package com.juma.tgm.waybill.domain;

import com.juma.tgm.base.domain.BaseDomain;

/**
 * @Description: 历史联系人
 * @author Administrator
 * @date 2016年7月4日 下午1:36:37
 * @version V1.0
 */
public class ContactHistory extends BaseDomain {

    private static final long serialVersionUID = 2816800604351082688L;

    private Integer contactId;

    private String contactName;

    private String contactPhone;

    private String spareContactPhone;

    private Integer addressId;
    
    private String md5;

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getSpareContactPhone() {
        return spareContactPhone;
    }

    public void setSpareContactPhone(String spareContactPhone) {
        this.spareContactPhone = spareContactPhone;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

}
