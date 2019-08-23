package com.juma.tgm.fms.domain.v3.vo;

import com.juma.tgm.fms.domain.v3.AdjustForMaster;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @description: ${description}
 *
 * @author: xieqiang
 *
 * @create: 2019-05-10 14:05
 **/
public class AdjustForMasterVo extends AdjustForMaster implements Serializable{

	@ApiModelProperty("调整人电话")
	private String createUserPhone;

	@ApiModelProperty("运单数量")
	private Integer waybillCount;

	@ApiModelProperty("调整比例")
	private BigDecimal adjustScale;

	@ApiModelProperty("业务区域")
	private List<String> areaCodeList;

	@ApiModelProperty("业务区域名称")
	private String areaName;

	private Date startTime;

	private Date endTime;

	public String getCreateUserPhone() {
		return createUserPhone;
	}

	public void setCreateUserPhone(String createUserPhone) {
		this.createUserPhone = createUserPhone;
	}

	public Integer getWaybillCount() {
		return waybillCount;
	}

	public void setWaybillCount(Integer waybillCount) {
		this.waybillCount = waybillCount;
	}

	public BigDecimal getAdjustScale() {
		return adjustScale;
	}

	public void setAdjustScale(BigDecimal adjustScale) {
		this.adjustScale = adjustScale;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public List<String> getAreaCodeList() {
		return areaCodeList;
	}

	public void setAreaCodeList(List<String> areaCodeList) {
		this.areaCodeList = areaCodeList;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
}
