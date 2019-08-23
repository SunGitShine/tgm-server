package com.juma.tgm.user.domain;

import com.juma.tgm.base.domain.BaseDomain;

import java.util.Date;

/**
 * user_route_master - 用户常用路线
 * 
 * @author  2017-06-29
 * @version 1.0 
 */
public class UserRouteMaster extends BaseDomain{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -7532968945484019448L;
    private Integer routeMasterId;
    
    /**
     * @since com.juma.tgm.waybill.domain.Waybill businessBranch
     */
    private Integer businessBranch;
    private Integer tenantId;
	private Integer userId;
	private String city;
	private String region;
	private String deliveryAddressName;
	private String deliveryAddress;
	private String deliveryAddressContactName;
	private String deliveryAddressContactPhone;
	private String location;
	private String md5Digest;
	private Date createTime;
	private Integer createUserId;
	private Date lastUpdateTime;
	private Integer lastUpdateUserId;

	public enum BusinessBranch{
		TYPE_SINGLE(1, "单收货地"),
		TYPE_MULTIPLY(2, "多收货地");

		private Integer code;
		private String desc;

		BusinessBranch(int code, String desc){
			this.code = code;
			this.desc = desc;
		}

		public Integer getCode() {
			return code;
		}
	}

	public Integer getRouteMasterId() {
		return routeMasterId;
	}

	public void setRouteMasterId(Integer routeMasterId) {
		this.routeMasterId = routeMasterId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getDeliveryAddressName() {
		return deliveryAddressName;
	}

	public void setDeliveryAddressName(String deliveryAddressName) {
		this.deliveryAddressName = deliveryAddressName;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getDeliveryAddressContactName() {
		return deliveryAddressContactName;
	}

	public void setDeliveryAddressContactName(String deliveryAddressContactName) {
		this.deliveryAddressContactName = deliveryAddressContactName;
	}

	public String getDeliveryAddressContactPhone() {
		return deliveryAddressContactPhone;
	}

	public void setDeliveryAddressContactPhone(String deliveryAddressContactPhone) {
		this.deliveryAddressContactPhone = deliveryAddressContactPhone;
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

    public String getMd5Digest() {
        return md5Digest;
    }

    public void setMd5Digest(String md5Digest) {
        this.md5Digest = md5Digest;
    }

    public Integer getBusinessBranch() {
        return businessBranch;
    }

    public void setBusinessBranch(Integer businessBranch) {
        this.businessBranch = businessBranch;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

}