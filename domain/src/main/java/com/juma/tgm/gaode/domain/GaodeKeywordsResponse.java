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

import com.alibaba.fastjson.annotation.JSONField;

/**
 *@Description: 高德 关键字搜索api
 *@author zxh
 *@date 2016年5月25日 下午8:15:34
 *@version V1.0  
 */
public class GaodeKeywordsResponse implements Serializable {

	private static final long serialVersionUID = -3649191970481095407L;

	private String status;
	
	private List<Poi> pois = new ArrayList<Poi>();
	
	public class Poi implements Serializable {

		private static final long serialVersionUID = 4731609813399451691L;

		private String name;
		
		private String pname;
		
		private String cityname;
		
		private String adname;
		
		private String location;
		
		@JSONField(serialize=false)
		private String address;
		@SuppressWarnings("all")
		private String addressDetail;
		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getPname() {
			return pname;
		}

		public void setPname(String pname) {
			this.pname = pname;
		}

		public String getCityname() {
			return cityname;
		}

		public void setCityname(String cityname) {
			this.cityname = cityname;
		}

		public String getAdname() {
			return adname;
		}

		public void setAdname(String adname) {
			this.adname = adname;
		}

		public String getAddressDetail() {
			return pname.equals(cityname)?cityname+adname+address:pname+cityname+adname+address;
		}

		public void setAddressDetail(String addressDetail) {
			this.addressDetail = addressDetail;
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

	public List<Poi> getPois() {
		return pois;
	}

	public void setPois(List<Poi> pois) {
		this.pois = pois;
	}
}
