package com.juma.tgm.project.vo.v2;

import java.io.Serializable;
import java.util.List;

import com.juma.tgm.project.domain.v2.Project;

public class ProjectParamVo extends Project implements Serializable{

	private Integer crmCustomerId;
	private Integer customerManagerId;
	private String customerName;
	private List<Integer> customerIds;
	private List<Integer> customerManagerIdList;
	private List<String> customerAreaCodeList;
	private List<String> areaCodeList;
	private List<Integer> projectStatusList;
	private List<Integer> projectIdList;

	public Integer getCrmCustomerId() {
		return crmCustomerId;
	}

	public void setCrmCustomerId(Integer crmCustomerId) {
		this.crmCustomerId = crmCustomerId;
	}

	public Integer getCustomerManagerId() {
		return customerManagerId;
	}

	public void setCustomerManagerId(Integer customerManagerId) {
		this.customerManagerId = customerManagerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public List<Integer> getCustomerIds() {
		return customerIds;
	}

	public void setCustomerIds(List<Integer> customerIds) {
		this.customerIds = customerIds;
	}

	public List<Integer> getCustomerManagerIdList() {
		return customerManagerIdList;
	}

	public void setCustomerManagerIdList(List<Integer> customerManagerIdList) {
		this.customerManagerIdList = customerManagerIdList;
	}

	public List<String> getCustomerAreaCodeList() {
		return customerAreaCodeList;
	}

	public void setCustomerAreaCodeList(List<String> customerAreaCodeList) {
		this.customerAreaCodeList = customerAreaCodeList;
	}

	public List<String> getAreaCodeList() {
		return areaCodeList;
	}

	public void setAreaCodeList(List<String> areaCodeList) {
		this.areaCodeList = areaCodeList;
	}

	public List<Integer> getProjectStatusList() {
		return projectStatusList;
	}

	public void setProjectStatusList(List<Integer> projectStatusList) {
		this.projectStatusList = projectStatusList;
	}

	public List<Integer> getProjectIdList() {
		return projectIdList;
	}

	public void setProjectIdList(List<Integer> projectIdList) {
		this.projectIdList = projectIdList;
	}
}
