package com.juma.tgm.fms.domain.v3.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class ReceivableApplyVo implements Serializable{

	/**
	 * 客户id
	 */
	private Integer customerId;
	/**
	 * 项目id
	 */
	private Integer projectId;
	/**
	 * 客户名称
	 */
	private String customerName;
	/**
	 * 项目名称
	 */
	private String projectName;
	/**
	 * 含税价
	 */
	private BigDecimal estimateFreight;
	/**
	 * 不含税价
	 */
	private BigDecimal afterTaxFreight;

	/**
	 * 子公司id
	 */
	private Integer departmentId;

	/**
	 * 子公司名字
	 */
	private String departmentName;
	
	/**
	 * 运营主体id
	 */
	private Integer payToCompany;
	
	/**
	 * 运营主体名字
	 */
	private String payToCompanyName;

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public BigDecimal getEstimateFreight() {
		return estimateFreight;
	}

	public void setEstimateFreight(BigDecimal estimateFreight) {
		this.estimateFreight = estimateFreight;
	}

	public BigDecimal getAfterTaxFreight() {
		return afterTaxFreight;
	}

	public void setAfterTaxFreight(BigDecimal afterTaxFreight) {
		this.afterTaxFreight = afterTaxFreight;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

    public Integer getPayToCompany() {
        return payToCompany;
    }

    public void setPayToCompany(Integer payToCompany) {
        this.payToCompany = payToCompany;
    }

    public String getPayToCompanyName() {
        return payToCompanyName;
    }

    public void setPayToCompanyName(String payToCompanyName) {
        this.payToCompanyName = payToCompanyName;
    }
}
