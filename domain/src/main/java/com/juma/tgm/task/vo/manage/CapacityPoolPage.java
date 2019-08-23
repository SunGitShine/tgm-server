package com.juma.tgm.task.vo.manage;

import java.io.Serializable;

import com.juma.vms.transport.domain.CapacityPool;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @description: ${description}
 *
 * @author: xieqiang
 *
 * @create: 2019-07-12 17:03
 **/
@ApiModel(value = "运力信息")
public class CapacityPoolPage extends CapacityPool implements Serializable{

	@ApiModelProperty(value = "承运商名称")
	private String vendorName;

	@ApiModelProperty(value = "司机名称")
	private String driverName;

	@ApiModelProperty(value = "承运商运营类型名称")
	private String vendorRunTypeName;

	@ApiModelProperty(value = "车牌")
	private String plateNumber;

	@ApiModelProperty(value = "车型名称")
	private String truckTypeName;

	@ApiModelProperty(value = "业务区域名称")
	private String areaCodeName;

	@ApiModelProperty(value = "车辆运营类型名称")
	private String truckRunTypeName;

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

	public String getVendorRunTypeName() {
		return vendorRunTypeName;
	}

	public void setVendorRunTypeName(String vendorRunTypeName) {
		this.vendorRunTypeName = vendorRunTypeName;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public String getTruckTypeName() {
		return truckTypeName;
	}

	public void setTruckTypeName(String truckTypeName) {
		this.truckTypeName = truckTypeName;
	}

	public String getAreaCodeName() {
		return areaCodeName;
	}

	public void setAreaCodeName(String areaCodeName) {
		this.areaCodeName = areaCodeName;
	}

	public String getTruckRunTypeName() {
		return truckRunTypeName;
	}

	public void setTruckRunTypeName(String truckRunTypeName) {
		this.truckRunTypeName = truckRunTypeName;
	}
}
