package com.juma.tgm.customer.domain.vo;

import com.juma.tgm.customer.domain.TruckCustomer;

import java.io.Serializable;

/**
 * @ClassName: CargoOwnerCustomerBo
 * @Description:
 * @author: liang
 * @date: 2017-06-02 13:41
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
public class CargoOwnerCustomerVo implements Serializable {
    /**
     * 用车人已存在
     */
    public static final int FLAG_EXSIT = 1;

    private TruckCustomer truckCustomer;

    private String msg;

    /**
     * 用车人已存在:1
     */
    private Integer flag;


    public CargoOwnerCustomerVo(){}

    public CargoOwnerCustomerVo(TruckCustomer truckCustomer, String msg) {
        this.truckCustomer = truckCustomer;
        this.msg = msg;
    }

    public TruckCustomer getTruckCustomer() {
        return truckCustomer;
    }

    public void setTruckCustomer(TruckCustomer truckCustomer) {
        this.truckCustomer = truckCustomer;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}
