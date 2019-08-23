package com.juma.tgm.crm.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * private_customer - private_customer
 * 
 * @author  2017-03-13
 * @version 1.0 
 */
public class PrivateCustomer implements Serializable {
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -4149171142389342567L;
    private Integer privateCustomerId;
	private String name;
	private Integer userId;
	private String industry;
	private String regionCode;
	private String address;
	private String remark;
	private Integer managerUserId;
	private Integer departmentId;
	private Integer checkOut;
	private String contractNum;
	private String contractPic;
	private String identityNum;
	private String identityPic;
	private Date createTime;
	private Integer createUserId;
	private Date lastUpdateTime;
	private Integer lastUpdateUserId;
	
	//联系人
	private List<PrivateCustomerContacts> privateCustomerContacts = new ArrayList<PrivateCustomerContacts>();
	//登陆帐号
	private String userName;
	//客户经理帐号
	private String managerUserName;
	//地区
	private String regionName;
	
	public Integer getPrivateCustomerId() {
		return privateCustomerId;
	}

	public void setPrivateCustomerId(Integer privateCustomerId) {
		this.privateCustomerId = privateCustomerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getManagerUserId() {
		return managerUserId;
	}

	public void setManagerUserId(Integer managerUserId) {
		this.managerUserId = managerUserId;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(Integer checkOut) {
		this.checkOut = checkOut;
	}

	public String getContractNum() {
		return contractNum;
	}

	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}

	public String getContractPic() {
		return contractPic;
	}

	public void setContractPic(String contractPic) {
		this.contractPic = contractPic;
	}

	public String getIdentityNum() {
		return identityNum;
	}

	public void setIdentityNum(String identityNum) {
		this.identityNum = identityNum;
	}

	public String getIdentityPic() {
		return identityPic;
	}

	public void setIdentityPic(String identityPic) {
		this.identityPic = identityPic;
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

    public List<PrivateCustomerContacts> getPrivateCustomerContacts() {
        return privateCustomerContacts;
    }

    public void setPrivateCustomerContacts(List<PrivateCustomerContacts> privateCustomerContacts) {
        this.privateCustomerContacts = privateCustomerContacts;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getManagerUserName() {
        return managerUserName;
    }

    public void setManagerUserName(String managerUserName) {
        this.managerUserName = managerUserName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

}