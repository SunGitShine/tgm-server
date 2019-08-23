package com.juma.tgm.customer.domain;


import java.io.Serializable;

/**
 * cargo_owner_customer - <��>
 * 
 * @author  2017-05-18
 * @version 1.0 
 */
public class CargoOwnerCustomer implements Serializable{
	private Integer truckCustomerId;
	private Integer customerInfoId;

	public Integer getTruckCustomerId() {
		return truckCustomerId;
	}

	public void setTruckCustomerId(Integer truckCustomerId) {
		this.truckCustomerId = truckCustomerId;
	}

	public Integer getCustomerInfoId() {
		return customerInfoId;
	}

	public void setCustomerInfoId(Integer customerInfoId) {
		this.customerInfoId = customerInfoId;
	}

}