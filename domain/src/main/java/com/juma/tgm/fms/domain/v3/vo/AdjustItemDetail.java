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
 * @create: 2019-05-17 17:38
 **/
public class AdjustItemDetail extends AdjustForItem implements Serializable {

	@ApiModelProperty("项目名称")
	private String projectName;

	@ApiModelProperty("承运商名称")
	private String vendorName;

	@ApiModelProperty("司机名称")
	private String driverName;

	@ApiModelProperty("车牌号")
	private String plateNumber;

	@ApiModelProperty("调整后金额")
	private BigDecimal adjustAfterFright;

	@ApiModelProperty("调整金额")
	private BigDecimal adjustFright;

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

	public BigDecimal getAdjustAfterFright() {
		return adjustAfterFright;
	}

	public void setAdjustAfterFright(BigDecimal adjustAfterFright) {
		this.adjustAfterFright = adjustAfterFright;
	}

	public BigDecimal getAdjustFright() {
		return adjustFright;
	}

	public void setAdjustFright(BigDecimal adjustFright) {
		this.adjustFright = adjustFright;
	}
}
