/**
* @Title: GaodeGeocode.java
* @Package com.juma.tgm.gaode.domain
*<B>Copyright</B> Copyright (c) 2016 www.jumapeisong.com All rights reserved. <br />
* 本软件源代码版权归驹马,未经许可不得任意复制与传播.<br />
* <B>Company</B> 驹马配送
* @date 2016年9月21日 下午2:23:58
* @version V1.0  
 */
package com.juma.tgm.gaode.domain;

import java.io.Serializable;

/**
 *@Description: 
 *@author Administrator
 *@date 2016年9月21日 下午2:23:58
 *@version V1.0  
 */
public class Geocode implements Serializable {

	private static final long serialVersionUID = 5308031610348850148L;

	private String province;
	
	private String city;
	
	private String district;
	
	private String location;

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
}
