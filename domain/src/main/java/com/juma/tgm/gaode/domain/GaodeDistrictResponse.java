/**
* @Title: GaodeDistrictResponse.java
* @Package com.juma.tgm.gaode.domain
*<B>Copyright</B> Copyright (c) 2016 www.jumapeisong.com All rights reserved. <br />
* 本软件源代码版权归驹马,未经许可不得任意复制与传播.<br />
* <B>Company</B> 驹马配送
* @date 2016年6月14日 下午7:21:50
* @version V1.0  
 */
package com.juma.tgm.gaode.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *@Description: 
 *@author Administrator
 *@date 2016年6月14日 下午7:21:50
 *@version V1.0  
 */
public class GaodeDistrictResponse implements Serializable {

	/**
	 * @Fields
	 */
	private static final long serialVersionUID = 2655626601612804700L;

	private String status;
	
	private String info;
	
	private String infocode;
	
	private List<District> districts = new ArrayList<District>();
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getInfocode() {
		return infocode;
	}

	public void setInfocode(String infocode) {
		this.infocode = infocode;
	}

	public List<District> getDistricts() {
		return districts;
	}

	public void setDistricts(List<District> districts) {
		this.districts = districts;
	}
}
