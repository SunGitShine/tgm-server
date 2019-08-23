package com.juma.tgm.customer.domain.vo;

import com.juma.tgm.crm.domain.CustomerInfo;
import com.juma.tgm.customer.domain.TruckCustomer;

import java.io.Serializable;

/**
 * 用于新增企业用车人
 * @ClassName: CargoOwnerVo
 * @Description:
 * @author: liang
 * @date: 2017-05-18 15:09
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
public class CargoOwnerVo implements Serializable {

    private CustomerInfo customerInfo;

    private TruckCustomer truckCustomer;


    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }

    public TruckCustomer getTruckCustomer() {
        return truckCustomer;
    }

    public void setTruckCustomer(TruckCustomer truckCustomer) {
        this.truckCustomer = truckCustomer;
    }
}
