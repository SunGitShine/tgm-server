/**
* @Title: GaodeTip.java
* @Package com.juma.tgm.gaode.domain
*<B>Copyright</B> Copyright (c) 2016 www.jumapeisong.com All rights reserved. <br />
* 本软件源代码版权归驹马,未经许可不得任意复制与传播.<br />
* <B>Company</B> 驹马配送
* @date 2016年5月25日 下午8:15:34
* @version V1.0  
 */
package com.juma.tgm.gaode.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *@Description: 高德 输入提示api
 *@author zxh
 *@date 2016年5月25日 下午8:15:34
 *@version V1.0  
 */
public class GaodeInputtipsResponse implements Serializable {

	private static final long serialVersionUID = -2924467564795633348L;

	private String status;
	
	private List<Tip> tips = new ArrayList<Tip>();
	
	public class Tip implements Serializable {

		private static final long serialVersionUID = 132398448147480537L;

		private String id;
		
		private String name;
		
		private String district;
		
		private String address;
		
		private String location;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDistrict() {
			return district;
		}

		public void setDistrict(String district) {
			this.district = district;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Tip> getTips() {
		return tips;
	}

	public void setTips(List<Tip> tips) {
		this.tips = tips;
	}
	
}
