package com.juma.tgm.fms.domain.v3.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.juma.tgm.fms.domain.v3.AdjustForItem;
import io.swagger.annotations.ApiModelProperty;

/**
 * @description: ${description}
 *
 * @author: xieqiang
 *
 * @create: 2019-05-20 16:39
 **/
public class AdjustForItemDetail extends AdjustForItem implements Serializable{

	@ApiModelProperty("项目名称")
	private String projectName;

	@ApiModelProperty("承运商名称")
	private String vendorName;

	@ApiModelProperty("司机名称")
	private String driverName;

	@ApiModelProperty("车牌")
	private String plateNumber;

	@ApiModelProperty("调整金额")
	private BigDecimal adjustAmount;

	@ApiModelProperty("调整后金额")
	private BigDecimal adjustAfterAmount;

	public BigDecimal getAdjustAmount() {
		return adjustAmount;
	}

	public void setAdjustAmount(BigDecimal adjustAmount) {
		this.adjustAmount = adjustAmount;
	}

	public BigDecimal getAdjustAfterAmount() {
		return adjustAfterAmount;
	}

	public void setAdjustAfterAmount(BigDecimal adjustAfterAmount) {
		this.adjustAfterAmount = adjustAfterAmount;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
}
