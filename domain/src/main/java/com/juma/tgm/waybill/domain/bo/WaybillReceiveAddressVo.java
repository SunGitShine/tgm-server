package com.juma.tgm.waybill.domain.bo;

import com.juma.tgm.waybill.domain.WaybillReceiveAddress;

import java.beans.Transient;
import java.io.Serializable;

/**
 *
 * 请注意这里设置vo属性值时<b>同时</b>设置了父类相应属性，减少逻辑代码量
 * @ClassName: WaybillReceiveAddressVo
 * @Description:
 * @author: liang
 * @date: 2017-05-02 14:34
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
public class WaybillReceiveAddressVo extends WaybillReceiveAddress implements Serializable {

    private String receiveAddressName;

    private String receiveAddressDetail;

    private String receiveContactName;

    private String receiveContactPhone;

    private String receiveRegionCode;

    private String receiveCoordinates;


    public String getReceiveAddressName() {
        return receiveAddressName;
    }

    public void setReceiveAddressName(String receiveAddressName) {
        this.receiveAddressName = receiveAddressName;
        super.setAddressName(receiveAddressName);
    }

    public String getReceiveAddressDetail() {
        return receiveAddressDetail;
    }

    public void setReceiveAddressDetail(String receiveAddressDetail) {
        this.receiveAddressDetail = receiveAddressDetail;
        super.setAddressDetail(receiveAddressDetail);
    }

    public String getReceiveContactName() {
        return receiveContactName;
    }

    public void setReceiveContactName(String receiveContactName) {
        this.receiveContactName = receiveContactName;
        super.setContactName(receiveContactName);
    }

    public String getReceiveContactPhone() {
        return receiveContactPhone;
    }

    public void setReceiveContactPhone(String receiveContactPhone) {
        this.receiveContactPhone = receiveContactPhone;
        super.setContactPhone(receiveContactPhone);

    }

    public String getReceiveRegionCode() {
        return receiveRegionCode;
    }

    public void setReceiveRegionCode(String receiveRegionCode) {
        this.receiveRegionCode = receiveRegionCode;
        super.setRegionCode(receiveRegionCode);
    }

    public String getReceiveCoordinates() {
        return receiveCoordinates;
    }

    public void setReceiveCoordinates(String receiveCoordinates) {
        this.receiveCoordinates = receiveCoordinates;

        super.setCoordinates(receiveCoordinates);
    }
}
