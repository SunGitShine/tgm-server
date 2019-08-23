/**
* @Title: AmapGeoResponse.java
* @Package com.juma.tgm.gaode.domain
*<B>Copyright</B> Copyright (c) 2016 www.jumapeisong.com All rights reserved. <br />
* 本软件源代码版权归驹马,未经许可不得任意复制与传播.<br />
* <B>Company</B> 驹马配送
* @date 2016年9月21日 下午2:24:51
* @version V1.0  
 */
package com.juma.tgm.gaode.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *@Description: 
 *@author Administrator
 *@date 2016年9月21日 下午2:24:51
 *@version V1.0  
 */
public class AmapGeoResponse implements Serializable{

	private static final long serialVersionUID = -6821420610738463002L;

	private Integer count;
    
    private Integer status;
    
    private List<Geocode> geocodes = new ArrayList<Geocode>();

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<Geocode> getGeocodes() {
		return geocodes;
	}

	public void setGeocodes(List<Geocode> geocodes) {
		this.geocodes = geocodes;
	}
	
}
