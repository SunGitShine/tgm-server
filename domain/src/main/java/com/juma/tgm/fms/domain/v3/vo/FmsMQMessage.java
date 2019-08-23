package com.juma.tgm.fms.domain.v3.vo;

import java.io.Serializable;

import com.juma.fms.v2.mq.dto.FmsMQMessageDTO;

public class FmsMQMessage implements Serializable{

	private String businessSystem = "TMS";

	private FmsMQMessageDTO data;

	public String getBusinessSystem() {
		return businessSystem;
	}

	public void setBusinessSystem(String businessSystem) {
		this.businessSystem = businessSystem;
	}

	public FmsMQMessageDTO getData() {
		return data;
	}

	public void setData(FmsMQMessageDTO data) {
		this.data = data;
	}
}
