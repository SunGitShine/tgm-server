package com.juma.tgm.waybill.domain;

import java.io.Serializable;
import java.util.List;

import com.juma.tgm.crm.domain.CustomerInfo;
import com.juma.tgm.customer.domain.TruckCustomer;
import com.juma.tgm.driver.domain.Driver;

/**
 * 
 * @Description: 报表条件
 * @author weilibin
 * @date 2016年8月5日 下午2:46:14
 * @version V1.0
 */

public class ReportQueryDomain implements Serializable {

	private static final long serialVersionUID = 8426135618817196579L;
	/** 货主端当前的司机的下拉 */
    private List<Driver> driverList;
    /** 司机端当前的发货人的下拉 */
    private List<TruckCustomer> truckCustomerList;
    /** 当前的大客户的下拉 */
    private List<CustomerInfo> customerInfoList;

    public List<Driver> getDriverList() {
        return driverList;
    }

    public void setDriverList(List<Driver> driverList) {
        this.driverList = driverList;
    }

    public List<TruckCustomer> getTruckCustomerList() {
        return truckCustomerList;
    }

    public void setTruckCustomerList(List<TruckCustomer> truckCustomerList) {
        this.truckCustomerList = truckCustomerList;
    }

	public List<CustomerInfo> getCustomerInfoList() {
		return customerInfoList;
	}

	public void setCustomerInfoList(List<CustomerInfo> customerInfoList) {
		this.customerInfoList = customerInfoList;
	}


}
