package com.juma.tgm.waybill.domain.bo;

import java.io.Serializable;

public class PrePaySign implements Serializable {

	private static final long serialVersionUID = -1032440565427116680L;

	private String cryptographicData;
	
	private String sign;
	
	public PrePaySign() {}
	
	public PrePaySign(String cryptographicData,String sign) {
		this.cryptographicData = cryptographicData;
		this.sign = sign;
	}
	
	

	public String getCryptographicData() {
		return cryptographicData;
	}

	public void setCryptographicData(String cryptographicData) {
		this.cryptographicData = cryptographicData;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
	
}
