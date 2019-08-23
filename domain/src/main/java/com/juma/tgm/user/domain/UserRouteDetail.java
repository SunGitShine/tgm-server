package com.juma.tgm.user.domain;

import java.util.Date;

import com.juma.tgm.base.domain.BaseDomain;
/**
 * user_route_detail - user_route_detail
 * 
 * @author  2017-06-29
 * @version 1.0 
 */
public class UserRouteDetail extends BaseDomain{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 4587580817431324923L;
    private Integer routeDetailId;
	private Integer routeMasterId;
	private String receiveAddressName;
	private String receiveAddress;
	private String receiveAddressContactName;
	private String receiveAddressContactPhone;
	private String location;
	private String city;
    private String region;
	private Date createTime;
	private Integer createUserId;
	private Date lastUpdateTime;
	private Integer lastUpdateUserId;

	public Integer getRouteDetailId() {
		return routeDetailId;
	}

	public void setRouteDetailId(Integer routeDetailId) {
		this.routeDetailId = routeDetailId;
	}

	public Integer getRouteMasterId() {
		return routeMasterId;
	}

	public void setRouteMasterId(Integer routeMasterId) {
		this.routeMasterId = routeMasterId;
	}

	public String getReceiveAddressName() {
		return receiveAddressName;
	}

	public void setReceiveAddressName(String receiveAddressName) {
		this.receiveAddressName = receiveAddressName;
	}

	public String getReceiveAddress() {
		return receiveAddress;
	}

	public void setReceiveAddress(String receiveAddress) {
		this.receiveAddress = receiveAddress;
	}

	public String getReceiveAddressContactName() {
		return receiveAddressContactName;
	}

	public void setReceiveAddressContactName(String receiveAddressContactName) {
		this.receiveAddressContactName = receiveAddressContactName;
	}

	public String getReceiveAddressContactPhone() {
		return receiveAddressContactPhone;
	}

	public void setReceiveAddressContactPhone(String receiveAddressContactPhone) {
		this.receiveAddressContactPhone = receiveAddressContactPhone;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Integer getLastUpdateUserId() {
		return lastUpdateUserId;
	}

	public void setLastUpdateUserId(Integer lastUpdateUserId) {
		this.lastUpdateUserId = lastUpdateUserId;
	}

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

}