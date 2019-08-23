package com.juma.tgm.fms.domain.v3.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="客户对账对象",description="客户对账对象")
public class ReconcilicationForReceivableVo implements Serializable{

	@ApiModelProperty(value="运单id列表",name="waybillIds")
	private List<Integer> waybillIds;

	@ApiModelProperty(value="业务区域",name="areaCode")
	private String areaCode;

	@ApiModelProperty(value="客户id",name="customerId")
	private Integer customerId;

	@ApiModelProperty(value="客户名称",name="customerName")
	private String customerName;

	@ApiModelProperty(value="项目id",name="projectId")
	private Integer projectId;

	@ApiModelProperty(value="项目名称",name="projectName")
	private String projectName;

	@ApiModelProperty(value="对账单号",name="reconcilicationNo")
	private String reconcilicationNo;

	@ApiModelProperty(value="含税总额",name="receivableWithTax")
	private BigDecimal receivableWithTax;

	@ApiModelProperty(value="不含税总额",name="receivableWithoutTax")
	private BigDecimal receivableWithoutTax;

	@ApiModelProperty(value="运单数量",name="waybillNum")
	private Integer waybillNum;

	@ApiModelProperty(value="收款状态",name="receiveStatus")
	private Integer receiveStatus;

	@ApiModelProperty(value="开票状态",name="invoiceStatus")
	private Integer invoiceStatus;

	@ApiModelProperty(value="审核状态，0：未提交，1：审核中，2：被驳回，3：已通过",name="approvalStatus")
	private Integer approvalStatus;

	private String departmentName;

	private Integer departmentId;
	
	/**
     * 运营主体id
     */
    private Integer payToCompany;
    
    /**
     * 运营主体名字
     */
    private String payToCompanyName;

	/**最终含税金额*/
	private BigDecimal sumFreightWithTax;
	/**最终不含税金额*/
	private BigDecimal sumFreightWithNotTax;
	/**承运侧含税金额*/
	private BigDecimal vendorFreightWithTax;
	/**承诺毛利率*/
	private String profitRatePromise;
	/**对账时毛利额*/
	private BigDecimal profit;
	/**首次对账毛利率*/
	private String profitRate;
	/**对账时与承诺毛利差*/
	private String profitRateDiffer;
	/**调整后的毛利额*/
	private BigDecimal adjustProfit;
	/**调整后的毛利率*/
	private String adjustProfitRate;
	/**调整后与承诺毛利率差*/
	private String adjustProfitRateDiffer;
	/**项目年份*/
	private Integer year;
	/**项目月份*/
	private Integer month;
	/**按月项目整体毛利额*/
	private BigDecimal monthProfit;
	/**按月项目整体毛利率*/
	private String monthProfitRate;
	/**按月项目整体毛利率差额*/
	private String monthProfitRateDiffer;

	public String getProfitRatePromise() {
		return profitRatePromise;
	}

	public void setProfitRatePromise(String profitRatePromise) {
		this.profitRatePromise = profitRatePromise;
	}

	public String getProfitRate() {
		return profitRate;
	}

	public void setProfitRate(String profitRate) {
		this.profitRate = profitRate;
	}

	public String getProfitRateDiffer() {
		return profitRateDiffer;
	}

	public void setProfitRateDiffer(String profitRateDiffer) {
		this.profitRateDiffer = profitRateDiffer;
	}

	public String getAdjustProfitRate() {
		return adjustProfitRate;
	}

	public void setAdjustProfitRate(String adjustProfitRate) {
		this.adjustProfitRate = adjustProfitRate;
	}

	public String getAdjustProfitRateDiffer() {
		return adjustProfitRateDiffer;
	}

	public void setAdjustProfitRateDiffer(String adjustProfitRateDiffer) {
		this.adjustProfitRateDiffer = adjustProfitRateDiffer;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public BigDecimal getMonthProfit() {
		return monthProfit;
	}

	public void setMonthProfit(BigDecimal monthProfit) {
		this.monthProfit = monthProfit;
	}

	public String getMonthProfitRate() {
		return monthProfitRate;
	}

	public void setMonthProfitRate(String monthProfitRate) {
		this.monthProfitRate = monthProfitRate;
	}

	public String getMonthProfitRateDiffer() {
		return monthProfitRateDiffer;
	}

	public void setMonthProfitRateDiffer(String monthProfitRateDiffer) {
		this.monthProfitRateDiffer = monthProfitRateDiffer;
	}

	public BigDecimal getVendorFreightWithTax() {
		return vendorFreightWithTax;
	}

	public void setVendorFreightWithTax(BigDecimal vendorFreightWithTax) {
		this.vendorFreightWithTax = vendorFreightWithTax;
	}

	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	public BigDecimal getAdjustProfit() {
		return adjustProfit;
	}

	public void setAdjustProfit(BigDecimal adjustProfit) {
		this.adjustProfit = adjustProfit;
	}

	public BigDecimal getSumFreightWithNotTax() {
		return sumFreightWithNotTax;
	}

	public void setSumFreightWithNotTax(BigDecimal sumFreightWithNotTax) {
		this.sumFreightWithNotTax = sumFreightWithNotTax;
	}

	public BigDecimal getSumFreightWithTax() {
		return sumFreightWithTax;
	}

	public void setSumFreightWithTax(BigDecimal sumFreightWithTax) {
		this.sumFreightWithTax = sumFreightWithTax;
	}

	public List<Integer> getWaybillIds() {
		return waybillIds;
	}

	public void setWaybillIds(List<Integer> waybillIds) {
		this.waybillIds = waybillIds;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getReconcilicationNo() {
		return reconcilicationNo;
	}

	public void setReconcilicationNo(String reconcilicationNo) {
		this.reconcilicationNo = reconcilicationNo;
	}

	public BigDecimal getReceivableWithTax() {
		return receivableWithTax;
	}

	public void setReceivableWithTax(BigDecimal receivableWithTax) {
		this.receivableWithTax = receivableWithTax;
	}

	public BigDecimal getReceivableWithoutTax() {
		return receivableWithoutTax;
	}

	public void setReceivableWithoutTax(BigDecimal receivableWithoutTax) {
		this.receivableWithoutTax = receivableWithoutTax;
	}

	public Integer getWaybillNum() {
		return waybillNum;
	}

	public void setWaybillNum(Integer waybillNum) {
		this.waybillNum = waybillNum;
	}

	public Integer getReceiveStatus() {
		return receiveStatus;
	}

	public void setReceiveStatus(Integer receiveStatus) {
		this.receiveStatus = receiveStatus;
	}

	public Integer getInvoiceStatus() {
		return invoiceStatus;
	}

	public void setInvoiceStatus(Integer invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}

	public Integer getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(Integer approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
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
