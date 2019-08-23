package com.juma.tgm.customer.domain.vo;

import java.io.Serializable;

/**
 * @description: ${description}
 *
 * @author: xieqiang
 *
 * @create: 2019-07-17 18:24
 **/
public class CustomerInfoFilter implements Serializable{

	/**
	 * 客户名称
	 */
	private String customerName;
	/**
	 * 客户类型 1、司机，2、货主
	 */
	private Integer customerType;

	/**
	 * 2、已淘汰
	 */
	private Integer statusNotEquals;

	private Integer pageSize = 15;

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Integer getCustomerType() {
		return customerType;
	}

	public void setCustomerType(Integer customerType) {
		this.customerType = customerType;
	}

	public Integer getStatusNotEquals() {
		return statusNotEquals;
	}

	public void setStatusNotEquals(Integer statusNotEquals) {
		this.statusNotEquals = statusNotEquals;
	}
}
