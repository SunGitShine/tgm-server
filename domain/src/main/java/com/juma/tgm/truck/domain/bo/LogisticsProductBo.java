package com.juma.tgm.truck.domain.bo;

import java.io.Serializable;

public class LogisticsProductBo implements Serializable{

	private String logisticsCode;

	private String logisticsName;

	public String getLogisticsCode() {
		return logisticsCode;
	}

	public void setLogisticsCode(String logisticsCode) {
		this.logisticsCode = logisticsCode;
	}

	public String getLogisticsName() {
		return logisticsName;
	}

	public void setLogisticsName(String logisticsName) {
		this.logisticsName = logisticsName;
	}
}
