/**
* @Title: ReportLocation.java
* @Package com.juma.tgm.gaode.domain
*<B>Copyright</B> Copyright (c) 2016 www.jumapeisong.com All rights reserved. <br />
* 本软件源代码版权归驹马,未经许可不得任意复制与传播.<br />
* <B>Company</B> 驹马配送
* @date 2016年7月21日 上午10:19:23
* @version V1.0  
 */
package com.juma.tgm.gaode.domain;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

/**
 *@Description: 坐标上报
 *@author Administrator
 *@date 2016年7月21日 上午10:19:23
 *@version V1.0  
 */
public class DriverLocation implements Serializable {

	private static final long serialVersionUID = -2544462860966529470L;

	@JSONField(serialize=false)
	private Integer locationId;
	
	@JSONField(name="_name")
	private String name = "司机位置";
	
	@JSONField(name="_location")
	private String location;
	
	private Integer coordtype = 1;
	
	@JSONField(name="_address")
	private String address;
	
	@JSONField(name="user_id")
	private Integer userId;
	
	@JSONField(name="waybill_id")
	private Integer waybillId = 0;

	@JSONField(serialize=false)
	private Date createTime;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getCoordtype() {
		return coordtype;
	}

	public void setCoordtype(Integer coordtype) {
		this.coordtype = coordtype;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String toJson() {
		return JSON.toJSONString(this);
	}

	public Integer getWaybillId() {
		return waybillId;
	}

	public void setWaybillId(Integer waybillId) {
		this.waybillId = waybillId;
	}
	
}
